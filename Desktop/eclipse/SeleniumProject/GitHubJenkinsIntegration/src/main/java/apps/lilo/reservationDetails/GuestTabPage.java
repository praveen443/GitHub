package apps.lilo.reservationDetails;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.WebDriverSetup;
import core.interfaces.Button;
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
 * 
 * @summary Contains the page methods & objects for guest tab page for a
 *          reservation
 * @version Created 9/15/2014
 * @author Jessica Marshall
 */
public class GuestTabPage {
	// ***********************
	// *** GuestTab Fields ***
	// ***********************
	private String getSrcAttribute;
	private String Xpath = ".//*[@id='pinSetUpConfirmForm:pinSetUpConfirmModalPanelContentTable']/tbody/tr[2]/td/table/tbody/tr[1]/td[1]/table/tbody/tr[2]/td/div/table/tbody/tr/td/input";
	private WebDriver driver;
	private String emailAddress;
	private Datatable datatable = new Datatable();

	// ************************************
	// *** Main GuestTabPage Elements ***
	// ************************************
	// Guest list table
	@FindBy(id = "viewGuestForm:guestList:tb")
	private Webtable tblGuestList;

	// Title
	@FindBy(id = "viewGuestForm:courtesyTitle")
	private Listbox lstTitle;

	// First name
	@FindBy(id = "viewGuestForm:guestname")
	private Textbox txtFirstName;

	// Last name
	@FindBy(id = "viewGuestForm:guestLastname")
	private Textbox txtLastName;

	// suffix
	@FindBy(id = "viewGuestForm:suffix")
	private Listbox lstSuffix;

	// Address type
	@FindBy(id = "viewGuestForm:addressTypeId")
	private Listbox lstAddressType;

	// country
	@FindBy(id = "viewGuestForm:countryId")
	private Listbox lstCountry;

	// address line 1
	@FindBy(id = "viewGuestForm:address1Id")
	private Textbox txtAddress1;

	// address line 2
	@FindBy(id = "viewGuestForm:address2")
	private Textbox txtAddress2;

	// zip code
	@FindBy(id = "viewGuestForm:ZipID")
	private Textbox txtZipCode;

	// city
	@FindBy(id = "viewGuestForm:cityId")
	private Textbox txtCity;

	// State
	@FindBy(id = "viewGuestForm:stateId")
	private Listbox lstState;

	// Language
	@FindBy(id = "viewGuestForm:guestAddPrefLanguageId")
	private Listbox lstLanguage;

	// primary phone type
	@FindBy(id = "viewGuestForm:phoneType1")
	private Listbox lstPriPhoneType;

	// primary phone number
	@FindBy(id = "viewGuestForm:phoneNumber1")
	private Textbox txtPrimaryPhoneNum;

	// secondary phone type
	@FindBy(id = "viewGuestForm:phoneType2")
	private Listbox lstSecPhoneType;

	// secondary phone number
	@FindBy(id = "viewGuestForm:phoneNumber2")
	private Textbox txtSecondaryPhoneNum;

	// email type
	@FindBy(id = "viewGuestForm:emailTypeId")
	private Listbox lstEmailType;

	// email address
	@FindBy(id = "viewGuestForm:emailAddress")
	private Textbox txtEmail;

	// Do not email
	@FindBy(id = "viewGuestForm:doNoMailCheckBox")
	private Checkbox chkDoNotEmail;
	
	// save
	@FindBy(id = "viewGuestForm:saveButton")
	private Button btnSave;
	
	// Cancel
	@FindBy(id = "viewGuestForm:cancelButton")
	private Button btnCancel;
	
	// Swap guest
	@FindBy(id = "viewGuestForm:swapButtonId")
	private Button btnSwapGuest;
	
	// View billing profile
	@FindBy(id = "viewGuestForm:viewallocationbutton")
	private Button btnViewBilling;
	
	// Undo claim
	@FindBy(id = "viewGuestForm:undoclaimbutton")
	private Button btnUndoClaim;
	
	// Address validation pop up
	@FindBy(id = "addressValidationConfirmMPanelIDCDiv")
	private Element eleAddressPopUp;
	
	// Address validation pop up
	@FindBy(id = "confirmationForm:noButton")
	private Button btnPopUpOk;
	
	// Proceed
	@FindBy(xpath = ".//*[@id='viewGuestForm:proToChkInButtonId']")
	private Link lnkProceed;
	
