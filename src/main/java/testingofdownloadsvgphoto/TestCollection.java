package testingofdownloadsvgphoto;

import java.awt.AWTException;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import pageobjectmodal.BasePage;
import pageobjectmodal.LoginPage;
import utils.TestDataProvider;

public class TestCollection {
	private WebDriver driver;
	private BasePage basePage;
	private LoginPage loginPage;

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		basePage = new BasePage(driver);
		loginPage = new LoginPage(driver);

		basePage.setupDriver();
		basePage.navigateToApplication();
	}

//	@AfterMethod
//	public void tearDown() {
//		basePage.closeBrowser();
//
//	}

	 @Test(dataProvider = "loginCredentialsAndKeyValue", dataProviderClass =
	 TestDataProvider.class)
	public void addToCollection(String emailId, String password, String searchKey)
			throws AWTException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		loginPage.login(emailId, password);

		Thread.sleep(6000);

		WebElement searchBox = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@id='homeSearchInput']//input[@name='search']")));
		searchBox.sendKeys(searchKey);

		try {

			WebElement searchEnter = wait.until(ExpectedConditions.elementToBeClickable(
					By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
			searchEnter.sendKeys(Keys.ENTER);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Thread.sleep(5000);
		List<WebElement> Icons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//div[@id='icons_div_container'] //div[@data-test-id='icon_element']") // Replace with
		// actual card
		// selector
		));

		if (Icons.size() == 0) {
			throw new RuntimeException("No doctor icons found!");
		}

		// 3. Pick a random card
		Random rand = new Random();
		WebElement randomIcon = Icons.get(rand.nextInt(Icons.size()));

		String uniqueId = randomIcon.getAttribute("data-slugid");

		System.out.println("yash" + uniqueId);

		Actions actions = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement saveToCollectionButton = null;

		int maxScrollAttempts = 5;
		boolean buttonFound = false;

		for (int i = 0; i < maxScrollAttempts; i++) {
			try {
				// Check if the button exists in the card
				saveToCollectionButton = randomIcon.findElement(By.xpath(".//div[@class='rounded bg-white svg_save']"));

				// Move to the card to ensure visibility
				actions.moveToElement(randomIcon).perform();
				Thread.sleep(1000); // give time for UI to update

				// Try clicking if it's visible and enabled
				if (saveToCollectionButton.isDisplayed() && saveToCollectionButton.isEnabled()) {
					buttonFound = true;
					break;
				}

			} catch (Exception e) {
				System.out.println("Attempt " + (i + 1) + ": Button not found or not interactable.");
			}

			// Scroll manually (in case it's not rendered yet)
			js.executeScript("window.scrollBy(0, 300);");
			Thread.sleep(1000);

			// Try clicking 'Load More' if present
			List<WebElement> loadMoreButtons = driver.findElements(By.xpath("//span[contains(text(),'Load')]"));
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

		WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.id("custom-search")));
		search.click();

		WebElement testCollection = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='card']")));
		testCollection.click();

		WebElement removeDefaultCollection = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='flexCheckDefault']")));
		removeDefaultCollection.click();

		WebElement saveButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='add-btn']")));
		saveButton.click();

		actions.moveByOffset(20, 20).click().build().perform();

		// Thread.sleep(15000);
		WebElement myCollection = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='nav-link fw-semibold']")));
		myCollection.click();

		WebElement cardCollection = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='card']")));
		cardCollection.click();

		List<WebElement> doctorCards1 = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//div[@id='icons_div_container'] //div[@data-test-id='icon_element']")));

		boolean isPresent = doctorCards1.stream().anyMatch(card -> card.getAttribute("data-slugid").equals(uniqueId));

		if (isPresent) {
			System.out.println("card is present.");
			String xpath = "//div[@data-slugid='" + uniqueId + "']";
			WebElement elementToClick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			elementToClick.click();
			WebElement downloadIcon = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Download']")));
			downloadIcon.click();

		} else {
			Assert.fail("card is NOT present in the refreshed list.");
		}
	}

	@Test(dataProvider = "loginCredentialsForManageCollection", dataProviderClass = TestDataProvider.class)
	public void addToCollectionCreateNewCollectionThenDeletecollection(String emailId, String password,
			String searchKey, String collectionName) throws AWTException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		loginPage.login(emailId, password);

		Thread.sleep(6000);

		WebElement searchBox = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@id='homeSearchInput']//input[@name='search']")));
		searchBox.sendKeys(searchKey);

		try {

			WebElement searchEnter = wait.until(ExpectedConditions.elementToBeClickable(
					By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
			searchEnter.sendKeys(Keys.ENTER);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Thread.sleep(5000);
		List<WebElement> Icons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//div[@id='icons_div_container'] //div[@data-test-id='icon_element']") // Replace with
		// actual card
		// selector
		));

		if (Icons.size() == 0) {
			throw new RuntimeException("No doctor icons found!");
		}

		// 3. Pick a random card
		Random rand = new Random();
		WebElement randomIcon = Icons.get(rand.nextInt(Icons.size()));

		String uniqueId = randomIcon.getAttribute("data-slugid");

		System.out.println("Icon Id" + uniqueId);

		Actions actions = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement saveToCollectionButton = null;

		int maxScrollAttempts = 5;
		boolean buttonFound = false;

		for (int i = 0; i < maxScrollAttempts; i++) {
			try {
				// Check if the button exists in the card
				saveToCollectionButton = randomIcon.findElement(By.xpath(".//div[@class='rounded bg-white svg_save']"));

				// Move to the card to ensure visibility
				actions.moveToElement(randomIcon).perform();
				Thread.sleep(1000); // give time for UI to update

				// Try clicking if it's visible and enabled
				if (saveToCollectionButton.isDisplayed() && saveToCollectionButton.isEnabled()) {
					buttonFound = true;
					break;
				}

			} catch (Exception e) {
				System.out.println("Attempt " + (i + 1) + ": Button not found or not interactable.");
			}

			// Scroll manually (in case it's not rendered yet)
			js.executeScript("window.scrollBy(0, 300);");
			Thread.sleep(1000);

			// Try clicking 'Load More' if present
			List<WebElement> loadMoreButtons = driver.findElements(By.xpath("//span[contains(text(),'Load')]"));
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

		WebElement createNewCollection = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='new-collection-btn']")));
		createNewCollection.click();

		WebElement createCollection = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='new-collection-name-field']")));
		createCollection.sendKeys(collectionName);

		WebElement save = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='save-collection-btn']")));
		save.click();
		WebElement removeDefaultCollection = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='flexCheckDefault']")));
		removeDefaultCollection.click();

		WebElement saveButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='add-btn']")));
		saveButton.click();

		actions.moveByOffset(20, 20).click().build().perform();

		// Thread.sleep(15000);
		WebElement myCollection = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='nav-link fw-semibold']")));
		myCollection.click();

		String collectionName1 = collectionName;
		
		Thread.sleep(6000);

		WebElement cardCollection = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='" + collectionName1 + "']")));
		actions.moveToElement(cardCollection).perform();
		cardCollection.click();

		List<WebElement> Cards1 = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//div[@id='icons_div_container'] //div[@data-test-id='icon_element']")));

		boolean isPresent = Cards1.stream().anyMatch(card -> card.getAttribute("data-slugid").equals(uniqueId));

		if (isPresent) {
			System.out.println("card is present.");
			String xpath = "//div[@data-slugid='" + uniqueId + "']";
			WebElement elementToClick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			actions.moveToElement(elementToClick).perform();

			elementToClick.click();

		} else {
			Assert.fail("card is NOT present in the refreshed list.");
		}
		Thread.sleep(3000);
		driver.navigate().back();
		Thread.sleep(3000);

		try {
			// Fix class match using contains
			WebElement sortTheCollection = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//div[contains(@class,'collection-card')]//a[normalize-space()='"
							+ collectionName1.trim() + "']")));

			// Move to the collection element
			actions.moveToElement(sortTheCollection).perform();

			// Go up to the parent card and find the dropdown
			WebElement parentCard = sortTheCollection
					.findElement(By.xpath("./ancestor::div[contains(@class,'collection-card')]"));
			WebElement dropdown = parentCard.findElement(By.xpath(".//span[contains(@class,'dropdown-trigger')]"));

			dropdown.click();

			WebElement deleteCollection = driver.findElement(By.xpath(
					"//ul[contains(@class,'dropdown-menu collection-options show')]//span[contains(@class,'danger')][normalize-space()='Delete Collection']"));
			deleteCollection.click();
		} catch (Exception e) {
			System.out.println("Dropdown not found or not clickable: " + e.getMessage());
			Assert.fail();
		}

	}
	
	
	@Test(dataProvider = "loginCredentialsForManageCollection", dataProviderClass = TestDataProvider.class)
	public void addToCollectionCreateNewCollectionThenEditcollectionName(String emailId, String password,
			String searchKey, String collectionName) throws AWTException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		loginPage.login(emailId, password);

		Thread.sleep(6000);

		WebElement searchBox = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@id='homeSearchInput']//input[@name='search']")));
		searchBox.sendKeys(searchKey);

		try {

			WebElement searchEnter = wait.until(ExpectedConditions.elementToBeClickable(
					By.cssSelector("input.bg-white.px-3.px-md-4.search-input-radius.fw-medium.form-control")));
			searchEnter.sendKeys(Keys.ENTER);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Thread.sleep(5000);
		List<WebElement> Icons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//div[@id='icons_div_container'] //div[@data-test-id='icon_element']") // Replace with
		// actual card
		// selector
		));

		if (Icons.size() == 0) {
			throw new RuntimeException("No doctor icons found!");
		}

		// 3. Pick a random card
		Random rand = new Random();
		WebElement randomIcon = Icons.get(rand.nextInt(Icons.size()));

		String uniqueId = randomIcon.getAttribute("data-slugid");

		System.out.println("Icon Id" + uniqueId);

		Actions actions = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement saveToCollectionButton = null;

		int maxScrollAttempts = 5;
		boolean buttonFound = false;

		for (int i = 0; i < maxScrollAttempts; i++) {
			try {
				// Check if the button exists in the card
				saveToCollectionButton = randomIcon.findElement(By.xpath(".//div[@class='rounded bg-white svg_save']"));

				// Move to the card to ensure visibility
				actions.moveToElement(randomIcon).perform();
				Thread.sleep(1000); // give time for UI to update

				// Try clicking if it's visible and enabled
				if (saveToCollectionButton.isDisplayed() && saveToCollectionButton.isEnabled()) {
					buttonFound = true;
					break;
				}

			} catch (Exception e) {
				System.out.println("Attempt " + (i + 1) + ": Button not found or not interactable.");
			}

			// Scroll manually (in case it's not rendered yet)
			js.executeScript("window.scrollBy(0, 300);");
			Thread.sleep(1000);

			// Try clicking 'Load More' if present
			List<WebElement> loadMoreButtons = driver.findElements(By.xpath("//span[contains(text(),'Load')]"));
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

		WebElement createNewCollection = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='new-collection-btn']")));
		createNewCollection.click();

		WebElement createCollection = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='new-collection-name-field']")));
		createCollection.sendKeys(collectionName);

		WebElement save = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='save-collection-btn']")));
		save.click();
		WebElement removeDefaultCollection = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='flexCheckDefault']")));
		removeDefaultCollection.click();

		WebElement saveButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='add-btn']")));
		saveButton.click();

		actions.moveByOffset(20, 20).click().build().perform();

		// Thread.sleep(15000);
		WebElement myCollection = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='nav-link fw-semibold']")));
		myCollection.click();

		String collectionName1 = collectionName;
		
		Thread.sleep(6000);

		WebElement cardCollection = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='" + collectionName1 + "']")));
		actions.moveToElement(cardCollection).perform();
		cardCollection.click();

		List<WebElement> Cards1 = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//div[@id='icons_div_container'] //div[@data-test-id='icon_element']")));

		boolean isPresent = Cards1.stream().anyMatch(card -> card.getAttribute("data-slugid").equals(uniqueId));

		if (isPresent) {
			System.out.println("card is present.");
			String xpath = "//div[@data-slugid='" + uniqueId + "']";
			WebElement elementToClick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			actions.moveToElement(elementToClick).perform();

			elementToClick.click();

		} else {
			Assert.fail("card is NOT present in the refreshed list.");
		}
		Thread.sleep(3000);
		driver.navigate().back();
		Thread.sleep(3000);

		try {
			// Fix class match using contains
			WebElement sortTheCollection = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//div[contains(@class,'collection-card')]//a[normalize-space()='"
							+ collectionName1.trim() + "']")));

			// Move to the collection element
			actions.moveToElement(sortTheCollection).perform();

			// Go up to the parent card and find the dropdown
			WebElement parentCard = sortTheCollection
					.findElement(By.xpath("./ancestor::div[contains(@class,'collection-card')]"));
			WebElement dropdown = parentCard.findElement(By.xpath(".//span[contains(@class,'dropdown-trigger')]"));

			dropdown.click();

			WebElement deleteCollection = driver.findElement(By.xpath(
					"//ul[@class='dropdown-menu collection-options show']//span[contains(text(),'Edit name')]"));
			deleteCollection.click();
		} catch (Exception e) {
			System.out.println("Dropdown not found or not clickable: " + e.getMessage());
			Assert.fail();
		}
		
		

		WebElement editField = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='new-collection-name']")));
		Faker faker = new Faker();
    	String editedName = "AutoCollection_" + faker.animal().name()+ "_" + System.currentTimeMillis();
		editField.sendKeys(editedName);
		editField.sendKeys(Keys.ENTER);
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}