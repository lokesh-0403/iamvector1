package pageobjectmodal;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

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

public class HomePageCollection extends BasePage {

	private WebDriverWait wait;
	private Actions actions;
	private JavascriptExecutor js;

	// Page Elements
	@FindBy(xpath = "//div[@id='homeSearchInput']//input[@name='search']")
	private WebElement searchBox;
	
	
	@FindBy(xpath = "//button[@id='remove-btn']")
	private WebElement RemoveIcon;
	
	
	@FindBy(xpath = "//button[@id='add-btn']")
	private WebElement saveButton;
	

	@FindBy(css = "input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")
	private WebElement searchEnterButton;


	@FindBy(xpath = "//div[@data-test-id='icon_element']")
	private List<WebElement> iconElements;

	@FindBy(xpath = "//span[contains(text(),'Load')]")
	private List<WebElement> loadMoreButtons;

	By saveUnsavedIcon = By.xpath(".//div[@data-test-id='save_unsave_icon_element']");

	// Constructor
	public HomePageCollection(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.actions = new Actions(driver);
		this.js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
	}

	// Page Methods
	public void searchForContent(String searchKey) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(searchBox));
		searchBox.sendKeys(searchKey);

		try {
			wait.until(ExpectedConditions.elementToBeClickable(searchEnterButton));
			searchEnterButton.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Thread.sleep(5000); // Wait for search results
	}
	

	public WebElement selectRandomIcon() {
		wait.until(ExpectedConditions.visibilityOfAllElements(iconElements));

		if (iconElements.size() == 0) {
			throw new RuntimeException("No icons found!");
		}

		Random rand = new Random();

		return iconElements.get(rand.nextInt(iconElements.size()));
	}
	
	
	public WebElement selectUnsavedRandomIcon() throws InterruptedException {
	    wait.until(ExpectedConditions.visibilityOfAllElements(iconElements));

	    if (iconElements.size() == 0) {
	        throw new RuntimeException("No icons found!");
	    }

	    Random rand = new Random();
	    int maxAttempts = Math.min(iconElements.size(), 20); // Limit attempts to avoid infinite loops
	    int attempts = 0;
	    
	    while (attempts < maxAttempts) {
	        WebElement randomIcon = iconElements.get(rand.nextInt(iconElements.size()));
	        
	        System.out.println("Attempt " + (attempts + 1) + ": Checking if icon is saved...");
	        
	        // Scroll icon into view first
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", randomIcon);
	        Thread.sleep(500);
	        
	        // Hover to reveal buttons
	        actions.moveToElement(randomIcon).perform();
	        Thread.sleep(800); // Increased wait time for buttons to appear
	        
	        // Check if icon is already saved (has unsave button)
	        List<WebElement> unsaveButtons = randomIcon.findElements(By.id("svg_unsave"));
	        
	        if (unsaveButtons.size() > 0 && unsaveButtons.get(0).isDisplayed()) {
	            System.out.println("Icon is already saved. Selecting another icon...");
	            attempts++;
	            
	            // Move mouse away to hide buttons before next attempt
	            actions.moveByOffset(100, 100).perform();
	            Thread.sleep(300);
	            
	            continue; // Icon is saved, try another one
	        } else {
	            // Icon is not saved, this is what we want
	            System.out.println("Found an unsaved icon!");
	            return randomIcon;
	        }
	    }
	    
	    throw new RuntimeException("Could not find an unsaved icon after " + maxAttempts + " attempts. All checked icons appear to be saved.");
	}
	
	
	
	
	
	public String getIconUniqueId(WebElement icon) {
		return icon.getAttribute("data-slugid");
	}
	
	

//	public void clickSaveToCollectionButton(WebElement icon) throws InterruptedException {
//		WebElement saveToCollectionButton = null;
//
//		boolean buttonFound = false;
//		int maxscrollby = 5;
//		for (int i = 0; i < maxscrollby; i++) {
//			try {
//				saveToCollectionButton = icon.findElement(By.xpath(".//div[@class='rounded bg-white svg_save']"));
//				System.out.println("yash1jan"+saveToCollectionButton);
//
//				actions.moveToElement(icon).perform();
//				Thread.sleep(600);
//
//				if (saveToCollectionButton.isDisplayed()
//						&& saveToCollectionButton.getAttribute("id").equals("svg_save")) {
//					buttonFound = true;
//					break;
//				}
//
//			} catch (Exception e) {
//				System.out.println("Attempt " + (i + 1) + ": Button not found or not interactable.");
//			}
//	
//		System.out.println(">>I am in loop");
//		JavascriptExecutor js = (JavascriptExecutor) driver; 
//   	    js.executeScript("arguments[0].scrollIntoView(true);", saveToCollectionButton);
//   	    Thread.sleep(2000);
////		js.executeScript("window.scrollBy(0,300)");
//		
//
//		if (!loadMoreButtons.isEmpty()) {
//			try {
//				loadMoreButtons.get(0).click();
//				Thread.sleep(800);
//			} catch (Exception ignored) {
//			}
//		}
//}
//		System.out.println(">>I am out of loop");
//		
//		if (saveToCollectionButton == null) {
//			throw new RuntimeException("Save to Collection button could not be found after scrolling/loading.");
//		}
//		System.out.println("-->>I am at bottom");
//		System.out.println(">>Ifinding button" + saveToCollectionButton);
//		Thread.sleep(1000);
//		Actions actions = new Actions(driver);
//		actions.moveToElement(saveToCollectionButton).build().perform();
//		System.out.println("-->> Button click : " + saveToCollectionButton);
//		Thread.sleep(3000);
//		saveToCollectionButton.click();
//		System.out.println("-->> Exiting the saveCollectionButtonClick method ");
//
//	}
	
