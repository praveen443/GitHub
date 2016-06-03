package apps.lilo.roomTab;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.WebDriverSetup;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Label;
import core.interfaces.Link;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

/**
* @summary Contains the elements and methods to interact with the LILO UI Checkout page
* @version Created 11/03/2014
* @author Waightstill W. Avery
*/
public class EarlyCheckoutPage {
	//***************************************
	//*** Main Early Checkout Page Fields ***
	//***************************************
	int timeout = WebDriverSetup.getDefaultTestTimeout();
	
	//*****************************************
	//*** Main Early Checkout Page Elements ***
	//*****************************************
	
	//Checkout link
	@FindBy(id = "earlyCheckoutForm:messageButtonId1")
	private Link lnkCheckout;
	
	//Cancel link
	@FindBy(id = "earlyCheckoutForm:messageButtonId2")
	private Link lnkCancel;
	
	//Early Checkout Reason listbox
	@FindBy(id = "earlyCheckoutForm:reasons")
	private Listbox lstEarlyCheckoutReason;
	
	//Early Checkout Freeform Reason
	@FindBy(id = "earlyCheckoutForm:specifyReasonValTxtId")
	private Textbox txtFreeformEarlyCheckoutReason;
	
	
	//Bell Service Yes button
	@FindBy(id = "bellserviceConfirmPopupForm:yes")
	private Button btnBellServiceYes;
	
	//Bell Service No button
	@FindBy(id = "bellserviceConfirmPopupForm:no")
	private Button btnBellServiceNo;
	
	//Checkout Confirmation message
	@FindBy(id = "checkoutConfirmForm:approvedReasonId")
	private Label lblCheckoutConfirmation;
	
	//Checkout COnfirmation Close button
	@FindBy(id = "checkoutConfirmForm:messageButtonId2")
	private Button btnConfirmationMessageClose;
	
	//*********************
    //** Build page area **
    //*********************
	private WebDriver driver;
	private Datatable datatable = new Datatable();

    /**
    * 
    * @summary Constructor to initialize the page
    * @version Created 11/03/2014
    * @author Waightstill W Avery
    * @param  driver
    * @throws NA
    * @return NA
    * 
    */
	public EarlyCheckoutPage(WebDriver driver){
		this.driver = driver;	
		ElementFactory.initElements(driver, this);  
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, lnkCheckout);
	}
	
	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}
	
	public EarlyCheckoutPage initialize() {
		return ElementFactory.initElements(driver,
				this.getClass());
	}
		     
    //****************************************
  	//*** Main Early Checkout Interactions ***
  	//****************************************
	
	/**
	 * 
	 * @summary Method to select an early checkout reason
	 * @version Created 11/03/2014
	 * @author Waightstill W Avery
	 * @param checkoutReason - early checkout reason to select from the list
	 * @throws NA
	 * @return NA
	 * 
	 */
	private void selectEarlyCheckoutReason(String checkoutReason){
		lstEarlyCheckoutReason.select(checkoutReason);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	/**
	 * 
	 * @summary Method to enter a freeform early checkout reason
	 * @version Created 11/03/2014
	 * @author Waightstill W Avery
	 * @param checkoutReason - early checkout to enter into the textbox
	 * @throws NA
	 * @return NA
	 * 
	 */
	private void eneterFreeformEarlyCheckoutReason(String checkoutReason){
		txtFreeformEarlyCheckoutReason.set(checkoutReason);
	}
	
	private void clickCheckout(){
		lnkCheckout.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	/**
	 * 
	 * @summary Method to process the bell service popup
	 * @version Created 11/03/2014
	 * @author Waightstill W Avery
	 * @param bellService - determines if bell service is required
	 * @throws NA
	 * @return NA
	 * 
	 */
	private void processBellService(boolean bellService){
		if(btnBellServiceYes.syncVisible(driver, 1, false)){
			if(bellService){
				btnBellServiceYes.jsClick(driver);
			}else{
				btnBellServiceNo.jsClick(driver);
			}
			new ProcessingYourRequest().WaitForProcessRequest(driver);	
		}
	}
	
	/**
	 * 
	 * @summary Method to confirm the successful checkout window appeared
	 * @version Created 11/03/2014
	 * @author Waightstill W Avery
	 * @param NA
	 * @throws NA
	 * @return NA
	 * 
	 */
	private void confirmSuccessfulCheckout(){
		Assert.assertEquals(lblCheckoutConfirmation.syncVisible(driver, 2, false), true);
	}
	
	private void closeConfirmationMessageWindow(){
		btnConfirmationMessageClose.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
}

