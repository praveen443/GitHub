// ********************************************************************************************************************
//  ---------------------------------  RE-USABLE FUNCTIONS  -----------------------------------------------------------
// ********************************************************************************************************************

package Actions;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import PageObjects.EditgiftWrappingDesign_Page;
import PageObjects.ManageGiftWrapping_Page;
import PageObjects.NewGiftWrapping_Page;
import PageObjects.Properties;
import PageObjects.OrdersPage;
import Utility.Constant;

public class Magento_ReusableFunctions extends DriverObject
{ 	
   
	public static String ValidOrderNumber;  //Assigned variable for passing the OrderNumber as an string argument.(Ref: TC4)
	public static String RandomString_GiftWrappingDesign; //Assigned variable to get the output string(Ref: TC12).
       
//**********************************************************************************************************************  
//Method-1   : PopUpWindow_IncomingMessage (Ref:- In all The TestCases after the Login Step).
//PURPOSE    : Created this method to handle the Incoming Message pop-up window after the user login.
//Author     : Praveen varma.
//CreatedDate: 17-08-2015.
//**********************************************************************************************************************
 
    public static void HandlePopUpWindow(WebDriver driver)
    {
    	 //Calling Objects From POM
		 PageFactory.initElements(driver, Properties.class);
		 Properties PageElements = new Properties(driver);
	
		 //Initialize the pageElements
		 //Properties PageElements = super.pageFactory(driver);
		    
    	 //If Incoming Message pop-up window is displayed
	     if(PageElements.POPWindow.isDisplayed()){
	    	 
	    	 System.out.println("POP-UP message is displayed.");
	    	 Reporter.log("POP-UP window is displayed.");
	    	 
	    	 //Close the pop-Up window
	    	 PageElements.ClosePOPWindow.click();
	    	 Reporter.log("Close the POP-UP window");
	    	 
	     }else {
	    	 
	    	 System.out.println("<---- POP-UP Message is not displayed. ---->");
	    	 Reporter.log("<---- POP-UP Message is not displayed. ---->");
	     }
    }
   
//*************************************************************************************************************************
//METHOD-2   : RandomGroupValueDropdownOptionSelection  (Ref:- TC3)
//PURPOSE    : Created FUNCTION/METHOD for Selecting the RANDOM VALUE FROM THE DROPDOWN LIST(Group Value)
//AUTHOR     : Praveen varma.
//CreatedDate: 17-08-2015.
//*************************************************************************************************************************
   
    public static void RandomGroupValueDropdownOptionSelection(WebDriver driver)
    {	       
    	
		    //Creating the object for the dropdownList.  
		    WebElement dropdownElementId = driver.findElement(By.id("group_id"));
		    
		    //Using select class to fetch the count 
			Select dropdown = new Select(dropdownElementId);
			
			//To get the dropdown options 
			List<WebElement> dropdownList = dropdown.getOptions();
			
			//To Count the no. of items in the list
			int dropdownOptionsCount = dropdownList.size();
			
			//Using Random class to generate random values
			Random randomvalue = new Random();
			
			int randomOptionSelect = randomvalue.nextInt(dropdownOptionsCount);
			
			//Selecting value from the DropDownList
		    dropdown.selectByIndex(randomOptionSelect);

		    //To display the random option selected from the dropdown list 
		    Reporter.log("Option selected from the dropdown field 'GROUP VALUE' is : "+dropdownElementId.getAttribute("value"));

    }
     
//*************************************************************************************************************************
// End of Method-2
//*************************************************************************************************************************
   
//*************************************************************************************************************************
//METHOD-3   : RandomCountryDropdownOptionSelection (Ref:- TC3)
//PURPOSE    : Created this METHOD for selecting the random value from the Country dropdown list
//AUTHOR     : Praveen varma.
//CreatedDate: 18-08-2015.  
//*************************************************************************************************************************
    
     public static void RandomCountryDropdownOptionSelection(WebDriver driver)
     {		
		//Creating the object for the dropdownList.  
	    WebElement dropdownElementId1 = driver.findElement(By.id("order-billing_address_country_id"));
	    
	    //Using select class to fetch the count 
		Select dropdown1 = new Select(dropdownElementId1);
		
		//To get the dropdown options 
		List<WebElement> dropdownList1 = dropdown1.getOptions();
		
		//To Count the no. of items in the list
		int dropdownOptions_Count = dropdownList1.size();
		
		//Using Random class to generate random values
		Random randomvalue = new Random();
		
		int randomOptionSelection = randomvalue.nextInt(dropdownOptions_Count);
		
		//Selecting value from the DropDownList
	    dropdown1.selectByIndex(randomOptionSelection);

	    //To display the random option selected from the dropdown list 
	    Reporter.log("Option selected from the dropdown field 'Country' is : "+dropdownElementId1.getAttribute("value"));

    }
     
//*************************************************************************************************************************
// End of Method-3
//*************************************************************************************************************************   
 
     
//*************************************************************************************************************************
//METHOD-4   : ValidMessage_afterSubmitButton (Refer: TC2 & TC3 Steps)
//PURPOSE    : Created this METHOD for validating the message after submitting the order. 
//AUTHOR     : Praveen varma.
//CreatedDate: 19-08-2015. 
//*************************************************************************************************************************
    
     public static void ValidMessage_afterSubmitButton(WebDriver driver)
     {	
    	
        //Verifying whether the user has created the order successfully
    	if(driver.findElement(By.xpath(".//*[@id='messages']/ul/li")).isDisplayed())  //li[@class='success-msg']/span[text()='The order has been created.']
		{
			String ValidTextMessage_AfterSubmittingOrder = driver.findElement(By.xpath("//div[2]/div/div[1]/ul/li/ul/li/span")).getText();
			Reporter.log("Message displayed after submitting Order is :" + ValidTextMessage_AfterSubmittingOrder );
			
			if(driver.findElement(By.xpath("//h3[@class='icon-head head-sales-order']")).isDisplayed()){
				String getOrderNumberText = driver.findElement(By.xpath("//h3[@class='icon-head head-sales-order']")).getText();
				Reporter.log("Created Order No. is : "+getOrderNumberText);		
			}			
		 }else if(driver.findElement(By.xpath("html/body/div[2]/div[3]/div/form/div[2]/ul/li/ul/li/span")).isDisplayed()) {
			  String InvalidMessage_AfterSubmittingOrder = driver.findElement(By.xpath("html/body/div[2]/div[3]/div/form/div[2]/ul/li/ul/li/span")).getText();
			  Reporter.log("Message displayed after submitting Order is : "+InvalidMessage_AfterSubmittingOrder);			 
		   
		 }else if(driver.findElement(By.xpath("html/body/div[2]/div[3]/div/form/div[2]/ul/li")).isDisplayed()) {
			  String ErrorMessage_AfterSubmittingOrder = driver.findElement(By.xpath("html/body/div[2]/div[3]/div/form/div[2]/ul/li")).getText();
			  Reporter.log("Error Message is displayed as : "+ErrorMessage_AfterSubmittingOrder);			   
	     }  	
     }
 
//*************************************************************************************************************************
// End of Method-4
//*************************************************************************************************************************   
     
     
//*************************************************************************************************************************
//METHOD-5   : GetShippingMethods_FixedRadioButton (Refer: TC2 & TC3 Steps)
//PURPOSE    : Created this METHOD for validating the message after submitting the order  
//AUTHOR     : Praveen varma.
//CreatedDate: 20-08-2015.
//*************************************************************************************************************************
     
