package apps.alc.existingReservation;

import java.util.List;

import javax.print.attribute.standard.MediaSize.NA;

import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.alc.pleaseWait.PleaseWait;
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
 * This class contains elements and element interactions for a given page of a web application
 * 
 * @author Waightstill W Avery
 * @version 12/15/2015 Waightstill W Avery - original
 */
public class ExistingReservationDiscoverPage {
	// ****************************
	// *** Discover Page Fields ***
	// ****************************
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	private int loopCounter;
	private Element obj;

	// ******************************
	// *** Discover Page Elements ***
	// ******************************
	@FindBy(id = "modifyguestPreferencesForm:getOfferSetCmdBtn")
	private Button btnGetOfferSet;

	/*
	 * Event type radio buttons
	 */
	// Table Service radiobutton
	@FindBy(id = "modifyguestPreferencesForm:modifyfunctionalAreaRadioButton:0")
	private RadioGroup radTableService;

	// Dinner Shows radiobutton
	@FindBy(id = "modifyguestPreferencesForm:modifyfunctionalAreaRadioButton:1")
	private RadioGroup radDinnerShow;

	// Recreation radiobutton
	@FindBy(id = "modifyguestPreferencesForm:modifyfunctionalAreaRadioButton:2")
	private RadioGroup radRecreation;

	// Children's Activities radiobutton
	@FindBy(id = "modifyguestPreferencesForm:modifyfunctionalAreaRadioButton:3")
	private RadioGroup radChildrenActivities;

	// Discover Selection radio group
	@FindBy(name = "modifyguestPreferencesForm:modifyfunctionalAreaRadioButton")
	private List<Element> radDiscoverSelection;

	/*
	 * Table Service Location elements
	 */
	// Facility listbox
	@FindBy(id = "modifyguestPreferencesForm:ModGuestPrefControllerForTableServiceLocation")
	private Listbox lstFacility;

	// Area listbox - used by Dinner Show, Recreation and Child's Activities as
	// well
	@FindBy(id = "modifyguestPreferencesForm:ModGuestPrefControllerAreaNotTheatre")
	private Listbox lstArea;

	// Service Segment listbox
	@FindBy(id = "modifyguestPreferencesForm:ModGuestPrefContSvcSegmentTablseSvc")
	private Listbox lstServiceSegment;

	// Cuisine listbox
	@FindBy(id = "modifyguestPreferencesForm:ModGuestPrefControllerTableSvcCuisine")
	private Listbox lstCuisine;

	// Product listbox
	@FindBy(id = "modifyguestPreferencesForm:modifyvenueoptions2")
	private Listbox lstProduct;

	// Event listbox
	@FindBy(id = "modifyguestPreferencesForm:ModGuestPrefContEventForTabSvc")
	private Listbox lstEvent;

	// Service Style listbox
	@FindBy(id = "modifyguestPreferencesForm:ModGuestPrefContSvcServiceTableService")
	private Listbox lstServiceStyle;

	/*
	 * Dinner Show Location elements
	 */
	// Facility listbox
	@FindBy(id = "modifyguestPreferencesForm:ModGuestPrefContLocationDinner")
	private Listbox lstDSFacility;

	// Area listbox
	@FindBy(id = "modifyguestPreferencesForm:modifyvenueoptions1")
	private Listbox lstDSProduct;

	/*
	 * Recreation Location elements
	 */
	// Activity Name listbox - used by Children's Activities as well
	@FindBy(id = "modifyguestPreferencesForm:ModGuestPrefContActivityRecChildAct2")
	private Listbox lstActivityName;

	// Activity Type listbox - used by Children's Activities as well
	@FindBy(id = "modifyguestPreferencesForm:ModGuestPrefContActivityForRecChildAct")
	private Listbox lstActivityType;

	/*
	 * Date and time
	 */
	// Date From textbox - used by Dinner Shows as well
	@FindBy(id = "modifyguestPreferencesForm:modifyguestPreferencesFromDateInput11")
	private Textbox txtDateFrom;
	
