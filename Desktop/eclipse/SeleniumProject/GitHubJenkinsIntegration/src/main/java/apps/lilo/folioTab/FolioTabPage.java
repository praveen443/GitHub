package apps.lilo.folioTab;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

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
import core.interfaces.Webtable;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.LinkImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

/**
 * @summary Contains the elements and methods to interact with the LILO UI Foio
 *          Tab page
 * @version Created 12/08/2014
 * @author Waightstill W. Avery
 */
public class FolioTabPage {
	// *********************************
	// *** Main Guest Tab Page Fields ***
	// *********************************
	private int loopCount = 0;
	private int timeout = WebDriverSetup.getDefaultTestTimeout();
	@SuppressWarnings("unused")
	private String cardNumber;
	private String paymentAmount;
	@SuppressWarnings("unused")
	private String refundCardNumber;
	private String refundPaymentAmount;
	private String cardNumberMasked;
	@SuppressWarnings("unused")
	private String refundCardNumberMasked;
	private String uiPaymentMethod = "";
	private boolean PaymentWindow_Status;
	private Datatable datatable = new Datatable();

	// Table locator xpath
	private String folioActivityFormTable = "//*[@id='viewFolioTabFormId:dataTable:tb']";
	/*
	 * Column Header texts; the columns can change location, so these texts can
	 * be used to located the columns actual location in the table
	 */
	@SuppressWarnings("unused")
	private final static String fulfillmentDateColumnHeaderText = "Fulfillment Date";
	private final static String ticketColumnHeaderText = "Ticket #";
	private final static String descriptionColumnHeaderText = "Description";
	@SuppressWarnings("unused")
	private final static String roomColumnHeaderText = "Room";
	@SuppressWarnings("unused")
	private final static String typeColumnHeaderText = "Type";
	@SuppressWarnings("unused")
	private final static String chargedByColumnHeaderText = "Charged By";
	@SuppressWarnings("unused")
	private final static String chargesColumnHeaderText = "Charges";
	@SuppressWarnings("unused")
	private final static String paymentColumnHeaderName = "Payment";
	@SuppressWarnings("unused")
	private final static String balanceColumnHeaderName = "Balance";
	@SuppressWarnings("unused")
	private final static String postingDateColumnHeadername = "Posting Date";
	@SuppressWarnings("unused")
	private final static String accountingDateColumnHeaderText = "Acct. Date";
	@SuppressWarnings("unused")
	private final static String pendingColumnHeaderText = "Pending";
	@SuppressWarnings("unused")
	private final static String preArrivalColumnHeaderText = "Pre-Arrival";
	@SuppressWarnings("unused")
	private final static String postArrivalColumnHeaderText = "Post-Arrival";
	@SuppressWarnings("unused")
	private final static String propertyColumnHeaderName = "Property";

	// ***********************************
	// *** Main Guest Tab Page Elements ***
	// ***********************************

	// Take Payment button
	@FindBy(id = "viewFolioTabFormId:takepaymentid")
	private Link lnkTakePayment;

	// Folio Main Table webtable
	@FindBy(id = "viewFolioTabFormId:dataTable")
	private Webtable tblMainData;

	// Exit Button
	@FindBy(id = "viewFolioTabFormId:folioExitButtonId")
	private Button btnExit;

	@FindBy(xpath = "//*[@id='exitFolioFrm']/input[2]")
	private Button btnExitImage;
	//
	@FindBy(id = "viewFolioTabFormId:dataTable")
	private Webtable tblFolioDataTable;
	
	@FindBy(id = "fullSearch:FolioTypeResFolioSearchResult")
	private Webtable tblReservationSearch;
	

	@FindBy(id = "viewFolioTabFormId:takePaidOutId")
	private Button btnPaidOut;

	@FindBy(id = "viewFolioTabFormId:printView")
	private Button btnPrintView;

	@FindBy(id = "printViewColSelModalPanelHeader")
	private Label lblPrintViewFolioActivityModal;

	@FindBy(id = "pritnViewColSelForm:close")
	private Button btnPrintViewClose;

	@FindBy(id = "viewFolioTabFormId:adjustmentsButton")
	private Button btnAdjustments;

	@FindBy(id = "viewFolioTabFormId:couponGridId")
	private Button btnCouponDetails;

	// Payment description link
	@FindBy(id = "viewFolioTabFormId:dataTable:0:ouputLinkDescMinus")
	private Link lnkPaymentDetails;

	// Payment ticket element
	@FindBy(id = "paymentTicketDetailModalPanelAccContentDiv")
	private Element eleTicketDetails;

	// Terminal ID element
	@FindBy(id = "paymentFormAcc:terminalId")
	private Element eleTerminalID;

	// Create Page Object for the Element to get Ticket Number

	// click Expansion
	@FindBy(id = "viewFolioTabFormId:dataTable:1:ouputLinkDescPlus")
	private Element linkTABleexpansion;

  	@FindBy(id = "viewFolioTabFormId:dataTable:0:outputTicket") 
  	private Element eleTicketNumber;
  	
  	//Find Button 
  	@FindBy(id = "fullSearch:folioSearchFindButtonId")
  	private Button btnFind;
  	
  	//Create Page object for Folio Search Tab
    @FindBy(id = "fullSearch:folioSearchTabPanel_lbl")
    private Label lblFolioSearchTab;
    
    //Create Page object for List SearchType
    @FindBy(id="fullSearch:SelectSearchType")
    private Listbox lstSearchType;
  //Create Page object for List SearchType
    @FindBy(id="fullSearch:SelectFolioTypeSus")
    private Listbox lstFolioType;
    
    
    //Create Page Object for Link Ticket Number  
  	@FindBy(id = "fullSearch:TicketNumberSearchResult:0:ticketNoForViewRes")
  	private Link lnkTicketNumber;
  	
  	//Create Page Object for the  Ticket Number  Textbox
  	@FindBy(id = "fullSearch:ticketNumber")
  	private Textbox txtTicketNumber;
  	
  	@FindBy(id = "viewFolioTabFormId:Print")
  	private Button btnFolioFormIDPrint;

  	@FindBy(id = "fullSearch:resNumId")
  	private Textbox txtReservationNumber;
  	
	/*//Click Print Button 
	@FindBy(id = "viewFolioTabFormId:dataTable:0:outputTicket")
	private Element eleTicketNumber;

	// Find Button
	@FindBy(id = "fullSearch:folioSearchFindButtonId")
	private Button btnFind;

	// Create Page object for Folio Search Tab
	@FindBy(id = "fullSearch:folioSearchTabPanel_lbl")
	private Label lblFolioSearchTab;

	// Create Page object for List SearchType
	@FindBy(id = "fullSearch:SelectSearchType")
	private Listbox lstSearchType;*/

