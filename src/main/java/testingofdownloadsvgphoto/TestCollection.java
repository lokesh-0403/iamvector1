package testingofdownloadsvgphoto;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageobjectmodal.BasePage;
import pageobjectmodal.BaseTest;
import pageobjectmodal.CollectionModal;
import pageobjectmodal.HomePageCollection;
import pageobjectmodal.LoginPage;
import pageobjectmodal.MyCollectionsPage;
import utils.TestDataProvider;

public class TestCollection extends BaseTest {
    private WebDriver driver;
    private BasePage basePage;
    private LoginPage loginPage;
    private HomePageCollection homePage;
    private CollectionModal collectionModal;
    private MyCollectionsPage myCollectionsPage;
    

    @BeforeMethod
    public void setUp() {
    	driver = getDriver();
        basePage = new BasePage(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePageCollection(driver);
        collectionModal = new CollectionModal(driver);
        myCollectionsPage = new MyCollectionsPage(driver);

        basePage.setupDriver();
        basePage.navigateToApplication();
    }
    
    
    

    // Uncomment when needed
     @AfterMethod
     public void tearDown() {
         basePage.closeBrowser();
     }
     
  
   
//   @Test(dataProvider = "loginCredentialsAndKeyValue", dataProviderClass = TestDataProvider.class)
//    public void addToCollection(String emailId, String password, String searchKey)
//            throws AWTException, InterruptedException {
//        
//        // Login
//        loginPage.login(emailId, password);
//        Thread.sleep(6000);
//
//        // Search for content
//        homePage.searchForContent(searchKey);
//
//        // Select random icon and get its ID
//        WebElement randomIcon = homePage.selectRandomIcon();
//        String uniqueId = homePage.getIconUniqueId(randomIcon);
//        System.out.println("Selected Icon ID: " + uniqueId);
//
//        // Save to collection
//        homePage.clickSaveToCollectionButton(randomIcon);
//        collectionModal.addToExistingCollection("card");
//
//        
//        Thread.sleep(4000);
//        // Navigate to My Collections and verify
//        myCollectionsPage.navigateToMyCollections();
//        myCollectionsPage.openCollection("card");
//
//        // Verify icon is present and download
//        boolean isPresent = homePage.isIconPresentInList(uniqueId);
//        if (isPresent) {
//            System.out.println("Icon is present in collection.");
//            homePage.clickIconByUniqueId(uniqueId);
////            myCollectionsPage.downloadIcon();
//        } else {
//            Assert.fail("Icon is NOT present in the collection.");
//        }   
//      //  driver.close();
//    }
     
   @Test(dataProvider = "loginCredentialsAndKeyValue", dataProviderClass = TestDataProvider.class)
     public void addToCollection(String emailId, String password, String searchKey)
             throws AWTException, InterruptedException {

         // Login
         loginPage.login(emailId, password);
         Thread.sleep(6000);

         // Search for content
         homePage.searchForContent(searchKey);

         // Select random UNSAVED icon and get its ID
         WebElement randomIcon = homePage.selectUnsavedRandomIcon(); // Changed method
         String uniqueId = homePage.getIconUniqueId(randomIcon);
         System.out.println("Selected Icon ID: " + uniqueId);

         // Save to collection
         homePage.clickSaveToCollectionButton(randomIcon);
         collectionModal.addToExistingCollection("card");

         Thread.sleep(4000);
         
         // Navigate to My Collections and verify
         myCollectionsPage.navigateToMyCollections();
         myCollectionsPage.openCollection("card");

         // Verify icon is present and download
         boolean isPresent = homePage.isIconPresentInList(uniqueId);
         if (isPresent) {
             System.out.println("Icon is present in collection.");
             homePage.clickIconByUniqueId(uniqueId);
         } else {
             Assert.fail("Icon is NOT present in the collection.");
         }
     }
     
     
     
     
     

   @Test(dataProvider = "loginCredentialsForManageCollection", dataProviderClass = TestDataProvider.class,retryAnalyzer = utils.RetryAnalyzer.class)
    public void addToCollectionCreateNewCollectionThenDeleteCollection(String emailId, String password,
            String searchKey, String collectionName) throws AWTException, InterruptedException {
        
        // Login
        loginPage.login(emailId, password);
        Thread.sleep(6000);

        // Search for content
        homePage.searchForContent(searchKey);

        // Select random icon and get its ID
        WebElement randomIcon = homePage.selectUnsavedRandomIcon();
        String uniqueId = homePage.getIconUniqueId(randomIcon);
        System.out.println("Selected Icon ID: " + uniqueId);

        // Create new collection and add icon
        homePage.clickSaveToCollectionButton(randomIcon);
        collectionModal.createNewCollectionAndAdd(collectionName);

        // Navigate to My Collections and verify
        Thread.sleep(1000);
        myCollectionsPage.navigateToMyCollections();
        myCollectionsPage.openCollection(collectionName);

        // Verify icon is present
        boolean isPresent = homePage.isIconPresentInList(uniqueId);
        if (isPresent) {
            System.out.println("Icon is present in new collection.");
            homePage.clickIconByUniqueId(uniqueId);
        } else {
            Assert.fail("Icon is NOT present in the new collection.");
        }
        // Delete the collection
        myCollectionsPage.deleteCollection(collectionName);
        driver.close();
    }

   @Test(dataProvider = "loginCredentialsForManageCollection", dataProviderClass = TestDataProvider.class,retryAnalyzer = utils.RetryAnalyzer.class)
    public void addToCollectionCreateNewCollectionThenEditCollectionName(String emailId, String password,
            String searchKey, String collectionName) throws AWTException, InterruptedException {
        
        // Login
        loginPage.login(emailId, password);
        Thread.sleep(6000);

        // Search for content
        homePage.searchForContent(searchKey);

        // Select random icon and get its ID
        WebElement randomIcon = homePage.selectUnsavedRandomIcon();
        String uniqueId = homePage.getIconUniqueId(randomIcon);
        System.out.println("Selected Icon ID: " + uniqueId);

        // Create new collection and add icon
        homePage.clickSaveToCollectionButton(randomIcon);
        collectionModal.createNewCollectionAndAdd(collectionName);

        // Navigate to My Collections and verify
        myCollectionsPage.navigateToMyCollections();
        myCollectionsPage.openCollection(collectionName);

        // Verify icon is present
        boolean isPresent = homePage.isIconPresentInList(uniqueId);
        if (isPresent) {
            System.out.println("Icon is present in new collection.");
            homePage.clickIconByUniqueId(uniqueId);
        } else {
            Assert.fail("Icon is NOT present in the new collection.");
        }

        // Edit collection name
        myCollectionsPage.editCollectionName(collectionName);
        myCollectionsPage.updateCollectionName();
        driver.close();
    }
}