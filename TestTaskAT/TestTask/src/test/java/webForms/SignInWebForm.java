package webForms;

import pageObject.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.DataTest;

public class SignInWebForm extends PageObject {

    DataTest dataTest = new DataTest();

    @FindBy(xpath = "//input[@name='login']")
    private WebElement login;

    @FindBy(xpath = "//input[@name='passwd']")
    private WebElement password;

    @FindBy(xpath = "//button[@type='submit'][.//span[contains(text(), 'Войти')]]")
    private WebElement signInButton;

    public SignInWebForm(WebDriver driver) {
        super(driver);
    }

    public void enterLogin(){
        this.login.sendKeys(dataTest.LOGIN);
    }

    public void enterPassword(){
        this.password.sendKeys(dataTest.PASSWORD);
    }

    public void pressSignInButton(){
        this.signInButton.click();
    }
}
