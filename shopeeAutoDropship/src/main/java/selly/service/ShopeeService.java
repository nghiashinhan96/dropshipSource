package selly.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import selly.driver.SeleniumGridDriver;
import selly.page.login.Homepage;
import selly.page.login.LoginPage;
import selly.page.searchpage.SearchResultPage;
import selly.page.uploadProduct.ProductInfoPage;

@Service
public class ShopeeService {

    @Value("${spring.url.seleHost}")
    private String seleHost;

    @Value("${spring.url.selePort}")
    private String selePort;

    @FindBy(how = How.ID, using = "username_show")
    @CacheLookup
    private WebElement userNameElement;

    @FindBy(how = How.ID, using = "password_show")
    @CacheLookup
    private WebElement passwordElement;

    @FindBy(how = How.ID, using = "loginbutton")
    @CacheLookup
    private WebElement btnElement;

    @FindBy(how = How.CLASS_NAME, using="")
    private WebElement phoneInput;

    WebDriver webdriver = null;

    public WebDriver setupDriver(){
        try{
            SeleniumGridDriver setupTestDriver = new SeleniumGridDriver(null, "chrome", "https://banhang.shopee.vn/", null, seleHost, selePort);
            webdriver = setupTestDriver.getDriver();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return webdriver;
    }


    public void loginShopee(String browser, String sellyUrl, String phone, String password) throws Exception{
        LoginPage loginPage = new LoginPage(webdriver);
        Utilities.sleep(5000);
        loginPage.setInputPhone(phone);
        loginPage.setInputPassword(password);
        loginPage.clickLogin();
        loginPage.sendSMS();
        /*PasswordPage passwordPage = new PasswordPage(webdriver);
        passwordPage.inputPassword(password);
        passwordPage.clickSubmit();*/
    }

    public void addPro(){
        Homepage homepage = new Homepage(webdriver);
        Utilities.sleep(300);
        homepage.closeAdButtonPress();
        homepage.addproduct();
    }

    public void putProductImages(){
        ProductInfoPage productInfoPage = new ProductInfoPage(webdriver);
        productInfoPage.putProductInfo(webdriver, "D://selly");
    }

    public void closeAdvertisement(){
        Homepage homepage = new Homepage(webdriver);
        homepage.clickAdveertisement(webdriver);
    }

    public int getTotalProduct(){
        SearchResultPage searchResultPage = new SearchResultPage(webdriver);
        return searchResultPage.totalProduct();
    }

}
