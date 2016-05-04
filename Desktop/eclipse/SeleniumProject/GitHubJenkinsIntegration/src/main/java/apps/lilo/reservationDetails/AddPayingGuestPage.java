package apps.lilo.reservationDetails;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import core.WebDriverSetup;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;

/**
 * 
 * @summary Contains the page methods & objects for add paying guest page that
 *          pops up when you add a responsible party from the billing options
 *          page
 * @version Created 9/22/2014
 * @author Jessica Marshall
 */
public class AddPayingGuestPage {
	// *****************************
	// *** AddPayingGuest Fields ***
	// *****************************
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	// ************************************
	// *** Main Page Elements ***
	// ************************************

	// Select guest name
	@FindBy(id = "addRespPartyForm:titleSelect1")
	private Listbox lstSelectGuest;

	// Use address on file
	@FindBy(id = "addRespPartyForm:isLocatorTypeChkbox")
	private Checkbox chkUseAddressOnFile;

	// Title
	@FindBy(id = "addRespPartyForm:titleSelect")
	private Listbox lstTitle;

	// First name
	@FindBy(id = "addRespPartyForm:respPartyFirstNameText")
	private Textbox txtFirstName;

	// Last name
	@FindBy(id = "addRespPartyForm:respPartyLastNameText")
	private Textbox txtLastName;

	// suffix
	@FindBy(id = "addRespPartyForm:suffixSelect")
	private Listbox lstSuffix;

	// Address type
	@FindBy(id = "addRespPartyForm:locatorTypeSelect")
	private Listbox lstAddressType;

	// country
	@FindBy(id = "addRespPartyForm:countrySelect")
	private Listbox lstCountry;

	// address line 1
	@FindBy(id = "addRespPartyForm:addressLine1Text")
	private Textbox txtAddress1;

	// address line 2
	@FindBy(id = "addRespPartyForm:addressLine2Text")
	private Textbox txtAddress2;

	// zip code
	@FindBy(id = "addRespPartyForm:zipCodeText")
	private Textbox txtZipCode;

	// city
	@FindBy(id = "addRespPartyForm:addressCityText")
	private Textbox txtCity;

	// State
	@FindBy(name = "addRespPartyForm:j_id2399")
	private Listbox lstState;

	// primary phone type
	@FindBy(id = "addRespPartyForm:phoneTypeSelect")
	private Listbox lstPriPhoneType;

	// primary phone number
	@FindBy(id = "addRespPartyForm:phoneNumberText")
	private Textbox txtPrimaryPhoneNum;

	// secondary phone type
	@FindBy(id = "addRespPartyForm:phoneTypeSelect2")
	private Listbox lstSecPhoneType;

	// secondary phone number
	@FindBy(id = "addRespPartyForm:phoneNumberText2")
	private Textbox txtSecondaryPhoneNum;

	// email type
	@FindBy(id = "addRespPartyForm:respPartyEmailTypeId")
	private Listbox lstEmailType;

	// email address
	@FindBy(id = "addRespPartyForm:emailAddressText")
	private Textbox txtEmail;

	// Do not email
	@FindBy(id = "addRespPartyForm:editGuestDoNoMailCheckBox")
	private Checkbox chkDoNotEmail;

	// save
	@FindBy(id = "addRespPartyForm:save")
	private Button btnSave;

	// close
	@FindBy(id = "addRespPartyForm:closeRespPartyHiddenButton")
	private Button btnClose;

	// add/modify
	@FindBy(id = "addRespPartyForm:j_id2421")
	private Button btnAddModify;

	// account limit
	@FindBy(id = "addRespPartyForm:accountLimitText")
	private Textbox txtAccountLimit;

	// tax exempt id
	@FindBy(id = "addRespPartyForm:taxExemptIdText")
	private Textbox txtTaxExemptID;

	// account type
	@FindBy(id = "addRespPartyForm:accountTypeSelect")
	private Listbox lstAccountType;

	// onsite - yes or no
	@FindBy(id = "addRespPartyForm:onsiteRadioButtons")
	private Listbox lstOnsite;

