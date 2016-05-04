package com.disney.test.composite.alc.modify;

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

import apps.alc.existingReservation.ExistingReservationGuestSearchPage;
import apps.alc.existingReservation.reservationTab.ExistingReservationReservationTab_DetailsTab;
import apps.alc.existingReservation.reservationTab.ExistingReservationReservationTab_DetailsTab_GuestRequests;
import apps.alc.mainTabNavigation.MainTabNavigation;
import apps.alc.newReservation.Discover;
import apps.alc.newReservation.NewEditGuest;
import apps.alc.newReservation.Offers;
import apps.alc.newReservation.SearchGuestPage;
import apps.alc.newReservation.ShoppingCart;
import apps.alc.newReservation.Summary;
import apps.alc.roleAndLocation.RoleAndLocationPage;
import apps.hub.HubLoginPage;
import core.WebDriverSetup;
import utils.Datatable;
import utils.TestReporter;

/**
 * @Summary: This class contains all fields, methods and classes required for
 *           updating the Special Requests.
 * @Precondition: N/A
 * @Author: Praveen Namburi.
 * @Version: 01/08/2016
 * @Return: N/A
 */
@Listeners({ core.Screenshot.class })
public class ALC_SpecialRequest_Update {
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
	private String capturedResvNumber;
	private String Adults;
	private String Childs;
	private String ReservationDetails;

