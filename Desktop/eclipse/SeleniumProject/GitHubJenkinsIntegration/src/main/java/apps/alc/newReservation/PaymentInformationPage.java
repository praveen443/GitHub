package apps.alc.newReservation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.SettlementUI.SettlementUIWindow;
import apps.alc.pleaseWait.PleaseWait;
import core.WebDriverSetup;
import core.WindowHandler;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;
import selenium.common.Constants;

/**
 * 
 * @summary Contains the page methods & objects for the payment Information page during
 *          the Cirque payment.
 * @version Created 02/04/2016
 * @author Praveen Namburi - Original
 */
public class PaymentInformationPage {
		// ************************************
		// *** Main Page Fields ***
		// ************************************
		private int timeout = WebDriverSetup.getDefaultTestTimeout();
		private WebDriver driver;
		private Datatable datatable = new Datatable();

		// ************************************
		// *** Main Page Elements ***
		// ************************************

		//Element PaymentInformation panel
		@FindBy(id = "addPaymentModalPanelContentDiv")
		private Element elePaymentInfoPanel;

		//Button PaymentInfo
		@FindBy(id = "addPaymentForm:addPaymentInfo")
		private Button btnPaymentInfo;
		
		//Textbox CardHolder Name
		@FindBy(id = "addPaymentForm:payerNameInput")
		private Textbox txtCardHolderName;
		
		//Listbox PaymentMethod
		@FindBy(id = "addPaymentForm:paymentMethodInput")
		private Listbox lstPaymentMethod;

		//Element Totalprice
		@FindBy(id = "addPaymentForm:reservationAmountOuputValue")
		private Element eleTotalPrice;
		
		//Element AmountPaid
		@FindBy(id = "addPaymentForm:amountPaidOutputValue")
		private Element eleAmountPaid;

		//Element BalanceDue
		@FindBy(id = "addPaymentForm:balanceDueOutputValue")
		private Element eleBalanceDue;
		
		//Element Error Header in settlemntUI Window
		@FindBy(id = "headerTextError") 
		private Element eleErrorHeader;
		
		//Button continue in payment page
		@FindBy(id = "reservationPaymentViewForm:newReservationContinueButton")
		private Button btnContinue;
		
		//Checkbox  'UsePrimaryAddress as BillingAddress'
		@FindBy(id = "addPaymentForm:usePrimaryAddressAsBillingAddressInput" )
		private Checkbox chkUseBillingAddres;

		//Textbox 'BillingAddr1'
		@FindBy(id = "addPaymentForm:billingAddress1Input")
		private Textbox txtBillingAddr1;

		//Checkbox  'UseBillingAddress as ShippingAddress'
		@FindBy(id = "addPaymentForm:usePrimaryAddressAsShippingAddressInput")
		private Checkbox chkUseShippingAddres;

		//Textbox 'ShippingAddr1'
		@FindBy(id = "addPaymentForm:shippingAddress1Input" )
		private Textbox txtShippingAddr1;
		
		//Button Add payment in paymentPage
		@FindBy(id = "reservationPaymentViewForm:newReservationPaymentAddPaymentButton1")
		private Button btnAddPayment1;
		
		//Button ProcessPayment
		@FindBy(id = "addPaymentForm:addPaymentProcessPaymentButton")
		private Button btnProcessPayment;

		//Element get CirquePayment message
		@FindBy(id = "addPaymentForm:addPaymentErrorsRepeat:0:addPaymentErrorsOutput")
		private Element eleGetPaymentMsg;
		
		//Button ProcessPayment
		@FindBy(id = "addPaymentForm:addPaymentCloseButton")
		private Button btnClose;

		// *********************
		// ** Build page area **
		// *********************

		public PaymentInformationPage(WebDriver driver) {
			this.driver = driver;
			ElementFactory.initElements(driver, this);
			datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
		}

		public PaymentInformationPage initialize() {
			return ElementFactory.initElements(driver, this.getClass());
		}