	 public static void GetShippingMethods_FixedRadioButton(WebDriver driver) 
	 {			
		//Click on get shipping methods and rates
		driver.findElement(By.xpath(".//*[@id='order-shipping-method-summary']/a")).click();
		Reporter.log("Click on the Get Shipping methods and rates");			   
		
	    //Verifying whether the FixedRate radio button exists after clicking on getting shipping methods			
		if(driver.findElement(By.xpath("//input[@id='s_method_flatrate_flatrate']")).isDisplayed()) 
 		 {								  			
 			driver.findElement(By.xpath("//input[@id='s_method_flatrate_flatrate']")).click();
 			Reporter.log("Click on the fixed Rate Radio button");
 			
 		 }else if(!driver.findElement(By.xpath("//input[@id='s_method_flatrate_flatrate']")).isDisplayed()){
	  	    	Reporter.log("No quotes are available for this order at this time");
	        } 		
     }

//*************************************************************************************************************************
// End of Method-5
//*************************************************************************************************************************   
	 
	 
//*************************************************************************************************************************
//METHOD-6   :  Verify_UserLogOut (Refer: All the TestCases)
//PURPOSE    :  Created this METHOD to verify the User LogOut.
//AUTHOR     :  Praveen varma.
//CreatedDate:  21-08-2015.
//*************************************************************************************************************************

	 public static void Verify_UserLogOut(WebDriver driver)
	 {   
		 //Calling Objects From POM
		 PageFactory.initElements(driver, Properties.class);
		 Properties PageElements = new Properties(driver);
		   
	    //Verify whether the login button exists after the user logout 
		if (PageElements.LoginButton.isDisplayed()) {
			Reporter.log("User logged-Out successfully.");
			System.out.println("User logged-Out successfully.");			
		} else{
			Reporter.log("<---- User failed to log-Out. ---->"); 
			System.out.println("User failed to log-Out.");			
	    }
	
	 }
	 
//*************************************************************************************************************************
// End of Method-6
//*************************************************************************************************************************   
	 
	 
//*************************************************************************************************************************
//METHOD-7   : Verify_UserLogin (Refer: All the TestCases)
//PURPOSE    : Created this METHOD to verify the User LogIn.
//AUTHOR     : Praveen varma.
//CreatedDate: 20-08-2015.
//*************************************************************************************************************************

	public static void Verify_UserLogin(WebDriver driver)
	{   
		//Calling Objects From POM
		PageFactory.initElements(driver, Properties.class);
		Properties PageElement = new Properties(driver);
		
		if (PageElement.MagentoLOGO.isDisplayed())
		{	    	
			Reporter.log("User Login is successfull.");
			System.out.println("User Login is successfull.");			
		} else{          
			  Reporter.log("<--------User failed to login --------->");
			  System.out.println("<--------User failed to login --------->");
		}
	}

//*************************************************************************************************************************
// End of Method-7
//*************************************************************************************************************************
	
	
//*************************************************************************************************************************
//Mehtod-8   : OrdersPage_Verification (Refer: TC4)
//Purpose    : Created this METHOD to verify whether the user is navigated to the Orders Page.
//Author     : Praveen varma.
//CreatedDate: 18-08-2015.
//*************************************************************************************************************************
	
	 public static void OrdersPage_Verification(WebDriver driver)		
	 {
		//Verifying whether the user is navigated to the Orders Page.
		if(OrdersPage.VerifyText_OrdersPage(driver).isDisplayed()) 
		{
			String Orders_text = OrdersPage.VerifyText_OrdersPage(driver).getText();
			Reporter.log("User is navigated to the " + Orders_text + "page");
			
		}else if(!OrdersPage.VerifyText_OrdersPage(driver).isDisplayed())
		   {
			  Reporter.log("User is failed to navigate to the orders page."); 
		   }	
	 }

//*************************************************************************************************************************
// End of Method-8
//*************************************************************************************************************************
	 
	 
//*************************************************************************************************************************	
//Method-9	 : VerifyingtheOrderNumber (Refer: TC4)
//Purpose    : Created this METHOD to verify the resulted OrderNumber.
//Author     : Praveen varma.
//CreatedDate: 14-08-2015.
//*************************************************************************************************************************
	 
	 public static void VerifyingtheOrderNumber(WebDriver driver,String ValidOrderNumber) throws InterruptedException 
	 {
		 //Verifying whether the searched ordernumber matches with the resulted order number
		 if (driver.findElement(By.xpath(".//*[@id='sales_order_grid_table']/tbody/tr")).isDisplayed()) 
		 {
		 	 String SearchedOrderNo_Text = driver.findElement(By.xpath(".//*[@id='sales_order_grid_table']/tbody/tr")).getText();
			 System.out.println(SearchedOrderNo_Text);	
				
			 if (SearchedOrderNo_Text.contains(ValidOrderNumber)) 
			 {					
				Reporter.log("The results displayed for the OrderNo# - "+ValidOrderNumber+" is true. ");						
			 }else 
				{
				  Reporter.log("No records found for the Order.No# : "+ValidOrderNumber);		
			    }
		 }				
		   
	  }
	 
//*************************************************************************************************************************
// End of Method-9
//*************************************************************************************************************************

	 
//*************************************************************************************************************************	
//Method-10	 : Verify_CreditMemoOutputMesage (Refer: TC11)
//Purpose    : Created this METHOD to verify the valid message after clicking on the Re-fund online button
//Author     : Praveen varma.
//CreatedDate: 14-08-2015.
//*************************************************************************************************************************	 
	
	 //Verify for the valid message after clicking on the Re-fund online button
	 public static void Verify_CreditMemoOutputMesage(WebDriver driver) throws InterruptedException 
	 {
		 	
       if (driver.findElement(By.xpath("//div[@id='messages']/ul/li")).isDisplayed()) {			
     	  String getValidTextMessage = driver.findElement(By.xpath("//div[@id='messages']/ul/li")).getText();
     	  
     	  if (getValidTextMessage.contains("The credit memo has been created.")) { 
     		  
     		   Reporter.log("OutPut Text Message is displayed as :  The credit memo has been created.");    		   
     		   String getOrderNumberFortheCreditMemo = driver.findElement(By.xpath("//h3[@class='icon-head head-sales-order']")).getText();       	  
         	   Reporter.log("The Credit memo has been created for the OrderNumber :  " + getOrderNumberFortheCreditMemo );
         	   
		   }else if(!getValidTextMessage.contains("The credit memo has been created.")){
			
			    String getInvalidTextMessage = driver.findElement(By.xpath("//div[@id='messages']/ul/li")).getText();
			    Reporter.log("OutPut Text Message is displayed as : " + getInvalidTextMessage);    
	        }
         }  	
		
     }

//*************************************************************************************************************************	
//Method-11	 : VerifyingtheOrderNumber (Refer: TC4)
//Purpose    : Created this METHOD to verify the resulted OrderNumber.
//Author     : Praveen varma.
//CreatedDate: 14-08-2015.
//*************************************************************************************************************************
	 
