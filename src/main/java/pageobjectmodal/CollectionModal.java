package pageobjectmodal;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

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
    
    @FindBy(css="div[class='form-check p-0'] span[class='fw-semibold']")
    private WebElement collectionName;
    
    @FindBy(css="button[id='add-btn']")
    private WebElement saveCollectionButton;
    
    
    @FindBy(id="search-create-input")
    WebElement searchCollection;
    
    By searchCollectionBy = By.id("search-create-input");
    By selectCollectionBy = By.cssSelector("input[type='checkbox']");
    By collectionNameBy = By.cssSelector("div[class='form-check p-0'] span[class='fw-semibold']");
    
    // Constructor
    public CollectionModal(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }
    
    // Page Methods
    

    public void addToExistingCollection(String collectionName) throws InterruptedException {
//        wait.until(ExpectedConditions.elementToBeClickable(customSearchButton));
//        customSearchButton.click();
    	List<WebElement> checkboxes =wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
    			By.cssSelector("input[type='checkbox']")));
    	if(!checkboxes.isEmpty()) {
    		
    		for(int i=0; i<checkboxes.size();i++) {
    			WebElement allCheckBox = checkboxes.get(i);
    		if(allCheckBox.isSelected()) {
    			System.out.println("Unselect the selected check box");
    			allCheckBox.click();    		
    			}	
    	}
    }	
    	wait.until(ExpectedConditions.presenceOfElementLocated(searchCollectionBy)).sendKeys(collectionName);
    	searchCollection.sendKeys(Keys.ENTER);
    	Thread.sleep(2000);  
    	 WebElement checkbox = driver.findElement(By.cssSelector("input[type='checkbox']"));
    	 if (!checkbox.isSelected()) {
 	        checkbox.click();
	    }
    	 Thread.sleep(3000);        
//     	if (collectionName.equals("card")) {
//     	WebElement coll = checkboxes.stream().filter(p->p.
// 				findElement( By.xpath("//span[normalize-space(text())='" + collectionName + "' or starts-with(normalize-space(text()), '" + collectionName + " ')]"))
// 				.getText().split("\\(")[0].trim().equalsIgnoreCase(collectionName))
//     			.findFirst().orElse(null);
////     	coll.findElement(By.cssSelector("input[type='checkbox']")).click();
//     	 WebElement checkbox =  driver.findElement(
//     	            with(By.cssSelector("input[type='checkbox']")).toLeftOf(By.cssSelector("div[id='recent-"+collectionName+"'] span[class='fw-semibold'])"
//     	        )));
//     	   if (!checkbox.isSelected()) {
//     	        checkbox.click();
//     	    }
//   }    	 
//            cardCollection.click();
//             else {
//            // For other collection names, use dynamic xpath
//            WebElement collection = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//div[normalize-space()='" + collectionName + "']")));
//            collection.click();
//        }
        
//        removeFromDefaultCollection();
    	 
        saveToCollection();
        closeModal();
        
        Thread.sleep(4000);
    }
    
    public void listOfCollections() {
    	
    }
    
    public void createNewCollectionAndAdd(String collectionName) throws InterruptedException {
        System.out.println("Entered Creat new collection block");
    	// First, uncheck all already selected checkboxes
        List<WebElement> checkboxes = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("input[type='checkbox']")));
        System.out.println(">>-There are-<<" + checkboxes.size() + ">>-Collections present-<<");

        if (!checkboxes.isEmpty()) {
            for (WebElement allCheckBox : checkboxes) {
                if (allCheckBox.isSelected()) {
                    allCheckBox.click(); // unselect it
                }
            }
        }

        // Now check if the collection already exists
        List<WebElement> coll = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(collectionNameBy));
        boolean found = false;

        for (WebElement element : coll) {
            String text = element.getText().split("\\(")[0].trim();
            System.out.println("Checking collection: " + text);

            if (collectionName.equalsIgnoreCase(text)) {
                found = true;
                System.out.println("--<< Collection already exists >>--");
                
                // Click the existing collection checkbox
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                break;
            }
        }

        // If not found, create a new collection
        if (!found) {
            WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(searchCollectionBy));
            searchBox.clear();
            searchBox.sendKeys(collectionName);
            searchBox.sendKeys(Keys.ENTER);
            
            Thread.sleep(700);
            saveToCollection();
            System.out.println("--<< We created new collection and saved icon in it >>--");
        }

        closeModal();
        Thread.sleep(5000);
    }

    
//    private void removeFromDefaultCollection() {
//        wait.until(ExpectedConditions.elementToBeClickable(defaultCollectionCheckbox));
//        defaultCollectionCheckbox.click();
//    }
    
    private void saveToCollection() {
         wait.until(ExpectedConditions.elementToBeClickable(saveCollectionButton));
    saveCollectionButton.click();
    }
    
    private void closeModal() {
        // Click outside the modal to close it
        actions.moveByOffset(20, 20).click().build().perform();
    }
}