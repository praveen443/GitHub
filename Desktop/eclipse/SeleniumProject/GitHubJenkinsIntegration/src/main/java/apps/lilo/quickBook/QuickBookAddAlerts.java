package apps.lilo.quickBook;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

/**
 * This class contains elements and element interactions for a given page of a
 * web application
 *
 * @summary Contains the page methods & objects to enter Guest REservation
 *          comments information
 * @version Created 10/29/2015
 * @author Marella Satish
 */

public class QuickBookAddAlerts {

	// *****************
	// Page Class Fields
	// *****************
	private WebDriver driver;
	private String[] AlertView;
	private String FromTo;
	private String Message;
	private Datatable datatable = new Datatable();

	// Declare a local WebDriver to be used by class method
	// *********************
	// Page Class Elements
	// *********************

	// Create Button object for Add from Alerts panel
	@FindBy(id = "guestMeassageFormId:addAlert")
	private Button btnAddAlerts;

	// From Select List element
	@FindBy(xpath = "//*[@id='guestMeassageForm']/table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td/table/tbody/tr[1]/td[2]/select")
	private Listbox lstFrom;

	// To Select List element
	@FindBy(xpath = "//*[@id='guestMeassageForm']/table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/select")
	private Listbox lstTo;

	// Create Textbox object for Message text area
	@FindBy(id = "guestMeassageForm:comments1")
	private Textbox txtMessage;

	// Create Button object for Submit from Guest Message Details
	@FindBy(id = "guestMeassageForm:messageButtonId")
	private Button btnSubmit;

	// Create Element object for Guest Message Number in Alerts panel
	@FindBy(id = "guestMeassageFormId:viewguestmessagelink")
	private Element eleViewGuestMessage;

	// Create Element object for FirstName
	@FindBy(id = "travelSummeryFrm:firstNameLabelId")
	private Element eleFirstName;

	// Create Element object for LastName
	@FindBy(id = "travelSummeryFrm:lastNameLabelId")
	private Element eleLastName;

	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Marella Satish
	 * @version Created 10/29/2015
	 * @param driver
	 */
	public QuickBookAddAlerts(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Marella Satish
	 * @version Created 10/29/2015
	 * @return an instance of this page with the proxy elements initialized
	 */
	public QuickBookAddAlerts initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Marella Satish
	 * @version Created 10/29/2015
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnAddAlerts);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				element);
	}

	// *************************************************
	// Recommend AddGuest Page and Manage Tickets Class Methods
	// *************************************************

	/**
	 * 
	 * @summary Method to Add Alerts Message information
	 * @version Created 10/29/2015
	 * @author Marella Satish
	 * @param driver
	 * @throws Exception
	 *             if datatable scenarios or parameters are not found
	 * @return NA
	 * @throws InterruptedException
	 * 
	 */

	public void addAlerts(String scenario) {

		// Set Virtual table page
		datatable.setVirtualtablePage("GuestReservationComment");

		// SEt Virtual table Scenario
		datatable.setVirtualtableScenario(scenario);

		// Validates if the Add Alerts page loaded
		pageLoaded(btnAddAlerts);
		btnAddAlerts.jsClick(driver);

		// Getting the selection from and To values from the datatable
		// FromTo =
		// datatable.getDataParameter("FirstName")+" "+datatable.getDataParameter("LastName");

		FromTo = eleFirstName.getText() + " " + eleLastName.getText();

		TestReporter.logStep("From_To " + FromTo);

		// Selecting the given Guest FROM
		lstFrom.select(FromTo);

		// SElecting the given Guest TO
		lstTo.select(FromTo);

		// REtrieving the message from datatable and passing it to the text box
		Message = datatable.getDataParameter("Message");
		txtMessage.safeSet(Message);

		// Clicking on the submit
		btnSubmit.jsClick(driver);

		// Validates if the Add Alerts page loaded
		// pageLoaded(btnAddAlerts);

		Sleeper.sleep(3000);

		TestReporter.logStep("After adding alert message : "
				+ eleViewGuestMessage.getText());

		// Splits the value Guest message
		AlertView = StringUtils.split(eleViewGuestMessage.getText(), " ");

		TestReporter.logStep(AlertView[0]);
		Assert.assertEquals(AlertView[0], "1");

	}

}

