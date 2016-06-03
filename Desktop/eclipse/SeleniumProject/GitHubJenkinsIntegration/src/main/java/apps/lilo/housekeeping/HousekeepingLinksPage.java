package apps.lilo.housekeeping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Link;
import core.interfaces.Listbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;

/**
 * 
 * @summary Contains the page methods & objects for the Housekeeping links page
 * 			Note - have to use a lot of xpath to identify the objects as the IDs 
 * 			are different between the aulani resort and the rest of the resorts
 * @version Created 9/11/2014
 * @author Jessica Marshall
 */
public class HousekeepingLinksPage {
	//
	// Page Fields
	//
	private Datatable datatable = new Datatable();
	private WebDriver driver;

	// *************************************
	// *** HousekeepingLinksPage Elements ***
	// *************************************

	// Resort drop down box
	@FindBy(id = "menuForm:HKselectedResortId")
	private Listbox lstResorts;

	// Configure global board colors
	@FindBy(id = "menuForm:j_id43")
	private Link lnkConfigureBoardColors;

	// Define board parameters
	@FindBy(id = "menuForm:j_id49")
	private Link lnkDefineBoardParameters;

	// Create allottments
	@FindBy(id = "menuForm:j_id51")
	private Link lnkCreateAllotments;

	// Create breaks
	@FindBy(id = "menuForm:j_id53")
	private Link lnkCreateBreaks;

	// Cleaning sequence
	@FindBy(id = "menuForm:j_id55")
	private Link lnkCleaningSequence;

	// View Global Board colors
	@FindBy(id = "menuForm:j_id57")
	private Link lnkViewBoardColors;

	// Housekeeping sections
	@FindBy(linkText = "Housekeeping Sections")
	private Link lnkHouseKeepingSections;

	// Housekeeper Assignments
	@FindBy(linkText = "Housekeeper Assignments")
	private Link lnkHousekeeperAssignments;

	// Room Schedule
	@FindBy(linkText = "Room Schedule")
	private Link lnkRoomSchedule;

	// Housekeeper Daily Schedules
	@FindBy(linkText = "Housekeeper Daily Schedules")
	private Link lnkHouseDailySchedules;

	// Track Housekeeper Productivity
	@FindBy(id = "menuForm:j_id68")
	private Link lnkTrackHouseProductivity;

	// Late Checkouts
	@FindBy(id = "menuForm:managelatecheckout2")
	private Link lnkLateCheckouts;

	// Create AM Housekeeper boards
	@FindBy(xpath = "//*[@id='menuForm:linksSection']/table/tbody/tr[2]/td/table/tbody/tr/td[2]/div/table/tbody/tr[2]/td[1]/div/a[1]")
	private Link lnkCreateBoard;

	// Modify AM Housekeeper boards
	@FindBy(xpath = "//*[@id='menuForm:linksSection']/table/tbody/tr[2]/td/table/tbody/tr/td[2]/div/table/tbody/tr[2]/td[1]/div/a[2]")
	private Link lnkModifyBoard;

	// View/Print AM Housekeepr boards
	@FindBy(xpath = "//*[@id='menuForm:linksSection']/table/tbody/tr[2]/td/table/tbody/tr/td[2]/div/table/tbody/tr[2]/td[1]/div/a[3]")
	private Link lnkViewBoard;

	// Create inspection
	@FindBy(id = "menuForm:boardsLinksEven:2:j_id79")
	private Link lnkCreateInspection;

	// Modify inspection
	@FindBy(id = "menuForm:boardsLinksEven:2:j_id82")
	private Link lnkModifyInspection;

	// View/Print inspection
	@FindBy(id = "menuForm:boardsLinksEven:2:j_id85")
	private Link lnkViewInspection;

	// Tracking Sheet
	@FindBy(linkText = "Tracking Sheet")
	private Link lnkTrackingSheet;

	// Create Turndown
	@FindBy(id = "menuForm:boardsLinksOdd:1:j_id93")
	private Link lnkCreateTurndown;

	// Modify Turndown
	@FindBy(id = "menuForm:boardsLinksOdd:1:j_id96")
	private Link lnkModifyTurndown;

	// View/Print Turndown
	@FindBy(id = "menuForm:boardsLinksOdd:1:j_id99")
	private Link lnkViewTurndown;
	
	// Error pop up
	@FindBy(id = "PMSRErrorModalPanelCDiv")
	private Element eleErrorPopUp;
	
	// Error pop up ok button
	@FindBy(id = "errorForm:okButtonId")
	private Button btnErrorOk;
	
	// Message pop up - displays if the boards have already been created for the day
	@FindBy(id = "messageModalPanelCDiv")
	private Element eleMessagePopUp;
	
	// Message pop up yes button
	@FindBy(id = "messageForBoards:save")
	private Button btnMsgPopUpYes;
	
	// Message pop up no button
	@FindBy(id = "messageForBoards:close")
	private Button btnMsgPopUpNo;

	// *********************
	// ** Build page area **
	// *********************

	public HousekeepingLinksPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public HousekeepingLinksPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	public boolean loadPage() {
		Sleeper.sleep(1000);
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, lnkDefineBoardParameters); 	
	}

	// *****************************************
	// ***Housekeeping Links Page Interactions ***
	// *****************************************
	
	public void selectSearchType(String resort){
		lstResorts.select(resort);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		//lstResorts.syncEnabled(driver);
	}
	
	public void clickConfigureBoardColors(){
		lnkConfigureBoardColors.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickDefineBoardParameters(){
		lnkDefineBoardParameters.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickCreateAllotments(){
		lnkCreateAllotments.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickCreateBreaks(){
		lnkCreateBreaks.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickCleaningSequence(){
		lnkCleaningSequence.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickViewBoardColors(){
		lnkViewBoardColors.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickHouseKeepingSections(){
		lnkHouseKeepingSections.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickHousekeeperAssignments(){
		lnkHousekeeperAssignments.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickRoomSchedule(){
		lnkRoomSchedule.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickHouseDailySchedules(){
		lnkHouseDailySchedules.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickTrackHouseProductivity(){
		//Sleeper.sleep(5000);
		lnkTrackHouseProductivity.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickLateCheckouts(){
		lnkLateCheckouts.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickCreateBoard(){
		lnkCreateBoard.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickModifyBoard(){
		lnkModifyBoard.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickViewBoard(){
		lnkViewBoard.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickCreateInspection(){
		lnkCreateInspection.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickModifyInspection(){
		lnkModifyInspection.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickViewInspection(){
		lnkViewInspection.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickTrackingSheet(){
		lnkTrackingSheet.jsClick(driver);
		//lnkTrackingSheet.focusClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickCreateTurndown(){
		lnkCreateTurndown.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickModifyTurndown(){
		lnkModifyTurndown.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickViewTurndown(){
		lnkViewTurndown.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public boolean isErrorPopUpDisplayed(){
		return eleErrorPopUp.isDisplayed();
	}
	
	public String getErrorPopUpText(){
		return eleErrorPopUp.getText();
	}
	
	public void clickErrorPopUpOk(){
		btnErrorOk.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickMessagePopUpYes(){
		btnMsgPopUpYes.syncVisible(driver);
		btnMsgPopUpYes.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickMessagePopUpNo(){
		btnMsgPopUpNo.syncVisible(driver);
		btnMsgPopUpNo.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	public boolean isMessagePopUpDisplayed(){
		return eleMessagePopUp.isDisplayed();
	}
}

