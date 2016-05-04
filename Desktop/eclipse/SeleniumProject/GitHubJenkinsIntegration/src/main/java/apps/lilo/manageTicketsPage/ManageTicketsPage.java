package apps.lilo.manageTicketsPage;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import apps.paymentUI.PaymentUIWindow;
import apps.paymentUI.PaymentUIWindow.paymentMethods;
import apps.paymentUI.PaymentUIWindow.paymentTypes;
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
import core.interfaces.exception.ElementNotHiddenException;
import core.interfaces.impl.CheckboxImpl;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import selenium.common.Constants;
import utils.Datatable;
import utils.ExtendedExpectedConditions;
import utils.Sleeper;
import utils.TestReporter;

/**
 * This class contains elements and element interactions for a given page of a
 * web application
 * 
 * @author Waightstill W Avery
 * @version 11/18/2015 Waightstill W Avery - original
 */
public class ManageTicketsPage {
	// *******************
	// Page Class Fields
	// *******************
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	private String manageGuestTable = "manageTicketsForm:accommodationTicketsListTable:0:ticketComponentDetailsTable:";
	private String ticketOfferSelectList = "//*[@id='searchTicketsForm:searchTicketsResultsTable:0:kttwTickets:0:noOfAdultKTTWTicketsSelectlist']/span[";
	private String guestDistribution = "//*[@id='searchTicketsForm:guestDistributionTable:0:kttwTicketsDistribution:";
	private String guestDistributionSelect = ":adultGuestKttwTicketSelect']/option[";
//	private String cardNumber;
//	private String paymentAmount;
//	private String refundCardNumber;
//	private String refundPaymentAmount;
//	private String cardNumberMasked;
//	private String uiPaymentMethod = "";

	// *********************
	// Page Class Elements
	// *********************
	// Search for Entitlement Number Input box
	@FindBy(id = "manageTicketsForm:entitlementNumberInput")
	private Textbox txtEntitlementNumberInput;

	// Search for entitlement GO button
	@FindBy(id = "manageTicketsForm:entitlementNumberInputButtonGo")
	private Button btnEntitlementGoButton;

	@FindBy(id = "manageTicketsForm:accommodationTicketsListTable:ticketDetailSelected")
	private Checkbox chkPrimaryGuest;

	@FindBy(linkText = "Check-In")
	private Link lnkCheckIn;

	// go button close
	@FindBy(id = "closeManageTicketsForm:exitQuickTabButtonId1")
	private Element btnClose;

	// go button OK
	@FindBy(id = "confirmationPopupForm:okButtonId")
	private Element btnOK;

	// Add Guest button
	@FindBy(id = "manageTicketsForm:addguestBtn")
	private Textbox btnAddGuest;

	// day Guest link
	@FindBy(id = "manageTicketsForm:addGuestButton:anchor")
	private Element eleDayGuest;

	// Create Textbox object for Guest First Name
	@FindBy(id = "manageTicketsForm:accommodationTicketsListTable:0:ticketComponentDetailsTable:0:firstNameInput")
	private Textbox txtFirstName;

	// Create Textbox object for Guest Last Name
	@FindBy(id = "manageTicketsForm:accommodationTicketsListTable:0:ticketComponentDetailsTable:0:lastNameInput")
	private Textbox txtLastName;

	// Create CheckBox object for Guest Name
	@FindBy(id = "manageTicketsForm:accommodationTicketsListTable:0:ticketComponentDetailsTable:0:ticketDetailSelected2")
	private Textbox chkNextToGuest;

	// Create Listbox object for Age Type
	@FindBy(id = "manageTicketsForm:accommodationTicketsListTable:0:ticketComponentDetailsTable:0:ageTypeSelect")
	private Listbox lstAgeType;

	// Comp button
	@FindBy(id = "manageTicketsForm:compBtn")
	private Element btnCompBtn;

	// Issue link
	@FindBy(id = "manageTicketsForm:compTicketsButton:anchor")
	private Element eleIssue;

	// Void link
	@FindBy(id = "manageTicketsForm:voidTicketsButton:anchor")
	private Element eleVoid;

	// Button Recommend
	@FindBy(id = "manageTicketsForm:recommendTicketsButton")
	private Button btnRecommendTickets;

	// Button Search
	@FindBy(id = "searchTicketsForm:searchButton")
	private Button btnSearch;

	// Button Close ManageTickets
	@FindBy(id = "closeManageTicketsForm:exitQuickTabButtonId1")
	private Button btnCloseManageTickets;

	// WebTable Accomodation List
	@FindBy(id = "manageTicketsForm:accommodationTicketsListTable")
	private Webtable tblAccomodationTicketList;

	// Element Entitlement Number
	@FindBy(xpath = "//table/tbody/tr[1]/td/span/span[1]/form[2]/div/table/tbody/tr[2]/th/div/div/table/tbody/tr[3]/td[6]")
	private Element elegetEntitlementNumber;

	// Element Entitlement value
	@FindBy(xpath = "//table/tbody/tr[1]/td/span/span[1]/form[2]/div/table/tbody/tr[2]/th/div/div/table/tbody/tr[3]/td[5]")
	private Element eleEntitlementValue;

	// Create Textbox object for Ticket Comment
	@FindBy(id = "searchTicketsForm:compTicketComment")
	private Textbox txtCompTicketComment;

	// searchCompTickets button
	@FindBy(id = "searchTicketsForm:searchCompTicketsButton")
	private Element btnSearchCompTktBtn;

	// partymix listbox button
	@FindBy(id = "searchTicketsForm:searchTicketsResultsTable:0:kttwTickets:0:noOfAdultKTTWTicketsSelectcomboboxButton")
	private Button btnPartyMixList;

	// Select List element
	@FindBy(xpath = ".//*[@id='searchTicketsForm:searchTicketsResultsTable:0:kttwTickets:0:noOfAdultKTTWTicketsSelectlist']/span[2]")
	private Element elePartyMixListItem;

	// Proceed button
	@FindBy(id = "searchTicketsForm:proceedButton")
	private Element btnProceed;

	// Create Listbox object for Guest Name
	@FindBy(id = "searchTicketsForm:guestDistributionTable:0:compTicketsDistribution:0:adultGuestCompTicketSelect")
	private Listbox lstGuestName;

	// Print Ticket button
	@FindBy(xpath = ".//*[@id='searchTicketsForm:printCompTicketButton']")
	private Element btnPrintTicket;

	// Purchase pop up
	@FindBy(id = "recommendTicketsInfoModalPanelHeader")
	private Label lblPurchasePopupSuccessHeader;

	// Purchase popup ok button
	@FindBy(id = "recommendTicketsInfoPopupPanelForm:okButton")
	private  Button btnPurchasePopupOk;

	//Purchase popup text element
	@FindBy(css = "div[id = 'recommendTicketsInfoModalPanelCDiv'] [class ='popupBody']")
	private Element elePurchasePopupText;

	//Manage Tickets Table
	@FindBy(id = "manageTicketsForm:accommodationTicketsListTable:tb")
	private Element eleManageTicketsTable;

	//Confirmation pop up
	@FindBy(id = "voidConfrimationModalPanelHeader")
	private  Label lblCofirmationPopupHeader;

	//Confirmation popup text element
	@FindBy(css = "div[id = 'voidConfrimationModalPanelCDiv'] [class ='popupBody']")
	private Element eleConfirmationPopupText;

	//Confirmation popup Yes button
	@FindBy(id = "voidConfirmationFrm:yesButton")
	private  Button btnConfirmPopupYes;

	//Void success pop up
	@FindBy(id = "sucessInforModalPanelCDiv")
	private  Label lblVoidSuccessPopupHeader;

	//Void success popup text element
	@FindBy(css = "div[id = 'sucessInforModalPanelCDiv'] [class ='popupBody']")
	private Element eleVoidSuccessPopupText;

	//Void  success pop up OK button
	@FindBy(id = "voidInfoFrm:okButton")
	private  Button btnVoidSuccessPopupOk;

	//Button Close ManageTickets
	@FindBy(id = "searchTicketsForm:prevoiusTOManageTicket")
	private Button btnPrevious;

	//Book tiket Confirmation pop up
	@FindBy(id = "bookTicketCompleteModalPanelHeader")
	private  Label lblBookTktCofirmationPopupHeader;

	//book ticket Confirmation popup text element
	@FindBy(css = "div[id = 'bookTicketCompleteModalPanelCDiv'] [class ='popupBody']")
	private Element eleBookTktConfirmationPopupText;

	//button cancel tickets
	@FindBy(id = "completeDayGuestTicketForm:cancelBookTickets")
	private Button btnCancelTickets;

	// day Guest link
	@FindBy(id = "manageTicketsForm:modifyTicketsButton:anchor")
	private Element eleModify;

	// select entitlement
	@FindBy(xpath = "//*[@id='manageTicketsForm']/div/table/tbody/tr[2]/th/div/div/table/tbody/tr[3]/td[4]/input")
	private Element eleCheckEntitlement;
	
	//Guest Information
	@FindBy(id = "tiketGuestAddressForm:address1Id") private Textbox txtGuestAddress1;
	@FindBy(id = "tiketGuestAddressForm:cityId") private Textbox txtGuestCity;
	@FindBy(id = "tiketGuestAddressForm:phoneNumber1") private Textbox txtGuestPhone1;
	@FindBy(id = "tiketGuestAddressForm:postalCode") private Textbox txtGuestZipCode;
	@FindBy(id = "tiketGuestAddressForm:stateId") private Listbox lstGuestState;
	@FindBy(id = "tiketGuestAddressForm:saveButton") private Button btnGuestSave;
	