	//Date from for Recreation
	@FindBy(id = "modifyguestPreferencesForm:modifyguestPreferencesFromDateInput")
	private Textbox txtDateFromRecreation;

	// Date To textbox - used by Dinner Shows as well
	@FindBy(id = "modifyguestPreferencesForm:modifyguestPreferencesToDateInput11")
	private Textbox txtDateTo;
	
	//Date To for Recreation
	@FindBy(id = "modifyguestPreferencesForm:modifyguestPreferencesToDateInput")
	private Textbox txtDateToRecreation;

	// Preferred Time listbox - used by Dinner Shows as well
	@FindBy(id = "modifyguestPreferencesForm:modTimePreferred")
	private Listbox lstPreferred;

	// Preferred time period listbox - used by Dinner Shows as well
	@FindBy(id = "modifyguestPreferencesForm:modTimePreferredAmPm")
	private Listbox lstPreferredAmPm;

	// Earliest Time listbox - used by Dinner Shows as well
	@FindBy(id = "modifyguestPreferencesForm:modTimeEarliest")
	private Listbox lstEarliest;

	// Earliest Time period listbox - used by Dinner Shows as well
	@FindBy(id = "modifyguestPreferencesForm:modTimeEarliestAmPm")
	private Listbox lstEarliestAmPm;

	// Latest Time listbox - used by Dinner Shows as well
	@FindBy(id = "modifyguestPreferencesForm:modTimeLatest")
	private Listbox lstLatest;

	// Latest Time period listbox - used by Dinner Shows as well
	@FindBy(id = "modifyguestPreferencesForm:modTimeLatestAmPm")
	private Listbox lstLatestAmPm;

	// Recreation Date From textbox - used by Child's Activities as well
	@FindBy(id = "modifyguestPreferencesForm:modifyguestPreferencesFromDateInput")
	private Textbox txtRecDateFrom;

	// Recreation Date To textbox - used by Child's Activities as well
	@FindBy(id = "modifyguestPreferencesForm:modifyguestPreferencesToDateInput")
	private Textbox txtRecDateTo;

	// Time From listbox - used by Child's Activities as well
	@FindBy(id = "modifyguestPreferencesForm:etimeFrom")
	private Listbox lstTimeFrom;

	// Time From periods listbox - used by Child's Activities as well
	@FindBy(id = "modifyguestPreferencesForm:etimeFromAmPm")
	private Listbox lstTimeFromAmPm;

	// Time To listbox - used by Child's Activities as well
	@FindBy(id = "modifyguestPreferencesForm:etimeTo")
	private Listbox lstTimeTo;

	// Time To periods listbox - used by Child's Activities as well
	@FindBy(id = "modifyguestPreferencesForm:etimeToAmPm")
	private Listbox lstTimeToAmPm;

	// Days listbox - used by Child's Activities as well
	@FindBy(xpath = "/html/body/div[2]/div[7]/div[2]/table/tbody/tr[2]/td[1]/table/tbody/tr/td/table/tbody/tr[2]/td[5]/table/tbody/tr/td/form/table/tbody/tr[3]/td[1]/span/div/div[2]/table/tbody/tr[3]/td[2]/select")
	private Listbox lstDay;

	/*
	 * Party mix
	 */
	// Number of Adults textbox
	@FindBy(id = "modifyguestPreferencesForm:modGuestPrefContAdultInpt")
	private Textbox txtNumberOfAdults;

	// Number of Children textbox
	@FindBy(id = "modifyguestPreferencesForm:modGuestPrefContNoChldInpt")
	private Textbox txtNumberOfChildren;

	// Child Age listbox for the first child age
	@FindBy(id = "modifyguestPreferencesForm:modifyguestPreferencesChildrenRepeat:0:ModGuestPrefContChildAge")
	private Listbox lstFirstChildAge;

