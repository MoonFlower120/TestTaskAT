package webForms;

import pageObject.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ListEmailWebForm extends PageObject {
    @FindBy(xpath = "(//a//descendant::span[contains(@class, 'mail-MessageSnippet-FromText')])[1]")
    private WebElement emailButton;

    public ListEmailWebForm(WebDriver driver) {
        super(driver);
    }

    public void pressEmailButton() {
        //driver.manage().timeouts().setScriptTimeout(3, TimeUnit.SECONDS);
        this.emailButton.click();
    }
}