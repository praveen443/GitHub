package WithoutTestNG_TestCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import Actions.AlertHandling;
import Actions.Application;
import Actions.DriverObject;
import Actions.Magento_ReusableFunctions;
import Actions.MouseHoverOperation;
import PageObjects.Properties;

public class TC31_DeleteMultipleCustomersbyID extends  DriverObject  
{

	public static void main(String[] args) throws InterruptedException 
	{

		 WebDriver driver = new FirefoxDriver();
		  
		 //Calling Objects From POM
		 PageFactory.initElements(driver, Properties.class);
		 Properties PageElements = new Properties(driver);
			   
		 //Launch the Application URL
		 Application.launchURL();
			
		 //Login into the Magento Application
		 //Application.login(driver, Constant.Username, Constant.Password);
			     
		 //Handle the Incoming message Pop-Up window
		 Magento_ReusableFunctions.HandlePopUpWindow(driver);
			     
		 //Verify the user login
		 Magento_ReusableFunctions.Verify_UserLogin(driver);
			  
		 //Calling method to perform Mouse-hover on the 'Customers' Tab
		 MouseHoverOperation.mouseHoverUsingPartialLinkText("Customers", driver);
		 Reporter.log("Mouse-hover on the Customers tab in the topmenu.");
			  
		 //Click on 'Manage Customers' link.
		 PageElements.TextLink_ManageCustomers.click();
		 System.out.println("Click on 'Manage Customers' link.");
			  
		 //Calling this method to verify the Manage Customers Page navigation.
		 Magento_ReusableFunctions.Verify_ManageCustomerspageNavigation(driver);
			  
		 //To capture the total no. of records available in the webtable
		 List<WebElement> NumOfRecords = driver.findElements(By.xpath(".//*[@id='customerGrid_table']/tbody/tr"));		  
		 int getTotalRecords = NumOfRecords.size();
		 System.out.println("Total No. of records found in the Customers table is : "+getTotalRecords); 	
		 
		 //Thread.sleep(5000);
		 
		 //Select the mutiple customers randomly
		 
		
		 Magento_ReusableFunctions.chooseOrdernumber_by_status(driver);
		 
		 //Select Option 'Delete' in the 'Actions' dropdown
		 Magento_ReusableFunctions.selectOptionDeleteFromActionsDropdown(driver, "Delete", "customerGrid_massaction-select");

		 //Click on 'Submit' button
		 PageElements.Button_Submit.click();
		 
		 //Handle the Alert Pop-UP Message
		 AlertHandling.checkAlert(driver);
		 
		 //Verify the valid message after deleting the records.
  	     if(PageElements.ValidTextMessage.isDisplayed())
  	     {
	    	String getValidText = PageElements.ValidTextMessage.getText();
	    	System.out.println("OutPut Message is : "+getValidText);
	    	Reporter.log("OutPut Message is :"+getValidText);
  	     }
  	     
  	     //Log-Out from the application
  	     Application.logOut(driver);
 		     
  	     //Verify the User-LogOut
  	     Magento_ReusableFunctions.Verify_UserLogOut(driver); 
  	     
  	     //Close the browser
  	     DriverObject.closeBrowser();
		 
	}

}
