// ********************************************************************************************************************
//  PURPOSE     : Created this class file to maintain all the project related reusable code for all the test cases.
//  AUTHOR      : Praveen Varma.
//  Created Date: 14-08-2015.
// ********************************************************************************************************************

package Actions;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import PageObjects.Home_Page;
import PageObjects.ManageGiftWrapping_Page;
import PageObjects.NewGiftWrapping_Page;
import PageObjects.OrdersPage;
import PageObjects.Properties;
import Utility.Constant;
import Utility.GenericFunctions;

public class Application extends Actions.DriverObject
{	
	
//CODE-2: Created this method to Perform the login (Ref: All the TestCases)
//-------------------------------------------------------------------------------------------------------------------------------    
	public static void login() 
	{	
		try {
			   //Calling Objects From POM
			   PageFactory.initElements(driver, Properties.class);
			   Properties PageElements = new Properties(driver);
			   
			   //Set Username
			   PageElements.Username.sendKeys(Constant.Username);
			   System.out.println("Enter the Username as : "+Constant.Username);
			   Reporter.log("Enter the Username as : "+Constant.Username);
			   
			   //Set Password
			   PageElements.Password.sendKeys(Constant.Password);
			   System.out.println("Enter the Password as : "+Constant.Password);
			   Reporter.log("Enter the Password as : "+Constant.Password);
			   
			   //Click on Login button
			   PageElements.LoginButton.click();
			   Reporter.log("Click on Login button.");
			   System.out.println("Click on Login button.");
			
		} catch (Exception e) {
			
			  System.out.println("<----- Found exception while login into the Application ----> "+e.getMessage());
			
		}
		   
	}
     
//-------------------------------------------------------------------------------------------------------------------------------
//CODE-3: Created this method to Log-Out from the Application (Ref: All the TestCases)
//-------------------------------------------------------------------------------------------------------------------------------          
    public static void logOut(WebDriver driver)    
	{	
	       //Calling Objects From POM
		   PageFactory.initElements(driver, Properties.class);
		   Properties PageElements = new Properties(driver);
					  			
		   //Click on Login button
		   PageElements.TextLink_LogOut.click();
		   Reporter.log("Click on Log-Out text link.");
		   System.out.println("Click on Log-Out text link.");
	}
     
//-------------------------------------------------------------------------------------------------------------------------------
//CODE-4: Created this method to add the giftWrapping  (Ref : TC12_AddGiftWrapping)
//-------------------------------------------------------------------------------------------------------------------------------                    
    public static void addgiftWrapping(WebDriver driver) throws InterruptedException    
	{	
	       //Calling Objects From POM
		   PageFactory.initElements(driver, Properties.class);
		   Properties PageElements = new Properties(driver);
				  			
		   //Mouse-Hover on the Sales tab in the HomePage. 
		   MouseHoverOperation.mouseHoverUsingxpath(".//*[@id='nav']/li[2]/a/span", driver);
		   Reporter.log("Mouse-hover on the sales tab in the top menu.");
		   System.out.println("Mouse-hover on the sales tab in the top menu.");
		   
		   //Click on the Gift Wrapping link under the sales tab in the HomePage. 
		   PageElements.Textlink_GiftWrapping.click();
		   System.out.println("Click on Gift Wrapping text link under the sales tab.");
		   Reporter.log("Click on Gift Wrapping text link under the sales tab.");
		   
		   //Calling method to verify the Manage Gift Wrapping Page Navigation.
		  // Magento_ReusableFunctions.Verify_ManageGiftWrappingPageNavigation(driver);
	   
		   //Click on the "Add Gift Wrapping" button.
		   PageElements.Button_AddGiftWrapping.click();
		   System.out.println("Click on 'Add Gift Wrapping' button.");
		   Reporter.log("Click on 'Add Gift Wrapping' button.");
		   
		   //Assigned the variable to provide the random String in the Gift Wrapping design edit-box
		   String RandomString_GiftWrappingDesign = GenericFunctions.string_GiftWrappingDesign();

		   //Enter the random String in the Gift Wrapping design edit-box
		   PageElements.InputBox_GiftWrappingDesign.sendKeys(RandomString_GiftWrappingDesign);
		   
		   //Calling method to select the random value from the WebType list in the Manage Gift Wrapping Page.		   
		  // Magento_ReusableFunctions.selectWebSiteType(driver);

		   //Calling method to select the random value from the status dropdown list.
		  // Magento_ReusableFunctions.selectStatusFrom_Dropdownlist(driver);	  

		   //Assigned variable to enter the 2 digit random number in the Price field
		   String PriceValue = GenericFunctions.Random_PriceValue();       

		   //Enter the value in the Price field
		   PageElements.InputBox_Price.sendKeys(PriceValue);
		   
		   //Click on the Save button
		   PageElements.Button_Save.click();
		   Reporter.log("Click on the Save button");
		   System.out.println("Click on the Save button.");
		   
		   //Calling this method to Verify the valid text message after saving the Gift Wrapping details   
		  // Magento_ReusableFunctions.Verify_GiftWrappingTextMessage(driver, RandomString_GiftWrappingDesign);	       	
	}
  
     
//-------------------------------------------------------------------------------------------------------------------------------
//CODE-5: Created this method to Create New Customer (Ref : TC26_CreateNewCustomer)
//-------------------------------------------------------------------------------------------------------------------------------                    
   public static void createNewCustomer(WebDriver driver) throws InterruptedException    
   	{	
   	      //Calling Objects From POM
   		  PageFactory.initElements(driver, Properties.class);
   		  Properties PageElements = new Properties(driver);
   				  			
   		  //Mouse-hover on the 'Customers' Tab   		  
 		  MouseHoverOperation.mouseHoverUsingPartialLinkText("Customers", driver);
 		  Reporter.log("Mouse-hover on the Customers tab in the topmenu.");
 		  System.out.println("Mouse-hover on the Customers tab in the topmenu.");
   		  
   		  //Click on 'Manage Customers' link.
   		  PageElements.TextLink_ManageCustomers.click();
   		  System.out.println("Click on 'Manage Customers' link.");
   		  Reporter.log("Click on 'Manage Customers' link.");
   		  
   		  //Calling this method to verify the Manage Customers Page navigation.
   		  Magento_ReusableFunctions.Verify_ManageCustomerspageNavigation(driver);
   		  
   		  //Click on 'Add New Customer' button
   		  PageElements.Button_AddNewCustomer.click();
   		  System.out.println("Click on 'Add New Customer' button");
   		  Reporter.log("Click on 'Add New Customer' button");
   		
   		  //Calling this method to verify the New Customer Page navigation.
   		  Magento_ReusableFunctions.Verify_NewCustomerPageNavigation(driver);
   		  
   		  System.out.println("**** Fill the Account details ****");
   		  Reporter.log("**** Fill the Account details ****");
   		  // ****** To fill the Account Information details  ******
   		  //Select the option from the 'Associate to Website *' dropdown list
   		  Magento_ReusableFunctions.selectAssociateValue(driver);
   		  	   
   		  //Select the option from the 'Group' dropdown list 
   		  Magento_ReusableFunctions.SelectGroupValue(driver);
   		  
   	      //Enter the FirstName
   		  PageElements.InputBox_AccountFirstName.sendKeys(Constant.FirstName);
   	      System.out.println("Enter the First Name : "+Constant.FirstName);	  
   		  
   		  //Enter the LastName
   		  PageElements.InputBox_AccountLastName.sendKeys(GenericFunctions.string_LastNameGeneration());
   	  
   		  //Enter the E-mail Id
   		  PageElements.InputBox_AccountEmail.sendKeys(GenericFunctions.string_EmailID());
   		
   		  //Enter the Password
   		  PageElements.InputBox_AccountPassword.sendKeys(GenericFunctions.string_RandomPassword());
   		
   		  //Click on Save Customer button
   		  PageElements.Button_SaveCustomer.click();  		  
   		  
   		  //Verify the message after creating the new customer
   		  if(PageElements.ValidTextMessage.isDisplayed()){
   			  String getValidTextMessage = PageElements.ValidTextMessage.getText();
   			  System.out.println("Output Message is : "+getValidTextMessage); 		  
   		  }else{
   			  System.out.println("<-----  Failed to create the New Customer. ----->");
   		  }       		   		  	       	
   	}
     
//-------------------------------------------------------------------------------------------------------------------------------
//CODE-6: Created this method to Serach the Order based on the Order Number (Ref : TC4_SearchOrder_basedOnOrderNumber)
//-------------------------------------------------------------------------------------------------------------------------------                    
    public static void searchOrder_basedOnOrderNumber(WebDriver driver) throws InterruptedException    
    {	   
    	   //****** Used Page Object Model instead of Page factory to locate the elements ******
    	  
      	   //Mouse-Hover on the Sales tab in the HomePage.
      	   Home_Page.TextLink_SalesTab(driver);
      	   			
      	   //Click on Orders link under the sales tab in the topmenu
      	   Home_Page.TextLink_Orders(driver).click();
      	   			
      	   //Assigned variable to get the ramdomly selected ordernumber from the arraylist and store it in a variable
      	   String ValidOrderNumber = GenericFunctions.PickRandomOrderNumber();
      	   			
      	   //Enter the OrderNumber# in the Order# edit box
      	   OrdersPage.EditBox_OrderNumber(driver).sendKeys(ValidOrderNumber);
      	   Reporter.log("OrderNo. entered in the Order# edit box is : "+ValidOrderNumber);

      	   //Click on the Search button
      	   OrdersPage.Button_Search(driver).click();
      	   			
      	   //Calling function to verify whether user is navigated to the orders page.
      	   Magento_ReusableFunctions.OrdersPage_Verification(driver);
      	   						
      	   //Calling function to Verify whether the searched OrderNumber matches with the Resulted OrderNumber
      	   Magento_ReusableFunctions.VerifyingtheOrderNumber(driver, ValidOrderNumber);       	
   }
     
//-------------------------------------------------------------------------------------------------------------------------------
//CODE-7: Created this method to Edit the Existing Gift Wrapping design name (Ref : TC15_Edit_ExistingGiftWrapping)
//-------------------------------------------------------------------------------------------------------------------------------                    
   public static void edit_ExistingGiftWrapping(WebDriver driver)     
   {	   
          //****** Used Page Object Model instead of Page factory to locate the elements ******
        	  
	      //Mouse-Hover on the Sales tab in the HomePage.
	      Home_Page.TextLink_SalesTab(driver);
	    		  
	      //Click on the Gift Wrapping link under the sales tab in the topmenu
	      Home_Page.Textlink_GiftWrapping(driver).click();	    	    
	    		   
	      //Calling method to verify the Manage Gift Wrapping Page Navigation.
	      Magento_ReusableFunctions.Verify_ManageGiftWrappingPageNavigation(driver);
	    		    
	      //Click on any random 'edit' link from the webtable
	      ManageGiftWrapping_Page.Randomtextlink_Edit(driver).click();			 
	    		    
	      //Calling method to verify whether the user is navigated to the 'Edit gift wrapping Design' Page.
	      Magento_ReusableFunctions.Verify_EditgiftWrappingPageNavigation(driver);
	    		    
	      //Clear the text in the 'Gift Wrapping Design'  edit-box
	      NewGiftWrapping_Page.InputBox_GiftWrappingDesign(driver).clear();	   
	      Reporter.log("Cleared the existing name in the Gift wrapping design edit-box"); 
	    		  
	      //Assigned the variable to store the random String in the Gift Wrapping design edit-box
	      String RandomString_GiftWrappingDesign = GenericFunctions.string_GiftWrappingDesign();
	    		    
	      //Enter the new random String value in the Gift Wrapping design edit-box
	      NewGiftWrapping_Page.InputBox_GiftWrappingDesign(driver).sendKeys(RandomString_GiftWrappingDesign);	    
	    		    
	      //Click on the Save button
	      NewGiftWrapping_Page.Button_Save(driver).click();        
	    	      
	      //Calling this method to Verify the valid text message after saving the Gift Wrapping details   
	      Magento_ReusableFunctions.Verify_GiftWrappingTextMessage(driver, RandomString_GiftWrappingDesign);
	      Reporter.log("The gift wrapping design is created with the name  : "+RandomString_GiftWrappingDesign);       	
   }
    
//-------------------------------------------------------------------------------------------------------------------------------
//CODE-8: Created this method to Change the Status of Gift Wrapping  (Ref : TC13_ChangetheStatusOf_GiftWrapping)
//-------------------------------------------------------------------------------------------------------------------------------                    
   public static void changetheStatusOfGiftWrapping(WebDriver driver)      
   {	   
          //****** Used Page Object Model instead of Page factory to locate the elements ******
          	  
    	  //Mouse-Hover on the Sales tab in the HomePage.
    	  Home_Page.TextLink_SalesTab(driver);
    		  
    	  //Click on the Gift Wrapping link under the sales tab in the topmenu
    	  Home_Page.Textlink_GiftWrapping(driver).click();	    	    
    		   
    	  //Calling method to verify the Manage Gift Wrapping Page Navigation.
    	  Magento_ReusableFunctions.Verify_ManageGiftWrappingPageNavigation(driver);
    		    
    	  //Select the option from the Status dropdown list
    	  Magento_ReusableFunctions.selectStatusFromDropdownlist1(driver);
    		    
    	  //Click on search button available in the page.
    	  OrdersPage.Button_Search(driver).click();
    	 
    	  List<WebElement> rows = driver.findElements(By.xpath(".//*[@id='giftwrappingGrid_table']/tbody/tr"));
    	  System.out.println("Total number of records found in the Table :"+ rows.size());
    	  
    	  if (ManageGiftWrapping_Page.getEntireWebTable(driver).isDisplayed()) {
    		 
    		 String getText = ManageGiftWrapping_Page.getEntireWebTable(driver).getText();
    	      
      	     if(getText.contains("No records found."))
      	     {
      	       Reporter.log("Output Message is : <---- No records found.---->");
      	  	   Reporter.log("<-- So, unable to update the records in the WebTable. -->");
      	  	   System.out.println("Output Message is : <---- No records found.---->");
      	  	   System.out.println("<-- So, unable to update the records in the WebTable. -->"); 
      	  	   
      	     }else{ 	    	  
      	    	       	     
    		   //Click on "Select All" link available in the page.
        	   ManageGiftWrapping_Page.TextLink_SelectAll(driver).click();
        		    
        	   //Select the Option 'Change status' in the "Actions" dropdown.
        	   Magento_ReusableFunctions.selectOptionFromActionsDropdownlist(driver);
        		    
        	   //Verifying whether the status dropdown list is displayed after selecting the Change Status option.
        	   Magento_ReusableFunctions.Verify_StatusDropdownIdentification(driver);
        		    
        	   //Select Disabled/Enabled status from the dropdown.
        	   Magento_ReusableFunctions.selectStatusFromDropdownlist2(driver);
        	 	    
        	   //Click on Submit button available in the Manage Gift Wrapping page.
        	   ManageGiftWrapping_Page.Button_Submit(driver).click();	  	  
        
        	   //Verifying the total no. of records that have been updated.   
        	   Magento_ReusableFunctions.Verify_TotalNumOfUpdatedRecords(driver);
			
      	     }
    	  }    	      	       	
    } 
    
//-------------------------------------------------------------------------------------------------------------------------------
//CODE-9: Created this method to delete the existing Gift Wrapping Design (Ref : TC14_Delete_SelectedGiftWrappingDesign)
//-------------------------------------------------------------------------------------------------------------------------------                    
    public static void deleteTheExistingGiftWrappingDesign(WebDriver driver) throws InterruptedException      
    {	   
          //****** Used Page Object Model instead of Page factory to locate the elements ******
            	  
    	  //Mouse-Hover on the Sales tab in the HomePage.
    	  Home_Page.TextLink_SalesTab(driver);
    	  
    	  //Click on the Gift Wrapping link under the sales tab in the topmenu
    	  Home_Page.Textlink_GiftWrapping(driver).click();	    	    
    	   
    	  //Calling method to verify the Manage Gift Wrapping Page Navigation.
    	  Magento_ReusableFunctions.Verify_ManageGiftWrappingPageNavigation(driver);
    	    
    	  //Select Disabled/Enabled status from the dropdown.
    	  Magento_ReusableFunctions.selectStatusFromDropdownlist1(driver);

    	  //Click on search button available in the page.
    	  OrdersPage.Button_Search(driver).click();
    	  
    	  //Get the rows count from the table   	  
		  List<WebElement> rows =  driver.findElements(By.xpath(".//*[@id='giftwrappingGrid_table']/tbody/tr"));
    	  System.out.println("Total number of records found in the Table :"+ rows.size());
    	  
    	  //Delete the records based on the status selection
    	  if(ManageGiftWrapping_Page.getEntireWebTable(driver).isDisplayed())
    	  {
    	    String getText = ManageGiftWrapping_Page.getEntireWebTable(driver).getText();
    	      
    	    if(getText.contains("No records found."))
    	    {
    	       Reporter.log("Output Message is : <---- No records found.---->");
    	  	   Reporter.log("<------------ Exit from the Test. ------------>");
    	  	   System.out.println("Output Message is : <--- No records found. --->");
    	  	   System.out.println("<------------ Exit from the Test. ------------>"); 
    	  	   
    	     }else{ 	    	  
    	    	
    	       //Click on "Select All" link available in the page.
    		   ManageGiftWrapping_Page.TextLink_SelectAll(driver).click();
    		  	   
    		   //Select the option 'delete' from the Actions dropdown list.
    		   Magento_ReusableFunctions.selectOptionDeleteFromActionsDropdown(driver, "Delete", "giftwrappingGrid_massaction-select");
    		  	    
    		   //Click on Submit button available in the Manage Gift Wrapping page.		  						    
    		   ManageGiftWrapping_Page.Button_Submit(driver).click();
    		   System.out.println("Click on the submit button");
    			  	
    		   //Calling method to verify the existance of alert message and to handle it.
    		   AlertHandling.checkAlert(driver);  	
    		   
    		   //Get the valid text message after deleting the records.
    		   String getNumOfDeletedRecords = ManageGiftWrapping_Page.getValidText(driver).getText();
    		   System.out.println("OutPut Message is : "+getNumOfDeletedRecords);
    		   Reporter.log("OutPut Message is : "+getNumOfDeletedRecords);
    		   
    	     }
    	  }     		       	     	       	
    } 
  
//-------------------------------------------------------------------------------------------------------------------------------
//CODE-10: Created this method to Search the customer based on the customer name (Ref : TC27_SearchCustomer_basedOn_CustomerName)
//-------------------------------------------------------------------------------------------------------------------------------                         
    public static void searchCustomerbasedOnCustomerName(WebDriver driver) throws InterruptedException      
    {	   	 
        //Calling Objects From POM
   		PageFactory.initElements(driver, Properties.class);
   		Properties PageElements = new Properties(driver);
   					  
   	    //Mouse-hover on the 'Customers' Tab	 	 
	 	MouseHoverOperation.mouseHoverUsingPartialLinkText("Customers", driver);
	 	Reporter.log("Mouse-hover on the Customers tab in the topmenu.");
	 	System.out.println("Mouse-hover on the Customers tab in the topmenu.");
   					  
   		//Click on 'Manage Customers' link.
   		PageElements.TextLink_ManageCustomers.click();
   		Reporter.log("Click on 'Manage Customers' link.");
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
   		
   		//Verify the customer with the given name
   		if(PageElements.htmlWebTable.isDisplayed())
   		{
   			String getDataFromWebTable = PageElements.htmlWebTable.getText();
   			
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
   				Magento_ReusableFunctions.Verify_CustomerInformationPageNavigation(driver);		
   								
   			    //Validate the customer details with the name.
   				Magento_ReusableFunctions.ValidateCustomerName(driver);
   			}
   		}
   }
     
