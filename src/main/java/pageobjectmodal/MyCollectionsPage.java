package pageobjectmodal;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

public class MyCollectionsPage extends BasePage {
    
    private WebDriverWait wait;
    private Actions actions;
    
    // Page Elements
    @FindBy(xpath = "//span[@class='nav-link fw-semibold']")
    private WebElement myCollectionLink;
    
    @FindBy(xpath = "//button[normalize-space()='Download']")
    private WebElement downloadButton;
    
    // Constructor
    public MyCollectionsPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }
    
    // Page Methods
    public void navigateToMyCollections() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(myCollectionLink));
        myCollectionLink.click();
        Thread.sleep(6000); // Wait for collections to load
    }
    
    public void openCollection(String collectionName) {
        WebElement collection = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[normalize-space()='" + collectionName + "']")));
        actions.moveToElement(collection).perform();
        collection.click();
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
            WebElement collectionElement = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'collection-card')]//a[normalize-space()='" + collectionName.trim() + "']")));
            
            actions.moveToElement(collectionElement).perform();
            
            WebElement parentCard = collectionElement.findElement(
                By.xpath("./ancestor::div[contains(@class,'collection-card')]"));
            WebElement dropdown = parentCard.findElement(
                By.xpath(".//span[contains(@class,'dropdown-trigger')]"));
            
            dropdown.click();
            
            WebElement deleteOption = driver.findElement(By.xpath(
                "//ul[contains(@class,'dropdown-menu collection-options show')]//span[contains(@class,'danger')][normalize-space()='Delete Collection']"));
            deleteOption.click();
            
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
                By.xpath("//div[contains(@class,'collection-card')]//a[normalize-space()='" + collectionName.trim() + "']")));
            
            actions.moveToElement(collectionElement).perform();
            
            WebElement parentCard = collectionElement.findElement(
                By.xpath("./ancestor::div[contains(@class,'collection-card')]"));
            WebElement dropdown = parentCard.findElement(
                By.xpath(".//span[contains(@class,'dropdown-trigger')]"));
            
            dropdown.click();
            
            WebElement editOption = driver.findElement(By.xpath(
                "//ul[@class='dropdown-menu collection-options show']//span[contains(text(),'Edit name')]"));
            editOption.click();
            
        } catch (Exception e) {
            System.out.println("Dropdown not found or not clickable: " + e.getMessage());
            throw e;
        }
    }
    
    public void updateCollectionName() {
        WebElement editField = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//input[@id='new-collection-name']")));
        
        Faker faker = new Faker();
        String editedName = "AutoCollection_" + faker.animal().name() + "_" + System.currentTimeMillis();
        
        editField.clear();
        editField.sendKeys(editedName);
        editField.sendKeys(Keys.ENTER);
    }
}