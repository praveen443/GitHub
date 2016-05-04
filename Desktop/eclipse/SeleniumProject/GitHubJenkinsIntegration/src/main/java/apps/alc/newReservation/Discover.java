package apps.alc.newReservation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.print.attribute.standard.MediaSize.NA;

import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import apps.alc.pleaseWait.PleaseWait;
import core.AlertHandler;
import core.WebDriverSetup;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.RadioGroup;
import core.interfaces.Textbox;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.ListboxImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;
import utils.date.DateTimeConversion;

/**
 * 
 * @summary Contains the page methods & objects for the Discovery page during
 *          the new reservation process
 * @version Created 10/01/2014
 * @author Waightstill W Avery
 */
public class Discover {
	// ****************************
	// *** Discover Page Fields ***
	// ****************************
	private WebDriver driver;
	private int loopCounter;
	private Element obj;
	private Datatable datatable = new Datatable();

	// ******************************
	// ** Discover Page Elements ***
	// ******************************

	// Table Search Get Offer Set
	@FindBy(id = "guestPreferencesForm:tseGetOfferSet")
	private Button btnTableSearchGetOfferSet;

	// Get Offer Set button for the remaining even types
	@FindBy(id = "guestPreferencesForm:getOfferSet")
	private Button btnGetOfferSet;

	// button reset
	@FindBy(id = "guestPreferencesForm:tseReset")
	private Element btnReset;

	// Table Search Date/Time webtable
	@FindBy(id = "guestPreferencesForm:tseDateTimeRichPanel_body")
	private Element eleDateTime;

	/*
	 * Event type radio buttons
	 */
	// Table Search radiobutton
	@FindBy(id = "guestPreferencesForm:functionalAreaRadioButton:0")
	private RadioGroup radTableSearch;

	// Table Service radiobutton
	@FindBy(id = "guestPreferencesForm:functionalAreaRadioButton:1")
	private RadioGroup radTableService;

	// Dinner Shows radiobutton
	@FindBy(id = "guestPreferencesForm:functionalAreaRadioButton:2")
	private RadioGroup radDinnerShow;

	// Recreation radiobutton
	@FindBy(id = "guestPreferencesForm:functionalAreaRadioButton:3")
	private RadioGroup radRecreation;

	// Children's Activities radiobutton
	@FindBy(id = "guestPreferencesForm:functionalAreaRadioButton:4")
	private RadioGroup radChildrenActivities;

	// Theatre/Stadium Event radiobutton
	@FindBy(id = "guestPreferencesForm:functionalAreaRadioButton:5")
	private RadioGroup radTheaterStadium;

	// Discover Selection radio group
	@FindBy(name = "guestPreferencesForm:functionalAreaRadioButton")
	private List<Element> radDiscoverSelection;

	/*
	 * Table Search Location elements
	 */
	// Location listbox
	@FindBy(id = "guestPreferencesForm:tseLocationId")
	private Listbox lstLocation;

	// Restaurant Name textbox
	@FindBy(id = "guestPreferencesForm:tseFacility")
	private Textbox txtRestaurantName;

	// Cuisine/Experience listbox
	@FindBy(id = "guestPreferencesForm:tseCuisineOrExperience")
	private Listbox lstCuisineExperience;

	// Disney Dining Plan checkbox
	@FindBy(id = "guestPreferencesForm:tseDiningPlan")
	private Checkbox chkDiningPlan;

	/*
	 * Table Service Location elements
	 */
	// Facility listbox
	@FindBy(id = "guestPreferencesForm:captureGuestPreferencetool")
	private Listbox lstFacility;

	// Area listbox - used by Recreation as well
	@FindBy(id = "guestPreferencesForm:guestPrfContAreaNotForTheatre")
	private Listbox lstArea;

	// Service Segment listbox
	@FindBy(id = "guestPreferencesForm:captureGuestPreferenceServiceSegment")
	private Listbox lstServiceSegment;

	// Cuisine listbox
	@FindBy(id = "guestPreferencesForm:guestPrfContCuisineTableService")
	private Listbox lstCuisine;

	// Product listbox - used by Dinner Shows as well
	@FindBy(id = "guestPreferencesForm:venueoptions2")
	private Listbox lstProduct;

	// Event listbox
	@FindBy(id = "guestPreferencesForm:captureGuestPreferenceEvent2")
	private Listbox lstEvent;

	// Service Style listbox
	@FindBy(id = "guestPreferencesForm:guestPrfContServiceStyleTableService")
	private Listbox lstServiceStyle;

	/*
	 * Dinner Show Location elements
	 */
	// Facility listbox
	@FindBy(id = "guestPreferencesForm:captureGuestPreferencetool1")
	private Listbox lstDSFacility;

	// Area listbox
	@FindBy(id = "guestPreferencesForm:venueoptions1")
	private Listbox lstDSProduct;

	/*
	 * Recreation Location elements
	 */
	// Activity Name listbox - used by Children's Activities as well
	@FindBy(id = "guestPreferencesForm:guestPrfContActivityNameRC2")
	private Listbox lstActivityName;

	// Activity Type listbox
	@FindBy(id = "guestPreferencesForm:guestPrfContForActivityTypeRC")
	private Listbox lstActivityType;

	/*
	 * Cirque Location elements
	 */
	// Cirque Facility listbox
	@FindBy(id = "guestPreferencesForm:guestPrfContLocationForTheatre")
	private Listbox lstCirqueFacility;

	// Cirque Product listbox
	@FindBy(id = "guestPreferencesForm:guestPrfContCategoryTheatre")
	private Listbox lstCirqueProduct;

	/*
	 * Date and time
	 */
	// Table Search Date textbox
	@FindBy(id = "guestPreferencesForm:tseReservationDate")
	private Textbox txtDate;

	// Table Search Time listbox
	@FindBy(id = "guestPreferencesForm:tseReservationTime")
	private Listbox lstTime;

