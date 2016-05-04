package apps.SettlementUI;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import apps.paymentUI.PaymentUIWindow;
import core.WebDriverSetup;
import core.WindowHandler;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Label;
import core.interfaces.Link;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.GenerateCard;
import utils.Sleeper;
import utils.TestReporter;

/**
 * 
 * @summary Contains the page methods & objects for the search guest page during
 *          the new reservation process
 * @version Created 10/02/2014
 * @author Waightstill W Avery
 */

public class SettlementUIWindow {

	// **********************************
	// *** Main PaymentUI Page Fields ***
	// **********************************
	private static String folioType = "";
	private static String responsibleParty = "";
	private static String rrnNumber;
	private static WebDriver driver;
	private String parentWindowHandle;
	String Xpath = ".//*[@id='pinSetUpConfirmForm:pinSetUpConfirmModalPanelContentTable']/tbody/tr[2]/td/table/tbody/tr[1]/td[1]/table/tbody/tr[2]/td/div/table/tbody/tr/td/input";

	// ************************************
	// *** Main PaymentUI Page Elements ***
	// ************************************

	// ------------------
	// Create Text box Object for entering First Name of Quick book Page
	@FindBy(id = "quickBookForm:firstNameId")
	private Textbox txFirstName;

	// Create Text box Object for entering First Name of Quick book Page
	@FindBy(id = "quickBookForm:lastNameId")
	private Textbox txtLastName;

	// Create Text box Object for entering First Name of Quick book Page
	@FindBy(id = "quickBookForm:quickBookButtonId")
	private Button btnQuickBook;

	// Create Text box Object for entering First Name of Quick book Page
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:0:accomodationCheckBox")
	private Element chkNextToReservation;

	// Create Text box Object for entering First Name of Quick book Page
	@FindBy(id = "roomTabFrm:proToChkInButtonId")
	private Button btnProceedToCheckIn;

	// Create Text box Object for entering First Name of Quick book Page
	@FindBy(xpath = ".//*[@id='viewGuestForm:guestList:0:j_id14321']/input")
	private Button chkNextToPrimaryGuest;

	// Create Text box Object for entering First Name of Quick book Page
	@FindBy(id = "viewGuestForm:ZipID")
	private Textbox txtZip;

	// Create Text box Object for entering First Name of Quick book Page
	@FindBy(id = "viewGuestForm:address1Id")
	private Textbox txtAddLine;

	// Create Text box Object for entering First Name of Quick book Page
	@FindBy(id = "viewGuestForm:phoneType1")
	private Element selectItemPhoneType;

	// Create Text box Object for entering First Name of Quick book Page
	@FindBy(xpath = ".//*[@id='viewGuestForm:phoneType1']/option[4]")
	private Element selectItemMobile;

	// Create Text box Object for entering First Name of Quick book Page
	@FindBy(id = "viewGuestForm:phoneNumber1")
	private Textbox txtPhoneNumber;

	// Create Text box Object for entering First Name of Quick book Page
	@FindBy(id = "viewGuestForm:emailTypeId")
	private Element selectItemEmailType;

	// Create Text box Object for entering First Name of Quick book Page
	@FindBy(xpath = ".//*[@id='viewGuestForm:emailTypeId']/option[2]")
	private Element selectItemEmail;

	// Create Text box Object for entering First Name of Quick book Page
	@FindBy(id = "viewGuestForm:emailAddress")
	private Textbox txtEmail;

	// Create page Object for Save Button
	@FindBy(id = "viewGuestForm:saveButton")
	private Button btnSave;

	// Create page Object for Proceed Button
	@FindBy(id = "viewGuestForm:proToChkInButtonId")
	private Button btnProceed;

	// Create page Object for - next Proceed Button
	@FindBy(id = "applyPymntForm:nextId")
	private Button btnNextProceed;

	// Create page Object for - Yes button exist on pop up
	@FindBy(id = "balanceDueCheckinForm:okButtonId1")
	private Button btnYes;

	// Create page Object for - Complete Check in
	@FindBy(id = "newMediaEncodeForm:completeCheckinId")
	private Button btnCompleteCheckin;

	// Create page Object for - Complete Check in
	@FindBy(id = "roomTabFrm:proToChkInButtonId")
	private Button btnCompleteCheckin_roomTab;

