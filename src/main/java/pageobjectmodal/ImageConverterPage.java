package pageobjectmodal;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ImageConverterPage {

	private WebDriverWait wait;
	private Actions actions;
	 
	public String firstWord = null;
	
	// Page Elements using @FindBy annotations
	@FindBy(xpath = "//h5[normalize-space()='Image Converter']")
	private WebElement imageConverterCard;

	@FindBy(css = "input[type='file']")
	private WebElement fileUploadInput;

	@FindBy(css = "a[class='files-item__download']")
	private WebElement downloadButton;

	By downloadButtonBy =By.cssSelector("a[class='files-item__download']");
	
	@FindBy(xpath = "//div[@class='links']//a[1]")
	private WebElement pngTab;
	
	@FindBy(css = "a[class$='links__link hover-link   border-start links__link-site12  ']")
	private WebElement jpegTab;

	@FindBy(xpath = "//div[@class='links']//a[3]")
	private WebElement base64Tab;

	@FindBy(xpath = "//div[@class='links']//a[4]")
	private WebElement webpTab;

	//public String filePath = "C:\\Users\\admin\\Downloads\\Sendarrow right arrow arrows next go send.svg";
	// Constructor
	public ImageConverterPage(WebDriver driver) {

		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		this.actions = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	// Page Actions/Methods
	public void clickImageConverterCard() throws InterruptedException {
		WebElement card = wait.until(ExpectedConditions.elementToBeClickable(imageConverterCard));
		actions.moveToElement(card).perform();
		card.click();
		Thread.sleep(1000);
	}

	public void uploadFile(String filePath, WebDriver driver) throws Exception {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    try {
	        WebElement fileInput = driver.findElement(By.cssSelector("input[data-test-id='converter_file_upload_input']"));
//	        fileInput.sendKeys(filePath);

	        js.executeScript("arguments[0].removeAttribute('hidden'); arguments[0].style.display='block';", fileInput);

	        fileInput.sendKeys(filePath);
	        System.out.println("✔ File sent to input");
	        Thread.sleep(2000);
	        // ⏳ WAIT UNTIL FILE NAME APPEARS IN LIST
	        WebElement fileItemName = wait.until(ExpectedConditions.presenceOfElementLocated(
	                By.xpath("//div[@class='files-item__name']")
	        		));
//	        js.executeScript("arguments[0].scrollIntoView(true);", fileItemName);
	        System.out.println("✔ Upload processing started");

	        // Wait till it's marked as uploaded or downloadable
	        wait.until(ExpectedConditions.or(
	                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".dz-complete")),
	                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".files-item__download"))
	        ));

	        System.out.println("✔ Upload completed and file entry visible");

	        String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
	        this.firstWord = fileName.split(" ")[0];

	    } catch (Exception e) {
	        System.err.println("❌ File Upload Failed: " + e.getMessage());
	        throw e;
	    }
	}


	
	public void clickDownload() throws InterruptedException {
		System.out.println("Uploaded file firstWord Before showing download: " + firstWord);
		List<WebElement> downloads = wait
			    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
			        By.xpath("//div[contains(@class,'dz-complete')]")));

		System.out.println("Uploaded file firstWord During showing download: " + firstWord);
	


		WebElement dwn = downloads.stream()
				.filter(p -> p.findElement(By.cssSelector("div[class='files-item__name']")).getText().split(" ")[0].trim()
						.equals(firstWord))
				.map(p -> p.findElement(By.cssSelector("div[class='files-item__name']")).findElement(By.xpath("..")))
				.findFirst().orElse(null);
		System.out.println("Uploaded file firstWord After showing download: " + firstWord);
		Thread.sleep(1000);
		if (dwn == null) {
			throw new RuntimeException("No matching uploaded file found for: " + firstWord);
		}
//		WebElement dwnArea = dwn.findElement(By.xpath(".."));
		WebElement downloadBtn =dwn.findElement(downloadButtonBy);
//wait.until(ExpectedConditions.elementToBeClickable(downloadButton));
		downloads.forEach(d -> System.out.println("Found: " + d.getText()));
		downloadBtn.click();
		
	}

	public void clickpngTab(WebDriver driver) throws InterruptedException {
		Thread.sleep(1000);
		WebElement jpeg = wait.until(ExpectedConditions.elementToBeClickable(pngTab));
		jpeg.click();
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver ;
		js.executeScript("window.scrollBy(0,350)");
		Thread.sleep(2000);
	}
	
	public void clickJpegTab(WebDriver driver) throws InterruptedException {
		WebElement jpeg = wait.until(ExpectedConditions.elementToBeClickable(jpegTab));
		jpeg.click();
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver ;
		js.executeScript("window.scrollBy(0,350)");
		Thread.sleep(2000);
	}

	public void clickBase64Tab(WebDriver driver) throws InterruptedException {
		WebElement base64 = wait.until(ExpectedConditions.elementToBeClickable(base64Tab));
		base64.click();
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver ;
		js.executeScript("window.scrollBy(0,350)");
		Thread.sleep(2000);
	}

	public void clickWebpTab(WebDriver driver) throws InterruptedException {
		WebElement webp = wait.until(ExpectedConditions.elementToBeClickable(webpTab));
		webp.click();
		JavascriptExecutor js = (JavascriptExecutor) driver ;
		js.executeScript("window.scrollBy(0,350)");
		Thread.sleep(2000);
	}

	// Combined methods for complete workflows
	public void convertSvgToPng(String filePath, WebDriver driver) throws Exception {
		
		clickImageConverterCard();
		clickpngTab(driver);
		
		uploadFile(filePath,driver);
		clickDownload();
	}

	public void convertSvgToJpeg(String filePath, WebDriver driver) throws Exception {
		clickImageConverterCard();
		clickJpegTab(driver);
		
		uploadFile(filePath,driver);
		clickDownload();
	}

	public void convertSvgToBase64(String filePath, WebDriver driver) throws Exception {
		clickImageConverterCard();
		clickBase64Tab(driver);
		
		uploadFile(filePath,driver);
		clickDownload();
	}

	public void convertSvgToWebp(String filePath, WebDriver driver) throws Exception {
		clickImageConverterCard();
		clickWebpTab(driver);
		
		uploadFile(filePath,driver);
		clickDownload();
	}
}