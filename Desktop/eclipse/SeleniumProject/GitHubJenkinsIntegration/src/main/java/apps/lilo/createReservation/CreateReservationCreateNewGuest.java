package apps.lilo.createReservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;

/**
 * 
 * @summary Contains the page methods & objects for the create new guest page
 *          during the create reservation process
 * @version Created 9/18/2014
 * @author Jessica Marshall
 */

public class CreateReservationCreateNewGuest {
	// ************************************
	// *** Create Reservation Create New Guest Page Fields ***
	// ************************************
	private Datatable datatable = new Datatable();
	private WebDriver driver;

	// ************************************
	// *** Create Reservation Create New Guest Page Elements ***
	// ************************************
	// Title
	@FindBy(id = "createResGuestDetailsForm:title")
	private Listbox lstTitle;

	// First name
	@FindBy(id = "createResGuestDetailsForm:firstName")
	private Textbox txtFirstName;

	// Last name
	@FindBy(id = "createResGuestDetailsForm:lastName")
	private Textbox txtLastName;

	// suffix
	@FindBy(id = "createResGuestDetailsForm:suffix")
	private Listbox lstSuffix;

	// country
	@FindBy(id = "createResGuestDetailsForm:primaryGuestCountry")
	private Listbox lstCountry;

	// address line 1
	@FindBy(id = "createResGuestDetailsForm:primaryGuestAddr1")
	private Textbox txtAddress1;

	// address line 2
	@FindBy(id = "createResGuestDetailsForm:primaryGuestAddr2")
	private Textbox txtAddress2;

	// zip code
	@FindBy(id = "createResGuestDetailsForm:primaryGuestZipCode")
	private Textbox txtZipCode;

	// city
	@FindBy(id = "createResGuestDetailsForm:primaryGuestCity")
	private Textbox txtCity;

	// State
	@FindBy(id = "createResGuestDetailsForm:primaryGuestStateSelect")
	private Listbox lstState;

	// primary phone type
	@FindBy(id = "createResGuestDetailsForm:primaryPhoneType")
	private Listbox lstPriPhoneType;

	// primary phone number
	@FindBy(id = "createResGuestDetailsForm:primaryPhoneNumber")
	private Textbox txtPrimaryPhoneNum;

	// secondary phone type
	@FindBy(id = "createResGuestDetailsForm:secondaryPhoneType")
	private Listbox lstSecPhoneType;

	// secondary phone number
	@FindBy(id = "createResGuestDetailsForm:secondaryPhoneNumber")
	private Textbox txtSecondaryPhoneNum;

	// email type
	@FindBy(id = "createResGuestDetailsForm:createResvEmailId")
	private Listbox lstEmailType;

	// email address
	@FindBy(id = "createResGuestDetailsForm:emailAddress")
	private Textbox txtEmail;

	// VIP
	@FindBy(id = "createResGuestDetailsForm:vipLevelBooleanId")
	private Checkbox chkVIP;

	// VIP level
	@FindBy(id = "createResGuestDetailsForm:vipLevelListId")
	private Listbox lstVIPLevel;

	// back to search guest
	@FindBy(id = "createResGuestDetailsForm:backToGuestSearchButton")
	private Button btnPrevious;

	// finish
	@FindBy(id = "createResGuestDetailsForm:bookReservation")
	private Button btnFinish;

	// cancel
	@FindBy(id = "createResGuestDetailsForm:cancelButton")
	private Button btnCancel;
	
	//Add Accommodation Finish button
	@FindBy(id = "createResGuestDetailsForm:addAccommodation")
	private Button btnAddAccommFinish;

	//page object to get text from error pop-up message
	@FindBy(xpath="//table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td/table/tbody/tr/td[2]/div/ul/li[1]")
	private Element eleGetErrorInfo;
	
	//Error Div
	@FindBy(id = "PMSRErrorModalPanelContentDiv")
	private Element eleErrorMsgPopUP;
	
	//page object for OK button in error pop-up msg.
	@FindBy(id="errorForm:okButtonId")
	private Button btnErrorOK;
	
	// *********************
	// ** Build page area **
	// *********************
	public CreateReservationCreateNewGuest(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

/*	private CreateReservationCreateNewGuest initialize(WebDriver driver) {
		return ElementFactory.initElements(driver,
				CreateReservationCreateNewGuest.class);
	}*/
	
	public boolean pageLoaded(){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnCancel);
	}
	
	public boolean pageLoaded(Element element){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}
	
	public CreateReservationCreateNewGuest initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	// **************************************
	// *** Create Reservation Create New Guest Page Interactions ***
	// **************************************

