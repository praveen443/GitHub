package apps.alc.newReservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.alc.pleaseWait.PleaseWait;
import core.WebDriverSetup;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Label;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

/**
 * 
 * @summary Contains the page methods & objects for the search guest page during
 *          the new reservation process
 * @version Created 10/01/2014
 * @author Waightstill W Avery
 */
public class NewEditGuest {
	// **********************************
	// *** New/Edit Guest Page Fields ***
	// **********************************
	private int timeout = WebDriverSetup.getDefaultTestTimeout();
	private int loopCounter;
	private String primaryGuestFirstName;
	private String primaryGuestLastName;
	private String primaryGuestAddress1;
	private String primaryGuestpostalcode;
	private String primaryGuestCity;
	private String primaryGuestState;
	private Datatable datatable = new Datatable();	
	private WebDriver driver;
	
	// ************************************
	// *** New/Edit Guest Page Elements ***
	// ************************************
	@FindBy(id = "guestDetailsForm:discoverButton")
	private Button btnDiscover;
	
	@FindBy(id = "guestDetailsForm:cancelChangesCommandButton")
	private Button btnCancelChanges;
	
	/*
	 * Guest details
	 */
	@FindBy(id = "guestDetailsForm:titleOptions")
	private Listbox lstTitle;

	@FindBy(id = "guestDetailsForm:firstName")
	private Textbox txtFirstName;

	@FindBy(id = "guestDetailsForm:lastName")
	private Textbox txtLastName;

	@FindBy(id = "guestDetailsForm:middleInitial")
	private Textbox txtMiddleInitial;

	@FindBy(id = "guestDetailsForm:suffixOptions")
	private Listbox lstSuffix;

	@FindBy(id = "guestDetailsForm:editProfileIDCountryOptions")
	private Listbox lstCountry;

	@FindBy(id = "guestDetailsForm:address1")
	private Textbox txtAddress1;

	@FindBy(id = "guestDetailsForm:address2")
	private Textbox txtAddress2;

	@FindBy(id = "guestDetailsForm:zip")
	private Textbox txtPostalCode;

	@FindBy(id = "guestDetailsForm:city")
	private Textbox txtCity;

	@FindBy(id = "guestDetailsForm:stateOptions")
	private Listbox lstState;

	@FindBy(id = "guestDetailsForm:Home")
	private Textbox txtPhoneNumber;

	@FindBy(id = "guestDetailsForm:email")
	private Textbox txtEmail;

	@FindBy(id = "guestDetailsForm:Cell")
	private Textbox txtCellNumber;

	/*
	 * Local informaiton
	 */

	@FindBy(id = "guestDetailsForm:location")
	private Textbox txtLocation;

	@FindBy(id = "guestDetailsForm:localPhone")
	private Textbox txtLocalPhone;

	/*
	 * Candian Spiel
	 */
	@FindBy(id = "canadianSpielModalPanelHeader")
	private Label lblCandianSpiel;

	@FindBy(id = "canadianSpielModalPanelDetailsForm:existingReservationPaymentDetailsCloseButton")
	private Button btnCanadianSpielClose;

	// *********************
	// ** Build page area **
	// *********************

	public NewEditGuest(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, lstTitle);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	public NewEditGuest initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	// **************************************
	// *** Main CheckIn Page Interactions ***
	// **************************************
	private void clickDiscover() {
		pageLoaded(btnDiscover);
		btnDiscover.highlight(driver);
		btnDiscover.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}
	
