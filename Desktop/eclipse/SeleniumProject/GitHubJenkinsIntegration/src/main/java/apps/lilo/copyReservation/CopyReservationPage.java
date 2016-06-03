package apps.lilo.copyReservation;

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
 * @version 11/17/2015 YourNameHere - original
 */
public class CopyReservationPage {
	// *******************
	// Page Class Fields
	// *******************
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	// *********************
	// Page Class Elements
	// *********************
	@FindBy(id = "copyResForm:bookButtonID")
	private Textbox btnBook;

	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Waightstill W Avery
	 * @version 11/17/2015 YourNameHere - original
	 * @param driver
	 */
	public CopyReservationPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Waightstill W Avery
	 * @version 11/17/2015 YourNameHere - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public CopyReservationPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 11/17/2015 YourNameHere - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnBook);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 11/17/2015 YourNameHere - original
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
	
	public void bookReservation(){
		initialize();
		pageLoaded(btnBook);
		btnBook.click();
	}
}
