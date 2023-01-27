package selly.service;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import selly.driver.SeleniumGridDriver;
import selly.page.login.Homepage;
import selly.page.login.LoginPage;
import selly.page.searchpage.SearchResultPage;

@Service
public class FunnimartService {

    @Value("${spring.url.seleHost}")
    private String seleHost;

    @Value("${spring.url.selePort}")
    private String selePort;


    WebDriver webdriver = null;

    public WebDriver setupDriver(){
        try{
            SeleniumGridDriver setupTestDriver = new SeleniumGridDriver(null, "chrome", "https://funimart.vn/login", null, seleHost, selePort);
            webdriver = setupTestDriver.getDriver();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return webdriver;
    }

    public void loginFunnimart(String browser, String sellyUrl, String email, String password) throws Exception{
        LoginPage loginPage = new LoginPage(webdriver);
        Utilities.sleep(5000);
        loginPage.setInputEmail(email);
        loginPage.setPassword(password);
        //loginPage.pressEnter();
    }

    public void gotoSellyHomePageAndSearch(String keyWord){
        Homepage homepage = new Homepage(webdriver);
        Utilities.sleep(200);
        homepage.searchWithKeyWord(keyWord);

    }

    public void gotoShopPage(){
        Homepage homepage = new Homepage(webdriver);
        homepage.gotoShopPage(webdriver);
    }

    public int getTotalProduct(){
        SearchResultPage searchResultPage = new SearchResultPage(webdriver);
        return searchResultPage.totalProduct();
    }

    public void serviceGetItemInfo(int itemIndex) throws ElementClickInterceptedException {
        //"D://sellyDownload//content.txt"
        System.out.println("serviceGetItemInfo " + itemIndex);
        SearchResultPage searchResultPage = new SearchResultPage(webdriver);
        searchResultPage.getItemInfo(itemIndex);
        searchResultPage.downloadImageButton();
        //searchResultPage.downloadInmage();
        Utilities.sleep(10000);
        System.out.println("move Folder");
        searchResultPage.moveFolder();
        Utilities.gotoTopPage(webdriver);
        // searchResultPage.createNewContentFile("D://sellyDownload//content.txt");

    }

}