	 public static String selectRandomOrder(WebDriver driver)
	 {
			String string = "";
			String ReturnValue = null;

			WebElement Webtable = driver.findElement(By.xpath(".//*[@id='sales_order_grid_table']/tbody"));
			List<WebElement> TotalRowCount = Webtable.findElements(By.tagName("tr"));
			for(WebElement rowData:TotalRowCount)
			{
			   string =  string + rowData.getText().trim().substring(0, 9)+";";
		    }
			
			String[] stringArray =string.split(";");
			int countofOrders = new Random().nextInt(stringArray.length);	
			
			//Select the random value from the array list 
			String random = (stringArray[countofOrders]);
			Reporter.log("Selected random order is : "+random);
	        ReturnValue = random;

			return ReturnValue;
	}
	 
//*************************************************************************************************************************
// End of Method-11
//*************************************************************************************************************************
	
	 
//*************************************************************************************************************************	
//Method-12	 : selectWebSiteType (Refer: TC12)
//Purpose    : Created this METHOD to select the random value from the WebType list in the Manage Gift Wrapping Page
//Author     : Praveen varma.
//CreatedDate: 02-09-2015.
//*************************************************************************************************************************
	 
	 //Calling method to sekect the random value from the WebType list in the Manage Gift Wrapping Page
	 public static void selectWebSiteType(WebDriver driver)
	 {
		
	    //Creating the object for the WebSiteList.  
	    WebElement SelectWebSiteType = NewGiftWrapping_Page.SelectDropdownValue_Websites(driver);
	    
	    //Using select class to fetch the count 
		Select dropdown = new Select(SelectWebSiteType);
		
		//To get the dropdown options 
		List<WebElement> dropdownList = dropdown.getOptions();
		
		//To Count the no. of items in the list
		int dropdownOptionsCount = dropdownList.size();
		
		//Using Random class to generate random values
		Random randomvalue = new Random();
		
		int randomOptionSelect = randomvalue.nextInt(dropdownOptionsCount);
		
		//Selecting value from the DropDownList
	    dropdown.selectByIndex(randomOptionSelect);
	    
	    String SelectedWebSiteString =  SelectWebSiteType.getAttribute("value");
		
		//To print the selected value from the status dropdownlist
		if(SelectedWebSiteString.contains("1")){
			 Reporter.log("Option selected in the field 'WebSites' is : 'Main Website' ");			 
		}else{
			 Reporter.log("Option selected in the field 'WebSites' is : 'Private Sales Website' ");
		}
	 }	 
	 
//*************************************************************************************************************************
// End of Method-12
//*************************************************************************************************************************	 
	

//*************************************************************************************************************************	
//Method-12	 : Verify_ManageGiftWrappingPageNavigation (Refer: TC12)
//Purpose    : Created this METHOD to Verify whether the Manage Gift Wrapping Page navigation.
//Author     : Praveen varma.
//CreatedDate: 02-09-2015.
//***************s**********************************************************************************************************
	 
	 public static void Verify_ManageGiftWrappingPageNavigation(WebDriver driver)
	 {
		  //Get the page title and store it in a variable.
		  String getPageTitle = driver.getTitle();		  
		   
		  if(getPageTitle.contains("Manage Gift Wrapping"))
		  {
			  System.out.println("User is navigated to the 'manage gift wrapping page'.");
		  }else{
			  System.out.println("User failed to navigate to the manage gift wrapping page");
		  }
	 }
	 
//*************************************************************************************************************************
// End of Method-13
//*************************************************************************************************************************	 

	 
//*************************************************************************************************************************	
//Method-14	 : selectWebSiteType (Refer: TC12)
//Purpose    : Created this METHOD to select the random value from the dropdown list 'status'.
//Author     : Praveen varma.
//CreatedDate: 02-09-2015.
//*************************************************************************************************************************
		 
	 //Created this method to select the random value from the dropdown list 'status'. 
	 public static void selectStatusFrom_Dropdownlist(WebDriver driver)
	 {			
		  //Creating the object for the WebSiteList.  
		  WebElement SelectStatus = NewGiftWrapping_Page.SelectDropdownValue_Status(driver);
		    
		  //Using select class to fetch the count 
	      Select dropdown = new Select(SelectStatus);
	
		  //To get the dropdown options 
		  List<WebElement> dropdownList = dropdown.getOptions();
			
		  //To Count the no. of items in the list
		  int dropdownOptionsCount = dropdownList.size();
			
		  //Using Random class to generate random values
		  Random randomvalue = new Random();
			
		  int randomOptionSelect = randomvalue.nextInt(dropdownOptionsCount);
			
		  //Selecting value from the DropDownList
		  dropdown.selectByIndex(randomOptionSelect);
		  
		  String SelectedStatusValue =  SelectStatus.getAttribute("value");
		
		  //To print the selected value from the status dropdownlist
		  if(SelectedStatusValue.contains("0")){
			Reporter.log("Option selected from the 'Status' dropdown list is : Disabled ");
		  }else{
			Reporter.log("Option selected from the 'Status' dropdown list is : 'selected' ");
		  }  
	 }
		
//*************************************************************************************************************************
// End of Method-14
//*************************************************************************************************************************	 
	

//*************************************************************************************************************************	
//Method-15	 : Verify_GiftWrappingTextMessage (Refer: TC12)
//Purpose    : Created this method to Verify the valid text message after saving the Gift Wrapping details.
//Author     : Praveen varma.
//CreatedDate: 02-09-2015.
//*************************************************************************************************************************
			 
	//Created this method to Verify the valid text message after saving the Gift Wrapping details 
	public static void Verify_GiftWrappingTextMessage(WebDriver driver,String RandomString_GiftWrappingDesign)
	{			
		//Verify whether the valid text message is displayed after saving the Gift Wrapping details
        if (driver.findElement(By.xpath("//li[@class='success-msg']")).isDisplayed()) {			
        	String getValidGiftWrappingText = driver.findElement(By.xpath("//li[@class='success-msg']")).getText();
        	Reporter.log("Output text message is displayed as : "+getValidGiftWrappingText);
        	System.out.println("Output text message is displayed as : "+getValidGiftWrappingText);
        	
        	//Get the newly created design name 
        	Reporter.log("The gift wrapping design is created with the name  : "+RandomString_GiftWrappingDesign);
      	    System.out.println("The gift wrapping design is created with the name  : "+RandomString_GiftWrappingDesign);
        	
		}else {			
			Reporter.log("Falied to save the gift wrapping.");
		}       
    }
			
//*************************************************************************************************************************
// End of Method-16
//*************************************************************************************************************************	 
	
	
//*************************************************************************************************************************
//METHOD-16  : SelectStatusValueFromDropdownList (Ref:- TC13)
//PURPOSE    : Created this METHOD for selecting the random value from the Status dropdown list
//AUTHOR     : Praveen varma.
//CreatedDate: 04-09-2015.  
//*************************************************************************************************************************
	    
