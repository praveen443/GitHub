package apps.lilo.reservationDetails;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

/**
 * 
 * @summary Contains the page methods & objects for the edit guest info page
 *          after you click edit from trabel plan section on reservation page
 * @version Created 9/18/2014
 * @author Jessica Marshall
 */

public class EditGuestInfoPage {
	// ****************************
	// *** EditGuestInfo Fields ***
	// ****************************
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	// Title
	@FindBy(id = "editGuestForm:courtesyTitle")
	private Listbox lstTitle;

	// First name
	@FindBy(id = "editGuestForm:pgFirstName")
	private Textbox txtFirstName;

	// Last name
	@FindBy(id = "editGuestForm:pgLastName")
	private Textbox txtLastName;

	// suffix
	@FindBy(id = "editGuestForm:suffix")
	private Listbox lstSuffix;

	// Address type
	@FindBy(id = "editGuestForm:editGuestAddressTypeId")
	private Listbox lstAddressType;

	// country
	@FindBy(id = "editGuestForm:countryId")
	private Listbox lstCountry;

	// address line 1
	@FindBy(id = "editGuestForm:address1Id")
	private Textbox txtAddress1;

	// address line 2
	@FindBy(id = "editGuestForm:address2")
	private Textbox txtAddress2;

	// zip code
	@FindBy(id = "editGuestForm:pgPostalCode")
	private Textbox txtZipCode;

	// city
	@FindBy(id = "editGuestForm:cityId")
	private Textbox txtCity;

	// State
	@FindBy(id = "editGuestForm:stateId")
	private Listbox lstState;

	// Language
	@FindBy(id = "editGuestForm:prefLanguageId")
	private Listbox lstLanguage;

	// primary phone type
	@FindBy(id = "editGuestForm:phoneType1")
	private Listbox lstPriPhoneType;

	// primary phone number
	@FindBy(id = "editGuestForm:phoneNumber1")
	private Textbox txtPrimaryPhoneNum;

	// secondary phone type
	@FindBy(id = "editGuestForm:phoneType2")
	private Listbox lstSecPhoneType;

	// secondary phone number
	@FindBy(id = "editGuestForm:phoneNumber2")
	private Textbox txtSecondaryPhoneNum;

	// email type
	@FindBy(id = "editGuestForm:editprimaryEmailTypeId")
	private Listbox lstEmailType;

	// email address
	@FindBy(id = "editGuestForm:pgEmail")
	private Textbox txtEmail;

	// Do not email
	@FindBy(id = "editGuestForm:editGuestDoNoMailCheckBox")
	private Checkbox chkDoNotEmail;

	// save
	@FindBy(id = "editGuestForm:saveButtonId")
	private Button btnSave;

	// close
	@FindBy(id = "editGuestForm:closeGuestEdit")
	private Button btnClose;

