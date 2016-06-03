package apps.lilo.resortFunctions;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Element;
import core.interfaces.Link;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;

/**
* @summary Contains the page methods & objects found in the Resort Functions page
* @version Created 8/15/2014
* @author Justin Phlegar
*/
public class ResortFunctionsPage {
	// ******************************
	// *** ResortFunctions Fields ***
	// ******************************
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	
	//*************************************
	//*** Resort Function Page Elements ***
	//*************************************
	//Create Link object for Manage Arrivals-Arrival Tab
    @FindBy(id="menuForm:manageArrivalsId")
    private Link lnkManageArrivalsArrivalTab;

	//Create Link object for Manage Arrivals-Inventory Summary Tab
    @FindBy(id="menuForm:manageArrivalsInventorySummaryId")
    private Link lnkManageArrivalsInventorySummary;

	//Create Link object for Manage Room Assignment - Manual
    @FindBy(id="menuForm:manualRoomAssignmentId")
    private Link lnkManageRoomAssignment;

	//Create Link object for View Room Availability
    @FindBy(id="menuForm:viewRoomAvailabilityId")
    private Link lnkViewRoomAvailability;

	//Create Link object for Manage Room Inventory Status
    @FindBy(id="menuForm:manageRoomInventoryStatusId")
    private Link lnkManageRoomInventoryStatus;

	//Create Link object for Update Room Status Manual
    @FindBy(id="menuForm:updateRoomStatusManualId")
    private Link lnkUpdateRoomStatus;

	//Create Link object for View Room History
    @FindBy(id="menuForm:viewRoomHistoryId")
    private Link lnkViewRoomHistory;

	//Create Link object for Create Reservation
    @FindBy(id="menuForm:createreservation")
    private Link lnkCreateReservation;

	//Create Link object for Quick Book
    @FindBy(id="menuForm:quickbook")
    private Link lnkQuickBook;

	//Create Link object for Personal Magic
    @FindBy(id="menuForm:personalMagic")
    private Link lnkPersonalMagic;

	//Create Link object for Encode Band
    @FindBy(id="menuForm:reEncode")
    private Link lnkEncodeBand;

	//Create Link object for Apply Multiple Charges
    @FindBy(id="menuForm:multiplecharges")
    private Link lnkApplyMultipleCharges;

	//Create Link object for Apply Group Master Charges
    @FindBy(id="menuForm:groupMasterCharges")
    private Link lnkApplyGroupMasterCharges;

	//Create Link object for Recommend Tickets
    @FindBy(id="menuForm:recommendTicketsLink")
    private Link lnkRecommendTickets;

	//Create Link object for Void Transaction
    @FindBy(id="menuForm:performtransfer")
    private Link lnkVoidTransaction;

	//Create Link object for Will Call
    @FindBy(id="menuForm:willCallTicketsButton")
    private Link lnkWillCall;

	//Create Link object for Override Printers and Encoder
	@FindBy(id="menuForm:printersandencoders")
	private Link lnkOverridePrinterEncoder;
	
	//Create Link object for Late Checkouts
	@FindBy(id="menuForm:managelatecheckout1")
	private Link lnkLateCheckouts;
	
	//Create Link object for Bank Out
	@FindBy(linkText = "Bank Out")
	private Link lnkBankOut;
	
	//Create Link object for Preliminary Bank Out
	@FindBy(id="menuForm:j_id1759")
	private Link lnkPreliminaryBankOut;	
	
	//Create Link object for Express Check-In
	@FindBy(linkText="Express Check-In")
	private Link lnkExpressCheckIn;
	
	@FindBy(linkText = "Bank In")
	private Link lnkBankIn;
	
	@FindBy(linkText = "Switch Location")
	private Link lnkSwitchLocations;
	
	//*********************
    //** Build page area **
    //*********************