	  //Created this method to select the random value from the dropdown list 'status'. 
      public static void selectStatusFromDropdownlist1(WebDriver driver)
	  {			
		  //Creating the object for the WebSiteList.  
		  WebElement SelectStatus = ManageGiftWrapping_Page.dropdown1_Status(driver);		  
			    
		  //Using select class to fetch the count 
		  Select dropdown = new Select(SelectStatus);
		
		  //To get the dropdown options 
		  List<WebElement> dropdownList = dropdown.getOptions();
				
		  //To Count the no. of items in the list
		  int dropdownOptionsCount = dropdownList.size();
				
		  //Using Random class to generate random values
		  Random randomvalue = new Random();
				
		  int randomOptionSelect = randomvalue.nextInt(dropdownOptionsCount);
				
	      //Selecting value from the DropDownList
		  dropdown.selectByIndex(randomOptionSelect);
			  
		  String SelectedStatusValue =  SelectStatus.getAttribute("value");
			
	      //To print the selected value from the status dropdownlist
		  if(SelectedStatusValue.contains("0")){
			  Reporter.log("Option selected from the 'Status' dropdown list is : Disabled ");
		      System.out.println("Option selected from the 'Status' dropdown list is : Disabled ");
		      
		    }else if(SelectedStatusValue.contains("1")){
				 Reporter.log("Option selected from the 'Status' dropdown list is : 'Enabled' ");
				 System.out.println("Option selected from the 'Status' dropdown list is : 'Enabled' ");
				 
		     }else if(SelectedStatusValue.contains("")){		    	 
		    	 Reporter.log("Option selected from the 'Status' dropdown list is : 'Null' ");
		    	 System.out.println("Option selected from the 'Status' dropdown list is : 'Null' ");
		    }
	  }

	
	     
//*************************************************************************************************************************
// End of Method-16
//*************************************************************************************************************************   


//*************************************************************************************************************************
//METHOD-17  : selectOptionFromActionsDropdownlist (Ref:- TC13)
//PURPOSE    : Created this METHOD to select the 'Change status' value from the Actions dropdown list.
//AUTHOR     : Praveen varma.
//CreatedDate: 04-09-2015.  
//*************************************************************************************************************************
    	    
     //Created this METHOD to select the 'Change status' value from the Actions dropdown list. 
     public static void selectOptionFromActionsDropdownlist(WebDriver driver)
     {			
    	//Creating the object for the Actions dropdown list.  
        WebElement SelectStatus = ManageGiftWrapping_Page.dropdown_Actions(driver);
  
        //Using select class to fetch the count 
    	Select dropdown = new Select(SelectStatus);
    		  
    	//Select the option 'Change status' from the Actions dropdown.
    	dropdown.selectByVisibleText("Change status");
    	Reporter.log("Option selected in the 'Actions' dropdown list is : Change status ");	  
    	System.out.println("Option selected in the 'Actions' dropdown list is : Change status ");
     }
     
//*************************************************************************************************************************
// End of Method-17
//*************************************************************************************************************************   
   
//*************************************************************************************************************************
//METHOD-18  : SelectStatusValueFromDropdownList2 (Ref:- TC13)
//PURPOSE    : Created this METHOD for selecting the random value from the Status dropdown list
//AUTHOR     : Praveen varma.
//CreatedDate: 07-09-2015.  
//*************************************************************************************************************************
   	    
   	  //Created this method to select the random value from the dropdown list 'status'. 
      public static void selectStatusFromDropdownlist2(WebDriver driver)
   	  {			
   		  //Creating the object for the Status dropdown list  
   		  WebElement SelectStatus = ManageGiftWrapping_Page.dropdown2_Status(driver);
   			    
   		  //Using select class to fetch the count 
   		  Select dropdown = new Select(SelectStatus);
   		
   		  //To get the dropdown options 
   		  List<WebElement> dropdownList = dropdown.getOptions();
   				
   		  //To Count the no. of items in the list
   		  int dropdownOptionsCount = dropdownList.size();
   				
   		  //Using Random class to generate random values
   		  Random randomvalue = new Random();
   				
   		  int randomOptionSelect = randomvalue.nextInt(dropdownOptionsCount);
   				
   	      //Selecting value from the DropDownList
   		  dropdown.selectByIndex(randomOptionSelect);
   			  
   		  String SelectedStatusValue =  SelectStatus.getAttribute("value");
   			
   	      //To print the selected value from the status dropdownlist
   		  if(SelectedStatusValue.contains("0")){
   			  Reporter.log("Option selected from the 'Status' dropdown list is : Disabled ");
   			  System.out.println("Option selected from the 'Status' dropdown list is : Disabled ");
   		    
   		    }else if(SelectedStatusValue.contains("1")){
   				 Reporter.log("Option selected from the 'Status' dropdown list is : 'Enabled' ");
   				 System.out.println("Option selected from the 'Status' dropdown list is : 'Enabled' ");
   		      
   		     }else if(SelectedStatusValue.contains("Null")){		    	 
   		    	 /*Reporter.log("Option selected from the 'Status' dropdown list is : 'Null' ");
   				 System.out.println("Option selected from the 'Status' dropdown list is : 'Null' ");*/
   				 
   				// The below code will select therandom value from the dropdown if the null value is selected.
   			    // This code works only when the null value is selected.
   			    WebElement SelectStatus1 = ManageGiftWrapping_Page.dropdown2_Status(driver);

   			    //Using select class to fetch the count 
   			    Select dropdown1 = new Select(SelectStatus1);

   			    //To get the dropdown options 
   			    List<WebElement> dropdownList1 = dropdown1.getOptions();
   					
   			    //To Count the no. of items in the list
   			    int dropdownOptionsCount1 = dropdownList1.size();
   					
   			    //Using Random class to generate random values
   			    Random randomvalue1 = new Random();
   					
   			    int randomOptionSelect1 = randomvalue1.nextInt(dropdownOptionsCount1);
   					
   				//Selecting value from the DropDownList
   			    dropdown.selectByIndex(randomOptionSelect1);
   				  
   			    String SelectedStatusValue1 =  SelectStatus.getAttribute("value");
   			 
   			    //To print the selected value from the status dropdownlist
   			    if(SelectedStatusValue1.contains("0")){
   				   Reporter.log("Option selected from the 'Status' dropdown list is : Disabled ");
   				   System.out.println("Option selected from the 'Status' dropdown list is : Disabled ");
   			   
   			    }else if(SelectedStatusValue1.contains("1")){
   					    Reporter.log("Option selected from the 'Status' dropdown list is : 'Enabled' ");
   					    System.out.println("Option selected from the 'Status' dropdown list is : 'Enabled' ");
   			   }
   		   }
   	  }

   	
   	     
//*************************************************************************************************************************
// End of Method-18
//*************************************************************************************************************************   
     
      
//*************************************************************************************************************************	
//Method-19	 : Verify_StatusDropdownIdentification (Refer: TC13)
//Purpose    : Created this method to Verify whether the status dropdown list is displayed after selecting the Change Status option.
//Author     : Praveen varma.
//CreatedDate: 07-09-2015.
//*************************************************************************************************************************
    			 
