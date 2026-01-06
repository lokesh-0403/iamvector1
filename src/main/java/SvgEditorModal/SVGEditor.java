package SvgEditorModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utils.TestDataProvider;
import pageobjectmodal.BasePage;
import pageobjectmodal.BaseTest;
import pageobjectmodal.ImageSvgEditorPage;
import pageobjectmodal.LoginPage;

public class SVGEditor extends BaseTest {
	
	   private BasePage basePage;
	    private LoginPage loginPage;
	    private ImageSvgEditorPage imageSvgEditorPage;
    
    @BeforeMethod
    public void setUp() {
    
    	    basePage = new BasePage(driver);
    	    basePage.setupDriver();
    	    basePage.navigateToApplication();
    	    loginPage = new LoginPage(driver);
    	    imageSvgEditorPage = new ImageSvgEditorPage(driver);
    }
    
   
    
   
    @Test(dataProvider = "loginCredentials", dataProviderClass = TestDataProvider.class)
    public void testSVGEditor(String emailId, String password) throws Exception {
    	 loginPage.login(emailId, password); 
        // Navigate directly to image compressor (no login required)
        String pngFilePath = "m136.81 116.53c.69 26.17-64.11 42-81.52-.73";
		imageSvgEditorPage.editSVGImage(pngFilePath, driver);
    }
    
    
}