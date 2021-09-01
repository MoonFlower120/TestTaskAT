package webForms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.PageObject;

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