//	public void clickSaveToCollectionButton(WebElement icon) throws InterruptedException {
//	    WebElement saveToCollectionButton = null;
//	    boolean buttonFound = false;
//	    int maxScrollAttempts = 5;
//	    
//	    // Locators
//	    By saveButtonLocator = By.xpath(".//div[@class='rounded bg-white svg_save']");
//	    By unsaveButtonLocator = By.xpath(".//div[@data-test-id='save_unsave_icon_element']");
//	    
//	    // Try to find the button with scrolling
//	    for (int i = 0; i < maxScrollAttempts && !buttonFound; i++) {
//	        try {
//	            System.out.println("Attempt " + (i + 1) + ": Searching for Save/Unsave button...");
//	            
//	            // Hover over the icon to make buttons appear
//	            actions.moveToElement(icon).perform();
//	            Thread.sleep(600);
//	            
//	            // Check if icon is already saved (unsave button present)
//	            try {
//	                WebElement unsaveButton = icon.findElement(unsaveButtonLocator);
//	                
//	                if (unsaveButton != null && unsaveButton.isDisplayed()) {
//	                    System.out.println("Icon is already saved. Unsaving first...");
//	                    
//	                    // Scroll to unsave button
//	                    JavascriptExecutor js = (JavascriptExecutor) driver;
//	                    js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", unsaveButton);
//	                    Thread.sleep(500);
//	                    
//	                    // Click unsave button
//	                    try {
//	                        unsaveButton.click();
//	                        
//	                        wait.until(ExpectedConditions.visibilityOf(RemoveIcon));
//	                		RemoveIcon.click();
//	                		 actions.moveToElement(icon).perform();
//	                        
//	                     
//	                    } catch (Exception e) {
//	                        js.executeScript("arguments[0].click();", unsaveButton);
//	                    }
//	                    
//	                    System.out.println("Icon unsaved successfully!");
//	                    Thread.sleep(1000); // Wait for UI to update
//	                    
//	                    // Hover again to reveal save button
//	                    actions.moveToElement(icon).perform();
//	                    Thread.sleep(600);
//	                }
//	            } catch (NoSuchElementException e) {
//	                System.out.println("Icon is not saved yet. Proceeding to save...");
//	            }
//	            
//	            Thread.sleep(4000);
//	            
//	            // Now try to find the save button
//	            saveToCollectionButton = icon.findElement(saveButtonLocator);
//	            
//	            System.out.println("Save button found: " + saveToCollectionButton);
//	            
//	            // Check if button is displayed and has correct ID
//	            if (saveToCollectionButton != null && 
//	                saveToCollectionButton.isDisplayed() && 
//	                "svg_save".equals(saveToCollectionButton.getAttribute("id"))) {
//	                
//	                buttonFound = true;
//	                System.out.println("Save button is displayed and verified!");
//	                break;
//	            }
//	            
//	        } catch (NoSuchElementException e) {
//	            System.out.println("Attempt " + (i + 1) + ": Save button not found yet.");
//	            
//	            // Scroll down to load more content
//	            JavascriptExecutor js = (JavascriptExecutor) driver;
//	            js.executeScript("window.scrollBy(0, 300);");
//	            Thread.sleep(800);
//	            
//	            // Click "Load More" if available
//	            if (!loadMoreButtons.isEmpty()) {
//	                try {
//	                    loadMoreButtons.get(0).click();
//	                    Thread.sleep(800);
//	                } catch (Exception ignored) {
//	                    System.out.println("Load More button not clickable.");
//	                }
//	            }
//	        }
//	    }
//	    
//	    // Verify button was found
//	    if (!buttonFound || saveToCollectionButton == null) {
//	        throw new RuntimeException(
//	            "Save to Collection button could not be found after " + 
//	            maxScrollAttempts + " attempts."
//	        );
//	    }
//	    
//	    System.out.println("Ready to click Save button!");
//	    
//	    // Scroll the button into view
//	    JavascriptExecutor js = (JavascriptExecutor) driver;
//	    js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", 
//	                     saveToCollectionButton);
//	    Thread.sleep(1000);
//	    
//	    // Move to element and click
//	    actions.moveToElement(saveToCollectionButton).perform();
//	  
//	    Thread.sleep(500);
//	    
//	    // Try click with retry
//	    try {
//	        saveToCollectionButton.click();
//	 
//	    } catch (Exception e) {
//	        System.out.println("Normal click failed, trying JS click...");
//	        js.executeScript("arguments[0].click();", saveToCollectionButton);
//	    }
//	    
//	    System.out.println("Save to Collection button clicked successfully!");
//	}
	
