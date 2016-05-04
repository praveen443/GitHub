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
import apps.hub.HubLoginPage;
import core.WebDriverSetup;
import utils.Datatable;
import utils.TestReporter;

/**
 * @Summary: create view Modify CAR
 * @Precondition: N/A
 * @Author: Lalitha Banda
 * @Version: 1/28/2016
 * @Return: N/A
 */
public class ALC_CreateViewModifyCAR {
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
			return dt.getTestScenariosByApp(application, "CreateViewModifyCAR");
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
	@Test(dataProvider = "dataProvider", description = "Test PTOI Marketing With DLR Guest Facing Role, California Guest, Table Service Event, CC Payment, 3rd Party", groups = {
			"regression", "alc", "Create Guest", "New Reservation-Discover tab" })
	public void ALC_createNewGuest(String testScenario, String role,
			String roleAndLocation, String SearchGuest, String discovery,
			String paySettle, String venueInfo, String DiscoverRecreation,
			String venueInfowithAdult, String payAmount) {
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
		// conversationId = searchGuest.getConversatinID();
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
		shoppingcart.bookReservation(venueInfo);

		TestReporter
				.logStep("Take Payment through Credit from Payment tab page");
		Payment payment = new Payment(driver);
		TestReporter.assertTrue(payment.pageLoaded(),
				"Payment Tab page failed to load");
		payment.applySettlement(paySettle);

		TestReporter
				.logStep("Get Reservation details and Reservation number from Summary tab page");
		Summary summary = new Summary(driver);
		TestReporter.assertTrue(summary.pageLoaded(),
				"Summary Tab page failed to load");
		String ReservationDetails = summary.getSummaryDetails();
		TestReporter.logStep("Reservation Details : " + ReservationDetails);
		String ReservationNumber = summary.getReservationNumber();
		String Adults = summary.getNumberOfAdultsDinnerShow();
		String Childs = summary.getNumberOfChildsDinnerShow();
		summary.clickDone();

		TestReporter
				.logStep("Navigating to Existing Reservation tab page from New Reservation tab page");
		MainTabNavigation mainTabNavigation = new MainTabNavigation(driver);
		TestReporter.assertTrue(mainTabNavigation.pageLoaded(),
				"New Reservation Tab page failed to load");
		mainTabNavigation.clickExistingReservationTab();

		TestReporter
				.logStep("Search for the existing reservation from Existing Reservation - Guest Search tab page");
		ExistingReservationGuestSearchPage existingReservationGuestSearchPage = new ExistingReservationGuestSearchPage(
				driver);
		TestReporter.assertTrue(
				existingReservationGuestSearchPage.pageLoaded(),
				"Existing Reservation Tab page failed to load");
		existingReservationGuestSearchPage
				.searchGuestByNameAndReservationNumber(SearchGuest,
						ReservationNumber);
		existingReservationGuestSearchPage.clickView();

		TestReporter
				.logStep("Get reservation details from  Existing Reservation - Reservation - Details tab page");
		ExistingReservationReservationTab_DetailsTab exiResReservationTab_DetailsTab = new ExistingReservationReservationTab_DetailsTab(
				driver);
		TestReporter
				.assertTrue(exiResReservationTab_DetailsTab.pageLoaded(),
						"Existing Reservation - Reservation - Details Tab page failed to load");
		exiResReservationTab_DetailsTab.getDetails();

		TestReporter.logStep("Verifying Reservation Details");
		TestReporter
				.assertTrue(exiResReservationTab_DetailsTab.pageLoaded(),
						"Existing Reservation - Reservation - Details Tab page failed to load");
		exiResReservationTab_DetailsTab.validateReservationDetails(
				ReservationDetails, ReservationDetails, Adults, Childs);
		summary.clickDone();

		TestReporter
				.logStep("Navigating to Existing Reservation tab page from New Reservation tab page");
		TestReporter.assertTrue(mainTabNavigation.pageLoaded(),
				"New Reservation Tab page failed to load");
		mainTabNavigation.clickExistingReservationTab();

		TestReporter
				.logStep("Search for the existing reservation from Existing Reservation - Guest Search tab page");
		existingReservationGuestSearchPage
				.searchGuestByNameAndReservationNumber(SearchGuest,
						ReservationNumber);
		existingReservationGuestSearchPage.ButtonModify();

		TestReporter.logStep("Clicking on EditButton");
		exiResReservationTab_DetailsTab.clickEdit();

		TestReporter
				.logStep("Navigating to Existing Reservation Discover Page from Existing Reservation Details Tab page");
		ExistingReservationDiscoverPage existingResDicoverPage = new ExistingReservationDiscoverPage(
				driver);
		TestReporter.assertTrue(existingResDicoverPage.pageLoaded(),
				"Existing Reservation Discover Page failed to load");
		existingResDicoverPage.enterDiscoverCriteria(DiscoverRecreation);

		TestReporter.logStep("Navigating to Existing Reservation offers page");
		ExistingReservationOffersPage erOfferPage = new ExistingReservationOffersPage(
				driver);
		erOfferPage.clickShoppingCart();

		TestReporter.logStep("Navigating to ShoppingCart page");
		ExistingReservationShoppingCartPage erShoppingCartPage = new ExistingReservationShoppingCartPage(
				driver);
		erShoppingCartPage.bookReservation(venueInfowithAdult);

		TestReporter.logStep("Navigating to Existing Reservation Confirm page");
		ExistingReservationConfirmPage confirm = new ExistingReservationConfirmPage(
				driver);
		confirm.acceptAndConfirmNewOffer();

		TestReporter
				.logStep("Take Payment through Credit from Payment tab page");
		ExistingReservationPayment exResPayment = new ExistingReservationPayment(
				driver);
		TestReporter.assertTrue(exResPayment.pageLoaded(),
				"The Payment Tab failed to load");
		exResPayment.applySettlement(payAmount);

		ExistingReservationSummaryPage erSummary = new ExistingReservationSummaryPage(
				driver);
		String ER_Res_No = erSummary.existingReservation_SummaryDetails();
		summary.clickDone();

		TestReporter
				.logStep("Navigating to Existing Reservation tab page from New Reservation tab page");
		mainTabNavigation.clickExistingReservationTab();
		TestReporter.assertTrue(
				existingReservationGuestSearchPage.pageLoaded(),
				"Existing Reservation Tab page failed to load");
		existingReservationGuestSearchPage
				.searchGuestByNameAndReservationNumber(SearchGuest, ER_Res_No);
		existingReservationGuestSearchPage.clickView();

		TestReporter
				.logStep("Get reservation details from  Existing Reservation - Reservation - Details tab page");
		TestReporter
				.assertTrue(exiResReservationTab_DetailsTab.pageLoaded(),
						"Existing Reservation - Reservation - Details Tab page failed to load");
		exiResReservationTab_DetailsTab.getDetails();
	}
}
