package Testcases;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MultipleWindowsHandling {

	public static void main(String[] args) {
		
		WebDriver driver = new FirefoxDriver();
		
		driver.get("https://www.google.co.in/?gws_rd=ssl");

	    String currentWindowHandle = driver.getWindowHandle();

	    driver.findElement(By.linkText("Gmail")).sendKeys(Keys.CONTROL, Keys.RETURN);

	    //Get the list of all window handles
	    ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());

	    for (String window:windowHandles){

	        //if it contains the current window we want to eliminate that from switchTo();
	        if (window != currentWindowHandle){
	            //Now switchTo new Tab.
	            driver.switchTo().window(windowHandles.get(1));
	            //Do whatever you want to do here.

	            //Close the newly opened tab
	            //driver.close();
	        }
	    }
	}

}
