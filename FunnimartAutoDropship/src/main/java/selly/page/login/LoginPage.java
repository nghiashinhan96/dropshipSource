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

    @FindBy(how = How.ID, using = "0.8777432612898772")
    @CacheLookup
    private WebElement emailElement;

    @FindBy(how = How.ID, using = "0.8997563650023572")
    @CacheLookup
    private WebElement passwordElement;

    @FindBy(how = How.CLASS_NAME, using = "Button-l2616d-0 kNutqg")
    @CacheLookup
    private WebElement btnElement;

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void setLoginValue(String phoneNumber) throws Exception {
        this.setInputEmail(phoneNumber);
    }

    public void setInputEmail(String phone) throws Exception{
        Thread.sleep(200);
        emailElement.sendKeys(phone);
    }


    public void setPassword(String password) {
        passwordElement.sendKeys(password);

    }

    public void clickLogin() {
        btnElement.click();
    }
}
