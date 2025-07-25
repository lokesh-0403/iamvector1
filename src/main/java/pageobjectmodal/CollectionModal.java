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

public class CollectionModal extends BasePage {
    
    private WebDriverWait wait;
    private Actions actions;
    
    // Page Elements
    @FindBy(id = "custom-search")
    private WebElement customSearchButton;
    
    @FindBy(xpath = "//div[normalize-space()='card']")
    private WebElement cardCollection;
    
    @FindBy(id = "flexCheckDefault")
    private WebElement defaultCollectionCheckbox;
    
    @FindBy(id = "add-btn")
    private WebElement addButton;
    
    @FindBy(id = "new-collection-btn")
    private WebElement newCollectionButton;
    
    @FindBy(id = "new-collection-name-field")
    private WebElement newCollectionNameField;
    
    @FindBy(id = "save-collection-btn")
    private WebElement saveCollectionButton;
    
    // Constructor
    public CollectionModal(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }
    
    // Page Methods
    public void addToExistingCollection(String collectionName) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(customSearchButton));
        customSearchButton.click();
        
        if (collectionName.equals("card")) {
            wait.until(ExpectedConditions.elementToBeClickable(cardCollection));
            cardCollection.click();
        } else {
            // For other collection names, use dynamic xpath
            WebElement collection = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[normalize-space()='" + collectionName + "']")));
            collection.click();
        }
        
        removeFromDefaultCollection();
        saveToCollection();
        closeModal();
    }
    
    public void createNewCollectionAndAdd(String collectionName) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(newCollectionButton));
        newCollectionButton.click();
        
        wait.until(ExpectedConditions.visibilityOf(newCollectionNameField));
        newCollectionNameField.sendKeys(collectionName);
        
        wait.until(ExpectedConditions.elementToBeClickable(saveCollectionButton));
        saveCollectionButton.click();
        
        removeFromDefaultCollection();
        saveToCollection();
        closeModal();
    }
    
    private void removeFromDefaultCollection() {
        wait.until(ExpectedConditions.elementToBeClickable(defaultCollectionCheckbox));
        defaultCollectionCheckbox.click();
    }
    
    private void saveToCollection() {
        wait.until(ExpectedConditions.elementToBeClickable(addButton));
        addButton.click();
    }
    
    private void closeModal() {
        // Click outside the modal to close it
        actions.moveByOffset(20, 20).click().build().perform();
    }
}