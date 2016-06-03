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

import apps.alc.existingReservation.ExistingReservationConfirmPage;
import apps.alc.existingReservation.ExistingReservationDiscoverPage;
import apps.alc.existingReservation.ExistingReservationGuestSearchPage;
import apps.alc.existingReservation.ExistingReservationOffersPage;
import apps.alc.existingReservation.ExistingReservationPayment;
import apps.alc.existingReservation.ExistingReservationShoppingCartPage;
import apps.alc.existingReservation.ExistingReservationSummaryPage;
import apps.alc.existingReservation.ExistingReservationTabNavigation;
import apps.alc.existingReservation.ExistingReservationVenueSearchPage;
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
import apps.dreams.PleaseWait.PleaseWait;
import apps.hub.HubLoginPage;
import core.WebDriverSetup;
import utils.Datatable;
import utils.TestReporter;

/**
 * This class contains all fields, methods and classes required to test the
 * Guest information created by recreation and verify the guest details
 * 
 * @author Stagliano, Dennis
 * @version 01/26/2016 Stagliano, Dennis - original
 */
@Listeners({ core.Screenshot.class })
public class ALC_CreateViewModifyTSDS {
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
					"CreateViewModify_TSDS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		 
	   /*
		* Define a method that will be executed prior to each @Test method.
		* The @BeforeTest argument "inheritGroups = true" ensures that the groups
		* defined by the @Test method are also used by this method. The @Parameter
		* aregument defines a list of expected parameters that are to be passed
		* from a TestNG XML. The method arguments correspond to the number of
		* arguments passed to the @Parameters line. This method sets local copies of
		* the passed arguments and sets the virtual table path for the page classes
		* to be used
	    */
   @BeforeMethod(alwaysRun = true)   
   @Parameters({"runLocation","browserUnderTest","environment"})
	public void setup(String runLocation, String browserUnderTest,
			String environment) {
		this.application = "alc";
		this.runLocation = runLocation;
		this.browserUnderTest = browserUnderTest;
		this.environment = environment;
		dt.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}
		 
