package testingofdownloadsvgphoto;

import java.io.File;
import java.net.URLDecoder;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import utils.TestDataProvider;

import pageobjectmodal.BasePage;
import pageobjectmodal.LoginPage;
import pageobjectmodal.ImageSvgEditorPage;

public class UploadingSVG extends BaseTest {
	
	public WebDriver driver;
	public BasePage basePage;
	public LoginPage loginPage;
	public ImageSvgEditorPage imageSVGEditor;
	
	@BeforeMethod
	public void setUp() {
		driver = getDriver();
		basePage = new BasePage(driver);
		loginPage = new LoginPage(driver);
		imageSVGEditor = new ImageSvgEditorPage(driver,getDownloadDir());
	    basePage.setupDriver();
		basePage.navigateToApplication();
	}


	@Test(dataProvider = "uplaodingSVGFile", dataProviderClass = TestDataProvider.class)
	public void uploadSvg(String emailId, String password, String file) throws Exception {

	    // Fix: decode URL-encoded path
	    String decodedPath = URLDecoder.decode(file, "UTF-8");

	    File f = new File(decodedPath);
	    if (!f.exists()) {
	        throw new RuntimeException("❌ File does NOT exist: " + decodedPath);
	    }

	    String correctPath = f.getAbsolutePath();
	    System.out.println("✔ Using Path: " + correctPath);

	    basePage.navigateToApplication();
	    loginPage.login(emailId, password);
	    imageSVGEditor.editSVGImage(correctPath, driver);

	    driver.quit();
	}

		    	
	}	