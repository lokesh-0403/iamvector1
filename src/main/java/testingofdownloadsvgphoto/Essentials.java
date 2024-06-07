package testingofdownloadsvgphoto;

import org.openqa.selenium.chrome.ChromeOptions;

public class Essentials {
	
public void chromeInput() {
	
	 ChromeOptions options = new ChromeOptions();
     options.addArguments("--disable-extensions");
     options.addArguments("--disable-notifications");
     options.addArguments("--no-sandbox");
     options.addArguments("--disable-dev-shm-usage");
     options.addArguments("--disable-gpu");
     options.addArguments("--window-size=1920,1080");
     options.addArguments("--disable-popup-blocking");
	
}
}
