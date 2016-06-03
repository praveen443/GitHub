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
import utils.Sleeper;
import utils.TestReporter;

/**
 * This class contains elements and element interactions for a given page of a
 * web application
 * 
 * @author Waightstill W Avery
 * @version 12/15/2015 Waightstill W Avery - original
 */
public class ExistingReservationConfirmPage {
	// *******************
	// Page Class Fields
	// *******************
	// Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	// *********************
	// Page Class Elements
	// *********************
	@FindBy(id = "ModifyResSummaryForm:ModifyReservation_Accept")
	private Button btnAccept;

	// Element Confirm New booking panel
	@FindBy(id = "reBookModalPanelShadowDiv")
	private Element eleConfirmNewbooking;

	// Element Confirm Modify panel
	@FindBy(id = "paymentTransferModalPanelContentDiv")
	private Element eleConfirmModify;

	// button Yes in Confirm modify pop-up
	@FindBy(id = "paymentTransferConfirm:ModifyReservation_Yes")
	private Button btnConfirmYES;

	// Button Yes in Confirm New Booking pop-up
	@FindBy(id = "reBookConfirm:ModifyReservation_Yes1")
	private Button btnConfirmYes;
	
	//New Booking Details
	@FindBy(id = "ModifyResSummaryForm:modifyResSummaryNewPanel_body")
	private WebElement tblNewOfferBody;
	
	@FindBy(id = "ModifyResSummaryForm:modifyResSummaryNewPanel_header")
	private WebElement newOfferDetails;
	
	//Original Booking Details
	
	@FindBy(id = "ModifyResSummaryForm:modifyResSummaryOrigPanel_header")
	private Label lblOriginalBookingHeader;
	
	@FindBy(id = "ModifyResSummaryForm:oldproductList:0:productFacilityId")
	private Label lblOriginalProductFacility;
	
	@FindBy(id = "ModifyResSummaryForm:oldproductList:0:productId")
	private Label lblOriginalProductID;
	
	@FindBy(id = "ModifyResSummaryForm:oldproductList:0:productTime")
	private Label lblOriginalProductTime;
	
	@FindBy(id = "ModifyResSummaryForm:oldNoOfAdults")
	private Label lblOriginalNumAdults;
	
	@FindBy(id = "ModifyResSummaryForm:oldNoOfNonAdults")
	private Label lblOriginalNumChildren;

	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
	 * @param driver
	 */
	public ExistingReservationConfirmPage(WebDriver driver) {
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
	public ExistingReservationConfirmPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnAccept);
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
	public void acceptAndConfirmNewOffer() {
		acceptModifiedOffer();
		confirmNewBooking();
	}

	private void acceptModifiedOffer() {
		pageLoaded(btnAccept);
		btnAccept.jsClick(driver);
	}

	private void confirmNewBooking() {
		initialize();
		pageLoaded(btnConfirmYes);
		Sleeper.sleep(1000);
		btnConfirmYes.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void confirmModify() {
		pageLoaded(btnConfirmYES);
		btnConfirmYES.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	/**
	 * @Summary: Method to accept if confirm new booking pop-up is visible, else
	 *           to accept confirm Modidy in confirm page.
	 * @author: Praveen Namburi, @Version : Created 1-18-2016.
	 */
	public void acceptAndConfirmNewOrModifiedBooking() {
		pageLoaded();
		acceptModifiedOffer();

		if (eleConfirmNewbooking.syncVisible(driver, 5, false)) {
			TestReporter.logStep("Confirm New booking pop-up is displayed.");
			confirmNewBooking();

		} else if (eleConfirmModify.syncVisible(driver)) {
			TestReporter.logStep("Confirm Modify pop-up is displayed.");
			confirmModify();
		}

	}
	/**
	 * @Summary: Method to report details on a new reservation or booking      
	 * @author: Stagliano, Dennis 
	 * @Version : Created 2-2-2016.
	 */
	public void reportNewBookingDetails(){
		initialize();
		String Header = newOfferDetails.getText();
	
		List<WebElement> trCollection = tblNewOfferBody.findElements(By.tagName("tr"));
		StringBuffer sb = new StringBuffer(" ");
		for (WebElement trow : trCollection) {
			List<WebElement> tdcoll = trow.findElements(By.tagName("td"));
			for (WebElement tdElement : tdcoll) {			
				sb.append(System.getProperty("line.separator"));		
				sb.append(tdElement.getText() + " ");
			}
		}
		TestReporter.logStep(Header);
		TestReporter.logStep(sb.toString());
	}
	/**
	 * @Summary: Method to report details on a previous reservation or booking      
	 * @author: Stagliano, Dennis 
	 * @Version : Created 2-2-2016.
	 */
	public void reportOriginalBookingDetails(){
		initialize();
		String originalHeader = lblOriginalBookingHeader.getText();
		String originalProductFacility = lblOriginalProductFacility.getText();
		String originalProductID = lblOriginalProductID.getText();
		String originalProductTime = lblOriginalProductTime.getText();
		String originalAdults = lblOriginalNumAdults.getText();
		String originalChildren = lblOriginalNumChildren.getText();
		TestReporter.logStep("\n" + originalHeader + "\n" + originalProductFacility +
		         "\n" + originalProductID + "\n" + originalProductTime + "\n" + originalAdults
		         + "\n" + originalChildren);
	   }
}