//-------------------------------------------------------------------------------------------------------------------------------
//CODE-11: Created this method to Edit the customer based on the customer name (Ref : TC28_EditCustomer_basedOn_CustomerName)
//-------------------------------------------------------------------------------------------------------------------------------                         

   public static void editCustomerbasedOnCustomerName(WebDriver driver) throws InterruptedException      
   {
     
		//Calling Objects From POM
		PageFactory.initElements(driver, Properties.class);
	 	Properties PageElements = new Properties(driver);
		
	 	//Mouse-hover on the 'Customers' Tab	 	 
	 	MouseHoverOperation.mouseHoverUsingPartialLinkText("Customers", driver);
	 	Reporter.log("Mouse-hover on the Customers tab in the topmenu.");
	 	System.out.println("Mouse-hover on the Customers tab in the topmenu.");
	 		  
	 	//Click on 'Manage Customers' link.
	 	PageElements.TextLink_ManageCustomers.click();
	 	System.out.println("Click on 'Manage Customers' link.");
	 		  
	 	//Calling this method to verify the Manage Customers Page navigation.
	 	Magento_ReusableFunctions.Verify_ManageCustomerspageNavigation(driver);
	 		  
	 	//Enter the valid customer name in the 'Name' field
	 	PageElements.ValidCustomerName.sendKeys(Constant.ValidCustomerName);
	 	Reporter.log("Enter the valid customer name");
	 	System.out.println("Enter the valid customer name");
	 		  
	 	Thread.sleep(2000);
	 		  
	 	//Click on the Search button
	 	PageElements.Button_Search.click();
	 	Reporter.log("Click on the Search button");
	 	System.out.println("Click on the Search button");
	 		  
	 	Thread.sleep(2000);
	 	
	 	//To locate the webtable in the 'manage customers' page.
	 	if(PageElements.htmlWebTable.isDisplayed())
	 	{
	 		String getDataFromWebTable = PageElements.htmlWebTable.getText(); 		
	 		     
	 		if (getDataFromWebTable.contains("No records found.")){
	 		    	
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
	 			Magento_ReusableFunctions.Verify_CustomerInformationPageNavigation(driver);
	 					
	 			//Validate the customer details with the name.
	 			Magento_ReusableFunctions.ValidateCustomerName(driver);	
	 			
	 			//Click on the 'Account Information' tab in the left-pane.
	 			PageElements.Tab_AccountInformation.click();
	 			System.out.println("Click on 'Account Information' tab in the left-pane.");
	 			Reporter.log("Click on 'Account Information' tab in the left-pane.");
	 			
	 			//Calling method to select the option 'VIP Member' in the group dropdown list.
	 			Magento_ReusableFunctions.SelectOptionVIPMember(driver);
	 			
	 			//Click on the 'Save Customer' button.
	 			PageElements.Button_SaveCustomer.click();
	 			System.out.println("Click on 'Save Customer' button.");
	 			Reporter.log("Click on 'Save Customer' button.");
	 			
	 			//get the valid text message after modifying the customer details
	 			String getValidTextMessage = PageElements.ValidTextMessage.getText();
	 			
	 			//Verify the valid message 
	 			if(getValidTextMessage.contains("The customer has been saved."))
	 			{
	 			  Reporter.log("Output Message is : " +getValidTextMessage);
	 			  System.out.println("Output Message is : " +getValidTextMessage);
	 			  
	 			}else{			
	 			  Reporter.log("<---- Failed to save the modified details for the customer : " + Constant.ValidCustomerName +" ---->");
	 		      System.out.println("<---- Failed to save the modified details for the customer : " + Constant.ValidCustomerName +" ---->");
	 		      
	 			}
	 		 }		 			 
	 	 }	
   }
   
