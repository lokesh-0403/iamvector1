package pageobjectmodal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
  
    private WebDriverWait wait;
    
    @FindBy(id = "login-btn")
    private WebElement loginButton;
    
    @FindBy(id = "auth-login-btn")
    private WebElement authLoginButton;
    
    @FindBy(id = "auth-login-email")
    private WebElement emailField;
    
    @FindBy(id = "auth-login-password")
    private WebElement passwordField;
    
    @FindBy(id = "auth-loginBtn")
    private WebElement submitButton;
    
    public LoginPage(WebDriver driver) {
       
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }
    
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }
    
    public void clickAuthLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(authLoginButton)).click();
    }
    
    public void enterEmail(String email) {
        WebElement emailElement = wait.until(ExpectedConditions.elementToBeClickable(emailField));
        emailElement.clear();
        emailElement.sendKeys(email);
    }
    
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordField)).sendKeys(password);
    }
    
    public void clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }
    
    public void login(String email, String password) {
        clickLoginButton();
        clickAuthLoginButton();
        enterEmail(email);
        enterPassword(password);
        clickSubmitButton();
    }
}