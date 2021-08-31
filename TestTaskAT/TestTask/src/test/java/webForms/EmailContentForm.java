package webForms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pageObject.PageObject;
import utils.DataTest;

public class EmailContentForm extends PageObject {

    @FindBy(xpath = "//div[contains(@class, 'mail-Message-Toolbar-Subject')]")
    private WebElement themeSendEmail;

    @FindBy(xpath = "//div[contains(@class, 'mail-User-Name')]/parent::div")
    private WebElement toSendEmail;

    @FindBy(xpath = "//div[contains(@class, 'mail-Message-Body-Content')]")
    private WebElement bodySendEmail;

    public EmailContentForm(WebDriver driver) {
        super(driver);
    }

    public String getTextToEmail(){ return this.toSendEmail.getAttribute("data-email"); }

    public String getTextThemeEmail(){
        return this.themeSendEmail.getText();
    }

    public String getTextBodyEmail(){
        return this.bodySendEmail.getText();
    }
}
