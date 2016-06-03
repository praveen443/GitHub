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

import apps.dreams.LeftFrame.LeftFrame;
import apps.dreams.travelPlanSearch.TravelPlanSearchPage;
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
public class Dreams_Book_TravelPlanSearch_Negative {
	// Defining global variables
	private String application = "Dreams";
	private String browserUnderTest = "";
	private String runLocation = "";
	private String environment = "";
	private Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();
	private Datatable dt = new Datatable();
	private String testName;

	@DataProvider(name = "travelPlanSearch_Negative")
	public Object[][] scenarios() {
		try {
			return dt.getTestScenariosByApp(application,
					"travelPlanSearch_Negative");
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

	@Test(dataProvider = "travelPlanSearch_Negative", description = "Search for a TravelPlan without passing any parameters and handling the alert ", groups = {
			"regression", "dreams" })
	public void bookNewTravelPlan(String testScenario, String role,
			String resort, String travelPlanSearch, String runTest){
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

			// Navigate to left frame and click on TravelPlan Search link from
			// Dreams Home page
			TestReporter
					.logStep("Click on TravelPlan search  link froms Dreams home page-Left Frame");
			LeftFrame leftFrame = new LeftFrame(driver);
			TestReporter.assertTrue(leftFrame.pageLoaded(),
					"Verify the left frame is displayed");
			leftFrame.clickTravelPlanSearch();

			TestReporter
					.logStep("Clicking Search button from TravelPlan search page");
			TravelPlanSearchPage travelPlanSearchPage = new TravelPlanSearchPage(
					driver);
			TestReporter.assertTrue(travelPlanSearchPage.pageLoaded(),
					"The TravelPlan search Page Page was not loaded.");
			travelPlanSearchPage.searchForTravelPlan(travelPlanSearch);
		}
	}
}