//-------------------------------------------------------------------------------------------------------------------------------
//CODE-12: Created this method to Delete the customer based on the customer name (Ref : TC29_DeleteCustomer_basedOnCustomerName)
//-------------------------------------------------------------------------------------------------------------------------------                         

   public static void deleteCustomerbasedOnCustomerName(WebDriver driver) throws InterruptedException      
   {
     
		//Calling Objects From POM
		PageFactory.initElements(driver, Properties.class);
	 	Properties PageElements = new Properties(driver);
		
	    //Mouse-hover on the 'Customers' Tab	 	 
		MouseHoverOperation.mouseHoverUsingPartialLinkText("Customers", driver);
		Reporter.log("Mouse-hover on the Customers tab in the topmenu.");
		System.out.println("Mouse-hover on the Customers tab in the topmenu.");
	 		  
	 	//Click on 'Manage Customers' link.
	 	PageElements.TextLink_ManageCustomers.click();
	 	System.out.println("Click on 'Manage Customers' link.");
	 		  
	 	//Calling this method to verify the Manage Customers Page navigation.
	 	Magento_ReusableFunctions.Verify_ManageCustomerspageNavigation(driver);
	 		  
	 	//Enter the valid customer name in the 'Name' field
	 	PageElements.ValidCustomerName.sendKeys(Constant.ValidCustomerName);
	 	Reporter.log("Enter the valid customer name");
	 	System.out.println("Enter the valid customer name");
	 		  
	 	Thread.sleep(2000);
	 		  
	 	//Click on the Search button
	 	PageElements.Button_Search.click();
	 	Reporter.log("Click on the Search button");
	 	System.out.println("Click on the Search button");
	 		  
	 	Thread.sleep(2000);
	 	
	 	//To locate the webtable in the 'manage customers' page.
	 	if(PageElements.htmlWebTable.isDisplayed())
	 	{
	 		String getDataFromWebTable = PageElements.htmlWebTable.getText(); 		
	 		     
	 		if (getDataFromWebTable.contains("No records found.")){
	 		    	
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
	 			Magento_ReusableFunctions.Verify_CustomerInformationPageNavigation(driver);
	 					
	 			//Validate the customer details with the name.
	 			Magento_ReusableFunctions.ValidateCustomerName(driver);	
	 			
	 			//Click on 'Delete Customer' button from the customer information page.
	 			PageElements.Button_DeleteCustomer.click();
	 			System.out.println("Click on 'Delete Customer' button.");
	 			Reporter.log("Click on 'Delete Customer' button.");
	 			  
	 			//Calling method to accept the confirmation alert message
	 			AlertHandling.checkAlert(driver);
	 			
	 			//get the valid text message after deleting the customer 
	 			String getValidTextMessage = PageElements.ValidTextMessage.getText();
	 				
	 			//Verify the valid message 
	 			if(getValidTextMessage.contains("The customer has been deleted."))
	 			{
	 			  Reporter.log("Output Message is : " +getValidTextMessage);
	 			  System.out.println("Output Message is : " +getValidTextMessage);		  	  
	 			}else{		  
	 			  Reporter.log("<---- Failed to delete the customer : " + Constant.ValidCustomerName +" ---->");
	 			  System.out.println("<---- Failed to delete the customer : " + Constant.ValidCustomerName +" ---->");	      		
	 			}
	 		 }		 			 
	 	 }	
    }