	public void clickCancel() {
		btnCancel.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickFinish() {
	//	initialize();
		pageLoaded(btnFinish);
		btnFinish.syncVisible(driver);
		btnFinish.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver, 60);
	}
	
	public void clickAddAccommFinish(){
	//	initialize();
		pageLoaded(btnAddAccommFinish);
		btnAddAccommFinish.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickPrevious() {
		btnPrevious.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * 
	 * @summary Enters the primary guest info during the create reservation
	 *          process
	 * 
	 * @version Created 9/18/2014
	 * @author Jessica Marshall
	 * @param The
	 *            datatable scenario
	 * @throws Exception
	 * @return
	 * 
	 */
	public void enterPrimaryGuestInfo(String scenario) {
		datatable.setVirtualtablePage("CreateResCreateNewGuestPage");
		datatable.setVirtualtableScenario(scenario);
		
		String VIP = datatable.getDataParameter("VIP");
		String SetState = datatable.getDataParameter("SetState");
	    String SetCity = datatable.getDataParameter("SetCity");

		// Enter the data
		lstTitle.select(datatable.getDataParameter("Title"));
		txtFirstName.safeSet(datatable.getDataParameter("FirstName"));
		txtLastName.safeSet(datatable.getDataParameter("LastName"));
		lstSuffix.select(datatable.getDataParameter("Suffix"));
		lstCountry.select(datatable.getDataParameter("Country"));
		txtAddress1.safeSet(datatable.getDataParameter("Address1"));
		txtAddress2.safeSet(datatable.getDataParameter("Address2"));
		txtZipCode.safeSet(datatable.getDataParameter("ZipCode"));
		
		//Zip code auto populates
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		txtCity.safeSet(datatable.getDataParameter("City"));
		lstState.select(datatable.getDataParameter("State"));
		
		//phone numbers auto format
		lstPriPhoneType.select(datatable.getDataParameter("PrimaryPhoneType"));
		txtPrimaryPhoneNum.safeSet(datatable.getDataParameter("PrimaryPhoneNum"));
		Sleeper.sleep(1000);
		lstSecPhoneType.select(datatable.getDataParameter("SecondaryPhoneType"));
		txtSecondaryPhoneNum.safeSet(datatable.getDataParameter("SecondaryPhoneNum"));
		Sleeper.sleep(1000);
		lstEmailType.select(datatable.getDataParameter("EmailType"));
		txtEmail.safeSet(datatable.getDataParameter("EmailAddress"));
		
		//Only check the VIP if specified "ON"
		if (VIP.equalsIgnoreCase("ON")){
			chkVIP.check();
			lstVIPLevel.select(datatable.getDataParameter("VIPLevel"));
		}
		
		//Added the below two if-conditions for enhancing the method for dataIterations. 
		if(SetCity.equalsIgnoreCase("true")){
			pageLoaded(txtCity);
			txtCity.clear();			
		}
		
		if(SetState.equalsIgnoreCase("true")){
			pageLoaded(lstState);
			lstState.select(datatable.getDataParameter("State"));
		}
		 
	}

	public void createNewGuest(String scenario) {
		enterPrimaryGuestInfo(scenario);
		clickFinish();
	}
	
	public String getPrimaryGuestFullName(String scenario){
		datatable.setVirtualtablePage("CreateResCreateNewGuestPage");
		datatable.setVirtualtableScenario(scenario);
		return datatable.getDataParameter("FirstName").trim()+"  "+datatable.getDataParameter("LastName").trim();
	}
	
	/**
	    * 
	    * @summary Method to validate Error Message 
	    * @version Created 11/25/2014
	    * @author Venkatesh A
	    * @param  NA
	    * @throws NA
	    * @return NA
	    * 
	    *//*
		public void validatingErrorMessage(String scenario){
			
			datatable.setVirtualtablePage("GetErrorMessages");
			datatable.setVirtualtableScenario(scenario);
			
			pageLoaded(eleErrorMsgPopUP);
			eleGetErrorInfo.highlight(driver);
			System.out.println("Express CheckOut Error Message : "+eleGetErrorInfo.getText());
			
			//Validating the error pop-up message here
			TestReporter.assertEquals(eleGetErrorInfo.getText(), datatable.getDataParameter("ErrorMessageInfo"), "Error Message Not Displayed");
			
			//Close the Error pop-up message by clicking on OK button.
			pageLoaded(btnErrorOK);
			btnErrorOK.highlight(driver);
			btnErrorOK.jsClick(driver);		
			
		}*/
}