    //Verifying whether the status dropdown list is displayed after selecting the Change Status option. 
    public static void Verify_StatusDropdownIdentification(WebDriver driver)
    {			
    	//Verifying whether the status dropdown list is displayed after selecting the Change Status option.
    	if(driver.findElement(By.xpath("//span[@class='field-row']/select[@class='required-entry absolute-advice select']")).isDisplayed()){
    	    Reporter.log("'Status' dropdown is displayed after selecting the Change Status option");
    	    System.out.println("'Status' dropdown is displayed after selecting the Change Status option");
    	    
    	}else{
    	       Reporter.log("'Status' dropdown is 'not displayed' after selecting the Change Status option");
    	       System.out.println("'Status' dropdown is 'not displayed' after selecting the Change Status option");    	       
             } 
    }

    			
//*************************************************************************************************************************
// End of Method-19
//*************************************************************************************************************************	 
    

//*************************************************************************************************************************	
//Method-20	 : Verify_TotalNumOfRecordsUpdated (Refer: TC13)
//Purpose    : Created this method to Verify whether the status dropdown list is displayed after selecting the Change Status option.
//Author     : Praveen varma.
//CreatedDate: 07-09-2015.
//*************************************************************************************************************************
      			 
      //Verifying the total no. of records that have been updated. 
      public static void Verify_TotalNumOfUpdatedRecords(WebDriver driver)
      {			
    	//Verifying the total no. of records that have been updated.
    	if (driver.findElement(By.xpath("//li[@class='success-msg']")).isDisplayed()) {   	    	
    	    String GetTotalNumOfUpdatedRecords = driver.findElement(By.xpath("//li[@class='success-msg']")).getText();
    	    System.out.println("OutPut Message is : "+GetTotalNumOfUpdatedRecords);   	    	
    	} 
    	
      }

      			
//*************************************************************************************************************************
// End of Method-20
//*************************************************************************************************************************	 
      
//*************************************************************************************************************************	
//Method-21	 : selectOptionDeleteFromActionsDropdown (Refer: TC14 & TC30)
//Purpose    : Created this Re-usable METHOD to select the 'delete' option from the Actions dropdown list.
//Author     : Praveen varma.
//CreatedDate: 07-09-2015.
//*************************************************************************************************************************
          			 
     //Created this METHOD to select the 'delete' option from the Actions dropdown list. 
     public static void selectOptionDeleteFromActionsDropdown(WebDriver driver,String Key,String Locator)
     {			
     	//Creating the object for the Actions dropdown list.  
        WebElement SelectStatus = driver.findElement(By.id(Locator));
   
        //Using select class to fetch the count 
     	Select dropdown = new Select(SelectStatus);
     		  
     	//Select the option 'Change status' from the Actions dropdown.
     	dropdown.selectByVisibleText(Key);
     	Reporter.log("Option selected in the 'Actions' dropdown list is : Delete ");	  
     	System.out.println("Option selected in the 'Actions' dropdown list is : Delete ");
     }
          
//*************************************************************************************************************************
// End of Method-21
//*************************************************************************************************************************	      
  
     
//*************************************************************************************************************************
//Mehtod-22  : Verify_EditgiftWrappingPageNavigation (Refer: TC15)
//Purpose    : Created this METHOD to verify whether the user is navigated to the Edit Gift Wrapping Design Page.
//Author     : Praveen varma.
//CreatedDate: 18-08-2015.
//*************************************************************************************************************************
   	
   	 public static void Verify_EditgiftWrappingPageNavigation(WebDriver driver)		
   	 {
   		//Verifying whether the user is navigated to the Edit gift wrapping Page.
 	    if (EditgiftWrappingDesign_Page.Text_EditGiftWrapping(driver).isDisplayed()) {
 	    	                                                                     
 			@SuppressWarnings("unused")
			String getEditGiftWrappingTextWithName = EditgiftWrappingDesign_Page.Text_EditGiftWrapping(driver).getText();
 		    Reporter.log("User is navigated to the Edit Gift Wrapping Page.");
 		    
 	    } else {
 	    	 //System.out.println("User failed to navigated to the Edit Gift Wrapping Page.");
 	    	 Reporter.log("User failed to navigated to the Edit Gift Wrapping Page.");
 		}
   	 }

//*************************************************************************************************************************
// End of Method-22
//*************************************************************************************************************************     
	
//*************************************************************************************************************************
//Mehtod-23  : Verify_EditgiftWrappingPageNavigation (Refer: TC15)
//Purpose    : Created this METHOD to verify whether the user is navigated to the Edit Gift Wrapping Design Page.
//Author     : Praveen varma.
//CreatedDate: 18-08-2015.
//*************************************************************************************************************************
      	
   	 public static String chooseOrdernumber_by_status(WebDriver driver)
   	 {		
   		String string = "";
		String ReturnValue = null;

		WebElement Webtable = driver.findElement(By.xpath(".//*[@id='sales_order_grid_table']/tbody"));
		List<WebElement> TotalRowCount = Webtable.findElements(By.tagName("tr"));
		for(int i=1;i<=TotalRowCount.size();i++){
			List<WebElement> output = driver.findElements(By.xpath(".//*[@id='sales_order_grid_table']/tbody/tr["+ i + "]/td[" + 9 + "]"));
			for(WebElement cellDataofStatus : output){
				if(!cellDataofStatus.getText().equalsIgnoreCase("Edit")){
					List<WebElement> output1 = driver.findElements(By.xpath(".//*[@id='sales_order_grid_table']/tbody/tr["+ i + "]/td[" + 2 + "]"));
					for(WebElement cellDataofOrderNumber : output1){
						string= string+cellDataofOrderNumber.getText()+";";
					}
				}
			}
	  }

	  // need to make it as method in utill lib ,ip:array ,op:string //
	  String[] stringArray =string.split(";");
	  int countofOrders = new Random().nextInt(stringArray.length);
	  
	  //Select the random value from the array list 
	  String random = (stringArray[countofOrders]);
	  System.out.println("Selected orderno based on status  is : "+random);
      ReturnValue = random;
      
	  return ReturnValue;
	}

//*************************************************************************************************************************
// End of Method-23
//*************************************************************************************************************************       	 

   	  
//*************************************************************************************************************************	
//Method-24	 : selectAssocuateValue (Refer: TC26)
//Purpose    : Created this METHOD to select the random value from the Associate list in the New Customer Page.
//Author     : Praveen varma.
//CreatedDate: 21-09-2015.
//*************************************************************************************************************************
   		 
