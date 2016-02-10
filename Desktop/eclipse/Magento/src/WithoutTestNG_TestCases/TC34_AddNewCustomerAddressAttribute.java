/*package WithoutTestNG_TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import Actions.Application;
import Actions.DriverObject;
import Actions.Magento_ReusableFunctions;
import Actions.MouseHoverOperation;
import PageObjects.Properties;
import Utility.Constant;

public class TC34_AddNewCustomerAddressAttribute {

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
		 PageElements.ManageCustomerAddressAttributes.click();
		 Reporter.log("Click on 'Manage Customer Address Attributes'.");
		 System.out.println("Click on 'Manage Customer Address Attributes'.");
		 
		 //Verify the 'Manage Customer Address Attributes' page navigation.
		 Magento_ReusableFunctions.Verify_ManageCustomerAddressAttributesPageNavigation(driver);
		 
		 //Click on 'Add New Attribute' button
		 PageElements.Button_AddNewAttribute.click();
		 Reporter.log("Click on 'Add New Attribute' button.");
		 System.out.println("Click on 'Add New Attribute' button.");
		 
		 //Verify the 'New Customer Address Attribute' page navigation.
		 Magento_ReusableFunctions.Verify_NewCustomerAddressAttributePageNavigation(driver);
		 
		 //****** Enter the mandatry fields in  'Properties' ******
		 //Enter the 'attribute code'
		 PageElements.Input_AttributeCode.sendKeys("s"+Utility.GenericFunctions.randomAlphanumericString());
		 
		 //Enter the 'Sort Order'
		 PageElements.Input_SortOrder.sendKeys(Utility.GenericFunctions.RndZipCodeGeneartor());
		 
		 //Click on 'Manage Label / Options' under the Attribute information section.
		 driver.findElement(By.partialLinkText("Manage Label / Options")).click();
		 Reporter.log("Click on 'Manage Label / Options'.");
		 System.out.println("Click on 'Manage Label / Options'.");
		 
		 //Enter the Manage Lable/ options.
		 //Set the String value in 'Admin' field
		 PageElements.Input_Admin.sendKeys(Constant.Admin);
		 
		 //Set the string value in 'English' field
		 PageElements.Input_English.sendKeys(Constant.English);
		 
		 //Click on Save Attribute button.
		 PageElements.Button_SaveAttribute.click();
		 Reporter.log("Click on 'Save Attribute' button.");
		 System.out.println("Click on 'Save Attribute' button.");
		 
		 //Verfy the valid message after saving the attribute values.
		 Magento_ReusableFunctions.Verify_NewAddressAttributeTextMessage(driver);
		 
		 //Log-Out from the application
	     Application.logOut(driver);
	     
	     //Verify the User-LogOut
	     Magento_ReusableFunctions.Verify_UserLogOut(driver); 
	     
	     //Close the browser
		 DriverObject.closeBrowser(driver);		 
	}

}
*/