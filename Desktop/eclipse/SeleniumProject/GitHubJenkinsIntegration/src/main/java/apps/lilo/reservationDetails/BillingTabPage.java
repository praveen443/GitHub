package apps.lilo.reservationDetails;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import apps.lilo.createReservation.CreateReservationCreateNewGuest;
import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Link;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.TestReporter;

/**
 * 
 * @summary Contains the page methods & objects for billing tab page for a
 *          reservation
 * @version Created 9/15/2014
 * @author Jessica Marshall
 */

public class BillingTabPage {
	// *************************
	// *** BillingTab Fields ***
	// *************************
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	
	// ************************************
	// *** Main Page Elements ***
	// ************************************
	
	// Add responsible party
	@FindBy(id = "resPartyListFrm:addRespParty")
	private Button btnAddRespParty;
	
	// Copy responsible party
	@FindBy(id = "resPartyListFrm:copyRespParty")
	private Button btnCopyRespParty;
	
	// Responsible party history
	@FindBy(id = "resPartyListFrm:historyRespParty")
	private Button btnHistoryRespParty;
	
	// View/Modify responsible party
	@FindBy(id = "resPartyChargeAllocFrm:editRespParty")
	private Button btnEditRespParty;
	
	// View/Modify charge allocations
	@FindBy(id = "resPartyChargeAllocFrm:chargeAlloctnInfo")
	private Button btnEditChargeAlloc;
	
	// History of charge allocations
	@FindBy(id = "resPartyChargeAllocFrm:chargeAllocationId")
	private Button btnHistoryChargeAlloc;
	
	// Responsible party list table
	@FindBy(id = "resPartyListFrm:richTablePanel")
	private Webtable tblRespPartyList;
	
	// Group RSR folio list table
	@FindBy(id = "groupRSRFolioListFrm:rsrAndGrp")
	private Webtable tblGroupList;
	
	// Selected paying guest details table
	@FindBy(id = "resPartyChargeAllocFrm")
	private Webtable tblRespPartyDetails;
	
	// span element for just the paying guest details panel
	@FindBy(id = "resPartyChargeAllocFrm:respPartyDtlPanel")
	private Element eleRespParty;
	
	//Charge Allocation
	@FindBy(id = "resPartyChargeAllocFrm:roomChargeDetailTable:tb")
	private Element eleChargeAllocationForm;
	
	//Room TaxAllocation CheckBox
	@FindBy(id = "chargeAllocationForm:accomodtnChargeAllocDtlPanel:0:allocationCheckBoxId")
	private Element eleRoomTaxALlocation;
	
	//Grab room Number 
	@FindBy(xpath = "//div/span/table/tbody/tr/td[2]/span")
	private Element eleRoomNumber;
	
	//Split TaxAllocation
	@FindBy(id = "chargeAllocationForm:accomodtnChargeAllocDtlPanel:0:roomSpendLimitPercent")
	private Textbox txtSplitTaxAllocation;
	
	//Charge Allocation Dates Link
	@FindBy(id = "chargeAllocationForm:accomodtnChargeAllocDtlPanel:0:nights")
	private Link lnkChargeAllocDates;
	
	//Dates form
	@FindBy(id = "datesSelectionForm")
	private Element eleDatesForm; 
	
	@FindBy(xpath = "//*[@id='datesSelectionForm']/table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td/table/tbody/tr[1]/td/input")
	private Element eleSelectAllDatesForRoom;
	
	@FindBy(xpath = "//*[@id='datesSelectionForm']/table/tbody/tr[1]/td[1]/table/tbody/tr[2]/td/div/table/tbody/tr/td/input[1]")
	private Button btnSelectDates;
	
	//grab Romm number from RoomTab allocation table in billing tab page
	@FindBy(xpath = ".//*[@id='resPartyChargeAllocFrm:roomChargeDetailTable:tb']/tr/td[1]")
	private Element eleTaxALlocationeRoomNumber;
	
	//Charge allocation window save button
	@FindBy(id = "chargeAllocationForm:saveChargeAllocationId")
	private Button btnChargeAllocationSave;
	
	//Re-Filter Yes Button
	@FindBy(id = "refilterForm:yesId")
	private Button btnReFilterYes;
	
	//ReFilter Success Ok Button
	@FindBy(id = "errorForm:okButtonId")
	private Button btnReFilterSuccessOk;
	
