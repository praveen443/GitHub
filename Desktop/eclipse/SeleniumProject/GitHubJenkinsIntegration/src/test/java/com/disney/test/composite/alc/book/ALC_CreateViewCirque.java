package com.disney.test.composite.alc.book;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import apps.alc.newReservation.Discover;
import apps.alc.newReservation.NewEditGuest;
import apps.alc.newReservation.Offers;
import apps.alc.newReservation.Payment;
import apps.alc.newReservation.PaymentInformationPage;
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
 *           Create and View Cirque.
 * 
 * @Precondition: N/A
 * @Author: Praveen Namburi.
 * @Version: 02/01/2016
 * @Return: N/A
 */
@Listeners({ core.Screenshot.class })
public class ALC_CreateViewCirque {
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
	@DataProvider(name = "dataScenarios")
	public Object[][] scenarios() {
		try {
			// Defining what table to run from the virtual tables
			return dt.getTestScenariosByApp(application, "CreateAndViewCirque");
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
	@BeforeTest(alwaysRun = true)
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
	@Test(dataProvider = "dataScenarios", description = "Create and View Cirque.", groups = {
			"regression", "alc", "Find/Add guest", "Discover Cirque",
			"SelectOffer", "ShoppingCart", "Accept AllTerms", "Take CcPayment" })
	public void CreateViewCirque(String testScenario, String role,
			String roleAndLocation, String GuestSearch, String SearchForCirque,
			String SelectOffer, String setCCPayment, String runTest){
		if(runTest.equalsIgnoreCase("true")){
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
			// conversationId = hubPage.getConversationId(application, environment);

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
			TestReporter.logStep("Search and find the guest");
			searchGuest.searchGuest(GuestSearch);

			// Capture the Guest Search name
			TestReporter.logStep("Navigating to the New/Edit Guest Tab page.");
			NewEditGuest newEditGuest = new NewEditGuest(driver);
			TestReporter.assertTrue(newEditGuest.pageLoaded(),
					"New/Edit Guest Tab failed to load");
			TestReporter.log("Captured Guest Search name is : "
					+ newEditGuest.getDefaultSearchGuestname());

			// Click on Discover button.
			TestReporter.logStep("Click on Discover button.");
			newEditGuest.clickbtnDiscover();

			// Select the Discover Criteria and Search For Cirque
			TestReporter.logStep("Navigating to the DiscoverTab page.");
			Discover discoverPage = new Discover(driver);
			TestReporter.assertTrue(discoverPage.pageLoaded(),
					"The DiscoverTab page is not loaded.");
			TestReporter
					.logStep("Select the Discover Criteria and Search For Cirque.");
			discoverPage.enterDiscoverCriteria(SearchForCirque);

			// Select the offer and go to Shopping cart.
			TestReporter.logStep("Navigating to the Offers-Tab page.");
			Offers OffersPage = new Offers(driver);
			TestReporter.assertTrue(OffersPage.pageLoaded(),
					"The Offers-Tab page is not loaded.");
			TestReporter.logStep("Select the offer and go to Shopping cart.");
			OffersPage.selectCirqueOfferAndGoToCart(SelectOffer);

			// Navigate to the Shopping Cart page , and
			// Accept All-terms and book the reservation.
			TestReporter.logStep("Navigating to the Shopping Cart page. ");
			ShoppingCart shoppingCartPage = new ShoppingCart(driver);
			TestReporter.assertTrue(shoppingCartPage.pageLoaded(),
					"Shopping Cart page is not loaded.");
			TestReporter.logStep("Accept all-terms and book the Reservation.");
			shoppingCartPage.acceptAllTermsAndBook();

			TestReporter.log("Clicking on add payment button in payment page.");
			Payment paymentPage =  new Payment(driver);
			paymentPage.clickaddPayment();
			
			// Navigate to the PaymentInformation page and addpayment.
			TestReporter.logStep("Navigating to the PaymentInformation page. ");
			PaymentInformationPage paymentInfoPage = new PaymentInformationPage(
					driver);
			TestReporter.assertTrue(paymentInfoPage.pageLoaded(),
					"PaymentInformation page is not loaded.");
			TestReporter
					.logStep("select the PaymentMethod and go with CCPayment process");
			paymentInfoPage.addPayment(setCCPayment);

			// Navigate to summaryTab page and capture the Reservation details
			TestReporter.logStep("Navigating to the SummaryTab page. ");
			Summary summaryPage = new Summary(driver);
			TestReporter.assertTrue(summaryPage.pageLoaded(),
					"SummaryTab page is not loaded.");
			TestReporter.logStep("Capturing the Reservation details");
			TestReporter.log("Reservation details : "
					+ summaryPage.getSummaryDetails());
			summaryPage.captureSummaryDetails();
		}
	}
}