	// Create page Object for - Complete Check in
	@FindBy(id = "errorForm:okButtonId")
	private Button ErrorForm;

	// Create Page Object for Cancel Button
	@FindBy(id = "printEncodeForm:defaultPrintEncodCancelId")
	private Button btnCancelPrinterScreen;

	// Create Page Object for No Bell Service
	@FindBy(id = "bellserviceConfirmPopupForm:no")
	private Button btnNoBellService;

	// Create Page Object for Add Special request
	@FindBy(id = "specialRequestId:addspecialRequests")
	private Button btnAddSpecialRequest;

	// Create Page Object for Add Special request
	@FindBy(id = "specialRequestForm:requestTypeRadio:1")
	private Element radioDelivary;

	// Create Page Object for Add Special request
	@FindBy(xpath = ".//*[@id='specialRequestForm:serviceMenu']/select")
	private Element SelectItemService;

	// Create Page Object for Add Special request
	@FindBy(xpath = ".//*[@id='specialRequestForm:serviceMenu']/select/option[21]")
	private Element SelectCRIBItemService;

	// Create Page Object for FreeForm Comment text input
	@FindBy(id = "specialRequestForm:specialRequest")
	private Textbox txtFreeformComment;

	// Create Page Object for text confirmation message
	@FindBy(id = "saveSuccessfulForm:confirmationMsgId")
	private Element txtConformMsg;

	// Create Page object for button OK
	@FindBy(id = "saveSuccessfulForm:saveSuccessfulOkBtnId")
	private Element btnOK;

	// Create Page object for button OK
	@FindBy(id = "specialRequestId:flatDetailViewList:0:editspecialrequest")
	private Element lnkEdit;

	// Validation textPtesent
	// Create Page object for button OK
	@FindBy(xpath = ".//*[@id='specialRequestId:specialRequestPanel']/table/tbody/tr[1]/td[1]/b[1]")
	private Element txtPresent;

	// ------------

	@FindBy(id = "settlementPopup:submitButtonId")
	private static Button btnSubmit;

	@FindBy(id = "settlementPopup:folioTypeText")
	private static Label lblFolioType;

	@FindBy(id = "settlementPopup:respPartyIndividualNameText")
	private static Label lblResponsibleParty;

	@FindBy(id = "settlementPopup:pmtTypeId")
	private static Listbox lstPaymentType;

	@FindBy(id = "settlementPopup:pmtMethTextId")
	private static Listbox lstPaymentMethod;

	@FindBy(id = "settlementPopup:creditCardTextId")
	private static Textbox txtCreditCardNumber;

	@FindBy(id = "settlementPopup:creditCardExpDateTextId")
	private static Textbox txtExpirationDate;

	@FindBy(id = "settlementPopup:cvvNumberTextId")
	private static Textbox txtCVV;

	@FindBy(id = "settlementPopup:cardHolderNameText")
	private static Textbox txtCardHolderName;

	@FindBy(id = "settlementPopup:showPrimaryGuestAddressId")
	private static Checkbox chkPrimaryAddress;

	@FindBy(id = "settlementPopup:countryTextDD")
	private static Listbox lstCountry;

	@FindBy(id = "settlementPopup:addLine1Text")
	private static Textbox txtAddress1;

	@FindBy(id = "settlementPopup:addLine2Text")
	private static Textbox txtAddress2;

	@FindBy(id = "settlementPopup:zipText")
	private static Textbox txtPostalCode;

	@FindBy(id = "settlementPopup:cityText")
	private static Textbox txtCity;

	@FindBy(id = "settlementPopup:stateTextDD")
	private static Listbox lstState;

	@FindBy(id = "processingModalPanelContentTable")
	private static Element eleProcessingRequest;

	@FindBy(id = "rrnForm:rrnPanel")
	private static Label lblRRNNumber;

	@FindBy(xpath = "//*[@id=\"rrnForm\"]/table/tbody/tr[1]/td[1]/table/tbody/tr[2]/td/div/table/tbody/tr/td/a")
	private static Button btnOk;

	@FindBy(id = "headerTextError")
	private static Label lblPaymentPopupErrorHeader;

	@FindBy(xpath = "//*[@id=\"errorForm\"]/table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td/table/tbody/tr/td[2]/div/ul/li")
	private static Label lblErrorMessage;

