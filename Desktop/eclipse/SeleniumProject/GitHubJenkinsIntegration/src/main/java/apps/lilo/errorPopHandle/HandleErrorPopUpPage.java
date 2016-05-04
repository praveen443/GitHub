package apps.lilo.errorPopHandle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.TestReporter;

/**
 * This class contains elements and element interactions for the Bell Service
 * page in Lilo UI
 * 
 * @author Venkatesh Athmakuri
 * @version 11/25/2015 Venkatesh Athmakuri - original
 */

public class HandleErrorPopUpPage {
	// *******************
	// Page Class Fields
	// *******************
	// Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	// *********************
	// Page Class Elements
	// *********************

	// Error PopUP Div
	@FindBy(id = "PMSRErrorModalPanelContentDiv")
	private Element eleErrorMsgPopUP;

	// page object to get text from error pop-up message
	@FindBy(xpath = "//table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td/table/tbody/tr/td[2]/div/ul/li[1]")
	private Element eleGetErrorInfo;

	// page object for OK button in error pop-up msg.
	@FindBy(id = "errorForm:okButtonId")
	private Button btnErrorOK;

	// PaidOut Error PopUP Div
	@FindBy(id = "pmtErrorModalPanelShadowDiv")
	private Element elePaymentErrorMsgPopUp;

	// page object for OK button in error pop-up msg.
	@FindBy(id = "errorForm:errAftrInitOkBtn")
	private Button btnPaymentErrorOK;

	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Venkatesh Athmakuri
	 * @version 11/25/2015 Venkatesh Athmakuri - original
	 * @param driver
	 */
	public HandleErrorPopUpPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Venkatesh Athmakuri
	 * @version 11/25/2015 Venkatesh Athmakuri - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public HandleErrorPopUpPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Venkatesh Athmakuri
	 * @version 11/25/2015 Venkatesh Athmakuri - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, eleErrorMsgPopUP);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Venkatesh Athmakuri
	 * @version 11/25/2015 Venkatesh Athmakuri - original
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
	 * 
	 * @summary Method to validate ErrorPopUp Message
	 * @version Created 11/25/2014
	 * @author Venkatesh A
	 * @param Scenario
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void validatingErrorMessage(String scenario) {

		datatable.setVirtualtablePage("GetErrorMessages");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded(eleErrorMsgPopUP);
		eleGetErrorInfo.highlight(driver);
		System.out.println("Error Message : " + eleGetErrorInfo.getText());

		// Validating the error pop-up message here
		TestReporter.assertEquals(eleGetErrorInfo.getText(), datatable.getDataParameter("ErrorMessageInfo"),
				"Error Message Not Displayed");

		// Close the Error pop-up message by clicking on OK button.
		pageLoaded(btnErrorOK);
		btnErrorOK.highlight(driver);
		btnErrorOK.jsClick(driver);

	}

	/**
	 * 
	 * @summary Method to validate Payment ErrorPopUp Message
	 * @version Created 11/26/2014
	 * @author Venkatesh A
	 * @param Scenario
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void validatinginvalidPaidOutPaymentErrorMessage(String scenario) {
		datatable.setVirtualtablePage("GetErrorMessages");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded(elePaymentErrorMsgPopUp);
		eleGetErrorInfo.highlight(driver);
		System.out.println("Error Message : " + eleGetErrorInfo.getText());

		// Validating the error pop-up message here
		TestReporter.assertTrue(eleGetErrorInfo.getText().contains(datatable.getDataParameter("ErrorMessageInfo")),
				"Error Message Not Displayed");

		// Close the Error pop-up message by clicking on OK button.
		btnPaymentErrorOK.highlight(driver);
		btnPaymentErrorOK.jsClick(driver);
	}
}

