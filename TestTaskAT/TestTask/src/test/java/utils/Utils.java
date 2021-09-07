package utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Utils {

    public static void clickElement(WebElement element) {
        element.click();
    }

    public static void inputText(WebElement element, Logger logger, String text) {
        logger.info("Ввод: " + text);
        element.clear();
        element.sendKeys(text);
    }

    public static void inputTextAssertByText(WebElement element, Logger logger, String text) {
        Assert.assertEquals(element.getText(), text);
        logger.debug("Данные элемента соответствует тестовым данным: " + text);
    }

    public static void inputTextAssertParse(WebElement element, Logger logger, String text) {
        String substr = element.getText();
        Assert.assertTrue(substr.contains(text));
        logger.debug("Данные элемента соответствует тестовым данным: " + text);
    }

    public static void inputTextAssertByAttribute(WebElement element, Logger logger, String text, String attr) {
        Assert.assertEquals(element.getAttribute(attr), text);
        logger.debug("Данные элемента соответствует тестовым данным: " + text);
    }
}
