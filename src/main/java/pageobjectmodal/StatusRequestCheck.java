package pageobjectmodal;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StatusRequestCheck {
	  private Set<String> links = new HashSet<>();
	  private Set<String> checkedLinks = new HashSet<>();
	  private Set<String> workingLinks = new HashSet<>();
	  private Set<String> brokenLinks = new HashSet<>();
	  private Set<String> otherLinks = new HashSet<>();
	  private Set<String> errorLink = new HashSet<>();
	  private WebDriverWait wait;
	
	    
	
	 public StatusRequestCheck(WebDriver driver) {
		// TODO Auto-generated constructor stub
		 this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        PageFactory.initElements(driver, this);
	}


	 public void checkLinks(WebDriver driver) throws Exception {
	 
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 
		 
	
			
		 long lastHeight = (long) js.executeScript("return document.body.scrollHeight");
		   while (true) {
	         
//	            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	            Thread.sleep(2000); 
	            List<WebElement> allLinks = driver.findElements(By.xpath("//a[@href]"));
	            for (WebElement link : allLinks) {
	            	String href = link.getAttribute("href");
	            	  if (href == null || href.trim().isEmpty() || checkedLinks.contains(href)) {
	                      continue;
	                  }

	            	 try {
	            	        
	            	        js.executeScript("arguments[0].scrollIntoView(true);", link);
	            	        Thread.sleep(500); 

	            	        if (href != null && !href.trim().isEmpty()) {
	            	            System.out.println("Checking link: " + href);
	            	            checkLinkStatus(href); 
	            	      //      checkLinkStatus(href, driver); 
	            	            checkedLinks.add(href);

	            	        }

	            	    } catch (Exception e) {
	            	        System.out.println("Error handling link: " + e.getMessage());
	            	    }
	            }
	            
	            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	            Thread.sleep(2000);

	            long newHeight = (long) js.executeScript("return document.body.scrollHeight");
	            if (newHeight == lastHeight) {
	                break;
	            }
	            lastHeight = newHeight;
	        }
		//   driver.findElement(By.cssSelector("li[title='Follow Us on LinkedIn']")).click();
		   
	        System.out.println(" Total unique links checked: " + checkedLinks.size());
            	        
} 
	 
	 
	 public void checkLinkStatus(String link) {
		
		 
		  try {
	            HttpURLConnection connection = (HttpURLConnection) new URL(link).openConnection();
	            connection.setRequestMethod("HEAD"); 
	            connection.setConnectTimeout(5000);
	            connection.connect();

	            int statusCode = connection.getResponseCode();
	            
	            if (statusCode == 200) {
	                System.out.println("<<+>> Link Working (" + statusCode + ") : " + link);
	                workingLinks.add(link);
	            } else if (statusCode >= 404 && statusCode != 999 ) {
	                System.out.println("<<*>> Link Broken (" + statusCode + ") : " + link);
	                brokenLinks.add(link+" with status code : ("+statusCode +")");
	            }else if (statusCode >= 300 && statusCode < 404) {
	                System.out.println("<<->> No Server Response (" + statusCode + ") : " + link);
	                otherLinks.add("-> Working Links : " + link + " with status code : ("+statusCode +")");
	            } else {
	                System.out.println("<<#>> Other Status (" + statusCode + ") : " + link);
	                otherLinks.add(link+" with status code : ("+statusCode +")");
	            }

	        } catch (Exception e) {
	            System.out.println("<<.>> "+link + " ---> Exception : " + e.getMessage());
	            errorLink.add(link);
	        }
	 }
	 
	 public void outputs() {
		 System.out.println("\n===== LINK STATUS REPORT =====");
		 System.out.println(" Working Links (" + workingLinks.size() + "):");
		 workingLinks.forEach(System.out::println);

		 System.out.println("\n Broken Links (" + brokenLinks.size() + "):");
		 brokenLinks.forEach(System.out::println);

		 System.out.println("\n Other Links (" + otherLinks.size() + "):");
		 otherLinks.forEach(System.out::println);
		 
		 System.out.println("\n Error Links (" + errorLink.size() + "):");
		 errorLink.forEach(System.out::println);
	 }
	 
}

