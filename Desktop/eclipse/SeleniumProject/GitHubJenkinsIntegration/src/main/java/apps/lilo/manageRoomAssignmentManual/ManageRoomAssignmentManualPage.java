package apps.lilo.manageRoomAssignmentManual;

import java.util.List;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.WebDriverSetup;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Link;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import selenium.common.CustomVerification;
import utils.Datatable;
import utils.Sleeper;

/**
* @summary Contains the elements and methods to interact with the LILO UI Manage Room Assignment - Manual page
* @version Created 12/09/2014
* @author Waightstill W. Avery
*/
public class ManageRoomAssignmentManualPage {
	// **************************
	// *** Manage Room Assignment - Manual Elements ***
	// **************************
	private int timeout = WebDriverSetup.getDefaultTestTimeout();
	private int loopCounter;
	private CustomVerification custom = new CustomVerification();
	private Datatable datatable = new Datatable();
	private WebDriver driver;
	// **************************
	// *** Manage Room Assignment - Manual Elements ***
	// **************************

	// Auto Assignment button
	@FindBy(id = "assignRoomAutoForm:autoAssignButtonId")
	private Link lnkAutoAssignment;

	// Search To Add button
	@FindBy(id = "assignRoomManualForm:searchToAddButtonId")
	private Link lnkSearchToAdd;

	// Available Rooms Find button
	@FindBy(id = "matchingRoomRackViewForm:findButtonId")
	private Link lnkAvailableRoommsFind;

	// Matching Rooms webtable
	@FindBy(id = "matchingRoomRackViewForm:matchingRoomTableId")
	private Webtable tblMatchingRooms;

	// Matching searched Data within Table
	@FindBy(id = "assignRoomManualForm:roomReservationList")
	private Webtable tblRoomsReservationList;

	// Matching Rooms Search Results Scroller webtable
	@FindBy(id = "matchingRoomRackViewForm:scrollerRack_table")
	private Webtable tblSearchResultsScroller;

	// Alternate Room tab
	@FindBy(id = "alternateRoomsAssignTabPanel_lbl")
	private Element eleAlternateRooms;

	// Alternate Room webtable
	@FindBy(id = "alternateRoomRackViewForm:alternateRoomTableId")
	private Webtable tblAlternateRooms;

	// Matching Rooms tab
	@FindBy(id = "matchingRoomsAssignTabPanel_lbl")
	private Element eleMatchingRooms;

	// Dual Monitor button
	@FindBy(id = "assignRoomManualResSearchForm:popOutButtonId")
	private Button btnDualMonitorPopup;
	/*
	 * Reservation Search Criteria elements
	 */
	// Reservation Number textbox
	@FindBy(id = "searchResvForm:resId")
	private Textbox txtReservationNumber;

	// First Name textBox
	@FindBy(id = "searchResvForm:guestFirstNameId")
	private Textbox txtFirstName;

	// Last Name textBox
	@FindBy(id = "searchResvForm:guestLastNameId")
	private Textbox txtLastName;

	// ETA
	@FindBy(id = "searchResvForm:arrivalDateInputTime")
	private Textbox txtArrivalDateInputTime;

	// Length of Stay
	@FindBy(id = "searchResvForm:durationId")
	private Textbox txtLengthofStay;

	// Find button
	@FindBy(id = "searchResvForm:repListButtonId")
	private Link lknReservationSearchFind;

	// Group Search link
	@FindBy(id = "searchResvForm:groupdCodeLinkId")
	private Link lnkGroupSearch;
	/*
	 * Group Search
	 */
	// Group Number textbox
	@FindBy(id = "groupSearchForm:grpSearchGroupCodeID")
	private Textbox txtGroupNumber;

	// Input box Search By GroupCode/MG/TW
	@FindBy(id = "searchResvForm:groupCodeID")
	private Textbox txtGroupCodeMGTW;

	// Group Search Search button
	@FindBy(id = "groupSearchForm:groupSearchButtonId")
	private Button btnGroupSearchSearch;

	// Group Search Select button
	@FindBy(id = "groupSearchForm:selectGroupButtonID")
	private Button btnGroupSearchSelect;