//-------------------------------------------------------------------------------------------------------------------------------
//CODE-13: Created this method to Add New Customer Attribute (Ref : TC32_AddNewCustomerAttribute)
//-------------------------------------------------------------------------------------------------------------------------------                         

    public static void addNewCustomerAttribute(WebDriver driver) throws InterruptedException      
    {      
    	//Calling Objects From POM
		 PageFactory.initElements(driver, Properties.class);
		 Properties PageElements = new Properties(driver);
			   			  
		 //Mouse-hover on the 'Customers' Tab
		 MouseHoverOperation.mouseHoverUsingPartialLinkText("Customers", driver);
		 Reporter.log("Mouse-hover on the 'Customers' tab in the topmenu.");
		 System.out.println("Mouse-hover on the 'Customers' tab in the topmenu.");
		 
		 //Calling re-usable method to perform Mouse-hover operation 
		 //Mouse-hover on the 'Attributes' under 'Customers' tab.
		 MouseHoverOperation.mouseHoverUsingPartialLinkText("Attributes", driver);
		 Reporter.log("Go to 'Attributes' in the Customers section.");
		 System.out.println("Go to 'Attributes' in the Customers section.");
		 
		 //Click on 'Manage Customer Address Attributes' under the 'Attributes' in 'Customers' section.
		 PageElements.ManageCustomerAttributes.click();
		 Reporter.log("/Click on 'Manage Customer Attributes'.");
		 System.out.println("/Click on 'Manage Customer Attributes'.");
		 
		 //Verify the 'Manage Customer Attributes' page navigation.
		 Magento_ReusableFunctions.Verify_ManageCustomerAttributesPageNavigation(driver);
		 
		 //Click on 'Add New Attribute' button
		 PageElements.Button_AddNewAttribute.click();
		 Reporter.log("Click on 'Add New Attribute' button.");
		 System.out.println("Click on 'Add New Attribute' button.");
		 
		 //Verify the 'New Customer Attribute' page navigation.
		 Magento_ReusableFunctions.Verify_NewCustomerAttributePageNavigation(driver);
		 
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
		 Magento_ReusableFunctions.Verify_NewAttributeTextMessage(driver);
   }

