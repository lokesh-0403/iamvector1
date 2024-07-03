package testingoffigmaplugin;

import java.awt.AWTException;
import java.time.Duration;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import imageconverter.ChromeOptionConfig;

@Test
public class Figmaplugintesting {
	public void testingOfFigmaPlugin() throws AWTException, InterruptedException {

		WebDriver driver = new ChromeDriver(ChromeOptionConfig.getChromeOptions());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("https://www.figma.com/login?is_not_gen_0=true");
		driver.findElement(By.id("email")).sendKeys("yesh@zasyasolutions.com");
		driver.findElement(By.id("current-password")).sendKeys("Yesh255198@");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Actions a = new Actions(driver);
		WebElement element = driver.findElement(By.cssSelector(
				"img[class='cx_hFull--460YR cx_wFull--sGUhp generic_tile_thumbnail--thumbnailContain--T8Gdv cx_p10--FxKqI']"));
		a.doubleClick(element).perform();
		Thread.sleep(4000);
		driver.findElement(By.id("toolbar-action-component-insert")).click();
		driver.findElement(By.cssSelector("div[data-label='Plugins']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='resource_tiles--pluginTileName--9Isog text--fontPos12--YsUAh text--_fontBase--QdLsd ellipsis--ellipsis--Tjyfa']")).click();
			Thread.sleep(3000);	

		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='plugin-iframe-in-modal']")));
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='Network Plugin Iframe']")));
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='Inner Plugin Iframe']")));
		Thread.sleep(5000);
		driver.findElement(By.id("search")).sendKeys("doctor");
		driver.findElement(By.cssSelector("button[onclick='searchIcons()']")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("div[class='result-svg']")).click();
		 driver.close();
      
	


	

		
		
}
}