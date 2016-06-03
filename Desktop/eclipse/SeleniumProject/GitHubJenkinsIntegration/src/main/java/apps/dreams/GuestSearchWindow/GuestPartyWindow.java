package apps.dreams.GuestSearchWindow;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import apps.dreams.AccommodationWrapUp.AccommodationWrapUpContentFrame;
import apps.dreams.PleaseWait.PleaseWait;
import core.WindowHandler;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Link;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

/**
 * @summary Contains the methods & objects for the Dreams UI Guest Party window
 * @version Created 09/30/2014
 * @author Waightstill W. Avery
 */
public class GuestPartyWindow {
	private WebDriver driver;
	private String firstlastName;
	private String fullName;
	private Datatable datatable = new Datatable();

	// ******************************
	// *** Content Frame Elements ***
	// ******************************
	@FindBy(name = "Ok")
	private Button btnOk;

	/*
	 * Personal Info
	 */
	@FindBy(name = "b_Yes")
	private Button btnYes;

	@FindBy(name = "individualTO.lastName")
	private Textbox txtLastName;

	@FindBy(name = "individualTO.firstName")
	private Textbox txtFirstName;

	@FindBy(name = "individualTO.middleName")
	private Textbox txtMiddleName;

	@FindBy(name = "selectedSuffix")
	private Listbox lstSuffix;

	@FindBy(name = "selectedSalutation")
	private Listbox lstTitle;

	@FindBy(name = "individualTO.ageAsString")
	private Textbox txtAge;

	@FindBy(id = "birthDate")
	private Textbox txtBirthDate;

	/*
	 * GuestPreferences Info
	 */
	@FindBy(name = "DoNotContInd")
	private Checkbox chkDoNotContact;

	/*
	 * Address Info
	 */
	@FindBy(id = "addressLine1")
	private Textbox txtAddressLine1;

	@FindBy(id = "zipCode")
	private Textbox txtPostalCode;

	@FindBy(id = "city")
	private Textbox txtCity;

	@FindBy(id = "state")
	private Textbox txtState;

	@FindBy(id = "country")
	private Listbox lstCountry;

	@FindBy(xpath = "/html/body/form/div/table[7]/tbody/tr/td[2]/input[2]")
	private Button btnUpdateAddress;

	@FindBy(name = "selectedAddressLocatorType")
	private Listbox lstAddressType;

	@FindBy(name="addressTO.primary")
	private Checkbox chkAddressPrimary;

	@FindBy(xpath = "//*[@id='firstLayer']/table[8]/tbody/tr[3]/td[1]/div/a")
	private Link lnkEditAddress;

	/*
	 * Phone Info
	 */
	@FindBy(name = "selectedPhoneLocatorType")
	private Listbox lstPhoneType;

	@FindBy(id = "number")
	private Textbox txtPhoneNumber;

	@FindBy(xpath = "/html/body/form/div/table[9]/tbody/tr/td[2]/input[2]")
	private Button btnUpdatePhone;

	@FindBy(name = "phoneTO.primary")
	private Checkbox chkPhonePrimary;

	@FindBy(xpath = "//*[@id='firstLayer']/table[10]/tbody/tr[3]/td[1]/div/a")
	private Link lnkEditPhone;
	
	@FindBy(xpath = "//*[@id='firstLayer']/table[12]/tbody/tr[4]/td[1]/div/a")
	private Link lnkEditEmail;
	

	/*
	 * Email Info
	 */
	@FindBy(name = "selectedEmailLocatorType")
	private Listbox lstEmailType;

	@FindBy(id = "emailAddress")
	private Listbox lstEmailAddress;

	@FindBy(xpath = "/html/body/form/div/table[11]/tbody/tr/td[2]/input[2]")
	private Button btnUpdateEmail;

	@FindBy(name = "emailTO.doNotEmailIndicator")
	private Checkbox chkDoNotEmailIndicator;

	@FindBy(name = "emailTO.primary")
	private Checkbox chkPrimaryEmail;

	@FindBy(name = "emailTO.address")
	private Textbox txtEmailAddress;

