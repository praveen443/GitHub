package apps.lilo.quickBook;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Link;
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
 * @author Atmakuri Venkatesh
 * 
 */
public class QuickBook_ModPartyMix {

	// ******************************
	// *** Main Page Fields ***
	// ******************************

	private List<WebElement> rows_GuestTable;
	private int Iterator;
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	// ***********************
	// Page Class Elements
	// ***********************

	// Click On Modify Reservation Link
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:0:modresLinkId")
	private Link lnkModifyReservation;

	// Click PartyMix Radio Button
	@FindBy(id = "modResFormPanelSelectionId:selectedPanelId:2")
	private Element elePartyMix;

	// Click PartyMix Radio Button
	@FindBy(id = "modResPartyFormId:addGuestId")
	private Button btnAddGuest;

	// Select PartyMixTitle
	@FindBy(id = "modResPartyFormId:guestList:1:prefixSelect")
	private Listbox lstPartyMixTitle;

	// Select PartyMix FirstName
	@FindBy(id = "modResPartyFormId:guestList:1:firstNameInput")
	private Textbox txtPartyMixFirstName;

	// Select PartyMix LastName
	@FindBy(id = "modResPartyFormId:guestList:1:lastNameInput")
	private Textbox txtPartyMixLastName;

	// Click Submit Button
	@FindBy(id = "modResActionsFormId:submit1")
	private Button btnSubmit;

	// Click Close Button
	@FindBy(id = "forModResClose:close")
	private Button btnClose;

	// Click Guests tab
	@FindBy(id = "viewGuestTabPanel_lbl")
	private Button btnGuests;

	// Guests table
	@FindBy(id = "viewGuestForm:guestList:tb")
	private WebElement eleGuest;

	// Click Exit Button
	@FindBy(id = "viewGuestForm:exitButtonId")
	private Button btnExit;

	// Click LOg Out
	@FindBy(id = "header1Form:logoutText")
	private Link lnkLogout;

	// *********************
	// ** Build page area **
	// *********************

	/**
	 * @summary Constructor to initialize the page
	 * @author Atmakuri Venkatesh
	 * @version 10/28/2015 Atmakuri Venkatesh - original
	 * @param driver
	 */
	public QuickBook_ModPartyMix(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Atmakuri Venkatesh
	 * @version 10/28/2015 Atmakuri Venkatesh - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public QuickBook_ModPartyMix initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Atmakuri Venkatesh
	 * @version 10/28/2015
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				lnkModifyReservation);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				element);
	}

	// *************************************************
	// Page Recommend and Manage Tickets Class Methods
	// *************************************************

	/**
	 * @summary Method to Add Guest
	 * @version Created 10/28/2015
	 * @author Atmakuri Venkatesh
	 * @param Scenario
	 * @return NA
	 */

	public void partyMix(String scenario) {
		pageLoaded();
		lnkModifyReservation.click();

		pageLoaded(elePartyMix);
		elePartyMix.click();

		pageLoaded(btnAddGuest);
		btnAddGuest.click();

		datatable.setVirtualtablePage("QuickBookGuestInfo");
		datatable.setVirtualtableScenario(scenario);

		pageLoaded(lstPartyMixTitle);
		lstPartyMixTitle.select(datatable.getDataParameter("Title"));
		txtPartyMixFirstName.safeSet(datatable
				.getDataParameter("PartytFirstName"));
		txtPartyMixLastName
				.safeSet(datatable.getDataParameter("PartyLastName"));
		btnSubmit.click();

		Sleeper.sleep(7000);
		btnClose.click();

		pageLoaded(btnGuests);
		btnGuests.click();

		partyMixGuestValidation(scenario);

		btnExit.click();

	}

	/**
	 * @summary Method to Validate the QuickBookPartyMix Guests
	 * @author Atmakuri Venkatesh
	 * @param Scenario
	 * @version 10/29/2015
	 * @return NA
	 */

	public void partyMixGuestValidation(String scenario) {
		Sleeper.sleep(5000);

		// To locate rows of table.
		rows_GuestTable = eleGuest.findElements(By.tagName("tr"));

		// Capturing Guest Details
		for (Iterator = 0; Iterator <= rows_GuestTable.size() - 5; Iterator++) {
			TestReporter.logStep("Total Guests Lists");
			TestReporter.logStep("Primay Guest : ["
					+ driver.findElement(
							By.id("viewGuestForm:guestList:" + Iterator
									+ ":firstName")).getText()
					+ " "
					+ driver.findElement(
							By.id("viewGuestForm:guestList:" + Iterator
									+ ":lastName")).getText() + "]");

			TestReporter.logStep("New Party  : ["
					+ (Iterator + 1)
					+ " "
					+ driver.findElement(
							By.id("viewGuestForm:guestList:" + Iterator
									+ ":firstName")).getText()
					+ " "
					+ driver.findElement(
							By.id("viewGuestForm:guestList:" + Iterator
									+ ":lastName")).getText() + "]");

		}
	}
}

