package apps.lilo.billingOptions;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import apps.lilo.quickBook.QuickBookPage;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
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
 * @version 11/18/2015 Waightstill W Avery - original
 */
public class BillingOptionsPage {
	// *******************
	// Page Class Fields
	// *******************
	// Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private boolean terminalValue;
	private String TerminalID;
	private String ExpectedAccountLimit = "100";
	private boolean isExpressCheckoutEnabled;
	private Datatable datatable = new Datatable();

	// *********************
	// Page Class Elements
	// *********************
	@FindBy(id = "resPartyChargeAllocFrm:chargeAllocationId")
	private Textbox btnHistory;

	@FindBy(id = "groupRSRFolioListFrm:rsrAndGrp:tb")
	private Webtable tblGroupRsrFolio;

	@FindBy(id = "resPartyChargeAllocFrm:chargeAlloctnInfo")
	private Button btnViewModifyChargeAllocation;

	@FindBy(id = "addRespPartyModalPanelCDiv:")
	private Element eleModPayGuestDiv;

	@FindBy(id = "resPartyChargeAllocFrm:editRespParty")
	private Element eleModPayVieOrModify;

	// ***************************************
	// *** Charge allocation form elements ***
	// ***************************************
	@FindBy(id = "chargeAllocationForm:saveChargeAllocationId")
	private Button btnsaveChargeAllocation;

	@FindBy(id = "chargeAllocationForm:close")
	private Button btnCloseChargeAllocation;

	@FindBy(id = "chargeAllocationForm:accomodtnChargeAllocDtlPanel:0:allocationCheckBoxId")
	private Checkbox chkFirstRoomInChargeAllocationform;

	@FindBy(id = "errorForm:okButtonId")
	private Button btnOk;

	@FindBy(id = "chargeAllocationForm:close")
	private Element eleErrorPopUp;

	@FindBy(xpath = "//table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td/table/tbody/tr/td[2]/div/ul/li")
	private Element eleGetErrorText;

	// Capturing Terminal ID
	@FindBy(id = "resPartyChargeAllocFrm:settlementMethodTable:tb")
	private static Element textTerminalID;

	// Add button for room charge allocation element
	@FindBy(id = "chargeAllocationForm:nonRoomChgeAlloctnTable:0:guestAllocationCheckBoxId")
	private Checkbox chkGuestAllocation;

	// Add button for room charge allocation element
	@FindBy(id = "chargeAllocationForm:addNonRoomChargeAllocationId")
	private Button btnAdd;

	// Refilter pop up
	@FindBy(id = "refilterModalPanelCDiv")
	private Element eleRefilterPopUp;

	// Refilter pop up No
	@FindBy(id = "refilterForm:noId")
	private Button btnRefilterNo;
	
	//Grab Guest Name from non room chargge allocation section
	@FindBy(id = "resPartyChargeAllocFrm:nonroomChargeDetailTable:0:guestFullName")
	private Element eleguestFullName;
	
	//Grab Spend limit from non room chargge allocation section
	@FindBy(id = "resPartyChargeAllocFrm:nonroomChargeDetailTable:0:accountLimitId1")
	private Element eleaccountLimit;

	/**
	 * 
	 * @summary Adding elements for payment and Settlement dialog screens
	 * @version Created 11/30/2014
	 * @author Dennis Stagliano
	 */
	// ***************************************
	// *** Settlement Method form elements ***
	// ***************************************

	// modify/Add payment button
	@FindBy(xpath = ".//*[@id='addRespPartyForm:j_id2463']")
	private Button btnAddModifyPayment;

	// drop down selection for payment Type
	@FindBy(id = "settlementPopup:pmtTypeId")
	private Listbox lstPaymentType;

	// Payment Method
	@FindBy(id = "settlementPopup:pmtMethTextId")
	private Listbox lstMethodOfPayment;

	// Credit card number
	@FindBy(id = "settlementPopup:creditCardTextId")
	private Textbox txtCreditCardNumber;

	// Credit card expire date
	@FindBy(id = "settlementPopup:creditCardExpDateTextId")
	private Textbox txtCardExpireDate;

	// Credit card security number
	@FindBy(id = "settlementPopup:cvvNumberTextId")
	private Textbox txtCardSecurityCode;

	// Card holder name
	@FindBy(id = "settlementPopup:cardHolderNameText")
	private Textbox txtCardHolderName;

	// Express Checkout checkbox
	@FindBy(id = "settlementPopup:expCheckOutId")
	private Checkbox chkExpressCheckoutFlag;

	// Yes radio button
	@FindBy(id = "settlementPopup:ccIncidentalId:0")
	private Element eleradYes;

	// No radio button
	@FindBy(id = "settlementPopup:ccIncidentalId:1")
	private Element eleradNo;

