package ge.tbc.testautomation;

import com.github.javafaker.Faker;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.util.FormsTestHelper;
import ge.tbc.testautomation.util.Util;
import org.openqa.selenium.Alert;
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

public class FormsTest {
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
        FormsTestHelper.setWait(wait);
    }

    @Test(priority = 1, description = "tests custom drop down")
    public void FormsTest() {
        driver.get(Constants.URL_TYMPANUS_CUSTOM_DROP_DOWN);
        WebElement demo1 =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constants.XPATH_DEMO_1)));
        demo1.click();
        WebElement selector = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dd")));
        Util.universalSelector(selector, "Github");
    }

    @Test(priority = 2, description = "tests native drop down and filling whole form")
    public void nativeDropDownTest() {
        driver.get(Constants.URL_TECHCANVAS_REGISTER);
        WebElement maleRadio = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constants.XPATH_RADIO_MALE)));
        maleRadio.click();

        WebElement modelSelector = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constants.XPATH_MODEL_SELECTOR)));
        Util.universalSelector(modelSelector, Constants.XPATH_MODEL_OPTION_TEXT);

        FormsTestHelper.fillForm(FormsTestHelper.generateFakeFormData());

        //clicks register button
        wait.until(ExpectedConditions.elementToBeClickable((By.xpath(Constants.XPATH_REGISTER_BUTTON)))).click();

        //imedia ar ikneba problema Asssertionebis damatebaze
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        Assert.assertEquals(alertText, Constants.ALERT_TEXT);
        alert.accept();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
