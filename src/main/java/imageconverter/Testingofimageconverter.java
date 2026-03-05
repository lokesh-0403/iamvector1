package imageconverter;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobjectmodal.BaseTest;
import pageobjectmodal.ImageConverterPage;
import utils.ResourceHelper;
import utils.RetryAnalyzer;
import utils.TestDataProvider;


public class Testingofimageconverter extends BaseTest {


	private ImageConverterPage imageConverterPage;
	private Goto goto1;
	

	@BeforeMethod
	public void setUp() {
	

		// Initialize page objects
		imageConverterPage = new ImageConverterPage(driver,getDownloadDir());
		goto1 = new Goto(driver);

		// Navigate to the application
		goto1.goTo();
	}


	//	@Test(dataProvider = "uplaodingSVGFile", dataProviderClass = TestDataProvider.class,retryAnalyzer = RetryAnalyzer.class)
	public void testImageConverterSvgToPng(String email, String password, String FILE_PATH) throws Exception {
		String fixedPath = ResourceHelper.absolutePath(FILE_PATH);
		imageConverterPage.convertSvgToPng(fixedPath, driver);
	}

//	@Test(dataProvider = "uplaodingSVGFile", dataProviderClass = TestDataProvider.class,retryAnalyzer = RetryAnalyzer.class)
	public void testImageConverterSvgToJpeg(String email, String password, String FILE_PATH) throws Exception {
		String fixedPath = ResourceHelper.absolutePath(FILE_PATH);
		imageConverterPage.convertSvgToJpeg(fixedPath, driver);
	}

	@Test(dataProvider = "uplaodingSVGFile", dataProviderClass = TestDataProvider.class,retryAnalyzer = RetryAnalyzer.class)
	public void testImageConverterSvgToBase64(String email, String password, String FILE_PATH) throws Exception {
		String fixedPath = ResourceHelper.absolutePath(FILE_PATH);
		imageConverterPage.convertSvgToBase64(fixedPath, driver);
	}

//	@Test(dataProvider = "uplaodingSVGFile", dataProviderClass = TestDataProvider.class,retryAnalyzer = RetryAnalyzer.class)
	public void testImageConverterSvgToWebp(String email, String password, String FILE_PATH) throws Exception {
		String fixedPath = ResourceHelper.absolutePath(FILE_PATH);
		imageConverterPage.convertSvgToWebp(fixedPath, driver);
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}