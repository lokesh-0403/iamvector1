package testingofdownloadsvgphoto;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import imageconverter.Goto;
@Test
public class Downloadinginmultiplepixelwebp {

	private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	@BeforeMethod
	public void setUp() {
		driver.set(new ChromeDriver());
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		getDriver().manage().window().maximize();
	}

	@AfterMethod
	public void tearDown() {
		getDriver().quit();
		driver.remove();
	}

	private WebDriver getDriver() {
		return driver.get();
	}

	@Test
	public void downloadInWebpWith26px() throws InterruptedException {
		WebDriver driver = getDriver();

		Goto page1 = new Goto(driver);
		page1.goTo();
		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
		searchBox.sendKeys("doctor");

//		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
//				By.cssSelector(".input-group-text.semi-round-search-button.btn-primary.pointer")));
//		searchButton.click();

		try {

			WebElement searchEnter = wait.until(ExpectedConditions.elementToBeClickable(
					By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
			searchEnter.sendKeys(Keys.ENTER);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement element1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-test-id='icon_element']")));
		element1.click();

		WebElement webpOption = wait.until(ExpectedConditions.elementToBeClickable(By.id("webp-option")));
		webpOption.click();
		WebElement sizeOption = wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-test-id='icon_size_option_26']")));
		sizeOption.click();

		WebElement downloadButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick='downloadIcon()']")));
		downloadButton.click();
		Thread.sleep(2000);

	}

	
	public void downloadInWebpWith46px() throws InterruptedException {
		WebDriver driver = getDriver();

		Goto page1 = new Goto(driver);
		page1.goTo();
		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
		searchBox.sendKeys("books");

//		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
//				By.cssSelector(".input-group-text.semi-round-search-button.btn-primary.pointer")));
//		searchButton.click();

		try {

			WebElement searchEnter = wait.until(ExpectedConditions.elementToBeClickable(
					By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
			searchEnter.sendKeys(Keys.ENTER);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement element1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-test-id='icon_element']")));
		element1.click();

		WebElement webpOption = wait.until(ExpectedConditions.elementToBeClickable(By.id("webp-option")));
		webpOption.click();
		WebElement sizeOption = wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-test-id='icon_size_option_46']")));
		sizeOption.click();

		WebElement downloadButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick='downloadIcon()']")));
		downloadButton.click();
		Thread.sleep(2000);

	}

	@Test
	public void downloadInWebpWith96px() throws InterruptedException {

		WebDriver driver = getDriver();

		Goto page1 = new Goto(driver);
		page1.goTo();
		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
		searchBox.sendKeys("office");

//		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
//				By.cssSelector(".input-group-text.semi-round-search-button.btn-primary.pointer")));
//		searchButton.click();

		try {

			WebElement searchEnter = wait.until(ExpectedConditions.elementToBeClickable(
					By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
			searchEnter.sendKeys(Keys.ENTER);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement element1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-test-id='icon_element']")));
		element1.click();

		WebElement webpOption = wait.until(ExpectedConditions.elementToBeClickable(By.id("webp-option")));
		webpOption.click();
		WebElement sizeOption = wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-test-id='icon_size_option_96']")));
		sizeOption.click();

		WebElement downloadButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick='downloadIcon()']")));
		downloadButton.click();
		Thread.sleep(2000);
		
	}

	
	public void downloadInWebpWith128px() {

		WebDriver driver = getDriver();

		Goto page1 = new Goto(driver);
		page1.goTo();
		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
		searchBox.sendKeys("bottle");

//		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
//				By.cssSelector(".input-group-text.semi-round-search-button.btn-primary.pointer")));
//		searchButton.click();

		try {

			WebElement searchEnter = wait.until(ExpectedConditions.elementToBeClickable(
					By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
			searchEnter.sendKeys(Keys.ENTER);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement element1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-test-id='icon_element']")));
		element1.click();

		WebElement webpOption = wait.until(ExpectedConditions.elementToBeClickable(By.id("webp-option")));
		webpOption.click();
		WebElement sizeOption = wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-test-id='icon_size_option_128']")));
		sizeOption.click();

		WebElement downloadButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick='downloadIcon()']")));
		downloadButton.click();

	}

	
	public void downloadInWebpWith512px() {
		WebDriver driver = getDriver();

		Goto page1 = new Goto(driver);
		page1.goTo();
		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
		searchBox.sendKeys("mobile");

//		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
//				By.cssSelector(".input-group-text.semi-round-search-button.btn-primary.pointer")));
//		searchButton.click();

		try {

			WebElement searchEnter = wait.until(ExpectedConditions.elementToBeClickable(
					By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
			searchEnter.sendKeys(Keys.ENTER);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement element1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-test-id='icon_element']")));
		element1.click();

		WebElement webpOption = wait.until(ExpectedConditions.elementToBeClickable(By.id("webp-option")));
		webpOption.click();
		WebElement sizeOption = wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-test-id='icon_size_option_512']")));
		sizeOption.click();

		WebElement downloadButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick='downloadIcon()']")));
		downloadButton.click();

	}

	@Test
	public void downloadInWebpWith1024px() {
		WebDriver driver = getDriver();

		Goto page1 = new Goto(driver);
		page1.goTo();
		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
		searchBox.sendKeys("car");

//		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
//				By.cssSelector(".input-group-text.semi-round-search-button.btn-primary.pointer")));
//		searchButton.click();

		try {

			WebElement searchEnter = wait.until(ExpectedConditions.elementToBeClickable(
					By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
			searchEnter.sendKeys(Keys.ENTER);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement element1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-test-id='icon_element']")));
		element1.click();

		WebElement webpOption = wait.until(ExpectedConditions.elementToBeClickable(By.id("webp-option")));
		webpOption.click();
		WebElement sizeOption = wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("button[data-test-id='icon_size_option_1024']")));
		sizeOption.click();

		WebElement downloadButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick='downloadIcon()']")));
		downloadButton.click();

	}

}