	// Group Search Search Results webtable
	@FindBy(id = "groupSearchForm:groupListTableID")
	private Webtable tblGroupSearchSearchResults;

	// Arrival Date
	@FindBy(id = "searchResvForm:arrivalDateInput")
	private Textbox txtArrivalDate;

	// Departure Date
	@FindBy(id = "searchResvForm:depDateInput")
	private Textbox departureDate;
	// Assign Button
	@FindBy(id = "assignRoomAutoForm:assignButtonId")
	private Button btnAssignRoom;
	// Check box reservation
	@FindBy(id = "assignRoomManualForm:roomReservationList:0:rowCheckBoxNewId")
	private Checkbox chkReservationnumber;

	 //Assigned Room  Number
    @FindBy(id = "assignRoomManualForm:roomReservationList:0:roomCmntsId")
	private Textbox txtRoomNumber; 
	/*
	 * Dual Monitor elements
	 */
	// Search To Add button
	@FindBy(id = "newAssignRoomManualForm:newSearchToAddButtonId")
	private Link lnkSearchToAdd_Dual;
	// *********************
	// ** Build page area **
	// *********************

    /**
    * 
    * @summary Constructor to initialize the page
    * @version Created 12/09/2014
    * @author Waightstill W Avery
    * @param  driver
    * @throws NA
    * @return NA
    * 
    */
	public ManageRoomAssignmentManualPage(WebDriver driver){
		this.driver = driver;	
		ElementFactory.initElements(driver, this);  
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, lnkAutoAssignment);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	public ManageRoomAssignmentManualPage initialize() {
		return ElementFactory.initElements(driver,
				this.getClass());
	}

	//**************************************
    //*** Manage Room Assignment - Manual Page Interactions ***
    //**************************************

    public void clickSearchToAdd(){
           isElementNull(lnkSearchToAdd, "The Search to Add button was not found after ["+String.valueOf(timeout)+"] seconds.");
           lnkSearchToAdd.syncVisible(driver);
           lnkSearchToAdd.jsClick(driver);
           new ProcessingYourRequest().WaitForProcessRequest(driver);
           Sleeper.sleep(3000);
           isElementNull(txtReservationNumber, "The reservation text box was not displayed after ["+String.valueOf(timeout)+"] seconds.");
    }
    
    private void clickSearchToAdd_DualMonitor(){
           isElementNull(lnkSearchToAdd_Dual, "The dual monitor search to add button was not displayed after ["+String.valueOf(timeout)+"] seconds.");
           
           lnkSearchToAdd_Dual.jsClick(driver);
           new ProcessingYourRequest().WaitForProcessRequest(driver);
           Sleeper.sleep(3000);
           isElementNull(txtReservationNumber, "The reservation text box was not displayed after ["+String.valueOf(timeout)+"] seconds.");
    }
    
    private void isElementNull(Element element, String errorMessage){
           int timeoutCounter = 0;
           
           do{
                  Sleeper.sleep(1000);
                  Assert.assertNotEquals(timeoutCounter++, timeout, errorMessage);
                  pageLoaded(element);
                  initialize();
           }while(element == null);
    }
    
    private void enterReservationNumberForSearch(String reservationNumber){
           isElementNull(txtReservationNumber, "The reservation number textbox was not found after ["+String.valueOf(timeout)+"] seconds");
           txtReservationNumber.set(reservationNumber);
    }
    
    private void enterFirstnameandLastname(String firstName, String lastName)
    {
           isElementNull(txtFirstName, "The reservation number textbox was not found after ["+String.valueOf(timeout)+"] seconds");
           isElementNull(txtLastName, "The reservation number textbox was not found after ["+String.valueOf(timeout)+"] seconds");
        txtLastName.set(lastName);
           txtFirstName.set(firstName);
  }
    
