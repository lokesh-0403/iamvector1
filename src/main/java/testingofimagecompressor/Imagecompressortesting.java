package testingofimagecompressor;
import java.awt.AWTException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import utils.ResourceHelper;
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
    public void testSvgImageCompressorWithLogin(String emailId, String password) throws AWTException, InterruptedException {
        // Login to the application
        loginPage.login(emailId, password); 
        
        // Compress SVG image
        String svgFilePath = ResourceHelper.getResourceFilePath("files/Female doctor with cross mark.svg");
        imageCompressorPage.compressImage(svgFilePath, driver);
    }   
    
    @Test(retryAnalyzer = utils.RetryAnalyzer.class)
    public void testPngImageCompressor() throws AWTException, InterruptedException {
        // Navigate directly to image compressor (no login required)
        String pngFilePath = ResourceHelper.getResourceFilePath("files/iamvector_download.png");
        imageCompressorPage.compressImage(pngFilePath, driver);
    }
    
    @Test(retryAnalyzer = utils.RetryAnalyzer.class)
    public void testJpgImageCompressor() throws AWTException, InterruptedException {
        // Navigate directly to image compressor (no login required)
        String jpgFilePath = ResourceHelper.getResourceFilePath("files/Mini arrow left.jpg");
        imageCompressorPage.compressImage(jpgFilePath, driver);
    }
    
    @Test(dataProvider = "imageFiles", dataProviderClass = TestDataProvider.class,retryAnalyzer = utils.RetryAnalyzer.class)
    public void testImageCompressorWithDifferentFormats(String fileType, String filePath) throws AWTException, InterruptedException {
        // Test different image formats without login
        imageCompressorPage.compressImage(filePath, driver);
    }
}