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

import apps.dreams.GroupSearchWindow.GroupProfileSearch;
import apps.dreams.LeftFrame.LeftFrame;
import apps.hub.HubLoginPage;
import core.WebDriverSetup;
import utils.Datatable;
import utils.TestReporter;

/**
 * @Summary: DREAMS Group Profile Search Booking
 * @Precondition: N/A
 * @Author: Venkatesh Atmakuri
 * @Version: 02/12/2016
 * @Return: N/A
 */
@Listeners({ core.Screenshot.class })
public class Dreams_Book_GroupSearch_Negative {
	// Defining global variables
	private String application = "Dreams";
	private String browserUnderTest = "";
	private String runLocation = "";
	private String environment = "";
	private Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();
	private Datatable dt = new Datatable();
	private String testName;

	@DataProvider(name = "groupScenario")
	public Object[][] scenarios() {
		try {
			return dt.getTestScenariosByApp(application,
					"DreamsBookGroupSearchNegative");
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

	// Used when running multiple @Tests in cunjuction with drivers hashmap so
	// the class will close the correct driver
	@AfterMethod(alwaysRun = true)
	public synchronized void closeSession(ITestResult test) {
		WebDriver driver = drivers.get(testName);
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(dataProvider = "groupScenario", description = "Group Booking Test", groups = {
			"regression", "dreams", "createGroupWithError" })
	public void bookGroupSearchNegative(String testScenario, String role,
			String errorMsg, String runTest, String GroupID) {
		if(runTest.equalsIgnoreCase("true")){
			// Get Test name for additional reporting
			testName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			// Initialize SeleniumWrapper to WDPRO intergration
			WebDriverSetup setup = new WebDriverSetup(testName + testScenario);
			WebDriver driver = setup.initialize(application, browserUnderTest,
					runLocation, environment, "32");

			// Add new driver to drivers collection
			drivers.put(testName, driver);

			// Launch App with info sent during driver initialization
			setup.launchApplication(driver);

			TestReporter.logStep("Log into Hub Page");
			HubLoginPage HubPage = new HubLoginPage(driver);
			TestReporter
					.assertTrue(HubPage.loadPage(), "Hub LogIn Page not loaded");
			HubPage.login(application, role);
			HubPage.getConversationId(application, environment);

			// Navigate to the Dreams Home Page
			TestReporter.logStep("Navigate to the Dreams Home Page");
			LeftFrame leftFrame = new LeftFrame(driver);
			TestReporter.assertTrue(leftFrame.pageLoaded(),
					"Verify the left frame is displayed");
			leftFrame.clickGroupSearch();

			// Navigate to the GroupProfileSearch Page Click New button
			TestReporter
					.logStep("Navigate to the GroupProfileSearch Page Click New button");
			GroupProfileSearch grpProfileSearch = new GroupProfileSearch(driver);
			TestReporter.assertTrue(grpProfileSearch.pageLoaded(),
					"Verify the Group Search page is displayed");
			
			grpProfileSearch.enterGroupProfileAndSearch(errorMsg, GroupID);
		}
	}
}
