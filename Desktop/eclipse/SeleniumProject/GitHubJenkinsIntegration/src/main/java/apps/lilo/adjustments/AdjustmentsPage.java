package apps.lilo.adjustments;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.WebDriverSetup;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Randomness;
import utils.Sleeper;
import utils.TestReporter;

/**
 * This class contains elements and element interactions for a given page of a
 * web application
 * 
 * @author Waightstill W Avery
 * @version 11/13/2015 Waightstill W Avery - original
 */
public class AdjustmentsPage {
	// *******************
	// Page Class Fields
	// *******************
	// Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private String ticketNumber = "";
	private Datatable datatable = new Datatable();

	// *********************
	// Page Class Elements
	// *********************

	@FindBy(id = "applyChargeBody:applyChargeButton")
	private Textbox btnApplyAdjustment;

	@FindBy(id = "applyChargeBody:suggest")
	private Textbox txtAccountingCenter;
	
	@FindBy(id = "applyChargeBody:suggestBoxId")
	private Element eleAccountingCentersForm;

	@FindBy(id = "applyChargeBody:suggestBoxId:suggest")
	private Webtable tblAccountingCenters;

	@FindBy(id = "applyChargeBody:revenueClassificationIdSelect")
	private Listbox lstRevenueClassification;

	@FindBy(id = "applyChargeBody:chargeTypeSelect")
	private Listbox lstChargeType;

	@FindBy(id = "applyChargeBody:transNumberInput")
	private Textbox txtTransactionNumber;

	@FindBy(id = "applyChargeBody:chargedBySelect")
	private Listbox lstChargedBy;

	@FindBy(id = "applyChargeBody:terminalNumberInput")
	private Textbox txtTerminalId;

	@FindBy(id = "applyChargeBody:couponTypeSelect1")
	private Listbox lstCouponType;

	@FindBy(id = "applyChargeBody:chargeAmountId")
	private Textbox txtChargeAmount;

	@FindBy(id = "applyChargeBody:reasonCodeSelect")
	private Listbox lstReasonCode;

	@FindBy(id = "applyChargeBody:commentsId")
	private Textbox txtComments;

	@FindBy(xpath = "/html/body/div[7]/div[2]/div/div[2]/table/tbody/tr[2]/td/form/table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td/table/tbody/tr/td[2]")
	private Element eleInfoText;

	@FindBy(id = "applychargeConfirmationForm:okButtonId")
	private Button btnOk;

	@FindBy(id = "PMSRErrorModalPanelHeader")
	private Element eleWarningHeader;

	@FindBy(id = "errorForm:continueId")
	private Button btnContinue;
	
	@FindBy(id = "applychargeConfirmationForm")
	private Element eleApplyConfirmationForm;
	
	@FindBy(id = "applyChargeBody:adultCouponCountInput")
	private Textbox txtAdultCouponCount;
	
	@FindBy(id = "applyChargeBody:childCouponCountInput")
	private Textbox txtChildCouponCount;

