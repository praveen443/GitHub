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
import apps.dreams.LeftFrame.LeftFrame;
import apps.hub.HubLoginPage;
import core.WebDriverSetup;
import utils.Datatable;
import utils.TestReporter;

/**
 * @Summary: Books a Dreams NewTravelPlan with negative/invalid Group Number
 * @Precondition: N/A
 * @Author: Marella Satish
 * @Version: 02/12/2016
 */
@Listeners({ core.Screenshot.class })
public class Dreams_Book_NewTravelPlan_Negative {
	// Defining global variables
	private String application = "Dreams";
	private String browserUnderTest = "";
	private String runLocation = "";
	private String environment = "";
	private Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();
	private Datatable dt = new Datatable();
	private String testName;
	// Page Class Objects
	private WebDriverSetup setup = null;
	private WebDriver driver = null;
	private HubLoginPage HubPage = null;
	private LeftFrame leftFrame = null;
	private DiscoveryPageContentFrame discoverContent = null;
	private boolean preReqsDone = false;

	@DataProvider(name = "bookNewTravelPlan")
	public Object[][] scenarios() {
		try {
			return dt.getTestScenariosByApp(application,
					"bookNewTravelPlan_Negative");
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

		preReqs();
	}

	// Used when running multiple @Tests in conjuction with drivers hashmap so
	// the class will close the correct driver
	@AfterMethod(alwaysRun = true)
	public synchronized void closeSession(ITestResult test) {
		if(!test.isSuccess()){
			preReqsDone = false;
		}
		if (test.getName().contains("areaDepartDate30DaysInThePast")
				|| !preReqsDone) {
			if (driver != null) {
				driver.quit();
			}
		}
	}

	@Test(description = "Validate negative scenarios for travel plan criteria", groups = {
			"regression", "dreams" }, alwaysRun = true, priority = 1)
	public void invalidGroupNumber() {
		invokeError("Consumer Package with Invalid GroupNumber");
	}

	@Test(description = "Validate negative scenarios for travel plan criteria", groups = {
			"regression", "dreams" }, alwaysRun = true, priority = 2)
	public void blankArrivalDate() {
		invokeError("Blank WDW Arrival Date");
	}

	@Test(description = "Validate negative scenarios for travel plan criteria", groups = {
			"regression", "dreams" }, alwaysRun = true, priority = 3)
	public void wdwArrivalDate30DaysInThePast() {
		invokeError("WDW Arrival Date 30 Days in the Past");
	}

	@Test(description = "Validate negative scenarios for travel plan criteria", groups = {
			"regression", "dreams" }, alwaysRun = true, priority = 4)
	public void invalidWdwArrivalDateFormat() {
		invokeError("Invalid WDW Arrival Date Format");
	}

	@Test(description = "Validate negative scenarios for travel plan criteria", groups = {
			"regression", "dreams" }, alwaysRun = true, priority = 5)
	public void invalidWdwNumberOfNightsFormat() {
		invokeError("Invalid WDW Number of Nights Format");
	}

	@Test(description = "Validate negative scenarios for travel plan criteria", groups = {
			"regression", "dreams" }, alwaysRun = true, priority = 6)
	public void blankWdwNumberOfNights() {
		invokeError("Blank WDW Number of Nights");
	}

	@Test(description = "Validate negative scenarios for travel plan criteria", groups = {
			"regression", "dreams" }, alwaysRun = true, priority = 7)
	public void invalidWdwDepartDateFormat() {
		invokeError("Invalid WDW Depart Date Format");
	}

	@Test(description = "Validate negative scenarios for travel plan criteria", groups = {
			"regression", "dreams" }, alwaysRun = true, priority = 8)
	public void wdwDepartDate30DaysInThePast() {
		invokeError("WDW Depart Date 30 Days in the Past");
	}

	@Test(description = "Validate negative scenarios for travel plan criteria", groups = {
			"regression", "dreams" }, alwaysRun = true, priority = 9)
	public void invalidAreaArrivalDateFormat() {
		invokeError("Invalid Area Arrival Date Format");
	}

	@Test(description = "Validate negative scenarios for travel plan criteria", groups = {
			"regression", "dreams" }, alwaysRun = true, priority = 10)
	public void areaArrivalDate30DaysInThePast() {
		invokeError("Area Arrival Date 30 Days in the Past");
	}

	@Test(description = "Validate negative scenarios for travel plan criteria", groups = {
			"regression", "dreams" }, alwaysRun = true, priority = 11)
	public void areaNumberOfNightsInvalidFormat() {
		invokeError("Area Number of Nights Invalid Format");
	}

	@Test(description = "Validate negative scenarios for travel plan criteria", groups = {
			"regression", "dreams" }, alwaysRun = true, priority = 12)
	public void areaDepartDateInvalidFormat() {
		invokeError("Area Depart Date Invalid Format");
	}

	@Test(description = "Validate negative scenarios for travel plan criteria", groups = {
			"regression", "dreams" }, alwaysRun = true, priority = 13)
	public void areaDepartDate30DaysInThePast() {
		invokeError("Area Depart Date 30 Days in the Past");
	}

	private void invokeError(String scenario) {
		TestReporter
				.logStep("Click on Discover link froms Dreams home page-Left Frame");
		leftFrame = new LeftFrame(driver);
		TestReporter.assertTrue(leftFrame.pageLoaded(),
				"Verify the left frame is displayed");
		leftFrame.clickDiscovery();

		TestReporter
				.logStep("Entering the required Discovery information (Guest,TravelPlan) from Discovery page");
		discoverContent = new DiscoveryPageContentFrame(driver);
		TestReporter.assertTrue(discoverContent.pageLoaded(),
				"The Discovery Page was not loaded.");
		discoverContent.enterTravelPlan_Negative(scenario);

	}

	private void preReqs() {
		if (!preReqsDone) {
			// Initialize SeleniumWrapper to WDPRO intergration
			setup = new WebDriverSetup();
			driver = setup.initialize(application, browserUnderTest,
					runLocation, environment, "32");

			// Add new driver to drivers collection
			drivers.put(testName, driver);

			// Launch App with info sent during driver initialization
			TestReporter.logStep("Launch Dreams application");
			setup.launchApplication(driver);

			// Login to Hub Page
			TestReporter.logStep("Log into Hub Page");
			HubPage = new HubLoginPage(driver);
			TestReporter.assertTrue(HubPage.pageLoaded(),
					"Verify the Hub Page is displayed");
			HubPage.login(application, "ADMIN");
			HubPage.getConversationId(application, environment);

			preReqsDone = true;
		}
	}
}
