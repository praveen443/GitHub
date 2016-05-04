package apps.lilo.housekeeping;

import java.util.List;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Label;
import core.interfaces.Link;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;

/**
 * 
 * @summary Contains the page methods & objects for the Manage AM housekeeping
 *          boards page
 * @version Created 9/11/2014
 * @author Jessica Marshall
 */

public class ManageAMHousekeeperBoardsPage {
	//
	// Page Fields
	//
	private Datatable datatable = new Datatable();
	private WebDriver driver;

	// *************************************
	// *** HousekeepingLinksPage Elements ***
	// *************************************

	// Max Value start text box
	@FindBy(id = "mainForm:boardMaxValueStart")
	private Textbox txtMaxValueStart;

	// Max Value end text box
	@FindBy(id = "mainForm:boardMaxValueEnd")
	private Textbox txtMaxValueEnd;

	// Maximize button
	@FindBy(id = "mainForm:maximizeButton")
	private Button btnMaximize;

	// Apply scheduled cleans
	// @FindBy(id = "mainForm:j_id213")
	@FindBy(xpath = "//span[@id='mainForm:buttonPanel']/table/tbody/tr/td/input[contains(@value,'Apply Scheduled Cleans')]")
	private Button btnApplySchCleans;

	// Revert scheduled cleans
	@FindBy(id = "mainForm:revert")
	private Button btnRevertSchCleans;

	// Save
	@FindBy(id = "mainForm:saveborrd")
	private Button btnSave;

	// Complete
	@FindBy(id = "mainForm:completeboard")
	private Button btnComplete;

	// Print
	@FindBy(id = "mainForm:j_id219")
	private Button btnPrint;

	// Close
	@FindBy(id = "mainForm:close")
	private Button btnClose;

	// Lock
	@FindBy(id = "mainForm:lockbutton")
	private Button btnLock;

	// UnLock
	@FindBy(id = "mainForm:unlockbutton")
	private Button btnUnlock;

	// Create Board
	@FindBy(id = "mainForm:createboardbutton")
	private Button btnCreateBoard;

	// Move rooms
	@FindBy(id = "mainForm:roomTextBox")
	private Textbox txtMoveRoom;

	// To destination board
	@FindBy(id = "mainForm:selectedBoard")
	private Listbox lstDestination;

	// Search by criteria
	@FindBy(id = "mainForm:selectCriteria")
	private Listbox lstSearchBy;

	// Search filter text box
	@FindBy(id = "mainForm:filterValueText")
	private Textbox txtSearchFilter;

	// Move
	@FindBy(id = "mainForm:moveButton")
	private Button btnMove;

	// Clear
	@FindBy(id = "mainForm:clear")
	private Button btnClear;

	// Unlocked boards table
	@FindBy(id = "mainForm:unlockedBoardSummaryTable")
	private Webtable tblUnlockedBoards;

	// Unlocked boards table
	@FindBy(xpath = "//tbody[@id='mainForm:unlockedBoardSummaryTable:tb']/tr")
	private List<WebElement> tblUnlockedBoardRows;

	// locked boards table
	@FindBy(id = "mainForm:lockedBoardSummaryTable")
	private Webtable tblLockedBoards;

	// Working boards table
	@FindBy(id = "mainForm:workingBoardTable")
	private Webtable tblWorkingBoards;

	// Workbench table
	@FindBy(id = "mainForm:roomTable")
	private Webtable tblWorkBench;

	// Tracking sheet
	@FindBy(id = "mainForm:keySheetLink")
	private Link lnkTrackingSheet;

	// Validation pop up that prompts user to contiune
	// when rooms exist on the work bench
	@FindBy(id = "beforeSaveModalPanelCDiv")
	private Element eleContinuePopUp;

	// Yes continue when rooms exist on the work bench
	@FindBy(id = "validationPopUp:save")
	private Button btnContinuePopUpYes;

	// No continue when rooms exist on the owrk bench
	@FindBy(id = "validationPopUp:close")
	private Button btnContinuePopUpNo;

	// Unassign link
	@FindBy(id = "mainForm:unassignHskLink")
	private Link lnkUnassign;

