package webForms;

import pageObject.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserWebForm extends PageObject {

    @FindBy(xpath = "//a[@target='_parent'][.//div[contains(@class, 'user-pic')]]")
    private WebElement userButton;

    @FindBy(xpath = "//div[span[contains(@class, 'user-account')]]/parent::div/following-sibling::ul" +
            "/descendant::a[span[contains(text(), 'Выйти из сервисов Яндекса')]]")
    private WebElement logOutButton;

    public UserWebForm(WebDriver driver) {
        super(driver);
    }

    public void pressUserButton(){
        this.userButton.click();
    }

    public void pressLogOutButton(){
        this.logOutButton.click();
    }
}
