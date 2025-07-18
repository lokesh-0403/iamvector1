package imageconverter;

import org.openqa.selenium.WebDriver;
public class Goto {
	WebDriver driver;
	
	public Goto(WebDriver driver) {
		
		this.driver=driver;
		
		// TODO Auto-generated constructor stub
	}

	public void goTo() {

		driver.get("https://iamvector.com/");

}

}
