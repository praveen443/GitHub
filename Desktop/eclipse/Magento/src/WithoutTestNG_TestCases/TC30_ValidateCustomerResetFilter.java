/*package WithoutTestNG_TestCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import Actions.Application;
import Actions.DriverObject;
import Actions.Magento_ReusableFunctions;
import Actions.MouseHoverOperation;
import PageObjects.Properties;
import Utility.Constant;

public class TC30_ValidateCustomerResetFilter 
{
   public static WebElement Element;

   public static void main(String[] args) throws InterruptedException 
   {		
	   WebDriver driver = new FirefoxDriver();
			  
	   //Calling Objects From POM
	   PageFactory.initElements(driver, Properties.class);
	   Properties PageElements = new Properties(driver);
			   
	   //Launch the Application URL
	   Application.launchURL(driver, Constant.AppURL);
			
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
			  
	   //Capture total number of  records available in the 'customers attributes' table before reset
	   List<WebElement> CustomersWebTable1 = driver.findElements(By.xpath(".//*[@id='customerGrid_table']/tbody/tr"));
	   int NumOfRecordsBeforeSearch = CustomersWebTable1.size();  
	   System.out.println("Total no. of records found in the Customers Table before Search is : "+NumOfRecordsBeforeSearch);
		
	   //Enter the 'customer name' in the Customer table
	   driver.findElement(By.id("customerGrid_filter_name")).sendKeys("Jane Doe");
	   
	   //Click on 'Search' button.
	   PageElements.Button_Search.click();
	   Reporter.log("Click on 'Search' button.");
	   System.out.println("Click on 'Search' button.");
	     
	   //Capture total number of records available in the 'customers' table after the search
	   List<WebElement> ResultedRecords = driver.findElements(By.xpath(".//*[@id='customerGrid_table']/tbody"));
	   int NumOfFilteredRecords = ResultedRecords.size();  
	   System.out.println("Total no. of records found after the search is : "+NumOfFilteredRecords);
	      
	   //Click on Reset Filter button
	   PageElements.Button_ResetFilter.click();
	   System.out.println("Click on Reset Filter button");
	   Reporter.log("Click on Reset Filter button");
	     
	   //Capture total number of  records available in the 'customers' table after Reset filter
	   List<WebElement> CustomersWebTable2 = driver.findElements(By.xpath(".//*[@id='customerGrid_table']/tbody/tr"));
	   int NumOfRecordsAfterReset = CustomersWebTable2.size();  
	   System.out.println("Total no. of records found after applying the 'Reset filter' is : "+NumOfRecordsAfterReset);
	        
	   //Validate the 'Reset filter' button in the 'Manage Customer' page.
	   //After clicking on the reset filter button, the records should match with the customers table.
	   if (NumOfRecordsBeforeSearch==NumOfRecordsAfterReset) {
		   Reporter.log("Customer Reset filter is validated Successfully");
		   System.out.println("Customer Reset filter is validated Successfully");
	   } else {		
		   Reporter.log("<---- Validation failed to Reset the filter ---->");
		   Reporter.log(" [NumOfRecordsBeforeSearch] doesn't matches with the [NumOfRecordsAfterReset] ");
		   System.out.println("<---- Validation failed to Reset the filter ---->");
		   System.out.println("[NumOfRecordsBeforeSearch] doesn't matches with the [NumOfRecordsAfterReset]");
	   } 
	     
	   //Log-Out from the application
	   Application.logOut(driver);
    
	   //Verify the User-LogOut
	   Magento_ReusableFunctions.Verify_UserLogOut(driver); 

	   //Close the browser
	   DriverObject.closeBrowser(driver);			  		
   }

 }


*/