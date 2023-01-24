package selly.page.login;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import selly.service.Utilities;

@Getter
public class LoginPage {

    @FindBy(how = How.XPATH, using = "//div[@class='shopee-input__inner shopee-input__inner--large']//input[@type='text']")
    @CacheLookup
    private WebElement inputPhone;
    @FindBy(how = How.XPATH, using = "//div[@class='shopee-input__inner shopee-input__inner--large']//input[@type='password']")
    @CacheLookup
    private WebElement passwordElement;

    @FindBy(how = How.XPATH, using = "//*[contains(@class,'shopee-form-item__control')]//button[@type='button']")
    @CacheLookup
    private WebElement submitButton;

    //shopee-modal__footer-buttons
    @FindBy(how = How.XPATH, using = "//*[contains(@class,'quvuMg')]//button")
    @CacheLookup
    private WebElement sendSMS;
    //quvuMg

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }


    public void setInputPhone(String phone) throws Exception{
        Thread.sleep(200);
        inputPhone.sendKeys(phone);
    }

    public void setInputPassword(String password) {
        Utilities.sleep(300);
        passwordElement.sendKeys(password);
    }


    public void clickLogin() {
        submitButton.click();
    }

    public void sendSMS(){
        Utilities.sleep(300);
        sendSMS.click();
    }
}
