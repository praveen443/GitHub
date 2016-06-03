package apps.alc.existingReservation.reservationTab;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.interfaces.Element;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.TestReporter;

/**
 * This class contains elements and element interactions for a given page of a
 * web application
 * 
 * @author Venkatesh Atmakuri
 * @version 01/06/2016 Venkatesh Atmakuri - original
 */
public class ExistingReservationReservationTab_PaymentHistoryTab {
	// *******************
	// Page Class Fields
	// *******************
	// Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	// *********************
	// Page Class Elements
	// *********************

	@FindBy(id = "paymentHistoryResultsTable")
	private Element eleResPaymentHistoryTable;

	@FindBy(xpath = "//*[@id='paymentHistoryResultsTable']/tbody/tr[1]/td[8]")
	private Element eleTerminalId;

	@FindBy(xpath = "//*[@id='paymentHistoryResultsTable']/tbody/tr[1]/td[6]")
	private Element eleGuestName;

	@FindBy(id = "paymentHistoryResultsTable:tb")
	private Element elePaymentHistoryTable;

	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Venkatesh Atmakuri
	 * @version 01/06/2016 Venkatesh Atmakuri - original
	 * @param driver
	 */
	public ExistingReservationReservationTab_PaymentHistoryTab(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Venkatesh Atmakuri
	 * @version 01/06/2016 Venkatesh Atmakuri - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public ExistingReservationReservationTab_PaymentHistoryTab initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Venkatesh Atmakuri
	 * @version 01/06/2016 Venkatesh Atmakuri - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				eleResPaymentHistoryTable);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Venkatesh Atmakuri
	 * @version 01/06/2016 Venkatesh Atmakuri - original
	 * @param element
	 *            - Element to be used to determine if the page is loaded
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				element);
	}

	// ********************
	// Page Class Methods
	// ********************

	/**
	 * 
	 * @summary This method gets and validate the TerminalId - Payment history
	 *          tab page
	 * @author Venkatesh Atmakuri
	 * @version 01/06/2015 Venkatesh Atmakuri - original
	 * @version 01/22/2016 Venkatesh Atmakuri
	 * @param NA
	 * @return NA
	 */
	public void validateTerminalID() {
		pageLoaded(elePaymentHistoryTable);
		elePaymentHistoryTable.highlight(driver);
		List<WebElement> trElements = elePaymentHistoryTable.findElements(By
				.tagName("tr"));
		int rowCount = trElements.size();
		System.out.println("Payment History Row Count : " + trElements.size());
		for (int Iterator = 1; Iterator <= rowCount; Iterator++) {
			String TerminalId = driver
					.findElement(
							By.xpath("//*[@id='paymentHistoryResultsTable']/tbody/tr["
									+ Iterator + "]/td[8]")).getText().trim();
			TestReporter.logStep("Terminal ID : " + TerminalId);
			TestReporter.assertNotEquals(TerminalId, "0001",
					"The given terminal id matches");
		}
	}

	/**
	 * @summary This method gets Guest Name - Payment history tab page
	 * @author Venkatesh Atmakuri
	 * @version 01/08/2016 Venkatesh Atmakuri - original
	 * @param na
	 * @return na
	 */
	public String getGuestName() {
		pageLoaded(eleGuestName);
		String guestName = eleGuestName.getText();
		TestReporter.logStep("Primary Guest Name : " + guestName);
		return guestName;
	}
}