		public boolean pageLoaded() {
			return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnPaymentInfo);
		}

		public boolean pageLoaded(Element element) {
			return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
		}

		// **************************************
		// *** Main CheckIn Page Interactions ***
		// **************************************
		
		public String getCardHolderName() {
			pageLoaded(txtCardHolderName);
			String CardHolderName = txtCardHolderName.getText();
			TestReporter.log("Card Holder Name : "+ CardHolderName);
			return CardHolderName;
		}
		
		public String getPaymentMethod() {
			pageLoaded(lstPaymentMethod);
			String PaymentMethod = lstPaymentMethod.getFirstSelectedOption().getText();
			return PaymentMethod;
		}

		/**
		 * @summary: Method to get Payment or Balance Due Details
		 * @author : Praveen Namburi - Original.
		 * @version: created 02-04-2016.
		 * @param   NA , @return  NA
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
		 * @summary: Method to click on paymentInfo button.
		 * @author : praveen namburi, @version: Created 2-4-2016
		 */
		public void clickPaymentInfo(){
			pageLoaded(btnPaymentInfo);
			btnPaymentInfo.highlight(driver);
			btnPaymentInfo.jsClick(driver);
		}
		
		/**
		 * @summary: method to click on continue button in payment page 
		 * after the payment process.
		 * @author: Praveen Namburi, @version: Created 02-04-2016
		 */
		private void clickContinue() {
			btnContinue.syncVisible(driver);
			btnContinue.jsClick(driver);
			PleaseWait.WaitForPleaseWait(driver, 120);
		}
		
		public void addPayment(){
			btnAddPayment1.highlight(driver);
			btnAddPayment1.jsClick(driver);
			PleaseWait.WaitForPleaseWait(driver);
		}
		
		/**
		 * @summary: Method to capture the text BillingAddress1 in paymentInfo page.
		 * @author: praveen namburi, @version: Created 02-04-2016
		 * @return: String BillingAddress1
		 */
		public String getBillingAddress1(){
			pageLoaded(txtBillingAddr1);
			String BillingAddress1 = txtBillingAddr1.getText();
			return BillingAddress1;
		}
		
		/**
		 * @summary: Method to capture the text ShippingAddress1 in paymentInfo page.
		 * @author: praveen namburi, @version: Created 2-04-2016
		 * @return: String ShippingAddress1
		 */
		public String getShippingAddress1(){
			pageLoaded(txtShippingAddr1);
			String ShippingAddress1 = txtShippingAddr1.getText();
			return ShippingAddress1;
		}
		
		/**
		 * @summary: Method to check the check0boxes for billing address and shipping address 
		 * in paymentInfo page.
		 * @author: Praveen namburi, @version: Created 2-4-2016
		 * @param UseBillingAddres, UseShippingAddres
		 */
		private void checkShipppingAndBillingAddresses(String UseBillingAddres,String UseShippingAddres){
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			if(UseBillingAddres.equalsIgnoreCase("true")){
				if(chkUseBillingAddres.syncVisible(driver, 2, false)){
					chkUseBillingAddres.jsToggle(driver);
					boolean infoFound = false;
					int counter = 0;
					do{
						try{
							String text = txtBillingAddr1.getText();
							if(!text.isEmpty()) infoFound = true;
						}catch(Exception e){
							Sleeper.sleep(1000);
							initialize();
							counter++;
						}
					}while(!infoFound && counter < Constants.PAGE_TIMEOUT);
					TestReporter.log("Captured BillingAddress1 value is : "+ getBillingAddress1());
				}
			}
			if(UseShippingAddres.equalsIgnoreCase("true")){
				if(chkUseShippingAddres.syncVisible(driver, 2, false)){
					chkUseShippingAddres.jsToggle(driver);
					boolean infoFound = false;
					int counter = 0;
					do{
						try{
							String text = txtShippingAddr1.getText();
							if(!text.isEmpty()) infoFound = true;
						}catch(Exception e){
							Sleeper.sleep(1000);
							initialize();
							counter++;
						}
					}while(!infoFound && counter < Constants.PAGE_TIMEOUT);
					TestReporter.log("Captured ShippingAddress1 value is : "+ getShippingAddress1());
				}
			}
			driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
		}
		
		/**
		 * @summary: Method to click on ProcessPayment button.
		 * @author: Praveen Namburi, @version: Created 2-4-2016
		 */
		public void clickProcessPayment(){
			initialize();
			if(btnProcessPayment.syncVisible(driver)){
			   btnProcessPayment.highlight(driver);
			   btnProcessPayment.jsClick(driver);
			   PleaseWait.WaitForPleaseWait(driver);
			}
		}
		
		public void closePaymentInfoPage(){
			pageLoaded(btnClose);
			btnClose.highlight(driver);
			btnClose.jsClick(driver);
		}
		
		/**
		 * @summary: Method to capture the cirque payment message
		 * after processing the payment in paymentInfo page.
		 * @author: Praveen Namburi, @version: Created 02-04-2016
		 * @return
		 */
		public String getCirquePaymentMsg(){
			pageLoaded(eleGetPaymentMsg);
			String cirqueMessage = eleGetPaymentMsg.getText();
			return cirqueMessage;
		}
		
		/**
		 * @summary: Created this method to set the payment method and to provide the
		 * card info while adding the payment in payment information page.
		 * @author:Praveen Namburi - original, @version: Created 2-4-2016
		 * @param scenario
		 */
		public void addPayment(String scenario) {
			System.out.println(scenario);
			datatable.setVirtualtablePath(Datatable.PAYMENTUI_MASTER_DATA_PATH);
			datatable.setVirtualtablePage("PaymentUI");
			datatable.setVirtualtableScenario(scenario);

			String paymentType = datatable.getDataParameter("PaymentType");
			String paymentMethod = datatable.getDataParameter("PaymentMethod");
			String status = datatable.getDataParameter("Status");
			String delay = datatable.getDataParameter("Delay");
			String thirdParty = datatable.getDataParameter("ThirdPartySettlement");
			String setBillingAddres = datatable.getDataParameter("UseBillingAddress");
			String setShippingAddres = datatable.getDataParameter("UseShippingAddress");
			
			/*//Clicking on Add payment button
			TestReporter.log("Clicking on add payment button in payment page.");
			Payment paymentPage =  new Payment(driver);
			paymentPage.clickaddPayment();*/
			
			if(elePaymentInfoPanel.syncVisible(driver, 3, false)){				
				elePaymentInfoPanel.highlight(driver);
				//select the paymentMethod
				pageLoaded(lstPaymentMethod);
				if(lstPaymentMethod.syncVisible(driver)){
				   lstPaymentMethod.select(paymentType);
				}
			}
			
			//Capturing card holder name and paymentMethod
			TestReporter.logStep("Capturing the Card Holder name and paymentMethod ...");
			TestReporter.log("Card Holder Name : " + getCardHolderName());
			TestReporter.log("PaymentMethod is : " + getPaymentMethod());
			
			//Clicking on paymentInfo button.
			clickPaymentInfo(); 
			WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
			WindowHandler.waitUntilWindowExistsTitleContains(driver, "Set Settlement");

			boolean errorFound = false;
			if(eleErrorHeader.syncVisible(driver, 5, false)){
				errorFound = true;
				TestReporter.assertFalse(errorFound, "An error occurred launching the Settlement UI. Error text follows: ["+driver.findElement(By.id("pmtErrorModalPanelContentDiv")).getText()+"].");
			}
			
			SettlementUIWindow settlementWindow = new SettlementUIWindow(driver);
			settlementWindow.applysetSettlementGuarantee(paymentType, paymentMethod, status, delay, thirdParty);
			WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
			WindowHandler.waitUntilWindowExistsTitleContains(driver, "SEServer");
			
			//set the billing address and shipping address check boxes to 'ON'
			checkShipppingAndBillingAddresses(setBillingAddres, setShippingAddres);
			
			//click on Process Payment button in PaymentInformation page
			clickProcessPayment();
			
			//Capturing the cirque payment message
			TestReporter.log("Cirque payment message is : " + getCirquePaymentMsg());
			//Close the paymentInfo page
			closePaymentInfoPage();
			
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			if (!btnContinue.syncEnabled(driver, 5, false)) {
				driver.findElement(By.id("main")).sendKeys(Keys.F5);
				pageLoaded(btnContinue);
				btnContinue.syncEnabled(driver, timeout, true);
			}
			driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
			clickContinue();
			datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
		
		}

}

