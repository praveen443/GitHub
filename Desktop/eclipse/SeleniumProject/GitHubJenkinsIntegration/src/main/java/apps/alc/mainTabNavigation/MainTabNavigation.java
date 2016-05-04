package apps.alc.mainTabNavigation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.alc.pleaseWait.PleaseWait;
import core.interfaces.Element;
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
public class MainTabNavigation {
	// *******************
	// Page Class Fields
	// *******************
	// Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	// *********************
	// Page Class Elements
	// *********************
	@FindBy(id = "newReservationTab_lbl")
	private Element eleNewReservationTab;

	@FindBy(id = "existingReservationTab_lbl")
	private Element eleExistingReservationTab;

	@FindBy(id = "fulfillmentTab_lbl")
	private Element eleFulfillmentTab;

	@FindBy(id = "jmsTestUtility_lbl")
	private Element eleJmsTab;

	@FindBy(id = "reportingTab_lbl")
	private Element eleReportingTab;

	@FindBy(id = "massCancelTab_lbl")
	private Element eleMassCancelTab;

	@FindBy(id = "admin2_lbl")
	private Element eleAdminTab;

	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
	 * @param driver
	 */
	public MainTabNavigation(WebDriver driver) {
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
	public MainTabNavigation initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, eleNewReservationTab);
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
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	// ********************
	// Page Class Methods
	// ********************

	public void clickNewReservationTab() {
		initialize();
		pageLoaded(eleNewReservationTab);
		eleNewReservationTab.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void clickExistingReservationTab() {
		pageLoaded(eleExistingReservationTab);
		eleExistingReservationTab.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void clickFulfillmentTab() {
		initialize();
		pageLoaded(eleFulfillmentTab);
		eleFulfillmentTab.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void clickJmsTab() {
		initialize();
		pageLoaded(eleJmsTab);
		eleJmsTab.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void clickReportingTab() {
		initialize();
		pageLoaded(eleReportingTab);
		eleReportingTab.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void clickMassCancelTab() {
		initialize();
		pageLoaded(eleMassCancelTab);
		eleMassCancelTab.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void clickAdminTab() {
		initialize();
		pageLoaded(eleAdminTab);
		eleAdminTab.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}
}


