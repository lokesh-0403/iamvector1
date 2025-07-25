package utils;

import org.testng.annotations.DataProvider;

import com.github.javafaker.Faker;

public class TestDataProvider {
    
    @DataProvider(name = "imageFiles")
    public Object[][] getImageFiles() {
        return new Object[][] {
            {"SVG", "/Users/yeshsharma/Documents/Female doctor to guide.svg"},
            {"PNG", "/Users/yeshsharma/Documents/Camp Kanga logo.png"},
            {"JPG", "/Users/yeshsharma/Documents/input-onlinejpgtools.jpg"}
        };
    }
    
    @DataProvider(name = "loginCredentialsAndKeyValue")
    public Object[][] getLoginCredentials() {
        return new Object[][] {
            {"yeshsharma516032@gmail.com", "Yesh255198@", "table"},
          //  {"yeshsharma516032@gmail.com", "Yesh255198@", "chair"},
          //  {"yeshsharma516032@gmail.com", "Yesh255198@", "mobile"},
        };
    }
    
    
    
    @DataProvider(name = "loginCredentialsForManageCollection")
   
    public Object[][] getLoginCredentialsForCreateCollectionAndDelete() {
    	Faker faker = new Faker();
    	String collectionName = "AutoCollection_" + faker.animal().name()+ "_" + System.currentTimeMillis();
        return new Object[][] {
            {"yeshsharma516032@gmail.com", "Yesh255198@", "login", collectionName},
        
        };
    }
    
    
    
    
    
    
    
}