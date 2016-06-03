package apps.lilo.searchToAddGuest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import core.interfaces.Element;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

public class SearchToAddGuest {
	// *******************
	// Page Class Fields
	// *******************
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	// *********************
	// Page Class Elements
	// *********************
	@FindBy(id = "searchGuestsForm:tapMediaId")private Textbox txtMagicbandNumber;
	
	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Venkatesh Athmakuri
	 * @version 12/07/2015 Venkatesh Athmakuri - original
	 * @param driver
	 */
	public SearchToAddGuest(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Venkatesh Athmakuri
	 * @version 12/07/2015 Venkatesh Athmakuri - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public SearchToAddGuest initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Venkatesh Athmakuri
	 * @version 12/07/2015 Venkatesh Athmakuri - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				txtMagicbandNumber);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Venkatesh Athmakuri
	 * @version 12/07/2015 Venkatesh Athmakuri - original
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
}
