package apps.dreams.travelPlanSearch;

import java.util.List;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import apps.dreams.PleaseWait.PleaseWait;
import core.FrameHandler;
import core.WindowHandler;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.CheckboxImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.TestReporter;
import utils.date.DateTimeConversion;
/**
* @summary 	Contains the methods & objects for the Dreams UI 
* 			travel plan search window
* @version 	Created 10/28/2014
* @author 	Jessica Marshall
*/
public class TravelPlanSearchPage {	
	//****************************
	//*** Content Frame Fields ***
	//****************************
	private WebDriver driver;
	private int loopCounter = 0;
	private Datatable datatable = new Datatable();
	
	//******************************
	//*** Content Frame Elements ***
	//******************************
	
	@FindBy(name = "Search")
	private Button btnSearch;
	
	@FindBy(name = "Clear")
	private Button btnClear;
	
	@FindBy(name = "travelPlanSegmentId")
	private Textbox txtReservationNum;
	
	@FindBy(name = "externalRefNumber")
	private Checkbox chkExternalRefNum;
	
	@FindBy(name = "travelPlanViewTO.travelPeriodStartDate")
	private Textbox txtArrivalDate;
	
	@FindBy(name = "travelPlanViewTO.travelPeriodEndDate")
	private Textbox txtDeptDate;
	
	@FindBy(name = "dateMatchFlag")
	private Checkbox chkDateExactMatch;
	
	@FindBy(name = "travelPlanViewTO.guestLastName")
	private Textbox txtLastName;
	
	@FindBy(name = "travelPlanViewTO.guestFirstName")
	private Textbox txtFirstName;
	
	@FindBy(name = "primaryGuestOnly")
	private Checkbox chkPrimaryGuest;
	
	@FindBy(name = "exactMatch")
	private Checkbox chkGuestExactMatch;
	
	@FindBy(name = "postalCode")
	private Textbox txtZipCode;
	
	@FindBy(name = "travelPlanViewTO.travelStatusForDisplay")
	private Listbox lstReservationStatus;
	
	@FindBy(name = "travelPlanViewTO.roomType.accommodationFacilityTO.resortCode")
	private Listbox lstResortName;
	
	@FindBy(name = "travelPlanViewTO.viplevelForDisplay")
	private Listbox lstVIPlevel;
	
	@FindBy(name = "travelPlanViewTO.childId")
	private Textbox txtChildID;
	
	@FindBy(name = "travelPlanViewTO.group.groupDescription")
	private Textbox txtGroupName;
	
	@FindBy(name = "travelPlanViewTO.group.groupCode")
	private Textbox txtGroupNum;
	
	@FindBy(name = "travelPlanViewTO.teamName")
	private Textbox txtTeamName;
	
	@FindBy(name = "travelPlanViewTO.gatheringName")
	private Textbox txtGatheringName;
	
	@FindBy(name = "travelPlanViewTO.gatheringId")
	private Textbox txtGatheringID;
	
	@FindBy(name = "travelPlanViewTO.agencyId")
	private Textbox txtAgencyID;
	
	//Search results table
	@FindBy(xpath = "//*[@id='firstLayer']/table[3]")
	private Webtable tblSearchResults;
	
	//Mass modify button in search results
	@FindBy(name = "b_modify")
	private Button btnMassModify;
	
	//Select All checkbox to select all reservations from teh search
	@FindBy(id = "b_SelectAll")
	private Checkbox chkSelectAll;
	
	@FindBy(id = "errorStr")
	private Element eleErrorMsg;
	
	@FindBy(name = "b_yes")
	private Button btnAlertOk;
	
	@FindBy(id = "wdwArrivalDate")
	private Textbox txtWdwArriveDate;
	
	@FindBy(id = "wdwDepartureDate")
	private Textbox txtWdwDepartDate;
	
	//*********************
    //** Build page area **
    //*********************

	public TravelPlanSearchPage(WebDriver driver){
		this.driver = driver;
		FrameHandler.findAndSwitchToFrame(driver, "contentFrame");
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnSearch);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	public TravelPlanSearchPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	//*****************************************
	//*** Content Frame Interactions ***
	//*****************************************
    
