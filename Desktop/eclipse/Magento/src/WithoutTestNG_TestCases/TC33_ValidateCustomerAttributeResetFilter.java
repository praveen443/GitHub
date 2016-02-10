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

public class TC33_ValidateCustomerAttributeResetFilter {

 public static void main(String[] args) throws InterruptedException {
		
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
	 
	 //Mouse-hover on the 'Customers' Tab
	 MouseHoverOperation.mouseHoverUsingPartialLinkText("Customers", driver);
	 Reporter.log("Mouse-hover on the Customers tab in the topmenu.");
	 System.out.println("Mouse-hover on the Customers tab in the topmenu.");
	 
	 //Calling re-usable method to perform Mouse-hover operation 
	 //Mouse-hover on the 'Attributes' under 'Customers' tab.
	 MouseHoverOperation.mouseHoverUsingPartialLinkText("Attributes", driver);
	 Reporter.log("Go to 'Attributes' in the Customers section.");
	 System.out.println("Go to 'Attributes' in the Customers section.");
	 
	 //Click on 'Manage Customer Attributes' under the 'Attributes' in 'Customers' section.
	 PageElements.ManageCustomerAttributes.click();
	 Reporter.log("Click on 'Manage Customer Attributes'.");
	 System.out.println("Click on 'Manage Customer Attributes'.");
	 
	 //Verify the 'Manage Customer Attributes' page navigation.
	 Magento_ReusableFunctions.Verify_ManageCustomerAttributesPageNavigation(driver);
	 
	 //Capture total number of  records available in the 'customers attributes' table before reset
     List<WebElement> CustomersWebTable1 = driver.findElements(By.xpath(".//*[@id='customerAttributeGrid_table']/tbody/tr"));
     int NumOfRecordsBeforeReset = CustomersWebTable1.size();   //.//*[@id='customerAttributeGrid_table']/tbody/tr
     System.out.println("Total no. of records in the Customers1 Table is : "+NumOfRecordsBeforeReset);
	 
     //Select the option - 'NO' from the 'System' dropdown 
     Magento_ReusableFunctions.SelectOptionNo(driver, "No", "customerAttributeGrid_filter_is_user_defined");
     
     //Click on 'Search' button.
     PageElements.Button_Search.click();
     Reporter.log("Click on 'Search' button.");
     System.out.println("Click on 'Search' button.");
     
     //Capture total number of  records available in the 'customers attributes' table after filtering with option - 'NO'
     List<WebElement> ResultedRecords = driver.findElements(By.xpath(".//*[@id='customerAttributeGrid_table']/tbody/tr"));
     int NumOfFilteredRecords = ResultedRecords.size();  
     System.out.println("Total no. of records found with status as 'NO' is : "+NumOfFilteredRecords);
      
     //Click on Reset Filter button
     PageElements.Button_ResetFilter.click();
     System.out.println("Click on Reset Filter button");
     Reporter.log("Click on Reset Filter button");
     
     //Capture total number of  records available in the 'customers attributes' table after Reset filter
     List<WebElement> CustomersWebTable2 = driver.findElements(By.xpath(".//*[@id='customerAttributeGrid_table']/tbody/tr"));
     int NumOfRecordsAfterReset = CustomersWebTable2.size();   
     System.out.println("Total no. of records found after applying the 'Reset filter' is : "+NumOfRecordsAfterReset);
        
     //Validate the 'Reset filter' button in the 'Manage Customer Attributes' page.
     //After clicking on the reset filter button, the records should match with the customers table.
     if (NumOfRecordsBeforeReset==NumOfRecordsAfterReset) {
		Reporter.log("Reset filter is validated Successfully");
		System.out.println("Reset filter is validated Successfully");
	 } else {
		Reporter.log("<---- Validation failed to Reset the filter ---->");
		Reporter.log(" [NumOfRecordsBeforeReset] doesn't matches with the [NumOfRecordsAfterReset] ");
		System.out.println("<---- Validation failed to Reset the filter ---->");
		System.out.println("[NumOfRecordsBeforeReset] doesn't matches with the [NumOfRecordsAfterReset]");
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