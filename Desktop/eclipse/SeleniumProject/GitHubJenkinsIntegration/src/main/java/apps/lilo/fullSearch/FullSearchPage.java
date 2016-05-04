package apps.lilo.fullSearch;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Label;
import core.interfaces.Link;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.CheckboxImpl;
import core.interfaces.impl.LinkImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;
import utils.date.DateTimeConversion;

public class FullSearchPage {
	// ***********************
	// ** Page Class Fields **
	// ***********************
	public static String QUICKSEARCH = "Quick Search";
	public static String ADVANCEDSEARCH = "Advanced Search";
	public static String RESPONSIBLEPARTYSEARCH = "Responsible Party Search";
	public static String FOLIOSEARCH = "Folio Search";
	private List<WebElement> searchResultRows;
	private List<WebElement> massModifyRows;
	private int reservationNumberColumn = 0;
	private int massModifyResNumberColumn = 0;
	private int reservationStatusColumn = 0;
	private Datatable datatable = new Datatable();
	
	//************************************
	//*** Full Search Page Elements ***
	//************************************
	//Create 
	@FindBy(id = "fullSearch:lstname")
	private Textbox txtLastName;

	@FindBy(id = "fullSearch:fstname")
	private Textbox txtFirstName;

	@FindBy(id = "fullSearch:resNumber")
	private Textbox txtReservationNumber;

	@FindBy(id = "fullSearch:quickSearchReservationFindButtonId")
	private Button btnFind;

	@FindBy(id = "fullSearch:quickSearchResViewList")
	private Webtable tblReservationList;

	//Modify Link
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:0:modresLinkId")
	private Link lnkModifyReservation;	
	
	//Modify Link
	@FindBy(id = "fullSearch:quickSearchResViewList:0:travleplanSegmentidLink")
	private Link lnkReservationFS;

	//Check in Status
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:0:reservationStatusId")
	private Label lblNotCheckedIn;

	//Room Number hyper Link
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:0:roomNumberId")
	private Link lnkroomNumer;

	//Page Object for Advanced Search Tab.
	@FindBy(id="fullSearch:reservationSearchTabPanel_lbl")
	private Label lblAdvancedSearchTab;

	@FindBy(id="fullSearch:responsiblePartySearchTabPanel_lbl")
	private Label lblResponsiblepartySearchTab;

	@FindBy(id="fullSearch:cancelFullSearchTabButtonId")
	private Label closeAdvancedSearchTab;

	@FindBy(id="confirmationPopupForm:okButtonId")
	private Button btnPopupYes;

	/*
	 * ---------------------------------
	 * Advanced Search Tab page fields
	 *---------------------------------
	 */

	@FindBy(id="fullSearch:advancedSearchReservationFindButtonId")
	private Button btnFindAdvancedSearch;

	@FindBy(id="fullSearch:firstNameRS")
	private Textbox txtFirstname;

	@FindBy(id="fullSearch:lastnameRS")
	private Textbox txtLastname;

	@FindBy(id="fullSearch:toArrivalDateInput")
	private Textbox txtTOArrivalDate;

	@FindBy(id="fullSearch:fromArrivalDateInput")
	private Textbox txtFromArrivalDate;

	@FindBy(id="fullSearch:fullSearchResViewList")
	private Webtable tblAdvancedSearchList;

	@FindBy(id="fullSearch:reservationNumberRS")
	private Textbox txtAdvancedSearchResvatiosnNum;

	//*---------------------------------------
	//* Responsible Party Search page fields 
	//*---------------------------------------

	@FindBy(id="fullSearch:lastnameRPS")
	private Textbox txtLastNameRSP;

	@FindBy(id="fullSearch:firstNameRSP")
	private Textbox txtFirstNameRSP;

	@FindBy(id="fullSearch:reservationNumberRPS")
	private Textbox txtReservationNumberRPS;

	@FindBy(id="fullSearch:searchRSP")
	private Button btnFindReservationPartySearch;

	@FindBy(id="fullSearch:fullSearchRespViewList:0:tpsIdrsp")
	private Link lnkReservationNUmberRPS;

	//---- FolioSearch page Elements ----

	//Select FolioType in FolioSearch.
	@FindBy(id="fullSearch:SelectFolioTypeSus")
	private Listbox lstSelectFolioType;

	//Reservation number in FolioSearch.
	@FindBy(id="fullSearch:resNumId")
	private Textbox txtResvNumber;

	//FolioID link
	@FindBy(id="fullSearch:FolioTypeResFolioSearchResult:0:folioId")
	private Link lnkFolioID;

	//page object for link visa card number
	@FindBy(id="viewFolioNonTabFormId:dataTable:1:ouputLinkDescMinus")
	private Link lnkVisaCardInfo;

	//page object to get description of card type.
	@FindBy(id="paymentFormAcc:descriptionTxt")
	private Element eleGetCardTypeDescription;

	//page object to get description of card type.
	@FindBy(id="paymentFormAcc:terminalId")
	private Element eleGetTerminalID;

	//Button close in Payment tickets page.	    
	@FindBy(id="paymentFormAcc:close")
	private Button btnCloseInPaymentForm;

	//Exit button in viewFolioNonTabForm	    
	@FindBy(id="viewFolioNonTabFormId:closeButtonId")
	private Button btnExitInViewFolioForm;

	//Find button in FullSearch page.	    
	@FindBy( id="fullSearch:folioSearchFindButtonId")
	private Button btnFindInFullSearch;


