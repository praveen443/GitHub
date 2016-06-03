package apps.lilo.groupFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Element;
import core.interfaces.Link;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

/**
 * This class contains elements and element interactions for a given page of a
 * web application
 * 
 * @author Waightstill W very
 * @version 11/23/2015 Waightstill W very - original
 */
public class GroupFunctionsPage {
	// *******************
	// Page Class Fields
	// *******************
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	// *********************
	// Page Class Elements
	// *********************
	@FindBy(linkText = "Rooming List") 
	private Link lnkRoomingList;
	
	@FindBy(linkText = "Reservation List Summary")
	private Link lnkReservationListSummary;

	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Waightstill W very
	 * @version 11/23/2015 Waightstill W very - original
	 * @param driver
	 */
	public GroupFunctionsPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Waightstill W very
	 * @version 11/23/2015 Waightstill W very - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public GroupFunctionsPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W very
	 * @version 11/23/2015 Waightstill W very - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, lnkRoomingList);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W very
	 * @version 11/23/2015 Waightstill W very - original
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

	/**
	 * Clicks on the Rooming List link
	 */
	public void enterRoomingList(){
		initialize();
		pageLoaded(lnkRoomingList);
		lnkRoomingList.highlight(driver);
		lnkRoomingList.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	/*
	 * Clicks on the Reservation List Summary link
	 */
	public void enterReservationListSummary(){
		initialize();
		pageLoaded(lnkReservationListSummary);
		lnkReservationListSummary.highlight(driver);
		lnkReservationListSummary.jsClick(driver);
	}
}