    private void clickReservationSearchFind(){
    	
    	   lknReservationSearchFind.syncEnabled(driver, 10, false);
           lknReservationSearchFind.jsClick(driver);
           new ProcessingYourRequest().WaitForProcessRequest(driver);
    }
    //Enter reservation number and search and match with actual reservation number
    public void searchForReservation(String reservationNumber){
           clickSearchToAdd();
           enterReservationNumberForSearch(reservationNumber);
           clickReservationSearchFind();
           matchReservationNumber(reservationNumber);
    }
    //Match with Table element after search
    public void matchReservationNumber(String expected_Value)
    {
           String Actual_value = new ElementImpl(driver.findElement(By.xpath("//table[@id='assignRoomManualForm:roomReservationList']/tbody/tr[1]/td[2]/a"))).getText().trim();
           custom.verifyTrue(Actual_value.equalsIgnoreCase(expected_Value), "The current search results page ["+Actual_value+"] is not what was expected["+expected_Value+"].");
 }
    //Search First name and Last Name 
    public void searchForFirstNameandLastName(String firstName, String lastName,String arrivalDate)
    {
           clickSearchToAdd();
           enterFirstnameandLastname(firstName,lastName);
           enterArrivalDate(arrivalDate);
           clickReservationSearchFind();
           matchfirstnamelastname(firstName,lastName);
 }
    
    //Match with Table element after search
           public void matchfirstnamelastname(String firstName, String lastName)
           {
                  String Actual_value = new ElementImpl(driver.findElement(By.xpath("//table[@id='assignRoomManualForm:roomReservationList']/tbody/tr[1]/td[2]/span"))).getText().trim();
                  String[] split = Actual_value.split(",");
                  String first_Name= split[1].trim();
                  String last_Name = split[0].trim();
               Assert.assertEquals(first_Name.equalsIgnoreCase(firstName), true, " The First Name match serach result ["+first_Name+"] is not what was expected["+firstName+"].");
                  Assert.assertEquals(last_Name.equalsIgnoreCase(lastName), true, "The Last Name match serach result ["+last_Name+"] is not what was expected["+lastName+"].");
        }
    public void enterArrivalDate(String arrivalDate){
               txtArrivalDate.safeSet(arrivalDate);
               pageLoaded();
           }
    
    public void enterDepartureDate(String arrivalDate){
           departureDate.safeSet(arrivalDate);
        pageLoaded();
    }
    public void SearchtoAddByArrivalDateManualDateEntry(String searchDate, String matchingDate)
    {      
              clickSearchToAdd();
              pageLoaded(txtArrivalDate);
              enterArrivalDate(searchDate);
              pageLoaded(txtGroupCodeMGTW);
              clickReservationSearchFind();
              pageLoaded(tblRoomsReservationList);
              matchwithColumnData(matchingDate,8); 
    }
    
    public void SearchtoAddByDepartureDatewithETD(String arrivalDate,String departureDate ,String matcharrival,String matchDeparture)
    {      
              clickSearchToAdd();
              pageLoaded(txtArrivalDate);
              enterArrivalDate(arrivalDate);
              pageLoaded(txtGroupCodeMGTW);
              enterDepartureDate(departureDate);
              pageLoaded(txtGroupCodeMGTW);
              clickReservationSearchFind();
              pageLoaded(tblRoomsReservationList);
              matchwithColumnData(matcharrival,8); 
              matchwithColumnData(matchDeparture,9); 
    }
    public void matchwithColumnData(String expected, int td)
    {
    
           WebElement table = driver.findElement(By.id("assignRoomManualForm:roomReservationList")); 
     
     // Now get all the TR elements from the table 
     List<WebElement> allRows = table.findElements(By.tagName("tr"));
      
      for(int i = 1 ; i< allRows.size();i++)
      {
      
        String actual= driver.findElement(By.xpath("//table[@id='assignRoomManualForm:roomReservationList']/tbody/tr["+i+"]/td["+td+"]/span")).getText().trim();
       
        Assert.assertEquals(actual.equalsIgnoreCase(expected), true, "Expected Value ["+expected+"]is Matched with  Actual ["+actual+"]");      
       }
    }
    public void enterGroupCode(String groupCode){
           
           isElementNull(txtGroupCodeMGTW, "The group search link was not found after ["+String.valueOf(timeout)+"] seconds.");
        txtGroupCodeMGTW.syncVisible(driver, 20, false);
           txtGroupCodeMGTW.set(groupCode);
        pageLoaded();
    }
    public void SearchtoAddByGroupCodesearch(String arrivalDate, String groupCode)
    {
              clickSearchToAdd();
              pageLoaded(txtArrivalDate);
              enterArrivalDate(arrivalDate);
              pageLoaded(txtGroupCodeMGTW);
              enterGroupCode(groupCode);
              clickReservationSearchFind();
              pageLoaded(tblRoomsReservationList);
              matchwithColumnData(groupCode,4); 
    
    }
    