	/* //-------------------------------
    //Full Tickets list page elements
    //-------------------------------

    //Label Full Ticket List
    @FindBy(id = "searchTicketsForm:allOffers_lbl")
    private Label lblFullTicketList;

    //Select Ticket Offer listbox button 
    @FindBy(id = "searchTicketsForm:searchTicketsResultsTable:0:kttwTickets:0:noOfAdultKTTWTicketsSelectcomboboxButton")
    private Button btnTicketOffer;

    //Button Proceed
    @FindBy(id = "searchTicketsForm:proceedButton")
    private Button btnProceed;

    //Create Listbox object for Age 10+ price under Theme park Tickets
    @FindBy(id = "searchTicketsForm:searchTicketsResultsTable:0:kttwTickets:0:noOfAdultKTTWTicketsSelectlist")
    private Listbox lstThemeParkTickets;

    //button list-box age 10+ price under theme park resorts
    @FindBy(id = "searchTicketsForm:searchTicketsResultsTable:0:kttwTickets:0:noOfAdultKTTWTicketsSelectcomboboxButton")
    private Button btnlstAge10plusprice;

    //select value from combo-list under Theme park resorts in full search page
    @FindBy(xpath="//div[@id='searchTicketsForm:searchTicketsResultsTable:0:kttwTickets:0:noOfAdultKTTWTicketsSelectlist']/span[2]")
    private Listbox lstSelectTickets;

    //Button Close
    @FindBy(id = "onLineCheckinCloseFrm")
    private Button btnCloseFullTicketList;
	 */
	//-----------------------------------
	//Guest Distribution Page Elements
	//----------------------------------
	/* 
    //Button Purchase
    @FindBy(id = "searchTicketsForm:purchaseButton")
    private Button btnPurchase;

    //EncoderSetup
    @FindBy(id = "printEncodeForm:cancelForTicket")
    private Button btnEncoderCancel;

    //Create Listbox object for the list ticket type
    @FindBy(id="searchTicketsForm:guestDistributionTable:0:kttwTicketsDistribution:0:adultGuestKttwTicketSelect")
    private Listbox lstTicketType;*/

	//-----------------------------------
	//Confirmation POP-UP  Elements
	//-----------------------------------
	/*
    //Element Confirmation POP-UP visivle
    @FindBy(id = "confirmationPopupModalPanelShadowDiv")
    private Element eleConfirmationPOPUP;

    //Element get Confirmation POp-UP message
    @FindBy(id = "confirmationMessage" )
    private Element eleGetConfirmationMessage;

    //Button OK in Confirmation POP-UP
    @FindBy(id = "confirmationPopupForm:okButtonId")
    private Button btnConfirmationOK;

    //Button Cancel in Confirmation POP-UP
    @FindBy(id = "confirmationPopupForm:cancelButtonId")
    private Button btnConfirmationCancel;*/

	//--------------------------------------
	//Complete Ticket Payment Elements
	//--------------------------------------

	/* //Element Complete Payment visivle
    @FindBy(id="bookTicketCompleteModalPanelShadowDiv")
    private Element eleCompletePayment;

    //Element get Confirmation POp-UP message
    @FindBy(id="completeDayGuestTicketForm:hiddenPaymentURLId" )
    private Element elegetCompletePaymentText;

    //Button OK in Confirmation POP-UP
    @FindBy(id="completeDayGuestTicketForm:takePayment")
    private Button btnTakePayment;

    //Button Continue WithOut Payment
    @FindBy(id="completeDayGuestTicketForm:cancelAddTickets")
    private Button btnContinueWithOutPayment;*/

	//----------------------------------------------
	// Ticket Purchase Success POP-UP
	//----------------------------------------------

	//    //Element Ticket Purchase Success
	//    @FindBy(id="recommendTicketsInfoModalPanelContentDiv")
	//    private Element eleTicketPurchaseSuccess;
	//
	//	private Button btnPurchasePopupOk;
	//
	//	// Purchase popup text element
	//	@FindBy(css = "div[id = 'recommendTicketsInfoModalPanelCDiv'] [class ='popupBody']")
	//	private Element elePurchasePopupText;
	//
	//	// Manage Tickets Table
	//	@FindBy(id = "manageTicketsForm:accommodationTicketsListTable:tb")
	//	private Element eleManageTicketsTable;
	//
	//	// Confirmation pop up
	//	@FindBy(id = "voidConfrimationModalPanelHeader")
	//	private Label lblCofirmationPopupHeader;
	//
	//	// Confirmation popup text element
	//	@FindBy(css = "div[id = 'voidConfrimationModalPanelCDiv'] [class ='popupBody']")
	//	private Element eleConfirmationPopupText;
	//
	//	// Confirmation popup Yes button
	//	@FindBy(id = "voidConfirmationFrm:yesButton")
	//	private Button btnConfirmPopupYes;
	//
	//	// Void success pop up
	//	@FindBy(id = "sucessInforModalPanelCDiv")
	//	private Label lblVoidSuccessPopupHeader;
	//
	//	// Void success popup text element
	//	@FindBy(css = "div[id = 'sucessInforModalPanelCDiv'] [class ='popupBody']")
	//	private Element eleVoidSuccessPopupText;
	//
	//	// Void success pop up OK button
	//	@FindBy(id = "voidInfoFrm:okButton")
	//	private Button btnVoidSuccessPopupOk;

	// -------------------------------
	// Full Tickets list page elements
	// -------------------------------

	// Label Full Ticket List
	@FindBy(id = "searchTicketsForm:allOffers_lbl")
	private Label lblFullTicketList;

	// Select Ticket Offer listbox button
	@FindBy(id = "searchTicketsForm:searchTicketsResultsTable:0:kttwTickets:0:noOfAdultKTTWTicketsSelectcomboboxButton")
	private Button btnTicketOffer;

	/*
	 * //Button Proceed
	 * 
	 * @FindBy(id = "searchTicketsForm:proceedButton") private Button
	 * btnProceed;
	 */

	// Create Listbox object for Age 10+ price under Theme park Tickets
	@FindBy(id = "searchTicketsForm:searchTicketsResultsTable:0:kttwTickets:0:noOfAdultKTTWTicketsSelectlist")
	private Listbox lstThemeParkTickets;

	// button list-box age 10+ price under theme park resorts
	@FindBy(id = "searchTicketsForm:searchTicketsResultsTable:0:kttwTickets:0:noOfAdultKTTWTicketsSelectcomboboxButton")
	private Button btnlstAge10plusprice;

	// select value from combo-list under Theme park resorts in full search page
	@FindBy(xpath = "//div[@id='searchTicketsForm:searchTicketsResultsTable:0:kttwTickets:0:noOfAdultKTTWTicketsSelectlist']/span[2]")
	private Listbox lstSelectTickets;

	// Button Close
	@FindBy(id = "onLineCheckinCloseFrm")
	private Button btnCloseFullTicketList;

	// -----------------------------------
	// Guest Distribution Page Elements
	// ----------------------------------

	// Button Purchase
	@FindBy(id = "searchTicketsForm:purchaseButton")
	private Button btnPurchase;

	// EncoderSetup
	@FindBy(id = "printEncodeForm:cancelForTicket")
	private Button btnEncoderCancel;

	// Create Listbox object for the list ticket type
	@FindBy(id = "searchTicketsForm:guestDistributionTable:0:kttwTicketsDistribution:0:adultGuestKttwTicketSelect")
	private Listbox lstTicketType;

	// -----------------------------------
	// Confirmation POP-UP Elements
	// -----------------------------------

	// Element Confirmation POP-UP visivle
	@FindBy(id = "confirmationPopupModalPanelShadowDiv")
	private Element eleConfirmationPOPUP;

	// Element get Confirmation POp-UP message
	@FindBy(id = "confirmationMessage")
	private Element eleGetConfirmationMessage;

	// Button OK in Confirmation POP-UP
	@FindBy(id = "confirmationPopupForm:okButtonId")
	private Button btnConfirmationOK;

	// Button Cancel in Confirmation POP-UP
	@FindBy(id = "confirmationPopupForm:cancelButtonId")
	private Button btnConfirmationCancel;

	// --------------------------------------
	// Complete Ticket Payment Elements
	// --------------------------------------

	// Element Complete Payment visivle
	@FindBy(id = "bookTicketCompleteModalPanelShadowDiv")
	private Element eleCompletePayment;

	// Element get Confirmation POp-UP message
	@FindBy(id = "completeDayGuestTicketForm:hiddenPaymentURLId")
	private Element elegetCompletePaymentText;

	// Button OK in Confirmation POP-UP
	@FindBy(id = "completeDayGuestTicketForm:takePayment")
	private Button btnTakePayment;

	// Button Continue WithOut Payment
	@FindBy(id = "completeDayGuestTicketForm:cancelAddTickets")
	private Button btnContinueWithOutPayment;

	// ----------------------------------------------
	// Ticket Purchase Success POP-UP
	// ----------------------------------------------

	// Element Ticket Purchase Success
	@FindBy(id = "recommendTicketsInfoModalPanelContentDiv")
	private Element eleTicketPurchaseSuccess;

	// Element get Ticket Purchase POp-UP message
	@FindBy(css = "div[id = 'recommendTicketsInfoModalPanelCDiv'] [class ='popupBody']")
	private Element eleGetTicketPurchasePopUPMsg;

	// Button OK in Ticket Purchase POP-UP
	@FindBy(id = "recommendTicketsInfoPopupPanelForm:okButton")
	private Button btnOKTicketPurchase;

	//---------------------------------------------

	//Button Existing Tickets 
	@FindBy(id="manageTicketsForm:existingticketsBtn")
	private Button btnExistingTickets;

	//check the check-box in accomodation ticket list table
	@FindBy(xpath="//*[@id='manageTicketsForm:accommodationTicketsListTable:tb']/tr[3]/td[4]/input")
	private Checkbox chkAccomodationTicket;

	//Button RefundTickets
	@FindBy(id="manageTicketsForm:refundTicketsButton:anchor")
	private Button btnRefundTickets;

	//Check-box for Refunding the ticket
	@FindBy(id="refundTicketsForm:ticketsListTable:0:ticketDetailSelected")
	private Checkbox chkRefundTicket;

	//Capture Refund amount
	@FindBy(id="refundTicketsForm:totalPrice")
	private Element eleGetRefundAmount;

	//Button Refund Amount
	@FindBy(id="refundTicketsForm:refundButton")
	private Button btnRefund;

	//Element Ticket Refund success popup visible
	@FindBy(id="recommendTicketsInfoModalPanelContentDiv")
	private Element eleTicketRefundPopUp;

	//Element get Ticket refund success popup message
	@FindBy(xpath=".//*[@id='recommendTicketsInfoPopupPanelForm']/table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td/table/tbody/tr/td[2]")
	private Element eleGetTicketREfundText;

	//Button Ok in Refund Success popup
	@FindBy(id="recommendTicketsInfoPopupPanelForm:okButtonForRefund")
	private Button btnRefundSuccessPopupOk;

	//Button Ok in Refund Success popup
	@FindBy(xpath="//*[@id='manageTicketsForm:accommodationTicketsListTable:tb']/tr[3]/td[3]")
	private Button eleGetTicketStatus;

	//Enter Payment amount in Apply payment window (Used in Refund payment for ticket)
	@FindBy(id="paymentPopup:paymentTypeId:0:editablePmtstId:0:amountInputText")
	private Textbox txtPaymentAmount;

