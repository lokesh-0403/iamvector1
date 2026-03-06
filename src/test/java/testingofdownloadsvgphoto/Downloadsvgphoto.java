package testingofdownloadsvgphoto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import imageconverter.Goto;

import java.time.Duration;
import java.util.List;
import java.util.Random;

@Test(retryAnalyzer = utils.RetryAnalyzer.class)
public class Downloadsvgphoto extends BaseTest {
	
	private ThreadLocal<WebDriver> driver = new ThreadLocal<>();


	@AfterMethod
	public void tearDown() {
		getDriver().quit();
		driver.remove();
	}

	

//	@Test
	public void allAssestsSvgDownload() {
		WebDriver driver = getDriver();

		 Goto page1 = new Goto(driver);
	       page1.goTo();
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
		searchBox.sendKeys("doctor");

		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector(".input-group-text.semi-round-search-button.btn-primary.pointer")));
		searchButton.click();

		WebElement element1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-test-id='icon_element']")));
		element1.click();

		
		WebElement sizeOption = wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-test-id='icon_size_option_26']")));
		sizeOption.click();

		WebElement downloadButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick='downloadIcon()']")));
		downloadButton.click();

	}
	
	
	

	public void outlinedSvgDownload() throws InterruptedException {
		
		WebDriver driver = getDriver();

		 Goto page1 = new Goto(driver);
	       page1.goTo();
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
		searchBox.sendKeys("doctor");

		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector(".input-group-text.semi-round-search-button.btn-primary.pointer")));
		searchButton.click();

		

		WebElement element11 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[data-test-id='filter_type_outlined']")));
		element11.click();

		
		
		WebElement element1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-test-id='icon_element']")));
		element1.click();

	
		WebElement downloadButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick='downloadIcon()']")));
		downloadButton.click();
	}

	public void multicolorSvgDownload() throws InterruptedException {
		
		

		WebDriver driver = getDriver();

		 Goto page1 = new Goto(driver);
	       page1.goTo();
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
		searchBox.sendKeys("chair");

		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector(".input-group-text.semi-round-search-button.btn-primary.pointer")));
		searchButton.click();

		

		WebElement element11 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[data-test-id='filter_type_multicolor']")));
		element11.click();

		
		
		WebElement element1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-test-id='icon_element']")));
		element1.click();

	
		WebElement downloadButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick='downloadIcon()']")));
		downloadButton.click();
	}

	public void monocolourSvgDownload() throws InterruptedException {

		WebDriver driver = getDriver();

		 Goto page1 = new Goto(driver);
	       page1.goTo();
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
		searchBox.sendKeys("chair");

		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector(".input-group-text.semi-round-search-button.btn-primary.pointer")));
		searchButton.click();

		

		WebElement element11 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[data-test-id='filter_type_monocolor']")));
		element11.click();

		
		
		WebElement element1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-test-id='icon_element']")));
		element1.click();

	
		WebElement downloadButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick='downloadIcon()']")));
		downloadButton.click();
		
		
		
		
		
	}

	

	public void iconSvgDownload() throws InterruptedException {
		WebDriver driver = getDriver();

		 Goto page1 = new Goto(driver);
	       page1.goTo();
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
		searchBox.sendKeys("chair");

		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector(".input-group-text.semi-round-search-button.btn-primary.pointer")));
		searchButton.click();

		

		WebElement element11 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[data-test-id='filter_type_icon']")));
		element11.click();

		
		
		WebElement element1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-test-id='icon_element']")));
		element1.click();

	
		WebElement downloadButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick='downloadIcon()']")));
		downloadButton.click();
		
	}

	public void pixelSvgDownload() throws InterruptedException {
		WebDriver driver = getDriver();

		 Goto page1 = new Goto(driver);
	       page1.goTo();
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
		searchBox.sendKeys("animals");

		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector(".input-group-text.semi-round-search-button.btn-primary.pointer")));
		searchButton.click();

		

		WebElement element11 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[data-test-id='filter_type_pixel']")));
		element11.click();

		
		
		WebElement element1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-test-id='icon_element']")));
		element1.click();

	
		WebElement downloadButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick='downloadIcon()']")));
		downloadButton.click();
	
	}
	
//	@Test
	public void thinSvgDownload() throws InterruptedException {
		WebDriver driver = getDriver();

		 Goto page1 = new Goto(driver);
	       page1.goTo();
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
		searchBox.sendKeys("animals");

		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector(".input-group-text.semi-round-search-button.btn-primary.pointer")));
		searchButton.click();

		

		WebElement element11 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[data-test-id='filter_type_thin']")));
		element11.click();

		
		
		WebElement element1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-test-id='icon_element']")));
		element1.click();

	
		WebElement downloadButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick='downloadIcon()']")));
		downloadButton.click();
	
	}
	
	
	
	public void interfaceSvgDownload() throws InterruptedException {
		WebDriver driver = getDriver();

		 Goto page1 = new Goto(driver);
	       page1.goTo();
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
		searchBox.sendKeys("chair");

		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector(".input-group-text.semi-round-search-button.btn-primary.pointer")));
		searchButton.click();

		

		WebElement element11 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[data-test-id='filter_type_interface']")));
		element11.click();

		
		
		WebElement element1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-test-id='icon_element']")));
		element1.click();

	
		WebElement downloadButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick='downloadIcon()']")));
		downloadButton.click();
	
	}
	
	public void lineSvgDownload() throws InterruptedException {
		WebDriver driver = getDriver();

		 Goto page1 = new Goto(driver);
	       page1.goTo();
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
		searchBox.sendKeys("chair");

		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector(".input-group-text.semi-round-search-button.btn-primary.pointer")));
		searchButton.click();

		

		WebElement element11 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[data-test-id='filter_type_line']")));
		element11.click();

		
		
		WebElement element1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-test-id='icon_element']")));
		element1.click();

	
		WebElement downloadButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick='downloadIcon()']")));
		downloadButton.click();
	
	}
	
	
}