	//Paying Guests Table
	@FindBy(id = "resPartyListFrm:richTablePanel:tb")
	private Element elePayingGuestsTable;
	
	// *********************
	// ** Build page area **
	// *********************

	public BillingTabPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public BillingTabPage initialize(WebDriver driver) {
		return ElementFactory.initElements(driver,
				BillingTabPage.class);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnAddRespParty); 
	}

	public boolean pageLoaded(Element element){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}
	
	
	// **************************************
	// *** Main Page Interactions ***
	// **************************************

	public void clickAddRespParty() {
		btnAddRespParty.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickCopyRespParty() {
		btnCopyRespParty.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickHistoryRespParty() {
		btnHistoryRespParty.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickEditRespParty() {
		btnEditRespParty.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickEditChargeAlloc() {
		btnEditChargeAlloc.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickHistoryChargeAlloc() {
		btnHistoryChargeAlloc.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	/**
	 * 
	 * @summary Gets the responsible party details displayed on the billing tab's 
	 * 			Selected paying guest - includes address, settlement, account limit.
	 * 			Just returns everything as one string.
	 * 
	 * @version Created 9/19/2014
	 * @author Jessica Marshall
	 * @param The datatable scenario
	 * @throws 
	 * @return	a string that contains all the text for that element
	 * 
	 */
	public String getRespPartyDetails(){
		return eleRespParty.getText();
	}
	
	
	/**
	 * 
	 * @summary Validates the Visible Paying Guest on the Billing Tab Page 
	 * @version Created 11/13/2015
	 * @author Marella Satish
	 * @param  NA
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void verifyPayingGuest(String reservationScenario, String payingGuestScenario)
	{
		//Get table header element from the table
		 WebElement table = driver.findElement(By.id("resPartyListFrm:richTablePanel:tb")); 
		 
	     //Now get all the TR elements from the table 
	     List<WebElement> allRows = table.findElements(By.tagName("tr"));
	     
	     //Get all rows from table
	     int rows = allRows.size();
	    TestReporter.logStep("----------------------------------------------------------------------"); 
	    TestReporter.logStep("Number of Paying Guests visible  "+rows);
	    TestReporter.logStep("----------------------------------------------------------------------");
		for(int guestIterator=0; guestIterator<=rows-1; guestIterator++){
			
			List<WebElement> GuestRows = table.findElements(By.tagName("tr"));
			String[] GuestName = null;
			
			//Calling the getGuestName method which returns array
			GuestName = getGuestName(reservationScenario,payingGuestScenario);
			
			//Fetching the Paying Guest information from the Billing tab Page
			String Guest = GuestRows.get(guestIterator).getText();
			Boolean GuestStatus;
			
			//Compares the paying guest name from datatable with paying guest name from 
			//billing tab page and holds in a boolean variable
			GuestStatus = Guest.toLowerCase().contains(GuestName[guestIterator].toLowerCase());
			TestReporter.logStep("Guest "+(guestIterator+1)); 
			TestReporter.logStep("Guest from Billing Tab Page - "+ Guest.toLowerCase());
			TestReporter.logStep("Guest from Datatable - "+ GuestName[guestIterator].toLowerCase());
			TestReporter.logStep("Guest Status - "+GuestStatus);
			Assert.assertTrue(GuestStatus, "The Paying Guest Information mismatched");
			
		}
		
	}
	
	
	/**
	 * 
	 * @summary Gets the guest full name from the two datatables and stores in an array
	 * @version Created 11/13/2015
	 * @author Marella Satish
	 * @param  NA
	 * @throws NA
	 * @return Array String contains Paying Guest FullNames
	 * 
	 */
	public String[] getGuestName(String reservationScenario, String payingGuestScenario){
	
		String [] GuestNames = null;
		CreateReservationCreateNewGuest  createReservationCreateNewGuest = new CreateReservationCreateNewGuest(driver);
		AddPayingGuestPage addPayingGuestPage = new AddPayingGuestPage(driver);
		GuestNames = StringUtils.split(createReservationCreateNewGuest.getPrimaryGuestFullName(reservationScenario)+";"+addPayingGuestPage.getGuestFullName(payingGuestScenario),";");
		return GuestNames;
	}
	
}