	// ApplyProceed
	@FindBy(id = "applyPymntForm:nextId")
	private Element eleApplyProceed;
	
	//Balance Due Yes Button
	@FindBy(id = "balanceDueCheckinForm:okButtonId1")
	private Button btnBalanceDue;
	
	//Complete CheckIn
	@FindBy(id = "newMediaEncodeForm:completeCheckinId")
	private Element eleCompleteCheckIn;
	
	//Printer Cancel
	@FindBy(id = "printEncodeForm:defaultPrintEncodCancelId")
	private Element elePrinterCancel;
	
	//Printer SetUP No
	@FindBy(id = "pinSetUpConfirmForm:j_id28123")
	private Element elePrintSetUpNo;
	
	//Printer Error
	@FindBy(id = "errorForm:okButtonId")
	private Button btnPrintErrorOk;
	
	//Pin Handle
	@FindBy(id = "bellserviceConfirmPopupForm:no")
	private Element eleBellNo;
	
	// Grab the primary guest checkbox
	@FindBy(css = "input[name^=\"viewGuestForm:guestList:0:j_id\"]")
	private Checkbox chkPrimaryGuestCheckBox;
	
	// Grab the Secondary guest checkbox
	@FindBy(id = "viewGuestForm:guestList:1:undoClaimrowCheckBoxId")
	private Checkbox chkSecondaryGuestCheckBox;
	
	// Grab secondary guest first name
	@FindBy(id = "viewGuestForm:guestList:1:firstName")
	private Element eleSecGstFName;
	
	// Grab secondary guest last name
	@FindBy(id = "viewGuestForm:guestList:1:lastName")
	private Element eleSecGstLName;
	
