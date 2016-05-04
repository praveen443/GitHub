package apps.lilo.updateRoomStatusManual;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import apps.lilo.resortFunctions.ResortFunctionsPage;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Link;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

/**
* @summary Contains the page methods & objects found in the Update Room Status Manual page
* @version Created 11/11/2014
* @author Waightstill W Avery
*/

public class UpdateRoomStatusManualPage {
	//*********************************************
	//*** Update Room Status Manual Page Fields ***
	//*********************************************
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	
	//***********************************************
	//*** Update Room Status Manual Page Elements ***
	//***********************************************
	
	//Update Status button
	@FindBy(id = "searchRoomStatusForm:updateButtonId")
	private Link lnkUpdateStatus;
	
	//Room Occupancy listbox
	/**
	 * @exampleOptions VACANT, OCCUPIED
	 */
	@FindBy(id = "searchRoomStatusForm:roomOccItemListId")
	private Listbox lstRoomOccupancy;
	
	//Housekeeping Status listbox
	/**
	 * @exampleOptions CLEAN, DIRTY, LINEN CHANGE, PICK UP, INSPECTED
	 */
	@FindBy(id = "searchRoomStatusForm:housekeeperStatusListId")
	private Listbox lstHousekeepingStatus;
	
	//Find button
	@FindBy(id = "searchRoomStatusForm:findButtonId")
	private Link lnkFind;
	
	//Update Room Status webtable
	@FindBy(id = "searchRoomStatusForm:updateRoomStatusTableId")
	private Webtable tblUpdateRoomStatus;
	
	//Select All checkbox
	@FindBy(id = "searchRoomStatusForm:updateRoomStatusTableId:selectAllCheckboxId")
	private Checkbox chkSelectAll;
	
	//Search Results scroller webtable
	@FindBy(id = "searchRoomStatusForm:roomDataTableScroller_table")
	private Webtable tblDataTableScroller;
	
	//Textbox Rooms
	@FindBy(id = "searchRoomStatusForm:roomId")
	private Textbox txtRooms;
	
	//Element HK Status
	@FindBy(id = "searchRoomStatusForm:updateRoomStatusTableId:0:houseKeepStatusId")
	private Element eleHouseKeepingStatus;
	
	//Element HK Occupancy
	@FindBy(id = "searchRoomStatusForm:updateRoomStatusTableId:0:houseKeepOccId")
	private Element eleHouseKeepingOccupancy;
	
	//Link exit
	@FindBy(id = "updateRoomStatusManualExit:findButtonId")
	private Link lnkExit;
	
	// Select room checkbox
	@FindBy(id = "searchRoomStatusForm:updateRoomStatusTableId:0:checkBoxId")
	private Checkbox chkRoom;
	
	//Room Type
	@FindBy(id = "searchRoomStatusForm:updateRoomStatusTableId:0:resRoomTypeText")
	private Element eleResRoomType;
	
	//Room Number
	@FindBy(id = "searchRoomStatusForm:updateRoomStatusTableId:0:roomNumLinkId")
	private Element eleRoomNumber;
	
	
	//*********************
    //** Build page area **
    //*********************

    /**
    * 
    * @summary Constructor to initialize the page
    * @version Created 11/11/2014
    * @author Waightstill W Avery
    * @param  driver
    * @throws NA
    * @return NA
    * 
    */
	public UpdateRoomStatusManualPage(WebDriver driver){
	       this.driver = driver;
	       ElementFactory.initElements(driver, this);
	   	datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public boolean pageLoaded(WebDriver driver){
	       return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, lnkUpdateStatus);        
	}