   	//Calling method to sekect the random value from the WebType list in the Manage Gift Wrapping Page
   	public static void selectAssociateValue(WebDriver driver)
   	{   			 
	    //Creating the object for the WebSiteList.  
 		WebElement SelectWebSiteType = driver.findElement(By.id("_accountwebsite_id"));
 		    
 		//Using select class to fetch the count 
 	    Select dropdown = new Select(SelectWebSiteType);
 			
 		//To get the dropdown options 
 		List<WebElement> dropdownList = dropdown.getOptions();
 			
 		//To Count the no. of items in the list
 		int dropdownOptionsCount = dropdownList.size();
 			
 		//Using Random class to generate random values
 		Random randomvalue = new Random();
 			
 		int randomOptionSelect = randomvalue.nextInt(dropdownOptionsCount);
 			
 		//Selecting value from the DropDownList
 		dropdown.selectByIndex(randomOptionSelect);
 		    
 		String SelectedWebSiteString =  SelectWebSiteType.getAttribute("value");
 			
 		//To identify the selected value from the status dropdownlist
 		if(SelectedWebSiteString.contains("0")){
 			Reporter.log("Option selected in the field 'Associate WebSite' is : 'Admin' ");
 			System.out.println("Option selected in the field 'Associate WebSite' is : 'Admin' ");
 			
 		}else if(SelectedWebSiteString.contains("1")){
 			Reporter.log("Option selected in the field 'Associate WebSite' is : 'Main Website' ");
 			System.out.println("Option selected in the field 'Associate WebSite' is : 'Main Website' ");
 			
 		}else if(SelectedWebSiteString.contains("2")){
 			Reporter.log("Option selected in the field 'Associate WebSite' is : 'Private Sales Website' ");
 			System.out.println("Option selected in the field 'Associate WebSite' is : 'Private Sales Website' ");
 			
 		}else{
 		// The below code will select therandom value from the dropdown if the null value is selected.
		    // This code works only when the null value is selected.
		    WebElement SelectStatus1 = ManageGiftWrapping_Page.dropdown2_Status(driver);

		    //Using select class to fetch the count 
		    Select dropdown1 = new Select(SelectStatus1);

		    //To get the dropdown options 
		    List<WebElement> dropdownList1 = dropdown1.getOptions();
				
		    //To Count the no. of items in the list
		    int dropdownOptionsCount1 = dropdownList1.size();
				
		    //Using Random class to generate random values
		    Random randomvalue1 = new Random();
				
		    int randomOptionSelect1 = randomvalue1.nextInt(dropdownOptionsCount1);
				
			//Selecting value from the DropDownList
		    dropdown.selectByIndex(randomOptionSelect1);
			  
		    String SelectedStatusValue1 =  SelectStatus1.getAttribute("value");
		 
		    //To identify the selected value from the Associate WebSite dropdown list
		    if(SelectedStatusValue1.contains("0")){
		    	Reporter.log("Option selected in the drop-down 'Associate WebSite' is : 'Admin' ");
			    System.out.println("Option selected in the field 'Associate WebSite' is : 'Admin' ");
		   
		    }else if(SelectedStatusValue1.contains("1")){
		    	Reporter.log("Option selected in the drop-down 'Associate WebSite' is : 'Main Website' ");
				System.out.println("Option selected in the field 'Associate WebSite' is : 'Main Website' ");				
		   }else{
			   Reporter.log("Option selected in the drop-down 'Associate WebSite' is : 'Private Sales Website' ");
			   System.out.println("Option selected in the field 'Associate WebSite' is : 'Private Sales Website' ");
		   }
 		}
   	}
   		 
//*************************************************************************************************************************
// End of Method-24
//*************************************************************************************************************************	 

   	
//*************************************************************************************************************************	
//Method-25	 : Verify_ManageCustomerspageNavigation (Refer: TC26)
//Purpose    : Created this METHOD to verify whether the user is navigated to the 'Manage Customers' page.
//Author     : Praveen varma.
//CreatedDate: 21-09-2015.
//*************************************************************************************************************************
 		   		 
    //Verify whether the user is navigated to the 'Manage Customers' page.
    public static void Verify_ManageCustomerspageNavigation(WebDriver driver)
    {      
	     //Get the Manage Customers page title.
	  	 String getManageCustomersPageTitle = driver.getTitle();  	
	  	  
	     //Verify whether the user is navigated to the 'Manage Customers' page.
	  	 if (getManageCustomersPageTitle.contains("Manage Customers")) {		  
	  	     System.out.println("User is navigated to the 'Manage Customers' page.");
	  		 Reporter.log("User is navigated to the 'Manage Customers' page.");
	  		  
	  	 }else {		   
	  		 System.out.println("User failed to navigate to the 'Manage Customers' page.");
	  		 Reporter.log("User failed to navigate to the 'Manage Customers' page.");
	  	 }  	  
    }	   
	   		
//*************************************************************************************************************************
// End of Method-25
//*************************************************************************************************************************	  		

    
//*************************************************************************************************************************	
//Method-26  : Verify_NewCustomerPageNavigation (Refer: TC26)
//Purpose    : Created this METHOD to verify whether the user is navigated to the 'New Customer' page.
//Author     : Praveen varma.
//CreatedDate: 21-09-2015.
//*************************************************************************************************************************
   		   		 
     //Verify whether the user is navigated to the 'New Customer' page.
     public static void Verify_NewCustomerPageNavigation(WebDriver driver)
     {        
    	  //Get the New Customer page title
    	  String getNewCustomerPageTitle = driver.getTitle();   	  
    	  
    	  //Verify whether the user is navigated to the 'New Customer' page.
    	  if (getNewCustomerPageTitle.contains("New Customer")) {		  
    		  System.out.println("User is navigated to the 'New Customer' page.");
    		  Reporter.log("User is navigated to the 'New Customer' page.");
    		  
    	  }else {		   
    		  System.out.println("User failed to navigate to the 'New Customer' page.");
    		  Reporter.log("User failed to navigate to the 'New Customer' page.");
          }    	  
     }	   
  	   		
//*************************************************************************************************************************
// End of Method-26
//*************************************************************************************************************************	  		

//*************************************************************************************************************************	
//Method-27  : Verify_NewCustomerPageNavigation (Refer: TC27)
//Purpose    : Created this METHOD to select the random value from the Group list in the New Customer Page.
//Author     : Praveen varma.
//CreatedDate: 21-09-2015.
//*************************************************************************************************************************
      		   		 
