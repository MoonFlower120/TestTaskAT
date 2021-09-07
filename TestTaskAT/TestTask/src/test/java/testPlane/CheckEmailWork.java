package testPlane;

import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import webForms.EmailWebForm;
import webForms.UserWebForm;

import java.util.Properties;

public class CheckEmailWork {
    private WebDriver driver;
    private WebDriverWait wait;
    EmailWebForm emailWebForm;
    UserWebForm userWebForm;
    private final static Logger logger = Logger.getLogger(CheckEmailWork.class);

    private final static Properties LOCATORPROPERTIES = PropertyManager
            .loadPropertyFile("config.properties");

    @BeforeClass
    public void setUp() {
        // ChromeDriver location set up in PropertyManager class
        System.setProperty("webdriver.chrome.driver", LOCATORPROPERTIES.getProperty("chromedriver"));
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
        emailWebForm = new EmailWebForm(driver);
        userWebForm = new UserWebForm(driver);
        logger.info("ChromeDriver подключен");
        driver.get(LOCATORPROPERTIES.getProperty("baseURL"));
        logger.info("Тест-кейс 'CheckEmailWork' запущен");
        userWebForm.signIn(driver, logger);
    }

    @Test(testName = "Check Draw Email", priority = 1)
    public void checkDrawEmail() throws InterruptedException {
        emailWebForm.createDraw(driver, logger);
        emailWebForm.checkDraw(driver, logger, wait);
    }

    @Test(testName = "Check Send Email", priority = 2)
    public void checkSendEmail() throws InterruptedException {
        emailWebForm.writeEmail(driver, logger);
        emailWebForm.checkSendEmail(driver, logger);
    }

    @AfterClass()
    public void closePage(){
        userWebForm.logOut(driver, logger);
        driver.manage().deleteAllCookies();
        logger.info("Файлы cookies удалены");
        driver.close();
        logger.info("ChromeDriver отключен");
        logger.info("Тест-кейс 'CheckEmailWork' выполнен");
    }
}