//-------------------------------------------------------------------------------------------------------------------------------
//CODE-14: Created this method to Add New Customer Address Attribute (Ref : TC34_AddNewCustomerAddressAttribute)
//-------------------------------------------------------------------------------------------------------------------------------                         
    
   public static void addNewCustomerAddressAttribute(WebDriver driver) throws InterruptedException      
   {      
    	 //Calling Objects From POM
		 PageFactory.initElements(driver, Properties.class);
		 Properties PageElements = new Properties(driver);
			   			  
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
  }
 
//-------------------------------------------------------------------------------------------------------------------------------
//CODE-15: Created this method to Validate The Customer Attribute Reset Filter (Ref : TC33_ValidateCustomerAttributeResetFilter)
//-------------------------------------------------------------------------------------------------------------------------------                         
      
  public static void validateTheCustomerAttributeResetFilter(WebDriver driver) throws InterruptedException      
  {      
      	 //Calling Objects From POM
  		 PageFactory.initElements(driver, Properties.class);
  		 Properties PageElements = new Properties(driver);
  		
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
  	     System.out.println("Total no. of records found in the Customers table 'before reset'  is  : "+NumOfRecordsBeforeReset);
  		 
  	     //Calling the re-usable method to Select the option - 'NO' from the 'System' dropdown 
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
  	     System.out.println("Total no. of records found in the Customers table 'after reset'  is : "+NumOfRecordsAfterReset);
  	        
  	     //Validate the reset filter button
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
  }
      
