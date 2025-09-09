package pageobjectmodal;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
public class ImageCompressorPage {

    private WebDriverWait wait;
    private Actions actions;
    private String firstWord = null;
    
    @FindBy(xpath = "//h5[normalize-space()='Image Compressor']")
    private WebElement imageCompressorCard;
    
    @FindBy(css = "input[data-test-id='compressor_upload_file_input']")
    private WebElement uploadInput;
    
    @FindBy(css = "a[class='files-item__download']")
    private WebElement downloadLink;
    
    By downloadButtonBy =By.cssSelector(".files-item__download");
    
    
    
    public ImageCompressorPage(WebDriver driver) {
   
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }
    
    public void navigateToImageCompressor() {
        WebElement compressorElement = wait.until(ExpectedConditions.elementToBeClickable(imageCompressorCard));
        actions.moveToElement(compressorElement).perform();
        compressorElement.click();
    }
    
    public void uploadFile(String filePath) {
        WebElement uploadElement = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("input[data-test-id='compressor_upload_file_input']")));
        uploadElement.sendKeys(filePath);
    	String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
		this.firstWord = fileName.split(" ")[0];
    }
    
    public void downloadCompressedFile(WebDriver driver) throws InterruptedException {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("window.scrollBy(0,350)");
    	Thread.sleep(2000);
//    WebElement qualitypanel = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[class='accordion-button']")));
//    qualitypanel.click();
//    WebElement quality = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("//label[normalize-space()='Quality']")));
//    quality.click(); 
//    
//    	WebElement slider = driver.findElement(By.cssSelector("input[type='range']"));
//    	Actions action = new Actions(driver);
//    	action.clickAndHold(slider).moveByOffset(-30, 0).release().perform();
    	
    	Thread.sleep(3000);
    	List<WebElement> downloads = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
			        By.xpath("//div[contains(@class,'dz-complete')]")));

		WebElement dwn = downloads.stream()
				.filter(p -> p.findElement(By.cssSelector("div[class='files-item__name']")).getText().split(" ")[0].trim()
						.equals(firstWord))
				.map(p -> p.findElement(By.cssSelector("div[class='files-item__name']")).findElement(By.xpath("..")))
				.findFirst().orElse(null);
		
		WebElement downloadBtn =dwn.findElement(downloadButtonBy);

		downloadBtn.click();
    }
    
    public void compressImage(String filePath, WebDriver driver) throws InterruptedException {
        navigateToImageCompressor();
        uploadFile(filePath);
        downloadCompressedFile(driver);
    }
}

