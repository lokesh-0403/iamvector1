package testingofimagecompressor;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import pageobjectmodal.BasePage;
import pageobjectmodal.ImageCompressorPage;
import pageobjectmodal.LoginPage;
import utils.TestDataProvider;
import utils.ResourceHelper;
import utils.RetryAnalyzer;
public class Imagecompressortesting extends BaseTest {

    private BasePage basePage;
    private LoginPage loginPage;
    private ImageCompressorPage imageCompressorPage;

    @BeforeMethod
    public void setUp() {
        basePage = new BasePage(driver);
        loginPage = new LoginPage(driver);
        imageCompressorPage = new ImageCompressorPage(driver, getDownloadDir());

        basePage.navigateToApplication();
    }

    @Test(dataProvider = "loginCredentials",
          dataProviderClass = TestDataProvider.class,
          retryAnalyzer = RetryAnalyzer.class)
    public void testSvgImageCompressorWithLogin(String emailId, String password) throws Exception {

        loginPage.login(emailId, password);

        String svgFilePath = ResourceHelper.getResourceFilePath("files/Female doctor with cross mark.svg");
        String fixedPath = ResourceHelper.absolutePath(svgFilePath);

        imageCompressorPage.compressImage(fixedPath, driver);
    }

  
    
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testPngImageCompressor() throws Exception {
        // Navigate directly to image compressor (no login required)
        String pngFilePath = ResourceHelper.getResourceFilePath("files/iamvector_download.png");
        String fixedPath = ResourceHelper.absolutePath(pngFilePath);
        imageCompressorPage.compressImage(fixedPath, driver);
    }
    
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testJpgImageCompressor() throws Exception {
        // Navigate directly to image compressor (no login required)
        String jpgFilePath = ResourceHelper.getResourceFilePath("files/Computer desktop imac.jpg");
        String fixedPath = ResourceHelper.absolutePath(jpgFilePath);
        imageCompressorPage.compressImage(fixedPath, driver);
    }
    
    @Test(dataProvider = "imageFiles", dataProviderClass = TestDataProvider.class,retryAnalyzer = RetryAnalyzer.class)
    public void testImageCompressorWithDifferentFormats(String fileType, String filePath) throws Exception {
        // Test different image formats without login
    	String fixedPath = ResourceHelper.absolutePath(filePath);
        imageCompressorPage.compressImage(fixedPath, driver);
    }
}
