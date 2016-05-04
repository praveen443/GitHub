package apps.alc.existingReservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.alc.pleaseWait.PleaseWait;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.TestReporter;

/**
 * This class contains elements and element interactions for a given page of a
 * web application
 * 
 * @author Waightstill W Avery
 * @version 12/15/2015 Waightstill W Avery - original
 */
public class ExistingReservationGuestSearchPage {
	// *******************
	// Page Class Fields
	// *******************
	// Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	// *********************
	// Page Class Elements
	// *********************
	@FindBy(id = "searchForReservationsByGuestForm:findReservationByGuestButton")
	private Button btnFind;

	@FindBy(id = "searchForReservationsByGuestForm:reservationId")
	private Textbox txtReservation;
	
	@FindBy(id = "ReservationResultByGuestForm:ReservationResultsByGuestTable:0:confValue")
	private Element elePrimaryReservationConfirmationNumber;
	
	@FindBy(id = "ReservationResultByGuestForm:ReservationResultsByGuestTable:n")
	private Webtable tblReservationResults;
	
	@FindBy(id = "ReservationResultByGuestForm:srchByGstMod")
	private Button btnModify;
	
	//textbox First name
	@FindBy(id = "searchForReservationsByGuestForm:firstName")
	private Textbox txtGSFirstName;
	
	//textbox Last name
	@FindBy(id = "searchForReservationsByGuestForm:lastName")
	private Textbox txtGSLastName;
	
	//textbox Postalcode
	@FindBy(id = "searchForReservationsByGuestForm:postalCode")
	private Textbox txtPostalcode;
	
	//textbox Phone
	@FindBy(id = "searchForReservationsByGuestForm:phone")
	private Textbox txtPhone;
	
	//textbox ReservationId
	@FindBy(id = "searchForReservationsByGuestForm:reservationId")
	private Textbox txtReservationId;
	
	//textbox iataNumber
	@FindBy(id = "searchForReservationsByGuestForm:iataNumber" )
	private Textbox txtiataNumber;
	
	//Listbox status options
	@FindBy(id = "searchForReservationsByGuestForm:reservationStatusOptions")
	private Listbox lstStatus;
	
	//textbox Email
	@FindBy(id="searchForReservationsByGuestForm:emailinput")
	private Textbox txtEmail;

	//Button Clear Search.
	@FindBy(id = "ReservationResultByGuestForm:srchByGstReset")
	private Button btnClearSearch;
	
	//Button Cancel.
	@FindBy(id = "ReservationResultByGuestForm:srchByGstCancel")
	private Button btnCancel;

	@FindBy(id = "ReservationResultByGuestForm:srchByGstView")
	private Button btnView;
		
	@FindBy(id = "existingReservationForm:noOfAdults")
	private Element eleNoofAdults;
	
	@FindBy(id = "existingReservationForm:noOfNonAdults")
	private Element eleNoOfchilds;
	
	@FindBy(id = "existingReservationForm:productList:0:productId")
	private Element eleResActivity;
	
	@FindBy(id = "existingReservationForm:reservationDetailsInfoPanel_header")
	private Button eleResDetails;
	
	/*@FindBy(id = "ReservationResultByGuestForm:srchByGstCancel")
	private Button btnCancel;
	
	@FindBy(id = "searchForReservationsByGuestForm:reservationStatusOptions")
	private Listbox lstStatus;*/
	
	@FindBy(xpath = ".//*[@id='ReservationResultByGuestForm:ReservationResultsByGuestPanel_body']/table/tbody/tr[1]/td/span[2]")
	private Element eleResultsNumber;
	
	@FindBy(id = "searchForReservationsByGuestForm:firstName")
	private Textbox txtFirstName ;
	