	// *********************
	// ** Build page area **
	// *********************
	public GuestTabPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	private GuestTabPage initialize(WebDriver driver) {
		return ElementFactory.initElements(driver,
				GuestTabPage.class);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnSave); 
		
	}
	
	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element); 
		
	}

	// **************************************
	// *** Main CheckIn Page Interactions ***
	// **************************************
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public void clickSave() {
		initialize(driver);
		btnSave.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickCancel() {
		btnCancel.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickSwapGuest() {
		btnSwapGuest.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickViewBilling() {
		btnViewBilling.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickUndoClaim() {
		btnUndoClaim.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void checkDoNotEmail(){
		initialize(driver);
		chkDoNotEmail.jsToggle(driver);
	}
	
	public void clickProceed(){
		lnkProceed.syncEnabled(driver);
		lnkProceed.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickApplyPaymentProceed(){
		pageLoaded(eleApplyProceed);
		eleApplyProceed.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clikYes(){
		pageLoaded(btnBalanceDue);
		btnBalanceDue.jsClick(driver);
//		pageLoaded(btnPrintErrorOk);
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		if(btnPrintErrorOk.syncVisible(driver, 5, false)){
			btnPrintErrorOk.click();			
		}
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
	}
	
	public void completeCheckIn(){
		initialize(driver);
		eleCompleteCheckIn.syncEnabled(driver);
		eleCompleteCheckIn.syncVisible(driver);
		eleCompleteCheckIn.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickSetPrinterCancel(){
//		initialize(driver);
		pageLoaded(elePrinterCancel);
		elePrinterCancel.syncVisible(driver);
		elePrinterCancel.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}


	/**
	 * 
	 * @summary Edits the primary guest info from the reservation page
	 * 
	 * @version Created 9/18/2014
	 * @author Jessica Marshall
	 * @param The datatable scenario
	 * @throws Exception
	 * @return
	 * 
	 */
	public void editPrimaryGuestInfo(String scenario) {
		/*datatable.setDatatableSheet("EditPrimaryGuestTabPage");
		datatable.setDatatableRow(datatable.getRowContains(scenario, 0));*/
		datatable.setVirtualtablePage("EditPrimaryGuestTabPage");
		datatable.setVirtualtableScenario(scenario);
		
		int counter = 0;
		int maxLoops = 10;
		boolean setValue = false;
		String doNotEmail = datatable.getDataParameter("DoNotEmail");
		
		//Set email address for later validation
		setEmailAddress(datatable.getDataParameter("EmailAddress"));
		
		// Enter the data
		lstTitle.select(datatable.getDataParameter("Title"));
		txtFirstName.safeSet(datatable.getDataParameter("FirstName"));
		txtLastName.safeSet(datatable.getDataParameter("LastName"));
		lstSuffix.select(datatable.getDataParameter("Suffix"));
		lstAddressType.select(datatable.getDataParameter("AddressType"));
		lstCountry.select(datatable.getDataParameter("Country"));
		txtAddress1.safeSet(datatable.getDataParameter("Address1"));
		txtAddress2.safeSet(datatable.getDataParameter("Address2"));
		Sleeper.sleep(1000);
		
		//handling for stale element exception, happens when you change country or just timing in general
		txtZipCode.syncVisible(driver);
		while (counter <= maxLoops){

			setValue = true;
			try {
				txtZipCode.safeSet(datatable.getDataParameter("ZipCode"));
			}
			catch (WebDriverException e){	
				setValue = false;
			}
			Sleeper.sleep(1000);
			counter++;
			initialize(driver);
			if (setValue == true){
				break;
			}
		}
		//txtZipCode.safeSet(datatable.getDataParameter("ZipCode"));
		
		//Zip code auto populates
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		Sleeper.sleep(1000);
		txtCity.safeSet(datatable.getDataParameter("City"));
		//lstState.syncVisible(driver);
		lstState.select(datatable.getDataParameter("State"));
		lstLanguage.select(datatable.getDataParameter("Language"));
		
		//phone numbers auto format
		lstPriPhoneType.select(datatable.getDataParameter("PrimaryPhoneType"));
		
		//putting a wait here to handle a stale element reference on this element
		//txtPrimaryPhoneNum.syncVisible(driver);
		Sleeper.sleep(2000);
		initialize(driver);
		//This code will handle the element exceptions while the zip code autopopulates
		counter = 0;
		while (counter <= maxLoops){

			setValue = true;
			try {
				txtPrimaryPhoneNum.safeSet(datatable.getDataParameter("PrimaryPhoneNum"));
			}
			catch (WebDriverException e){	
				setValue = false;
			}
			Sleeper.sleep(1000);
			counter++;
			initialize(driver);
			if (setValue == true){
				break;
			}
		}

		Sleeper.sleep(2000);
		lstSecPhoneType.select(datatable.getDataParameter("SecondaryPhoneType"));
		txtSecondaryPhoneNum.safeSet(datatable.getDataParameter("SecondaryPhoneNum"));
		Sleeper.sleep(2000);
		
		lstEmailType.select(datatable.getDataParameter("EmailType"));
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		txtEmail.syncVisible(driver);
		txtEmail.safeSet(datatable.getDataParameter("EmailAddress"));
		
		//Only check the do not email if specified "ON"
		if (doNotEmail.equalsIgnoreCase("ON")){
			chkDoNotEmail.check();
		}
		
	}
	
	public String getEmailAddressFieldValue(){
		return txtEmail.getText();
	}
	
	public String getErrorMessage(){
		return eleAddressPopUp.getText();
	}
	
	public void clickPopUpOk(){
		btnPopUpOk.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	
	/**
	 * 
	 * @summary Determines if the city text field is filled out,
	 * 			useful in determining if the zip code autopopulates the field
	 * 			uses isEmpty()
	 * @version Created 9/23/2014
	 * @author Jessica Marshall
	 * @param  NA
	 * @throws NA
	 * @return true if is empty, false if not
	 * 
	 */
	public boolean cityTextFieldEmpty() {
		return txtCity.getText().isEmpty();
	}
	
	/**
	 * 
	 * @summary Determines if the state listbox is filled out,
	 * 			useful in determining if the zip code autopopulates the field
	 * 			Compares against the default value "State...", have to use a 
	 * 			zip code that system has a matching state for (27103, 27284, etc)
	 * 			to test
	 * @version Created 9/23/2014
	 * @author Jessica Marshall
	 * @param  NA
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
	    * @summary Method to Handle print set up popUp
	    * @version Created 11/08/2015
	    * @author Atmakuri Venkatesh
	    * @param  CheckOut Reason
	    * @throws NA
	    * @return NA
	    * 
	    */
	
	public void handlePINsetup(){
		List<WebElement> inputsofPINSetUp = driver.findElements(By.xpath(Xpath));
		for (WebElement input : inputsofPINSetUp) {
			getSrcAttribute = input.getAttribute("src");
			System.out.println("-->" + getSrcAttribute);
			if (getSrcAttribute.contains("no_popup")) {
				String getID = input.getAttribute("id");
				Element element = new ElementImpl(driver.findElement(By.id(getID)));
				element.jsClick(driver);
				new ProcessingYourRequest().WaitForProcessRequest(driver);
				break;
			}
		}
	}
	
	/**
	    * 
	    * @summary Method to Proceed Complete CheckIn Process in Guest Page
	    * @version Created 11/08/2015
	    * @author Atmakuri Venkatesh
	    * @param  NA
	    * @throws NA
	    * @return NA
	    * 
	    */
	public void clickProceedAndApply(){
		pageLoaded(eleApplyProceed);
		clickApplyPaymentProceed();
		clikYes();
		completeCheckIn();
		clickSetPrinterCancel();
		handlePINsetup();
		clickBellNoBUtton();
	}
	
	public void clickBellNoBUtton(){
		initialize(driver);
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try{
			if(eleBellNo.syncVisible(driver, 5, false)){
				eleBellNo.jsClick(driver);
			}
		}catch(Exception e){
			
		}
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
	}
	
	//Click Primary Guest Check Box
	public void clickPrimayGuestCheckBox(){
		pageLoaded(chkPrimaryGuestCheckBox);
		chkPrimaryGuestCheckBox.highlight(driver);
		chkPrimaryGuestCheckBox.click();
	}
	
	

	/**
	 * 
	 * @summary Edits the secondary guest info from the reservation page
	 * 
	 * @version Created 12/21/2015
	 * @author Marella Satish
	 * @param The datatable scenario
	 * @throws 
	 * @return
	 * 
	 */
	public void editSecondaryGuestInfo(String scenario) {
		
		datatable.setVirtualtablePage("EditPrimaryGuestTabPage");
		datatable.setVirtualtableScenario(scenario);
		
		//Select secondary guest
		clickSecondaryGuestCheckBox();
		
		Sleeper.sleep(3000);
		
		// Enter the data
		lstTitle.select(datatable.getDataParameter("Title"));
		txtFirstName.safeSet(datatable.getDataParameter("FirstName"));
		txtLastName.safeSet(datatable.getDataParameter("LastName"));
		lstSuffix.select(datatable.getDataParameter("Suffix"));
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		
	}
	
	
	/**
	 * 
	 * @summary Click Secondary Guest Checkbox
	 * @version Created 12/21/2015
	 * @author Marella Satish
	 * @param 
	 * @throws 
	 * @return
	 * 
	 */
		public void clickSecondaryGuestCheckBox(){
			pageLoaded(chkSecondaryGuestCheckBox);
			chkSecondaryGuestCheckBox.highlight(driver);
			chkSecondaryGuestCheckBox.click();
			new ProcessingYourRequest().WaitForProcessRequest(driver);
		}
		
		
	/**
	 * 
	 * @summary Validates the secondary guest information after updating
	 * @version Created 12/21/2015
	 * @author Marella Satish
	 * @param The datatable scenario
	 * @throws 
	 * @return
	 * 
	 */
	public void validateModifiedSecondaryGuest(String scenario){
		datatable.setVirtualtablePage("EditPrimaryGuestTabPage");
		datatable.setVirtualtableScenario(scenario);
		String ExpectedSecGuestFName =   datatable.getDataParameter("FirstName");
		String ExpectedSecGuestLName = datatable.getDataParameter("LastName");
		String ActualSecGuestFName = eleSecGstFName.getText().trim();
		String ActualSecGuestLName = eleSecGstLName.getText().trim();
		
		TestReporter.logStep("Secondary Guest First Name "+ActualSecGuestFName );
		TestReporter.logStep("Secondary Guest Last Name "+ActualSecGuestLName );
		
		TestReporter.assertEquals(ExpectedSecGuestFName, ActualSecGuestFName, "Actual Secondary Guest First Name  ["+ActualSecGuestFName+"] "
				+ "does not equals expected Secondary Guest First Name[ "+ExpectedSecGuestFName+"]");
		TestReporter.logStep("Secondary Guest First Name updated successfully");
		
		TestReporter.assertEquals(ExpectedSecGuestLName, ActualSecGuestLName, "Actual Secondary Guest Last Name  ["+ActualSecGuestLName+"] "
				+ "does not equals expected Secondary Guest Last Name[ "+ExpectedSecGuestLName+"]");
		TestReporter.logStep("Secondary Guest Last Name updated successfully");
		
	}
	
	
}

