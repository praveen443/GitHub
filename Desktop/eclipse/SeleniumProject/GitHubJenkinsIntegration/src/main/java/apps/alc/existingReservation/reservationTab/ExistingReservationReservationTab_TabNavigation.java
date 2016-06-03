package apps.alc.existingReservation.reservationTab;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import core.interfaces.Element;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

/**
 * This class contains elements and element interactions for a given page of a
 * web application
 * 
 * @author Waightstill W Avery
 * @version 12/15/2015 Waightstill W Avery - original
 */
public class ExistingReservationReservationTab_TabNavigation {
	// *******************
	// Page Class Fields
	// *******************
	// Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	// *********************
	// Page Class Elements
	// *********************
	@FindBy(id = "ReservationRecapTab_lbl")
	private Textbox eleDetailsTab;

	@FindBy(id = "ReservationHistoryTab_lbl")
	private Textbox eleHistoryTab;

	@FindBy(id = "PaymentHistoryTab_lbl")
	private Textbox elePaymenthistoryTab;

	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
	 * @param driver
	 */
	public ExistingReservationReservationTab_TabNavigation(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public ExistingReservationReservationTab_TabNavigation initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				eleDetailsTab);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
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
	 * @summary This method clicks the Details tab from existing reservation-
	 *          reservation tab page
	 * @author Marella Satish
	 * @version 01/06/2015 Marella Satish - original
	 * @param na
	 * @return na
	 */
	public void clickDetailsTab() {
		initialize();
		pageLoaded(eleDetailsTab);
		eleDetailsTab.jsClick(driver);
	}

	/**
	 * @summary This method clicks the History tab from existing reservation-
	 *          reservation tab page
	 * @author Marella Satish
	 * @version 01/06/2015 Marella Satish - original
	 * @param na
	 * @return na
	 */
	public void clickHistoryTab() {
		pageLoaded(eleHistoryTab);
		eleHistoryTab.jsClick(driver);
	}

	/**
	 * @summary This method clicks the Payment history tab from existing
	 *          reservation- reservation tab page
	 * @author Marella Satish
	 * @version 01/06/2015 Marella Satish - original
	 * @param na
	 * @return na
	 */
	public void clickPaymentHistoryTab() {
		pageLoaded(elePaymenthistoryTab);
		elePaymenthistoryTab.jsClick(driver);
	}
}

