package pageobjectmodal;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ImageConverterPage {

	private WebDriverWait wait;
	private Actions actions;

	// Page Elements using @FindBy annotations
	@FindBy(xpath = "//h5[normalize-space()='Image Converter']")
	private WebElement imageConverterCard;

	@FindBy(css = "input[type='file']")
	private WebElement fileUploadInput;

	@FindBy(css = ".files-item__download")
	private WebElement downloadButton;

	@FindBy(xpath = "//a[2]")
	private WebElement jpegTab;

	@FindBy(xpath = "//a[3]")
	private WebElement base64Tab;

	@FindBy(xpath = "//a[4]")
	private WebElement webpTab;

	// Constructor
	public ImageConverterPage(WebDriver driver) {

		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		this.actions = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	// Page Actions/Methods
	public void clickImageConverterCard() {
		WebElement card = wait.until(ExpectedConditions.elementToBeClickable(imageConverterCard));
		actions.moveToElement(card).perform();
		card.click();
	}

	public void uploadFile(String filePath) {
		WebElement fileInput = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='file']")));
		fileInput.sendKeys(filePath);
	}

	public void clickDownload() {
		WebElement download = wait.until(ExpectedConditions.elementToBeClickable(downloadButton));
		download.click();
	}

	public void clickJpegTab() {
		WebElement jpeg = wait.until(ExpectedConditions.elementToBeClickable(jpegTab));
		jpeg.click();
	}

	public void clickBase64Tab() {
		WebElement base64 = wait.until(ExpectedConditions.elementToBeClickable(base64Tab));
		base64.click();
	}

	public void clickWebpTab() {
		WebElement webp = wait.until(ExpectedConditions.elementToBeClickable(webpTab));
		webp.click();
	}

	// Combined methods for complete workflows
	public void convertSvgToPng(String filePath) {
		clickImageConverterCard();
		uploadFile(filePath);
		clickDownload();
	}

	public void convertSvgToJpeg(String filePath) {
		clickImageConverterCard();
		clickJpegTab();
		uploadFile(filePath);
		clickDownload();
	}

	public void convertSvgToBase64(String filePath) {
		clickImageConverterCard();
		clickBase64Tab();
		uploadFile(filePath);
		clickDownload();
	}

	public void convertSvgToWebp(String filePath) {
		clickImageConverterCard();
		clickWebpTab();
		uploadFile(filePath);
		clickDownload();
	}
}