	@FindBy(id = "settlementMethodSuccessModalPanelCDiv")
	private static Label lblPaymentPopupSuccessHeader;

	@FindBy(id = "errorForm:errAftrInitOkBtn")
	private static Button btnErrorOk;

	@FindBy(xpath = "//*[@id=\"settlementPopup:j_id177\"]/a[2]")
	private static Button btnErrorCancel;

	@FindBy(linkText = "ChargeAccount")
	private Link lnkChargeAccount;

	@FindBy(id = "applyPymntForm:settlementmethodsId")
	private static Button btnSettlementButton;

	@FindBy(xpath = "//input[contains(@id,'settlementMethodForm:j_id')]")
	private static Button btnAddModify;

	/*
	 * @FindBy(xpath =
	 * "//td[contains(@id,'settlementMethodForm:settlementMethodTableRP:0:j_id')]")
	 * private static Element textsettilmentmethodInfo;
	 */

	@FindBy(xpath = "//td[contains(@id,'settlementMethodForm:settlementMethodTableRP:0:j_id')]")
	private static Element textsettilmentmethodInfo;

	@FindBy(id = "settlementMethodForm:closeSettlemetMethodId")
	private static Element btnClose;

	// close
	@FindBy(id = "addRespPartyForm:closeRespPartyHiddenButton")
	private Button btnModifyPartyGuestClose;

	@FindBy(id = "settlementPopup:pmtTypeId")
	private Element elePaymentType;

	// settlementPopupBody
	@FindBy(id = "settlementPopupBody")
	private Element elePaymentWindowFocus;
	
	//Settlement Express Checkout Checkbox
	@FindBy (id = "settlementPopup:expCheckOutId")
	private Checkbox chkExpressCheckoutFlag;
	
	// Payment Method
	@FindBy(id = "settlementPopup:pmtMethTextId")
	private Listbox lstMethodOfPayment;
	
	// Yes Incidentals radio button
	@FindBy(id = "settlementPopup:ccIncidentalId:0")
	private Element eleradYes;
	
	// Button validate
	@FindBy(id = "settlementPopup:validateButtonId")
	private Button btnValidate;
	
	@FindBy(id = "settlementMethodSuccessModalPanelContentDiv")
	private static Label lblSettlementSuccessHeader;
		
	private Datatable datatable = new Datatable();

	// .//*[@id='resPartyChargeAllocFrm:settlementMethodTable:0:j_id2111']
	// *********************
	// ** Build page area **
	// *********************