    public void clickSearch(){
    	pageLoaded(btnSearch);
    	initialize();
    	btnSearch.syncVisible(driver);
    	btnSearch.click();
    	PleaseWait.WaitForPleaseWait(driver);
    }

    public void clickClear(){
    	pageLoaded(btnClear);
    	initialize();
    	btnClear.syncVisible(driver);
    	btnClear.click();
    	PleaseWait.WaitForPleaseWait(driver);
    }
    
    public void clickMassModify(){
    	pageLoaded(btnMassModify);
    	initialize();
    	btnMassModify.syncVisible(driver);
    	btnMassModify.jsClick(driver);
    	PleaseWait.WaitForPleaseWait(driver);
    }
    
	/**
	 * @summary Does a search by reservation number.  If the boolean value of externalRef is true, 
	 * 			then will check the checkbox.  Arrival date & departure date are required fields so
	 * 			must enter values in those.  
	 * @version Created 10/28/2014
	 * @author 	Jessica Marshall
	 * @param 	reservationNum, externalREf, arrivalDate, deptDate
	 * @throws 	NA
	 * @return 	NA
	 */
    public void searchByReservationNum (String reservationNum, boolean externalRef, String arrivalDate, String deptDate){
    	pageLoaded(txtReservationNum);
    	initialize();
    	txtReservationNum.syncVisible(driver);
    	txtReservationNum.set(reservationNum);
    	checkExternalRefNum(externalRef);
    	txtArrivalDate.set(arrivalDate);
    	txtDeptDate.set(deptDate); 	
    	clickSearch();
    }
    
    public void searchByReservationNumAndClickResLink (String reservationNum){
    	pageLoaded(txtReservationNum);
    	initialize();
    	txtReservationNum.syncVisible(driver);
    	txtReservationNum.set(reservationNum);
    	clickSearch();
    	clickReservationFromSearchResults(reservationNum);
    }
    
	/**
	 * @summary Checks the external reference checkbox if the boolean paramter is true 
	 * @version Created 10/28/2014
	 * @author 	Jessica Marshall
	 * @param 	externalRef
	 * @throws 	NA
	 * @return 	NA
	 */
    public void checkExternalRefNum(boolean externalRef){
    	if (externalRef){
    		chkExternalRefNum.check();
    	}
    }
    
    public boolean clickReservationFromSearchResults(String reservationNum){
    	pageLoaded(tblSearchResults);
    	tblSearchResults.syncVisible(driver);
    	
    	//Get a list of of all the reservation number links in the table
    	List <WebElement> linkList = tblSearchResults.findElements(By.tagName("a"));
    	
    	//if there are not rows, then return false
    	if (linkList.isEmpty())
    		return false;
    	
    	for (WebElement element:linkList){
    		if (element.getText().equals(reservationNum)){
    			element.click();
    			tblSearchResults.syncHidden(driver);
    	    	PleaseWait.WaitForPleaseWait(driver);
    			return true; 			
    		}
    	}
    	
    	return false;
    }
    
    public void selectAllReservations(){
    	chkSelectAll.highlight(driver);
    	chkSelectAll.focus(driver);
    	chkSelectAll.toggle();
    }
    
    public boolean checkReservationFromSearchResults(String reservationNum){
    	initialize();
    	
    	tblSearchResults.syncVisible(driver);
    	
    	//Get a list of of all the rows in the table
    	//List <WebElement> rowList = tblSearchResults.findElements(By.tagName("tr"));
    	
    	//if there are not rows, then return false
/*    	if (rowList.isEmpty())
    		return false;
    	
    	//If the reservation is found, then check the check box next to it
    	for (WebElement element:rowList){
    		//get a list of the cells
    		List <WebElement> cellList = element.findElements(By.tagName("td"));
    		if (!cellList.isEmpty()){
	    		//verify the reservation number matches
	    		if (cellList.get(1).getText().equals(reservationNum)){
	    			//if it's the right row, then check the checkbox
	    			cellList.get(0).findElement(By.cssSelector("input[type='checkbox']")).click();
	    			return true;
	    		}
    		}
    	}*/
    	List <WebElement> we = driver.findElements(By.name("selected"));
    	we.size();
    	
    	//If there are no checkboxes, return false
    	if(we.size() == 0){
    		return false;
    	}
    	
    	//If the reservation is found, then check the check box next to it
    	for(loopCounter = 0; loopCounter < we.size(); loopCounter++){
    		Checkbox checkbox = new CheckboxImpl(we.get(loopCounter));
    		if(checkbox.getAttribute("onclick").contains(reservationNum)){
    			//checkbox.highlight(driver);
    			checkbox.jsToggle(driver);
    			return true;
    		}
    	}
    	Reporter.log("Reservation number: " + reservationNum + " was not found in list of search results");
    	return false;
    }

