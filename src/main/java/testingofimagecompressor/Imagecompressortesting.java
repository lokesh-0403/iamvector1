package testingofimagecompressor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import imageconverter.Testingofimageconverter;
import utils.ResourceHelper;
import utils.RetryAnalyzer;
import utils.TestDataProvider;
import pageobjectmodal.BasePage;
import pageobjectmodal.ImageCompressorPage;
import pageobjectmodal.LoginPage;

public class Imagecompressortesting {
    private WebDriver driver;
    private BasePage basePage;
    private LoginPage loginPage;
    private ImageCompressorPage imageCompressorPage;
    
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        basePage = new BasePage(driver);
        loginPage = new LoginPage(driver);
        imageCompressorPage = new ImageCompressorPage(driver);
        
        basePage.setupDriver();
        basePage.navigateToApplication();
    }
    
    @AfterMethod
    public void tearDown() {
        basePage.closeBrowser();
    }
    
    @Test(dataProvider = "loginCredentials", dataProviderClass = TestDataProvider.class)
    public void testSvgImageCompressorWithLogin(String emailId, String password) throws Exception {
        // Login to the application
        loginPage.login(emailId, password); 
        
        // Compress SVG image
        String svgFilePath = ResourceHelper.getResourceFilePath("files/Female doctor with cross mark.svg");
        String fixedPath = ResourceHelper.absolutePath(svgFilePath);
        imageCompressorPage.compressImage(fixedPath, driver);
    }   
    
 //   @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testPngImageCompressor() throws Exception {
        // Navigate directly to image compressor (no login required)
        String pngFilePath = ResourceHelper.getResourceFilePath("files/iamvector_download.png");
        String fixedPath = ResourceHelper.absolutePath(pngFilePath);
        imageCompressorPage.compressImage(pngFilePath, driver);
    }
    
   // @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testJpgImageCompressor() throws Exception {
        // Navigate directly to image compressor (no login required)
        String jpgFilePath = ResourceHelper.getResourceFilePath("files/Computer desktop imac.jpg");
        String fixedPath = ResourceHelper.absolutePath(jpgFilePath);
        imageCompressorPage.compressImage(jpgFilePath, driver);
    }
    
    //@Test(dataProvider = "imageFiles", dataProviderClass = TestDataProvider.class,retryAnalyzer = RetryAnalyzer.class)
    public void testImageCompressorWithDifferentFormats(String fileType, String filePath) throws Exception {
        // Test different image formats without login
    	String fixedPath = ResourceHelper.absolutePath(filePath);
        imageCompressorPage.compressImage(fixedPath, driver);
    }
}