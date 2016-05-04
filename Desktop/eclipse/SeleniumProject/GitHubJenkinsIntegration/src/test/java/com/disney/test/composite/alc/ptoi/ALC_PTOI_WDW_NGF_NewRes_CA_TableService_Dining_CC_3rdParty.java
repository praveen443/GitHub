package test.composite.alc.ptoi;

package com.disney.test.composite.alc.ptoi;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import apps.alc.existingReservation.ExistingReservationConfirmPage;
import apps.alc.existingReservation.ExistingReservationDiscoverPage;
import apps.alc.existingReservation.ExistingReservationGuestSearchPage;
import apps.alc.existingReservation.ExistingReservationOffersPage;
import apps.alc.existingReservation.ExistingReservationPayment;
import apps.alc.existingReservation.ExistingReservationShoppingCartPage;
import apps.alc.existingReservation.ExistingReservationSummaryPage;
import apps.alc.existingReservation.reservationTab.ExistingReservationReservationTab_DetailsTab;
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
import utils.PTOI;
import utils.TestReporter;

/**
 * This class contains all fields, methods and classes required to test booking
 * a table service and Opting-Out of marketing preferences, modifying venue and
 * party mix and applying third-party preferences to the marketing preferences
 * 
 * @author Atmakuri Venkatesh
 * @version 1/25/2016 Atmakuri Venkatesh - original
 */
