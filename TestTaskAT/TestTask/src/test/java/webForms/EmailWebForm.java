package webForms;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.PageObject;
import utils.DataTest;
import utils.Utils;

import java.util.concurrent.TimeUnit;

public class EmailWebForm extends PageObject {

    DataTest dataTest;
    Utils utils;
    public EmailWebForm(WebDriver driver) {
        super(driver);
        dataTest = new DataTest();
        utils = new Utils();
    }

    // [1] Содержимое отправленного письма

    //Путь к контейнеру с темой письма во вкладке "Отправленные"
    @FindBy(xpath = "//div[contains(@class, 'mail-Message-Toolbar-Subject')]")
    private WebElement themeSendEmail;

    //Путь к контейнеру с почтой получателя во вкладке "Отправленные"
    @FindBy(xpath = "//div[contains(@class, 'mail-User-Name')]/parent::div")
    private WebElement toSendEmail;

    //Путь к контейнеру с телом письма во вкладке "Отправленные"
    @FindBy(xpath = "//div[contains(@class, 'mail-Message-Body-Content')]/div")
    private WebElement bodySendEmail;

    // [2] Содержимое левого меню

    //Путь к кнопке "Написать" в левом меню
    @FindBy(xpath = "//span[contains(text(), 'Написать')]/parent::a")
    private WebElement writeButton;

    //Путь к кнопке "Черновики" в левом меню
    @FindBy(xpath = "//span[contains(text(), 'Черновики')]/parent::a")
    private WebElement drawEmailsButton;

    //Путь к кнопке "Отправленные" в левом меню
    @FindBy(xpath = "//span[contains(text(), 'Отправленные')]/parent::a")
    private WebElement sendEmailsButton;

    // [3] Содержимое списка писем

    //Путь к последнему письму в списке писем
    @FindBy(xpath = "(//a//descendant::span[contains(@class, 'mail-MessageSnippet-FromText')])[1]")
    private WebElement emailButton;

    // [4] Содержимое формы для создания нового письма

    //Путь к форме при написании нового письма для добавления почты получателя
    @FindBy(xpath = "//label[.//span[contains(text(), 'Кому')]]/following-sibling::div" +
            "[@class='compose-LabelRow-Content']/descendant::div[@class='composeYabbles']")
    private WebElement to;

    //Путь к почте получателя при написании нового письма
    @FindBy(xpath = "//label[.//span[contains(text(), 'Кому')]]/following-sibling::div" +
            "[@class='compose-LabelRow-Content']/descendant::div[@class='ComposeYabble-Text']")
    private WebElement toExist;

    //Путь к форме при написании нового письма для добавления темы письма
    @FindBy(xpath = "//input[@name='subject']")
    private WebElement theme;

    //Путь к форме при написании нового письма для добавления тела письма
    @FindBy(xpath = "//div[@role='textbox']/child::div")
    private WebElement body;

    //Путь к кнопке "Закрыть письмо"
    @FindBy(xpath = "//*[@id='nb-1']/body/div[2]/div[10]/div/div/div[2]/div/div[1]/div/div[2]/div/div[3]")
    private WebElement closeButton;

    //Путь к кнопке "Отправить письмо"
    @FindBy(xpath = "//div[span[contains(text(), 'Отправить')]]/ancestor::button[@type='button']")
    private WebElement sendButton;

    //Путь к кнопке "Вернуться во входящие"
    @FindBy(xpath = "//a[contains(text(), 'Вернуться во \"Входящие\"')]")
    private WebElement backButton;

    ////////////////////////////////////////////////////////////////
    /////////////////////////////* Шаги *///////////////////////////
    ////////////////////////////////////////////////////////////////

    //Создать черновик
    public void createDraw(WebDriver driver, Logger logger){
        logger.info("Тест 'Email draw' запущен");

        utils.clickElement(writeButton);
        logger.debug("Нажата кнопка 'Написать'");

        //ожидание загрузки скрипта
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //Ввести тестовые данные
        utils.inputText(to, logger, dataTest.TO);

        utils.inputText(theme, logger, dataTest.THEME);

        utils.inputText(body, logger, dataTest.BODY);

        utils.clickElement(closeButton);
        logger.debug("Нажата кнопка 'Закрыть'");

        logger.info("Тест 'Email draw' выполнен");
    }

    //Проверить черновик на наличие правильных данных
    public void checkDraw(WebDriver driver, Logger logger, WebDriverWait wait) throws InterruptedException {
        logger.info("Тест 'Check draw' запущен");

        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);

        utils.clickElement(drawEmailsButton);
        logger.debug("Нажата кнопка 'Черновики'");

        Thread.sleep(3000);

        utils.clickElement(emailButton);
        logger.debug("Выбран черновик с почтой получателя: " + dataTest.TO);

        utils.inputTextAssertByText(toExist, logger, dataTest.TO);

        utils.inputTextAssertByAttribute(theme, logger, dataTest.THEME, "value");

        utils.inputTextAssertByText(body, logger, dataTest.BODY);

        utils.clickElement(closeButton);
        logger.debug("Нажата кнопка 'Закрыть'");

        logger.info("Тест 'Check draw' выполнен");
    }

    //Отправить письмо
    public void writeEmail(WebDriver driver, Logger logger) throws InterruptedException {
        logger.info("Тест 'Email draw' запущен");

        utils.clickElement(writeButton);
        logger.debug("Нажата кнопка 'Написать'");

        //ожидание загрузки скрипта
        Thread.sleep(3000);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //Ввести тестовые данные
        utils.inputText(to, logger, dataTest.TO);

        utils.inputText(theme, logger, dataTest.THEME);

        utils.inputText(body, logger, dataTest.BODY);

        utils.clickElement(sendButton);
        logger.debug("Нажата кнопка 'Отправить'");

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        utils.clickElement(backButton);
        logger.debug("Нажата кнопка 'Вернуться во \"Входящие\"'");

        logger.info("Тест 'Write email' выполнен");
    }

    //Проверить отправленное письмо на наличие правильных данных
    public void checkSendEmail(WebDriver driver, Logger logger) throws InterruptedException {
        logger.info("Тест 'Check send email' запущен");

        utils.clickElement(sendEmailsButton);
        logger.debug("Нажата кнопка 'Отправленные'");

        Thread.sleep(3000);

        utils.clickElement(emailButton);
        logger.debug("Выбрано отправленное письмо с почтой получателя: " + dataTest.TO);

        Thread.sleep(3000);

        utils.inputTextAssertByAttribute(toSendEmail, logger, dataTest.TO, "data-email");

        utils.inputTextAssertByText(themeSendEmail, logger, dataTest.THEME);

        utils.inputTextAssertParse(bodySendEmail, logger, dataTest.BODY);

        logger.info("Тест 'Check send email' выполнен");
    }
}
