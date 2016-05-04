package apps.lilo.reservationDetails;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import apps.SettlementUI.SettlementUIWindow;
import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Link;
import core.interfaces.Webtable;
import core.interfaces.Textbox;
import core.interfaces.Listbox;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

/**
 * 
 * @summary Contains the page methods & objects for modify paying guest page
 *          that pops up when you modify the responsible party details from the
 *          billing options page
 * @version Created 9/19/2014
 * @author Jessica Marshall
 */
public class ModifyPayingGuestPage {
	// ********************************
	// *** ModifyPayingGuest Fields ***
	// ********************************
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	private String ParentWindow;

	// ************************************
	// *** Main Page Elements ***
	// ************************************

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

	// page object to get text from error pop-up message
	@FindBy(xpath = "//table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td/table/tbody/tr/td[2]/div/ul/li[1]")
	private Element eleGetErrorInfo;

	// Error Div
	@FindBy(id = "PMSRErrorModalPanelContentDiv")
	private Element eleErrorMsgPopUP;

	// page object to get text from error pop-up message for Invalid fields
	@FindBy(xpath = "//table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td/table/tbody/tr/td[2]/div/ul/li[2]")
	private Element eleGetErrorPopUpMsg;

	// page object for OK button in error pop-up msg.
	@FindBy(id = "errorForm:okButtonId")
	private Button btnErrorOK;

	@FindBy(linkText = "Add/Modify")
	private Link lnkAddOrModify;

	@FindBy(id = "addRespPartyForm:closeRespPartyHiddenButton")
	private Element elePartyFormClose;

	@FindBy(id = "addRespPartyModalPanelCDiv:")
	private Element eleModPayGuestDiv;

	// *********************
	// ** Build page area **
	// *********************

