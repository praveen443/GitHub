/*package WithoutTestNG_TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import Actions.Application;
import Actions.DriverObject;
import Actions.Magento_ReusableFunctions;
import PageObjects.Properties;
import Utility.Constant;

public class TC27_SearchCustomer_basedOn_CustomerName extends Application
{
   public static WebElement Element;

   public static void main(String[] args) throws InterruptedException 
   {	   		   
	    //Open the firefox browser.	
		WebDriver driver = new FirefoxDriver();
		
		 //Calling Objects From POM
  		PageFactory.initElements(driver, Properties.class);
  		Properties PageElements = new Properties(driver);

		//Maximize the nrowser window
		DriverObject.maximizeBrowserWindow(driver);
			   
		//Launch the Application URL
		Application.launchURL(driver, Constant.AppURL);
			
		//Login into the Magento Application
		//Application.login(driver, Constant.Username, Constant.Password);
			     
		//Handle the Incoming message Pop-Up window
		Magento_ReusableFunctions.HandlePopUpWindow(driver);
			     
		//Verify the user login
		Magento_ReusableFunctions.Verify_UserLogin(driver);
			  
		//Mouse-hover on the 'Customers' Tab
		Actions action = new Actions(driver);
		Element   = driver.findElement(By.partialLinkText("Customers"));
		action.moveToElement(Element).build().perform();
		Reporter.log("Mouse-hover on the Customers tab in the topmenu.");
			  
		//Click on 'Manage Customers' link.
		PageElements.TextLink_ManageCustomers.click();
		System.out.println("Click on 'Manage Customers' link.");
			  
		//Calling this method to verify the Manage Customers Page navigation.
		Magento_ReusableFunctions.Verify_ManageCustomerspageNavigation(driver);
			  
		//Enter the valid customer name in the 'Name' field
		PageElements.ValidCustomerName.sendKeys(Constant.ValidCustomerName);
		Reporter.log("Enter the valid customer name");
		System.out.println("Enter the valid customer name");
			 			  
		//Click on the Search button
		PageElements.Button_Search.click();
		Reporter.log("Click on the Search button");
		System.out.println("Click on the Search button");
			
		Thread.sleep(2000);
		
		if(driver.findElement(By.xpath(".//*[@id='customerGrid_table']/tbody")).isDisplayed())
		{
			String getDataFromWebTable = driver.findElement(By.xpath(".//*[@id='customerGrid_table']/tbody")).getText(); 		
			     
			if(getDataFromWebTable.contains("No records found.")){
			    	
			     Reporter.log("Output Message is : <------ No records found. ------>");	 
			     Reporter.log(" <----- Exit from the Test. -----> ");
		      	 System.out.println("Output Message is : <------ No records found. ------>");
	             System.out.println(" <----- Exit from the Test. -----> ");
			     
			}else{		    	 
				    //Click on 'Edit' link for the Searched Customer.
					PageElements.TextLink_Edit.click();
					Reporter.log("Click on 'Edit' text-link");
					System.out.println("Click on 'Edit' text-link");
										
					//Verify whether the user is navigated to the 'Customer Information' page.
					Magento_ReusableFunctions.Verify_ManageCustomerspageNavigation(driver);
						
					//Validate the customer details with the name.
					Magento_ReusableFunctions.ValidateCustomerName(driver);			 
			     }		 			 
		 }	
			  
		 //Log-Out from the Application.
		 Application.logOut(driver);
			  
		 //Verify the User Log-Out.
		 Magento_ReusableFunctions.Verify_UserLogOut(driver);
			  
		 //Close the browser.
	     DriverObject.closeBrowser(driver);			  
	}
   
}
*/