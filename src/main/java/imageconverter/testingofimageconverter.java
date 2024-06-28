package imageconverter;

import java.awt.AWTException;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
@Test
public class testingofimageconverter {

	public void imageConverterSvgToPng() throws AWTException {
		
		WebDriver driver = new ChromeDriver();
		
     
       
	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	    Goto page1 = new Goto(driver);
	       page1.goTo();
	       
	
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Scroll down the page
		js.executeScript("window.scrollBy(0, 300);"); // Scroll down by 1000 pixels
		driver.findElement(
				By.xpath("//a[@href='/image-convertor']//div[@class='card p-4 py-md-5 shadow-sm border border-1']"))
				.click();
		WebElement ele = driver.findElement(By.cssSelector("input[type='file']"));
		ele.sendKeys("//Users/yeshsharma//Documents//Female doctor to guide.svg");
		driver.findElement(By.cssSelector(".files-item__download")).click();
		driver.close();

	}
	

	public void imageConverterSvgToJpeg() throws AWTException {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		 Goto page1 = new Goto(driver);
	       page1.goTo();
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Scroll down the page
		js.executeScript("window.scrollBy(0, 300);"); // Scroll down by 1000 pixels
		driver.findElement(
				By.xpath("//a[@href='/image-convertor']//div[@class='card p-4 py-md-5 shadow-sm border border-1']"))
				.click();
		driver.findElement(By.xpath("//a[2]")).click();

		WebElement ele = driver.findElement(By.cssSelector("input[type='file']"));
		ele.sendKeys("//Users/yeshsharma//Documents//Female doctor to guide.svg");
		driver.findElement(By.cssSelector(".files-item__download")).click();
		driver.close();
	}

	public void imageConverterSvgToBase64() throws AWTException {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		 Goto page1 = new Goto(driver);
	       page1.goTo();
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Scroll down the page
		js.executeScript("window.scrollBy(0, 300);"); // Scroll down by 1000 pixels
		driver.findElement(
				By.xpath("//a[@href='/image-convertor']//div[@class='card p-4 py-md-5 shadow-sm border border-1']"))
				.click();
		driver.findElement(By.xpath("//a[3]")).click();
		WebElement ele = driver.findElement(By.cssSelector("input[type='file']"));
		ele.sendKeys("//Users/yeshsharma//Documents//Female doctor to guide.svg");
		driver.findElement(By.cssSelector(".files-item__download")).click();
		driver.close();
	}

	public void imageConverterSvgToWebp() throws AWTException {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		 Goto page1 = new Goto(driver);
	       page1.goTo();
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Scroll down the page
		js.executeScript("window.scrollBy(0, 300);"); // Scroll down by 1000 pixels
		driver.findElement(
				By.xpath("//a[@href='/image-convertor']//div[@class='card p-4 py-md-5 shadow-sm border border-1']"))
				.click();
		driver.findElement(By.xpath("//a[4]")).click();
		WebElement ele = driver.findElement(By.cssSelector("input[type='file']"));
		ele.sendKeys("//Users/yeshsharma//Documents//Female doctor to guide.svg");
		driver.findElement(By.cssSelector(".files-item__download")).click();
		driver.close();
	}

}