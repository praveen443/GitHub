package apps.hub;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javax.print.attribute.standard.MediaSize.NA;

import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.lilo.bankIn.BankInPage;
import apps.lilo.logout.LiloLogout;
import core.FrameHandler;
import core.WebDriverSetup;
import core.WindowHandler;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Textbox;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.ConversationID;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

/**
 * @summary Contains the page objects & methods required to login to an
 *          application through the HUB login page
 * @version Created 08/15/2014
 * @author Justin Phlegar
 * @modifed 09/15/2014 - Waightstill W Avery - Returned date driving to Master
 *          Data sheet
 */
public class HubLoginPage {
	private Datatable datatable = new Datatable();

	// ******************************
	// *** Main Hub Page Elements ***
	// ******************************

	// Create Textbox for Username
	@FindBy(name = "USER")
	private Textbox txtUserName;

	// Create Textbox for Password
	@FindBy(name = "PASSWORD")
	private Textbox txtPassword;

	// Create Button for Sign In
	@FindBy(name = "Enter")
	// @FindByNG(ngButtonText = "Sign In")
	private Button btnSignIn;

	// *********************
	// ** Build page area **
	// *********************
	private WebDriver driver;

	public HubLoginPage(WebDriver driver) {
		this.driver = driver;
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Sign In",1);
		ElementFactory.initElements(driver, this);
		// datatable.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);

	}

