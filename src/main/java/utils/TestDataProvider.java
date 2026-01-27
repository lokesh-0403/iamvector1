package utils;

import java.io.InputStream;

import org.testng.annotations.DataProvider;

import com.github.javafaker.Faker;

public class TestDataProvider {
    
    @DataProvider(name = "imageFiles")
    public Object[][] getImageFiles() {
        return new Object[][] {
            {"SVG", ResourceHelper.getResourceFilePath("files/Female doctor with cross mark.svg")},
            {"PNG", ResourceHelper.getResourceFilePath("files/iamvector_download.png")},
            {"JPG", ResourceHelper.getResourceFilePath("files/Computer desktop imac.jpg")}
        };
    }
    
    @DataProvider(name = "loginCredentialsAndKeyValue")
    public Object[][] getLoginCredentials() {
        return new Object[][] {
            {"yeshsharma516032@gmail.com", "Yesh255198@","cat"},
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
            {"yeshsharma516032@gmail.com", "Yesh255198@", "Phone", collectionName},
        
        };
    }
    
    
    @DataProvider(name = "uplaodingSVGFile")
    public Object uploadSVGFile() {
    	 String file = ResourceHelper.getResourceFilePath("files/Female doctor with cross mark.svg");
    	return new Object[][] {
              {"yeshsharma516032@gmail.com", "Yesh255198@", file},
          
          };
    }  
    
}