	// Table Search Time AM/PM listbox
	@FindBy(id = "guestPreferencesForm:tseReservationTimeAmPm")
	private Listbox lstAmPm;

	// Date From textbox - used by Dinner Shows as well
	@FindBy(id = "guestPreferencesForm:guestPreferencesFromDateInput11")
	private Textbox txtDateFrom;

	// Date To textbox - used by Dinner Shows as well
	@FindBy(id = "guestPreferencesForm:guestPreferencesToDateInput11")
	private Textbox txtDateTo;

	// Preferred Time listbox
	@FindBy(id = "guestPreferencesForm:timePreferred")
	private Listbox lstPreferred;

	// Preferred time period listbox
	@FindBy(id = "guestPreferencesForm:timePreferredAmPm")
	private Listbox lstPreferredAmPm;

	// Earliest Time listbox
	@FindBy(id = "guestPreferencesForm:timeEarliest")
	private Listbox lstEarliest;

	// Earliest Time period listbox
	@FindBy(id = "guestPreferencesForm:timeEarliestAmPm")
	private Listbox lstEarliestAmPm;

	// Latest Time listbox
	@FindBy(id = "guestPreferencesForm:timeLatest")
	private Listbox lstLatest;

	// Latest Time period listbox
	@FindBy(id = "guestPreferencesForm:timeLatestAmPm")
	private Listbox lstLatestAmPm;

	// Recreation Date From textbox
	@FindBy(id = "guestPreferencesForm:guestPreferencesFromDateInput")
	private Textbox txtRecDateFrom;

	// Recreation Date To textbox
	@FindBy(id = "guestPreferencesForm:guestPreferencesToDateInput")
	private Textbox txtRecDateTo;

	// Time From listbox
	@FindBy(id = "guestPreferencesForm:timeFrom")
	private Listbox lstTimeFrom;

	// Time To listbox
	@FindBy(id = "guestPreferencesForm:timeTo")
	private Listbox lstTimeTo;

	// Days listbox
	@FindBy(id = "guestPreferencesForm:daysSelectMenu")
	private Listbox lstDay;

	// Cirque Date textbox
	@FindBy(id = "guestPreferencesForm:guestPreferencesDateInput")
	private Textbox txtCirqueDate;

	// Cirque Time listbox
	@FindBy(id = "guestPreferencesForm:timeFromTheatre")
	private Listbox lstCirqueTime;

	@FindBy(id = "guestPreferencesForm:timeFromAmPm")
	private Listbox lstRecreationTimeFromAmPm;

	@FindBy(id = "guestPreferencesForm:timeToAmPm")
	private Listbox lstRecreationTimeToAmPm;

	/*
	 * Party mix
	 */
	// Number of Adults textbox
	@FindBy(id = "guestPreferencesForm:guestPreferencesNumberOfAdults")
	private Textbox txtNumberOfAdults;

	// Number of Children textbox
	@FindBy(id = "guestPreferencesForm:guestPreferencesNumberOfChild")
	private Textbox txtNumberOfChildren;

	// Child Age listbox for the first child age
	@FindBy(id = "guestPreferencesForm:guestPreferencesChildrenRepeat:0:selectOneMenuID")
	private Listbox lstFirstChildAge;

	// Table Service Party Size listbox
	@FindBy(xpath = "//div[@id='guestPreferencesForm:tseDateTimeRichPanel_body']/table/tbody/tr[2]/td[2]/select")
	// @FindBy(xpath = "//select[contains(@size, '1')]")
	private Listbox lstPartySize;

	/*
	 * Inventory requests
	 */
	// Inventory Override checkbox
	@FindBy(id = "guestPreferencesForm:saveOverRideID")
	private Checkbox chkInventoryOverride;

	// Inventory Override Reason listbox
	@FindBy(id = "guestPreferencesForm:overrideInventoryReasonOptions")
	private Listbox lstOverrideReason;

	/*
	 * // Vip checkbox
	 */

	// Vip checkbox
	@FindBy(xpath = "//div[@id = 'guestPreferencesForm:guestPreferencesSpecialRequestsPanel_body']/table/tbody/tr[2]/td/input")
	private Checkbox chkVIP;

	/*
	 * // Vip checkbox
	 */

	// Priority listbox
	@FindBy(id = "guestPreferencesForm:priorityID")
	private Listbox lstPriority;

	// Sign Language Interpreter checkbox
	@FindBy(id = "guestPreferencesForm:selectedSpecialRequestsID:0")
	private Checkbox chkInterpreter;

	// Wheelchair Accessibility checkbox
	@FindBy(id = "guestPreferencesForm:selectedSpecialRequestsID:1")
	private Checkbox chkWheelchair;
	
	// Error Panel Content Table element
	@FindBy(id = "facesErrorPanelContentTable")
	private Element eleErrorPanelContentTable;

	// *********************
	// ** Build page area **
	// *********************

	public Discover(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}