	// Submit button
	@FindBy(id = "settlementPopup:submitButtonId")
	private Button btnSubmitButton;

	// Touch button
	@FindBy(xpath = ".//*[@id='settlementPopup']/table[2]/tbody/tr[9]/td/table/tbody/tr/td[2]/a")
	private Button btnTouchButton;

	// Charge account button
	@FindBy(xpath = ".//*[@id='settlementPopup']/table[2]/tbody/tr[9]/td/table/tbody/tr/td[4]/a")
	private Button btnChargeAccountButton;

	// Cancel button
	@FindBy(xpath = ".//*[@id='settlementPopup:j_id177']/a[2]")
	private Button btnCancelButton;

	@FindBy(id = "addRespPartyForm:settlementMethodTableRP:tb")
	private Element eleCaptureSettlementMethodDatatable;

	// Spend Limit Text
	@FindBy(id = "chargeAllocationForm:nonRoomChgeAlloctnTable:0:nonRoomSpendLimit")
	private Textbox txtNonRoomSpendLimit;

	// *********************
	// ** Build page area **;
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Waightstill W Avery
	 * @version 11/18/2015 Waightstill W Avery - original
	 * @param driver
	 */
	public BillingOptionsPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Waightstill W Avery
	 * @version 11/18/2015 Waightstill W Avery - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public BillingOptionsPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 11/18/2015 Waightstill W Avery - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnHistory);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 11/18/2015 Waightstill W Avery - original
	 * @param element
	 *            - Element to be used to determine if the page is loaded
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	/**
	 * @summary : Method to determine if charge allocations page is loaded
	 * @author : Praveen Namburi
	 * @version : 11/20/2015
	 * @return : Boolean, true if the page is loaded, false otherwise
	 */
	public boolean chargeAllocationsPageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnsaveChargeAllocation);
	}

	// ********************
	// Page Class Methods
	// ********************

	public void verifyGroupDelegateBillingProfile(String scenario) {
		datatable.setVirtualtablePage("BillingOptions");
		datatable.setVirtualtableScenario(scenario);

		String groupDelegateBillingProfile = datatable.getDataParameter("GroupDelegateBillingProfile");
		String groupRsrFolioContents = tblGroupRsrFolio.findElement(By.xpath("tr/td")).getText();
		boolean profileFound = false;

		TestReporter.log("Validating Group Delegate Billing Profile: " + groupDelegateBillingProfile);
		TestReporter.log("Table contents: " + groupRsrFolioContents);

		profileFound = groupRsrFolioContents.contains(groupDelegateBillingProfile);
		TestReporter.assertTrue(profileFound, "The Group Delegate Billing Profile [" + groupDelegateBillingProfile
				+ "] was not found in [" + groupRsrFolioContents + "].");
	}

	/**
	 * 
	 * @summary Method to Validate Terminal ID
	 * @version Created 11/18/2014
	 * @author Venkatesh A
	 * @param NA
	 * @throws NA
	 * @return NA
	 * 
	 */

	public boolean Verify_terminalID() {

		List<WebElement> tds = textTerminalID.findElements(By.tagName("td"));
		System.out.println("Terminal Table total rows : " + tds.size());
		System.out.println("Terminal Id : " + tds.get(2).getText());

		// Terminal id
		pageLoaded(textTerminalID);
		terminalValue = false;
		TerminalID = tds.get(2).getText();
		System.out.println("Terminal ID:" + TerminalID);

		if (!TerminalID.equalsIgnoreCase("0001")) {
			return terminalValue = true;
		}
		return terminalValue;
	}

	/**
	 * @Summary : Method to click on button - View/modify for the Charge
	 *          Allocation in Billing options page.
	 * @author : Parveen Namburi.
	 * @version : Created 11/20/2015
	 * @return : NA
	 * 
	 */
	public void clickbtnViewModifyForChargeAllocation() {

		initialize();
		pageLoaded(btnViewModifyChargeAllocation);
		btnViewModifyChargeAllocation.syncVisible(driver);
		btnViewModifyChargeAllocation.highlight(driver);

		btnViewModifyChargeAllocation.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * @Summary : Method to click on button Save in Charge Allocation form.
	 * @author : Parveen Namburi.
	 * @version : Created 11/20/2015
	 * @return : NA
	 * 
	 */
	public void clickbtnSaveChargeAllocation() {

		initialize();
		pageLoaded(btnsaveChargeAllocation);
		btnsaveChargeAllocation.syncVisible(driver);
		btnsaveChargeAllocation.highlight(driver);

		btnsaveChargeAllocation.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * @Summary : Method to click on button Close in Charge Allocation form.
	 * @author : Parveen Namburi.
	 * @version : Created 11/20/2015
	 * @return : NA
	 * 
	 */
	public void clickbtnCloseChargeAllocation() {

		initialize();
		pageLoaded(btnCloseChargeAllocation);
		btnCloseChargeAllocation.syncVisible(driver);
		btnCloseChargeAllocation.highlight(driver);

		btnCloseChargeAllocation.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * @Summary : Method to get the error text message from Pop-Up and validate
	 *          it.
	 * @author : Parveen Namburi.
	 * @version : Created 11/20/2015
	 * @return : NA
	 * 
	 */
	public void verifyChargeAllocationErrorMessage() {

		initialize();
		pageLoaded(eleErrorPopUp);
		eleErrorPopUp.syncVisible(driver);

		TestReporter.assertTrue(eleErrorPopUp.isDisplayed(), "The error pop-up message is not loaded.");
		eleErrorPopUp.highlight(driver);

		// get the text from pop-up and validate it.
		pageLoaded(eleGetErrorText);
		String getErrorMessage = eleGetErrorText.getText();
		TestReporter.logStep("----> Error message is : " + getErrorMessage);

		Sleeper.sleep(1000);

		// Validating the error pop-up message here
		TestReporter.assertTrue(
				getErrorMessage
						.contains("Room and Tax charge allocations cannot be modified for Primary Responsible Party"),
				"The expected string doesn't matches with the actual string.");

		// Close the Error message by clicking on OK button.
		pageLoaded(btnOk);
		btnOk.highlight(driver);
		btnOk.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * @Summary : Method to check the check-box for Primary room and click on
	 *          Save button in charge allocations form.
	 * @author : Parveen Namburi.
	 * @version : Created 11/20/2015
	 * @return : NA
	 * 
	 */
	public void selectPrimaryRoomAndSaveIt() {

		initialize();
		pageLoaded(chkFirstRoomInChargeAllocationform);
		chkFirstRoomInChargeAllocationform.syncVisible(driver);
		chkFirstRoomInChargeAllocationform.jsToggle(driver);

		new ProcessingYourRequest().WaitForProcessRequest(driver);

		// Click on Save button in charge allocations form
		clickbtnSaveChargeAllocation();
	}

	/**
	 * 
	 * @summary Method to clik on View/Modify Button
	 * @version Created 11/23/2014
	 * @author Venkatesh A
	 * @param NA
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void clickViewOrModifyButton() {
		eleModPayVieOrModify.focus(driver);
		eleModPayVieOrModify.click();
	}

	/**
	 * @summary Method to click on Add/Modify Button
	 * @version Created 11/30/2014
	 * @author Dennis Stagliano
	 * @param NA
	 * @throws NA
	 * @return NA
	 */
	public void clickAddModifyPayment() {
		// initialize();
		btnAddModifyPayment.focus(driver);
		Sleeper.sleep(1000);
		btnAddModifyPayment.click();
	}

	/**
	 * @summary Method to verify that the Express Checkout Check box has been
	 *          activated
	 * @author Dennis Stagliano
	 * @version 12/01/2015 Dennis Stagliano - original
	 * @param NA
	 * @return NA
	 */
	public boolean verifyExpressCheckoutCheckbox() {
		switchWindowFocus();
		boolean checked = driver.findElement(By.id("addRespPartyForm:isExpChkbox")).isSelected();
		if (checked == true) {
			System.out.println("The ExpressCheckout radio button has been enabled = " + checked);
		}else{
			System.out.println("The ExpressCheckout radio button has not been enabled = " + checked);
		}
		return isExpressCheckoutEnabled;
	}

	/**
	 * @summary Method to click the submit button on payment screen
	 * @author Dennis Stagliano
	 * @version 12/01/2015 Dennis Stagliano - original
	 * @param NA
	 * @return NA
	 */
	public void settlementModifyPaymentSubmitButton() {
		// click Submit
		driver.findElement(By.xpath(".//*[@id='settlementPopup:submitButtonId']")).click();
	}

	/**
	 * @summary Method to click the verification OK button on payment screen
	 * @author Dennis Stagliano
	 * @version 12/01/2015 Dennis Stagliano - original
	 * @param NA
	 * @return NA
	 */
	public void settlementModifyPaymentOKButton() {
		// click OK
		driver.findElement(
				By.xpath(".//*[@id='rrnForm']/table/tbody/tr[1]/td[1]/table/tbody/tr[2]/td/div/table/tbody/tr/td/a"))
				.click();
	}

	/**
	 * @summary Method to click the close button on payment screen
	 * @author Dennis Stagliano
	 * @version 12/01/2015 Dennis Stagliano - original
	 * @param NA
	 * @return NA
	 */
	public void settlementModifyPaymentCloseButton() {
		// click close
		switchWindowFocus();
		driver.findElement(By.xpath(".//*[@id='addRespPartyForm:closeRespPartyHiddenButton']")).click();
	}

	/**
	 * @summary Method to capture and write out the settlement payment data
	 * @author Dennis Stagliano
	 * @version 12/01/2015 Dennis Stagliano - original
	 * @param NA
	 * @return NA
	 */
	public void captureSettlementMethodData() {
		// Get the table rows in a collection
		List<WebElement> trcollection = eleCaptureSettlementMethodDatatable.findElements(By.tagName("tr"));
		for (WebElement trElement : trcollection) {
			List<WebElement> tdcollection = trElement.findElements(By.tagName("td"));
			for (WebElement tdElement : tdcollection) {
				System.out.println("cell value = " + tdElement.getText());
			}
		}
	}

	/**
	 * @summary Method to switch focus from main window to whatever second
	 *          screen is activated for payment settlement changes
	 * @author Dennis Stagliano
	 * @version 12/01/2015 Dennis Stagliano - original
	 * @param NA
	 * @return NA
	 */
	public void switchWindowFocus() {
		String mainWindow = driver.getWindowHandle();
		// System.out.println(mainWindow);
		for (String winHandle : driver.getWindowHandles()) {
			if (!mainWindow.equals(winHandle)) {
				driver.switchTo().window(winHandle);
			}
		}
	}

	/**
	 * @summary Method to select and enter non room details
	 * @author Marella Satish
	 * @version 11/30/2015 Marella Satish - original
	 * @param NA
	 * @return NA
	 * 
	 */
	public void enterNonRoomChargeDetails() {
		selectNonRoomChargeAllocation();
		initialize();
		pageLoaded(btnsaveChargeAllocation);
		txtNonRoomSpendLimit.safeSet("100");
		completeChargeAllocationUpdates();
	}

	/**
	 * @summary Method to select the charge allocation record
	 * @author Marella Satish
	 * @version 11/30/2015 Marella Satish - original
	 * @param NA
	 * @return NA
	 * 
	 */

	public void selectNonRoomChargeAllocation() {
		clickAdd();
		initialize();
		pageLoaded(btnsaveChargeAllocation);
		chkGuestAllocation.jsToggle(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * @summary Method to handle any pop up like filter
	 * @author Marella Satish
	 * @version 11/30/2015 Marella Satish - original
	 * @param NA
	 * @return NA
	 */
	public boolean completeChargeAllocationUpdates() {

		clickbtnSaveChargeAllocation();

		// Click ok at the refilter pop up if it displays
		if (isRefilterPopUpDisplayed()) {
			clickRefilterNo();
		}

		/*
		 * Add any other errors/warnings pop up if exists any
		 * 
		 * 
		 */

		return true;
	}

	public void clickAdd() {

		pageLoaded(btnsaveChargeAllocation);
		btnAdd.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public boolean isRefilterPopUpDisplayed() {
		return eleRefilterPopUp.isDisplayed();
	}

	public void clickRefilterNo() {
		btnRefilterNo.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * @summary Method to validate the Non Room Charge Allocations in folio
	 *          activity tab page
	 * @author Marella Satish
	 * @version 12/01/2015 Marella Satish - original
	 * @param Data
	 *            table scenario
	 * @return NA
	 */
	public void validateNonRoomChargeAllocations(String scenario) {

		QuickBookPage quickBookPage = new QuickBookPage(driver);
		String ExpectedGuestName = quickBookPage.getGuestFullName(scenario);
		String ActualGuestName = eleguestFullName.getText();
		TestReporter.logStep("ActualGuestName " + ActualGuestName);
		TestReporter.logStep("ExpectedGuestName " + ExpectedGuestName);
		boolean GuestStatus = eleguestFullName.getText().equalsIgnoreCase(ExpectedGuestName);
		TestReporter.assertTrue(GuestStatus,
				"Actual String[" + ActualGuestName + "] does not equals expected string[" + ExpectedGuestName + "]");

		String AccountLimit = eleaccountLimit.getText().replace("$", "").trim();
		String[] ActualAccountLimit = StringUtils.split(AccountLimit, ".");
		TestReporter.logStep("ActualAccountLimit " + ActualAccountLimit[0]);
		TestReporter.logStep("ExpectedAccountLimit " + ExpectedAccountLimit);
		boolean AccountLimitStatus = ActualAccountLimit[0].equals(ExpectedAccountLimit);
		TestReporter.assertTrue(AccountLimitStatus, "Actual String[" + ActualAccountLimit[0]
				+ "] does not equals expected string[" + ExpectedAccountLimit + "]");

	}

}// end class


