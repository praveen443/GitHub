package apps.dreams.ReservationPage;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import apps.SettlementUI.SettlementUIWindow;
import apps.dreams.PleaseWait.PleaseWait;
import apps.paymentUI.PaymentUIWindow;
import core.FrameHandler;
import core.WebDriverSetup;
import core.WindowHandler;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Link;
import core.interfaces.Listbox;
import core.interfaces.RadioGroup;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.ButtonImpl;
import core.interfaces.impl.CheckboxImpl;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.LinkImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import selenium.common.Constants;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;
import utils.date.DateTimeConversion;

/**
 * @summary Contains the methods & objects for the Dreams UI Reservation Page
 *          content frame
 * @version Created 11/04/2014
 * @author Waightstill W. Avery
 */

public class ReservationPageContentFrame {
	// ****************************
	// *** Content Frame Fields ***
	// ****************************
	private Datatable datatable = new Datatable();
	private String reservationNumber = "";
	private WebDriver driver;
	@SuppressWarnings("unused")
	private String cardNumber;
	private String cardNumberMasked;
	@SuppressWarnings("unused")
	private String uiPaymentMethod;
	@SuppressWarnings("unused")
	private String refundCardNumber;
	@SuppressWarnings("unused")
	private String refundPaymentAmount;
	private String paymentAmount;

	// ******************************
	// *** Content Frame Elements ***
	// ******************************

	// Cancel Reservation button
	@FindBy(name = "b_Cancel")
	private Button btnCancel;

	// Process Payment and Settlement buttons
	@FindBy(id = "PostBut")
	private List<Button> btnPaymentAndSettlement;

	// Reservation number
	@FindBy(xpath = "//*[@id=\"firstLayer\"]/table[6]/tbody/tr/td/table/tbody/tr[2]/td[6]/div")
	private Element eleReservationNumber;

	// Reservation Summary table
	@FindBy(xpath = "//*[@id=\"firstLayer\"]/table[6]/tbody/tr/td/table")
	private Webtable tblReservationSummary;

	// Expand All button
	@FindBy(name = "b_ExpandAll")
	private Button btnExpandAll;

	// Deposit and Billing Summary Link
	@FindBy(linkText = "Deposit and Billing Summary")
	private Link lnkDepositAndBillingSummary;

	// process selected travel plan
	@FindBy(partialLinkText = "-$")
	private Link processselectedtravelplan;

	// Refund button
	@FindBy(xpath = "//*[@value='Refund']")
	private Link refundbtn;

	//
	@FindBy(name = "refunds.amountString")
	private Textbox Amount;

	@FindBy(xpath = "//*[@value='0.00']")
	private Textbox AmountToRefund;

	@FindBy(xpath = "//*[@value='test_value_0']")
	private Button btnSelectFolio;

	@FindBy(name = "refunds.commentReasonComment.commentReason.commentReasonCode")
	private Listbox refundreason;

	/* @FindBy(name="s_FolioSelectView_129391201") */
	/* @FindBy(xpath="//*[@value='SHOW_PAYMENTS']") */
	@FindBy(xpath = "//*[div[@align='left']/select[@ class='controls']]")
	private Listbox Selecttype;

	@FindBy(name = "refunds.commentReasonComment.comment.commentText")
	private Textbox comments;

	/* By.xpath("//a[contains(@href, '/user/reddit/m/redditnews')]") */
	// PTOI Marketing Preferences button
	@FindBy(xpath = "//*[@id='MarketingPreferences'][@value='Marketing Preferences Required!']")
	private Button btnMarketingPreferencesRequired;

	// PTOI Unable To Confirm button
	@FindBy(name = "btnUnable")
	private Button btnPtoiUnableToConfirm;

	// selectAll checkbox in accommodation summary
	@FindBy(name = "travelPlanTO.currentTravelPlanSegment.facilityOrderTOList[0].accommodationToList[0].selected")
	private Checkbox chkselectAll;

	@FindBy(name = "travelPlanTO.currentTravelPlanSegment.facilityOrderTOList[0].doryAccommodationsListForDisplay[0].doryTicketsList[0].selected")
	private Checkbox chkSelectAll;

	// PTOI Form element
	@FindBy(name = "PTOIQuestionForm")
	private Element elePtoiForm;

	// PTOI Info button
	@FindBy(name = "btnInfo")
	private Button btnPtoiInfo;

	// PTOI Info Close button
	@FindBy(name = "btnSave")
	private Button btnPtoiInfoClose;

	// PTOI 3rd Party CC button
	@FindBy(name = "btn3rdPartyCC")
	private Button btnPtoiThirdPartyCC;

	// PTOI Save button
	@FindBy(name = "btnSave")
	private Button btnPtoiSave;

	// PTOI Edit Marketing Preferences button
	@FindBy(xpath = "//*[@id='MarketingPreferences'][@value='Edit Marketing Preferences']")
	private Button btnPtoiEditMarketingPreferences;

	@FindBy(partialLinkText = "Submit")
	private Link lnkSubmit;

	// Element Error Header in settlemntUI Window
	@FindBy(id = "headerTextError")
	private Element eleErrorHeader;

	// Celebrations linke
	@FindBy(xpath = "//B[text()='Celebrations ']")
	private Link lnkCelebrations;

	// Clicking on Add button - internal comments window
	@FindBy(xpath = "//*[@id='LkUp1But'][@value='Add']")
	private Button btnInternalCommentsAdd;

	// Clicking on OK button - internal comments window
	@FindBy(xpath = "//*[@id='LkUp1But'][@value='OK']")
	private Button btnInternalCommentsOk;

	@FindBy(id = "Deposit and Billing Summary")
	private Element eleDepositAndBillingSummary;

	@FindBy(partialLinkText = "Edit")
	private Link lnkEdit;

	@FindBy(partialLinkText = "Delete")
	private Link lnkDelete;

	//button Split payment in payment window

	// button Split payment in payment window
	@FindBy(id = "paymentPopup:splitPaymentButId")
	private Button btnSplitPayment;

	@FindBy(xpath = "html/body/div[2]/div[2]/div/div[2]/table/tbody/tr[2]/td/form/table/tbody/tr[1]/td[1]/table/tbody/tr[2]/td/div/table/tbody/tr/td/a")
	private Button btnOkSucssesPayment;

	@FindBy(name = "CommonPopulateCancelReservationForm")
	private Element eleCancelDiv;

	// click on Marketing Preferences Required
	@FindBy(id = "MarketingPreferences")
	private Link MarketingPreferencesRequired;

	@FindBy(xpath = "//*[@id='MarketingPreferences'][@value='Edit Marketing Preferences']")
	private Button EditMarketingPreference;

	@FindBy(id = "MarketingPreferences")
	private Button btnMrkPrf;

	@FindBy(name = "btnInfo")
	private Button btnInfo;

	@FindBy(name = "b_Re-Discovery")
	private Button btnReDiscovery;

	@FindBy(xpath = "//INPUT[contains(@value,'Close')]")
	private Button btnInfoClose;

	@FindBy(xpath = "//INPUT[contains(@title,'Email Opt Out')]")
	private Button btnEmailOptOut;

	@FindBy(xpath = "//INPUT[contains(@title,'Mailing Address Opt Out')]")
	private Button btnAddressOptOut;

	@FindBy(xpath = "//*[@id='firstLayer']/table[18]/tbody/tr/td/a")
	private Link lnkConfirmation;

	@FindBy(partialLinkText = "Edit")
	private Link lnkEditGuest;

	@FindBy(name = "b_ok")
	private Button btnAlertOk;

	@FindBy(name = "b_yes")
	private Button btnErrorYes;

	// Personal Magic Link
	@FindBy(partialLinkText = "Personal Magic")
	private Link lnkPersonalMagic;

	// Click on PersonalMagicButton
	@FindBy(xpath = "//*[@value='New Personal Magic'][@name='b_PersonalMagic'] ")
	private Button btnNewPersonalMagic;

	// select check box TPS
	@FindBy(id = "selectedTPS")
	private Checkbox chkSelectTPS;

	// Cancel RES window //
	// select Cancellation Reason
	@FindBy(id = "resortName")
	private Listbox lstCancellationReason;

	// Contact name
	@FindBy(name = "contactName")
	private Textbox txtContactName;

	// click on button Re-instate
	@FindBy(name = "b_Re-instate")
	private Button btnReinstate;

	// Create adjustment button
	@FindBy(xpath = "//*[@value='Create Adjustment']")
	private Button btnCreateAdjustment;

	// set adjustment amount
	@FindBy(name = "adjustmentTO.amountStr")
	private Textbox txtadjustmentAmount;

	// select drop down
	@FindBy(name = "adjustmentTO.reasonCode")
	private Listbox txtAdjustmentReasonCode;

	// set adjustment comments
	@FindBy(name = "adjustmentTO.comments")
	private Textbox txtAdjustmentComments;

	// click on ok button
	@FindBy(xpath = "//*[@value='OK']")
	private Button btnAdjustmentOk;

	// Dept Due Date Extension //

	// go click on Number of days
	@FindBy(name = "radioSelection")
	private RadioGroup radNoDays;

	// provide extension Input
	@FindBy(name = "extentionInput")
	private Textbox txtExtInput;

	// provide extension Input
	@FindBy(name = "OK")
	private Button btnOK;

	@FindBy(name = "travelPlanTO.currentTravelPlanSegment.facilityOrderTOList[0].doryAccommodationsListForDisplay[0].doryTicketsList[0].selected")
	private Checkbox chkSelectAlldoryAccom;

	@FindBy(xpath = "//*[contains(@onclick,'validateKttwTicketCancel')]")
	private Button btnKTTWCancel;

	@FindBy(xpath = "//*[@value='OK']")
	private Button btnKTTWOk;

	@FindBy(partialLinkText = "KTTW Tickets")
	private Link lnkKTTWTickets;

	//*********************
	//** Build page area **
	//*********************

