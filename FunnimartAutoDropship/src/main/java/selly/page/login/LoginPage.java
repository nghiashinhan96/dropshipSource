package selly.page.login;

import selly.service.Utilities;

import java.util.List;

import lombok.Getter;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

@Getter
public class LoginPage {

    @FindBy(how = How.XPATH, using = "//input[@type='email']")
    @CacheLookup
    private WebElement emailInput;

    @FindBy(how = How.XPATH, using = "//input[@type='password']")
    @CacheLookup
    private WebElement passwordInput;

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void setInputEmail(String email) throws Exception{
        Thread.sleep(200);
        emailInput.sendKeys(email);
    }

    public void setPassword(String password) throws Exception{
        Thread.sleep(200);
        passwordInput.sendKeys(password);
        passwordInput.sendKeys(Keys.ENTER);
    }

    public void pressEnter() {
        passwordInput.sendKeys(Keys.ENTER);
    }
}
