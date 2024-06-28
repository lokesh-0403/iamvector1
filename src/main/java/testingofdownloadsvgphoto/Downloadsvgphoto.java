package testingofdownloadsvgphoto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import imageconverter.Goto;

import java.time.Duration;
import java.util.List;
import java.util.Random;

@Test
public class Downloadsvgphoto {

	public void allAssestsSvgDownload() {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		 Goto page1 = new Goto(driver);
	       page1.goTo();
		driver.manage().window().maximize();

	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		try {
			WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
					By.cssSelector("input[class='bg-white px-3 px-md-4 search-input-radius fw-medium form-control']")));
			searchBox.sendKeys("doctor");
			driver.findElement(By.cssSelector(".input-group-text.semi-round-search-button.btn-primary.pointer"))
					.click();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("here the error :" + e);
		}

		List<WebElement> ele = driver.findElements(By.id("icons_div_container"));
		// Get a random index to select a random SVG icon
		Random random = new Random();
		int randomIndex = random.nextInt(ele.size());

		// Click on the random SVG icon to download it
		WebElement randomSVGIcon = ele.get(randomIndex);
		randomSVGIcon.click();

		driver.findElement(By.cssSelector("button[onclick='downloadIcon()']")).click();
		driver.close();
	}

	public void outlinedSvgDownload() throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		 Goto page1 = new Goto(driver);
	       page1.goTo();
		driver.manage().window().maximize();

		// Search for "doctor SVG"
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		try {
			WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
					By.cssSelector("input[class='bg-white px-3 px-md-4 search-input-radius fw-medium form-control']")));
			searchBox.sendKeys("hospital");
			driver.findElement(By.cssSelector(".input-group-text.semi-round-search-button.btn-primary.pointer"))
					.click();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("here the error :" + e);
		}

		driver.findElement(By.cssSelector("li[data-test-id='filter_type_outlined']")).click();
		Thread.sleep(3000);
		List<WebElement> ele = driver.findElements(By.id("icons_div_container"));
		// Get a random index to select a random SVG icon
		Random random = new Random();
		int randomIndex = random.nextInt(ele.size());

		// Click on the random SVG icon to download it
		WebElement randomSVGIcon = ele.get(randomIndex);
		randomSVGIcon.click();

		driver.findElement(By.cssSelector("button[onclick='downloadIcon()']")).click();
		driver.close();
	}

	public void multicolourSvgDownload() throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		 Goto page1 = new Goto(driver);
	       page1.goTo();
		driver.manage().window().maximize();

		// Search for "doctor SVG"
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		try {
			WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
					By.cssSelector("input[class='bg-white px-3 px-md-4 search-input-radius fw-medium form-control']")));
			searchBox.sendKeys("building");
			driver.findElement(By.cssSelector(".input-group-text.semi-round-search-button.btn-primary.pointer"))
					.click();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("here the error :" + e);
		}

		driver.findElement(By.cssSelector("li[data-test-id='filter_type_multicolor']")).click();
		Thread.sleep(3000);
		List<WebElement> ele = driver.findElements(By.id("icons_div_container"));
		// Get a random index to select a random SVG icon
		Random random = new Random();
		int randomIndex = random.nextInt(ele.size());

		// Click on the random SVG icon to download it
		WebElement randomSVGIcon = ele.get(randomIndex);
		randomSVGIcon.click();

		driver.findElement(By.cssSelector("button[onclick='downloadIcon()']")).click();
		driver.close();
	}

	public void monocolourSvgDownload() throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		 Goto page1 = new Goto(driver);
	       page1.goTo();
		driver.manage().window().maximize();

		// Search for "doctor SVG"
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		try {
			WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
					By.cssSelector("input[class='bg-white px-3 px-md-4 search-input-radius fw-medium form-control']")));
			searchBox.sendKeys("bike");
			driver.findElement(By.cssSelector(".input-group-text.semi-round-search-button.btn-primary.pointer"))
					.click();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("here the error :" + e);
		}

		driver.findElement(By.cssSelector("li[data-test-id='filter_type_monocolor']")).click();
		Thread.sleep(3000);
		List<WebElement> ele = driver.findElements(By.id("icons_div_container"));
		// Get a random index to select a random SVG icon
		Random random = new Random();
		int randomIndex = random.nextInt(ele.size());

		// Click on the random SVG icon to download it
		WebElement randomSVGIcon = ele.get(randomIndex);
		randomSVGIcon.click();

		driver.findElement(By.cssSelector("button[onclick='downloadIcon()']")).click();
		driver.close();
	}

	

	public void iconSvgDownload() throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		 Goto page1 = new Goto(driver);
	       page1.goTo();
		driver.manage().window().maximize();

		// Search for "doctor SVG"
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		try {
			WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
					By.cssSelector("input[class='bg-white px-3 px-md-4 search-input-radius fw-medium form-control']")));
			searchBox.sendKeys("animal");
			driver.findElement(By.cssSelector(".input-group-text.semi-round-search-button.btn-primary.pointer"))
					.click();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("here the error :" + e);
		}

		driver.findElement(By.cssSelector("li[data-test-id='filter_type_icon']")).click();
		Thread.sleep(3000);
		List<WebElement> ele = driver.findElements(By.id("icons_div_container"));
		// Get a random index to select a random SVG icon
		Random random = new Random();
		int randomIndex = random.nextInt(ele.size());

		// Click on the random SVG icon to download it
		WebElement randomSVGIcon = ele.get(randomIndex);
		randomSVGIcon.click();

		driver.findElement(By.cssSelector("button[onclick='downloadIcon()']")).click();
		driver.close();
	}

	public void filledrSvgDownload() throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		 Goto page1 = new Goto(driver);
	       page1.goTo();
		driver.manage().window().maximize();

		// Search for "doctor SVG"
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		try {
			WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
					By.cssSelector("input[class='bg-white px-3 px-md-4 search-input-radius fw-medium form-control']")));
			searchBox.sendKeys("watch");
			driver.findElement(By.cssSelector(".input-group-text.semi-round-search-button.btn-primary.pointer"))
					.click();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("here the error :" + e);
		}
		driver.findElement(By.cssSelector("li[data-test-id='filter_type_filled']")).click();
		Thread.sleep(3000);
		List<WebElement> ele = driver.findElements(By.id("icons_div_container"));
		// Get a random index to select a random SVG icon
		Random random = new Random();
		int randomIndex = random.nextInt(ele.size());

		// Click on the random SVG icon to download it
		WebElement randomSVGIcon = ele.get(randomIndex);
		randomSVGIcon.click();

		driver.findElement(By.cssSelector("button[onclick='downloadIcon()']")).click();
		driver.close();
	}
}
