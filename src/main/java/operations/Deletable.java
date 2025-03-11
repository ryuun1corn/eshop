package operations;

import java.util.UUID;

public interface Deletable {
    boolean delete(UUID modelId);
}
