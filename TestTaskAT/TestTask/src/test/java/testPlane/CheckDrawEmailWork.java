package testPlane;

import funtions.EmailFunction;
import funtions.UserFunction;
import utils.PropertyManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.Properties;

public class CheckDrawEmailWork {
    private WebDriver driver;
    private final static Logger logger = Logger.getLogger(CheckDrawEmailWork.class);

    private final static Properties LOCATORPROPERTIES = PropertyManager
            .loadPropertyFile("config.properties");

    private EmailFunction emailFunction = new EmailFunction();
    private UserFunction userFunction = new UserFunction();

    @BeforeClass
    public void setUp() {
        // ChromeDriver location set up in PropertyManager class
        System.setProperty("webdriver.chrome.driver", LOCATORPROPERTIES.getProperty("chromedriver"));
        driver = new ChromeDriver();
        //wait = new WebDriverWait(driver, 20);
        logger.info("ChromeDriver подключен");
        driver.get(LOCATORPROPERTIES.getProperty("baseURL"));
        logger.info("Тест-кейс 'CheckDrawEmailWork' запущен");
        userFunction.signIn(driver, logger);
    }

    @Test(testName = "Check Draw Email", priority = 1)
    public void checkDrawEmail() throws InterruptedException {
        emailFunction.createDraw(driver, logger);
        emailFunction.checkDraw(driver, logger);
    }

    @Test(testName = "Check Send Email", priority = 2)
    public void checkSendEmail() throws InterruptedException {
        emailFunction.writeEmail(driver, logger);
        emailFunction.checkSendEmail(driver, logger);
    }

    @Test(testName = "Check REST API", priority = 3)
    public void checkRestApi() {

    }

    @AfterClass()
    public void closePage(){
        userFunction.logOut(driver, logger);
        driver.manage().deleteAllCookies();
        logger.info("Файлы cookies удалены");
        driver.close();
        logger.info("ChromeDriver отключен");
        logger.info("Тест-кейс 'CheckDrawEmailWork' выполнен");
    }
}
