/*package WithoutTestNG_TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import Actions.Application;
import Actions.DriverObject;
import Actions.Magento_ReusableFunctions;
import Utility.Constant;

public class TC29_DeleteCustomer_basedOnCustomerName {

public static WebElement Element;

public static void main(String[] args) throws InterruptedException {
	
	  WebDriver driver = new FirefoxDriver();
	   
	  //Launch the Application URL
	  Application.launchURL(driver, Constant.AppURL);
	
	  //Login into the Magento Application
	  //Application.login(driver, Constant.Username, Constant.Password);
	     
	  //Handle the Incoming message Pop-Up window
	  Magento_ReusableFunctions.HandlePopUpWindow(driver);
	     
	  //Verify the user login
	  Magento_ReusableFunctions.Verify_UserLogin(driver);
	  
	  //Calling method to delete the customer based on the customer name
	  Application.deleteCustomerbasedOnCustomerName(driver);
	  
	  //Log-Out from the Application.
	  Application.logOut(driver);
			  
	  //Verify the User Log-Out.
	  Magento_ReusableFunctions.Verify_UserLogOut(driver);
			  
	  //Close the browser.
	  DriverObject.closeBrowser(driver);			  
	}

}
*/