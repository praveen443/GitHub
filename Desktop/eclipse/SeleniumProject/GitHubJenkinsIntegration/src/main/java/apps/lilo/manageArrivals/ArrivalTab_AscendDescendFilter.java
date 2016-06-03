package apps.lilo.manageArrivals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.WebDriverSetup;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Link;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;

/**
* @summary Contains the page methods & objects found in the Manage Arrivals-Arrival Tab page
* @version Created 03/04/2015
* @author   Brajesh Ahirwar
*/
public class ArrivalTab_AscendDescendFilter {

	//*******************************
	//*** Arrival Tab Page Fields ***
	//*******************************
	private int timeout = WebDriverSetup.getDefaultTestTimeout();
	private int loopCounter = 0;
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	
	//*********************************
	//*** Arrival Tab Page Elements ***
	//*********************************
	
	//Go search button
	@FindBy(id = "manageArrivalForm:goSearchSelectedResortIdsForArrivals")
	private Link lnkGoSearch;
	
	//Filter textbox
	@FindBy(id = "filter_input")
	private Textbox txtFilter;
	
	//Search Date textbox
	@FindBy(id = "manageArrivalForm:selectDateInput")
	private Textbox txtDateSearch;
	
	//Arriving Accommodations webtable
	@FindBy(id = "arrivingAccommodationsDataTable1")
	private Webtable tblArrivingAccommodations;
	
	//Notifications button
	@FindBy(id = "manageArrivalForm:notificationID")
	private Button btnNotifications;
	
	//Arrival Resort listbox
	@FindBy(id = "adjustRoomCountAppMenu:selectedResortIdForArrival")
	private Listbox lstArrivalResort;
	
	//Loading image
	@FindBy(id = "manageArrivalForm:mngArrInPageStatusRegion:status.start")
	private Element eleLoading;
	
	//Refresh button
	@FindBy(id = "manageArrivalForm:butRefresh")
	private Link lnkRefesh;
	
	//Hide Selected button
	@FindBy(id = "manageArrivalForm:butClear")
	private Button btnHideSelected;
	
	//Element Notification On 
	@FindBy(id="manageArrivalForm:onNotofication")
	private Element onNotofication;
	
	//Element Notification Off 
	@FindBy(id="manageArrivalForm:ofNotofication")
	private Element ofNotofication;
	
	//Link Assign 
	@FindBy(linkText = "Assign")
	private Link lnkAssign;
	
	//Button inside Popup Window 
	@FindBy(id = "OffNotificationConfirmationAllPopupForm:okButtonId")
	private Button btnWarningYes;
	
	
	//*********************
    //** Build page area **
    //*********************
    /**
    * 
    * @summary Constructor to initialize the page
    * @version Created 03/03/2015
    * @author  Brajesh Ahirwar
    * @param   driver
    * @throws  NA
    * @return  NA
    * 
    */
	public ArrivalTab_AscendDescendFilter(WebDriver driver){
	       this.driver = driver;
	       ElementFactory.initElements(driver, this);
	   	datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}
	