//	public void clickSaveToCollectionButton(WebElement icon) throws InterruptedException {
//	    JavascriptExecutor js = (JavascriptExecutor) driver;
//
//	    // Locators
//	    By saveButtonLocator = By.id("svg_save");
//	    By unsaveButtonLocator = By.id("svg_unsave");
//
//	    // Store icon identifier for re-location after DOM refresh
//	    String iconId = icon.getAttribute("data-id"); // Adjust based on your actual unique attribute
//	    // Or use another unique identifier like:
//	    // String iconId = icon.getAttribute("id");
//	    // String iconTitle = icon.getAttribute("title");
//	    By iconLocator = By.xpath("//div[@data-slug-id='" + iconId + "']"); 
//	    // Adjust the xpath based on how you identify icons uniquely
//
//	    System.out.println("Checking icon save status...");
//
//	    // Hover over the icon to make buttons appear
//	    actions.moveToElement(icon).perform();
//	    Thread.sleep(600);
//
//	    // Check if icon is already saved (unsave button present)
//	    boolean wasUnsaved = false;
//	    try {
//	        WebElement unsaveButton = icon.findElement(unsaveButtonLocator);
//
//	        // If we found it, try to use it - if it fails, we'll catch it
//	        System.out.println("Icon is already saved. Unsaving it...");
//
//	        // Scroll to unsave button
//	        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", unsaveButton);
//	        Thread.sleep(500);
//
//	        // Click unsave button
//	        try {
//	            unsaveButton.click();
//	        } catch (Exception e) {
//	            js.executeScript("arguments[0].click();", unsaveButton);
//	        }
//
//	        // Wait for and click remove confirmation
//	        wait.until(ExpectedConditions.visibilityOf(RemoveIcon));
//	        RemoveIcon.click();
//
//	        Thread.sleep(5000);
//	        
//	        
//	        System.out.println("Icon unsaved successfully!");
//	     
//	        wasUnsaved = true;
//	      
//
//	    } catch (NoSuchElementException e) {
//	        System.out.println("Unsave button not found. Icon is not saved. Proceeding to save it...");
//	    } catch (Exception e) {
//	        System.out.println("Error during unsave operation: " + e.getMessage() + ". Proceeding to save...");
//	    }
//
//	   //
//
//	    // Now save the icon (whether it was just unsaved and re-located, or was never saved)
//	    try {
//	        WebElement saveToCollectionButton = icon.findElement(saveButtonLocator);
//
//	        System.out.println("Save button found!");
//
//	        // Scroll the button into view
//	        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
//	                         saveToCollectionButton);
//	        Thread.sleep(500);
//
//	        // Move to element and click
//	        actions.moveToElement(saveToCollectionButton).perform();
//	        Thread.sleep(500);
//
//	        // Try click with retry
//	        try {
//	            saveToCollectionButton.click();
//	        } catch (Exception e) {
//	            System.out.println("Normal click failed, trying JS click...");
//	            js.executeScript("arguments[0].click();", saveToCollectionButton);
//	        }
//
//	        System.out.println("Icon saved successfully!");
//
//	    } catch (NoSuchElementException e) {
//	        throw new RuntimeException("Save button could not be found on the icon: " + e.getMessage());
//	    }
//	}
	
	public void clickSaveToCollectionButton(WebElement icon) throws InterruptedException {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    By saveButtonLocator = By.id("svg_save");

	    System.out.println("Saving icon to collection...");

	    // Hover over the icon to make buttons appear
	    actions.moveToElement(icon).perform();
	    Thread.sleep(600);

	    try {
	        WebElement saveToCollectionButton = icon.findElement(saveButtonLocator);
	        System.out.println("Save button found!");

	        // Scroll the button into view
	        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
	                         saveToCollectionButton);
	        Thread.sleep(500);

	        // Move to element and click
	        actions.moveToElement(saveToCollectionButton).perform();
	        Thread.sleep(500);

	        // Try click with retry
	        try {
	            saveToCollectionButton.click();
	        } catch (Exception e) {
	            System.out.println("Normal click failed, trying JS click...");
	            js.executeScript("arguments[0].click();", saveToCollectionButton);
	        }

	        System.out.println("Icon saved successfully!");

	    } catch (NoSuchElementException e) {
	        throw new RuntimeException("Save button could not be found on the icon: " + e.getMessage());
	    }
	}
	

	public void clickIconByUniqueId(String uniqueId) {
		String xpath = "//div[@data-slugid='" + uniqueId + "']";
		WebElement elementToClick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		actions.moveToElement(elementToClick).build().perform();
		elementToClick.click();
	}

	public boolean isIconPresentInList(String uniqueId) {
		wait.until(ExpectedConditions.visibilityOfAllElements(iconElements));
		return iconElements.stream().peek(card -> System.out.println("UniqueId: " + card.getAttribute("data-slugid")))
				.anyMatch(card -> card.getAttribute("data-slugid").equals(uniqueId));

	}
}
