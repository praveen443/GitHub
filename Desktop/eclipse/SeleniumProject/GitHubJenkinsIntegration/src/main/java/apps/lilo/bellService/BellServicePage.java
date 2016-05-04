package apps.lilo.bellService;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

/**
 * This class contains elements and element interactions for the Bell Service page in Lilo UI 
 * 
 * @author Waightstill W Avery
 * @version 10/26/2015 Waightstill W Avery - original
 */
public class BellServicePage {
	// *******************
	// Page Class Fields
	// *******************
	// Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	//Define a variable to hold the table version
	private final String tableVersion = "j_id34955";

	// *********************
	// Page Class Elements
	// *********************
	//Bell Service popup
	@FindBy(id = "bellservicePopupForm")
	private Textbox eleBellServiceForm;
	
	//Room Number element
	@FindBy(css = "#bellservicePopupForm\\:"+tableVersion+"_lbl")
	private Element eleRoomNumber;
	
	//Service Type listbox
	@FindBy(css = "#bellservicePopupForm\\:"+tableVersion+" > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > select:nth-child(1)")
	private Listbox lstServiceType;
	
	//Bag Quantity textbox
	@FindBy(css = "#bellservicePopupForm\\:"+tableVersion+" > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > input:nth-child(1)")
	private Textbox txtBagQuantity;
	
	//Bag Source Location listbox
	@FindBy(css = "#bellservicePopupForm\\:"+tableVersion+" > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(2) > select:nth-child(1)")
	private Listbox lstBagSourceLocation;
	
	//Guest Location listbox
	@FindBy(css = "#guestLocationTable > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > select:nth-child(1)")
	private Listbox lstGuestLocation;
	
	//Bag Destination Location listbox
	@FindBy(css = "#bellservicePopupForm\\:"+tableVersion+" > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2) > select:nth-child(1)")
	private Listbox lstBagDestinationLocation;
	
	//Special Instruction textbox
	@FindBy(css = "#bellservicePopupForm\\:"+tableVersion+" > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(2) > input:nth-child(1)")
	private Textbox txtSpecialInstructions;
	
	@FindBy(id = "bellservicePopupForm:bellServiceButtonIdForCheckin")
	private Button btnSubmit;
	
	@FindBy(id = "bellservicePopupForm:closeButtonId")
	private Button btnClose;
	
	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Waightstill W Avery
	 * @version 10/26/2015 Waightstill W Avery - original
	 * @param driver
	 */
	public BellServicePage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Waightstill W Avery
	 * @version 10/26/2015 Waightstill W Avery - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public BellServicePage initialize(){
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 10/26/2015 Waightstill W Avery - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, eleBellServiceForm);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 10/26/2015 Waightstill W Avery - original
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
	
	
	
	
}

