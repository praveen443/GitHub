package com.disney.test.composite.dreams;

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

import apps.dreams.DiscoveryPage.DiscoveryPageContentFrame;
import apps.dreams.GuestSearchWindow.GuestSearch;
import apps.dreams.LeftFrame.LeftFrame;
import apps.hub.HubLoginPage;
import core.WebDriverSetup;
import utils.Datatable;
import utils.TestReporter;

/**
 * @Summary: Travel Plan Search without entering any fields
 * @Precondition: N/A
 * @Author: Marella Satish
 * @Version: 02/16/2016
 */
@Listeners({ core.Screenshot.class })
public class Dreams_Book_SearchGuest_Negative {
	// Defining global variables
	private String application = "Dreams";
	private String browserUnderTest = "";
	private String runLocation = "";
	private String environment = "";
	private Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();
	private Datatable dt = new Datatable();
	private String testName;

	@DataProvider(name = "guestSearch_Negative")
	public Object[][] scenarios() {
		try {
			return dt
					.getTestScenariosByApp(application, "guestSearch_Negative");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Define TestNG parameters to the test case
	@BeforeMethod(alwaysRun = true)
	@Parameters({ "runLocation", "browserUnderTest", "environment" })
	public void setup(String runLocation, String browserUnderTest,
			String environment) {
		this.application = "Dreams";
		this.runLocation = runLocation;
		this.browserUnderTest = browserUnderTest;
		this.environment = environment;
		dt.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);
	}

	// Used when running multiple @Tests in conjuction with drivers hashmap so
	// the class will close the correct driver
	@AfterMethod(alwaysRun = true)
	public synchronized void closeSession(ITestResult test) {
		WebDriver driver = drivers.get(testName);
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(dataProvider = "guestSearch_Negative", description = "Search for a guest without passing any parameters and handling the alert ", groups = {
			"regression", "dreams" })
	public void searchGuest(String testScenario, String role, String resort,
			String searchGuest, String runTest) {
		if (runTest.equalsIgnoreCase("true")) {
			TestReporter.logScenario(testScenario);

			// Get Test name for additional reporting
			testName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			// Initialize SeleniumWrapper to WDPRO intergration
			WebDriverSetup setup = new WebDriverSetup();
			WebDriver driver = setup.initialize(application, browserUnderTest,
					runLocation, environment, "32");

			// Add new driver to drivers collection
			drivers.put(testName, driver);

			// Launch App with info sent during driver initialization
			TestReporter.logStep("Launch Dreams application");
			setup.launchApplication(driver);

			// Login to Hub Page
			TestReporter.logStep("Log into Hub Page");
			HubLoginPage HubPage = new HubLoginPage(driver);
			TestReporter.assertTrue(HubPage.pageLoaded(),
					"Verify the Hub Page is displayed");
			HubPage.login(application, role);
			HubPage.getConversationId(application, environment);

			// Navigate to left frame and click on Discovery link from Dreams
			// Home
			// page
			TestReporter
					.logStep("Click on Discover  link froms Dreams home page-Left Frame");
			LeftFrame leftFrame = new LeftFrame(driver);
			TestReporter.assertTrue(leftFrame.pageLoaded(),
					"Verify the left frame is displayed");
			leftFrame.clickDiscovery();

			// Click new Search guest from discover page
			TestReporter.logStep("Click new  Search guest from Discovery page");
			DiscoveryPageContentFrame discoverContent = new DiscoveryPageContentFrame(
					driver);
			TestReporter.assertTrue(discoverContent.pageLoaded(),
					"The Discovery Page was not loaded.");
			discoverContent.clickNewPrimaryGuest();

			// Perform gust search without any search criteria and validating
			// the
			// Alert message
			GuestSearch guestSearch = new GuestSearch(driver);
			TestReporter.assertTrue(guestSearch.pageLoaded(),
					"The Guest Search Window was not loaded.");
			guestSearch.searchGuestValidateError(searchGuest);
		}
	}
}
