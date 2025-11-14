package pageobjectmodal;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.github.javafaker.Faker;

public class MyCollectionsPage extends BasePage {
    
    private WebDriverWait wait;
    private Actions actions;
    private JavascriptExecutor js;
    // Page Elements
    @FindBy(css = "a[href='https://dev.iamvector.com/collections']")
    private WebElement myCollection;
    
    @FindBy(xpath = "//button[normalize-space()='Download']")
    private WebElement downloadButton;
    
    @FindBy(css= "a[href='/dashboard']")
    private WebElement dashboardButton;
    
    // Constructor
    public MyCollectionsPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }
    
    // Page Methods
    public void navigateToMyCollections() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(dashboardButton));
        dashboardButton.click();
        Thread.sleep(3000); 
        wait.until(ExpectedConditions.elementToBeClickable(myCollection));
        myCollection.click();
        Thread.sleep(6000);
        // Wait for collections to load
    }
    
    public void openCollection(String collectionName) throws InterruptedException {
    	
    	 js.executeScript("window.scrollBy(0,300)");
    	Thread.sleep(1000);
    	
        WebElement collection = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("(//a[normalize-space()='"+collectionName+"'])[1]")));
        actions.moveToElement(collection).build().perform();
        collection.click();
        
        Thread.sleep(3000);
        System.out.println("We are in side the collection"+collection);
    }
    
    public void downloadIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(downloadButton));
        downloadButton.click();
    }
    
    public void deleteCollection(String collectionName) throws InterruptedException {
        Thread.sleep(3000);
        driver.navigate().back();
        Thread.sleep(3000);
        
        try {
            WebElement collectionElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'card-body d-flex align-items-center gap-3')] //a[contains(.,'"+collectionName.trim()+"')] /parent::div /parent::div")));
            
            actions.moveToElement(collectionElement).perform();
            
//            WebElement parentCard = collectionElement.findElement(
//                By.xpath("./ancestor::div[contains(@class,'collection-card')]"));
            WebElement dropdown = collectionElement.findElement(
                By.xpath(".//span[contains(@class,'dropdown-trigger')]"));
            
            dropdown.click();
            Thread.sleep(800);
            WebElement deleteOption = driver.findElement(By.xpath(
                "//ul[contains(@class,'dropdown-menu collection-options show')]//span[contains(@class,'danger')][normalize-space()='Delete Collection']"));
            deleteOption.click();
          
      
           Thread.sleep(800);
            String alterText= driver.switchTo().alert().getText();
            Assert.assertEquals(alterText.trim(), "Are you sure you want to delete this collection?");
    		driver.switchTo().alert().accept();
            
          Thread.sleep(3000);         
          
          driver.navigate().refresh();
          
        } catch (Exception e) {
            System.out.println("Dropdown not found or not clickable: " + e.getMessage());
            throw e;
        }
    }
    
    public void editCollectionName(String collectionName) throws InterruptedException {
        Thread.sleep(3000);
        driver.navigate().back();
        Thread.sleep(3000);
        
        try {
            WebElement collectionElement = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'card-body d-flex align-items-center gap-3')] //a[contains(.,'"+collectionName.trim()+"')] /parent::div /parent::div")));
            
            actions.moveToElement(collectionElement).perform();
            
//            WebElement parentCard = collectionElement.findElement(
//                By.xpath("./ancestor::div[contains(@class,'collection-card')]"));
            WebElement dropdown = collectionElement.findElement(
                By.xpath(".//span[contains(@class,'dropdown-trigger')]"));
            
            dropdown.click();
            
            Thread.sleep(800);
            
            WebElement editOption = driver.findElement(By.xpath(
                "//ul[@class='dropdown-menu collection-options show']//span[contains(text(),'Edit name')]"));
            editOption.click();
            
            Thread.sleep(800);
            
        } catch (Exception e) {
            System.out.println("Dropdown not found or not clickable: " + e.getMessage());
            throw e;
        }
    }
    
    public void updateCollectionName() throws InterruptedException {
        WebElement editField = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//input[@id='edit-name-input']")));
        
        Faker faker = new Faker();
        String editedName = "Updated-AutoCollection_" + faker.animal().name() + "_" + System.currentTimeMillis();
        
        editField.clear();
        editField.sendKeys(editedName);
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("button[id='save-name-btn']"));
       
        Thread.sleep(3000);
        
    }
    
}