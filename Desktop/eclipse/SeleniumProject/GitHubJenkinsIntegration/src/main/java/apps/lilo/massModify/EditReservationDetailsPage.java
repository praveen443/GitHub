package apps.lilo.massModify;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Randomness;
import utils.date.DateTimeConversion;

/**
 * 
 * @summary Contains the page methods & objects for the Mass Modify page
 * @version Created 10/29/2014
 * @author Jessica Marshall
 */
public class EditReservationDetailsPage {
	//
	// Page Fields
	//
	private Datatable datatable = new Datatable();
	private WebDriver driver;
	
	// *************************************
	// *** Page Elements ***
	// *************************************
	// Group code
	@FindBy(id = "resDetailsForm:groupCodeID")
	private Textbox txtGroupCode;

	// Guaranteed by
	@FindBy(id = "resDetailsForm:guaranteedBySOMID")
	private Listbox lstGuaranteedBy;

	// Block code
	@FindBy(id = "resDetailsForm:blockCodeSOMID")
	private Listbox lstBlockCode;

	// Team name
	@FindBy(id = "resDetailsForm:teamNameSOMID")
	private Listbox lstTeamName;

	// RSR Indicator
	@FindBy(id = "resDetailsForm:rsrIndicatorID")
	private Checkbox chkRSR;

	// Package code
	@FindBy(id = "resDetailsForm:packageCodeID")
	private Textbox txtPackageCode;

	// Tax exempt type
	@FindBy(id = "resDetailsForm:taxExemptTypeSOMID")
	private Listbox lstTaxExemptType;

	// Tax exempt ID
	@FindBy(id = "resDetailsForm:taxExemptIdSOMID")
	private Textbox txtTaxExemptID;

	// Resort
	@FindBy(id = "resDetailsForm:resortSOMID")
	private Listbox lstResort;

	// Room type
	@FindBy(id = "resDetailsForm:roomTypeSOMID")
	private Listbox lstRoomType;

	// Arrival date
	@FindBy(id = "resDetailsForm:arrivalDateIDInput")
	private Textbox txtArrivalDate;

	// Number of nights
	@FindBy(id = "resDetailsForm:numberOfDaysID")
	private Textbox txtNumNights;

	// Departure date
	@FindBy(id = "resDetailsForm:departureDateIDInput")
	private Textbox txtDeptDate;

	// Confirmation flag indicator
	@FindBy(id = "resDetailsForm:confirmationFlagID")
	private Checkbox chkConfirmation;

	// Title
	@FindBy(id = "resDetailsForm:titleSelectID")
	private Listbox lstTitle;

	// First name
	@FindBy(id = "resDetailsForm:firstNameID")
	private Textbox txtFirstName;

	// Last name
	@FindBy(id = "resDetailsForm:lastNameID")
	private Textbox txtLastName;

	// Suffix
	@FindBy(id = "resDetailsForm:suffixSelectID")
	private Listbox lstSuffix;

	// Country
	@FindBy(id = "resDetailsForm:countrySOMID")
	private Listbox lstCountry;

	// Address line 1
	@FindBy(id = "resDetailsForm:address1ID")
	private Textbox txtAddress1;

	// Address line 2
	@FindBy(id = "resDetailsForm:address2ID")
	private Textbox txtAddress2;

	// Zip code
	@FindBy(id = "resDetailsForm:postalCodeID")
	private Textbox txtZipCode;

	// City
	@FindBy(id = "resDetailsForm:cityID")
	private Textbox txtCity;

	// State
	@FindBy(id = "resDetailsForm:stateIdInput")
	private Listbox lstState;

	// Apply
	@FindBy(id = "resDetailsForm:applyResDetailsButtonID")
	private Button btnApply;

	// Clear
	@FindBy(id = "resDetailsForm:clearResDetailsButtonID")
	private Button btnClear;

	// Cancel
	@FindBy(id = "resDetailsForm:cancelResDetailsButtonID")
	private Button btnCancel;

	// Unexpected error pop up
	@FindBy(id = "PMSRErrorModalPanelDiv")
	private Element eleErrorPopUp;

