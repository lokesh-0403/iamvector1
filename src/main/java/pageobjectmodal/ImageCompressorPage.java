package pageobjectmodal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class ImageCompressorPage {

    private WebDriverWait wait;
    private Actions actions;
    
    @FindBy(xpath = "//h5[normalize-space()='Image Compressor']")
    private WebElement imageCompressorCard;
    
    @FindBy(css = "input[data-test-id='compressor_upload_file_input']")
    private WebElement uploadInput;
    
    @FindBy(css = "a[class='files-item__download']")
    private WebElement downloadLink;
    
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
    }
    
    public void downloadCompressedFile() {
        WebElement downloadElement = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("a[class='files-item__download']")));
        downloadElement.click();
    }
    
    public void compressImage(String filePath) {
        navigateToImageCompressor();
        uploadFile(filePath);
        downloadCompressedFile();
    }
}

