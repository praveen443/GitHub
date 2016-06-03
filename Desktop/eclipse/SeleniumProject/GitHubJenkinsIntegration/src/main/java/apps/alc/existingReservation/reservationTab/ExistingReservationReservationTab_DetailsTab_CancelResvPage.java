package apps.alc.existingReservation.reservationTab;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.alc.pleaseWait.PleaseWait;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.TestReporter;

/**
 * This class contains elements and element interactions for a given page of a
 * web application
 * 
 * @author Praveen Namburi.
 * @version 01/07/2016 Praveen namburi - original
 */
public class ExistingReservationReservationTab_DetailsTab_CancelResvPage {

	// *******************
	// Page Class Fields
	// *******************
	// Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	// *********************
	// Page Class Elements
	// *********************
	// textbox ContactName
	@FindBy(id = "cancelConfirm:contactName")
	private Textbox txtContactName;

	// Listbox CancelReasonOptions
	@FindBy(id = "cancelConfirm:cancelReasonOptions")
	private Listbox lstCancelReasonOptions;

	@FindBy(id = "cancelConfirm:cancelConfirmCancel")
	private Button btnYes;

	@FindBy(id = "cancelConfirm:cancelConfirmNo")
	private Button btnNo;

	@FindBy(id = "cancellationConfirmationForm:cancelConfirmContinue")
	private Button btnContinue;

	@FindBy(id = "cancellationConfirmationForm:cancellationConfirmationPanel_body")
	private Element eleCancellationConfirmation;
	
	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Praveen Namburi.
	 * @version 01-07-2016 Praveen Namburi - original
	 * @param driver
	 */
	public ExistingReservationReservationTab_DetailsTab_CancelResvPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Praveen Namburi.
	 * @version 01-07-2016 Praveen Namburi - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public ExistingReservationReservationTab_DetailsTab_CancelResvPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Praveen Namburi.
	 * @version 01-07-2016 Praveen Namburi - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnYes);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Praveen Namburi.
	 * @version 01-07-2016 Praveen Namburi - original
	 * @param element
	 *            - Element to be used to determine if the page is loaded
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	// ********************
	// Page Class Methods
	// ********************

	// Click on button Yes.
	public void clickbtnYes() {
		// click btn Yes
		pageLoaded(btnYes);
		btnYes.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	// Click on button No.
	public void clickbtnNo() {
		// click btn No
		pageLoaded(btnNo);
		btnNo.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	// Click on button Continue.
	public void clickbtnContinue() {
		// click btn continue
		pageLoaded(btnContinue);
		btnContinue.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	/**
	 * @summary : Method to Cancel the existing Reservation.
	 * @author: praveen namburi, @version : Created 1-7-2016
	 * @param scenario
	 */
	public void CancelReservationInfo(String scenario) {
		datatable.setVirtualtablePage("CancelReservationDetailsTab");
		datatable.setVirtualtableScenario(scenario);

		String contactName = datatable.getDataParameter("ContactName");
		String cancelReasonOptions = datatable.getDataParameter("ReasonForCancel");
		String confirmCancel = datatable.getDataParameter("ConfirmCancel");
		String selectcontinue = datatable.getDataParameter("Selectcontinue");

		pageLoaded(txtContactName);
		txtContactName.safeSet(contactName);
		pageLoaded(lstCancelReasonOptions);
		lstCancelReasonOptions.select(cancelReasonOptions);

		if (confirmCancel.equalsIgnoreCase("Yes")) {
			clickbtnYes();
		} else {
			clickbtnNo();
		}

		pageLoaded(eleCancellationConfirmation);
		eleCancellationConfirmation.syncVisible(driver);
		TestReporter.assertFalse(eleCancellationConfirmation.getText().isEmpty(), "No information was captured for the cancellation confirmation.");
		TestReporter.log("Cancellation Confirmation Data: \n" + eleCancellationConfirmation.getText());
		
		if (selectcontinue.equalsIgnoreCase("Yes")) {
			clickbtnContinue();
		}
	}
}
