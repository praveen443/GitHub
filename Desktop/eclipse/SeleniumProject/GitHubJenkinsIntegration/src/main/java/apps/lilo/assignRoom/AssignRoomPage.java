package apps.lilo.assignRoom;

import java.util.List;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Label;
import core.interfaces.Link;
import core.interfaces.Webtable;
import core.interfaces.impl.CheckboxImpl;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.TestReporter;

/**
 * @summary Contains the page methods & objects for the Assign Room page
 * @version Created 9/11/2014
 * @author Waightstill W. Avery
 */
public class AssignRoomPage {

	// ************************************
	// *** Main Assign Room Page Fields ***
	// ************************************
	public Checkbox chkRoomStatusCheckbox;
	private String strRoomNumber;
	private Datatable datatable = new Datatable();

	// *********************************
	// *** Main Assign Room Page Elements ***
	// ******************************
	// Choose room button
	@FindBy(id = "buttonsPanelForm:chooseRoomButtonId")
	private Button btnChoose;

	// Alternate rooms tab
	@FindBy(id = "alternateRoomsTabPanel_lbl")
	private Label lblAlternateRooms;

	// Alternate rooms table
	@FindBy(id = "availableRoomTabForm:availableRoomReservationList")
	private Webtable tblAlternateRooms;

	// Clear Search Criteria link
	@FindBy(id = "searchFilterLinkForm:clearSearchFilterId")
	private Link lnkClearSearchCriteria;

	// Search Criteria Link
	@FindBy(id = "searchFilterLinkForm:searchFilterId")
	private Link lnkSearchCriteria;

	// Room Status table
	@FindBy(id = "searchRoomForm:roomStatusListId")
	private Webtable tblRoomStatus;

	// Room Status table
	@FindBy(id = "matchingRoomTabForm:mainRoomReservationList")
	private Webtable tblMatchingRoom;

	// Show Rooms button
	@FindBy(id = "searchRoomForm:showRoomButtonId")
	private Button btnShowRooms;

	// Assign Room window Close buttonf
	@FindBy(id = "buttonsPanelForm:closeButtonId")
	private Button btnAssignRoomClose;

	// Room Number link
	@FindBy(id = "applyPymntForm:accommodationList:0:subTable:0:roomNumberId")
	private Link lnkRoomNumber;

	// First Available Matching Room
	@FindBy(id = "matchingRoomTabForm:mainRoomReservationList:0:mainRoomNumberId")
	private Element eleFirstAvailMatchingRoom;

	// Room Status List webtable
	@FindBy(id = "searchRoomForm:roomStatusListId")
	private Webtable tblRoomStatusList;

	/**
	 * Page objects for Link Room availability
	 * 
	 */

	// click on link search criteria : Room availability page
	@FindBy(id = "roomTableForm:roomSearchFilterId")
	private Link lnkSearchcriteria;

	// go select check box clean occupied
	@FindBy(id = "roomSearchPopupForm:roomStatusListId:0:roomStatusAttribChkBoxId")
	private Link chkCleanOccupied;

	// go select check box Inspected ,vacant
	@FindBy(id = "roomSearchPopupForm:roomStatusListId:15:roomStatusAttribChkBoxId")
	private Link chkInspectedVacant;

	// *********************
	// ** Build page area **
	// *********************
	private WebDriver driver;

	/**
	 * 
	 * @summary Constructor to initialize the page
	 * @version Created 9/11/2014
	 * @author Waightstill W Avery
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public AssignRoomPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * 
	 * @summary Method to grab all of the elements on the page
	 * @version Created 9/11/2014
	 * @author Waightstill W Avery
	 * @param driver
	 * @throws NA
	 * @return All elements of the current page
	 * 
	 */
	private AssignRoomPage initialize(WebDriver driver) {
		return ElementFactory.initElements(driver, AssignRoomPage.class);
	}

