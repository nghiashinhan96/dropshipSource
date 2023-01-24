package selly.page.login;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

@Getter
public class LoginPage {

    @FindBy(how = How.XPATH, using = "//*[contains(@class,'login-container')]//input[@type='text']")
    @CacheLookup
    private WebElement inputPhone;

    @FindBy(how = How.ID, using = "username_show")
    @CacheLookup
    private WebElement userNameElement;

    @FindBy(how = How.ID, using = "password_show")
    @CacheLookup
    private WebElement passwordElement;

    @FindBy(how = How.XPATH, using = "//*[contains(@class,'login-container')]//button[@type='submit']")
    @CacheLookup
    private WebElement submitButton;

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void setLoginValue(String phoneNumber) throws Exception {
        this.setInputPhone(phoneNumber);
    }

    public void setInputPhone(String phone) throws Exception{
        Thread.sleep(200);
        inputPhone.sendKeys(phone);
    }

    public void setUserName(String user) {
        userNameElement.sendKeys(user);
    }

    public void setPassword(String password) {
        passwordElement.sendKeys(password);

    }

    public void clickLogin() {
        submitButton.click();
    }
}
