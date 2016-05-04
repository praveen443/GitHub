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

import apps.dreams.GroupSearchWindow.GroupPageContentFrame;
import apps.dreams.GroupSearchWindow.GroupProfilePage;
import apps.dreams.GroupSearchWindow.GroupProfileSearch;
import apps.dreams.HeaderFrame.HeaderFrame;
import apps.dreams.LeftFrame.LeftFrame;
import apps.hub.HubLoginPage;
import core.WebDriverSetup;
import utils.Datatable;
import utils.TestReporter;

/**
 * @Summary: This class contains all fields, methods and classes required for
 *           Dreams Block Allotment.
 * @Precondition: N/A
 * @Author: Praveen Namburi
 * @Version: 03/10/2016
 * @Return: N/A
 */
@Listeners({ core.Screenshot.class })
public class Dreams_Block_Allotment {

	// Defining global variables
	private String application = "dreams";
	private String browserUnderTest = "";
	private String runLocation = "";
	private String environment = "";
	private String testName;
	private Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();
	private Datatable dt = new Datatable();

	@DataProvider(name = "blockScenario")
	public Object[][] scenarios() {
		try {
			// Defining what table to run from the virtual tables
			return dt.getTestScenariosByApp(application, "BlockAllotment");
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
	@Test(dataProvider = "blockScenario", description = "Block Allotment", groups = {
			"regression", "dreams", "CreateGroupProfile", "OrgSearch",
			"contactGuestSearch", "GroupInfo", "GroupConfirmations",
			"createBlock", "SelectResort", "setBlockInventory", "EditBlock",
			"addFirstAllotment", "addSecondAllotment", "EditBlock",
			"DeleteAllotment" })
	public void blockAllotment(String testScenario, String role,
			String entergroupsProfile, String OrgSearch,
			String contactGuestSearch, String groupInfo,
			String CreateBlockAndSelectResort, String setBlockInventory,
			String addFirstAllotment, String addSecondAllotment, String RunTest) {
		if (RunTest.equalsIgnoreCase(RunTest)) {
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
			TestReporter.assertTrue(HubPage.pageLoaded(),
					"Verify the Hub Page is displayed");
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
					"Verify the GroupSearch page is displayed");
			grpProfileSearch.clickNew();

			// Entering Group Profile
			TestReporter.logStep("Enter Group Profile information");
			GroupPageContentFrame grpPageContentFrame = new GroupPageContentFrame(
					driver);
			TestReporter.assertTrue(grpPageContentFrame.pageLoaded(),
					"Verify the GroupProfile page is displayed");
			String groupId = grpPageContentFrame
					.enterGroupProfile(entergroupsProfile);
			TestReporter.log("Group ID : " + groupId);

			// Click OrganizationSearch and enter Group Profile Information
			TestReporter
					.logStep("Click OrganizationSearch and enter GroupProfile Information");
			grpPageContentFrame.clickOrganizationSearch(OrgSearch);

			// Click on Contact link and provide Contact information
			TestReporter
					.logStep("Click on Contact link and provide Contact Information");
			grpPageContentFrame.clickContactSearch();
			grpPageContentFrame.guestSearch(contactGuestSearch);
			grpPageContentFrame.selectContactRole(contactGuestSearch);

			// Enter Group Information Details
			TestReporter.logStep("Enter Group Information Details");
			grpPageContentFrame.grpInformationDetails(groupInfo);

			// Click on ConfirmAll
			TestReporter.logStep("Click on ConfirmAll");
			HeaderFrame headerFrame = new HeaderFrame(driver);
			TestReporter.assertTrue(headerFrame.pageLoaded(),
					"Verify the Headerframe is displayed");
			headerFrame.clickConfirmall();

			// Click on GroupSearch and accept Warning Dialog.
			TestReporter
					.logStep("Click on GroupSearch and accept Warning Dialog.");
			TestReporter.assertTrue(leftFrame.pageLoaded(),
					"Verify the left frame is displayed");
			leftFrame.clickGroupSearch();
			grpPageContentFrame.clickonYesButton();

			// Enter GroupID in GroupProfile page and Search it.
			TestReporter
					.logStep("Enter GroupID in GroupProfile page and Search it");
			grpProfileSearch.enterGroupCode(groupId);
			grpProfileSearch.clickSearch();

			// Validate GroupID Search Result
			TestReporter.logStep("Validating the Searched GroupID Result");
			grpProfileSearch.validateGroupSearchResult(groupId);

			// Click on Block.
			TestReporter.logStep("Click on Block.");
			grpPageContentFrame.clickBlock();

			// Click on New in GroupProfile page.
			TestReporter.logStep("Navigate to the GroupProfile page.");
			GroupProfilePage groupProfilePage = new GroupProfilePage(driver);
			TestReporter.assertTrue(groupProfilePage.pageLoaded(),
					"Verify the GroupProfile page is loaded");
			TestReporter.logStep("Click on New in GroupProfile page.");
			groupProfilePage.clickbtnNew();

			// Set details for creating a new block
			TestReporter.logStep("Set details for creating a new block");
			groupProfilePage
					.setBookingSourceAndBlockManagement(CreateBlockAndSelectResort);

			// Click on ConfirmAll
			TestReporter.logStep("Click on ConfirmAll");
			TestReporter.assertTrue(headerFrame.pageLoaded(),
					"Verify the Headerframe is displayed");
			headerFrame.clickConfirmall();

			// Click on Edit button in BLockList table.
			TestReporter.logStep("Navigate to GroupProfile page.");
			TestReporter.assertTrue(groupProfilePage.pageLoaded(),
					"Verify the GroupProfile page is loaded");
			TestReporter.logStep("Click on Edit button in BlockList table");
			grpPageContentFrame.clickEditBlock();

			// Click on Edit button in BLockList table.
			TestReporter
					.logStep("Navigate to GroupProfile - Display Block/Block Inventory page.");
			TestReporter
					.assertTrue(groupProfilePage.pageLoaded(),
							"Verify GroupProfile - Display Block/Block Inventory page is loaded");
			TestReporter.logStep("Click on button Go-To-BlockInventory.");
			groupProfilePage.clickbtnGoToInventory();

			if (driver.getTitle().contains("Sign In")) {
				// Navigate to hub page and login again
				TestReporter.logStep("Navigate to hub page and login again");
				TestReporter.assertTrue(HubPage.pageLoaded(),
						"Verify the Hub Page is displayed");
				HubPage.login(application, role);
			}

			// Select the resort and click on OK button
			TestReporter.logStep("Select the resort and click on OK button");
			groupProfilePage.seletFilterResort(CreateBlockAndSelectResort);

			// Select and add BlockInventory details.
			TestReporter.logStep("Add and Save BlockInventory details");
			groupProfilePage.addBlockInventorySetup(setBlockInventory);
			groupProfilePage.clickbtnSave();

			// Click on ConfirmAll
			TestReporter.logStep("Click on ConfirmAll");
			TestReporter.assertTrue(headerFrame.pageLoaded(),
					"Verify the Headerframe is displayed");
			headerFrame.clickConfirmall();

			// Click on GroupSearch and accept Warning Dialog.
			TestReporter
					.logStep("Click on GroupSearch and accept Warning Dialog.");
			TestReporter.assertTrue(leftFrame.pageLoaded(),
					"Verify the left frame is displayed");
			leftFrame.clickGroupSearch();
			grpPageContentFrame.clickonYesButton();

			// Enter GroupID in GroupProfile page and Search it.
			TestReporter
					.logStep("Enter GroupID in GroupProfile page and Search it");
			TestReporter.assertTrue(grpProfileSearch.pageLoaded(),
					"Verify the GroupProfile page is displayed");
			grpProfileSearch.enterGroupCode(groupId);
			grpProfileSearch.clickSearch();

			// Validate GroupID Search Result
			TestReporter.logStep("Validating the Searched GroupID Result");
			grpProfileSearch.validateGroupSearchResult(groupId);

			// Click on Block.
			TestReporter.logStep("Click on Block.");
			grpPageContentFrame.clickBlock();

			// Click on Edit button in BlockList table.
			TestReporter.logStep("Navigate to GroupProfile page.");
			TestReporter.assertTrue(groupProfilePage.pageLoaded(),
					"Verify the GroupProfile page is loaded");
			TestReporter.logStep("Click on Edit button in BlockList table");
			grpPageContentFrame.clickEditBlock();

			// Click on link Utilization Codes
			TestReporter.logStep("Click on link Utilization Codes");
			groupProfilePage.clickUtilizationCodes();

			// Select and add Allotment availability and booking
			TestReporter.assertTrue(groupProfilePage.pageLoaded(),
					"Verify the Display block/block Inventory page is loaded");
			TestReporter
					.logStep("Select and add Allotment availability and booking");
			groupProfilePage
					.addAllotmentAvailabilityAndBooking(addFirstAllotment);

			// Click on ConfirmAll
			TestReporter.logStep("Click on ConfirmAll");
			TestReporter.assertTrue(headerFrame.pageLoaded(),
					"Verify the Headerframe is displayed");
			headerFrame.clickConfirmall();

			// Click on Edit button in BLockList table.
			TestReporter.logStep("Navigate to GroupProfile page.");
			TestReporter.assertTrue(groupProfilePage.pageLoaded(),
					"Verify the GroupProfile page is loaded");
			TestReporter.logStep("Click on Edit button in BlockList table");
			grpPageContentFrame.clickEditBlock();

			// Click on link Utilization Codes and Allotment
			TestReporter
					.logStep("Click on link Utilization Codes and Allotment");
			groupProfilePage.clickUtilizationCodes();
			groupProfilePage.clickAllotment();

			// Click on GroupSearch and accept Warning Dialog.
			TestReporter
					.logStep("Click on GroupSearch and accept Warning Dialog.");
			TestReporter.assertTrue(leftFrame.pageLoaded(),
					"Verify the left frame is displayed");
			leftFrame.clickGroupSearch();
			grpPageContentFrame.clickonYesButton();

			// Enter GroupID in GroupProfile page and Search it.
			TestReporter
					.logStep("Enter GroupID in GroupProfile page and Search it");
			TestReporter.assertTrue(grpProfileSearch.pageLoaded(),
					"Verify the GroupProfile page is displayed");
			grpProfileSearch.enterGroupCode(groupId);
			grpProfileSearch.clickSearch();

			// Validate GroupID Search Result
			TestReporter.logStep("Validating the Searched GroupID Result");
			grpProfileSearch.validateGroupSearchResult(groupId);

			// Click on Block.
			TestReporter.logStep("Click on Block.");
			grpPageContentFrame.clickBlock();

			// Click on Edit button in BlockList table.
			TestReporter.logStep("Navigate to GroupProfile page.");
			TestReporter.assertTrue(groupProfilePage.pageLoaded(),
					"Verify the GroupProfile page is loaded");
			TestReporter.logStep("Click on Edit button in BlockList table");
			grpPageContentFrame.clickEditBlock();

			// Click on link Utilization Codes
			TestReporter.logStep("Click on link Utilization Codes");
			groupProfilePage.clickUtilizationCodes();

			// Select and add Allotment availability and booking
			TestReporter.assertTrue(groupProfilePage.pageLoaded(),
					"Verify the Display block/block Inventory page is loaded");
			TestReporter
					.logStep("Select and add Allotment availability and booking");
			groupProfilePage
					.addAllotmentAvailabilityAndBooking(addSecondAllotment);

			// Click on ConfirmAll
			TestReporter.logStep("Click on ConfirmAll");
			TestReporter.assertTrue(headerFrame.pageLoaded(),
					"Verify the Headerframe is displayed");
			headerFrame.clickConfirmall();

			// Click on Edit button in BLockList table.
			TestReporter.logStep("Navigate to GroupProfile page.");
			TestReporter.assertTrue(groupProfilePage.pageLoaded(),
					"Verify the GroupProfile page is loaded");
			TestReporter.logStep("Click on Edit button in BlockList table");
			grpPageContentFrame.clickEditBlock();

			// Click on link Utilization Codes and Allotment
			TestReporter
					.logStep("Click on link Utilization Codes and Allotment");
			groupProfilePage.clickUtilizationCodes();
			groupProfilePage.clickAllotment();

			// Click on GroupSearch and accept Warning Dialog.
			TestReporter
					.logStep("Click on GroupSearch and accept Warning Dialog.");
			TestReporter.assertTrue(leftFrame.pageLoaded(),
					"Verify the left frame is displayed");
			leftFrame.clickGroupSearch();
			grpPageContentFrame.clickonYesButton();

			// Enter GroupID in GroupProfile page and Search it.
			TestReporter
					.logStep("Enter GroupID in GroupProfile page and Search it");
			TestReporter.assertTrue(grpProfileSearch.pageLoaded(),
					"Verify the GroupProfile page is displayed");
			grpProfileSearch.enterGroupCode(groupId);
			grpProfileSearch.clickSearch();

			// Validate GroupID Search Result
			TestReporter.logStep("Validating the Searched GroupID Result");
			grpProfileSearch.validateGroupSearchResult(groupId);

			// Click on Block.
			TestReporter.logStep("Click on Block.");
			grpPageContentFrame.clickBlock();

			// Click on Edit button in BlockList table.
			TestReporter.logStep("Navigate to GroupProfile page.");
			TestReporter.assertTrue(groupProfilePage.pageLoaded(),
					"Verify the GroupProfile page is loaded");
			TestReporter.logStep("Click on Edit button in BlockList table");
			grpPageContentFrame.clickEditBlock();

			// Click on link Utilization Codes and Allotment
			TestReporter
					.logStep("Click on link Utilization Codes and Allotment");
			groupProfilePage.clickUtilizationCodes();
			groupProfilePage.clickAllotment();

			// Verifying whether the Added Allotment details exist in the table.
			TestReporter
					.logStep("Verifying the Added Allotment details before deleting them.");
			groupProfilePage.verifyAddedAllotmentBeforeDeleting();

			// Click on ConfirmAll
			TestReporter.logStep("Click on ConfirmAll");
			TestReporter.assertTrue(headerFrame.pageLoaded(),
					"Verify the Headerframe is displayed");
			headerFrame.clickConfirmall();

			// Click on Edit button in BlockList table.
			TestReporter.logStep("Navigate to GroupProfile page.");
			TestReporter.assertTrue(groupProfilePage.pageLoaded(),
					"Verify the Display Block/Block Inventory page is loaded");
			TestReporter.logStep("Click on Edit button in BlockList table");
			grpPageContentFrame.clickEditBlock();

			// Click on link Utilization Codes and Allotment
			TestReporter
					.logStep("Click on link Utilization Codes and Allotment");
			groupProfilePage.clickUtilizationCodes();
			groupProfilePage.clickAllotment();

			// Verifying whether the Allotment details are deleted from the
			// table.
			TestReporter
					.logStep("Verifying whether the Allotment details are deleted from the table.");
			groupProfilePage.verifyAddedAllotmentAfterDeletion();
		}
	}
}