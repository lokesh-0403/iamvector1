package pageobjectmodal;

import java.time.Duration;
import java.util.List;
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
    
    @FindBy(css = "input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")
    private WebElement searchEnterButton;
    
    @FindBy(xpath = "//div[@id='icons_div_container'] //div[@data-test-id='icon_element']")
    private List<WebElement> iconElements;
    
    @FindBy(xpath = "//span[contains(text(),'Load')]")
    private List<WebElement> loadMoreButtons;
    
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
    
    public String getIconUniqueId(WebElement icon) {
        return icon.getAttribute("data-slugid");
    }
    
    public void clickSaveToCollectionButton(WebElement icon) throws InterruptedException {
        WebElement saveToCollectionButton = null;
        int maxScrollAttempts = 5;
        boolean buttonFound = false;
        
        for (int i = 0; i < maxScrollAttempts; i++) {
            try {
                saveToCollectionButton = icon.findElement(By.xpath(".//div[@class='rounded bg-white svg_save']"));
                
                actions.moveToElement(icon).perform();
                Thread.sleep(1000);
                
                if (saveToCollectionButton.isDisplayed() && saveToCollectionButton.isEnabled()) {
                    buttonFound = true;
                    break;
                }
                
            } catch (Exception e) {
                System.out.println("Attempt " + (i + 1) + ": Button not found or not interactable.");
            }
            
            // Scroll manually
            js.executeScript("window.scrollBy(0, 300);");
            Thread.sleep(1000);
            
            // Try clicking 'Load More' if present
            if (!loadMoreButtons.isEmpty()) {
                try {
                    loadMoreButtons.get(0).click();
                    System.out.println("Clicked Load More");
                    Thread.sleep(2000);
                } catch (Exception ex) {
                    System.out.println("Load More click failed: " + ex.getMessage());
                }
            }
        }
        
        if (!buttonFound || saveToCollectionButton == null) {
            throw new RuntimeException("Save to Collection button could not be found after scrolling/loading.");
        }
        
        saveToCollectionButton.click();
    }
    
    public void clickIconByUniqueId(String uniqueId) {
        String xpath = "//div[@data-slugid='" + uniqueId + "']";
        WebElement elementToClick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        actions.moveToElement(elementToClick).perform();
        elementToClick.click();
    }
    
    public boolean isIconPresentInList(String uniqueId) {
        wait.until(ExpectedConditions.visibilityOfAllElements(iconElements));
        return iconElements.stream().anyMatch(card -> card.getAttribute("data-slugid").equals(uniqueId));
    }
}
