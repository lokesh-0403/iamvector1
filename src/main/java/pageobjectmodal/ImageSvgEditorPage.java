package pageobjectmodal;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.ResourceHelper;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.regex.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageSvgEditorPage {

    private WebDriverWait wait;
    private Actions actions;
    private String firstWord = null;
    
    @FindBy(xpath = "//h5[normalize-space()='SVG Editor']")
    private WebElement svgEditorCard;
    
    @FindBy(css = "input[data-test-id='editor_upload_file_input']")
    private WebElement uploadInput;
    
    @FindBy(css = "a[class='files-item__download']")
    private WebElement downloadLink;
    
    By downloadButtonBy =By.cssSelector(".svg-download-button");
    
    WebDriver driver;
    private Path downloadDir;

    public ImageSvgEditorPage(WebDriver driver, Path downloadDir) {
        this.driver = driver;
        this.downloadDir = downloadDir;
    
        if (downloadDir == null) {
            throw new RuntimeException("DownloadDir is NULL. Pass it from BaseTest.");
        }
     
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }
    
  
    
    public void navigateToSVGEditor() throws InterruptedException {
        WebElement editorElement = wait.until(ExpectedConditions.elementToBeClickable(svgEditorCard));
        actions.moveToElement(editorElement).perform();
        editorElement.click();
        Thread.sleep(700);
    }
    
    public WebElement expandShadowRoot(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (WebElement) js.executeScript("return arguments[0].shadowRoot", element);
    }
    
    public void uploadFile(String filePath, WebDriver driver) {
        try {
            WebElement uploadInput = driver.findElement(By.id("fileInput"));
            uploadInput.sendKeys(filePath);

            System.out.println("[DEBUG] File upload triggered...");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

          

            // Optionally wait until download button becomes clickable (strong validation)
          
            System.out.println("[DEBUG] Upload completed and SVG rendered.");

        } catch (Exception e) {
            System.err.println("[ERROR] Upload failed: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    
    public void downloadeditedFile(WebDriver driver) throws InterruptedException {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("window.scrollBy(0,350)");
    	Thread.sleep(1000);
  		
    WebElement dropDown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-bs-toggle='dropdown']")));
    dropDown.click();
    WebElement selectPixel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[normalize-space()='256Px']")));
    selectPixel.click();
    
    WebElement downloadBtn = wait.until(ExpectedConditions.elementToBeClickable(downloadButtonBy));

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
	  
    public void imagecheck(WebDriver driver) throws IOException {

    	// Read original SVG from file
    //	String originalSvg = new String(Files.readAllBytes(Paths.get("C:\\Users\\admin\\Downloads\\qazasya-iamvector1-9c52d69d233d\\src\\main\\resources\\files\\Female doctor with cross mark.svg")), StandardCharsets.UTF_8);
    	String originalSvg = ResourceHelper.getResourceFilePath("files/Female doctor with cross mark.svg");
    	
    //	String originalSvg = new String(Files.readAllBytes(Paths.get("C:\\Users\\admin\\Downloads\\Female doctor with cross mark.svg")), StandardCharsets.UTF_8);
    	// Get rendered SVG from the preview pane
    	WebElement renderedSvgElement = driver.findElement(By.cssSelector("svg"));
    	String renderedSvg = renderedSvgElement.getAttribute("outerHTML");

    	// Normalize (remove spaces, newlines)
    	originalSvg = originalSvg.replaceAll("\\s+", "");
    	renderedSvg = renderedSvg.replaceAll("\\s+", "");

    	// Compare
    	if (originalSvg.equals(renderedSvg)) {
    	    System.out.println(originalSvg);
    	} else {
    		 System.out.println("Uploaded SVG"+originalSvg);
    	    System.out.println("Render SVG"+renderedSvg);
    	}

    }

	public void editSVGImage(String pngFilePath, WebDriver driver) throws InterruptedException, Exception {
		// TODO Auto-generated method stub
		navigateToSVGEditor();
        uploadFile(pngFilePath, driver);
        imagecheck(driver);
        downloadeditedFile(driver);
	}
}

