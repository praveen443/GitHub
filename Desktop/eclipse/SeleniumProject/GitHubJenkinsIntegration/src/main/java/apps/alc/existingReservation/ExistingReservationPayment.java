package apps.alc.existingReservation;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.SettlementUI.SettlementUIWindow;
import apps.alc.pleaseWait.PleaseWait;
import apps.paymentUI.PaymentUIWindow;
import core.WebDriverSetup;
import core.WindowHandler;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;

/**
 * This class contains elements and element interactions for a given page of a
 * web application
 * 
 * @author Waightstill W Avery
 * @version 12/15/2015 Waightstill W Avery - original
 */
public class ExistingReservationPayment {
	// *******************
	// Page Class Fields
	// *******************
	/*
	 * Class fields go here (int, Strings, List<>, etc)
	 */
	// Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private int timeout = WebDriverSetup.getDefaultTestTimeout();
	private Datatable datatable = new Datatable();

	// *********************
	// Page Class Elements
	// *********************
	/*
	 * FindBy elements go here
	 */
	@FindBy(id = "existingReservationPaymentViewForm:existingReservationContinueButton")
	private Button btnContinue;

	@FindBy(id = "existingReservationPaymentViewForm:existingReservationSetSettlementButton")
	private Button btnSetGuarantee;
	
	@FindBy(id = "existingReservationPaymentViewForm:existingReservationPaymentAddPaymentButton1")
	private Button btnAddPayment;
	
	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
	 * @param driver
	 */
	public ExistingReservationPayment(WebDriver driver) {
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
	public ExistingReservationPayment initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnAddPayment);
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
	public void applySettlementAndContinue(String scenario){
		applySettlement(scenario);
		//clickContinue();
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
				if (driver.switchTo().window(handle).getTitle().contains("Set Settlement")) {
					settleUiFound = true;
					break;
				}
			}
		} while (!settleUiFound);

		SettlementUIWindow settlementWindow = new SettlementUIWindow(driver);
		settlementWindow.applySettlementGuarantee(paymentType, paymentMethod, status, delay,thirdParty);
		new WindowHandler().swapToWindow(driver, parentWindow);
		if (!btnContinue.syncEnabled(driver, timeout, false)) {
			driver.findElement(By.id("main")).sendKeys(Keys.F5);
			pageLoaded();
			btnContinue.syncEnabled(driver, timeout, true);
		}
		clickContinue();
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}

	private void clickSetGuarantee() {
		btnSetGuarantee.highlight(driver);
		btnSetGuarantee.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	private void clickContinue() {
		new PageLoaded().isDomComplete(driver);
		pageLoaded(btnContinue);
		btnContinue.syncVisible(driver);
		btnContinue.syncEnabled(driver);
		btnContinue.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
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
		Sleeper.sleep(3000);
		String parentWindow = driver.getTitle();
		//Loop until 2 window are present
		do{
			Sleeper.sleep(500);
		}while(driver.getWindowHandles().size() < 2);
		
		//Loop through each window until the Settlement UI can be identified
		boolean paymentUiFound = false;
		do{
			Sleeper.sleep(500);
			for(String handle : driver.getWindowHandles()){
				if(driver.switchTo().window(handle).getTitle().contains("Apply Payment")){
					paymentUiFound = true;
					break;
				}
			}
		}while(!paymentUiFound);

		PaymentUIWindow paymentWindow = new PaymentUIWindow(driver);
		paymentWindow.takePayment(
				PaymentUIWindow.getpaymentTypeEnum(paymentType), 
				PaymentUIWindow.getpaymentMethodEnum(paymentMethod), 
				status, delay, incidentals, amount, expiredCC, primaryGuestInfo, errorMessage);
		
		new WindowHandler().swapToWindow(driver, parentWindow);
		if (!btnContinue.syncEnabled(driver, timeout, false)) {
			driver.findElement(By.id("main")).sendKeys(Keys.F5);
			pageLoaded();
			btnContinue.syncEnabled(driver, timeout, true);
		}
		clickContinue();
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}
	
	private void clickAddPayment(){
		initialize();
		List<WebElement> addPayments = driver.findElements(By.cssSelector("input[value='Add Payment']"));
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		for(WebElement addPayment : addPayments){
			Element ap = new ElementImpl(addPayment);
			ap.highlight(driver);
			if(ap.syncVisible(driver, 1, false)){
				ap.highlight(driver);
				ap.jsClick(driver);
				break;
			}
		}
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
	}
}

