package utils;

import java.net.URL;

public class ResourceHelper {

	 public static String getResourceFilePath(String fileName) {
	        URL url = ResourceHelper.class.getClassLoader().getResource(fileName);

	        if (url == null) {
	            throw new RuntimeException("Resource file not found: " + fileName);
	        }

	        return url.getPath();
	    }
}
