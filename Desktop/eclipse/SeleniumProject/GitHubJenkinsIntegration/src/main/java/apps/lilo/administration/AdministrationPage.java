package apps.lilo.administration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import apps.lilo.bankIn.BankInPage;
import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Link;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

public class AdministrationPage {
	//
	// Page Fields
	//
	private Datatable datatable = new Datatable();
	private WebDriver driver;
	
	// *************************************
	// *** Resort Function Page Elements ***
	// *************************************
	// Create Link object for Global Manage User
	@FindBy(id = "menuForm:manageUserId")
	private Link lnkGlobalManageUser;

	// Create Link object for Global Maintain Campus
	@FindBy(id = "menuForm:maintaincampus")
	private Link lnkGlobalMaintainCampus;

	// Create Link object for Global Maintain Resorts
	@FindBy(id = "menuForm:manageresorts")
	private Link lnkGlobalMaintainResorts;

	// Create Link object for Global Housekeeping Types
	@FindBy(xpath = "//*[@id=\"menuForm:linksSection\"]/table/tbody/tr[2]/td/table/tbody/tr/td[2]/div/table/tbody/tr/td[1]/div/a[4]")
	private Link lnkGlobalHousekeepingTypes;

	// Create Link object for Global Common Doors
	@FindBy(xpath = "//*[@id=\"menuForm:linksSection\"]/table/tbody/tr[2]/td/table/tbody/tr/td[2]/div/table/tbody/tr/td[1]/div/a[5]")
	private Link lnkGlobalCommonDoor;

	// Create Link object for Housekeeping Late Checkouts
	@FindBy(id = "menuForm:managelatecheckout")
	private Link lnkHousekeepingLateCheckouts;

	// Create Link object for Guest Stay Maintain Fixed Comments
	@FindBy(id = "menuForm:mainmenufixedcomment")
	private Link lnkGuestStayMaintainFixedCommentType;

	// Create Link object for Room Inventory Maintain Room Sequence
	@FindBy(id = "menuForm:roomsequence")
	private Link lnkRoomInventoryMaintainRoomSequence;

	// Create Link object for Room Inventory Maintain Room Attribute Type
	@FindBy(id = "menuForm:roomattributestype")
	private Link lnkRoomInventoryMaintainRoomAttributeType;

	// Create Link object for Room Inventory Maintain Room Attributes
	@FindBy(id = "menuForm:roomattributes")
	private Link lnkRoomInventoryMaintainRoomAttributes;

	// Create Link object for Room Inventory Maintain Room Rack
	@FindBy(id = "menuForm:roomrack")
	private Link lnkRoomInventoryMaintainRoomRack;

	// Create Link object for Room Inventory Maintain Status Type
	@FindBy(xpath = "//*[@id=\"menuForm:linksSection\"]/table/tbody/tr[2]/td/table/tbody/tr/td[2]/div/table/tbody/tr/td[1]/div/a[12]")
	private Link lnkRoomInventoryMaintainStatusType;

	// Create Link object for Room Inventory Update Room Status
	@FindBy(id = "menuForm:roomstatus")
	private Link lnkRoomInventoryUpdateRoomStatus;

	// Create Link object for Room Inventory Maintain Status Transition Rules
	@FindBy(id = "menuForm:statustransitionrules")
	private Link lnkRoomInventoryMaintainStatusTransition;

	// Create Link object for Room Inventory Maintain Location
	@FindBy(id = "menuForm:maintainlocation")
	private Link lnkRoomInventoryMaintainLocation;

	// Create Link object for Finance Archive Search
	@FindBy(id = "menuForm:archiveSearch")
	private Link lnkFinanceArchiveSearch;

	// Create Link object for Finance Accounting Center
	@FindBy(id = "menuForm:accountingcenter")
	private Link lnkFinanceAccountingCenter;

	// Create Link object for Finance Accounting Center Payment Method
	@FindBy(id = "menuForm:accountingcenterpaymentmethod")
	private Link lnkFinanceAccountingCenterPaymentMethod;

	// Create Link object for Finance Posting Item
	@FindBy(id = "menuForm:accountingcenterpostingitem")
	private Link lnkFinanceAccountingCenterPostingItem;

	// Create Link object for Finance Maintain Charge Back
	@FindBy(id = "menuForm:autochargeback")
	private Link lnkFinanceMaintainAutoChargeBack;

