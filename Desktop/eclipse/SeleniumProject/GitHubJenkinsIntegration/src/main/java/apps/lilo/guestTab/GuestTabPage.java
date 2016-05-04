package apps.lilo.guestTab;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.WebDriverSetup;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Link;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

/**
 * @summary Contains the elements and methods to interact with the LILO UI Guest
 *          tab
 * @version Created 10/29/2014
 * @author Waightstill W. Avery
 */
public class GuestTabPage {
	//*********************************
	//*** Main Guest Tab Page Fields ***
	//*********************************
	private int loopnum = 10;
	private int loopCount = 0;
	private int intLoopCounter;
	private int intSecondaryGuestCounter;
	private int intMaxLoops = WebDriverSetup.getDefaultTestTimeout();
	private List<WebElement> lstGuestCheckboxes;
	@SuppressWarnings("unused")
	private Object[] arrGuestCheckBoxes;
	private WebElement objElement;
	private Datatable datatable = new Datatable();

	
	//***********************************
	//*** Main Guest Tab Page Elements ***
	//***********************************
	// ***********************************
	// *** Main Guest Tab Page Elements ***
	// ***********************************

	// Guest List webtabl
	@FindBy(id = "viewGuestForm:guestList")
	private Webtable tblGuestlList;
	
	//Primary First Name text box
	@FindBy(id = "viewGuestForm:guestname")
	private Textbox txtPrimaryFirstName;
	
	//Primary Last Name text box
	@FindBy(id = "viewGuestForm:guestLastname")
	private Textbox txtPrimaryLastName;
	
	//Primary Suffix
	@FindBy(id = "viewGuestForm:suffix")
	private Listbox lstPrimarySuffix;
	
	//Guest Tab Address Type
	@FindBy(id = "viewGuestForm:addressTypeId")
	private Listbox lstGuestAddressType;
	
	//Primary Country
	@FindBy(id = "viewGuestForm:countryId")
	private Listbox lstPrimaryCountry;
	
	//Primary zip code textbox
	@FindBy(id = "viewGuestForm:ZipID")
	private Textbox txtPrimaryZipCode;
	
	//Primary Address 1 line
	@FindBy(id = "viewGuestForm:address1Id")
	private Textbox txtPrimaryAddress1;
	
	//Primary Address 2 line
	@FindBy(id = "viewGuestForm:address2")
	private Textbox txtPrimaryAddress2;
		
	//Primary City
	@FindBy(id = "viewGuestForm:cityId")
	private Textbox txtPrimaryCity;
	
	//Primary State
	@FindBy(id = "viewGuestForm:stateId")
	private Listbox lstPrimaryState;
	
	//Primary language option
	@FindBy(id = "viewGuestForm:guestAddPrefLanguageId")
	private Listbox lstPrimaryPrefLanguage;
	
	//Primary Phone Type
	@FindBy(id = "viewGuestForm:phoneType1")
	private Listbox lstPrimaryPhoneType;
	
	//Pimary Phone Number textbox

	// Pimary Phone Number textbox
	@FindBy(id = "viewGuestForm:phoneNumber1")
	private Textbox txtPrimaryPhoneNumber;

	// Do not Email checkBox
	@FindBy(id = "viewGuestForm:doNoMailCheckBox")
	private Button btnDoNotEmail;

	// Proceed Proceed CheckIn button
	@FindBy(id = "viewGuestForm:proToChkInButtonId")
	private Button btnProceedCheckIn;

	// Edit link object
	@FindBy(id = "travelSummeryFrm:tpsId")
	private Link lnkEdit;

	// Refresh object
	@FindBy(id = "refreshReservationFrm:refreshViewRes")
	private Element eleRefresh;

	// Grab the Save button
	@FindBy(id = "viewGuestForm:saveButton")
	private Button btnSave;

	/*
	 * *Primary Guest Objects
	 */
	// Grab the primary guest checkbox
	@FindBy(css = "input[name^=\"viewGuestForm:guestList:0:j_id\"]")
	private Checkbox chkPrimaryGuestCheckBox;

	// Grab the primary guest title list
	@FindBy(id = "viewGuestForm:courtesyTitle")
	private Listbox lstPrimaryGuestTitle;

	// Grab the primary guest first name text box
	@FindBy(id = "viewGuestForm:guestname")
	private Textbox txtPrimaryGuestFirstName;