	/*
	 * Warning popup
	 */
	@FindBy(name = "b_Yes")
	private Button btnWarningYes;

	@FindBy(id = "errorStr")
	private Element eleErrorMsg;

	@FindBy(name = "b_yes")
	private Button btnAlertOk;

	@FindBy(xpath = "//*[@id= 'LkUp1But']  [@value='Cancel']")
	private  Button btnCancel;

	@FindBy(name = "individualFilterTO.zipCode")
	private Textbox txtZipCode;

	@FindBy(name = "addressTO.zipCode")
	private Textbox txtZip;	


	@FindBy(name = "addressTO.addressLine1")
	private Textbox txtAddress;

	@FindBy(name = "emailTO.address")
	private Textbox txtEmail;

	@FindBy(id = "ext")
	private Textbox txtPhoneExtension;

	// *********************
	// ** Build page area **
	// *********************

	/**
	 * 
	 * @summary Constructor to initialize the window
	 * @version Created 09/26/2014
	 * @author Waightstill W Avery
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public GuestPartyWindow(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnOk);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	public GuestPartyWindow initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	// *****************************************
	// *** Content Frame Interactions ***
	// *****************************************
	public void enterPrimaryGuestInfo(String scenario) {
		datatable.setVirtualtablePage("PrimaryGuestList");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded(lstTitle);

		String firstname = datatable.getDataParameter("FirstName");
		String lastname = datatable.getDataParameter("LastName");
		String title = datatable.getDataParameter("Title");
		String address = datatable.getDataParameter("Address");
		String phoneNumber = datatable.getDataParameter("PhoneNumber");
		String country = datatable.getDataParameter("Country");
		String negative = datatable.getDataParameter("Negative");
		String phoneNumberExtension = datatable.getDataParameter("PhoneExtension");
		String birthdate =  datatable.getDataParameter("BirthDate");
		String negativeAddress = datatable.getDataParameter("NegativeAddress");
		String negativePhone = datatable.getDataParameter("NegativePhone");
		String state = datatable.getDataParameter("State");
		firstlastName = firstlastnameConcat(firstname, lastname);

		fullName = firstname + " " + lastname;

		lstTitle.select(title);
		txtBirthDate.safeSet(birthdate);
		txtState.safeSet(state);
		txtAddressLine1.safeSet(address);
		lstCountry.select(country);
		//Updated the below step by Praveen - 2/23/2016
		pageLoaded(txtPhoneNumber);
		txtPhoneNumber.safeSet(phoneNumber);
		txtPhoneExtension.safeSet(phoneNumberExtension);

		// JavaScript clicks are found to not invoke expected behavior for
		// negative tests. Therefore, Selenium clicks will be used for negative
		// tests, and jsClicks otherwise
		if(!negative.equalsIgnoreCase("true") || negativeAddress.equalsIgnoreCase("true")){
			pageLoaded(btnUpdateAddress);
			btnUpdateAddress.syncVisible(driver);
			btnUpdateAddress.click();
			PleaseWait.WaitForPleaseWait(driver);
			try{
				try{
					WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
				}catch(TimeoutException te){}
				WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
				WindowHandler.waitUntilWindowExistsTitleContains(driver, "Party");
				new PageLoaded().isDomComplete(driver);
			}catch(Exception e){
				Sleeper.sleep(2000);
			}
		}else{
			pageLoaded(btnUpdateAddress);
			btnUpdateAddress.syncVisible(driver);
			btnUpdateAddress.jsClick(driver);
			PleaseWait.WaitForPleaseWait(driver);
			try{
				try{
					WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
				}catch(TimeoutException te){}
				WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
				WindowHandler.waitUntilWindowExistsTitleContains(driver, "Party");
				new PageLoaded().isDomComplete(driver);
			}catch(Exception e){
				Sleeper.sleep(2000);
			}
		}

		if(!negative.equalsIgnoreCase("true")|| negativePhone.equalsIgnoreCase("true")){
			pageLoaded(btnUpdatePhone);
			btnUpdatePhone.syncVisible(driver);
			btnUpdatePhone.jsClick(driver);
			PleaseWait.WaitForPleaseWait(driver);
			try{
				try{
					WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
				}catch(TimeoutException te){}
				WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
				WindowHandler.waitUntilWindowExistsTitleContains(driver, "Party");
				new PageLoaded().isDomComplete(driver);
			}catch(Exception e){
				Sleeper.sleep(2000);
			}
		}else{
			pageLoaded(btnUpdatePhone);
			btnUpdatePhone.syncVisible(driver);
			btnUpdatePhone.jsClick(driver);
			PleaseWait.WaitForPleaseWait(driver);
			try{
				try{
					WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
				}catch(TimeoutException te){}
				WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
				WindowHandler.waitUntilWindowExistsTitleContains(driver, "Party");
				new PageLoaded().isDomComplete(driver);
			}catch(Exception e){
				Sleeper.sleep(2000);
			}
		}

		if(!negative.equalsIgnoreCase("true")){
			pageLoaded(btnUpdateEmail);
			btnUpdateEmail.syncVisible(driver);
			btnUpdateEmail.scrollIntoView(driver);
			btnUpdateEmail.jsClick(driver);
			PleaseWait.WaitForPleaseWait(driver);
			try{
				try{
					WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
				}catch(TimeoutException te){}
				WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
				WindowHandler.waitUntilWindowExistsTitleContains(driver, "Party");
				new PageLoaded().isDomComplete(driver);
			}catch(Exception e){
				Sleeper.sleep(2000);
			}
		}

		if(!negativeAddress.equalsIgnoreCase("true") && !negativePhone.equalsIgnoreCase("true")){
			clickOk();
		}
		if(!negative.equalsIgnoreCase("true")){
			clickonYesButton();
		}else{
			validateCreateGuestAlert(scenario);
		}
	}
	
	public void enterPrimaryGuestInfoNameTitleOnly(String scenario) {
		datatable.setVirtualtablePage("PrimaryGuestList");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded(lstTitle);

		String firstname = datatable.getDataParameter("FirstName");
		String lastname = datatable.getDataParameter("LastName");
		String title = datatable.getDataParameter("Title");
		fullName = firstname + " " + lastname;
		lstTitle.select(title);

		/*if(txtFirstName.getText().isEmpty()) txtFirstName.jsSet(firstname);
		if(txtLastName.getText().isEmpty()) txtLastName.jsSet(lastname);*/
		
