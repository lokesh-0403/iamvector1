package testingofdownloadsvgphoto;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import imageconverter.ChromeOptionConfig;
import imageconverter.Goto;

@Test
public class Downloadinmultiplepixelsvg {

	private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	@BeforeMethod
	public void setUp() {
		driver.set(new ChromeDriver(ChromeOptionConfig.getChromeOptions()));
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

	public void downloadInPngWith26px() {

		WebDriver driver = getDriver();

		Goto page1 = new Goto(driver);
		page1.goTo();
		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
		searchBox.sendKeys("doctor");

		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector(".input-group-text.semi-round-search-button.btn-primary.pointer")));
		searchButton.click();

		WebElement element1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-test-id='icon_element']")));
		element1.click();

		WebElement webpOption = wait.until(ExpectedConditions.elementToBeClickable(By.id("png-option")));
		webpOption.click();
		WebElement sizeOption = wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-test-id='icon_size_option_26']")));
		sizeOption.click();

		WebElement downloadButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick='downloadIcon()']")));
		downloadButton.click();

	}

	public void downloadInPngWith46px() {

		WebDriver driver = getDriver();

		Goto page1 = new Goto(driver);
		page1.goTo();
		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
		searchBox.sendKeys("table");

		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector(".input-group-text.semi-round-search-button.btn-primary.pointer")));
		searchButton.click();

		WebElement element1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-test-id='icon_element']")));
		element1.click();

		WebElement webpOption = wait.until(ExpectedConditions.elementToBeClickable(By.id("png-option")));
		webpOption.click();
		WebElement sizeOption = wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-test-id='icon_size_option_46']")));
		sizeOption.click();

		WebElement downloadButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick='downloadIcon()']")));
		downloadButton.click();

	}

	public void downloadInPngWith96px() {
		WebDriver driver = getDriver();

		Goto page1 = new Goto(driver);
		page1.goTo();
		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
		searchBox.sendKeys("bike");

		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector(".input-group-text.semi-round-search-button.btn-primary.pointer")));
		searchButton.click();

		WebElement element1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-test-id='icon_element']")));
		element1.click();

		WebElement webpOption = wait.until(ExpectedConditions.elementToBeClickable(By.id("png-option")));
		webpOption.click();
		WebElement sizeOption = wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-test-id='icon_size_option_96']")));
		sizeOption.click();

		WebElement downloadButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick='downloadIcon()']")));
		downloadButton.click();

	}

	public void downloadInPngWith128px() {

		WebDriver driver = getDriver();

		Goto page1 = new Goto(driver);
		page1.goTo();
		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
		searchBox.sendKeys("telephone");

		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector(".input-group-text.semi-round-search-button.btn-primary.pointer")));
		searchButton.click();

		WebElement element1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-test-id='icon_element']")));
		element1.click();

		WebElement webpOption = wait.until(ExpectedConditions.elementToBeClickable(By.id("png-option")));
		webpOption.click();
		WebElement sizeOption = wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-test-id='icon_size_option_128']")));
		sizeOption.click();

		WebElement downloadButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick='downloadIcon()']")));
		downloadButton.click();

	}

	public void downloadInPngWith512px() {
		WebDriver driver = getDriver();

		Goto page1 = new Goto(driver);
		page1.goTo();
		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
		searchBox.sendKeys("screen");

		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector(".input-group-text.semi-round-search-button.btn-primary.pointer")));
		searchButton.click();

		WebElement element1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-test-id='icon_element']")));
		element1.click();

		WebElement webpOption = wait.until(ExpectedConditions.elementToBeClickable(By.id("png-option")));
		webpOption.click();
		WebElement sizeOption = wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-test-id='icon_size_option_512']")));
		sizeOption.click();

		WebElement downloadButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick='downloadIcon()']")));
		downloadButton.click();

	}

	public void downloadInPngWith1024px() {

		WebDriver driver = getDriver();

		Goto page1 = new Goto(driver);
		page1.goTo();
		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
		searchBox.sendKeys("cup");

		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector(".input-group-text.semi-round-search-button.btn-primary.pointer")));
		searchButton.click();

		WebElement element1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-test-id='icon_element']")));
		element1.click();

		WebElement webpOption = wait.until(ExpectedConditions.elementToBeClickable(By.id("png-option")));
		webpOption.click();
		WebElement sizeOption = wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("button[data-test-id='icon_size_option_1024']")));
		sizeOption.click();

		WebElement downloadButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick='downloadIcon()']")));
		downloadButton.click();

	}

}