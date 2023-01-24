package selly.page.uploadProduct;

import selly.page.ShopeeConstants;
import selly.service.Utilities;

import java.io.File;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ProductInfoPage extends ShopeeConstants {

  //product-category-box-inner
  @CacheLookup
  @FindBy(how = How.XPATH, using = "//div[@class='product-category-box-inner']")
  private WebElement productType;

  //category-item selected
  @CacheLookup
  @FindBy(how = How.XPATH, using = "//li[@class='category-item']//p")
  private List<WebElement> listProductType;
  //Túi Ví Nam

  //category-item category-item selected
  @CacheLookup
  @FindBy(how = How.XPATH, using = "//li[@class='category-item category-item']")
  private List<WebElement> backpack; // ba lo nam

  //category-selector category-selector
  @CacheLookup
  @FindBy(how = How.XPATH, using = "//div[@class='category-selector category-selector']//button[@type='button']")
  private List<WebElement> submitProductType;

  private void inputBackPack(){
    productType.click();
    Utilities.sleep(300);
    listProductType.forEach(pType-> {if("Túi Ví Nam".equals(pType.getText())){
      pType.click();
    }});
    Utilities.sleep(300);
    backpack.get(DETAIL_CATEGORY_BA_LO).click();
    submitProductType.get(1).click();
  }

  @CacheLookup
  @FindBy(how = How.XPATH, using = "//div[@class='product-edit__side']//a")
  private List<WebElement> listProductInfo;

  @CacheLookup
  @FindBy(how = How.XPATH, using = "//div[@class='shopee-upload-wrapper shopee-upload-dragger']//input[@type='file']")
  private WebElement uploadImages;
  //shopee-input__inner shopee-input__inner--large
  @CacheLookup
  @FindBy(how = How.XPATH, using = "//div[@class='shopee-input__inner shopee-input__inner--large']//input[@type='text']")
  private WebElement productname;


  //product-category-text
  @CacheLookup
  @FindBy(how = How.XPATH, using = "//div[@class='product-category-text']//span")
  private WebElement productCategory;

  //category-list/li
  @CacheLookup
  @FindBy(how = How.XPATH, using = "//div[@class='category-list']//li")
  private List<WebElement> listProductCategory;

  public ProductInfoPage(WebDriver webDriver) {
    PageFactory.initElements(webDriver, this);
  }


  private void inputProductName(String pName) {
    productname.sendKeys(pName);
  }

  public void putProductInfo(WebDriver driver, String argPath) {
    /*String path = "D://selly";*/
    List<String> listFolderName = Utilities.getListFolderNameFromPath(argPath);
    inputProductName(listFolderName.get(0));
    inputProductDescription(Utilities.getContentFromTextFile(
        argPath + "//" + listFolderName.get(0) + "//" + "content.txt"));
    Utilities.getAllImagesFileFromfolder(argPath + "//" + listFolderName.get(0)).forEach(file -> {
      Utilities.moveToElementByAction(driver, uploadImages);
      uploadImages.sendKeys(file.toPath().toString());
      System.out.println("--uploading :" + file.toPath().toString());
      Utilities.sleep(300);

    });
    inputBackPack();

        /*listFolderName.forEach(folderName -> {
            inputProductName(folderName);
            inputProductDescription(Utilities.getContentFromTextFile(path + "//" + folderName + "//" + "content.txt"));
            Utilities.getAllImagesFileFromfolder(path + "//" + listFolderName.get(0)).forEach(file -> {
                Utilities.moveToElementByAction(driver, uploadImages);
                uploadImages.sendKeys(file.toPath().toString());
                System.out.println("--uploading :" + file.toPath().toString());
                Utilities.sleep(300);
            });
        });
        inputBackPack();*/
  }

  private void clickListCategory(String productCate) {
    switch (productCate) {
    case WOMEN_FASHION: {
      listProductInfo.get(0).click();
    }
    caseMEN_FASHION: {
      listProductInfo.get(1).click();
    }
    }

  }

  private void clickProductCategory() {
    productCategory.click();
  }


  //shopee-input shopee-input__area//textarea

  @CacheLookup
  @FindBy(how = How.XPATH, using = "//div[@class='shopee-input shopee-input__area']//textarea[@type='textarea']")
  private WebElement productDescription;

  private void inputProductDescription(String description) {
    productDescription.sendKeys(description);
  }


  private void clickOn(String clickOn) {
    switch (clickOn) {
    case BASIC_INFO:
    listProductInfo.get(0).click();
    caseDETAIL_INFO:
    listProductInfo.get(1).click();
    caseDELIVERY_INFO:
    listProductInfo.get(2).click();
    caseOTHER_INFO:
    listProductInfo.get(3).click();
    }
  }

  private void uploadImages(List<File> listImages) {

    listImages.forEach(image -> {
      System.out.println("--processing for file: " + image.toPath().toString());
      uploadImages.sendKeys(image.toPath().toString());
    });
  }
}