	private Discover initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnTableSearchGetOfferSet);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	// **********************************
	// *** Discover Page Interactions ***
	// **********************************

	/**
	 * 
	 * @summary Method to select and event type and invoke the method to enter
	 *          search criteria
	 * @version Created 11/21/2014
	 * @author Waightstill W Avery
	 * @param scenario
	 *            - string, used to determine datatable row
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void enterDiscoverCriteria(String scenario) {
		datatable.setVirtualtablePage("Discover");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded();

		boolean validEventType = false;

		String selection = datatable.getDataParameter("Selection");

		System.out.println(selection);

		// Determine the event search type
		switch (selection.toLowerCase()) {
		case "table search":
			obj = new ElementImpl(radDiscoverSelection.get(0));
			obj.jsClick(driver);
			PleaseWait.WaitForPleaseWait(driver);
			searchForTableSearch(scenario);
			break;
		case "table service":
			obj = new ElementImpl(radDiscoverSelection.get(1));
			obj.jsClick(driver);
			PleaseWait.WaitForPleaseWait(driver);
			searchForTableService(scenario);
			break;
		case "dinner shows":
		case "dinner show":
			obj = new ElementImpl(radDiscoverSelection.get(2));
			obj.jsClick(driver);
			PleaseWait.WaitForPleaseWait(driver);
			searchForDinnerShows(scenario);
			break;
		case "recreation":
			obj = new ElementImpl(radDiscoverSelection.get(3));
			obj.jsClick(driver);
			PleaseWait.WaitForPleaseWait(driver);
			searchForRecChild(scenario);
			break;
		case "children's activities":
			obj = new ElementImpl(radDiscoverSelection.get(4));
			obj.jsClick(driver);
			PleaseWait.WaitForPleaseWait(driver);
			searchForRecChild(scenario);
			break;
		case "theater/stadium event":
			obj = new ElementImpl(radDiscoverSelection.get(5));
			obj.jsClick(driver);
			PleaseWait.WaitForPleaseWait(driver);
			//searchForCirque(scenario);
			searchForCirqueShow(scenario);
			break;
		default:
			TestReporter.assertEquals(validEventType, true, "A valid event type must be entered");
			break;
		}
	}

	/**
	 * 
	 * @summary Method to enter search criteria for a table search
	 * @version Created 11/21/2014
	 * @author Waightstill W Avery
	 * @param scenario
	 *            - string, used to determine datatable row
	 * @throws NA
	 * @return NA
	 * 
	 */
	private void searchForTableSearch(String scenario) {
		datatable.setVirtualtablePage("Discover");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded();

		String location = datatable.getDataParameter("Location");
		String cuisineExperience = datatable.getDataParameter("CuisineExperience");
		String restaurantName = datatable.getDataParameter("RestaurantName");
		boolean disneyDiningPlan = Boolean.valueOf(datatable.getDataParameter("DisneyDiningPlan"));
		String date = datatable.getDataParameter("DaysOut");
		String time = datatable.getDataParameter("Preferred");
		String partySize = datatable.getDataParameter("PartySize");

		DateTimeConversion convertDate = new DateTimeConversion();
		date = convertDate.ConvertToDateMMDDYY(date);

		// Enter facility search criteria
		searchFacility(location, cuisineExperience, restaurantName, disneyDiningPlan);
		// Enter date and time search criteria
		dateTime(date, time);
		// Enter party mix search criteria
		partyMix(partySize);

		clickGetOfferSet(btnTableSearchGetOfferSet);
	}

	/**
	 * 
	 * @summary Method to enter search criteria for a table service
	 * @version Created 11/21/2014
	 * @author Waightstill W Avery
	 * @param scenario
	 *            - string, used to determine datatable row
	 * @throws NA
	 * @return NA
	 * 
	 */
	private void searchForTableService(String scenario) {
		datatable.setVirtualtablePage("Discover");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded(lstCuisine);
		TestReporter.log("serachFor);" + "TableService set virtualTable and page loaded.  Get databale parameters");
		String facility = datatable.getDataParameter("Facility");
		String area = datatable.getDataParameter("Area");
		String serviceSegment = datatable.getDataParameter("ServiceSegment");
		String cuisine = datatable.getDataParameter("Cuisine");
		String product = datatable.getDataParameter("Product");
		String event = datatable.getDataParameter("Event");
		String serviceStyle = datatable.getDataParameter("ServiceStyle");
		String daysOut = datatable.getDataParameter("DaysOut");
		String numberOfNights = datatable.getDataParameter("NumberOfNights");
		String preferred = datatable.getDataParameter("Preferred").replace("\"", "");
		System.out.println(preferred);
		String earliest = datatable.getDataParameter("Earliest").replace("\"", "");
		String latest = datatable.getDataParameter("Latest").replace("\"", "");
		String numberOfAdults = datatable.getDataParameter("NumberOfAdults");
		String numberOfChildren = datatable.getDataParameter("NumberOfChildren");
		String[] childAges = datatable.getDataParameter("ChildrenAges").split(";");
		String overrideInventory = datatable.getDataParameter("InventoryOverride");
		String overrideReason = datatable.getDataParameter("OverrideReason");
		String vip = datatable.getDataParameter("VIP");
		String priority = datatable.getDataParameter("Priority");
		int span = Integer.parseInt(daysOut) + Integer.parseInt(numberOfNights);

		DateTimeConversion convertDate = new DateTimeConversion();
		String dateFrom = convertDate.ConvertToDateMMDDYY(daysOut);
		String dateTo = convertDate.ConvertToDateMMDDYY(String.valueOf(span));

		TestReporter.log("TableServcie search for facility;");
		searchFacility(facility, area, serviceSegment, cuisine, product, event, serviceStyle);
		TestReporter.log("TableServcie, just searched for facility, now set date times;");
		dateTime(dateFrom, dateTo, preferred, earliest, latest);
		partyMix(numberOfAdults, numberOfChildren, childAges);
		inventory(overrideInventory, overrideReason, vip, priority);

		clickGetOfferSet(btnGetOfferSet);
	}

	/**
	 * 
	 * @summary Method to enter search criteria for a dinner show
	 * @version Created 11/21/2014
	 * @author Waightstill W Avery
	 * @param scenario
	 *            - string, used to determine datatable row
	 * @throws NA
	 * @return NA
	 * 
	 */
	private void searchForDinnerShows(String scenario) {
		datatable.setVirtualtablePage("Discover");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded(lstDSProduct);

		String facility = datatable.getDataParameter("Facility");
		String area = datatable.getDataParameter("Area");
		String product = datatable.getDataParameter("Product");
		String daysOut = datatable.getDataParameter("DaysOut");
		String numberOfNights = datatable.getDataParameter("NumberOfNights");
		String preferred = datatable.getDataParameter("Preferred").replace("\"", "");
		String earliest = datatable.getDataParameter("Earliest").replace("\"", "");
		String latest = datatable.getDataParameter("Latest").replace("\"", "");
		String numberOfAdults = datatable.getDataParameter("NumberOfAdults");
		String numberOfChildren = datatable.getDataParameter("NumberOfChildren");
		String[] childAges = datatable.getDataParameter("ChildrenAges").split(";");
		String overrideInventory = datatable.getDataParameter("InventoryOverride");
		String overrideReason = datatable.getDataParameter("OverrideReason");
		String vip = datatable.getDataParameter("VIP");
		String priority = datatable.getDataParameter("Priority");
		boolean interpreter = Boolean.valueOf(datatable.getDataParameter("interpreter"));
		boolean wheelchair = Boolean.valueOf(datatable.getDataParameter("Priority"));
		int span = Integer.parseInt(daysOut) + Integer.parseInt(numberOfNights);

		DateTimeConversion convertDate = new DateTimeConversion();
		String dateFrom = convertDate.ConvertToDateMMDDYY(daysOut);
		String dateTo = convertDate.ConvertToDateMMDDYY(String.valueOf(span));

		searchFacility(facility, area, product);
		dateTime(dateFrom, dateTo, preferred, earliest, latest);
		partyMix(numberOfAdults, numberOfChildren, childAges);
		inventory(overrideInventory, overrideReason, vip, priority, interpreter, wheelchair);

		clickGetOfferSet(btnGetOfferSet);
	}

	/**
	 * 
	 * @summary Method to enter search criteria for a recreation or children's
	 *          activities
	 * @version Created 11/21/2014
	 * @author Waightstill W Avery
	 * @param scenario
	 *            - string, used to determine datatable row
	 * @throws NA
	 * @return NA
	 * 
	 * @Summary Added a call to getDaysOut after the DateTimeConversion with a
	 *          format
	 * @version Modified 12/30/15 / Modified 1/10/16 - Updated dateFrom and
	 *          dateTo to use current methods dateFrom =
	 *          DateTimeConversion.getDaysOut(dateFrom, form); and dateTo =
	 *          DateTimeConversion.getDaysOut(span, form);
	 * @author Stagliano, Dennis
	 */
	private void searchForRecChild(String scenario) {
		datatable.setVirtualtablePage("Discover");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded(lstActivityName);

		String activityName = datatable.getDataParameter("ActivityName");
		String area = datatable.getDataParameter("Area");
		String activityType = datatable.getDataParameter("ActivityType");
		String dateFrom = datatable.getDataParameter("DaysOut");
		String dateTo = datatable.getDataParameter("NumberOfNights");
		String timeFrom = datatable.getDataParameter("Earliest");
		String timeTo = datatable.getDataParameter("Latest");
		String day = datatable.getDataParameter("Day");
		String numberOfAdults = datatable.getDataParameter("NumberOfAdults");
		String numberOfChildren = datatable.getDataParameter("NumberOfChildren");
		String[] childAges = datatable.getDataParameter("ChildrenAges").split(";");
		String override = datatable.getDataParameter("InventoryOverride");
		String overrideReason = datatable.getDataParameter("OverrideReason");
		String vip = datatable.getDataParameter("VIP");
		String priority = datatable.getDataParameter("Priority");

		// Below code sets the date format and passes the dateFrom and dateTo
		// along
		// with the format to the method getDaysOut in the utilities folder
		String form = "MM/dd/yy";

		String span = Integer.toString((Integer.parseInt(dateFrom) + Integer.parseInt(dateTo)));
		dateFrom = DateTimeConversion.getDaysOut(dateFrom, form);
		dateTo = DateTimeConversion.getDaysOut(span, form);

		searchActivity(activityName, area, activityType);
		dateTimeDay(dateFrom, dateTo, timeFrom, timeTo, day);
		partyMix(numberOfAdults, numberOfChildren, childAges);
		inventory(override, overrideReason, vip, priority);
		clickGetOfferSet(btnGetOfferSet);
	}

	/**
	 * 
	 * @summary Method to enter search criteria for a Cirque event
	 * @version Created 11/21/2014
	 * @author Waightstill W Avery
	 * @param scenario
	 *            - string, used to determine datatable row
	 * @throws NA
	 * @return NA
	 * 
	 */
	@SuppressWarnings("unused")
	private void searchForCirque(String scenario) {
		datatable.setVirtualtablePage("Discover");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded(lstCirqueFacility);

		String facility = datatable.getDataParameter("Facility");
		String product = datatable.getDataParameter("Product");
		String date = datatable.getDataParameter("DaysOut");
		String time = datatable.getDataParameter("Preferred");
		String numberOfAdults = datatable.getDataParameter("NumberOfAdults");
		String numberOfChildren = datatable.getDataParameter("NumberOfChildren");
		String[] childAges = datatable.getDataParameter("ChildrenAges").split(";");

		DateTimeConversion convertDate = new DateTimeConversion();
		date = convertDate.ConvertToDateMMDDYY(date);

		dateTimeCirque(date, time);
		searchFacility(facility, product);
		partyMix(numberOfAdults, numberOfChildren, childAges);

		clickGetOfferSet(btnGetOfferSet);
	}

	private void clickGetOfferSet(Button button) {
		int counter = 0;
		while (counter++ < 20) {
			try {
				button.focus(driver);
				if (button.isEnabled()) {
					// button.jsClick(driver);
					counter = 10;
					break;
				} else {
					Sleeper.sleep(100);
					TestReporter.log("Discover: clickGetOfferSet NOT enabled on try " + counter);
				}
			} catch (Exception e) {
				TestReporter.log("Exception trying to focus and enable Discover: clickGetOfferSet: " + e.getMessage());
			}
		}
		button.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver, 60);
	}

	/**
	 * 
	 * @summary Method to enter location facility search criteria for
	 *          {@link #searchForTableSearch}
	 * @version Created 11/21/2014
	 * @author Waightstill W Avery
	 * @param location
	 *            - string, used to select a location
	 * @param cuisineExperience
	 *            - string, used to select a cuisine or experience
	 * @param restaurantName
	 *            - string, used to enter a specific restaurant name
	 * @param disneyDiningPlan
	 *            - boolean, used to search for only venues on the Disney Dining
	 *            Plan
	 * @throws NA
	 * @return NA
	 * 
	 */
	private void searchFacility(String location, String cuisineExperience, String restaurantName,
			boolean disneyDiningPlan) {
		lstLocation.select(location);
		txtRestaurantName.set(restaurantName);
		lstCuisineExperience.select(cuisineExperience);

		if (disneyDiningPlan) {
			chkDiningPlan.jsToggle(driver);
		}
	}

	/**
	 * 
	 * @summary Method to enter location facility search criteria for
	 *          {@link #searchForTableService}
	 * @version Created 11/21/2014
	 * @author Waightstill W Avery
	 * @param facility
	 *            - string, used to select a facility
	 * @param area
	 *            - string, used to select an area
	 * @param serviceSegment
	 *            - string, used to select a service segment
	 * @param cuisine
	 *            - string, used to select a cuisine type
	 * @param product
	 *            - string, used to select a specific product
	 * @param event
	 *            - string, used to select a specific event
	 * @param serviceStyle
	 *            - string, used to select a service style
	 * @throws NA
	 * @return NA
	 * 
	 */
	private void searchFacility(String facility, String area, String serviceSegment, String cuisine, String product,
			String event, String serviceStyle) {
		lstFacility.select(facility);
		selectAvoidingStaleness(lstArea, area);
		selectAvoidingStaleness(lstServiceSegment, serviceSegment);
		selectAvoidingStaleness(lstCuisine, cuisine);
		selectAvoidingStaleness(lstProduct, product);
		selectAvoidingStaleness(lstEvent, event);
		selectAvoidingStaleness(lstServiceStyle, serviceStyle);
	}

	private void selectAvoidingStaleness(Listbox listbox, String value) {
		if (!value.isEmpty()) {
			int counter = 0;
			boolean success = false;
			int maxTries = 10;
			do {
				try {
					Sleeper.sleep(1000);
					counter++;
					listbox.select(value);
					success = true;
				} catch (Exception e) {
					pageLoaded(listbox);
				}
			} while (!success && counter < maxTries);
		}
	}

	/**
	 * 
	 * @summary Method to enter location facility search criteria
	 *          {@link #searchForDinnerShows}
	 * @version Created 11/21/2014
	 * @author Waightstill W Avery
	 * @param facility
	 *            - string, used to select a facility
	 * @param area
	 *            - string, used to select an area
	 * @param product
	 *            - string, used to select a specific product
	 * @throws NA
	 * @return NA
	 */
	private void searchFacility(String facility, String area, String product) {
		lstDSFacility.select(facility);
		Sleeper.sleep(500);
		initialize();
		lstArea.select(area);
		Sleeper.sleep(500);
		initialize();
		lstDSProduct.select(product);
	}

	/**
	 * 
	 * @summary Method to enter location facility search criteria
	 *          {@link #searchForCirque}
	 * @version Created 11/21/2014
	 * @author Waightstill W Avery
	 * @param facility
	 *            - string, used to select a facility
	 * @param product
	 *            - string, used to select a specific product
	 * @throws NA
	 * @return NA
	 * 
	 */
	private void searchFacility(String facility, String product) {
		lstCirqueFacility.select(facility);
		lstCirqueProduct.select(product);
	}

	/**
	 * 
	 * @summary Method to enter location activity search criteria
	 *          {@link #searchForRecChild}
	 * @version Created 11/21/2014
	 * @author Waightstill W Avery
	 * @param activityName
	 *            - string, used to select an activity name
	 * @param area
	 *            - string, used to select an area
	 * @param activityType
	 *            - string, used to select an area type
	 * @throws NA
	 * @return NA
	 * 
	 * @version Created 12/30/15
	 * @author Stagliano, A Dennis
	 * @param no
	 *            change
	 * @summary Updated to sync and focusClick
	 */
	private void searchActivity(String activityName, String area, String activityType) {
		initialize();
		// Activity Name
		lstActivityName.syncVisible(driver);
		Sleeper.sleep(500);
		lstActivityName.select(activityName);
		Sleeper.sleep(500);
		// Area
		lstArea.focusClick(driver);
		Sleeper.sleep(500);
		lstArea.select(area);
		Sleeper.sleep(500);
		// Activity Type
		lstActivityType.syncVisible(driver);
		Sleeper.sleep(500);
		lstActivityType.select(activityType);
	}

	/**
	 * 
	 * @summary Method to enter date and time
	 * @version Created 11/21/2014
	 * @author Waightstill W Avery
	 * @param date
	 *            - string, used to enter a search date
	 * @param time
	 *            - string, used to enter a search time
	 * @throws NA
	 * @return NA
	 * 
	 */
	private void dateTime(String date, String time) {
		txtDate.set(date);
		if (time.toLowerCase().contains("am") || time.toLowerCase().contains("pm")) {
			String[] timeArray = time.split(" ");
			lstTime.select(timeArray[0]);
			lstAmPm.select(timeArray[1].toLowerCase());
		} else {
			lstTime.select(time);
		}
	}

	/**
	 * 
	 * @summary Method to enter date and time
	 * @version Created 11/21/2014
	 * @author Waightstill W Avery
	 * @param dateFrom
	 *            - string, used to enter a start date for the search
	 * @param dateTo
	 *            - string, used to enter an end date for the search
	 * @param preferred
	 *            - string, used to select a preferred time
	 * @param earliest
	 *            - string, used to select the earliest time the guest can
	 *            accommodate
	 * @param latest
	 *            - string, used to select the latest time the guest can
	 *            accommodate
	 * @throws NA
	 * @return NA
	 * 
	 */
	private void dateTime(String dateFrom, String dateTo, String preferred, String earliest, String latest) {

		if (preferred.charAt(preferred.length() - 3) != ' ') {
			System.out.println("Invalid Time Format from Virtual Table.  preferred Time=" + preferred);
			TestReporter.logStep("Invalid Time Format from Virtual Table.  preferred Time=" + preferred);
		}

		txtDateFrom.safeSet(dateFrom);
		txtDateTo.safeSet(dateTo);

		/*
		 * Populate the times
		 */
		selectAndTab(lstPreferred, getTimeOnly(preferred));
		populateListBox(lstPreferredAmPm);
		selectAndTab(lstPreferredAmPm, getTimeAmPm(preferred).toLowerCase());
		populateListBox(lstEarliest);

		selectAndTab(lstEarliest, getTimeOnly(earliest));
		populateListBox(lstEarliestAmPm);
		selectAndTab(lstEarliestAmPm, getTimeAmPm(earliest).toLowerCase());
		populateListBox(lstLatest);

		selectAndTab(lstLatest, getTimeOnly(latest));
		populateListBox(lstLatestAmPm);
		selectAndTab(lstLatestAmPm, getTimeAmPm(latest).toLowerCase());
	}

	private void selectAndTab(Listbox listbox, String value) {
		int counter = 0;
		int maxTries = 10;
		boolean success = false;
		do {
			try {
				Sleeper.sleep(500);
				counter++;
				listbox.select(value);
				success = true;
			} catch (Exception e) {
				pageLoaded(listbox);
			}
		} while (!success && counter < maxTries);
		TestReporter.assertTrue(counter < maxTries, "The value [" + value + "] was not able to be selected.");

		counter = 0;
		success = false;
		do {
			try {
				Sleeper.sleep(500);
				counter++; 
				listbox.sendKeys(Keys.TAB);
				success = true;
			} catch (Exception e) {
				pageLoaded(listbox);
			}
		} while (!success && counter < maxTries);
		TestReporter.assertTrue(counter < maxTries, "The element was not able to be tabbed away from.");
	}

	private void populateListBox(Listbox listbox) {
		int counter = 0;
		int maxTries = 10;
		while (counter < maxTries) {
			try {
				listbox.jsClick(driver);
				Sleeper.sleep(500);
				counter++;
				if (listbox.isEnabled())
					break;
			} catch (Exception e) {
				pageLoaded(listbox);
			}
		}
		TestReporter.assertTrue(counter != maxTries - 1, "The listbox was not found to be enabled.");
	}

	/**
	 * @summary Given a time in the format of HH:mm AM, this return the numeric
	 *          time only and strips the AM/PM period.
	 * @author Cory Shields
	 * @param time
	 * @return
	 */
	private String getTimeOnly(String time) {
		if (time.charAt(time.length() - 3) != ' ') {
			System.out.println("Invalid Time Format from Virtual Table.  preferred Time=" + time);
			TestReporter.logStep("Invalid Time Format from Virtual Table.  preferred Time=" + time);
		}
		return time.substring(0, time.length() - 3);
	}

	/**
	 * @summary Given a time in the format of HH:mm AM, this return the period
	 *          only and strips the numeric time. Updated 1-15 - Added
	 *          toLowerCase() - Dennis Stagliano and removed quotes Given the
	 *          data table remains a constant format.
	 * @author Cory Shields
	 * @param time
	 * @return
	 */

	private String getTimeAmPm(String time) {
		TestReporter.log("Discover getTimeAmPm for [" + time + "]");
		// if (time.charAt(time.length() - 3) != ' ') {
		// System.out.println("Invalid Time Format from Virtual Table. preferred
		// Time=" + time);
		// TestReporter.logStep("Invalid Time Format from Virtual Table.
		// preferred Time=" + time);

		// }
		String theNewTime = time;
		theNewTime = time.substring(time.length() - 3).toLowerCase().replaceAll("\"", "").trim();
		return theNewTime;
	}

	/**
	 * 
	 * @summary Method to enter date, time and day of the week Updated on
	 *          12/30/15 - Performs the same functionality, but added a
	 *          conditional statement for the radio selection value of
	 *          "selection" since dateTimeDay is only being called from the
	 *          searchForRecChild method only used if Recreation radio was
	 *          selected. Updated elements and added a focusClick() and Sleepers
	 *          as necessary to help prevent timing errors.
	 * @version Created 11/21/2014 / Version created 12/20/15
	 * @author Waightstill W Avery / Stagliano, Dennis
	 * @param dateFrom
	 *            - string, used to enter a start date for the search
	 * @param dateTo
	 *            - string, used to enter an end date for the search
	 * @param timeFrom
	 *            - string, used to select the earliest time the guest can
	 *            accommodate
	 * @param timeTo
	 *            - string, used to select the latest time the guest can
	 *            accommodate
	 * @param day
	 *            - string, preferred day of the week for the guest
	 * @throws NA
	 * @return NA
	 * 
	 */
	private void dateTimeDay(String dateFrom, String dateTo, String timeFrom, String timeTo, String day) {
		pageLoaded(txtRecDateFrom);
		datatable.setVirtualtablePage("Discover");

		/**
		 * Implementing functionality used by other date/time methods which avoid
		 * stale elements
		 */
		 //Set dateFrom
		 txtRecDateFrom.safeSet(dateFrom);
		
		 //Set dateTo
		 txtRecDateTo.safeSet(dateTo);
		 
		//
		// //Set timeFrom
		 String theTimeFrom = formatTheTimeRecreation(timeFrom);
		// Sleeper.sleep(500);
		// lstTimeFrom.focusClick(driver);
		// Sleeper.sleep(500);
		// lstTimeFrom.select(theTimeFrom);
		selectAndTab(lstTimeFrom, theTimeFrom);
		
		//
		// //Set timeTo
		 String theTimeTo = formatTheTimeRecreation(timeTo);
		// Sleeper.sleep(500);
		// lstTimeTo.focusClick(driver);
		// Sleeper.sleep(500);
		// lstTimeTo.select(theTimeTo);
		populateListBox(lstTimeTo);
		selectAndTab(lstTimeTo, theTimeTo);
		
		// //Set From AmPm
		 String fTime = getTimeAmPm(timeFrom);
		// Sleeper.sleep(500);
		// lstRecreationTimeFromAmPm.focusClick(driver);
		// Sleeper.sleep(500);
		//
		// lstRecreationTimeFromAmPm.select(fTime);
		// Sleeper.sleep(500);
		populateListBox(lstRecreationTimeFromAmPm);
		selectAndTab(lstRecreationTimeFromAmPm, fTime);
		
		// //Set To AmPm
		 String tTime = getTimeAmPm(timeTo);
		// Sleeper.sleep(500);
		// lstRecreationTimeToAmPm.focusClick(driver);
		// Sleeper.sleep(500);
		// lstRecreationTimeToAmPm.select(tTime);
		// Sleeper.sleep(500);
		populateListBox(lstRecreationTimeToAmPm);
		selectAndTab(lstRecreationTimeToAmPm, tTime);
		
		// //Set Day
		// lstDay.focusClick(driver);
		// Sleeper.sleep(500);
		// lstDay.select(day);
		populateListBox(lstDay);
		selectAndTab(lstDay, day);
	}// end if

	/**
	 * 
	 * @summary Method to format time for the Recreation for "from" and "To"
	 *          time selection dropdowns
	 * @version Created 12/30/15 / Update 1-17 - removed -1 from substring
	 * @version Created 1/19/16 - Used ReplaceAll as a better way to format time
	 *          as long as VT is not sending decimals.
	 * @author Stagliano, Dennis
	 * @param theTime
	 *            - string, used to from VT and passed from method dataTimeDay
	 *            parameters - Strips the AM/PM out and leaves the actual time
	 *            value needed to be selected - for the "from" and "to" time
	 *            selections given that the data table remains a constant
	 *            format.
	 * @throws NA
	 * @return String of the proper time format listed in the time drop down
	 *         fields eg 8:00 or 11:30
	 * 
	 */
	private String formatTheTimeRecreation(String theTime) {

		String newTime = null;
		if (theTime.contains("AM")) {
			newTime = theTime.replaceAll("AM", "");
			newTime = newTime.replaceAll("\"", "").trim();
		}
		if (theTime.contains("PM")) {
			newTime = theTime.replaceAll("PM", "");
			newTime = newTime.replaceAll("\"", "").trim();
		}
		return newTime;

	}

	/**
	 * 
	 * @summary Method to enter date and time for a Cirque reservation
	 * @version Created 11/21/2014
	 * @author Waightstill W Avery
	 * @param date
	 *            - string, exact date on which to search for availability
	 * @param time
	 *            - string, show time
	 * @throws NA
	 * @return NA
	 * 
	 */
	private void dateTimeCirque(String date, String time) {
		txtCirqueDate.safeSet(date);
		PleaseWait.WaitForPleaseWait(driver);
		pageLoaded(lstCirqueProduct);
		lstCirqueTime.select(time);

	}

	/**
	 * 
	 * @summary Method to enter party size
	 * @version Created 11/21/2014
	 * @author Waightstill W Avery
	 * @param partySize
	 *            - string integer, used to define the party size
	 * @throws NA
	 * @return NA
	 * 
	 */
	private void partyMix(String partySize) {
		lstPartySize.select(partySize);
	}

	/**
	 * 
	 * @summary Method to enter party size / 1/8 - Removed old comments and
	 *          backed up old version
	 * @version Created 11/21/2014 / Version created 12/30/15 /Version 1/8/16
	 * @author Waightstill W Avery / Stagliano, Dennis
	 * @param numberOfAdults
	 *            - string integer, used to select the number of adults in the
	 *            party
	 * @param numberOfChildren
	 *            - string integer, used to select the number of children in the
	 *            party
	 * @param childAges
	 *            - string, used to enter child ages. Needs to be a string of
	 *            numbers delimited by semi-colons
	 * @throws NA
	 * @return NA
	 * 
	 */
	private void partyMix(String numberOfAdults, String numberOfChildren, String[] childAges) {

		txtNumberOfAdults.click();
		txtNumberOfAdults.set(numberOfAdults);
		txtNumberOfChildren.set(numberOfChildren);
		// Need to click back on the Number of Adults to allow application
		// to show children drop down fields, otherwise script times out.
		// Clicking out
		// of Number of Children Textbox activates application to dynamically
		// add the Child
		// ages selection fields.
		if (!numberOfChildren.isEmpty()){
			if(Integer.parseInt(numberOfChildren) > 0) {
				txtNumberOfAdults.click();
				Sleeper.sleep(2000);
			}
		}

		// Perform the parseInt early to set up looping through child ages to
		// make sure they are
		// the same data types.

		// System.out.println("AdultsNum " + AdultsNum);
		Integer ChildNum = 0;
		Integer AdultsNum = 0;
		try {
			AdultsNum = Integer.parseInt(numberOfAdults);
		} catch (NumberFormatException e) {
			AdultsNum = 0;
		}
		try{
			ChildNum = Integer.parseInt(numberOfChildren);
		}catch(NumberFormatException e){
			ChildNum = 0;
		}

		if (ChildNum != null && AdultsNum != null) {
			try {
				if (ChildNum > 0) {
					pageLoaded(lstFirstChildAge);
					TestReporter.assertEquals(ChildNum, childAges.length, "The number of children " + ChildNum
							+ " does not equal the number of child ages " + String.valueOf(childAges.length));

					// Set child ages
					for (loopCounter = 0; loopCounter < ChildNum; loopCounter++) {
						String htmlId = "guestPreferencesForm:guestPreferencesChildrenRepeat:"
								+ String.valueOf(loopCounter) + ":selectOneMenuID";
						Listbox childAge = new ListboxImpl(driver.findElement(By.id(htmlId)));
						childAge.focusClick(driver);
						Sleeper.sleep(1000);
						childAge.select(childAges[loopCounter]);
					}
				}
			} catch (NumberFormatException n) {
				n.printStackTrace();
			}
		}

	}// end method

	/**
	 * 
	 * @summary Method to enter inventory criteria
	 * @version Created 11/21/2014
	 * @author Waightstill W Avery
	 * @param overrideInventory
	 *            - string, used to determine if inventory restrictions should
	 *            be overriden
	 * @param overrideReason
	 *            - string, used to select an override reason
	 * @param vip
	 *            - string, used to determine if the Vip checkbox should be
	 *            toggled
	 * @param priority
	 *            - string, used to select a priority level
	 * @throws NA
	 * @return NA
	 * 
	 */
	private void inventory(String overrideInventory, String overrideReason, String vip, String priority) {
		StopWatch watch = new StopWatch();
		if (overrideInventory.equalsIgnoreCase("true")) {
			chkInventoryOverride.jsToggle(driver);
			try {
				watch.start();
				do {
					Sleeper.sleep(100);
					initialize();
					pageLoaded(lstOverrideReason);
				} while (lstOverrideReason.getAttribute("disabled").equalsIgnoreCase("disabled")
						&& watch.getTime() < WebDriverSetup.getDefaultTestTimeout());
			} catch (Exception e) {
				Sleeper.sleep(3000);
				lstOverrideReason.select(overrideReason);
			}
		}

		if (vip.equalsIgnoreCase("true")) {
			// chkVIP.check();
			chkVIP.highlight(driver);
			chkVIP.click();
		}

		lstPriority.select(priority);
	}

	/**
	 * 
	 * @summary Method to enter inventory criteria
	 * @version Created 11/21/2014
	 * @author Waightstill W Avery
	 * @param overrideInventory
	 *            - string, used to determine if inventory restrictions should
	 *            be overriden
	 * @param overrideReason
	 *            - string, used to select an override reason
	 * @param vip
	 *            - string, used to determine if the Vip checkbox should be
	 *            toggled
	 * @param priority
	 *            - string, used to select a priority level
	 * @param interpreter
	 *            - boolean, used to determine if the Sign Language Interpreter
	 *            checkbox should be toggled
	 * @param wheelchair
	 *            - boolean, used to determine if the Wheelchair Assistance
	 *            checkbox should be toggled
	 * @throws NA
	 * @return NA
	 * 
	 */
	private void inventory(String overrideInventory, String overrideReason, String vip, String priority,
			boolean interpreter, boolean wheelchair) {

		inventory(overrideInventory, overrideReason, vip, priority);

		if (interpreter) {
			chkInterpreter.jsToggle(driver);
		}

		if (wheelchair) {
			chkWheelchair.jsToggle(driver);
		}
	}

	public void verifyDicoverPage() {
		Discover discoverPage = new Discover(driver);
		TestReporter.assertTrue(discoverPage.pageLoaded(chkVIP), "Validation Failed Discover Page loaded");
	}

	/**
	 * @summary: Method to use the down arrow key.
	 * @author: Praveen Namburi, @version: Created 1-26-2016 - original.
	 */
	public void performDownArrowkey() {
		Actions actionObject = new Actions(driver);
		actionObject.sendKeys(Keys.ARROW_DOWN);
		actionObject.perform();
	}
	
	/**
	 * 
	 * @summary Method to enter search criteria for a Cirque event
	 * @version Created 02/01/2016
	 * @author  Praveen namburi
	 * @param scenario
	 *            - string, used to determine datatable row
	 * @throws NA, @return NA
	 */
	private void searchForCirqueShow(String scenario) {
		datatable.setVirtualtablePage("Discover");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded(lstCirqueFacility);

		String facility = datatable.getDataParameter("Facility");
		String product = datatable.getDataParameter("Product");
		String date = datatable.getDataParameter("DaysOut");
		String time = datatable.getDataParameter("Preferred");
		String numberOfAdults = datatable.getDataParameter("NumberOfAdults");
		String numberOfChildren = datatable.getDataParameter("NumberOfChildren");
		String[] childAges = datatable.getDataParameter("ChildrenAges").split(";");
		
		/*
		 * Not all days are valid for Cirque.  Loop through the days until a valid day is found. 
		 */
		String daysOut = date;
		
		date = determineCirqueOutageDates(daysOut);
		txtCirqueDate.safeSet(date);
		PleaseWait.WaitForPleaseWait(driver, 20, false);
		lookForError();
		pageLoaded(lstCirqueProduct);
		lstCirqueProduct.syncVisible(driver);
		if (lstCirqueProduct.isEnabled()) {
			lstCirqueTime.select(time.replace("\"", ""));
			searchFacility(facility, product);
			partyMix(numberOfAdults, numberOfChildren, childAges);
		}
		
		clickGetOfferSet(btnGetOfferSet);
	}
	
	private String determineCirqueOutageDates(String daysOut){
		DateTimeConversion convertDate = new DateTimeConversion();
		int counter = 0;
		String input_date;
		Date dt1 = null;
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat format2 = null;
		String finalDay;
		
		input_date= convertDate.ConvertToDateMMDDYY(String.valueOf(Integer.parseInt(daysOut) + counter));
		try {
			dt1 = format1.parse(input_date);
		} catch (ParseException e) {}
		format2 = new SimpleDateFormat("EEEE"); 
		finalDay = format2.format(dt1);
		
		switch (finalDay.toLowerCase()) {
		case "sunday":
			counter = 2;
			break;
		case "monday":
			counter = 1;
			break;
		}
				
		return convertDate.ConvertToDateMMDDYY(String.valueOf(Integer.parseInt(daysOut) + counter));
	}

	private String lookForError(){
		String errorText = "";
		String errorType = "";
		String noPerformancesForDate = "There are no performances for date specified";
		
		try{
			if(eleErrorPanelContentTable.syncVisible(driver, 3, false)){
				errorText = eleErrorPanelContentTable.getText();
			}
		}catch(Exception e){
			
		}
		
		if(errorText.contains(noPerformancesForDate)){
			errorType = "NOPERFORMANCESFORDATE";
			AlertHandler.handleAlerts(driver, 5);
		}
		
		return errorType;
	}
}

