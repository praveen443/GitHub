package apps.lilo.manageTicketsPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Element;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

/**
 * @summary Contains the page methods & objects found in the ManageTicketsPage
 * @version Created 11/10/2015
 * @author Lalitha Banda
 */
public class Ticketing_VerifyAddGuestPage {

	// **********************************
	// *** Manage Tickets Page Fields ***
	// ***********************************
	private Datatable datatable = new Datatable();

	// *********************************
	// *** Manage Tickets Page Elements ***
	// *********************************

	// menu Item : RecommendTickets
	@FindBy(id = "menuForm:recommendTicketsLink")
	private Element lnkRecommendTickets;

	// menu Item : WillCall
	@FindBy(id = "menuForm:willCallTicketsButton")
	private Element lnkWillcall;

	// go button find tickets : will call form
	@FindBy(id = "willCallTicketForm:willCallTicketsButton")
	private Element btnfindTickets;

	// go button find tickets : will call form
	@FindBy(xpath = ".//*[@id='errorForm']/table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td/table/tbody/tr/td[2]/div/ul/li")
	private Element txtPopupmsg;

	// go add guest button
	@FindBy(id = "manageTicketsForm:addguestBtn")
	private Element btnaddGuest;

	// go link search
	@FindBy(xpath = "//span[contains(@id,'manageTicketsForm:j_id')]")
	private Element lnkSearch;

	// go link day Guest
	@FindBy(id = "manageTicketsForm:addGuestButton:anchor")
	private Element lnkDayGuest;

	// go button close
	@FindBy(id = "closeManageTicketsForm:exitQuickTabButtonId1")
	private Element btnClose;

	// go button OK
	@FindBy(id = "confirmationPopupForm:okButtonId")
	private Element btnOK;

	// go button ok : error popup
	@FindBy(id = "errorForm:okButtonId")
	private Element btnOK_errorpopup;

	// go Comp button
	@FindBy(id = "manageTicketsForm:compBtn")
	private Element btnComp;

	// go Recommend button
	@FindBy(id = "manageTicketsForm:recommendTicketsButton")
	private Element btnREcommend;

	// go Existing Tickets button
	@FindBy(id = "manageTicketsForm:existingticketsBtn")
	private Element btnExistingTicket;

	// *********************
	// ** Build page area **
	// *********************
	private WebDriver driver;

	/**
	 * 
	 * @summary Constructor to initialize the page
	 * @version Created 11/10/2015
	 * @author Lalitha Banda
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public Ticketing_VerifyAddGuestPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public Ticketing_VerifyAddGuestPage initialize() {
		return ElementFactory.initElements(driver,
				Ticketing_VerifyAddGuestPage.class);
	}

	public boolean pageLoaded(WebDriver driver) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				lnkRecommendTickets);
	}

	public boolean pageLoaded(WebDriver driver, Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				element);
	}

	// ****************************************
	// *** Manage Tickets Page Interactions ***
	// ****************************************
	/**
	 * 
	 * @summary Method to validate AddGuest Button Menu Items
	 * @version Created 11/10/2015
	 * @author Lalitha Banda
	 * @param
	 * @throws NA
	 * @return NA
	 */
	public void verifyaddGuestButtons() {

		// Click on add guest button
		pageLoaded(driver, btnaddGuest);
		btnaddGuest.jsClick(driver);

		// Verify Day Guest Button
		boolean getDayGuestEnableStatus = verifyHistoryEnabled(lnkDayGuest);
		TestReporter.assertEquals(getDayGuestEnableStatus, true,
				"The DayGuest element not enabled!!");
		TestReporter.logStep("Enable Status of DayGuest : "
				+ getDayGuestEnableStatus);

		// Verify Day Guest Button
		boolean getSearchEnableStatus = verifyHistoryEnabled(lnkSearch);
		TestReporter.assertEquals(getDayGuestEnableStatus, true,
				"The Search element not enabled!!");
		TestReporter.logStep("Enable Status of Search : "
				+ getSearchEnableStatus);

		// Click on button close
		pageLoaded(driver, btnClose);
		btnClose.jsClick(driver);

		// Click OK
		btnOK.click();
	}

	public void verifyButtons(String scenario) {
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
		datatable
				.setVirtualtablePage("Lilo_Ticketing_VerifyAddGuestButton_Button");
		datatable.setVirtualtableScenario(scenario);

		String buttonname = datatable.getDataParameter("Button_Name");
		switch (buttonname.trim()) {
		case "Add Guest":
			// Validate add guest button
			validateButtonsEnabledOrDisabled(btnaddGuest);
			break;
		case "COMP":
			// Validate comp button
			validateButtonsEnabledOrDisabled(btnComp);
			break;
		case "Recommend":
			// Validate Recommend button
			validateButtonsEnabledOrDisabled(btnREcommend);
			break;
		case "Existing Tickets_Disabled":
			// Validate Existing tickets button
			validateButtonsEnabledOrDisabled(btnExistingTicket);
		default:
			break;
		}
	}

	// Click on Menu item : RecommendTickets
	public void ClickRecommendTickets() {
		pageLoaded(driver, lnkRecommendTickets);
		lnkRecommendTickets.click();
	}

	// Click on Menu item : WillCall
	public void ClickWillCall() {
		pageLoaded(driver, lnkWillcall);
		lnkWillcall.syncVisible(driver);
		lnkWillcall.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	// This method verifies that input element is enabled or not
	public boolean verifyHistoryEnabled(Element inputLocator) {
		boolean isEnabled = false;
		initialize();
		pageLoaded(driver, inputLocator);
		Sleeper.sleep(3000);
		if (inputLocator.isEnabled()) {
			isEnabled = true;
		}

		return isEnabled;
	}

	public void verifyErrorMessage() {

		// Click on Find Tickets link
		pageLoaded(driver, btnfindTickets);
		btnfindTickets.click();

		// Validating Error Message
		pageLoaded(driver, txtPopupmsg);
		String errorMsg = txtPopupmsg.getText();
		TestReporter.assertEquals(errorMsg,
				"Please enter either Confirmation No or Guest Name.",
				"Expected and actual error messages are equal");
		TestReporter.logStep(errorMsg);

		// Click Ok on Pop up
		pageLoaded(driver, btnOK_errorpopup);
		btnOK_errorpopup.click();
	}

	private boolean validateButtonsEnabledOrDisabled(Element locatorName) {
		boolean isEnabled = true;
		pageLoaded(driver, locatorName);
		// Verifying Button status
		if (locatorName.getAttribute("class").contains("disabled-true")) {
			isEnabled = false;
		}
		if (isEnabled == true) {
			// Validating enabled button
			locatorName.highlight(driver);
			TestReporter.assertTrue(isEnabled,
					locatorName.getElementIdentifier() + " button is enabled");
		} else {
			// Validating Disabled button
			locatorName.highlight(driver);
			TestReporter.assertFalse(isEnabled,
					locatorName.getElementIdentifier() + " button is disabled");
		}
		// Click on cancel and ok buttons
		clickCloseAndOk();
		return isEnabled;
	}

	public void clickCloseAndOk() {
		// Click on button close
		pageLoaded(driver, btnClose);
		btnClose.jsClick(driver);

		// Click OK
		btnOK.click();
	}
}
