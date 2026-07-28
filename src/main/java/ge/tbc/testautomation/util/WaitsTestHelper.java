package ge.tbc.testautomation.util;

import ge.tbc.testautomation.data.Constants;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

public class WaitsTestHelper {
    private static WebDriver driver;
    private static WebDriverWait wait;

    public static void setDriver(WebDriver driver) {
        if(driver == null) throw new IllegalArgumentException();
        WaitsTestHelper.driver = driver;
    }
    public static void setWait(WebDriverWait wait) {
        if(wait == null) throw new IllegalArgumentException();
        WaitsTestHelper.wait = wait;
    }

    public static String formattedCodeBlocksXpath(String pNumBefore, String pNumAfter) {
        return String.format(Constants.XPATH_ALL_CODE_BLOCKS, pNumBefore, pNumAfter, pNumBefore, pNumAfter);
    }

    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static String getCodeBlockText(String precedingIndex, String nextIndex) {
        WebElement codeBlock = driver.findElement(By.xpath(
                formattedCodeBlocksXpath(precedingIndex, nextIndex)
        ));
        scrollToElement(codeBlock);
        return codeBlock.getText();
    }

    public static String getHeaderText(String index) {
        return driver.findElement(By.xpath(
                String.format(Constants.XPATH_CODE_BLOCK_HEADER, index)
        )).getText();
    }

    public static Map<String, String> getMap() {
        Map<String, String> headingCodeMap= new HashMap<>();

        String precedingHeaderIndex = null;
        for (int i = 0; i < Constants.MAX_PARAGRAPH_NUM; i++) {
            for (int j = 1; j < Constants.Max_HEADER_NUM; j++) {
                String currentIndex = i + "." + j;
                try {
                    driver.findElement(By.xpath(String.format(Constants.XPATH_CODE_BLOCK_HEADER, currentIndex)));

                    if (precedingHeaderIndex == null) {
                        precedingHeaderIndex = currentIndex;
                        continue;
                    }

                    // Call methods via the extractor instance
                    String codeBlockText = getCodeBlockText(precedingHeaderIndex, currentIndex);
                    String precedingHeaderText = getHeaderText(precedingHeaderIndex);
                    headingCodeMap.put(precedingHeaderText, codeBlockText);

                    precedingHeaderIndex = currentIndex;

                } catch (NoSuchElementException e) {
                    break;
                }
            }
        }
        //last element
        if (precedingHeaderIndex != null) {
            try {
                String finalCodeBlockText = getCodeBlockText(precedingHeaderIndex, "");
                String finalHeaderText = getHeaderText(precedingHeaderIndex);
                headingCodeMap.put(finalHeaderText, finalCodeBlockText);
            } catch (NoSuchElementException e) {}
        }
        return headingCodeMap;
    }
}
