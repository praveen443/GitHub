package apps.dreams.GuestSearchWindow;

import java.util.List;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.dreams.PleaseWait.PleaseWait;
import core.WindowHandler;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Link;
import core.interfaces.Textbox;
import core.interfaces.impl.LinkImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import selenium.common.Constants;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

/**
 * @summary Contains the methods & objects for the Dreams UI Guest Search window
 * @version Created 09/26/2014
 * @author Waightstill W. Avery
 */
public class GuestSearch {
	private Datatable datatable = new Datatable();
	private WebDriver driver;
	private String fullName;

	// ******************************
	// *** Content Frame Elements ***
	// ******************************

	@FindBy(name = "NewAction")
	private Button btnNew;

	@FindBy(name = "SearchAction")
	private Button btnSearch;

	@FindBy(name = "individualFilterTO.lastName")
	private Textbox txtLastName;

	@FindBy(name = "individualFilterTO.firstName")
	private Textbox txtFirstName;

	@FindBy(name = "individualFilterTO.zipCode")
	private Textbox txtPostalCode;

	@FindBy(name = "individualFilterTO.city")
	private Textbox txtCity;

	@FindBy(name = "individualFilterTO.state")
	private Textbox txtState;

	@FindBy(name = "individualFilterTO.country")
	private Textbox txtCountry;

	@FindBy(name = "individualFilterTO.phone")
	private Textbox txtPhone;

	@FindBy(name = "individualFilterTO.email")
	private Textbox txtEmail;

	@FindBy(name = "lastNameExactMatchIndicator")
	private Checkbox chkExactMatch;

	// Search results table
	@FindBy(xpath = "//*[@id='firstLayer']/table[3]")
	private Checkbox tblSearchResults;

	@FindBy(id = "errorStr")
	private Element eleErrorMsg;

	@FindBy(name = "b_yes")
	private Button btnAlertOk;

	@FindBy(xpath = "//*[@id= 'LkUp1But']  [@value='Cancel']")
	private Button btnCancel;

	// *********************
	// ** Build page area **
	// *********************

	/**
	 * 
	 * @summary Constructor to initialize the window
	 * @version Created 09/26/2014
	 * @author Waightstill W Avery
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public GuestSearch(WebDriver driver) {
		this.driver = driver;
		// FrameHandler.findAndSwitchToFrame(driver, "contentFrame");
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);
	}

	// private GuestSearch initialize() {
	// //FrameHandler.findAndSwitchToFrame(driver, "contentFrame");
	// return ElementFactory.initElements(driver, GuestSearch.class);
	// }
	//
	// public void GuestSearchLoaded(WebDriver driver){
	// while (!btnNew.elementWired()){
	// new WindowHandler().swapToWindow(driver, "Guest Search");
	// initialize();
	// }
	// btnNew.syncVisible(driver);
	// }

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnNew);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				element);
	}

	public GuestSearch initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	// *****************************************
	// *** Content Frame Interactions ***
	// *****************************************
	public void searchForPrimaryGuest(String scenario) {
		enterSearchCriteria(scenario);
		clickSearch();
		selectGuestFromSearchResults(scenario);
	}

	/**
	 * @summary Method to pass primaryguest name
	 * @version Created 03/10/2016
	 * @author Sabitha Adama
	 */
	public String getPrimaryGuestFullName() {
		return fullName;
	}

	public void addNewPrimaryGuest(String scenario) {
		enterSearchCriteria(scenario);
		clickNew();

	}

	public void enterSearchCriteria(String scenario) {
		datatable.setVirtualtablePage("PrimaryGuest");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded(txtLastName);

		String lastName = datatable.getDataParameter("LastName");
		String firstName = datatable.getDataParameter("FirstName");
		String postalCode = datatable.getDataParameter("Zip");
		String city = datatable.getDataParameter("City");
		String state = datatable.getDataParameter("State");
//		String country = datatable.getDataParameter("Country");
		String phone = datatable.getDataParameter("PhoneNumber");
		String email = datatable.getDataParameter("Email");

		txtLastName.set(lastName);
		txtFirstName.set(firstName);
		txtPostalCode.set(postalCode);
		txtCity.set(city);
		txtState.set(state.toUpperCase());
		// txtCountry.set(country);
		txtPhone.safeSet(phone);
		txtEmail.set(email);

	}

