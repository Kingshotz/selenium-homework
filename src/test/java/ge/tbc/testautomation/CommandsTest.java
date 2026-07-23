package ge.tbc.testautomation;

import ge.tbc.testautomation.data.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class CommandsTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "profile.password_manager_leak_detection", false
        ));
        options.addArguments("--disable-features=PasswordLeakDetection,AutofillServerCommunication");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(Constants.URL_DYNAMIC_CONTROLS);
    }

    @Test(priority = 1, description = "Check header and description text")
    public void labelsTest() {
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h4")));
        Assert.assertEquals(heading.getText(), Constants.HEADER_TEXT, "Header text is not found");

        WebElement desc = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("p")));
        Assert.assertEquals(desc.getText(), Constants.DESCRIPTION_TEXT, "Description text is not found");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
