FROM docker.io/library/eclipse-temurin:24-jdk-alpine@sha256:8668c919ce07d8470e70101ca3bab3f71d239c050a6cdb1f989667c606abd35a AS builder

WORKDIR /src/advshop
COPY . .
RUN ./gradlew clean bootjar

FROM docker.io/library/eclipse-temurin:24-jdk-alpine@sha256:8668c919ce07d8470e70101ca3bab3f71d239c050a6cdb1f989667c606abd35a AS runner

ARG USER_NAME=advshop
ARG USER_UID=1000
ARG USER_GID=${USER_UID}

RUN addgroup -g ${USER_GID} ${USER_NAME} \
    && adduser -h /opt/advshop -D -u ${USER_UID} -G ${USER_NAME} ${USER_NAME}

USER ${USER_NAME}
WORKDIR /opt/advshop
COPY --from=builder --chown=${USER_UID}:${USER_GID} /src/advshop/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java"]
CMD ["-jar", "app.jar"]
