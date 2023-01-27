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
import java.util.stream.Stream;

public class SearchResultPage {
    public SearchResultPage(WebDriver webdriver){
        PageFactory.initElements(webdriver, this);
    }


    //justify-content-center modal-footer
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'justify-content-center modal-footer')]//button]")
    @CacheLookup
    private WebElement addTobasketButton;

    @FindBy(how = How.XPATH, using = "//div[@class='GridStyle__StyledGrid-sc-1r6thsr-0 ihvoBx']")
    @CacheLookup
    private List<WebElement> listItem;

    //d-flex flex-row w-100 mt-3
    @FindBy(how = How.XPATH, using = "//div[@class='ps-document']//p")
    @CacheLookup
    private List<WebElement> listInfoContent;
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
    @FindBy(how = How.XPATH, using = "//h1[@class='Typography-sc-1nbqu5-0 fvuXSd']")
    @CacheLookup
    private WebElement productName;

    @FindBy(how = How.XPATH, using = "//button[@class='Button-l2616d-0 bDFgyw']")
    @CacheLookup
    private WebElement downloadImage;

    String pName = null;

    public int totalProduct(){
        return listItem.size();
    }


    public void downloadImageButton(){
        downloadImage.click();
    }

    public void getItemInfo(int item)throws ElementClickInterceptedException {
        listItem.get(item).click();
        pName = productName.getText();
        String productInfo = getProductInformation(listInfoContent);
        //String productInfo = infoProduct.getText();
        Utilities.createNewFolder("D://funniMartDownload");
        Utilities.createFileAndInsert(productInfo, "D://funniMartDownload//content.txt");
    }

    private String getProductInformation(List<WebElement> listProInfo){
        StringBuilder stringBuilder = new StringBuilder();
        for(WebElement proInfo : listProInfo){
            stringBuilder.append(proInfo.getText());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
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
