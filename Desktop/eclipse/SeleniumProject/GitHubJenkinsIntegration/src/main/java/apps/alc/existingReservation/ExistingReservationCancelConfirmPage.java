package apps.alc.existingReservation;

//package com.disney.composite.apps.alc.existingReservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.TestReporter;

/**
 * This class contains elements and element interactions for a given page of a
 * web application
 * 
 * @author Venkatesh Atmakuri
 * @version 1/8/2016 Venkatesh Atmakuri - original
 */
public class ExistingReservationCancelConfirmPage {
	// *******************
	// Page Class Fields
	// *******************
	// Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	// *********************
	// Page Class Elements
	// *********************
	@FindBy(id = "cancellationConfirmationForm:cancelConfirmContinue")
	private Button btnContinue;
	
	@FindBy(xpath = "//*[@id='cancellationConfirmationForm:cancellationConfirmationPanel_body']/center/table/tbody/tr[1]")
	private Element eleCancelledReservationNumber;
	
	@FindBy(xpath = "//*[@id='cancellationConfirmationForm:cancellationConfirmationPanel_body']/center/table/tbody/tr[2]")
	private Element eleContactPerson;
	
	@FindBy(xpath = "//*[@id='cancellationConfirmationForm:cancellationConfirmationPanel_body']/center/table/tbody/tr[7]")
	private Element eleCancellationDateTime;
	
	@FindBy(xpath = "//*[@id='cancellationConfirmationForm:cancellationConfirmationPanel_body']/center/table/tbody/tr[8]")
	private Element eleCancellationReason;
	
	@FindBy(xpath = "//*[@id='cancellationConfirmationForm:cancellationConfirmationPanel_body']/center/table/tbody/tr[9]")
	private Element eleCancellationNumber;
	
	@FindBy(xpath = "//*[@id='cancellationConfirmationForm:cancellationConfirmationPanel_body']/center/table/tbody/tr[10]")
	private Element eleCancellationFeeWaived;

	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Venkatesh Atmakuri
	 * @version 1/8/2016 Venkatesh Atmakuri - original
	 * @param driver
	 */
	public ExistingReservationCancelConfirmPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Venkatesh Atmakuri
	 * @version 1/08/2016 Venkatesh Atmakuri - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public ExistingReservationCancelConfirmPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Venkatesh Atmakuri
	 * @version 1/8/2016 Venkatesh Atmakuri - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnContinue);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Venkatesh Atmakuri
	 * @version 1/8/2016 Venkatesh Atmakuri - original
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
	public void clickContinue(){
		pageLoaded(btnContinue);
		btnContinue.highlight(driver);
		btnContinue.click();
	}
	
	/**
	 * @summary Method to get Cancel Confirmation Details
	 * @author Venkatesh Atmakuri
	 * @version 1/8/2016 Venkatesh Atmakuri - original
	 * @param Na
	 * @return NA
	 */
	public void getCancelConformationDetails(){
		TestReporter.logStep(eleCancelledReservationNumber.getText());
		TestReporter.logStep(eleContactPerson.getText());
		TestReporter.logStep(eleCancellationDateTime.getText());
		TestReporter.logStep(eleCancellationReason.getText());
		TestReporter.logStep(eleCancellationNumber.getText());
		TestReporter.logStep(eleCancellationFeeWaived.getText());
	}
}