	// *********************
	// ** Build page area **
	// *********************
	/**
	 * 
	 * @summary Constructor to initialize the frame
	 * @version Created 09/10/2014
	 * @author Waightstill W Avery
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public ReservationPageContentFrame(WebDriver driver) {
		this.driver = driver;
		FrameHandler.findAndSwitchToFrame(driver, "contentFrame");
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);

		pageLoaded(btnExpandAll);
		btnExpandAll.syncVisible(driver);
		btnExpandAll.syncEnabled(driver);
		if (btnExpandAll.getAttribute("value").equalsIgnoreCase("ExpandAll")) {
			btnExpandAll.jsClick(driver);
			boolean expanded = false;
			int counter = 0;
			int maxTries = 10;
			do {
				Sleeper.sleep(500);
				try {
					if (btnExpandAll.getAttribute("value").equalsIgnoreCase(
							"CollapseAll")) {
						expanded = true;
					}
				} catch (Exception e) {
					initialize();
				}
			} while (!expanded && counter < maxTries);
			TestReporter.assertTrue(counter < maxTries,
					"The Reservation page was not expanded.");
		}
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnExpandAll);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				element);
	}

	public ReservationPageContentFrame initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	// *****************************************
	// *** Content Frame Interactions ***
	// *****************************************

	public void clickPaymentProcess() {
		List<WebElement> elementList = null;
		try{
			elementList = driver.findElements(By.id("PostBut"));
			System.out.println(elementList.size());
		}catch(NoSuchWindowException nswe){
			nswe.printStackTrace();
		}
		Iterator<WebElement> iterator = elementList.iterator();
		boolean processPaymentFound = false;

		while (iterator.hasNext()) {
			Element element = new ElementImpl(iterator.next());
			if (element.getAttribute("value").equalsIgnoreCase(
					"Process Payment")) {
				processPaymentFound = true;
				element.sendKeys(Keys.ENTER);
				PleaseWait.WaitForPleaseWait(driver);
				break;
			}
		}
		TestReporter.assertEquals(processPaymentFound, true,
				"The Process Payment button was not found\n");
	}

	public void takePayment(String scenario) {
		datatable.setVirtualtablePath(Datatable.PAYMENTUI_MASTER_DATA_PATH);
		datatable.setVirtualtablePage("PaymentUI");
		datatable.setVirtualtableScenario(scenario);

		String paymentType = datatable.getDataParameter("PaymentType");
		String paymentMethod = datatable.getDataParameter("PaymentMethod");
		String status = datatable.getDataParameter("Status");
		String delay = datatable.getDataParameter("Delay");
		@SuppressWarnings("unused")
		String cancel = datatable.getDataParameter("CancelButton");

		boolean incidentals = true;
		if (datatable.getDataParameter("Incidentals").equalsIgnoreCase("false")) {
			incidentals = false;
		}
		String amount = datatable.getDataParameter("Amount");
		String expiredCC = datatable.getDataParameter("ExpiredCC");
		String primaryGuestInfo = datatable
				.getDataParameter("UsePrimaryGuestInfo");
		String errorMessage = datatable
				.getDataParameter("ExpectedErrorMessage");

		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Apply Payment");

		PaymentUIWindow paymentUIWindow = new PaymentUIWindow(driver);
		TestReporter.assertTrue(paymentUIWindow.pageLoaded(),
				"The Payment UI window did not load");
		try {
			paymentUIWindow.takePayment(
					PaymentUIWindow.getpaymentTypeEnum(paymentType),
					PaymentUIWindow.getpaymentMethodEnum(paymentMethod),
					status, delay, incidentals, amount, expiredCC,
					primaryGuestInfo, errorMessage);
		} catch (Exception e1) {
			new Exception(e1);
		}
		cardNumber = paymentUIWindow.getCardNumber().replace("-", "");
		cardNumberMasked = paymentUIWindow.getCardNumberMasked().replace("-",
				"");
		System.out.println("Masked card number: " + cardNumberMasked);
		paymentAmount = paymentUIWindow.getPaymentAmount().replace("$", "");
		uiPaymentMethod = paymentUIWindow.getPaymentMethod();

		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Disney Reservation Entry and Management System");

		datatable.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);

		PleaseWait.WaitForPleaseWait(driver);

		// pageLoaded();
		new PageLoaded().isDomComplete(driver);
		captureReservationNumber();
	}

	/*
	 * Methods to capture and return the reservation number
	 */
	private void captureReservationNumber() {
		int timeout = WebDriverSetup.getDefaultTestTimeout();
		int loopCounter = 0;
		boolean resNumberNotFound = true;
		eleReservationNumber.highlight(driver);
		pageLoaded(tblReservationSummary);

		// reservationNumber = tblReservationSummary.getCellData(driver, row+1,
		// column);
		while (eleReservationNumber.getText().equalsIgnoreCase("")
				&& resNumberNotFound) {
			Sleeper.sleep(1000);
			loopCounter++;
			if (loopCounter == timeout + 1) {
				Assert.assertEquals(resNumberNotFound, false,
						"The reservation could not be found");
			}
		}
		;
		reservationNumber = eleReservationNumber.getText();
		setReservationNumber(reservationNumber);
	}

	public String captureReservationNumberPkg() {
		int timeout = WebDriverSetup.getDefaultTestTimeout();
		int loopCounter = 0;
		boolean resNumberNotFound = true;
		pageLoaded(tblReservationSummary);
		while (eleReservationNumber.getText().equalsIgnoreCase("")
				&& resNumberNotFound) {
			Sleeper.sleep(1000);
			loopCounter++;
			if (loopCounter == timeout + 1) {
				Assert.assertEquals(resNumberNotFound, false,
						"The reservation could not be found");
			}
		}
		;
		reservationNumber = eleReservationNumber.getText();
		setReservationNumber(reservationNumber);

		return reservationNumber;
	}

	public String getReservationNumber() {
		if (reservationNumber.isEmpty())
			captureReservationNumber();
		// if(reservationNumber.isEmpty()) captureReservationNumber();
		return reservationNumber;
	}

	private void setReservationNumber(String resNum) {
		reservationNumber = resNum;
	}

	public void clickDepositAndBillingSummary() {
		pageLoaded(lnkDepositAndBillingSummary);
		lnkDepositAndBillingSummary.syncVisible(driver);
		lnkDepositAndBillingSummary.highlight(driver);
		lnkDepositAndBillingSummary.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Disney Reservation");
		try{
			new PageLoaded().isDomComplete(driver);
		}catch(Exception e){
			Sleeper.sleep(2000);
		}
	}

	/**
	 * 
	 * @summary method to click process selected "[ -$601.88 ]"
	 * @version Created 03/14/2016
	 * @author SUnitha Bachala
	 * @param Datatable
	 *            scenario
	 * @throws NA
	 * @return NA
	 * 
	 */

	public void processselected() {
		pageLoaded(lnkDepositAndBillingSummary);
		processselectedtravelplan.highlight(driver);
		processselectedtravelplan.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void selectAndClickFolio() {
		pageLoaded(btnSelectFolio);
		// System.out.println();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		btnSelectFolio = new ButtonImpl(
				eleDepositAndBillingSummary.findElement(By
						.xpath("div[1]/table/tbody/tr[3]/td[6]/div/a")));
		btnSelectFolio.highlight(driver);
		btnSelectFolio.click();
	}

	public void processselected(String amountPaid) {
		String linkText = "-$" + amountPaid;
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		Link link = null;

		boolean linkFound = false;
		try {
			try {
				WindowHandler.waitUntilNumberOfWindowsAre(driver, 2, 3);
			} catch (TimeoutException te) {
			}

			WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
			WindowHandler.waitUntilWindowExistsTitleContains(driver,
					"Disney Reservation Entry and Management System");
			FrameHandler.findAndSwitchToFrame(driver, "contentFrame");
			ElementFactory.initElements(driver, this);
			for (WebElement links : driver.findElements(By
					.partialLinkText(linkText))) {
				link = new LinkImpl(links);
				if (link.syncVisible(driver, 1, false)) {
					linkFound = true;
					break;
				}
			}
		} catch (Exception e) {
			System.out.println();
			System.out.println();
		}
		TestReporter.assertTrue(linkFound, "The link with the amount paid ["
				+ linkText + "] was not found.");
		link.highlight(driver);
		link.jsClick(driver);
	}

	public void chkSelectAll() {
		pageLoaded(chkSelectAll);
		chkSelectAll.syncVisible(driver);
		chkSelectAll.click();
	}

	/**
	 * 
	 * @summary method to click on refund
	 * @version Created 03/14/2016
	 * @author SUnitha Bachala
	 * @param Datatable
	 *            scenario
	 * @throws NA
	 * @return NA
	 * 
	 */

	public void refund() {
		pageLoaded(lnkDepositAndBillingSummary);
		refundbtn.highlight(driver);
		refundbtn.click();

		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Post Refund Request");

	}

	/**
	 * 
	 * @summary method for entering the details for refundrequest
	 * @version Created 03/14/2016
	 * @author Sunitha Bachala
	 * @param Datatable
	 *            scenario
	 * @throws NA
	 * @return NA
	 * 
	 */

	public void refundrequest(String scenario) {

		datatable.setVirtualtablePage("Reservation");
		datatable.setVirtualtableScenario(scenario);

		String refundType = datatable.getDataParameter("AmountToRefund");
		String AmountType = datatable.getDataParameter("Amount");
		String refundreasonType = datatable.getDataParameter("RefundReason");
		String commentsType = datatable.getDataParameter("Comments");

		pageLoaded(Amount);
		Amount.syncVisible(driver);
		Amount.highlight(driver);
		Amount.safeSet(AmountType);

		pageLoaded(AmountToRefund);
		AmountToRefund.syncVisible(driver);
		AmountToRefund.highlight(driver);
		AmountToRefund.safeSet(refundType);

		pageLoaded(refundreason);
		refundreason.syncVisible(driver);
		refundreason.highlight(driver);
		refundreason.select(refundreasonType);

		pageLoaded(comments);
		comments.syncVisible(driver);
		comments.highlight(driver);
		comments.safeSet(commentsType);

		btnAlertOk.highlight(driver);
		btnAlertOk.click();
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Disney Reservation Entry and Management System");
		/*WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Disney Reservation Entry and Management System");
*/
	}

