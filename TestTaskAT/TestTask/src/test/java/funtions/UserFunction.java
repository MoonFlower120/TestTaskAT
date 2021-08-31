package funtions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import webForms.SignInWebForm;
import webForms.UserWebForm;

import java.util.concurrent.TimeUnit;

public class UserFunction {

    public void signIn(WebDriver driver, Logger logger){
        logger.info("Тест 'Sign In' запущен");
        SignInWebForm signInWebForm = new SignInWebForm(driver);
        signInWebForm.enterLogin();
        logger.debug("Введен логин: ");
        signInWebForm.pressSignInButton();
        logger.debug("Нажата кнопка 'Войти'");
        //ожидание загрузки скрипта
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        signInWebForm.enterPassword();
        logger.debug("Введен пароль: ");
        signInWebForm.pressSignInButton();
        logger.debug("Нажата кнопка 'Войти'");
        logger.info("Тест 'Sign In' выполнен");
    }

    public void logOut(WebDriver driver, Logger logger){
        logger.info("Тест 'Log out' запущен");
        UserWebForm userWebForm = new UserWebForm(driver);
        userWebForm.pressUserButton();
        logger.debug("Нажата кнопка 'пользователь'");

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


        userWebForm.pressLogOutButton();
        logger.debug("Нажата кнопка 'Выйти из сервисов Яндекса'");
        logger.info("Тест 'Log out' выполнен");
    }
}