	   /*
		* Define a method that will be executed after each @Test method.
	    * The @AfterTest argument "inheritGroups = true" ensures that the groups
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
	@Test(dataProvider = "dataProvider", description = "Test for CreateViewModifyTSDS Validation With GF.Admin.IT.Administrator Role", groups = {
			"regression", "alc" })
	public void CreateViewModifyTSDS(String testScenario, String role,
			String roleAndLocation, String searchGuest,
			String discoverScenario, String addRequests,
			String applySettlement, String searchExistingReservation,
			String existingReservationModify, String editGuest,
			String AddPaymentExistingReservation, String searchResByVenue){
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

		TestReporter.logStep("Select Role And Location");
		RoleAndLocationPage roleLoc = new RoleAndLocationPage(driver);
		TestReporter.assertTrue(roleLoc.pageLoaded(),
				"The Role and Location Page did not load.");
		roleLoc.selectRoleAndLocation(roleAndLocation);

		TestReporter.logStep("Creating Section of the Test Case");

		TestReporter.logStep("Entering Search Guest Page to search for Guest");
		SearchGuestPage sgp = new SearchGuestPage(driver);
		sgp.searchGuest(searchGuest);

		TestReporter.logStep("Creating Initial Reservation");
		TestReporter.logStep("Entering NewEditGuest");
		NewEditGuest neg = new NewEditGuest(driver);
		neg.enterGuestInformation(editGuest);

		TestReporter.logStep("Entering Discover Page");
		TestReporter
				.logStep("and populating all necessary fields in the Discover Page");
		Discover discover = new Discover(driver);
		discover.enterDiscoverCriteria(discoverScenario);

		TestReporter
				.logStep("Entering the Offers Page and selecting the default offer");
		Offers offers = new Offers(driver);
		TestReporter.assertTrue(offers.pageLoaded(),
				"Offers page failed to load");
		String offerTitle = offers.getDefaultOfferValue();
		TestReporter
				.logStep("Reporting the Default Offer Title for Initial Reservation");
		TestReporter.logStep("Default Offer Title = " + "[ " + offerTitle
				+ " ]");

		ShoppingCart cart = new ShoppingCart(driver);
		TestReporter.assertTrue(cart.pageLoaded(),
				"ShoppingCart page failed to load");
		TestReporter
				.logStep("Getting Activity Name and Selecting Requests for the Reservation");
		String activityName;
		activityName = cart.returnSelectedOfferTitle();
		TestReporter.logStep("Activity Name on ShoppingCart Page = " + "[ "
				+ activityName + " ]");
		cart.clickbtnRequests();
		PleaseWait.WaitForPleaseWaitWithTimeout(driver);
		cart.addRequests(addRequests);

		TestReporter.logStep("Booking the Reservation");
		cart.clickBookButton();

		Payment payment = new Payment(driver);
		TestReporter.assertTrue(payment.pageLoaded(),
				"Payment page failed to load");
		TestReporter.logStep("Setting the Guarantee with Credit Card Payment");
		payment.applySettlement(applySettlement);

		Summary summary = new Summary(driver);
		TestReporter.assertTrue(summary.pageLoaded(), "Verifying Summary Page");
		String getSummaryDet;
		TestReporter.logStep("Capture Summary Details After Payment");
		getSummaryDet = summary.getSummaryDetails();
		String reservationNumber;
		reservationNumber = summary.getTheReservationNumber();
		TestReporter.logStep("Reservation Number " + reservationNumber);
		TestReporter.logStep("Reservation Summary " + getSummaryDet);
		summary.clickDone();

		TestReporter.logStep("Navigating to the Existing Resrvation Tab");
		MainTabNavigation mtn = new MainTabNavigation(driver);
		mtn.clickExistingReservationTab();

		ExistingReservationGuestSearchPage ergs = new ExistingReservationGuestSearchPage(
				driver);
		ergs.initialize();
		ergs.pageLoaded();
		ergs.searchGuestByNameAndReservationNumber(searchExistingReservation,
				reservationNumber);
		TestReporter
				.logStep("Searching the Initial Reservation by Reservation Number");
		TestReporter
				.logStep("Verify the proper result came back of one record");
		boolean recordCameBack;
		recordCameBack = ergs.verifyExistingGuestResults();
		TestReporter
				.logStep("Verify a Record Returned - Result of the record verification = "
						+ recordCameBack);
		TestReporter.logStep("Viewing and Reporting the  Reservation Details");
		ergs.clickView();

		TestReporter.logStep("Viewing Section of the Test Case");

		ExistingReservationReservationTab_DetailsTab rtDetails = new ExistingReservationReservationTab_DetailsTab(
				driver);
		rtDetails.initialize();
		TestReporter.assertTrue(rtDetails.pageLoaded(),
				"Verifying ExistingReservationReservationTab_DetailsTab Page");
		TestReporter
				.logStep("Reporting Complete Details on the Initial Reservation");
		String details = rtDetails.getCompleteFirstReservationDetails();
		TestReporter.logStep(details);

		TestReporter.logStep("Clicking Done");
		TopNavigationBar done = new TopNavigationBar(driver);
		done.clickDone();

		TestReporter
				.logStep("Search for the Booked Existing Reservation So That it can be Modified");
		MainTabNavigation mainNav = new MainTabNavigation(driver);
		mainNav.clickExistingReservationTab();

		TestReporter.logStep("Modifying Section of the Test Case");

		ExistingReservationGuestSearchPage exgs = new ExistingReservationGuestSearchPage(
				driver);
		TestReporter.assertTrue(exgs.pageLoaded(),
				"Verifying ExistingReservationReservationGuestSearchPage");
		exgs.searchByReservationNumber(reservationNumber);
		TestReporter.logStep("Clicking the Modify button");
		exgs.clickModify();

		ExistingReservationReservationTab_DetailsTab tabDetail = new ExistingReservationReservationTab_DetailsTab(
				driver);
		TestReporter
				.assertTrue(tabDetail.pageLoaded(),
						"Verifying ExistingReservationReservationTab_DetailsTab Page Loaded");
		String statusHeader = tabDetail
				.getReservationDetailsHeaderInformation();
		TestReporter.logStep("Reporting the Reservation Status = "
				+ statusHeader);
		TestReporter
				.logStep("Continue with Modifying the Existing Reservation by Entering ExistingReservation >> Discover Page");
		tabDetail.clickEdit();

		ExistingReservationDiscoverPage erdp = new ExistingReservationDiscoverPage(
				driver);
		erdp.pageLoaded();
		erdp.enterDiscoverCriteria(existingReservationModify);

		TestReporter.logStep("Entering Offers Page and Selecting First Offer");
		ExistingReservationOffersPage erop = new ExistingReservationOffersPage(
				driver);
		erop.initialize();
		TestReporter.logStep("Clicking the Shopping Cart Button");
		erop.clickShoppingCart();

		TestReporter.logStep("Getting First Offer Reservation Details");
		ExistingReservationShoppingCartPage ersc = new ExistingReservationShoppingCartPage(
				driver);
		TestReporter.assertTrue(ersc.pageLoaded(),
				"Verifying ExistingReservationShoppingCartPage");
		TestReporter.logStep("Entering Offers Page and Selecting First Offer");
		TestReporter.logStep("Modified Existing Reservation Offer Details");
		ersc.offerDetails();

		TestReporter.logStep("Entering Guest Requests");
		TestReporter
				.logStep("Clearing All Requests CheckBoxes so that the Requests can be reset to Other values");
		ersc.clickDefaultRequestsButton();
		ersc.clearAllRequestCheckBoxes();
		TestReporter.logStep("Accepting All Terms and Clicking Modify");
		ersc.acceptAllTermsAndModify();

		ExistingReservationConfirmPage erc = new ExistingReservationConfirmPage(
				driver);
		TestReporter.assertTrue(erc.pageLoaded(),
				"Verifying ExistingReservationConfirmPage");
		TestReporter
				.logStep("Reporting the New Booking Details and Original Booking Details");
		erc.reportNewBookingDetails();
		erc.reportOriginalBookingDetails();
		TestReporter.logStep("Confirming the New Offer");
		erc.acceptAndConfirmNewOffer();

		TestReporter.logStep("Adding the Payment to the Modified Reservation");

		ExistingReservationPayment erPay = new ExistingReservationPayment(
				driver);
		TestReporter.assertTrue(erPay.pageLoaded(),
				"Verifying ExistingReservationPayment");
		erPay.applyPayment(AddPaymentExistingReservation);

		ExistingReservationSummaryPage erSumm = new ExistingReservationSummaryPage(
				driver);
		TestReporter.assertTrue(erSumm.pageLoaded(),
				"Verifying ExistingReservationSummaryPage");

		// New Reservation Number
		String newReservationNumber;
		newReservationNumber = erSumm.getReservationNumber();
		TestReporter
				.logStep("Capturing the New reservation Number for the Modification");
		TestReporter
				.logStep("New Reservation Number = " + newReservationNumber);

		TestReporter
				.logStep("Complete Summary Details for the new Reservation");
		erSumm.ReportCompleteSummaryDetails();

		TopNavigationBar done1 = new TopNavigationBar(driver);
		done1.clickDone();

		TestReporter.logStep("Search for the Booked Existing Reservation");
		MainTabNavigation mainNav2 = new MainTabNavigation(driver);
		mainNav2.clickExistingReservationTab();

		ExistingReservationGuestSearchPage existgs = new ExistingReservationGuestSearchPage(
				driver);
		TestReporter.assertTrue(existgs.pageLoaded(),
				"Verifying ExistingReservationReservationGuestSearchPage");
		ExistingReservationTabNavigation erTabNav = new ExistingReservationTabNavigation(
				driver);
		erTabNav.clickVenuesearchTab();

		ExistingReservationVenueSearchPage ervsp = new ExistingReservationVenueSearchPage(
				driver);
		TestReporter.assertTrue(ervsp.pageLoaded(),
				"Verifying ExistingReservationVenueSearchPage");
		ervsp.searchReservationByVenue(searchResByVenue);
		int rCount;
		rCount = ervsp.findNumberOfResults();
		TestReporter.logStep("Records found for Venue search = " + rCount);

		TestReporter.logStep("Locate Results and Click View");
		TestReporter.assertTrue(ervsp.pageLoaded(),
				"Verifying ExistingReservationVenueSearchPage");
		ervsp.selectGuestSearchRecord(rCount, newReservationNumber);

		TestReporter.logStep("Reporting the Summary of the new Reservation");
		ExistingReservationReservationTab_DetailsTab tabD = new ExistingReservationReservationTab_DetailsTab(
				driver);
		TestReporter.assertTrue(tabD.pageLoaded(),
				"Verifying ExistingReservationReservationTabb_DetailsTab");
		String detailsSummary;
		detailsSummary = tabD.getCompleteFirstReservationDetails();
		TestReporter.logStep(detailsSummary);
	}
}