	/**
	 * 
	 * @summary Method that ensure the page is loaded and the "Choose" button is
	 *          visible
	 * @version Created 9/11/2014
	 * @author Waightstill W Avery
	 * @param NA
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void assignRoomPageLoaded() {
		// while (!btnChoose.syncVisible(driver)){
		while (!btnChoose.elementWired()) {
			initialize(driver);
		}
		btnChoose.syncVisible(driver);

	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				element);
	}

	// **************************************
	// *** Main Assign Rooms Interactions ***
	// **************************************

	/**
	 * 
	 * @summary Method that searches for vacant rooms and assigns on the
	 *          reservation if one is available
	 * @version Created 9/11/2014
	 * @author Waightstill W Avery
	 * @param NA
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void assignVacantRoom() {
		if (!btnChoose.getAttribute("class").contains("disabled-true")) {
			if (btnChoose.getAttribute("text").contains("Choose")) {
				clickClearSearchCriteria();
				clickSearchCriteria();
				assignRoomPageLoaded();
				selectAllVacantOptions();
				clickShowRooms();
				selectRoom();
				assignRoomPageLoaded();
				captureRoomNumber();
			} else {
				clickChooseRoomButton();
				captureRoomNumber();
			}
		} else {
			selectFristAvailableMatchingRoom();
			assignRoomPageLoaded();
			clickChooseRoomButton();
		}
	}

	private void clickClearSearchCriteria() {
		lnkClearSearchCriteria.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	private void clickSearchCriteria() {
		pageLoaded(lnkSearchCriteria);
		lnkSearchCriteria.syncVisible(driver);
		lnkSearchCriteria.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * 
	 * @summary Method to check every option in the search criteria that has
	 *          "VACANT" in the description
	 * @version Created 9/11/2014
	 * @author Waightstill W Avery
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	private void selectAllVacantOptions() {
		int tableRowCount = tblRoomStatus.getRowCount();
		int rowLoopCounter;
		String strCheckboxId = "";
		String strRowRoomStatus = "";
		for (rowLoopCounter = 0; rowLoopCounter < tableRowCount; rowLoopCounter++) {
			strCheckboxId = "input[id^=\"searchRoomForm:roomStatusListId:"
					+ String.valueOf(rowLoopCounter) + "\"]";
			// Grab the next checkbox
			chkRoomStatusCheckbox = new CheckboxImpl(driver.findElement(By
					.cssSelector(strCheckboxId)));
			// Grab the value associated with the next checkbox
			strRowRoomStatus = tblRoomStatus.getCellData(driver,
					rowLoopCounter + 1, 1);

			// If the next checkbox correlates to a vacant room status, check
			// the box
			if (strRowRoomStatus.indexOf("VACANT") > 0
					|| strRowRoomStatus.indexOf("vacant") > 0) {
				chkRoomStatusCheckbox.jsToggle(driver);
			}
		}
	}
	
	public void selectRoomTypes() {

		int tableRowCount = tblRoomStatus.getRowCount();
		int rowLoopCounter;
		String strCheckboxId = "";
		String strRowRoomStatus = "";

		String[] RoomTypes = new String[] { "CLEAN, VACANT",
				"INSPECTED, VACANT" };
		for (rowLoopCounter = 0; rowLoopCounter < tableRowCount; rowLoopCounter++) {
			strCheckboxId = "input[id^=\"searchRoomForm:roomStatusListId:"
					+ String.valueOf(rowLoopCounter) + "\"]";
			// Grab the next check box
			chkRoomStatusCheckbox = new CheckboxImpl(driver.findElement(By
					.cssSelector(strCheckboxId)));
			// Grab the value associated with the next check box
			strRowRoomStatus = tblRoomStatus.getCellData(driver,
					rowLoopCounter + 1, 1);
			System.out.println(strRowRoomStatus);

			for (int i = 0; i < RoomTypes.length; i++) {
				if (RoomTypes[i].equalsIgnoreCase(strRowRoomStatus)) {
					chkRoomStatusCheckbox.jsToggle(driver);
				}
			}

		}
	}

	private void clickShowRooms() {
		initialize(driver);
		assignRoomPageLoaded();
		/*
		 * if(WebDriverSetup.browser.toString().equalsIgnoreCase("firefox")){
		 * JavascriptExecutor jse = (JavascriptExecutor) driver;
		 * jse.executeScript
		 * ("document.getElementById(\"searchRoomForm:showRoomButtonId\").click();"
		 * ); }else{
		 */
		btnShowRooms.syncVisible(driver);
		btnShowRooms.jsClick(driver);
		// }
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * 
	 * @summary Method to select a Matching or Alternate room if one is
	 *          available
	 * @version Created 9/11/2014
	 * @author Waightstill W Avery
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	private void selectRoom() {
		if (btnChoose.getAttribute("text").equalsIgnoreCase("Choose")) {
			clickAlternateRoomsTab();
			assignRoomPageLoaded();
			if (btnChoose.getAttribute("text").equalsIgnoreCase("Choose")) {
				Reporter.log("No matching rooms or alternate rooms exists with which to assign to the reservation.");
				closeAssignRoomWindow();
			} else {
				clickChooseRoomButton();
			}
		} else {
			clickChooseRoomButton();
		}
	}