	// Create Link object for Finance Bill Code
	@FindBy(id = "menuForm:billcode")
	private Link lnkFinanceBillCode;

	// Create Link object for Finance Maintain Coupon
	@FindBy(id = "menuForm:coupon")
	private Link lnkFinanceMaintainCoupon;

	// Create Link object for Finance Maintain Event Type
	@FindBy(id = "menuForm:eventtype")
	private Link lnkFinanceMaintainEventType;

	// Create Link object for Finance Payment Method Type
	@FindBy(id = "menuForm:paymentmethodtype")
	private Link lnkFinancePaymentMethodType;

	// Create Link object for Finance Payment Method
	@FindBy(id = "menuForm:paymentmethod")
	private Link lnkFinancePaymentMethod;
	// Create Link object for Finance Maintain POS
	@FindBy(id = "menuForm:posmastermapper")
	private Link lnkFinanceMaintainPOSMaster;

	// Create Link object for Finance Posting Group
	@FindBy(id = "menuForm:postinggroup")
	private Link lnkFinancePostingGroup;

	// Create Link object for Finance Posting Item
	@FindBy(id = "menuForm:postingitem")
	private Link lnkFinancePostingItem;

	// Create Link object for Finance Posting Item Type
	@FindBy(id = "menuForm:postingitemtype")
	private Link lnkFinancePostingItemType;

	// Create Link object for Finance Maintain Report Category
	@FindBy(id = "menuForm:reportcategory")
	private Link lnkFinanceMaintainReportCategory;

	// Create Link object for Finance Maintain Revenue Type Association
	@FindBy(id = "menuForm:revenuetypeassociation")
	private Link lnkFinanceMaintainRevenueTypeAssocation;

	// Create Link object for Finance Reconcile Travel Agent Commissions
	@FindBy(id = "menuForm:travelcommission")
	private Link lnkFinanceReconcileTravelAgentCommissions;

	// Create Link object for Finance Maintain Travel Agent Commission Rate
	@FindBy(id = "menuForm:travelagentcommissionrate")
	private Link lnkFinanceMaintainTravelAgentCommissionRate;

	// Create Link object for Finance WBS Element
	@FindBy(id = "menuForm:wbselement")
	private Link lnkFinanceWBSElement;

	// Create Link object for Finance Material
	@FindBy(id = "menuForm:material")
	private Link lnkFinanceMaterial;

	// Create Link object for Finance Centralized Accounting Center
	@FindBy(id = "menuForm:centralizedaccountingcenter")
	private Link lnkFinanceCentralizedAccountingCenter;

	// Create Link object for Finance Reason Code
	@FindBy(id = "menuForm:reasoncode")
	private Link lnkFinanceReasonCode;

	// Create Link object for Finance Manage Wholesaler Exception
	@FindBy(id = "menuForm:wholesalerexception")
	private Link lnkFinanceManageWholesalerException;

	// Create Link object for Finance Reconcile Guest Ledger
	@FindBy(id = "menuForm:reconcileguestledger")
	private Link lnkFinanceReconcileGuestLedger;

	// Create Link object for Finance Charge Code Interface
	@FindBy(id = "menuForm:chargecodeinterface")
	private Link lnkFinanceChargeCodeInterface;

	// Create Link object for Finance Clearing Accounts
	@FindBy(id = "menuForm:clearingaccounts")
	private Link lnkFinanceClearingAccounts;

	// Create Link object for Finance Maintain Natural Configuration
	@FindBy(id = "menuForm:naturalconfig")
	private Link lnkFinanceMaintainNaturalConfiguration;

	// Create Link object for Finance View into Pricing
	@FindBy(id = "menuForm:priceId")
	private Link lnkFinanceViewIntoPricing;

	// *********************
	// ** Build page area **
	// *********************