	/*
	 * Inventory requests
	 */
	// Inventory Override checkbox
	@FindBy(id = "modifyguestPreferencesForm:modsaveOverRideID")
	private Checkbox chkInventoryOverride;

	// Inventory Override Reason listbox
	@FindBy(id = "modifyguestPreferencesForm:modoverrideInventoryReasonOptions")
	private Listbox lstOverrideReason;

	// Vip checkbox
	@FindBy(xpath = "/html/body/div[2]/div[7]/div[2]/table/tbody/tr[2]/td[1]/table/tbody/tr/td/table/tbody/tr[2]/td[5]/table/tbody/tr/td/form/table/tbody/tr[3]/td[3]/span/div/div[2]/table/tbody/tr[2]/td/input")
	private Checkbox chkVIP;

	// Priority listbox
	@FindBy(id = "modifyguestPreferencesForm:modpriorityID")
	private Listbox lstPriority;

	// Sign Language Interpreter checkbox
	@FindBy(id = "modifyguestPreferencesForm:modselectedSpecialRequestsID:0")
	private Checkbox chkInterpreter;

	// Wheelchair Accessibility checkbox
	@FindBy(id = "modifyguestPreferencesForm:modselectedSpecialRequestsID:1")
	private Checkbox chkWheelchair;

	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
	 * @param driver
	 */
	public ExistingReservationDiscoverPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public ExistingReservationDiscoverPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnGetOfferSet);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
	 * @param element
	 *            - Element to be used to determine if the page is loaded
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
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

		// Determine the event search type
		switch (selection.toLowerCase()) {
		case "table service":
			obj = new ElementImpl(radDiscoverSelection.get(0));
			obj.jsClick(driver);
			PleaseWait.WaitForPleaseWait(driver);
			searchForTableService(scenario);
			break;
		case "dinner shows":
		case "dinner show":
			obj = new ElementImpl(radDiscoverSelection.get(1));
			obj.jsClick(driver);
			PleaseWait.WaitForPleaseWait(driver);
			searchForDinnerShows(scenario);
			break;
		case "recreation":
			obj = new ElementImpl(radDiscoverSelection.get(2));
			obj.jsClick(driver);
			PleaseWait.WaitForPleaseWait(driver);
			searchForRecChild(scenario);
			break;
		case "children's activities":
			obj = new ElementImpl(radDiscoverSelection.get(3));
			obj.jsClick(driver);
			PleaseWait.WaitForPleaseWait(driver);
			searchForRecChild(scenario);
			break;
		default:
			TestReporter.assertEquals(validEventType, true, "A valid event type must be entered");
			break;
		}
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
		String earliest = datatable.getDataParameter("Earliest").replace("\"", "");
		String latest = datatable.getDataParameter("Latest").replace("\"", "");
		/*String preferred = datatable.getDataParameter("Preferred").replace("\"", "");
		String earliest = datatable.getDataParameter("Earliest");
		String latest = datatable.getDataParameter("Latest");*/
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
		String preferred = datatable.getDataParameter("Preferred").replace("'", "").replace("\"", "");
		String earliest = datatable.getDataParameter("Earliest").replace("'", "").replace("\"", "");
		String latest = datatable.getDataParameter("Latest").replace("'", "").replace("\"", "");
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
	 */
	private void searchForRecChild(String scenario) {
		datatable.setVirtualtablePage("Discover");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded(lstActivityName);

		String activityName = datatable.getDataParameter("ActivityName");
		String area = datatable.getDataParameter("Area");
		String activityType = datatable.getDataParameter("ActivityType");
		String dateFrom = datatable.getDataParameter("DaysOut").replace("\"", "");
		String dateTo = datatable.getDataParameter("NumberOfNights");
		String timeFrom = datatable.getDataParameter("Earliest").replace("\"", "");
		String timeTo = datatable.getDataParameter("Latest").replace("\"", "");
		String day = datatable.getDataParameter("Day");
		String numberOfAdults = datatable.getDataParameter("NumberOfAdults");
		String numberOfChildren = datatable.getDataParameter("NumberOfChildren");
		String[] childAges = datatable.getDataParameter("ChildrenAges").split(";");
		String override = datatable.getDataParameter("InventoryOverride");
		String overrideReason = datatable.getDataParameter("OverrideReason");
		String vip = datatable.getDataParameter("VIP");
		String priority = datatable.getDataParameter("Priority");

		String form = "MM/dd/yy";

		String span =Integer.toString((Integer.parseInt(dateFrom)+Integer.parseInt(dateTo)));
		dateFrom = DateTimeConversion.getDaysOut(dateFrom, form);
		dateTo = DateTimeConversion.getDaysOut(span, form);

		//dateTo = convertDate.getDaysOut(dateTo, form);
		System.out.println("datefrom " + dateFrom + "dateTo " + dateTo);

		searchActivity(activityName, area, activityType);
		dateTimeDay(dateFrom, dateTo, timeFrom, timeTo, day);
		partyMix(numberOfAdults, numberOfChildren, childAges);
		inventory(override, overrideReason, vip, priority);
		Sleeper.sleep(2000);

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
		//button.jsClick(driver);
		button.highlight(driver);
		button.click();
		PleaseWait.WaitForPleaseWait(driver);
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
		Sleeper.sleep(1000);
		initialize();
		pageLoaded(lstArea);
		lstArea.select(area);
		lstServiceSegment.select(serviceSegment);
		lstCuisine.select(cuisine);
		lstProduct.select(product);
		lstEvent.select(event);
		lstServiceStyle.select(serviceStyle);
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
	 * 
	 */
	private void searchFacility(String facility, String area, String product) {
		lstDSFacility.select(facility);
		lstArea.select(area);
		lstDSProduct.select(product);
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
	 */
	private void searchActivity(String activityName, String area, String activityType) {
		lstActivityName.select(activityName);
		Sleeper.sleep(3000);
		lstArea.select(area);
		lstActivityType.select(activityType);
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
		lstPreferred.select(getTimeOnly(preferred));
		String ampm = getTimeAmPm(preferred).toLowerCase();
		TestReporter.logStep("Select Prefereed AmPm [" + ampm + "]");
		int counter = 0;
		while (counter++ < 10) {
			try {
				lstPreferredAmPm.focusClick(driver);
				if (lstPreferredAmPm.isEnabled()) {
					// if (lstPreferredAmPm.syncEnabled(driver)) {
					counter = 10;
				} else {
					Sleeper.sleep(100);
					TestReporter.log("Discover.java: dateTime() lstPrefereedAmPm NOT enabled on try " + counter);
				}
			} catch (Exception e) {
				TestReporter.log("Exception trying to enable lstPreferedAMPM " + e.getMessage());
			}
		}
		initialize();
		lstPreferredAmPm.select(ampm);
		counter = 0;
		while (counter++ < 10) {
			try {
				lstEarliest.focusClick(driver);
				if (lstEarliest.isEnabled())
					counter = 100;
				Sleeper.sleep(100);// sleep up to 1/2 second
			} catch (Exception e) {
				TestReporter.log("Exception trying to select lstEarliest   " + e.getMessage());
				// e.printStackTrace();
				// throw e;
			}
		}

		do {
			Sleeper.sleep(100);
			lstEarliest.focusClick(driver);
		} while (lstEarliest.getAttribute("value").equalsIgnoreCase(""));
		lstEarliest.select(getTimeOnly(earliest));
		lstEarliestAmPm.select(getTimeAmPm(earliest));
		lstLatest.select(getTimeOnly(latest));
		lstLatestAmPm.select(getTimeAmPm(latest));
	}

	/**
	 * @summary Given a time in the format of HH:mm AM, this return the numeric
	 *          time only and strips the AM/PM period.
	 * @author Cory Shields
	 * @param time
	 * @return
	 */
	private String getTimeOnly(String time) {
		return time.substring(0,time.indexOf(":") + 3);
	}

	/**
	 * @summary Given a time in the format of HH:mm AM, this return the period
	 *          only and strips the numeric time.
	 * @author Cory Shields
	 * @param time
	 * @return
	 */
	private String getTimeAmPm(String time) {
		return time.substring(time.indexOf(":") + 3, time.length()).trim().toLowerCase();
	}

	/**
	 * 
	 * @summary Method to enter date, time and day of the week
	 * @version Created 11/21/2014
	 * @author Waightstill W Avery
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
		txtDateFromRecreation.set(dateFrom);
		txtDateToRecreation.set(dateTo);

		//Reading TimeOnly
		String StartTime = getTimeOnly(timeFrom);
		String EndTime = getTimeOnly(timeTo);

		//Reading AMPM
		String timeFrom1AMPM  =getTimeAmPm(timeFrom);
		String timeTo1AMPM  =getTimeAmPm(timeTo);

		lstTimeFrom.select(StartTime);
		Sleeper.sleep(3000);
		lstTimeTo.select(EndTime);
		Sleeper.sleep(1000);
		lstTimeFromAmPm.select(timeFrom1AMPM.toLowerCase().replace("\"", ""));
		Sleeper.sleep(1000);
		lstTimeToAmPm.select(timeTo1AMPM.toLowerCase().replace("\"", ""));

		lstDay.select(day);
	}

	/**
	 * 
	 * @summary Method to enter party size
	 * @version Created 11/21/2014
	 * @author Waightstill W Avery
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
		// Ensure the number of children passed is not an empty string
		if (!numberOfChildren.isEmpty()) {
			// Ensure the number of children passed is an integer
			try {
				if (Integer.parseInt(numberOfChildren) > 0) {
					// Ensure the first child age box is loaded
					pageLoaded(lstFirstChildAge);
					// Ensure the the number of children passed and the number
					// of ages matches
					try{
						// Locator is different for Existing Reservation - Recreation
						for (loopCounter = 0; loopCounter < Integer.parseInt(numberOfChildren); loopCounter++) {
							String htmlId = "modifyguestPreferencesForm:modifyguestPreferencesChildrenRepeat:"
									+ String.valueOf(loopCounter) + ":ModGuestPrefContChildAge";

							Listbox childAge = new ListboxImpl(driver.findElement(By.id(htmlId)));
							childAge.select(childAges[loopCounter]);
						}

					}catch(Exception e){
						TestReporter.assertEquals(numberOfChildren, childAges.length,
								"The number of children [" + numberOfChildren + "] does not equal the number of "
										+ "child ages [" + String.valueOf(childAges.length) + "].");
						// Enter the child ages
						for (loopCounter = 0; loopCounter < Integer.parseInt(numberOfChildren); loopCounter++) {
							String htmlId = "guestPreferencesForm:guestPreferencesChildrenRepeat:"
									+ String.valueOf(loopCounter) + ":selectOneMenuID";
							Listbox childAge = new ListboxImpl(driver.findElement(By.id(htmlId)));
							childAge.select(childAges[loopCounter]);
						}

					}
				}
			} catch (NumberFormatException n) {
				n.printStackTrace();
			}
		}
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
	 * @throws NA
	 * @return NA
	 * 
	 */
	private void inventory(String overrideInventory, String overrideReason, String vip, String priority) {
		StopWatch watch = new StopWatch();
		if (overrideInventory.equalsIgnoreCase("true")) {
			chkInventoryOverride.check();
			try {
				watch.start();
				do {
					Sleeper.sleep(100);
					initialize();
					pageLoaded(lstOverrideReason);
				} while (lstOverrideReason.getAttribute("disabled").equalsIgnoreCase("disabled"));
			} catch (Exception e) {
				lstOverrideReason.select(overrideReason);
			}
		}

		if (vip.equalsIgnoreCase("true")) {
			chkVIP.check();
		}

		lstPriority.select(priority);
		lstOverrideReason.select(overrideReason);
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

}

