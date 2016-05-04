package apps.paymentUI;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utils.AutomationException;
import core.WebDriverSetup;
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
import selenium.common.Constants;
import utils.Datatable;
import utils.GenerateCard;
import utils.Randomness;
import utils.Sleeper;
import utils.TestReporter;

public class PaymentUIWindow {

	// **********************************
	// *** Main PaymentUI Page Fields ***
	// **********************************
	private  String strEmptyAmountDue = "";
	private  String strFolioAmountDue = "";
	private  String strTotalAmountDue = "";
	private  String strRRNNumber = "";
	private  WebDriver driver;
	private String uiPaymentType;
	private String uiPaymentMethod;
	private String cardNumberMasked = "";
	private String paymentAmount = "";
	private String cardNumber = "";
	private  String splitPayment = "";
	private String ExpiredExpirationDate = "12/12";
	private String errorMessage = "";
	private Datatable datatable = new Datatable();
	/**
	 * Define enumerated values used to determine the payment types required by
	 * a particular flow. They are used to determine the correct string value to enter
	 * into the Payment Ui payment type listbox
	 * @author Waightstill W Avery
	 * @version 11/09/2015 - original
	 *
	 */
	public enum paymentTypes {
		CASH, CHECK, CREDITCARD, VOUCHER, CHARGEACCOUNT, PAIDOUT, PREPAIDCARD, DISNEYREWARDCARD
	}

	/**
	 * Define enumerated values used to determine the payment methods required by
	 * a particular flow. They are used to determine the correct string value to enter
	 * into the Payment Ui payment method listbox
	 * @author Waightstill W Avery
	 * @version 11/09/2015 - original
	 *
	 */
	public enum paymentMethods {
		CASH, PAIDOUT, DISNEYDOLLARS, TRAVELERSCHECK, AMERICANEXPRESS, DINERSCLUB, DISCOVER, JCB, MASTERCARD, VISA, MANUALCHECK, GIFTCERTIFICATE10, GIFTCERTIFICATE25, GIFTCERTIFICATE50, GIFTCERTIFICATE100, GIFTCARD, DISNEYREWARDSCARD
	}

	// ************************************
	// *** Main PaymentUI Page Elements ***
	// ************************************

	// Grab the Apply button
	@FindBy(id = "paymentPopup:okButtonId")
	private  Button btnApply;

	// Grab the Banking Account Center
	@FindBy(id = "paymentPopup:bacSelect")
	private  Listbox lstBankingAccountCenter;

	// Grab the Folio Type list
	@FindBy(id = "paymentPopup:folioTypeSelect")
	private  Listbox lstFolioType;

	// Grab the Payment Type List
	@FindBy(id = "paymentPopup:paymentTypeId:0:editablePmtstId:0:ePmtTypeId")
	private  Listbox lstPaymentType;

	// Grab the Payment Method list
	@FindBy(id = "paymentPopup:paymentTypeId:0:editablePmtstId:0:ePmtMethId")
	private  Listbox lstPaymentMethod;

	// Grab the Payment Amount text box
	@FindBy(id = "paymentPopup:paymentTypeId:0:editablePmtstId:0:amountInputText")
	private  Textbox txtPaymentAmount;

	// Grab the currency list
	@FindBy(id = "paymentPopup:paymentTypeId:0:editablePmtstId:0:eCurrencyListId")
	private  Listbox lstCurrency;

	// Grab the Split Payment button
	@FindBy(id = "paymentPopup:splitPaymentButId")
	private  Button btnSplitPayment;

	// Grab the Balance Inquiry button
	@FindBy(id = "paymentPopup:balanceInqButId")
	private  Button btnBalanceInquiry;

	// Grab the Folio and Total Amount Due label
	@FindBy(id = "paymentPopup:folioAmountDuePanel")
	private Label lblFolioAndTotalAmountDue;

	// Grab the Cancel button
	@FindBy(css = "a.actionButton-grey:nth-child(2)")
	private  Button btnCancel;

	// Grab the Credit Card Number text box
	@FindBy(id = "paymentPopup:paymentTypeId:0:editablePmtstId:0:creditCardText")
	private  Textbox txtCreditCardNumber;

	// Grab the Credit Card Expiration Date text box
	@FindBy(id = "paymentPopup:paymentTypeId:0:editablePmtstId:0:creditCardExpDateText")
	private  Textbox txtCreditCardExpirationDate;

	// Grab the Credit Card Security code text box
	@FindBy(id = "paymentPopup:paymentTypeId:0:editablePmtstId:0:cvvNumberTextId")
	private  Textbox txtCreditCardSecurityCode;

	// Grab the Incidental radio group
	@FindBy(id = "paymentPopup:ccIncidentalId:0")
	private  Button radCreditCardIncidentals;

	// Grab the Incidental radio group
	@FindBy(id = "paymentPopup:ccIncidentalId:1")
	private  Button radCreditCardIncidentalsNo;

	// Grab the Check Number text box
	@FindBy(id = "paymentPopup:paymentTypeId:0:editablePmtstId:0:checkNbrText")
	private  Textbox txtCheckNumber;

	// Grab the Check Account Holder text box
	@FindBy(id = "paymentPopup:paymentTypeId:0:editablePmtstId:0:checkOrginatorText")
	private  Textbox txtCheckAccountHolder;

	// Grab the Voucher Number text box
	@FindBy(id = "paymentPopup:paymentTypeId:0:editablePmtstId:0:vouchNbrId")
	private  Textbox txtVoucherNumber;

	// Grab the Gift Card Number text box
	@FindBy(id = "paymentPopup:paymentTypeId:0:editablePmtstId:0:ppCrdNbrId")
	private  Textbox txtGiftCardNumber;

	// Grab the Disney Rewards Card Number text box
	@FindBy(id = "paymentPopup:paymentTypeId:0:editablePmtstId:0:drCrdNbrId")
	private  Textbox txtDisneyRewardCardNumber;

	// Grab the Disney Rewards Card Expiration Date text box
	@FindBy(id = "paymentPopup:paymentTypeId:0:editablePmtstId:0:drCardExpDateText")
	private  Textbox txtDisneyRewardCardExpirationDate;

	// Grab the Disney Rewards Card Security Code text box
	@FindBy(id = "paymentPopup:paymentTypeId:0:editablePmtstId:0:disneyRewardsCVVNumberTextId")
	private  Textbox txtDisneyRewardCardSecurityCode;

	// Grab the Card Holder Information text box
	@FindBy(id = "paymentPopup:cardHolderNameText")
	private  Textbox txtCreditCardHolderName;

	// Grab the Payment Processing Popup
	@FindBy(id = "pmtProcessingPopup")
	private  Element elePaymentProcessing;

	@FindBy(id = "rrnForm:rrnPaymentPanel")
	private  Label lblRRNNumber;

	@FindBy(css = "#retrievalRefNumberPanelModalPanelHeader > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > div:nth-child(1)")
	private  Label lblPaymentPopupSuccessHeader;

	@FindBy(id = "rrnForm:okId")
	private  Button btnPaymentPopupSuccessOk;

	@FindBy(css = "#headerTextWarn")
	private  Label lblPaymentPopupWarningHeader;

	@FindBy(css = "input[id^=\"errorForm:j_id\"]")
	private  Button btnPaymentPopupWarningContinue;
	
	@FindBy(css = "a[id^='errorForm:j_id']")
	private Link lnkPaymentPopupWarningContinue;

	@FindBy(id = "errorForm:CancelId")
	private  Button btnPaymentPopupWarningCancel;

	@FindBy(id = "headerTextError")
	private  Label lblPaymentPopupErrorHeader;

	@FindBy(css = ".scrollableContentXY > ul:nth-child(1) > li:nth-child(1)")
	private  Label lblPaymentPopupText;

	@FindBy(id = "errorForm:errAftrInitOkBtn")
	private  Button btnPaymentPopupErrorOk;

	@FindBy(xpath = "/html/body/div[2]/div[2]/div/div[2]/table/tbody/tr[2]/td/form/table/tbody/tr[1]/td[1]/table/tbody/tr[2]/td/div/table/tbody/tr/td/a[1]")
	private Button btnDupDocumentContinue;