	// tax exempt - yes or no
	@FindBy(id = "addRespPartyForm:isTaxExemptChkbox")
	private Listbox lstTaxExempt;

	// tax exempt type
	@FindBy(id = "addRespPartyForm:taxExmptTypDrpDwn1")
	private Listbox lstTaxExemptType;

	// Express checkout
	@FindBy(id = "addRespPartyForm:isExpChkbox")
	private Checkbox chkExpressCheckout;

	// email option for express checkout
	@FindBy(id = "addRespPartyForm:isExpChkbox1")
	private Checkbox chkEmail;

	// print option for express checkout
	@FindBy(id = "addRespPartyForm:isExpChkbox3")
	private Checkbox chkMail;

	// Settlement method table
	@FindBy(id = "addRespPartyForm:settlementMethodTableRP")
	private Webtable tblSettlement;

	// info pop up ok button
	@FindBy(id = "errorForm:okButtonId")
	private Button btnInfoPopUpOK;

	// pop up
	@FindBy(id = "PMSRErrorModalPanelCDiv")
	private Element eleInfoPopUp;

	// Address validation pop up
	@FindBy(id = "addressValidationModalPanelCDiv")
	private Element eleAddressValidPopUp;

	// Address validation pop up yes
	@FindBy(id = "confirmationFormId:yesButtonId")
	private Button btnAddressPopUpYes;

	// Address validation pop up cancel
	@FindBy(id = "confirmationFormId:noButtonId")
	private Button btnAddressPopUpCancel;

	// Refilter pop up
	@FindBy(id = "refilterModalPanelCDiv")
	private Element eleRefilterPopUp;

	// Refilter pop up ok
	@FindBy(id = "refilterForm:okId")
	private Button btnRefilterOk;

	// *********************
	// ** Build page area **
	// *********************

