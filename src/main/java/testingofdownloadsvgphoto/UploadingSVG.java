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

public class UploadingSVG {
	
	public WebDriver driver;
	public BasePage basePage;
	public LoginPage loginPage;
	
	
	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		basePage = new BasePage(driver);
		loginPage = new LoginPage(driver);

	    basePage.setupDriver();
		basePage.navigateToApplication();
	}

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	
	@Test(dataProvider = "uplaodingSVGFile",dataProviderClass=TestDataProvider.class)
	public void uploadSvg(String emailId,String password,String file, String fileE) throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		
		basePage.navigateToApplication();
		loginPage.login(emailId,password);
		
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(2000);
		
		Actions action = new Actions(driver);
		
		WebElement cardSVG = driver.findElement(By.xpath
				("(//div[@class='card-body p-0 d-flex flex-column align-items-center gap-3 gap-md-4 justify-content-between'])[2]"));
		action.moveToElement(cardSVG).click().perform();
		
//		JavascriptExecutor js1 = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,400)");
		Thread.sleep(2000);

		WebElement clearButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.id("clearButton")));
		clearButton.click();
		
		js.executeScript("window.scrollBy(0,-400)");
		Thread.sleep(2000);
		
//		WebElement clickButton = wait.until(ExpectedConditions.elementToBeClickable(
//				By.id("openFileButton")));
//		clickButton.click();
		
		// checking for the svg upload 
		
		WebElement fileInput = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='file']")));
		fileInput.sendKeys(file);
		
		Thread.sleep(2000);
		
		
		
		String fileExtension = file.substring(file.lastIndexOf(".") + 1);
		
			

		 
		        // Replace this with your actual alert locator if different
		    	if (fileExtension !="svg") {
		    		
		        js.executeScript("window.scrollBy(0,400)");
				Thread.sleep(2000);

				WebElement cleaButton = wait.until(ExpectedConditions.elementToBeClickable(
						By.id("clearButton")));
				cleaButton.click();
		        
				js.executeScript("window.scrollBy(0,-400)");
				Thread.sleep(2000);
		    }
		    	
		    	
		    		
		    		
		    	
	
		    		WebElement fileInput1 = wait.until(ExpectedConditions.presenceOfElementLocated(
		    						By.cssSelector("input[type='file']")));
		    		fileInput1.sendKeys(fileE);
		    		
		    		Thread.sleep(2000);
		    		
	

		
		try {
		
		WebElement resultFrame = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//body[@style='display: flex; justify-content: center; align-items: center;']")));
		if (!resultFrame.isDisplayed()) {
            System.out.println("Result frame not visible.");
            return;
		}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
//		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(2000);
		
		driver.findElement(
				By.xpath("//button[@class='btn btn-secondary dropdown-toggle dropdown-toggle-split']")).click();
		Thread.sleep(2000);
		
		WebElement pixel1 = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("div[class='btn-group dropend'] li:nth-child(2)")));
		 Thread.sleep(1000);
		 Actions actions = new Actions(driver);
		 actions.moveToElement(pixel1).click().perform();
			
		WebElement dwnBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.id("download_png_btn")));
		Thread.sleep(1000);
		dwnBtn.click();
		
		
		driver.quit();
		
			
		}
		    	
	}	