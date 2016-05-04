package test.dreams;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import api.soapServices.accommodationSalesServicePort.operations.Cancel;
import api.soapServices.accommodationSalesServicePort.operations.Retrieve;
import apps.dreams.AccommodationWrapUp.AccommodationWrapUpContentFrame;
import apps.dreams.DiscoveryPage.DiscoveryPageContentFrame;
import apps.dreams.HeaderFrame.HeaderFrame;
import apps.dreams.LeftFrame.LeftFrame;
import apps.dreams.OfferPage.OfferPageContentFrame;
import apps.dreams.OfferPage.TicketsWindow;
import apps.dreams.ReservationPage.ReservationPageContentFrame;
import apps.hub.HubLoginPage;
import core.WebDriverSetup;
import utils.Datatable;
import utils.Randomness;
import utils.TestReporter;
import utils.dataFactory.ResortInfo;
import utils.dataFactory.ResortInfo.ResortColumns;

/**
 * @Summary: Books a Life Cycle Package
 * @Precondition: N/A
 * @Author: Chinagudaba Pawan
 * @Version: N/A
 * @Return: N/A
 */
public class Dreams_BookingLifeCyclePackage {

	// Defining global variables
	private String application = "dreams";
	private String browserUnderTest = "";
	private String runLocation = "";
	private String environment = "";
	private String reservationNumber = "";
	private Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();
	private Datatable dt = new Datatable();
	private String locationId;
	private String TCG_ID;
	private String testName;