	/**
	 * @Summary : Method to determine if the Advanced Search page is loaded.
	 * @author  : Praveen Namburi
	 * @version : Created 11/24/2015
	 * @return  : Boolean - true, if the page is loaded.Else will return - false.
	 */
	public boolean AdvancedSearchpageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnFindAdvancedSearch);
	}

	/**
	 * @Summary : Method to determine if the Reservation Party Search page is loaded.
	 * @author  : Praveen Namburi
	 * @version : Created 11/25/2015
	 * @return  : Boolean - true, if the page is loaded.Else will return - false.
	 */
	public boolean ReservationPartySearchpageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnFindReservationPartySearch);
	}

	//********************************
	//** Main Full Search Functions **
	//********************************
	/*public void searchReservation(String text) throws InterruptedException{
	    	FullSearchLoaded();
	    }*/

	/*// Check in Status
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:0:reservationStatusId")
	private Label lblNotCheckedIn;


<<<<<<< HEAD

=======
>>>>>>> branch 'UFT_Conversion' of https://github.disney.com/WDPRO-QA/lilo.git
	// Room Number hyper Link
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:0:roomNumberId")
<<<<<<< HEAD
	private Link lnkroomNumer;

=======
	private Link lnkroomNumer;*/


	//Quick Search Tab element
	@FindBy(id = "fullSearch:quickSearchTabPanel_lbl")
	private Element eleQuickSearch;


	//Advanced Search Tab element
	@FindBy(id = "fullSearch:reservationSearchTabPanel_lbl")
	private Element eleAdvancedSearch;




	//	    public void clickonModifyReservation()
	//	    {
	//	    	lnkModifyReservation.syncVisible(driver,60,false);
	//	    	pageLoaded(lnkModifyReservation);
	//	    	initialize();
	//	    	lnkModifyReservation.jsClick(driver);
	//	    	new ProcessingYourRequest().WaitForProcessRequest(driver);
	//	     }
	//	    //Method to click on room number hyper link 
	//	    public String clickOnRoomLink()
	//       {
	//	    	 
	//	    	lnkroomNumer.syncVisible(driver, 1, false);
	//	        String txtRoomNumber =lnkroomNumer.getText().trim();
	//	    	lnkroomNumer.jsClick(driver);
	//	    	new ProcessingYourRequest().WaitForProcessRequest(driver);
	//	    	return txtRoomNumber;
	//	   }

	/**
	 * @Summary : Method to click on Advanced Search Tab in Fullsearch page.
	 * @author  : Praveen Namburi
	 * @version : Created 11/24/2015
	 * @param   : NA
	 * @return  : NA
	 */
	public void clickAdvancedSearchTab(){

		pageLoaded(lblAdvancedSearchTab);
		lblAdvancedSearchTab.jsClick(driver);	    	
		new ProcessingYourRequest().WaitForProcessRequest(driver);

	}

	/**
	 * @Summary : Method to click on Responsibleparty Search Tab in Fullsearch page.
	 * @author  : Praveen Namburi
	 * @version : Created 11/24/2015
	 * @param   : NA
	 * @return  : NA
	 */
	public void clickResponsiblepartySearchTab(){

		pageLoaded(lblResponsiblepartySearchTab);
		lblResponsiblepartySearchTab.jsClick(driver);	    	
		new ProcessingYourRequest().WaitForProcessRequest(driver);

	}

	/**
	 * @summary : Method to click on Find button in advanced search page.
	 * @author  : Praveen Namburi
	 * @version : Craeted 11-24-2015
	 * @param   : NA
	 * @return  : NA
	 */
	public void clickbtnFindAdvancedSearch(){
		pageLoaded(btnFindAdvancedSearch);
		btnFindAdvancedSearch.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);

	}

	/**
	 * @summary : Method to click on Find button in Reservation Party search page.
	 * @author  : Praveen Namburi
	 * @version : Craeted 11-25-2015
	 * @param   : NA
	 * @return  : NA
	 */
	public void clickbtnFindReservationPartySearch(){
		pageLoaded(btnFindReservationPartySearch);
		btnFindReservationPartySearch.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);

	}

	/**
	 * @Summary : Method to enter Advanced Search Criteria in Advanced Search Tab page.
	 * @author  : Praveen Namburi
	 * @version : Created 11/24/2015
	 * @param   : scenario
	 * @return  : NA
	 */
	public void enterAdvancedSearchCriteria(String UserFirstName,String UserLastName,String DaysOut){

		//Initialize the page and set the user first name and last name.
		AdvancedSearchpageLoaded();

		pageLoaded(txtFirstname);
		txtFirstname.set(UserFirstName);

		pageLoaded(txtLastname);
		txtLastname.set(UserLastName);

		//Set Date range TO:			
		String arrivalDate = DateTimeConversion.getDaysOut(DaysOut,"MM/dd/yyyy");
		TestReporter.log("Arrival Date TO : "+arrivalDate); 
		txtTOArrivalDate.safeSet(arrivalDate);
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		//Click on Find button.
		clickbtnFindAdvancedSearch();

	}

	/**
	 * @Summary : Search and verify the reservation number and click on the link Reservation #
	 * @author  : Praveen Namburi
	 * @version : Created 11/24/2015
	 * @param   : text
	 * @throws  : NA
	 */
	public void searchAndVerifyResvNum_AdvancedSearchTabPage(String TpiD) {

		AdvancedSearchpageLoaded();	    	

		pageLoaded(tblAdvancedSearchList);

		List<WebElement> rows = tblAdvancedSearchList.findElements(By.xpath("tbody/tr"));
		System.out.println("Number of rows: " + rows.size());

		for(int row =1;row<=rows.size();row++){
			List<WebElement> inputLinks = driver.findElements(By.xpath("//tbody/tr/td/span/table/tbody/tr[3]/th/div/div/table/tbody/tr["+row+"]/td[5]/a"));
			for(WebElement input : inputLinks){
				if(input.getText().equalsIgnoreCase(TpiD)){
					String getResvNumber = input.getText();
					System.out.println("Identified the Reservation # number :"+ getResvNumber);
					System.out.println("Clicking on the Reservation number # :"+getResvNumber);

					//Validating the Reservation # number here
					TestReporter.assertEquals(getResvNumber, TpiD, "The Expected Reservation # doesn't matches with the Actual tpiD value in the table.");						

					input.click();
					break;
				}
			}
		}
	}	    

	/**
	 * @Summary : Method to search the reservation number in Reservation Party Search page.
	 * @author  : Praveen Namburi
	 * @version : Created 11/24/2015
	 * @param   : Strings - Quickbook user FirstName, lastName and Reservation Number
	 * @return  : NA
	 */
	public void enterResponsiblePartySearch(String UserFirstName,String UserLastName,String ReservationNum){	    

		//Initialize the page and set the user first name,last name and Reservation Number
		ReservationPartySearchpageLoaded();

		pageLoaded(txtFirstNameRSP);
		txtFirstNameRSP.syncVisible(driver);
		txtFirstNameRSP.set(UserFirstName);

		pageLoaded(txtLastNameRSP);
		txtLastNameRSP.set(UserLastName);

		pageLoaded(txtReservationNumberRPS);
		txtReservationNumberRPS.set(ReservationNum);

		//Click on Find button.
		clickbtnFindReservationPartySearch();

	}

	/**
	 * @Summary : Verify the reservation number and click on the link Reservation # in Reservation Party Search page.
	 * @author  : Praveen Namburi
	 * @version : Created 11/25/2015
	 * @param   : NA
	 * @throws  : NA
	 */
	public void verifyResNumber_ReservationPartySearch(String TpID) {

		pageLoaded(lnkReservationNUmberRPS);

		//Get the Reservation # Number
		String getReservationFromRPS = lnkReservationNUmberRPS.getText();	    	
		TestReporter.logStep("----> Reservation # number is :" + getReservationFromRPS);

		//Validating the Reservation# number in RPS page.
		TestReporter.assertEquals(getReservationFromRPS, TpID, "The Expected Reservtion # doesn't matches with the Actual value in Resrvtn party Search page.");

		//Click on the Reservation # link 
		lnkReservationNUmberRPS.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);

	}


	// go get quick search label
	@FindBy(id = "fullSearch:quickSearchTabPanel_lbl")
	private Element txtQuickSearchTabLbl;

	// go get quick search label
	@FindBy(id = "PMSRErrorModalPanelHeader")
	private Element eleErrorPopup;

	// error pop up text message
	@FindBy(xpath = ".//*[@id='errorForm']/table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td/table/tbody/tr/td[2]/div/ul/li")
	private Element txtErrorMessage;

	// error pop up Ok
	@FindBy(id = "errorForm:okButtonId")
	private Button btnErrorPopupOk;

	// go click button cancel - quick search tab
	@FindBy(id = "fullSearch:cancelQuickTabButtonId")
	private Button btnCancel_QuickSerachTab;

	// go click button ok - quick search tab
	@FindBy(id = "confirmationPopupForm:okButtonId")
	private Button btnQuickSearchOk;



	//Responsible Party Search Tab element
	@FindBy(id = "fullSearch:responsiblePartySearchTabPanel_lbl")
	private Element eleResponsibleParty;

	@FindBy(id = "fullSearch:folioSearchTabPanel_lbl")
	private Element eleFolioSearch;

	@FindBy(id = "fullSearch:lastnameRS")
	private Textbox txtAdvancedSearchLastName;

	@FindBy(id="fullSearch:firstNameRS")
	private Textbox txtAdvancedSearchFirstName;

	@FindBy(id = "fullSearch:advancedSearchReservationFindButtonId")
	private Button btnAdvancedSearchFind;

	@FindBy(id = "fullSearch:fullSearchResViewList")
	private Webtable tblAdvancedSearchResults;

	@FindBy(id = "fullSearch:fullSearchResViewList:tb")
	private Webtable tblAdvancedSearchResultsBody;

	@FindBy(id = "fullSearch:massModifyButtonID")
	private Button btnMassModify;

	@FindBy(id = "MassModifyForm:cancelResID")
	private Button btnMassModifyCancel;

	@FindBy(id="MassModifyForm:massModifyDataListID")
	private Webtable tblMassModify;

	@FindBy(id = "MassModifyForm:massModifyDataListID:tb")
	private Webtable tblMassModifyBody;

	@FindBy(id = "cancelResMPIDHeader")
	private Element eleCancelResHeader;

	@FindBy(id = "cancelResForm:cancelationReasonSOMID")
	private Listbox lstCancelResReason;

	@FindBy(id = "cancelResForm:cancelContactNameID")
	private Textbox txtCancelResContact;

	@FindBy(id = "cancelResForm:waveFeeID")
	private Checkbox chkCancelResWaiveFee;

	@FindBy(id = "cancelResForm:cancelResSubmitButtonID")
	private Button btnCancelResSubmit;

	@FindBy(id = "showChangeConfirmModelPanelHeader")
	private Element eleChangeConfirmationHeader;

	@FindBy(id = "massModifyId:yesButton")
	private Button btnChecngeConfirmYes;

	@FindBy(id = "MassModifyForm:reinstateButtonID")
	private Button btnMassModifyReinstate;

	@FindBy(id = "reinstateMPIDHeader")
	private Element eleReinstateHeader;

	@FindBy(id = "reinstateForm:keepSettlementMethodRadioID:0")
	private Element eleReinstateSettlementYes;

	@FindBy(id = "reinstateForm:keepSettlementMethodRadioID:1")
	private Element eleReinstateSettlementNo;

	@FindBy(id="reinstateForm:keepPackageRadioID:0")
	private Element eleReinstatePackageYes;

	@FindBy(id="reinstateForm:keepPackageRadioID:1")
	private Element eleReinstatePackageNo;

	@FindBy(id = "reinstateForm:reinstateSubmitButtonID")
	private Button btnReinstateSubmit;

	@FindBy(id="reinstateForm:reinstateReasonSOMID")
	private Listbox lstReinstateReason;

	@FindBy(id = "reinstateForm:companyCodeID")
	private Textbox txtReinstateContact;

	@FindBy(id = "reinstateForm:requestorID")
	private Textbox txtReinstateManager;

	@FindBy(id = "fullSearch:arrivalDateInput")
	private Textbox txtArrivalDate;

	@FindBy(id = "fullSearch:departureDateInput")
	private Textbox txtDepartureDate;

	@FindBy(id="fullSearch:rmNumber")
	private Textbox txtRoomNumber;

	@FindBy(id="fullSearch:postalCode")
	private Textbox txtZipCode;

	// *********************
	// ** Build page area **
	// *********************
	private WebDriver driver;

	public FullSearchPage(WebDriver driver) {

		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);

	}


	/*public FullSearchPage initialize() {
		return ElementFactory.initElements(driver, FullSearchPage.class);	        
	}

	public void FullSearchLoaded(){

		while (!btnFind.syncVisible(driver)){
			initialize();
		}
		btnFind.syncVisible(driver);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnFind);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	public boolean pageLoaded(WebDriver driver){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnFind);        
	}*/


	//********************************
	//** Main Full Search Functions **
	//********************************
	public void searchReservation(String text) {
		FullSearchLoaded();

		txtReservationNumber.set(text);
		

		clickButtonFind();
		//btnFind.jsClick(driver);

		new ProcessingYourRequest().WaitForProcessRequest(driver);

		int reservationRow = tblReservationList.getRowWithCellText(driver, text.toString())-2;
		System.out.println( "row size --> "+reservationRow);
		int reservationRow_new =0;

		//WebElement lnkReservation = driver.findElement(By.id("fullSearch:quickSearchResViewList:"+reservationRow+":travleplanSegmentidLink"));
		WebElement lnkReservation = driver.findElement(By.id("fullSearch:quickSearchResViewList:"+reservationRow_new+":travleplanSegmentidLink"));

		//Cast the WebElement into a Link Implementation
		Link link = new LinkImpl(lnkReservation);
		link.jsClick(driver);
		//pageLoaded(lnkModifyReservation);
		Sleeper.sleep(3000);

		new ProcessingYourRequest().WaitForProcessRequest(driver);    	
	}
		
	public void verifyReservationNumber_FullSearch(String TpID, String text) {

		FullSearchLoaded();
		txtReservationNumber.set(TpID);

		//Get the Reservation # Number
		String getReservationFromFS = txtReservationNumber.getText();	    	
		TestReporter.logStep("----> Reservation # number is :" + getReservationFromFS);

		//Validating the Reservation# number in RPS page.
		TestReporter.assertTrue(getReservationFromFS.contains(TpID), "The Expected Reservtion # doesn't matches with the Actual value in Full Search page.");

		clickButtonFind();
		//Click on the Reservation # link
		pageLoaded(lnkReservationFS);
		lnkReservationFS.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
		public void verifyReservationNumber_FullSearch(String TpID) {

			FullSearchLoaded();
			txtReservationNumber.set(TpID);
			
			//Get the Reservation # Number
			String getReservationFromFS = txtReservationNumber.getText();	    	
			TestReporter.logStep("----> Reservation # number is :" + getReservationFromFS);

			//Validating the Reservation# number in RPS page.
			TestReporter.assertTrue(getReservationFromFS.contains(TpID), "The Expected Reservtion # doesn't matches with the Actual value in Full Search page.");

			clickButtonFind();
			//Click on the Reservation # link
			pageLoaded(lnkReservationFS);
			lnkReservationFS.jsClick(driver);
			new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	/*public void clickonModifyReservation()
	{
		lnkModifyReservation.syncVisible(driver,60,false);
		pageLoaded(lnkModifyReservation);
		initialize();
		lnkModifyReservation.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	//Method to click on room number hyper link 
	public String clickOnRoomLink()
	{

		lnkroomNumer.syncVisible(driver, 1, false);
		String txtRoomNumber =lnkroomNumer.getText().trim();
		lnkroomNumer.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		return txtRoomNumber;
	}*/


	//Go click Button find 
	public void clickButtonFind(){
		pageLoaded(btnFind);
		btnFind.jsClick(driver);
	}

	/**
	 * @Summary : Created method for verifying Quick Search tab
	 * @version : Created 11/24/2015
	 * @author 	: Lalitha Banda
	 * @param   : NA
	 * @throws  : Exception
	 * @Return  : boolean 
	 */

	//verify quick search tab is active or not
	public void verify_QuickSearchTab(){
		pageLoaded(txtQuickSearchTabLbl);
		txtQuickSearchTabLbl.highlight(driver);
		TestReporter.log(txtQuickSearchTabLbl.getText());
		TestReporter.assertTrue(txtQuickSearchTabLbl.getText().equalsIgnoreCase("Quick Search"), 
				"Quick Search Tab in Not Loaded!!");
	}


	/**
	 * @Summary : this method handles error msgs
	 * @version :  Created 02/19/2016
	 * @author 	: SUNITHA BACHALA
	 * @param   : NA
	 * @throws  : Exception
	 * @Return  : boolean 
	 */


	public String setArrivalDate() {
        String ArivalDate = DateTimeConversion.getDaysOut("-1", "MM/dd/yyyy");
		TestReporter.log("Dept Date " + ArivalDate);
		return ArivalDate;


	}

	public  static String setDeptDate() {
        String deptDate = DateTimeConversion.getDaysOut("-1", "MM/dd/yyyy");
		TestReporter.log("Dept Date " + deptDate);
		return deptDate;


	}

	public void enterNegativeQuickSearchInfo(String scenario){	
		datatable.setVirtualtablePage("QuickSearchNegativeDataInfo");
		datatable.setVirtualtableScenario(scenario);

		String GuestFirstName = datatable.getDataParameter("GuestFirstName");
		String GuestLastName = datatable.getDataParameter("GuestLastName");
		String ArrivalDate = datatable.getDataParameter("ArrivalDate");
		String DepartureDate = datatable.getDataParameter("DepartureDate");
		String RoomNum = datatable.getDataParameter("RoomNum");
		String GuestZip = datatable.getDataParameter("GuestZip");
		String ReservationNum = datatable.getDataParameter("ReservationNum");

		
		pageLoaded(txtFirstName);
		txtFirstName.syncVisible(driver);
		txtFirstName.safeSet(GuestFirstName);

		pageLoaded(txtLastName);
		txtLastName.safeSet(GuestLastName);	    	

		if(scenario.contains("4")){
			pageLoaded(txtArrivalDate);
			txtArrivalDate.safeSet(setArrivalDate());
		}else{
			pageLoaded(txtArrivalDate);
			txtArrivalDate.safeSet(ArrivalDate);
		}

		if(scenario.contains("6")){
			pageLoaded(txtDepartureDate);
			txtDepartureDate.safeSet(setDeptDate());
		}else{
			pageLoaded(txtDepartureDate);
			txtDepartureDate.safeSet(DepartureDate);
		}

		pageLoaded(txtRoomNumber);
		txtRoomNumber.safeSet(RoomNum);	
		
		pageLoaded(txtZipCode);
		txtZipCode.safeSet(GuestZip);	
		
		pageLoaded(txtReservationNumber);
		txtReservationNumber.safeSet(ReservationNum);

		//Click on Find button.
		clickButtonFind();

	}

	/**
	 * @Summary : Created method for Handling error Pop up in quick search find functionality.
	 * @version : Created 11/24/2015
	 * @author 	: Lalitha Banda
	 * @param   : NA
	 * @throws  : Exception
	 * @Return  : NA 
	 */
	// Validate error Popup_QuickSearchNegative
	public void handle_Popup_QuickSearchNegitive(String scenario){
		datatable.setVirtualtablePage("QuickSearchNegativeDataInfo");
		datatable.setVirtualtableScenario(scenario);

		String expectedErrorMsg = datatable.getDataParameter("ExpectedErrorMsg");
		if(eleErrorPopup.isDisplayed()){
			txtErrorMessage.highlight(driver);
			TestReporter.log(txtErrorMessage.getText());
			TestReporter.assertTrue(txtErrorMessage.getText().contains(expectedErrorMsg), 
					"Expected errorMsg : "+expectedErrorMsg+" is Not displayed!!");
			btnErrorPopupOk.highlight(driver);
			btnErrorPopupOk.click();
		}

	}

	/**
	 * @Summary : Created method for closing QuickSearch tab
	 * @version : Created 11/24/2015
	 * @author 	: Lalitha Banda
	 * @param   : NA
	 * @throws  : Exception
	 * @Return  : NA 
	 */

	//Close quickSearchTab 
	public void close_QuicksearchTab(){
		btnCancel_QuickSerachTab.highlight(driver);
		btnCancel_QuickSerachTab.click();
		btnQuickSearchOk.highlight(driver);
		btnQuickSearchOk.click();
	}


	public FullSearchPage initialize() {
		return ElementFactory.initElements(driver, FullSearchPage.class);
	}

	public void FullSearchLoaded() {

		while (!btnFind.syncVisible(driver)) {
			initialize();
		}
		btnFind.syncVisible(driver);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnFind);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	public boolean pageLoaded(WebDriver driver) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnFind);
	}

	// ********************************
	// ** Main Full Search Functions **
	// ********************************
	/*public void searchReservation(String text) throws InterruptedException {
		FullSearchLoaded();

		txtReservationNumber.set(text);

		btnFind.jsClick(driver);

		new ProcessingYourRequest().WaitForProcessRequest(driver);

		int reservationRow = tblReservationList.getRowWithCellText(driver, text.toString()) - 2;
		System.out.println("row size --> " + reservationRow);
		int reservationRow_new = 0;

		// WebElement lnkReservation =
		// driver.findElement(By.id("fullSearch:quickSearchResViewList:"+reservationRow+":travleplanSegmentidLink"));
		WebElement lnkReservation = driver.findElement(
				By.id("fullSearch:quickSearchResViewList:" + reservationRow_new + ":travleplanSegmentidLink"));

		// Cast the WebElement into a Link Implementation
		Link link = new LinkImpl(lnkReservation);
		link.jsClick(driver);
		// pageLoaded(lnkModifyReservation);
		Sleeper.sleep(3000);

		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}*/

	public void clickonModifyReservation() {
		lnkModifyReservation.syncVisible(driver, 60, false);
		pageLoaded(lnkModifyReservation);
		initialize();
		lnkModifyReservation.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	// Method to click on room number hyper link
	public String clickOnRoomLink() {
		lnkroomNumer.syncVisible(driver, 1, false);
		String txtRoomNumber = lnkroomNumer.getText().trim();
		lnkroomNumer.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		return txtRoomNumber;
	}

	/**
	 * Naviagates to the desired search type
	 * @param searchType - search type to use
	 */
	public void navigateSearchTypeTabs(String searchType){
		boolean validSearchType = true;
		initialize();
		switch (searchType) {
		case "Quick Search":
			pageLoaded(eleQuickSearch);
			eleQuickSearch.jsClick(driver);
			break;
		case "Advanced Search":
			pageLoaded(eleAdvancedSearch);
			eleAdvancedSearch.jsClick(driver);
			break;
		case "Responsible Party Search":
			pageLoaded(eleResponsibleParty);
			eleResponsibleParty.jsClick(driver);
			break;
		case "Folio Search":
			pageLoaded(eleFolioSearch);
			eleFolioSearch.jsClick(driver);
			break;
		default:
			validSearchType = false;
			String validSearchTypes = QUICKSEARCH+";"+ADVANCEDSEARCH+";"+RESPONSIBLEPARTYSEARCH+";"+FOLIOSEARCH;
			TestReporter.assertTrue(validSearchType, "The search type ["+searchType+"] is not a valid search type. The following are valid search types:\n" + validSearchTypes);
			break;
		}
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}



	/**
	 * Performs Advanced Search using a guest's name
	 * @param firstName guest first name
	 * @param lastName guest last name
	 */
	public void advancedSearchForReservationsByGuestName(String firstName, String lastName, String[] TP_IDS){
		/*initialize();*/
		pageLoaded(txtAdvancedSearchLastName);
		txtAdvancedSearchLastName.syncVisible(driver);
		txtAdvancedSearchLastName.safeSet(lastName);
		txtAdvancedSearchFirstName.safeSet(firstName);

		/*txtTOArrivalDate.safeSet("12/14/2015");
		txtFromArrivalDate.safeSet("12/14/2015");*/

		btnAdvancedSearchFind.highlight(driver);
		btnAdvancedSearchFind.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		validateReservations(TP_IDS);
	}

	/**
	 * Validates Travel Plan ID's were returned from the Advanced Search
	 * @param TP_IDS - String array of travel plan IDs
	 */
	private void validateReservations(String[] TP_IDS){
		Map<String, Boolean> reservationsFound = new HashMap<String, Boolean>();
        initialize();
		for(String tp : TP_IDS){
			reservationsFound.put(tp, false);
		}

		pageLoaded(tblAdvancedSearchResults);

		//Grab a column headers
		List<WebElement> headers = tblAdvancedSearchResults.findElements(By.xpath("thead/tr/th"));
		//Determine the reservation number column
		for(int header = 0; header < headers.size(); header++){
			if(headers.get(header).getText().contains("Reservation #")){
				reservationNumberColumn = header + 1;

				break;
			}
		}

		//Grab the rows from teh search results
		searchResultRows = tblAdvancedSearchResultsBody.findElements(By.xpath("tr"));
		TestReporter.assertTrue(searchResultRows.size() >= TP_IDS.length, "The number of search results ["+searchResultRows.size()+"] is less than the number of expected travel plan IDs ["+TP_IDS.length+"].");
		//Iterate through the results and determine if each reservation is found
		for(WebElement row : searchResultRows){
			for(String tp : TP_IDS){
				if(row.findElement(By.xpath("td["+reservationNumberColumn+"]/a")).getText().equalsIgnoreCase(tp)){
					reservationsFound.put(tp, true);
					break;
				}
			}
		}
		//Assert that each reservation is found
		for (Map.Entry<String, Boolean> entry : reservationsFound.entrySet()) {
			TestReporter.assertTrue(entry.getValue(), "The reervation ["+entry.getKey()+"] was not found in the search resuults."); 
		}
	}

	/**
	 * Selects reservations based on an array of reservation numbers
	 * @param TP_IDS - array of reservations numbers
	 */
	public void selectReservations(String[] TP_IDS){
		initialize();
		//Iterate through the results and determine if each reservation is found
		for(WebElement row : searchResultRows){
			for(String tp : TP_IDS){
				if(row.findElement(By.xpath("td["+reservationNumberColumn+"]/a")).getText().equalsIgnoreCase(tp)){
					Checkbox resCheck = new CheckboxImpl(row.findElement(By.xpath("td[1]/input")));
					resCheck.highlight(driver);
					resCheck.jsToggle(driver);
					break;
				}
			}
		}
	}

	/**
	 * CLicks the Mass modify button
	 */
	private void clickMassModify(){
		pageLoaded(btnMassModify);
		btnMassModify.syncVisible(driver);
		btnMassModify.highlight(driver);
		btnMassModify.jsClick(driver);
	}

	/**
	 * Mass Cancels reservations
	 * @param TP_IDS - array of reservations numbers
	 * @param firstName - guest first name
	 * @param lastName - guest last name
	 * @param scenario - page class data scenario
	 */
	public void massCancel(String[] TP_IDS, String firstName, String lastName, String scenario){
		selectReservations(TP_IDS);
		clickMassModify();
		selectReservationsToMassModify(TP_IDS);
		clickMassModifyCancel();
		enterCancellationReason(firstName, lastName, scenario);
		confirmChanges();
	}

	/**
	 * Selects reservations to mass modify based on an array of reservations numbers
	 * @param TP_IDS - array of reservations numbers
	 */
	private void selectReservationsToMassModify(String[] TP_IDS){
		pageLoaded(tblMassModify);

		//Grab a list of column headers
		List<WebElement> headers = tblMassModify.findElements(By.xpath("thead/tr/th"));
		for(int header = 0; header < headers.size(); header++){
			if(headers.get(header).getText().equalsIgnoreCase("Res #")){
				massModifyResNumberColumn = header + 1;
				break;
			}
		}

		massModifyRows = tblMassModifyBody.findElements(By.xpath("tr"));
		for(WebElement row : massModifyRows){
			for(String tp : TP_IDS){
				if(row.findElement(By.xpath("td["+massModifyResNumberColumn+"]")).getText().equalsIgnoreCase(tp)){
					Checkbox resCheck = new CheckboxImpl(row.findElement(By.xpath("td[1]/input")));
					resCheck.highlight(driver);
					resCheck.jsToggle(driver);
				}
			}
		}
	}

	/**
	 * Clicks Mass Modify Cancel button
	 */
	private void clickMassModifyCancel(){
		pageLoaded(btnMassModifyCancel);
		btnMassModifyCancel.highlight(driver);
		btnMassModifyCancel.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * Processes the Cancellation reason popup
	 * @param firstName - guest first name
	 * @param lastName - guest last name
	 * @param scenario - page class data scenario
	 */
	private void enterCancellationReason(String firstName, String lastName, String scenario){
		datatable.setVirtualtablePage("CancelReservationPage");
		datatable.setVirtualtableScenario(scenario);

		String reason = datatable.getDataParameter("SelectOption");
		String waiveFee = datatable.getDataParameter("WaiveFee");

	    pageLoaded(eleCancelResHeader);
		lstCancelResReason.syncVisible(driver);
		lstCancelResReason.select(reason);
		txtCancelResContact.safeSet(firstName + " " + lastName);

		if(waiveFee.equalsIgnoreCase("true")){
			chkCancelResWaiveFee.highlight(driver);
			chkCancelResWaiveFee.jsToggle(driver);	
		}

		btnCancelResSubmit.highlight(driver);
		btnCancelResSubmit.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * Confirms Mass Modify Changes
	 */
	private void confirmChanges(){
	    pageLoaded(eleChangeConfirmationHeader);

		btnChecngeConfirmYes.highlight(driver);
		btnChecngeConfirmYes.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * Mass reinstates reservations
	 * @param TP_IDS - array of reservations numbers
	 * @param guestFirstName - guest first name
	 * @param guestLastName - guest last name
	 * @param reinstate - page class data scenario
	 */
	public void massReinstate(String[] TP_IDS, String guestFirstName, String guestLastName, String reinstate){
		selectReservations(TP_IDS);
		clickMassModify();
		selectReservationsToMassModify(TP_IDS);
		clickMassModifyReinstate();
		reinstateSelected(guestFirstName, guestLastName, reinstate);
	}

	/**
	 * Clicks the Mass Modify Reinstate button
	 */
	private void clickMassModifyReinstate(){
		pageLoaded(btnMassModifyReinstate);
		btnMassModifyReinstate.highlight(driver);
		btnMassModifyReinstate.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver, 60);
	}

	/**
	 * Processes the reinstate popup
	 * @param guestFirstName - guest first name
	 * @param guestLastName - guest last name
	 * @param reinstate - page class data scenario
	 */
	private void reinstateSelected(String guestFirstName, String guestLastName, String reinstate){
		datatable.setVirtualtablePage("Reinstate");
		datatable.setVirtualtableScenario(reinstate);

		String reason = datatable.getDataParameter("Reason");
		String manager = datatable.getDataParameter("Manager");
		String keepSettlement = datatable.getDataParameter("KeepSettlement");
		String keepPackage = datatable.getDataParameter("KeepPackage");

		pageLoaded(eleReinstateHeader);
		lstReinstateReason.select(reason);
		pageLoaded(txtReinstateContact);
		txtReinstateContact.safeSet(guestFirstName + " " + guestLastName);
		txtReinstateManager.safeSet(manager);
		if(keepSettlement.equalsIgnoreCase("true")){
			eleReinstateSettlementYes.jsClick(driver);
		}else{
			eleReinstateSettlementNo.jsClick(driver);
		}

		if(keepPackage.equalsIgnoreCase("true")){
			eleReinstatePackageYes.jsClick(driver);
		}else{
			eleReinstatePackageNo.jsClick(driver);
		}

		pageLoaded(btnReinstateSubmit);
		btnReinstateSubmit.jsClick(driver);
	}

	/**
	 * Verifies the status' of an array of reservations
	 * @param TP_IDS - array of reservations numbers
	 * @param status - array of reservation status'
	 */
	public void verifyReservationStatus(String[] TP_IDS, String[] status){
		Map<String, Boolean> statusVerified = new HashMap<String, Boolean>();

		for(String tp : TP_IDS){
			statusVerified.put(tp, false);
		}

	    pageLoaded(tblAdvancedSearchResults);

		//Grab a column headers
		List<WebElement> headers = tblAdvancedSearchResults.findElements(By.xpath("thead/tr/th"));
		//Determine the reservation number column
		for(int header = 0; header < headers.size(); header++){
			if(headers.get(header).getText().contains("Reservation #")){
				reservationNumberColumn = header + 1;
				break;
			}
		}

		//Determine the reservation status number column
		for(int header = 0; header < headers.size(); header++){
			if(headers.get(header).getText().contains("Status")){
				reservationStatusColumn = header + 1;
				break;
			}
		}

		//Grab the rows from the search results
		searchResultRows = tblAdvancedSearchResultsBody.findElements(By.xpath("tr"));
		TestReporter.assertTrue(searchResultRows.size() >= TP_IDS.length, "The number of search results ["+searchResultRows.size()+"] is less than the number of expected travel plan IDs ["+TP_IDS.length+"].");
		int resCounter = 0;
		//Iterate through the results and determine if each reservation is found
		for(WebElement row : searchResultRows){
			for(String tp : TP_IDS){
				if(row.findElement(By.xpath("td["+reservationNumberColumn+"]/a")).getText().equalsIgnoreCase(tp)){
					if(row.findElement(By.xpath("td["+reservationStatusColumn+"]")).getText().equalsIgnoreCase(status[resCounter])){
						statusVerified.put(tp, true);
						resCounter++;
						break;	
					}
				}
			}
		}
		//Assert that each reservation is found
		resCounter = 0;
		for (Map.Entry<String, Boolean> entry : statusVerified.entrySet()) {
			TestReporter.log(entry.getKey() +":" +entry.getValue());
			TestReporter.assertTrue(entry.getValue(), "The reervation ["+entry.getKey()+"] was not did not have the expected status ["+status[resCounter]+"].");
			resCounter++;
		}

	}

	/**
	 * @Summary : Created method for closing Advanced Search Tab
	 * @version : Created 12/09/2015
	 * @author 	: Lalitha Banda
	 * @param   : NA
	 * @throws  : Exception
	 * @Return  : NA 
	 */

	//close advanced Search tab in full search page
	public void clickCancel(){
		closeAdvancedSearchTab.highlight(driver);
		closeAdvancedSearchTab.click();
		btnPopupYes.highlight(driver);
		btnPopupYes.click();
	}

	/**
	 * @Summary: Method to select the FoliotYpe and enter reservation number.
	 * @param FolioType
	 * @param ResvNumber
	 */
	public void setFolioTypeAndResvNumber(String FolioType, String ResvNumber){
		pageLoaded(lstSelectFolioType);
		lstSelectFolioType.highlight(driver);
		lstSelectFolioType.select(FolioType);
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		pageLoaded(txtResvNumber);
		txtResvNumber.highlight(driver);
		txtResvNumber.safeSet(ResvNumber);

		pageLoaded(btnFindInFullSearch);
		btnFindInFullSearch.highlight(driver);
		btnFindInFullSearch.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * @summary : Method to click FolioID and capture the payment details.
	 * @author  : Praveen Namburi.
	 * @version : created 31-12-2015.
	 */
	public void clickFolioIDAndCaptureTerminalIDS(){

		Sleeper.sleep(2000);
		pageLoaded(lnkFolioID);
		lnkFolioID.highlight(driver);
		lnkFolioID.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		pageLoaded(lnkVisaCardInfo);
		lnkVisaCardInfo.syncVisible(driver);
		lnkVisaCardInfo.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		TestReporter.logStep("Navigating to the Payment details page.");
		TestReporter.logStep("Capturing the CardType description and TerminalID.");
		pageLoaded(eleGetCardTypeDescription);
		eleGetCardTypeDescription.highlight(driver);
		String CardDescription = eleGetCardTypeDescription.getText();
		TestReporter.logStep("Capturing the Card Description : "+CardDescription);

		pageLoaded(eleGetTerminalID);
		eleGetTerminalID.highlight(driver);
		String TerminalID = eleGetTerminalID.getText();
		TestReporter.logStep("Terminal ID is : "+TerminalID);

		//close the payment form
		pageLoaded(btnCloseInPaymentForm);
		btnCloseInPaymentForm.highlight(driver);
		btnCloseInPaymentForm.click();

	}

	public void clickbtnExitInViewFolio(){
	    pageLoaded(btnExitInViewFolioForm);
		btnExitInViewFolioForm.syncVisible(driver);
		btnExitInViewFolioForm.highlight(driver);
		btnExitInViewFolioForm.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
}