	// 'Create Board' Page elements
	// Board Name input field.
	@FindBy(name = "createBoard:j_id298")
	private Textbox txtCreateBoardBoardName;

	// Board Name "OK" button
	@FindBy(id = "createBoard:create")
	private Button btnCreateBoardOK;

	// Board Name "Cancel" button
	@FindBy(id = "createBoard:close")
	private Button btnCreateBoardCancel;

	// Validation move popup panel
	@FindBy(id = "validationModalPanelForMoveCDiv")
	private Element eleValidationMovePopUp;

	// Validation move panel pop up yes button
	@FindBy(id = "ValidationPopupMove:yes")
	private Button btnValidMovePopUpYes;

	// Validation move panel pop up no button
	@FindBy(id = "ValidationPopupMove:No")
	private Button btnValidMovePopUpNo;

	// Validation panel pop up
	@FindBy(id = "validationModalPanelCDiv")
	private Element eleValidationPopUp;

	// Validation panel pop up yes button
	@FindBy(id = "valid:yes")
	private Button btnValidPopUpYes;

	// Validation panel pop up no button
	@FindBy(id = "valid:no")
	private Button btnValidPopUpNo;

	// Saved successfully pop up
	@FindBy(id = "saveSuccessfulModalPanelCDiv")
	private Element eleSavedPopUp;

	// Saved pop up close button
	// @FindBy(id = "j_id428:j_id434")
	@FindBy(xpath = "//table[@class='panelBackgroundModalPanel']/tbody/tr/td/input[contains(@onclick,'saveSuccessfulModalPanel')]")
	private Button btnSavedPopUpClose;

	// Change housekeeper link
	@FindBy(id = "mainForm:changeHouskeeperLink")
	private Link lnkChangeHousekeeper;

	// the currently selected open boards details (housekeeper name, board name,
	// etc)
	@FindBy(id = "mainForm:roomNumbers")
	private Element eleOpenBoardDetails;

	// table header for unlocked boards that contains details such as number of
	// boards
	@FindBy(id = "mainForm:unlockedBoardSummaryHeaderTable")
	private Element eleUnlockedBoardTableHeader;

	// table header for locked boards that contains details such as number of
	// boards
	@FindBy(id = "mainForm:lockedBoardSummaryHeaderTable")
	private Element eleLockedBoardTableHeader;

	// Label for board information
	@FindBy(id = "mainForm:roomNumbers_body")
	private Label lblBoardInfo;

	// table header for workbench
	@FindBy(id = "mainForm:roomHeaderTable")
	private Element eleWorkbenchTableHeader;

	// *********************
	// ** Build page area **
	// *********************

