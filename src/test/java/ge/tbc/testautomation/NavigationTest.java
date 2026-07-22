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
import java.util.Map;

public class NavigationTest {
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
        driver.get(Constants.URL_ULTIMATE_QA_AUTOMATION);
    }

    @Test(priority = 1, description = "checking url navigation")
    public void goToServicesAndBack() {
        WebElement servicesLinkText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Services")));
        servicesLinkText.click();
        wait.until(ExpectedConditions.urlToBe(Constants.URL_ULTIMATE_QA_CONSULTING));
        Assert.assertEquals(driver.getCurrentUrl(), Constants.URL_ULTIMATE_QA_CONSULTING);
        driver.navigate().back();
        wait.until(ExpectedConditions.urlToBe(Constants.URL_ULTIMATE_QA_AUTOMATION));
        Assert.assertEquals(driver.getCurrentUrl(), Constants.URL_ULTIMATE_QA_AUTOMATION);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
