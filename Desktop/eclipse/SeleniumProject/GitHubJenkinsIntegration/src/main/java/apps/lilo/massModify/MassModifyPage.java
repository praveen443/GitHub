package apps.lilo.massModify;

import java.util.List;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.CheckboxImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

/**
 * 
 * @summary Contains the page methods & objects for the Mass Modify page
 * @version Created 10/29/2014
 * @author Jessica Marshall
 */
public class MassModifyPage {
	//
	// MassModify Fields
	//
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	// *************************************
	// *** Page Elements ***
	// *************************************
	// Reservation details
	@FindBy(id = "MassModifyForm:resDetailsButtonID")
	private Button btnResDetails;

	// Billing profile
	@FindBy(id = "MassModifyForm:billingProfileButtonID")
	private Button btnBillingProfile;

	// RSR Billing
	@FindBy(id = "MassModifyForm:rsrBillingButtonID")
	private Button btnRSRBilling;

	// Tickets
	@FindBy(id = "MassModifyForm:ticketsButtonID")
	private Button btnTickets;

	// Reinstate
	@FindBy(id = "MassModifyForm:reinstateButtonID")
	private Button btnReinstate;

	// Remove group
	@FindBy(id = "MassModifyForm:removeGroupButtonID")
	private Button btnRemoveGroup;

	// Cancel reservation
	@FindBy(id = "MassModifyForm:cancelResID")
	private Button btnCancelRes;

	// Submit
	@FindBy(id = "MassModifyForm:submitButtonID")
	private Button btnSubmit;

	// Exit
	@FindBy(id = "MassModifyForm:exitButtonID")
	private Button btnExit;

	// Mass modify unique name
	@FindBy(id = "MassModifyForm:processNameID")
	private Textbox txtMassModifyName;

	// Mass modify results table
	@FindBy(id = "MassModifyForm:massModifyDataListID:tb")
	private Webtable tblMassModifyResults;

	// Unexpected error pop up
	@FindBy(id = "PMSRErrorModalPanelDiv")
	private Element eleErrorPopUp;

	// Error pop up ok
	@FindBy(id = "errorForm:okButtonId")
	private Button btnErrorOk;

	// *********************
	// ** Build page area **
	// *********************

	/**
	 * @summary Constructor to initialize the page
	 * @version Created 9/11/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public MassModifyPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Constructor to initialize the page
	 * @version Created 9/11/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public MassModifyPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Constructor to initialize the page
	 * @version Created 9/11/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public boolean loadPage() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnResDetails);
	}

	// *****************************************
	// ***Page Interactions ***
	// *****************************************

	public void clickResDetails() {
		btnResDetails.syncVisible(driver);
		btnResDetails.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickBillingProfile() {
		btnBillingProfile.syncVisible(driver);
		btnBillingProfile.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickCancelRes() {
		btnCancelRes.syncVisible(driver);
		btnCancelRes.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickExit() {
		btnExit.syncVisible(driver);
		btnExit.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickReinstate() {
		btnReinstate.syncVisible(driver);
		btnReinstate.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickRemoveGroup() {
		btnRemoveGroup.syncVisible(driver);
		btnRemoveGroup.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickRSRBilling() {
		btnRSRBilling.syncVisible(driver);
		btnRSRBilling.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickSubmit() {
		btnSubmit.syncVisible(driver);
		btnSubmit.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickTickets() {
		btnTickets.syncVisible(driver);
		btnTickets.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public String getUniqueMassModifyID() {
		return txtMassModifyName.getAttribute("value");
	}

	public void clickErrorPopUpOk() {
		btnErrorOk.syncVisible(driver);
		btnErrorOk.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * @summary Checks the checkbox for the row that has the specified
	 *          reservation number. If the row is not found, then return false
	 *          else return true
	 * @version Created 10/29/2014
	 * @author Jessica Marshall
	 * @param reservationNum
	 * @throws NA
	 * @return false if the reservation number is not found in the table,
	 *         otherwise true
	 */
	public boolean checkAReservation(String reservationNum) {

		tblMassModifyResults.syncVisible(driver);

		// Get all the rows in a list
		List<WebElement> rowList = tblMassModifyResults.findElements(By
				.tagName("tr"));

		// If there are no rows, then return false
		if (rowList.isEmpty())
			return false;

		// Find the row that contains the reservation number
		for (WebElement row : rowList) {
			// Get a list of all the table data elements
			List<WebElement> cellList = row.findElements(By.tagName("td"));

			// if the 3rd cell has the specified reservation number, then check
			// the checkbox
			if (cellList.get(2).getText().equals(reservationNum)) {
				// The first element is the checkbox cell
				// cellList.get(0).findElement(By.cssSelector("input[type='checkbox']")).click();
				Checkbox checkbox = new CheckboxImpl(cellList.get(0)
						.findElement(By.cssSelector("input[type='checkbox']")));
				// checkbox.highlight(driver);
				checkbox.jsToggle(driver);
				return true;
			}
		}
		Reporter.log("Travel Plan ID: " + reservationNum
				+ " was not found in search results");
		return false;
	}

	/**
	 * @summary Selects a reservation from the results, checks the checkbox, and
	 *          clicks the reservation details button. If an error displays, or
	 *          the reservation was not found in the tablethen handles it and
	 *          returns a false, else returns a true
	 * @version Created 10/29/2014
	 * @author Jessica Marshall
	 * @param NA
	 * @throws NA
	 * @return false if error pop up displays, true if not
	 */
	public boolean editReservationDetailsForAReservation(String reservationNum) {
		boolean results;
		// find the reservation & check the checkbox
		results = checkAReservation(reservationNum);

		if (results) {
			clickResDetails();
			if (handleErrorPopUp())
				return false;
		} else
			return false;

		return true;
	}

	/**
	 * @summary Handles an error pop up if it displays. If it displays, returns
	 *          true, else returns false
	 * @version Created 10/29/2014
	 * @author Jessica Marshall
	 * @param NA
	 * @throws NA
	 * @return true if error pop up displays, false if not
	 */
	public boolean handleErrorPopUp() {
		boolean found = eleErrorPopUp.syncVisible(driver, 3, false);
		if (found) {
			// output the error message
			Reporter.log("Error message displayed"
					+ driver.findElement(By.tagName("li")).getText());
			clickErrorPopUpOk();
		}

		return found;
	}

}

