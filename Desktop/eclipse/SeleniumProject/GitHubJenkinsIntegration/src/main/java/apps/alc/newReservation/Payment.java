package apps.alc.newReservation;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.SettlementUI.SettlementUIWindow;
import apps.alc.pleaseWait.PleaseWait;
import apps.lilo.processingYourRequest.ProcessingYourRequest;
import apps.paymentUI.PaymentUIWindow;
import apps.paymentUI.PaymentUIWindow.paymentMethods;
import apps.paymentUI.PaymentUIWindow.paymentTypes;
import core.WebDriverSetup;
import core.WindowHandler;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Label;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

/**
 * 
 * @summary Contains the page methods & objects for the search guest page during
 *          the new reservation process
 * @version Created 10/02/2014
 * @author Waightstill W Avery
 */
public class Payment {
	// ************************************
	// *** Main Page Fields ***
	// ************************************
	private int timeout = WebDriverSetup.getDefaultTestTimeout();
	private WebDriver driver;
	private String selectedOfferDetails;
	private String totalPrice;
	private String balanceDue;
	private Datatable datatable = new Datatable();

	// ************************************
	// *** Main Page Elements ***
	// ************************************

	@FindBy(id = "reservationPaymentViewForm:newReservationGiveUpButton")
	private Button btnGiveUpOffer;

	@FindBy(xpath = "//*[@id=\"reservationPaymentViewForm:offerInfoPanel_body\"]/table/tbody/tr[1]")
	private Label lblReservationDetails;

	@FindBy(id = "reservationPaymentViewForm:balancePanelTotalInput")
	private Label lblTotalPrice;

	@FindBy(id = "reservationPaymentViewForm:balancePanelBalanceDueInput")
	private Label lblRemainingBalance;

	@FindBy(id = "reservationPaymentViewForm:newReservationSetSettlementButton")
	private Button btnSetGuarantee;

	@FindBy(id = "reservationPaymentViewForm:newReservationPaymentAddPaymentButton")
	private Button btnAddPayment;
	
	@FindBy(id = "reservationPaymentViewForm:newReservationPaymentAddPaymentButton1")
	private Button btnAddPayment1;

	@FindBy(id = "reservationPaymentViewForm:newReservationContinueButton")
	private Button btnContinue;
	
	@FindBy(xpath = "//*[@id='reservationPaymentViewForm:balancePanel_body']/table/tbody/tr[1]")
	private Element eleTotalPrice;
	
	@FindBy(xpath = "//*[@id='reservationPaymentViewForm:balancePanel_body']/table/tbody/tr[2]")
	private Element eleAmountPaid;
	
	@FindBy(xpath = "//*[@id='reservationPaymentViewForm:balancePanel_body']/table/tbody/tr[3]")
	private Element eleBalanceDue;
	
	@FindBy(id = "headerTextError") 
	private Element eleErrorHeader;
	
	@FindBy(css="input[value='Add Payment']")
	private Button btnAddpayment;

	// *********************
	// ** Build page area **
	// *********************

	public Payment(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}

