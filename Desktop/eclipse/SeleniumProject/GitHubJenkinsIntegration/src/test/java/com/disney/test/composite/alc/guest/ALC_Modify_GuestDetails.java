package com.disney.test.composite.alc.guest;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import apps.alc.LeftNavigationBar.GuestInformationPage;
import apps.alc.LeftNavigationBar.ModifyGuestInformation;
import apps.alc.existingReservation.ExistingReservationGuestSearchPage;
import apps.alc.existingReservation.reservationTab.ExistingReservationReservationTab_DetailsTab;
import apps.alc.existingReservation.reservationTab.ExistingReservationReservationTab_HistoryTab;
import apps.alc.existingReservation.reservationTab.ExistingReservationReservationTab_TabNavigation;
import apps.alc.mainTabNavigation.MainTabNavigation;
import apps.alc.newReservation.Discover;
import apps.alc.newReservation.NewEditGuest;
import apps.alc.newReservation.Offers;
import apps.alc.newReservation.Payment;
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
 * This class contains all fields, methods and classes required to test the
 * Guest information created by table service and modify the guest details and
 * verify the guest details are been modified
 * 
 * @author Marella Satish
 * @version 01/08/2016 Marella Satish - original
 */
@Listeners({ core.Screenshot.class })
public class ALC_Modify_GuestDetails {
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
			return dt.getTestScenariosByApp(application, "ModifyGuestDetails");
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
	@Test(dataProvider = "dataProvider", description = "Test for Guest Search and create table service , modify guest details and verify guest information after modify With GF.Admin.IT.Administrator Role", groups = {
			"regression", "alc", "Search",
			"Existing Reservation-Reservation tab", "Discovery", "Offers",
			"CC Guarantee", "Modify" })
	public void modifyGuestDetails(String testScenario, String role,
			String roleAndLocation, String guestSearch, String guestInfo,
			String discoverTableService, String offerSelect,
			String setGuarantee, String guestModifiedSearch,
			String ModifyGuestInfo) {
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
		 * Initialize SeleniumWrapper to WDPRO intergration and generate the
		 * driver to be used by the test The last argument in .initialize()
		 * force the use of the 32bit IE webdriver
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
		driver.manage().deleteAllCookies();
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

		// Select Role and Location and swap to ALC application
		TestReporter
				.logStep("Select Role And Location and Swap to ALC Application");
		RoleAndLocationPage roleLoc = new RoleAndLocationPage(driver);
		TestReporter.assertTrue(roleLoc.pageLoaded(),
				"The Role and Location Page did not load.");
		roleLoc.selectRoleAndLocation(roleAndLocation);

		// Search for the guest information from new reservation
		TestReporter.logStep("Search for Guest with guest information");
		SearchGuestPage searchGuest = new SearchGuestPage(driver);
		TestReporter.assertTrue(searchGuest.pageLoaded(),
				"Search Guest Tab failed to load");
		searchGuest.searchGuest(guestSearch);

		// Enter guest information from newEditGuest Page and clicking on
		// discover
		TestReporter.logStep("Modify Guest Information and click discover");
		NewEditGuest newEditGuest = new NewEditGuest(driver);
		TestReporter.assertTrue(newEditGuest.pageLoaded(),
				"New/Edit Guest Tab failed to load");
		newEditGuest.enterGuestInformation(guestInfo);

		// Enter DIscover Criteria details from Discover Tab page
		TestReporter.logStep("Entering Discover Criteria details");
		Discover discover = new Discover(driver);
		TestReporter.assertTrue(discover.pageLoaded(),
				"Dicover Tab page failed to load");
		discover.enterDiscoverCriteria(discoverTableService);

		// Selecting offer and proceed to shopping cart
		TestReporter.logStep("Selecting Offer and proceed to shopping cart");
		Offers offers = new Offers(driver);
		TestReporter.assertTrue(offers.pageLoaded(),
				"Offers Tab page failed to load");
		offers.selectOffer(offerSelect);

		// Booking a reservation
		TestReporter
				.logStep("Check AcceptAllTerms and Clicks on Book from shopping cart tab page");
		ShoppingCart shoppingCart = new ShoppingCart(driver);
		TestReporter.assertTrue(shoppingCart.pageLoaded(),
				"ShoppingCart Tab page failed to load");
		TestReporter.logStep("Selected Offer Title : "
				+ shoppingCart.getSelectedOfferDetails());
		shoppingCart.acceptAllTermsAndBook();

		// Performing Settlement Credit Card Payment
		TestReporter
				.logStep("Set Settlement Credit Card Guarantee from Payment tab page");
		Payment payment = new Payment(driver);
		TestReporter.assertTrue(payment.pageLoaded(),
				"Payment Tab page failed to load");
		payment.applySettlement(setGuarantee);

		// Fetch reservation details from summary tab page and navigates to
		// home/default page
		TestReporter
				.logStep("Get Reservation details and Reservation number from Summary tab page");
		Summary summary = new Summary(driver);
		TestReporter.assertTrue(summary.pageLoaded(),
				"Summary Tab page failed to load");
		String ReservationDetails = summary.getSummaryDetails();
		TestReporter.logStep("Reservation Details : " + ReservationDetails);
		String ReservationNumber = summary.getReservationNumber();
		String Adults = summary.getNumberOfAdults();
		String Childs = summary.getNumberOfChilds();
		summary.clickDone();

		// Navigating to Existing Reservation tab page
		TestReporter
				.logStep("Navigating to Existing Reservation tab page from New Reservation tab page");
		MainTabNavigation mainTabNavigation = new MainTabNavigation(driver);
		TestReporter.assertTrue(mainTabNavigation.pageLoaded(),
				"New Reservation Tab page failed to load");
		mainTabNavigation.clickExistingReservationTab();

		// Perform search operation for existing guest which were booked
		// previously and clicks on view
		TestReporter
				.logStep("Search for the existing reservation from Existing Reservation - Guest Search tab page");
		ExistingReservationGuestSearchPage existingReservationGuestSearchPage = new ExistingReservationGuestSearchPage(
				driver);
		TestReporter.assertTrue(
				existingReservationGuestSearchPage.pageLoaded(),
				"Existing Reservation Tab page failed to load");
		existingReservationGuestSearchPage.enterGuestSearchInfo(
				guestModifiedSearch, ReservationNumber);
		existingReservationGuestSearchPage.clickView();

		// Grab the reservation details from details tab page
		TestReporter
				.logStep("Get reservation details from  Existing Reservation - Reservation - Details tab page");
		ExistingReservationReservationTab_DetailsTab exiResReservationTab_DetailsTab = new ExistingReservationReservationTab_DetailsTab(
				driver);
		TestReporter
				.assertTrue(exiResReservationTab_DetailsTab.pageLoaded(),
						"Existing Reservation - Reservation - Details Tab page failed to load");
		exiResReservationTab_DetailsTab.getDetails();

		// Validating the reservation details after book with the details
		// fetched from guest search
		TestReporter.logStep("Verifying Reservation Details");
		TestReporter
				.assertTrue(exiResReservationTab_DetailsTab.pageLoaded(),
						"Existing Reservation - Reservation - Details Tab page failed to load");
		exiResReservationTab_DetailsTab.validateReservationDetails(
				ReservationDetails, ReservationDetails, Adults, Childs);

		// Navigating to History tab page
		TestReporter
				.logStep("Navigate to Existing Reservation - Reservation - History tab page");
		ExistingReservationReservationTab_TabNavigation exiResReservationTab_TabNavigation = new ExistingReservationReservationTab_TabNavigation(
				driver);
		TestReporter
				.assertTrue(exiResReservationTab_TabNavigation.pageLoaded(),
						"Existing Reservation - Reservation - Details Tab page failed to load");
		exiResReservationTab_TabNavigation.clickHistoryTab();

		// Validating the Reservation status
		TestReporter.logStep("Verifying Reservation Activity");
		ExistingReservationReservationTab_HistoryTab exiResReservationTab_HistoryTab = new ExistingReservationReservationTab_HistoryTab(
				driver);
		TestReporter
				.assertTrue(exiResReservationTab_HistoryTab.pageLoaded(),
						"Existing Reservation - Reservation - History Tab page failed to load");
		exiResReservationTab_HistoryTab.validateReservationActivity();

		// Clicking on the Done button from Top Navigation Bar
		TestReporter.logStep("Clicks Done from Top Navigation Bar");
		TopNavigationBar topNav = new TopNavigationBar(driver);
		TestReporter.assertTrue(topNav.pageLoaded(),
				"The Top Navigation failed to load");
		topNav.clickDone();

		// Navigating to Existing Reservation tab page
		TestReporter
				.logStep("Navigating to Existing Reservation tab page from New Reservation tab page");
		TestReporter.assertTrue(mainTabNavigation.pageLoaded(),
				"New Reservation Tab page failed to load");
		mainTabNavigation.clickExistingReservationTab();

		// Perform search operation for existing guest which were booked
		// previously and clicks on Modify button
		TestReporter
				.logStep("Search for the existing reservation from Existing Reservation - Guest Search tab page");
		TestReporter.assertTrue(
				existingReservationGuestSearchPage.pageLoaded(),
				"Existing Reservation Tab page failed to load");
		existingReservationGuestSearchPage.enterGuestSearchInfo(
				guestModifiedSearch, ReservationNumber);
		existingReservationGuestSearchPage.ButtonModify();

		// Clicking on the Edit button on left navigation from Guest Information
		TestReporter
				.logStep("Click on Edit button on left navigation from Guest Information Page");
		GuestInformationPage guestInformationPage = new GuestInformationPage(
				driver);
		TestReporter.assertTrue(guestInformationPage.pageLoaded(),
				"Guest Information Page failed to load");
		guestInformationPage.clickEdit();

		// Enter the new guest information and save the updated guest details
		TestReporter
				.logStep("Enter the new guest information and click save button from Modify Guest Informaion page");
		ModifyGuestInformation modifyGuestInformation = new ModifyGuestInformation(
				driver);
		TestReporter.assertTrue(modifyGuestInformation.pageLoaded(),
				"Modify Guest Information Page failed to load");
		modifyGuestInformation.enterGuestInformation(ModifyGuestInfo);
		modifyGuestInformation.clickSave();

		// Clicking on the Edit button on left navigation from Guest Information
		TestReporter
				.logStep("Click on Edit button on left navigation from Guest Information Page");
		TestReporter.assertTrue(guestInformationPage.pageLoaded(),
				"Guest Information Page failed to load");
		guestInformationPage.clickEdit();

		// Validating the updated guest information with the actual guest data
		TestReporter
				.logStep("Validate guest information after modifying the data from modify guest informaion page");
		TestReporter.assertTrue(modifyGuestInformation.pageLoaded(),
				"Modify Guest Information Page failed to load");
		modifyGuestInformation.validateGuestInfo(ModifyGuestInfo);
		modifyGuestInformation.clickCancel();
	}
}