    public void SearchtoAddByArrivalDatewithETA(String arrivalDate, String eta)
    {
              clickSearchToAdd();
              pageLoaded(txtArrivalDate);
              enterArrivalDate(arrivalDate);
              pageLoaded(txtArrivalDateInputTime);
              isElementNull(txtArrivalDateInputTime, "The group search link was not found after ["+String.valueOf(timeout)+"] seconds.");
              txtArrivalDateInputTime.syncVisible(driver, 20, false);
              txtArrivalDateInputTime.set(eta);
              pageLoaded();
              clickReservationSearchFind();
              
              pageLoaded(tblRoomsReservationList);
              matchwithColumnData(eta,5); 
  }
    
    public void SearchtoAddByArrivalDateAndLengthofStay(String arrivalDate, String length)
    {
              clickSearchToAdd();
              pageLoaded(txtArrivalDate);
              enterArrivalDate(arrivalDate);
              pageLoaded(txtLengthofStay);
              isElementNull(txtLengthofStay, "The group search link was not found after ["+String.valueOf(timeout)+"] seconds.");
              txtLengthofStay.syncVisible(driver, 20, false);
              txtLengthofStay.set(length);
              pageLoaded();
              clickReservationSearchFind();
              
              pageLoaded(tblRoomsReservationList);
              matchwithColumnData(length,10); 
  }
    
    public void searchForReservation_DualMonitor(String reservationNumber){
           clickSearchToAdd_DualMonitor();
           enterReservationNumberForSearch(reservationNumber);
           clickReservationSearchFind();
    }
    
    public void verifySearchCriteriaPersists(String reservationNumber){
           String newSearchResultPage = "";
           searchForReservation(reservationNumber);
           findAvailableRooms();
           newSearchResultPage = selectAnotherSearchResultPage();
           clickRoomAvailTab("alternate");
           clickRoomAvailTab("matching");
           verifySearchPagePersists(newSearchResultPage);
    }
    
    private void findAvailableRooms(){
           clickAvailRoomsFind();
    }
    
    private void clickAvailRoomsFind(){
           pageLoaded(lnkAvailableRoommsFind);
           initialize();
           lnkAvailableRoommsFind.jsClick(driver);
           new ProcessingYourRequest().WaitForProcessRequest(driver);
           isElementNull(tblMatchingRooms, "The matching rooms search results webtable was not displayed after ["+String.valueOf(timeout)+"] seconds.");
    }
    
    private String selectAnotherSearchResultPage(){
           pageLoaded(tblSearchResultsScroller);
           String newPage = "";
           List<WebElement> pageList = driver.findElements(By.xpath("//table[@id='matchingRoomRackViewForm:scrollerRack_table']/tbody/tr/td"));
           
           for(loopCounter = 0; loopCounter < pageList.size(); loopCounter++){
                  String attribute = pageList.get(loopCounter).getAttribute("class"); 
                  if((!attribute.contains("inact")) && (!attribute.contains("button"))){
                        newPage = pageList.get(loopCounter+1).getText();
                        new ElementImpl(pageList.get(loopCounter+1)).jsClick(driver);
                        new ProcessingYourRequest().WaitForProcessRequest(driver);
                        break;
                  }
           }
           pageLoaded(tblSearchResultsScroller);
           initialize();
           
           return newPage;
    }
    