     //Select the random value in the Group dropdown list
     public static void SelectGroupValue(WebDriver driver)
     {         
        	//Creating the object for the WebSiteList.  
        	WebElement SelectGroupType = driver.findElement(By.id("_accountgroup_id"));
        	    
        	//Using select class to fetch the count 
            Select dropdown = new Select(SelectGroupType);
        		
        	//To get the dropdown options 
        	List<WebElement> dropdownList = dropdown.getOptions();
        		
        	//To Count the no. of items in the list
        	int dropdownOptionsCount = dropdownList.size();
        		
        	//Using Random class to generate random values
        	Random randomvalue = new Random();
        		
        	int randomOptionSelect = randomvalue.nextInt(dropdownOptionsCount);
        		
        	//Selecting value from the DropDownList
        	dropdown.selectByIndex(randomOptionSelect);
        	    
        	String SelectedGroupString =  SelectGroupType.getAttribute("value");
        	
        	//To identify the selected value from the Associate WebSite dropdown list
		    if(SelectedGroupString.contains("1")){
		    	Reporter.log("Option selected in the drop-down 'Group' is : 'General' ");
			    System.out.println("Option selected in the drop-down 'Group' is : 'General' ");		   
		    }else if(SelectedGroupString.contains("2")){
		    	Reporter.log("Option selected in the drop-down 'Group' is : 'Wholesale' ");
				System.out.println("Option selected in the drop-down 'Group' is : 'Wholesale' ");				
		   }else if(SelectedGroupString.contains("4")){
			   Reporter.log("Option selected in the drop-down 'Group' is : 'VIP Member' ");
			   System.out.println("Option selected in the drop-down 'Group' is : 'VIP Member' ");
		   }else if(SelectedGroupString.contains("5")){
			   Reporter.log("Option selected in the drop-down 'Group' is : 'Private Sales Member' ");
			   System.out.println("Option selected in the drop-down 'Group' is : 'Private Sales Member' ");
		   }else{
			   Reporter.log("Option selected in the drop-down 'Group' is : 'walmart' ");
			   System.out.println("Option selected in the drop-down 'Group' is : 'walmart' ");
		   }     	  
      }	   
     	   		
//*************************************************************************************************************************
// End of Method-27
//*************************************************************************************************************************	  		

//*************************************************************************************************************************	
//Method-28  : Verify_NewCustomerPageNavigation (Refer: TC27)
//Purpose    : Created this METHOD to Validate the customer details with the given name.
//Author     : Praveen varma.
//CreatedDate: 23-09-2015.
//*************************************************************************************************************************
      		   		 
     //To Validate the customer details with the given name.
     public static void ValidateCustomerName(WebDriver driver)
     {        
    	 //get the customet name
    	 String getUserName = driver.findElement(By.xpath("//h3[@class='icon-head head-customer']")).getText();
    		
    	 if (getUserName.contains(Constant.ValidCustomerName)) {
    		 Reporter.log("Customer details matches with the given name.");
    		 Reporter.log("<----- Validation is successfull ----->");
    		 System.out.println("Customer details matches with the given name.");
    		 System.out.println("<----- Validation is successfull ----->");
    				
    	 }else {	    		 
    		 System.out.println("<--- Customer details doesn't matches with the given name. ---->");
    		 System.out.println("Found the customer name in the details as : "+getUserName);
    		 Reporter.log("<--- Customer details doesn't matches with the given name. ---->");
    		 Reporter.log("Found the customer name in the details as : "+getUserName);    		 
    	 }    	 
     }	   
     	   		
//*************************************************************************************************************************
// End of Method-28
//*************************************************************************************************************************	  		    
      
//*************************************************************************************************************************	
//Method-29	 : Verify_ManageGiftWrappingPageNavigation (Refer: TC12)
//Purpose    : Created this METHOD to Verify whether the Manage Gift Wrapping Page navigation.
//Author     : Praveen varma.
//CreatedDate: 02-09-2015.
//**************************************************************************************************************************
   	 
   	public static void Verify_CustomerInformationPageNavigation(WebDriver driver)
   	{
   		//get the Customer information page title
   		String getCustomerInfoPageTitle = driver.getTitle();
		
		if (getCustomerInfoPageTitle.contains("Manage Customers")) {					
		    System.out.println("User is navigated to the 'Customer Information' Page");
		    Reporter.log("User is navigated to the 'Customer Information' Page");
		}else {
            System.out.println("<--- User failed to navigate to the 'Customer Information' Page --->");
            Reporter.log("<--- User failed to navigate to the 'Customer Information' Page --->");
		} 
   	 }
   	 
//*************************************************************************************************************************
// End of Method-29
//*************************************************************************************************************************	 

   	
//*************************************************************************************************************************	
//Method-30  : SelectOptionVIPMember (Refer: TC28_EditCustomer_basedOn_CustomerName)
//Purpose    : Created this METHOD to select the option 'VIP Member' from the dropdown list - Group.
//Author     : Praveen varma.
//CreatedDate: 23-09-2015.
//*************************************************************************************************************************
        		   		 
    //Select the random value in the Group dropdown list
    public static void SelectOptionVIPMember(WebDriver driver)
    {     
    	//Calling Objects From POM
    	PageFactory.initElements(driver, Properties.class);
    	Properties PageElements = new Properties(driver);
    	
        //Creating the object for the dropdownList.  
    	WebElement SelectGroupValue = driver.findElement(By.id("_accountgroup_id"));
    	    
    	//Using select class to fetch the count 
    	Select dropdown = new Select(SelectGroupValue);
    		
    	//select the option 'VIP Member'
        dropdown.selectByVisibleText("VIP Member");
        
        //get the default selected text from the dropdown- 'group'
        String getSelectedDropdownOption = PageElements.gettextfromdropdown.getText();
        
        if(getSelectedDropdownOption.contains("VIP Member")){
        
           //To display the random option selected from the dropdown list 
           Reporter.log("Option selected in the dropdown field 'Group' is : "+getSelectedDropdownOption);
           System.out.println("Option selected in the dropdown field 'Group' is : "+getSelectedDropdownOption);
            
        }else{
        	
           //To display the random option selected from the dropdown list 
           Reporter.log("<---- Failed to select the option 'VIP Member' from the Group drodown. ---->");
           System.out.println("<---- Failed to select the option 'VIP Member' from the Group drodown. ---->");
        	
        }
     }
       
//*************************************************************************************************************************
// End of Method-30
//*************************************************************************************************************************	  		
	
//*************************************************************************************************************************	
//Method-31  : Verify_ManageCustomerAttributesPageNavigation (Refer: TC32_AddNewCustomerAttribute)
//Purpose    : Created this METHOD to verify the 'Manage Customer Attributes' page navigation.
//Author     : Praveen varma.
//CreatedDate: 28-09-2015.
//*************************************************************************************************************************    
    
    public static void Verify_ManageCustomerAttributesPageNavigation(WebDriver driver)
    {    	
    	 //Verify the 'Manage Customer Attributes' page navigation.
		 String getManageCustomerAttributesPageTitle = driver.getTitle();
		 
		 if (getManageCustomerAttributesPageTitle.contains("Manage Customer Attributes")) {			
			 System.out.println("User is navigated to the 'Manage Customer Attributes' page.");	
			 Reporter.log("User is navigated to the 'Manage Customer Attributes' page.");
		 }else{
			 System.out.println("User failed to navigate to the 'Manage Customer Attributes' page.");
			 Reporter.log("User failed to navigate to the 'Manage Customer Attributes' page.");
		 }
    }
    
//*************************************************************************************************************************
//End of Method-31
//*************************************************************************************************************************	

//*************************************************************************************************************************	
//Method-32  : Verify_NewCustomerAttributePageNavigation (Refer: TC32_AddNewCustomerAttribute)
//Purpose    : Created this METHOD to verify the 'New Customer Attribute' page navigation.
//Author     : Praveen varma.
//CreatedDate: 28-09-2015.
//*************************************************************************************************************************    
      