	/*// Create Page Object for Link Ticket Number
	@FindBy(id = "fullSearch:TicketNumberSearchResult:0:ticketNoForViewRes")
	private Link lnkTicketNumber;

	// Create Page Object for the Ticket Number Textbox
	@FindBy(id = "fullSearch:ticketNumber")
	private Textbox txtTicketNumber;

	@FindBy(id = "viewFolioTabFormId:Print")
	private Button btnFolioFormIDPrint;*/

	// Click Print Button
	@FindBy(id = "newMediaEncodeForm:printBtn")
	private Button btnPrint;

	// Print Menu Div
	@FindBy(id = "newMediaEncodeForm:Print_menu")
	private Element elePrintMenu;

	// Print Pcr Link
	@FindBy(id = "newMediaEncodeForm:print_pcr:anchor")
	private Link lnkPrintPCR;

	// Print Entitlement Link
	@FindBy(id = "newMediaEncodeForm:printentitlement:anchor")
	private Link lnkPrintE;

	// Print MagiBandId
	@FindBy(id = "newMediaEncodeForm:printETickets:anchor")
	private Link lnkMagicBandId;

	// Click Primary Guest CheckBox
	@FindBy(id = "newMediaEncodeForm:kttwDetails:0:rowCheckBoxId")
	private Checkbox chkGuest;

	// History Button
	@FindBy(id = "newMediaEncodeForm:viewHistory")
	private Link lnkHistory;

	// View KTTW HIstory Window Close button
	@FindBy(id = "kttwHistoryForm:closeButtonId")
	private Button btnHistoryKTTWClose;

	// PaidOut amount element
	@FindBy(id = "viewFolioTabFormId:dataTable:0:outputcgrPayment")
	private Element elePaidOut;

	// Cash amount element
	@FindBy(id = "viewFolioTabFormId:dataTable:1:outputpay")
	private Element eleCash;

	// Balance amount element
	@FindBy(id = "viewFolioTabFormId:dataTable:1:outputBalance")
	private Element eleBalance;
	// Grab the Cancel button
	@FindBy(css = "a.actionButton-grey:nth-child(2)")
	private static Button btnCancel;

	// Payment Description
	@FindBy(xpath = "viewFolioTabFormId:dataTable:3:ouputLinkDescMinus")
	private Element elePaymentCardInfo;

	// Payment table
	@FindBy(xpath = "//*[@id='viewFolioTabFormId:dataTable:tb']")
	private Element elePaymentTable;

	// Payment Ticket Details Window
	@FindBy(id = "paymentTicketDetailModalPanelAccCDiv")
	private Element elepaymentTicketDetailWindow;

	// Close Payment Ticket Details Window
	@FindBy(id = "paymentFormAcc:close")
	private Element eleClosePaymentWindow;

	// The payment amount on the Ticket Details Page
	@FindBy(id = "paymentFormAcc:amountTxt")
	private Element eleTicketDetailsPaymentAmount;

	// Foliotab View payment details table
	@FindBy(id = "viewFolioTabFormId:dataTable:tb")
	private Webtable tblViewFolioTab;
	
	//Textbox Reservation Number
	@FindBy(id = "fullSearch:resNumId")
	private Textbox txtReservationNum;
	
	//Textbox Reservation Number
	@FindBy(id="fullSearch:FolioTypeResFolioSearchResult")
	private Webtable tblFolioTypeResv;
	
	//Link Reservation Number
	@FindBy(xpath=".//*[@id='fullSearch:FolioTypeResFolioSearchResult']/tbody/tr/td[2]/a")
	private Link lnkResvNumber;
	
	// *********************
	// ** Build page area **
	// *********************
	private WebDriver driver;

