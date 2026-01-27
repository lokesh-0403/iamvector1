package testingofdownloadsvgphoto;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobjectmodal.BasePage;
import pageobjectmodal.BaseTest;
import pageobjectmodal.CollectionModal;
import pageobjectmodal.HomePageCollection;
import pageobjectmodal.LoginPage;
import pageobjectmodal.MyCollectionsPage;
import utils.TestDataProvider;

public class IconCountTest extends BaseTest {

	
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
	     
	     
	     @Test(dataProvider = "loginCredentialsAndKeyValue",
	             dataProviderClass = TestDataProvider.class)
	       public void iconCountTest(String emailId, String password, String searchKey) throws InterruptedException {

	           loginPage.login(emailId, password);

	           Thread.sleep(2000);

	           homePage.searchForContent(searchKey);

	           int expectedCount = homePage.getExpectedIconCount();

	           homePage.loadIconsUntilCountMatches(expectedCount);

	           int actualCount = homePage.getAllRecomendedIcons().size();

	           Assert.assertEquals(
	                   actualCount,
	                   expectedCount,
	                   "Icon count mismatch after loading all icons"
	           );
	       }
	   
}