    /**
    * 
    * @summary Constructor to initialize the page
    * @version Created 8/15/2014
    * @author Justin Phlegar
    * @param  driver
    * @throws NA
    * @return NA
    * 
    */
	public ResortFunctionsPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}
	
	public boolean loadPage() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, lnkManageArrivalsArrivalTab); 
	}
	
	public boolean pageLoaded(){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, lnkManageArrivalsArrivalTab);
	}
	
	public boolean pageLoaded(Element element){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}
	
	public ResortFunctionsPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	//*****************************************
	//*** Resort Function Page Interactions ***
	//*****************************************
    public void enterManageArrivalsArrivalTab(){
    	pageLoaded(lnkManageArrivalsArrivalTab);
		lnkManageArrivalsArrivalTab.jsClick(driver);
		
		//new ProcessingYourRequest().WaitForProcessRequest(driver);
		Sleeper.sleep(10000);
	}
	
    public void enterManageArrivalsInventorySummary(){
    	loadPage();
    	lnkManageArrivalsInventorySummary.highlight(driver);
		lnkManageArrivalsInventorySummary.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
    public void enterManageRoomAssignmentManual(){
    	pageLoaded(lnkManageRoomAssignment);
		lnkManageRoomAssignment.jsClick(driver);
		//new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
    public void enterViewRoomAvailiblity(){
    	pageLoaded(lnkViewRoomAvailability);
		lnkViewRoomAvailability.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
    public void enterManageRoomInvetoryStatus(){
    	loadPage();
		lnkManageRoomInventoryStatus.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
    public void enterUpdateRoomStatusManual(){
    	loadPage();
		lnkUpdateRoomStatus.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
    public void enterViewRoomHistory(){
    	loadPage();
		lnkViewRoomHistory.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
    public void enterCreateReservation(){
    	pageLoaded(lnkCreateReservation);
		lnkCreateReservation.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
		
    public void enterQuickBook(){
    	initialize();
    	loadPage();
		//lnkQuickBook.click();
    	lnkQuickBook.syncVisible(driver);
    	lnkQuickBook.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
    public void enterPersonalMagic(){
    	loadPage();
		lnkPersonalMagic.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
    public void enterEncodeBand(){
    	loadPage();
		lnkEncodeBand.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
    public void enterApplyMultipleCharges(){
    	loadPage();
		lnkApplyMultipleCharges.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
    public void enterApplyGroupMasterCharges(){
    	loadPage();
		lnkApplyGroupMasterCharges.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
    public void enterRecommendTickets(){
    	pageLoaded(lnkRecommendTickets);
    	lnkRecommendTickets.syncVisible(driver);
		lnkRecommendTickets.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
    public void enterVoidTransaction(){
    	loadPage();
		lnkVoidTransaction.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
		
    public void enterWillCall(){
    	loadPage();
		lnkWillCall.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
    public void enterOverridePrinterEncoder(){
    	loadPage();
		lnkOverridePrinterEncoder.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
    public void enterLateCheckouts(){
    	loadPage();
		lnkLateCheckouts.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
    public void enterBankout(){
    	initialize();
    	pageLoaded(lnkBankOut);
    	lnkBankOut.syncVisible(driver);
		lnkBankOut.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
    
    public void enterBankIn(){
    	initialize();
    	pageLoaded(lnkBankIn);
    	lnkBankIn.syncVisible(driver);
    	lnkBankIn.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
    }
    
    public void enterSwitchLocations(){
    	initialize();
    	pageLoaded(lnkSwitchLocations);
    	lnkSwitchLocations.syncVisible(driver);
    	lnkSwitchLocations.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
    }
	
    public void enterPreliminaryBankout(){
    	loadPage();
		lnkPreliminaryBankOut.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
    
    public void enterExpressCheckIn(){
    	pageLoaded(lnkExpressCheckIn);
    	lnkExpressCheckIn.highlight(driver);
		lnkExpressCheckIn.jsClick(driver);
	}
    
    public boolean verifyBankInLinkDisplayed(){
    	return pageLoaded(lnkBankIn);
    }
    
    public boolean verifyBankOutLinkDisplayed(){
    	return pageLoaded(lnkBankOut);
    }
    
    public boolean verifySwitchLocationDisplayed(){
    	return pageLoaded(lnkSwitchLocations);
    }
}