	@FindBy(xpath = "/html/body/div[3]/div[2]/div/div[2]/table/tbody/tr[2]/td/form/table/tbody/tr[1]/td[1]/table/tbody/tr[2]/td/div/table/tbody/tr/td/a[1]")
	private Button btnDupAmountContinue;

	@FindBy(id = "paymentPopup:folioIdSelect")
	private Listbox lstResponsibleParty;

	@FindBy(id = "manualAuthorizationForm:checkAuthNumberText")
	private Textbox txtAuthorizationNumber;

	@FindBy(id = "manualAuthorizationForm:submitId")
	private Button btnManualAuthorizationSubmit;

	@FindBy(id = "paymentPopup:cardBalancePanel")
	private Label lblCardBalancePanel;

	@FindBy(id = "paymentPopup:paymentTypeId:0:editablePmtstId:0:paidOutText")
	private Textbox txtPaidOutDocumentNumber;

	@FindBy(id = "paymentPopup:paymentTypeId:0:editablePmtstId:0:paidOutCommentsText")
	private Textbox txtPaidOutComments;

	//Grab the Folio Type list
	@FindBy(id = "paymentPopup:paymentTypeId:0:editablePmtstId:0:ReasonCodesId")
	private  Listbox lstPaidOutReason;

	@FindBy(linkText = "Continue")
	private Element eleContinue;

	@FindBy(xpath = ".//div/ul/li")
	private Textbox txtErrorInfo_EmptyAmount;

	@FindBy(linkText = "Cancel")
	private Element elePaymentCancel;

	@FindBy(id = "paymentPopup:paymentTypeId:0:editablePmtstId:0:ReasonCodesId")
	private Listbox lstPaidOutResons;
	
	@FindBy(id = "paymentPopup:showPrimaryGuestAddressId")
	private Checkbox chkPrimaryGuest;
	private String invalidExpirationDate = "abcd";
	
	@FindBy(xpath="html/body/div[2]/div[2]/div/div[2]/table/tbody/tr[2]/td/form/table/tbody/tr[1]/td[1]/table/tbody/tr[2]/td/div/table/tbody/tr/td/a")
	private Button btnOkSucssesPayment;
	
	@FindBy(linkText = "Cancel")
	private Link lnkCancel;	

	// *********************
	// ** Build page area **
	// *********************