	/**
	 * 
	 * @summary Constructor to initialize the page
	 * @version Created 10/29/2014
	 * @author Waightstill W Avery
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public FolioTabPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary : Created this pageloaded assertion to verify whether the folio
	 *          search page is loaded.
	 * @version : Created 11/04/2015
	 * @author : Praveen Namburi
	 * @throws : NA
	 * @return : Boolean, true if the page is loaded, false otherwise
	 */
	public boolean folioSearchPageIsLoaded() {

		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnFind);
	}

	/**
	 * @summary : Created method to synchronize and find the element for Ticket
	 *          number in folio page.
	 * @version : Created 11/04/2015
	 * @author : Praveen Namburi
	 * @throws : NA
	 * @return : NA
	 */
	public void folioTabLoaded() {

		while (!eleTicketNumber.syncVisible(driver)) {
			initialize();
		}
		eleTicketNumber.syncVisible(driver);
	}

	/**
	 * @summary : Created method to synchronize and find the button
	 *          'Adjustments' in folio page.
	 * @version : Created 11/04/2015
	 * @author : Praveen Namburi
	 * @throws : NA
	 * @return : NA
	 */
	public void folioTabPageLoaded() {

		while (!btnAdjustments.syncVisible(driver)) {
			initialize();
		}
		btnAdjustments.syncVisible(driver);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				lnkTakePayment);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				element);
	}

	public FolioTabPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	// **********************************
	// *** Main Guest Tab Interactions ***
	// **********************************

	public void clickHistory() {
		lnkHistory.click();
	}

	public void clickHistoryKTTWClose() {
		pageLoaded(btnHistoryKTTWClose);
		btnHistoryKTTWClose.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		TestReporter.logStep("HistoryKTTW Window Closed");
	}

	public void verifyNoAdditionalAdultCosts() {
		pageLoaded(tblMainData);
		boolean additionalAdultFound = false;
		int currentRowCount = tblMainData.getRowCount();
		Element element = new ElementImpl(
				driver.findElement(By
						.xpath("//table[@id='viewFolioTabFormId:dataTable']/tbody/tr[2]/td[4]/input")));
		element.highlight(driver);

		element.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		loopCount = 0;
		int newRowCount;
		do {
			Sleeper.sleep(1000);
			Assert.assertNotEquals(
					loopCount++,
					timeout,
					"The main webtable was not expanded after ["
							+ String.valueOf(timeout) + "] seconds.");
			pageLoaded(tblMainData);
			initialize();
			newRowCount = tblMainData.getRowCount();
		} while (newRowCount == currentRowCount);

		List<WebElement> rowList = driver.findElements(By
				.xpath("//table[@id='viewFolioTabFormId:dataTable']/tbody/tr"));
		for (loopCount = 0; loopCount < rowList.size(); loopCount++) {
			element = new ElementImpl(rowList.get(loopCount));
			if (element.getText().toLowerCase().contains("additional adult")) {
				additionalAdultFound = true;
				break;
			}
		}
		Assert.assertEquals(additionalAdultFound, false,
				"An additional adult fee was found in the folio when none were expected.");
	}

	public void verifyAdditionalAdultCosts() {
		pageLoaded(tblMainData);
		boolean additionalAdultFound = false;
		int currentRowCount = tblMainData.getRowCount();
		Element element = new ElementImpl(
				driver.findElement(By
						.xpath("//table[@id='viewFolioTabFormId:dataTable']/tbody/tr[2]/td[4]/input")));
		element.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		loopCount = 0;
		int newRowCount;
		do {
			Sleeper.sleep(1000);
			Assert.assertNotEquals(
					loopCount++,
					timeout,
					"The main webtable was not expanded after ["
							+ String.valueOf(timeout) + "] seconds.");
			pageLoaded(tblMainData);
			initialize();
			newRowCount = tblMainData.getRowCount();
		} while (newRowCount == currentRowCount);

		List<WebElement> rowList = driver.findElements(By
				.xpath("//table[@id='viewFolioTabFormId:dataTable']/tbody/tr"));
		for (loopCount = 0; loopCount < rowList.size(); loopCount++) {
			element = new ElementImpl(rowList.get(loopCount));
			if (element.getText().toLowerCase().contains("additional adult")) {
				additionalAdultFound = true;
				break;
			}
		}
		Assert.assertEquals(additionalAdultFound, true,
				"An additional adult fee was not found in the folio as expected.");
	}

	public void upgradeAsnoChages(String previousResort,
			String upgradedResortName, int row_Number) throws Exception {
		//DataProvider_ExcelSheet datapro = new DataProvider_ExcelSheet();
		List<String> add_price = new ArrayList<String>();
		String room_actual_price = "";
		String room_minus_price = "";
		String room_original_price = "";
		for (int m = 2; m < 5; m++) {
			// Get the resort Name and split it and Take first only
			String resortNameslipt = driver
					.findElement(
							By.xpath("//table[@id='viewFolioTabFormId:dataTable']/tbody/tr["
									+ m + "]/td[4]/a")).getText().trim();
			String split[] = resortNameslipt.split("-");
			// Match with Previous resort Name

			if (split[0].trim().equalsIgnoreCase(previousResort)) {
				// Get the content "X" if match the add the room price into a
				// list
				String get_X = driver
						.findElement(
								By.xpath("//table[@id='viewFolioTabFormId:dataTable']/tbody/tr["
										+ m + "]/td[6]")).getText().trim();
				if (get_X.equalsIgnoreCase("X")) {
					// Get the exiting price
					String actual_Price = driver
							.findElement(
									By.xpath("//table[@id='viewFolioTabFormId:dataTable']/tbody/tr["
											+ m + "]/td[8]")).getText().trim();
					add_price.add(actual_Price);
				}

			}
		}
		room_actual_price = add_price.get(0);
		room_minus_price = add_price.get(1);
		if (room_actual_price.length() < room_minus_price.length()) {
			room_original_price = room_actual_price;
		} else {
			room_original_price = room_minus_price;
		}
		for (int i = 2; i < 5; i++) {
			// Get the price
			String actual_Price = driver
					.findElement(
							By.xpath("//table[@id='viewFolioTabFormId:dataTable']/tbody/tr["
									+ i + "]/td[8]")).getText().trim();
			//datapro.writeDataintoExcel(row_Number, 13, actual_Price);
			// Get the value of resort from table and split it
			String resortNameslipt = driver
					.findElement(
							By.xpath("//table[@id='viewFolioTabFormId:dataTable']/tbody/tr["
									+ i + "]/td[4]/a")).getText().trim();
			String split[] = resortNameslipt.split("-");
			// Upgraded resort Name

			if (split[0].trim().equalsIgnoreCase(upgradedResortName)) {
				// Get the length of "X" if length equals to zero
				String get_X = driver
						.findElement(
								By.xpath("//table[@id='viewFolioTabFormId:dataTable']/tbody/tr["
										+ i + "]/td[6]")).getText().trim();
				if (get_X.length() == 0) {
					String get_changed_price = driver
							.findElement(
									By.xpath("//table[@id='viewFolioTabFormId:dataTable']/tbody/tr["
											+ i + "]/td[8]")).getText().trim();
					//datapro.writeDataintoExcel(row_Number, 14,
							//get_changed_price);

					Assert.assertTrue(get_changed_price.trim()
							.equalsIgnoreCase(room_original_price),
							"Actual price is not matched with room original price");

				}

			}

		}

	}

	/**
	 * 
	 * @summary Method that return current room price
	 * @version Created 07/23/2015
	 * @author Brajesh Ahirwar
	 * @param NA
	 * @throws Exception
	 *             if data table is not get loaded or parameters are not found
	 * @return room price
	 * 
	 */
	public String getRoomPrice() {
		tblMainData.syncVisible(driver, 60, false);
		pageLoaded(tblMainData);
		initialize();
		String get_changed_price = driver
				.findElement(
						By.xpath("//table[@id='viewFolioTabFormId:dataTable']/tbody/tr[2]/td[8]"))
				.getText().trim();
		TestReporter.logStep("Room Price :" + get_changed_price);
		return get_changed_price;
	}

	public void clickOnExit() {
		btnExit.syncEnabled(driver);
		btnExit.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);

	}

	public void clickOnExitImage() {
		pageLoaded(btnExitImage);
		btnExitImage.syncVisible(driver);
		btnExitImage.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);

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
	public void takePayment(String scenario) {
		System.out.println(scenario);
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

		String winHandleBefore = driver.getWindowHandle();
		initialize();
		if (paymentType.equalsIgnoreCase("paidout")) {
			pageLoaded(btnPaidOut);
			btnPaidOut.click();
		} else {
			pageLoaded(lnkTakePayment);
			lnkTakePayment.click();
		}

		/*
		 * Commenting out to use more succinct methods
		 */
		// do{
		// Sleeper.sleep(500);
		// }while(driver.getWindowHandles().size() < 2);
		//
		// for(String handle : driver.getWindowHandles()){
		// if(driver.switchTo().window(handle).getTitle().contains("Apply Payment")){
		// break;
		// }
		// }
		System.out.println();
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Apply Payment");

		PaymentUIWindow paymentUIWindow = new PaymentUIWindow(driver);
		paymentUIWindow.takePayment(
				PaymentUIWindow.getpaymentTypeEnum(paymentType),
				PaymentUIWindow.getpaymentMethodEnum(paymentMethod), status,
				delay, incidentals, amount, expiredCC, primaryGuestInfo,
				errorMessage);
		cardNumber = paymentUIWindow.getCardNumber().replace("-", "");
		cardNumberMasked = paymentUIWindow.getCardNumberMasked().replace("-",
				"");
		paymentAmount = paymentUIWindow.getPaymentAmount().replace("$", "");
		uiPaymentMethod = paymentUIWindow.getPaymentMethod();
		driver.switchTo().window(winHandleBefore);

		if (datatable.getDataParameter("RefundCardPayment").equalsIgnoreCase(
				"true")) {
			winHandleBefore = driver.getWindowHandle();
			initialize();
			pageLoaded(lnkTakePayment);
			lnkTakePayment.click();

			do {
				Sleeper.sleep(500);
			} while (driver.getWindowHandles().size() < 2);

			for (String handle : driver.getWindowHandles()) {
				if (driver.switchTo().window(handle).getTitle()
						.contains("Apply Payment")) {
					break;
				}
			}
			paymentUIWindow.refundCardPayment(paymentType, paymentMethod, "-"
					+ amount);
			refundCardNumber = paymentUIWindow.getCardNumber().replace("-", "");
			refundPaymentAmount = paymentUIWindow.getPaymentAmount().replace(
					"$", "");
			driver.switchTo().window(winHandleBefore);
		}

		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * This method passes card payment fields defined by takePayment(scenario)
	 * to another method that verifies the card payment history on the Folio tab
	 * 
	 * @author Waightstill W Avery
	 * @version 11/06/2015 - original
	 */
	public void verifyCardPaymentHistory() {
		verifyPaymentHistory(cardNumberMasked, paymentAmount);
	}

	/**
	 * This method passes a masked card number and payment amount defined by
	 * takePayment(scenario) to another method that verifies the card payment
	 * history on the Folio tab
	 * 
	 * @author Waightstill W Avery
	 * @version 11/06/2015 - original
	 */
	public void verifyRefundCardPaymentHistory() {
		verifyPaymentHistory(cardNumberMasked, refundPaymentAmount);
	}

	/**
	 * This method passes cash payment fields defined by takePayment(scenario)
	 * to another method that verifies the card payment history on the Folio tab
	 * 
	 * @author Waightstill W Avery
	 * @version 11/06/2015 - original
	 */
	public void verifyCashPaymentHistory() {
		verifyPaymentHistory(uiPaymentMethod, paymentAmount);
	}

	/**
	 * This method verifes payment history on the Folio tab
	 * 
	 * @author Waightstill W Avery
	 * @version 11/06/2015 - original
	 * @param paymentMethod
	 *            - payment method used in the payment process
	 * @param paymentAmount
	 *            - payment amount used in the payment process
	 */
	public void verifyPaymentHistory(String paymentMethod, String paymentAmount) {
		System.out.println("Payment Method: " + paymentMethod.trim());
		System.out.println("Payment Amount: "
				+ paymentAmount.replace(",", "").replace("-", "")
						.replace("+", "").replace("$", "").trim());
		Sleeper.sleep(2000);
		initialize();
		pageLoaded(tblFolioDataTable);
		List<WebElement> rows = tblFolioDataTable.findElements(By
				.xpath("tbody/tr"));
		boolean paymentFound = false;
		boolean amountFound = false;

		for (WebElement row : rows) {
			paymentFound = false;
			amountFound = false;
			if (row.getText().contains(paymentMethod.trim())) {
				paymentFound = true;
				if (row.getText()
						.replace(",", "")
						.replace("-", "")
						.replace("+", "")
						.replace("$", "")
						.contains(
								paymentAmount.replace(",", "").replace("-", "")
										.replace("+", "").replace("$", "")
										.trim())) {
					amountFound = true;
					break;
				}
			}
		}
		TestReporter
				.assertTrue(
						paymentFound && amountFound,
						"The payment method ["
								+ paymentMethod
								+ "] was not found to be associated with the payment amount ["
								+ paymentAmount + "].");
	}

	/**
	 * This method iterates over a string array and calls a method that verifies
	 * payment history on the Folio tab
	 * 
	 * @author Waightstill W Avery
	 * @version 11/06/2015 - original
	 * @param paymentMethods
	 * @param paymentAmounts
	 */
	public void verifyPaymentHistory(String[] paymentMethods,
			String[] paymentAmounts) {
		TestReporter.assertTrue(
				paymentMethods.length == paymentAmounts.length,
				"The number of payment methods ["
						+ String.valueOf(paymentMethods.length)
						+ "] does not match the number of payment amounts ["
						+ String.valueOf(paymentAmounts.length) + "]'");
		for (int counter = 0; counter < paymentMethods.length; counter++) {
			verifyPaymentHistory(paymentMethods[counter],
					paymentAmounts[counter]);
		}
	}

	/**
	 * This method verifies the Paid Out payment process on the Folio tab
	 */
	public void verifyPaidOut() {
		System.out.println("Payment Method: " + uiPaymentMethod.trim());
		System.out.println("Payment Amount: "
				+ paymentAmount.replace(",", "").replace("-", "")
						.replace("+", "").replace("$", "").trim());
		Sleeper.sleep(2000);
		initialize();
		pageLoaded(tblFolioDataTable);
		List<WebElement> rows = tblFolioDataTable.findElements(By
				.xpath("tbody/tr"));
		boolean paymentFound = false;
		boolean amountFound = false;

		for (WebElement row : rows) {
			paymentFound = false;
			amountFound = false;
			if (row.getText().contains(uiPaymentMethod.trim())) {
				paymentFound = true;
				if (row.getText()
						.replace(",", "")
						.replace("-", "")
						.replace("+", "")
						.replace("$", "")
						.contains(
								paymentAmount.replace(",", "").replace("-", "")
										.replace("+", "").replace("$", "")
										.trim())) {
					amountFound = true;
					break;
				}
			}
		}
		TestReporter
				.assertTrue(
						paymentFound && amountFound,
						"The payment method ["
								+ uiPaymentMethod
								+ "] was not found to be associated with the payment amount ["
								+ paymentAmount + "].");
	}

	public void verifyPrintViewButton() {
		initialize();
		pageLoaded(btnPrintView);
		btnPrintView.click();

		initialize();
		pageLoaded(lblPrintViewFolioActivityModal);
		TestReporter
				.assertTrue(
						lblPrintViewFolioActivityModal.syncVisible(driver),
						"The printer selection window was not displayed ["
								+ String.valueOf(WebDriverSetup
										.getDefaultTestTimeout())
								+ "] seconds after clicking PRint View.");

		btnPrintViewClose.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
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
		datatable.setVirtualtablePath(Datatable.PAYMENTUI_MASTER_DATA_PATH);
		datatable.setVirtualtablePage("PaymentUI");
		datatable.setVirtualtableScenario(scenario);

		String[] paymentType = datatable.getDataParameter("PaymentType").split(
				";");
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
		String[] strIncidentals = datatable.getDataParameter("Incidentals")
				.split(";");
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
		String[] primaryGuestInfo = datatable.getDataParameter(
				"UsePrimaryGuestInfo").split(";");
		@SuppressWarnings("unused")
		String[] cancel = datatable.getDataParameter("CancelButton").split(";");

		String winHandleBefore = driver.getWindowHandle();
		initialize();
		pageLoaded(lnkTakePayment);
		lnkTakePayment.click();

		do {
			Sleeper.sleep(500);
		} while (driver.getWindowHandles().size() < 2);

		for (String handle : driver.getWindowHandles()) {
			if (driver.switchTo().window(handle).getTitle()
					.contains("Apply Payment")) {
				break;
			}
		}

		for (int pays = 0; pays < types.length; pays++) {
			System.out.println("Payment type " + String.valueOf(pays) + ": "
					+ paymentType[pays]);
			System.out.println("Payment method " + String.valueOf(pays) + ": "
					+ paymentMethod[pays]);
			System.out.println("Payment status " + String.valueOf(pays) + ": "
					+ status[pays]);
			System.out.println("Payment delay " + String.valueOf(pays) + ": "
					+ delay[pays]);
			System.out.println("Payment incidentals " + String.valueOf(pays)
					+ ": " + strIncidentals[pays]);
			System.out.println("Payment amount " + String.valueOf(pays) + ": "
					+ amount[pays]);

			PaymentUIWindow paymentUIWindow = new PaymentUIWindow(driver);
			paymentUIWindow.takePaymentWithSplit(
					PaymentUIWindow.getpaymentTypeEnum(paymentType[pays]),
					PaymentUIWindow.getpaymentMethodEnum(paymentMethod[pays]),
					status[pays], delay[pays], incidentals[pays], amount[pays],
					splits[pays], expiredCC[pays], primaryGuestInfo[pays]);
			cardNumber = paymentUIWindow.getCardNumber().replace("-", "");
			cardNumberMasked = paymentUIWindow.getCardNumberMasked().replace(
					"-", "");
			paymentAmount = paymentUIWindow.getPaymentAmount().replace("$", "");
			uiPaymentMethod = paymentUIWindow.getPaymentMethod();
		}
		driver.switchTo().window(winHandleBefore);

		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public void clickAdjustments() {
		initialize();
		pageLoaded(btnAdjustments);
		btnAdjustments.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void verifyTicketNumber(String ticketNumber) {
		boolean ticketFound = false;
		int ticketColumnNumber = 0;

		ticketColumnNumber = locateColumnByName(ticketColumnHeaderText);
		System.out.println("Ticket Column Number: " + ticketColumnNumber);

		List<WebElement> rows = tblMainData.findElements(By.xpath("tbody/tr"));
		System.out.println("Number of rows: " + rows.size());
		System.out.print("Searching for ticket number [" + ticketNumber
				+ "]....");
		for (WebElement row : rows) {
			if (row.getText().contains(ticketNumber)) {
				new ElementImpl(row).highlight(driver);
				ticketFound = true;
				break;
			}
		}
		System.out.println("done");
		TestReporter.assertTrue(ticketFound, "The ticket number ["
				+ ticketNumber + "] was not found.");
	}

	private int locateColumnByName(String columnName) {
		int columnNumber = -1;
		int counter = 0;
		List<WebElement> headerColumns = tblMainData.findElements(By
				.xpath("thead/tr/th"));
		System.out.println("Number of column headers: " + headerColumns.size());
		System.out.print("Locating [" + columnName + "] column number...");
		for (WebElement column : headerColumns) {
			counter++;
			if (column.getText().equalsIgnoreCase(columnName)) {
				new ElementImpl(column).highlight(driver);
				columnNumber = counter;
				break;
			}
		}
		System.out.println(columnNumber);
		TestReporter.assertTrue(columnNumber > -1, "The column [" + columnName
				+ "] was not found.");
		return columnNumber;
	}

	public void clickDescriptionLink(String description) {
		int columnNumber = locateColumnByName(descriptionColumnHeaderText);
		int rowNumber = -1;

		List<WebElement> rows = tblMainData.findElements(By.xpath("tbody/tr"));
		System.out.println("Number of rows: " + rows.size());
		System.out.print("Searching for row with description [" + description
				+ "]....");

		int rowCounter = 0;
		for (WebElement row : rows) {
			rowCounter++;
			if (row.getText().contains(description)) {
				rowNumber = rowCounter;
				break;
			}
		}
		System.out.println("done.");
		TestReporter.assertTrue(rowNumber > -1,
				"A row was not found that contained [" + description
						+ "] in the description");

		System.out.println(rowNumber);
		Element descriptionLink = new ElementImpl(
				tblMainData.findElement(By.xpath("tbody/tr[" + rowNumber
						+ "]/td[" + columnNumber + "]/a")));
		descriptionLink.highlight(driver);
		descriptionLink.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickCouponDetails() {
		initialize();
		pageLoaded(btnCouponDetails);
		btnCouponDetails.click();
	}

	/**
	 * @summary : Method to click on the Adjustment button
	 * @version : Created 11/04/2015
	 * @Author : Praveen Namburi
	 * @return : NA
	 */
	public void clickAdjustmentButton() {
		folioTabPageLoaded();
		btnAdjustments.syncEnabled(driver);
		btnAdjustments.jsClick(driver);

		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * 
	 * @summary Created Method to get the TicketNumber
	 * @version Created 11/4/2015
	 * @author Praveen Namburi
	 * @param NA
	 * @return String
	 * 
	 */
	public String getTicketNumber() {
		folioTabLoaded();
		pageLoaded(eleTicketNumber);
		String TicketNumbr = eleTicketNumber.getText();
		// System.out.println("TicketNumber is : " + TicketNumbr);
		return TicketNumbr;
	}

	/**
	 * 
	 * @summary Calling methods in method to navigate To FolioSearch Tab and
	 *          SetSearchType
	 * @version Created 11/4/2015
	 * @author Praveen Namburi
	 * @param scenario
	 * @return NA
	 * 
	 */
	public void navigateToFolioandSetSearchType(String scenario)
			throws Exception {

		clickFolioSearchTab();

		setSearchAndFolioType(scenario);
	}

	/**
	 * @summary Created Method to set the searchType to TicketNumber
	 * @version Created 11/4/2015
	 * @author Praveen Namburi
	 * @param scenario
	 * @return NA
	 */
	public void clickFolioSearchTab() {

		pageLoaded(lblFolioSearchTab);
		lblFolioSearchTab.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * 
	 * @summary Created Method to set the list searchType to TicketNumber
	 * @version Created 11/4/2015
	 * @author Praveen Namburi
	 * @param THe
	 *            datatable scenario
	 * @return NA
	 * 
	 */
//	public void setSearchType(String scenario) {

	public void setSearchAndFolioType(String scenario){		    

		//Setup the virtual table name and scenarion name
		datatable.setVirtualtablePage("FolioTabPage");
		datatable.setVirtualtableScenario(scenario);

		// Select the option in the searchType Listbox
		lstSearchType.click();
		lstSearchType.select(datatable.getDataParameter("SelectSearchOption"));	
		
		//lstSearchType.select(datatable.getDataParameter("SelectSearchOption"));

		if(!datatable.getDataParameter("SelectFolioType").isEmpty()) {
			pageLoaded(lstFolioType);
			lstFolioType.select(datatable.getDataParameter("SelectFolioType"));
		}
	}

	/**
	 * @summary: Method to set the SearchType and Locate folio based on the 
	 * 			 switch case and datatable condition(Used for data-iterations).
	 * @author: Praveen Namburi, @version: Created 04/05/2016
	 * @param scenario, TicketNumber, reservationNum
	 */
	public void setSearchTypeAndLocateFolio(String scenario, String TicketNumber,String reservationNum){
		  datatable.setVirtualtablePath(datatable.LILO_MASTER_DATA_PATH);
		  datatable.setVirtualtablePage("FolioTabPage");
		  datatable.setVirtualtableScenario(scenario);

		  String searchType = datatable.getDataParameter("SelectSearchOption");
		  switch(searchType.trim())
		  {
		  case "Ticket Number": 
			 //Set SearchType With TicketNum 
			 setSearchTypeWithTicketNum(scenario, TicketNumber);
		   break;
		  case "Folio Type":
			 //Set SearchType With Folio
			 setSearchTypeWithFolio(scenario, reservationNum);
		  default:
		   break;
		  } 
	}
	
	/**
	 * @summary: Method to Set searchType as ticketNumber and verify it.
	 * @author: Praveen namburi, @version: Created 04/05/2016
	 * @param scenario, TicketNumber
	 */
	public void setSearchTypeWithTicketNum(String scenario,String TicketNumber){
		//Setup the virtual table name and scenarion name
		datatable.setVirtualtablePage("FolioTabPage");
		datatable.setVirtualtableScenario(scenario);

		//Select the option in the searchType Listbox 
		lstSearchType.click();
		lstSearchType.select(datatable.getDataParameter("SelectSearchOption"));
		
		setTicketNumber(TicketNumber);
		findandClickTicketNumber();
	}
	
	/**
	 * @summary: Method to set Searchtype as folio and Verify the Reservation number.
	 * @author: Praveens namburi, @version: Created 04/05/2016
	 * @param scenario, @param reservationNum
	 */
	public void setSearchTypeWithFolio(String scenario, String reservationNum){
		//Setup the virtual table name and scenarion name
		datatable.setVirtualtablePage("FolioTabPage");
		datatable.setVirtualtableScenario(scenario);
		String searchType = datatable.getDataParameter("SelectSearchOption");
		String folioType = datatable.getDataParameter("SelectFolioType");
		
		//Select the option in SearchType Listbox 
		lstSearchType.click();
		lstSearchType.select(searchType);
		
		//Select the option in FolioType listbox
		pageLoaded(lstFolioType);
		lstFolioType.click();
		lstFolioType.select(folioType);
		
		//Set reservation number and click on find btn.
		pageLoaded(txtReservationNum);
		txtReservationNum.safeSet(reservationNum);
		
		findAndClickReservationNumber(reservationNum);
	}
	
	/**
	 * @summary: Method to find and verify the Reservation number after seraching for the folioType.
	 * @version: Created 04/05/2016 , @auhtor: Praveen namburi
	 * @param reservationNum
	 */
	public void findAndClickReservationNumber(String reservationNum){
		clickFindButton();
		
		pageLoaded(tblFolioTypeResv);
		tblFolioTypeResv.highlight(driver);
		TestReporter.assertTrue(tblFolioTypeResv.isDisplayed(), "FolioType resv search result table is not displayed.");
		
		List<WebElement> totalRows = tblFolioTypeResv.findElements(By.tagName("tr"));
		TestReporter.assertGreaterThanZero(totalRows.size());
		
		for(int row = 1; row <= totalRows.size()-1; row++){
			String linkResvNum = ".//*[@id='fullSearch:FolioTypeResFolioSearchResult']/tbody/tr["+row+"]/td[2]/a";
			for(WebElement link : totalRows){
				Link lnk = new LinkImpl(driver.findElement(By.xpath(linkResvNum)));
				if(lnk.getText().contains(reservationNum)){
					TestReporter.assertTrue(lnk.getText().contains(reservationNum), "The Reservation number is not found in the FolioType table.");
					lnk.highlight(driver);
					lnk.jsClick(driver);
					break;
				}
			}
		}
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}
	/**
	 * 
	 * @summary Calling methods in method to click find button and to click on
	 *          TicketNumber Link
	 * @version Created 11/4/2015
	 * @author Praveen Namburi
	 * @param scenario
	 * @return NA
	 * 
	 */
	public void findandClickTicketNumber() {
		clickFindButton();
		clickOnTicketNumberLink();
	}
	
	public void searchReservation(String reservationNumber){
		pageLoaded(txtReservationNumber);
		txtReservationNumber.set(reservationNumber);
		clickFindButton();
	}

	public void clickFirstFolioId(){
		pageLoaded(tblReservationSearch);
		new LinkImpl(tblReservationSearch.getCell(driver, 2, 1).findElement(By.tagName("a"))).jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	public void clickFindButton() {

		pageLoaded(btnFind);
		btnFind.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * @summary : Method to get the ticket number and verify it.
	 * @author : Praveen Namburi
	 * @version : Created 11/05/2015
	 * @return : NA
	 * @throws : error - if assertion fail, else - status as pass.
	 * 
	 */
	public void clickOnTicketNumberLink() {
		pageLoaded(lnkTicketNumber);
		String getticketNum = lnkTicketNumber.getText();

		//Added the assertion for validating the Ticket Number.
		TestReporter.assertEquals(getTicketNumber(), getticketNum,"The Expected and Actual ticket number doesn't matches");
		// Added the assertion for validating the Ticket Number.
		//Assert.assertEquals(getTicketNumber(), getticketNum);

		TestReporter.logStep("Click on the Link - TicketNumber");
		lnkTicketNumber.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * @summary : Method to enter the ticket number in folio serach page.
	 * @author : Praveen Namburi
	 * @version : Created 11/05/2015
	 * @param : text
	 * @return : NA
	 */
	public void setTicketNumber(String text) {
		pageLoaded(txtTicketNumber);
		txtTicketNumber.click();
		txtTicketNumber.set(text);
	}

	// Performs operation to click on the first record of payment link
	public void getPaymentDetails() {
		pageLoaded();
		lnkPaymentDetails.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver, 60);
		new PageLoaded().isDomComplete(driver);
	}

	// Performs operation to fetch the CCTID value and validates with the given
	// value.
	public void getTerminalID() {

		getPaymentDetails();
		initialize();
		pageLoaded(eleTicketDetails);
		PaymentWindow_Status = eleTicketDetails.isDisplayed();
		TestReporter.logStep("PaymentWindow_Status " + PaymentWindow_Status);
		if (PaymentWindow_Status = true) {
			pageLoaded(eleTicketDetails);
			TestReporter.logStep("Terminal ID " + eleTerminalID.getText());
			TestReporter.assertNotEquals(eleTerminalID.getText(), "0001",
					"The Terminal ID was found to be 00001");
		} else {
			TestReporter.logStep("No PopUp Found");
		}

	}

	/**
	 * 
	 * @summary Created Method to Verify the override rate.
	 * @version Created 12/01/2015
	 * @author Lalitha Bandha
	 * @param String
	 * @return NA
	 * 
	 */

	// Performs Validating room Rate
	public void verify_OverrideRoomRate(String RoomRate) {

		linkTABleexpansion.click();

		// verify Override room rate
		String getrOwText = driver.findElement(
				By.xpath(".//*[@id='viewFolioTabFormId:dataTable:tb']/tr[3]"))
				.getText();
		TestReporter.log("Row Text ======>> " + getrOwText);
		TestReporter.assertTrue(getrOwText.contains(RoomRate),
				"Room Rate not Override as Expected!!!");
	}

	/**
	 * 
	 * @summary Method that to click Primary Guest Check box
	 * @version Created 07/23/2015
	 * @author Atmakuri Venkatesh
	 * 
	 */
	/*
	 * public void clickGuestCheckBox() { pageLoaded(chkGuest); initialize();
	 * chkGuest.click(); pageLoaded(lnkHistory); initialize(); }
	 * 
	 * public void clickPrimaryGuest(){ chkGuest.check(); }
	 */

	/**
	 * @summary Method that Validate the PopUp After Click on Print
	 * @version Created 10/28/2015
	 * @author Atmakuri Venkatesh
	 */
	public void validatingPrintAnchorPopUP() {
		int counter = 0;
		boolean found = false;
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		do {
			counter++;
			try {
				if (btnPrint.syncVisible(driver, 1, false)) {
					found = true;
				}
			} catch (StaleElementReferenceException sere) {
				pageLoaded(btnPrint);
			}
		} while (!found && counter < WebDriverSetup.getDefaultTestTimeout());
		driver.manage()
				.timeouts()
				.implicitlyWait(WebDriverSetup.getDefaultTestTimeout(),
						TimeUnit.SECONDS);

		btnPrint.jsClick(driver);
		pageLoaded(elePrintMenu);
		// initialize();
		TestReporter.assertTrue(elePrintMenu.syncVisible(driver)
				&& elePrintMenu.syncEnabled(driver),
				"Print Menu Link Not Enabled");
		TestReporter.assertTrue(
				lnkPrintPCR.syncVisible(driver)
						&& lnkPrintPCR.syncEnabled(driver),
				"Print PCR Link Not Enabled");
		TestReporter.assertTrue(
				lnkPrintE.syncVisible(driver) && lnkPrintE.syncEnabled(driver),
				"Print E # Link Enabled");
		TestReporter.assertTrue(lnkMagicBandId.syncVisible(driver)
				&& lnkMagicBandId.syncEnabled(driver),
				"Magic Bands Link Enabled");
		// Commenting out the below line as assertions are needed here
		// if(pageLoaded(elePrintMenu)&&lnkPrintPCR.isEnabled()&&lnkPrintE.isEnabled()&&lnkMagicBandId.isEnabled()){
		// TestReporter.logStep("Print PCR Link Enabled");
		// TestReporter.logStep("Print E # Link Enabled");
		// TestReporter.logStep("Magic Bands Link Enabled");
		// }
	}

	/**
	 * 
	 * @summary Method to Process Invalidate PaidOut Payment
	 * @version Created 11/26/2014
	 * @author Venkatesh A
	 * @param Scenario
	 * @throws NA
	 * @return NA
	 * 
	 */
	public String invalidPaidOutPayment(String scenario) {

		datatable.setVirtualtablePath(Datatable.PAYMENTUI_MASTER_DATA_PATH);
		datatable.setVirtualtablePage("PaymentUI");
		datatable.setVirtualtableScenario(scenario);

		String amount = datatable.getDataParameter("Amount");
		String comment = datatable.getDataParameter("PaidOutComments");

		String winHandleBefore = driver.getWindowHandle();

		btnPaidOut.click();
		Sleeper.sleep(2000);
		do {
			Sleeper.sleep(500);
		} while (driver.getWindowHandles().size() < 2);

		for (String handle : driver.getWindowHandles()) {
			if (driver.switchTo().window(handle).getTitle()
					.contains("Apply Payment")) {
				break;
			}
		}

		PaymentUIWindow paymentUI = new PaymentUIWindow(driver);
		paymentUI.invalidPaidOutPayment(comment, amount);

		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);

		return winHandleBefore;
	}

	/**
	 * 
	 * @summary Method that to click Primary Guest Check box
	 * @version Created 07/23/2015
	 * @author Atmakuri Venkatesh
	 * 
	 */

	public void clickGuestCheckBox() {
		pageLoaded(chkGuest);
		chkGuest.jsToggle(driver);
		pageLoaded(lnkHistory);
	}

	public void clickPrimaryGuest() {
		chkGuest.jsClick(driver);
	}

	/**
	 * @summary Method to validate the PaidOut amount in folio activity tab page
	 * @author Marella Satish
	 * @version 11/27/2015 Marella Satish - original
	 * @param Data
	 *            table scenario
	 * @return NA
	 */
	public void validatePaidOut(String scenario) {

		datatable.setVirtualtablePath(Datatable.PAYMENTUI_MASTER_DATA_PATH);
		datatable.setVirtualtablePage("PaymentUI");
		datatable.setVirtualtableScenario(scenario);

		String ActualPaidOut = datatable.getDataParameter("Amount");

		TestReporter.logStep("ActualPaidOut " + ActualPaidOut);
		// TestReporter.logStep("PaidOut "+elePaidOut.getText());
		String PaidOut = elePaidOut.getText().replace(",", "").replace("-", "")
				.replace("+", "").replace("$", "").trim();
		// TestReporter.logStep("PaidOut "+PaidOut);

		String[] ExpectedPaidOut = StringUtils.split(PaidOut, ".");

		TestReporter.logStep("ExpectedPaidOut " + ExpectedPaidOut[0]);

		TestReporter.logStep("PaidOut Status "
				+ ActualPaidOut.equals(ExpectedPaidOut[0]));
		TestReporter.assertTrue(ActualPaidOut.equals(ExpectedPaidOut[0]),
				"Actual PaidOut [" + ActualPaidOut
						+ "] does not equals expected Paidout["
						+ ExpectedPaidOut[0] + "]");

	}

	/**
	 * @summary Method to validate the Cash Pay amount in folio activity tab
	 *          page
	 * @author Marella Satish
	 * @version 11/27/2015 Marella Satish - original
	 * @param Data
	 *            table scenario
	 * @return NA
	 */
	public void validateCashPay(String scenario) {
		datatable.setVirtualtablePath(Datatable.PAYMENTUI_MASTER_DATA_PATH);
		datatable.setVirtualtablePage("PaymentUI");
		datatable.setVirtualtableScenario(scenario);

		String ActualCashPay = datatable.getDataParameter("Amount");
		TestReporter.logStep("ActualCashPay " + ActualCashPay);
		// TestReporter.logStep("CashPay "+eleCash.getText());
		String CashPay = eleCash.getText().replace(",", "").replace("-", "")
				.replace("+", "").replace("$", "").trim();
		// TestReporter.logStep("CashPay "+CashPay);
		String[] ExpectedCashPay = StringUtils.split(CashPay, ".");

		TestReporter.logStep("ExpectedCashPay " + ExpectedCashPay[0]);

		TestReporter.logStep("Cash Pay Status "
				+ ActualCashPay.equals(ExpectedCashPay[0]));
		TestReporter.assertTrue(ActualCashPay.equals(ExpectedCashPay[0]),
				"Actual cashpay [" + ActualCashPay
						+ "] does not equals expected Cashpay[ "
						+ ExpectedCashPay[0] + "]");
	}

	/**
	 * @summary Method to validate the Transaction Details in folio activity tab
	 *          page
	 * @author Venkatesh A
	 * @version 12/15/2015 Venkatesh A - original
	 * @param Scenario
	 *            ,GuestName,ResortName
	 * @return NA
	 */
	public void validateAndRetrieveTransactionDetails(String GuestName,
			String ResortName, String scenario) {

		String PostedBy = driver.findElement(By.id("header1Form:userName"))
				.getText();

		datatable.setVirtualtablePath(Datatable.PAYMENTUI_MASTER_DATA_PATH);
		datatable.setVirtualtablePage("PaymentUI");
		datatable.setVirtualtableScenario(scenario);
		String paymentMethod = datatable.getDataParameter("PaymentMethod");

		// Grab Data from Payment Ticket Details
		TestReporter
				.logStep("----------------------------------------------------------------------");
		TestReporter.logStep(" 					Payment Ticket Details         						");
		TestReporter
				.logStep("----------------------------------------------------------------------");

		// Grab Table row count
		List<WebElement> Table = elePaymentTable.findElements(By.tagName("tr"));
		System.out.println("TAble Count : " + Table.size());

		// Grab description column text from all the table rows
		for (int Iteration = 1; Iteration <= Table.size(); Iteration++) {
			String Description = driver.findElement(
					By.xpath(folioActivityFormTable + "/tr[" + Iteration
							+ "]/td[4]")).getText();

			// Validate the Description value
			if (Description.contains(paymentMethod)) {
				driver.findElement(
						By.xpath(folioActivityFormTable + "/tr[" + Iteration
								+ "]/td[4]")).findElement(By.tagName("a"))
						.click();

				TestReporter.assertTrue(
						elepaymentTicketDetailWindow.isDisplayed(),
						"Payment Details Window  Not Displayed");

				// Grab Data from Payment Ticket Details
				TestReporter
						.logStep("----------------------------------------------------------------------");
				TestReporter
						.logStep(" 					Payment Ticket Details         						");
				TestReporter
						.logStep("----------------------------------------------------------------------");

				// Validating Description
				TestReporter.assertTrue(Description.contains(driver
						.findElement(By.id("paymentFormAcc:descriptionTxt"))
						.getText()), "Failed to validate payment description");
				TestReporter.logStep("Description : "
						+ driver.findElement(
								By.id("paymentFormAcc:descriptionTxt"))
								.getText());

				// Validating Charged By
				TestReporter.assertTrue(
						driver.findElement(By.id("paymentFormAcc:chargedById"))
								.getText().contains(GuestName),
						"Failed to validate Guest Name");
				TestReporter.logStep("Charged By : "
						+ driver.findElement(
								By.id("paymentFormAcc:chargedById")).getText());

				// Validating PostedBy
				TestReporter
						.assertTrue(
								PostedBy.contains(driver.findElement(
										By.id("paymentFormAcc:postedByTxt"))
										.getText()),
								"Failed to validate User/PostedBy Name");
				TestReporter.logStep("PostedBy/User : "
						+ driver.findElement(
								By.id("paymentFormAcc:postedByTxt")).getText());

				// Grab TicketNumber, RRN,Terminal Id,Property,AUthorization
				// Number, Accounting Start Date, Payment Amount, Posted Date,
				// Ordinal Currency details
				TestReporter
						.logStep("Ticket Number : "
								+ driver.findElement(
										By.id("paymentFormAcc:ticketnumber"))
										.getText());
				TestReporter.logStep("RRN : "
						+ driver.findElement(
								By.id("paymentFormAcc:retrivalRefNumber"))
								.getText());
				TestReporter
						.logStep("Terminal Id : "
								+ driver.findElement(
										By.id("paymentFormAcc:terminalId"))
										.getText());
				TestReporter.logStep("Property : "
						+ driver.findElement(By.id("paymentFormAcc:propId"))
								.getText());
				TestReporter.logStep("Authorization Number : "
						+ driver.findElement(By.id("paymentFormAcc:auth"))
								.getText());
				TestReporter.logStep("Accounting Start Date : "
						+ driver.findElement(By.id("paymentFormAcc:accDate"))
								.getText());
				TestReporter.logStep("Payment Amount : "
						+ driver.findElement(By.id("paymentFormAcc:amountTxt"))
								.getText());
				TestReporter.logStep("Original Currency : "
						+ driver.findElement(
								By.id("paymentFormAcc:originalCurrency"))
								.getText());
				TestReporter
						.logStep("----------------------------------------------------------------------");
				break;
			}
		}
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public void clickClosePaymentWindow() {
		eleClosePaymentWindow.highlight(driver);
		eleClosePaymentWindow.click();
	}

	/**
	 * @summary Method to capture and return the payment amount
	 * @author Dennis Stagliano
	 * @version 12/28/2015
	 * @param NA
	 * @return Payment Amount
	 */
	public String TicketDetailsCapturePaymentAmount() {
		Assert.assertTrue(eleTicketDetailsPaymentAmount.isDisplayed(),
				"Ticket Payment Amount was not displayed");
		String paymentAmount = eleTicketDetailsPaymentAmount.getText();
		return paymentAmount;
	}

	/**
	 * @summary Method to capture the terminal Id's based on total payments done
	 *          by using Credit Card
	 * @author Venkatesh Atmakuri
	 * @version 03/028/2016
	 * @param CreditCard
	 *            Details ArrayList
	 * @return NA
	 */
	public void getTerminalID(ArrayList<String> ccListDetails) {

		pageLoaded(tblViewFolioTab);

		for (String ccDisc : ccListDetails) {
			TestReporter
					.log("Search for the row contains following Credit card Description : "
							+ ccDisc);
			driver.findElement(
					By.xpath("//*[@id='viewFolioTabFormId:dataTable:tb']/tr["
							+ tblViewFolioTab.getRowThatContainsCellText(
									driver, ccDisc, 4) + "]/td[4]/a")).click();
			initialize();
			pageLoaded(eleTicketDetails);
			TestReporter.log("PaymentWindow_Status "
					+ eleTicketDetails.isDisplayed());
			if (PaymentWindow_Status = true) {
				pageLoaded(eleTicketDetails);
				TestReporter.log("Terminal ID " + eleTerminalID.getText());
				TestReporter.assertNotEquals(eleTerminalID.getText(), "0001",
						"The Terminal ID was found to be 0001");
				clickClosePaymentWindow();
				initialize();
			} else {
				TestReporter.logStep("No PopUp Found");
			}
		}
	}
}