	//Create Textbox Object for KTTWNumber
	@FindBy(id = "applyChargeBody:kttwNumberInput")
	private Textbox txtKTTWNumber; 

	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Waightstill W Avery
	 * @version 11/13/2015 Waightstill W Avery - original
	 * @param driver
	 */
	public AdjustmentsPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Waightstill W Avery
	 * @version 11/13/2015 Waightstill W Avery - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public void initialize() {
	//	return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 11/13/2015 Waightstill W Avery - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnApplyAdjustment);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 11/13/2015 Waightstill W Avery - original
	 * @param element
	 *            - Element to be used to determine if the page is loaded
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	public void eleAccountingcenterLoaded(){
    	
    	while (!txtAccountingCenter.syncVisible(driver)){
    		initialize();
    	}
    	txtAccountingCenter.syncVisible(driver);
    }
	
	/**
	 * @summary : Method to synchronize and find the textbox accounting center. 
	 * @author  : Praveen Namburi
	 * @version : Created 11/06/2015
	 * @return  : NA
	 */
	public void adjustmentPageLoaded(){
    	
    	while (!txtAccountingCenter.syncVisible(driver)){
    		initialize();
    	}
    	txtAccountingCenter.syncVisible(driver);
    }
	
	// ********************
	// Page Class Methods
	// ********************

	public void applyAdjustment(String scenario) {
		datatable.setVirtualtablePage("Adjustments");
		datatable.setVirtualtableScenario(scenario);
		String accountingCenter = datatable.getDataParameter("AccountingCenter");
		String revenueClassification = datatable.getDataParameter("RevenueClassification");
		String chargeType = datatable.getDataParameter("ChargeType");
		String transactionNumber = datatable.getDataParameter("TransactionNumber");
		String chargedBy = datatable.getDataParameter("ChargeBy");
		String terminalId = datatable.getDataParameter("TerminalID");
		String couponType = datatable.getDataParameter("CouponType");
		String chargeAmount = datatable.getDataParameter("ChargeAmount");
		String reasonCode = datatable.getDataParameter("ReasonCode");
		String comments = datatable.getDataParameter("Comments");
		String numAdults = datatable.getDataParameter("NumberOfAdults");
		String numChild = datatable.getDataParameter("NumberOfChildren");

		selectAccountingCenter(accountingCenter);
		selectRevenueClassification(revenueClassification);
		selectChargeType(chargeType);
		setTransactionNumber(transactionNumber);
		selectChargedBy(chargedBy);
		setTerminalId(terminalId);
		selectCouponType(couponType);
		setPartyMix(numAdults, numChild);
		setChargeAmount(chargeAmount);
		selectResonCode(reasonCode);
		setComments(comments);
		clickApplyCharge();
		handleWarnings();
		captureTicketNumber();
		clickOk();
	}
	
	private void setPartyMix(String adults, String children){
		//initialize();
		pageLoaded(txtAdultCouponCount);
		txtAdultCouponCount.set(adults);
		txtChildCouponCount.set(children);
	}

	private void handleWarnings() {
		initialize();
		pageLoaded(eleWarningHeader);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		try {
			if (eleWarningHeader.syncVisible(driver, 5, false)) {
				initialize();
				pageLoaded(btnContinue);
				btnContinue.jsClick(driver);
				System.out.print("Sleeping....");
				Sleeper.sleep(3000);
				System.out.println("done");
			}
		} catch (NoSuchElementException | StaleElementReferenceException e) {

		} finally {
			driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
		}
		System.out.println("Warnings handled.");
	}

	private void clickOk() {
	//	initialize();
		pageLoaded(btnOk);
		btnOk.click();
//		new ProcessingYourRequest().WaitForProcessRequest(driver);
		Sleeper.sleep(2000);
	}

	private void captureTicketNumber() {
	//	initialize();
		pageLoaded(eleApplyConfirmationForm);
		System.out.println("Capturing ticket number.");
		ticketNumber = eleApplyConfirmationForm.findElement(By.xpath("table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td/table/tbody/tr/td[2]")).getText().split(":")[1].replace(".", "");
		System.out.println("Ticket Number: " + ticketNumber);
		TestReporter.log("Ticket Number: " + ticketNumber);
	}

	private void clickApplyCharge() {
	//	initialize();
		pageLoaded(btnApplyAdjustment);
		btnApplyAdjustment.click();
		Sleeper.sleep(2000);
//		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	private void setComments(String comments) {
	//	initialize();
		pageLoaded(txtComments);
		txtComments.set(comments);
	}

	private void selectResonCode(String reasonCode) {
	//	initialize();
		pageLoaded(lstReasonCode);
		lstReasonCode.select(reasonCode);
	}

	private void setChargeAmount(String chargeAmount) {
	//	initialize();
		pageLoaded(txtChargeAmount);
		txtChargeAmount.set(chargeAmount);
	}

	private void selectCouponType(String couponType) {
	//	initialize();
		if(!couponType.isEmpty()){
			pageLoaded(lstCouponType);
			lstCouponType.select(couponType);
		}
	}

	private void setTerminalId(String terminalId) {
	//	initialize();
		pageLoaded(txtTerminalId);
		if (terminalId.isEmpty() || terminalId.equalsIgnoreCase("RANDOM")) {
			terminalId = Randomness.randomNumber(3);
		}
		txtTerminalId.set(terminalId);
	}

	private void selectChargedBy(String chargedBy) {
		///initialize();
		pageLoaded(txtTransactionNumber);
		if (chargedBy.isEmpty() || chargedBy.equalsIgnoreCase("PRIMARY GUEST")) {
			chargedBy = lstChargedBy.findElement(By.xpath("option[2]")).getText();
		}
		lstChargedBy.select(chargedBy);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	private void setTransactionNumber(String transactionNumber) {
	//	initialize();
		pageLoaded(txtTransactionNumber);
		if (transactionNumber.isEmpty() || transactionNumber.equalsIgnoreCase("random")) {
			transactionNumber = Randomness.randomNumber(6);
		}
		txtTransactionNumber.set(transactionNumber);
	}

	private void selectRevenueClassification(String revenueClassification) {
	//	initialize();
		pageLoaded(lstRevenueClassification);
		lstRevenueClassification.select(revenueClassification);
	}

	private void selectChargeType(String chargeType) {
	//	initialize();
		pageLoaded(lstChargeType);
		lstChargeType.select(chargeType);
	}

	private void selectAccountingCenter(String accountingCenter) {
	//	initialize();
		pageLoaded(txtAccountingCenter);
		txtAccountingCenter.set(accountingCenter);

		initialize();
		pageLoaded(eleAccountingCentersForm);
		System.out.println("Waiting for table to load");
		try{
			do{
				initialize();
				Sleeper.sleep(100);
			}while(eleAccountingCentersForm.getAttribute("display").equalsIgnoreCase("none"));
		}catch(StaleElementReferenceException | NullPointerException e){
			
		}
		System.out.println("Table loaded");
		
		initialize();
		pageLoaded(tblAccountingCenters);
		tblAccountingCenters.syncVisible(driver);
		tblAccountingCenters.highlight(driver);
		List<WebElement> rows = tblAccountingCenters.findElements(By.xpath("tbody/tr"));
		System.out.println("Number Of Rows: " + rows.size());
		
		boolean accountingCenterFound = false;
		for (WebElement row : rows) {
			if (row.findElement(By.xpath("td[2]/span")).getText().equalsIgnoreCase(accountingCenter)) {
				accountingCenterFound = true;
				row.click();
				break;
			}
		}
		Sleeper.sleep(2000);
		TestReporter.assertTrue(accountingCenterFound,
				"The Accounting Center [" + accountingCenter + "] was not found.");
	}
	
	public String getTicketNumber(){
		return ticketNumber;
	}
}

