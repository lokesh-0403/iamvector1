package testingofimagecompressor;

import java.awt.AWTException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import imageconverter.Goto;

@Test
public class Imagecompressortesting {
	
	

		public void svgimageCompressor() throws AWTException, InterruptedException {

			WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

			 Goto page1 = new Goto(driver);
		       page1.goTo();
			driver.manage().window().maximize();
			JavascriptExecutor js = (JavascriptExecutor) driver;

			// Scroll down the page
			js.executeScript("window.scrollBy(0, 300);"); // Scroll down by 1000 pixels
			driver.findElement(By.xpath("//a[@href='/image-compressor']//div[@class='card p-4 py-md-5 shadow-sm border border-1']")).click();
			driver.findElement(By.cssSelector("input[data-test-id='compressor_upload_file_input']")).sendKeys("//Users/yeshsharma//Documents//Female doctor to guide.svg");
	        driver.findElement(By.cssSelector("a[class='files-item__download']")).click();
	        Thread.sleep(3000);
	        driver.close();

}

	
	

		public void pngimageCompressor() throws AWTException, InterruptedException {

			WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

			 Goto page1 = new Goto(driver);
		       page1.goTo();
			driver.manage().window().maximize();
			JavascriptExecutor js = (JavascriptExecutor) driver;

			// Scroll down the page
			js.executeScript("window.scrollBy(0, 300);"); // Scroll down by 1000 pixels
			driver.findElement(By.xpath("//a[@href='/image-compressor']//div[@class='card p-4 py-md-5 shadow-sm border border-1']")).click();
			driver.findElement(By.cssSelector("input[data-test-id='compressor_upload_file_input']")).sendKeys("//Users/yeshsharma//Documents//Car travel.png");
			driver.findElement(By.cssSelector("a[class='files-item__download']")).click();
	        Thread.sleep(3000);
            driver.close();
}
		
	
	

		public void jpgimageConverter() throws AWTException, InterruptedException {

			WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

			 Goto page1 = new Goto(driver);
		       page1.goTo();
			driver.manage().window().maximize();
			JavascriptExecutor js = (JavascriptExecutor) driver;

			// Scroll down the page
			js.executeScript("window.scrollBy(0, 300);"); // Scroll down by 1000 pixels
			// Scroll down the page
						js.executeScript("window.scrollBy(0, 300);"); // Scroll down by 1000 pixels
						driver.findElement(By.xpath("//a[@href='/image-compressor']//div[@class='card p-4 py-md-5 shadow-sm border border-1']")).click();
						driver.findElement(By.cssSelector("input[data-test-id='compressor_upload_file_input']")).sendKeys("//Users/yeshsharma//Documents//input-onlinejpgtools.jpg");
						driver.findElement(By.cssSelector("a[class='files-item__download']")).click();
				        Thread.sleep(3000);
			            driver.close();
	
}
	}
