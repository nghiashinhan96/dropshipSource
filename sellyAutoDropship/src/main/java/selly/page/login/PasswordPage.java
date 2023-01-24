package selly.page.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import javax.annotation.CheckForNull;

public class PasswordPage {

    public PasswordPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    @CacheLookup
    @FindBy(how = How.XPATH, using = "//*[contains(@class,'login-container')]//input[@type='password']")
    private WebElement passwordInput;

    @CacheLookup
    @FindBy(how = How.XPATH, using = "//*[contains(@class,'login-container')]//button[@type='submit']")
    private WebElement subitButton;

    public void inputPassword(String passWord){
        passwordInput.sendKeys(passWord);
    }

    public void clickSubmit(){
        subitButton.click();
    }
}