	/**
	 * 
	 * @summary Constructor to initialize the page
	 * @version Created 8/15/2014
	 * @author Justin Phlegar
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public AdministrationPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(BankInPage.class, driver,
				lnkGlobalManageUser);
	}

	public void assertPageLoaded() {
		Assert.assertTrue(pageLoaded(),
				"Lilo Adminstrator Page failed to load.");
	}

	// *****************************************
	// *** Administration Page Interactions ***
	// *****************************************

	/*
	 * Global Area
	 */
	public void enterGlobalMaintainUser() {
		lnkGlobalManageUser.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterGlobalMaintainCampus() {
		lnkGlobalMaintainCampus.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterGlobalMaintainResorts() {
		lnkGlobalMaintainResorts.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterGlobalHousekeepingTypes() {
		lnkGlobalHousekeepingTypes.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterGlobalCommonDoors() {
		// String url =
		// WebDriverSetup.getEnvironmentURLRepository().getString("COMMONDOOR_"
		// +
		// WebDriverSetup.getCurrentEnvironment().toUpperCase().replace(" ",""));

		// Perform the click operation that opens new window
		((JavascriptExecutor) driver).executeScript("window.open()");

		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		driver.manage().window().maximize();
		// driver.navigate().to(url);
		// lnkGlobalCommonDoor.mouseClick();

	}

	/*
	 * Housekeeping Area
	 */
	public void enterHousekeepingLateCheckouts() {
		lnkHousekeepingLateCheckouts.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);

	}

	/*
	 * Guest Stay Area
	 */
	public void enterGuestStayMaintainFixedCommentType() {
		lnkGuestStayMaintainFixedCommentType.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/*
	 * Room Inventory Area
	 */
	public void enterRoomInventoryMaintainRoomSequence() {
		lnkRoomInventoryMaintainRoomSequence.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterRoomInventoryMaintainRoomAttributeType() {
		lnkRoomInventoryMaintainRoomAttributeType.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterRoomInventoryMaintainRoomAttributes() {
		lnkRoomInventoryMaintainRoomAttributes.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterRoomInventoryMaintainRoomRack() {
		lnkRoomInventoryMaintainRoomRack.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterRoomInventoryMaintainStatusType() {
		lnkRoomInventoryMaintainStatusType.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterRoomInventoryUpdateRoomStatus() {
		lnkRoomInventoryUpdateRoomStatus.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterRoomInventoryMaintainStatusTransition() {
		lnkRoomInventoryMaintainStatusTransition.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterRoomInventoryMaintainLocation() {
		lnkRoomInventoryMaintainLocation.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/*
	 * Finance Area
	 */
	public void enterFinanceArchiveSearch() {
		lnkFinanceArchiveSearch.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterFinanceAccountingCenter() {
		lnkFinanceAccountingCenter.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterFinanceAccountingCenterPaymentMethod() {
		lnkFinanceAccountingCenterPaymentMethod.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterFinanceAccountingCenterPostingItem() {
		lnkFinanceAccountingCenterPostingItem.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterFinanceMaintainAutoChargeBack() {
		lnkFinanceMaintainAutoChargeBack.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterFinanceBillCode() {
		lnkFinanceBillCode.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterFinanceMaintainCoupon() {
		lnkFinanceMaintainCoupon.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterFinanceMaintainEventType() {
		lnkFinanceMaintainEventType.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterFinancePaymentMethodType() {
		lnkFinancePaymentMethodType.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterFinancePaymentMethod() {
		lnkFinancePaymentMethod.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterFinanceMaintainPOSMaster() {
		lnkFinanceMaintainPOSMaster.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterFinancePostingGroup() {
		lnkFinancePostingGroup.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterFinancePostingItem() {
		lnkFinancePostingItem.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterFinancePostingItemType() {
		lnkFinancePostingItemType.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterFinanceMaintainReportCategory() {
		lnkFinanceMaintainReportCategory.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterFinanceReconcileTravelAgentCommissions() {
		lnkFinanceReconcileTravelAgentCommissions.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterFinanceMaintainTravelAgentCommissionRate() {
		lnkFinanceMaintainTravelAgentCommissionRate.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterFinanceWBSElement() {
		lnkFinanceWBSElement.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterFinanceMaterial() {
		lnkFinanceMaterial.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterFinanceCentralizedAccountingCenter() {
		lnkFinanceCentralizedAccountingCenter.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterFinanceReasonCode() {
		lnkFinanceReasonCode.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterFinanceManageWholesalerException() {
		lnkFinanceManageWholesalerException.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterFinanceReconcileGuestLedger() {
		lnkFinanceReconcileGuestLedger.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterFinanceChargeCodeInterface() {
		lnkFinanceChargeCodeInterface.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterFinanceClearingAccounts() {
		lnkFinanceClearingAccounts.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterFinanceMaintainNaturalConfiguration() {
		lnkFinanceMaintainNaturalConfiguration.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterFinanceViewIntoPricing() {
		lnkFinanceViewIntoPricing.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
}

