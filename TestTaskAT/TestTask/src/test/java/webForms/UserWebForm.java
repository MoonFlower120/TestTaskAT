package webForms;

import org.apache.log4j.Logger;
import pageObject.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.DataTest;
import utils.Utils;

import java.util.concurrent.TimeUnit;

public class UserWebForm extends PageObject {

    DataTest dataTest;
    Utils utils;

    public UserWebForm(WebDriver driver) {
        super(driver);
        dataTest = new DataTest();
        utils = new Utils();
    }
    //[1] Функции пользователя

    @FindBy(xpath = "//a[@target='_parent'][.//div[contains(@class, 'user-pic')]]")
    private WebElement userButton;

    @FindBy(xpath = "//div[span[contains(@class, 'user-account')]]/parent::div/following-sibling::ul" +
            "/descendant::a[span[contains(text(), 'Выйти из сервисов Яндекса')]]")
    private WebElement logOutButton;

    //[2] Авторизация пользователя

    @FindBy(xpath = "//input[@name='login']")
    private WebElement login;

    @FindBy(xpath = "//input[@name='passwd']")
    private WebElement password;

    @FindBy(xpath = "//button[@type='submit'][.//span[contains(text(), 'Войти')]]")
    private WebElement signInButton;

    public void signIn(WebDriver driver, Logger logger){
        logger.info("Тест 'Sign In' запущен");

        utils.inputText(login, logger, dataTest.LOGIN);

        utils.clickElement(signInButton);
        logger.debug("Нажата кнопка 'Войти'");

        //ожидание загрузки скрипта
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        utils.inputText(password, logger, dataTest.PASSWORD);

        utils.clickElement(signInButton);
        logger.debug("Нажата кнопка 'Войти'");

        logger.info("Тест 'Sign In' выполнен");
    }

    public void logOut(WebDriver driver, Logger logger){
        logger.info("Тест 'Log out' запущен");

        utils.clickElement(userButton);
        logger.debug("Нажата кнопка 'пользователь'");

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        utils.clickElement(logOutButton);
        logger.debug("Нажата кнопка 'Выйти из сервисов Яндекса'");

        logger.info("Тест 'Log out' выполнен");
    }
}
