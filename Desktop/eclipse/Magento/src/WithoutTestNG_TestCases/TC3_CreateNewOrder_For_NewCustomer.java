/*package WithoutTestNG_TestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import Actions.Magento_Login;
import Actions.Magento_ReusableFunctions;
import PageObjects.Home_Page;
import PageObjects.OrdersPage;
import Utility.Constant;
import Utility.GenericFunctions;

public class TC3_CreateNewOrder_For_NewCustomer 
{

	public static void main(String[] args) throws InterruptedException 
	{
	
	try {
					
			Reporter.log(" ------------ Started the TestCase : TC2_CreateNewOrder_NewCustomer ------------ ");
			
			//Creates a new instance of a webdriver
			FirefoxDriver driver = new FirefoxDriver();
			Reporter.log("Opened the firefox browser");	
			
			//Maximize the browser window
			driver.manage().window().maximize();
			Reporter.log("Maximized the browser window");
			
			//Launch the Application URL
			driver.get(Constant.AppURL);
			Reporter.log("Application URL is launched successfully");
		    
			//Waits for 10 sec's until the webpage loads
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
	        //Login into the website by Passing the Constant Variables as arguments to Execute method
		//	Magento_Login.Execute(driver,Constant.Username,Constant.Password);
			Reporter.log("User Login is successfull.");
			
			//Implicitly waits for 5 sec's until the webpage loads
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					
			//*Created Re-usable function to handle the Incoming Message pop-up window after the user login
			Magento_ReusableFunctions.PopUpWindow_IncomingMessage(driver);
				
			//Click on sales tab in the topmenu
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(By.xpath(".//*[@id='nav']/li[2]/a/span"));
			action.moveToElement(we).build().perform();
			
			Home_Page.TextLink_Orders(driver).click();
			Reporter.log("Click on the 'Orders' Link under the 'sales' tab in the homepage.");
			
			//Implicitly waits for 10 sec's until the webpage loads
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			//Click on Create New Order button
			OrdersPage.Button_CreateNewOrder(driver).click();
			Reporter.log("Click on Create New Order button");		
			
			//Waits for 10 sec's until the webpage loads
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			//Click on Create New Customer button
			driver.findElement(By.xpath("//button[@title='Create New Customer']")).click();
			Reporter.log("Click on Create New Customer button.");
			
			//Select the English(store) Radio button 
			driver.findElement(By.id("store_1")).click();
			Reporter.log("Select the English(store) Radio button.");

			//Re-Usable function for selecting the random value from the Group value list/dropdown
			Magento_ReusableFunctions.RandomGroupValueDropdownOptionSelection(driver);
			
			//Re-Usable function for selecting the random value from the Country list/dropdown
			Magento_ReusableFunctions.RandomCountryDropdownOptionSelection(driver);
			
			//Assigned the variable(SelectedCity) for passing the value from the method return type.
			//Also,Used generic function for selecting the random city values from the arraylist
			String SelectedCity= GenericFunctions.RandomCitySelection_FromArrayList();
			
			//Enter the City value
			driver.findElement(By.id("order-billing_address_city")).sendKeys(SelectedCity);
		
			//Assigned the variable for(Lastname) for passing the value from the method return type.
			//Also,Used generic function for providing the random name
			String Lastname = GenericFunctions.string_LastNameGeneration();
			Reporter.log("Entered the Last name : "+Lastname);
			
		    //Enter the LastName
			driver.findElement(By.id("order-billing_address_lastname")).sendKeys(Lastname);
			//System.out.println("=================");
			
			//Assigning the variable for passing the value of Randomly generated Telephone number 
			String TelephoneNumber = GenericFunctions.RndPhoneNumGenerator();
			
			//Enter the TelephoneNumber for creating a new customer
			driver.findElement(By.id("order-billing_address_telephone")).sendKeys(TelephoneNumber);
			
			//Assigning the variable for passing the value of randomly generated poincode number
			String PostalCodeNumber = GenericFunctions.RndZipCodeGeneartor();
			
			//Enter the Zip/PostalCode number
			driver.findElement(By.id("order-billing_address_postcode")).sendKeys(PostalCodeNumber);
			
			//Declared the variable for providing the First name
			String FirstName = Constant.FirstName;
			
			//Enter the First name
			driver.findElement(By.id("order-billing_address_firstname")).sendKeys(FirstName);
			
			//Declared the variable for providing the Street address
			String StreetAddress = Constant.StreetAddress;
			
			//Enter the Street address
			driver.findElement(By.id("order-billing_address_street0")).sendKeys(StreetAddress); 
	    	
			//Applied the wait for 3 sec's to identify the 'Add Products' web-button
			//Otherwise, the script will thorw an error as webelement unidentified.
	    	Thread.sleep(5000);
	    	
	    	//Used the if-condition for verifying whether the 'Add Products' button is displayed on the webpage.
	    	//If exists, Webdriver will continue with executing the test steps. Otherwise, it will log-out the user.
			if (!driver.findElement(By.xpath(".//*[@id='order-items']/div/div/div[1]/div/button[2]")).isDisplayed()) 
			{
				Reporter.log("Add Products button is not displayed."
						     + "TestCase_2 ended without creating the new user.");
				
				System.out.println("Add Products button is not displayed.");
				System.out.println("Due to this,webdriver is not able to perform the next steps in the scenario.");
				System.out.println("TestCase_2 ended without creating the new user.");			
			} 
			 else if(driver.findElement(By.xpath(".//*[@id='order-items']/div/div/div[1]/div/button[2]")).isDisplayed())
			    {
				    
				    //Click on 'Add Products' button.
					driver.findElement(By.xpath(".//*[@id='order-items']/div/div/div[1]/div/button[2]")).click();
					Reporter.log("Click on Add Products button");
					System.out.println("Click on Add Products button");
					
					//CODE FOR RANDOM SELECTION FROM THE TABLE
					Thread.sleep(3000);
					
					//Click on "Add Selected Products(s)" to order button
					driver.findElement(By.xpath("//button[@title='Add Selected Product(s) to Order']")).click();
					Reporter.log("Click on 'Add Selected Products(s) to order' button");
					System.out.println("Click on 'Add Selected Products(s) to order' button");		
					
					Thread.sleep(5000);
					
					//Click on the check-box for the billing address to be same as shipping address
					//Verifying whether the check box is checked for the shipping address 
					if (!driver.findElement(By.id("order-shipping_same_as_billing")).isSelected()) 
					{
						driver.findElement(By.id("order-shipping_same_as_billing")).click();
						Reporter.log("Check-Box checked for 'shipping address is same as billing address'");
						System.out.println("Check-Box checked for 'shipping address is same as billing address'");
					}
					
					// Waits for 6 sec's  to load the webpage elements
					Thread.sleep(6000);
					
					//div[@id='advice-required-entry-order[has_shipping]']				
					//Click on get shipping methods and rates
					driver.findElement(By.partialLinkText("Get shipping methods and rates")).click();
					Reporter.log(" Click on the Get Shipping methods and rates");
					System.out.println("Click on the Get Shipping methods and rates");
					
					// Waits for 6 sec's  to load the webpage elements
					Thread.sleep(6000);
					
	     			//Function for validating the Fixed-Rate radio button
					//Magento_ReusableFunctions.GetShippingMethods_FixedRadioButton(driver);
					//Click on get shipping methods and rates
					driver.findElement(By.xpath(".//*[@id='order-shipping-method-summary']/a")).click();
					Reporter.log("Click on the Get Shipping methods and rates");			   
					
				    //Verifying whether the FixedRate radio button exists after clicking on getting shipping methods			
					if(driver.findElement(By.xpath("//input[@id='s_method_flatrate_flatrate']")).isDisplayed()) 
			 		 {								  			
			 			driver.findElement(By.xpath("//input[@id='s_method_flatrate_flatrate']")).click();
			 			Reporter.log("Click on the fixed Rate Radio button");
			 			
			 		 } else if(!driver.findElement(By.xpath("//input[@id='s_method_flatrate_flatrate']")).isDisplayed())
				        {
				  	    	Reporter.log("No quotes are available for this order at this time");
				        } 
					
					//Click on the Submit button
					driver.findElement(By.id("submit_order_top_button")).click();
					Reporter.log("STEP-23 : Click on the Submit button");
					System.out.println("Click on the Submit button");
					
				    //Implicitly waits for 5 sec's until the webpage loads
	     			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	     	
	     			//Created Re-usable function for validating the message after submitting the order
					Magento_ReusableFunctions.ValidMessage_afterSubmitButton(driver);
		
			    }
			
			//Click on LogOut text-link		
			Home_Page.TextLink_LogOut(driver).click();
			Reporter.log("STEP-25 : Click on the LogOut text-link");
			
			//Verifying whether the user is logged-out successfully.
		    Magento_ReusableFunctions.Verify_UserLogOut(driver);
			
			//Close the browser
			driver.close();
			Reporter.log("Closed the browser");
			System.out.println("Closed the browser");
			
			} 
			catch (Exception e)
			{
				System.out.println(e);
			}
			
			Reporter.log("---------- End of the TestCase: TC3_CreateNewOrder_NewCustomer ---------- "); 
			System.out.println("---------- End of the TestCase: TC3_CreateNewOrder_NewCustomer ----------");			
		
	}


		
}
*/