public class ALC_PTOI_WDW_NGF_NewRes_CA_TableService_Dining_CC_3rdParty {
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
	private Map<String, String> convos = new HashMap<String, String>();
	private Datatable dt = new Datatable();
	private String primaryName;
	private String reservationNumber;

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
					"WDW_NGF_Mod_Venue_CA_TableService_Dining_CC_3rdParty");
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
	@Test(dataProvider = "dataProvider", description = "Test PTOI Marketing With WDW Non-Guest Facing Role, California Guest, Table Service Event, CC Payment, 3rd Party", groups = {
			"regression", "alc", "Search", "Discovery", "Offers",
			"CC Guarantee", "PTOI", "PTOI window" })
	public void book(String testScenario, String role, String roleAndLocation,
			String guestSearch, String newGuest, String discovery,
			String offer, String shopping, String paySettle,
			String summaryScenario, String modifyDiscovery,
			String modifySummaryScenario, String dinnerPayment){
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

		TestReporter.logStep("Select Role And Location");
		RoleAndLocationPage roleLoc = new RoleAndLocationPage(driver);
		TestReporter.assertTrue(roleLoc.pageLoaded(),
				"The Role and Location Page loaded.");
		roleLoc.selectRoleAndLocation(roleAndLocation);

		TestReporter.logStep("Search for Guest");
		SearchGuestPage searchGuest = new SearchGuestPage(driver);
		TestReporter.assertTrue(searchGuest.pageLoaded(),
				"Search Guest Tab loaded");
		convos.put("book", hubPage.getConversationId(application, environment));
		searchGuest.searchGuest(guestSearch);

		TestReporter.logStep("Enter New Guest Information");
		NewEditGuest newEditGuest = new NewEditGuest(driver);
		TestReporter.assertTrue(newEditGuest.pageLoaded(),
				"New/Edit Guest Tab failed to load");
		newEditGuest.enterGuestInformation(newGuest);
		primaryName = newEditGuest.getPrimaryGuestFirstName() + " "
				+ newEditGuest.getPrimaryGuestLastName();

		TestReporter.logStep("Enter Discovery Information");
		Discover discover = new Discover(driver);
		TestReporter.assertTrue(discover.pageLoaded(),
				"The Discover Tab failed to load");
		discover.enterDiscoverCriteria(discovery);

		TestReporter.logStep("Select an Offer");
		Offers offers = new Offers(driver);
		TestReporter.assertTrue(offers.pageLoaded(),
				"The Offers Tab failed to load");
		offers.selectOffer(offer);

		TestReporter.logStep("Process Shopping Cart");
		ShoppingCart shoppingCart = new ShoppingCart(driver);
		TestReporter.assertTrue(shoppingCart.pageLoaded(),
				"The Shopping Cart Tab failed to load");
		shoppingCart.bookReservation(shopping);

		TestReporter.logStep("Setting Guarantee");
		Payment payment = new Payment(driver);
		TestReporter.assertTrue(payment.pageLoaded(),
				"The Payment Tab failed to load");
		payment.applySettlement(paySettle);

		TestReporter.logStep("Summary Page");
		Summary summary = new Summary(driver);
		TestReporter.assertTrue(summary.pageLoaded(),
				"The Summary Tab failed to load");
		reservationNumber = summary.getReservationNumber();
		TestReporter.assertTrue(!reservationNumber.isEmpty(),
				"The reservation number is an empty string.");
		TestReporter.log("Reservation Number: " + reservationNumber);
		summary.handlePtoi(summaryScenario, primaryName);

		TestReporter.logStep("Click Done");
		TopNavigationBar topNav = new TopNavigationBar(driver);
		TestReporter.assertTrue(topNav.pageLoaded(),
				"The Top Navigation failed to load");
		topNav.initialize();
		topNav.clickDone();
		verifyPtoiInDatabases(summaryScenario);

		convos.put("mod", hubPage.getConversationId(application, environment));
		TestReporter.logStep("Navigate to the Existing Reservation Tab");
		MainTabNavigation mainTab = new MainTabNavigation(driver);
		TestReporter.assertTrue(mainTab.pageLoaded(),
				"Existing Reservation failed to load");
		mainTab.clickExistingReservationTab();

		TestReporter.logStep("Search Existing Reservation Guest Search Page");
		ExistingReservationGuestSearchPage exResGSP = new ExistingReservationGuestSearchPage(
				driver);
		TestReporter.assertTrue(exResGSP.pageLoaded(),
				"Existing Reservation Guest Search Page Tab failed to load");
		exResGSP.searchGuestByNameAndReservationNumber(guestSearch,
				reservationNumber);

		TestReporter.logStep("Search Existing Reservation Guest Search Page");
		TestReporter.assertTrue(exResGSP.verifyExistingGuestResults(),
				"Expected Guest result not available");
		exResGSP.clickModify();

		TestReporter
				.logStep("Get reservation details from  Existing Reservation - Reservation - Details tab page");
		ExistingReservationReservationTab_DetailsTab exiResReservationTab_DetailsTab = new ExistingReservationReservationTab_DetailsTab(
				driver);
		TestReporter
				.assertTrue(exiResReservationTab_DetailsTab.pageLoaded(),
						"Existing Reservation - Reservation - Details Tab page failed to load");
		exiResReservationTab_DetailsTab.clickEdit();

		TestReporter.logStep("Enter Discovery Information");
		ExistingReservationDiscoverPage exResDiscoverPage = new ExistingReservationDiscoverPage(
				driver);
		TestReporter.assertTrue(exResDiscoverPage.pageLoaded(),
				"The Discover Tab failed to load");
		exResDiscoverPage.enterDiscoverCriteria(modifyDiscovery);

		TestReporter.logStep("Select an Offer");
		ExistingReservationOffersPage exResoffersPage = new ExistingReservationOffersPage(
				driver);
		TestReporter.assertTrue(exResoffersPage.pageLoaded(),
				"The Offers Tab failed to load");
		exResoffersPage.selectOffer(offer);

		TestReporter.logStep("Process Shopping Cart");
		ExistingReservationShoppingCartPage exiResShoppingCartpage = new ExistingReservationShoppingCartPage(
				driver);
		TestReporter.assertTrue(exiResShoppingCartpage.pageLoaded(),
				"The Shopping Cart Tab failed to load");
		exiResShoppingCartpage.acceptAllTermsAndModify();

		TestReporter.logStep("Confirm Page");
		ExistingReservationConfirmPage exiResConfirmPage = new ExistingReservationConfirmPage(
				driver);
		TestReporter.assertTrue(exiResConfirmPage.pageLoaded(),
				"The Confirm Page loaded");
		exiResConfirmPage.confirmModify();

		TestReporter.logStep("Apply Payment");
		ExistingReservationPayment exResPayment = new ExistingReservationPayment(
				driver);
		TestReporter.assertTrue(exResPayment.pageLoaded(),
				"The Payment Tab failed to load");
		exResPayment.applyPayment(dinnerPayment);

		TestReporter.logStep("Summary Page");
		ExistingReservationSummaryPage exRegSummary = new ExistingReservationSummaryPage(
				driver);
		TestReporter.assertTrue(exRegSummary.pageLoaded(),
				"The Summary Tab failed to load");
		exRegSummary.getPaymentOrBalanceDetails();
		reservationNumber = exRegSummary.getReservationNumber();
		TestReporter.assertTrue(!reservationNumber.isEmpty(),
				"The reservation number is an empty string.");
		TestReporter.log("Reservation Number: " + reservationNumber);
		exRegSummary.handlePtoi(modifySummaryScenario, primaryName);
		verifyPtoiInDatabases(modifySummaryScenario);
	}

	private void verifyPtoiInDatabases(String scenario) {
		dt.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
		dt.setVirtualtablePage("Summary");
		dt.setVirtualtableScenario(scenario);

		String expectedEmailOptStatus = dt.getDataParameter("EmailOptIn");

		String expectedMailOptStatus = dt.getDataParameter("MailOptIn");
		String expectedMarketBrand = dt.getDataParameter("MarketingBrand");

		PTOI ptoi = new PTOI();
		ptoi.validatePtoiDatabaseValues(environment, expectedEmailOptStatus,
				expectedMailOptStatus, expectedMarketBrand, convos,
				reservationNumber);
	}
}