		clickOk();
		clickonYesButton();
	}

	public String getPrimaryGuestFullName(){
		return fullName;
	}

	public void clickonYesButton() {				
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 3);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "WARNING");
		clickYes();
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,"Disney Reservation Entry and Management System");
	}

	public void clickYes() {
		pageLoaded(btnYes);
		btnYes.jsClick(driver);
	}

	// Method will concat Last last and First Name and return a concatinated
	// String(i.e. ----GUEST,ONE)
	public String firstlastnameConcat(String fristname, String lastName) {
		String fName = lastName.toUpperCase().concat(",");
		String concat = fName.concat(fristname.toUpperCase());
		return concat;
	}

	public String returnconcatfirstlastname() {

		return firstlastName;
	}

	public void clickOk() {
		pageLoaded(btnOk);
//		btnOk.click();
		btnOk.focus(driver);
		btnOk.sendKeys(Keys.ENTER);
		PleaseWait.WaitForPleaseWait(driver);
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 3);
	}
	
	 
	 private void clickEditEmailLink(){
		 	pageLoaded(lnkEditEmail);
		 	lnkEditEmail.syncVisible(driver);
		 	lnkEditEmail.highlight(driver);
		 	lnkEditEmail.jsClick(driver);
	 }
	 
	 

	/**
	 * @summary Method to handle and Validate if trying to create guest without passing any search criteria 
	 * @version Created 02/18/2016
	 * @author Marella Satish
	 * @param Datatable scenario
	 * @throws NA
	 * @return NA
	 */
	public void validateCreateGuestAlert(String scenario) {
		datatable.setVirtualtablePage("ErrorMessages");
		datatable.setVirtualtableScenario(scenario);

		String expectedError = datatable.getDataParameter("Message");
		if ((WindowHandler.waitUntilNumberOfWindowsAre(driver, 3)) && 
				(WindowHandler.waitUntilWindowExistsTitleContains(driver, "Alert"))) {
			String getAlertMessage = eleErrorMsg.getText();
			TestReporter.log("Alert Message : " +getAlertMessage);
			TestReporter.assertEquals(expectedError, getAlertMessage, "The expected message"
					+ "[ "+expectedError+" ] is not same as actual message[ "+getAlertMessage+" ]");
			btnAlertOk.highlight(driver);
			btnAlertOk.click();
			TestReporter.log("Alert Handled");
			WindowHandler.waitUntilWindowExistsTitleContains(driver,"Party");
		}

	}

	/**
	 * @summary Method to click cancel button from guest search page
	 * @version Created 02/17/2016
	 * @author Marella Satish
	 * @param NA
	 * @throws NA
	 * @return NA
	 */
	public void clickCancel(){
		pageLoaded(btnCancel);
		btnCancel.click();
		clickonYesButton();
	}	

	public void clickBtnUpdateAddress(){
		pageLoaded(btnUpdateAddress);
		btnUpdateAddress.syncVisible(driver);
		btnUpdateAddress.highlight(driver);
		btnUpdateAddress.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void clickBtnUpdatePhone(){
		pageLoaded(btnUpdatePhone);
		btnUpdatePhone.syncVisible(driver);
		btnUpdatePhone.highlight(driver);
		btnUpdatePhone.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void clickBtnUpdateEmail(){
		pageLoaded(btnUpdateEmail);
		btnUpdateEmail.scrollIntoView(driver);
		btnUpdateEmail.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	private void clickEditPhoneLink(){
		pageLoaded(lnkEditPhone);
		lnkEditPhone.syncVisible(driver);
		lnkEditPhone.highlight(driver);
		lnkEditPhone.jsClick(driver);
	}

	/**
	 * @summary: Method to enter and update primary guest party info.
	 * @author: Praveen Namburi
	 * @version: Created 2-23-2016
	 * @param scenario
	 */
	public void enterAndupdatePrimaryGuestPartyInfo(String scenario) {
		datatable.setVirtualtablePage("PrimaryGuestList");
		datatable.setVirtualtableScenario(scenario);

		String personalInfo = datatable.getDataParameter("PersonalInfo");
		String addressInfo = datatable.getDataParameter("AddressInfo");
		String guestPreferencesInfo = datatable.getDataParameter("GuestPreferencesInfo");
		//String setphoneInfo = datatable.getDataParameter("PhoneInfo");
		String editphoneInfo = datatable.getDataParameter("EditphoneInfo");
		String editemailInfo = datatable.getDataParameter("EditemailInfo");
		String emailInfo = datatable.getDataParameter("EmailInfo");
		String firstname = datatable.getDataParameter("FirstName");
		String lastname = datatable.getDataParameter("LastName");
		String title = datatable.getDataParameter("Title");
		String birthDate = datatable.getDataParameter("BirthDate");
		String age = datatable.getDataParameter("Age");
		String doNotContact = datatable.getDataParameter("DoNotContact");
		String addressType = datatable.getDataParameter("AddressType");
		String primaryAddress = datatable.getDataParameter("PrimaryAddress");
		String address = datatable.getDataParameter("Address");
		String postalCode = datatable.getDataParameter("Zip");
		String city = datatable.getDataParameter("City");
		String state = datatable.getDataParameter("State");
		String phoneNumber = datatable.getDataParameter("PhoneNumber");
		String phonePrimary = datatable.getDataParameter("PhonePrimary");
		String primaryEmail = datatable.getDataParameter("PrimaryEmail");
		String doNotEmailIndicator = datatable.getDataParameter("DoNotEmailIndicator");
		String emailAddress = datatable.getDataParameter("Email");
		String NewGuestInfo = datatable.getDataParameter("NewGuestInfo");


		pageLoaded(lstTitle);

		//Enter Personal Info.
		if(personalInfo.equalsIgnoreCase("true")){
			lstTitle.select(title);
			txtLastName.safeSet(lastname);
			txtFirstName.safeSet(firstname);
			txtBirthDate.safeSet(birthDate);
			txtAge.safeSet(age);
		}

		//Enter and update the Guest address information.
		if(addressInfo.equalsIgnoreCase("true")){
			pageLoaded(lstAddressType);
			lstAddressType.select(addressType);

			if(primaryAddress.equalsIgnoreCase("ON")){
				chkAddressPrimary.syncVisible(driver);
				chkAddressPrimary.highlight(driver);
				chkAddressPrimary.check();
			}
			txtAddressLine1.safeSet(address);
			txtPostalCode.safeSet(postalCode);
			txtCity.safeSet(city);
			txtState.safeSet(state);
			//Click button UpdateAddress
			clickBtnUpdateAddress();
		}

		//Enter GuestPreferences Information.
		if(guestPreferencesInfo.equalsIgnoreCase("true")){
			if(doNotContact.equalsIgnoreCase("ON")){
				chkDoNotContact.syncVisible(driver);
				chkDoNotContact.highlight(driver);
				chkDoNotContact.check();
			}
		}


		//Enter or Edit phone details and update them.
		if(editphoneInfo.equalsIgnoreCase("true")){
			clickEditPhoneLink();
			pageLoaded(txtPhoneNumber);
			txtPhoneNumber.syncVisible(driver);
			txtPhoneNumber.setValidate(driver, phoneNumber);
			if(phonePrimary.equalsIgnoreCase("ON")){
				chkPhonePrimary.syncVisible(driver);
				chkPhonePrimary.highlight(driver);
				chkPhonePrimary.check();
			}
			//Click Button UpdatePhone
			clickBtnUpdatePhone();

		}else{
			pageLoaded(txtPhoneNumber);
			txtPhoneNumber.syncVisible(driver);
			txtPhoneNumber.setValidate(driver, phoneNumber);
			//Click Button UpdatePhone
			clickBtnUpdatePhone();
		}
		
		//Enter or Edit email details and update them.
		if(editemailInfo.equalsIgnoreCase("true")){
			clickEditEmailLink();
			pageLoaded(txtEmailAddress);
			txtEmailAddress.syncVisible(driver);
			txtEmailAddress.safeSet(emailAddress);
			if(primaryEmail.equalsIgnoreCase("ON")){
				chkPrimaryEmail.syncVisible(driver);
				chkPrimaryEmail.highlight(driver);
				chkPrimaryEmail.check();
			}	
				if(doNotEmailIndicator.equalsIgnoreCase("ON")){
					pageLoaded(chkDoNotEmailIndicator);
					chkDoNotEmailIndicator.highlight(driver);
					chkDoNotEmailIndicator.check();
				}	
				//Click Button UpdatePhone
				clickBtnUpdateEmail();
		
				if(personalInfo.equalsIgnoreCase("true")){
					lstTitle.select(title);
					txtLastName.safeSet(lastname);
					txtFirstName.safeSet(firstname);
					txtBirthDate.safeSet(birthDate);
					txtAge.safeSet(age);
				}
				
		}

		//Enter and update Email Information.
		if(emailInfo.equalsIgnoreCase("true")){
			if(primaryEmail.equalsIgnoreCase("ON")){
				pageLoaded(chkPrimaryEmail);
				chkPrimaryEmail.highlight(driver);
				chkPrimaryEmail.check();
			}
			if(doNotEmailIndicator.equalsIgnoreCase("ON")){
				pageLoaded(chkDoNotEmailIndicator);
				chkDoNotEmailIndicator.highlight(driver);
				chkDoNotEmailIndicator.check();
			}	

			txtEmailAddress.safeSet(emailAddress);
			//Click Button UpdateEmail
			clickBtnUpdateEmail();
		}

		// Enter guest Information for Party Window
		if(NewGuestInfo.equalsIgnoreCase("true")){
			lstTitle.select(title);
			txtLastName.safeSet(lastname);
			txtFirstName.safeSet(firstname);
			txtPostalCode.safeSet(postalCode);
		}

		clickOk();
		clickonYesButton();

	} 


	/**
	 * @summary Method to add multiple guest information 
	 * @version Created 03/11/2016
	 * @author Chinagudaba Pawan
	 * @param Datatable scenario
	 * @throws NA
	 * @return NA
	 */
	public void addGuestInformation(String scenario) throws Exception {
		pageLoaded();
		initialize();
		datatable.setVirtualtablePage("AccomWrapUp");
		datatable.setVirtualtableScenario(scenario);

		int numberOfAdults = Integer.parseInt(datatable.getDataParameter("NumberOfAdults"));
//		int numberOfChildren = Integer.parseInt(datatable.getDataParameter("NumberOfChildren"));
		int addGuestCounter;
		//String guestSearchWindowName = "Guest Search";
		/*
		 * Adult data
		 */
		String[] adultTitle = datatable.getDataParameter("AdultTitle").split(";");
		String[] adultFirstName = datatable.getDataParameter("AdultFirstName").split(";");
//		String[] adultMiddleName = datatable.getDataParameter("AdultMiddleName").split(";");
		String[] adultLastName = datatable.getDataParameter("AdultLastName").split(";");
		String[] adultZip = datatable.getDataParameter("AdultZip").split(";");
//		String[] adultAddress = datatable.getDataParameter("AdultAddress").split(";");
		String[] adultCity = datatable.getDataParameter("AdultCity").split(";");
		String[] adultState = datatable.getDataParameter("AdultState").split(";");
		String[] adultPhoneType = datatable.getDataParameter("AdultPhoneType").split(";");
		String[] adultPhoneNumber = datatable.getDataParameter("AdultPhoneNumber").split(";");
		String[] adultEmailType = datatable.getDataParameter("AdultEmailType").split(";");
		String[] adultEmail = datatable.getDataParameter("AdultEmail").split(";");
		/*
		 * Child data
		 */
		
//		String[] childAge = datatable.getDataParameter("ChildAge").split(";");
//		String[] childFirstName = datatable.getDataParameter("ChildFirstName").split(";");
//		String[] childMiddleName = datatable.getDataParameter("ChildMiddleName").split(";");
//		String[] childLastName = datatable.getDataParameter("ChildLastName").split(";");
//		String[] childZip = datatable.getDataParameter("ChildZip").split(";");

		if (numberOfAdults > 0) {

			// Add adult guests
			for (addGuestCounter = 0; addGuestCounter < numberOfAdults; addGuestCounter++) {

				AccommodationWrapUpContentFrame wrapUpContentFrame = new AccommodationWrapUpContentFrame(driver);
				wrapUpContentFrame.click_AddGuest();
				GuestSearch guestSearch = new GuestSearch(driver);
				TestReporter.assertTrue(guestSearch.pageLoaded(),
						"Guest search window is not loaded.");
				TestReporter.logStep("Adding guest information");
				guestSearch.clickNew();

				WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
				WindowHandler.waitUntilWindowExistsTitleContains(driver,"Party Page");
//				GuestSearchWindow GuestSearchWindow = new GuestSearchWindow(driver);

				txtLastName.set(adultLastName[addGuestCounter]);
				//txtMiddleName.set(adultMiddleName[addGuestCounter]);
				txtFirstName.set(adultFirstName[addGuestCounter]);
				lstTitle.select(adultTitle[addGuestCounter]);
				//txtAddress.set(adultAddress[addGuestCounter]);
				txtZip.set(adultZip[addGuestCounter]);
				txtCity.set(adultCity[addGuestCounter]);
				    	txtState.set(adultState[addGuestCounter]);
				    	txtPhoneNumber.set(adultPhoneNumber[addGuestCounter]);
				    	lstPhoneType.select(adultPhoneType[addGuestCounter]);
				    	lstEmailType.select(adultEmailType[addGuestCounter]);
				    	txtEmail.set(adultEmail[addGuestCounter]);


				clickOk();
				clickonYesButton();

			}
		} else {
			Reporter.log("The number of adults is zero. No adults will be added to this reservation");
		}
	}

}

