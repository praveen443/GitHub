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
import utils.TestReporter;

/**
 * @Summary: Books a Dreams Standard Booking Package
 * @Precondition: N/A
 * @Author: Marella Satish
 * @Version: 02/04/2016
 */
@Listeners({ core.Screenshot.class })
public class Dreams_Booking_StandardBookingPackage {
	// Defining global variables
	private String application = "Dreams";
	private String browserUnderTest = "";
	private String runLocation = "";
	private String environment = "";
	private Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();
	private Datatable dt = new Datatable();
	private String testName;

	@DataProvider(name = "standardBookingPackage")
	public Object[][] scenarios() {
		try {
			return dt.getTestScenariosByApp(application,
					"standardBookingPackage");
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
	}

	@Test(dataProvider = "standardBookingPackage", description = "Standard Package Booking ", groups = {
			"regression", "dreams" })
	public void StandardPackageBooking(String testScenario, String role,
			String resort, String guestDetails, String travelPlan,
			String accommodations, String partyMix, String packageCode,
			String offers, String codedRemarks) {
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

		// Navigate to left frame and click on Discovery link from Dreams Home
		// page
		TestReporter
				.logStep("Click on Discover link froms Dreams home page-Left Frame");
		LeftFrame leftFrame = new LeftFrame(driver);
		TestReporter.assertTrue(leftFrame.pageLoaded(),
				"Verify the left frame is displayed");
		leftFrame.clickDiscovery();

		// Enter the discovery information from discovery page
		TestReporter
				.logStep("Entering the required Discovery information (Guest,TravelPlan,PartyMix,"
						+ "Accommodations,Package) from Discovery page");
		DiscoveryPageContentFrame discoverContent = new DiscoveryPageContentFrame(
				driver);
		TestReporter.assertTrue(discoverContent.pageLoaded(),
				"The Discovery Page was not loaded.");
		discoverContent.addPrimaryGuest(guestDetails);
		discoverContent.enterTravelPlan(travelPlan);
		discoverContent.enterPartyMix(partyMix);
		discoverContent.enterAccommodations(accommodations);

		// The below package code search is valid for 01905
		discoverContent.enterPackageCode(packageCode, "0", "WDW", "WHOLESALE",
				"*DWSL", "14", "", "ANN MYW Pkg + Dining");

		// Clicks on the Continue link from Dreams app Header Frame
		TestReporter.logStep("Click on Continue from the Header frame");
		HeaderFrame header = new HeaderFrame(driver);
		TestReporter.assertTrue(header.pageLoaded(),
				"The header frame was not loaded.");
		header.clickContinue();

		// Entering the reservation details from offer page
		TestReporter
				.logStep("Entering the required reservation details from offers page");
		OfferPageContentFrame offerPage = new OfferPageContentFrame(driver);
		TestReporter.assertTrue(offerPage.pageLoaded(),
				"The Offers page  was not displayed.");
		offerPage.enterReservationDetails(offers);
		offerPage.selectOffer(offers);

		// clicks on bypass tickets from present offers ticketing window
		TestReporter
				.logStep("Click on ByPassTickets link  from present offers ticketing window");
		TicketsWindow ticketsWindow = new TicketsWindow(driver);
		TestReporter.assertTrue(ticketsWindow.pageLoaded(),
				"Package Ticketing Window page failed to load");
		ticketsWindow.clickBypassTkts();

		// Clicks on the Continue link from Dream app Header Frame
		TestReporter.logStep("Click on Continue from the Header frame");
		TestReporter.assertTrue(header.pageLoaded(),
				"The header frame was not loaded.");
		header.clickContinue();

		// Entering the Coded Comments from Accommodation WrapUp page
		TestReporter
				.logStep("Entering the Coded Comments from the Accommodation WrapUp page");
		AccommodationWrapUpContentFrame accWrapUpContent = new AccommodationWrapUpContentFrame(
				driver);
		TestReporter.assertTrue(accWrapUpContent.pageLoaded(),
				"The AccWrapUpContent not loaded.");
		accWrapUpContent.clickCodedRemarks();
		accWrapUpContent.enterCodedCommentsInfo(codedRemarks);

		// Validating the coded comments from the Accommodation WrapUp page
		TestReporter
				.logStep("Validating the Coded Comments from the Accommodation WrapUp page");
		accWrapUpContent.validateCodeComments(codedRemarks);

		// Clicks on the Continue link from Dream app Header Frame
		TestReporter.logStep("Click on Continue from the Header frame");
		TestReporter.assertTrue(header.pageLoaded(),
				"The header frame was not loaded.");
		header.clickContinue();

		// Get the reservation number from reservation page
		TestReporter
				.logStep("Get the reservation number from reservation page");
		ReservationPageContentFrame reservationPageContentFrame = new ReservationPageContentFrame(
				driver);
		TestReporter.assertTrue(reservationPageContentFrame.pageLoaded(),
				"The reservation page was not loaded.");
		TestReporter.log("Reservation Numer : "
				+ reservationPageContentFrame.getReservationNumber());
	}
}
