package apps.lilo.housekeeping;

import java.util.List;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Label;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.ButtonImpl;
import core.interfaces.impl.TextboxImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;

/**
 * 
 * @summary Contains the page methods & objects for the Define board parameters
 *          page page
 * @version Created 10/10/2014
 * @author Jessica Marshall
 */
public class DefineBoardParametersPage {
	//
	// Page Fields
	//
	private WebDriver driver;
	private String instruction = "";
	private Datatable datatable = new Datatable();

	// *************************************
	// *** HousekeepingLinksPage Elements ***
	// *************************************

	// Board parameters tab
	@FindBy(id = "manageBoardParameter:boardParameterTabPanel_lbl")
	private Element eleBoardParametersTab;

	// Housekeeper daily instructions
	@FindBy(id = "manageBoardParameter:instructionTabPanel_lbl")
	private Element eleDailyInstructionsTab;

	// Current HK Board Type
	@FindBy(id = "headerForm:boardTypeName")
	private Label lblBoardType;

	// Maxiumum deep cleans
	@FindBy(id = "manageBoardParameter:maximumDeepCleans")
	private Textbox txtMaxDeepCleans;

	// Board max start value
	@FindBy(id = "manageBoardParameter:boardMaximumStartValue")
	private Textbox txtMaxStartValue;

	// Board max end value
	@FindBy(id = "manageBoardParameter:boardMaximumEndValue")
	private Textbox txtMaxEndValue;

	// Board max checkout start value
	@FindBy(id = "manageBoardParameter:maximumCheckoutsStart")
	private Textbox txtMaxCheckoutStartValue;

	// Board max checkout end value
	@FindBy(id = "manageBoardParameter:maximumCheckoutsEnd")
	private Textbox txtMaxCheckoutEndValue;

	// Linen change interval
	@FindBy(id = "manageBoardParameter:linenChangeInterval")
	private Textbox txtLinenChange;

	// Merit Minute multiplier
	// @FindBy(id = "manageBoardParameter:meritMinuteMultiplier1")
	// @FindBy(id = "manageBoardParameter:meritMinuteMultiplier")
	@FindBy(xpath = "//input[contains(@id, 'manageBoardParameter:meritMinuteMultiplier')]")
	private Textbox txtMeritMinuteMult;

	// Maximum breaks
	@FindBy(id = "manageBoardParameter:maximumBreaks")
	private Textbox txtMaxBreaks;

	// Maximum room value range
	@FindBy(id = "manageBoardParameter:roomRangeValue")
	private Textbox txtMaxRoomRange;

	// Maximize percent merits
	@FindBy(id = "manageBoardParameter:percentMaximumMerits")
	private Textbox txtPercentMaxMerits;

	// Primary threshold percentage
	@FindBy(id = "manageBoardParameter:primaryThresholdPercent")
	private Textbox txtThresholdPercent;

	// Save board parameters
	@FindBy(id = "manageBoardParameter:save")
	private Button btnSaveBoard;

	// Close board parameters
	@FindBy(id = "manageBoardParameter:close")
	private Button btnCloseBoard;

	// Web table of Daily Instructions
	@FindBy(id = "manageBoardParameter:instructionList:tb")
	private Webtable tblDailyInstructions;

	// Add Daily Instruction
	@FindBy(id = "manageBoardParameter:add")
	private Button btnAddInstruction;

	// Delete Daily Instruction
	@FindBy(id = "manageBoardParameter:delete")
	private Button btnDeleteInstruction;

	// Select board type arrow button
	@FindBy(id = "headerForm:selectBoardType")
	private Button btnSelectBoardType;

	// Table for Board Type options
	@FindBy(id = "boardTypeListForm")
	private Webtable tblBoardTypes;

	// Select Button for Section Board Type popup
	@FindBy(css = "input[\"value=Select\"]")
	private Button btnBoardTypeSelect;

	// CloseButton for Section Board Type popup
	@FindBy(css = "input[\"value=Close\"]")
	private Button btnBoardTypeClose;

	// Recurrence Popup Start Date
	@FindBy(id = "selectRecurrenceForm:startDateInputDate")
	private Textbox txtRecurrenceStartDate;

	// Recurrence Popup Daily option
	@FindBy(id = "daily")
	private Button btnRecurrenceDaily;

	// Recurrence Popup Weekly option
	@FindBy(id = "weekly")
	private Button btnRecurrenceWeekly;

	// Recurrence Popup Monthly option
	@FindBy(id = "month")
	private Button btnRecurrenceMonthly;

	// Recurrence Popup Yearly option
	@FindBy(id = "yearly")
	private Button btnRecurrenceYearly;

	// Recurrence Popup table
	@FindBy(id = "selectRecurrenceForm:recurrenceSelectPanel")
	private Webtable tblRecurrencePattern;

	// Recurrence Popup Ok button
	@FindBy(id = "selectRecurrenceForm:ok")
	private Webtable btnRecurrenceOk;

	// Saved successful Popup Ok button
	@FindBy(id = "j_id315:j_id321")
	private Button btnPopupSaveOK;

	// Saved successful Popup
	@FindBy(id = "saveSuccessfulModalPanelCDiv")
	private Element eleSavedPopUp;

	// Recurrence Popup table
	@FindBy(id = "j_id201:j_id207")
	private Webtable btnRecurrenceSuccessClose;

	// *********************
	// ** Build page area **
	// *********************