	//Link Apply in Apply payment window (Used in Refund payment for ticket)
	@FindBy(id="paymentPopup:okButtonId")
	private Link lnkApply;

	//Click Continue button in Document warning pop-up
	@FindBy(xpath = "/html/body/div[2]/div[2]/div/div[2]/table/tbody/tr[2]/td/form/table/tbody/tr[1]/td[1]/table/tbody/tr[2]/td/div/table/tbody/tr/td/a[1]")
	private Button btnDupDocumentContinue;

	//Element Success pop-up panel (Used in Refund payment for ticket)
	@FindBy(id="retrievalRefNumberPanelModalPanelContentDiv")
	private Element eleSuccessPopup;

	//Link Apply in Apply payment window (Used in Refund payment for ticket)
	@FindBy(xpath = ".//*[@id='rrnForm']/table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td/table/tbody/tr/td[2]")
	private Element eleGetSuccessPopuptext;;

	//Link Apply in Apply payment window (Used in Refund payment for ticket)
	@FindBy(id="rrnForm:okId")
	private Button btnSuccessPopupOk; 

	//----- Second printer encoder elements -----

	//Cancel second printer encdoer
	//@FindBy(xpath="//div[9]/div[2]/div/div[2]/table/tbody/tr[2]/td/form/table/tbody/tr[1]/td[1]/table/tbody/tr[2]/td/div/table/tbody/tr/td/input[2]")
	@FindBy(id="printEncodeForm:defaultPrintEncodCancelId")
	private Button btnCancelSecondEncoder;

	//printer second encoder panel 
	@FindBy(id="printerEncoderModalPanelContentTable")
	private Element eleSecondEncoderPanel;

	@FindBy(id="printerEncoderModalPanelDiv")
	private Element eleEncoderDivpanel;

	//button ok in Refund success pop-up 
	@FindBy(id="recommendTicketsInfoPopupPanelForm:okButton")
	private Button btnOkInRefundPOPUP;

	// Take Payment button
	@FindBy(id = "viewFolioTabFormId:takepaymentid")
	private Link lnkTakePayment;

	//button Split payment in payment window
	@FindBy(id = "paymentPopup:splitPaymentButId")
	private Button btnSplitPayment;

	//button Ok
	//html/body/div[2]/div[2]/div/div[2]/table/tbody/tr[2]/td/form/table/tbody/tr[1]/td[1]/table/tbody/tr[2]/td/div/table/tbody/tr/td/a
	//@FindBy(id="rrnForm:okId")
	@FindBy(xpath="html/body/div[2]/div[2]/div/div[2]/table/tbody/tr[2]/td/form/table/tbody/tr[1]/td[1]/table/tbody/tr[2]/td/div/table/tbody/tr/td/a")
	private Button btnOkSucssesPayment;

	/*
	 * Discount/Passes Panel
	 */
	@FindBy(id="searchTicketsForm:groupCodeInput") private Textbox txtGroupCode;
	@FindBy(id="searchTicketsForm:groupNameInput") private Textbox txtGroupName;
	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author YourNameHere
	 * @version 9/25/2015 YourNameHere - original
	 * @param driver
	 */
	public ManageTicketsPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author YourNameHere
	 * @version 9/25/2015 YourNameHere - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public void initialize() {
		//return ElementFactory.initElements(driver, this.getClass());
	}

