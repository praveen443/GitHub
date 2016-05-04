package apps.lilo.checkIn;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
//import org.custommonkey.*;


import apps.lilo.processingYourRequest.ProcessingYourRequest;
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
import utils.TestReporter;

/**
 * @summary Contains the page methods & objects to begin the Check-In process
 * @version Created 08/25/2014
 * @author Waightstill W. Avery
 */
public class CheckInPage {
	//********************************
	//*** Main CheckIn Page Fields ***
	//********************************
	private int intLoopCounter;
	private int intSecondaryGuestCounter;
	private int intMaxLoops = WebDriverSetup.getDefaultTestTimeout();
	private List<WebElement> lstGuestCheckboxes;
	@SuppressWarnings("unused")
	private Object[] arrGuestCheckBoxes;
	private WebElement objElement;
	private Datatable datatable = new Datatable();
	//**********************************
	//*** Main CheckIn Page Elements ***
	//**********************************

	//Grab the Proceed button
	@FindBy(id = "viewGuestForm:proToChkInButtonId")
	private Button btnProceed;

	//Grab the Save button
	@FindBy(id = "viewGuestForm:saveButton")
	private Button btnSave;

	/*
	 * *Primary Guest Objects
	 */	
	//Grab the primary guest checkbox
	@FindBy(css = "input[name^=\"viewGuestForm:guestList:0:j_id\"]")
	private Checkbox chkPrimaryGuestCheckBox;

	//Grab the primary guest title list
	@FindBy(id = "viewGuestForm:courtesyTitle")
	private Listbox lstPrimaryGuestTitle;

	//Grab the primary guest first name text box
	@FindBy(id = "viewGuestForm:guestname")
	private Textbox txtPrimaryGuestFirstName;

	//Grab the primary guest last name text box
	@FindBy(id = "viewGuestForm:guestLastname")
	private Textbox txtPrimaryGuestLastName;

	//Guest Table 
	@FindBy(id = "viewGuestForm:guestList")
	private Webtable tblGuestList;

	//Grab the primary guest suffix list
	@FindBy(id = "viewGuestForm:suffix")
	private Listbox lstPrimaryGuestSuffix;

	//Grab the primary guest address home type list
	@FindBy(id = "viewGuestForm:addressTypeId")
	private Listbox lstPrimaryGuestAddressType;

	//Grab the primary guest country list
	@FindBy(id = "viewGuestForm:countryId")
	private Listbox lstPrimaryGuestCountry;

	//Grab the primary guest postal code text box
	@FindBy(id = "viewGuestForm:ZipID")
	private Textbox txtPrimaryGuestPostalCode;

	//Grab the primary guest address line 1 text box
	@FindBy(id = "viewGuestForm:address1Id")
	private Textbox txtPrimaryGuestAddressLine1;

	//Grab the primary guest address line 2 text box
	@FindBy(id = "viewGuestForm:address2")
	private Textbox txtPrimaryGuestAddressLine2;

	//Grabe the primary guest city text box
	@FindBy(id = "viewGuestForm:cityId")
	private Textbox txtPrimaryGuestCity;

	//Grab the primary guest state list
	@FindBy(id = "viewGuestForm:stateId")
	private Listbox lstPrimaryGuestState;

	//Grab the primary guest language list
	@FindBy(id = "viewGuestForm:guestAddPrefLanguageId")
	private Listbox lstPrimaryGuestLanguage;

	//Grab the primary guest phone type 1 list
	@FindBy(id = "viewGuestForm:phoneType1")
	private Listbox lstPrimaryGuestPhoneType1;

	//Grab the primary guest phone number 1 text box
	@FindBy(id = "viewGuestForm:phoneNumber1")
	private Textbox txtPrimaryGuestPhoneNumber1;

	//Grab the primary guest phone type 2 list
	@FindBy(id = "viewGuestForm:phoneType2")
	private Listbox lstPrimaryGuestPhoneType2;