	/**
	 * @summary Constructor to initialize the page
	 * @version Created 9/11/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public ManageAMHousekeeperBoardsPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Constructor to initialize the page
	 * @version Created 9/11/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public ManageAMHousekeeperBoardsPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Constructor to initialize the page
	 * @version Created 9/11/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @return
	 * @throws NA
	 * @return NA
	 */
	public boolean loadPage() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnSave);
	}

	// *****************************************
	// ***Housekeeping Links Page Interactions ***
	// *****************************************

	public void clickSaveBoards() {
		btnSave.syncVisible(driver);
		btnSave.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		// wait for page to reload after the please wait goes away
		Sleeper.sleep(2000);
		btnSave.syncVisible(driver);
	}

	public void clickCompleteBoards() {
		btnComplete.syncVisible(driver);
		btnComplete.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		// wait for page to reload after the please wait goes away
		btnComplete.syncVisible(driver);
	}

	/**
	 * @summary Clicks complete boards and handles all the additional pop ups
	 *          that display
	 * @version Created 9/11/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @return
	 * @throws NA
	 * @return NA
	 */
	public boolean completeBoards() {

		clickCompleteBoards();
		Sleeper.sleep(1000);
		if (doesRoomsExistPopUpDisplayed()) {
			clickContinueYesButton();
			new ProcessingYourRequest().WaitForProcessRequest(driver);
		}

		Sleeper.sleep(1000);
		initialize();

		// Wait for the saved successfully pop up to display
		// eleSavedPopUp.syncVisible(driver);

		// Click close
		clickSavedPopUpClose();

		return true;
	}

	public boolean eleSavedPopUpIsDisplayed() {
		return eleSavedPopUp.isDisplayed();
	}

	public void clickSavedPopUpClose() {
		btnSavedPopUpClose.syncVisible(driver);
		btnSavedPopUpClose.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public boolean doesRoomsExistPopUpDisplayed() {
		return eleContinuePopUp.isDisplayed();
	}

	public void clickContinueYesButton() {
		btnContinuePopUpYes.syncVisible(driver);
		btnContinuePopUpYes.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickContinueNoButton() {
		btnContinuePopUpNo.syncVisible(driver);
		btnContinuePopUpNo.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void createNewBoard(String scenario) {
		/*
		 * datatable.setDatatableSheet("CreateAdditionalBoardInfo");
		 * datatable.setDatatableRow(datatable.getRowContains(scenario, 0));
		 */
		datatable.setVirtualtablePage("CreateAdditionalBoardInfo");
		datatable.setVirtualtableScenario(scenario);

		// Click the create board button.
		btnCreateBoard.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		// Wait for processing to finish and verify the creation dialogue box is
		// ready on the screen.
		txtCreateBoardBoardName.syncVisible(driver);

		// Enter new board name.
		txtCreateBoardBoardName.set(datatable.getDataParameter("BoardName"));

		// Click "OK" to create the board.
		btnCreateBoardOK.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);

	}

	public void unassignHouseKeeper() {
		// With Housekeeper Boards dispayed, select a board to be updated from
		// the Unlocked Boards Summary section.
		// If there are no unlocked boards, you will have select a locked board
		// and unlock it before continuing.
		if (tblUnlockedBoards.getRowCount() == 0) {
			// Unlock a locked board.
			tblLockedBoards.clickCell(driver, 1, 1);
			btnUnlock.click();
		}

		// Click the first unlocked board.
		tblUnlockedBoards.clickCell(driver, 1, 1);

		// Click the "Unassign" link from the displayed board.
		// btnUnassign.click();

		// Click the "Save" button.
		btnSave.click();

	}

	public void clickCloseButton() {
		btnClose.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		clickValidationPopUpNo();
	}

	public void clickLockButton() {
		btnLock.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickUnlockButton() {
		btnUnlock.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public int getNumOfUnlockedBoards() {
		String[] splitArray = eleUnlockedBoardTableHeader.getText()
				.split("\\(");
		String[] splitArrya2 = splitArray[1].split("\\)");
		return Integer.parseInt(splitArrya2[0]);
	}

	public int getNumOfLockedBoards() {
		String[] splitArray = eleLockedBoardTableHeader.getText().split("\\(");
		String[] splitArrya2 = splitArray[1].split("\\)");
		return Integer.parseInt(splitArrya2[0]);
	}

	/**
	 * @summary Selects an element from the working boards and drags onto the
	 *          work bench based on the row number passed in
	 * @version Created 9/30/2014
	 * @author Jessica Marshall
	 * @param Row
	 *            number (starts at 1)
	 * @throws NA
	 * @return NA
	 */
	public void moveRoomFromOpenBoardToWorkBench_DragAndDrop(int rowNum) {
		Actions builder = new Actions(driver);
		// Have to use the depreciated pause as otherwise it doesn't wait long
		// enough
		// for the element to get a green check mark before releasing and won't
		// display in the table
		@SuppressWarnings("deprecation")
		Action dragAndDrop = builder
				.clickAndHold(tblWorkingBoards.getCell(driver, rowNum, 2))
				.moveToElement(tblWorkBench.getCell(driver, 1, 1)).pause(2000)
				.release(tblWorkBench.getCell(driver, 1, 1)).build();
		dragAndDrop.perform();
		// builder.dragAndDrop(tblWorkingBoards.getCell(driver, rowNum, 2),
		// tblWorkBench.getCell(driver, 1, 2)).perform();
		// builder.dragAndDrop(tblWorkingBoards.getCell(driver, rowNum, 2),
		// builder.dragAndDropBy(tblWorkingBoards.getCell(driver, rowNum, 2),
		// tblWorkBench.getCell(driver, 1, 1).getLocation().getX(),
		// tblWorkBench.getCell(driver, 1, 1).getLocation().getY()).perform();
		// new Mouse(driver).dragAndDrop(tblWorkingBoards.getCell(driver,
		// rowNum, 2),
		// eleWorkbenchTableHeader.findElement(By.xpath("//table[@id='mainForm:roomHeaderTable']/thead/tr/th[1]")));

		Sleeper.sleep(500);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		Sleeper.sleep(500);
	}

	/**
	 * @summary Selects an element from the work bench and drags onto the
	 *          working boards table based on the row number passed in
	 * @version Created 9/30/2014
	 * @author Jessica Marshall
	 * @param Row
	 *            number (starts at 1)
	 * @throws NA
	 * @return NA
	 */
	public void moveRoomFromWorkBenchToOpenBoard_DragAndDrop(int rowNum) {
		Actions builder = new Actions(driver);

		// Have to use the depreciated pause as otherwise it doesn't wait long
		// enough
		// for the element to get a green check mark before releasing and won't
		// display in the table
		@SuppressWarnings("deprecation")
		Action dragAndDrop = builder
				.clickAndHold(tblWorkBench.getCell(driver, rowNum, 1))
				.moveToElement(tblWorkingBoards.getCell(driver, 1, 1), 5, -32)
				.pause(3000).release().build();
		dragAndDrop.perform();

		// builder.dragAndDrop(tblWorkBench.getCell(driver, rowNum, 1),
		// tblWorkingBoards.getCell(driver, 1, 2)).perform();
		// start coordinates
		// new Mouse(driver).dragAndDrop(tblWorkBench.getCell(driver, rowNum,
		// 1), tblWorkingBoards.getCell(driver, 2, 2));

		Sleeper.sleep(500);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		Sleeper.sleep(500);
	}

	/**
	 * @summary Returns the room number from the working boards table based on
	 *          the table row number passed in
	 * @version Created 9/30/2014
	 * @author Jessica Marshall
	 * @param rowNum
	 *            (starts at 1)
	 * @throws NA
	 * @return room number (cell data)
	 */
	public String getRoomNumberfromWorkingBoards(int rowNum) {
		tblWorkingBoards.syncVisible(driver);
		return tblWorkingBoards.getCellData(driver, rowNum, 2);
	}

	/**
	 * @summary Returns the room number from the work bench table based on the
	 *          table row number passed in
	 * @version Created 9/30/2014
	 * @author Jessica Marshall
	 * @param row
	 *            number (starts at 1)
	 * @throws NA
	 * @return room number (cell data)
	 */
	public String getRoomNumberFromWorkBench(int rowNum) {
		tblWorkBench.syncVisible(driver);
		return tblWorkBench.getCellData(driver, rowNum, 1);
	}

	/**
	 * @summary Returns the row number for the string that you pass in, if the
	 *          string is not found then returns a zero
	 * @version Created 9/30/2014
	 * @author Jessica Marshall
	 * @param String
	 *            value
	 * @throws NA
	 * @return Row number if string is found, otherwise returns 0
	 */
	public int getRowNumberForWorkingBoardsTableWithRoomNum(String roomNum) {
		int rowLocation = 0;
		tblWorkingBoards.syncVisible(driver);
		List<WebElement> tableRows = tblWorkingBoards.findElements(By
				.tagName("tr"));
		for (int rowNum = 0; rowNum < tableRows.size(); rowNum++) {
			List<WebElement> row = tableRows.get(rowNum).findElements(
					By.tagName("td"));
			if (row.get(1).getText().equals(roomNum)) {
				// System.out.println(rowNum+ 1);
				rowLocation = rowNum + 1;
			}

		}
		return rowLocation;
		/*
		 * int row = tblWorkingBoards.getRowWithCellText(driver, roomNum, 1);
		 * System.out.println(row); return row;
		 */
	}

	/**
	 * @summary Returns the row number for the string that you pass in, if the
	 *          string is not found then returns a zero
	 * @version Created 9/30/2014
	 * @author Jessica Marshall
	 * @param String
	 *            value
	 * @throws NA
	 * @return Row number if string is found, otherwise returns 0
	 */
	public int getRowNumberForWorkBenchTableWithRoomNum(String roomNum) {
		/*
		 * tblWorkBench.syncVisible(driver); int row =
		 * tblWorkBench.getRowWithCellText(driver, roomNum, 1);
		 * System.out.println(row); return row;
		 */
		int rowLocation = 0;
		tblWorkBench.syncVisible(driver);
		List<WebElement> tableRows = tblWorkBench
				.findElements(By.tagName("tr"));
		for (int rowNum = 0; rowNum < tableRows.size(); rowNum++) {
			List<WebElement> row = tableRows.get(rowNum).findElements(
					By.tagName("td"));
			if (row.get(0).getText().contains(roomNum)) {
				// System.out.println(rowNum+ 1);
				rowLocation = rowNum + 1;
			}

		}
		return rowLocation;
	}

	/**
	 * @summary Moves a room from one board to another, instead of doing a drag
	 *          and drop, enters the room number in a text field, selects the
	 *          destination board, and clicks move. For multiple rooms, they
	 *          need to be separated by a comma with no spaces i.e.
	 *          1101,1102,1115
	 * @version Created 9/30/2014
	 * @author Jessica Marshall
	 * @param Room
	 *            number(s) & the destination board (i.e. Workbench)
	 * @throws NA
	 * @return NA
	 */
	public void moveRoomFromBoardToBoard(String roomNum, String destination) {
		txtMoveRoom.safeSet(roomNum);
		lstDestination.select(destination);
		btnMove.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		Sleeper.sleep(1000);
	}

	public String getBoardNameFromUnlockedBoardsTable(int rowNum) {
		tblUnlockedBoards.syncVisible(driver);
		// return tblUnlockedBoards.getCellData(driver, rowNum, 1);
		List<WebElement> columns = tblUnlockedBoardRows.get(rowNum - 1)
				.findElements(By.tagName("td"));
		return columns.get(1).getText();
	}

	/**
	 * @summary Returns the row number for the string that you pass in, if the
	 *          string is not found then returns a zero
	 * @version Created 9/30/2014
	 * @author Jessica Marshall
	 * @param String
	 *            value
	 * @throws NA
	 * @return Row number if string is found, otherwise returns 0
	 */
	public int getRowNumForUnlockedBoardsTableWithBoardName(String boardName) {
		tblUnlockedBoards.syncVisible(driver);
		System.out.println(tblUnlockedBoards.getRowWithCellText(driver,
				boardName, 2));
		return tblUnlockedBoards.getRowWithCellText(driver, boardName, 2);

	}

	public void clickABoardFromTheUnlockedBoardsTable(String boardName) {
		// get the row number that contains the board name
		// rowNum = tblUnlockedBoards.getRowWithCellText(driver, boardName, 1);
		// tblUnlockedBoards.clickCell(driver, rowNum, 1);
		for (int rowNum = 0; rowNum < tblUnlockedBoardRows.size(); rowNum++) {
			List<WebElement> columns = tblUnlockedBoardRows.get(rowNum)
					.findElements(By.tagName("td"));
			if (columns.get(1).getText().equals(boardName)) {
				columns.get(1).click();
				break;
			}
		}
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickABoardFromTheUnlockedBoardsByRowNum(int rowNum) {
		tblUnlockedBoards.clickCell(driver, rowNum, 1);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickABoardFromTheLockedBoardsByRowNum(int rowNum) {
		tblLockedBoards.clickCell(driver, rowNum, 1);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * @summary Returns boolean whether the validation popup is displayed
	 * @version Created 10/1/2014
	 * @author Jessica Marshall
	 * @param NA
	 * @throws NA
	 * @return True if displayed, False if not displayed
	 */
	public boolean validationPopUpIsDisplayed() {
		Sleeper.sleep(500);
		return eleValidationPopUp.isDisplayed();
	}

	public void clickValidationPopUpYes() {
		btnValidPopUpYes.syncVisible(driver);
		btnValidPopUpYes.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickValidationPopUpNo() {
		btnValidPopUpNo.syncVisible(driver);
		btnValidPopUpNo.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * @summary Returns boolean whether the validation move popup is displayed,
	 *          happens sometimes when you move rooms from open board to
	 *          unlocked board and the unlocked board housekeeper is maxed out
	 * @version Created 10/1/2014
	 * @author Jessica Marshall
	 * @param NA
	 * @throws NA
	 * @return True if displayed, False if not displayed
	 */
	public boolean validationMovePopUpIsDisplayed() {
		return eleValidationMovePopUp.isDisplayed();
	}

	public void clickValidationMovePopUpYes() {
		btnValidMovePopUpYes.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickValidationMovePopUpNo() {
		btnValidMovePopUpNo.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickChangeHousekeeperLink() {
		lnkChangeHousekeeper.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * @summary Returns in a string value all the details of the currently
	 *          selected open board details such as housekeeper name, board
	 *          name, max value, etc
	 * @version Created 10/2/2014
	 * @author Jessica Marshall
	 * @param NA
	 * @throws NA
	 * @return string of details
	 */
	public String getSelectedOpenBoardDetails() {
		return eleOpenBoardDetails.getText();
	}

	public void clickUnassignLink() {
		lnkUnassign.syncVisible(driver);
		lnkUnassign.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickPrintButton() {
		btnPrint.syncVisible(driver);
		btnPrint.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public static void completeAMHouseKeeperBoards() {
		// Call CreateAMHouseKeeperBoards

		// Click the "Complete" button.

		// Click the "Close" button to close the message.

		// Verify the boards in the Locked Board Summary remain in the Locked
		// Board Summary.

		// Verify the boards in the Unlocked Board Summary remain in the
		// Unlocked Board Summary.
	}

	public static void printAMHouseKeeperBoards() {
		// Call CompleteAMHouseKeeperBoards

		// Click the "Print" button.

		// Select any option you would like to print or select "All" to print
		// all items on the boards.

		// Enter any addiitional instructions or message into the Additional
		// Instructions field. (The message will be included on all boards.)
		// This step is optional. Boards can be printed without entering any
		// Additional Instructions.

		// Click the "Print" button.

		// Click the "Print" button within the print preview window.

		// Select a printer and click the "Print" button from within the Select
		// Printer window.

		// Click the "Close" button from within the print preview window.

		// Verify all information displays correctly on the printed boards.
	}

	public static void assignOrUpdateHouseKeeperToBoard() {
		// With Housekeeper Boards dispayed, select a board to be updated from
		// the Unlocked Boards Summary section.
		// If there are no unlocked boards, you will have to create a new board
		// or select a locked board and unlock it before continuing.

		// Click the "Change" link from the displayed board.

		// Select a Housekeeper from the list, then click the "Assign" button.

		// Click the "Save" button.
	}

	/**
	 * @summary Validates the sum of all merits in the working boards match the
	 *          total displayed at the top of the screen
	 * @version Created 10/15/2014
	 * @author Justin Phlegar
	 * @param String
	 *            value
	 * @throws NA
	 * @return Row number if string is found, otherwise returns 0
	 */
	public boolean validateBoardMeritAccumulation() {
		double cummulativeMerits = 0.0;
		double reportedMerits = 0.0;
		tblWorkingBoards.syncVisible(driver);
		List<WebElement> tableRows = tblWorkingBoards.findElements(By
				.tagName("tr"));
		for (int rowNum = 0; rowNum < tableRows.size(); rowNum++) {
			List<WebElement> row = tableRows.get(rowNum).findElements(
					By.tagName("td"));
			cummulativeMerits += Double.parseDouble(row.get(4).getText());
		}

		List<WebElement> lblItems = lblBoardInfo.findElements(By
				.tagName("span"));
		reportedMerits = Double.parseDouble(lblItems.get(lblItems.size() - 2)
				.getText());

		// Debugging text
		// System.out.println(cummulativeMerits);
		// System.out.println("Label merits: " + reportedMerits);

		return cummulativeMerits == reportedMerits;

	}
}