	public static void initialize(WebDriver driver) {
		//return ElementFactory.initElements(driver, ManageTicketsPage.class);
	}
	/**
	 * @summary Method to determine if a page is loaded
	 * @author YourNameHere
	 * @version 9/25/2015 YourNameHere - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, chkPrimaryGuest);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author  Parveen Namburi
	 * @version 12/7/2015	
	 * @return  Boolean, true if the page is loaded, false otherwise
	 */
	public boolean offersPageIsloaded(){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnPrevious);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author YourNameHere
	 * @version 9/25/2015	 YourNameHere - original
	 * @param element - Element to be used to determine if the page is loaded
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded(Element element){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element); 
	}  

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Parveen Namburi
	 * @version 12/4/2015
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean fullTicketListPageIsLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnCloseFullTicketList);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Parveen Namburi
	 * @version 12/4/2015
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean guestdistributionPageIsLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnPurchase);
	}

	// ********************
	// Page Class Methods
	// ********************
	private void selectPrimaryGuest() {
		initialize();
		pageLoaded(chkPrimaryGuest);
		chkPrimaryGuest.toggle();
	}

	private void verifyCheckInLinkDisabled() {
		initialize();
		boolean checkInVisible = false;
		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			checkInVisible = lnkCheckIn.syncVisible(driver, 5, false);
			driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
		} catch (NoSuchElementException e) {

		}
		TestReporter.assertFalse(checkInVisible, "The Check-In button was visible when it should not have been.");
	}

	private void closeManageTickets() {
		initialize();
		pageLoaded(btnClose);
		btnClose.click();

		initialize();
		pageLoaded(btnOK);
		btnOK.click();
	}

	public void verifyTicketsNotAvailable() {
		selectPrimaryGuest();
		verifyCheckInLinkDisabled();
		closeManageTickets();
	}

	/**
	 * @summary Method to click AddGuest button in ManageTickets page
	 * @author Marella Satish
	 * @version 12/03/2015 Marella Satish - original
	 * @param NA
	 * @return NA
	 */
	public void clickAddGuest() {
		pageLoaded(btnAddGuest);
		btnAddGuest.syncVisible(driver);
		btnAddGuest.jsClick(driver);

	}

	/**
	 * @summary Method to click DayGuest link from addguest in ManageTickets
	 *          page
	 * @author Marella Satish
	 * @version 12/03/2015 Marella Satish - original
	 * @param NA
	 * @return NA
	 */
	public void clickDayGuest() {
		pageLoaded(eleDayGuest);
		eleDayGuest.highlight(driver);
		eleDayGuest.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver, 60);
	}

	/**
	 * @summary Method to click Existing tickets button in ManageTickets page
	 * @author Marella Satish
	 * @version 12/22/2015 Marella Satish - original
	 * @param NA
	 * @return NA
	 */
	public void clickExistingTickets() {
		initialize();
		pageLoaded();
		btnExistingTickets.click();

	}

	/**
	 * @summary Method to click modify link from ManageTickets in ManageTickets
	 *          page
	 * @author Marella Satish
	 * @version 12/22/2015 Marella Satish - original
	 * @param NA
	 * @return NA
	 */
	public void clickModify() {
		initialize();
		pageLoaded(eleModify);
		eleModify.highlight(driver);
		eleModify.jsClick(driver);

	}

	/**
	 * @summary Method to select the guest
	 * @author Marella Satish
	 * @version 12/03/2015 Marella Satish - original
	 * @param NA
	 * @return NA
	 */
	public void selectGuest() {
		pageLoaded(chkNextToGuest);
		
		boolean isEnabled = false;
		int counter = 0;
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		do{
			if(chkNextToGuest.syncEnabled(driver, 1, false)){
				isEnabled = true;
			}
			counter++;
		}while(!isEnabled && counter < WebDriverSetup.getDefaultTestTimeout());
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
		
		chkNextToGuest.syncVisible(driver);
		chkNextToGuest.jsClick(driver);
	}

	public void clickPaymentButton() {
		pageLoaded(btnTakePayment);
		btnTakePayment.syncVisible(driver);
//		btnTakePayment.highlight(driver);
		btnTakePayment.click();
	}

	public void clickCancelButton() {
		btnClose.highlight(driver);
		btnClose.jsClick(driver);

		pageLoaded(btnConfirmationOK);
		btnConfirmationOK.syncVisible(driver);
//		btnConfirmationOK.highlight(driver);
		btnConfirmationOK.jsClick(driver);
	}

	/**
	 * @summary Method to enter the guest details
	 * @author Marella Satish
	 * @version 12/03/2015 Marella Satish - original
	 * @param Data
	 *            table scenario
	 * @return NA
	 */
	public void enterManageGuestDetails(String scenario) {
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
		datatable.setVirtualtablePage("manageTickets");
		datatable.setVirtualtableScenario(scenario);
	
		pageLoaded(chkNextToGuest);

		// Enter Guest First Name
		txtFirstName.safeSet(datatable.getDataParameter("FirstName"));

		// Enter Guest Last Name
		txtLastName.safeSet(datatable.getDataParameter("LastName"));

		// Select Age Type
		lstAgeType.select(datatable.getDataParameter("AgeType"));
	}

	/**
	 * @summary Method to click Comp button from manage tickets page
	 * @author Marella Satish
	 * @version 12/03/2015 Marella Satish - original
	 * @param NA
	 * @return NA
	 */
	public void clickComp() {
		pageLoaded(btnCompBtn);
		btnCompBtn.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * @summary Method to click issue link from Comp list from manage tickets
	 *          page
	 * @author Marella Satish
	 * @version 12/03/2015 Marella Satish - original
	 * @param NA
	 * @return NA
	 */
	public void clickIssue() {
		pageLoaded(eleIssue);
		eleIssue.highlight(driver);
		eleIssue.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * @summary Method to click void link from Comp list from manage tickets
	 *          page
	 * @author Marella Satish
	 * @version 12/03/2015 Marella Satish - original
	 * @param NA
	 * @return NA
	 */
	public void clickVoid() {
		pageLoaded(eleVoid);
		eleVoid.highlight(driver);
		eleVoid.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * @summary Method to enter the issuecomment and clicking on Search button
	 *          from manage tickets page
	 * @author Marella Satish
	 * @version 12/03/2015 Marella Satish - original
	 * @param NA
	 * @return NA
	 */
	public void issueCommentAndSearch(String scenario) {
		pageLoaded(btnSearchCompTktBtn);
		txtCompTicketComment.safeSet(datatable.getDataParameter("CompIssueComment"));
		btnSearchCompTktBtn.click();

	}

	/**
	 * @summary : Method to click on button Recommend in manage Tickets page.
	 * @author : Praveen Namburi
	 * @version : Created 12/3/2015
	 */
	public void clickBtnRecommend() {
		pageLoaded(btnRecommendTickets);
		btnRecommendTickets.syncVisible(driver);
		btnRecommendTickets.highlight(driver);
		btnRecommendTickets.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * @summary Method to select entitlement checkbox from manage tickets page
	 * @author Marella Satish
	 * @version 12/22/2015 Marella Satish - original
	 * @param NA
	 * @return NA
	 */
	public void checkEntitlement() {
		initialize();
		pageLoaded(eleCheckEntitlement);
		eleCheckEntitlement.highlight(driver);
		eleCheckEntitlement.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}


	/**
	 * @summary Method to perform the operations to generate a ticket status
	 *          from issue to void
	 * @author Marella Satish
	 * @version 12/04/2015 Marella Satish - original
	 * @param Data
	 *            table scenario
	 * @return NA
	 */
	public void voidTickets(String scenario) {

		// To click AddGuest link
		clickAddGuest();

		// To click Day Guest link
		clickDayGuest();

		// Selecting the guest
		selectGuest();

		// entering the guest details
		enterManageGuestDetails(scenario);

		// clicking on comp and isssue link
		clickComp();
		clickIssue();

		// adding issue comments and searching
		issueCommentAndSearch(scenario);

		// selecting the partymix value
		btnPartyMixList.click();
		pageLoaded(elePartyMixListItem);
		elePartyMixListItem.click();

		pageLoaded(btnProceed);
		btnProceed.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		pageLoaded(btnPrintTicket);

		// selecting the Guest Name from datatable
		lstGuestName.select(datatable.getDataParameter("FirstName") + " " + datatable.getDataParameter("LastName"));
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		pageLoaded(btnPrintTicket);
		// Method to click issue link from Comp list
		btnPrintTicket.syncVisible(driver);
//		btnPrintTicket.highlight(driver);
		btnPrintTicket.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		enterGuestInformation();
		
		// Handling the print ticket success popup
		handleWarningsErrorsAndSuccessPopup();
	}
	
	private void enterGuestInformation(){
		Datatable dt = new Datatable();
		dt.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
		dt.setVirtualtablePage("CreateResCreateNewGuestPage");
		dt.setVirtualtableScenario("Generic Primary Guest");
		
		String zipCode = dt.getDataParameter("ZipCode");
		String city = dt.getDataParameter("City");
		String address1 = dt.getDataParameter("Address1");
		String phone1 = dt.getDataParameter("PrimaryPhoneNum");
		String state = dt.getDataParameter("State");
		
		pageLoaded(txtGuestZipCode);
		txtGuestZipCode.safeSet(zipCode);
		new WebDriverWait(driver, 10).until(ExtendedExpectedConditions.textToMatchInElement(txtGuestCity, city));
		txtGuestCity.safeSet(city);
		txtGuestAddress1.safeSet(address1);
		txtGuestPhone1.safeSet(phone1);
		lstGuestState.select(state);
		
		btnGuestSave.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver, 60);	
	}

	/**
	 * @summary Method to validate the Tickets status from Active to Void
	 * @author Marella Satish
	 * @version 12/04/2015 Marella Satish - original
	 * @param NA
	 * @return NA
	 */
	public void validateTicketStatusAsVoid() {
		pageLoaded();
		TestReporter.logStep("validate Ticket Status As Void");
		//commenting below code to bring the element into the Element Factory
//		String tktIssueStatus = driver
//				.findElement(By.xpath("//*[@id='manageTicketsForm:accommodationTicketsListTable:tb']/tr[3]/td[3]"))
//				.getText();
		Element element = new ElementImpl(driver.findElement(By.xpath("//*[@id='manageTicketsForm:accommodationTicketsListTable:tb']/tr[3]/td[3]")));
		String tktIssueStatus = element.getText();
		
		TestReporter.logStep("Issue ticket Status  : " + tktIssueStatus);
//		WebElement selectGuest = driver
//				.findElement(By.xpath("//form[2]/div/table/tbody/tr[2]/th/div/div/table/tbody/tr[3]/td[4]/input"));
		Checkbox checkbox = new CheckboxImpl(driver
				.findElement(By.xpath("//form[2]/div/table/tbody/tr[2]/th/div/div/table/tbody/tr[3]/td[4]/input")));
		
		// Selecting the guest and changing the status from Active to Void
//		selectGuest.click();
		checkbox.jsToggle(driver);
		clickComp();
		clickVoid();

		// Handles confirmation popup
		handleWarningsErrorsAndSuccessPopup();
		// Handles the Void success popup
		handleWarningsErrorsAndSuccessPopup();

		verifyTicketVoidStatus();
	}

	/**
	 * @summary Method to handle warning/error/confirmation/success popup's
	 * @author Marella Satish
	 * @version 12/14/2015 Marella Satish - original
	 * @param Data
	 *            table scenario
	 * @return NA
	 */
	public void handleWarningsErrorsAndSuccessPopup() {

		TestReporter.logStep("Syncing for Popup's");

		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		if (lblPurchasePopupSuccessHeader.syncVisible(driver, 2, false) == true) {

			TestReporter.logStep("Handling Purchase Ticket Success Popup");
			pageLoaded(elePurchasePopupText);
			String ExpectedPurchaseText = "Ticket have been";
			String ActualPurchaseText = elePurchasePopupText.getText();

			boolean PurchaseStatus = ActualPurchaseText.toLowerCase().contains(ExpectedPurchaseText.toLowerCase());

			if (PurchaseStatus == true) {
				TestReporter.assertTrue(PurchaseStatus, "Actual String[" + ActualPurchaseText
						+ "] does not contains Expected string[" + ExpectedPurchaseText + "]");
				pageLoaded(btnPurchasePopupOk);
				btnPurchasePopupOk.jsClick(driver);
				new ProcessingYourRequest().WaitForProcessRequest(driver, 60);
			}

		} else if (lblCofirmationPopupHeader.isDisplayed() == true) {

			TestReporter.logStep("Handling Confirmation Popup");
			pageLoaded(eleConfirmationPopupText);
			String ExpectedConfirmationText = "You are about to void the selected tickets";
			String ActualConfirmationText = eleConfirmationPopupText.getText();

			boolean ConfirmationStatus = ActualConfirmationText.toLowerCase()
					.contains(ExpectedConfirmationText.toLowerCase());

			if (ConfirmationStatus == true) {
				TestReporter.assertTrue(ConfirmationStatus, "Actual String[" + ActualConfirmationText
						+ "] does not contains Expected string[" + ExpectedConfirmationText + "]");
				pageLoaded(btnConfirmPopupYes);
				btnConfirmPopupYes.highlight(driver);
				btnConfirmPopupYes.jsClick(driver);
				new ProcessingYourRequest().WaitForProcessRequest(driver, 60);
			}

		} else if (lblVoidSuccessPopupHeader.isDisplayed() == true) {

			TestReporter.logStep("Handling Void Ticket Success Popup");
			pageLoaded(eleVoidSuccessPopupText);
			String ExpectedVoidSuccessText = "The tickets have been voided successfully.";
			String ActualVoidSuccessText = eleVoidSuccessPopupText.getText();

			boolean VoidSuccess = ActualVoidSuccessText.toLowerCase().contains(ExpectedVoidSuccessText.toLowerCase());

			if (VoidSuccess == true) {
				TestReporter.assertTrue(VoidSuccess, "Actual String[" + ActualVoidSuccessText
						+ "] does not contains Expected string[" + ExpectedVoidSuccessText + "]");
				pageLoaded(btnVoidSuccessPopupOk);
				btnVoidSuccessPopupOk.highlight(driver);
				btnVoidSuccessPopupOk.jsClick(driver);
				new ProcessingYourRequest().WaitForProcessRequest(driver, 60);
			}

		}
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
	}

	/**
	 * @summary : Method to click on button Search in manage Tickets page.
	 * @author : Praveen Namburi
	 * @version : Created 12/3/2015
	 */
	public void clickBtnSearch() {
		pageLoaded(btnSearch);
		btnSearch.syncVisible(driver);
		btnSearch.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver, 60);
	}

	/**
	 * @summary : Method to select the primary guest check-box and click on
	 *          Recommend button in manage tickets page.
	 * @author : Praveen Namburi
	 * @version : Created 12/3/2015
	 */
	public void selectPriGuestAndClickRecommend() {
		initialize();
		selectPrimaryGuest();
		clickBtnRecommend();
	}

	/**
	 * @summary : Method to click on label Full Ticket list in the top menu.
	 * @author : Praveen Namburi
	 * @version : Created 12/3/2015
	 */
	public void clickFullTicketList() {
		pageLoaded(lblFullTicketList);
		lblFullTicketList.syncVisible(driver);
		lblFullTicketList.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);

	}

	public void clickPurchaseOk() {
		pageLoaded(btnOKTicketPurchase);
		btnOKTicketPurchase.highlight(driver);
		btnOKTicketPurchase.click();
	}

	/**
	 * @summary : Method to click on Proceed button in Full Ticket List page.
	 * @author : Praveen Namburi
	 * @version : Created 12/4/2015
	 */
	public void clickProceedINFullTicketList() {
		pageLoaded(btnProceed);
		btnProceed.syncVisible(driver);
		btnProceed.highlight(driver);
		btnProceed.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * @summary : Method to select the option from combo-list under theme park
	 *          resorts and click on Proceed button in Full Ticket page form.
	 * @author : Praveen Namburi
	 * @version : Created 12/4/2015
	 */
	public void selectTicketsAndProceed() {

		initialize();
		pageLoaded(btnlstAge10plusprice);
		btnlstAge10plusprice.syncVisible(driver);
		btnlstAge10plusprice.jsClick(driver);

		// To select the option - "1" from combo-list
		String selectOptionUsingXPath = "//div[@id='searchTicketsForm:searchTicketsResultsTable:0:kttwTickets:0:noOfAdultKTTWTicketsSelectlist']/span[2]";
		driver.findElement(By.xpath(selectOptionUsingXPath)).click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		// Clck on Proceed
		clickProceedINFullTicketList();

	}

	/**
	 * @summary : Method to click on Purchase button in Guest Distribution page.
	 * @author  : Praveen Namburi
	 * @version : Created 12/4/2015
	 */
	public void clickBtnPurchase(){
		pageLoaded(btnPurchase);
		btnPurchase.syncVisible(driver);
		btnPurchase.highlight(driver);
		btnPurchase.jsClick(driver);
		try{
			new ProcessingYourRequest().WaitForProcessRequest(driver);
		}catch(ElementNotHiddenException enhe){}
	}

	public void clickCancelEncoder() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		pageLoaded(btnEncoderCancel);
		if(btnEncoderCancel.syncVisible(driver, 3, false)){
			btnEncoderCancel.highlight(driver);
			btnEncoderCancel.jsClick(driver);
		}
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * @summary : Method to select the guest name in Guest Distribution page.
	 * @author : Praveen Namburi
	 * @version : Created 12/4/2015
	 * @param :
	 *            scenario - Used to get the datatable values from the virtual
	 *            table scenario.
	 */
	public void selectGuestTicket(String scenario) {
		datatable.setVirtualtablePage("EnterQuickBookPartyMixInfo");
		datatable.setVirtualtableScenario(scenario);

		pageLoaded(lstTicketType);
		lstTicketType.select(datatable.getDataParameter("FirstName") + " " + datatable.getDataParameter("LastName"));
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * @summary : Method to select the Guest and Purchase the ticket in Guest
	 *            Distribution page.
	 * @version : Created 12/4/2015
	 * @author  : Praveen Namburi
	 */
	public void selectGuestAndPurchaseTicket(String scenario) {
		initialize();
		selectGuestName(scenario);
		clickBtnPurchase();
	}

	public void selectGuestName(String scenario) {
		datatable.setVirtualtablePage("manageTickets");
		datatable.setVirtualtableScenario(scenario);

		pageLoaded(lstTicketType);
		lstTicketType.select(datatable.getDataParameter("FirstName") + " " + datatable.getDataParameter("LastName"));
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * @summary : Method to handle the confirmation pop-up.
	 * @author : Praveen Namburi
	 * @version : Created 12/4/2015
	 * @param :
	 *            Scenario
	 */
	public void handleConfirmationPOPUP(String scenario) {
		datatable.setVirtualtablePage("HandleManageticketsPOPUPs");
		datatable.setVirtualtableScenario(scenario);

		initialize();
		pageLoaded(eleConfirmationPOPUP);
		eleConfirmationPOPUP.syncVisible(driver);

		if (!eleConfirmationPOPUP.isDisplayed()) {
			TestReporter.logStep("Confirmation Pop-up is not loaded.");
		} else {
			TestReporter.logStep("Confirmation Pop-up is loaded.");

			if (datatable.getDataParameter("ConfirmationPOPUPOk").equalsIgnoreCase("FALSE")) {
				btnConfirmationCancel.jsClick(driver);
			} else {
				btnConfirmationOK.jsClick(driver);
			}

			new ProcessingYourRequest().WaitForProcessRequest(driver);
		}

	}

	/**
	 * @summary : Method to handle the confirmation pop-up.
	 * @author : Praveen Namburi
	 * @version : Created 12/4/2015
	 * @param :
	 *            Scenario
	 */
	public void handleCompletePaymentPOPUP(String scenario) {
		datatable.setVirtualtablePage("HandleManageticketsPOPUPs");
		datatable.setVirtualtableScenario(scenario);

		initialize();
		pageLoaded(eleCompletePayment);
		eleCompletePayment.syncVisible(driver);

		if(!eleCompletePayment.isDisplayed()){
			TestReporter.logStep("Complete Payment Pop-up is not loaded.");
		}else{
			TestReporter.logStep("Complete Payment Pop-up is loaded.");

			if(datatable.getDataParameter("CompletePaymentSelect").equalsIgnoreCase("FALSE")){
				btnContinueWithOutPayment.jsClick(driver);
			}else{
				TestReporter.logStep("Clicking on Take Payment button.");
				btnTakePayment.click();
			}

			new ProcessingYourRequest().WaitForProcessRequest(driver);
		}

	}

	/**
	 * @summary : Method to handle Ticket Purchase Success POPUP.
	 * @author  : Praveen Namburi
	 * @version : Created 12/4/2015
	 */
	public void handleTicketPurchaseSuccessPOPUP(){

		initialize();
		pageLoaded(eleTicketPurchaseSuccess);
		eleTicketPurchaseSuccess.syncVisible(driver);

		if(!eleTicketPurchaseSuccess.isDisplayed()){
			TestReporter.logStep("Ticket Purchase Success Pop-up is not loaded.");
		}else{
			TestReporter.logStep("Ticket Purchase Success Pop-up is loaded.");

			pageLoaded(eleGetTicketPurchasePopUPMsg);
			//eleGetTicketPurchasePopUPMsg.syncVisible(driver);
			String getTicketPaymentSuccessMsg = eleGetTicketPurchasePopUPMsg.getText();
			TestReporter.logStep("Actual Pop-up message is : " + getTicketPaymentSuccessMsg);

			//verifying the ticketPurchase success pop-up message
			TestReporter.assertTrue(getTicketPaymentSuccessMsg.contains("Ticket have been purchased successfully"), "The Actual Ticket purchase pop-up msg deosn't matches with the expected one.");

			TestReporter.logStep("Click on Ok button.");
			pageLoaded(btnOKTicketPurchase);
			btnOKTicketPurchase.jsClick(driver);
			new ProcessingYourRequest().WaitForProcessRequest(driver);
		}

	}

	/**
	 * @summary : Method to purchase the ticket without completing the payment
	 * @author  : Praveen Namburi
	 * @version : Craeted 12/4/2015
	 * @param 	: scenario
	 */
	public void purchaseTicketWithOutPayment(String scenario){

		initialize();
		handleCompletePaymentPOPUP(scenario);
		handleTicketPurchaseSuccessPOPUP();
	}

	/**
	 * @summary : Method to close the manage tickets form.
	 * @author  : Praveen Namburi
	 * @version : Created 12/4/2015 
	 */
	public void clickCloseManageTickets(){
//		initialize();
		pageLoaded(btnCloseManageTickets);
		btnCloseManageTickets.syncVisible(driver);
		btnCloseManageTickets.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}


	/**
	 * @summary : Method to capture the entitlement number.
	 * @version : Created 12/4/2015
	 * @author : Praveen Namburi
	 * @return : String Entitlement number #
	 */
	public String capturingEntitlementNum() {
		pageLoaded(tblAccomodationTicketList);

		// Capturing the entitlement number
		elegetEntitlementNumber.syncVisible(driver);
		String getEntitlementNumber = elegetEntitlementNumber.getText();
		TestReporter.assertFalse(getEntitlementNumber.isEmpty(), "No entitlement number was captured");
		TestReporter.logStep("Captured Entitlement Number is : " + getEntitlementNumber);

		return getEntitlementNumber;

	}

	/**
	 * @summary : Method to verify the ticket is purchased successfully.
	 * @author : Praveen Namburi
	 * @version : Created 12/06/2015
	 */
	public void verifyTicketsWereSold() {
		String CapturedEntitlementNumber = capturingEntitlementNum();

		TestReporter.assertTrue(CapturedEntitlementNumber != null, "The Ticket is not purchased successfully.");

	}

	/**
	 * 
	 * @summary Method to enter multiple guest details
	 * @version Created 12/03/2014
	 * @author Venkatesh A
	 * @param Scenario
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void enterMultipleManageDayGuests(String scenario) {

		datatable.setVirtualtablePage("manageTickets");
		datatable.setVirtualtableScenario(scenario);

		initialize();
		pageLoaded();

		int guestCount = Integer.parseInt(datatable.getDataParameter("NumberOfGuests"));

		if (guestCount > 0) {

			for (int Iterator = 0; Iterator < guestCount; Iterator++) {
				System.out.println("Control in For loop");
				// Click AddGuest button
				clickAddGuest();

				// Click DayGuest Link
				pageLoaded(eleDayGuest);
				clickDayGuest();

				initialize();
				pageLoaded(chkNextToGuest);

				// Enter Guest First Name
				System.out.println("Guest First Name : " + datatable.getDataParameter("FirstName"));
				System.out.println("Id: " + manageGuestTable + (Iterator) + ":firstNameInput");
				driver.findElement(By.id(manageGuestTable + (Iterator) + ":firstNameInput"))
				.sendKeys(datatable.getDataParameter("FirstName"));

				// Enter Guest Last Name
				System.out.println("Guest Last Name : " + datatable.getDataParameter("LastName") + (Iterator + 1));
				System.out.println("Id: " + manageGuestTable + (Iterator) + ":lastNameInput");
				driver.findElement(By.id(manageGuestTable + (Iterator) + ":lastNameInput"))
				.sendKeys(datatable.getDataParameter("LastName") + (Iterator + 1));

				// Select Age Type
				driver.findElement(By.id(manageGuestTable + (Iterator) + ":ageTypeSelect"))
				.sendKeys(datatable.getDataParameter("AgeType"));

				// Select Check
				driver.findElement(By.id(manageGuestTable + (Iterator) + ":ticketDetailSelected2")).click();
			}
		}
	}

	/**
	 * 
	 * @summary Method to Select offer and select multiple guests to match with
	 *          tickets
	 * @version Created 12/04/2014
	 * @author Venkatesh A
	 * @param Scenario
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void selectOffer(String scenario) {
		datatable.setVirtualtablePage("manageTickets");
		datatable.setVirtualtableScenario(scenario);

		// Grab Number of guests
		int guestCount = Integer.parseInt(datatable.getDataParameter("NumberOfGuests"));
		pageLoaded(btnTicketOffer);
		TestReporter.logStep("Click Offer List box");
		btnTicketOffer.syncVisible(driver);
		btnTicketOffer.jsClick(driver);
		initialize();

		// Selecting Guest count in offers page
		driver.findElement(By.xpath(ticketOfferSelectList + (guestCount + 1) + "]")).click();

		TestReporter.logStep("Clicking on Proceed Button");
		pageLoaded(btnProceed);
		btnProceed.highlight(driver);
		btnProceed.jsClick(driver);

		// Iterator to select multiple guests to match with tickets
		for (int Iterator = 0; Iterator < guestCount; Iterator++) {
			// Select Guest to match with tickets
			driver.findElement(
					By.xpath(guestDistribution + (Iterator) + guestDistributionSelect + (Iterator + 2) + "]")).click();
		}
	}

	/**
	 * 
	 * @summary Method to take payment from manage ticket window
	 * @version Created 12/04/2014
	 * @author Venkatesh A
	 * @param Scenario
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void takePayment(String scenario){
		datatable.setVirtualtablePath(Datatable.PAYMENTUI_MASTER_DATA_PATH);
		datatable.setVirtualtablePage("PaymentUI");
		datatable.setVirtualtableScenario(scenario);
		
		paymentTypes paymentType = PaymentUIWindow.getpaymentTypeEnum(datatable.getDataParameter("PaymentType"));
		paymentMethods paymentMethod = PaymentUIWindow
				.getpaymentMethodEnum(datatable.getDataParameter("PaymentMethod"));
		String status = datatable.getDataParameter("Status");
		String delay = datatable.getDataParameter("Delay");
		String expiredCC = datatable.getDataParameter("ExpiredCC"); 
		String primaryGuestInfo = datatable.getDataParameter("UsePrimaryGuestInfo");		
		
		boolean incidentals = true;
		if (datatable.getDataParameter("Incidentals").equalsIgnoreCase("false")) {
			incidentals = false;
		}
		String amount = datatable.getDataParameter("Amount");
		String errorMessage = datatable.getDataParameter("ExpectedErrorMessage");

		//Updating the following code to use WindowHandler methods
//		do {
//			Sleeper.sleep(500);
//		} while (driver.getWindowHandles().size() <= 1);
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);

//		for (String handle : driver.getWindowHandles()) {
//			if (driver.switchTo().window(handle).getTitle().contains("Apply Payment")) {
//				break;
//			}
//		}
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Apply Payment");

		PaymentUIWindow paymentUI = new PaymentUIWindow(driver);
		paymentUI.takePayment(paymentType, paymentMethod, status, delay, incidentals, 
				amount, expiredCC, primaryGuestInfo, errorMessage);

		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
//		driver.switchTo().window(winHandleBefore);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Lilo");

		new ProcessingYourRequest().WaitForProcessRequest(driver);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * 
	 * @summary Method to retrieve guest Row frome Manage gUest Table after
	 *          payment
	 * @version Created 12/04/2014
	 * @author Venkatesh A
	 * @param Number
	 *            of Guests
	 * @throws NA
	 * @return NA
	 * 
	 */
	public static ArrayList<String> capturingGuestNames(int NumberOfGuests) {

		ArrayList<String> guestNameRows = new ArrayList<String>();
		// int Counter = 0;
		int sum = 1;
		for (int guestNamesIterator = 1; guestNamesIterator <= NumberOfGuests; guestNamesIterator++) {
			// System.out.println(sum);
			String StrVal = Integer.toString(sum);
			guestNameRows.add(StrVal);
			sum += 3;
		}
		return guestNameRows;

	}

	/**
	 * 
	 * @summary Method to Validate Guest Name and Entitlement for multiple
	 *          Guests on Manage Tickets Window
	 * @version Created 12/04/2014
	 * @author Venkatesh A
	 * @param Scenario
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void validatingGuestNameAndEntitlement(String scenario) {
		datatable.setVirtualtablePage("manageTickets");
		datatable.setVirtualtableScenario(scenario);
		
		pageLoaded();

		//Guest count from Datatable
		int guestCount = Integer.parseInt(datatable.getDataParameter("NumberOfGuests"));

		ArrayList<String> rowNumber = capturingGuestNames(guestCount);

		//Grab Available Guest Names and Entitlement from Recommend and Manage table after payment
		for(int i=1; i<=guestCount; i++){

			//Grab Guest Name
			String GuestName = driver.findElement(By.xpath("//*[@id='manageTicketsForm:accommodationTicketsListTable:tb']/tr["+rowNumber.get(i-1)+"]/td[3]")).getText();
			System.out.println("GuestName : "+GuestName);
			TestReporter.assertTrue(!(GuestName).equals(null), "Guest Name not found in 'Recommend and Manage Guest Tickets' table row number : "+rowNumber);

			//Grab Entitlement numbers
			String Entitlement = driver.findElement(By.xpath("//*[@id='manageTicketsForm:accommodationTicketsListTable:tb']/tr["+(3*i)+"]/td[6]")).getText();
			System.out.println("Entitlement  : "+Entitlement);
			TestReporter.assertTrue(!(Entitlement).equals(null), "Entitlement not found in 'Recommend and Manage Guest Tickets' table row number : "+(3*i));
		}

	}

	/**
	 * @summary : Method to capture the guest details and validate whether
	 * 			  the ticket is purchased successfully.
	 * @version : Created 12/08/2014, @author  : Praveen Namburi
	 * @param 	: Scenario
	 */
	public void captureGuestDetailsAndVerifyTicketWasSold(String scenario){   	
		datatable.setVirtualtablePage("manageTickets");
		datatable.setVirtualtableScenario(scenario);

		initialize();
		pageLoaded();   	
		//Guest count from Datatable
		int guestCount = Integer.parseInt(datatable.getDataParameter("NumberOfGuests"));   	
		ArrayList<String> rowNumber = capturingGuestNames(guestCount);

		//Grab Available Guest Name,TypeOrAge,Status and Entitlement from Recommend and Manage table after payment
		for(int i=1; i<=guestCount; i++){

			TestReporter.logStep("---------------------------------");
			//Grab Guest Name											                                                 
			String GuestName = driver.findElement(By.xpath("//*[@id='manageTicketsForm:accommodationTicketsListTable:tb']/"
					+ "tr["+rowNumber.get(i-1)+"]/td[3]")).getText();
			TestReporter.logStep("GuestName : "+GuestName);
			TestReporter.assertTrue(!(GuestName).equals(null), 
					"Guest Name not found in 'Recommend and Manage Guest Tickets' table row number : "+rowNumber);

			//Grab the Type/Age
			String TypeOrAge = driver.findElement(By.xpath("//*[@id='manageTicketsForm:accommodationTicketsListTable:tb']/"
					+ "tr["+rowNumber.get(i-1)+"]/td[4]")).getText(); 
			TestReporter.logStep("TypeOrAge  : "+TypeOrAge);
			TestReporter.assertTrue(!(TypeOrAge).equals(null), 
					"TypeOrAge not found in 'Recommend and Manage Guest Tickets' table row number : "+(3*i));

			//Grab the Status
			String Status = driver.findElement(By.xpath("//*[@id='manageTicketsForm:accommodationTicketsListTable:tb']/"
					+ "tr["+(3*i)+"]/td[3]")).getText(); 
			TestReporter.logStep("Status  : "+Status);
			TestReporter.assertTrue(!(Status).equals(null), 
					"Status not found in 'Recommend and Manage Guest Tickets' table row number : "+(3*i));

			//Grab Entitlement numbers
			String Entitlement = driver.findElement(By.xpath("//*[@id='manageTicketsForm:accommodationTicketsListTable:tb']/"
					+ "tr["+(3*i)+"]/td[6]")).getText();
			TestReporter.logStep("Entitlement  : "+Entitlement);
			TestReporter.assertTrue(!(Entitlement).equals(null), 
					"Entitlement not found in 'Recommend and Manage Guest Tickets' table row number : "+(3*i));

			//Verifying that ticket is sold successfully for the manage guest user.	    	
			TestReporter.assertTrue(!(Entitlement).equals(null), "Ticket is not purchased for the Guest : ["+GuestName+"].");
			TestReporter.logStep("---------------------------------");
		}

	}

	/**
	 * @summary Method to search for an order using the Entitlement Number
	 *          Textbox and clicking the GO button.
	 * @version Created 12/11/2014
	 * @author Dennis Stagliano
	 * @param String
	 *            Entitlement Number
	 * @throws NA
	 * @return NA
	 */
	public void searchforEntitlement(String entitlementNumber) {
		// Enter the entitlement number into the textbox
		pageLoaded(txtEntitlementNumberInput);
		txtEntitlementNumberInput.safeSetValidate(driver, entitlementNumber);
		btnEntitlementGoButton.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
<<<<<<< HEAD
	 * @summary : Method to verify whether the existing tickets button is enabled.
	 * @author  : praveen namburi,  @version : Created 12-22-2015
	 */
	public void verifyExistingTicketsButtonEnabled() {
		pageLoaded(btnExistingTickets);
		TestReporter.assertTrue(btnExistingTickets.syncEnabled(driver), 
				"Existing Tickets button is not Enabled");
		btnExistingTickets.jsClick(driver);
	}

	/**
	 * @summary : Method to check the accomodation check-box from accomodation 
	 * 			  ticket list table.
	 * @author  : Praveen namburi,  @Version : Created 12-22-2015
	 */
	public void chkAccomodationTicket(){
		pageLoaded(chkAccomodationTicket);
		chkAccomodationTicket.syncVisible(driver);
		chkAccomodationTicket.highlight(driver);
		chkAccomodationTicket.jsToggle(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);		
	}

	/**
	 * @summary : Method to click on the RefundTickets button.
	 * @author  : Praveen Namburi ,  @version : Created 12-22-2015
	 */
	public void clickRefundTicketsButton(){
		pageLoaded(btnRefundTickets);
		btnRefundTickets.syncVisible(driver);
		btnRefundTickets.highlight(driver);
		btnRefundTickets.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);		
	}

	/**
	 * @summary : Method to select the refundTicket check-box and
	 * 			  click on Refund button.
	 * @author  : Praveen Namburi,  @version : Created 12-22-2015
	 */
	public void captureRefundAmountAndClickRefundBtn(){
		initialize();
		pageLoaded(chkRefundTicket);
		chkRefundTicket.syncVisible(driver);
		chkRefundTicket.highlight(driver);
		chkRefundTicket.jsToggle(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		//Get Total refundable amount
		String captureRefundAmount = getRefundAmount();	
		TestReporter.logStep("Total refundable amount : " + captureRefundAmount);

		pageLoaded(btnRefund);
		btnRefund.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);

	}

	/**
	 * @summary : Method to capture the Refund amount.
	 * @author  : Praveen namburi , @version : Created 12-22-2015
	 * @return  : string Refund amount.
	 */
	public String getRefundAmount(){
		pageLoaded(eleGetRefundAmount);
		eleGetRefundAmount.highlight(driver);
		String RefundAmount = eleGetRefundAmount.getText().replace("$", "-");	
		return RefundAmount;
	}

	/**
	 * @summary : Method to handle the Ticket Refund Success pop-up.
	 * @author  : praveen namburi , @version : Created 12-22-2015
	 */
	public void handleTicketRefundSuccessPopUp(){
		initialize();

		TestReporter.logStep("Syncing for Ticket Refund Success Popup.");
		pageLoaded(eleTicketRefundPopUp);
		if (eleTicketRefundPopUp.isDisplayed() == true) {
			TestReporter.logStep("Ticket Refund Success PopUp is displayed.");
			String ExpRefundSuccessText = "Ticket have been refunded successfully.";
			String ActRefundSuccessText = eleGetTicketREfundText.getText();
			System.out.println("Pop-up message is : "+ActRefundSuccessText);
			boolean RefundSuccessMsg = ActRefundSuccessText.toLowerCase().contains(ExpRefundSuccessText.toLowerCase());

			if (RefundSuccessMsg == true) {
				TestReporter.assertTrue(RefundSuccessMsg, "Actual String[" + ActRefundSuccessText
						+ "] does not contains Expected string[" +ExpRefundSuccessText + "]");
				initialize();
				pageLoaded(btnRefundSuccessPopupOk);
				btnRefundSuccessPopupOk.highlight(driver);
				btnRefundSuccessPopupOk.click();
			}

		}
	}

	/**
	 * @summary : Method to verify the Ticket status is changed to Void.
	 * @author  : Praveen Namburi , @version : Created 12-22-2015.
	 */
	public void verifyTicketVoidStatus(){
		pageLoaded(eleGetTicketStatus);
		eleGetTicketStatus.syncVisible(driver);
		eleGetTicketStatus.highlight(driver);
		String tktVoidStatus = eleGetTicketStatus.getText();
		TestReporter.logStep("Void ticket Status  : " + tktVoidStatus);
		String ExpectedStatus = "Void";
		TestReporter.assertEquals(tktVoidStatus, ExpectedStatus, 
				"The Ticket status not changed from 'Active' to" + tktVoidStatus);

	}

	/**
	 * @summary : Method to refund the payment for ticket in manage tickets page.
	 * @version : Created 12-23-2015, @author : Praveen Namburi.
	 * @param   : Totalrefundableamount
	 */
	public void refundPaymentInManageTickets(String Totalrefundableamount) {

		Sleeper.sleep(4000);
		String winHandleBefore = driver.getWindowHandle();
		int countofwindows = driver.getWindowHandles().size();
		System.out.println("Count of windows : "+countofwindows);

		for(String handle : driver.getWindowHandles()){
			if(driver.switchTo().window(handle).getTitle().contains("Apply Payment")){
				TestReporter.logStep("Apply payment window is displayed.");
				break;
			}
		}

		initialize();
		TestReporter.logStep("Enter the Refund amount : "+Totalrefundableamount);
		Sleeper.sleep(2000);
		txtPaymentAmount.highlight(driver);
		pageLoaded(txtPaymentAmount);
		txtPaymentAmount.syncVisible(driver);

		txtPaymentAmount.click();
		txtPaymentAmount.safeSet(Totalrefundableamount);

		TestReporter.logStep("Click on text-link Apply & handle warning pop-ups.");
		pageLoaded(lnkApply);
		lnkApply.highlight(driver);
		lnkApply.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		Sleeper.sleep(1500);
		initialize();
		pageLoaded(btnDupDocumentContinue);
		btnDupDocumentContinue.syncVisible(driver);
		btnDupDocumentContinue.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		handleSuccessPopup_PaymentWindow();

		driver.switchTo().window(winHandleBefore);
	}

	/**
	 * @summary : Method to handle Success POPUP after ticket refund in manage tickets page.
	 * @author  : Praveen Namburi
	 * @version : Created 12/23/2015
	 */
	public void handleSuccessPopup_PaymentWindow(){

		initialize();
		pageLoaded(eleSuccessPopup);
		eleSuccessPopup.syncVisible(driver);

		if(!eleSuccessPopup.isDisplayed()){
			TestReporter.logStep("Success Pop-up is not loaded.");
		}else{
			TestReporter.logStep("Success Pop-up is loaded.");	    		
			pageLoaded(eleGetSuccessPopuptext);
			eleGetSuccessPopuptext.syncVisible(driver);
			String getSuccessPOPUPMsg = eleGetSuccessPopuptext.getText();
			TestReporter.logStep("Actual Pop-up message is : " + getSuccessPOPUPMsg);

			TestReporter.logStep("Click on Ok button.");
			pageLoaded(btnSuccessPopupOk);
			btnSuccessPopupOk.highlight(driver);
			btnSuccessPopupOk.click();
			new ProcessingYourRequest().WaitForProcessRequest(driver);

		}

	}

	/**
	 * @summary : Method to close managetickets window and,
	 * 			  click on Ok button in Confirmation pop-up.
	 * @author  : praveen Namburi, @version : Created 12-29-2015.
	 */
	public void closeManagetickets(){
		clickCloseManageTickets();
		//click 'Ok' button in confirmation pop-up
		pageLoaded(btnConfirmationOK);
		btnConfirmationOK.highlight(driver);
		btnConfirmationOK.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * @summary : Method to handle the second refund success pop-up.
	 * @author  : praveen Namburi, @version : Created 12-29-2015.
	 */
	public void closeRefundSuccessPopUp(){

//		initialize();		
		TestReporter.logStep("Syncing for Ticket Refund Success Popup.");
		pageLoaded(eleTicketRefundPopUp);
		if (eleTicketRefundPopUp.isDisplayed()) {
			TestReporter.logStep("Ticket Refund Success PopUp is displayed.");
			pageLoaded(btnOkInRefundPOPUP);
			btnOkInRefundPOPUP.highlight(driver);
			btnOkInRefundPOPUP.jsClick(driver);			
		}
	}

	/**
	 *@summary : Method to cancel the SEcond printer Encoder.
	 *@bersion : Created 23-22-2015, @author : Praveen namburi.
	 */
	public void cancelSecondEncoder(){

		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		pageLoaded(btnCancelSecondEncoder);
		if(btnCancelSecondEncoder.syncVisible(driver, 3, false)){
			btnCancelSecondEncoder.highlight(driver);
			btnCancelSecondEncoder.jsClick(driver);;
		}
		pageLoaded(btnCancelSecondEncoder);
		if(btnCancelSecondEncoder.syncVisible(driver, 3, false)){
			btnCancelSecondEncoder.highlight(driver);
			btnCancelSecondEncoder.jsClick(driver);;
		}
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
	}	

	/* @summary Method to handle book ticket confirmation popup
	 * @author Atmakuri Vekatesh
	 * @version 12/21/2015 
	 * @param Datatable scenario
	 * @return NA
	 */
	public void handleBookTicketPopup(String scenario) {
		TestReporter.logStep("Syncing for Popup's");

		if (lblBookTktCofirmationPopupHeader.syncVisible(driver) == true) {

			TestReporter.logStep("Handling Book Ticket Confirmation Popup");
			initialize();
			String ExpectedPurchaseText = "Please Complete the Payment";
			String ActualPurchaseText = eleBookTktConfirmationPopupText.getText();
			boolean BookedStatus = ActualPurchaseText.toLowerCase().contains(ExpectedPurchaseText.toLowerCase());
			String BookConfirmation = datatable.getDataParameter("BookConfirmation").toLowerCase();
			if (BookedStatus == true & BookConfirmation == "false")   {
				TestReporter.assertTrue(BookedStatus, "Actual String[" + ActualPurchaseText
						+ "] does not contains Expected string[" + ExpectedPurchaseText + "]");
				initialize();
				pageLoaded(btnCancelTickets);
				btnCancelTickets.jsClick(driver);
				new ProcessingYourRequest().WaitForProcessRequest(driver);
				TestReporter.logStep("Clicked on the Cancel Tickets");

			}else if (BookedStatus == true & BookConfirmation == "true")   {
				TestReporter.assertTrue(BookedStatus, "Actual String[" + ActualPurchaseText
						+ "] does not contains Expected string[" + ExpectedPurchaseText + "]");
				initialize();
				pageLoaded(btnTakePayment);
				btnTakePayment.highlight(driver);
				btnTakePayment.click();
				TestReporter.logStep("Clicked on the Take Payment");
			}
		}

	}

	/**
	 * @summary Method to cancel a ticket without purchase
	 * @author Atmakuri Vekatesh
	 * @version 12/21/2015 
	 * @param Datatable scenario
	 * @return NA
	 */
	public void cancelPurchaseTickets(String scenario){

		datatable.setVirtualtablePage("manageTickets");
		datatable.setVirtualtableScenario(scenario);

		// To click AddGuest link
		clickAddGuest();

		// To click Day Guest link
		clickDayGuest();

		// Selecting the guest
		selectGuest();

		// entering the guest details
		enterManageGuestDetails(scenario);

		// Click recommend
		clickBtnRecommend();

		// Click Search button
		clickBtnSearch();

		// selecting the theme park tickets for age 10+s
		btnlstAge10plusprice.click();
		pageLoaded(elePartyMixListItem);
		elePartyMixListItem.click();

		pageLoaded(btnProceed);
		btnProceed.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		// selecting the Guest Name from datatable
		lstTicketType.select(datatable.getDataParameter("FirstName") + " " + datatable.getDataParameter("LastName"));
		Sleeper.sleep(3000);
		initialize();

		//Click purchase button
		clickBtnPurchase();

		//handles the confirmation popup
		handleBookTicketPopup(scenario);

		//handles the ticket cancellation success popup
		handleWarningsErrorsAndSuccessPopup();

	}


	/**
	 * @summary Method to recommend tickets
	 * @author Marella Satish
	 * @version 12/22/2015 
	 * @param Datatable scenario
	 * @return NA
	 * @throws NA 
	 */
	public String getEntitlementValue() {
		// grab the entitlement value
		initialize();
		pageLoaded(eleEntitlementValue);
		eleEntitlementValue.syncVisible(driver);
		String getEntitlementValue = eleEntitlementValue.getText();
		TestReporter.logStep("Entitlement value : " + getEntitlementValue);

		return getEntitlementValue;

	}

	/**
	 * @summary Method to recommend tickets
	 * @author Marella Satish
	 * @version 12/22/2015 
	 * @param Datatable scenario
	 * @return NA
	 * @throws NA 
	 */
	public void recommendTickets(String scenario) {

		// To click AddGuest link
		clickAddGuest();

		// To click Day Guest link
		clickDayGuest();

		// Selecting the guest
		selectGuest();

		// entering the guest details
		enterManageGuestDetails(scenario);

		// Click recommend
		clickBtnRecommend();
	}

	/**
	 * @summary Method to search and purchase tickets
	 * @author Marella Satish
	 * @version 12/22/2015 
	 * @param Datatable scenario
	 * @return Entitlement value as string
	 * @throws Exception 
	 */
	public String searchAndPurchaseTickets(String scenario,String tktPayment) {

		datatable.setVirtualtablePage("manageTickets");
		datatable.setVirtualtableScenario(scenario);

		// Click Search button
		clickBtnSearch();

		// selecting the theme park tickets for age 10+s
		btnlstAge10plusprice.click();
		pageLoaded(elePartyMixListItem);
		elePartyMixListItem.click();

		pageLoaded(btnProceed);
		btnProceed.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		lstTicketType.highlight(driver);

		String GuestName = datatable.getDataParameter("FirstName") + " " + datatable.getDataParameter("LastName");

		TestReporter.logStep("GuestName "+GuestName);

		// selecting the Guest Name from datatable
		lstTicketType.select(GuestName);
		Sleeper.sleep(3000);
		initialize();

		//Click purchase button
		clickBtnPurchase();

		//handles the confirmation popup
		handleBookTicketPopup(scenario);

		//Ticket Payment through Credit Card
		takePayment(tktPayment);

		//cancel the set printer encoder popup
		clickCancelEncoder();

		//handles the ticket confirmation popup
		handleWarningsErrorsAndSuccessPopup();

		String Entitlement = getEntitlementValue();
		return Entitlement;


	}


	/**
	 * @summary Method to  modify the existing tickets and verify the update tickets
	 * @author Marella Satish
	 * @version 12/22/2015 
	 * @param Datatable scenario
	 * @return NA
	 * @throws Exception 
	 */
	public void modifyAndVerifyExistingTickets(String scenario,String tktPayment) {

		//Perform recommend ticket operations
		recommendTickets(scenario);

		//Perform first ticket search and payment of ticket throug credit card operations
		String FirstPurchasedTicket = searchAndPurchaseTickets(scenario, tktPayment);

		//Checks the entitlement
		checkEntitlement();

		//click existing tickets link
		clickExistingTickets();

		//clicks modify link
		clickModify();

		//Perform second ticket search and payment of ticket throug credit card operations
		String SecondPurchasedTicket = 	searchAndPurchaseTickets(scenario, tktPayment);
		TestReporter.logStep("First Purchased Ticket : "+FirstPurchasedTicket);
		TestReporter.logStep("Second Purchased Ticket : "+SecondPurchasedTicket);
		TestReporter.assertNotEquals(FirstPurchasedTicket, SecondPurchasedTicket, "The tickets failed to update");
		TestReporter.logStep("Existing ticket modified by user sucessfully on Media");
	}


	public void enterDisneyRewardCardDetails(String scenario) {
		datatable.setVirtualtablePath(Datatable.PAYMENTUI_MASTER_DATA_PATH);
		datatable.setVirtualtablePage("PaymentUI");
		datatable.setVirtualtableScenario(scenario);

		String paymentUiHandle = "";
		String[] paymentTypes = datatable.getDataParameter("PaymentType").split(";");
		paymentTypes paymentType; 
		String[] paymentMethods = datatable.getDataParameter("PaymentMethod").split(";");
		paymentMethods paymentMethod;
		String[] statuses = datatable.getDataParameter("Status").split(";");
		String status;
		String[] delays = datatable.getDataParameter("Delay").split(";");
		String delay;
		String[] incidentals = datatable.getDataParameter("Incidentals").split(";");
		boolean incidental;
		String[] amounts = datatable.getDataParameter("Amount").split(";");
		String amount;
		String split = datatable.getDataParameter("SplitPayment");
		String expiredCC = datatable.getDataParameter("ExpiredCC"); 
		String primaryGuestInfo = datatable.getDataParameter("UsePrimaryGuestInfo");
		
		StopWatch watch = new StopWatch();
		watch.start();
		do {
			Sleeper.sleep(500);
		} while (driver.getWindowHandles().size() < 2 && watch.getTime() < WebDriverSetup.getDefaultTestTimeout() * 1000);
		watch.stop();
		if(watch.getTime() > WebDriverSetup.getDefaultTestTimeout() * 1000){
			TestReporter.logFailure("The Payment UI did not load within ["+watch.getTime()+"] milliseconds");
		}
		WindowHandler wh = new WindowHandler();
		wh.waitUntilWindowExists(driver, "Apply Payment");
//		WindowHandler.waitUnitNubmerOfWindowsExist(driver, 2, 60);

		for (String handle : driver.getWindowHandles()) {
			if (driver.switchTo().window(handle).getTitle().contains("Apply Payment")) {
				paymentUiHandle = driver.getWindowHandle();
				break;
			}
		}
		TestReporter.assertTrue(driver.getTitle().contains("Apply Payment"), "The title of the page did not contain 'Apply Payment'");
		PaymentUIWindow paymentUI = new PaymentUIWindow(driver);
		
		for(int payment = 0; payment < paymentTypes.length; payment++){
			paymentType = PaymentUIWindow.getpaymentTypeEnum(paymentTypes[payment]);
			paymentMethod = PaymentUIWindow
					.getpaymentMethodEnum(paymentMethods[payment]);
			status = statuses[payment];
			delay = delays[payment];
			incidental = true;
			if (incidentals[payment].equalsIgnoreCase("false")) {
				incidental = false;
			}
			amount = amounts[payment];

			paymentUI.takePaymentWithSplit(paymentType, paymentMethod, status, 
					delay, incidental, amount, split, expiredCC, primaryGuestInfo);
			if(payment != paymentTypes.length - 1){
				paymentUI.clickSplitPayment();
			}
		}
		
		for(String handle : driver.getWindowHandles()){
			if(!handle.equalsIgnoreCase(paymentUiHandle)){
				driver.switchTo().window(handle);
			}
		}
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary : Method to perform the split payment in ManageTickets page.
	 * @author : Praveen Namburi, @version : Created 12-31-2015,
	 * @param scenario, @throws Exception
	 */
	public void splitPayments(String scenario) throws Exception {
		System.out.println(scenario);
		datatable.setVirtualtablePath(Datatable.PAYMENTUI_MASTER_DATA_PATH);
		datatable.setVirtualtablePage("PaymentUI");
		datatable.setVirtualtableScenario(scenario);		

		//split payment button
		pageLoaded(btnSplitPayment);
		btnSplitPayment.highlight(driver);
		btnSplitPayment.click();
		//Ok button in Success pop-up. 
		btnOkSucssesPayment.highlight(driver);
		btnOkSucssesPayment.jsClick(driver);
		
//		String winHandleBefore = driver.getWindowHandle();
		String[] paymentType = datatable.getDataParameter("PaymentType").split(";");
		PaymentUIWindow.paymentTypes[] types = new PaymentUIWindow.paymentTypes[paymentType.length];
		System.out.println("=========="+paymentType.length);
		for(int type = 0; type < paymentType.length; type++){
			types[type] = PaymentUIWindow.getpaymentTypeEnum(paymentType[type]);
		}
		String[] paymentMethod = datatable.getDataParameter("PaymentMethod").split(";");
		PaymentUIWindow.paymentMethods[] methods = new PaymentUIWindow.paymentMethods[paymentMethod.length];
		for(int method = 0; method < paymentMethod.length; method++){
			methods[method] = PaymentUIWindow.getpaymentMethodEnum(paymentMethod[method]);
		}
		String[] status = datatable.getDataParameter("Status").split(";");
		String[] delay = datatable.getDataParameter("Delay").split(";");
		String[] strIncidentals = datatable.getDataParameter("Incidentals").split(";");
		String[] splits = datatable.getDataParameter("SplitPayment").split(";");
		String[] expiredCC = datatable.getDataParameter("ExpiredCC").split(";"); 
		String[] primaryGuestInfo = datatable.getDataParameter("UsePrimaryGuestInfo").split(";");
		boolean[] incidentals = new boolean[strIncidentals.length];
		for(int bool = 0; bool < strIncidentals.length; bool++){
			if(strIncidentals[bool].equalsIgnoreCase("true")){
				incidentals[bool] = true;
			}else{
				incidentals[bool] = false;
			}
		}
		
		String[] amount = datatable.getDataParameter("Amount").split(";");

		for(int pays = 0; pays < types.length; pays++){
			System.out.println("Payment type " +String.valueOf(pays) + ": " + paymentType[pays]);
			System.out.println("Payment method " +String.valueOf(pays) + ": " + paymentMethod[pays]);
			System.out.println("Payment status " +String.valueOf(pays) + ": " + status[pays]);
			System.out.println("Payment delay " +String.valueOf(pays) + ": " + delay[pays]);
			System.out.println("Payment incidentals " +String.valueOf(pays) + ": " + strIncidentals[pays]);
			System.out.println("Payment amount " +String.valueOf(pays) + ": " + amount[pays]);

			PaymentUIWindow paymentUIWindow = new PaymentUIWindow(driver);
			paymentUIWindow.takePaymentWithSplit(
					PaymentUIWindow.getpaymentTypeEnum(paymentType[pays]),
					PaymentUIWindow.getpaymentMethodEnum(paymentMethod[pays]), 
					status[pays], delay[pays], incidentals[pays], amount[pays], 
					splits[pays], expiredCC[pays], primaryGuestInfo[pays]);
		}
		
		
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary : Calling methods in method to enter disney reward card details
	 * 			 and make split payments.
	 * @version : Created 31-12-2015.
	 * @author  : Praveen Namburi.
	 * @param scenario1
	 * @param scenario2
	 */
	public void enterDisneyRewardCardInfoAndMakesplitPayments(String scenario1, String scenario2){
		String before = driver.getWindowHandle();
		TestReporter.logStep("Clicking on Payment button.");
		clickPaymentButton();
		
		//Provide disney Reward Card details and make Balance Inquiry.
		TestReporter.logStep("Provide disney Reward Card details and make Balance Inquiry.");
		try {
			enterDisneyRewardCardDetails(scenario1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		//Performing the Split Payments here.
		TestReporter.logStep("Performing the Split Payments here.");
		try {
			splitPayments(scenario2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.switchTo().window(before);
	}
	
	/*public void enterGroupCode(String environment, String groupType){
		String groupCode = retrieveGroupValue(environment, groupType );
		//System.out.println(groupCode);
		pageLoaded(txtGroupCode);
		txtGroupCode.syncVisible(driver);
		txtGroupCode.safeSet(groupCode);
		
		boolean groupName = false;
		StopWatch sw = new StopWatch();
		sw.start();
		do{
			try{
				groupName = !txtGroupName.getText().isEmpty();
			}catch(Exception e){
			}
		}while(sw.getTime() < Constants.ELEMENT_TIMEOUT * 1000 && !groupName);
		sw.stop();
		TestReporter.assertTrue(sw.getTime() < Constants.ELEMENT_TIMEOUT * 1000, "The group name was not populated after ["+Constants.ELEMENT_TIMEOUT+"] seconds.");
	}*/
	
	/*private String retrieveGroupValue(String environment, String groupType){
//		datatable.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);
//		datatable.setVirtualtablePage("GroupNumbers");
//		datatable.setVirtualtableScenario(scenario);
//		String value = datatable.getDataParameter(parameter);
//		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
		GroupNumbers group = new GroupNumbers(environment, groupType);
		return group.getGroupNumber(); 
	}*/
}
