package com.disney.test.composite.alc.book;

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
import apps.alc.existingReservation.reservationTab.ExistingReservationReservationTab_HistoryTab;
import apps.alc.existingReservation.reservationTab.ExistingReservationReservationTab_PaymentHistoryTab;
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
 * This class contains all fields, methods and classes required to test
 * 
 * @author Venkatesh A
 * @version 12/25/2015 Venkatesh A - Original
 */

@Listeners({ core.Screenshot.class })
public class ALC_CreateViewDSTSTermIDValid {

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
			return dt.getTestScenariosByApp(application,
					"ALC_CreateViewDSTSTermIDValid");
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
    @Parameters({"runLocation","browserUnderTest","environment"})
    public void setup(String runLocation, String browserUnderTest, String environment){
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
	@Test(dataProvider = "dataProvider", description = "DLR Guest Facing Role, DinnerShows, TermID Validationt", groups = {
			"regression", "alc", "Search", "Discovery", "CC Payment",
			"Payment History", "Payment History tab" })
	public void CreateViewDSTSTermIDValid(String testScenario, String role,
			String roleAndLocation, String searchGuest, String discover,
			String book, String payment) {
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

		TestReporter
				.logStep("Select Role And Location and Swap to ALC Application");
		RoleAndLocationPage roleLoc = new RoleAndLocationPage(driver);
		TestReporter.assertTrue(roleLoc.pageLoaded(),
				"The Role and Location Page did not load.");
		roleLoc.selectRoleAndLocation(roleAndLocation);

		TestReporter.logStep("Search for Guest");
		SearchGuestPage searchGuestPage = new SearchGuestPage(driver);
		TestReporter.assertTrue(searchGuestPage.pageLoaded(),
				"Search Guest Tab failed to load");
		searchGuestPage.searchGuest(searchGuest);

		TestReporter
				.logStep("Navigating to NewEditGuest page and Enter Guest Details");
		NewEditGuest newEditGuestPage = new NewEditGuest(driver);
		TestReporter.assertTrue(newEditGuestPage.pageLoaded(),
				"Edit Guest Tab failed to load");
		newEditGuestPage.clickbtnDiscover();

		TestReporter
				.logStep("Navigating to Discover page Set Discover Criteria");
		Discover discoverPage = new Discover(driver);
		TestReporter.assertTrue(discoverPage.pageLoaded(),
				"Discover Page Tab failed to load");
		discoverPage.enterDiscoverCriteria(discover);

		TestReporter.logStep("Navigating to offers page");
		Offers offersPage = new Offers(driver);
		TestReporter.assertTrue(offersPage.pageLoaded(),
				"Offer Page Tab failed to load");
		String defaultOfferValue = offersPage.getDefaultOfferValue();
		TestReporter.logStep("DefaultOfferOffer : " + defaultOfferValue);

		TestReporter.logStep("Navigating to shopping cart page");
		ShoppingCart shoppingCartPage = new ShoppingCart(driver);
		TestReporter.assertTrue(shoppingCartPage.pageLoaded(),
				"ShoppingCart Page Tab failed to load");
		String SelectedOffer = shoppingCartPage.returnSelectedOfferTitle();
		TestReporter.logStep("SelectedOfferTitle : " + SelectedOffer);

		TestReporter.logStep("Click Book");
		shoppingCartPage.bookReservation(book);

		TestReporter.logStep("Navigating to Payment page");
		Payment paymentPage = new Payment(driver);
		TestReporter.assertTrue(paymentPage.pageLoaded(),
				"Payment Page Tab failed to load");
		paymentPage.capturePaymentDetails();
		paymentPage.addPayment();
		paymentPage.takePayment(payment);

		TestReporter
				.logStep("Get Reservation details and Reservation number from Summary tab page");
		Summary summary = new Summary(driver);
		TestReporter.assertTrue(summary.pageLoaded(),
				"Summary Tab page failed to load");
		String ReservationDetails = summary.getSummaryDetails();
		TestReporter.log("Reservation Details : " + ReservationDetails);
		String ReservationNumber = summary.getReservationNumber();
		String Adults = summary.getNumberOfAdultsDinnerShow();
		String Childs = summary.getNumberOfChildsDinnerShow();
		summary.clickDone();

		TestReporter
				.logStep("Navigating to Existing Reservation Guest Search Page");
		MainTabNavigation tabNavigation = new MainTabNavigation(driver);
		tabNavigation.clickExistingReservationTab();

		TestReporter.logStep("Search Existing Reservation Guest Search Page");
		ExistingReservationGuestSearchPage exResGSP = new ExistingReservationGuestSearchPage(
				driver);
		TestReporter.assertTrue(exResGSP.pageLoaded(),
				"Existing Reservation Guest Search Page Tab failed to load");
		exResGSP.searchGuestByNameAndReservationNumber(searchGuest,
				ReservationNumber);

		TestReporter.logStep("Search Existing Reservation Guest Search Page");
		TestReporter.assertTrue(exResGSP.verifyExistingGuestResults(),
				"Expected Guest result not available");
		exResGSP.clickView();

		TestReporter
				.logStep("Get reservation details from  Existing Reservation - Reservation - Details tab page");
		ExistingReservationReservationTab_DetailsTab exiResReservationTab_DetailsTab = new ExistingReservationReservationTab_DetailsTab(
				driver);
		TestReporter
				.assertTrue(exiResReservationTab_DetailsTab.pageLoaded(),
						"Existing Reservation - Reservation - Details Tab page failed to load");

		TestReporter.logStep("Verifying Reservation Details");
		TestReporter
				.assertTrue(exiResReservationTab_DetailsTab.pageLoaded(),
						"Existing Reservation - Reservation - Details Tab page failed to load");
		exiResReservationTab_DetailsTab.validateReservationDetails(
				ReservationDetails, ReservationDetails, Adults, Childs);

		TestReporter
				.logStep("Navigate to Existing Reservation - Reservation - History tab page");
		ExistingReservationReservationTab_TabNavigation exiResReservationTab_TabNavigation = new ExistingReservationReservationTab_TabNavigation(
				driver);
		TestReporter
				.assertTrue(exiResReservationTab_TabNavigation.pageLoaded(),
						"Existing Reservation - Reservation - Details Tab page failed to load");
		exiResReservationTab_TabNavigation.clickHistoryTab();

		TestReporter.logStep("Verifying Reservation Activity");
		ExistingReservationReservationTab_HistoryTab exiResReservationTab_HistoryTab = new ExistingReservationReservationTab_HistoryTab(
				driver);
		TestReporter
				.assertTrue(exiResReservationTab_TabNavigation.pageLoaded(),
						"Existing Reservation - Reservation - History Tab page failed to load");
		exiResReservationTab_HistoryTab.validateReservationActivity();

		TestReporter
				.logStep("Navigate to Existing Reservation - Reservation - Payment History tab page");
		TestReporter
				.assertTrue(exiResReservationTab_TabNavigation.pageLoaded(),
						"Existing Reservation - Reservation - Details Tab page failed to load");
		exiResReservationTab_TabNavigation.clickPaymentHistoryTab();

		TestReporter.logStep("Verifying Reservation Activity");
		ExistingReservationReservationTab_PaymentHistoryTab paymentHistoryTab = new ExistingReservationReservationTab_PaymentHistoryTab(
				driver);
		paymentHistoryTab.validateTerminalID();

		TestReporter.logStep("Logout of ALC");
		TopNavigationBar topNav = new TopNavigationBar(driver);
		topNav.clickLogout();

		TestReporter.logStep("Validating Hub with Login Page");
		TestReporter.assertTrue(hubPage.pageLoaded(),
				"The Hub Page did not load.");
	}
}
