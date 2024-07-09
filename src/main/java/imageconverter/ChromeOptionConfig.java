package imageconverter;

import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeOptionConfig {
	
		public static  ChromeOptions getChromeOptions() {
	        ChromeOptions options = new ChromeOptions();
	      // options.addArguments("--headless");
	        options.addArguments("--disable-gpu"); // Applicable to Windows OS
	        options.addArguments("--window-size=1920,1080"); // Set window size
	      //  options.addArguments("--blink-settings=imagesEnabled=false");
	        //// Disable CSS
	       // options.addArguments("--disable-css");

	        // Disable JavaScript
	       // options.addArguments("--disable-javascript");
	        
	        return options;
	    }
}
