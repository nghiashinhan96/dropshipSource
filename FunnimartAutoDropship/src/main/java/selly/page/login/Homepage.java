package selly.page.login;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Homepage {
    //modal-content bg-transparent
    //"//span[contains(text(), 'This field is required.')]"
    //d-block cursor-pointer d-contents
    //d-block cursor-pointer d-contents
    @FindBy(how = How.XPATH, using = "//button[@class='Button-l2616d-0 kDftjj']")
    @CacheLookup
    private WebElement gotoShopPageButton;

    //bg-white rounded input-group
    @FindBy(how = How.XPATH, using = "//input[@class='TextFieldStyle__SyledTextField-h6a756-0 jwhSfC search-field']")
    @CacheLookup
    private WebElement searchInput;


    public Homepage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }

    public void gotoShopPage(WebDriver driver){
        gotoShopPageButton.click();
    }

    public void searchWithKeyWord(String keyWord){
        searchInput.sendKeys(Keys.CLEAR);
        searchInput.sendKeys(keyWord);
        searchInput.sendKeys(Keys.ENTER);
    }
}
