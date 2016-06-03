package core;

import java.net.URL;
import java.util.ResourceBundle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;

import selenium.SeleniumWrapper;
import selenium.SeleniumWrapper.DriverType;
import selenium.common.Constants;
import utils.Datatable;
import utils.FrameworkHarness;
import utils.Randomness;
import utils.Sleeper;
import utils.TestReporter;

public class WebDriverSetup {

	public WebDriver driver;
	private String testName = "";
	private String testEnvironment = "";
	private String testApplication = "";
	private String driverWindow = "";
	private String application = "";
	public Datatable scenario = new Datatable();
	public ResourceBundle appURLRepository = ResourceBundle
			.getBundle("com.disney.composite.environmentInfo.EnvironmentURLs");
	public String browser = null;
	public String location = null;
	private URL seleniumHubURL = null;
	private static int TIMEOUT = 20;
	private boolean isGrid = false;
	ThreadLocal<WebDriver> threadedDriver = new ThreadLocal<WebDriver>();

	public WebDriverSetup() {
	}

	public WebDriverSetup(String testName) {
		this.testName = testName;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebDriver initialize(String application, String browserUnderTest, String runLocation, String environment) {
		//threadedDriver.set(initialize(application, browserUnderTest, runLocation, environment, ""));
		//return threadedDriver.get();
		driver = initialize(application, browserUnderTest, runLocation, environment, "");
		return driver;
	}

	public WebDriver initialize(String application, String browserUnderTest, String runLocation, String environment,
			String preferedArchitecture) {
		TestReporter.setPrintToConsole(true);
		WebDriver driver = null;
		
		this.application = application;
		this.testEnvironment = environment;
		if (runLocation.toLowerCase().equals("remote"))
			isGrid = true;
		System.out.println("**********************************************");
		System.out.println("**********************************************");
		System.out.println("**********************************************");
		System.out.println("browserUnderTest: " + browserUnderTest);
		System.out.println("runLocation: " + runLocation);
		System.out.println("isGrid: " + String.valueOf(isGrid));
		System.out.println("testName: " + testName);
		System.out.println("application: " + application);
		System.out.println("environment: " + environment);
		System.out.println("preferedArchitecture: " + preferedArchitecture);
		System.out.println("**********************************************");
		System.out.println("**********************************************");
		System.out.println("**********************************************");
		new FrameworkHarness().hookToWDPro(application, environment, 60, browserUnderTest, isGrid);
		SeleniumWrapper seleniumWrapper = new SeleniumWrapper();
		seleniumWrapper.getConfiguration().setPreferedArchitecture(preferedArchitecture);

		// Set the driver type based on the browserUnderTest
		switch (browserUnderTest.toLowerCase()) {
		case "ie":
		case "iexplorer":
			seleniumWrapper.getConfiguration().setDriverType(DriverType.INTERNET_EXPLORER);
			break;
		case "firefox":
			seleniumWrapper.getConfiguration().setDriverType(DriverType.FIREFOX);
			break;
		case "chrome":
			seleniumWrapper.getConfiguration().setDriverType(DriverType.CHROME);
			break;
		case "htmlunit":
			seleniumWrapper.getConfiguration().setDriverType(DriverType.HTML_UNIT);
			break;

		}

		try {
			
			//Issue #138 - Adding a loop for driver initialization to remedy grid issues
			int tries =0;
			int maxTries = 5;
			do{
				// if attempting a retry, pausing briefly to between 3 and 7 seconds to allow congestion to clear
				if(tries != 0) Sleeper.sleep(Randomness.randomNumberBetween(3000, 7000));
				driver = seleniumWrapper.initialize(testName);
				tries++;
			}while(driver == null && tries < maxTries);
			
			if(tries == maxTries && driver == null) throw new WebDriverException("Failed to create a driver");
		
			//threadedDriver.set(seleniumWrapper.initialize(testName));
			//Commenting out to test for any residual affects
			//driver.manage().deleteAllCookies();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(isGrid && System.getProperty(Constants.SELENIUM_RC_HOST_PROPERTY).contains("dnhprpptstw701")){
			String sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
			TestReporter.logVideo(sessionId);
		}
		return driver;
		//return threadedDriver.get();
	}

	public void launchApplication(WebDriver driver) {
		System.out.println(application.toUpperCase() + "_" + testEnvironment.toUpperCase());
		System.out.println(appURLRepository);
		System.out.println(appURLRepository.getString(application.toUpperCase() + "_" + testEnvironment.toUpperCase()));


		if(driver == null) throw new RuntimeException("Launch Application: driver null");
		if(application == null) throw new RuntimeException("Launch Application: application null");
		if(testEnvironment == null) throw new RuntimeException("Launch Application: testEnvironment null");
		if(appURLRepository == null) throw new RuntimeException("Launch Application: appURLRepository null");
		
		driver.get(appURLRepository.getString(application.toUpperCase() + "_" + testEnvironment.toUpperCase()));
		System.out.println("Initial URL: "
				+ appURLRepository.getString(application.toUpperCase() + "_" + testEnvironment.toUpperCase()));
//		if(application.toLowerCase().equalsIgnoreCase("lilo")) AlertHandler.handleAlerts(driver, 5);
	}

	public static String getBrowserUnderTest() {
		return System.getProperty("selenium.rc_type");
	}

	public static String getApplicationUnderTest() {
		return System.getProperty("selenium.application");
	}

	public static String getEnvironmentUnderTest() {
		return System.getProperty("selenium.environment");
	}

	public static int getDefaultTestTimeout() {
		return Integer.parseInt(System.getProperty("selenium.default_timeout"));
	}

	@Parameters({ "environment" })
	// @BeforeTest
	public void setEnvironment(String environment) {
		setCurrentEnvironment(environment);
	}

	private void setCurrentEnvironment(String environment) {
		testEnvironment = environment;
	}

	public String getCurrentEnvironment() {
		return testEnvironment;
	}

	public void setCurrentApplication(String application) {
		testApplication = application;
	}

	public String getCurrentApplication() {
		return testApplication;
	}

	public void setDriverWindow(String window) {
		driverWindow = window;
	}

	public String getDriverWindow() {
		return driverWindow;
	}

	public ResourceBundle getEnvironmentURLRepository() {
		return appURLRepository;
	}

	public static void setDefaultTestTimeout(int timeout) {
		TIMEOUT = timeout;
	}	 

	public void setDriver(WebDriver driverSession) {
		driver = driverSession;
	}
}

