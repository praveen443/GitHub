/*package apps.dreams.DiscoveryPage;

import java.util.Iterator;
import java.util.List;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.dreams.GuestSearchWindow.GuestPartyWindow;
import apps.dreams.GuestSearchWindow.GuestSearch;
import apps.dreams.HeaderFrame.HeaderFrame;
import apps.dreams.PleaseWait.PleaseWait;
import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.FrameHandler;
import core.WebDriverSetup;
import core.WindowHandler;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Link;
import core.interfaces.Listbox;
import core.interfaces.RadioGroup;
import core.interfaces.Textbox;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import selenium.common.Constants;
import utils.Datatable;
import utils.Randomness;
import utils.Sleeper;
import utils.TestReporter;
import utils.date.DateTimeConversion;

*//**
 * @summary Contains the methods & objects for the Dreams UI content frame for
 *          discovery page
 * @version Created 09/10/2014
 * @author Waightstill W. Avery
 *//*
public class DiscoveryPageContentFrame {
	// ****************************
	// *** Content Frame Fields ***
	// ****************************
	private int timeout = WebDriverSetup.getDefaultTestTimeout();
	private int loopCount = 0;
	private Datatable datatable = new Datatable();
	private WebDriver driver;

	// Package Code values
	private String environment = WebDriverSetup.getEnvironmentUnderTest();
	private String packageCode = "";
	private String arrivalDate_reruntoFunstion;
	private String guestLastFirstName;
	// ******************************
	// *** Content Frame Elements ***
	// ******************************
	@FindBy(name = "b_New")
	private Button btnNewPrimaryGuest;

	@FindBy(xpath = "//*[@id='firstLayer']/table[2]/tbody/tr/td[2]/input[1]")
	private Button btnNewTravelAgency;

	@FindBy(id = "agencyId")
	private Textbox txtAgentID;

	@FindBy(name = "Search")
	private Button btnAgencySearch;

	@FindBy(partialLinkText = "Travel Agency/Wholesaler Information")
	private Link lnkTravelAgency;

	@FindBy(id = "groupCode")
	private Textbox txtGroupCode;

	@FindBy(id = "contactName")
	// @FindBy(name = "discoveryPreferenceTO.contactName")
	private Textbox txtContactName;

	@FindBy(id = "wdwArrivalDate")
	private Textbox txtWDWArrivalDate;

	@FindBy(id = "wdwNights")
	private Textbox txtWDWNights;

	@FindBy(id = "wdwDepartureDate")
	private Textbox txtWDWDepartureDate;

	@FindBy(id = "areaArrivalDate")
	private Textbox txtAreaArrivalDate;

	@FindBy(id = "areaNights")
	private Textbox txtAreaNights;

	@FindBy(id = "areaDepartureDate")
	private Textbox txtAreaDepartureDate;

	@FindBy(id = "bookingSource")
	private Textbox txtBookingSource;

	@FindBy(id = "extRefNumber")
	private Textbox txtReferenceNumber;

	@FindBy(name = "rsrReservation")
	private Checkbox chkRSRflag;

	@FindBy(id = "numberOfAdults")
	private Textbox txtNumberOfAdults;

	@FindBy(id = "numberOfChildren")
	private Textbox txtNumberOfChildren;

	@FindBy(name = "ResortSelCriteria")
	private RadioGroup radResortSelectCriteria;

	@FindBy(name = "selectedFacilityType")
	private Listbox lstResortType;

	@FindBy(id = "selectedBlockResortList")
	private Listbox lstResortList;

	@FindBy(id = "selectedBlockResortRoomTypeList")
	private Listbox lstRoomTypeList;

	@FindBy(name = "discoveryPreferenceTO.numberOfRoomsForDisplay")
	private Textbox txtNumberOfRooms;

	@FindBy(id = "selectedResortList")
	private Listbox lstResortAndRoomTypes;

	@FindBy(name = "packageCode")
	private Textbox txtPackageCode;

	@FindBy(name = "Add")
	private Button btnAddPackageCode;

	@FindBy(name = "selectedPackageDescriptionList")
	private Listbox lstPackageCodeDescriptions;

	@FindBy(name = "adaCheck")
	private Checkbox chkADA;

	@FindBy(id = "loading")
	private Element eleAccommodationLoadingImage;

	@FindBy(xpath = "//*[@id=\"firstLayer\"]/table[3]/tbody/tr/td/table[2]/tbody/tr[2]/td[2]/div/a/img")
	private Element eleWDWCalendar;

	@FindBy(id = "id=errorStr")
	private Element eleError;

	@FindBy(name = "b_yes")
	private Button btnErrorYes;

	@FindBy(id = "errorStr")
	private Element eleErrorMsg;

	@FindBy(name = "b_yes")
	private Button btnAlertOk;
	@FindBy(name = "selectedBlockPackageList")
	private Listbox lstBlockPackage;

	@FindBy(partialLinkText = "Delete")
	private Link lnkDelete;

	// *********************
	// ** Build page area **
	// *********************

	// DataProvider_ExcelSheet datapro = new DataProvider_ExcelSheet();
	*//**
	 * 
	 * @summary Constructor to initialize the frame
	 * @version Created 09/10/2014
	 * @author Waightstill W Avery
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 *//*
	public DiscoveryPageContentFrame(WebDriver driver) {
		this.driver = driver;
		FrameHandler.findAndSwitchToFrame(driver, "contentFrame");
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);
	}

	private DiscoveryPageContentFrame initialize() {
		FrameHandler.findAndSwitchToFrame(driver, "contentFrame");
		return ElementFactory.initElements(driver, this.getClass());
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnNewPrimaryGuest);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				element);
	}

	// *****************************************
	// *** Content Frame Interactions ***
	// *****************************************

	public void clickNewPrimaryGuest() {
		pageLoaded(btnNewPrimaryGuest);
		btnNewPrimaryGuest.syncVisible(driver);
		btnNewPrimaryGuest.sendKeys(Keys.ENTER);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler
		.waitUntilWindowExistsTitleContains(driver, "Guest Search");
	}

	*//**
	 * 
	 * @summary Method to enter travel plan data
	 * @version Created 09/10/2014
	 * @author Waightstill W Avery
	 * @param scenario
	 *            - key for choosing which travel plan to enter
	 * @throws Exception
	 *             surrounding datatable access
	 * @return NA
	 * 
	 *//*
	public void enterTravelPlan(String scenario) {
		datatable.setVirtualtablePage("DiscoveryTravelPlan");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded(txtGroupCode);
		// initialize();

		// Workaround for leading zero on groupNumber
		// Added by Dennis requested by Justin
		String groupNumber = datatable.getDataParameter("GroupNumber");
		if (groupNumber.equals("1825")) {
			// add leading zero
			groupNumber = "01825";
		}
		if (groupNumber.equals("1905")) {
			// add leading zero
			groupNumber = "01905";
		}
		if (groupNumber.equals("4250")) {
			// add leading zero
			groupNumber = "04250";
		}

		String contactName = datatable.getDataParameter("ContactName");
		// String daysOut = datatable.getDataParameter("DaysOut");
		String numberNights = datatable.getDataParameter("NumberNights");
		String bookingSource = datatable.getDataParameter("BookingSource");
		String externalRefNumber = datatable
				.getDataParameter("ExternalRefNumber");
		String rsr = datatable.getDataParameter("RSR");
//		String WDWDepartDaysOut = datatable
//				.getDataParameter("WDWDepartDaysOut");
//		String AreaNights = datatable.getDataParameter("AreaNights");
//		String AreaDepartDaysOut = datatable
//				.getDataParameter("AreaDepartDaysOut");
//		String Negative = datatable.getDataParameter("Negative");
//		String ErrorMessage = datatable.getDataParameter("ErrorMessage");

		// Establish a random external reference number if needed
		if (externalRefNumber.equalsIgnoreCase("random")) {
			externalRefNumber = Randomness.randomNumber(16);
		}

		String arrivalDate = DateTimeConversion.getDaysOut(
				datatable.getDataParameter("DaysOut"), "MM/dd/yyyy");
		arrivalDate_reruntoFunstion = arrivalDate;
		System.out.println("Arrival Date : " + arrivalDate);

		// Set the group number
		if (!groupNumber.isEmpty()) {
			// txtGroupCode.sendKeys(groupNumber + Keys.TAB);
			txtGroupCode.safeSet(groupNumber);
			PleaseWait.WaitForPleaseWait(driver);

			System.out.println(Randomness.generateCurrentXMLDatetime());
			loopCount = 0;
			boolean isEmpty = true;
			do {
				Sleeper.sleep(1000);
				initialize();
				loopCount++;
				TestReporter.assertNotEquals(
						loopCount,
						timeout * 10,
						"The group code was not added within "
								+ String.valueOf(timeout) + " seconds.");
				// } while (!txtBookingSource.getText().isEmpty());
				try {
					txtBookingSource.highlight(driver);
					isEmpty = txtBookingSource.getAttribute("value").isEmpty();
				} catch (Exception e) {

				}
			} while (isEmpty);
			System.out.println(Randomness.generateCurrentXMLDatetime());
			// Sleeper.sleep(3000);

			// initialize();
		}

		// Handles Alert for invalid Group Number and validates the Alert
		// message
		if ((WindowHandler.waitUntilNumberOfWindowsAre(driver, 2, 5))
				&& (WindowHandler.waitUntilWindowExistsTitleContains(driver,
						"Alert", 5))) {
			validateGroupCodeAlert(scenario);
		} else {
			pageLoaded(txtContactName);

			// Set the contact name
			txtContactName.set(contactName);

			// Occassionally an error is thrown even though a valid date is
			// entered. This is a known issue with Dreams and automation. Loop
			// until the error no longer appears.
			boolean dateSetSuccessfully = false;
			int counter = 0;
			int maxTries = 10;
			String alertMessage = "";
			do {
				pageLoaded(txtWDWArrivalDate);
				txtWDWArrivalDate.safeSet(arrivalDate);
				counter++;
				if ((WindowHandler.waitUntilNumberOfWindowsAre(driver, 2, 3))
						&& (WindowHandler.waitUntilWindowExistsTitleContains(driver,
								"Alert", 3))) {	
					alertMessage = eleErrorMsg.getText();
					btnAlertOk.click();
					WindowHandler.waitUntilNumberOfWindowsAre(driver, 1, 5);
					WindowHandler.waitUntilWindowExistsTitleContains(driver,
							"Disney Reservation Entry and Management System");
				} else {
					dateSetSuccessfully = true;
				}
			} while (!dateSetSuccessfully && counter <= maxTries);
			TestReporter.assertTrue(counter <= maxTries,
					"An error occurred when trying to enter WDW Arrival Dates. Error message is ["
							+ alertMessage + "].");

			// Set the WDW number of nights
			txtWDWNights.safeSet(numberNights);
			Sleeper.sleep(500);
			// txtWDWNights.safeSet(numberNights);

			// Set the Area arrival date
			txtAreaArrivalDate.safeSet(arrivalDate);

			// Set the Area number of nights
			txtAreaNights.safeSet(numberNights);

			// Set the booking source
			txtBookingSource.safeSet(bookingSource);

			// Set the External Reference Number
			txtReferenceNumber.set(externalRefNumber);

			// Set the RSR flag
			if (rsr.equalsIgnoreCase("TRUE")) {
				chkRSRflag.check();
				pageLoaded();
				initialize();
			}
		}
	}

	*//**
	 * 
	 * @summary Method to enter travel plan data
	 * @version Created 09/10/2014
	 * @author Waightstill W Avery
	 * @param scenario
	 *            - key for choosing which travel plan to enter
	 * @throws Exception
	 *             surrounding datatable access
	 * @return NA
	 * 
	 *//*
	public void enterTravelPlan_Negative(String scenario) {
		datatable.setVirtualtablePage("DiscoveryTravelPlan");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded(txtGroupCode);

		String groupNumber = datatable.getDataParameter("GroupNumber");

		String wdwDaysOut = datatable.getDataParameter("DaysOut");
		String wdwNumberNights = datatable.getDataParameter("NumberNights");
		String wdwDepartDaysOut = datatable
				.getDataParameter("WDWDepartDaysOut");
		String wdwArrivalDate = "";
		String wdwDepartDate = "";

		String areaDaysOut = datatable.getDataParameter("AreaArrivalDaysOut");
		String areaNights = datatable.getDataParameter("AreaNights");
		String areaDepartDaysOut = datatable
				.getDataParameter("AreaDepartDaysOut");
		String areaArrivalDate = "";
		String areaDepartDate = "";

//		String Negative	 = datatable.getDataParameter("Negative");
		String expectedErrorMessage = datatable.getDataParameter("ErrorMessage");
		String actualErrorMessage = "";
		String ClickContinue = datatable.getDataParameter("ClickContinue");

		System.out.println("WDW Nights : " + wdwNumberNights);
		try {
			wdwArrivalDate = DateTimeConversion.getDaysOut(wdwDaysOut,
					"MM/dd/yyyy");
		} catch (NumberFormatException nfe) {
			wdwArrivalDate = wdwDaysOut;
		}
		System.out.println("WDW Arrival Date : " + wdwArrivalDate);

		try{
			wdwDepartDate = DateTimeConversion.getDaysOut(wdwDepartDaysOut, "MM/dd/yyyy");
		}catch(NumberFormatException nfe){
			try {
				wdwDepartDate = DateTimeConversion.getDaysOut(wdwDepartDaysOut,
						"MM/dd/yyyy");
			} catch (NumberFormatException nfe1) {
				wdwDepartDate = wdwDepartDaysOut;
			}
			System.out.println("WDW Departure Date : " + wdwDepartDate);

			System.out.println("Area Nights : " + areaNights);
			try {
				areaArrivalDate = DateTimeConversion.getDaysOut(areaDaysOut,
						"MM/dd/yyyy");
			} catch (NumberFormatException nfe2) {
				areaArrivalDate = areaDaysOut;
			}
			System.out.println("Area Arrival Date : " + areaArrivalDate);

			try{
				areaDepartDate = DateTimeConversion.getDaysOut(areaDepartDaysOut, "MM/dd/yyyy");
			}catch(NumberFormatException nfe3){
				areaDepartDate = areaDepartDaysOut;
			}
			System.out.println("Area Departure Date : " + areaDepartDate);

			// Set the group number
			if (!groupNumber.isEmpty()) {
				txtGroupCode.safeSet(groupNumber);
				PleaseWait.WaitForPleaseWait(driver);
			}

			// Handles Alert for invalid Group Number and validates the Alert
			// message
			if (!groupNumber.isEmpty()) {
				if ((WindowHandler.waitUntilNumberOfWindowsAre(driver, 2, 5))
						&& (WindowHandler.waitUntilWindowExistsTitleContains(
								driver, "Alert", 5))) {
					validateGroupCodeAlert(scenario);
				}
			} else {
				pageLoaded(txtContactName);

				// Set the WDW arrival date
				pageLoaded(txtWDWArrivalDate);
				// initialize();
				txtWDWArrivalDate.safeSet(wdwArrivalDate);
				Sleeper.sleep(500);

				// Set the WDW number of nights
				txtWDWNights.safeSet(wdwNumberNights);
				Sleeper.sleep(500);

				// Set the WDW departure date
				txtWDWDepartureDate.safeSet(wdwDepartDate);
				Sleeper.sleep(500);

				// Set the Area arrival date
				txtAreaArrivalDate.safeSet(areaArrivalDate);

				// Set the Area number of nights
				txtAreaNights.safeSet(areaNights);

				// Set the Area departure date
				txtAreaDepartureDate.safeSet(areaDepartDate);
				Sleeper.sleep(500);

				if(ClickContinue.equalsIgnoreCase("true")){
					//			if(ClickContinue.equalsIgnoreCase("true")){
					new HeaderFrame(driver).clickContinueError();
				}
				WindowHandler.waitUntilNumberOfWindowsAre(driver, 2, 5);
				WindowHandler
				.waitUntilWindowExistsTitleContains(driver, "Alert", 5);
				actualErrorMessage = eleErrorMsg.getText();
				TestReporter.assertTrue(
						actualErrorMessage.toLowerCase().contains(
								expectedErrorMessage.toLowerCase()),
								"The actual error message [" + actualErrorMessage
								+ "] does not contain the expected error message ["
								+ expectedErrorMessage + "].");
				btnAlertOk.jsClick(driver);
				WindowHandler.waitUntilNumberOfWindowsAre(driver, 1, 5);
				WindowHandler.waitUntilWindowExistsTitleContains(driver,
						"Disney Reservation Entry and Management System");
			}
		}

	}

	public void enterTravelPlanwithoutGroupCode(String scenario) {
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Disney Reservation");
		new PageLoaded().isDomComplete(driver);
		FrameHandler.findAndSwitchToFrame(driver, "contentFrame");
		datatable.setVirtualtablePage("DiscoveryTravelPlan");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded(txtGroupCode);

//		String groupNumber = datatable.getDataParameter("GroupNumber");
		String contactName = datatable.getDataParameter("ContactName");
		// String daysOut = datatable.getDataParameter("DaysOut");
		String numberNights = datatable.getDataParameter("NumberNights");
//		String bookingSource = datatable.getDataParameter("BookingSource");
//		String externalRefNumber = datatable
//				.getDataParameter("ExternalRefNumber");
		String rsr = datatable.getDataParameter("RSR");

		// Establish a random external reference number if needed
		
		 * if(externalRefNumber.equalsIgnoreCase("random")){ externalRefNumber =
		 * Randomness.randomNumber(16); }
		 

		String arrivalDate = DateTimeConversion.ConvertToDate(datatable
				.getDataParameter("DaysOut"));
		arrivalDate_reruntoFunstion = arrivalDate;
		System.out.println("Arrival Date : " + arrivalDate);

		// Set the group number
		
		 * if(!groupNumber.isEmpty()){ //txtGroupCode.sendKeys(groupNumber +
		 * Keys.TAB); txtGroupCode.safeSet(groupNumber);
		 * 
		 * System.out.println(Randomness.generateCurrentXMLDatetime());
		 * loopCount = 0; do{ Sleeper.sleep(100); loopCount++;
		 * TestReporter.assertNotEquals(loopCount, timeout*10,
		 * "The group code was not added within " +String.valueOf(timeout)+
		 * " seconds."); }while(!txtBookingSource.getText().isEmpty());
		 * System.out.println(Randomness.generateCurrentXMLDatetime());
		 * Sleeper.sleep(3000); pageLoaded(txtContactName); initialize(); }
		 

		// Set the contact name
		txtContactName.set(contactName);

		// Set the WDW arrival date
		pageLoaded(txtWDWArrivalDate);
		txtWDWArrivalDate.safeSet(arrivalDate);
		Sleeper.sleep(500);
		txtWDWArrivalDate.safeSet(arrivalDate);

		// Set the WDW number of nights
		txtWDWNights.set(numberNights);
		Sleeper.sleep(500);
		txtWDWNights.set(numberNights);

		// Set the Area arrival date
		txtAreaArrivalDate.safeSet(arrivalDate);

		// Set the Area number of nights
		txtAreaNights.set(numberNights);

		// Set the booking source
		// txtBookingSource.set(bookingSource);

		// Set the External Reference Number
		// txtReferenceNumber.set(externalRefNumber);

		// Set the RSR flag
		if (rsr.equalsIgnoreCase("TRUE")) {
			chkRSRflag.check();
			pageLoaded();
			initialize();
		}
	}

	*//**
	 * 
	 * @summary Method to enter party mix data
	 * @version Created 09/10/2014
	 * @author Waightstill W Avery
	 * @param scenario
	 *            - key for choosing which party mix to enter
	 * @throws Exception
	 *             surrounding datatable access
	 * @return NA
	 * 
	 *//*
	public void enterPartyMix(String scenario) {
		datatable.setVirtualtablePage("DiscoveryPartyMix");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded(txtNumberOfAdults);

		String numberOfAdults = datatable.getDataParameter("NumberAdults");
		String numberOfChildren = datatable.getDataParameter("NumberChildren");
		String childAges = datatable.getDataParameter("childAges");
		String[] arrChildAges = childAges.split(";");

		// If the number of children is not blank, ensure there are an equal
		// number of child ages as there are number of children
		if (!numberOfChildren.isEmpty()) {
			TestReporter.assertEquals(arrChildAges.length,
					Integer.parseInt(numberOfChildren),
					"The number of child ages [" + arrChildAges.length
					+ "] does not match the number of children ["
					+ numberOfChildren + "]");
		}

		// Enter the number of adults
		txtNumberOfAdults.set(numberOfAdults);

		// Enter the number of children
		txtNumberOfChildren.set(numberOfChildren);

		// Enter child ages
		if (!numberOfChildren.isEmpty()) {
			if (Integer.parseInt(numberOfChildren) > 0) {
				enterChildAges();
			}
		}
	}

	*//**
	 * 
	 * @summary Method to enter party mix data
	 * @version Created 09/10/2014
	 * @author Waightstill W Avery
	 * @param scenario
	 *            - key for choosing which party mix to enter
	 * @throws Exception
	 *             surrounding datatable access
	 * @return NA
	 * 
	 *//*
	public void enterPartyMix_Negative(String scenario) {
		datatable.setVirtualtablePage("DiscoveryPartyMix");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded(txtNumberOfAdults);

		String numberOfAdults = datatable.getDataParameter("NumberAdults");
		String numberOfChildren = datatable.getDataParameter("NumberChildren");
		String expectedErrorMessage = datatable
				.getDataParameter("ErrorMessage");
		String actualErrorMessage = "";

		// Enter the number of adults
		if (numberOfAdults.equalsIgnoreCase("{BLANK}")) {
			txtNumberOfAdults.sendKeys(Keys.CONTROL + "a");
			txtNumberOfAdults.sendKeys(Keys.DELETE);
		} else {
			txtNumberOfAdults.safeSet(numberOfAdults);
		}

		// Enter the number of children
		txtNumberOfChildren.safeSet(numberOfChildren);

		if(!numberOfChildren.isEmpty()){
			if (!numberOfChildren.isEmpty()) {
				WindowHandler.waitUntilNumberOfWindowsAre(driver, 2, 5);
				WindowHandler
				.waitUntilWindowExistsTitleContains(driver, "Alert", 5);
				actualErrorMessage = eleErrorMsg.getText();
				TestReporter.assertTrue(
						actualErrorMessage.toLowerCase().contains(
								expectedErrorMessage.toLowerCase()),
								"The actual error message [" + actualErrorMessage
								+ "] does not contain the expected error message ["
								+ expectedErrorMessage + "].");
				btnAlertOk.jsClick(driver);
				WindowHandler.waitUntilNumberOfWindowsAre(driver, 1, 5);
				WindowHandler.waitUntilWindowExistsTitleContains(driver,
						"Disney Reservation Entry and Management System");
			}
		}
	}

	public void validateErrorMessage(String scenario, String vtPage){
		datatable.setVirtualtablePage(vtPage);
		datatable.setVirtualtableScenario(scenario);

		String expectedErrorMessage = datatable.getDataParameter("ErrorMessage");

		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2, 5);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Alert", 5);
		String actualErrorMessage = eleErrorMsg.getText();
		TestReporter.assertTrue(
				actualErrorMessage.toLowerCase().contains(
						expectedErrorMessage.toLowerCase()),
						"The actual error message [" + actualErrorMessage
						+ "] does not contain the expected error message ["
						+ expectedErrorMessage + "].");
		btnAlertOk.jsClick(driver);
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1, 5);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Disney Reservation Entry and Management System");
	}

	*//**
	 * 
	 * @summary Method to enter accommodation data
	 * @version Created 09/10/2014
	 * @author Waightstill W Avery
	 * @param scenario
	 *            - key for choosing which accommodations to choose
	 * @throws Exception
	 *             surrounding datatable access
	 * @return NA
	 * 
	 *//*
	public void enterAccommodations(String scenario) {
		datatable.setVirtualtablePage("DiscoveryAccommodations");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded();

		String resortCriteria = datatable.getDataParameter("ResortCriteria");
		String resortType = datatable.getDataParameter("ResortType");
		String resort = datatable.getDataParameter("Resort");
		String roomType = datatable.getDataParameter("RoomType");
		String numberOfRooms = datatable.getDataParameter("NumberRooms");
		String ada = datatable.getDataParameter("ADA");
		System.out.println("ada" + ada);

		// Determine if an ADA room is required
		if (ada.equalsIgnoreCase("TRUE")) {
			chkADA.check();
		}

		// Select resort criteria
		if (!txtGroupCode.getAttribute("value").toString().equalsIgnoreCase("")) {
			// radResortSelectCriteria.selectByValue(resortCriteria);
			List<WebElement> elementList = driver.findElements(By
					.name("ResortSelCriteria"));
			Iterator<WebElement> iterator = elementList.iterator();
			while (iterator.hasNext()) {
				Element element = new ElementImpl(iterator.next());
				// element.highlight(driver);
				if (element.getAttribute("value").equalsIgnoreCase(
						resortCriteria)) {
					element.click();
					do {
						Sleeper.sleep(3000);
						element.click();
						TestReporter.assertNotEquals(
								loopCount++,
								timeout,
								"The loading image is still displayed after selecting the Resort Criteria "
										+ resortCriteria
										+ " and after a duration of "
										+ String.valueOf(timeout) + " seconds.");
					} while (eleAccommodationLoadingImage.getAttribute("style")
							.equalsIgnoreCase(""));
				}
			}
			pageLoaded(lstResortType);
			// initialize();
		}

		// Select resort type
		loopCount = 0;
		do {
			lstResortType.select(resortType);
			lstResortType.sendKeys(Keys.TAB);

			TestReporter.assertNotEquals(
					loopCount++,
					timeout,
					"The loading image is still displayed after selecting the Resort Type "
							+ resortType + " and after a duration of "
							+ String.valueOf(timeout / 2) + " seconds.");
		} while (eleAccommodationLoadingImage.getAttribute("style")
				.equalsIgnoreCase(""));
		pageLoaded(lstResortList);

		lstResortList.syncTextInElement(driver, resort);
		// Select resort
		loopCount = 0;
//		int length = 0;
//		boolean isEmpty = true;
		do {
			lstResortList.deselectAll();
			lstResortList.sendKeys(resort);
			lstResortList.sendKeys(Keys.TAB);
			txtNumberOfRooms.jsClick(driver);
			Sleeper.sleep(500);
			new PageLoaded().isDomComplete(driver);
			try{
				pageLoaded(lstRoomTypeList);
			}catch(Exception e){
				Sleeper.sleep(2000);
				try{
					new PageLoaded().isDomComplete(driver);
					pageLoaded(lstRoomTypeList);
				}catch(Exception e2){

				}
			}
			TestReporter.assertNotEquals(loopCount++, timeout,
					"The loading image is still displayed after selecting the Resort " + resort
					+ " and after a duration of " + String.valueOf(timeout / 2) + " seconds.");
			pageLoaded(lstRoomTypeList);
			TestReporter.assertNotEquals(
					loopCount++,
					timeout,
					"The loading image is still displayed after selecting the Resort "
							+ resort + " and after a duration of "
							+ String.valueOf(timeout / 2) + " seconds.");
		} while (lstRoomTypeList.getText().length() == 0
				&& eleAccommodationLoadingImage.getAttribute("style")
				.equalsIgnoreCase(""));
		
		 * ======= // Grab the values used to determine if the room types are
		 * loaded. // They assignement statements are placed in a try-catch to
		 * avoid // unhandled exceptions. try{ length =
		 * lstRoomTypeList.getText().length(); isEmpty =
		 * eleAccommodationLoadingImage
		 * .getAttribute("style").equalsIgnoreCase(""); }catch(Exception e){
		 * initialize(); } } while (length == 0 && isEmpty);
		 * 
		 * >>>>>>> branch 'dreams' of
		 * https://github.disney.com/WDPRO-QA/lilo.git // Select room type //
		 * pageLoaded(lstRoomTypeList); int count = 0; boolean notPopulated =
		 * true; do{ try{ selectRoomType(roomType); notPopulated = false;
		 * }catch(RuntimeException e){ count++; lstResortList.deselectAll();
		 * lstResortList.sendKeys(resort); lstResortList.sendKeys(Keys.TAB);
		 * Sleeper.sleep(500); } }while(notPopulated && count < 5); if(count ==
		 * 5) throw new
		 * RuntimeException("The room type list was not populated after 5 tries."
		 * );
		 * 
		 * new PageLoaded().isDomComplete(driver);
		 * //lstResortAndRoomTypes.syncTextInElement(driver, resort); // Enter
		 * the number of rooms txtNumberOfRooms.set(numberOfRooms); }
		 

		// Select room type
		// pageLoaded(lstRoomTypeList);
		int count = 0;
		boolean notPopulated = true;
		do {
			try {
				selectRoomType(roomType);
				notPopulated = false;
			} catch (RuntimeException e) {
				count++;
				lstResortList.deselectAll();
				lstResortList.sendKeys(resort);
				lstResortList.sendKeys(Keys.TAB);
				Sleeper.sleep(500);
			}
		} while (notPopulated && count < 5);
		if (count == 5)
			throw new RuntimeException(
					"The room type list was not populated after 5 tries.");
		new PageLoaded().isDomComplete(driver);
		// lstResortAndRoomTypes.syncTextInElement(driver, resort);
		// Enter the number of rooms
		txtNumberOfRooms.set(numberOfRooms);
	}

	*//**
	 * 
	 * @summary Method to enter accommodation data
	 * @version Created 09/10/2014
	 * @author Waightstill W Avery
	 * @param scenario
	 *            - key for choosing which accommodations to choose
	 * @throws Exception
	 *             surrounding datatable access
	 * @return NA
	 * 
	 *//*
	public void enterAccommodations_Negative(String scenario) {
		datatable.setVirtualtablePage("DiscoveryAccommodations");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded();

		String resortCriteria = datatable.getDataParameter("ResortCriteria");
		String resortType = datatable.getDataParameter("ResortType");
		String resort = datatable.getDataParameter("Resort");
		String roomType = datatable.getDataParameter("RoomType");
		String numberOfRooms = datatable.getDataParameter("NumberRooms");

		if (!resort.isEmpty()) {
			// Select resort criteria
			if (!txtGroupCode.getAttribute("value").toString()
					.equalsIgnoreCase("")) {
				List<WebElement> elementList = driver.findElements(By
						.name("ResortSelCriteria"));
				Iterator<WebElement> iterator = elementList.iterator();
				while (iterator.hasNext()) {
					Element element = new ElementImpl(iterator.next());
					// element.highlight(driver);
					if (element.getAttribute("value").equalsIgnoreCase(
							resortCriteria)) {
						element.click();
						do {
							Sleeper.sleep(3000);
							element.click();
							TestReporter.assertNotEquals(loopCount++, timeout,
									"The loading image is still displayed after selecting the Resort Criteria "
											+ resortCriteria
											+ " and after a duration of "
											+ String.valueOf(timeout)
											+ " seconds.");
						} while (eleAccommodationLoadingImage.getAttribute(
								"style").equalsIgnoreCase(""));
					}
				}
				pageLoaded(lstResortType);
			}

			// Select resort type
			loopCount = 0;
			do {
				lstResortType.select(resortType);
				lstResortType.sendKeys(Keys.TAB);

				TestReporter.assertNotEquals(loopCount++, timeout,
						"The loading image is still displayed after selecting the Resort Type "
								+ resortType + " and after a duration of "
								+ String.valueOf(timeout / 2) + " seconds.");
			} while (eleAccommodationLoadingImage.getAttribute("style")
					.equalsIgnoreCase(""));
			pageLoaded(lstResortList);

			lstResortList.syncTextInElement(driver, resort);
			// Select resort
			loopCount = 0;
			do {
				lstResortList.deselectAll();
				lstResortList.sendKeys(resort);
				lstResortList.sendKeys(Keys.TAB);
				txtNumberOfRooms.jsClick(driver);
				Sleeper.sleep(500);
				new PageLoaded().isDomComplete(driver);
				pageLoaded(lstRoomTypeList);
				TestReporter.assertNotEquals(
						loopCount++,
						timeout,
						"The loading image is still displayed after selecting the Resort "
								+ resort + " and after a duration of "
								+ String.valueOf(timeout / 2) + " seconds.");
			} while (lstRoomTypeList.getText().length() == 0
					&& eleAccommodationLoadingImage.getAttribute("style")
					.equalsIgnoreCase(""));

			int count = 0;
			boolean notPopulated = true;
			do {
				try {
					selectRoomType(roomType);
					notPopulated = false;
				} catch (RuntimeException e) {
					count++;
					lstResortList.deselectAll();
					lstResortList.sendKeys(resort);
					lstResortList.sendKeys(Keys.TAB);
					Sleeper.sleep(500);
				}
			} while (notPopulated && count < 5);
			if (count == 5)
				throw new RuntimeException(
						"The room type list was not populated after 5 tries.");

			new PageLoaded().isDomComplete(driver);
		}

		// Enter the number of rooms
		enterNumberOfRooms(numberOfRooms);
	}

	public void enterNumberOfRooms(String rooms){
		// As this method can be used in various situations, adding method calls
		// to ensure the correct page is loaded
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1, 5);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Disney Reservation Entry and Management System");
		new PageLoaded().isDomComplete(driver);

		pageLoaded(txtNumberOfRooms);
		txtNumberOfRooms.syncVisible(driver);
		txtNumberOfRooms.highlight(driver);
		if (rooms.equalsIgnoreCase("{BLANK}")) {
			txtNumberOfRooms.click();
			txtNumberOfRooms.sendKeys(Keys.CONTROL + "a");
			txtNumberOfRooms.sendKeys(Keys.DELETE);
			txtNumberOfRooms.sendKeys(Keys.TAB);
		} else {
			txtNumberOfRooms.safeSet(rooms);
		}
	}

	public void enterNumberOfRooms(int rooms) {
		enterNumberOfRooms(String.valueOf(rooms));
	}

	*//**
	 * 
	 * @summary Method to enter ackage code
	 * @version Created 09/10/2014
	 * @author Waightstill W Avery
	 * @param scenario
	 *            - key for choosing which package code to enter and select
	 * @throws Exception
	 *             surrounding datatable access
	 * @return NA
	 * 
	 *//*
	@Deprecated
	public void enterPackageCodeStatic(String pkgCode) {
		pageLoaded(txtPackageCode);
		txtPackageCode.set(pkgCode);
		btnAddPackageCode.jsClick(driver);
		initialize();
		clickAddAndSync();
	}

	public void enterPackageCode(String scenario, String daysOut,
			String resortArea, String packageType, String packageBillCode,
			String resortCode, String roomCode, String packageDescription)
	{
		datatable.setVirtualtablePage("DiscoveryPackages");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded(txtPackageCode);
		// DSJ - Added functionality to pull package code from the virtual table
		// set up for theis scenario.
		// WWA - Returning package code retrieval to database query
		// packageCode = datatable.getDataParameter("PackageCode");

		// System.out.println("Package Code - " + packageCode);

		// DJS - the following code has been commented out as we are using the
		// Virtual Table to house the package code.
		// WWA - Returning package code retrieval to database query
		PackageCodes pkg = new PackageCodes();
		packageCode = pkg.retrievePackageCode(environment, daysOut, resortArea,
				packageType, packageBillCode, resortCode, roomCode,
				packageDescription);

		// Enter the package code
		txtPackageCode.safeSetValidate(driver, packageCode);
		btnAddPackageCode.jsClick(driver);
		clickAddAndSync();
	}

	private void clickAddAndSync() {
		boolean packageFound = false;
		int counter = 0;
		int maxTries = 20;
		do {
			Sleeper.sleep(500);
			counter++;
			try {
				String contents = lstPackageCodeDescriptions.getText();
				if (!contents.isEmpty()) {
					TestReporter
					.log("Packge added successfully.  Package description is ["
							+ contents + "].");
					packageFound = true;
				}
			} catch (Exception e) {
				initialize();
			}
		} while (!packageFound && counter < maxTries);
		TestReporter.assertTrue(counter < maxTries,
				"No package code was added.");
	}

	*//**
	 * 
	 * @summary Method to enter ages of children
	 * @version Created 09/10/2014
	 * @author Waightstill W Avery
	 * @param scenario
	 *            - key for choosing which set of children's ages to enter
	 * @throws Exception
	 *             surrounding datatable access
	 * @return NA
	 * 
	 *//*
	private void enterChildAges() {

	}

	*//**
	 * 
	 * @summary Method to enter choose a room type. The parameter passed can be
	 *          any value that is found in name of the desired room type. For
	 *          example, "Example Room - (AB)" can be selected by passing either
	 *          the room type name "Example Room" or room type code "AB".
	 * @version Created 09/10/2014
	 * @author Waightstill W Avery
	 * @param roomType
	 *            - key for determining which room type to select
	 * @throws Exception
	 *             surrounding datatable access
	 * @return NA
	 * 
	 *//*
	private void selectRoomType(String roomType) throws RuntimeException {
		lstRoomTypeList.syncTextInElement(driver, roomType, 5, true);
		lstRoomTypeList.select(roomType);
		lstResortList.sendKeys(Keys.TAB);
		// List<WebElement> roomTypes = lstRoomTypeList.getOptions();
		// Iterator<WebElement> iterator = roomTypes.iterator();
		// WebElement nextValue;
		// String roomList = "";
		// boolean found = false;
		// while (iterator.hasNext() && !found) {
		// nextValue = iterator.next();
		// if (nextValue.getText().contains(roomType)) {
		// loopCount = 0;
		// do {
		// lstRoomTypeList.select(nextValue.getText());
		// Sleeper.sleep(500);
		// TestReporter.assertNotEquals(loopCount++, timeout,
		// "The loading image is still displayed after selecting the Room Type "
		// + roomType
		// + " and after a duration of " + String.valueOf(timeout / 2)
		// + " seconds. A list of available room types are " + "as follows: \n"
		// + roomList);
		// } while (lstResortAndRoomTypes.getOptions().size() < 1);
		// // lstRoomTypeList.select(nextValue.getText());
		// found = true;
		// } else {
		// if (roomList.equalsIgnoreCase("")) {
		// roomList = nextValue.getText() + "\n";
		// } else {
		// roomList = roomList + nextValue.getText() + "\n";
		// }
		// }
		// }
		// TestReporter.assertEquals(found, true, "Room type " + roomType +
		// "was not found in the list. "
		// + "The following are option found in the list: \n" + roomList);
	}

	public void addPrimaryGuest(String scenario) {
		clickNewPrimaryGuest();

		// WindowHandler.waitUntilWindowExistsTitleContains(driver,
		// "Guest Search");

		GuestSearch guestSearch = new GuestSearch(driver);
		guestSearch.enterSearchCriteria(scenario);
		guestSearch.clickNew();

		GuestPartyWindow guestParty = new GuestPartyWindow(driver);
		TestReporter.assertTrue(guestParty.pageLoaded(),
				"The Guest Party Window was not loaded.");
		guestParty.enterPrimaryGuestInfo(scenario);
	}

	public String returnDate() {

		return arrivalDate_reruntoFunstion;
	}

	public String getGuestName() {
		return guestLastFirstName;
	}

	*//**
	 * @summary Method to handle and Validate if an invalid Group Number is
	 *          passed
	 * @version Created 02/11/2016
	 * @author Marella Satish
	 * @param Datatable
	 *            scenario
	 * @throws NA
	 * @return NA
	 *//*
	public void validateGroupCodeAlert(String scenario) {

		datatable.setVirtualtablePage("ErrorMessages");
		datatable.setVirtualtableScenario(scenario);

		String expectedError = datatable.getDataParameter("Message");
		String getAlertMessage = eleErrorMsg.getText();

		TestReporter.log("Alert Message : " + getAlertMessage);
		TestReporter.assertEquals(expectedError, getAlertMessage,
				"The expected message[ " + expectedError
				+ " ] is not same as actual message[ "
				+ getAlertMessage + " ]");
		btnAlertOk.highlight(driver);
		btnAlertOk.click();
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Disney Reservation Entry and Management System");
	}

	*//**
	 * @summary Method to avoid staleness on a text box
	 * @version Created 02/11/2016
	 * @author Stagliano, Dennis
	 * @param Textbox
	 *            Object and the String Value
	 * @throws Try
	 *             /Catch Exception
	 * @return NA
	 *//*
	@SuppressWarnings("unused")
	private void selectAvoidingStaleness(Textbox textbox, String value) {
		if (!value.isEmpty()) {
			int counter = 0;
			boolean success = false;
			int maxTries = 10;
			do {
				try {
					Sleeper.sleep(1000);
					counter++;
					textbox.safeSet(value);
					success = true;
				} catch (Exception e) {
					pageLoaded(textbox);
				}
			} while (!success && counter < maxTries);
		}
	}

	*//**
	 * @summary Method to verify default Number of Adults is 1
	 * @version Created 02/11/2016
	 * @author Stagliano, Dennis
	 * @param NA
	 * @throws Number
	 *             Format Exception
	 * @return Number of Adults as an integer
	 *//*
	public int verifyNumOfAdults() {
		pageLoaded(txtNumberOfAdults);
		String verifyAdultNumber = txtNumberOfAdults.getText();
		Integer adults = null;
		try {
			adults = Integer.valueOf(verifyAdultNumber);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adults;

	}

	*//**
	 * 
	 * @summary Simple public method to enter the RoomType
	 * @version Created 02/15/2016
	 * @author Stagliano, Dennis
	 * @param roomType
	 *            Name
	 * @throws NA
	 * @return NA
	 * 
	 *//*
	public void enterRoomType(String roomType) {
		pageLoaded(lstRoomTypeList);
		lstRoomTypeList.select(roomType);
		lstResortList.sendKeys(Keys.TAB);
	}

	*//**
	 * 
	 * @summary method to verify Number Adults
	 * @version Created 02/22/2016
	 * @author Lalitha Banda
	 * @param Datatable
	 *            scenario
	 * @throws NA
	 * @return NA
	 * 
	 *//*
	public void verifyNoAdults(String scenario) {
		datatable.setVirtualtableScenario(scenario);
		pageLoaded(txtNumberOfAdults);
		String numberOfAdults = datatable.getDataParameter("NumberAdults");

		String NumberAdults = verifyNumOfAdults() + "";
		TestReporter.assertTrue(NumberAdults.equalsIgnoreCase(numberOfAdults),
				"Number adults does not matched with " + NumberAdults + ":"
						+ numberOfAdults);
	}

	*//**
	 * 
	 * @summary method to verify Number Children
	 * @version Created 03/11/2016
	 * @author Sunitha Bachala
	 * @param Datatable
	 *            scenario
	 * @throws NA
	 * @return NA
	 * 
	 *//*

	public void verifyNoChidren(String scenario) {
		datatable.setVirtualtableScenario(scenario);
		pageLoaded(txtNumberOfChildren);
		String numberOfChildren = datatable.getDataParameter("NumberChildren");

		String NumberChildren = verifyNumOfAdults() + "";
		TestReporter.assertTrue(
				NumberChildren.equalsIgnoreCase(numberOfChildren),
				"Number Children does not matched with " + NumberChildren + ":"
						+ numberOfChildren);
	}

	*//**
	 * 
	 * @summary method to clickNewTravelAgency
	 * @version Created on 03/01/2016
	 * @author Chinagudaba Pawan
	 * @param Datatable
	 *            scenario
	 * @throws NA
	 * @return NA
	 * 
	 *//*
	public void clickNewTravelAgency(String scenario) {

		datatable.setVirtualtablePage("OrganizationSearch");
		datatable.setVirtualtableScenario(scenario);

		String agencyId = datatable.getDataParameter("AgencyId");

		pageLoaded(btnNewTravelAgency);
		btnNewTravelAgency.highlight(driver);
		btnNewTravelAgency.click();

		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Organization Search");
		pageLoaded(txtAgentID);
		txtAgentID.highlight(driver);
		txtAgentID.safeSet(agencyId);
		btnAgencySearch.jsClick(driver);

		PleaseWait.WaitForPleaseWait(driver);
		List<WebElement> elements = driver.findElements(By.tagName("a"));

		for (WebElement element : elements) {
			Element e = new ElementImpl(element);
			if (e.syncVisible(driver, 1, false)) {
				e.jsClick(driver);
				break;
			}

		}

		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Disney Reservation");

		pageLoaded(lnkTravelAgency);
		lnkTravelAgency.highlight(driver);
		lnkTravelAgency.jsClick(driver);
	}

	public void setBlockPackage() {
		
		 * datatable.setVirtualtablePage("DiscoveryPackages");
		 * datatable.setVirtualtableScenario(packageCode);
		 * 
		 * String PackageCode = datatable.getDataParameter("PackageCode");
		 
		pageLoaded(lstBlockPackage);
		lstBlockPackage.focus(driver);
		lstBlockPackage.highlight(driver);
		lstBlockPackage.sendKeys(Keys.TAB);
		lstBlockPackage.findElement(By.tagName("OPTION")).click();
	}

	// Select Room and Resort type
	public void deleteSelectedResort(String scenario){


		datatable.setVirtualtablePage("DiscoveryAccommodations");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded();

		String resort = datatable.getDataParameter("Resort");
		String roomType = datatable.getDataParameter("RoomType");
		String actualResort = resort+"-"+roomType;
		String selectedResort = lstResortAndRoomTypes.getAttribute("value");

		pageLoaded(lstResortAndRoomTypes);
		if(actualResort.equals(selectedResort)){
			pageLoaded(lstResortAndRoomTypes);
			lstResortAndRoomTypes.sendKeys(Keys.chord(Keys.ARROW_UP));
		}else {
			lstResortAndRoomTypes.select(selectedResort);
			pageLoaded(lstResortAndRoomTypes);
			lstResortAndRoomTypes.sendKeys(Keys.chord(Keys.ARROW_UP));
		}

		lnkDelete.click();	

	}

	*//**
	 * @summary Method to enter Group Number
	 * @version Created 04/06/2016
	 * @author Marella Satish
	 * @param environment,groupType (RSR,Convention)
	 * @throws NA
	 * @return NA
	 *//*
	public void enterGroupNumber(String environment, String groupType){
	
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Disney Reservation");
		new PageLoaded().isDomComplete(driver);
		FrameHandler.findAndSwitchToFrame(driver, "contentFrame");
		
		GroupNumbers group = new GroupNumbers(environment, groupType);
		String groupNumber = group.getGroupNumber();
		txtGroupCode.syncVisible(driver);
		txtGroupCode.safeSet(groupNumber);
		
		boolean groupNameEmpty = false;
		int counter = 0;
		do{
			try{
			//	groupNameEmpty = txtGroupNameCode.getText().isEmpty();
				groupNameEmpty = lstBlockPackage.getFirstSelectedOption().getText().isEmpty();
			}catch(Exception e){
				Sleeper.sleep(1000);
				counter++;
			}
		}while(counter < Constants.ELEMENT_TIMEOUT && groupNameEmpty);
	}
	
	*//**
	 * @summary Method to enter accommodation data for group reservations
	 * @version Created 04/06/2016
	 * @author Marella Satish
	 * @param datatable scenario, environment,groupType (RSR,Convention)
	 * @throws Exception
	 *             surrounding datatable access
	 * @return NA
	 *//*
	public void enterAccommodations_Group(String scenario,String environment,String groupType) {
		datatable.setVirtualtablePage("DiscoveryAccommodations");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded();

		String resortCriteria = datatable.getDataParameter("ResortCriteria");
		String resortType = datatable.getDataParameter("ResortType");
		String numberOfRooms = datatable.getDataParameter("NumberRooms");
		String ada = datatable.getDataParameter("ADA");
		
		GroupNumbers group = new GroupNumbers(environment, groupType);
		String resort = group.getResortName();
		String roomType = group.getRoomType();
		
		// Determine if an ADA room is required
		if (ada.equalsIgnoreCase("TRUE")) {
			chkADA.check();
		}
		// Select resort criteria
		if (!txtGroupCode.getAttribute("value").toString().equalsIgnoreCase("")) {
			// radResortSelectCriteria.selectByValue(resortCriteria);
			List<WebElement> elementList = driver.findElements(By
					.name("ResortSelCriteria"));
			Iterator<WebElement> iterator = elementList.iterator();
			while (iterator.hasNext()) {
				Element element = new ElementImpl(iterator.next());
				// element.highlight(driver);
				if (element.getAttribute("value").equalsIgnoreCase(
						resortCriteria)) {
					element.click();
					do {
						Sleeper.sleep(3000);
						element.click();
						TestReporter.assertNotEquals(
								loopCount++,
								timeout,
								"The loading image is still displayed after selecting the Resort Criteria "
										+ resortCriteria
										+ " and after a duration of "
										+ String.valueOf(timeout) + " seconds.");
					} while (eleAccommodationLoadingImage.getAttribute("style")
							.equalsIgnoreCase(""));
				}
			}
			pageLoaded(lstResortType);
			// initialize();
		}

		// Select resort type
		loopCount = 0;
		do {
			lstResortType.select(resortType);
			lstResortType.sendKeys(Keys.TAB);

			TestReporter.assertNotEquals(
					loopCount++,
					timeout,
					"The loading image is still displayed after selecting the Resort Type "
							+ resortType + " and after a duration of "
							+ String.valueOf(timeout / 2) + " seconds.");
		} while (eleAccommodationLoadingImage.getAttribute("style")
				.equalsIgnoreCase(""));
		pageLoaded(lstResortList);

		lstResortList.syncTextInElement(driver, resort);
		// Select resort
		loopCount = 0;
//		int length = 0;
//		boolean isEmpty = true;
		do {
			lstResortList.deselectAll();
			lstResortList.sendKeys(resort);
			lstResortList.sendKeys(Keys.TAB);
			txtNumberOfRooms.jsClick(driver);
			Sleeper.sleep(500);
			new PageLoaded().isDomComplete(driver);
			try{
				pageLoaded(lstRoomTypeList);
			}catch(Exception e){
				Sleeper.sleep(2000);
				try{
					new PageLoaded().isDomComplete(driver);
					pageLoaded(lstRoomTypeList);
				}catch(Exception e2){

				}
			}
			TestReporter.assertNotEquals(loopCount++, timeout,
					"The loading image is still displayed after selecting the Resort " + resort
					+ " and after a duration of " + String.valueOf(timeout / 2) + " seconds.");
			pageLoaded(lstRoomTypeList);
			TestReporter.assertNotEquals(
					loopCount++,
					timeout,
					"The loading image is still displayed after selecting the Resort "
							+ resort + " and after a duration of "
							+ String.valueOf(timeout / 2) + " seconds.");
		} while (lstRoomTypeList.getText().length() == 0
				&& eleAccommodationLoadingImage.getAttribute("style")
				.equalsIgnoreCase(""));
		
		int count = 0;
		boolean notPopulated = true;
		do {
			try {
				selectRoomType(roomType);
				notPopulated = false;
			} catch (RuntimeException e) {
				count++;
				lstResortList.deselectAll();
				lstResortList.sendKeys(resort);
				lstResortList.sendKeys(Keys.TAB);
				Sleeper.sleep(500);
			}
		} while (notPopulated && count < 5);
		if (count == 5)
			throw new RuntimeException(
					"The room type list was not populated after 5 tries.");
		new PageLoaded().isDomComplete(driver);
		txtNumberOfRooms.set(numberOfRooms);
	}
}


*/