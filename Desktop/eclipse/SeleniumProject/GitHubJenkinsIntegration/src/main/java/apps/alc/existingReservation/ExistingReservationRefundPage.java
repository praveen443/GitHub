package apps.alc.existingReservation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.alc.pleaseWait.PleaseWait;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Label;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.TestReporter;

/**
 * This class contains elements and element interactions for a given page of a
 * web application
 * 
 * @author Stagliano, A Dennis
 * @version 1/6/2016 Stagliano, A Dennis - original
 */

public class ExistingReservationRefundPage {
	// *******************
	// Page Class Fields
	// *******************
	// Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	// *********************
	// Page Class Elements
	// *********************

	@FindBy(id = "processRefundPaymentViewForm:processRefundBalancePanelAmountPaidInput")
	private Label lblAmountPaid;

	@FindBy(id = "processRefundPaymentViewForm:processRefundBalancePanelBalDueInput")
	private Label lblRefundBalanceDue;

	@FindBy(id = "processRefundPaymentViewForm:processRefundBalancePanelHeaderOutput")
	private Label lblPanelHeaderOutput;

	@FindBy(id = "processRefundPaymentViewForm:refundAllButton")
	private Button btnRefundAll;

	@FindBy(id = "refundAllResultsForm:refundAllCloseButton")
	private Button btnRefundClose;

	@FindBy(id = "processRefundPaymentViewForm:processRefundBalancePanelBalDueInput")
	private Label lblFinalBalance;

	@FindBy(id = "processRefundPaymentViewForm:refundContinueButton")
	private Button btnContinue;

	@FindBy(id = "cancellationConfirmationForm:cancellationConfirmationPanel_header")
	private Label lblCompleteRefundSummaryHeader;

	@FindBy(id = "cancellationConfirmationForm:cancellationConfirmationPanel_body")
	private Element eleCompleteRefundSummaryBody;

	// *********************
	// ** Build page area **
	// *********************

	/**
	 * @summary Constructor to initialize the page
	 * @author Stagliano A Dennis
	 * @version 1/6/2016 Stagliano A Dennis - original
	 * @param driver
	 */

	public ExistingReservationRefundPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Stagliano A Dennis
	 * @version 1/6/2016 Stagliano A Dennis - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public ExistingReservationRefundPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Stagliano A Dennis
	 * @version 1/6/2016 Stagliano A Dennis - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnRefundAll);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Stagliano A Dennis
	 * @version 1/6/2016 Stagliano A Dennis - original
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
	public void writeOutCompleteRefundSummary() {
		initialize();
		String Header = lblCompleteRefundSummaryHeader.getText();
		Header = Header + "And Complete Refund Summary";
		System.out.println(Header);
		List<WebElement> trCollection = eleCompleteRefundSummaryBody.findElements(By.tagName("tr"));
		System.out.println("*************************************************");
		System.out.println("************ Complete Refund Summary ************");
		System.out.println("*************************************************");
		StringBuffer sb = new StringBuffer(" ");
		int counter = 0;
		for (WebElement trow : trCollection) {
			List<WebElement> tdcoll = trow.findElements(By.tagName("td"));
			for (WebElement tdElement : tdcoll) {
				if (counter % 2 == 0) {
					sb.append(System.getProperty("line.separator"));
				}
				sb.append(tdElement.getText() + " ");
				counter++;
			}
		}
		TestReporter.log(sb.toString());
	}

	/**
	 * @summary Method to verify that there was an amount paid for the
	 *          reservation, and there will be a refund
	 * @author Stagliano, Dennis
	 * @version 1/5/2016
	 * @param NA
	 * @return String
	 */
	public String totalAmountPaid() {
		String amountPaid = lblAmountPaid.getText();
		return amountPaid;
	}

	/**
	 * @summary Method to verify that there was an amount due back to Primary
	 *          for the reservation refund.
	 * @author Stagliano, Dennis
	 * @version 1/5/2016
	 * @param NA
	 * @return String
	 */
	public String totalRefundAmountDue() {
		pageLoaded(lblRefundBalanceDue);
		String refundDue = lblRefundBalanceDue.getText();
		return refundDue;
	}

	/**
	 * @summary Method to click Refund All button
	 * @author Stagliano, Dennis
	 * @version 1/5/2016
	 * @param NA
	 * @return NA
	 */
	public void clickRefundAll() {
		pageLoaded(btnRefundAll);
		btnRefundAll.jsClick(driver); // changed this
		PleaseWait.WaitForPleaseWait(driver); // added this
	}

	/**
	 * @summary Method to close popup for refund
	 * @author Stagliano, Dennis
	 * @version 1/7/2016
	 * @param NA
	 * @return NA
	 */
	public void clickRefundClose() {
		pageLoaded(btnRefundClose);
		btnRefundClose.jsClick(driver);
	}

	/**
	 * @summary Method to verify upcoming refund balance before finalizing
	 * @author Stagliano, Dennis
	 * @version 1/7/2016
	 * @param NA
	 * @return NA
	 */
	public String verifyFinalRefundBalance() {
		initialize();
		pageLoaded(lblFinalBalance);
		String finalRefundBalance = lblFinalBalance.getText();
		return finalRefundBalance;
	}

	/**
	 * @summary Method to click continue to get to the final refund Summary
	 * @author Stagliano, Dennis
	 * @version 1/7/2016
	 * @param NA
	 * @return NA
	 */
	public void clickContinue() {
		pageLoaded(btnContinue);
		btnContinue.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

}

