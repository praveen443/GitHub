package com.disney.test.composite.alc.ptoi;

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

import apps.alc.newReservation.Discover;
import apps.alc.newReservation.NewEditGuest;
import apps.alc.newReservation.Offers;
import apps.alc.newReservation.Payment;
import apps.alc.newReservation.SearchGuestPage;
import apps.alc.newReservation.ShoppingCart;
import apps.alc.newReservation.Summary;
import apps.alc.roleAndLocation.RoleAndLocationPage;
import apps.hub.HubLoginPage;
import core.WebDriverSetup;
import utils.Datatable;
import utils.PTOI;
import utils.TestReporter;

/**
 * This class contains all fields, methods and classes required to test booking
 * a dinning show and applying third-party preferences to the marketing
 * preferences
 * 
 * @author Waightstill W Avery
 * @version 12/17/2015 Waightstill W Avery - original
 */
@Listeners({ core.Screenshot.class })
public class ALC_PTOI_WDW_NGF_NewRes_CA_DinnerShow_CC_3rdParty {
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
			return dt.getTestScenariosByApp(application,
					"WDW_NGF_NewRes_CA_DinnerShow_CC_3rdParty");
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
	@Test(dataProvider = "dataProvider", description = "Test PTOI Marketing With DLR Non-Guest Facing Role, California Guest, Table Service Event, CC Payment, 3rd Party", groups = {
			"regression", "alc", "Search", "Discovery", "Offers", "CC Payment",
			"PTOI", "PTOI window" })
	public void book(String testScenario, String role, String roleAndLocation,
			String guestSearch, String newGuest, String discovery,
			String offer, String shopping, String paySettle,
			String summaryScenario) {
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
				"The Role and Location Page did not load.");
		roleLoc.selectRoleAndLocation(roleAndLocation);

		TestReporter.logStep("Search for and Create a New Guest");
		SearchGuestPage searchGuest = new SearchGuestPage(driver);
		TestReporter.assertTrue(searchGuest.pageLoaded(),
				"Search Guest Tab failed to load");
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
		payment.applyPayment(paySettle);

		TestReporter.logStep("Summary Page");
		Summary summary = new Summary(driver);
		TestReporter.assertTrue(summary.pageLoaded(),
				"The Summary Tab failed to load");
		reservationNumber = summary.getReservationNumber();
		TestReporter.assertTrue(!reservationNumber.isEmpty(),
				"The reservation number is an empty string.");
		TestReporter.log("Reservation Number: " + reservationNumber);
		summary.handlePtoi(summaryScenario, primaryName);

		verifyPtoiInDatabases(summaryScenario);
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
