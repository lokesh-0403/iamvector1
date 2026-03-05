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
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
public class ImageCompressorPage {

    private WebDriverWait wait;
    private Actions actions;
    private String firstWord = null;
    
    WebDriver driver;
    private Path downloadDir;

    public ImageCompressorPage(WebDriver driver, Path downloadDir) {
        this.driver = driver;
        this.downloadDir = downloadDir;
    
        if (downloadDir == null) {
            throw new RuntimeException("DownloadDir is NULL. Pass it from BaseTest.");
        }
     
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    
    @FindBy(xpath = "//h5[normalize-space()='Image Compressor']")
    private WebElement imageCompressorCard;
    
    @FindBy(css = "input[data-test-id='compressor_upload_file_input']")
    private WebElement uploadInput;
    
    @FindBy(css = "a[class='files-item__download']")
    private WebElement downloadLink;
    
    By downloadButtonBy =By.cssSelector(".files-item__download");
    
  
    
//    public ImageCompressorPage(WebDriver driver) {
//    	
//    	this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        this.actions = new Actions(driver);
//        PageFactory.initElements(driver, this);
//    }
    
    public void navigateToImageCompressor() {
        WebElement compressorElement = wait.until(ExpectedConditions.elementToBeClickable(imageCompressorCard));
        actions.moveToElement(compressorElement).perform();
        compressorElement.click();
    }
    
    public void uploadFile(String filePath, WebDriver driver) throws Exception {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    try {
	        WebElement fileInput = driver.findElement(By.cssSelector("input[data-test-id='compressor_upload_file_input']"));
//	        fileInput.sendKeys(filePath);

	        js.executeScript("arguments[0].removeAttribute('hidden'); arguments[0].style.display='block';", fileInput);

	        fileInput.sendKeys(filePath);
	        System.out.println("✔ File sent to input");
	        Thread.sleep(2000);
	        // ⏳ WAIT UNTIL FILE NAME APPEARS IN LIST
	        WebElement fileItem = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//div[@class='files-item dz-processing dz-success dz-complete']")
	        		));
	        js.executeScript("window.scrollBy(0, 350);");
	        System.out.println("✔ Upload processing started");

	        // Wait till it's marked as uploaded or downloadable
	        wait.until(ExpectedConditions.or(
	                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".dz-complete")),
	                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".files-item__download"))
	        ));

	        System.out.println("✔ Upload completed and file entry visible");

	        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
	        System.out.println("Name of the file Uploaded: " + fileName);
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
		Thread.sleep(5000);
		  Assert.assertTrue(
			        waitForDownload(20),
			        "File was not downloaded successfully"
			    );
	}
    
    private boolean waitForDownload(int timeoutSeconds) {

        File folder = downloadDir.toFile();
        int waited = 0;

        while (waited < timeoutSeconds) {
            File[] files = folder.listFiles();

            if (files != null && files.length > 0) {
                for (File file : files) {
                    if (!file.getName().endsWith(".crdownload")) {
                        System.out.println("Downloaded: " + file.getName());
                        return file.length() > 0;
                    }
                }
            }

            try {
                Thread.sleep(1000);
                waited++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    public void compressImage(String filePath, WebDriver driver) throws Exception {
        navigateToImageCompressor();
        uploadFile(filePath,driver);
        clickDownload();
    }
}

