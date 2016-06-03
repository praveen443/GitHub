package com.disney.test.composite.alc.modify;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import apps.alc.existingReservation.ExistingReservationGuestSearchPage;
import apps.alc.existingReservation.reservationTab.ExistingReservationReservationTab_DetailsTab;
import apps.alc.mainTabNavigation.MainTabNavigation;
import apps.alc.newReservation.Discover;
import apps.alc.newReservation.NewEditGuest;
import apps.alc.newReservation.Offers;
import apps.alc.newReservation.SearchGuestPage;
import apps.alc.newReservation.ShoppingCart;
import apps.alc.newReservation.Summary;
import apps.alc.roleAndLocation.RoleAndLocationPage;
import apps.alc.topNavigationBar.TopNavigationBar;
import apps.hub.HubLoginPage;
import core.WebDriverSetup;
import utils.Datatable;
import utils.TestReporter;

/**
 * @Summary: creates new guest and verifies ALC_Notifications_Update
 * @Precondition: N/A
 * @Author: Lalitha Banda
 * @Version: 12/29/2015
 * @Return: N/A
 */
public class ALC_Notifications_Update {
	// *******************
	// Test Class Fields
	// *******************
	// Defining global variables
	private String testName;
	private String application = "alc";
	private String browserUnderTest = "";
	private String runLocation = "";
	private String environment = "";
	private Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();
	private Datatable dt = new Datatable();
	private String internalTextBoxComment = "Extra";
	private String externalTextBoxComment = "Add Extra Extrnal Comment";

