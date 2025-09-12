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
            {"yeshsharma516032@gmail.com", "Yesh255198@", "creature"},
          //  {"yeshsharma516032@gmail.com", "Yesh255198@", "chair"},
          //  {"yeshsharma516032@gmail.com", "Yesh255198@", "mobile"},
        };
    }
    
    @DataProvider(name = "loginCredentials")
    public Object[][] LoginCredentials() {
        return new Object[][] {
            {"yeshsharma516032@gmail.com", "Yesh255198@"}
          //  {"yeshsharma516032@gmail.com", "Yesh255198@"},
          //  {"yeshsharma516032@gmail.com", "Yesh255198@"},
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
    
    
    @DataProvider(name = "uplaodingSVGFile")
    public Object uploadSVGFile() {
    	String file = "C:\\Users\\admin\\Downloads\\Female doctor with cross mark.png";
    	String fileE ="C:\\Users\\admin\\Downloads\\SVG.svg";
    	return new Object[][] {
              {"yeshsharma516032@gmail.com", "Yesh255198@", file,fileE},
          
          };
    }  
    
}