	// *********************
	// ** Build page area **
	// *********************
	public EditGuestInfoPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public EditGuestInfoPage initialize(WebDriver driver) {
		return ElementFactory.initElements(driver, EditGuestInfoPage.class);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnSave);

	}

	/**
	 * @summary : Method to determine if the text box addressline 1 is loaded on
	 *          the page.
	 * @version : Created date 11/09/2015
	 * @author : Praveen Namburi
	 * @param : textbox
	 * @return : Boolean - True, if the element is loaded. Else will return
	 *         false.
	 */
	public boolean pageLoaded(Textbox textbox) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				txtAddress1);

	}

	public boolean pageLoaded(Button button) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnClose);

	}

	// **************************************
	// *** Main CheckIn Page Interactions ***
	// **************************************

	public void clickSave() {
		btnSave.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickClose() {
		btnClose.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * 
	 * @summary Edits the primary guest info from the reservation page
	 * 
	 * @version Created 9/18/2014
	 * @author Jessica Marshall
	 * @param The
	 *            datatable scenario
	 * @throws Exception
	 * @return
	 * 
	 */
	public void editPrimaryGuestInfo(String scenario) {
		/*
		 * datatable.setDatatableSheet("EditPrimaryGuestInfoPage");
		 * datatable.setDatatableRow(datatable.getRowContains(scenario, 0));
		 */
		datatable.setVirtualtablePage("EditPrimaryGuestInfoPage");
		datatable.setVirtualtableScenario(scenario);

		String doNotEmail = datatable.getDataParameter("DoNotEmail");

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
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		txtCity.safeSet(datatable.getDataParameter("City"));
		lstState.select(datatable.getDataParameter("State"));
		lstLanguage.select(datatable.getDataParameter("Language"));

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

	}

	/**
	 * 
	 * @summary Edit the primary guest info from the reservation page and Save
	 *          it.
	 * 
	 * @version Created 11/12/2015
	 * @author Praveen Namburi.
	 * @param The
	 *            datatable scenario
	 * @throws Exception
	 * @return NA
	 * 
	 */
	public void editPrimaryGuestDetailsAndSaveIt(String scenario)
			throws Exception {
		// Modify the primary guest details.
		editPrimaryGuestInfo(scenario);

		// Click on save button.
		clickSave();
	}

	/**
	 * 
	 * @summary Capture the following values from the Edit Guest Information
	 *          page : AddressLine1, PrimaryPhoneNumber, EmailAddress.. and
	 *          validate them.
	 * 
	 * @version Created 11/12/2015.
	 * @author Praveen Namburi.
	 * @return String Addressline1, primaryphonenumber, EmailAddress.
	 * 
	 */
	public void captureEditGuestInfoDetails(String scenario) {

		// Capture text from addressline 1 field and validate it.
		captureAddressLine1(scenario);

		// Capture primary phone number and validate it.
		capturePrimaryphoneNumber(scenario);

		// Capture email address and validate it.
		captureEmailaddress(scenario);

	}

	/**
	 * 
	 * @summary Method to get the Line1address from the Edit Guest info page and
	 *          validate it.
	 * @version Created 11/12/2015
	 * @author Praveen Namburi
	 * @param From
	 *            Datatable scenario
	 * @return String Line1Address
	 * 
	 */

	public String captureAddressLine1(String scenario) {

		// Set the virtual table and datascenario
		datatable.setVirtualtablePage("CheckInInfo");
		datatable.setVirtualtableScenario(scenario);

		pageLoaded(txtAddress1);
		txtAddress1.highlight(driver);

		String Line1Address = txtAddress1.getText();
		TestReporter.logStep("--------> Captured Addressline1 value is : "
				+ Line1Address);

		// Validating the AddresLine1 value with its expected and actual values.
		Assert.assertEquals(Line1Address,
				datatable.getDataParameter("PrimaryAddressLine1"));

		return Line1Address;

	}

	/**
	 * 
	 * @summary Method to get the Emailaddress from the Edit Guest info page and
	 *          validate it.
	 * @version Created 11/12/2015
	 * @author Praveen Namburi
	 * @param From
	 *            Datatable scenario
	 * @return String EmailAddress
	 * 
	 */

	public String captureEmailaddress(String scenario) {
		datatable.setVirtualtablePage("CheckInInfo");
		datatable.setVirtualtableScenario(scenario);

		pageLoaded(txtEmail);
		txtEmail.highlight(driver);

		String EmailAddress = txtEmail.getText();
		// System.out.println(EmailAddress);
		TestReporter.logStep("--------> Captured Emailaddress is : "
				+ EmailAddress);

		// Added the assertion for validating the EmailAddress.
		Assert.assertEquals(EmailAddress,
				datatable.getDataParameter("PrimaryEmailAddress"));
		return EmailAddress;

	}

	/**
	 * 
	 * @summary Created Method to get the PrimaryPhoneNumber from the Edit Guest
	 *          info page and validate it.
	 *
	 * @version Created 11/12/2015
	 * @author Praveen Namburi
	 * @param From
	 *            Datatable scenario
	 * @return String PrimaryphoneNumber
	 * 
	 */

	public String capturePrimaryphoneNumber(String scenario) {

		// Set the virtual table and datascenario
		datatable.setVirtualtablePage("CheckInInfo");
		datatable.setVirtualtableScenario(scenario);

		pageLoaded(txtPrimaryPhoneNum);
		txtPrimaryPhoneNum.highlight(driver);

		String PrimaryphoneNumber = txtPrimaryPhoneNum.getText();
		TestReporter.logStep("--------> Captured PrimaryphoneNumber is : : "
				+ PrimaryphoneNumber);

		// Added the assertion for validating the Primary Phone Number.
		Assert.assertEquals(PrimaryphoneNumber,
				datatable.getDataParameter("PrimaryPhoneNumber1"));
		return PrimaryphoneNumber;

	}
}