	public HubLoginPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	public boolean loadPage() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, txtUserName);
	}

	public boolean pageLoaded() {
		/*
		 * Sleeper.sleep(2000); AlertHandler.handleAlerts(driver, 2);
		 * 
		 * if(driver.getWindowHandles().size() == 2){ do{ Sleeper.sleep(1000);
		 * }while(driver.getWindowHandles().size() == 2); }
		 * 
		 * for(String handle : driver.getWindowHandles()){
		 * if(driver.switchTo().window(handle).getTitle().contains("Sign In")){
		 * break; } }
		 */driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		return txtPassword.syncPresent(driver); //new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, txtPassword);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	// **********************************
	// *** Main Hub Page Interactions ***
	// **********************************

	/**
	 * 
	 * @summary Method to login to the Hub page by determining and entering the
	 *          username and password
	 * @version Created 9/11/2014
	 * @author Justin Phlegar
	 * @param application
	 *            - application under test
	 * @param role
	 *            - user role
	 * @throws NA
	 * @return NA
	 * @modifed 09/15/2014 - Waightstill W Avery - imiplemented setSecure method
	 *          for password entry
	 */
	public void login(String application, String role) {
		final String username;
		final String password;
		final ResourceBundle userCredentialRepo = ResourceBundle
				.getBundle("environmentInfo.UserCredentials");

		username = userCredentialRepo.getString(application.toUpperCase() + "_" + role.toUpperCase());
		password = userCredentialRepo.getString(application.toUpperCase() + "_PASSWORD");
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		txtUserName.syncVisible(driver, 20, false);
		txtUserName.set(username);
		txtPassword.safeSetSecure(password);

		try {
			btnSignIn.submit();
			Sleeper.sleep(2000);
			driver.manage().timeouts().implicitlyWait( 0, TimeUnit.SECONDS);
			txtUserName.syncHidden(driver, 5, false);

		} catch (UnhandledAlertException e) {}

		applicationHandler(application, username, password);
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
	}

	private void applicationHandler(String application, String username, String password) {
		switch (application.toLowerCase()) {
		case "lilo":
			checkForHubceptionWaitForWindowAndSwap("Lilo", username, password);
			break;
		case "alc":
		case "dvc":
			//Sleeper.sleep(5000);
			WindowHandler.waitUntilWindowExistsTitleContains(driver, "Sign In", 5);

			if (txtUserName.syncVisible(driver, 5, false)) {
				txtUserName.set(username);
				txtPassword.safeSetSecure(password);
	
				try{
					btnSignIn.submit();
				}catch(UnhandledAlertException uae){}

				txtUserName.syncHidden(driver, 20, false);
			}
			if(application.toLowerCase().equals("dvc")){
				TestReporter.assertTrue(driver.findElement(By.id("logoutLink")).getText().toLowerCase().contains(username.toLowerCase().replace(".user", "")), "Validate proper user logged in");
			}
			break;
		case "passport":
		case "sbc_wdw":
		case "sbc_dlr":
			driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
			WindowHandler.waitUntilWindowExistsTitleContains(driver, "Sign In", 5);
			if(driver.getTitle().contains("Sign In")){
				txtUserName.set(username);
				txtPassword.safeSetSecure(password);
	
				try{
					btnSignIn.submit();
				}catch(UnhandledAlertException uae){}
				txtUserName.syncHidden(driver, 20, false);
			}
			WindowHandler.waitUntilWindowExistsTitleContains(driver, "Passport", 5);
			//SystemError.checkForSystemErrors(driver);
			
			break;
		case "dreams":
//			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
//			WindowHandler.waitUntilWindowExistsTitleContains(driver, "Sign In", 5);
//			try {
//				if (driver.getTitle().contains("Sign In")) {
//					if (txtUserName.syncVisible(driver, 5, false)) {
//						txtUserName.set(username);
//						txtPassword.safeSetSecure(password);
//						btnSignIn.submit();
//						txtUserName.syncHidden(driver, 20, false);
//					}
//				}
//			} catch (NoSuchWindowException nswe) {
//
//			}
//			driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
//			WindowHandler.waitUntilWindowExistsTitleContains(driver, "Disney Reservation Entry and Management System",
//					30);
			checkForHubceptionWaitForWindowAndSwap("Disney Reservation Entry and Management System", username, password);
			FrameHandler.findAndSwitchToFrame(driver, "leftFrame");
		default:
			break;
		}
	}
	
	private void checkForHubceptionWaitForWindowAndSwap(String titleContains, String username, String password){
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Sign In", 5);
		try {
			if (driver.getTitle().contains("Sign In")) {
				if (txtUserName.syncVisible(driver, 5, false)) {
					txtUserName.set(username);
					txtPassword.safeSetSecure(password);
					btnSignIn.submit();
					txtUserName.syncHidden(driver, 20, false);
				}
			}
		} catch (NoSuchWindowException nswe) {

		}
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, titleContains, 30);
	}

	/**
	 * 
	 * @summary Method to swap from the Hub login page to the application once
	 *          it is launched after login
	 * @version Created 8/15/2014
	 * @author Justin Phlegar
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void swapToApplication(String app) {
		// Utility.setCurrentWindow(driver);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, app, 1);
	}

	/**
	 * 
	 * @summary Method to login to the Hub page by determining and entering the
	 *          username and password
	 * @version Created 6/12/2015
	 * @author Jaya Tiwari
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public void loginFDO(String application, String role) {
		final String username;
		final String password;
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		final ResourceBundle userCredentialRepo = ResourceBundle
				.getBundle("environmentInfo.UserCredentials");

		username = userCredentialRepo.getString(application.toUpperCase() + "_" + role.toUpperCase());

		password = userCredentialRepo.getString(application.toUpperCase() + "_PASSWORD");
		Sleeper.sleep(2000);

		loadPage();

		txtUserName.syncVisible(driver, 20, false);

		txtUserName.set(username);

		txtPassword.safeSetSecure(password);
		// txtPassword.set(password);

		btnSignIn.jsClick(driver);

	}

	/**
	 * 
	 * @summary Method to login to the Hub page by determining and entering the
	 *          username and password, if user is already bankedIn click on
	 *          'OK'. if location is not matching will change location
	 * @version Created 9/10/2015
	 * @author Brajesh Ahirwar
	 * @param application-
	 *            application under test
	 * @param role
	 *            - user role
	 * @throws NA
	 * @return NA
	 * @throws Exception
	 */

	public boolean loginBankedIn(String environment, String application, String role, String resort, String locationId)
			throws Exception {
		final String username;
		final ResourceBundle userCredentialRepo = ResourceBundle
				.getBundle("environmentInfo.UserCredentials");

		username = userCredentialRepo.getString(application.toUpperCase() + "_" + role.toUpperCase());

		Boolean isUserBankedIn = false;
		BankInPage bankInPage = new BankInPage(driver);
		LiloLogout logout = new LiloLogout(driver);
		try {

			Sleeper.sleep(500);
			swapToApplication("Sign In");
			Element txtActual = new ElementImpl(driver.findElement(By.className("quickAccessPanel")));
			if (txtActual.isDisplayed()) {
				login(application, role);
				if (bankInPage.isBankInPage()) {
					bankInPage.bankIn(resort, role);
					isUserBankedIn = logout.txtMatchUser(resort, username, environment, locationId);
					return isUserBankedIn;
				} else {
					bankInPage.clickOnOk();
					isUserBankedIn = logout.txtMatchUser(resort, username, environment, locationId);
					return isUserBankedIn;
				}
			}
		} catch (Exception e) {

			if (bankInPage.isBankInPage()) {
				bankInPage.bankIn(resort, role);
				isUserBankedIn = logout.txtMatchUser(resort, username, environment, locationId);
				return isUserBankedIn;

			} else {
				bankInPage.clickOnOk();
				isUserBankedIn = logout.txtMatchUser(resort, username, environment, locationId);
				return isUserBankedIn;
			}

		}
		return isUserBankedIn;
	}

	/**
	 * Returns the conversation ID
	 * 
	 * @author Waightstill W Avery - Original
	 * @version
	 * @param application
	 *            - application under test
	 * @return String, conversation ID
	 */
	public String getConversationId(String application, String environment) {
		/*String convoId = "";
		boolean convoIdFound = true;

		String title = "";

		StopWatch watch = new StopWatch();
		watch.start();
		do {
			try {
				title = driver.getTitle();
			} catch (NoSuchWindowException e) {

			}
		}while(title.isEmpty());
		watch.stop();
		watch.reset();
		TestReporter.assertFalse(title.isEmpty(), "The title was not found");

		TestReporter.log("PAGE TITLE:" + driver.getTitle());
		convoId = driver.getTitle();
		
		try{			
			switch (application.toLowerCase()) {
			case "lilo":
				switch (environment.toLowerCase()) {
				case "sleepy":
				case "bashful":
				case "grumpy":
					convoId = convoId.split("-")[1];
					break;
				default:
					break;
				}
				break;
			case "alc":
				switch (environment.toLowerCase()) {
				case "sleepy":
				case "grumpy":
				case "bashful":
				case "doc":
					String convoParts[] = convoId.split(" ");
					convoId = convoParts[2] + " - " + convoParts[4];
					break;
				default:
					break;
				}
				break;
			case "dreams":
				convoId = convoId.split(" ")[8].split(",")[0];
				break;
			case "dvc":
				convoId = convoId.split(" ")[7];
				break;
			default:
				break;
			}
		}catch(ArrayIndexOutOfBoundsException aioobe){
			convoIdFound = false;
			TestReporter.assertTrue(convoIdFound, "An error occurred while attempting to grab the conversation ID.");
		}
		TestReporter.assertTrue(convoId != "",
				"The conversation ID was not captured for the application [" + application + "].");
		TestReporter.log("Conversation ID: " + convoId);*/
		String convoId = "";
		try{
			convoId = ConversationID.getConversationIdFromCurrentWindow(driver, application);
		}catch (ArrayIndexOutOfBoundsException aioobe){
			TestReporter.assertTrue(false, "An error occurred while attempting to grab the conversation ID.");
		}
		TestReporter.assertTrue(convoId != "",
				"The conversation ID was not captured for the application [" + application + "].");
		TestReporter.log("Conversation ID: " + convoId);
		return convoId;
	}
}