    public static void Verify_NewCustomerAttributePageNavigation(WebDriver driver)
    {    	
      	 //Verify the 'New Customer Attribute' page navigation.
  		 String getNewCustomerAttributePageTitle = driver.getTitle();
  		
  		 if (getNewCustomerAttributePageTitle.contains("New Attribute")) {			
  			 System.out.println("User is navigated to the 'New Customer Attribute' page.");	
  			 Reporter.log("User is navigated to the 'New Customer Attribute' page.");
  		 }else{
  			 System.out.println("User failed to navigate to the 'New Customer Attribute' page.");
  			 Reporter.log("User failed to navigate to the 'New Customer Attribute' page.");
  		 }
    }
      
//*************************************************************************************************************************
//End of Method-32
//*************************************************************************************************************************	

//*************************************************************************************************************************	
//Method-33  : Verify_Verify_NewAttributeTextMessage (Refer: TC32_AddNewCustomerAttribute)
//Purpose    : Created this METHOD to verify the valid text message after saving the new attribute details.
//Author     : Praveen varma.
//CreatedDate: 28-09-2015.
//************************************************************************************************************************* 
    
    //Created this method to Verify the valid text message after saving the new attribute details.
  	public static void Verify_NewAttributeTextMessage(WebDriver driver)
  	{			
  		 //Verify whether the valid text message is displayed after saving the new attribute details
         if (driver.findElement(By.xpath("//li[@class='success-msg']")).isDisplayed()) {			
          	 String getValidText = driver.findElement(By.xpath("//li[@class='success-msg']")).getText();
          	 Reporter.log("Output text message is displayed as : "+getValidText);
          	 System.out.println("Output text message is displayed as : "+getValidText);
          	
  		 }else {			
  			 Reporter.log("Failed to save the details for the 'New Customer Attribute'.");
  		 }       
    }
  	
//*************************************************************************************************************************
//End of Method-33
//*************************************************************************************************************************	

//*************************************************************************************************************************	
//Method-34  : Verify_ManageCustomerAddressAttributesPageNavigation (Refer: TC34_AddNewCustomerAttribute)
//Purpose    : Created this METHOD to verify the 'Manage Customer Address Attributes' page navigation.
//Author     : Praveen varma.
//CreatedDate: 28-09-2015.
//*************************************************************************************************************************    
      
    public static void Verify_ManageCustomerAddressAttributesPageNavigation(WebDriver driver)
    {    	
      	 //Verify the 'Manage Customer Address Attributes' page navigation.
  		 String getManageCustomerAttributesPageTitle = driver.getTitle();
  		 
  		 if (getManageCustomerAttributesPageTitle.contains("Manage Customer Address Attributes")) {			
  			 System.out.println("User is navigated to the 'Manage Customer Address Attributes' page.");	
  			 Reporter.log("User is navigated to the 'Manage Customer Address Attributes' page.");
  		 }else{
  			 System.out.println("User failed to navigate to the 'Manage Customer Address Attributes' page.");
  			 Reporter.log("User failed to navigate to the 'Manage Customer Address Attributes' page.");
  		 }
    }
      
//*************************************************************************************************************************
//End of Method-34
//*************************************************************************************************************************	

//*************************************************************************************************************************	
//Method-35  : Verify_Verify_NewAddressAttributeTextMessage (Refer: TC34_AddNewCustomerAddressAttribute)
//Purpose    : Created this METHOD to verify the valid text message after saving the new address attribute details.
//Author     : Praveen varma.
//CreatedDate: 28-09-2015.
//************************************************************************************************************************* 
      
     //Created this method to Verify the valid text message after saving the new attribute details.
     public static void Verify_NewAddressAttributeTextMessage(WebDriver driver)
     {			
    	   //Verify whether the valid text message is displayed after saving the new address attribute details
           if (driver.findElement(By.xpath("//li[@class='success-msg']")).isDisplayed()) {			
            	 String getValidText = driver.findElement(By.xpath("//li[@class='success-msg']")).getText();
            	 Reporter.log("Output text message is displayed as : "+getValidText);
            	 System.out.println("Output text message is displayed as : "+getValidText);
            	
    		 }else {			
    			 Reporter.log("Failed to save the details for the 'New Customer Address Attribute'.");
    		 }       
     }
    	
//*************************************************************************************************************************
//End of Method-35
//*************************************************************************************************************************	
 
//*************************************************************************************************************************	
//Method-36  : Verify_NewCustomerAddressAttributePageNavigation (Refer: TC34_AddNewCustomerAddressAttribute)
//Purpose    : Created this METHOD to verify the 'New Customer Address Attribute' page navigation.
//Author     : Praveen varma.
//CreatedDate: 28-09-2015.
//*************************************************************************************************************************    
         
       public static void Verify_NewCustomerAddressAttributePageNavigation(WebDriver driver)
       {    	
         	 //Verify the 'New Customer Attribute' page navigation.
     		 String getNewCustomerAddressAttributePageTitle = driver.getTitle();
     		 System.out.println(getNewCustomerAddressAttributePageTitle);
     		 if (getNewCustomerAddressAttributePageTitle.contains("New Attribute")) {			
     			 System.out.println("User is navigated to the 'New Customer Address Attribute' page.");	
     			 Reporter.log("User is navigated to the 'New Customer Address Attribute' page.");
     		 }else{
     			 System.out.println("User failed to navigate to the 'New Customer Address Attribute' page.");
     			 Reporter.log("User failed to navigate to the 'New Customer Address Attribute' page.");
     		 }
       }
         
//*************************************************************************************************************************
//End of Method-36
//*************************************************************************************************************************	

//*************************************************************************************************************************	
//Method-37  : SelectOptionNO_FromSystemDropdownList (Refer: TC33 and TC35)
//Purpose    : Created this Re-usable METHOD to select the option 'NO' from the dropdown list - System.
//Author     : Praveen varma.
//CreatedDate: 28-09-2015.
//*************************************************************************************************************************
             		   		 
    //Select the Option 'NO' in the System dropdown list
    public static void SelectOptionNo(WebDriver driver,String key,String Locator)
    {     
        //Creating the object for the dropdownList.  
        WebElement SelectGroupValue = driver.findElement(By.id(Locator));
         	    
        //Using select class to fetch the count 
        Select dropdown = new Select(SelectGroupValue);
         		
        //select the option 'VIP Member'
        dropdown.selectByVisibleText(key);
 
        //To display the option selected from the dropdown list - System
        Reporter.log("Option selected in the dropdown field 'System' is : 'NO' ");
        System.out.println("Option selected in the dropdown field 'System' is : 'NO' ");                    
    }
            
//*************************************************************************************************************************
// End of Method-37
//*************************************************************************************************************************	  		


      
}


	



    


