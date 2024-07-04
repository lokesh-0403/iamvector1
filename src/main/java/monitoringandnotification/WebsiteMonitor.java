package monitoringandnotification;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebsiteMonitor {
    private static final String WEBSITE_URL = "https://www.iamvectors.com";
    private static final String EXPECTED_TITLE = "Expected Title";

    public boolean isWebsiteUp() {
        WebDriver driver = null;
        boolean isUp = false;

        try {
            // Set the path to your ChromeDriver executable
      

            // Initialize WebDriver
            driver = new ChromeDriver();

            // Navigate to the website URL
            driver.get(getWebsiteUrl());

            // Check if the website is accessible by verifying the title
            isUp = driver.getTitle().equals(EXPECTED_TITLE);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Quit WebDriver after use
            if (driver != null) {
                driver.quit();
            }
        }

        return isUp;
    }

	public static String getWebsiteUrl() {
		return WEBSITE_URL;
	}
}
