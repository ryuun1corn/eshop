package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class HomePageFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    WebDriver driver;

    private static final String chromeDriverPath = "/home/ryuun1corn/.nix-profile/bin/chromedriver";
    private static final String chromeBinaryPath = "/run/current-system/sw/bin/google-chrome-stable";

    @BeforeAll
    static void setupClass() {
        Optional<Path> browserPath = WebDriverManager.chromedriver().getBrowserPath();

        if (browserPath.isEmpty()) {
            System.out.println("No browser detected, using custom paths...");
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            System.setProperty("webdriver.chrome.binary", chromeBinaryPath);
        } else {
            System.out.println("Browser detected at: " + browserPath.get());

            // Use WebDriverManager to manage the driver only if no browser was found
            WebDriverManager.chromedriver().setup();
        }

    }

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
        ChromeOptions options = new ChromeOptions();

        options.setBinary(System.getProperty("webdriver.chrome.binary"));

        driver = new ChromeDriver(options);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void pageTitle_isCorrect() throws Exception {
        driver.get(baseUrl);
        String pageTitle = driver.getTitle();

        assertEquals("ADV Shop", pageTitle);
    }

    @Test
    void welcomeMessage_homePage_isCorrect() throws Exception {
        driver.get(baseUrl);
        String welcomeMessage = driver.findElement(By.tagName("h3")).getText();

        assertEquals("Welcome", welcomeMessage);
    }
}