	// Error pop up ok
	@FindBy(id = "errorForm:okButtonId")
	private Button btnErrorOk;

	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @version Created 9/11/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public EditReservationDetailsPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Constructor to initialize the page
	 * @version Created 9/11/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public EditReservationDetailsPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Constructor to initialize the page
	 * @version Created 9/11/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public boolean loadPage() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnApply);
	}

	// *****************************************
	// ***Page Interactions ***
	// *****************************************

	public void clickApply() {
		btnApply.syncVisible(driver);
		btnApply.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickClear() {
		btnClear.syncVisible(driver);
		btnClear.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickCancel() {
		btnCancel.syncVisible(driver);
		btnCancel.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickErrorPopUpOk() {
		btnErrorOk.syncVisible(driver);
		btnErrorOk.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * @summary Updates the reservation details for a mass modify
	 * @version Created 10/31/2014
	 * @author Jessica Marshall
	 * @param
	 * @throws NA
	 * @return NA
	 */
	public void editReservationDetails(String scenario) {
		datatable.setDatatableSheet("MassModifyReservationDetails");
		datatable.setDatatableRow(datatable.getRowContains(scenario, 0));

		String deptDate = datatable.getDataParameter("NumberOfNights");
		String arrivalDate = datatable.getDataParameter("DaysOut");

		if (arrivalDate.equalsIgnoreCase("RANDOM")) {
			arrivalDate = (Randomness.randomNumber(1));
		}
		if (!arrivalDate.equals(""))
			arrivalDate = DateTimeConversion.ConvertToDate(arrivalDate);
		if (!deptDate.equals(""))
			deptDate = DateTimeConversion.ConvertToDate(arrivalDate + deptDate);

		txtGroupCode.set(datatable.getDataParameter("GroupCode"));
		lstBlockCode.select(datatable.getDataParameter("BlockCode"));
		lstGuaranteedBy.select(datatable.getDataParameter("GuaranteedBy"));
		lstTeamName.select(datatable.getDataParameter("TeamName"));
		if (datatable.getDataParameter("RSR").equalsIgnoreCase("TRUE"))
			chkRSR.check();
		txtPackageCode.set(datatable.getDataParameter("PackageCode"));
		lstTaxExemptType.select(datatable.getDataParameter("TaxExemptType"));
		txtTaxExemptID.set(datatable.getDataParameter("TaxExemptID"));
		lstResort.select(datatable.getDataParameter("Resort"));
		lstRoomType.select(datatable.getDataParameter("RoomType"));
		txtArrivalDate.set(arrivalDate);
		txtNumNights.set(datatable.getDataParameter("NumberOfNights"));
		txtDeptDate.set(deptDate);
		if (datatable.getDataParameter("Confirmation").equalsIgnoreCase("TRUE"))
			chkConfirmation.check();
	}

	/**
	 * @summary Edits the primary guest details for a reservation during mass
	 *          modify
	 * @version Created 10/31/2014
	 * @author Jessica Marshall
	 * @param
	 * @throws NA
	 * @return na
	 */
	public void editPrimaryGuestDetails(String scenario) {
		datatable.setDatatableSheet("MassModifyEditGuestInfo");
		datatable.setDatatableRow(datatable.getRowContains(scenario, 0));

		lstTitle.select(datatable.getDataParameter("Title"));
		txtFirstName.safeSet(datatable.getDataParameter("FirstName"));
		txtLastName.safeSet(datatable.getDataParameter("LastName"));
		lstSuffix.select(datatable.getDataParameter("Suffix"));
		lstCountry.select(datatable.getDataParameter("Country"));
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		txtAddress1.safeSet(datatable.getDataParameter("Address1"));
		txtAddress2.safeSet(datatable.getDataParameter("Address2"));
		txtZipCode.safeSet(datatable.getDataParameter("ZipCode"));
		txtCity.safeSet(datatable.getDataParameter("City"));
		lstState.select(datatable.getDataParameter("State"));
	}

	/**
	 * @summary Handles an error pop up if it displays. If it displays, returns
	 *          true, else returns false
	 * @version Created 10/29/2014
	 * @author Jessica Marshall
	 * @param NA
	 * @throws NA
	 * @return true if error pop up displays, false if not
	 */
	public boolean handleErrorPopUp() {
		boolean found = eleErrorPopUp.syncVisible(driver, 3, false);
		if (found) {
			// output the error message
			Reporter.log("Error message displayed"
					+ driver.findElement(By.tagName("li")).getText());
			clickErrorPopUpOk();
		}

		return found;
	}

}