	/**
	 * 
	 * @summary Method to check room status
	 * @version Created 08/06/2015
	 * @author Brajesh Ahirwar
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public String chooseVacantRoomToAssign() {

		tblMatchingRoom.syncVisible(driver, 1, false);
		int tableRowCount = tblMatchingRoom.getRowCount();
		int rowLoopCounter;
		String txtRoomNumber = "";
		for (rowLoopCounter = 0; rowLoopCounter < tableRowCount - 1; rowLoopCounter++) {
			// Grab the text of Element
			Element txtMainRoomStatus = new ElementImpl(driver.findElement(By
					.id("matchingRoomTabForm:mainRoomReservationList:"
							+ rowLoopCounter + ":mainRoomStatusId")));
			Element txtConRoomStatus = new ElementImpl(driver.findElement(By
					.id("matchingRoomTabForm:mainRoomReservationList:"
							+ rowLoopCounter + ":connectRoomStatusId")));
			Element txtMainRoomNumber = new ElementImpl(driver.findElement(By
					.id("matchingRoomTabForm:mainRoomReservationList:"
							+ rowLoopCounter + ":mainRoomNumberId")));

			// Grab the value associated with the next checkbox
			String txtMainId = txtMainRoomStatus.getText().trim();
			String txtConnectId = txtConRoomStatus.getText().trim();
			if ((txtMainId.equalsIgnoreCase("Ready"))
					&& (txtConnectId.equalsIgnoreCase("VACANT, CLEAN"))) {

				txtMainRoomStatus.jsClick(driver);
				txtRoomNumber = txtMainRoomNumber.getText().trim();
				new ProcessingYourRequest().WaitForProcessRequest(driver);
				clickChooseRoomButton();
				return txtRoomNumber;
			}
		}
		return txtRoomNumber;

	}

	private void clickAlternateRoomsTab() {
		initialize(driver);
		lblAlternateRooms.syncVisible(driver);
		lblAlternateRooms.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	private void closeAssignRoomWindow() {
		btnAssignRoomClose.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	private void clickChooseRoomButton() {
		btnChoose.syncEnabled(driver, 1, false);
		btnChoose.highlight(driver);
		btnChoose.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	private void captureRoomNumber() {
		strRoomNumber = lnkRoomNumber.getAttribute("text");
		Reporter.log(
				"The room assigned to the reservation is " + strRoomNumber,
				true);
	}

	public String getRoomNumber() {
		return strRoomNumber;
	}

	private void selectFristAvailableMatchingRoom() {
		eleFirstAvailMatchingRoom.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * This method iterates through all checkboxes in the Room Status list table
	 * and checks the boxes that are indicated by the String array argument
	 * 
	 * @param values
	 *            - array of strings corresponding to the checkbox values as the
	 *            appear in the application
	 * @author Marella Satish - 12/15/2015 - original
	 */
	private void selectRoomSearchStatusByValue(String[] values) {
		pageLoaded(tblRoomStatusList);

		for (int value = 0; value < values.length; value++) {
			Checkbox checkbox = new CheckboxImpl(
					tblRoomStatusList.findElement(By
							.xpath("//tbody/tr/td/span[contains(text(),'"
									+ values[value] + "')]/../input")));
			checkbox.scrollIntoView(driver);
			checkbox.check();
		}
	}

	/**
	 * This method uses a to verified assigned room number not ready
	 * 
	 * @param N
	 *            /A
	 * @author Marella Satish - 12/16/2015 - original
	 */

	public void verify_RoomNumber() {
		initialize(driver);
		pageLoaded(btnChoose);
		btnChoose.highlight(driver);
		String ActualRoomNO = btnChoose.getText().trim();
		btnChoose.jsClick(driver);
		// Commenting out in favor of a more dynamic approach
		// Sleeper.sleep(4000);
		new ProcessingYourRequest().WaitForProcessRequest(driver, 60);
		pageLoaded(lnkRoomNumber);
		lnkRoomNumber.highlight(driver);
		String ExpectedRoomNO = lnkRoomNumber.getText();
		TestReporter.log("Actual Room :" + ActualRoomNO + " >> "
				+ "Expected Room :" + ExpectedRoomNO);
		TestReporter.assertTrue(ActualRoomNO.contains(ExpectedRoomNO),
				"Check Out is not Completed!!");
	}

	/**
	 * This method uses a user-defined search criteria to determine if any rooms
	 * are available that meet the search criteria. If no rooms are found, the
	 * test will fail.
	 * 
	 * @param status
	 *            - array of strings corresponding to the checkbox values as the
	 *            appear in the application
	 * @author Marella Satish - 12/15/2015 - original
	 */
	// public void searchForAvailableRoomsByRoomStatus(String[] status){
	public String searchForAvailableRoomsByRoomStatus(String scenario,
			String RoomNumber) {
		String roomNumber = "";

		datatable.setVirtualtablePage("ViewRoomAvailability");
		datatable.setVirtualtableScenario(scenario);

		String searchCriteria = datatable
				.getDataParameter("RoomSearchCriteria");
		@SuppressWarnings("unused")
		String returnRoomNumber = datatable
				.getDataParameter("SelectRoomNumber");
		String[] searchCriteriaArray = searchCriteria.split(";");
		clickSearchCriteria();
		selectRoomSearchStatusByValue(searchCriteriaArray);
		clickShowRooms();
		clickAlternateRoomsTab();
		// grab room number
		List<WebElement> getTRs = tblAlternateRooms.findElements(By
				.tagName("tr"));
		for (WebElement input : getTRs) {
			Element eInput = new ElementImpl(input);
			if (eInput.getText().contains(RoomNumber)) {
				roomNumber = eInput.getText();
				eInput.jsClick(driver);
				break;
			}
		}
		verify_RoomNumber();
		return roomNumber;
	}

	public void clickSEarchCriteria() {
		lnkSearchcriteria.click();
	}

}

