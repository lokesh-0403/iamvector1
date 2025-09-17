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


import java.time.Duration;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.regex.*;
import java.io.File;
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
    
    
    
    public ImageSvgEditorPage(WebDriver driver) {
   
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
    
    public void uploadFile(String filePath, WebDriver  driver) throws Exception {
    	

    	 try {
    	        // Find the hidden file input
    	        WebElement uploadInput = driver.findElement(
    	            By.cssSelector("input[data-test-id='editor_upload_file_input'][type='file']")
    	        );

    	        // Send the file path directly
    	        uploadInput.sendKeys(filePath);
    	        Thread.sleep(5000);
    	        System.out.println("[DEBUG] File uploaded: " + filePath);
    	    } catch (Exception e) {
    	        System.err.println("[ERROR] Upload failed: " + e.getMessage());
    	        e.printStackTrace();
    	    }
    	
    }
    
    public void downloadeditedFile(WebDriver driver) throws InterruptedException {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("window.scrollBy(0,350)");
    	Thread.sleep(1000);

    	 WebElement svgPhoto =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("svg")));
    
    	if(svgPhoto.isDisplayed()){
    		
    WebElement dropDown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-bs-toggle='dropdown']")));
    dropDown.click();
    WebElement selectPixel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[normalize-space()='256Px']")));
    selectPixel.click();
    
    WebElement downloadBtn = wait.until(ExpectedConditions.elementToBeClickable(downloadButtonBy));

		downloadBtn.click();
		
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "⚠ This is an alert!");
    		System.out.println("! ALERT: Input the valid svg file !");
    	}
	}
    
    public void imagecheck(WebDriver driver) {
    	WebElement codeMirrorElement = driver.findElement(By.cssSelector(".CodeMirror"));
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	String codeText = (String) js.executeScript(
    	    "return arguments[0].CodeMirror.getValue();", codeMirrorElement);
    	Pattern pattern = Pattern.compile("d=\"([^\"]+)\"");
    	Matcher matcher = pattern.matcher(codeText);
    	 String dValueText = null;
    	while (matcher.find()) {
    		dValueText = matcher.group(1);
    	}
    	
    	WebElement emoji =  driver.findElement(By.cssSelector("path"));
    	String attributeText = emoji.getAttribute("d");
    	
    	Assert.assertEquals(dValueText,attributeText);
    }
  

	public void editSVGImage(String pngFilePath, WebDriver driver) throws InterruptedException, Exception {
		// TODO Auto-generated method stub
		navigateToSVGEditor();
        uploadFile(pngFilePath, driver);
        imagecheck(driver);
        downloadeditedFile(driver);
	}
}

