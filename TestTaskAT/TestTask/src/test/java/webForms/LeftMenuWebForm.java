package webForms;

import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObject.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class LeftMenuWebForm extends PageObject {

    @FindBy(xpath = "//span[contains(text(), 'Написать')]/parent::a")
    private WebElement writeButton;

    @FindBy(xpath = "//span[contains(text(), 'Черновики')]/parent::a")
    private WebElement drawEmailsButton;

    @FindBy(xpath = "//span[contains(text(), 'Отправленные')]/parent::a")
    private WebElement sendEmailsButton;



    public LeftMenuWebForm(WebDriver driver) {
        super(driver);
    }

    public void pressWriteButton(){
        this.writeButton.click();
    }

    public void pressDrawEmailButton(){
        //driver.manage().timeouts().setScriptTimeout(3, TimeUnit.SECONDS);
        this.drawEmailsButton.click();
    }



    public void pressSendEmailButton(){this.sendEmailsButton.click();}
}
