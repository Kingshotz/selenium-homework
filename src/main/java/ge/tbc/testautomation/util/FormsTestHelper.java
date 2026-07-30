package ge.tbc.testautomation.util;

import com.github.javafaker.Faker;
import ge.tbc.testautomation.data.Constants;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FormsTestHelper {
    public static void setWait(WebDriverWait wait) {
        if(wait ==null) throw new IllegalArgumentException();
        FormsTestHelper.wait = wait;
    }

    private static WebDriverWait wait;

    private static void fillInput(String labelName, String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(String.format(Constants.XPATH_GENERIC_INPUT, labelName))))
                .sendKeys(value);
    }
    public static Map<String, String> generateFakeFormData() {
        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String address = faker.address().streetAddress();
        String contact = faker.phoneNumber().phoneNumber();
        String city = faker.address().city();

        Map<String, String> formData = new LinkedHashMap<>();
        formData.put("First Name", firstName);
        formData.put("Last Name", lastName);
        formData.put("Address1", address);
        formData.put("Address2", address);
        formData.put("Contact1", contact);
        formData.put("Contact2", contact);
        formData.put("City", city);

        return formData;
    }
    public static void fillForm(Map<String, String> formData) {
        formData.forEach(FormsTestHelper::fillInput);
    }
}