	public SettlementUIWindow(WebDriver driver) {
		SettlementUIWindow.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.PAYMENTUI_MASTER_DATA_PATH);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnSubmit);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	public SettlementUIWindow initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	@FindBy(id = "settlementPopup:ccIncidentalId:0")
	private static Element rdioYes;

	@FindBy(id = "settlementPopup:ccIncidentalId:1")
	private static Element rdioNo;

	// **************************************
	// *** Main CheckIn Page Interactions ***
	// **************************************

	public String getFolioType() {
		captureFolioType();
		return folioType;
	}

	private void captureFolioType() {
		folioType = lblFolioType.getText();
	}

	public String getResponsibleParty() {
		captureResponsibleParty();
		return responsibleParty;
	}

	private void captureResponsibleParty() {
		responsibleParty = lblResponsibleParty.getText();
	}

	public void settlementUiProcessingWindowHidden() {
		eleProcessingRequest.syncHidden(driver);
	}

	public String getRRNNumber() {
		return rrnNumber.substring(7);
	}

	private void captureRRNNumber() {
		rrnNumber = lblRRNNumber.getText();
		Reporter.log("RRN Number: [" + rrnNumber.substring(7) + "]", true);
	}

	public void applySettlement(String scenario) throws InterruptedException {

		datatable.setVirtualtablePage("Payment");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded();

		String paymentType = datatable.getDataParameter("PaymentType");
		System.out.println("Selct payment type as " + paymentType);

		switch (paymentType.toLowerCase()) {
		case "creditcard":
			settlementCreditCardInfo(scenario);
			break;
		}
	}

	// SettilmentCreditCardInfo
	/**
	 * @deprecated - This is being phased out as it uses a hard-coded credit
	 *             card number. The intent is to replace this with
	 *             #applySettlementGuarantee method
	 * @param scenario
	 *            - virtual table scenario
	 * @throws InterruptedException
	 */
	@Deprecated
	private void settlementCreditCardInfo(String scenario) throws InterruptedException {
		// pageLoaded(elePaymentType);
		// elePaymentType.focus(driver);
		datatable.setVirtualtablePage("Payment");
		datatable.setVirtualtableScenario(scenario);

		pageLoaded(elePaymentWindowFocus);
		initialize();
		elePaymentWindowFocus.highlight(driver);

		// driver.findElement(By.id(".//*[@id='settlementPopup:pmtTypeId']/option[2]")).click();

		Select sel = new Select(driver.findElement(By.id("settlementPopup:pmtTypeId")));
		sel.selectByVisibleText("CreditCard");
		System.out.println("selected");

		pageLoaded(txtCreditCardNumber);
		initialize();

		String paymentType = datatable.getDataParameter("PaymentType");
		String paymentMethod = datatable.getDataParameter("MethodOfPayment");
		String cardHoldername = datatable.getDataParameter("CardHolderName");
		String useCardRadioButton = datatable.getDataParameter("UseCard");
		String cardNumber = datatable.getDataParameter("CardNumber");

		lstPaymentType.select(paymentType);
		lstPaymentMethod.select(paymentMethod);

		/**
		 * @summary Checking Credit card number -
		 * @version Created 12/09/2015
		 * @author Modified by Venkat Atmakuri
		 */
		if (cardNumber.equalsIgnoreCase("")) {
			txtCreditCardNumber.safeSet("4444333322221111");
		} else {
			txtCreditCardNumber.safeSet(cardNumber);
		}

		txtExpirationDate.sendKeys("12/15");

		txtCardHolderName.set(cardHoldername);

		/**
		 * @summary Click Use this card for charges incurred during your stay?
		 *          radio button based on condition
		 * @version Created 12/09/2015
		 * @author Modified by Venkat Atmakuri
		 */
		if (useCardRadioButton.equalsIgnoreCase("false")) {
			rdioNo.click();
		} else {
			rdioYes.click();
		}

		/**
		 * @summary Click submit button handle popUp based on condition
		 * @version Created 12/09/2015
		 * @author Modified by Venkat Atmakuri
		 */
		if (cardNumber.equalsIgnoreCase("")) {
			clickSubmit();
			pageLoaded();
			initialize();

			// handle pop up
			lblPaymentPopupSuccessHeader.syncVisible(driver);
			captureRRNNumber();
			clickOk();

		} else {
			clickSubmit();
		}
	}

	/**
	 * @deprecated - This is being phased out as it uses a hard-coded credit
	 *             card number
	 * @param scenario
	 *            - virtual table scenario
	 * @throws InterruptedException
	 */
	private void settlementCreditCard(String scenario) throws InterruptedException {
		datatable.setVirtualtablePage("Payment");
		datatable.setVirtualtableScenario(scenario);

		pageLoaded();

		boolean expectError = Boolean.parseBoolean(datatable.getDataParameter("IsNegative").toLowerCase());
		String paymentType = datatable.getDataParameter("PaymentType");
		String paymentMethod = datatable.getDataParameter("MethodOfPayment");
		String cardHoldername = datatable.getDataParameter("CardHolderName");
		boolean usePrimaryAddress = Boolean.parseBoolean(datatable.getDataParameter("UsePrimaryAddress").toLowerCase());
		String country = datatable.getDataParameter("Country");
		String addressLine1 = datatable.getDataParameter("AddressLine1");
		String addressLine2 = datatable.getDataParameter("AddressLine2");
		String postalCode = datatable.getDataParameter("PostalCode");
		String city = datatable.getDataParameter("City");
		String state = datatable.getDataParameter("State");
		String expectedErrorMessage = datatable.getDataParameter("ErrorMessage");
		String actualErrorMessage = "";

		lstPaymentType.select(paymentType);
		lstPaymentMethod.select(paymentMethod);

		txtCreditCardNumber.safeSet("4444333322221111");
		txtExpirationDate.sendKeys("12/15");

		txtCardHolderName.set(cardHoldername);

		if (usePrimaryAddress) {
			chkPrimaryAddress.toggle();
			pageLoaded();
		} else {
			lstCountry.select(country.toUpperCase());
			// PleaseWait.WaitForPleaseWait(driver);
			settlementUiProcessingWindowHidden();
			pageLoaded();
			txtAddress1.set(addressLine1);
			txtAddress2.set(addressLine2);
			txtPostalCode.safeSet(postalCode);
			pageLoaded();

			txtCity.set(city);
			lstState.select(state);
		}

		clickSubmit();
		pageLoaded();
		initialize();

		if (lblPaymentPopupSuccessHeader.syncVisible(driver, 5, false)) {
			lblPaymentPopupSuccessHeader.syncVisible(driver);
			captureRRNNumber();
			clickOk();
		} else {
			boolean errorPopupFound = lblPaymentPopupErrorHeader.syncVisible(driver, 1, false);
			if (errorPopupFound == false & expectError == true) {
				throw new RuntimeException("A payment error popup was expected but was not found.");
			} else if (errorPopupFound == true & expectError == false) {
				actualErrorMessage = lblErrorMessage.getText();
				clickErrorOk();
				clickCancel();
				Reporter.log(
						"An error occurred during the settlement process. Error message [" + actualErrorMessage + "]",
						true);
				throw new RuntimeException(
						"A payment error popup was found with error message: [" + actualErrorMessage);
			} else if (errorPopupFound == true & expectError == true) {
				if (lblErrorMessage.getText().toLowerCase().contains(expectedErrorMessage)) {
					Reporter.log("The error message was found as expected.Error message is as follows ["
							+ lblErrorMessage.getText() + "].", true);
				} else {
					Assert.fail("The error message was not found as expected. The error message displayed is ["
							+ lblErrorMessage.getText());
				}
			}
		}
	}

	private void clickSubmit() {
		//btnSubmit.jsClick(driver);
		btnSubmit.click();
		// PleaseWait.WaitForPleaseWait(driver);
		settlementUiProcessingWindowHidden();
	}

	private void clickOk() {
		btnOk.jsClick(driver);
		// btnOk.click();
	}

	private void clickErrorOk() {
		btnErrorOk.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickCancel() {
		btnErrorCancel.click();
	}

	public void launchChargeAccountUI() {
		initialize();
		pageLoaded(lnkChargeAccount);
		lnkChargeAccount.highlight(driver);
		lnkChargeAccount.click();
		System.out.println();
		System.out.println();
	}

	/**
	 * 
	 * @summary Method to click on Settlement button
	 * @version Created 11/16/2014
	 * @author Venkatesh A
	 * @param NA
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void clickonSettilmentButton() {
		parentWindowHandle = driver.getWindowHandle();
		System.out.println("Parent window: " + parentWindowHandle);
		pageLoaded(btnSettlementButton);
		btnSettlementButton.click();
	}

	/**
	 * 
	 * @summary Method to click on Add/Modify button
	 * @version Created 11/16/2014
	 * @author Venkatesh A
	 * @param NA
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void clickonAddModify() {

		pageLoaded(btnAddModify);
		btnAddModify.click();
		handleWindow();
	}

	public String captureParrentWindow() {
		parentWindowHandle = driver.getWindowHandle();
		System.out.println("Parent window at capture method: " + parentWindowHandle);
		return parentWindowHandle;
	}

	/**
	 * 
	 * @summary Method to Handle Windows
	 * @version Created 11/16/2014
	 * @author Venkatesh A
	 * @param NA
	 * @throws NA
	 * @return NA
	 * 
	 */

	public void handleWindow() {

		String getWindow = "";
		String[] getWindows = null;

		driver.navigate().forward();
		Set<String> windows = driver.getWindowHandles();
		System.out.println(windows.size());
		for (String window : windows) {
			getWindow = getWindow + window + ";";
		}

		getWindows = getWindow.split(";");
		System.out.println("Settlement UI window Parent at handleWIndow in Settlement: " + getWindows[0]);
		System.out.println("Settlement UI window: " + getWindows[1]);
		driver.switchTo().window(getWindows[1]);

	}

	/**
	 * 
	 * @summary Method to Validate Settlement Visa number
	 * @version Created 11/17/2014
	 * @author Venkatesh A
	 * @param NA
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void validate_SettilmentMethods() {

		initialize();
		pageLoaded(btnClose);
		Sleeper.sleep(3000);
		initialize();
		WebElement SettlementFormTable = driver.findElement(By.id("settlementMethodForm:settlementMethodTableRP:tb"));
		List<WebElement> tds = SettlementFormTable.findElements(By.tagName("td"));
		System.out.println("settlement TYpe : " + tds.get(0).getText());

		// Validating settlement reservation form
		TestReporter.assertTrue((!tds.get(0).getText().equals("null")), "SettlementMethod Failed to Validate");

		// clicking on close
		btnClose.click();
	}

	/**
	 * 
	 * @summary Method to navigate back to parent Window
	 * @version Created 11/16/2014
	 * @author Venkatesh A
	 * @param NA
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void switchToParentWindow() {
		Sleeper.sleep(3000);
		System.out.println("parentWindowHandle : " + parentWindowHandle);
		driver.switchTo().window(parentWindowHandle);
		System.out.println("Parent Window @ swith to PArent Window method: " + driver.getWindowHandle());
	}

	// Method to click on complete check in button
	public void completeCheckin() {
		// Click yes on PopUp Window
		pageLoaded(btnYes);
		btnYes.click();

		// Click Complete CheckIn button
		pageLoaded(btnCompleteCheckin);
		btnCompleteCheckin.click();
	}

	/**
	 * 
	 * @summary Method to Validate Settlement Visa number
	 * @version Created 11/24/2014
	 * @author Venkatesh A
	 * @param NA
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void validateModifyPartyGuestSettilmentMethods() {

		initialize();
		pageLoaded(btnModifyPartyGuestClose);
		Sleeper.sleep(3000);
		initialize();
		WebElement SettlementFormTable = driver.findElement(By.id("addRespPartyForm:settlementMethodTableRP:tb"));
		List<WebElement> tds = SettlementFormTable.findElements(By.tagName("td"));
		TestReporter.logStep("settlement Type : " + tds.get(0).getText());

		// Validating settlement reservation form
		TestReporter.assertTrue((!tds.get(0).getText().equals("null")), "SettlementMethod Failed to Validate");
	}

	public void applySettlementGuarantee(String paymentType, String paymentMethod, String status, String delay, String thirdParty) {
		enterPaymentTypeAndMethod(paymentType, paymentMethod);
		enterCardInformation(paymentType, paymentMethod, delay, status, thirdParty);
	}
	
	public void applySettlementGuarantee(String scenario){
		datatable.setVirtualtablePath(datatable.PAYMENTUI_MASTER_DATA_PATH);
		datatable.setVirtualtablePage("PaymentUI");
		datatable.setVirtualtableScenario(scenario);
		
		applySettlementGuarantee(datatable.getDataParameter("PaymentType"),
				datatable.getDataParameter("PaymentMethod"),
				datatable.getDataParameter("Status"),
				datatable.getDataParameter("Delay"),
				datatable.getDataParameter("ThirdPartySettlement"));
	}

	/**
	 * This method enters the payment type and method
	 * 
	 * @param type
	 *            - payment type as it is found in the application
	 * @param method
	 *            - payment method as it is found in the application
	 */
	private void enterPaymentTypeAndMethod(String type, String method) {
		TestReporter.log("Selecting payment type: " + type);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		initialize();
		pageLoaded(lstPaymentType);
		lstPaymentType.syncVisible(driver);
		lstPaymentType.select("CreditCard");
		Sleeper.sleep(1000);
		initialize();
		pageLoaded(lstPaymentMethod);
		TestReporter.log("Selecting payment method: " + method);
		lstPaymentMethod.select(method);
		Sleeper.sleep(1000);
		initialize();
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
	}
	
	private void enterCardInformation(String type, String method, String delay, String status, String thirdParty){	
		GenerateCard card = new GenerateCard();
		Map<String, String> cardInfo = null;
		try {
			cardInfo = card.getCardInfo(status, delay, card.getCradTypeEnum(method));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String cardNumber = cardInfo.get("AccountNumber").replace("-", "");
		String expMonth = cardInfo.get("ExpMonth");
		String expYear = cardInfo.get("ExpYear");
		String expDate = expMonth + "/" + expYear;
		String CVVNumber = cardInfo.get("CVV2");
		
		txtCreditCardNumber.safeSetCreditCard(cardNumber);
		txtExpirationDate.sendKeys(expDate);
		TestReporter.log("Entering security code: " + cardInfo.get("CVV2"));
		txtCVV.sendKeys(CVVNumber);

		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		if(thirdParty.equalsIgnoreCase("true")){
			String carhHolderName = cardInfo.get("NameOnCard");
			String address = cardInfo.get("BillingStreet");
			String city = cardInfo.get("BillingCity");
			String postalCode = cardInfo.get("BillingZip");
			txtCardHolderName.safeSet(carhHolderName);
			txtAddress1.safeSet(address);
			txtPostalCode.safeSet(postalCode);
			txtCity.safeSet(city);
		}else if(chkPrimaryAddress.syncVisible(driver, 3, false)){
			chkPrimaryAddress.jsToggle(driver);
			Sleeper.sleep(1500);
		}
		
		try{
			if(rdioNo.syncVisible(driver, 1, false)){
				if(datatable.getDataParameter("Incidentals").equalsIgnoreCase("false")){
					rdioNo.jsClick(driver);
				}else{
					rdioYes.jsClick(driver);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
		
		clickSubmit();
		pageLoaded();
		initialize();

		if (lblPaymentPopupSuccessHeader.syncVisible(driver, 5, false)) {
			lblPaymentPopupSuccessHeader.syncVisible(driver);
			captureRRNNumber();
			clickOk();
		}
		// else {
		// boolean errorPopupFound =
		// lblPaymentPopupErrorHeader.syncVisible(driver, 1, false);
		// if (errorPopupFound == false & expectError == true) {
		// throw new RuntimeException("A payment error popup was expected but
		// was not found.");
		// } else if (errorPopupFound == true & expectError == false) {
		// actualErrorMessage = lblErrorMessage.getText();
		// clickErrorOk();
		// clickCancel();
		// Reporter.log(
		// "An error occurred during the settlement process. Error message [" +
		// actualErrorMessage + "]",
		// true);
		// throw new RuntimeException(
		// "A payment error popup was found with error message: [" +
		// actualErrorMessage);
		// } else if (errorPopupFound == true & expectError == true) {
		// if
		// (lblErrorMessage.getText().toLowerCase().contains(expectedErrorMessage))
		// {
		// Reporter.log("The error message was found as expected.Error message
		// is as follows ["
		// + lblErrorMessage.getText() + "].", true);
		// } else {
		// Assert.fail("The error message was not found as expected. The error
		// message displayed is ["
		// + lblErrorMessage.getText());
		// }
		// }
		// }
	}

	public void switchToSettlementUI(){
		StopWatch watch = new StopWatch();
		watch.start();
		do{
			Sleeper.sleep(1000);
		}while(watch.getTime() < WebDriverSetup.getDefaultTestTimeout() && driver.getWindowHandles().size() < 2);
		
		for(String handle : driver.getWindowHandles()){
			if(driver.switchTo().window(handle).getTitle().contains("Set Settlement")){
				break;
			}
		}
	}
	
	/**
	 * This method Turns on the ExpressCheckout Check box and turns on
	 * the Incidentals if the payment method is already set to CardOnFile value
	 * then it submits the SettlementUI window.  This can be used when a payment is already
	 * made with a card on filer and you need to just modify it by adding expresscheckout and incidentals
	 * assuming they are already turned off
	 * @param na
	 * @return na
	 */
	public void cardAlreadyOnFileSetExpressCheckoutAndIncidentals(){
		
		//This writes out the current payment status for information purpose
			String val = lstMethodOfPayment.getText();
			System.out.println("Current Payment Status - " + val);
			Sleeper.sleep(2000);
					
		//If Method of Payment has CARD ON FILE value then 
		//this will click the chkExpressCheckout radio button
				if (val.contains("ON FILE")) {
					chkExpressCheckoutFlag.click();
					Sleeper.sleep(3000);
					cardAlreadyOnFileClickIncidentals();
				}
	}
	
	private void cardAlreadyOnFileClickIncidentals(){
		//If Method of Payment has CARD ON FILE value then 
		//this will click the chkExpressCheckout radio button
		eleradYes.click();
		Sleeper.sleep(2000);
		clickSubmit();
		Sleeper.sleep(2000);
		clickOk();
	}
	
	/**
	 * @summary: Method to click on Validate button.
	 * @version: Created 02-04-2016, @author: Praveen namburi - Original.
	 */
	public void clickValidate(){
			initialize();
			pageLoaded(btnValidate);
			btnValidate.highlight(driver);
			btnValidate.jsClick(driver);
			settlementUiProcessingWindowHidden();
	}
	
	/**
	 * @summary: Created this method to enter Payment type,payment method, credit card info while 
	 * adding the payment in payment information page for create view cirque.
	  * @author Praveen Namburi, @version: Created 2-4-2016
	 * @param paymentType,paymentMethod, status,delay,thirdParty
	 */
	public void applysetSettlementGuarantee(String paymentType, String paymentMethod, String status, String delay, String thirdParty) {
		enterPaymentTypeAndMethodInSettlementUI(paymentType, paymentMethod);
		setCardInformation(paymentType, paymentMethod, delay, status, thirdParty);
	}
	
	/**
	 * @summary: This method enters the payment type and method in settlemntUI 
	 * while adding the payment in paymentInfo page.(Ref- CreateViewCirque test).
	 * @author: Praveen Namburi - original; @version: Created 2-4-2016.
	 * @param type
	 *            - payment type as it is found in the application
	 * @param method
	 *            - payment method as it is found in the application
	 */
	private void enterPaymentTypeAndMethodInSettlementUI(String type, String method) {
		TestReporter.log("Selecting payment type: " + type);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		initialize();
		pageLoaded(lstPaymentType);
		lstPaymentType.syncVisible(driver);
		/*if (type.equalsIgnoreCase("cash")) {
			btnSubmit.highlight(driver);
			btnSubmit.click();
		
		}else{*/
		initialize();
		pageLoaded(lstPaymentMethod);
		TestReporter.log("Selecting payment method: " + method);
		lstPaymentMethod.select(method);
		Sleeper.sleep(1000);
		initialize();
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
		//}
	}
	
	/**
	 * @summary: Created this method to enter credit card info while 
	 * adding the payment in payment information page for create view cirque.
	 * @author Praveen Namburi, @version: Created 2-4-2016
	 * @param type,method,delay,status,thirdParty
	 */
	private void setCardInformation(String type, String method, String delay, String status, String thirdParty){	
		GenerateCard card = new GenerateCard();
		Map<String, String> cardInfo = null;
		try {
			cardInfo = card.getCardInfo(status, delay, card.getCradTypeEnum(method));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String cardNumber = cardInfo.get("AccountNumber").replace("-", "");
		String expMonth = cardInfo.get("ExpMonth");
		String expYear = cardInfo.get("ExpYear");
		String expDate = expMonth + "/" + expYear;
		String CVVNumber = cardInfo.get("CVV2");
		
		txtCreditCardNumber.safeSetCreditCard(cardNumber);
		txtExpirationDate.sendKeys(expDate);
		TestReporter.log("Entering security code: " + cardInfo.get("CVV2"));
		txtCVV.sendKeys(CVVNumber);

		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		if(thirdParty.equalsIgnoreCase("true")){
			String cardHolderName = cardInfo.get("NameOnCard");
			txtCardHolderName.safeSet(cardHolderName);
		}
		
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
		
		//Click on Validate button
		clickValidate();
		initialize();

		if (lblPaymentPopupSuccessHeader.syncVisible(driver, 5, false)) {
			lblPaymentPopupSuccessHeader.syncVisible(driver);
			captureRRNNumber();
			clickOk();
		}		
	}
	
	/**
	 * @summary: method to set settlement guarantee
	 * @author: Sowmya ch
	 * @param paymentType
	 * @param paymentMethod
	 * @param status
	 * @param delay
	 * @param thirdParty
	 */
	public void setSettlementGuarantee(String paymentType, String paymentMethod, String status, String delay, String thirdParty) {
	
		TestReporter.log("Settlement Method Invoked");

		switch (paymentType) {
		case "Cash":
			pageLoaded(lstPaymentType);
			btnSubmit.highlight(driver);
			btnSubmit.click();
			if (lblSettlementSuccessHeader.syncVisible(driver, 5, false)) {
				lblSettlementSuccessHeader.syncVisible(driver);
				clickOk();
			}		
			break;
		default:
			break;
		}
		
	}
	
}
