package selly.page.login;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import selly.service.Utilities;

import java.util.List;

public class Homepage {
    //modal-content bg-transparent
    //"//span[contains(text(), 'This field is required.')]"
    //d-block cursor-pointer d-contents
    //d-block cursor-pointer d-contents
    //sidebar-submenu-item

    // shopee-modal guide-modal/ i shopee-icon shopee-modal__close
    @FindBy(how = How.XPATH, using = "//div[@class='shopee-modal guide-modal']//i[@class='shopee-icon shopee-modal__close']")
    @CacheLookup
    private WebElement closeAdButton;

    @FindBy(how = How.XPATH, using = "//li[@class='sidebar-menu-box ps_menu_product']//ul[@class='sidebar-submenu']//li")
    @CacheLookup
    private List<WebElement> allManage;

    public void addproduct(){
        allManage.get(1).click();
    }

    @FindBy(how = How.XPATH, using = "//div[@class='shopee-popup__close-btn']//svg")
    @CacheLookup
    private WebElement advertisement;

    //bg-white rounded input-group
    @FindBy(how = How.XPATH, using = "//div[@class='bg-white rounded input-group']//input")
    @CacheLookup
    private WebElement searchInput;


    public Homepage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }


    public void closeAdButtonPress(){
        closeAdButton.click();
    }

    public void clickAdveertisement(WebDriver driver){
        System.out.println("clickAdveertisement");
        advertisement.click();
    }

    public void searchWithKeyWord(String keyWord){
        searchInput.sendKeys(Keys.DELETE);
        searchInput.sendKeys(keyWord);
        searchInput.sendKeys(Keys.ENTER);
    }
}
