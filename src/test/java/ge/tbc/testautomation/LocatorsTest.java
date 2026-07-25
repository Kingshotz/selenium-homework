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

public class LocatorsTest {
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
        driver.get(Constants.URL_JQUERY_SLIDER);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test(priority = 1, description = "checking url navigation")
    public void unorderedListTest() {
        List<WebElement> effects = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(Constants.EFFECTS_XPATH))
        );
        List<WebElement> filteredEffects = effects.stream()
                .filter(li -> li.getText().contains("o"))
                .toList();

        filteredEffects.parallelStream().forEach(li -> {
            WebElement aTag = li.findElement(By.tagName("a"));
            String href = aTag.getAttribute("href");
            if (href != null && !href.contains("animate")) {
                System.out.println(href);
            }
        });
    }

    @Test(priority = 2 ,description = "buttons test")
    public void buttonsTest() {
        driver.get(Constants.URL_INTERNET_HOOKUP_ADD_OR_REMOVE);
        WebElement addElementButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constants.ADD_ELEMENT_XPATH)));
        for(int i=0;i<3;i++){
            addElementButton.click();
        }
        WebElement lastDelete = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(Constants.LAST_DELETE_BUTTON_SELECTOR))));

        String className = lastDelete.getAttribute("class");
        Assert.assertEquals(className, Constants.ADDED_MANUALLY_CLASS, "delete button doesnt have added-manually class");


        WebElement deleteButton = driver.findElements(By.cssSelector(Constants.DELETE_BUTTONS_SELECTOR)).stream().
                reduce((first, second) -> second).orElseThrow();

        String onClick = deleteButton.getAttribute("onclick");
        Assert.assertEquals(onClick, Constants.DELETE_ELEMENT_ONCLICK, "delete button onclick attribute is not deleteElement()");
    }

    @Test(priority = 3, description = "challenging dom test")
    public void challengingDomTest() {
        driver.get(Constants.URL_CHALLENGING_DOM);

        WebElement loremForApeirian9 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constants.LOREM_BY_IPSUM_VALUE_XPATH)));
        String loremValue = loremForApeirian9.getText();
        System.out.println("Lorem value for Ipsum='Apeirian9': " + loremValue);
    }
}
