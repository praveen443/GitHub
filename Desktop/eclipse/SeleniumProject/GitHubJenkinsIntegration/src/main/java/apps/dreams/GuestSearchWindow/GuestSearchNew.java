package apps.dreams.GuestSearchWindow;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.dreams.PleaseWait.PleaseWait;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;

/**
 * @summary Contains the methods & objects for the Dreams UI Guest Search window
 * @version Created 09/26/2014
 * @author Waightstill W. Avery
 */
public class GuestSearchNew {

	// ******************************
	// *** Content Frame Elements ***
	// ******************************
	private WebDriver driver;
	private Datatable datatable = new Datatable();

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
	public GuestSearchNew(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnNew);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				element);
	}

	public GuestSearchNew initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	// *****************************************
	// *** Content Frame Interactions ***
	// *****************************************
	public void searchForPrimaryGuest(String scenario) {
		// new PageLoaded().isDomComplete(driver);
		pageLoaded();
		enterSearchCriteria(scenario);
		clickSearch();
		selectGuestFromSearchResults(scenario);
	}

	public void addNewPrimaryGuest(String scenario) {
		enterSearchCriteria(scenario);
		clickNew();

	}

	public void enterSearchCriteria(String scenario) {
		datatable.setVirtualtablePage("PrimaryGuestList");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded(txtLastName);
		initialize();

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
		txtState.set(state);
		// txtCountry.set(country);
		txtPhone.set(phone);
		txtEmail.set(email);
	}

	public void clickSearch() {
		pageLoaded(btnSearch);
		initialize();
		btnSearch.highlight(driver);
		btnSearch.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void clickNew() {
		pageLoaded(btnNew);
		initialize();
		btnNew.highlight(driver);
		btnNew.jsClick(driver);
		Sleeper.sleep(2000);
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void selectGuestFromSearchResults(String scenario) {
		datatable.setVirtualtablePage("PrimaryGuest");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded();
		initialize();

		String lastName = datatable.getDataParameter("LastName");
		List<WebElement> elements = driver.findElements(By.linkText(lastName));

		elements.get(0).click();
	}
}

