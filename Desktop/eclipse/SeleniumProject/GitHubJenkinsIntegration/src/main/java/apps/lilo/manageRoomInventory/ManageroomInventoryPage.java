package apps.lilo.manageRoomInventory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.TestReporter;
import utils.date.DateTimeConversion;

/**
 * @Summary : This class contains elements and element interactions for a given
 *          page of a web application.
 *
 * @author : Praveen Namburi
 * @version : 10/27/2015
 */
public class ManageroomInventoryPage {

	// *******************
	// Page Class Fields
	// *******************
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	// *********************
	// Page Class Elements
	// *********************

	// Page Object for button Find.
	@FindBy(id = "manageRoomInventory:findButtonId")
	private Button btnFind;

	// Object for Inventory Status ComboList.
	@FindBy(id = "manageRoomInventory:invStatusComboBoxId")
	private Listbox lstInvenStatus;

	// Object for Anticipated Start Date.
	@FindBy(id = "manageRoomInventory:startDateInput")
	private Textbox txtAnticipatedDate;

	// Object for Anticipated Start Date TO input.
	@FindBy(id = "manageRoomInventory:startDateToInput")
	private Textbox txtAnticipatedStartDateTO;

	// **************************************
	// ---- Warning POP-UPp elements
	// **************************************
	// Object for Finding the Warning POP-UP
	@FindBy(id = "PMSRErrorModalPanelContentDiv")
	private Element elewarningPOPuP;

	// Created page Object for getting the Warning pop-up message
	@FindBy(xpath = "//table/tbody/tr/td/table/tbody/tr/td[2]/div/ul/li")
	private Element eleGetWarningPopUpMessage;

	// Ok button in waring pop-up
	@FindBy(id = "errorForm:okButtonId")
	private Button btnOKinWarningPopUp;

	// **************************************

	// Page Object for Exit button
	@FindBy(id = "j_id150:resortsExitButton")
	private Button btnExit;

	// Object for Manage Room Inventory Table search list.
	@FindBy(id = "manageRoomInventory:roomSearchList")
	private Webtable tblRoomSearchList;

	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary : Constructor to initialize the page
	 * @author : Praveen Namburi
	 * @version : 11/26/2015
	 * @param : driver
	 */
	public ManageroomInventoryPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary : Method to initialize all proxy elements for this page
	 * @author : Praveen Namburi
	 * @version : 11/26/2015
	 * @return : an instance of this page with the proxy elements initialized
	 */
	public ManageroomInventoryPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary : Method to determine if Manage Room Inventory page is loaded
	 * @author : Praveen Namburi
	 * @version : 11/26/2015
	 * @return : Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnFind);
	}

	/**
	 * @summary : Method to determine if a page is loaded
	 * @author : Praveen Namburi
	 * @version : 11/26/2015
	 * @param : element - Element to be used to determine if the page is loaded
	 * @return : Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				element);
	}

	// ********************
	// Page Class Methods
	// ********************

	/**
	 * @Summary : This method clicks the Find button and waits for the Room
	 *          Search table to be displayed.
	 * @author : Praveen Namburi
	 * @version : Created 11/26/2015
	 * @return : NA
	 */
	private void clickbtnFind() {
		pageLoaded(btnFind);
		btnFind.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);

	}

	/**
	 * @Summary : Method to enter and Search RoomInventory Status within 90Days.
	 * @author : Praveen Namburi
	 * @version : Created 11/26/2015
	 * @return : NA
	 */
	public void enterRoomInventoryDetails_For90Days(String scenario) {

		datatable.setVirtualtablePage("EnterManageRommInventoryPageInfo");
		datatable.setVirtualtableScenario(scenario);

		// Initialize the page.
		initialize();

		pageLoaded(lstInvenStatus);
		lstInvenStatus
				.select(datatable.getDataParameter("InventoryStatusList"));

		// set the Anticipated date values from datatable.
		// Set Anticipated Date range:
		String AnticipatedDate = DateTimeConversion.getDaysOut(
				datatable.getDataParameter("AnticipatedDate_DaysOut"),
				"MM/dd/yyyy");
		TestReporter
				.log("Anticipated Satrt Date value is : " + AnticipatedDate);
		pageLoaded(txtAnticipatedDate);
		txtAnticipatedDate.safeSet(AnticipatedDate);

		// Set Anticipated Date TO range:
		String AnticipatedStartDateTO = DateTimeConversion.getDaysOut(
				datatable.getDataParameter("AnticipatedDateTO_DaysOut"),
				"MM/dd/yyyy");
		TestReporter.log("Anticipated Start Date TO value is : "
				+ AnticipatedStartDateTO);
		pageLoaded(txtAnticipatedStartDateTO);
		txtAnticipatedStartDateTO.safeSet(AnticipatedStartDateTO);
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		// Click on Find button.
		clickbtnFind();
	}

	/**
	 * @summary : Created Method to handle Warning pop-up message if displayed
	 *          or to get the Room inventory details from manage Room inventory
	 *          Search list table.
	 * @author : Praveen Namburi
	 * @version : Created 11/26/2015
	 */
	public void getRoomInventoryDetails_OR_handleWarningPopUP() {

		initialize();

		if (elewarningPOPuP.isDisplayed()) {

			TestReporter.logStep("Warning Pop-Up message is displayed.");

			pageLoaded(eleGetWarningPopUpMessage);

			// get the warning pop-up message
			String getWarningpPopUpMessage = eleGetWarningPopUpMessage
					.getText();
			TestReporter.logStep("Warning Pop-up message is : "
					+ getWarningpPopUpMessage);

			// Click on ok button.
			pageLoaded(btnOKinWarningPopUp);
			btnOKinWarningPopUp.jsClick(driver);
			new ProcessingYourRequest().WaitForProcessRequest(driver);

			// Click on the Exit button.
			pageLoaded(btnExit);
			btnExit.jsClick(driver);
			new ProcessingYourRequest().WaitForProcessRequest(driver);

		} else if (tblRoomSearchList.isDisplayed()) {

			// grab the table and get the row count
			int getRowsCount = tblRoomSearchList.getRowCount();
			System.out.println("Total no. of rows in the table is :"
					+ getRowsCount);

			// get all the TR elements from the table
			List<WebElement> allRows = tblRoomSearchList.findElements(By
					.tagName("tr"));

			// And iterate over them, getting the cells
			for (WebElement row : allRows) {
				List<WebElement> cells = row.findElements(By.tagName("td"));

				// Print the contents of each cell
				for (WebElement cell : cells) {
					TestReporter.logStep(cell.getText());
				}
			}

			// Click on the Exit button.
			pageLoaded(btnExit);
			btnExit.jsClick(driver);
			new ProcessingYourRequest().WaitForProcessRequest(driver);
		}

	}

}