	public Payment initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnGiveUpOffer);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	// **************************************
	// *** Main CheckIn Page Interactions ***
	// **************************************
	public void capturePaymentDetails() {
		captureTotalPrice();
		captureBalanceDue();
		captureReservationDetails();
	}

	private void captureTotalPrice() {
		totalPrice = lblTotalPrice.getText();
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	private void captureBalanceDue() {
		balanceDue = lblRemainingBalance.getText();
	}

	public String getBalanceDue() {
		return balanceDue;
	}

	private void captureReservationDetails() {
		lblReservationDetails.highlight(driver);
		selectedOfferDetails = lblReservationDetails.getText();
	}

	public String getReservationDetails() {
		return selectedOfferDetails;
	}
	

	@Deprecated
	public void applyPaymentOrSettlement(String scenario) throws InterruptedException {
		datatable.setVirtualtablePage("Payment");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded();

		String flow = datatable.getDataParameter("Flow");

		switch (flow.toLowerCase()) {
		case "settlement":
		case "guarantee":
			clickSetGuarantee();
			Sleeper.sleep(3000);
			String parentWindow = driver.getTitle();
			new WindowHandler().swapToWindow(driver, "Set Settlement");
			SettlementUIWindow settlement = new SettlementUIWindow(driver);
			settlement.settlementUiProcessingWindowHidden();
			settlement.applySettlement(scenario);
			new WindowHandler().swapToWindow(driver, parentWindow);
			if (!btnContinue.syncEnabled(driver, 10, false)) {
				driver.findElement(By.id("main")).sendKeys(Keys.F5);
				initialize();
				pageLoaded(btnContinue);
				btnContinue.syncEnabled(driver, timeout, true);
			}
			clickContinue();

			break;
		case "payment":
			// TODO: Add the methods for applying payment (a.k.a. prepaid)
			break;
		default:
			break;
		}
	}

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

		initialize();

		clickSetGuarantee();
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Set Settlement");

		boolean errorFound = false;
		if(eleErrorHeader.syncVisible(driver, 5, false)){
			errorFound = true;
			TestReporter.assertFalse(errorFound, "An error occurred launching the Settlement UI. Error text follows: ["+driver.findElement(By.id("pmtErrorModalPanelContentDiv")).getText()+"].");
		}
		
		SettlementUIWindow settlementWindow = new SettlementUIWindow(driver);
		settlementWindow.applySettlementGuarantee(paymentType, paymentMethod, status, delay, thirdParty);
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "SEServer");
		Sleeper.sleep(5000);
		
		//Modifying timeout to 5 seconds - WWA
		// Determine if the Continue button is disabled. If not, try sending F5
		// to the application as recommended by the application.  This is a known application issue. - WWA
		try{
			if (!btnContinue.syncEnabled(driver, 5, false)) {
				btnContinue.highlight(driver);
				driver.findElement(By.id("main")).sendKeys(Keys.F5);
				Sleeper.sleep(2000);
				new PageLoaded().isDomComplete(driver);
				PleaseWait.WaitForPleaseWait(driver, 120);
			}
		}catch(Exception e){
			pageLoaded(btnContinue);
		}
		btnContinue.syncEnabled(driver, timeout, true);
		btnContinue.highlight(driver);
		btnContinue.isDisplayed();
		btnContinue.isEnabled();
		clickContinue();
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}
	
	public void applyPayment(String scenario) {

		System.out.println(scenario);
		datatable.setVirtualtablePath(Datatable.PAYMENTUI_MASTER_DATA_PATH);
		datatable.setVirtualtablePage("PaymentUI");
		datatable.setVirtualtableScenario(scenario);

		String paymentType = datatable.getDataParameter("PaymentType");
		String paymentMethod = datatable.getDataParameter("PaymentMethod");
		String status = datatable.getDataParameter("Status");
		String delay = datatable.getDataParameter("Delay");
		boolean incidentals = false;
		if(datatable.getDataParameter("Incidentals").equalsIgnoreCase("true")){
			incidentals = true;
		}
		String amount = datatable.getDataParameter("Amount");
		String expiredCC = datatable.getDataParameter("ExpiredCC"); 
		String primaryGuestInfo = datatable.getDataParameter("UsePrimaryGuestInfo"); 
		String errorMessage = datatable.getDataParameter("ExpectedErrorMessage");
		
		initialize();

		clickAddPayment();
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Apply Payment");

		PaymentUIWindow paymentWindow = new PaymentUIWindow(driver);
		paymentWindow.takePayment(
				PaymentUIWindow.getpaymentTypeEnum(paymentType), 
				PaymentUIWindow.getpaymentMethodEnum(paymentMethod), 
				status, delay, incidentals, amount, expiredCC, primaryGuestInfo, errorMessage);
		
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "SEServer");
		
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		if (!btnContinue.syncEnabled(driver, 5, false)) {
			driver.findElement(By.id("main")).sendKeys(Keys.F5);
			pageLoaded();
			btnContinue.syncEnabled(driver, timeout, true);
		}
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
		clickContinue();
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}
	
	private void clickAddPayment(){
		initialize();
		List<WebElement> addPayments = driver.findElements(By.cssSelector("input[value='Add Payment']"));
		for(WebElement addPayment : addPayments){
			Element ap = new ElementImpl(addPayment);
			ap.highlight(driver);
			if(ap.syncVisible(driver, 1, false)){
				ap.highlight(driver);
				ap.jsClick(driver);
				break;
			}
		}
	}

	private void clickSetGuarantee() {
		pageLoaded(btnSetGuarantee);
		btnSetGuarantee.syncVisible(driver);
		btnSetGuarantee.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	private void clickContinue() {
		pageLoaded(btnContinue);
		btnContinue.syncVisible(driver);
		btnContinue.syncEnabled(driver);
		btnContinue.click();
	}
	
	//- 
	public void reservationDetails(){
		captureReservationDetails();
	}
	public void takePayment(String scenario) {
		datatable.setVirtualtablePath(Datatable.PAYMENTUI_MASTER_DATA_PATH);
		datatable.setVirtualtablePage("PaymentUI");
		datatable.setVirtualtableScenario(scenario);

		paymentTypes paymentType = PaymentUIWindow.getpaymentTypeEnum(datatable.getDataParameter("PaymentType"));
		paymentMethods paymentMethod = PaymentUIWindow
				.getpaymentMethodEnum(datatable.getDataParameter("PaymentMethod"));
		String status = datatable.getDataParameter("Status");
		String delay = datatable.getDataParameter("Delay");
		String errorMessage = datatable.getDataParameter("ExpectedErrorMessage");
		boolean incidentals = true;

		if (datatable.getDataParameter("Incidentals").equalsIgnoreCase("false")) {
			incidentals = false;
		}
		String amount = datatable.getDataParameter("Amount");
		String expiredCC = datatable.getDataParameter("ExpiredCC"); 
		String primaryGuestInfo = datatable.getDataParameter("UsePrimaryGuestInfo");
		
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Apply Payment");

		PaymentUIWindow paymentUI = new PaymentUIWindow(driver);
		paymentUI.takePayment(paymentType, paymentMethod, status, delay, incidentals, amount, 
				expiredCC, primaryGuestInfo, errorMessage);

		WindowHandler.waitUntilWindowExistsTitleContains(driver, "SEServer");

		new ProcessingYourRequest().WaitForProcessRequest(driver);
		/*
		 * initialize(driver); captureAmountDueAtCheckIn();
		 */ // Here the test getting failed
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
		clickContinue();
	}
	
	public void addPayment(){
		btnAddPayment1.highlight(driver);
		btnAddPayment1.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}
	
	/**
	 * @summary: Method to get Payment or Balance Due Details
	 * @author : Venkatesh Atmakuri - Original.
	 * @version: created 1-22-2016.
	 * @param   NA
	 * @return  NA
	 */
	public void getPaymentOrBalanceDetails(){
		pageLoaded(eleTotalPrice);
		eleTotalPrice.highlight(driver);
		TestReporter.log("Total Price : "+eleTotalPrice.getText());
		eleAmountPaid.highlight(driver);
		TestReporter.log("Amount Paid : "+eleAmountPaid.getText());
		eleBalanceDue.highlight(driver);
		TestReporter.log("Balance Due : "+eleBalanceDue.getText());
	}
	
	/**
	 * @summary: Method to click on the Add Payment button.
	 * @author: Praveen Namburi, @Version: Created 2-8-2016
	 */
	public void clickaddPayment(){
		//driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		if (!btnAddpayment.syncEnabled(driver, 5, false)) {
			driver.findElement(By.id("main")).sendKeys(Keys.F5);
			pageLoaded(btnAddpayment);
			btnAddpayment.syncEnabled(driver, timeout, true);
		}
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
		//Click on 'Add Payment' button in payment page.
		btnAddpayment.syncVisible(driver);
		btnAddpayment.click();
	}
	
	
	
}