	public boolean pageLoaded(WebDriver driver){
	       return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, lnkGoSearch);        
	}
	
	public boolean pageLoaded(WebDriver driver, Element element){
	       return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);        
	}
	
	public boolean pageLoaded(){
	       return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, lnkGoSearch);        
	}
	
	public boolean pageLoaded(Element element){
	       return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);        
	}

	public ArrivalTab_AscendDescendFilter initialize() {
		return ElementFactory.initElements(driver,
				this.getClass());
	}

	//*************************************
	//*** Arrival Tab Page Interactions ***
	//*************************************
    /**
    * 
    * @summary Method to check Ascending/Descending Order
    * @version Created 11/05/2014
    * @author  Brajesh Ahirwar
    * @param  
    * @throws NA
    * @return NA
    */

	private void clickSearchGo(){
		lnkGoSearch.jsClick(driver);
		//new ProcessingYourRequest().WaitForProcessRequest(driver);
		pageLoaded(driver);
	}

	public void enterArrivalDate(String arrivalDate){
		txtDateSearch.safeSet(arrivalDate);
		loadingImageHidden();
		pageLoaded(driver);
	}
	
	private void loadingImageHidden(){
		loopCounter = 0;
		while(eleLoading.getAttribute("style").equalsIgnoreCase("")){
			Sleeper.sleep(500);
			Assert.assertNotEquals(loopCounter++, timeout, "The loading image was not hidden after " 
					+String.valueOf(timeout/2)+ " seconds.\n");
		}
	}
	
	
	 /**
	    * 
	    * @summary Method to check Ascending/Descending Order 
	    * @version Created 03/04/2015
	    * @author  Brajesh Ahirwar
	    * @param   
	    * @throws NA
	    * @return NA
	    */	
	//Passing  Heading number and Column number 
	public void checkAscendingDecendingOrder_VIP()
	{
		checkAscendingDecendingOrder(1,2);
	}
	
	public void checkAscendingDecendingOrder_AdaAlert()
	{
		checkAscendingDecendingOrder(2,3);
	
	}
	public void checkAscendingDecendingOrder_Name()
	{
		checkAscendingDecendingOrder(3,4);
	}
	public void  checkAscendingDecendingOrder_ReservationNumber()
	{
		checkAscendingDecendingOrder(4,5);
	    
	}
	
	public void  checkAscendingDecendingOrder_AccommStatus()
	{
		checkAscendingDecendingOrder(5,6);
	
	}
	public void  checkAscendingDecendingOrder_Room()
	{
		
		checkAscendingDecendingOrder(6,7);
	}
	public void  checkAscendingDecendingOrder_HKStatus()
	{
		checkAscendingDecendingOrder(7,8);
	
	}
	public void  checkAscendingDecendingOrder_Notification()
	{
		
		checkAscendingDecendingOrder(8,9);
	}
	
	public void  checkAscendingDecendingOrder_DtrOlci()
	{
		checkAscendingDecendingOrder(9 ,10);
	
	}
	
	public void  checkAscendingDecendingOrder_ETA()
	{
		checkAscendingDecendingOrder(10,11);
	
	}
	
	public void  checkAscendingDecendingOrder_ArrivalStatus()
	{
		checkAscendingDecendingOrder(11,12);
	
	}
	
	public void  checkAscendingDecendingOrder_Source()
	{
		checkAscendingDecendingOrder(12,13);
	
	}
	public void  checkAscendingDecendingOrder_ETD()
	{
		checkAscendingDecendingOrder(13,14);
	
	}
	
	public void  checkAscendingDecendingOrder_Gruop()
	{
		checkAscendingDecendingOrder(14,16);
	
	}
	

	public void resrvationNavigation(int i)
	{
		clickRefresh();
	}

	
	private void clickRefresh(){
		lnkRefesh.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
 /**
	    * 
	    * @summary Method to check ascending/ Descending order 
	    * @version Created 03/05/2014
	    * @author  Brajesh Ahirwar
	    * @param   Table heading number , Table column number
	    * @throws  NA
	    * @return  NA
	    */	
	public void checkAscendingDecendingOrder(int image,int td )
	{
		  
		  initialize();
		  List<String> beforeAscending = new ArrayList<String>();
		  List<String> AfterAscending  = new ArrayList<String>();
		  List<String> ListAfterClick  = new ArrayList<String>();
		  List<String> ListDescending  =  new ArrayList<String>();
		  List<String> AfterDescending = new ArrayList<String>();
		  Sleeper.sleep(6000);
	      beforeAscending = ad_order(td);
	      Collections.sort(beforeAscending); 
          AfterAscending.addAll(beforeAscending);
          List <WebElement> allImages  = driver.findElements(By.className("tablesorter-icon"));
	 try
		 {
		  WebElement img =  allImages.get(image);
		  Sleeper.sleep(5000);
		  img.click();
		  pageLoaded(tblArrivingAccommodations);
		  ListAfterClick=ad_order(td);
		  Sleeper.sleep(10000);
		  matchListElement(AfterAscending,ListAfterClick,"Ascending Order");
		  Collections.sort(AfterAscending, Collections.reverseOrder());
		  ListDescending.addAll(AfterAscending);
		  Sleeper.sleep(10000);
		  img.click();
		  pageLoaded(tblArrivingAccommodations);
		  AfterDescending=ad_order(td);
		  Sleeper.sleep(10000);
		  matchListElement(ListDescending,AfterDescending, "Descending Order");
		 }catch(Exception e)
		{
		  e.printStackTrace();
	    }

	}
	
	/**
	    * 
	    * @summary Method to Add column element into list
	    * @version Created 03/05/2014
	    * @author  Brajesh Ahirwar
	    * @param   Table column number
	    * @throws  NA
	    * @return  List of Element
	    */	
	
    public List<String> ad_order(int j)
    {
		 initialize();
		 pageLoaded(tblArrivingAccommodations);
		 WebElement table = driver.findElement(By.id("arrivingAccommodationsDataTable1")); 
		 List<String> beforeAscending = new ArrayList<String>();
		 // Now get all the TR elements from the table 
		 List<WebElement> allRows = table.findElements(By.tagName("tr"));
		 int rows = allRows.size();
		 for(int i = 1 ; i<rows;i++)
		 {
		  String actual= driver.findElement(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["+i+"]/td["+j+"]")).getText().trim();
		  beforeAscending.add(actual);
		 }
	 return beforeAscending;
	}
    
    /**
	    * 
	    * @summary Method to match elements After click on ascending/Descending order
	    * @version Created 03/05/2014
	    * @author  Brajesh Ahirwar
	    * @param   Lists of elements arranged in order,Lists of elements  added after clicked, Ascending/Descending order 
	    * @throws  NA
	    * @return  List of Element
	    */	
	public void matchListElement(List<String> elementArrangedinOrder, List<String> Afterclick,String Order)
	{
		initialize();
		int size1 = elementArrangedinOrder.size();
		
		if(size1>20)
		{
			for(int j = 0 ; j <20;j++)
			{
		    	 String a = elementArrangedinOrder.get(j).trim();
		    	 String  b = Afterclick.get(j).trim();
		    	 Assert.assertEquals(a.equals(b), true, "Expected "+a+" and Actual "+b+"Value :"+Order); 
	    	 
	        }
	    }else
		{
			for(int j = 0 ; j< size1;j++)
		     {
		    	 String a = elementArrangedinOrder.get(j);
		    	 String  b = Afterclick.get(j);
		    	 Assert.assertEquals(a.equals(b), true, "Expected "+a+" and Actual "+b+"Value :"+Order); 
		     }
        }
        new ProcessingYourRequest().WaitForProcessRequest(driver);
		
	}
    
	 /**
	    * 
	    * @summary Method to Validate Notification Turn single reservation On
	    * @version Created 03/05/2014
	    * @author  Brajesh Ahirwar
	    * @param   Reservation Number , Arrival Date
	    * @throws  NA
	    * @return  NA
	    */	
    public void notificationTurnSingleReservationOn(String reservationNumber,String arrivalDate)
    {
	    	boolean notificationOn= notificationTurn(reservationNumber,arrivalDate,"On");
	    	Assert.assertEquals(notificationOn, true, "Expected Value true and Found "+notificationOn);
	    	mouseOverClick("Off");
	    	Sleeper.sleep(500);
	    	boolean notificationOff= notificationTurn(reservationNumber,arrivalDate,"Off");
	    	Assert.assertEquals(notificationOff, true, "Expected Value true and Found "+notificationOff);
	    	
    }
    /**
	    * 
	    * @summary Method to Validate Notification Turn single reservation Off
	    * @version Created 03/16/2014
	    * @author  Brajesh Ahirwar
	    * @param   Rervation Number and Date of Arrival 
	    * @throws  NA
	    * @return  NA
	    */	
    public void notificationTurnSingleReservationOff(String reservationNumber,String arrivalDate)
    {
    	boolean notificationOn= notificationTurn(reservationNumber,arrivalDate,"Off");
    	Assert.assertEquals(notificationOn, true, "Expected Value true and Found "+notificationOn);
    	mouseOverClick("On");
    	Sleeper.sleep(500);
    	boolean notificationOff= notificationTurn(reservationNumber,arrivalDate,"On");
    	Assert.assertEquals(notificationOff, true, "Expected Value true and Found "+notificationOff);
    }
    
    
   //Filter by reservation number and click on the check box
    public boolean notificationTurn(String filter,String arrivalDate,String notification)

    {
    	
    		initialize();
    		clickRefresh();
    		enterArrivalDate(arrivalDate);
    		pageLoaded(driver,tblArrivingAccommodations);
    	    clickSearchGo();
    	    Sleeper.sleep(2000);
    	    pageLoaded(driver,tblArrivingAccommodations);
    	    //Enter search text in search input box 
    	    txtFilter.safeSet(filter);
    	    pageLoaded(driver,tblArrivingAccommodations);
    	    boolean notificationStatus = false;
    	    WebElement table = driver.findElement(By.id("arrivingAccommodationsDataTable1")); 
    	    
    	    // Now get all the TR elements from the table 
    	    List<WebElement> allRows = table.findElements(By.tagName("tr"));
    	    int rows = allRows.size();
    	    
            for(int i = 1 ; i<rows;i++){
            //Get the Reservation Number from Table/TR/TD/ Reservation number and  store into String reservationNumber	
      	    String reservationNumber= driver.findElement(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["+i+"]/td[5]")).getText().trim();
      	    //Match with the actual(reservationNumber) to Filter(coming form expected(Filter))
      	    if(reservationNumber.equalsIgnoreCase(filter))
      	     {
      	    	 
      	       //Actual and Expected reservation number got matched than Select the checkbox next to single staged reservation for updating Notification from Off to On
      	         driver.findElement(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["+i+"]/td[1]/span/input")).click();
      	         pageLoaded(driver);
      	         //Get the notification for selected reservation
      	    	 String tdNotification= driver.findElement(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["+i+"]/td[9]")).getText().trim();
      	    	 pageLoaded(driver);
      	         if(tdNotification.equalsIgnoreCase(notification))
      	         {
      	        	 return notificationStatus=true;
      	         }
      	         
      	    	 break;
      	       }
             }
       return  notificationStatus ;
     }
  //Mouse over Notification button and click on the element On or Off
    public void mouseOverClick(String notification )
    {   
	        JavascriptExecutor js = (JavascriptExecutor) driver;  
	    	String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";  
	    	js.executeScript(mouseOverScript, btnNotifications);  
	    	Sleeper.sleep(100);
	    	
	        if(notification=="Off")
	        {
	         ofNotofication.jsClick(driver);
	        }
	        else
	        {
	    	 onNotofication.jsClick(driver);
	        }
       
	        Sleeper.sleep(3000);
			new ProcessingYourRequest().WaitForProcessRequest(driver);
	    	initialize();    	
	    	ArrayList<String> tabs;
	        tabs = new ArrayList<String>(driver.getWindowHandles());
	        driver.switchTo().window(tabs.get(0));
	        btnWarningYes.jsClick(driver);
    }

    /**
	    * 
	    * @summary Method to Validate Notification Turn Multiple reservation On 
	    * @version Created 03/16/2014
	    * @author  Brajesh Ahirwar
	    * @param   Rervation Numbers  and Date of Arrival(Date should be same of all the reservation number)
	    * @throws  NA
	    * @return  
	    */	
    public void notificationTurnMultiReservationOn(List<String> reservationNumber, String arrivalDate)
    {
    	initialize();
    	clickRefresh();
    	enterArrivalDate(arrivalDate);
    	pageLoaded(driver,tblArrivingAccommodations);
        clickSearchGo();
    	Sleeper.sleep(500);
        for(int m= 0 ; m<reservationNumber.size(); m++)
    	{
           
        	boolean notificationOn =  multipleNotification(reservationNumber.get(m).trim(),"On");	
        	Assert.assertEquals(notificationOn, true, "Reservation Number " +reservationNumber.get(m)+"Notification :"+notificationOn);
         }
        mouseOverClick("Off");
    	Sleeper.sleep(500);
        clickRefresh();
        Sleeper.sleep(500);
        initialize();
    	pageLoaded(driver,tblArrivingAccommodations);
    	enterArrivalDate(arrivalDate);
    	pageLoaded(driver,tblArrivingAccommodations);
        clickSearchGo();
    	
        for(int n= 0 ; n<reservationNumber.size(); n++)
    	{
            
        	boolean notificationOn =  multipleNotification(reservationNumber.get(n).trim(),"Off");	
        	Assert.assertEquals(notificationOn, true, "Reservation Number " +reservationNumber.get(n)+"Notification :"+notificationOn);
    	}
    }
    /**
	    * 
	    * @summary Method to Validate Notification Turn Multiple reservation Off 
	    * @version Created 03/16/2014
	    * @author  Brajesh Ahirwar
	    * @param   Rervation Numbers  and Date of Arrival(Date should be same of all the reservation number)
	    * @throws  NA
	    * @return  
	    */	
    
    
    public void notificationTurnMultiReservationOff(List<String> reservationNumber, String arrivalDate)
    {
    	initialize();
    	clickRefresh();
    	enterArrivalDate(arrivalDate);
    	pageLoaded(driver,tblArrivingAccommodations);
        clickSearchGo();
        pageLoaded(driver,tblArrivingAccommodations);
        for(int x= 0 ; x<reservationNumber.size(); x++)
    	{
            boolean notificationOn =  multipleNotification(reservationNumber.get(x).trim(),"Off");	
        	Assert.assertEquals(notificationOn, true, "Reservation Number " +reservationNumber.get(x)+"Notification :"+notificationOn);
        	
        }
        
        mouseOverClick("On");
    	Sleeper.sleep(500);
        clickRefresh();
        Sleeper.sleep(500);
        initialize();
    	pageLoaded(driver,tblArrivingAccommodations);
    	enterArrivalDate(arrivalDate);
    	pageLoaded(driver,tblArrivingAccommodations);
        clickSearchGo();
        Sleeper.sleep(500);
        initialize();
        for(int y= 0 ; y<reservationNumber.size(); y++)
    	{
           
        	boolean notificationOn =  multipleNotification(reservationNumber.get(y).trim(),"On");	
        	Assert.assertEquals(notificationOn, true, "Reservation Number " +reservationNumber.get(y)+"Notification :"+notificationOn);
         }
    }
   public boolean multipleNotification(String resNumber,String notification)
    {
    	Sleeper.sleep(1000);
    	pageLoaded(driver,tblArrivingAccommodations);
        boolean notificationStatus = false;
        WebElement table = driver.findElement(By.id("arrivingAccommodationsDataTable1")); 
        
        // Now get all the TR elements from the table 
        List<WebElement> allRows = table.findElements(By.tagName("tr"));
        int rows = allRows.size();
        
        for(int i = 1 ; i<rows;i++)
        {
        //Get the Reservation Number from Table/TR/TD/ Reservation number and  store into String reservationNumber	
    	 String reservationNumber= driver.findElement(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["+i+"]/td[5]")).getText().trim();
    	//Match with the actual(reservationNumber) to Filter(coming form expected(Filter))
    	if(reservationNumber.equalsIgnoreCase(resNumber))
    	 {
    	    	 
    	 //Actual and Expected reservation number got matched than Select the checkbox next to single staged reservation for updating Notification from Off to On
    	  driver.findElement(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["+i+"]/td[1]/span/input")).click();
    	  pageLoaded(driver);
    	  //Get the notification for selected reservation
    	  String tdNotification= driver.findElement(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["+i+"]/td[9]")).getText().trim();
    	  pageLoaded(driver);
    	  if(tdNotification.equalsIgnoreCase(notification.trim()))
    	 {
    	  return notificationStatus=true;
    	 }
    	         
    	  break;
    	}
       }
     return  notificationStatus ;
   }
    
   /**
    * 
    * @summary Method to Validate Selected Hide for single reservation number 
    * @version Created 03/16/2014
    * @author  Brajesh Ahirwar
    * @param   Rervation Number and Date of Arrival
    * @throws  NA
    * @return  
    */	
    public void SelectedHideSingleReservation(String filter,String arrivalDate)
    {
	    	//Pass reservation number and Arrival date
	    	hideReservation(filter, arrivalDate);
	    	Sleeper.sleep(1000);
	        btnHideSelected.jsClick(driver);
	    	Sleeper.sleep(2000);
	    	try
	    	{
	    	driver.findElement(By.linkText(filter)).isDisplayed();
	    	Assert.assertEquals(false, true, "Reservation Number " +filter+" is Found Test case is Fail");	
	    	}catch(Exception e)
	    	{
	    	Assert.assertEquals(true, true, "Reservation Number " +filter+" is Not Found Test case is Pass");
	    	}

    }
    /**
	    * 
	    * @summary Method to Validate Hide Multiple reservation 
	    * @version Created 03/17/2014
	    * @author  Brajesh Ahirwar
	    * @param   Rervation Numbers  and Date of Arrival(Date should be same of all the reservation number)
	    * @throws  NA
	    * @return  NA
	    */	
    public void SelectedHideMultipleReservation(List<String> reservationNumber,String arrivalDate)
    {
    	
    	initialize();
    	//click on refresh button
    	clickRefresh();
    	//Select Arrival Date 
    	enterArrivalDate(arrivalDate);
    	pageLoaded(driver,tblArrivingAccommodations);
        clickSearchGo();
      //Get the reservation number from list and pass to selectReservationToSelectedHide function 
        for(int m= 0 ; m<reservationNumber.size(); m++)
    	{
          selectReservationToSelectedHide(reservationNumber.get(m).trim());	
    	}
        
        Sleeper.sleep(1000);
        btnHideSelected.jsClick(driver);
        Sleeper.sleep(1000);
        
        for(int m= 0 ; m<reservationNumber.size(); m++)
    	{
        	try
        	{
                driver.findElement(By.linkText(reservationNumber.get(m).trim())).isDisplayed();
                Assert.assertEquals(false, true, "Reservation Number " +reservationNumber.get(m)+" is Found Test case is Fail");	
        	}catch(Exception e)
        	{
        		Assert.assertEquals(true, true, "Reservation Number " +reservationNumber.get(m)+" is Not Found Test case is Pass");
        	}
         }
    }

    public void selectReservationToSelectedHide(String reservationnumber)
    {
    		WebElement table = driver.findElement(By.id("arrivingAccommodationsDataTable1")); 
    		// Now get all the TR elements from the table 
    		List<WebElement> allRows = table.findElements(By.tagName("tr"));
    		int rows = allRows.size();
        
    		for(int i = 1 ; i<rows;i++)
    		{
            //Get the Reservation Number from Table/TR/TD/ Reservation number and  store into String reservationNumber	
    	    String reservationNumber= driver.findElement(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["+i+"]/td[5]")).getText().trim();
    	    //Match with the actual(reservationNumber) to Filter(coming form expected(Filter))
    	    if(reservationNumber.equalsIgnoreCase(reservationnumber))
    	     {
    	         //Actual and Expected reservation number got matched than Select the checkbox next to single staged reservation for updating Notification from Off to On
    	    	 driver.findElement(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["+i+"]/td[1]/span/input")).click();
    	         break; 
    	     }
    		}
    }



    //
    public void hideReservation(String filter,String arrivalDate)
    {
    	initialize();
    	//Refresh the Page 
    	clickRefresh();
    	//Arrival date from calendar 
    	enterArrivalDate(arrivalDate);
    	pageLoaded(driver,tblArrivingAccommodations);
        clickSearchGo();
        Sleeper.sleep(2000);
        pageLoaded(driver,tblArrivingAccommodations);
        //Enter search text in search input box 
        txtFilter.safeSet(filter);
        pageLoaded(driver,tblArrivingAccommodations);
        WebElement table = driver.findElement(By.id("arrivingAccommodationsDataTable1")); 
        // Now get all the TR elements from the table 
        List<WebElement> allRows = table.findElements(By.tagName("tr"));
        int rows = allRows.size();
        
        for(int i = 1 ; i<rows;i++)
        {
            //Get the Reservation Number from Table/TR/TD/ Reservation number and  store into String reservationNumber	
    	    String reservationNumber= driver.findElement(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["+i+"]/td[5]")).getText().trim();
    	    //Match with the actual(reservationNumber) to Filter(coming form expected(Filter))
    	     if(reservationNumber.equalsIgnoreCase(filter))
    	     {
    	         //Actual and Expected reservation number got matched than Select the checkbox next to single staged reservation for updating Notification from Off to On
    	    	 driver.findElement(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["+i+"]/td[1]/span/input")).click();
    	         break;
    	     }
         }

    }
    
    
    
    
    
     	
}