	@DataProvider(name = "BookingLifeCyclePackageScenarios")
	public Object[][] scenarios() {
		try {
			return dt.getTestScenariosByApp(application,
					"BookingLifeCyclePackage");
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

	// Used when running multiple @Tests in cuonjuction with drivers hashmap so
	// the class will close the correct driver
	@AfterMethod(alwaysRun = true)
	public synchronized void closeSession(ITestResult test) {
		WebDriver driver = drivers.get(testName);
		if (driver != null) {
			driver.quit();
		}
		if (reservationNumber != null) {
			if (!reservationNumber.isEmpty()) {
				try {
					Retrieve retrieve = new Retrieve(environment, "BYTPS_ID");
					retrieve.setLocationId(locationId);
					retrieve.setTravelPlanSegmentId(reservationNumber);
					retrieve.sendRequest();
					TCG_ID = retrieve.getTravelComponentGroupingId();

					Cancel cancel = new Cancel(environment, "Main");
					cancel.setCancelDate(Randomness
							.generateCurrentXMLDatetime());
					cancel.setTravelComponentGroupingId(TCG_ID);
					cancel.sendRequest();
					System.out.println(cancel.getResponse());
					System.out.println();
					System.out.println();
				} catch (Exception e) {
				}
			}
		}
	}

	@Test(dataProvider = "BookingLifeCyclePackageScenarios", description = "BookingLifeCyclePackage", groups = {
			"regression", "dreams" })
	public void BookReservationwithGroup(String testScenario, String role,
			String resort, String travelPlan, String travelPlanUpdate,
			String accommodations, String partyMix, String packagecode,
			String accomWrapUp, String offers, String tickets, String agencyId,
			String ReservDetails) {
		TestReporter.logScenario(testScenario);

		// Get Test name for additional reporting
		testName = new Object() {
		}.getClass().getEnclosingMethod().getName();

		// Initialize SeleniumWrapper to WDPRO intergration
		// WebDriverSetup setup = new WebDriverSetup(testName + testScenario);
		WebDriverSetup setup = new WebDriverSetup();
		WebDriver driver = setup.initialize(application, browserUnderTest,
				runLocation, environment, "32");

		// Add new driver to drivers collection
		drivers.put(testName, driver);

		// Launch App with info sent during driver initialization
		setup.launchApplication(driver);

		// Log into Hub Page
		TestReporter.logStep("Log into Hub Page");
		HubLoginPage HubPage = new HubLoginPage(driver);
		HubPage.login(application, role);
		HubPage.getConversationId(application, environment);

		// Navigate to the Discovery Page
		TestReporter.logStep("Navigate to the Discovery Page");
		LeftFrame leftFrame = new LeftFrame(driver);
		TestReporter.assertTrue(leftFrame.pageLoaded(),
				"Verify the left frame is displayed");
		leftFrame.clickDiscovery();

		DiscoveryPageContentFrame discoverContent = new DiscoveryPageContentFrame(
				driver);
		TestReporter.assertEquals(discoverContent.pageLoaded(), true,
				"The Discovery Page was not loaded.");
		discoverContent.addPrimaryGuest(accomWrapUp);
		discoverContent.clickNewTravelAgency(agencyId);
		discoverContent.enterTravelPlan(travelPlan);
		discoverContent.enterPartyMix(partyMix);
		discoverContent.verifyNoAdults(partyMix);
		discoverContent.enterAccommodations(accommodations);

		locationId = ResortInfo.getResortInfo(ResortColumns.RESORT_NAME,
				resort, ResortColumns.TRANSACTION_CENTER_ID);
		discoverContent.enterPackageCode(packagecode, "0", "WDW", "WDW PKG",
				"*WDTC", locationId, "", "R MYW Pkg + Quick Service Dining");

		// CLick Continue
		TestReporter.logStep("Click Continue");
		HeaderFrame header = new HeaderFrame(driver);
		TestReporter.assertTrue(header.pageLoaded(),
				"Verify the header frame is displayed");
		header.clickContinue();

		// Selecting an Offer
		TestReporter.logStep("Select an Offer");
		OfferPageContentFrame offerPage = new OfferPageContentFrame(driver);
		TestReporter.assertTrue(offerPage.pageLoaded(),
				"Verify the Offers selection is displayed.");
		offerPage.selectOffer(offers);

		// Selecting Ticket according to the package code
		TestReporter.logStep("Ticket Selection");
		TicketsWindow selectTicket = new TicketsWindow(driver);
		TestReporter.assertTrue(selectTicket.pageLoaded(),
				"Verify the Ticket selection is displayed");
		selectTicket.selectTicket(tickets);

		// CLick Continue
		TestReporter.logStep("Click Continue");
		TestReporter.assertTrue(header.pageLoaded(),
				"Verify the header frame is displayed");
		header.clickContinue();
		header.clickContinue();

		// Getting captured reservation number
		ReservationPageContentFrame rpc = new ReservationPageContentFrame(
				driver);
		reservationNumber = rpc.getReservationNumber();
		TestReporter.logStep("Resv Number is : " + reservationNumber);

		// Clicking Accommodation summary LInk
		TestReporter.logStep("Clicking Accommodation summary LInk");
		AccommodationWrapUpContentFrame wrapUpContentFrame = new AccommodationWrapUpContentFrame(
				driver);
		TestReporter.assertTrue(wrapUpContentFrame.pageLoadedAccomSummary(), "The Reservation page did not load.");
		wrapUpContentFrame.clickaccommodationSummary();

		// VErifying Accommodation summary ELements in Accommodation summary
		// LInk
		TestReporter
				.logStep("VErifying Accommodation summary ELements in Accommodation summary LInk");
		wrapUpContentFrame.verifyaccomodationSummary(travelPlan,
				accommodations, packagecode, reservationNumber);

		// Clicking the SelectALL checkbox
		TestReporter.logStep("Clicking the SelectALL checkbox");
		rpc.chkSelectAll();

		// Clicking the Edit button
		TestReporter.logStep("Clicking the Edit button");
		wrapUpContentFrame.clickEdit();

		// EDiting the DoryAccommodationUI page
		TestReporter.logStep("Editing the DoryAccommodationUI page");
		wrapUpContentFrame.editAccommodationUI();

		// Updating travel plan without Group COde
		TestReporter.logStep("Updating travel plan without Group COde");
		discoverContent.enterTravelPlanwithoutGroupCode(travelPlanUpdate);

		// Clicking Re-Discovery Button
		TestReporter.logStep("Clicking Re-Discovery Button");
		rpc.clickReDiscovery();

		// Selecting an Offer
		TestReporter.logStep("Select an Offer");
		offerPage.selectOffer(offers);		

		// Selecting Ticket according to the package code
		TestReporter.logStep("Ticket Selection");
		selectTicket.selectTicket(tickets);

		// Click Continue
		TestReporter.logStep("Click Continue");
		TestReporter.assertTrue(header.pageLoaded(),
				"Verify the header frame is displayed");
		header.clickContinue();
		header.clickContinue();

		// Select Reservation number
		TestReporter.logStep("Cancel Reservation");
		TestReporter.assertTrue(rpc.pageLoaded(), "The Reservation page did not load.");
		rpc.select_TPS();
		rpc.perform_ResCancel(ReservDetails, accomWrapUp);

		// Selecting Reservation checkbox
		TestReporter.logStep("Selecting Reservation checkbox");
		TestReporter.assertTrue(rpc.pageLoaded(), "The Reservation page did not load.");
		rpc.select_TPS();

		// Clicking Re-instate Button
		TestReporter.logStep("Clicking Re-instate Button");
		rpc.click_Reinstate();

		// Reinstating selection of Ticket
		TestReporter.logStep("Ticket Selection");
		selectTicket.selectTicket(tickets);
	}
}
