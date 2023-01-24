package selly.page.searchpage;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import selly.service.Utilities;

import java.io.File;
import java.util.List;

public class SearchResultPage {
    public SearchResultPage(WebDriver webdriver){
        PageFactory.initElements(webdriver, this);
    }
    //modal-dialog modal-lg modal-dialog-centered
    @FindBy(how = How.XPATH, using = "//div[@class='modal-header']//button")
    @CacheLookup
    private WebElement closeProductInfoPopup;

    //justify-content-center modal-footer
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'justify-content-center modal-footer')]//button]")
    @CacheLookup
    private WebElement addTobasketButton;

    @FindBy(how = How.XPATH, using = "//div[@class='w-100 g-3 row-cols-5 hide-scrollbar m-0 row']//div[@class='col']")
    @CacheLookup
    private List<WebElement> listItem;

    //d-flex flex-row w-100 mt-3
    @FindBy(how = How.XPATH, using = "//div[@class='d-flex flex-row w-100 mt-3']//button")
    @CacheLookup
    private List<WebElement> listInfoButton;
    //fs-7
    //[@class='fs-7']
    @FindBy(how = How.XPATH, using = "//div[contains(@class,'shareDescContainer')]//p")
    @CacheLookup
    private WebElement infoProduct;

    //px-4 modal-body
    //col-auto
    @FindBy(how = How.XPATH, using = "//div[@class='px-4 modal-body']//div[@class='col-auto']")
    @CacheLookup
    private List<WebElement> listImages;

    //fw-bolder mb-3
    @FindBy(how = How.XPATH, using = "//h3[@class='fw-bolder mb-3']")
    @CacheLookup
    private WebElement productName;

    String pName = null;

    public int totalProduct(){
        return listItem.size();
    }

    public void addtoBasket(){
        listInfoButton.get(1).click();
        System.out.println("add to basket"+pName);
        addTobasketButton.click();
    }
//alert

    public void getItemInfo(int item)throws ElementClickInterceptedException{
            listItem.get(item).click();
            pName = productName.getText();
            listInfoButton.get(0).click();
            String productInfo = infoProduct.getText();
            Utilities.createNewFolder("D://sellyDownload");
            Utilities.createFileAndInsert(productInfo, "D://sellyDownload//content.txt");


    }

    public void closeProductInfoPopup(){
        closeProductInfoPopup.click();
    }

    public void downloadInmage(){
        listImages.forEach(images -> {
            images.click();
        });
    }

    public void moveFolder(){
        Utilities.moveFolder(pName);
    }

    /**
     * Create new file content after move to new folder
     * @param contentFileName
     */
    public void createNewContentFile(String contentFileName) {
        File contentFile = new File(contentFileName);
        /*try {
            if (!contentFile.exists()) {
                contentFile.createNewFile();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/


    }
}