	private void clickCancelChanges() {
		btnCancelChanges.highlight(driver);
		btnCancelChanges.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void enterGuestInformation(String scenario) {
		datatable.setVirtualtablePage("NewEditGuest");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded();

		String title = datatable.getDataParameter("Title");
		String firstName = datatable.getDataParameter("FirstName");
		String lastName = datatable.getDataParameter("LastName");
		String suffix = datatable.getDataParameter("Suffix");
		String postalCode = datatable.getDataParameter("PostalCode");
		String homePhone = datatable.getDataParameter("HomePhone");
		String country = datatable.getDataParameter("Country");
		String email = datatable.getDataParameter("Email");
		String address1 = datatable.getDataParameter("Address1");
		String address2 = datatable.getDataParameter("Address2");
		String city = datatable.getDataParameter("City");
		String state = datatable.getDataParameter("State");
		String cellPhone = datatable.getDataParameter("CellPhone");
		String location = datatable.getDataParameter("Location");
		String localPhone = datatable.getDataParameter("LocalPhone");
		String cancelChange = datatable.getDataParameter("CancelChange");
		boolean valueFound = true;

		initialize();
		pageLoaded(lstTitle);
		lstTitle.syncVisible(driver);
		lstTitle.select(title);
		// txtFirstName.click();
		txtFirstName.focusClick(driver);
		if (!txtFirstName.syncVisible(driver)) {
			// throw new Exception ("Unable to find txtFirstName");
			TestReporter.log("txtFirstName NOT visible");
		}
		
		
		// txtFirstName.set(firstName);
		txtFirstName.setValidate(driver, firstName);
		setPrimaryGuestFirstName(firstName);
		txtLastName.setValidate(driver, lastName);
		setPrimaryGuestLastName(lastName);
		lstSuffix.select(suffix);
		lstCountry.select(country);
		if (country.toLowerCase().equalsIgnoreCase("canada")) {
			pageLoaded();
			lblCandianSpiel.syncVisible(driver);
			btnCanadianSpielClose.click();
			pageLoaded();
		}
		txtAddress1.setValidate(driver, address1);
		txtAddress2.setValidate(driver, address2);
		txtPostalCode.safeSetValidate(driver, postalCode);
		float fLoopCounter = (float) 0.0;
		if (!postalCode.isEmpty()) {
			if (txtPostalCode.getAttribute("value").isEmpty()) {
				do {
					Sleeper.sleep(500);
					fLoopCounter += .5;
					if (fLoopCounter == (float) timeout / 2) {
						break;
					}
				} while (txtCity.getAttribute("value").isEmpty());
			}
		}
		loopCounter = 0;
		while (txtCity.getAttribute("value").isEmpty() && valueFound == true) {
			txtCity.setValidate(driver, city);
			Sleeper.sleep(2000);
			if (loopCounter++ == timeout) {
				valueFound = false;
			}
			initialize();
		}
		;
		TestReporter.assertEquals(valueFound, true,
				"The value " + city + " was not found to have been set in the City text box");
		lstState.select(state);
		txtPhoneNumber.setValidate(driver, homePhone);
		txtEmail.setValidate(driver, email);
		txtCellNumber.setValidate(driver, cellPhone);
		txtLocation.setValidate(driver, location);
		txtLocalPhone.setValidate(driver, localPhone);
		
		setPrimaryGuestAddress1(address1);
		setPrimaryGuestPostalCode(postalCode);
		setPrimaryGuestCity(city);
		setPrimaryGuestState(state);
		

    	//Check if the edit guest to cancel changes or to click discover 
    	if (cancelChange.toLowerCase().equalsIgnoreCase("true")){
    		clickCancelChanges();
    	}
    	else {
    		        		
    		clickDiscover();
    	}
	}
	
	
	private void setPrimaryGuestFirstName(String firstName){
		this.primaryGuestFirstName = firstName;
	}
	
	public String getPrimaryGuestFirstName(){
		return this.primaryGuestFirstName;
	}
	
	private void setPrimaryGuestLastName(String lastName){
		this.primaryGuestLastName = lastName;
	}
	
	public String getPrimaryGuestLastName(){
		return this.primaryGuestLastName;
	}
	
	/**
	 * @summary Methods to read guest details
	 * @author Lalitha Banda
	 * @version 12/29/2015 Lalitha Banda - original
	 * @param element
	 *            - Element to be used to determine if the page is loaded
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	private void setPrimaryGuestAddress1(String address1){
		this.primaryGuestAddress1 = address1;
	}
	
	public String getPrimaryGuestAddress1(){
		return this.primaryGuestAddress1;
	}
	
	
	private void setPrimaryGuestPostalCode(String PostalCode){
		this.primaryGuestpostalcode = PostalCode;
	}
	
	public String getPrimaryGuestPostalCode(){
		return this.primaryGuestpostalcode;
	}
	
	
	private void setPrimaryGuestCity(String city){
		this.primaryGuestCity = city;
	}
	
	public String getPrimaryGuestcity(){
		return this.primaryGuestCity;
	}
	
	
	private void setPrimaryGuestState(String state){
		this.primaryGuestState = state;
	}
	
	public String getPrimaryGuestState(){
		return this.primaryGuestState;
	}
	
	 //collects all the required guest information into an array	
    public String[] getGuestInformation(){
    	initialize();
    	pageLoaded();
    	 
    	 TestReporter.logStep("Invoked getGuestInformation()");
    	 
    	 String[] GuestInfo = {lstTitle.getFirstSelectedOption().getText(), txtFirstName.getText(),txtLastName.getText(),
    			 txtMiddleInitial.getText(),lstSuffix.getFirstSelectedOption().getText(),lstCountry.getFirstSelectedOption().getText(),
    			 txtAddress1.getText(),txtAddress2.getText(),txtPostalCode.getText(),
    			 lstState.getFirstSelectedOption().getText(),txtPhoneNumber.getText(),
    			 txtEmail.getText(),txtCellNumber.getText()
    			 };
    	
    	TestReporter.logStep("Title ["+GuestInfo[0]+"] FirstName ["+GuestInfo[1]+"] LastName ["+GuestInfo[2]+"]");
    	TestReporter.logStep("MiddleInitial ["+GuestInfo[3]+"] Suffix ["+GuestInfo[4]+"] Country ["+GuestInfo[5]+"]");
    	TestReporter.logStep("Address1 ["+GuestInfo[6]+"] Address2 ["+GuestInfo[7]+"] PostalCode ["+GuestInfo[8]+"]");
    	TestReporter.logStep("State ["+GuestInfo[9]+"] PhoneNumber ["+GuestInfo[10]+"]");
    	TestReporter.logStep("Email ["+GuestInfo[11]+"] CellNumber ["+GuestInfo[12]+"]");
    	
    	return GuestInfo ;
    }
    
    /**
	 * @summary Methods to click discover button
	 * @author Lalitha Banda
	 * @version 12/4/2015 Lalitha Banda - original
	 * @param NA
	 * @return NA
	 */
    
    public void clickDiscoverButton(){
    	initialize();
    	clickDiscover();
    }
   /*  * @summary : Method to click on button Discover.
     * @author  : Praveen Namburi, @version : Created 1/4/2015.
     */
    public void clickbtnDiscover(){
    	pageLoaded(btnDiscover);
//    	btnDiscover.highlight(driver);
    	btnDiscover.jsClick(driver);
    	PleaseWait.WaitForPleaseWait(driver);
    }
   
    /**
     * @summary : Method to get default Guest Search name.
     * @author : Praveen Namburi, @version : Created 1-6-2016.
     * @return : String GuestName.
     */
    public String getDefaultSearchGuestname(){
    	pageLoaded(txtFirstName);    	
    	primaryGuestFirstName = txtFirstName.getText();
    	primaryGuestLastName = txtLastName.getText();
    	String getGuestName = primaryGuestFirstName +" "+primaryGuestLastName;
    	
    	return getGuestName;
    }
}