	public AddPayingGuestPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public AddPayingGuestPage initialize(WebDriver driver) {
		return ElementFactory.initElements(driver, AddPayingGuestPage.class);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnSave);

	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				element);
	}

	public AddPayingGuestPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	// **************************************
	// *** Main CheckIn Page Interactions ***
	// **************************************

	public void clickSave() {
		initialize();
		pageLoaded(btnSave);
		btnSave.click();
		// new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickClose() {
		initialize();
		pageLoaded(btnClose);
		btnClose.click();
		// new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickAddModify() {
		initialize();
		pageLoaded(btnAddModify);
		btnAddModify.click();
		// new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * 
	 * @summary Edits the responsible party address info
	 * 
	 * @version Created 9/19/2014
	 * @author Jessica Marshall
	 * @param The
	 *            datatable scenario
	 * @throws Exception
	 * @return
	 * 
	 */
	public void addRespPartyAddressInfo(String scenario) {
		datatable.setVirtualtablePage("EditRespPartyAddressInfo");
		datatable.setVirtualtableScenario(scenario);

		String doNotEmail = datatable.getDataParameter("DoNotEmail");
		String useAddressOnFile = datatable
				.getDataParameter("UseAddressOnFile");

		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		// Select the guest name you want to add as a responsible party, the
		// listbox
		// autopopulates with the guests from the reservation
		lstSelectGuest.select(datatable.getDataParameter("SelectGuestName"));

		// only check the use address on file check box if specified to do so
		if (useAddressOnFile.equalsIgnoreCase("ON")) {
			chkUseAddressOnFile.check();
		}

		// Enter the data
		lstTitle.select(datatable.getDataParameter("Title"));
		txtFirstName.safeSet(datatable.getDataParameter("FirstName"));
		txtLastName.safeSet(datatable.getDataParameter("LastName"));
		lstSuffix.select(datatable.getDataParameter("Suffix"));
		lstAddressType.select(datatable.getDataParameter("AddressType"));
		lstCountry.select(datatable.getDataParameter("Country"));
		txtAddress1.safeSet(datatable.getDataParameter("Address1"));
		txtAddress2.safeSet(datatable.getDataParameter("Address2"));
		txtZipCode.safeSet(datatable.getDataParameter("ZipCode"));

		// Zip code auto populates
		// new ProcessingYourRequest().WaitForProcessRequest(driver);
		txtCity.safeSet(datatable.getDataParameter("City"));
		lstState.select(datatable.getDataParameter("State"));

		// phone numbers auto format
		lstPriPhoneType.select(datatable.getDataParameter("PrimaryPhoneType"));
		txtPrimaryPhoneNum.safeSet(datatable
				.getDataParameter("PrimaryPhoneNum"));
		Sleeper.sleep(1000);
		lstSecPhoneType
				.select(datatable.getDataParameter("SecondaryPhoneType"));
		txtSecondaryPhoneNum.safeSet(datatable
				.getDataParameter("SecondaryPhoneNum"));
		Sleeper.sleep(1000);
		lstEmailType.select(datatable.getDataParameter("EmailType"));
		txtEmail.safeSet(datatable.getDataParameter("EmailAddress"));

		// Only check the do not email if specified "ON"
		if (doNotEmail.equalsIgnoreCase("ON")) {
			chkDoNotEmail.check();
		}
		driver.manage()
				.timeouts()
				.implicitlyWait(WebDriverSetup.getDefaultTestTimeout(),
						TimeUnit.SECONDS);
	}

	public void clickAddressPopUpYes() {
		initialize();
		pageLoaded(btnAddressPopUpYes);
		btnAddressPopUpYes.click();
		// new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickAddressPopUpCancel() {
		initialize();
		pageLoaded(btnAddressPopUpCancel);
		btnAddressPopUpCancel.click();
		// new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickInfoPopUpOk() {
		initialize();
		pageLoaded(btnInfoPopUpOK);
		btnInfoPopUpOK.click();
		// new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public String getInfoPopUpMsg() {
		return eleInfoPopUp.getText();

	}

	public boolean isInfoPopUpDisplayed() {
		return eleInfoPopUp.isDisplayed();
	}

	public boolean isAddressPopUpDisplayed() {
		return eleAddressValidPopUp.isDisplayed();
	}

	public boolean isRefilterPopUpDisplayed() {
		return eleRefilterPopUp.isDisplayed();
	}

	public void clickRefilterOk() {
		initialize();
		pageLoaded(btnRefilterOk);
		btnRefilterOk.click();
		// new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * 
	 * @summary Completes the process of saving the responsible party updates,
	 *          clicks save and then handles the 3-4 different pop ups/warnings
	 *          that display and then clicks close
	 * 
	 * @version Created 9/24/2014
	 * @author Jessica Marshall
	 * @param NA
	 * @throws Exception
	 * @return NA
	 * 
	 */
	public void completeRespPartyUpdates() {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		clickSave();

		// if the address isn't 'valid', a soft warning will display
		if (isAddressPopUpDisplayed()) {
			clickAddressPopUpYes();
		}

		// click ok at success message
		clickInfoPopUpOk();

		// Click ok at the refilter pop up if it displays
		if (isRefilterPopUpDisplayed()) {
			clickRefilterOk();
		}

		// Click ok at the there are no charges to be posted at this time pop up
		// if it displays
		if (isInfoPopUpDisplayed()) {
			clickInfoPopUpOk();
		}

		// Click close
		clickClose();
		driver.manage()
				.timeouts()
				.implicitlyWait(WebDriverSetup.getDefaultTestTimeout(),
						TimeUnit.SECONDS);
	}

	/**
	 * 
	 * @summary Gets the guest full name from the datatable
	 * @version Created 11/13/2015
	 * @author Venkatesh Atmakuri
	 * @param Scenario
	 * @throws NA
	 * @return String as Guest Full Name
	 * 
	 */
	public String getGuestFullName(String scenario) {
		datatable.setVirtualtablePage("EditRespPartyAddressInfo");
		datatable.setVirtualtableScenario(scenario);

		return datatable.getDataParameter("FirstName").trim() + "  "
				+ datatable.getDataParameter("LastName").trim();
	}

}