	//Grab the primary guest phone number 2 text box
	@FindBy(id = "viewGuestForm:phoneNumber2")
	private Textbox txtPrimaryGuestPhoneNumber2;

	//Grab the primary guest email type list
	@FindBy(id = "viewGuestForm:emailTypeId")
	private Listbox lstPrimaryGuestEmailType;

	//Grab the primary guest email address text box
	@FindBy(id = "viewGuestForm:emailAddress")
	private Textbox txtPrimaryGuestEmailAddress;

	//Grab the primary guest Do Not Email checkbx
	@FindBy(id = "viewGuestForm:doNoMailCheckBox")
	private Checkbox chkPrimaryGuestDoNotEmail;

	/*
	 * *Secondary Guest Objects
	 */
	//Grab the secondary guest checkbox
	@FindBy(css = "input[name^=\"viewGuestForm:guestList:1:j_id\"]")
	private Checkbox chkSecondaryGuestCheckBox;

	//Grab the secondary guest title list
	@FindBy(id = "viewGuestForm:courtesyTitle")
	private Listbox lstSecondaryGuestTitle;

	//Grab the secondary guest first name text box
	@FindBy(id = "viewGuestForm:guestname")
	private Textbox txtSecondaryGuestFirstName;

	//Grab the secondary guest last name text box
	@FindBy(id = "viewGuestForm:guestLastname")
	private Textbox txtSecondaryGuestLastName;

	//Grab the secondary guest suffix list
	@FindBy(id = "viewGuestForm:suffix")
	private Listbox lstSecondaryGuestSuffix;

	/*
	 * *Loading Icon Objects
	 */
	//Grab the loading icon for phone number 1
	@FindBy(id = "viewGuestForm:phone1RegionId:status.start")
	private Element icnPhone1Loading;

	//Grab the loading icon for phone number 2
	@FindBy(id = "viewGuestForm:phone2RegionId:status.start")
	private Element icnPhone2Loading;



	//grab missing error guest info pop up msg 
	@FindBy(xpath = ".//*[@id='GuestAddressConfirmationModalPanelHeader']/table/tbody/tr/td[2]/div")
	private Element missingGuestInfo_popupHeader;

	//grab pop up body
	@FindBy(id = "GuestAddressConfirmationSubmitForm:guestAddressConfirmId")
	private Element missingGuestInfo_validationMSG;

	//go button No
	@FindBy(id = "GuestAddressConfirmationSubmitForm:guestAddressConfirmNoId")
	private Element missingGuestInfo_ButtonNO;



	//*********************
	//** Build page area **
	//*********************
	private WebDriver driver;