	public void enterSearchCriteriaNameOnly(String scenario) {
		datatable.setVirtualtablePage("PrimaryGuest");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded(txtLastName);

		String lastName = datatable.getDataParameter("LastName");
		String firstName = datatable.getDataParameter("FirstName");

		txtLastName.set(lastName);
		txtFirstName.set(firstName);
	}

	public void clickSearch() {
		// initialize();
		pageLoaded(btnSearch);
		// btnSearch.jsClick(driver);
		btnSearch.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void clickNew() {
		pageLoaded(btnNew);
		btnNew.focus(driver);
		btnNew.sendKeys(Keys.ENTER);
//		btnNew.click();
		PleaseWait.WaitForPleaseWait(driver);
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Party");
		new PageLoaded().isDomComplete(driver);
	}

	public void selectGuestFromSearchResults(String scenario) {
		datatable.setVirtualtablePage("PrimaryGuest");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded();

		String lastName = datatable.getDataParameter("LastName");
		List<WebElement> elements = null;
		boolean guestsFound = false;
		int counter = 0;
		int guests = -1;
		do{
			try{
				elements = driver.findElements(By.linkText(lastName));
				guests = elements.size();
				guestsFound = true;
			}catch(Exception e){
				Sleeper.sleep(1000);
				counter++;
			}
		}while(counter < Constants.PAGE_TIMEOUT && guests == -1);
		TestReporter.assertTrue(guestsFound, "No guests were found in the search.");
		TestReporter.log("size : " + elements.size());

		Link link = new LinkImpl(elements.get(0));
		link.highlight(driver);
		link.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
		
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Disney Reservation Entry and Management System");
	}

	/**
	 * @summary Method to handle and Validate if Guest Search without passing
	 *          any search criteria
	 * @version Created 02/16/2016
	 * @author Marella Satish
	 * @param Datatable
	 *            scenario
	 * @throws NA
	 * @return NA
	 */
	public void validateGuestSearchAlert(String scenario) {
System.out.println();
		datatable.setVirtualtablePage("ErrorMessages");
		datatable.setVirtualtableScenario(scenario);

		String expectedError = datatable.getDataParameter("Message");
		if ((WindowHandler.waitUntilNumberOfWindowsAre(driver, 3))
				&& (WindowHandler.waitUntilWindowExistsTitleContains(driver,
						"Alert"))) {
			String getAlertMessage = eleErrorMsg.getText();
			TestReporter.log("Alert Message : " + getAlertMessage);
			TestReporter.assertEquals(expectedError, getAlertMessage,
					"The expected message" + "[ " + expectedError
							+ " ] is not same as actual message[ "
							+ getAlertMessage + " ]");
			btnAlertOk.highlight(driver);
			btnAlertOk.click();
			TestReporter.log("Alert Handled");
			WindowHandler.waitUntilWindowExistsTitleContains(driver,
					"Guest Search");
		}
	}

	/**
	 * @summary Method to click cancel button from guest search page
	 * @version Created 02/17/2016
	 * @author Marella Satish
	 * @param NA
	 * @throws NA
	 * @return NA
	 */
	public void clickCancel() {
		pageLoaded(btnCancel);
		btnCancel.click();
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Disney Reservation Entry and Management System");
	}

	public void searchGuestValidateError(String searchGuest) {
		datatable.setVirtualtablePage("PrimaryGuest");
		datatable.setVirtualtableScenario(searchGuest);
		
		String clickSearch = datatable.getDataParameter("ClickSearch");
		enterSearchCriteria(searchGuest);
		if(clickSearch.equalsIgnoreCase("true")){
			clickSearch();
		}
		validateGuestSearchAlert(searchGuest);
	}
}