    private void clickRoomAvailTab(String tab){
           Element element = null;
           
           switch (tab.toLowerCase()) {
           case "matching":
                  eleMatchingRooms.jsClick(driver);
                  element = tblMatchingRooms;
                  break;
           case "alternate":
                  eleAlternateRooms.jsClick(driver);
                  element = tblAlternateRooms;
                  break;
           default:
                  break;
           }
           new ProcessingYourRequest().WaitForProcessRequest(driver);
           isElementNull(element, "The "+tab+" rooms search results webtable was not displayed after ["+String.valueOf(timeout)+"] seconds.");
    }
    
    private void verifySearchPagePersists(String expectedPage){
           pageLoaded(tblSearchResultsScroller);
           String currentPage = "";
           List<WebElement> pageList = driver.findElements(By.xpath("//table[@id='matchingRoomRackViewForm:scrollerRack_table']/tbody/tr/td"));
           
           for(loopCounter = 0; loopCounter < pageList.size(); loopCounter++){
                  String attribute = pageList.get(loopCounter).getAttribute("class"); 
                  if((!attribute.contains("inact")) && (!attribute.contains("button"))){
                        currentPage = pageList.get(loopCounter).getText();
                        break;
                  }
           }
           Assert.assertEquals(currentPage, expectedPage, "The current search results page ["+currentPage+"] is not what was expected["+expectedPage+"].");
    }
    
    public void verifySearchCriteriaPersists_DualMonitor(String reservationNumber){
           isElementNull(btnDualMonitorPopup, "The Dual Monitor Popup window was not displayed after ["+String.valueOf(timeout)+"] seconds.");
           String parentWindow = clickDualMonitor();
           String newSearchResultPage = "";
           searchForReservation_DualMonitor(reservationNumber);
           swapToParentWindow(parentWindow);
           isElementNull(lnkAvailableRoommsFind, "The Available Rooms Find button was not found after ["+String.valueOf(timeout)+"] seconds.");
           
           findAvailableRooms();
           newSearchResultPage = selectAnotherSearchResultPage();
           clickRoomAvailTab("alternate");
           clickRoomAvailTab("matching");
           verifySearchPagePersists(newSearchResultPage);
    }
    
    public String clickDualMonitor(){
        btnDualMonitorPopup.jsClick(driver);
           return swapToDualMonitorWindow();
    }
    
    private String swapToDualMonitorWindow(){
           String winHandleBefore = driver.getWindowHandle();
           
           loopCounter = 0;
           do{
                  Sleeper.sleep(1000);
                  Assert.assertNotEquals(loopCounter++, timeout, "The second window was not found after ["+String.valueOf(timeout)+"] seconds");
           }while(driver.getWindowHandles().size() < 2);
           
           for (String winHandle : driver.getWindowHandles()) {
                  if(!winHandle.equalsIgnoreCase(winHandleBefore)){
                        driver.switchTo().window(winHandle);
                        break;
                  }
                  
           }
    return winHandleBefore;
    }
    private void swapToParentWindow(String parentWindowHandle){
           driver.switchTo().window(parentWindowHandle);
    }
    
    public void validateSearchByGroupResults(String groupNumber){
           clickSearchToAdd();
           searchAndAddAGroup(groupNumber);
           verifyGroupInSearchResults(groupNumber);
    }
    
    public void validateSearchByGroupResults_DualMonitor(String groupNumber){
        isElementNull(btnDualMonitorPopup, "The Dual Monitor Popup window was not displayed after ["+String.valueOf(timeout)+"] seconds.");
        
        clickSearchToAdd_DualMonitor();
        searchAndAddAGroup_DualMonitor(groupNumber);
        
//        swapToParentWindow(parentWindow);
        isElementNull(lnkAvailableRoommsFind, "The Available Rooms Find button was not found after ["+String.valueOf(timeout)+"] seconds.");
        
        verifyGroupInSearchResults_DualMonitor(groupNumber);
 }
 
    private void searchAndAddAGroup(String groupNumber){
           clickGroupSearch();
           searchForGroupNumber(groupNumber);
           clickGroupSearchFirstResult();
           clickGroupSearchSelect(groupNumber);
           clickReservationSearchFind();
           verifyGroupInSearchResults(groupNumber);
    }

