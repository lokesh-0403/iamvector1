package imageconverter;

import java.awt.AWTException;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobjectmodal.ImageConverterPage;


public class testingofimageconverter {

	private WebDriver driver;
	private ImageConverterPage imageConverterPage;
	private Goto goto1;
	private static final String FILE_PATH = "C:\\Users\\admin\\Downloads\\Sendarrow right arrow arrows next go send.svg";

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();

		// Initialize page objects
		imageConverterPage = new ImageConverterPage(driver);
		goto1 = new Goto(driver);

		// Navigate to the application
		goto1.goTo();
	}

	@Test
	public void testImageConverterSvgToPng() throws AWTException, InterruptedException {
		imageConverterPage.convertSvgToPng(FILE_PATH, driver);
	}

	@Test
	public void testImageConverterSvgToJpeg() throws AWTException, InterruptedException {
		imageConverterPage.convertSvgToJpeg(FILE_PATH, driver);
	}

	@Test
	public void testImageConverterSvgToBase64() throws AWTException, InterruptedException {
		imageConverterPage.convertSvgToBase64(FILE_PATH, driver);
	}

	@Test
	public void testImageConverterSvgToWebp() throws AWTException, InterruptedException {
		imageConverterPage.convertSvgToWebp(FILE_PATH, driver);
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}