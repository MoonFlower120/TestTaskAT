package webForms;

import pageObject.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.DataTest;

public class NewEmailWebForm extends PageObject {

    DataTest dataTest = new DataTest();

    @FindBy(xpath = "//label[.//span[contains(text(), 'Кому')]]/following-sibling::div" +
            "[@class='compose-LabelRow-Content']/descendant::div[@class='composeYabbles']")
    private WebElement to;

    @FindBy(xpath = "//label[.//span[contains(text(), 'Кому')]]/following-sibling::div" +
            "[@class='compose-LabelRow-Content']/descendant::div[@class='ComposeYabble-Text']")
    private WebElement toExist;

    @FindBy(xpath = "//input[@name='subject']")
    private WebElement theme;

    @FindBy(xpath = "//div[@role='textbox']/child::div")
    private WebElement body;

    @FindBy(xpath = "//*[@id='nb-1']/body/div[2]/div[10]/div/div/div[2]/div/div[1]/div/div[2]/div/div[3]")
    private WebElement closeButton;

    @FindBy(xpath = "//div[span[contains(text(), 'Отправить')]]/ancestor::button[@type='button']")
    private WebElement sendButton;

    @FindBy(xpath = "//a[contains(text(), 'Вернуться во \"Входящие\"')]")
    private WebElement backButton;

    public NewEmailWebForm(WebDriver driver) {
        super(driver);
    }


    public void enterTo(){ this.to.sendKeys(dataTest.TO); }

    public void enterTheme(){
        this.theme.sendKeys(dataTest.THEME);
    }

    public void enterBody(){
        this.body.sendKeys(dataTest.BODY);
    }


    public void pressCloseButton(){
        this.closeButton.click();
    }

    public void pressBackButton() {this.backButton.click();}

    public void pressSendButton(){ this.sendButton.click();}

    public String getTextTo(){
        return this.toExist.getText();
    }

    public String getTextTheme(){
        return this.theme.getAttribute("value");
    }

    public String getTextBody(){
        return this.body.getText();
    }
}
