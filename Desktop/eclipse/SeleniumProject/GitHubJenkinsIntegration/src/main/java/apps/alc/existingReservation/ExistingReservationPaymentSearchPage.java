package apps.alc.existingReservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

/**
 * This class contains elements and element interactions for a given page of a
 * web application
 * 
 * @author Stagliano, A Dennis
 * @version 1/12/2016 Stagliano, A Dennis - original
 */
public class ExistingReservationPaymentSearchPage {
		// *******************
		// Page Class Fields
		// *******************
		// Declare a local WebDriver to be used by class method	
		private WebDriver driver;	
		private Datatable datatable = new Datatable();
		
		// *********************
		// Page Class Elements
		// *********************
		@FindBy(id = "ReservationResultByGuestForm:srchByGstMod")
		private Button btnModify;
		
		@FindBy(id = "searchForReservationsByPaymentForm:findReservationByPaymentButton")
		private Button btnFindButton;
		
		// *********************
		// ** Build page area **
		// *********************
		
		/**
		  * @summary Constructor to initialize the page
		  * @author Stagliano A Dennis
		  * @version 1/28/2016 Stagliano A Dennis - original
		  * @param driver
		*/
		
		public ExistingReservationPaymentSearchPage(WebDriver driver) {
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
		public ExistingReservationPaymentSearchPage initialize() {
			return ElementFactory.initElements(driver, this.getClass());
		}
		
		/**
		 * @summary Methods to determine if a page is loaded
		 * @author Stagliano, Dennis
		 * @version 1/28/2016 Stagliano, Dennis - original
		 * @return Boolean, true if the page is loaded, false otherwise
		 */
		public boolean pageLoaded() {
			return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnFindButton);
		}
		
		public boolean pageLoaded(Element element) {
			return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
		}
		
		// ********************
		// Page Class Methods
		// ********************
		
		public void clickModifyButton(){
			initialize();
			btnModify.syncEnabled(driver);
			btnModify.highlight(driver);
			btnModify.click();
		}	
}

