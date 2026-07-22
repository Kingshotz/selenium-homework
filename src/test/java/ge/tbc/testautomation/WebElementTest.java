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

public class WebElementTest {
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
        driver.get(Constants.URL_DRAG_AND_DROP);
    }

    @Test(priority = 1, description = "check dimmensions")
    public void dimensionsTest() {
        WebElement columnA = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("column-a")));
        WebElement columnB = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("column-b")));

        Assert.assertEquals(columnA.getLocation().getY(), columnB.getLocation().getY(), "Columns are on different heights");

        String draggableA = columnA.getAttribute("draggable");
        String draggableB = columnB.getAttribute("draggable");
        Assert.assertEquals(draggableA, "true", "Column A is not draggable");
        Assert.assertEquals(draggableB, "true", "Column B is not draggable");
    }

    @Test(priority = 2, description = "check href on 'Elemental Selenium'")
    public void linkTest() {
        WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Elemental Selenium")));
        Assert.assertEquals(name.getAttribute("href"), Constants.ELEMENTAL_SELENIUM_HREF, "Invalid href");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