	/**
	 * @summary Constructor to initialize the page
	 * @version Created 10/10/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public DefineBoardParametersPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Constructor to initialize the page
	 * @version Created 10/10/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public DefineBoardParametersPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Constructor to initialize the page
	 * @version Created 10/10/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public boolean loadPage() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnSaveBoard);
	}

	// *****************************************
	// ***Page Interactions ***
	// *****************************************

	public void clickSave() {
		btnSaveBoard.syncVisible(driver);
		btnSaveBoard.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);

	}

	public void clickSavedPopUpClose() {
		btnPopupSaveOK.syncVisible(driver);
		Sleeper.sleep(1000);
		btnPopupSaveOK.click();
		// ((JavascriptExecutor)driver).executeScript("document.getElementById('j_id315:j_id321').click");
		new ProcessingYourRequest().WaitForProcessRequest(driver);

	}

	public void clickClose() {
		btnCloseBoard.syncVisible(driver);
		btnCloseBoard.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);

	}

	public Double getMeritMinuteMultiplierValue() {
		return Double.parseDouble(txtMeritMinuteMult.getAttribute("value")
				.trim());
	}

	public String getLinenChangeValue() {
		txtLinenChange.syncVisible(driver);
		return txtLinenChange.getAttribute("value").trim();
	}

	public void setLinenChangeValue(String value) {
		txtLinenChange.syncVisible(driver);
		txtLinenChange.set(value);
	}

	public void openBoardParametersTab() {
		eleBoardParametersTab.click();
		loadPage();
	}

	public void openDailyInstructionsTab() {
		eleDailyInstructionsTab.click();
		loadPage();
	}

	public void addDailyInstruction(String scenario) {
		/*
		 * datatable.setDatatableSheet("HouseKeeperDailyInstruction");
		 * datatable.setDatatableRow(datatable.getRowContains(scenario, 0));
		 */
		datatable.setVirtualtablePage("HouseKeeperDailyInstruction");
		datatable.setVirtualtableScenario(scenario);

		BoardsHeader header = new BoardsHeader(driver);
		header.selectBoardType(datatable.getDataParameter("BoardType"));
		openDailyInstructionsTab();

		Sleeper.sleep(500);

		btnAddInstruction.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		List<WebElement> dailyInstructionRows = tblDailyInstructions
				.findElements(By.className("dr-table-firstrow"));
		WebElement row = dailyInstructionRows
				.get(dailyInstructionRows.size() - 1);

		setInstructionName(datatable.getDataParameter("Instruction"));
		fillInstuction(row, getInstructionName());

		clickSave();
		clickSavedPopUpClose();
	}

	public void updateDailyInstruction(String instructionToUpdate,
			String scenario) {
		boolean rowFound = false;
		datatable.setVirtualtablePage("HouseKeeperDailyInstruction");
		datatable.setVirtualtableScenario(scenario);

		BoardsHeader header = new BoardsHeader(driver);
		header.selectBoardType(datatable.getDataParameter("BoardType"));
		openDailyInstructionsTab();

		Sleeper.sleep(500);
		setInstructionName(datatable.getDataParameter("Instruction"));
		List<WebElement> dailyInstructionRows = tblDailyInstructions
				.findElements(By.className("dr-table-firstrow"));
		for (WebElement row : dailyInstructionRows) {

			if (row.findElement(By.tagName("input")).getAttribute("value")
					.equalsIgnoreCase(instructionToUpdate)) {
				fillInstuction(row, getInstructionName());
				rowFound = true;
				break;
			}
		}

		if (!rowFound)
			throw new RuntimeException("Instruction with value [ "
					+ instruction + " ] was not found.");

		clickSave();
		clickSavedPopUpClose();
	}

	public void deleteDailyInstruction(String instructionToDelete,
			String scenario) {
		datatable.setVirtualtablePage("HouseKeeperDailyInstruction");
		datatable.setVirtualtableScenario(scenario);

		openDailyInstructionsTab();

		Sleeper.sleep(500);
		List<WebElement> dailyInstructionRows = tblDailyInstructions
				.findElements(By.className("dr-table-firstrow"));
		for (WebElement row : dailyInstructionRows) {
			if (row.findElement(By.tagName("input")).getAttribute("value")
					.equalsIgnoreCase(instructionToDelete)) {
				row.click();
				Sleeper.sleep(500);
				btnDeleteInstruction.click();
				new ProcessingYourRequest().WaitForProcessRequest(driver);
				break;
			}
		}

		clickSave();
		clickSavedPopUpClose();
	}

	private void fillInstuction(WebElement row, String instruction) {
		Textbox txtInstruction = new TextboxImpl(row.findElement(By
				.tagName("input")));
		txtInstruction.focus(driver);
		Sleeper.sleep(200);
		txtInstruction.set(instruction);

		// Element eleRecurrance = new ElementImpl(table.getCell(driver,
		// nthRow+1, 2));
		Button btnRecurrance = new ButtonImpl(row.findElement(By
				.className("button")));// By.xpath("//input[contains(@class,'button')]")));
		btnRecurrance.click();

		new ProcessingYourRequest().WaitForProcessRequest(driver);
		// tblRecurrencePattern.syncVisible(driver);
		// Webtable tblRecurrence = null;
		// switch (datatable.getDataParameter("RecurrenceType").toLowerCase()){
		// case "daily":

		// tblRecurrence = new WebtableImpl(tblRecurrencePattern.getCell(driver,
		// 5, 1));
		btnRecurrenceDaily.click();
		btnRecurrenceOk.click();

		new ProcessingYourRequest().WaitForProcessRequest(driver);
		btnRecurrenceSuccessClose.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	private void setInstructionName(String instructionName) {
		instruction = instructionName;
	}

	public String getInstructionName() {
		return instruction;
	}

	public boolean handleSavedPopUp() {

		eleSavedPopUp.syncVisible(driver);
		btnPopupSaveOK.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		return true;
	}
}