    /* DJS - Added new functionality for Dual Monitor scenario. This was added
     * because new names of objects. */
    
    private void searchAndAddAGroup_DualMonitor(String groupNumber){
        clickGroupSearch();
        searchForGroupNumber(groupNumber);
        clickGroupSearchFirstResult();
        clickGroupSearchSelect(groupNumber);
        clickReservationSearchFind();
        verifyGroupInSearchResults_DualMonitor(groupNumber);
    }

    
    private void clickGroupSearch(){
           isElementNull(lnkGroupSearch, "The group search link was not found after ["+String.valueOf(timeout)+"] seconds.");
           lnkGroupSearch.jsClick(driver);
           new ProcessingYourRequest().WaitForProcessRequest(driver);
           isElementNull(txtGroupNumber, "The group search group number was not found after ["+String.valueOf(timeout)+"] seconds.");
    }
    
    private void enterGroupNumber(String groupNumber){
           isElementNull(txtGroupNumber, "The group search group number was not found after ["+String.valueOf(timeout)+"] seconds.");
           txtGroupNumber.set(groupNumber);
    }
    
    private void searchForGroupNumber(String groupNumber){
           enterGroupNumber(groupNumber);
           clickGroupSearchSearch();
    }
    
    private void clickGroupSearchSearch(){
           btnGroupSearchSearch.jsClick(driver);
           new ProcessingYourRequest().WaitForProcessRequest(driver);
           isElementNull(tblGroupSearchSearchResults, "The group search results webtable was not found after ["+String.valueOf(timeout)+"] seconds.");
    }
    
    private void clickGroupSearchFirstResult(){
           int rows = tblGroupSearchSearchResults.getRowCount();
           String tableRow = "";
           if(rows == 2){
                  tableRow = "tr";
           }else if(rows > 2){
                  tableRow = "tr[2]";
           }else{
                  Assert.assertNotEquals(rows, "0", "The group search did not return any resutls.");
           }
           
           new ElementImpl(driver.findElement(By.xpath("//table[@id='groupSearchForm:groupListTableID']/tbody/"+tableRow))).jsClick(driver);
           new ProcessingYourRequest().WaitForProcessRequest(driver);
    }
    
    private void clickGroupSearchSelect(String groupNumber){
           btnGroupSearchSelect.jsClick(driver);
           new ProcessingYourRequest().WaitForProcessRequest(driver);
           isElementNull(txtGroupNumber, "The group number textbox was not displayed after ["+String.valueOf(timeout)+"] seconds.");
           Assert.assertEquals(txtGroupNumber.getAttribute("value"), groupNumber, "The group number textbox did not containg the group number ["+groupNumber+"] as expected.");
    }
    
    private void verifyGroupInSearchResults(String groupNumber){
           List<WebElement> tableRows = driver.findElements(By.xpath("//table[@id='assignRoomManualForm:roomReservationList']/tbody/tr"));
           String tableRow = "";
           for(loopCounter = 0; loopCounter < tableRows.size(); loopCounter++){
                  if(tableRows.size() == 1){
                        tableRow = "tr";
                  }else{
                	  /* DJS Needed to update the logic for the tableRow value to reflect the actual location. This
                	   * was the original logic
                	   * 
                	   * tableRow = "tr["+String.valueOf(loopCounter)+1+"]";
                	   * 
                	   * It has been update to the following */
                	  
                        tableRow = "tr["+(loopCounter+1)+"]";
                  }
                  String value = new ElementImpl(driver.findElement(By.xpath("//table[@id='assignRoomManualForm:roomReservationList']/tbody/"+tableRow+"/td[4]/span"))).getText();
                  Assert.assertEquals(value, groupNumber, "The group number ["+groupNumber+"] was not found with guest ["+String.valueOf(loopCounter)+1+"].");
           }
    }
    
    /* DJS - Added new functionality for Dual Monitor scenario. This was added
     * because new names of objects. */

