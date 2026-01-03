 package brokenLinkTest;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import pageobjectmodal.BasePage;

import pageobjectmodal.LoginPage;

import pageobjectmodal.StatusRequestCheck;
import utils.TestDataProvider;

public class LinkStatusCheck {

	  private WebDriver driver;
	    private BasePage basePage;
	    private LoginPage loginPage;
	    private	DevTools devTools;
	    private	StatusRequestCheck statusRequestCheck;
	    public static ExecutorService executor;
	    public static HttpClient client;
	    private Set<String> links = new HashSet<>();
	    
	    @BeforeClass
	    public void setupHttpClient() {
	        executor = Executors.newFixedThreadPool(5);
	        client = HttpClient.newBuilder()
	                .executor(executor)
	                .build();
	    }
	   
	    @BeforeMethod
	    public void setUp() {
	        driver = new ChromeDriver();
	      
	        basePage = new BasePage(driver);
	        loginPage = new LoginPage(driver);
	        statusRequestCheck = new StatusRequestCheck(driver);
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.manage().window().maximize();
	        basePage.setupDriver();
	        basePage.navigateToApplication();
	    }
	    
	    
	    @Test(dataProvider = "loginCredentials", dataProviderClass = TestDataProvider.class,retryAnalyzer = utils.RetryAnalyzer.class)
	    public void addToCollection(String emailId, String password)
	            throws Exception {
	        
	        // Login
	        loginPage.login(emailId, password);
	        Thread.sleep(6000);
	        statusRequestCheck.checkLinks(driver);	    
	    	statusRequestCheck.outputs();
}
	    	
	    	
	    @AfterMethod
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	    
	

}
