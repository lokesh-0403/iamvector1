package testingofdownloadsvgphoto;

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
import org.testng.annotations.Test;
import utils.TestDataProvider;

import pageobjectmodal.BasePage;
import pageobjectmodal.LoginPage;
import pageobjectmodal.ImageSvgEditorPage;

public class UploadingSVG {
	
	public WebDriver driver;
	public BasePage basePage;
	public LoginPage loginPage;
	public ImageSvgEditorPage imageSVGEditor;
	
	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		basePage = new BasePage(driver);
		loginPage = new LoginPage(driver);
		imageSVGEditor = new ImageSvgEditorPage(driver);
	    basePage.setupDriver();
		basePage.navigateToApplication();
	}


	
	@Test(dataProvider = "uplaodingSVGFile",dataProviderClass=TestDataProvider.class)
	public void uploadSvg(String emailId,String password,String  file, String fileE) throws Exception {
		
		
		basePage.navigateToApplication();
		loginPage.login(emailId,password);
		
		imageSVGEditor.editSVGImage(file,driver);
		
		driver.quit();
		
			
		}
		    	
	}	