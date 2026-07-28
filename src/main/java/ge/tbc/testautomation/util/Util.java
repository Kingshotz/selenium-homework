package ge.tbc.testautomation.util;

import ge.tbc.testautomation.data.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public  final class Util {
    private Util() {}

    public static<T> void universalSelector(T select , String visibleText){
        if(select instanceof Select){
            SelectFromNativeSelector((Select) select, visibleText);
        }else if(select instanceof WebElement){
            SelectFromCustomSelector((WebElement) select, visibleText);
        }else{
            throw new IllegalArgumentException("Invalid type");
        }
        System.out.println("'I AM A FIX");
    }
    private static void SelectFromCustomSelector(WebElement selector , String visibleText){
        selector.click();
        WebDriver driver = getDriverFrom(selector);
        By xpathSelector = By.xpath(String.format(Constants.OPTION_XPATH_TEMPLATE, visibleText));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(xpathSelector));
        option.click();
    }
    private static void SelectFromNativeSelector(Select selector, String  visibleText){
        selector.selectByVisibleText(visibleText);
        ((WebElement) selector).click();
    }
    private static WebDriver getDriverFrom(WebElement elem){
        if(elem instanceof WrapsDriver){
            return ((WrapsDriver) elem).getWrappedDriver();
        }else{
            throw new IllegalArgumentException("Couldn't Find driver");
        }
    }

}
