package apps.lilo.modifyReservation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import apps.lilo.quickBook.QuickBookPage;
import core.WebDriverSetup;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Label;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;

/*
 * @author Dennis Stagliano
 * @version 11/11/2015 Dennis Stagliano - original
 * @version 9/30/2015 SecondContributorNameHere -Brief summary of changes here
 * @version 10/2/2015 ThirdContributorNameHere -Brief summary of changes here
 * 
 *This is a helper class
 */

public class Lilo_ModifyTicketAlreadyOnMediaTab {
	//
	// ModifyTicketAlreadyOnMediaTab
	//
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	
	//
	// ModifyTicketAlreadyOnMediaTab
	//
	@FindBy(id = "quickBookHeaderId")
	private Element eleqbpage;

	@FindBy(id = "travelSummeryFrm:tpsId")
	private Button btneditLink;

	@FindBy(id = "viewGuestForm:proToChkInButtonId")
	private Button btnfirstProceed;
	
	@FindBy(id = "roomTabFrm:proToChkInButtonId")
	private Button btnProceedToCheckIn;

	@FindBy(id = "applyPymntForm:nextId")
	private Element eleapplyPaymentFormProceed;

	@FindBy(id = "balanceDueCheckinForm:okButtonId1")
	private Element eleBalanceDueOk;

	@FindBy(id = "pinSetUpConfirmForm:closePinSetupPopupId")
	private Button btnpinSetUpNo;

	@FindBy(id = "mediaPanel_lbl")
	private Label lblMediaTab;

	@FindBy(id = "newMediaEncodeForm:kttwDetails:0:rowCheckBoxId")
	private Checkbox chkPrimary;

	@FindBy(id = "newMediaEncodeForm:ticketsButton")
	private Button btnTicketButton;

	// *********************
	// ** Build page area **
	// *********************

	public Lilo_ModifyTicketAlreadyOnMediaTab(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public Lilo_ModifyTicketAlreadyOnMediaTab initialize() {
		return ElementFactory.initElements(driver, Lilo_ModifyTicketAlreadyOnMediaTab.class);
	}

	public boolean loadPage() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, eleqbpage);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, eleqbpage);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	// *********************************************************
	// *** Modify Tickets Already on Media Tab Interactions ***
	// *********************************************************

	public void guestInofrmation(String Accomodation, String PartyMix) {
		// create a new quick book page and enter all fields
		// When finished click the quick book button from
		// qbp(QuickBookPageClass)
		// data from virtual table see name in QuickBookPage class
		QuickBookPage qbp = new QuickBookPage(driver);
		qbp.enterQuickBookInfo(Accomodation, PartyMix);
		Sleeper.sleep(1000);
		qbp.clickQuickbookButton();
	}

	public void clickEditLink() {
		initialize();
		pageLoaded(btneditLink);
		btneditLink.syncVisible(driver);
		btneditLink.jsClick(driver);
	}

	// Proceed button first occurrence
	public void proceedButton() {
		pageLoaded(btnfirstProceed);
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		if(btnfirstProceed.syncVisible(driver, 3, false)){
			btnfirstProceed.jsClick(driver);
		}else if(btnProceedToCheckIn.syncVisible(driver, 3, false)){
			btnProceedToCheckIn.jsClick(driver);
		}
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
		new ProcessingYourRequest().WaitForProcessRequest(driver, 60);
	}

	// Apply payment proceed
	public void applyPaymentFormProceed() {
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		WebElement btnapplyPaymentFormProceed = wait.until(ExpectedConditions.visibilityOf(eleapplyPaymentFormProceed));
//		initialize();
		pageLoaded(eleapplyPaymentFormProceed);
		eleapplyPaymentFormProceed.syncVisible(driver);
		eleapplyPaymentFormProceed.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver, 60);
	}

	// Enters the Media Tab screen
	public void mediaTabClick() {
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		WebElement lblMediaTabClick = wait.until(ExpectedConditions.visibilityOf(lblMediaTab));
		initialize();
		pageLoaded(lblMediaTab);
		lblMediaTab.syncVisible(driver);
		lblMediaTab.jsClick(driver);
	}

	// Checks the primary ticket purchaser checkbox
	public void checkprimary() {
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		WebElement chkboxPrimarySelect = wait.until(ExpectedConditions.visibilityOf(chkPrimary));
		initialize();
		pageLoaded(chkPrimary);
		chkPrimary.syncVisible(driver);
		chkPrimary.jsClick(driver);
	}

	// Clicks the Tickets button to continue with reservation
	public void clickTicketsButton() {
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		WebElement btnTickets = wait.until(ExpectedConditions.visibilityOf(btnTicketButton));
		initialize();
		pageLoaded(btnTicketButton);
		btnTicketButton.syncVisible(driver);
		btnTicketButton.jsClick(driver);
	}

	// clicks yes on balance due dialog
	public void balanceDue() {
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		WebElement btnBalanceDueOk = wait.until(ExpectedConditions.visibilityOf(eleBalanceDueOk));
//		initialize();
		pageLoaded(eleBalanceDueOk);
		eleBalanceDueOk.syncVisible(driver);
		eleBalanceDueOk.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver, 60);
	}

	// Declines setting a pin if pin dialog appears
	public void pinSetUpNo() {
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		WebElement pinNo = wait.until(ExpectedConditions.visibilityOf(btnpinSetUpNo));
//		initialize();
		pageLoaded(btnpinSetUpNo);
		btnpinSetUpNo.syncVisible(driver);
		btnpinSetUpNo.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver, 60);
	}

}