	@FindBy(id = "searchForReservationsByGuestForm:lastName")
	private Textbox txtLastName ;

	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
	 * @param driver
	 */
	public ExistingReservationGuestSearchPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public ExistingReservationGuestSearchPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnFind);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
	 * @param element
	 *            - Element to be used to determine if the page is loaded
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	// ********************
	// Page Class Methods
	// ********************
	public void searchByReservationToModify(String reservationNumber){
		searchByReservationNumber(reservationNumber);
		clickPrimaryReservation();
		clickModify();
	}
	
	public void searchByReservationNumber(String reservationNumber){
		pageLoaded(txtReservation);
		txtReservation.syncVisible(driver);
		txtReservation.safeSet(reservationNumber);
		clickFind();
	}
	
	/**
	 * @summary Method to start the search to cancel a reservation.  This also makes sure the 
	 * 			status dropdown is set to Booked and uses Reservation number to search
	 * @author Stagliano, Dennis
	 * @version 1/5/2016
	 * @param String
	 *            - String used for the reservationNumber
	 * @return NA
	 */
	public void searchByReservationNumberToCancel(String reservationNumber){
		pageLoaded();
		//Set the Status field to "Booked"
		lstStatus.select("Booked");
		searchByReservationNumber(reservationNumber);
		verifyPrimaryFindResultsForCancelReservation();
	}
	
	private void clickFind(){
		pageLoaded(btnFind);
		btnFind.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
		pageLoaded(btnModify);
		btnModify.syncVisible(driver);
	}
	
	private void clickPrimaryReservation(){
		initialize();
		new PageLoaded(driver).isDomComplete();
		pageLoaded(elePrimaryReservationConfirmationNumber);
		elePrimaryReservationConfirmationNumber.jsClick(driver);
//		pageLoaded(tblReservationResults);
//		tblReservationResults.jsClick(driver);
	}
	
	public void clickModify(){
		new PageLoaded(driver).isDomComplete();
		pageLoaded(btnModify);
		//btnModify.jsClick(driver);
		btnModify.highlight(driver);
		btnModify.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}
	
	/**
	 * @summary : Method to click on button Find.
	 * @author : Praveen Namburi, @version : Created 1-7-2016.
	 */
	public void clickbtnFind(){
		//Click Find button
		pageLoaded(btnFind);
		btnFind.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}
	
	/**
	 * @summary : Method to click on button ClearSearch.
	 * @author : Praveen Namburi, @version : Created 1-7-2016.
	 */
	public void clickbtnClearSearch(){
		//Click btn Clear Search
		pageLoaded(btnClearSearch);
		btnClearSearch.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}
	
	/**
	 * @summary : Method to click on button Cancel.
	 * @author : Praveen Namburi, @version : Created 1-7-2016.
	 */
	public void clickcbtnCancel(){
		//Click btn Cancel
		pageLoaded(btnCancel);
		btnCancel.syncVisible(driver);
		btnCancel.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}
	
	
	
	/**
	 * @summary : Method to enter the Guest search info.
	 * @version: Created 1-6-2015, @author : Praveen Namburi.
	 * @param scenario, @param reservationNumber.
	 */
	public void enterGuestSearchInfo(String scenario, String reservationNumber){
		datatable.setVirtualtablePage("GuestSearchForExistingResv");
		datatable.setVirtualtableScenario(scenario);
    	pageLoaded();
    	
    	String firstName = datatable.getDataParameter("FirstName");
    	String lastName = datatable.getDataParameter("LastName");
    	String postalCode = datatable.getDataParameter("PostalCode");
    	String phoneNumber = datatable.getDataParameter("Phone");
    	//String reservationNumber = datatable.getDataParameter("ReservationNumber");
    	String iataNumber = datatable.getDataParameter("IataNumber");
    	String status = datatable.getDataParameter("Status");
    	String email = datatable.getDataParameter("Email");
		String findGuest = datatable.getDataParameter("FindGuest");
		String clearSearch = datatable.getDataParameter("ClearSearch");
		
		initialize();
		pageLoaded(txtGSFirstName);
		txtGSFirstName.safeSet(firstName);
		txtGSLastName.safeSet(lastName);
		pageLoaded(txtPostalcode);
		txtPostalcode.safeSet(postalCode);
		txtPhone.safeSet(phoneNumber);
		pageLoaded(txtReservationId);
		txtReservationId.safeSet(reservationNumber);
		txtiataNumber.safeSet(iataNumber);
		pageLoaded(lstStatus);
		lstStatus.select(status);
		txtEmail.safeSet(email);
	
		if(findGuest.equalsIgnoreCase("True")){
			clickbtnFind();
		  }
		else if(clearSearch.equalsIgnoreCase("True")){
			clickbtnClearSearch();
		}
	}

	/* @summary Methods to click viewr button
	 * @author Lalitha Banda
	 * @version 12/5/2015 Lalitha Banda - original
	 * @param NA
	 * @return NA
	 */
	public void clickView(){
		pageLoaded(btnView);
		btnView.highlight(driver);
		btnView.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}
	
	public void ButtonModify(){
		clickModify();
	}
	
	
	/**
	 * @summary This method makes sure a result comes back with 1 result which is expected
	 * 			when searching for a reservation via reservation number.  It takes the results text
	 * 			and converts to Integer, so that is the result count is == to 1, then it will click on 
	 * 			the result which is the Primary reservation, then click the Cancel button to go to the
	 * 			details screen.
	 * @author Stagliano, Dennis
	 * @version 1/5/2016
	 * @param NA
	 * @return NA
	 */
	private void verifyPrimaryFindResultsForCancelReservation(){
		String numOfResults = "";
		int resultCount = 0;		
	
			numOfResults = eleResultsNumber.getText();
		try {
			//string function to get number out of [#] or [##]
				numOfResults = numOfResults.substring(1, numOfResults.length() - 1);	
			    resultCount = Integer.parseInt(numOfResults);
			
			//Should only be one result for reservation number
			if(resultCount == 1){
				//verify results displayed reservation number passed
				clickPrimaryReservation();
				clickCancel();
				PleaseWait.WaitForPleaseWait(driver);
			}
		
		} catch (NumberFormatException e) {
			TestReporter.log("Failed with a Number Format error in verifyFindResultsForCancelReservation" );
			TestReporter.log("numOfResults = " + numOfResults + " Should be converted to Integer");
			e.printStackTrace();
		}
	}
	
	/**
	 * @summary This method clicks the Cancel button to take you to the details screens to actually
	 * 			perform the cancellation.
	 * @author Stagliano, Dennis
	 * @version 1/5/2016
	 * @param NA
	 * @return NA
	 */
	public void clickCancel(){
		initialize();
		new PageLoaded(driver).isDomComplete();
		pageLoaded(elePrimaryReservationConfirmationNumber);
		btnCancel.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}
	
	/**
	 * @summary This method makes sure a result comes back with 1 result which is expected
	 * @author  Venkatesh
	 * @version 1/6/2016
	 * @param Scenario,ReservationNumber
	 * @return NA
	 */
	public void searchGuestByNameAndReservationNumber(String scenario, String reservationNumber){
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
		datatable.setVirtualtablePage("GuestSearch");
		datatable.setVirtualtableScenario(scenario);
		
		txtFirstName.safeSet(datatable.getDataParameter("FirstName"));
		txtLastName.safeSet(datatable.getDataParameter("LastName"));
		txtReservation.safeSet(reservationNumber);
		lstStatus.select(datatable.getDataParameter("ReservationStatus"));
		clickFind();
	}
	
	/**
	 * @summary This method makes sure a result comes back with 1 result which is expected
	 * @author  Venkatesh
	 * @version 1/6/2016
	 * @param NA
	 * @return NA
	 */
	public boolean verifyExistingGuestResults(){
		String numOfResults = "";
		int resultCount = 0;	
		boolean result = false;
	
			numOfResults = eleResultsNumber.getText();
		try {
			//string function to get number out of [#] or [##]
				numOfResults = numOfResults.substring(1, numOfResults.length() - 1);	
			    resultCount = Integer.parseInt(numOfResults);
			
			//Should only be one result for reservation number
			if(resultCount == 1){
				tblReservationResults.highlight(driver);
				tblReservationResults.focus(driver);
				result = true;
			}
		
		} catch (NumberFormatException e) {
			TestReporter.log("Failed with a Number Format error in verifyFindResults" );
			TestReporter.log("numOfResults = " + numOfResults + " Should be converted to Integer");
			e.printStackTrace();
		}
		System.out.println("Boolean : "+result);
		return result;
	}
	
	
}