	/**
	 * 
	 * @summary Method to click the Nth payment button. Both Settlement and
	 *          PaymentProcess Buttons have the same ID. Index starting at 0 for
	 *          the first button.
	 * @version Created 02/15/2016
	 * @author Stagliano, Dennis
	 * @param index
	 *            - Number of Button to click
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void clickSettlementandPaymentProcessButtonByIndex(int index) {
		List<WebElement> paymentProcessButtonList = driver.findElements(By
				.id("PostBut"));
		int whichPaymentProcessButton = 0;
		for (WebElement processButton : paymentProcessButtonList) {
			if (whichPaymentProcessButton == index) {
				processButton.click();
				PleaseWait.WaitForPleaseWait(driver);
				break;
			}
			whichPaymentProcessButton++;
		}
	}

	public void windowHandle(int WindowNo, String title) {
		PleaseWait.WaitForPleaseWait(driver);
		WindowHandler.waitUntilNumberOfWindowsAre(driver, WindowNo);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, title);
	}

	public void clickMarketingPref() {
		btnMrkPrf.click();
		windowHandle(2, "PTOIQuestionForm");
	}

	public void clickemailAndAddressOut() {
		btnEmailOptOut.click();
		btnAddressOptOut.click();
		clickInfo();
		windowHandle(2, "PTOIQuestionForm");
		clickSave();
	}

	public void clickInfo() {
		btnInfo.click();
		windowHandle(3, "Post Transaction Opt-InInfo");
		btnInfoClose.highlight(driver);
		btnInfoClose.click();
	}

	public void clickSave() {
		btnPtoiSave.jsClick(driver);
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Disney Reservation Entry");
	}

	/**
	 * @summary MEthod to click confirmation LINk on Reservation Page content
	 *          frame
	 * @version Created 03-03-2016
	 * @author Pawan Chinagudaba
	 */

	public void clickConfirmation() {
		pageLoaded(lnkConfirmation);
		lnkConfirmation.highlight(driver);
		lnkConfirmation.jsClick(driver);
		/*
		 * WindowHandler.waitUntilNumberOfWindowsAre(driver,1);
		 * WindowHandler.waitUntilWindowExistsTitleContains(driver,
		 * "Rservation Page");
		 */
	}

	/**
	 * @summary MEthod to click Edit Link on Reservation Page content frame
	 * @version Created 03-03-2016
	 * @author Pawan Chinagudaba
	 */

	public void clickEditLink() {
		pageLoaded(lnkEditGuest);
		lnkEditGuest.highlight(driver);
		lnkEditGuest.click();
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Party");
	}

	public void clickReDiscovery() {
		pageLoaded(btnReDiscovery);
		btnReDiscovery.highlight(driver);
		btnReDiscovery.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void verifyPtoiHidden() {
		boolean visible = btnMarketingPreferencesRequired.syncVisible(driver,
				5, false);
		TestReporter
		.assertFalse(visible,
				"The PTOI Marketing Preferences button was not hidden as expected.");

	}

	/**
	 * Handle the PTOI popup should it appear
	 * 
	 * @param scenario
	 *            - scenario describing how to handle the popup
	 */
	/*
	 * public void handlePtoi(String scenario, String guestName) {
	 * datatable.setVirtualtablePage("Reservation");
	 * datatable.setVirtualtableScenario(scenario);
	 * 
	 * String emailOptIn = datatable.getDataParameter("EmailOptIn"); String
	 * mailOptIn = datatable.getDataParameter("MailOptIn"); String thirdParty =
	 * datatable.getDataParameter("ThirdParty"); String unableToConfirm =
	 * datatable.getDataParameter("UnableToConfirm"); String validateInfo =
	 * datatable.getDataParameter("ValidateInfo"); String validateSpiel =
	 * datatable.getDataParameter("ValidateSpiel"); String expectedSpiel =
	 * datatable.getDataParameter("Spiel"); String expectedInfo =
	 * datatable.getDataParameter("Info");
	 * 
	 * pageLoaded(btnMarketingPreferencesRequired);
	 * driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //
	 * Determine if the PTOI button exists which requires if
	 * (btnMarketingPreferencesRequired.syncVisible(driver)) {
	 * btnMarketingPreferencesRequired.jsClick(driver);
	 * PleaseWait.WaitForPleaseWait(driver); pageLoaded(btnPtoiUnableToConfirm);
	 * btnPtoiUnableToConfirm.highlight(driver);
	 * btnPtoiUnableToConfirm.syncVisible(driver);
	 * 
	 * // Determine if the spiel is to be validated if
	 * (validateSpiel.equalsIgnoreCase("true")) {
	 * ptoiValidateSpiel(expectedSpiel); }
	 * 
	 * // Determine if the info is to be validated if
	 * (validateInfo.equalsIgnoreCase("true")) {
	 * ptoiValidateInformation(expectedInfo); }
	 * 
	 * // Determine if Unable To Confirm if
	 * (unableToConfirm.equalsIgnoreCase("true")) { ptoiClickUnableToConfirm();
	 * ptoiVerifyMarketingPreferencesSaved(emailOptIn, mailOptIn, thirdParty,
	 * unableToConfirm); } // Determine if using a third party credit card else
	 * if (thirdParty.equalsIgnoreCase("true")) { ptoiClickThirdParty();
	 * ptoiVerifyMarketingPreferencesSaved(emailOptIn, mailOptIn, thirdParty,
	 * unableToConfirm); } // Determine if Marketing Preferences are to be
	 * entered else if (!mailOptIn.isEmpty()) {
	 * ptoiEnterMarketingPreferences(emailOptIn, mailOptIn);
	 * ptoiVerifyMarketingPreferencesSaved(emailOptIn, mailOptIn, thirdParty,
	 * unableToConfirm); } // For all other cases, handle the PTOI popup else {
	 * ptoiClickUnableToConfirm(); } } else {
	 * TestReporter.log("PTOI Marketing Preferences button is not visible."); }
	 * 
	 * driver.manage().timeouts().implicitlyWait(WebDriverSetup.
	 * getDefaultTestTimeout(), TimeUnit.SECONDS); }
	 */

	public void verifyNoPtoi() {
		boolean isFound = false;
		boolean positiveX = false;
		boolean positiveY = false;
		boolean positiveHeight = false;
		boolean positiveWidth = false;
		pageLoaded(btnMarketingPreferencesRequired);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// Determine if the PTOI button exists which requires
		positiveHeight = btnMarketingPreferencesRequired.getSize().height > 0;
		positiveWidth = btnMarketingPreferencesRequired.getSize().width > 0;
		positiveX = btnMarketingPreferencesRequired.getLocation().x > 0;
		positiveY = btnMarketingPreferencesRequired.getLocation().y > 0;
		// Commenting out below code as the PTOI button is visible according to
		// syncVisible, even though it is not on the page
		// if (btnMarketingPreferencesRequired.syncVisible(driver)) {
		// isFound = true;
		// }
		if (positiveHeight && positiveWidth && positiveX && positiveY) {
			isFound = true;
		}
		driver.manage()
		.timeouts()
		.implicitlyWait(WebDriverSetup.getDefaultTestTimeout(),
				TimeUnit.SECONDS);
		TestReporter.assertFalse(isFound,
				"The PTOI button was found when it was not expected.");
	}

	/**
	 * This method validates the text contained in the Spiel
	 * 
	 * @param expectedSpiel
	 *            - expected information contents
	 */
	private void ptoiValidateSpiel(String expectedSpiel) {
		boolean spielConfirmed = false;
		String actualSpiel = elePtoiForm.getText().trim().replace(" ", "");
		if (actualSpiel.contains(expectedSpiel.trim().replace(" ", ""))) {
			spielConfirmed = true;
		}

		TestReporter.assertTrue(spielConfirmed,
				"The expected spiel was not found. The actual spiel is ["
						+ actualSpiel + "].");
	}

	/**
	 * This method validates the text contained in the Information window
	 * 
	 * @param expectedInfo
	 */
	private void ptoiValidateInformation(String expectedInfo, String WindowName) {
		boolean infoConfirmed = false;
		String actualInfo;

		btnPtoiInfo.click();

		// Navigating to new window to close information window
		btnInfo.click();
		PleaseWait.WaitForPleaseWait(driver);
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 3);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Post Transaction Opt-InInfo");

		btnInfoClose.highlight(driver);
		btnInfoClose.click();

		// Added window handler to navigate back to previous window
		PleaseWait.WaitForPleaseWait(driver);
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, WindowName);

		pageLoaded(btnPtoiSave);
		actualInfo = driver.getPageSource().replace(" ", "");
		if (actualInfo.contains(expectedInfo.trim().replace(" ", ""))) {
			infoConfirmed = true;
		}

		TestReporter.assertTrue(infoConfirmed,
				"The expected info was not found. The actual spiel is ["
						+ actualInfo + "].");

