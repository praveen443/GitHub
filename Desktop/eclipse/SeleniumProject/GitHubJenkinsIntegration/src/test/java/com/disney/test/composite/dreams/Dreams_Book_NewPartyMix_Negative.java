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
import apps.dreams.HeaderFrame.HeaderFrame;
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
public class Dreams_Book_NewPartyMix_Negative {
	// Defining global variables
	private String application = "Dreams";
	private String browserUnderTest = "";
	private String runLocation = "";
	private String environment = "";
	private Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();
	private Datatable dt = new Datatable();
	private String testName;
	private boolean preReqsDone = false;
	private boolean invalidChildNumber;
	// Page Class Objects
	private WebDriverSetup setup = null;
	private WebDriver driver = null;
	private HubLoginPage HubPage = null;
	private LeftFrame leftFrame = null;
	private DiscoveryPageContentFrame discoverContent = null;

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
		if (test.getName().contains("invalidChildNumberFormat")
				|| !preReqsDone) {
			if (driver != null) {
				driver.quit();
			}
		}
	}

	@Test(description = "Validate negative scenarios for travel plan criteria", groups = {
			"regression", "dreams" }, alwaysRun = true, priority = 1)
	public void invalidAdultNumberFormat() {
		invokeError("Invalid Adult Number Format");
	}

	@Test(description = "Validate negative scenarios for travel plan criteria", groups = {
			"regression", "dreams" }, alwaysRun = true, priority = 2)
	public void noAdults() {
		invokeError("No Adults");
	}

	@Test(description = "Validate negative scenarios for travel plan criteria", groups = {
			"regression", "dreams" }, alwaysRun = true, priority = 3)
	public void emptyPartyMixFields() {
		invokeError("Empty Party Mix Fields");
	}

	@Test(description = "Validate negative scenarios for travel plan criteria", groups = {
			"regression", "dreams" }, alwaysRun = true, priority = 4)
	public void invalidChildNumberFormat() {
		invalidChildNumber = true;
		invokeError("Invalid Child Number Format");
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
		discoverContent.enterTravelPlan("Consumer Same day One Night");
		discoverContent.enterPartyMix_Negative(scenario);
		if(!invalidChildNumber){
			discoverContent.enterAccommodations("Garden Wing Standard View One Room Non-ADA");
			discoverContent.enterPackageCode(environment, "0", "WDW", "DRC RO", 
					"", "14", "", "");
			new HeaderFrame(driver).clickContinueError();
			discoverContent.validateErrorMessage(scenario, "DiscoveryPartyMix");
		}
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
