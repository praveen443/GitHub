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
import apps.alc.existingReservation.reservationTab.ExistingReservationReservationTab_DetailsTab;
import apps.alc.existingReservation.reservationTab.ExistingReservationReservationTab_DetailsTab_CancelResvPage;
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
 *           Create ,View and Cancel the table Search.
 * @Precondition: N/A
 * @Author: Praveen Namburi.
 * @Version: 01/25/2016
 * @Return: N/A
 */
@Listeners({core.Screenshot.class })
public class ALC_CreateViewCancelTblSearch {
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
	private String ReservationDetails;
	private String Adults;
	private String Childs;
	private Datatable dt = new Datatable();
	private String guestName;

	/*
	 * Define a method that reads in data and returns a 2-dimensional array to
	 * be used by the test Give the data provider a unique name that will be
	 * referenced by test methods. In the method
	 * Datatable.getTestScenariosByApp(String, String), the first string is the
	 * application name which is defined later, and the second string is the
	 * test scenario name as it is found in the virtual tables
	 * 
	 */
	@DataProvider(name = "dataScenarios")
	public Object[][] scenarios() {
		try {
			// Defining what table to run from the virtual tables
			return dt.getTestScenariosByApp(application, "CreateViewAndCancelTableSearch");
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
	 * arguments passed to the @Parameters line. This method sets local copies
	 * of the passed arguments and sets the virtual table path for the page
	 * classes to be used
	 */
	@BeforeMethod(alwaysRun = true)
	@Parameters({ "runLocation", "browserUnderTest", "environment" })
	public void setup(String runLocation, String browserUnderTest, String environment) {
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
		if(driver != null){
			driver.quit();
		}
	}

	/*
	 * Define a method that contains the functionality under test NOTE:
	 * The @Test annotation signifies a method that contains within a
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
	@Test(dataProvider = "dataScenarios", description = "Create, View and Cancel the table search.", 
			groups = {
			"regression", "alc", "Search", "Existing Reservation-Reservation tab", "Discovery", 
			"Offers", "Add-Ons", "CC Guarantee", "Cancel"})
	public void CreateViewAndCancelTblSearch(String testScenario, String role, String roleAndLocation,
			String GuestSearchInfo, String SelectAndEnterTableSearchInfo, String SelectOffer,
			String AddtheAddONsAndValidate, String AddRequestAndSetAllTerms, String SetCCGuarantee,
			String ExistingGuestSearch, String CancelExistingReservation, String changeExistingGuestSearchStatus, String runtest,
			String Summary) {
	if (runtest.equalsIgnoreCase("true")) {
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
		WebDriver driver = setup.initialize(application, browserUnderTest, runLocation, environment, "32");

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
		TestReporter.assertTrue(hubPage.pageLoaded(), "The Hub Page did not load.");
		hubPage.login(application, role);
		// Hub Login Window goes away, grab new pop up window
		hubPage.swapToApplication(application);

		TestReporter.logStep("Select Role And Location");
		RoleAndLocationPage roleLoc = new RoleAndLocationPage(driver);
		TestReporter.assertTrue(roleLoc.pageLoaded(), "The Role and Location Page did not load.");
		roleLoc.selectRoleAndLocation(roleAndLocation);

		// Search and find the guest.
		TestReporter.logStep("Navigating to the New Reservation page.");
		SearchGuestPage searchGuest = new SearchGuestPage(driver);
		TestReporter.assertTrue(searchGuest.pageLoaded(), "Search Guest Tab failed to load.");
		TestReporter.logStep("Search and Add the guest");
		searchGuest.searchGuest(GuestSearchInfo);

		// Capture the Guest Search name
		TestReporter.logStep("Navigating to the New/Edit Guest Tab page.");
		NewEditGuest newEditGuest = new NewEditGuest(driver);
		TestReporter.assertTrue(newEditGuest.pageLoaded(), "New/Edit Guest Tab failed to load.");
		TestReporter.logStep("Captured Guest Search name is : " + newEditGuest.getDefaultSearchGuestname());

		TestReporter.logStep("Click on button - Discover.");
		newEditGuest.clickbtnDiscover();
		//Capture the guest's full name for use in PTOI validations
		if(!Summary.isEmpty()){
			guestName = newEditGuest.getPrimaryGuestFirstName() + " " + newEditGuest.getPrimaryGuestLastName();
		}

		// Discover Table Search activities
		TestReporter.logStep("Navigating to the DiscoverTab page.");
		Discover discoverPage = new Discover(driver);
		TestReporter.assertTrue(discoverPage.pageLoaded(), "The DiscoverTab page failed to load.");
		TestReporter.logStep("Discover the Table Search activities.");
		discoverPage.enterDiscoverCriteria(SelectAndEnterTableSearchInfo);

		// Select the Offer
		TestReporter.logStep("Navigating to the NewReservation Offers page.");
		Offers OffersPage = new Offers(driver);
		TestReporter.assertTrue(OffersPage.IsPageLoaded(), "The OffersTab page failed to load.");
		TestReporter.logStep("Select the Offer Time and GoToCart");
		OffersPage.selectTheOfferAndGotoCart(SelectOffer);

		// Navigate to shoppingCart page and Add-On products.
		TestReporter.logStep("Navigating to the ShoppingCart page.");
		ShoppingCart shoppingCartPage = new ShoppingCart(driver);
		TestReporter.assertTrue(shoppingCartPage.pageLoaded(), "ShoppingCart page did not Load.");
		TestReporter.logStep("Add the Add-ONs and Validate.");
		shoppingCartPage.addTheAddOnsAndValidateIt(AddtheAddONsAndValidate);

		// Set Accept All-terms and conditions and Click on Book.
		TestReporter.logStep("Add Requests and Set All-terms and conditions.");
		shoppingCartPage.clickbtnRequests();
		shoppingCartPage.addRequestsAndBook(AddRequestAndSetAllTerms);

		// Make the payment in SettlementUI Window.
		TestReporter.logStep("Navigating to the Payment page.");
		Payment paymentPage = new Payment(driver);
		TestReporter.assertTrue(paymentPage.pageLoaded(), "The Payment tab page is not loaded.");
		TestReporter.logStep("Enter card details and make payment in SettlementUI Window.");
		paymentPage.applySettlement(SetCCGuarantee);

		// Capture Reservation details and click on button - Done.
		TestReporter.logStep("Navigating to the Summary page.");
		Summary summaryPage = new Summary(driver);
		TestReporter.assertTrue(summaryPage.pageLoaded(), "SummaryTab page is not loaded.");
		TestReporter.logStep("Capturing the Reservation details and resv number from Summary tab page");
		ReservationDetails = summaryPage.getSummaryDetails();
		capturedResvNumber = summaryPage.getTheReservationNumber();
		Adults = summaryPage.getNumberOfAdultsDinnerShow();
		Childs = summaryPage.getNumberOfChildsDinnerShow();
		TestReporter.log("Reservation details : " + ReservationDetails);
		TestReporter.log("ResvNumber : " + capturedResvNumber);
		TestReporter.log("Adults : " + Adults);
		TestReporter.log("Childs : " + Childs);
		TestReporter.logStep("Click on button - Done.");
		//If PTOI is expected, handle the popup. If not, click Done.
		if(!Summary.isEmpty()){
			summaryPage.handlePtoi(Summary, guestName);
		}
		summaryPage.clickDone();

		// Click on Existing Reservation tab.
		TestReporter.logStep("Navigating to the New Reservation page.");
		MainTabNavigation mainTabNavigationPage = new MainTabNavigation(driver);
		TestReporter.assertTrue(mainTabNavigationPage.pageLoaded(), "MainTabNavigation is not loaded.");
		TestReporter.logStep("Click on Existing Reservation tab.");
		mainTabNavigationPage.clickExistingReservationTab();

		// Navigate to the ExistingReservationTab page,and
		// Enter GuestSearch info in ExistingResv Tab page.
		TestReporter.logStep("Navigating to the ExistingReservationGuestSearch Page");
		ExistingReservationGuestSearchPage existngResvGueSearchPage = new ExistingReservationGuestSearchPage(driver);
		TestReporter.assertTrue(existngResvGueSearchPage.pageLoaded(), "ExistngResvGuestSearchPage did not load.");
		TestReporter.logStep("Enter the GuestSearch details and Search for Reservation.");
		existngResvGueSearchPage.enterGuestSearchInfo(ExistingGuestSearch, capturedResvNumber);

		// Click on View button.
		TestReporter.logStep("Click on View");
		existngResvGueSearchPage.clickView();

		// Navigating to the Details tab page in Existingresv_ReservationTab.
		TestReporter.logStep("Navigating to the Details tab page in Existingresv_ReservationTab. ");
		ExistingReservationReservationTab_DetailsTab ExisResv_ResvTab_DetailsTabpage = new ExistingReservationReservationTab_DetailsTab(
				driver);
		TestReporter.assertTrue(ExisResv_ResvTab_DetailsTabpage.pageLoaded(),
				"DetailsTab page in Existingresv_ReservationTab did not load");

		// Get the Reservation details from details tab page.
		TestReporter.logStep("Get the Reservation details");
		ExisResv_ResvTab_DetailsTabpage.getDetails();

		// Click on Done.
		TestReporter.logStep("Click on Done.");
		summaryPage.clickDone();

		// Click on Existing Reservation tab.
		TestReporter.logStep("Navigating to the New Reservation page.");
		TestReporter.assertTrue(mainTabNavigationPage.pageLoaded(), "MainTabNavigation is not loaded.");
		TestReporter.logStep("Click on Existing Reservation tab");
		mainTabNavigationPage.clickExistingReservationTab();

		// Navigate to the ExistingReservationTab page,and
		// Search for Reservation.
		TestReporter.logStep("Navigating to the ExistingReservationGuestSearch Page");
		TestReporter.assertTrue(existngResvGueSearchPage.pageLoaded(), "ExistngResvGuestSearchPage did not load.");
		TestReporter.logStep("Search for Reservation");
		existngResvGueSearchPage.enterGuestSearchInfo(ExistingGuestSearch, capturedResvNumber);

		// Click on button Cancel.
		TestReporter.logStep("Click on button Cancel.");
		existngResvGueSearchPage.clickcbtnCancel();

		// Navigating to the Details tab page in Existingresv_ReservationTab.
		// Get the Reservation details from details tab page.
		TestReporter.logStep("Navigating to the Details tab page in Existingresv_ReservationTab. ");
		TestReporter.assertTrue(ExisResv_ResvTab_DetailsTabpage.pageLoaded(),
				"DetailsTab page in Existingresv_ReservationTab did not load");
		TestReporter.logStep("Get the Reservation details");
		ExisResv_ResvTab_DetailsTabpage.getDetails();

		// Verifying the Reservation details.
		TestReporter.logStep("Verifying the Reservation details.");
		ExisResv_ResvTab_DetailsTabpage.validateReservationDetails(ReservationDetails, ReservationDetails, Adults,
				Childs);

		// Click on button Cancel in Details tab page.
		TestReporter.logStep("Click on button Cancel.");
		ExisResv_ResvTab_DetailsTabpage.clickbtnCancel();

		// Navigate to the CancelResv page
		// Existingresv_ReservationTab_DetailsTab page.
		// Cancel the existing reservation.
		TestReporter.logStep("Navigating to the CancelResv page in Existingresv_ReservationTab_DetailsTab page.");
		ExistingReservationReservationTab_DetailsTab_CancelResvPage exisResv_ResvTab_DetailsTab_CancelResvPage = new ExistingReservationReservationTab_DetailsTab_CancelResvPage(
				driver);
		TestReporter.assertTrue(exisResv_ResvTab_DetailsTab_CancelResvPage.pageLoaded(),
				"CancelReservation page in Existingresv_ReservationTab_DetailsTabpage did not load");
		TestReporter.logStep("Cancel the existing reservation.");
		exisResv_ResvTab_DetailsTab_CancelResvPage.CancelReservationInfo(CancelExistingReservation);

		// Click on Done button.
		TestReporter.logStep("Click on Done button");
		summaryPage.clickDone();

		// Click on Existing Reservation tab.
		TestReporter.logStep("Navigating to the New Reservation page.");
		TestReporter.assertTrue(mainTabNavigationPage.pageLoaded(), "MainTabNavigation is not loaded.");
		TestReporter.logStep("Click on Existing Reservation tab");
		mainTabNavigationPage.clickExistingReservationTab();

		// Navigate to the ExistingReservationTab page,and
		// Search for Reservation.
		TestReporter.logStep("Navigating to the ExistingReservationGuestSearch Page");
		TestReporter.assertTrue(existngResvGueSearchPage.pageLoaded(), "ExistngResvGuestSearchPage did not load.");
		TestReporter.logStep("Search for Reservation by changing the status");
		existngResvGueSearchPage.enterGuestSearchInfo(changeExistingGuestSearchStatus, capturedResvNumber);

		// Click on View button.
		TestReporter.logStep("Click on View");
		existngResvGueSearchPage.clickView();

		// Navigating to the Details tab page in Existingresv_ReservationTab.
		// Get the Reservation details from details tab page.
		TestReporter.logStep("Navigating to the Details tab page in Existingresv_ReservationTab. ");
		TestReporter.assertTrue(ExisResv_ResvTab_DetailsTabpage.pageLoaded(),
				"DetailsTab page in Existingresv_ReservationTab did not load");
		TestReporter.logStep("Get the Reservation details");
		ExisResv_ResvTab_DetailsTabpage.getDetails();

		// Verifying the Reservation status
		TestReporter.logStep("Verifying the Reservation status");
		ExisResv_ResvTab_DetailsTabpage.validateCancelReservation();
	}
  }
}