	public ModifyPayingGuestPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	private ModifyPayingGuestPage initialize(WebDriver driver) {
		return ElementFactory.initElements(driver, ModifyPayingGuestPage.class);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnSave);
	}

	/**
	 * @summary : Method to determine if the text box is loaded in the page.
	 * @author : Praveen Namburi
	 * @version : Created 11/23/2015
	 * @param : textbox
	 * @return : Boolean-true, if the textbox is found, else will return -
	 *         false.
	 * 
	 */
	public boolean pageLoaded(Textbox textbox) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				textbox);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				element);
	}

	// **************************************
	// *** Main CheckIn Page Interactions ***
	// **************************************

	public void clickSave() {
		btnSave.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickClose() {
		btnClose.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickAddModify() {
		btnAddModify.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickAddressPopUpYes() {
		btnAddressPopUpYes.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickAddressPopUpCancel() {
		btnAddressPopUpCancel.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickInfoPopUpOk() {
		btnInfoPopUpOK.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
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
		btnRefilterOk.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * 
	 * @summary Completes the process of saving the responsible party updates,
	 *          clicks save and then handles the 3-4 different pop ups/warnings
	 *          that display and then clicks close. If it doesn't save
	 *          successfully, it will return false, other wise just returns true
	 * 
	 * @version Created 9/24/2014
	 * @author Jessica Marshall
	 * @param NA
	 * @throws Exception
	 * @return Boolean
	 * 
	 */
	public boolean completeRespPartyUpdates() {

		clickSave();

		// Returns a false here in
		if (isInfoPopUpDisplayed()) {
			if (getInfoPopUpMsg().contains("Error")) {
				// Log the error:
				Reporter.log("Error message from modify paying guest window: "
						+ getInfoPopUpMsg());
				return false;
			}
		}

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

		return true;

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
	 * @return NA
	 * 
	 */
	public void editRespPartyAddressInfo(String scenario) throws Exception {
		/*
		 * datatable.setDatatableSheet("EditRespPartyAddressInfo");
		 * datatable.setDatatableRow(datatable.getRowContains(scenario, 0));
		 */
		datatable.setVirtualtablePage("EditRespPartyAddressInfo");
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

		// handling for stale element exception, happens when you switch the
		// country
		txtZipCode.syncVisible(driver);
		Sleeper.sleep(500);
		txtZipCode.safeSet(datatable.getDataParameter("ZipCode"));

		// Zip code auto populates
		new ProcessingYourRequest().WaitForProcessRequest(driver);
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
		Sleeper.sleep(1000);
	}

	/**
	 * 
	 * @summary Determines if the city text field is filled out, useful in
	 *          determining if the zip code autopopulates the field uses
	 *          isEmpty()
	 * @version Created 9/23/2014
	 * @author Jessica Marshall
	 * @param NA
	 * @throws NA
	 * @return true if is empty, false if not
	 * 
	 */
	public boolean cityTextFieldEmpty() {
		return txtCity.getText().isEmpty();
	}

	/**
	 * 
	 * @summary Determines if the state listbox is filled out, useful in
	 *          determining if the zip code autopopulates the field Compares
	 *          against the default value "State...", have to use a zip code
	 *          that system has a matching state for (27103, 27284, etc) to test
	 * @version Created 9/23/2014
	 * @author Jessica Marshall
	 * @param NA
	 * @throws NA
	 * @return true if state is autopopulated, false if not
	 * 
	 */
	public boolean stateAutopopulated() {
		if (lstState.getFirstSelectedOption().getText().contains("State..."))
			return false;
		else
			return true;
	}

	/**
	 * 
	 * @summary Modify the responsible party address info
	 * 
	 * @version Created 11/05/2015
	 * @author Marella Satish
	 * @param The
	 *            datatable scenario
	 * @throws Exception
	 * @return NA
	 * 
	 */
	public void ModifyGuestInfo(String scenario) {

		datatable.setVirtualtablePage("EditRespPartyAddressInfo");
		datatable.setVirtualtableScenario(scenario);

		// Enter the data
		txtAddress1.safeSet(datatable.getDataParameter("Address1"));

		// handling for stale element exception, happens when you switch the
		// country
		txtZipCode.syncVisible(driver);
		txtZipCode.safeSet(datatable.getDataParameter("ZipCode"));

		// Zip code auto populates
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		// phone numbers auto format
		lstPriPhoneType.select(datatable.getDataParameter("PrimaryPhoneType"));
		txtPrimaryPhoneNum.safeSet(datatable
				.getDataParameter("PrimaryPhoneNum"));

		// TaxExempt,TaxExemptType and TaxExemptID
		String Taxexempt = datatable.getDataParameter("TaxExempt");
		if (Taxexempt.equalsIgnoreCase("True")) {
			lstTaxExempt.select("Yes");
		}
		lstTaxExemptType.highlight(driver);
		lstTaxExemptType.select(datatable.getDataParameter("TaxExemptType"));
		txtTaxExemptID.highlight(driver);
		txtTaxExemptID.safeSet(datatable.getDataParameter("TaxExemptID"));

		// Validates all the pop up's if any when clicks on Save button
		completeRespPartyUpdates();

	}

	// collects all the required guest information into an array
	public String[] getGuestDetails() {

		String GuestInfo[];
		String GuestData;
		GuestData = txtAddress1.getText() + ";" + txtPrimaryPhoneNum.getText()
				+ ";" + txtZipCode.getText();

		// Splits the value Guest message
		GuestInfo = StringUtils.split(GuestData, ";");

		TestReporter.logStep("Address [" + GuestInfo[0] + "] Phone ["
				+ GuestInfo[1] + "] ZipCode [" + GuestInfo[2] + "]");
		return GuestInfo;
	}

	/**
	 * 
	 * @summary : Method to Modify and save the responsible party with Invalid
	 *          address info
	 * 
	 * @version : Created 11/23/2015
	 * @author : Praveen Namburi
	 * @param : The datatable scenario
	 * @throws : Exception
	 * @return : NA
	 * 
	 */
	public void ModifyPayingGuestWithInvalidAddressInfo(String scenario) {

		datatable.setVirtualtablePage("EditRespPartyAddressInfo");
		datatable.setVirtualtableScenario(scenario);

		// Enter the data
		pageLoaded(txtAddress1);
		txtAddress1.clear();
		txtAddress1.safeSet(datatable.getDataParameter("Address1"));

		// handling for stale element exception, happens when you switch the
		// country
		txtZipCode.syncVisible(driver);
		txtZipCode.safeSet(datatable.getDataParameter("ZipCode"));

		// Zip code auto populates
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		// phone numbers auto format
		lstPriPhoneType.select(datatable.getDataParameter("PrimaryPhoneType"));
		txtPrimaryPhoneNum.safeSet(datatable
				.getDataParameter("PrimaryPhoneNum"));

		// Click on Save button
		clickSave();

	}

	/**
	 * 
	 * @summary : Method to validate the error pop-up message after setting up
	 *          with Invalid address in Modify paying guest page.
	 * 
	 * @version : Created 11/23/2015
	 * @author : Praveen Namburi
	 * @param : NA
	 * @throws : Exception
	 * @return : NA
	 * 
	 */
	public void VerifyTheErrorPopUpMessage() {

		initialize(driver);

		// get the text from pop-up and validate it.
		pageLoaded(eleInfoPopUp);
		String getErrorMsg1 = eleGetErrorInfo.getText();
		TestReporter.logStep("----> Error pop-up message is :" + getErrorMsg1);

		String getErrorMessage2 = eleGetErrorPopUpMsg.getText();
		TestReporter.logStep("----> Error Message for invalid Info : "
				+ getErrorMessage2);

		Sleeper.sleep(1000);

		// Validating the error pop-up message here
		TestReporter.assertTrue(getErrorMessage2
				.contains("Missing Required Fields : Address 1."),
				"The expected string doesn't matches with the actual string.");

		// Close the Error pop-up message by clicking on OK button.
		pageLoaded(btnErrorOK);
		btnErrorOK.highlight(driver);
		btnErrorOK.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);

	}

	/**
	 * 
	 * @summary Method to click on add/Modify Button and capture the Parent
	 *          Window Id
	 * @version Created 11/23/2014
	 * @author Venkatesh A
	 * @param NA
	 * @throws NA
	 * @return Parent Window Id at run time
	 * 
	 */
	public String clickAddOrModifyButton() {
		SettlementUIWindow SettlementUI = new SettlementUIWindow(driver);
		ParentWindow = SettlementUI.captureParrentWindow();
		System.out.println("Capturing Parent Window Id : " + ParentWindow);
		lnkAddOrModify.focus(driver);
		lnkAddOrModify.click();
		return ParentWindow;
	}

	/**
	 * 
	 * @summary Method to navigate back to the Parent Window Id
	 * @version Created 11/24/2014
	 * @author Venkatesh A
	 * @param NA
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void swithToParentWindow(String ParentWindow) {
		Sleeper.sleep(3000);
		System.out.println("Parent WINdo in CLose method : " + ParentWindow);
		driver.switchTo().window(ParentWindow);

	}

	public void clickCancelButton() {

		pageLoaded(eleModPayGuestDiv);
		initialize(driver);

		elePartyFormClose.highlight(driver);
		elePartyFormClose.click();
	}

	/**
	 * 
	 * @summary Method to click Express CheckOut Check Box
	 * @version Created 11/24/2014
	 * @author Venkatesh A
	 * @param NA
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void checkExpressCheckOut() {
		pageLoaded(chkExpressCheckout);
		initialize(driver);
		chkExpressCheckout.jsClick(driver);
	}

	/**
	 * 
	 * @summary Method to UnCheck the Email Check Box
	 * @version Created 11/24/2014
	 * @author Venkatesh A
	 * @param NA
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void unCheckEmail() {
		pageLoaded(chkEmail);
		initialize(driver);
		Sleeper.sleep(2000);
		chkEmail.syncEnabled(driver);
		chkEmail.jsClick(driver);
	}
}