//-------------------------------------------------------------------------------------------------------------------------------
//CODE-15: Created this method to Validate The Customer Address Attribute Reset Filter (Ref : TC33_ValidateCustomerAddressAttributeResetFilter)
//-------------------------------------------------------------------------------------------------------------------------------                         
       
  public static void validateTheCustomerAddressAttributeResetFilter(WebDriver driver) throws InterruptedException      
  {      
       	//Calling Objects From POM
   		PageFactory.initElements(driver, Properties.class);
   		Properties PageElements = new Properties(driver);
   		
   		//Mouse-hover on the 'Customers' Tab
		MouseHoverOperation.mouseHoverUsingPartialLinkText("Customers", driver);
		Reporter.log("Mouse-hover on the Customers tab in the topmenu.");
		System.out.println("Mouse-hover on the Customers tab in the topmenu.");
		 
		//Calling re-usable method to perform Mouse-hover operation 
		//Mouse-hover on the 'Attributes' under 'Customers' tab.
		MouseHoverOperation.mouseHoverUsingPartialLinkText("Attributes", driver);
		Reporter.log("Go to 'Attributes' in the Customers section.");
		System.out.println("Go to 'Attributes' in the Customers section.");
		 
		//Click on 'Manage Customer Address Attributes' under the 'Attributes' in 'Customers' section.
		PageElements.ManageCustomerAddressAttributes.click();
		Reporter.log("Click on 'Manage Customer Address Attributes'.");
		System.out.println("Click on 'Manage Customer Address Attributes'.");
		 
		//Verify the 'Manage Customer Address Attributes' page navigation.
		Magento_ReusableFunctions.Verify_ManageCustomerAddressAttributesPageNavigation(driver);
		 
		//Capture total number of  records available in the 'customers address attributes' table before reset
	    List<WebElement> CustomersWebTable1 = driver.findElements(By.xpath(".//*[@id='customerAddressAttributeGrid_table']/tbody/tr"));
	    int NumOfRecordsBeforeReset = CustomersWebTable1.size();   //.//*[@id='customerAttributeGrid_table']/tbody/tr
	    System.out.println("Total no. of records found in the Customers address table 'before reset' is : "+NumOfRecordsBeforeReset);
		 
	    //Calling Re-usable method to Select the option - 'NO' from the 'System' dropdown 
	    Magento_ReusableFunctions.SelectOptionNo(driver, "No", "customerAddressAttributeGrid_filter_is_user_defined");
	     
	    //Click on 'Search' button.
	    PageElements.Button_Search.click();
	    Reporter.log("Click on 'Search' button.");
	    System.out.println("Click on 'Search' button.");
	    
	    //Capture total number of  records available in the 'customers attributes' table after filtering with option - 'NO'
	    List<WebElement> ResultedRecords = driver.findElements(By.xpath(".//*[@id='customerAddressAttributeGrid_table']/tbody/tr"));
	    int NumOfFilteredRecords = ResultedRecords.size();  
	    System.out.println("Total no. of records found with status as 'NO' is : "+NumOfFilteredRecords);
 	      
 	    //Click on Reset Filter button
 	    PageElements.Button_ResetFilter.click();
 	    System.out.println("Click on Reset Filter button");
 	    Reporter.log("Click on Reset Filter button");
 	     
 	    //Capture total number of  records available in the 'customers address attributes' table after Reset filter
 	    List<WebElement> CustomersWebTable2 = driver.findElements(By.xpath(".//*[@id='customerAddressAttributeGrid_table']/tbody/tr"));
 	    int NumOfRecordsAfterReset = CustomersWebTable2.size();   
 	    System.out.println("Total no. of records found in the Customers address table 'after reset'  is : "+NumOfRecordsAfterReset);
 	        
 	    //Validate the 'Reset filter' button in the 'Manage Customer Address Attributes' page.
 	    //After clicking on the reset filter button, the records should match with the customers table.
 	    if (NumOfRecordsBeforeReset==NumOfRecordsAfterReset) {
 			Reporter.log("'Reset filter' button is validated Successfully");
 			System.out.println("'Reset filter' button is validated Successfully");
 		} else {
 			Reporter.log("<---- Validation failed to Reset the filter ---->");
 			Reporter.log(" [NumOfRecordsBeforeReset] doesn't matches with the [NumOfRecordsAfterReset] ");
 			System.out.println("<---- Validation failed to Reset the filter ---->");
 			System.out.println("[NumOfRecordsBeforeReset] doesn't matches with the [NumOfRecordsAfterReset]");
 		}  
   }

//-------------------------------------------------------------------------------------------------------------------------------
//CODE-16: Created this method to Validate The Customer Address Attribute Reset Filter (Ref : TC30_ValidateCustomerResetFilter)
//-------------------------------------------------------------------------------------------------------------------------------                         
       
   public static void validateTheCustomerResetFilter(WebDriver driver) throws InterruptedException      
   {    
	   //Calling Objects From POM
	   PageFactory.initElements(driver, Properties.class);
	   Properties PageElements = new Properties(driver);
 		
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
   }  

//-------------------------------------------------------------------------------------------------------------------------------
     
 }
    