	// Grab the primary guest last name text box
	@FindBy(id = "viewGuestForm:guestLastname")
	private Textbox txtPrimaryGuestLastName;

	// Guest Table
	@FindBy(id = "viewGuestForm:guestList")
	private Webtable tblGuestList;

	// Grab the primary guest suffix list
	@FindBy(id = "viewGuestForm:suffix")
	private Listbox lstPrimaryGuestSuffix;

	// Grab the primary guest address home type list
	@FindBy(id = "viewGuestForm:addressTypeId")
	private Listbox lstPrimaryGuestAddressType;

	// Grab the primary guest country list
	@FindBy(id = "viewGuestForm:countryId")
	private Listbox lstPrimaryGuestCountry;

	// Grab the primary guest postal code text box
	@FindBy(id = "viewGuestForm:ZipID")
	private Textbox txtPrimaryGuestPostalCode;

	// Grab the primary guest address line 1 text box
	@FindBy(id = "viewGuestForm:address1Id")
	private Textbox txtPrimaryGuestAddressLine1;

	// Grab the primary guest address line 2 text box
	@FindBy(id = "viewGuestForm:address2")
	private Textbox txtPrimaryGuestAddressLine2;

	// Grabe the primary guest city text box
	@FindBy(id = "viewGuestForm:cityId")
	private Textbox txtPrimaryGuestCity;

	// Grab the primary guest state list
	@FindBy(id = "viewGuestForm:stateId")
	private Listbox lstPrimaryGuestState;

	// Grab the primary guest language list
	@FindBy(id = "viewGuestForm:guestAddPrefLanguageId")
	private Listbox lstPrimaryGuestLanguage;

	// Grab the primary guest phone type 1 list
	@FindBy(id = "viewGuestForm:phoneType1")
	private Listbox lstPrimaryGuestPhoneType1;

	// Grab the primary guest phone number 1 text box
	@FindBy(id = "viewGuestForm:phoneNumber1")
	private Textbox txtPrimaryGuestPhoneNumber1;

	// Grab the primary guest phone type 2 list
	@FindBy(id = "viewGuestForm:phoneType2")
	private Listbox lstPrimaryGuestPhoneType2;

	// Grab the primary guest phone number 2 text box
	@FindBy(id = "viewGuestForm:phoneNumber2")
	private Textbox txtPrimaryGuestPhoneNumber2;

	// Grab the primary guest email type list
	@FindBy(id = "viewGuestForm:emailTypeId")
	private Listbox lstPrimaryGuestEmailType;

	// Grab the primary guest email address text box
	@FindBy(id = "viewGuestForm:emailAddress")
	private Textbox txtPrimaryGuestEmailAddress;

	// Grab the primary guest Do Not Email checkbx
	@FindBy(id = "viewGuestForm:doNoMailCheckBox")
	private Checkbox chkPrimaryGuestDoNotEmail;

	/*
	 * *Secondary Guest Objects
	 */
	// Grab the secondary guest checkbox
	@FindBy(css = "input[name^=\"viewGuestForm:guestList:1:j_id\"]")
	private Checkbox chkSecondaryGuestCheckBox;

	// Grab the secondary guest title list
	@FindBy(id = "viewGuestForm:courtesyTitle")
	private Listbox lstSecondaryGuestTitle;

	// Grab the secondary guest first name text box
	@FindBy(id = "viewGuestForm:guestname")
	private Textbox txtSecondaryGuestFirstName;

	// Grab the secondary guest last name text box
	@FindBy(id = "viewGuestForm:guestLastname")
	private Textbox txtSecondaryGuestLastName;

	// Grab the secondary guest suffix list
	@FindBy(id = "viewGuestForm:suffix")
	private Listbox lstSecondaryGuestSuffix;

	/*
	 * *Loading Icon Objects
	 */
	// Grab the loading icon for phone number 1
	@FindBy(id = "viewGuestForm:phone1RegionId:status.start")
	private Element icnPhone1Loading;

	// Grab the loading icon for phone number 2
	@FindBy(id = "viewGuestForm:phone2RegionId:status.start")
	private Element icnPhone2Loading;
	
	@FindBy(id = "viewGuestForm:saveButton")
	private Button btnGuestSaveButton;

	// *********************
	// ** Build page area **
	// *********************
	private WebDriver driver;