	/*
	 * Define a method that reads in data and returns a 2-dimensional array to
	 * be used by the test Give the data provider a unique name that will be
	 * referenced by test methods. In the method
	 * Datatable.getTestScenariosByApp(String, String), the first string is the
	 * application name which is defined later, and the second string is the
	 * test scenario name as it is found in the virtual tables
	 */
	@DataProvider(name = "dataProvider")
	public Object[][] scenarios() {
		try {
			// Defining what table to run from the virtual tables
			return dt
					.getTestScenariosByApp(application, "Notifications_Update");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Define a method that will be executed prior to each @Test method. The
	 * 
	 * @BeforeTest argument "inheritGroups = true" ensures that the groups
	 * defined by the @Test method are also used by this method. The @Parameter
	 * aregument defines a list of expected parameters that are to be passed
	 * from a TestNG XML. The method arguments correspond to the number of
	 * arguments passed to the @Parameters line. This method sets local copies
	 * of the passed arguments and sets the virtual table path for the page
	 * classes to be used
	 */
	@BeforeMethod(alwaysRun = true)
	@Parameters({ "runLocation", "browserUnderTest", "environment" })
	public void setup(String runLocation, String browserUnderTest,
			String environment) {
		this.application = "alc";
		this.runLocation = runLocation;
		this.browserUnderTest = browserUnderTest;
		this.environment = environment;
		dt.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}

	/*
	 * Define a method that will be executed after each @Test method. The
	 * 
	 * @AfterTest argument "inheritGroups = true" ensures that the groups
	 * defined by the @Test method are also used by this method. The method
	 * argument is a TestNG object and contains information about the current
	 * test, such as the test name.
	 */
	@AfterMethod(alwaysRun = true)
	public synchronized void closeSession(ITestResult test) {
		WebDriver driver = drivers.get(testName);
		if (driver != null) {
			driver.quit();
		}
	}

	/*
	 * Define a method that contains the functionality under test NOTE: The
	 * 
	 * @Test annotation signifies a method that contains within a
	 * self-contained, complete test. Multiple "@Test" methods may be called in
	 * a single Test Class.
	 * 
	 * NOTE: The data provider name must match the name of a data provider
	 * included in this Test Class
	 * 
	 * NOTE: The "groups ={ }" is a collection of strings, intended to be used
	 * for gathering tests of a specific type and running then together, without
	 * having to explicitly maintain a list
	 * 
	 * NOTE: The number of data points passed by the data provider must match
	 * the number of method arguments
	 */
	@Test(dataProvider = "dataProvider", description = "Test PTOI Marketing With DLR Guest Facing Role, California Guest, Table Service Event, CC Payment, 3rd Party", groups = {
			"regression", "alc", "Search", "Discovery", "Offers", "Requests",
			"CC Guarantee", "Notifications",
			"Existing Reservation-Reservation tab" })
	public void ALC_createNewGuest(String testScenario, String role,
			String roleAndLocation, String SearchGuest, String newGuest,
			String discovery, String offer, String shopping, String paySettle,
			String summaryScenario, String modifyDiscovery, String modifySummary){
		/*
		 * Log the test scenario name in the reporter
		 */
		TestReporter.logScenario(testScenario);

		/*
		 * Grab the @Test method name and use it to define the test name
		 */
		testName = new Object() {
		}.getClass().getEnclosingMethod().getName();

		/*
		 * Initialize SeleniumWrapper to WDPRO integration and generate the
		 * driver to be used by the test The last argument in .initialize()
		 * force the use of the 32bit IE web driver
		 */
		WebDriverSetup setup = new WebDriverSetup(testName + testScenario);
		WebDriver driver = setup.initialize(application, browserUnderTest,
				runLocation, environment, "32");

		/*
		 * Add the new driver to drivers collection
		 */
		drivers.put(testName, driver);

		/*
		 * Launch App with info sent during driver initialization
		 */
		setup.launchApplication(driver);

		/*
		 * Log a simple description of each step Instantiate the necessary page
		 * class Assert that the page was loaded Interact with the classes
		 * methods
		 */
		TestReporter.logStep("Launch Hub with ALC URL");
		HubLoginPage hubPage = new HubLoginPage(driver);
		TestReporter.assertTrue(hubPage.pageLoaded(),
				"The Hub Page did not load.");
		hubPage.login(application, role);
		// Hub Login Window goes away, grab new pop up window
		hubPage.swapToApplication(application);

		TestReporter.logStep("Select Role And Location");
		RoleAndLocationPage roleLoc = new RoleAndLocationPage(driver);
		TestReporter.assertTrue(roleLoc.pageLoaded(),
				"The Role and Location Page did not load.");
		roleLoc.selectRoleAndLocation(roleAndLocation);

		TestReporter.logStep("Search for and Create a New Guest");
		SearchGuestPage searchGuest = new SearchGuestPage(driver);
		TestReporter.assertTrue(searchGuest.pageLoaded(),
				"Search Guest Tab failed to load");
		hubPage.getConversationId(application, environment);
		searchGuest.searchGuest(SearchGuest);

		TestReporter.logStep("Navigating to NewEditGuest page ");
		NewEditGuest newEditGuest = new NewEditGuest(driver);
		newEditGuest.clickDiscoverButton();

		TestReporter.logStep("Navigating to Discover page ");
		Discover discoverPage = new Discover(driver);
		TestReporter.assertTrue(discoverPage.pageLoaded(),
				"discover Page  failed to load");
		discoverPage.enterDiscoverCriteria(discovery);

		TestReporter.logStep("Navigating to offers page");
		Offers offersPage = new Offers(driver);
		TestReporter.assertTrue(offersPage.pageLoaded(),
				"Offer Page  failed to load");
		offersPage.getDefaultOfferValue();

		TestReporter.logStep("Navigating to ShoppingCart page");
		ShoppingCart shoppingcart = new ShoppingCart(driver);
		TestReporter.assertTrue(shoppingcart.pageLoaded(),
				"ShoppingCart Page  failed to load");
		shoppingcart.captureDetails();
		shoppingcart.BookOffer();

		TestReporter.logStep("Navigating to Summary Page");
		Summary summary = new Summary(driver);
		TestReporter.assertTrue(shoppingcart.pageLoaded(),
				"ShoppingCart Page  failed to load");
		String ReservationNo = summary.getReservationNumber();
		TestReporter.logStep("Reservation Number : " + ReservationNo);
		String resDetails = summary.getReservationDetails();
		TestReporter.logStep("Reservation Details : " + resDetails);

		TestReporter.logStep("Clicking on Done");
		summary.clickDone();

		TestReporter.logStep("Navigating to Main Tab Navigation Page");
		MainTabNavigation mainTab = new MainTabNavigation(driver);
		TestReporter.assertTrue(mainTab.pageLoaded(),
				"mainTab Page  failed to load");

		TestReporter.logStep("Navigating to Existing reservation tab ");
		mainTab.clickExistingReservationTab();

		// view
		TestReporter
				.logStep("Navigating to Main Tab Existing Reservation Gues tSearch Page");
		ExistingReservationGuestSearchPage existingResPage = new ExistingReservationGuestSearchPage(
				driver);
		TestReporter.assertTrue(existingResPage.pageLoaded(),
				"Existing Reservation Gues tSearch Page failed to load");

		TestReporter.logStep("Searching by Reservation Number");
		existingResPage.searchByReservationNumber(ReservationNo);

		TestReporter
				.logStep("Verify Reservation details for existing reservation");
		boolean getStatus = searchGuest
				.verify_ExistingReservation(ReservationNo);
		TestReporter
				.assertTrue(getStatus, " Specified Record does not exist!!");

		// clicking on View reservation button
		existingResPage.clickView();

		TestReporter
				.logStep("Navigating to Main Tab ExistingReservationReservationTab_DetailsTab");
		ExistingReservationReservationTab_DetailsTab resdetailsTab = new ExistingReservationReservationTab_DetailsTab(
				driver);
		TestReporter.assertTrue(resdetailsTab.pageLoaded(),
				"ExistingReservationReservationTab_DetailsTab failed to load");
		resdetailsTab.getDetails();

		TestReporter.logStep("Navigating to Main Tab TopNavigationBar");
		TopNavigationBar topNav = new TopNavigationBar(driver);
		TestReporter.assertTrue(topNav.pageLoaded(),
				"ExistingReservationReservationTab_DetailsTab failed to load");

		TestReporter.logStep("Clicking on Done");
		summary.clickDone();

		TestReporter.logStep("Navigating to Existing reservation tab ");
		mainTab.clickExistingReservationTab();

		// modify add notifications
		TestReporter.logStep("Adding Notification and verifying Notifications");
		existingResPage.searchByReservationNumber(ReservationNo);
		existingResPage.ButtonModify();
		resdetailsTab.addNotifications(internalTextBoxComment,
				externalTextBoxComment);
		boolean getstatusForNotifications = resdetailsTab.verify_Notifications(
				internalTextBoxComment, externalTextBoxComment);
		TestReporter.assertTrue(getstatusForNotifications,
				" Notification is not added!! ");

		TestReporter.logStep("Clicking on Done");
		summary.clickDone();

		// modify extra care
		TestReporter.logStep("Navigating to Existing reservation tab ");
		mainTab.clickExistingReservationTab();

		TestReporter.logStep("Searching by Reservation Number");
		existingResPage.searchByReservationNumber(ReservationNo);
		existingResPage.ButtonModify();
		resdetailsTab.clickExtraCare();
		resdetailsTab.clickCancel();
	}
}
