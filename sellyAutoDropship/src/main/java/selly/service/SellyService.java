package selly.service;

import org.openqa.selenium.ElementClickInterceptedException;
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
import selly.page.login.PasswordPage;
import selly.page.searchpage.SearchResultPage;

@Service
public class SellyService {

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
            SeleniumGridDriver setupTestDriver = new SeleniumGridDriver(null, "chrome", "https://selly.vn/login", null, seleHost, selePort);
            webdriver = setupTestDriver.getDriver();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return webdriver;
    }

    public void loginSelly(String browser, String sellyUrl, String phone, String password) throws Exception{
        LoginPage loginPage = new LoginPage(webdriver);
        loginPage.setInputPhone(phone);
        loginPage.clickLogin();
        PasswordPage passwordPage = new PasswordPage(webdriver);
        passwordPage.inputPassword(password);
        passwordPage.clickSubmit();
    }

    public void gotoSellyHomePageAndSearch(String keyWord){
        Homepage homepage = new Homepage(webdriver);
        Utilities.sleep(200);
        homepage.searchWithKeyWord(keyWord);

    }

    public void closeAdvertisement(){
        Homepage homepage = new Homepage(webdriver);
        homepage.clickAdveertisement(webdriver);
    }

    public int getTotalProduct(){
        SearchResultPage searchResultPage = new SearchResultPage(webdriver);
        return searchResultPage.totalProduct();
    }

    public void serviceGetItemInfo(int itemIndex) throws ElementClickInterceptedException {
        //"D://sellyDownload//content.txt"
        System.out.println("serviceGetItemInfo "+itemIndex);
        SearchResultPage searchResultPage = new SearchResultPage(webdriver);
         searchResultPage.getItemInfo(itemIndex);
            searchResultPage.downloadInmage();
            Utilities.sleep(10000);
            System.out.println("move Folder");
            searchResultPage.moveFolder();
            Utilities.gotoTopPage(webdriver);
            searchResultPage.closeProductInfoPopup();
       // searchResultPage.createNewContentFile("D://sellyDownload//content.txt");

    }

}