	/**
	 * 
	 * @summary Constructor to initialize the page
	 * @version Created 08/25/2014
	 * @author Waightstill W Avery
	 * @param  driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public CheckInPage(WebDriver driver){
		this.driver = driver;	
		ElementFactory.initElements(driver, this);	  
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	private void initialize(WebDriver driver) {
	//	return ElementFactory.initElements(driver, CheckInPage.class);	        
	}

	@SuppressWarnings("unused")
	private CheckInPage initialize() {
		return ElementFactory.initElements(driver, CheckInPage.class);	        
	}

	public boolean pageLoaded(){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnProceed); 
	}

	/**
	 * @summary : Method to determine if the Guest page is loaded.
	 * @author  : Praveen Namburi
	 * @version : 11/18/2015
	 * @return	: Boolean, true if the page is loaded, false otherwise
	 */
	public boolean guestpageIsLoaded(){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnSave); 
	}

	public boolean pageLoaded(Element element){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element); 
	}

	//**************************************
	//*** Main CheckIn Page Interactions ***
	//************************************** 

	/**
	 * 
	 * @summary Method to enter guest information (e.g. first name, last name, etc)
	 * 	This will, depending on the scenario, enter guest information for each guest on the reservation
	 * @version Created 9/11/2014
	 * @author Waightstill W Avery
	 * @param  scenario - used to determine which row of the Master data table\sheet to use; also determines how many guests to enter data for
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void EnterGuestInformation(String scenario) {
		/*datatable.setDatatableSheet("CheckInInfo");
		datatable.setDatatableRow(datatable.getRowContains(scenario, 0));
		 */
		datatable.setVirtualtablePage("CheckInInfo");
		datatable.setVirtualtableScenario(scenario);
		final String[] arrSecondaryGuestTitle = datatable.getDataParameter("SecondaryTitle").split(";");
		final String[] arrSecondaryGuestFirstName = datatable.getDataParameter("SecondaryFirstName").split(";");
		final String[] arrSecondaryGuestLastName = datatable.getDataParameter("SecondaryLastName").split(";");
		final String[] arrSecondaryGuestSuffix = datatable.getDataParameter("SecondarySuffix").split(";");
		String strId;
		int loopCounter;

		pageLoaded();
		initialize(driver);

		lstGuestCheckboxes = driver.findElements(By.cssSelector("input[name^=\"viewGuestForm:guestList:\"]"));
		//itrWebElement = lstGuestCheckboxes.iterator();

		if(lstGuestCheckboxes.size() != Integer.parseInt(datatable.getDataParameter("NumberOfGuests"))){
			Reporter.log("The number of guests does not match the number of displayed check boxes.");
		}

		intLoopCounter = 0;
		intSecondaryGuestCounter = 0;
		intMaxLoops = lstGuestCheckboxes.size();

		//Determine if the primary guest is to be edited
		if(!datatable.getDataParameter("EditPrimaryGuest").toString().equalsIgnoreCase("false")){
			//Check the primary guest check box
			chkPrimaryGuestCheckBox.check();
			//new ProcessingYourRequest().WaitForProcessRequest(driver);
			Sleeper.sleep(2000);
			pageLoaded(lstPrimaryGuestTitle);

			//Select a title
			lstPrimaryGuestTitle.select(datatable.getDataParameter("PrimaryTitle"));

			//Edit the guest first name
			txtPrimaryGuestFirstName.set(datatable.getDataParameter("PrimaryFirstName"));

			//Edit the guest first name
			txtPrimaryGuestLastName.set(datatable.getDataParameter("PrimaryLastName"));

			//Select a suffix
			lstPrimaryGuestSuffix.select(datatable.getDataParameter("PrimarySuffix"));

			//Select a address type
			lstPrimaryGuestAddressType.select(datatable.getDataParameter("PrimaryAddressType"));

			//Select a country
			lstPrimaryGuestCountry.select(datatable.getDataParameter("PrimaryCountry"));

			//Enter a zip code
			if(!datatable.getDataParameter("PrimaryPostalCode").isEmpty()){
				txtPrimaryGuestPostalCode.safeSet(datatable.getDataParameter("PrimaryPostalCode"));
				//Loop until the city is autopopulated after entering the postal code
				intLoopCounter = 0;
				do{
					pageLoaded(txtPrimaryGuestCity);
					///initialize(driver);
					intLoopCounter++;
					if(intLoopCounter == intMaxLoops){
						break;
					}
					Sleeper.sleep(1500);			
				}while(txtPrimaryGuestCity.getAttribute("value").isEmpty());
			}

			//Enter the first line of the address
			txtPrimaryGuestAddressLine1.set(datatable.getDataParameter("PrimaryAddressLine1"));

			//Enter the second line of the address
			txtPrimaryGuestAddressLine2.set(datatable.getDataParameter("PrimaryAddressLine2"));

			//Enter a city
			txtPrimaryGuestCity.set(datatable.getDataParameter("PrimaryCity"));

			//Select a state
			lstPrimaryGuestState.select(datatable.getDataParameter("PrimaryState"));

			//Select a language
			lstPrimaryGuestLanguage.select(datatable.getDataParameter("PrimaryLanguage"));	

			//Select a phone type 1
			lstPrimaryGuestPhoneType1.select(datatable.getDataParameter("PrimaryPhoneType1"));
			Sleeper.sleep(1500);

			if(!datatable.getDataParameter("PrimaryPhoneType1").isEmpty()){
				//Enter a phone number 1
				loopCounter = 0;
				do{
					try{
						txtPrimaryGuestPhoneNumber1.safeSet(datatable.getDataParameter("PrimaryPhoneNumber1"));
					}catch(StaleElementReferenceException sere){
						pageLoaded(txtPrimaryGuestPhoneNumber1);
					}
					//					icnPhone1Loading.syncHidden(driver);
					
					loopCounter++;

					pageLoaded(txtPrimaryGuestPhoneNumber1);
					Sleeper.sleep(1000);
				}while(txtPrimaryGuestPhoneNumber1.getAttribute("value").equalsIgnoreCase("") && loopCounter < WebDriverSetup.getDefaultTestTimeout());	

			}

			//Select a phone type 2
			lstPrimaryGuestPhoneType2.select(datatable.getDataParameter("PrimaryPhoneType2"));

			//Enter a phone number 2
			txtPrimaryGuestPhoneNumber2.safeSet(datatable.getDataParameter("PrimaryPhoneNumber2"));
			//icnPhone2Loading.syncHidden(driver);
			Sleeper.sleep(1000);

			//Select a email type
			lstPrimaryGuestEmailType.select(datatable.getDataParameter("PrimaryEmailType"));
			new ProcessingYourRequest().WaitForProcessRequest(driver);

			//Enter an email address
			txtPrimaryGuestEmailAddress.safeSetValidate(driver, datatable.getDataParameter("PrimaryEmailAddress"));

			//Set the Do Not Email checkbox
			if(datatable.getDataParameter("PrimaryDoNotEmail").toString().equalsIgnoreCase("false")){
				chkPrimaryGuestDoNotEmail.check();
			}

			Sleeper.sleep(1000);

			//Click the Save button
			btnSave.jsClick(driver);
			new ProcessingYourRequest().WaitForProcessRequest(driver);

			intLoopCounter++;
		}

		//Determine if secondary guests are to be edited
		if(!datatable.getDataParameter("EditSecondaryGuest").toString().equalsIgnoreCase("false")){
			//Select the next guest to edit
			for(intSecondaryGuestCounter = 0; intSecondaryGuestCounter < Integer.parseInt(datatable.getDataParameter("NumberOfGuests"))-1;intSecondaryGuestCounter++){
				//Define the next check box object
				strId = "input[name^=\"viewGuestForm:guestList:" +String.valueOf(intSecondaryGuestCounter+1)+"\"]";
				//Grab the next checkbox
				objElement = driver.findElement(By.cssSelector(strId));
				//Click the next checkbox
				objElement.click();

				new ProcessingYourRequest().WaitForProcessRequest(driver);

				//Select a title
				if(intSecondaryGuestCounter >= arrSecondaryGuestTitle.length){
					lstSecondaryGuestTitle.select(arrSecondaryGuestTitle[arrSecondaryGuestTitle.length-1]);
				}else{
					lstSecondaryGuestTitle.select(arrSecondaryGuestTitle[intSecondaryGuestCounter]);
				}

				//Enter the first name
				if(intSecondaryGuestCounter >= arrSecondaryGuestFirstName.length){
					txtSecondaryGuestFirstName.set(arrSecondaryGuestFirstName[arrSecondaryGuestFirstName.length-1]);
				}else{
					txtSecondaryGuestFirstName.set(arrSecondaryGuestFirstName[intSecondaryGuestCounter]);
				}

				//Enter the second name
				if(intSecondaryGuestCounter >= arrSecondaryGuestLastName.length){
					txtSecondaryGuestLastName.set(arrSecondaryGuestLastName[arrSecondaryGuestLastName.length-1]);
				}else{
					txtSecondaryGuestLastName.set(arrSecondaryGuestLastName[intSecondaryGuestCounter]);
				}

				//Select a suffix
				if(intSecondaryGuestCounter >= arrSecondaryGuestSuffix.length){
					lstSecondaryGuestSuffix.select(arrSecondaryGuestSuffix[arrSecondaryGuestSuffix.length-1]);
				}else{
					lstSecondaryGuestSuffix.select(arrSecondaryGuestSuffix[intSecondaryGuestCounter]);
				}

				//Click the Save button
				btnSave.click();
				new ProcessingYourRequest().WaitForProcessRequest(driver);
			}
		}		
	}	


	/*
	 * *Method to click the Proceed button
	 */
	public void clickProceed(){
		pageLoaded(btnProceed);
		btnProceed.syncEnabled(driver);
		btnProceed.highlight(driver);
		btnProceed.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver,120);
	}


	// enter Primary guest INfo  - invalid 

	public void EnterGuestInformation_invalid(String scenario) {
		/*datatable.setDatatableSheet("CheckInInfo");
		datatable.setDatatableRow(datatable.getRowContains(scenario, 0));
		 */
		datatable.setVirtualtablePage("CheckInInfo");
		datatable.setVirtualtableScenario(scenario);
		@SuppressWarnings("unused")
		final String[] arrSecondaryGuestTitle = datatable.getDataParameter("SecondaryTitle").split(";");
		@SuppressWarnings("unused")
		final String[] arrSecondaryGuestFirstName = datatable.getDataParameter("SecondaryFirstName").split(";");
		@SuppressWarnings("unused")
		final String[] arrSecondaryGuestLastName = datatable.getDataParameter("SecondaryLastName").split(";");
		@SuppressWarnings("unused")
		final String[] arrSecondaryGuestSuffix = datatable.getDataParameter("SecondarySuffix").split(";");

		pageLoaded(chkPrimaryGuestCheckBox);
		//initialize(driver);

		lstGuestCheckboxes = driver.findElements(By.cssSelector("input[name^=\"viewGuestForm:guestList:\"]"));

		if(lstGuestCheckboxes.size() != Integer.parseInt(datatable.getDataParameter("NumberOfGuests"))){
			Reporter.log("The number of guests does not match the number of displayed check boxes.");
		}

		intLoopCounter = 0;
		intSecondaryGuestCounter = 0;
		intMaxLoops = lstGuestCheckboxes.size();

		//Determine if the primary guest is to be edited
		if(!datatable.getDataParameter("EditPrimaryGuest").toString().equalsIgnoreCase("false")){
			//Check the primary guest check box
			chkPrimaryGuestCheckBox.check();
			new ProcessingYourRequest().WaitForProcessRequest(driver);

			/*//Select a title
			lstPrimaryGuestTitle.select(datatable.getDataParameter("PrimaryTitle"));

			//Edit the guest first name
			txtPrimaryGuestFirstName.set(datatable.getDataParameter("PrimaryFirstName"));

			//Edit the guest first name
			txtPrimaryGuestLastName.set(datatable.getDataParameter("PrimaryLastName"));

			//Select a suffix
			lstPrimaryGuestSuffix.select(datatable.getDataParameter("PrimarySuffix"));

			//Select a address type
			lstPrimaryGuestAddressType.select(datatable.getDataParameter("PrimaryAddressType"));

			//Select a country
			lstPrimaryGuestCountry.select(datatable.getDataParameter("PrimaryCountry"));*/

			//Enter a zip code
			if(!datatable.getDataParameter("PrimaryPostalCode").isEmpty()){
				txtPrimaryGuestPostalCode.safeSet(datatable.getDataParameter("PrimaryPostalCode"));
				//Loop until the city is autopopulated after entering the postal code
				intLoopCounter = 0;
				do{					

					pageLoaded(txtPrimaryGuestCity);
					intLoopCounter++;
					if(intLoopCounter == intMaxLoops){
						break;
					}
					Sleeper.sleep(1500);
				}while(txtPrimaryGuestCity.getAttribute("value").isEmpty());
			}

			//Enter the first line of the address
			txtPrimaryGuestAddressLine1.set(datatable.getDataParameter("PrimaryAddressLine1"));

			/*//Enter the second line of the address
			txtPrimaryGuestAddressLine2.set(datatable.getDataParameter("PrimaryAddressLine2"));

			//Enter a city
			txtPrimaryGuestCity.set(datatable.getDataParameter("PrimaryCity"));

			//Select a state
			lstPrimaryGuestState.select(datatable.getDataParameter("PrimaryState"));

			//Select a language
			lstPrimaryGuestLanguage.select(datatable.getDataParameter("PrimaryLanguage"));	

			//Select a phone type 1
			lstPrimaryGuestPhoneType1.select(datatable.getDataParameter("PrimaryPhoneType1"));
			Thread.sleep(1500);

			if(!datatable.getDataParameter("PrimaryPhoneType1").isEmpty()){
				//Enter a phone number 1
				loopCounter = 0;
				do{
					txtPrimaryGuestPhoneNumber1.safeSet(datatable.getDataParameter("PrimaryPhoneNumber1"));
//					icnPhone1Loading.syncHidden(driver);
					Thread.sleep(1000);
					loopCounter++;

					pageLoaded();

					initialize(driver);
				}while(txtPrimaryGuestPhoneNumber1.getAttribute("value").equalsIgnoreCase("") && loopCounter < WebDriverSetup.getDefaultTestTimeout());	

		}*/

			/*//Select a phone type 2
			lstPrimaryGuestPhoneType2.select(datatable.getDataParameter("PrimaryPhoneType2"));

			//Enter a phone number 2
			txtPrimaryGuestPhoneNumber2.safeSet(datatable.getDataParameter("PrimaryPhoneNumber2"));
			//icnPhone2Loading.syncHidden(driver);
			Sleeper.sleep(1000);

			//Select a email type
			lstPrimaryGuestEmailType.select(datatable.getDataParameter("PrimaryEmailType"));
			new ProcessingYourRequest().WaitForProcessRequest(driver);

			//Enter an email address
			txtPrimaryGuestEmailAddress.safeSetValidate(driver, datatable.getDataParameter("PrimaryEmailAddress"));

			//Set the Do Not Email checkbox
			if(datatable.getDataParameter("PrimaryDoNotEmail").toString().equalsIgnoreCase("false")){
				chkPrimaryGuestDoNotEmail.check();
			}

			Sleeper.sleep(2000);*/

			//Click the Save button
			btnSave.highlight(driver);
			btnSave.jsClick(driver);
			new ProcessingYourRequest().WaitForProcessRequest(driver);

			intLoopCounter++;
		}

		clickProceed();
	}

	/**
	 * 
	 * @summary Created Method to handle missing guest information pop up 
	 * @version Created 12/01/2015
	 * @author  Lalitha Banda
	 * @param   NA
	 * @return  NA
	 * 
	 */

	// handle Error Pop up - MissingGuestInfo Pop up 
	public void handle_MissingErrorGuestInfo(){
		if(missingGuestInfo_popupHeader.syncVisible(driver,3,false)){

			TestReporter.log(missingGuestInfo_validationMSG.getText());
			TestReporter.assertTrue(missingGuestInfo_validationMSG.getText().contains("fields are missing, Primary Address, City, State, Zip Code, Address Type, Phone/Phone Type. Do you wish to proceed"), "Invalid Error Message!");
			missingGuestInfo_ButtonNO.highlight(driver);
			missingGuestInfo_ButtonNO.click();

		}else{
			TestReporter.log("No popup Found.");
		}
	}

}
