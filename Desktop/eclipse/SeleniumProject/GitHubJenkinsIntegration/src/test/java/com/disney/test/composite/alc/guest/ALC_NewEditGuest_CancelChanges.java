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

import apps.alc.newReservation.NewEditGuest;
import apps.alc.newReservation.SearchGuestPage;
import apps.alc.roleAndLocation.RoleAndLocationPage;
import apps.hub.HubLoginPage;
import core.WebDriverSetup;
import utils.ArrayUtil;
import utils.Datatable;
import utils.TestReporter;

/**
 * This class contains all fields, methods and classes required to test the
 * Guest information was same as previous after canceling the new data
 * 
 * @author Marella Satish
 * @version 12/30/2015 Marella Satish - original
 */
@Listeners({ core.Screenshot.class })
public class ALC_NewEditGuest_CancelChanges {
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
					"EditGuest_CancelChanges");
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
	@Test(dataProvider = "dataProvider", description = "Test for Guest Search and edit guest info and Cancel With GF.Admin.IT.Administrator Role", groups = {
			"regression", "ALC", "Search", "New Reservation-Guest Search tab" })
	public void InvalidSearchGuest(String testScenario, String role,
			String roleAndLocation, String guestSearch, String guestInfo,
			String RunTest) {
		if (RunTest.equalsIgnoreCase("true")) {

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
			 * Log a simple description of each step Instantiate the necessary
			 * page class Assert that the page was loaded Interact with the
			 * classes methods
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

			TestReporter.logStep("Search for Guest with guest information");
			SearchGuestPage searchGuest = new SearchGuestPage(driver);
			TestReporter.assertTrue(searchGuest.pageLoaded(),
					"Search Guest Tab failed to load");
			searchGuest.searchGuest(guestSearch);

			TestReporter
					.logStep("Get default guest information before editing");
			NewEditGuest newEditGuest = new NewEditGuest(driver);
			TestReporter.assertTrue(newEditGuest.pageLoaded(),
					"New/Edit Guest Tab failed to load");
			String[] beforeCancelChanges = newEditGuest.getGuestInformation();

			TestReporter.logStep("Modify Guest Information and cancel change");
			TestReporter.assertTrue(newEditGuest.pageLoaded(),
					"New/Edit Guest Tab failed to load");
			newEditGuest.enterGuestInformation(guestInfo);

			TestReporter
					.logStep("Get guest information after editing and cancel changes");
			TestReporter.assertTrue(newEditGuest.pageLoaded(),
					"New/Edit Guest Tab failed to load");
			String[] afterCancelChanges = newEditGuest.getGuestInformation();

			// Compares the Guest information before and after modification with
			// cancel change and validates the data is same
			TestReporter
					.logStep("Validate and Compares the Guest data before modification and after modification with cancel change");
			ArrayUtil arrayUtil = new ArrayUtil();
			arrayUtil.compareArrayOneToOneSame(beforeCancelChanges,
					afterCancelChanges);
		}
	}
}