	/*
	 * Define a method that reads in data and returns a 2-dimensional array to
	 * be used by the test Give the data provider a unique name that will be
	 * referenced by test methods. In the method
	 * Datatable.getTestScenariosByApp(String, String), the first string is the
	 * application name which is defined later, and the second string is the
	 * test scenario name as it is found in the virtual tables
	 */
	@DataProvider(name = "dataScenarios")
	public Object[][] scenarios() {
		try {
			// Defining what table to run from the virtual tables
			Datatable dt = new Datatable();
			return dt.getTestScenariosByApp(application,
					"SpecialRequestsUpdate");
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
	@Test(dataProvider = "dataScenarios", description = "Create a TableService and View it,Modify and Update special requests.", groups = {
			"regression", "alc", "Find/Add guest", "Discover TableService",
			"SelectOffer", "ShoppingCart", "Add Requests", "Accept AllTerms",
			"Search ExistingReservtion", "Verify Reservation details",
			"Modify requests" })
	public void SpecialRequestupdate(String testScenario, String role,
			String roleAndLocation, String GuestSearchInfo,
			String SelectAndEnterTableServiceInfo, String AddRequests,
			String ExistingGuestSearch, String ModifyAndUpdateSpecialRequest){
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

		// Search and find the guest.
		TestReporter.logStep("Navigating to the New Reservation page.");
		SearchGuestPage searchGuest = new SearchGuestPage(driver);
		TestReporter.assertTrue(searchGuest.pageLoaded(),
				"Search Guest Tab failed to load");
		TestReporter.logStep("Search for guests to add.");
		searchGuest.searchGuest(GuestSearchInfo);

		// Capture the Guest Search name
		TestReporter.logStep("Navigating to the New/Edit Guest Tab page.");
		NewEditGuest newEditGuest = new NewEditGuest(driver);
		TestReporter.assertTrue(newEditGuest.pageLoaded(),
				"New/Edit Guest Tab failed to load");
		TestReporter.log("Captured Guest Search name is : "
				+ newEditGuest.getDefaultSearchGuestname());

		TestReporter.logStep("Click on button - Discover.");
		newEditGuest.clickbtnDiscover();

		// Select the Discover Criteria and Enter the Table Service info.
		TestReporter.logStep("Navigating to the DiscoverTab page.");
		Discover discoverPage = new Discover(driver);
		TestReporter.assertTrue(discoverPage.pageLoaded(),
				"The DiscoverTab page is not loaded.");
		TestReporter
				.logStep("Select the Discover Criteria and Enter the Table Service info.");
		discoverPage.enterDiscoverCriteria(SelectAndEnterTableServiceInfo);

		// Get the offer title default value, and
		// Click on button - Go to shopping cart.
		TestReporter.logStep("Navigating to the Offers-Tab page.");
		Offers OffersPage = new Offers(driver);
		TestReporter.assertTrue(OffersPage.pageLoaded(),
				"The Offers-Tab page is not loaded.");
		TestReporter
				.logStep("Get default Offer value and Click on button - Go to Shopping Cart.");
		OffersPage.getDefaultOfferValue();

		// Click on button Requests.
		TestReporter.logStep("Navigating to the ShoppingCart page.");
		ShoppingCart shoppingCartPage = new ShoppingCart(driver);
		TestReporter.assertTrue(shoppingCartPage.pageLoaded(),
				"ShoppingCart page did not Load.");
		TestReporter.log("Total Price : " + shoppingCartPage.getTotalPrice());
		TestReporter.log("Offer Price : " + shoppingCartPage.getPrice());
		TestReporter.logStep("Click on button - Requests.");
		shoppingCartPage.clickbtnRequests();

		// Add Requests and Click on Book.
		TestReporter.logStep("Add Requests in ShoppingCart page.");
		shoppingCartPage.addRequestsAndBook(AddRequests);

		// Capture Reservation details
		TestReporter.logStep("Navigating to the Summary page.");
		Summary summaryPage = new Summary(driver);
		TestReporter.assertTrue(summaryPage.pageLoaded(),
				"SummaryTab page is not loaded.");
		TestReporter
				.logStep("Capturing the Reservation details and resv number from Summary tab page");
		ReservationDetails = summaryPage.getSummaryDetails();
		TestReporter.log("Reservation details : " + ReservationDetails);
		capturedResvNumber = summaryPage.getTheReservationNumber();
		TestReporter.log("ResvNumber : " + capturedResvNumber);
		Adults = summaryPage.getNumberOfAdults();
		Childs = summaryPage.getNumberOfChilds();

		// Click on button - Done.
		TestReporter.logStep("Click on button - Done.");
		summaryPage.clickDone();

		// Click on Existing Reservation tab.
		TestReporter.logStep("Navigating to the New Reservation page.");
		MainTabNavigation mainTabNavigationPage = new MainTabNavigation(driver);
		TestReporter.assertTrue(mainTabNavigationPage.pageLoaded(),
				"MainTabNavigation is not loaded.");
		TestReporter.logStep("Click on Existing Reservation tab.");
		mainTabNavigationPage.clickExistingReservationTab();

		// Navigate to the ExistingReservationTab page,AND
		// Enter GuestSearch info in ExistingResv Tab page.
		TestReporter
				.logStep("Navigating to the ExistingReservationGuestSearch Page");
		ExistingReservationGuestSearchPage existngResvGueSearchPage = new ExistingReservationGuestSearchPage(
				driver);
		TestReporter.assertTrue(existngResvGueSearchPage.pageLoaded(),
				"ExistngResvGuestSearchPage did not load.");
		TestReporter
				.logStep("Enter the GuestSearch details and Search for Reservation.");
		existngResvGueSearchPage.enterGuestSearchInfo(ExistingGuestSearch,
				capturedResvNumber);

		// Click on View.
		TestReporter.logStep("Click on View.");
		existngResvGueSearchPage.clickView();

		// Navigating to the Details tab page in Existingresv_ReservationTab.
		TestReporter
				.logStep("Navigating to the Details tab page in Existingresv_ReservationTab. ");
		ExistingReservationReservationTab_DetailsTab ExisResv_ResvTab_DetailsTabpage = new ExistingReservationReservationTab_DetailsTab(
				driver);
		TestReporter.assertTrue(ExisResv_ResvTab_DetailsTabpage.pageLoaded(),
				"DetailsTab page in Existingresv_ReservationTab did not load");
		TestReporter
				.logStep("Get reservation details from  Existing Reservation - Reservation - Details tab page");
		ExisResv_ResvTab_DetailsTabpage.getDetails();

		TestReporter.logStep("Verifying the Reservation details.");
		ExisResv_ResvTab_DetailsTabpage.validateReservationDetails(
				ReservationDetails, ReservationDetails, Adults, Childs);

		// Click on button done.
		TestReporter.logStep("Click on button Done.");
		summaryPage.clickDone();

		// Click on Existing Reservation tab.
		TestReporter.logStep("Click on Existing Reservation tab.");
		mainTabNavigationPage.clickExistingReservationTab();

		// Enter GuestSearch details and Search for Reservation.
		TestReporter
				.logStep("Enter GuestSearch details and Search for Reservation.");
		existngResvGueSearchPage.enterGuestSearchInfo(ExistingGuestSearch,
				capturedResvNumber);

		// Click on Modify
		TestReporter.logStep("Click on Modify.");
		existngResvGueSearchPage.ButtonModify();

		// Click Edit button for Guest Requests
		TestReporter.logStep("Click Edit button for Guest Requests.");
		ExisResv_ResvTab_DetailsTabpage.clickSpecialRequestEditbtn();

		// Modify And Update SpecialRequest in Guest Requests page.
		TestReporter.logStep("Navigating to the Guest Requests page.");
		ExistingReservationReservationTab_DetailsTab_GuestRequests exisResv_ResvTab_DetailsTabpage_GuestReqtsPage = new ExistingReservationReservationTab_DetailsTab_GuestRequests(
				driver);
		TestReporter
				.assertTrue(exisResv_ResvTab_DetailsTabpage_GuestReqtsPage
						.pageLoaded(),
						"The Guest Requests page in exisResv_ResvTab_DetailsTab did not load.");
		TestReporter.logStep("Modify and Update special guest requests.");
		exisResv_ResvTab_DetailsTabpage_GuestReqtsPage
				.modifyAndSaveSpecialRequests(ModifyAndUpdateSpecialRequest);
	}
}

