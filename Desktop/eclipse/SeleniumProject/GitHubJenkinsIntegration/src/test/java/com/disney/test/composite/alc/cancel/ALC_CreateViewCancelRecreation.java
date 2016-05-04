package com.disney.test.composite.alc.cancel;

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
import apps.alc.existingReservation.ExistingReservationRefundPage;
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
import apps.hub.HubLoginPage;
import core.WebDriverSetup;
import utils.Datatable;
import utils.TestReporter;

/**
 * @Summary: This class contains all fields, methods and classes required to
 *           test Creating, Viewing and Canceling a Recreation
 * @author: Stagliano, Dennis
 * @version: 12/28/2015 Stagliano, Dennis - original
 */
@Listeners({ core.Screenshot.class })
public class ALC_CreateViewCancelRecreation {

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
					"CreateViewCancel_Recreation");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Define a method that will be executed prior to each @Test method. The
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
	@Test(dataProvider = "dataProvider", description = "Create, View and Cancel Recreation", groups = {
			"regression", "alc", "Search", "Discovery", "Offers", "CC Payment" })
	public void book(String testScenario, String role, String roleAndLocation,
			String CVC_Rec_Guest, String EditGuest, String DiscoverCriteria,
			String SelectOffer, String VenueInformation, String PaySettle,
			String ContactName, String ReasonForCancel) {
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
		// driver.manage().deleteAllCookies();
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

		TestReporter.logStep("Entering Search Guest Page to search for Guest");
		SearchGuestPage sgp = new SearchGuestPage(driver);
		TestReporter.assertTrue(sgp.pageLoaded(),
				"SearchGuestPage page failed to load");
		sgp.searchGuest(CVC_Rec_Guest);

		TestReporter.logStep("Creating the Reservation");
		TestReporter.logStep("Entering NewEditGuest to edit new guest");

		NewEditGuest neg = new NewEditGuest(driver);
		neg.enterGuestInformation(EditGuest);
		TestReporter.assertTrue(neg.pageLoaded(),
				"NewEditGuest page failed to load");

		TestReporter.logStep("Entering Existing Reservation");
		TestReporter.logStep("and populating all fields in the Discover Page");

		Discover discover = new Discover(driver);
		TestReporter.assertTrue(discover.pageLoaded(),
				"Discover page failed to load");
		discover.enterDiscoverCriteria(DiscoverCriteria);

		TestReporter
				.logStep("Entering the Offers Page and selecting the default offer");
		Offers offers = new Offers(driver);
		TestReporter.assertTrue(offers.pageLoaded(),
				"Offers page failed to load");
		offers.getDefaultOfferValue();

		TestReporter.logStep("Getting Offer Title on Shopping Cart Page");
		ShoppingCart cart = new ShoppingCart(driver);
		TestReporter.assertTrue(cart.pageLoaded(),
				"ShoppingCart page failed to load");
		String offerTitle = cart.returnSelectedOfferTitle();
		TestReporter.logStep("Selected Offer Title = " + offerTitle);

		// must have a price
		String OfferTotalPrice = cart.returnSelectedOfferTotalPrice();
		TestReporter.logStep("Selected Offer Total Price = " + OfferTotalPrice);
		TestReporter.logStep("Booking Reservation");
		cart.bookReservation(VenueInformation);

		TestReporter.logStep("Applying Payment");
		Payment payment = new Payment(driver);
		// clicking the Add Payment Button
		payment.applyPayment(PaySettle);

		TestReporter.logStep("Writing out the Summary Details");
		Summary summary = new Summary(driver);
		TestReporter.assertTrue(summary.pageLoaded(),
				"Summary page failed to load");
		String reservationDetails = summary.getReservationDetails();
		TestReporter.logStep("Reservation Details = " + reservationDetails);

		// Putting reservation number in a local variable for use later
		String reservationNumber;
		reservationNumber = summary.getReservationNumber();
		System.out.println("Reservation Number =  - " + reservationNumber);
		TestReporter.logStep("Reservation Created for Recreation");

		summary.clickDone();

		MainTabNavigation mtn = new MainTabNavigation(driver);
		TestReporter.assertTrue(mtn.pageLoaded(),
				"MainTabNavigation was not found");
		TestReporter.logStep("Reservation Viewing and Cancel Section");
		mtn.clickExistingReservationTab();
		ExistingReservationGuestSearchPage ergs = new ExistingReservationGuestSearchPage(
				driver);
		TestReporter.assertTrue(ergs.pageLoaded(),
				"ExistingReservationGuestSearchPage failed to load");

		ergs.searchByReservationNumberToCancel(reservationNumber);
		TestReporter.logStep("Getting reservation details before canceling");

		ExistingReservationReservationTab_DetailsTab ertd = new ExistingReservationReservationTab_DetailsTab(
				driver);
		TestReporter.assertTrue(ertd.pageLoaded(),
				"ExistingReservationReservationTab_DetailsTab failed to load");

		String PartyDetails = ertd.getCompleteFirstReservationDetails();
		TestReporter.logStep(PartyDetails);

		TestReporter.logStep("Canceling Venue Specific Reservation");
		ertd.cancelExistingReservationDetailsTab(ContactName, ReasonForCancel);
		TestReporter.logStep("Getting cancel refund details");
		ExistingReservationRefundPage err = new ExistingReservationRefundPage(
				driver);
		String refundDue = err.totalRefundAmountDue();
		TestReporter.logStep("Refund Details = Amount Due = " + refundDue);

		err.clickRefundAll();
		err.clickRefundClose();
		ExistingReservationRefundPage err2 = new ExistingReservationRefundPage(
				driver);
		TestReporter.assertTrue(err2.pageLoaded(),
				"ExistingReservationReservationTab_DetailsTab failed to load");
		err2.clickContinue();
		err2.writeOutCompleteRefundSummary();
	}
}