	/**
	 * 
	 * @summary Constructor to initialize the page
	 * @version Created 10/29/2014
	 * @author Waightstill W Avery
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public GuestTabPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				tblGuestlList);
	}

	/**
	 * @summary : Method to determine if the Guest page is loaded.
	 * @author : Praveen Namburi
	 * @version : 11/19/2015
	 * @return : Boolean, true if the page is loaded, false otherwise
	 */
	public boolean guestpageIsLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnSave);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				element);
	}

	/**
	 * @summary Mehtod to determine if the object edit link is loaded on the
	 *          page.
	 * @version Created 11/19/2015
	 * @author Praveen Namburi
	 * @param link
	 * @return Boolean - true, if the page is loaded.Else, will return false.
	 */
	public boolean pageLoaded(Link link) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				lnkEdit);
	}

	public GuestTabPage initialize(WebDriver driver) {
		return ElementFactory.initElements(driver, this.getClass());
	}

	// **********************************
	// *** Main Guest Tab Interactions ***
	// **********************************

	public void verifyPartyMixAges(String scenario) {
		datatable.setVirtualtablePage("GuestTab");
		datatable.setVirtualtableScenario(scenario);

		String numberOfAdults = datatable.getDataParameter("NumberOfAdults");
		String numberOfChildren = datatable.getDataParameter("NumberOfChildren");
		@SuppressWarnings("unused")
		String[] childAges = datatable.getDataParameter("NumberOfChildren")
				.split(";");
		List<WebElement> list = driver.findElements(By
				.xpath("//table[@id='viewGuestForm:guestList']/tbody/tr"));
		int rows = list.size();
		int adultsFound = 0;
		int childrenFound = 0;
		boolean numAdultsVerified = true;
		boolean numChildrenVerified = true;

		for (loopCount = 0; loopCount < rows; loopCount++) {
			String testValue = driver.findElement(
					By.xpath("//table[@id='viewGuestForm:guestList']/tbody/tr["
							+ String.valueOf(loopCount + 1) + "]/td[3]"))
					.getText();
			if (testValue.toLowerCase().contains("adult")) {
				adultsFound++;
				if (adultsFound > Integer.parseInt(numberOfAdults)) {
					numAdultsVerified = false;
				}
			} else if (testValue.toLowerCase().contains("child")) {
				childrenFound++;
				if (childrenFound > Integer.parseInt(numberOfChildren)) {
					numChildrenVerified = false;
				}
			}
		}
		Assert.assertEquals(numAdultsVerified, true,
				"The number of adults found [" + numberOfAdults
						+ "] did not match the expected number of adults ["
						+ String.valueOf(adultsFound) + "].");
		Assert.assertEquals(numChildrenVerified, true,
				"The number of children found [" + numberOfChildren
						+ "] did not match the expected number of children ["
						+ String.valueOf(childrenFound) + "].");
	}

	public void verifyPartyNoChildAges(String scenario) {
		List<WebElement> list = driver.findElements(By
				.xpath("//table[@id='viewGuestForm:guestList']/tbody/tr"));
		int rows = list.size();
		boolean childAgeFound = false;

		for (loopCount = 0; loopCount < rows; loopCount++) {
			String testValue = driver.findElement(
					By.xpath("//table[@id='viewGuestForm:guestList']/tbody/tr["
							+ String.valueOf(loopCount + 1) + "]/td[3]"))
					.getText();
			if (!testValue.equalsIgnoreCase("ADULT")) {
				childAgeFound = true;
				Reporter.log(
						"A child's age was found to be associated with an adult guest. The value found was ["
								+ testValue + "].", true);
			}
		}
		Assert.assertEquals(
				childAgeFound,
				false,
				"A child's age was found to be associated with an adult. Check the reporter for a detailed account of each occurrance.");
	}

	public void verifyPhoneNumber(String number) {
		pageLoaded(txtPrimaryPhoneNumber);
		String testString = txtPrimaryPhoneNumber.getText();
		testString = testString.replace("-", "");
		testString = testString.trim();
		number = testString.replace("-", "");
		number = testString.trim();
		Assert.assertEquals(testString, number,
				"The phone number found in the application [" + testString
						+ "] did not match the expected phone number ["
						+ number + "].");
	}

	public void clickDoNotEmail() {
		btnDoNotEmail.click();
		//Commenting out as the Please Wait window is intermittently be found to always be visible - WWA
		//new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickSave() {
		btnSave.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickProceedCheckIn() {
		pageLoaded(btnProceedCheckIn);
		btnProceedCheckIn.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver, 60);
	}

	/**
	 * 
	 * @Summary : Click on edit link that exists below to the top Navigation
	 *          pane.
	 * @version : 11/12/2015
	 * @author : Praveen Namburi
	 * @return : NA
	 */
	public void clickEditLink() {

		pageLoaded(lnkEdit);
		lnkEdit.highlight(driver);
		lnkEdit.jsClick(driver);

		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * 
	 * @Summary : Click on Refresh Image that exists below to the top Navigation
	 *          pane.
	 * @version : 11/12/2015
	 * @author : Praveen Namburi
	 * @return : NA
	 */
	public void clickRefresh() {

		pageLoaded(eleRefresh);
		eleRefresh.highlight(driver);
		eleRefresh.jsClick(driver);

		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * 
	 * @summary : Method to modify and enter the Primary Guest Information.
	 *          (e.g. first name, last name, etc) This will, depending on the
	 *          scenario, enter guest information for each guest on the
	 *          reservation.
	 * @version : Created 11/19/2015
	 * @author : Praveen Namburi
	 * @param : scenario - used to determine which row of the Master data
	 *        table\sheet to use; also determines how many guests to enter data
	 *        and modify them.
	 * @throws : NA
	 * @return : NA
	 * 
	 */
	public void modifyPrimaryGuestDetailsAndSaveIt(String scenario){

		/*
		 * datatable.setDatatableSheet("CheckInInfo");
		 * datatable.setDatatableRow(datatable.getRowContains(scenario, 0));
		 */
		datatable.setVirtualtablePage("CheckInInfo");
		datatable.setVirtualtableScenario(scenario);
		final String[] arrSecondaryGuestTitle = datatable.getDataParameter(
				"SecondaryTitle").split(";");
		final String[] arrSecondaryGuestFirstName = datatable.getDataParameter(
				"SecondaryFirstName").split(";");
		final String[] arrSecondaryGuestLastName = datatable.getDataParameter(
				"SecondaryLastName").split(";");
		final String[] arrSecondaryGuestSuffix = datatable.getDataParameter(
				"SecondarySuffix").split(";");
		String strId;
		int loopCounter;

		pageLoaded(btnSave);
		initialize(driver);

		lstGuestCheckboxes = driver.findElements(By
				.cssSelector("input[name^=\"viewGuestForm:guestList:\"]"));

		if (lstGuestCheckboxes.size() != Integer.parseInt(datatable.getDataParameter("NumberOfGuests"))) {
			Reporter.log("The number of guests does not match the number of displayed check boxes.");
		}

		intLoopCounter = 0;
		intSecondaryGuestCounter = 0;
		intMaxLoops = lstGuestCheckboxes.size();

		// Determine if the primary guest is to be edited
		if (!datatable.getDataParameter("EditPrimaryGuest").toString()
				.equalsIgnoreCase("false")) {
			// Check the primary guest check box
			chkPrimaryGuestCheckBox.check();
			new ProcessingYourRequest().WaitForProcessRequest(driver);

			// Select a title
			lstPrimaryGuestTitle.select(datatable.getDataParameter("PrimaryTitle"));

			// Edit the guest first name
			txtPrimaryGuestFirstName.set(datatable.getDataParameter("PrimaryFirstName"));

			// Edit the guest first name
			txtPrimaryGuestLastName.set(datatable.getDataParameter("PrimaryLastName"));

			// Select a suffix
			lstPrimaryGuestSuffix.select(datatable.getDataParameter("PrimarySuffix"));

			// Select a address type
			lstPrimaryGuestAddressType.select(datatable.getDataParameter("PrimaryAddressType"));

			// Select a country
			lstPrimaryGuestCountry.select(datatable.getDataParameter("PrimaryCountry"));

			// Enter a zip code
			if (!datatable.getDataParameter("PrimaryPostalCode").isEmpty()) {
				txtPrimaryGuestPostalCode.safeSet(datatable.getDataParameter("PrimaryPostalCode"));
				// Loop until the city is autopopulated after entering the
				// postal code
				intLoopCounter = 0;
				do {
					Sleeper.sleep(1500);

					pageLoaded(btnSave);
					initialize(driver);
					intLoopCounter++;
					if (intLoopCounter == intMaxLoops) {
						break;
					}
				} while (txtPrimaryGuestCity.getAttribute("value").isEmpty());
			}

			// Enter the first line of the address
			txtPrimaryGuestAddressLine1.set(datatable.getDataParameter("PrimaryAddressLine1"));

			// Enter the second line of the address
			txtPrimaryGuestAddressLine2.set(datatable.getDataParameter("PrimaryAddressLine2"));

			// Enter a city
			txtPrimaryGuestCity.set(datatable.getDataParameter("PrimaryCity"));

			// Select a state
			lstPrimaryGuestState.select(datatable.getDataParameter("PrimaryState"));

			// Select a language
			lstPrimaryGuestLanguage.select(datatable.getDataParameter("PrimaryLanguage"));

			// Select a phone type 1
			lstPrimaryGuestPhoneType1.select(datatable.getDataParameter("PrimaryPhoneType1"));
			Sleeper.sleep(1500);

			if (!datatable.getDataParameter("PrimaryPhoneType1").isEmpty()) {
				// Enter a phone number 1
				loopCounter = 0;
				do {
					txtPrimaryGuestPhoneNumber1.safeSet(datatable.getDataParameter("PrimaryPhoneNumber1"));
					// icnPhone1Loading.syncHidden(driver);
					Sleeper.sleep(1000);
					loopCounter++;

					// pageLoaded();
					pageLoaded(btnSave);

					initialize(driver);
				} while (txtPrimaryGuestPhoneNumber1.getAttribute("value")
						.equalsIgnoreCase("")
						&& loopCounter < WebDriverSetup.getDefaultTestTimeout());

			}

			// Select a phone type 2
			lstPrimaryGuestPhoneType2.select(datatable.getDataParameter("PrimaryPhoneType2"));

			// Enter a phone number 2
			txtPrimaryGuestPhoneNumber2.safeSet(datatable.getDataParameter("PrimaryPhoneNumber2"));
			// icnPhone2Loading.syncHidden(driver);
			Sleeper.sleep(1000);

			// Select a email type
			lstPrimaryGuestEmailType.select(datatable.getDataParameter("PrimaryEmailType"));
			new ProcessingYourRequest().WaitForProcessRequest(driver);

			// Enter an email address
			txtPrimaryGuestEmailAddress.safeSetValidate(driver,
					datatable.getDataParameter("PrimaryEmailAddress"));

			// Set the Do Not Email checkbox
			if (datatable.getDataParameter("PrimaryDoNotEmail").toString()
					.equalsIgnoreCase("false")) {
				chkPrimaryGuestDoNotEmail.check();
			}

			Sleeper.sleep(2000);

			// Click the Save button
			btnSave.jsClick(driver);
			new ProcessingYourRequest().WaitForProcessRequest(driver);

			intLoopCounter++;
		}

		// Determine if secondary guests are to be edited
		if (!datatable.getDataParameter("EditSecondaryGuest").toString()
				.equalsIgnoreCase("false")) {
			// Select the next guest to edit
			for (intSecondaryGuestCounter = 0; intSecondaryGuestCounter < Integer
					.parseInt(datatable.getDataParameter("NumberOfGuests")) - 1; intSecondaryGuestCounter++) {
				// Define the next check box object
				strId = "input[name^=\"viewGuestForm:guestList:"
						+ String.valueOf(intSecondaryGuestCounter + 1) + "\"]";
				// Grab the next checkbox
				objElement = driver.findElement(By.cssSelector(strId));
				// Click the next checkbox
				objElement.click();

				new ProcessingYourRequest().WaitForProcessRequest(driver);

				// Select a title
				if (intSecondaryGuestCounter >= arrSecondaryGuestTitle.length) {
					lstSecondaryGuestTitle
							.select(arrSecondaryGuestTitle[arrSecondaryGuestTitle.length - 1]);
				} else {
					lstSecondaryGuestTitle
							.select(arrSecondaryGuestTitle[intSecondaryGuestCounter]);
				}

				// Enter the first name
				if (intSecondaryGuestCounter >= arrSecondaryGuestFirstName.length) {
					txtSecondaryGuestFirstName
							.set(arrSecondaryGuestFirstName[arrSecondaryGuestFirstName.length - 1]);
				} else {
					txtSecondaryGuestFirstName
							.set(arrSecondaryGuestFirstName[intSecondaryGuestCounter]);
				}

				// Enter the second name
				if (intSecondaryGuestCounter >= arrSecondaryGuestLastName.length) {
					txtSecondaryGuestLastName
							.set(arrSecondaryGuestLastName[arrSecondaryGuestLastName.length - 1]);
				} else {
					txtSecondaryGuestLastName
							.set(arrSecondaryGuestLastName[intSecondaryGuestCounter]);
				}

				// Select a suffix
				if (intSecondaryGuestCounter >= arrSecondaryGuestSuffix.length) {
					lstSecondaryGuestSuffix
							.select(arrSecondaryGuestSuffix[arrSecondaryGuestSuffix.length - 1]);
				} else {
					lstSecondaryGuestSuffix
							.select(arrSecondaryGuestSuffix[intSecondaryGuestCounter]);
				}

				// Click the Save button
				btnSave.click();
				new ProcessingYourRequest().WaitForProcessRequest(driver);
			}
		}
	}
	
	/**
	 * @summary : Method to edit the primary form and Save
	 * @author  : Dennis Stagliano
	 * @version : 11/17/2015
	 * @Return  : N/A
	 * 
	 * NOTE:  This just edits typical testing fields for a Primary Guest Only - Does not enter secondary guest Info
	 * NOTE:  Leave Datatable field empty if you do not want to edit
	 * NOTE:  The fields FirstName, LastName, Country and Language are left default
	 */
	
	public void editPrimaryForm(String scenario) {
		
		datatable.setVirtualtablePage("GuestTab");
		datatable.setVirtualtableScenario(scenario);
		
		//Set title
		lstPrimaryGuestTitle.select(datatable.getDataParameter("Title"));
		Sleeper.sleep(1000);
		//Set Suffix
		lstPrimarySuffix.select(datatable.getDataParameter("Suffix"));
		Sleeper.sleep(1000);
		//set Address Line 1
		txtPrimaryAddress1.set(datatable.getDataParameter("AddressLine1"));
		Sleeper.sleep(1000);
		
		//check city and state should be auto populated if zip code and address line 1 was entered
		if(!datatable.getDataParameter("ZipCode").isEmpty()){
			txtPrimaryZipCode.safeSet(datatable.getDataParameter("ZipCode"));
			//Loop until the city is auto populated after entering the postal code
			int LoopCounter = 0;
			do{
				Sleeper.sleep(2000);					
				initialize(driver);
				LoopCounter++;
				if(LoopCounter == loopnum){
					TestReporter.logStep("The city field did not auto populate");
					break;			
				}
			}while(txtPrimaryCity.getAttribute("value").isEmpty());
		}
		/**
		 * @Summary: Adding this to override a city value if the test case asks for it
		 * @Author: Dennis Stagliano
		 * @Version: 12/27/2015
		 */
		//Override the city entry if the data table has a city 
		String checkCity = "";
		checkCity = datatable.getDataParameter("City");
		if(checkCity != null && !checkCity.isEmpty()){
			txtPrimaryCity.set(checkCity);
		}
		
		//Set Primary Phone type
		lstPrimaryPhoneType.select(datatable.getDataParameter("PhoneType"));
		Sleeper.sleep(1000);
		//Set phone number
		txtPrimaryPhoneNumber.safeSet(datatable.getDataParameter("PhoneNumber"));
		Sleeper.sleep(1000);
		//Set email type
		lstPrimaryGuestEmailType.select(datatable.getDataParameter("EmailAddressType"));
		Sleeper.sleep(1000);
		//set email
		txtPrimaryGuestEmailAddress.safeSet(datatable.getDataParameter("EmailAddress"));
		Sleeper.sleep(1000);
		//do not email flag
		String DoNotFlag = (datatable.getDataParameter("DoNotEmail"));
			if(DoNotFlag.equalsIgnoreCase("TRUE")){
				clickDoNotEmail();
			}
		//Click Save button
		btnGuestSaveButton.click();
		Sleeper.sleep(2000);
	}
	
	public void clickPrimaryGuestCheckBox(){
		initialize(driver);
		Sleeper.sleep(1000);
		guestpageIsLoaded();
		Sleeper.sleep(1000);
		chkPrimaryGuestCheckBox.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

}

