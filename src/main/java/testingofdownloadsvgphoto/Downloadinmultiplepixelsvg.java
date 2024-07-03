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
import org.testng.annotations.Test;

import imageconverter.ChromeOptionConfig;
import imageconverter.Goto;
@Test
public class Downloadinmultiplepixelsvg {
	
	public void downloadInPngWith26px() {
	

		WebDriver driver = new ChromeDriver(ChromeOptionConfig.getChromeOptions());
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
		driver.findElement(By.id("png-option")).click();
		driver.findElement(By.cssSelector("button[data-test-id='icon_size_option_26']")).click();
		driver.findElement(By.cssSelector("button[onclick='downloadIcon()']")).click();
         driver.close();
}
	
	
	
	
	
		
		public void downloadInPngWith46px() {
		

			WebDriver driver = new ChromeDriver(ChromeOptionConfig.getChromeOptions());
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

			 Goto page1 = new Goto(driver);
		       page1.goTo();
			driver.manage().window().maximize();

		
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			try {
				WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
						By.cssSelector("input[class='bg-white px-3 px-md-4 search-input-radius fw-medium form-control']")));
				searchBox.sendKeys("books");
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
			driver.findElement(By.id("png-option")).click();
			driver.findElement(By.cssSelector("button[data-test-id='icon_size_option_46']")).click();
				
			driver.findElement(By.cssSelector("button[onclick='downloadIcon()']")).click();
		     driver.close();
	}
		
		
		public void downloadInPngWith96px() {
		

			WebDriver driver = new ChromeDriver(ChromeOptionConfig.getChromeOptions());
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

			 Goto page1 = new Goto(driver);
		       page1.goTo();
			driver.manage().window().maximize();

		
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			try {
				WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
						By.cssSelector("input[class='bg-white px-3 px-md-4 search-input-radius fw-medium form-control']")));
				searchBox.sendKeys("helicopter");
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
			driver.findElement(By.id("png-option")).click();
			driver.findElement(By.cssSelector("button[data-test-id='icon_size_option_96']")).click();
				
			driver.findElement(By.cssSelector("button[onclick='downloadIcon()']")).click();
		     driver.close();
}
		public void downloadInPngWith128px() {
			

			WebDriver driver = new ChromeDriver(ChromeOptionConfig.getChromeOptions());
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

			 Goto page1 = new Goto(driver);
		       page1.goTo();
			driver.manage().window().maximize();

		
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			try {
				WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
						By.cssSelector("input[class='bg-white px-3 px-md-4 search-input-radius fw-medium form-control']")));
				searchBox.sendKeys("car");
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
			driver.findElement(By.id("png-option")).click();
			driver.findElement(By.cssSelector("button[data-test-id='icon_size_option_128']")).click();
				
			driver.findElement(By.cssSelector("button[onclick='downloadIcon()']")).click();
		     driver.close();
}
		public void downloadInPngWith512px() {
			

			WebDriver driver = new ChromeDriver(ChromeOptionConfig.getChromeOptions());
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

			 Goto page1 = new Goto(driver);
		       page1.goTo();
			driver.manage().window().maximize();

		
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			try {
				WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
						By.cssSelector("input[class='bg-white px-3 px-md-4 search-input-radius fw-medium form-control']")));
				searchBox.sendKeys("apple");
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
			driver.findElement(By.id("png-option")).click();
			driver.findElement(By.cssSelector("button[data-test-id='icon_size_option_512']")).click();
				
			driver.findElement(By.cssSelector("button[onclick='downloadIcon()']")).click();
		     driver.close();
}
		public void downloadInPngWith1024px() {
			

			WebDriver driver = new ChromeDriver(ChromeOptionConfig.getChromeOptions());
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

			 Goto page1 = new Goto(driver);
		       page1.goTo();
			driver.manage().window().maximize();

		
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

			List<WebElement> ele = driver.findElements(By.id("icons_div_container"));
			// Get a random index to select a random SVG icon
			Random random = new Random();
			int randomIndex = random.nextInt(ele.size());

			// Click on the random SVG icon to download it
			WebElement randomSVGIcon = ele.get(randomIndex);
			randomSVGIcon.click();
			driver.findElement(By.id("png-option")).click();
			driver.findElement(By.cssSelector("button[data-test-id='icon_size_option_1024']")).click();
				
			driver.findElement(By.cssSelector("button[onclick='downloadIcon()']")).click();
		     driver.close();
}

}