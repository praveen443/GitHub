package apps.lilo.reinstateReservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Label;
import core.interfaces.Listbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.TestReporter;

/**
 * This class contains elements and element interactions for a given page of a
 * web application
 * 
 * @summary Contains the page methods & objects for Reinstate Reservation window
 * @version Created 10/29/2015
 * @author Praveen Namburi
 */
public class ReinstateReservationPage {

	// **********************************************
	// *** Main Reinstate Reservation Page Fields ***
	// **********************************************
//	private int loopCount = 0;
//	private int timeout = WebDriverSetup.getDefaultTestTimeout();
//	private String getCancelledstatus = "";
//	private String ReservationNumber = "";
//	private String getResevStatusAfterReinstate = "";
	private WebDriver driver;
//	private DataProvider_ExcelSheet datapro = new DataProvider_ExcelSheet();
	private Datatable datatable = new Datatable();

	// ************************************************
	// *** Main Reinstate reservation Page Elements ***
	// ************************************************

	// Create Button object for Cancel
	@FindBy(id = "reinstateResForm:cancelButtonId")
	private Button btntnCancel;

	// Create Button object for Reinstate With Price
	@FindBy(id = "reinstateResForm:reinstWithPriceButtonId")
	private Button btnReinstateWithPrice;

	// Create Button object for the Reinstate button
	@FindBy(id = "reinstateResForm:reinstButtonId")
	private Button btnReinstate;

	// Create Button object for the Reinstate button
	@FindBy(id = "reinstateResForm:amountLabelId")
	private Label lblCancellationFeeApplied;

	// Create a Page Object for the Reinstate Reservation window
	@FindBy(id = "showReinstResModalPanelContentDiv")
	private Element eleReinstateWindow;

	// Create a Page Object for the Reason Listbox
	@FindBy(id = "reinstateResForm:reasons")
	private Listbox lstSelectReinstateReason;

	// ***************************
	// ***** Build page area *****
	// ***************************

	/**
	 * 
	 * @summary Constructor to initialize the Reason Reinstate Page.
	 * @version Created 10/29/2015
	 * @author Praveen Namburi
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public ReinstateReservationPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public ReinstateReservationPage initialize() {
		return ElementFactory.initElements(driver,
				ReinstateReservationPage.class);
	}

	public void ReasonReinstateLoaded() {
		while (!btnReinstate.syncVisible(driver)) {
			initialize();
		}
		btnReinstate.syncVisible(driver);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnReinstate);
	}

	public boolean pageLoaded(Button button) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				button);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				element);
	}

	// ******************************************************
	// *** Main QuickbookCancelReinstatePage Interactions ***
	// ******************************************************

	/**
	 * 
	 * @summary Created Method to Select a cancellation reason from the "Reason"
	 *          listbox and submit it.
	 * @version Created 10/29/2015
	 * @author Praveen Namburi
	 * @param driver
	 * @throws Exception
	 *             if datatable scenarios or parameters are not found
	 * @return NA
	 * 
	 */
	public void submitReasonReinstate(String scenario) {
		// Synchronize and find the Reinstate button in the Reinstate Page.
		ReasonReinstateLoaded();

		// Setup the virtual table name and scenarion name
		datatable.setVirtualtablePage("CancelReservationPage");
		datatable.setVirtualtableScenario(scenario);
		String reasonReinstate = datatable.getDataParameter("ReinstateReason");
		
		// Find the Reason Reinstates popup and highlight the element
		pageLoaded(eleReinstateWindow);
		eleReinstateWindow.highlight(driver);

		// Select the option in the Reason Listbox for cancellation
		/*
		 * TestReporter.logStep("Select the option in Reason list "+datatable.
		 * getDataParameter("SelectCancelReason"));
		 */
		lstSelectReinstateReason.click();
		lstSelectReinstateReason.select(datatable
				.getDataParameter("SelectCancelReason"));
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		if(reasonReinstate.equalsIgnoreCase("True")){
			// Click on Reinstate With Price button.
			TestReporter.logStep("Click on Reinstate With Price button");
			btnReinstateWithPrice.highlight(driver);
			btnReinstateWithPrice.jsClick(driver);
			new ProcessingYourRequest().WaitForProcessRequest(driver);
			
		}else{
			// Click on Reinstate button.
			TestReporter.logStep("Click on Reinstate button");
			btnReinstate.highlight(driver);
			btnReinstate.jsClick(driver);
			new ProcessingYourRequest().WaitForProcessRequest(driver);
		}
	}
	
}

