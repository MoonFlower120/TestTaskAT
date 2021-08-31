package funtions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.DataTest;
import webForms.EmailContentForm;
import webForms.LeftMenuWebForm;
import webForms.ListEmailWebForm;
import webForms.NewEmailWebForm;

import java.util.concurrent.TimeUnit;

public class EmailFunction {
    DataTest dataTest = new DataTest();

    public void createDraw(WebDriver driver, Logger logger){
        logger.info("Тест 'Email draw' запущен");
        NewEmailWebForm newEmailWebForm = new NewEmailWebForm(driver);
        LeftMenuWebForm leftMenuWebForm = new LeftMenuWebForm(driver);
        leftMenuWebForm.pressWriteButton();
        logger.debug("Нажата кнопка 'Написать'");
        //ожидание загрузки скрипта
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        newEmailWebForm.enterTo();
        logger.debug("Введена почта получателя: " + dataTest.TO);
        newEmailWebForm.enterTheme();
        logger.debug("Введена тема письма: " + dataTest.THEME);
        newEmailWebForm.enterBody();
        logger.debug("Введено тело письма: " + dataTest.BODY);
        newEmailWebForm.pressCloseButton();
        logger.debug("Нажата кнопка 'Закрыть'");
        logger.info("Тест 'Email draw' выполнен");
    }

    public void checkDraw(WebDriver driver, Logger logger) throws InterruptedException {
        logger.info("Тест 'Check draw' запущен");

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        LeftMenuWebForm leftMenuWebForm = new LeftMenuWebForm(driver);
        ListEmailWebForm listEmailWebForm = new ListEmailWebForm(driver);
        NewEmailWebForm newEmailWebForm = new NewEmailWebForm(driver);
        leftMenuWebForm.pressDrawEmailButton();
        logger.debug("Нажата кнопка 'Черновики'");
        Thread.sleep(3000);
        listEmailWebForm.pressEmailButton();
        logger.debug("Выбран черновик с почтой получателя: " + dataTest.TO);

        Assert.assertEquals(newEmailWebForm.getTextTo(), dataTest.TO);
        logger.debug("Почта получателя соответствует тестовым данным: " + dataTest.TO);

        Assert.assertEquals(newEmailWebForm.getTextTheme(), dataTest.THEME);
        logger.debug("Тема письма соответствует тестовым данным: " + dataTest.THEME);

        Assert.assertEquals(newEmailWebForm.getTextBody(), dataTest.BODY);
        logger.debug("Тело письма соответствует тестовым данным: " + dataTest.BODY);

        newEmailWebForm.pressCloseButton();
        logger.debug("Нажата кнопка 'Закрыть'");

        logger.info("Тест 'Check draw' выполнен");
    }

    public void writeEmail(WebDriver driver, Logger logger) throws InterruptedException {
        logger.info("Тест 'Email draw' запущен");
        NewEmailWebForm newEmailWebForm = new NewEmailWebForm(driver);
        LeftMenuWebForm leftMenuWebForm = new LeftMenuWebForm(driver);
        leftMenuWebForm.pressWriteButton();
        logger.debug("Нажата кнопка 'Написать'");
        //ожидание загрузки скрипта
        Thread.sleep(3000);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        newEmailWebForm.enterTo();
        logger.debug("Введена почта получателя: " + dataTest.TO);
        newEmailWebForm.enterTheme();
        logger.debug("Введена тема письма: " + dataTest.THEME);
        newEmailWebForm.enterBody();
        logger.debug("Введено тело письма: " + dataTest.BODY);
        newEmailWebForm.pressSendButton();
        logger.debug("Нажата кнопка 'Отправить'");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        newEmailWebForm.pressBackButton();
        logger.debug("Нажата кнопка 'Вернуться во \"Входящие\"'");
        logger.info("Тест 'Write email' выполнен");
    }

    public void checkSendEmail(WebDriver driver, Logger logger) throws InterruptedException {
        logger.info("Тест 'Check send email' запущен");

        LeftMenuWebForm leftMenuWebForm = new LeftMenuWebForm(driver);
        EmailContentForm emailContentForm = new EmailContentForm(driver);

        leftMenuWebForm.pressSendEmailButton();
        logger.debug("Нажата кнопка 'Отправленные'");

        Thread.sleep(3000);

        ListEmailWebForm listEmailWebForm = new ListEmailWebForm(driver);
        listEmailWebForm.pressEmailButton();
        logger.debug("Выбрано отправленное письмо с почтой получателя: " + dataTest.TO);

        Thread.sleep(3000);
        Assert.assertEquals(emailContentForm.getTextToEmail(), dataTest.TO);
        logger.debug("Почта получателя соответствует тестовым данным: " + dataTest.TO);

        Assert.assertEquals(emailContentForm.getTextThemeEmail(), dataTest.THEME);
        logger.debug("Тема письма соответствует тестовым данным: " + dataTest.THEME);

        Assert.assertEquals(emailContentForm.getTextBodyEmail(), dataTest.BODY);
        logger.debug("Тело письма соответствует тестовым данным: " + dataTest.BODY);

        logger.info("Тест 'Check send email' выполнен");
    }
}