	public PaymentUIWindow(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.PAYMENTUI_MASTER_DATA_PATH);
	}

	public  PaymentUIWindow initialize(WebDriver driver) {
		return ElementFactory.initElements(driver, PaymentUIWindow.class);
	}

	public void paymentUILoaded() {
		while (!btnApply.elementWired()) {
			initialize(driver);
		}
		btnApply.syncVisible(driver);
		captureFolioAndTotalAmountDue();
	}

	public void paymentUILoaded(Element element) {
		while (!element.elementWired()) {
			initialize(driver);
		}
		btnApply.syncVisible(driver);
		captureFolioAndTotalAmountDue();
	}

	private PaymentUIWindow initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author YourNameHere
	 * @version 9/25/2015 YourNameHere - original
	 * @param element
	 *            - Element to be used to determine if the page is loaded
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}
	
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnApply);
	}

	// **************************************
	// *** Main CheckIn Page Interactions ***
	// **************************************

	/*
	 * *Method to capture the Folio and Total amount due
	 */
	public void captureFolioAndTotalAmountDue() {
		String strAmountDueText;
		String[] arrAmountDueText;
		int intLoopCounter;

		strAmountDueText = lblFolioAndTotalAmountDue.getText();
		if (strAmountDueText.contains("\n")) {
			strAmountDueText = strAmountDueText.replace("\n", " ");
		}
		arrAmountDueText = strAmountDueText.split(" ");

		for (intLoopCounter = 0; intLoopCounter < arrAmountDueText.length; intLoopCounter++) {
			// Test for Folio Amount Due
			if (arrAmountDueText[intLoopCounter].contains("Folio")) {
				intLoopCounter += 3;
				this.strFolioAmountDue = arrAmountDueText[intLoopCounter];
				this.strFolioAmountDue = strFolioAmountDue.replace("$", "");
				this.strFolioAmountDue = strFolioAmountDue.replace(",", "");
			} else if (arrAmountDueText[intLoopCounter].contains("Total") || arrAmountDueText[intLoopCounter].contains("Deposit")) {
				intLoopCounter += 3;
				this.strTotalAmountDue = arrAmountDueText[intLoopCounter];
				this.strTotalAmountDue = strTotalAmountDue.replace("$", "");
				this.strTotalAmountDue = strTotalAmountDue.replace(",", "");
			}
		}
	}

	/*
	 * *Method to get and return the Folio amount due
	 * 
	 * 
	 */
	public String getEmptyAmountDue() {
		TestReporter.log("Empty Amount Due: " + strEmptyAmountDue);
		return strEmptyAmountDue;
	}

	public String getFolioAmountDue() {
		TestReporter.log("Folio Amount Due: " + strFolioAmountDue);
		return strFolioAmountDue;
	}

	/*
	 * *Method to get and return the Total amount due
	 */
	public String getTotalAmountDue() {
		TestReporter.log("Total Amount Due: " + strTotalAmountDue);
		return strTotalAmountDue;
	}

	/*
	 * *Method to sync for the Payment UI Processing window to be hidden
	 */
	public void paymentUiProcessingWindowHidden() {
		elePaymentProcessing.syncHidden(driver);
	}

	/*
	 * Method for setting the RRN Number
	 */
	private void captureRRNNumber() {
		initialize(driver);
		strRRNNumber = lblRRNNumber.getText();
		TestReporter.log("RRN Number: " + strRRNNumber);
	}

	/*
	 * Method for returning the RRN Number
	 */
	public String getRRNNumber() {
		return strRRNNumber;
	}

	public void clickCancel() {
		btnCancel.jsClick(driver);
	}

	/**
	 * This method is designed to handle mutiple payment flows, the beginning of
	 * which is triggered by the payment type
	 * 
	 * @param paymentType
	 *            - Enum, defined at the top of the page class, used to
	 *            determine the string value to enter into the Payment UI
	 *            payment type listbox
	 * @param paymentMethod
	 *            - Enum, defined at the top of the page class, used to
	 *            determine the string value to enter into the Payment UI
	 *            payment method listbox
	 * @param status
	 *            - status of a card to be used in a payment flow that requires
	 *            a card for the PCard repository
	 * @param delay
	 *            - delay of a card to be used in a payment flow that requires a
	 *            card for the PCard repository
	 * @param incidentals
	 *            - boolean, true f incidentals are to be applied, false
	 *            otherwise
	 * @param amount
	 *            - amount to be paid
	 * @throws Exception
	 */
	public void takePayment(paymentTypes paymentType, paymentMethods paymentMethod, String status, String delay,
			boolean incidentals, String amount, String expiredCC, String primaryGuestInfo,
			String errorMessage) {
		this.errorMessage = errorMessage;
//		paymentUILoaded();
		pageLoaded();
		
		//Determine the payment type to use
		switch (paymentType) {
		case CASH:
			boolean validCashType = true;
			uiPaymentType = "Cash";
			switch (paymentMethod) {
			case CASH:
				uiPaymentMethod = "Cash";
				break;
			case DISNEYDOLLARS:
				uiPaymentMethod = "Disney Dollars";
				break;
			case TRAVELERSCHECK:
				uiPaymentMethod = "Travelers Check";
				break;
			default:
				validCashType = false;
				TestReporter.assertTrue(validCashType,
						"The payment method [" + paymentMethod + "] is not valid for cash payments.");
				break;
			}
			//Make the cash payment
			TestReporter.log("RRN Number: " + cashPayment(uiPaymentType, uiPaymentMethod, amount));
			break;
		case CHARGEACCOUNT:
			break;
		case CHECK:
			uiPaymentType = "Check";
			uiPaymentMethod = "Manual Check";
			//Make the check payment
			TestReporter.log("RRN Number: " + checkPayment(uiPaymentType, uiPaymentMethod, amount));
			break;
		case CREDITCARD:
			boolean validCardType = true;
			uiPaymentType = "CreditCard";
			switch (paymentMethod) {
			case DINERSCLUB:
				uiPaymentMethod = "Diners Club";
				break;
			case JCB:
				uiPaymentMethod = "JCB";
				break;
			case AMERICANEXPRESS:
				uiPaymentMethod = "American Express";
				break;
			case DISCOVER:
				uiPaymentMethod = "Discover";
				break;
			case MASTERCARD:
				uiPaymentMethod = "Master Card";
				break;
			case VISA:
				uiPaymentMethod = "Visa";
				break;
			default:
				validCardType = false;
				TestReporter.assertTrue(validCardType,
						"The payment method [" + paymentMethod + "] is not valid for credit card payments.");
				break;
			}
			//Make the credit card payment
			TestReporter.log("RRN Number: "
					+ creditCardPayment(uiPaymentType, uiPaymentMethod, amount, delay, status, incidentals, expiredCC, primaryGuestInfo));
			break;
		case PAIDOUT:
			uiPaymentType = "PaidOut";
			uiPaymentMethod = "PaidOut";
			//Process Paid Out
			TestReporter.log("RRN Number: "
					+ paidOut(uiPaymentType, uiPaymentMethod, amount));
			break;
		case VOUCHER:
			boolean validVoucherType = true;
			uiPaymentType = "Voucher";
			switch (paymentMethod) {
			case GIFTCERTIFICATE10:
				uiPaymentMethod = "$10 Gift Certificate";
				break;
			case GIFTCERTIFICATE25:
				uiPaymentMethod = "$25 Gift Certificate";
				break;
			case GIFTCERTIFICATE50:
				uiPaymentMethod = "$50 Gift Certificate";
				break;
			case GIFTCERTIFICATE100:
				uiPaymentMethod = "$100 Gift Certificate";
				break;
			default:
				validVoucherType = false;
				TestReporter.assertTrue(validVoucherType,
						"The payment method [" + paymentMethod + "] is not valid for voucher payments");
				break;
			}
			break;
		case DISNEYREWARDCARD:
			uiPaymentType = "DisneyRewardCard";
			uiPaymentMethod = "Disney Rewards Card";

			//Enter Reward card details and make Balance Inquiry
			TestReporter.log("cardBalance : " 
					+ setRewardcardAndBalanceEnquiry(uiPaymentType, uiPaymentMethod, delay, status, expiredCC));
			break;
		case PREPAIDCARD:
			uiPaymentType = "PrePaidCard";
			uiPaymentMethod = "Gift Card";
			//Make the gift card payment
			TestReporter.log("RRN Number: "
					+ giftCardPayment(uiPaymentType, uiPaymentMethod, amount, delay, status));
			break;
		default:
			TestReporter.assertTrue(false,
					"The payment type [" + paymentType
					+ "] is not a valid payment type. Please select from one of the following: ["
					+ paymentTypes.values().toString() + "].");
			break;
		}
	}

	/*
	private String paidOut(String paymentType, String paymentMethod, String amount){
		String comments = datatable.getDataParameter("PaidOutComments");
		String reason = datatable.getDataParameter("PaidOutReason");
		enterPaymentTypeAndMethod(paymentType, paymentMethod);
		txtPaidOutDocumentNumber.safeSet(Randomness.randomNumber(6));

		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		if(lstPaidOutResons.syncVisible(driver, 2, false)){
			lstPaidOutResons.select(reason);
		}
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
		initialize();

		txtPaidOutComments.safeSet(comments);
		setAmount(amount);
		clickApply();
		handleWarningsAndErrors();
		return strRRNNumber;
	}*/

	//Added paidOutReason step to get value from datatable - Marella Satish
	private String paidOut(String paymentType, String paymentMethod, String amount){
		String comments = datatable.getDataParameter("PaidOutComments");
		String paidOutReason = datatable.getDataParameter("PaidOutReason");
		enterPaymentTypeAndMethod(paymentType, paymentMethod);
		txtPaidOutDocumentNumber.safeSet(Randomness.randomNumber(6));
		txtPaidOutComments.safeSet(comments);
		lstPaidOutReason.select(paidOutReason);
		setAmount(amount);
		clickApply();
		handleWarningsAndErrors();
		return strRRNNumber;
	}


	/**
	 * This method performs a balance inquiry on the gift card, then applies paymen using the same gift card
	 * @param type - payment type for a gift card as it is found in the application
	 * @param method - payment method for a gift card as it is found in the application
	 * @param amount - amount to be applied
	 * @param delay - delay on the card, used for querying PCard repository
	 * @param status - status on the card, used for querying PCard repository
	 * @return RRN number from the transaction
	 * @throws Exception
	 */
	private String giftCardPayment(String type, String method, String amount, 
			String delay, String status) {
		enterPaymentTypeAndMethod(type, method);
		GenerateCard card = new GenerateCard();
		Map<String, String> cardInfo = card.getCardInfo(status, delay, card.getCradTypeEnum(method));
		cardNumber = cardInfo.get("AccountNumber");
		cardNumberMasked = cardNumber.substring(0, cardNumber.length() - 4) + "****";

		TestReporter.log("Entering credit card number: " + "************" + cardInfo.get("AccountNumber")
				.substring(cardInfo.get("AccountNumber").length() - 4, cardInfo.get("AccountNumber").length()));
		txtGiftCardNumber.safeSetCreditCard(cardInfo.get("AccountNumber").replace("-", ""));
		checkGiftCardBalance();
		setAmount(amount);
		clickApply();
		handleWarningsAndErrors();
		return strRRNNumber;
	}

	/**
	 * This method is intended to apply a refund to a card already on file
	 * @param type - payment type for a card as it is found in the application
	 * @param method - payment method for a card as it is found in the application
	 * @param amount - amount to be applied
	 * @return RRN number from the transaction
	 * @throws Exception
	 */
	public String refundCardPayment(String type, String method, String amount) {
		enterPaymentTypeAndMethod(type, method);
		txtGiftCardNumber.safeSetCreditCard(getCardNumber().replace("-", ""));
		setAmount(amount);
		clickApply();

		initialize();
		pageLoaded(btnDupDocumentContinue);
		btnDupDocumentContinue.syncVisible(driver);
		btnDupDocumentContinue.click();

		handleWarningsAndErrors();

		return strRRNNumber;
	}

	/**
	 * This method performs a balance inquiry on a card
	 * @return String value of the card balance
	 */
	private String checkGiftCardBalance(){
		String cardBalance = "";
		long timeoutBefore = WebDriverSetup.getDefaultTestTimeout();

		pageLoaded(txtPaymentAmount);
		txtPaymentAmount.safeSet("0");

		pageLoaded(btnBalanceInquiry);
		btnBalanceInquiry.jsClick(driver);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		do{
			try{
				cardBalance = lblCardBalancePanel.getText().split(":")[1].trim();
				TestReporter.log("Gift Card Balanace: " + cardBalance);
			}catch(NoSuchElementException | StaleElementReferenceException | ArrayIndexOutOfBoundsException e){
				initialize();
			}
		}while(cardBalance.isEmpty());
		driver.manage().timeouts().implicitlyWait(timeoutBefore, TimeUnit.SECONDS);
		return cardBalance.trim();
	}

	/**
	 * This method applies a cash payment
	 * @param type - payment type for cash as it is found in the application
	 * @param method - payment method a cash method as it is found in the application
	 * @param amount - amount to be applied
	 * @return RRN number from the transaction
	 */
	private String cashPayment(String type, String method, String amount) {
		enterPaymentTypeAndMethod(type, method);
		setAmount(amount);
		clickApply();
		handleWarningsAndErrors();

		return strRRNNumber;
	}

	/**
	 * This method applies a check payment
	 * @param type - payment type for a check as it is found in the application
	 * @param method - payment method a check method as it is found in the application
	 * @param amount - amount to be applied
	 * @return RRN number from the transaction
	 */
	private String checkPayment(String type, String method, String amount){
		enterPaymentTypeAndMethod(type, method);
		setDocumnetNumberAndAccountholder();
		setAmount(amount);
		clickApply();
		handleCheckPaymentFloorLimitWarning();
		handleWarningsAndErrors();

		return strRRNNumber;
	}

	/**
	 * This method handles the event of a payment amount exceeding the maximum
	 * amount allowed by the business rules. In this event, the method overrides
	 * the warning and allows the payment to continue.
	 */
	private void handleCheckPaymentFloorLimitWarning(){
		long timeoutBefore = WebDriverSetup.getDefaultTestTimeout();
		driver.manage().timeouts().implicitlyWait((long)2, TimeUnit.SECONDS);
		if(lblPaymentPopupWarningHeader.syncVisible(driver, 1, false)){
			TestReporter.log("Handling Check Payment Floor Limit Warning");
			String authorizationNumber = Randomness.randomNumber(4);
			initialize();
			try{
				btnPaymentPopupWarningContinue.jsClick(driver);
				Sleeper.sleep(1000);
			}catch(Exception e){
				initialize();
				TestReporter.log("Enter authorization override number: [" + authorizationNumber);
				txtAuthorizationNumber.safeSet(authorizationNumber);
				btnManualAuthorizationSubmit.jsClick(driver);
				Sleeper.sleep(3000);
			}
			do {
				initialize();
				pageLoaded(btnPaymentPopupSuccessOk);
				btnPaymentPopupSuccessOk.jsClick(driver);
				Sleeper.sleep(1000);
			} while (btnPaymentPopupSuccessOk.syncVisible(driver, 1, false));
		}
		driver.manage().timeouts().implicitlyWait(timeoutBefore, TimeUnit.SECONDS);
	}

	/**
	 * This method sets the document number and account holder name for a check payment
	 */
	private void setDocumnetNumberAndAccountholder(){
		setDocumentNumber();
		setAccountHolder();
	}

	/**
	 * This method sets the documnet number for a check payment
	 * Rewriting to handle error scenarios - WWA
	 */
	private void setDocumentNumber(){
		String checkNumber = "";
		pageLoaded(txtCheckNumber);
		TestReporter.log("Setting check number to: ["+checkNumber+"].");
		if(!errorMessage.equalsIgnoreCase("Check Number is required")){
			Randomness.randomNumber(3);
			txtCheckNumber.safeSet(checkNumber);
		}
	}

	/**
	 * This method sets the accound holder's name for a check payment
	 */
	private void setAccountHolder(){
		String accountholder = getResponsibleParty().split("-")[0].trim();
		initialize();
		pageLoaded(txtCheckAccountHolder);
		TestReporter.log("Setting account holder to: ["+accountholder+"]");
		txtCheckAccountHolder.safeSet(accountholder);
	}

	/**
	 * This method grabs the responsible party name from the Folio listbox
	 * @return responsible party's name
	 */
	private String getResponsibleParty(){
		return lstResponsibleParty.getText();
	}

	/**
	 * This method retrieves a credit card from the PCard repository, generates
	 * the masked card value as it would be found in the Folio payment history
	 * page, processes the credit card payment and returns the RRn number
	 * @param type - payment type as it is found in the application
	 * @param method - payment method as it is found in the application
	 * @param amount - payment amount to apply to the card
	 * @param delay - card delay, used to retrieve the desired card from PCard
	 * @param status - card status, used to retrieve the desired card from PCard
	 * @param incidentals - boolean true if incidentals are to be applied to the card, false otherwise
	 * @return - RRN transaction number
	 * @throws Exception
	 */
	private String creditCardPayment(String type, String method, String amount, String delay, String status,
			boolean incidentals, String ExpiredCCStatus, String primaryGuestInfo) {
		System.out.println("@@@@ ExpiredCCStatus @@@@"+ExpiredCCStatus);

		enterPaymentTypeAndMethod(type, method);

		GenerateCard card = new GenerateCard();
		
		/*
		 * Attempt to retrieve a card with a valid CCV value (i.e. a value that is not 'null')
		 * Since the card generator for PCard gives random cards, try this retrieval 10 times.
		 * If no valid card is returned after 10 attempts, throw an error as the test cannot continue.
		 */
		int maxTries = 10;
		int attempt = 0;
		Map<String, String> cardInfo;
		do{
			attempt++;
			cardInfo = card.getCardInfo(status, delay, card.getCradTypeEnum(method));
		}while(attempt < maxTries && cardInfo.get("CVV2").equalsIgnoreCase("null"));
		
		if((attempt >= maxTries) && (cardInfo.get("CVV2").equalsIgnoreCase("null"))){
			TestReporter.assertTrue(false,  "No card was returned with a valid CCV value.  The last card retreived was: ID = ["+cardInfo.get("CardID")+"].");
		}

		if(errorMessage.equalsIgnoreCase("Credit Card Number is required")){
			TestReporter.log("Entering a blank credit card number to invoke an expected error.");
			txtCreditCardNumber.safeSetClear("");
		}else{
//			System.out.println();
			cardNumber = cardInfo.get("AccountNumber");
			cardNumberMasked = "************" + cardInfo.get("AccountNumber")
					.substring(cardInfo.get("AccountNumber").length() - 4, cardInfo.get("AccountNumber").length());
			TestReporter.log("Entering credit card number: " + "************" + cardInfo.get("AccountNumber")
					.substring(cardInfo.get("AccountNumber").length() - 4, cardInfo.get("AccountNumber").length()));
			txtCreditCardNumber.safeSetCreditCard(cardInfo.get("AccountNumber").replace("-", ""));
			
		}

		//Validate whether to enter Expired expiration Date from datatable or Valid expiration date from generated from API
		pageLoaded(txtCreditCardExpirationDate);
		if (ExpiredCCStatus.equalsIgnoreCase("true")){
			TestReporter.log("Enter expired expiration date: " + ExpiredExpirationDate);
			txtCreditCardExpirationDate.safeSet(ExpiredExpirationDate);
		}
		else if(errorMessage.equalsIgnoreCase("Expiration Date is invalid")){
			TestReporter.log("Entering value ["+invalidExpirationDate+"] for the expiration date.");
			txtCreditCardExpirationDate.safeSet(invalidExpirationDate );
		}else{
			TestReporter.log("Enter expiration date: " + cardInfo.get("ExpMonth") + "/" + cardInfo.get("ExpYear"));
			if(cardInfo.get("ExpMonth").length() < 2) cardInfo.put("ExpMonth", "0" + cardInfo.get("ExpMonth"));
			txtCreditCardExpirationDate.safeSet(cardInfo.get("ExpMonth") + "/" + cardInfo.get("ExpYear"));
		}

		TestReporter.log("Entering security code: " + cardInfo.get("CVV2"));
		txtCreditCardSecurityCode.safeSet(cardInfo.get("CVV2"));
		setAmount(amount);
		handleIncidentals(incidentals);
		checkForPrimaryGuets(primaryGuestInfo);
		clickApply();
		handleWarningsAndErrors();
//		System.out.println();
		return strRRNNumber;
	}
	
	private void checkForPrimaryGuets(String useGuestInfo){
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		if(useGuestInfo.equalsIgnoreCase("true")){
			if(chkPrimaryGuest.syncVisible(driver, 1, false)){
				chkPrimaryGuest.jsToggle(driver);
				Sleeper.sleep(1000);
			}
		}
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
	}

	/**
	 * This method enters the payment type and method
	 * @param type - payment type as it is found in the application
	 * @param method - payment method as it is found in the application
	 */
	private void enterPaymentTypeAndMethod(String type, String method) {
		TestReporter.log("Selecting payment type: " + uiPaymentType);
		pageLoaded(lstPaymentType);
		lstPaymentType.syncVisible(driver);
		/*
		 * Replacing the below code in an attempt to find a more efficient
		 * method of selecting payment types - WWA
		 */
//		WebDriverWait wait = new WebDriverWait(driver,5);
//		try{
//			wait.until(ExpectedConditions.stalenessOf(lstPaymentType));
//		}catch(TimeoutException te){}
//		initialize();
		
		int counter = 0;
		boolean success = false;
		//Try for 5 seconds to select the payment type
		do{
			try{
				Sleeper.sleep(500);
				counter++;
				lstPaymentType.select(uiPaymentType);
				success = true;
			}catch(Exception e){
				initialize();
			}
		}while(!success && counter < 10);
		TestReporter.assertTrue(counter < 10, "The Payment Type was not selected within 5 seconds");
		
//		pageLoaded(lstPaymentMethod);
		/*
		 * Replacing the below code in an attempt to find a more efficient
		 * method of selecting payment types - WWA
		 */
//		try{
//			wait.until(ExpectedConditions.stalenessOf(lstPaymentMethod));
//		}catch(TimeoutException te){}
		counter = 0;
		success = false;
		//Try for 5 seconds to select the payment method
		do {
			try {
				Sleeper.sleep(500);
				counter++;
				TestReporter.log("Selecting payment method: " + uiPaymentMethod);
				lstPaymentMethod.select(uiPaymentMethod);
				success = true;
			} catch (Exception e) {
				initialize();
			}
		} while (!success && counter < 10);
		TestReporter.assertTrue(counter < 10, "The Payment Type was not selected within 5 seconds");
		initialize();
	}

	/**
	 * This method sets the amount to be paid. If an amount "total" is passed,
	 * then the Total Amount Due is captured and that amount is applied. If the
	 * amount "folio" is passed, then the Folio Amount Due is grabbed and that
	 * amount is applied. OTherwise, the value that is passed is applied
	 * directly.
	 * 
	 * @param amount - amount to be paid
	 */
	private void setAmount(String amount) {
		//Capture amounts due if not already populated
		if(strTotalAmountDue.isEmpty() || strFolioAmountDue.isEmpty()) captureFolioAndTotalAmountDue();
		System.out.println(getTotalAmountDue());
		switch (amount.toLowerCase()) {
		case "":
			TestReporter.log("Entering the Total Amount Due as found in the payment UI");
			amount = getEmptyAmountDue();
			break;
		case "total":
			TestReporter.log("Entering the Total Amount Due as found in the payment UI");
			amount = getTotalAmountDue();
			break;
		case "folio":
			TestReporter.log("Entering the Folio Amount Due as found in the payment UI");
			amount = getFolioAmountDue();
			break;
		default:
			TestReporter.log("Entering the amount to pay as [" + amount + "]");
			break;
		}
		TestReporter.log("Paying ["+amount+"] amount due of ["+amount+"]");
		paymentAmount = amount;
		txtPaymentAmount.safeSetClear(amount);
	}

	/**
	 * This method handles incidentals
	 * @param handleIncidentals - boolean true to apply incidentals, false otherwise
	 */
	private void handleIncidentals(boolean handleIncidentals) {
		TestReporter.log("Setting incidentals to [" + String.valueOf(handleIncidentals) + "]");
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		if (handleIncidentals) {
			if(radCreditCardIncidentals.syncVisible(driver, 1, false)){
				radCreditCardIncidentals.jsClick(driver);
			}
		} else {
			if(radCreditCardIncidentalsNo.syncVisible(driver, 1, false)){
				radCreditCardIncidentalsNo.jsClick(driver);
			}
		}
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
	}

	/**
	 * This method clicks the Apply button
	 */
	private void clickApply() {
		if(splitPayment.equalsIgnoreCase("first payment") ||
				splitPayment.equalsIgnoreCase("second of third payment")){
			// Split payment
			btnSplitPayment.jsClick(driver);
			Sleeper.sleep(1500);
			initialize(driver);
		}else{		
			// Apply payment
			btnApply.jsClick(driver);
			Sleeper.sleep(1500);
			initialize(driver);
		}
	}

	/**
	 * This method handles any warnings (e.g. duplicate document ro duplicate
	 * amount) or errors that may occur after the payment is attempted
	 */
	private void handleWarningsAndErrors() {
		boolean expectError;
		if(errorMessage.isEmpty()){
			expectError = false;
		}else{
			expectError = true;
		}
		// Handle any warnings that may occur
		System.out.println("Handling Warnings");
		driver.manage().timeouts().implicitlyWait((long)2, TimeUnit.SECONDS);
		/*
		 * The following code is intended to handle the occurrence of the
		 * Duplicate Document and/or Duplicate Amount warnings
		 * 
		 * UPDATE: It seems that the continue button for Warnings was originally
		 * designed inthe framework to be an input button. However, it seems
		 * that the Continue element may also be a link. Code has been added to
		 * try and determine which is used. - WWA
		 */
		Element eContinue;
		try{
			//Look for the first warning box
			initialize();
			try{
				if(btnPaymentPopupWarningContinue.syncVisible(driver, 3, false)){
					eContinue = btnPaymentPopupWarningContinue;
				}else{
					eContinue = lnkPaymentPopupWarningContinue;
				}
				if(eContinue.syncVisible(driver, 3, false)){
					eContinue.focus(driver);
					eContinue.sendKeys(Keys.ENTER);
					Sleeper.sleep(2000);
				}
			}catch(Exception err){
				err.printStackTrace();
			}

			//Look for  execute it oncthe next warning box
			initialize();
			try{
				if(btnPaymentPopupWarningContinue.syncVisible(driver, 3, false)){
					eContinue = btnPaymentPopupWarningContinue;
				}else{
					eContinue = lnkPaymentPopupWarningContinue;
				}
				if(eContinue.syncVisible(driver, 3, false)){
					eContinue.focus(driver);
					eContinue.sendKeys(Keys.ENTER);
					Sleeper.sleep(2000);
				}
			}catch(Exception err){
				err.printStackTrace();
			}
		}catch(ClassCastException | NoSuchElementException | StaleElementReferenceException e){

		}

		// Handle any errors the may occur; if no error, capture the RRN number
		// and close the success modal
		System.out.println("Syncing for errors.");
		initialize();

		/*
		 * Commenting out below line as the error popup should be visible, and
		 * not hidden, if an error occurred
		 */
		//boolean errorPopupFound = lblPaymentPopupErrorHeader.syncHidden(driver, 1, false);
		boolean errorPopupFound = lblPaymentPopupErrorHeader.syncVisible(driver, 1, false);
		TestReporter.logStep("Error popup SyncHidden Status : "+errorPopupFound);

		if (errorPopupFound == false & expectError == true) {
			throw new RuntimeException("A payment error popup was expected but was not found.");
		} else if (errorPopupFound == true & expectError == false) {
			throw new AutomationException(
					"A payment error popup was found with error message: " + lblPaymentPopupText.getText(), driver);

		}else if(lblPaymentPopupErrorHeader.isDisplayed()){

			//Validating Error Pop up Message - Empty amount
			lblPaymentPopupText.highlight(driver);
			TestReporter.logStep(" Error Pop Up Message : "+lblPaymentPopupText.getText());
			
			if(!errorMessage.isEmpty()){
				TestReporter.assertTrue(lblPaymentPopupText.getText().contains(errorMessage), "Actual String["+lblPaymentPopupText.getText()+"] does not contains Expected Value ["+errorMessage+"].");				
			}else{
				TestReporter.assertTrue(lblPaymentPopupText.getText().contains("Payment Amount is required"), "Actual String["+lblPaymentPopupText.getText()+"] does not contains Expected Value");
			}
			
			btnPaymentPopupErrorOk.highlight(driver);
			btnPaymentPopupErrorOk.jsClick(driver);
			
			btnCancel.jsClick(driver);

		}
		// Validate  the Warning PopUp for Expired Expiration date
		else if (lblPaymentPopupWarningHeader.syncVisible(driver) == true) {
			initialize();
			lblPaymentPopupText.highlight(driver);
			String ActualWarningText = lblPaymentPopupText.getText();
			String ExpectedDateWarningText = "Expiration Date may be incorrect";
			String ExpectedAmountWarningText = "Duplicate Amount";
			boolean ExpCCDateStatus = ActualWarningText.toLowerCase().contains(ExpectedDateWarningText.toLowerCase());
			boolean ExpAmountStatus = ActualWarningText.toLowerCase().contains(ExpectedAmountWarningText.toLowerCase());

			if (ExpAmountStatus == true){

				TestReporter.assertTrue(ExpAmountStatus , "Actual String["+ActualWarningText+"] does not contains Expected string["+ExpectedAmountWarningText+"]");
				initialize();
				eleContinue.highlight(driver);
				eleContinue.jsClick(driver);
				Sleeper.sleep(2000);
				TestReporter.logStep("Transaction Continued...");
				initialize();
				pageLoaded(btnPaymentPopupSuccessOk);
				btnPaymentPopupSuccessOk.jsClick(driver);

			}else{
				TestReporter.assertTrue(ExpCCDateStatus , "Actual String["+ActualWarningText+"] does not contains Expected string["+ExpectedDateWarningText+"]");
				btnPaymentPopupWarningCancel.highlight(driver);
				//btnPaymentPopupWarningCancel.jsClick(driver);
				initialize();
				btnCancel.highlight(driver);
				btnCancel.jsClick(driver);
				TestReporter.logStep("Transaction Cancelled due to invalid expiration date");

			}



			/*if (ExpCCDateStatus == true){

				TestReporter.assertTrue(ExpCCDateStatus , "Actual String["+ActualWarningText+"] does not contains Expected string["+ExpectedDateWarningText+"]");
				btnPaymentPopupWarningCancel.highlight(driver);
				//btnPaymentPopupWarningCancel.jsClick(driver);
				initialize();
				btnCancel.highlight(driver);
				btnCancel.jsClick(driver);
				TestReporter.logStep("Transaction Cancelled due to invalid expiration date");

			}else{
				TestReporter.assertTrue(ExpAmountStatus , "Actual String["+ActualWarningText+"] does not contains Expected string["+ExpectedAmountWarningText+"]");
				initialize();
				eleContinue.highlight(driver);
				eleContinue.jsClick(driver);
				Sleeper.sleep(2000);
				TestReporter.logStep("Transaction Continued...");
				initialize();
				pageLoaded(btnPaymentPopupSuccessOk);
				btnPaymentPopupSuccessOk.jsClick(driver);


			}*/

		} 

		/*else if (errorPopupFound == true & expectError == true) {

		 * @TODO Add Negative Flow validations


		}*/

		else {
			//Commenting out the below line to search for all elements with a certain attribute type
//			lblPaymentPopupSuccessHeader.syncVisible(driver);
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			List<WebElement> wElements = driver.findElements(By.id("rrnForm:rrnPaymentPanel"));
			for(WebElement we : wElements){
				Element e3 = new ElementImpl(we);
				if(e3.syncVisible(driver)){
					e3.highlight(driver);
					break;
				}
			}
			driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
			
			if((!uiPaymentType.equalsIgnoreCase("cash")) && (!uiPaymentType.equalsIgnoreCase("check") && (!uiPaymentType.equalsIgnoreCase("paidout")))){
				captureRRNNumber();
			}
			do {
				initialize();
				pageLoaded(btnPaymentPopupSuccessOk);
				btnPaymentPopupSuccessOk.jsClick(driver);
				Sleeper.sleep(1000);
			} while (btnPaymentPopupSuccessOk.syncVisible(driver, 1, false));
			Sleeper.sleep(5000);
		}
		driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT, TimeUnit.SECONDS);
	}


	/**
	 * This method allows classes/method exterior to this class to pass in a
	 * string value and have a valid payment type enum returned. The cases can
	 * be expanded to allow for new use-cases.
	 * @param type - payment type to generate a paymentTypes enum
	 * @return paymentTypes enum value
	 */
	public static paymentTypes getpaymentTypeEnum(String type) {
		paymentTypes types = null;
		boolean validMethod = true;

		switch (type.toLowerCase().replace(" ", "").trim()) {
		case "cash":
			types = paymentTypes.CASH;
			break;
		case "check":
			types = paymentTypes.CHECK;
			break;
		case "creditcard":
			types = paymentTypes.CREDITCARD;
			break;
		case "voucher":
			types = paymentTypes.VOUCHER;
			break;
		case "chargeaccount":
			types = paymentTypes.CHARGEACCOUNT;
			break;
		case "paidout":
			types = paymentTypes.PAIDOUT;
			break;
		case "prepaidcard":
			types = paymentTypes.PREPAIDCARD;
			break;
		case "disneyrewardcard":
			types = paymentTypes.DISNEYREWARDCARD;
			break;
		default:
			validMethod = false;
			TestReporter.assertTrue(validMethod, "The payment type [" + type + "] is not valid/");
			break;
		}

		return types;
	}

	/**
	 * This method allows classes/method exterior to this class to pass in a
	 * string value and have a valid payment method enum returned. The cases can
	 * be expanded to allow for new use-cases.
	 * @param method - payment method to generate a paymentMethods enum
	 * @return paymentMethods enum value
	 */
	public static paymentMethods getpaymentMethodEnum(String method) {
		paymentMethods localMethod = null;
		boolean validmethod = true;

		switch (method.toLowerCase().replace(" ", "").trim()) {
		case "cash":
			localMethod = paymentMethods.CASH;
			break;
		case "disneydollars":
			localMethod = paymentMethods.DISNEYDOLLARS;
			break;
		case "mastercard":
			localMethod = paymentMethods.MASTERCARD;
			break;
		case "travelerscheck":
			localMethod = paymentMethods.TRAVELERSCHECK;
			break;
		case "americanexpress":
			localMethod = paymentMethods.AMERICANEXPRESS;
			break;
		case "dinersclub":
			localMethod = paymentMethods.DINERSCLUB;
			break;
		case "discover":
			localMethod = paymentMethods.DISCOVER;
			break;
		case "jcb":
			localMethod = paymentMethods.JCB;
			break;
		case "visa":
			localMethod = paymentMethods.VISA;
			break;
		case "manualcheck":
			localMethod = paymentMethods.MANUALCHECK;
			break;
		case "$10giftcertificate":
			localMethod = paymentMethods.GIFTCERTIFICATE10;
			break;
		case "$25giftcertificate":
			localMethod = paymentMethods.GIFTCERTIFICATE25;
			break;
		case "$50giftcertificate":
			localMethod = paymentMethods.MASTERCARD;
			break;
		case "$100giftcertificate":
			localMethod = paymentMethods.GIFTCERTIFICATE100;
			break;
		case "giftcard":
			localMethod = paymentMethods.GIFTCARD;
			break;
		case "disneyrewardscard":
			localMethod = paymentMethods.DISNEYREWARDSCARD;
			break;
		case "paidout":
			localMethod = paymentMethods.PAIDOUT;
			break;
		default:
			validmethod = false;
			TestReporter.assertTrue(validmethod, "The payment method [" + method + "] is not valid.");
			break;
		}

		return localMethod;
	}

	/**
	 * This method returns the masked card number as it is found in the Folio Tab page
	 * @return String, masked card number
	 */
	public String getCardNumberMasked() {
		return this.cardNumberMasked;
	}

	/**
	 * This method returns the raw card value
	 * @return String, raw card number
	 */
	public String getCardNumber() {
		return this.cardNumber;
	}

	/**
	 * This method returns the payment amount as it was entered in the Payment UI
	 * @return
	 */
	public String getPaymentAmount() {
		return this.paymentAmount;
	}

	/**
	 * This method returns the payment method as it was entered in the Payment UI
	 * @return
	 */
	public String getPaymentMethod(){
		return this.uiPaymentMethod;
	}

	/**
	 * This method is designed to handle mutiple payment flows, the beginning of
	 * which is triggered by the payment type
	 * 
	 * @param paymentType
	 *            - Enum, defined at the top of the page class, used to
	 *            determine the string value to enter into the Payment UI
	 *            payment type listbox
	 * @param paymentMethod
	 *            - Enum, defined at the top of the page class, used to
	 *            determine the string value to enter into the Payment UI
	 *            payment method listbox
	 * @param status
	 *            - status of a card to be used in a payment flow that requires
	 *            a card for the PCard repository
	 * @param delay
	 *            - delay of a card to be used in a payment flow that requires a
	 *            card for the PCard repository
	 * @param incidentals
	 *            - boolean, true f incidentals are to be applied, false
	 *            otherwise
	 * @param amount
	 *            - amount to be paid
	 * @throws Exception
	 */
	public void takePaymentWithSplit(paymentTypes paymentType, paymentMethods paymentMethod, String status, String delay,
			boolean incidentals, String amount, String split, String expiredCC, String primaryGuestInfo) {
		paymentUILoaded();
		
		//Determine if a split payment is required and/or needs to be completed
		if(split.equalsIgnoreCase("true")){
			if(splitPayment.equalsIgnoreCase("")){
				splitPayment = "first payment";
			}
		}

		//Determine the payment type to use
		switch (paymentType) {
		case CASH:
			boolean validCashType = true;
			uiPaymentType = "Cash";
			switch (paymentMethod) {
			case CASH:
				uiPaymentMethod = "Cash";
				break;
			case DISNEYDOLLARS:
				uiPaymentMethod = "Disney Dollars";
				break;
			case TRAVELERSCHECK:
				uiPaymentMethod = "Travelers Check";
				break;
			default:
				validCashType = false;
				TestReporter.assertTrue(validCashType,
						"The payment method [" + paymentMethod + "] is not valid for cash payments.");
				break;
			}
			//Make the cash payment
			TestReporter.log("RRN Number: " + cashPayment(uiPaymentType, uiPaymentMethod, amount));
			break;
		case CHARGEACCOUNT:
			break;
		case CHECK:
			uiPaymentType = "Check";
			uiPaymentMethod = "Manual Check";
			//Make the check payment
			TestReporter.log("RRN Number: " + checkPayment(uiPaymentType, uiPaymentMethod, amount));
			break;
		case CREDITCARD:
			boolean validCardType = true;
			uiPaymentType = "CreditCard";
			switch (paymentMethod) {
			case DINERSCLUB:
				uiPaymentMethod = "Diners Club";
				break;
			case JCB:
				uiPaymentMethod = "JCB";
				break;
			case AMERICANEXPRESS:
				uiPaymentMethod = "American Express";
				break;
			case DISCOVER:
				uiPaymentMethod = "Discover";
				break;
			case MASTERCARD:
				uiPaymentMethod = "Master Card";
				break;
			case VISA:
				uiPaymentMethod = "Visa";
				break;
			default:
				validCardType = false;
				TestReporter.assertTrue(validCardType,
						"The payment method [" + paymentMethod + "] is not valid for credit card payments.");
				break;
			}
			//Make the credit card payment
			TestReporter.log("RRN Number: "
					+ creditCardPayment(uiPaymentType, uiPaymentMethod, amount, delay, status, incidentals, expiredCC, primaryGuestInfo));
			break;
		case PAIDOUT:
			uiPaymentType = "PaidOut";
			uiPaymentMethod = "PaidOut";
			//Process Paid Out
			TestReporter.log("RRN Number: "
					+ paidOut(uiPaymentType, uiPaymentMethod, amount));
			break;
		case VOUCHER:
			boolean validVoucherType = true;
			uiPaymentType = "Voucher";
			switch (paymentMethod) {
			case GIFTCERTIFICATE10:
				uiPaymentMethod = "$10 Gift Certificate";
				break;
			case GIFTCERTIFICATE25:
				uiPaymentMethod = "$25 Gift Certificate";
				break;
			case GIFTCERTIFICATE50:
				uiPaymentMethod = "$50 Gift Certificate";
				break;
			case GIFTCERTIFICATE100:
				uiPaymentMethod = "$100 Gift Certificate";
				break;
			default:
				validVoucherType = false;
				TestReporter.assertTrue(validVoucherType,
						"The payment method [" + paymentMethod + "] is not valid for voucher payments");
				break;
			}
			break;
		case DISNEYREWARDCARD:
			uiPaymentType = "DisneyRewardCard";
			uiPaymentMethod = "Disney Rewards Card";
			//Enter Reward card details and make Balance Inquiry
			TestReporter.log("cardBalance : " 
					+ setRewardcardAndBalanceEnquiry(uiPaymentType, uiPaymentMethod, delay, status, expiredCC));
			
			break;
		case PREPAIDCARD:
			uiPaymentType = "PrePaidCard";
			uiPaymentMethod = "Gift Card";
			//Make the gift card payment
			TestReporter.log("RRN Number: "
					+ giftCardPayment(uiPaymentType, uiPaymentMethod, amount, delay, status));
			break;
		default:
			TestReporter.assertTrue(false,
					"The payment type [" + paymentType
					+ "] is not a valid payment type. Please select from one of the following: ["
					+ paymentTypes.values().toString() + "].");
			break;
		}
		//Determine if a split payment is required and/or needs to be completed
		if(split.equalsIgnoreCase("true")){
			if(splitPayment.equalsIgnoreCase("first payment")){
				splitPayment = "second payment";
			}else if(splitPayment.equalsIgnoreCase("second payment")){
				splitPayment = "split complete";
			}
			}
		
	}
	public void takeThreePaymentsWithSplit(paymentTypes paymentType, paymentMethods paymentMethod, String status, String delay,
			boolean incidentals, String amount, String split, String expiredCC, String primaryGuestInfo) {
		paymentUILoaded();
		
		//Determine if a split payment is required and/or needs to be completed
		if(split.equalsIgnoreCase("true")){
			if(splitPayment.equalsIgnoreCase("")){
				splitPayment = "first payment";
			}
		}

		//Determine the payment type to use
		switch (paymentType) {
		case CASH:
			boolean validCashType = true;
			uiPaymentType = "Cash";
			switch (paymentMethod) {
			case CASH:
				uiPaymentMethod = "Cash";
				break;
			case DISNEYDOLLARS:
				uiPaymentMethod = "Disney Dollars";
				break;
			case TRAVELERSCHECK:
				uiPaymentMethod = "Travelers Check";
				break;
			default:
				validCashType = false;
				TestReporter.assertTrue(validCashType,
						"The payment method [" + paymentMethod + "] is not valid for cash payments.");
				break;
			}
			//Make the cash payment
			TestReporter.log("RRN Number: " + cashPayment(uiPaymentType, uiPaymentMethod, amount));
			break;
		case CHARGEACCOUNT:
			break;
		case CHECK:
			uiPaymentType = "Check";
			uiPaymentMethod = "Manual Check";
			//Make the check payment
			TestReporter.log("RRN Number: " + checkPayment(uiPaymentType, uiPaymentMethod, amount));
			break;
		case CREDITCARD:
			boolean validCardType = true;
			uiPaymentType = "CreditCard";
			switch (paymentMethod) {
			case DINERSCLUB:
				uiPaymentMethod = "Diners Club";
				break;
			case JCB:
				uiPaymentMethod = "JCB";
				break;
			case AMERICANEXPRESS:
				uiPaymentMethod = "American Express";
				break;
			case DISCOVER:
				uiPaymentMethod = "Discover";
				break;
			case MASTERCARD:
				uiPaymentMethod = "Master Card";
				break;
			case VISA:
				uiPaymentMethod = "Visa";
				break;
			default:
				validCardType = false;
				TestReporter.assertTrue(validCardType,
						"The payment method [" + paymentMethod + "] is not valid for credit card payments.");
				break;
			}
			//Make the credit card payment
			TestReporter.log("RRN Number: "
					+ creditCardPayment(uiPaymentType, uiPaymentMethod, amount, delay, status, incidentals, expiredCC, primaryGuestInfo));
			break;
		case PAIDOUT:
			uiPaymentType = "PaidOut";
			uiPaymentMethod = "PaidOut";
			//Process Paid Out
			TestReporter.log("RRN Number: "
					+ paidOut(uiPaymentType, uiPaymentMethod, amount));
			break;
		case VOUCHER:
			boolean validVoucherType = true;
			uiPaymentType = "Voucher";
			switch (paymentMethod) {
			case GIFTCERTIFICATE10:
				uiPaymentMethod = "$10 Gift Certificate";
				break;
			case GIFTCERTIFICATE25:
				uiPaymentMethod = "$25 Gift Certificate";
				break;
			case GIFTCERTIFICATE50:
				uiPaymentMethod = "$50 Gift Certificate";
				break;
			case GIFTCERTIFICATE100:
				uiPaymentMethod = "$100 Gift Certificate";
				break;
			default:
				validVoucherType = false;
				TestReporter.assertTrue(validVoucherType,
						"The payment method [" + paymentMethod + "] is not valid for voucher payments");
				break;
			}
			break;
		case DISNEYREWARDCARD:
			uiPaymentType = "DisneyRewardCard";
			uiPaymentMethod = "Disney Rewards Card";
			//Enter Reward card details and make Balance Inquiry
			TestReporter.log("cardBalance : " 
					+ setRewardcardAndBalanceEnquiry(uiPaymentType, uiPaymentMethod, delay, status, expiredCC));
			
			break;
		case PREPAIDCARD:
			uiPaymentType = "PrePaidCard";
			uiPaymentMethod = "Gift Card";
			//Make the gift card payment
			TestReporter.log("RRN Number: "
					+ giftCardPayment(uiPaymentType, uiPaymentMethod, amount, delay, status));
			break;
		default:
			TestReporter.assertTrue(false,
					"The payment type [" + paymentType
					+ "] is not a valid payment type. Please select from one of the following: ["
					+ paymentTypes.values().toString() + "].");
			break;
		}
		//Determine if a split payment is required and/or needs to be completed
		if (split.equalsIgnoreCase("true")) {
			if (splitPayment.equalsIgnoreCase("first payment")) {
				splitPayment = "second of third payment";
			} else if(splitPayment.equalsIgnoreCase("second of third payment")){
				splitPayment = "final payment";
			} else if (splitPayment.equalsIgnoreCase("final payment")) {
				splitPayment = "split complete";
			}
		}
		
	}
	

	/**
	 * 
	 * @summary Method to Process Invalidate PaidOut Payment
	 * @version Created 11/26/2014
	 * @author Venkatesh A
	 * @param  Comments,Amount
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void invalidPaidOutPayment(String comments, String amount){
		txtPaidOutComments.highlight(driver);
		txtPaidOutComments.safeSet(comments);
		setAmount(amount);
		clickApply();
	}

	/**
	 * @summary : Method to setRewardCarddetails and make balance inquiry.
	 * @version : Created 30-12-2015.
	 * @author: Praveen Namburi
	 * @param paymentType,  @param paymentMethod, @param status
	 * @param delay, @return
	 * @throws Exception
	 */
	public String setRewardcardAndBalanceEnquiry(String paymentType, 
			String paymentMethod,String delay,String status, 
			String ExpiredCCStatus) {
		System.out.println("@@@@ ExpiredCCStatus @@@@"+ExpiredCCStatus);

		//--
		enterPaymentTypeAndMethod(paymentType,paymentMethod);

		GenerateCard card = new GenerateCard();
		Map<String, String> cardInfo = card.getCardInfo(status, delay, card.getCradTypeEnum(paymentMethod));
		cardNumber = cardInfo.get("AccountNumber");
		cardNumberMasked = cardNumber.substring(0, cardNumber.length() - 4) + "****";

		TestReporter.log("Entering Disney Reward card number: " + "************" + cardInfo.get("AccountNumber")
				.substring(cardInfo.get("AccountNumber").length() - 4, cardInfo.get("AccountNumber").length()));
		txtDisneyRewardCardNumber.safeSetCreditCard(cardInfo.get("AccountNumber").replace("-", ""));

		//Validate whether to enter Expired expiration Date from datatable or Valid expiration date from generated from API
		if (ExpiredCCStatus.equalsIgnoreCase("true")){
			TestReporter.log("Enter expired expiration date: " + ExpiredExpirationDate);
			txtDisneyRewardCardExpirationDate.safeSet(ExpiredExpirationDate);

		}else if(errorMessage != null && !errorMessage.isEmpty()){
			if (errorMessage.equalsIgnoreCase("Expiration Date is invalid")) {
				TestReporter.log("Setting the expiration date value as ["
						+ errorMessage + "].");
				txtDisneyRewardCardExpirationDate
						.safeSet(ExpiredExpirationDate);
			} else if (errorMessage
					.equalsIgnoreCase("Expiration Date is require")) {
				TestReporter
						.log("Blanking the expiration date value to invoke an expected error.");
				txtDisneyRewardCardExpirationDate.safeSetClear("");
			}
		}
		else{
				TestReporter.log("Enter expiration date: " + cardInfo.get("ExpMonth") + "/" + cardInfo.get("ExpYear"));
				txtDisneyRewardCardExpirationDate.safeSet(cardInfo.get("ExpMonth") + "/" + cardInfo.get("ExpYear"));
		}

		TestReporter.log("Entering security code: " + cardInfo.get("CVV2"));
		txtDisneyRewardCardSecurityCode.safeSet(cardInfo.get("CVV2"));

		String cardBalance = "";
		long timeoutBefore = WebDriverSetup.getDefaultTestTimeout();

		pageLoaded(txtPaymentAmount);
		txtPaymentAmount.clear();
		txtPaymentAmount.safeSet("0");

		
		//to make balance enquiry
		initialize();
		pageLoaded(btnBalanceInquiry);
		btnBalanceInquiry.jsClick(driver);
		if(errorMessage != null){
			if(errorMessage.isEmpty()){
				
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				do{
					try{
						try{
							cardBalance = lblCardBalancePanel.findElement(By.xpath("span[2]")).getText();
							TestReporter.log("Card Balance: " + cardBalance);
						}catch(StaleElementReferenceException sere){
							Sleeper.sleep(1000);
							pageLoaded(lblCardBalancePanel);
							lblCardBalancePanel.syncVisible(driver);
							cardBalance = lblCardBalancePanel.findElement(By.xpath("span[2]")).getText();
						}
						TestReporter.log("Gift Card Balanace: " + cardBalance);
					}catch(NoSuchElementException e){
		
					}
				}while(cardBalance.isEmpty());
				TestReporter.log("Disney Reward Card Balance: " + cardBalance);//to set amount
				pageLoaded(txtPaymentAmount);
				txtPaymentAmount.safeSet("1");
				driver.manage().timeouts().implicitlyWait(timeoutBefore, TimeUnit.SECONDS);
				
				
				clickApply();
				
			}	
		}
		handleWarningsAndErrors();
	return cardBalance.trim();
	}
	public void clickSplitPayment(){
	    btnSplitPayment.highlight(driver);
		btnSplitPayment.jsClick(driver);
		handleWarningsAndErrors();
		
	}
	
	public void btnapply(){
		// Apply the payment
		       btnApply.highlight(driver);
				btnApply.click();
				Sleeper.sleep(1500);
				initialize(driver);

	}
	
//	public String splitpaymentcarddetails(String paymentType, String paymentMethod,String amount, String status,String delay) throws Exception{
//
//		String ExpiredCCStatus = datatable.getDataParameter("ExpiredCC");
//		System.out.println("@@@@ ExpiredCCStatus @@@@"+ExpiredCCStatus);
//
//		enterPaymentTypeAndMethod(paymentType,paymentMethod);
//		Sleeper.sleep(500);
//		txtDisneyRewardCardNumber.safeSet("6274250000617412");
//		pageLoaded(txtDisneyRewardCardExpirationDate);
//		txtDisneyRewardCardExpirationDate.safeSet("12/15");
//		pageLoaded(txtDisneyRewardCardSecurityCode);
//		txtDisneyRewardCardSecurityCode.safeSet("672");
//		
//		//to set amount
//		initialize();
//		pageLoaded(txtPaymentAmount);
//		setAmount(amount);
//		
//		return null;
//	}

	
	
}
