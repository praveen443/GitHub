/*package WithoutTestNG_TestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import Actions.Magento_Login;
import Actions.Magento_ReusableFunctions;
import PageObjects.CreateNewOrder_Page;
import PageObjects.Home_Page;
import PageObjects.OrdersPage;
import Utility.Constant;

public class TC2_CreateNewOrder_ExistingCustomer 
{
	public static String string = "";
	String ReturnValue = null;
	
	public static void main(String[] args) throws InterruptedException
	{
		
		    Reporter.log("------------------ Started the TestCase: TC2_CreateNewOrder_ExistingCustomer ------------------");			
			
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
			//Magento_Login.Execute(driver,Constant.Username,Constant.Password);
			
			//Implicitly waits for 5 sec's until the webpage loads
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					
			//*Created Re-usable function to handle the Incoming Message pop-up window after the user login
			Magento_ReusableFunctions.PopUpWindow_IncomingMessage(driver);
				
			//Click on sales tab in the topmenu
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(By.xpath(".//*[@id='nav']/li[2]/a/span"));
			action.moveToElement(we).build().perform();
			
			Home_Page.TextLink_Orders(driver).click();
			//System.out.println("Click on the 'Orders' Link under the 'sales' tab in the homepage.");
			Reporter.log("Click on the 'Orders' Link under the 'Sales' tab in the homepage.");
			
			//Implicitly waits for 10 sec's until the webpage loads
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			//Click on Create New Order button
			OrdersPage.Button_CreateNewOrder(driver).click();
			Reporter.log("Click on Create New Order button");
			
			//Calling method to select the random OrderNumber from the webTable
			Magento_ReusableFunctions.selectRandomOrder(driver);
			
			//Get the entire html table and store this in a variable   
            WebElement Webtable = (WebElement) CreateNewOrder.htmlTable_CreateNewOrder(driver);	 	
            
			//Get number of rows in table 
			int numOfRows = Webtable.findElements(By.xpath("tr")).size(); 
			Reporter.log("No. of Rows in the table : "+ numOfRows);
			//System.out.println("No. of Rows in the table : "+ numOfRows);
            	
            
			//WebElement Webtable = driver.findElement(By.xpath(".//*[@id='sales_order_grid_table']/tbody"));
			List<WebElement> TotalRowCount = Webtable.findElements(By.tagName("tr"));
			for(WebElement rowData:TotalRowCount){
				string =  string + rowData.getText().trim().substring(0, 9)+";";
			}
			String[] stringArray =string.split(";");
			int countofOrders = new Random().nextInt(stringArray.length);
			//Select the random value from the array list 
			String random = (stringArray[countofOrders]);
			System.out.println("Selected orderno is : "+random);
			
			
			// FIRST CODING PART
			//Wait for 4 sec's
			Thread.sleep(5000);
			
			//Select the English(store) Radio button 
			CreateNewOrder_Page.RadioButton_English(driver).click();
			Reporter.log("Select the English(store) Radio button");

			Thread.sleep(7000);
			 
			//Used the if-condition for verifying whether the 'Add Products' button is displayed on the webpage.
	    	//If exists, Webdriver will continue with executing the test steps. Otherwise, it will log-out the user.
			if (!CreateNewOrder_Page.AddProductsButton(driver).isDisplayed()) 
			{
				Reporter.log("Add Products button is not displayed."+"TestCase_2 ended without creating the new user.");
			} 
			 else if(CreateNewOrder_Page.AddProductsButton(driver).isDisplayed())
			    {
				    
				    //Click on 'Add Products' button.
				    CreateNewOrder_Page.AddProductsButton(driver).click();
					Reporter.log("Click on Add Products button");
					
					//CODE FOR RANDOM SELECTION FROM THE TABLE
					Thread.sleep(3000);
					
					//Click on "Add Selected Products(s)" to order button
					driver.findElement(By.xpath("//button[@title='Add Selected Product(s) to Order']")).click();
					Reporter.log("Click on 'Add Selected Products(s) to order' button");
					
					Thread.sleep(6000);			
					
	     			//Calling Function for validating the Fixed-Rate radio button
					Magento_ReusableFunctions.GetShippingMethods_FixedRadioButton(driver);
					
					//Implicitly waits for 18 sec's until the webpage loads
	     			driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
					
					//Click on the Submit button
					driver.findElement(By.id("submit_order_top_button")).click();
					Reporter.log("Click on the Submit button");
					
				    //Implicitly waits for 15 sec's until the webpage loads
	     			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	     			
	     			//Thread.sleep(6000);
					Magento_ReusableFunctions.ValidMessage_afterSubmitButton(driver);				
					
					WebDriverWait wait = new WebDriverWait(driver,20);
					wait.until( ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='messages']/ul/li/ul/li/span")));
			    }
			
			//Click on LogOut text-link		
			Home_Page.TextLink_LogOut(driver).click();
			Reporter.log("Click on the Log-Out textlink");
			
			//Verifying whether the user is logged-out successfully.
			Magento_ReusableFunctions.Verify_UserLogOut(driver);
			
			//Close the browser
			driver.close();
			Reporter.log("Closed the browser");
			
			Reporter.log("---------- End of the TestCase: TC2_CreateNewOrder_ExistingCustomer ---------- "); 
	
	}
	
}


*/