	public boolean pageLoaded(WebDriver driver, Element element){
	       return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);        
	}

	public boolean pageLoaded(Textbox textbox){
	       return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, txtRooms);        
	}
	
	public boolean pageLoaded(Link link){
	       return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, lnkFind);        
	}
	
	public  UpdateRoomStatusManualPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}
	
	//***************************************************
	//*** Update Room Status Manual Page Interactions ***
	//***************************************************
    /**
    * 
    * @summary Method to navigate to the Update Room Status Manual page
    * @version Created 11/11/2014
    * @author Waightstill W Avery
    * @param  NA
    * @throws NA
    * @return NA
    * 
    */
	public void navigateToUpdateRoomStatusManualPage(){
		ResortFunctionsPage resortFunctions = new ResortFunctionsPage(driver);
		resortFunctions.enterUpdateRoomStatusManual();
		
		pageLoaded(driver);
	}
	
    /**
    * 
    * @summary Method to select an option from the Room Occupancy list
    * @version Created 11/11/2014
    * @author Waightstill W Avery
    * @param  option - String option to select from the list
    * @throws NA
    * @return NA
    * 
    */
	private void selectRoomOccupanyOption(String option){
		lstRoomOccupancy.select(option);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		pageLoaded(driver);
	}
	
    /**
    * 
    * @summary Method to select an option from the Housekeeping Status list
    * @version Created 11/11/2014
    * @author Waightstill W Avery
    * @param  option - String option to select from the list
    * @throws NA
    * @return NA
    * 
    */
	private void selectHousekeepingStatusOption(String option){
		if(!option.equalsIgnoreCase("")){
			lstHousekeepingStatus.select(option);
			new ProcessingYourRequest().WaitForProcessRequest(driver);
			pageLoaded(driver);
		}
	}
	
	private void clickFind(){
		lnkFind.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		pageLoaded(driver, chkSelectAll);
	}
	
    /**
    * 
    * @summary Method to check the Select All checkbox
    * @version Created 11/11/2014
    * @author Waightstill W Avery
    * @param  NA
    * @throws NA
    * @return NA
    * 
    */
	private void checkSelectAll_firstPage(){
		chkSelectAll.toggle();
		pageLoaded(driver);
	}
	
	/**
	    * 
	    * @summary Method to check the Select All checkbox on subsequent pages. The checkbox tends to remain "checked" when switching from page to page
	    * @version Created 11/11/2014
	    * @author Waightstill W Avery
	    * @param  NA
	    * @throws NA
	    * @return NA
	    * 
	    */
		private void checkSelectAll_subsequentPages(){
			chkSelectAll.toggle();
			pageLoaded(driver, chkSelectAll);
			chkSelectAll.toggle();
			pageLoaded(driver);
		}
	
    /**
    * 
    * @summary Method to navigate one page to the "right"
    * @version Created 11/11/2014
    * @author Waightstill W Avery
    * @param  NA
    * @throws NA
    * @return NA
    * 
    */
	private void navigateOnePageToTheRight(){
		int column = tblDataTableScroller.getColumnWithCellText(driver,">>");
		Element element = new ElementImpl(tblDataTableScroller.findElement(By.xpath("//tbody/tr/td["+String.valueOf(column)+"]")));
		element.focusClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		//pageLoaded(driver);
	}
	
	public void updateRoomStatus_All(String scenario){
		datatable.setVirtualtablePage("UpdateRoomStatusManual");
		datatable.setVirtualtableScenario(scenario);
		
		String roomOccupancy_search = datatable.getDataParameter("RoomOccupancy_Search");
		String hkStatus_search = datatable.getDataParameter("HKStatus_Search");
		String hkStatus_update = datatable.getDataParameter("HKStatus_Update");
		String hkOccupancy_update = datatable.getDataParameter("HKOccupancy_Update");
		
		boolean firstPage = true;
		
		selectHousekeepingStatusOption(hkStatus_search);
		selectRoomOccupanyOption(roomOccupancy_search);
		clickFind();
		
		do{
			if(firstPage){
				checkSelectAll_firstPage();
				firstPage = false;
			}else{
				checkSelectAll_subsequentPages();
			}
			clickUpdateStatus();
			
			UpdateStatusPage updateStatus = new UpdateStatusPage(driver);
			updateStatus.pageLoaded(driver);
			
			updateStatus.updateStatus(hkStatus_update, hkOccupancy_update);
			if(canNavigatePageRight()){
				navigateOnePageToTheRight();
			}
		}while(canNavigatePageRight());
	}
	
	private boolean canNavigatePageRight(){
		boolean canNavigate = true;
		
		if(tblDataTableScroller.elementWired()){
			int column = tblDataTableScroller.getColumnWithCellText(driver, ">>");
			Element element = new ElementImpl(tblDataTableScroller.findElement(By.xpath("//tbody/tr/td["+String.valueOf(column)+"]")));
			if(element.getAttribute("class").toLowerCase().contains("dsbld")){
				canNavigate = false;
			}
		}else{
			canNavigate = false;
		}
		
		return canNavigate;
	}
	
	private void clickUpdateStatus(){
		lnkUpdateStatus.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	/**
	 * @summary : Method to find the room status using roomId and 
	 * 			  also to check the accomodation and navigate to Update status page. 
	 * @author  : Praveen Namburi 
	 * @param   : String RoomNumber
	 * @version : Created 11/30/2015
	 * @return  : NA
	 */
	public void searchRoomStatusUsingRoomNumber(String RoomNumber){
	
		//Passing the string roomnumber as an argument in the method
		pageLoaded(txtRooms);
		txtRooms.safeSet(RoomNumber);
		
		//Click on link-text Find.
		clickFind();
		
		//Check all the check-boxes for the Resulted Room status
		checkSelectAll_firstPage();
		
		//Click on Update status link
		clickUpdateStatus();
	}
	
	/**
	 * @Summary : Method to get the HouseKeeping Status
	 * @author  : Praveen Namburi
	 * @version : Created 11/30/2015
	 * @return  : String HouseKeepingStatus
	 */
	public String getHouseKeepingStatus(){
		pageLoaded(driver);
		String HouseKeepingStatus = eleHouseKeepingStatus.getText();
		TestReporter.logStep("----> House keeping Status is : "+HouseKeepingStatus);
		
		return HouseKeepingStatus;
	  
    }
  
	/**
	 * @Summary : Method to get the HouseKeeping Occupancy
	 * @author  : Praveen Namburi
	 * @version : Created 11/30/2015
	 * @return  : String HousekeepingOccupancy 
	 */
	public String getHouseKeepingOccupancy(){
		pageLoaded(driver);
		String HouseKeepingOccupancy = eleHouseKeepingOccupancy.getText();
		TestReporter.logStep("----> House keeping Occupancy is : "+HouseKeepingOccupancy);
		
		return HouseKeepingOccupancy;
	}
  	
	/**
	 * @summary: capturing both house keeping and house occupancy values and concatenating them for future use
	 * @author : Praveen Namburi
	 * @version: Created 11/30/2015
	 * @return : String 
	 */
	public String captureHouseKeepingANDHouseOccupancy(){
		
		//Capture House keeping Occupancy value and store it in a variable
		String GetHouseOccupancy = getHouseKeepingOccupancy();
		
		//Capture House keeping status value and store it in a variable
		String GetHouseKeeping = getHouseKeepingStatus();
		
		//Concatenate both values and store them in one variable for future use
		String ConcatenateBothValues = GetHouseKeeping +" "+ GetHouseOccupancy;
		return ConcatenateBothValues;
		
	}
	
	/**
	 * @summary : Method to click on text-link Exit.
	 * @author  : Praveen Namburi
	 * @version : Created 11/30/2015
	 * @return  : NA
	 */
	public void clickLinkExit(){
		pageLoaded(lnkExit);
		lnkExit.highlight(driver);
		lnkExit.click();
		Sleeper.sleep(3000);
		//new ProcessingYourRequest().WaitForProcessRequest(driver);
		
	}
	
	
	
	private void selectRoom() {
		initialize();
		pageLoaded(driver);
		chkRoom.jsToggle(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public String updateRoomStatus_Selected(String scenario){
		datatable.setVirtualtablePage("UpdateRoomStatusManual");
		datatable.setVirtualtableScenario(scenario);
		
		String roomOccupancy_search = datatable.getDataParameter("RoomOccupancy_Search");
		String hkStatus_search = datatable.getDataParameter("HKStatus_Search");
		String hkStatus_update = datatable.getDataParameter("HKStatus_Update");
		String hkOccupancy_update = datatable.getDataParameter("HKOccupancy_Update");
		
		selectHousekeepingStatusOption(hkStatus_search);
		selectRoomOccupanyOption(roomOccupancy_search);
		clickFind();
		selectRoom();
		
		clickUpdateStatus();
		UpdateStatusPage updateStatus = new UpdateStatusPage(driver);
		updateStatus.pageLoaded(driver);
			
		updateStatus.updateStatus(hkStatus_update, hkOccupancy_update);
		TestReporter.logStep(getResRoomType());
		TestReporter.logStep(getResRoomNumber());
		String RoomType = getResRoomType();
		String RoomNumber = getResRoomNumber();
		clickLinkExit();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		return RoomType+":"+RoomNumber;
		
	}
	
	/**
	 * @summary Method to get Room Type from Update Room status manual page
	 * @author  Marella Satish
	 * @version 12/15/2015 Marella Satish - original
	 * @param  
	 * @return  NA
	 */
	public String getResRoomType(){
		pageLoaded(driver);
		String RoomType = eleResRoomType.getText();
		return RoomType;
	}
	
	/**
	 * @summary Method to get Room Number from Update Room status manual page
	 * @author  Marella Satish
	 * @version 12/15/2015 Marella Satish - original
	 * @param   
	 * @return  NA
	 */
	public String getResRoomNumber(){
		//pageLoaded(driver);
		eleRoomNumber.highlight(driver);
		String RoomNumber = eleRoomNumber.getText();
		return RoomNumber;
	}
	
	
	/**
	 * @summary Method to validate the Housekeeping Status and Housekeeping Occupancy in 
	 * Update Room status manual page
	 * @author  Marella Satish
	 * @version 12/15/2015 Marella Satish - original
	 * @param   Data table scenario
	 * @return  NA
	 */
	public void validateHousekeepingStatusAndOccupancy(String scenario){
		
		datatable.setVirtualtablePage("UpdateRoomStatusManual");
		datatable.setVirtualtableScenario(scenario);
		
		//Get Housekeeping Status and Housekeeping Occupancy from datatable
		String ExpectedHKStatus = datatable.getDataParameter("HKStatus_Update");
		String ExpectedHKOccupancy = datatable.getDataParameter("HKOccupancy_Update");
		TestReporter.logStep("ExpectedHKStatus "+ExpectedHKStatus+" s### ExpectedHKOccupancy "+ExpectedHKOccupancy);
		
		UpdateStatusPage updateStatus = new UpdateStatusPage(driver);
		updateStatus.pageLoaded(driver);
		updateStatus.updateStatus(ExpectedHKStatus, ExpectedHKOccupancy);
		
		//Grabb the Housekeeping Status and Housekeeping Occupancy from Update Room status manual page
		String ActualHKStatus = getHouseKeepingStatus();
		String ActualHKOccupancy = getHouseKeepingOccupancy();
		
		//Validating Housekeeping Status and Housekeeping Occupancy after update
		TestReporter.assertEquals(ExpectedHKStatus, ActualHKStatus, "The Housekeeping status not updated");
		TestReporter.assertEquals(ExpectedHKOccupancy, ActualHKOccupancy, "Housekeeping occupancy not updated");
		clickLinkExit();
		
	}
	
}


