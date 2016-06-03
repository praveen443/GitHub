package apps.lilo.bankOut;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import apps.lilo.resortFunctions.ResortFunctionsPage;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Randomness;
import utils.TestReporter;

/**
 * This class contains elements and element interactions for a given page of a
 * web application
 * 
 * @author Waightstill W. aVery
 * @version 11/04/2015 - Waightstill W. Avery - original
 */
public class BankOutPage {
	// *******************
	// Page Class Fields
	// *******************
	// Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	// *********************
	// Page Class Elements
	// *********************
	@FindBy(id = "bankOutForm:submitId")
	private Button btnSubmit;
	
	@FindBy(css = "#bankOutForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(2) > input:nth-child(1)")
	private Textbox txtBagNumber;
	
	@FindBy(css = "#bankOutForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(2) > input:nth-child(1)")
	private Textbox txtReEnterBagNumber;

	@FindBy(id = "bankOutForm:depositType")
	private Listbox lstDepositType;
	
	@FindBy(id = "specialTender:nextId")
	private Button btnNext;
	
	@FindBy(id = "specialTender:bankoutId")
	private Button btnCompleteBankOut;
	
	@FindBy(id = "bankoutSucForm:noId")
	private Button btnSuccessfulBankOutNo;
	
	@FindBy(id = "bankoutSucForm:yesId")
	private Button btnSuccessfulBankOutYes;
	
	@FindBy(id = "specialTender:closeId")
	private Button btnExit;
	
	@FindBy(id = "specialTender:suspendId")
	private Button btnSuspendBankout;
	
	@FindBy(id = "lateOrAdvance:cashId")
	private Textbox txtAdvanceDepositAmount;
	
	@FindBy(id = "lateOrAdvance:advId")
	private Button btnCompleteAdvancedDeposit;
	
	@FindBy(id = "lateOrAdvance:closeId")
	private Button btnLateOrAdvancedExit;
	
	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Waightstill W. aVery
	 * @version 11/04/2015 - Waightstill W. Avery - original
	 * @param driver
	 */
	public BankOutPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Waightstill W. aVery
	 * @version 11/04/2015 - Waightstill W. Avery - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public BankOutPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W. aVery
	 * @version 11/04/2015 - Waightstill W. Avery - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnSubmit);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W. aVery
	 * @version 11/04/2015 - Waightstill W. Avery - original
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
	public void bankOut(String scenario){
		datatable.setVirtualtablePage("BankOutPage");
		datatable.setVirtualtableScenario(scenario);
		String depositType = datatable.getDataParameter("DepositType");
		String suspendBankout = datatable.getDataParameter("SuspendBankout");
		String advancedDepositAmount = datatable.getDataParameter("AdvancedDepositAmount");
		String retrieveSuspended = datatable.getDataParameter("RetrieveSuspendedBankOut");
		String print = datatable.getDataParameter("Print");
		
		TestReporter.log("Deposit Type: " + depositType);
		enterBagNumberAndDepositType(depositType);
		
		if(depositType.equalsIgnoreCase("Advance Deposit")){
			TestReporter.log("Processing Advanced Deposit");
			enterAdvancedDeposit(advancedDepositAmount);
			processSuccessfulBankOut(print);
			clickLateOrAdvancedExit();
			verifyBankOutLink();
		}else{
			clickNext();
			if(depositType.equalsIgnoreCase("Final Deposit")){
				if(suspendBankout.equalsIgnoreCase("true")){
					TestReporter.log("Processing Suspended Deposit");
					clickSuspendBankOut();
//					cickExit();
					verifyBankOutLink();
					if(retrieveSuspended.equalsIgnoreCase("true")){
						retrieveSuspendedBankOut(print);
					}
				}else{
					processFinalDeposit(print);
				}
			}
		}
	}
	
	private void processFinalDeposit(String print){
		TestReporter.log("Processing Final Deposit");
		clickCompleteBankOut();
		processSuccessfulBankOut(print);
		cickExit();
		verifyBankInLink();
	}
	
	private void enterBagNumberAndDepositType(String depositTYpe){
		String bagNumber = Randomness.randomNumber(4);
		
		initialize();
		pageLoaded(txtBagNumber);
		txtBagNumber.syncVisible(driver);
		txtBagNumber.safeSet(bagNumber);
		initialize();
		txtReEnterBagNumber.safeSet(bagNumber);
		initialize();
		lstDepositType.select(depositTYpe);
		initialize();
		btnSubmit.jsClick(driver);
		try{
			new ProcessingYourRequest().WaitForProcessRequest(driver);
		}catch(Exception e){
			
		}
	}
	
	private void clickNext(){
		initialize();
		pageLoaded(btnNext);
		btnNext.syncVisible(driver);
		btnNext.highlight(driver);
		btnNext.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	private void clickCompleteBankOut(){
		initialize();
		pageLoaded(btnCompleteBankOut);
		btnCompleteBankOut.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	private void clickSuspendBankOut(){
		initialize();
		pageLoaded(btnSuspendBankout);
		btnSuspendBankout.highlight(driver);
		btnSuspendBankout.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	private void processSuccessfulBankOut(String print){
		initialize();
		if(print.equalsIgnoreCase("yes")){
			pageLoaded(btnSuccessfulBankOutYes);
			btnSuccessfulBankOutYes.jsClick(driver);
		}else{
			pageLoaded(btnSuccessfulBankOutNo);
			btnSuccessfulBankOutNo.jsClick(driver);
		}
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	private void cickExit(){
		initialize();
		pageLoaded(btnExit);
		btnExit.highlight(driver);
		btnExit.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver, 60);
	}
	
	private void clickLateOrAdvancedExit(){
		initialize();
		pageLoaded(btnLateOrAdvancedExit);
		btnLateOrAdvancedExit.highlight(driver);
		btnLateOrAdvancedExit.jsClick(driver);
	}
	
	private void verifyBankInLink(){
		ResortFunctionsPage rf = new ResortFunctionsPage(driver);
		TestReporter.assertTrue(rf.verifyBankInLinkDisplayed(), "The Bank In link was not displayed as expected.");
		TestReporter.log("Successfully banked out.");
	}
	
	private void verifyBankOutLink(){
		ResortFunctionsPage rf = new ResortFunctionsPage(driver);
		TestReporter.assertTrue(rf.verifyBankOutLinkDisplayed(), "The Bank Out link was not displayed as expected.");
		TestReporter.log("Successfully banked out.");
	}
	
	public void retrieveSuspendedBankOut(String print){
		ResortFunctionsPage resortFunctionsPage = new ResortFunctionsPage(driver);
		TestReporter.assertTrue(resortFunctionsPage.pageLoaded(), "The Resort Functions Page did not load.");
		resortFunctionsPage.enterBankout();
		
		enterBagNumberAndDepositType("Retrieve Suspended Deposit");
		clickNext();
		clickCompleteBankOut();
		processSuccessfulBankOut(print);
		cickExit();
		verifyBankInLink();
	}
	
	private void enterAdvancedDeposit(String depositAmount){
		initialize();
		pageLoaded(txtAdvanceDepositAmount);
		System.out.println("Advance Deposit Amount: " + depositAmount);
		txtAdvanceDepositAmount.syncVisible(driver);
		txtAdvanceDepositAmount.highlight(driver);
		txtAdvanceDepositAmount.safeSet(depositAmount);
		btnCompleteAdvancedDeposit.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
}

