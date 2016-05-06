package cucumberStepDefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

	/**
	 * Selenium Test Script
	 * Now write a simple script performing the following steps in Selenium.
	 *   1.Launch the Browser
	 *   2.Navigate to Home Page
	 *   3.Click on the LogIn link
	 *   4.Enter UserName and Password
	 *   5.Click on Submit button
	 *   6.Print a successful message
	 *   7.LogOut from the application
	 *   8.Print a successful message
	 *   9.Close the Browser
	 */
	public class SeleniumTest {
		private static WebDriver driver = null;
	
		public static void main(String[] args) {
		// Create a new instance of the Firefox driver
 
        driver = new FirefoxDriver();
 
        //Put a Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception
 
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 
        //Launch the Online Store Website
 
        driver.get("http://www.store.demoqa.com");
 
        // Find the element that's ID attribute is 'account'(My Account) 
 
        driver.findElement(By.xpath(".//*[@id='account']/a")).click();
 
        // Find the element that's ID attribute is 'log' (Username)
 
        // Enter Username on the element found by above desc.
 
        driver.findElement(By.id("log")).sendKeys("testuser_1"); 
 
        // Find the element that's ID attribute is 'pwd' (Password)
 
        // Enter Password on the element found by the above desc.
 
        driver.findElement(By.id("pwd")).sendKeys("Test@123");
 
        // Now submit the form. WebDriver will find the form for us from the element 
 
        driver.findElement(By.id("login")).click();
 
        // Print a Log In message to the screen
 
        System.out.println("Login Successfully");
 
        // Find the element that's ID attribute is 'account_logout' (Log Out)
 
        driver.findElement (By.xpath(".//*[@id='account_logout']/a")).click();
 
        // Print a Log In message to the screen
 
        System.out.println("LogOut Successfully");
 
        // Close the driver
 
        driver.quit();
 
	}
 
}