		btnPtoiInfoClose.jsClick(driver);

	}

	/**
	 * This method clicks the Unable To Confirm button and syncs to the Summary
	 * page being loaded
	 */
	/*
	 * private void ptoiClickUnableToConfirm() {
	 * btnPtoiUnableToConfirm.highlight(driver);
	 * btnPtoiUnableToConfirm.jsClick(driver);
	 * WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
	 * WindowHandler.waitUntilWindowExistsTitleContains(driver,
	 * "Disney Reservation Entry and Management System");
	 * 
	 * }
	 *//**
	 * This method clicks the Third Party CC button and syncs to the Summary
	 * page bring loaded
	 */
	/*
	 * private void ptoiClickThirdParty() { btnPtoiThirdPartyCC.jsClick(driver);
	 * pageLoaded(btnPtoiEditMarketingPreferences); }
	 */
	/**
	 * This method enters and saves the marketing preferences
	 * 
	 * @param emailOptIn
	 *            - email marketing preference
	 * @param mailOptIn
	 *            - mail marketing preference
	 */
	/*
	 * private void ptoiEnterMarketingPreferences(String emailOptIn, String
	 * mailOptIn) { List<WebElement> optOuts =
	 * driver.findElements(By.xpath("//input[@value='Opt-Out']")); Element
	 * elePtoiEmailOptOut = new ElementImpl(optOuts.get(0)); Element
	 * elePtoiMailOptOut = new ElementImpl(optOuts.get(1)); List<WebElement>
	 * optIns = driver.findElements(By.xpath("//input[@value='Opt-In']"));
	 * Element elePtoiEmailOptIn = new ElementImpl(optIns.get(0)); Element
	 * elePtoiMailOptIn = new ElementImpl(optIns.get(1));
	 * 
	 * // Process the email marketing preferences if
	 * (emailOptIn.equalsIgnoreCase("true")) {
	 * TestReporter.assertTrue(elePtoiEmailOptIn.syncVisible(driver),
	 * "The email opt-in radio button was not visible.");
	 * elePtoiEmailOptIn.highlight(driver); elePtoiEmailOptIn.jsClick(driver); }
	 * else { TestReporter.assertTrue(elePtoiEmailOptOut.syncVisible(driver),
	 * "The email opt-in radio button was not visible.");
	 * elePtoiEmailOptOut.highlight(driver); elePtoiEmailOptOut.jsClick(driver);
	 * }
	 * 
	 * Sleeper.sleep(500);
	 * 
	 * // Process the mail marketing preferences if
	 * (mailOptIn.equalsIgnoreCase("true")) {
	 * TestReporter.assertTrue(elePtoiMailOptIn.syncVisible(driver),
	 * "The mail opt-in radio button was not visible.");
	 * elePtoiMailOptIn.highlight(driver); elePtoiMailOptIn.jsClick(driver); }
	 * else { TestReporter.assertTrue(elePtoiMailOptOut.syncVisible(driver),
	 * "The mail opt-out radio button was not visible.");
	 * elePtoiMailOptOut.highlight(driver); elePtoiMailOptOut.jsClick(driver); }
	 * 
	 * Sleeper.sleep(500);
	 * 
	 * ptoiClickSave(); }
	 * 
	 * private void ptoiClickSave() { pageLoaded(btnPtoiSave); //
	 * btnPtoiSave.syncEnabled(driver);
	 * driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	 * List<WebElement> saves =
	 * driver.findElements(By.xpath("//input[@value = 'Save']")); for
	 * (WebElement save : saves) { Element ele = new ElementImpl(save); if
	 * (ele.syncVisible(driver, 1, false)) { ele.highlight(driver);
	 * ele.jsClick(driver); } } initialize();
	 * pageLoaded(btnPtoiEditMarketingPreferences);
	 * driver.manage().timeouts().implicitlyWait
	 * (WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS); }
	 * 
	 * private void ptoiVerifyMarketingPreferencesSaved(String emailOptIn,
	 * String mailOptIn, String thirdParty, String unableToConfirm) {
	 * pageLoaded(btnPtoiEditMarketingPreferences);
	 * btnPtoiEditMarketingPreferences.syncVisible(driver);
	 * btnPtoiEditMarketingPreferences.jsClick(driver); initialize();
	 * 
	 * // Determine if third party. If so, no marketing preferences should be //
	 * entered if (thirdParty.equalsIgnoreCase("true")) {
	 * ptoiVerifyThirdParty(); } // Determine if unable to confirm. If so, both
	 * marketing preferences // should be opt-out else if
	 * (unableToConfirm.equalsIgnoreCase("true")) { ptoiVerifyUnableToConfirm();
	 * } // Otherwise, verify marketing preferences were saved else {
	 * ptoiVerifyOptInOptOut(emailOptIn, mailOptIn); } }
	 * 
	 * private void ptoiVerifyThirdParty() { Sleeper.sleep(4000);
	 * pageLoaded(btnPtoiThirdPartyCC); //
	 * driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	 * btnPtoiThirdPartyCC.syncVisible(driver);
	 * btnPtoiThirdPartyCC.highlight(driver); //
	 * driver.manage().timeouts().implicitlyWait
	 * (WebDriverSetup.getDefaultTestTimeout(), // TimeUnit.SECONDS);
	 * List<WebElement> optOuts =
	 * driver.findElements(By.xpath("//input[@value='Opt-Out']")); Element
	 * elePtoiEmailOptOut = new ElementImpl(optOuts.get(0)); Element
	 * elePtoiMailOptOut = new ElementImpl(optOuts.get(1)); List<WebElement>
	 * optIns = driver.findElements(By.xpath("//input[@value='Opt-In']"));
	 * Element elePtoiEmailOptIn = new ElementImpl(optIns.get(0)); Element
	 * elePtoiMailOptIn = new ElementImpl(optIns.get(1));
	 * 
	 * System.out.println("Validating preferences"); try {
	 * elePtoiEmailOptIn.highlight(driver);
	 * TestReporter.assertFalse(elePtoiEmailOptIn.isSelected(),
	 * "The email opt-in marketing preference was selected when no selection was expected."
	 * ); elePtoiEmailOptOut.highlight(driver);
	 * TestReporter.assertFalse(elePtoiEmailOptOut.isSelected(),
	 * "The email opt-out marketing preference was selected when no selection was expected."
	 * ); elePtoiMailOptIn.highlight(driver);
	 * TestReporter.assertFalse(elePtoiMailOptIn.isSelected(),
	 * "The mail opt-in marketing preference was selected when no selection was expected."
	 * ); elePtoiMailOptOut.highlight(driver);
	 * TestReporter.assertFalse(elePtoiMailOptOut.isSelected(),
	 * "The mail opt-out marketing preference was selected when no selection was expected."
	 * ); } catch (Exception e) { System.out.println("Grumble Grumble");
	 * 
	 * } ptoiClickThirdParty(); }
	 * 
	 * private void ptoiVerifyUnableToConfirm() { List<WebElement> optOuts =
	 * driver.findElements(By.xpath("//input[@value='Opt-Out']")); Element
	 * elePtoiEmailOptOut = new ElementImpl(optOuts.get(0)); Element
	 * elePtoiMailOptOut = new ElementImpl(optOuts.get(1));
	 * 
	 * System.out.println("Validating preferences"); try {
	 * TestReporter.assertTrue(elePtoiEmailOptOut.isSelected(),
	 * "The email opt-out marketing prefernce was not selected as expected.");
	 * TestReporter.assertTrue(elePtoiMailOptOut.isSelected(),
	 * "The mail opt-out marketing prefernce was not selected as expected."); }
	 * catch (Exception e) { System.out.println("Grumble Grumble");
	 * 
	 * } ptoiClickUnableToConfirm(); }
	 * 
	 * private void ptoiVerifyOptInOptOut(String emailOptIn, String mailOptIn) {
	 * List<WebElement> optOuts = null; do {
	 * System.out.println("Grabbing Opt-Outs."); Sleeper.sleep(100); optOuts =
	 * driver.findElements(By.xpath("//input[@value='Opt-Out']")); } while
	 * (optOuts.size() < 2); System.out.println("Opt-Outs found."); Element
	 * elePtoiEmailOptOut = new ElementImpl(optOuts.get(0)); Element
	 * elePtoiMailOptOut = new ElementImpl(optOuts.get(1)); List<WebElement>
	 * optIns = driver.findElements(By.xpath("//input[@value='Opt-In']"));
	 * Element elePtoiEmailOptIn = new ElementImpl(optIns.get(0)); Element
	 * elePtoiMailOptIn = new ElementImpl(optIns.get(1));
	 * 
	 * System.out.println("Validating preferences"); try { // Validate the email
	 * marketing preferences if (emailOptIn.equalsIgnoreCase("true")) {
	 * TestReporter.assertTrue(elePtoiEmailOptIn.isSelected(),
	 * "The email opt-in marketing preference was not selected as expected."); }
	 * else { TestReporter.assertTrue(elePtoiEmailOptOut.isSelected(),
	 * "The email opt-out marketing preference was not selected as expected.");
	 * }
	 * 
	 * // Validate the mail marketing preferences if
	 * (mailOptIn.equalsIgnoreCase("true")) {
	 * TestReporter.assertTrue(elePtoiMailOptIn.isSelected(),
	 * "The mail opt-in marketing preference was not selected as expected."); }
	 * else { TestReporter.assertTrue(elePtoiMailOptOut.isSelected(),
	 * "The mail opt-out marketing preference was not selected as expected."); }
	 * } catch (Exception e) { System.out.println("Grumble Grumble"); }
	 * 
	 * ptoiClickSave(); }
	 * 
	 * /**
	 * 
	 * @summary: Method to click on MarketingPreferencesrEquired.
	 * 
	 * @author : Praveen namburi, @version : Created 1-12-2016.
	 *//*
	 * public void clickMarketingPreferences() {
	 * pageLoaded(btnMarketingPreferencesRequired);
	 * btnMarketingPreferencesRequired.syncVisible(driver);
	 * btnMarketingPreferencesRequired.jsClick(driver); }
	 */

	public void clickMarketingPreferences() {
		pageLoaded(MarketingPreferencesRequired);
		MarketingPreferencesRequired.syncVisible(driver);
		MarketingPreferencesRequired.jsClick(driver);
	}

	public void chkselectAll() {
		pageLoaded(chkselectAll);
		chkselectAll.syncVisible(driver);
		chkselectAll.jsToggle(driver);
	}

	/*
	 * public void SetsettlementMethod() { lnkSubmit.click();
	 * 
	 * }
	 */

	/**
	 * @param
	 */

	public void applySettlement(String scenario) {

		System.out.println(scenario);
		datatable.setVirtualtablePath(Datatable.PAYMENTUI_MASTER_DATA_PATH);
		datatable.setVirtualtablePage("PaymentUI");
		datatable.setVirtualtableScenario(scenario);

		String paymentType = datatable.getDataParameter("PaymentType");
		String paymentMethod = datatable.getDataParameter("PaymentMethod");
		String status = datatable.getDataParameter("Status");
		String delay = datatable.getDataParameter("Delay");
		String thirdParty = datatable.getDataParameter("ThirdPartySettlement");

		pageLoaded();

		// clickSetGuarantee();
		clickSettlementandPaymentProcessButtonByIndex(1);

		Sleeper.sleep(3000);
		String parentWindow = driver.getTitle();
		// Loop until 2 window are present
		do {
			Sleeper.sleep(500);
		} while (driver.getWindowHandles().size() < 2);

		// Loop through each window until the Settlement UI can be identified
		boolean settleUiFound = false;
		do {
			Sleeper.sleep(500);
			for (String handle : driver.getWindowHandles()) {
				if (driver.switchTo().window(handle).getTitle()
						.contains("Set Settlement")) {
					settleUiFound = true;
					break;
				}
			}
		} while (!settleUiFound);

		SettlementUIWindow settlementWindow = new SettlementUIWindow(driver);
		settlementWindow.setSettlementGuarantee(paymentType, paymentMethod,
				status, delay, thirdParty);
		new WindowHandler().swapToWindow(driver, parentWindow);

		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Set Settlement");

		boolean errorFound = false;
		if (eleErrorHeader.syncVisible(driver, 5, false)) {
			errorFound = true;
			TestReporter.assertFalse(
					errorFound,
					"An error occurred launching the Settlement UI. Error text follows: ["
							+ driver.findElement(
									By.id("pmtErrorModalPanelContentDiv"))
									.getText() + "].");
		}

		/*
		 * SettlementUIWindow settlementWindow = new SettlementUIWindow(driver);
		 * settlementWindow.setSettlementGuarantee(paymentType, paymentMethod,
		 * status, delay, thirdParty);
		 */
		// WindowHandler.waitUntilWindowExistsTitleContains(driver, "SEServer");

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

	public void setSettlement(String scenario) {

		System.out.println(scenario);
		datatable.setVirtualtablePath(Datatable.PAYMENTUI_MASTER_DATA_PATH);
		datatable.setVirtualtablePage("PaymentUI");
		datatable.setVirtualtableScenario(scenario);

		String paymentType = datatable.getDataParameter("PaymentType");
		String paymentMethod = datatable.getDataParameter("PaymentMethod");
		String status = datatable.getDataParameter("Status");
		String delay = datatable.getDataParameter("Delay");
		String thirdParty = datatable.getDataParameter("ThirdPartySettlement");

		pageLoaded();
		clickSettlementandPaymentProcessButtonByIndex(1);
		System.out.println("Count : "
				+ WindowHandler.waitUntilNumberOfWindowsAre(driver, 2));
		// WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		System.out.println("Window : "
				+ WindowHandler.waitUntilWindowExistsTitleContains(driver,
						"Set Settlement"));

		/*
		 * boolean errorFound = false; if(eleErrorHeader.syncVisible(driver, 5,
		 * false)){ errorFound = true; TestReporter.assertFalse(errorFound,
		 * "An error occurred launching the Settlement UI. Error text follows: ["
		 * +
		 * driver.findElement(By.id("pmtErrorModalPanelContentDiv")).getText()+
		 * "]." ); }
		 */

		SettlementUIWindow settlementWindow = new SettlementUIWindow(driver);
		settlementWindow.setSettlementGuarantee(paymentType, paymentMethod,
				status, delay, thirdParty);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Disney Reservation");
		btnExpandAll.highlight(driver);

	}

	public void btnEditMarketPreferences() {
		pageLoaded(btnPtoiEditMarketingPreferences);
		btnPtoiEditMarketingPreferences.syncVisible(driver);
		btnPtoiEditMarketingPreferences.click();

	}

	/*
	 * public void windowHandle(int WindowNo, String title){
	 * PleaseWait.WaitForPleaseWait(driver);
	 * WindowHandler.waitUntilNumberOfWindowsAre(driver, WindowNo);
	 * WindowHandler.waitUntilWindowExistsTitleContains(driver,title); }
	 * 
	 * public void clickMarketingPref(){ btnMrkPrf.click();
	 * windowHandle(2,"PTOIQuestionForm"); }
	 * 
	 * public void clickemailAndAddressOut(){ btnEmailOptOut.click();
	 * btnAddressOptOut.click(); clickInfo();
	 * windowHandle(2,"PTOIQuestionForm"); clickSave(); }
	 * 
	 * public void clickInfo(){ btnInfo.click();
	 * windowHandle(3,"Post Transaction Opt-InInfo");
	 * btnInfoClose.highlight(driver); btnInfoClose.click(); }
	 * 
	 * public void clickSave(){ btnPtoiSave.click();
	 * WindowHandler.waitUntilNumberOfWindowsAre(driver,1);
	 * WindowHandler.waitUntilWindowExistsTitleContains(driver,
	 * "Disney Reservation Entry"); }
	 */

	/*
	 * public void verifyPtoiHidden(){ boolean visible =
	 * btnMarketingPreferencesRequired.syncVisible(driver, 5, false);
	 * TestReporter.assertFalse(visible,
	 * "The PTOI Marketing Preferences button was not hidden as expected."); }
	 */
	/**
	 * Handle the PTOI popup should it appear
	 * 
	 * @param scenario
	 *            - scenario describing how to handle the popup
	 */
	public void handlePtoi(String scenario, String guestName) {
		datatable.setVirtualtablePage("Reservation");
		datatable.setVirtualtableScenario(scenario);

		String emailOptIn = datatable.getDataParameter("EmailOptIn");
		String mailOptIn = datatable.getDataParameter("MailOptIn");
		String thirdParty = datatable.getDataParameter("ThirdParty");
		String unableToConfirm = datatable.getDataParameter("UnableToConfirm");
		String validateInfo = datatable.getDataParameter("ValidateInfo");
		String validateSpiel = datatable.getDataParameter("ValidateSpiel");
		String expectedSpiel = datatable.getDataParameter("Spiel");
		String expectedInfo = datatable.getDataParameter("Info");
		String WindowName = datatable.getDataParameter("WindowName");

		pageLoaded(btnMarketingPreferencesRequired);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Determine if the PTOI button exists which requires
		if (btnMarketingPreferencesRequired.syncVisible(driver)) {
			btnMarketingPreferencesRequired.click();

			// Added Window Handler
			PleaseWait.WaitForPleaseWait(driver);
			WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
			WindowHandler
			.waitUntilWindowExistsTitleContains(driver, WindowName);

			btnPtoiUnableToConfirm.highlight(driver);
			btnPtoiUnableToConfirm.syncVisible(driver);

			// Determine if the spiel is to be validated
			if (validateSpiel.equalsIgnoreCase("true")) {
				ptoiValidateSpiel(expectedSpiel);
			}

			// Determine if the info is to be validated
			if (validateInfo.equalsIgnoreCase("true")) {
				ptoiValidateInformation(expectedInfo, WindowName);
			}

			// Determine if Unable To Confirm
			if (unableToConfirm.equalsIgnoreCase("true")) {
				ptoiClickUnableToConfirm();
				ptoiVerifyMarketingPreferencesSaved(emailOptIn, mailOptIn,
						thirdParty, unableToConfirm, WindowName);
			}

			// Determine if using a third party credit card
			else if (thirdParty.equalsIgnoreCase("true")) {
				ptoiClickThirdParty();
				ptoiVerifyMarketingPreferencesSaved(emailOptIn, mailOptIn,
						thirdParty, unableToConfirm, WindowName);
			}
			// Determine if Marketing Preferences are to be entered
			else if (!mailOptIn.isEmpty()) {
				ptoiEnterMarketingPreferences(emailOptIn, mailOptIn);
				ptoiVerifyMarketingPreferencesSaved(emailOptIn, mailOptIn,
						thirdParty, unableToConfirm, WindowName);
			}
			// For all other cases, handle the PTOI popup
			else {
				ptoiClickUnableToConfirm();
			}
		} else {
			TestReporter
			.log("PTOI Marketing Preferences button is not visible.");
		}

		driver.manage()
		.timeouts()
		.implicitlyWait(WebDriverSetup.getDefaultTestTimeout(),
				TimeUnit.SECONDS);
	}

	/*
	 * public void verifyNoPtoi() { boolean isFound = false;
	 * pageLoaded(btnMarketingPreferencesRequired);
	 * driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //
	 * Determine if the PTOI button exists which requires if
	 * (btnMarketingPreferencesRequired.syncVisible(driver)) { isFound = true; }
	 * driver
	 * .manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout
	 * (), TimeUnit.SECONDS); TestReporter.assertFalse(isFound,
	 * "The PTOI button was found when it was not expected."); }
	 */
	/**
	 * This method validates the text contained in the Spiel
	 * 
	 * @param expectedSpiel
	 *            - expected information contents
	 */
	/*
	 * private void ptoiValidateSpiel(String expectedSpiel) { boolean
	 * spielConfirmed = false; String actualSpiel =
	 * elePtoiForm.getText().trim().replace(" ", ""); if
	 * (actualSpiel.contains(expectedSpiel.trim().replace(" ", ""))) {
	 * spielConfirmed = true; }
	 * 
	 * TestReporter.assertTrue(spielConfirmed,
	 * "The expected spiel was not found. The actual spiel is [" + actualSpiel +
	 * "]."); }
	 */
	/**
	 * This method validates the text contained in the Information window
	 * 
	 * @param expectedInfo
	 */
	/*
	 * private void ptoiValidateInformation(String expectedInfo) { boolean
	 * infoConfirmed = false; String actualInfo;
	 * 
	 * btnPtoiInfo.jsClick(driver); pageLoaded(btnPtoiSave); actualInfo =
	 * driver.getPageSource().replace(" ", ""); if
	 * (actualInfo.contains(expectedInfo.trim().replace(" ", ""))) {
	 * infoConfirmed = true; }
	 * 
	 * TestReporter.assertTrue(infoConfirmed,
	 * "The expected info was not found. The actual spiel is [" + actualInfo +
	 * "].");
	 * 
	 * btnPtoiInfoClose.jsClick(driver); }
	 */
	/**
	 * This method clicks the Unable To Confirm button and syncs to the Summary
	 * page being loaded
	 */
	private void ptoiClickUnableToConfirm() {
		btnPtoiUnableToConfirm.highlight(driver);
		btnPtoiUnableToConfirm.jsClick(driver);
		// Added Window Handler
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Disney Reservation Entry and Management System");
	}

	/**
	 * This method clicks the Third Party CC button and syncs to the Summary
	 * page bring loaded
	 */
	private void ptoiClickThirdParty() {
		btnPtoiThirdPartyCC.highlight(driver);
		btnPtoiThirdPartyCC.jsClick(driver);
		/* pageLoaded(btnPtoiEditMarketingPreferences); */
		// Added Window Handler
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Disney Reservation Entry and Management System");
	}

	/**
	 * This method enters and saves the marketing preferences
	 * 
	 * @param emailOptIn
	 *            - email marketing preference
	 * @param mailOptIn
	 *            - mail marketing preference
	 */
	private void ptoiEnterMarketingPreferences(String emailOptIn,
			String mailOptIn) {
		List<WebElement> optOuts = driver.findElements(By
				.xpath("//input[@value='Opt-Out']"));
		Element elePtoiEmailOptOut = new ElementImpl(optOuts.get(0));
		Element elePtoiMailOptOut = new ElementImpl(optOuts.get(1));
		List<WebElement> optIns = driver.findElements(By
				.xpath("//input[@value='Opt-In']"));
		Element elePtoiEmailOptIn = new ElementImpl(optIns.get(0));
		Element elePtoiMailOptIn = new ElementImpl(optIns.get(1));
		boolean isOptOutEnabled = false;
		boolean isOptInEnabled = false;
		// Process the email marketing preferences
		/*
		 * Determine if the guest is to opt-in. If so, determine if the opt-in
		 * option is enabled. If it is not, then the test must fail.
		 */
		if (emailOptIn.equalsIgnoreCase("true")) {
			TestReporter.assertTrue(elePtoiEmailOptIn.syncVisible(driver),
					"The email opt-in radio button was not visible.");
			elePtoiEmailOptIn.highlight(driver);
			if (elePtoiEmailOptIn.isEnabled()) {
				elePtoiEmailOptIn.jsClick(driver);
			} else {
				// isOptInEnabled will be false here if not enabled and the test
				// will fail
				TestReporter
				.assertTrue(isOptInEnabled,
						"The Specified email Optin is in not in enabled mode!!");
			}
		}
		/*
		 * Determine if no email is defined for the guest, hence no marketing
		 * preferences should be enabled. If they are enabled, then the test
		 * must fail.
		 */
		else if (emailOptIn.equalsIgnoreCase("noEmail")) {
			if (elePtoiEmailOptOut.isEnabled()) {
				isOptOutEnabled = true;
			}
			if (elePtoiEmailOptIn.isEnabled()) {
				isOptInEnabled = true;
			}
			TestReporter
			.assertFalse(
					isOptOutEnabled,
					"The marketing preference opt-out is not diabled as expected for a guest with no email.");
			TestReporter
			.assertFalse(
					isOptInEnabled,
					"The marketing preference opt-in ise not diabled as expected for a guest with no email.");
		}
		/*
		 * Determine if the guest is to opt-out. If so, determine if the opt-out
		 * option is enabled. If it is not, then the test must fail.
		 */
		else {
			TestReporter.assertTrue(elePtoiEmailOptOut.syncVisible(driver),
					"The email opt-in radio button was not visible.");
			elePtoiEmailOptOut.highlight(driver);
			if (elePtoiEmailOptOut.isEnabled()) {
				elePtoiEmailOptOut.jsClick(driver);
			} else {
				// isOptOutEnabled will be false here if not enabled and the
				// test will fail
				TestReporter
				.assertTrue(isOptOutEnabled,
						"The Specified email OptOut is in not in enabled mode!!");
			}
		}

		Sleeper.sleep(500);

		// Process the mail marketing preferences
		if (mailOptIn.equalsIgnoreCase("true")) {
			TestReporter.assertTrue(elePtoiMailOptIn.syncVisible(driver),
					"The mail opt-in radio button was not visible.");
			elePtoiMailOptIn.highlight(driver);
			// elePtoiMailOptIn.jsClick(driver);
			if (elePtoiMailOptIn.isEnabled()) {
				elePtoiMailOptIn.jsClick(driver);
			} else {
				TestReporter
				.log("The Specified mail OptIn is in not in enabled mode!!");
			}
		} else {
			TestReporter.assertTrue(elePtoiMailOptOut.syncVisible(driver),
					"The mail opt-out radio button was not visible.");
			elePtoiMailOptOut.highlight(driver);
			// elePtoiMailOptOut.jsClick(driver);
			if (elePtoiMailOptOut.isEnabled()) {
				elePtoiMailOptOut.jsClick(driver);
			} else {
				TestReporter
				.log("The Specified mail OptOut is in not in enabled mode!!");
			}
		}

		Sleeper.sleep(500);

		ptoiClickSave();
	}

	private void ptoiClickSave() {
		pageLoaded(btnPtoiSave);
		// btnPtoiSave.syncEnabled(driver);
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		List<WebElement> saves = driver.findElements(By
				.xpath("//input[@value = 'Save']"));
		for (WebElement save : saves) {
			Element ele = new ElementImpl(save);
			if (ele.syncVisible(driver, 1, false)) {
				ele.highlight(driver);
				// ele.jsClick(driver);
				ele.click();
				break;
			}
		}
		PleaseWait.WaitForPleaseWait(driver);
		// Added window handler to load element -
		// btnPtoiEditMarketingPreferences
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Disney");
		initialize();
		pageLoaded(btnPtoiEditMarketingPreferences);
		driver.manage()
		.timeouts()
		.implicitlyWait(WebDriverSetup.getDefaultTestTimeout(),
				TimeUnit.SECONDS);
	}

	private void ptoiVerifyMarketingPreferencesSaved(String emailOptIn,
			String mailOptIn, String thirdParty, String unableToConfirm,
			String WindowName) {
		initialize();
		btnPtoiEditMarketingPreferences.syncVisible(driver);
		btnPtoiEditMarketingPreferences.click();

		// Added Window Handler
		PleaseWait.WaitForPleaseWait(driver);
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, WindowName);

		pageLoaded(btnPtoiUnableToConfirm);
		btnPtoiUnableToConfirm.highlight(driver);
		btnPtoiUnableToConfirm.syncVisible(driver);

		// Determine if third party. If so, no marketing preferences should be
		// entered
		if (thirdParty.equalsIgnoreCase("true")) {
			ptoiVerifyThirdParty();
		}
		// Determine if unable to confirm. If so, both marketing preferences
		// should be opt-out
		else if (unableToConfirm.equalsIgnoreCase("true")) {
			ptoiVerifyUnableToConfirm();
		}
		// Otherwise, verify marketing preferences were saved
		else {
			ptoiVerifyOptInOptOut(emailOptIn, mailOptIn);
		}
	}

	private void ptoiVerifyThirdParty() {
		Sleeper.sleep(4000);
		pageLoaded(btnPtoiThirdPartyCC);
		// driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		btnPtoiThirdPartyCC.syncVisible(driver);
		btnPtoiThirdPartyCC.highlight(driver);
		// driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(),
		// TimeUnit.SECONDS);
		List<WebElement> optOuts = driver.findElements(By
				.xpath("//input[@value='Opt-Out']"));
		Element elePtoiEmailOptOut = new ElementImpl(optOuts.get(0));
		Element elePtoiMailOptOut = new ElementImpl(optOuts.get(1));
		List<WebElement> optIns = driver.findElements(By
				.xpath("//input[@value='Opt-In']"));
		Element elePtoiEmailOptIn = new ElementImpl(optIns.get(0));
		Element elePtoiMailOptIn = new ElementImpl(optIns.get(1));

		System.out.println("Validating preferences");
		try {
			elePtoiEmailOptIn.highlight(driver);
			TestReporter
			.assertFalse(
					elePtoiEmailOptIn.isSelected(),
					"The email opt-in marketing preference was selected when no selection was expected.");
			elePtoiEmailOptOut.highlight(driver);
			TestReporter
			.assertFalse(
					elePtoiEmailOptOut.isSelected(),
					"The email opt-out marketing preference was selected when no selection was expected.");
			elePtoiMailOptIn.highlight(driver);
			TestReporter
			.assertFalse(
					elePtoiMailOptIn.isSelected(),
					"The mail opt-in marketing preference was selected when no selection was expected.");
			elePtoiMailOptOut.highlight(driver);
			TestReporter
			.assertFalse(
					elePtoiMailOptOut.isSelected(),
					"The mail opt-out marketing preference was selected when no selection was expected.");
		} catch (Exception e) {
			System.out.println("Grumble Grumble");

		}
		ptoiClickThirdParty();
	}

	private void ptoiVerifyUnableToConfirm() {
		List<WebElement> optOuts = driver.findElements(By
				.xpath("//input[@value='Opt-Out']"));
		Element elePtoiEmailOptOut = new ElementImpl(optOuts.get(0));
		Element elePtoiMailOptOut = new ElementImpl(optOuts.get(1));

		System.out.println("Validating preferences");
		try {
			TestReporter
			.assertTrue(elePtoiEmailOptOut.isSelected(),
					"The email opt-out marketing prefernce was not selected as expected.");
			TestReporter
			.assertTrue(elePtoiMailOptOut.isSelected(),
					"The mail opt-out marketing prefernce was not selected as expected.");
		} catch (Exception e) {
			System.out.println("Grumble Grumble");

		}
		ptoiClickUnableToConfirm();
	}

	private void ptoiVerifyOptInOptOut(String emailOptIn, String mailOptIn) {
		List<WebElement> optOuts = null;
		do {
			System.out.println("Grabbing Opt-Outs.");
			Sleeper.sleep(100);
			optOuts = driver
					.findElements(By.xpath("//input[@value='Opt-Out']"));
		} while (optOuts.size() < 2);
		System.out.println("Opt-Outs found.");
		Element elePtoiEmailOptOut = new ElementImpl(optOuts.get(0));
		Element elePtoiMailOptOut = new ElementImpl(optOuts.get(1));
		List<WebElement> optIns = driver.findElements(By
				.xpath("//input[@value='Opt-In']"));
		Element elePtoiEmailOptIn = new ElementImpl(optIns.get(0));
		Element elePtoiMailOptIn = new ElementImpl(optIns.get(1));
		boolean isOptOutEnabled = false;
		boolean isOptInEnabled = false;
		System.out.println("Validating preferences");
		try {
			// Validate the email marketing preferences
			if (emailOptIn.equalsIgnoreCase("true")) {
				if (elePtoiEmailOptIn.isEnabled()) {
					TestReporter
					.assertTrue(elePtoiEmailOptIn.isSelected(),
							"The email opt-in marketing preference was not selected as expected.");
				} else {
					TestReporter
					.log("The email opt-in marketing preference is available in disabled mode!!");
				}
				// Validate the No email marketing preferences
			} else if (emailOptIn.equalsIgnoreCase("noEmail")) {
				if (elePtoiEmailOptOut.isEnabled()) {
					isOptOutEnabled = true;
				}
				if (elePtoiEmailOptIn.isEnabled()) {
					isOptInEnabled = true;
				}
				TestReporter
				.assertFalse(
						isOptOutEnabled,
						"The marketing preference opt-out is not diabled as expected for a guest with no email.");
				TestReporter
				.assertFalse(
						isOptInEnabled,
						"The marketing preference opt-in ise not diabled as expected for a guest with no email.");
			} else {
				if (elePtoiEmailOptOut.isEnabled()) {
					TestReporter
					.assertTrue(elePtoiEmailOptOut.isSelected(),
							"The email opt-out marketing preference was not selected as expected.");
				} else {
					TestReporter
					.log("The email opt-out marketing preference is available in disabled mode!!");
				}
			}

			// Validate the mail marketing preferences
			if (mailOptIn.equalsIgnoreCase("true")) {
				if (elePtoiMailOptIn.isEnabled()) {
					TestReporter
					.assertTrue(elePtoiMailOptIn.isSelected(),
							"The mail opt-in marketing preference was not selected as expected.");
				} else {
					TestReporter
					.log("The mail opt-in marketing preference is available in disabled mode!!");
				}
			} else {
				if (elePtoiMailOptOut.isEnabled()) {
					TestReporter
					.assertTrue(elePtoiMailOptOut.isSelected(),
							"The mail opt-out marketing preference was not selected as expected.");
				} else {
					TestReporter
					.log("The mail opt-out marketing preference is available in disabled mode!!");
				}
			}
		} catch (Exception e) {
			System.out.println("Grumble Grumble");
		}

		ptoiClickSave();
	}

	public void launchMagicalCelebrations() {
		initialize();
		pageLoaded(lnkCelebrations);
		lnkCelebrations.syncVisible(driver);
		lnkCelebrations.jsClick(driver);

		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Magical Celebrations");

		Sleeper.sleep(3000);

		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Magical Celebrations");

		new PageLoaded().isDomComplete(driver);
		new PageLoaded().isDomInteractive(driver);
	}

	public void validateNumberofCelebrations(int numberOfCelebrations) {
		String expectedText = "Celebrations ("
				+ String.valueOf(numberOfCelebrations) + ")";
		lnkCelebrations = new LinkImpl(driver.findElement(By
				.partialLinkText(expectedText)));
		pageLoaded(lnkCelebrations);
		lnkCelebrations.syncVisible(driver);
		TestReporter.assertEquals(
				expectedText,
				lnkCelebrations.getText(),
				"The expected text [" + expectedText
				+ "] did not match the actual text ["
				+ lnkCelebrations.getText() + "].");
	}

	public void validateNumberofCelebrations(String numberOfCelebrations) {
		validateNumberofCelebrations(Integer.parseInt(numberOfCelebrations));
	}

	/**
	 * @summary: method to Personal magic link
	 * @author: Sowmya ch
	 */
	// Method to click personal magic link
	public void clickPersonalMagic() {
		pageLoaded(lnkPersonalMagic);
		lnkPersonalMagic.highlight(driver);
		lnkPersonalMagic.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	// Method to click New PersonalMagic button
	public void clickPersonalMagicBtn() {
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Disney Reservation");
		
		String handleBefore = driver.getWindowHandle();
		boolean inDreams = true;
		pageLoaded(btnNewPersonalMagic);
		int attempts = 0;
		
//		btnNewPersonalMagic.click();
		btnNewPersonalMagic.sendKeys(Keys.ENTER);
		Sleeper.sleep(5000);
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		try{
			new PageLoaded().isDomComplete(driver);
		}catch(Exception e){
			Sleeper.sleep(5000);
		}
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		do{			
			attempts++;
			System.out.println("Do loop " +  attempts);
			Sleeper.sleep(1000);
			for(String handle : driver.getWindowHandles()){
				System.out.println("For loop " +  handle);
				
				if(!handle.toString().equals(handleBefore.toString())){
					System.out.println("Attempt to swap to Window " +  handle);
					driver.switchTo().window(handle);
					System.out.println("Swapped to Window " +  handle);
					
					try{
						if(new ElementImpl(driver.findElement(By.name("USER"))).syncVisible(driver, 1, false)) {

							System.out.println("Found sign in window");
							driver.close();
							System.out.println("Closed Sign in Window");
						}else break;
					}catch(NoSuchElementException throwAway){}
					
				}
			}
			

			System.out.println("Check for Misc Sales");
			try{
				if(new ElementImpl(driver.findElement(By.id("mainForm:departureDateButtonInput"))).syncVisible(driver, 3, false)) inDreams = false;
			}catch(NoSuchElementException throwAway){}
			
		}while(inDreams && attempts < 10);
		if (attempts >=10) throw new RuntimeException("Failed to swap to Misc Sales"); 
		driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT, TimeUnit.SECONDS);
	}

	// Clicking on InternalComments NEW button
	public void clickNew() {
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Disney Reservation");
		FrameHandler.findAndSwitchToFrame(driver, "contentFrame");
		try {
			new PageLoaded().isDomComplete(driver);
		} catch (Exception e) {
			Sleeper.sleep(2000);
		}
		// Clicking on internal comments New
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		List<WebElement> buttons = driver.findElements(By
				.xpath("//input[@type='button'][@value='New']"));
		for (WebElement inputButton : buttons) {
			if (inputButton.getAttribute("onclick").contains(
					"loadPopulateInternalCommentsAction()")) {
				Button btn = new ButtonImpl(inputButton);
				pageLoaded(btn);
				btn.syncVisible(driver);
				// inputButton.click();
				inputButton.sendKeys(Keys.ENTER);
				break;
			}
		}
		driver.manage().timeouts()
		.implicitlyWait(Constants.ELEMENT_TIMEOUT, TimeUnit.SECONDS);
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Internal Comments");
	}

	public void clickAdd() {
		pageLoaded(btnInternalCommentsAdd);
		btnInternalCommentsAdd.highlight(driver);
		btnInternalCommentsAdd.click();
	}

	public void clickOk() {
		pageLoaded(btnInternalCommentsOk);
		btnInternalCommentsOk.highlight(driver);
		btnInternalCommentsOk.jsClick(driver);
	}

	/**
	 * @summary Method to add internal comments
	 * @version Created 03/15/2016
	 * @author Lalitha Banda
	 * @param Input
	 *            Comment type String
	 * @throws NA
	 * @return NA
	 */

	// Add Comments - Internal comments
	public void addInternalComments(String scenario) {

		datatable.setVirtualtablePage("Reservation");
		datatable.setVirtualtableScenario(scenario);

		clickNew();
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Internal Comments");
		driver.findElement(By.tagName("TEXTAREA")).sendKeys(
				datatable.getDataParameter("InternalComments"));

		clickAdd();
		clickOk();
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Disney Reservation Entry and Management System");
	}

	/**
	 * @summary Method to click Select TPS check box - Reservation summary
	 * @version Created 03/17/2016
	 * @author Lalitha Banda
	 * @param NA
	 * @throws NA
	 * @return NA
	 */

	// Clicking on TPS check box - Reservation Summary
	public void select_TPS() {
		pageLoaded(chkSelectTPS);
		chkSelectTPS.highlight(driver);
		chkSelectTPS.jsToggle(driver);
	}

	/**
	 * @summary: method to click create adjustment
	 * @author: Sabitha Adama
	 */
	public void clickCreateAdjustment() {
		pageLoaded(btnCreateAdjustment);
		btnCreateAdjustment.highlight(driver);
		btnCreateAdjustment.click();
		PleaseWait.WaitForPleaseWait(driver);
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Adjustment");
	}

	/**
	 * @summary Method to click Re instate - Reservation summary
	 * @version Created 03/17/2016
	 * @author Lalitha Banda
	 * @param NA
	 * @throws NA
	 * @return NA
	 */
	// Clicking on Button Re- instate - Reservation Summary
	public void click_Reinstate() {
		pageLoaded(btnReinstate);
		btnReinstate.highlight(driver);
		btnReinstate.click();
		PleaseWait.WaitForPleaseWait(driver);
	}

	/**
	 * @summary: method to set adjustment details
	 * @author: Sabitha Adama
	 */
	public void adjustmentDetails(String scenario) {
		datatable.setVirtualtablePage("Adjustment_Info");
		datatable.setVirtualtableScenario(scenario);

		String adjustment_Amount = datatable.getDataParameter("Amount");
		String adjustment_ReasonCode = datatable
				.getDataParameter("Reason_code");
		String adjustment_Comments = datatable.getDataParameter("Comments");

		txtadjustmentAmount.set(adjustment_Amount);
		txtAdjustmentReasonCode.select(adjustment_ReasonCode);
		txtAdjustmentComments.set(adjustment_Comments);

		pageLoaded(btnAdjustmentOk);
		btnAdjustmentOk.highlight(driver);
		btnAdjustmentOk.click();
		PleaseWait.WaitForPleaseWait(driver);
	}

	public String getPaymentAmount() {
		return paymentAmount;
	}

	/**
	 * This method reads-in the data for a payment process, launches the Payment
	 * UI window, and processes payment
	 * 
	 * @author Waightstill W Avery
	 * @version 11/06/2015 - original
	 * @param scenario
	 *            - Payment UI data scenario
	 * @throws Exception
	 */
	public void splitPayments(String scenario) {
		System.out.println(scenario);
		System.out.println();
		datatable.setVirtualtablePath(Datatable.PAYMENTUI_MASTER_DATA_PATH);
		datatable.setVirtualtablePage("PaymentUI");
		datatable.setVirtualtableScenario(scenario);

		String[] paymentType = datatable.getDataParameter("PaymentType").split(";");
		PaymentUIWindow.paymentTypes[] types = new PaymentUIWindow.paymentTypes[paymentType.length];
		for (int type = 0; type < paymentType.length; type++) {
			types[type] = PaymentUIWindow.getpaymentTypeEnum(paymentType[type]);
		}
		String[] paymentMethod = datatable.getDataParameter("PaymentMethod")
				.split(";");
		PaymentUIWindow.paymentMethods[] methods = new PaymentUIWindow.paymentMethods[paymentMethod.length];
		for (int method = 0; method < paymentMethod.length; method++) {
			methods[method] = PaymentUIWindow
					.getpaymentMethodEnum(paymentMethod[method]);
		}
		String[] status = datatable.getDataParameter("Status").split(";");
		String[] delay = datatable.getDataParameter("Delay").split(";");
		String[] strIncidentals = datatable.getDataParameter("Incidentals").split(";");
		boolean[] incidentals = new boolean[strIncidentals.length];
		for (int bool = 0; bool < strIncidentals.length; bool++) {
			if (strIncidentals[bool].equalsIgnoreCase("true")) {
				incidentals[bool] = true;
			} else {
				incidentals[bool] = false;
			}
		}
		String[] amount = datatable.getDataParameter("Amount").split(";");
		String[] splits = datatable.getDataParameter("SplitPayment").split(";");
		String[] expiredCC = datatable.getDataParameter("ExpiredCC").split(";"); 
		String[] primaryGuestInfo = datatable.getDataParameter("UsePrimaryGuestInfo").split(";");

		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Apply Payment");

		PaymentUIWindow paymentUIWindow = new PaymentUIWindow(driver);
		for(int pays = 0; pays < types.length; pays++){
			System.out.println("Payment type " +String.valueOf(pays) + ": " + paymentType[pays]);
			System.out.println("Payment method " +String.valueOf(pays) + ": " + paymentMethod[pays]);
			System.out.println("Payment status " +String.valueOf(pays) + ": " + status[pays]);
			System.out.println("Payment delay " +String.valueOf(pays) + ": " + delay[pays]);
			System.out.println("Payment incidentals " +String.valueOf(pays) + ": " + strIncidentals[pays]);
			System.out.println("Payment amount " +String.valueOf(pays) + ": " + amount[pays]);
			System.out.println("Split Payment " +String.valueOf(pays) + ": " + splits[pays]);
			System.out.println("Expired CC " +String.valueOf(pays) + ": " + expiredCC[pays]);
			System.out.println("Primary Guest Info " +String.valueOf(pays) + ": " + primaryGuestInfo[pays]);

			if(types.length == 2){
				paymentUIWindow.takePaymentWithSplit(
						PaymentUIWindow.getpaymentTypeEnum(paymentType[pays]),
						PaymentUIWindow.getpaymentMethodEnum(paymentMethod[pays]), 
						status[pays], delay[pays], incidentals[pays], amount[pays], 
						splits[pays], expiredCC[pays], primaryGuestInfo[pays]);
			}else if(types.length == 3){
				paymentUIWindow.takeThreePaymentsWithSplit(
						PaymentUIWindow.getpaymentTypeEnum(paymentType[pays]),
						PaymentUIWindow.getpaymentMethodEnum(paymentMethod[pays]), 
						status[pays], delay[pays], incidentals[pays], amount[pays], 
						splits[pays], expiredCC[pays], primaryGuestInfo[pays]);
			}
			cardNumber = paymentUIWindow.getCardNumber().replace("-", "");
			cardNumberMasked = paymentUIWindow.getCardNumberMasked().replace(
					"-", "");
			paymentAmount = paymentUIWindow.getPaymentAmount().replace("$", "");
			uiPaymentMethod = paymentUIWindow.getPaymentMethod();
		}
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Disney Reservation");
		datatable.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);
	}



	/**
	 * @summary: method to Select folio
	 * @author: Sabitha Adama
	 */
	public void selectFolio() {
		try {
			btnSelectFolio = new ButtonImpl(driver.findElement(By
					.xpath("//INPUT[@value='test_value_0'][@name='Select']")));
		} catch (Exception e) {
		}
		pageLoaded(btnSelectFolio);
		btnSelectFolio.highlight(driver);
		btnSelectFolio.jsClick(driver);
	}

	/**
	 * @summary Method to click Edit Internal comments - Reservation summary
	 * @version Created 03/19/2016
	 * @author Sowmya .ch
	 * @param Input
	 *            Comment type String
	 * @throws NA
	 * @return NA
	 */
	public void EditInternalComments_Click(String Comment) {
		clickNew();
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Internal Comments");
		pageLoaded(lnkEdit);
		lnkEdit.highlight(driver);
		lnkEdit.click();
		clickAdd();
		clickOk();
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Disney Reservation Entry and Management System");
	}

	/**
	 * @summary Method to click Delete Internal comment- Reservation summary
	 * @version Created 03/19/2016
	 * @author Sowmya .ch
	 * @param Input
	 *            Comment type String
	 * @throws NA
	 * @return NA
	 */

	// Clicking on delete Button Reservation Summary
	public void click_DeleteInternalComment() {
		clickNew();
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Internal Comments");
		pageLoaded(lnkDelete);
		lnkDelete.highlight(driver);
		lnkDelete.click();
		clickOk();
	}

	/**
	 * @summary Method to Perform Cancel Reservation
	 * @version Created 03/19/2016
	 * @author Lalitha Banda
	 * @param DataTable
	 *            scenario
	 * @throws NA
	 * @return NA
	 */

	// Cancel Reservation Window - Reservation Summary
	public void perform_ResCancel(String scenario, String scenario1) {
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Disney Reservation");
		pageLoaded(btnCancel);
		btnCancel.highlight(driver);

		btnCancel.sendKeys(Keys.ENTER);
		Sleeper.sleep(2000);
		PleaseWait.WaitForPleaseWait(driver);

		// The window count switches between 1 and 2 several times before the
		// cancellation window appears
		try {
			WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
			Sleeper.sleep(1000);
			WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		} catch (TimeoutException te) {
			te.printStackTrace();
		}

		System.out.println(driver.getWindowHandles().size());
		for (String handle : driver.getWindowHandles()) {
			System.out.println("Window Name : " + handle);
			if (!driver.switchTo().window(handle).getTitle()
					.contains("Disney Reservation")) {
				break;
			}
		}
		// Switching Cancel Reservation Page
		CancelReservation cancelRES = new CancelReservation(driver);
		cancelRES.pageLoaded();
		try {
			// perform cancel reservation
			cancelRES.perform_CancelRES(scenario, scenario1);

		} catch (Exception e) {
		}
	}

	/**
	 * @summary Method to perform Due Date Extension Pop Up
	 * @version Created 03/23/2016
	 * @author Lalitha Banda
	 * @param  DataTable scenario
	 * @throws NA
	 * @return NA
	 */

	// 
	public void perform_DeptDueDateExtension(String extensioninput){
		initialize();
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,"Disney Reservation Entry and Management System");
		//Clicking on Due Date Extension link
		String currentDate = DateTimeConversion.getDaysOut("0", "MM/dd/yyyy");
		try{
			driver.findElement(By.linkText(currentDate)).click();
		}catch(Exception e){
			List<WebElement> links = driver.findElements(By.tagName("a"));
			for(WebElement link : links){
				if(link.getText().equalsIgnoreCase(currentDate)){
					link.click();
					break;
				}
			}	
		}

		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Extension Due Date");
		radNoDays.click();
		txtExtInput.safeSet(extensioninput);

		//clicking on OK button
		List<WebElement> buttons = driver.findElements(By.className("button"));
		for(WebElement button: buttons){
			button.click();
			break;
		}

		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,"Disney Reservation Entry and Management System");

	}

	/**
	 * @summary Method to Click on New button of KTTW Tickets
	 * @version Created 03/22/2016
	 * @author Sabitha Adama
	 */
	public void clickKTTWTicketsNew(){
		List<WebElement> KTTWElements = driver.findElements(By.className("button"));
		for (WebElement inputButton : KTTWElements) {
			if (inputButton.getAttribute("onclick").contains(
					"ValidateTicketOrAccommodationNew('ticket')")) {
				inputButton.click();
			}
		}
	}

	/**
	 * @summary Method to Click on New button of KTTW Tickets
	 * @version Created 03/23/2016
	 * @author Sabitha Adama
	 */
	public void clickKTTWTickets(){
		pageLoaded(lnkKTTWTickets);
		lnkKTTWTickets.highlight(driver);
		lnkKTTWTickets.click();
	}

	/**
	 * @summary Method to Click on New button of KTTW Tickets
	 * @version Created 03/24/2016
	 * @author Sabitha Adama
	 */
	public void setCurrentTravelPlan(String scenario){
		datatable.setVirtualtablePage("Tickets");
		datatable.setVirtualtableScenario(scenario);
		int SelectTickets;
		String[] currentTravelPlan = datatable.getDataParameter("CurrentTravelPlan").split(";");

		try{
			// selecting current travel plan,checking the check boxes
			for (SelectTickets = 0; SelectTickets < currentTravelPlan.length; SelectTickets++) {
				System.out.println("am entered in to for loop");

				if (currentTravelPlan[SelectTickets].equalsIgnoreCase("true")) {
					String locator="travelPlanTO.currentTravelPlanSegment.ticketListForDisplay[0].individualTicketTOList["+SelectTickets+"].freeSellTicketTO.selected";	
					driver.findElement(By.name(locator)).click();
					TestReporter.logStep("Button is selected");
				}
				else{
					TestReporter.logStep("Button is not selected");
				}	
			}
		}
		catch(Exception e){}
	}

	/**
	 * @summary Method to Click on New button of KTTW Tickets
	 * @version Created 03/24/2016
	 * @author Sabitha Adama
	 */
	public void clickKTTWTicketsEdit(){
		getButtons("validateTicketsEdit()");
	}

	/**
	 * @summary Method to Click on New button of KTTW Tickets
	 * @version Created 03/24/2016
	 * @author Sabitha Adama
	 */
	public void clickKTTWTicketsCancel(String ticketsScenario){
		datatable.setVirtualtablePage("Tickets");
		//datatable.setVirtualtableScenario(ticketsScenario);
		datatable.setVirtualtableScenario("KTTWTickets");
		String ticketOption=datatable.getDataParameter("TicketOption");
		
		Checkbox ticket = new CheckboxImpl(driver.findElement(By.xpath("//*[contains(normalize-space(),'" + ticketOption +"')]/td/div/input[@id='ticketForDisplaySelected']")));
		ticket.highlight(driver);
		ticket.check();
		
		pageLoaded(btnKTTWCancel);
		btnKTTWCancel.highlight(driver);
		btnKTTWCancel.click();	
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "WARNING");
		
		new ButtonImpl(driver.findElement(By.name("b_Yes"))).jsClick(driver);
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,"Disney Reservation Entry and Management System");
		pageLoaded(btnKTTWCancel);
	}

	/**
	 * @summary Method to Click on New button of KTTW Tickets
	 * @version Created 03/24/2016
	 * @author Sabitha Adama
	 */
	public void getButtons(String inputButtonValue) {
		List<WebElement> buttons = driver.findElements(By.name("b_Edit"));
		for (WebElement button : buttons) {
			if (button.getAttribute("onclick").equalsIgnoreCase(inputButtonValue)) {
				button.click();
				break;
			}
		}
	}

	/**
	 * @summary Method to Click on New button of KTTW Tickets
	 * @version Created 03/25/2016
	 * @author Sabitha Adama
	 */
	public void clickonOkButton() {				
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 3);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Alert !");
		pageLoaded(btnKTTWOk);
		btnKTTWOk.jsClick(driver);
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,"Disney Reservation Entry and Management System");
	}

}