    private void verifyGroupInSearchResults_DualMonitor(String groupNumber){
        List<WebElement> tableRows = driver.findElements(By.xpath("//table[@id='newAssignRoomManualForm:newRoomReservationList']/tbody/tr"));
        String tableRow = "";
        for(loopCounter = 0; loopCounter < tableRows.size(); loopCounter++){
               if(tableRows.size() == 1){
                     tableRow = "tr";
               }else{
                    tableRow = "tr["+(loopCounter+1)+"]";
               }
               String value = new ElementImpl(driver.findElement(By.xpath("//table[@id='newAssignRoomManualForm:newRoomReservationList']/tbody/"+tableRow+"/td[4]/span"))).getText();
               Assert.assertEquals(value, groupNumber, "The group number ["+groupNumber+"] was not found with guest ["+String.valueOf(loopCounter)+1+"].");
        }
 }
 
    /**
	    * 
	    * @summary  Method to search a reservation, Assigned a room number in Available room pod and validate 
	    *           reservation is associated to the room 
	    *           for the days the reservation will be occupying the room in Room avail pod section Tab     
	    * @version Created 08/06/2015
	    * @author  Brajesh Ahirwar
	    * @param   reservation number
	    * @throws  Exception 
	    * @return  NA
	    * 
	    */
	
	
	public void selectReservation(String reservationNumber) throws InterruptedException
	{
		searchForReservation(reservationNumber);
		clickReservationCheckBox();
		clickAvailRoomsFind();
		String roomNumber_exp =chooseRoomToAssign();
		btnRoomAssign();
		String roomNumber_act =getRoomNumber();
	    custom.verifyTrue(roomNumber_exp.equalsIgnoreCase(roomNumber_act),"Expected Room Number : ["+roomNumber_exp+"]  but Found : ["+roomNumber_act+"]");
		custom.checkForVerificationErrors();
	}

	public void clickReservationCheckBox()
	{
		chkReservationnumber.syncEnabled(driver,1,false);
	    chkReservationnumber.jsToggle(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		
	} 
    
	public void  btnRoomAssign()
	{
		btnAssignRoom.syncEnabled(driver, 1, false);
	    btnAssignRoom.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public String getRoomNumber() 
	{
		tblRoomsReservationList.syncVisible(driver, 20, false);
		initialize();
		String assignRoomNumber = "";
	    assignRoomNumber= txtRoomNumber.getAttribute("value");
	    return assignRoomNumber;
		
		
		
		
	}
	/**
	    * 
	    * @summary Method to Assign a Room Number
	    * @version Created 08/06/2015
	    * @author  Brajesh Ahirwar
	    * @param  driver
	    * @throws NA
	    * @return NA
	    * 
	    */
		public String chooseRoomToAssign(){
			tblMatchingRooms.syncVisible(driver, 2, false);
			initialize();
			int tableRowCount = tblMatchingRooms.getRowCount();
			int rowLoopCounter;
			String txtRoomNumber = "" ;
		  for(rowLoopCounter = 0; rowLoopCounter < tableRowCount-1; rowLoopCounter++){
			    //Grab the text of Element
				 Element txtMainRoomStatus = new ElementImpl(driver.findElement(By.id("matchingRoomRackViewForm:matchingRoomTableId:"+rowLoopCounter+":roomStatusId")));
				 Element txtMainRoomNumber = new ElementImpl(driver.findElement(By.id("matchingRoomRackViewForm:matchingRoomTableId:"+rowLoopCounter+":roomNumberId2")));
				
				//Grab the value associated with the next checkbox
			      String txtMainId = txtMainRoomStatus.getText().trim();
			      
			     if(txtMainId.equalsIgnoreCase("CLEAN")||txtMainId.equalsIgnoreCase("PICK U..") )
			     {
			    	
			    	 txtRoomNumber = txtMainRoomNumber.getText().trim();
			    	 Reporter.log("\b Room Number : [" + txtRoomNumber + "].\n");
			    	 txtMainRoomStatus.jsClick(driver);
			    	 break;
		          }
			}
		return txtRoomNumber;
			 
		}
	
	
    
    
    
    
    
    
}
