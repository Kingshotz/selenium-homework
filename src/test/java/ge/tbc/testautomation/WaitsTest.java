package ge.tbc.testautomation;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.util.FormsTestHelper;
import ge.tbc.testautomation.util.WaitsTestHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WaitsTest {
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
        WaitsTestHelper.setDriver(driver);
        WaitsTestHelper.setWait(wait);
    }

    @Test(priority = 1)
    public void waitForDisappearance() {
        driver.get(Constants.URL_DYNAMIC_CONTROLS);
        WebElement DisEnButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Constants.CSS_SELECTOR_DISABLE_ENABLE_BUTTON)));
        DisEnButton.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(Constants.CSS_SELECTOR_PROGRESS_BAR)));
        String text = DisEnButton.getText();
        Assert.assertEquals(text, "Disable");
        WebElement textInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Constants.CSS_SELECTOR_TEXT_INPUT)));
        textInput.sendKeys("ACCESS GRANTED");
    }

    @Test(priority = 2)
    public void waitForText() {
        driver.get(Constants.URL_DEMOQA_PROGRESS_BAR);
        WebElement startBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Constants.CSS_SELECTOR_DEEMOQA_START_BUTTON)));
        startBtn.click();

        WebElement progressBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Constants.CSS_SELECTOR_DEEMOQA_PROGRESS_BAR)));
        wait.until(ExpectedConditions.attributeToBe(progressBar, "aria-valuenow", "100"));
        WebElement resetBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Constants.CSS_SELECTOR_DEEMOQA_RESET_BUTTON)));
        String resetBtnTet = resetBtn.getText();
        Assert.assertEquals(resetBtnTet, "Reset");
    }
//    @Test(priority = 3)
//    public void scrollTest(){
//        driver.get(Constants.URL_SCROLL_TEST);
//        Map<String, String> map = new HashMap<>();
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(Constants.XPATH_CODE_BLOCK_HEADER, "2.1"))));
//        String precedingHeaderIndex = null;
//        for (int i = 0; i < Constants.MAX_PARAGRAPH_NUM; i++) {
//            for (int j = 1; j < Constants.Max_HEADER_NUM; j++) {
//                String currentIndex = i + "." + j;
//                try {
//                    WebElement Header = driver.findElement(By.xpath(String.format(Constants.XPATH_CODE_BLOCK_HEADER, currentIndex)));
//
//                    if (precedingHeaderIndex == null) {
//                        precedingHeaderIndex = currentIndex;
//                        continue;
//                    }
//                    WebElement CodeBlock = driver.findElement(By.xpath(WaitsTestHelper.formattedCodeBlocksXpath(precedingHeaderIndex, currentIndex)));
//                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", CodeBlock);
//                    String CodeBlockText = CodeBlock.getText();
//                    String headerText = Header.getText();
//                    map.put(headerText, CodeBlockText);
//                    precedingHeaderIndex = currentIndex;
//                } catch (NoSuchElementException e) {
//                    break;
//                }
//            }
//        }
//        try {
//            WebElement CodeBlock = driver.findElement(By.xpath(WaitsTestHelper.formattedCodeBlocksXpath(precedingHeaderIndex, "")));
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", CodeBlock);
//            String CodeBlockText = CodeBlock.getText();
//            String headerText = driver.findElement(By.xpath(String.format(Constants.XPATH_CODE_BLOCK_HEADER, precedingHeaderIndex))).getText();
//            map.put(headerText, CodeBlockText);
//        } catch (NoSuchElementException e) {
//
//        }
//        Assert.assertEquals(map.size(), 7);
//
//
//    }
@Test(priority = 3)
public void scrollTest() {
    driver.get(Constants.URL_SCROLL_TEST);
    wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath(String.format(Constants.XPATH_CODE_BLOCK_HEADER, "2.1"))
    ));

    Map<String, String> headerCodeMap = WaitsTestHelper.getMap();
    Assert.assertEquals(headerCodeMap.size(),Constants.CODE_BLOCKS_NUM, "Invalid number of Code_blocks error happened");


    Map<String, String> TutorialHrefMap = new HashMap<>();
    List<WebElement> aElements = driver.findElements(By.cssSelector(Constants.CSS_POPULAR_POST_TITLE_LINK));
    aElements.parallelStream().forEach(
            aElement -> {
                String href = aElement.getAttribute("href");
                String text = aElement.getText();
                WaitsTestHelper.scrollToElement(aElement);
                TutorialHrefMap.put(text, href);
            }
    );
    Assert.assertEquals(TutorialHrefMap.size(), Constants.POPULAR_POSTS_NUM, "Invalid number of Popular Posts error happened");

    WebElement lastElement = driver.findElement(By.xpath(Constants.XPATH_LAST_ELEMENT));
    WaitsTestHelper.scrollToElement(lastElement);
}
    @AfterClass
    public void afterClass() {
       driver.quit();
    }
}