    public boolean massModifyAReservation(String reservationNum){
    	
    	//in the search results, check the reservation, if its not found, then return false
    	if (!checkReservationFromSearchResults(reservationNum))
    		return false;
    	
    	//Click the mass modify button
    	clickMassModify();
    	
    	//Verify a new Lilo window is opened & swap to it.  
    	return WindowHandler.swapToWindowWithTimeout(driver, "Lilo", 10);
    	
    }

    public boolean massModifyAllReservations(){
    	selectAllReservations();
    	
    	//Click the mass modify button
    	clickMassModify();
    	
    	//Verify a new Lilo window is opened & swap to it.  
    	return WindowHandler.swapToWindowWithTimeout(driver, "Lilo", 10);
    	
    }

    /**
	 * @summary Method to handle and Validate if TravelPlan Search without passing any search criteria 
	 * @version Created 02/16/2016
	 * @author Marella Satish
	 * @param Datatable scenario
	 * @throws NA
	 * @return NA
	 */
	 private void validateTravelPlanSearchAlert(String scenario) {
		 
		 datatable.setVirtualtablePage("ErrorMessages");
		 datatable.setVirtualtableScenario(scenario);
		 
		 String expectedError = datatable.getDataParameter("Message");
		 if ((WindowHandler.waitUntilNumberOfWindowsAre(driver, 2)) && 
				 (WindowHandler.waitUntilWindowExistsTitleContains(driver, "Alert"))) {
			 String getAlertMessage = eleErrorMsg.getText();
			 TestReporter.log("Alert Message : " +getAlertMessage);
			 TestReporter.assertEquals(expectedError, getAlertMessage, "The expected message"
			 		+ "[ "+expectedError+" ] is not same as actual message[ "+getAlertMessage+" ]");
			 btnAlertOk.highlight(driver);
			 btnAlertOk.click();
			 TestReporter.log("Alert Handled");
			WindowHandler.waitUntilWindowExistsTitleContains(driver,"Disney Reservation Entry and Management System");
	  }
	 }
    
	 public void searchForTravelPlan(String scenario){
		 datatable.setVirtualtablePage("TravelPlanSearch");
		 datatable.setVirtualtableScenario(scenario);
		 
		 String ArrivalDaysOut = datatable.getDataParameter("ArrivalDaysOut");
		 String DepartureDaysOut = datatable.getDataParameter("DepartureDaysOut");
		 String ClickSearch = datatable.getDataParameter("ClickSearch");
		 
		 pageLoaded(txtWdwArriveDate);
		 txtWdwArriveDate.syncVisible(driver);
		 if(!ArrivalDaysOut.isEmpty()){
			 try{
				 txtWdwArriveDate.safeSet(DateTimeConversion.ConvertToDate(ArrivalDaysOut));
			 }catch(Exception e){
				 txtWdwArriveDate.safeSet(ArrivalDaysOut);
			 }
		 }
		 if(!DepartureDaysOut.isEmpty()){
			 try{
				 txtWdwDepartDate.safeSet(DateTimeConversion.ConvertToDate(DepartureDaysOut));
			 }catch(Exception e){
				 txtWdwDepartDate.safeSet(DepartureDaysOut);
			 }
		 }
		 if(ClickSearch.equalsIgnoreCase("true")){
			 btnSearch.click();
		 }
		 validateTravelPlanSearchAlert(scenario);
	 }
}

