package apps.lilo.manageArrivals;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.MediaSize.NA;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import selenium.common.CustomVerification;
import utils.Datatable;
import utils.Sleeper;
import utils.date.DateTimeConversion;

/**
 * @summary Contains the page methods & objects found in the Manage
 *          Arrivals-Inventory Summary Tab page
 * @version Created 09/15/2015
 * @author Brajesh Ahirwar
 */
public class InventorySummaryTab {

	// *******************************
	// *** Inventory Summary Page Fields ***
	// *******************************
	private Datatable datatable = new Datatable();

	// *********************************
	// *** Inventory Summary Page Elements ***
	// *********************************

	// Arrival Resort list box
	@FindBy(id = "adjustRoomCountAppMenu:selectedResortIdForInventory")
	private Listbox lstResortforinventory;

	// Find button
	@FindBy(id = "invSummaryTabForm:findButtonId")
	private Button btnFind;

	// Radio Button For Multiple day
	@FindBy(id = "invSummaryTabForm:dayViewId:0")
	private Element rdoMulti;

	// Radio Button For Single day
	@FindBy(id = "invSummaryTabForm:dayViewId:1")
	private Element rdoSingle;

	// Input field for enter date
	@FindBy(id = "invSummaryTabForm:searchDateInput")
	private Textbox txtDate;

	// Inventory Summary WebTable
	@FindBy(id = "invSummaryTabForm:parentTableId")
	private Webtable tblInvSummary;

	// Inventory Summary WebTable
	@FindBy(xpath = "//table[@id='invSummaryTabForm:parentTableId']/thead/tr/th[3]/a[2]")
	private Element eleDateMatching;

	// Adjust Room Count button
	@FindBy(id = "invSummaryTabForm:adjustRoomCountBtnId")
	private Button btnAdjustRoomCount;

	// Button Adjust ok on Pop up
	@FindBy(id = "errorForm:okButtonId")
	private Button btnAdjustOk;

	// Button Back Arrow
	@FindBy(id = "invSummaryTabForm:postDateBtnId")
	private Button btnForwardArrow;

	// Button Forward Arrow
	@FindBy(id = "invSummaryTabForm:preDateBtnId")
	private Button btnBackwardArrow;

	// Inventory Summary single Day WebTable
	@FindBy(id = "invSummaryTabForm:parentSingleDayTableId")
	private Webtable tblInvSummarySingTable;

	@FindBy(id = "adjustRoomCountsPageForm:adjustRoomCountTableId:0:roomTypeListId")
	private Listbox lstAdjRoomType;

	// Button Back Arrow
	@FindBy(id = "adjustRoomCountsPageForm:approveAdjustmentButtonId")
	private Button btnAdjustApproved;

	// Button Back Arrow
	@FindBy(id = "backButtonForm:backToInvSummaryButtonId")
	private Button btnBackToInventory;

	// Date Filter text box
	@FindBy(id = "adjustRoomCountsPageForm:searchDateInput")
	private Textbox txtDateFilter;

	// Adjust Room Count table
	@FindBy(id = "adjustRoomCountsPageForm:adjustRoomCountTableId")
	private Webtable tblAdjustRoomCountTable;

	// Adjust Result Popup Panel Content table
	@FindBy(id = "adjustmentResultPopupModalPanelContentTable")
	private Webtable tblAdjustResultPopupCountTable;

	// Adjust Result Popup Panel Content table
	@FindBy(id = "adaRoomTypeAdjustmentConfirmModalPanelContentTable")
	private Webtable tblAddRmTypeAdjustment;

	// Adjust
	@FindBy(css = "input[id^=\"adjustmentResultForm:approvedAdjustmentTableId:0:j_id\"]")
	private Webtable lblReservationNumber;

	// To get a Lilo Availability Single Day
	@FindBy(xpath = "//table[@id='invSummaryTabForm:parentSingleDayTableId']/tbody/tr[1]/td[15]")
	private Webtable txtLiloAvailabilitySD;

	// To get a Lilo Availability Multiple Day
	@FindBy(xpath = "//table[@id='invSummaryTabForm:parentTableId']/tbody/tr[1]/td[2]")
	private Webtable txtLiloAvailabilityMD;

	// To get a Lilo Availability Single Day
	@FindBy(id = "PMSRErrorModalPanelContentTable")
	private Webtable tblORTNotSelected;

	// *********************
	// ** Build page area **
	// *********************
	private WebDriver driver;
	CustomVerification custom = new CustomVerification();
	Map<String, String> status = new HashMap<String, String>();
	private String reservationNumber = "";

	/**
	 * 
	 * @summary Constructor to initialize the page
	 * @version Created 09/15/2015
	 * @author Brajesh Ahirwar
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public InventorySummaryTab(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public boolean pageLoaded(WebDriver driver) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnFind);
	}

	public boolean pageLoaded(WebDriver driver, Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnFind);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	public InventorySummaryTab initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	// *************************************
	// *** Inventory Summary Tab Page Interactions ***
	// *************************************

	/**
	 * 
	 * @summary Method to verify Lilo availability total count
	 * @version Created 09/18/2015
	 * @author Brajesh Ahirwar
	 * @param resort
	 * @throws NA
	 * @return NA
	 */

	public void verifySingleDayFutureDate(String resort) {
		pageLoaded(lstResortforinventory);
		initialize();
		selectResort(resort);
		pageLoaded(rdoSingle);
		initialize();
		enterdate(getDate("1"));
		pageLoaded();
		clickOnFindButton();
		waitForTblElementSD();
		matchCountSingleDay();
	}

	// Method to match total room count and font color for Single day
	public void matchCountSingleDay() {

		int room = 0;
		int total = 0;
		List<WebElement> rows = driver
				.findElements(By.xpath("//table[@id='invSummaryTabForm:parentSingleDayTableId']/tbody/tr[*]"));
		int sizeofRow = rows.size();
		for (int row = 1; row <= sizeofRow; row++) {
			Element element = new ElementImpl(driver.findElement(
					By.xpath("//table[@id='invSummaryTabForm:parentSingleDayTableId']/tbody/tr[" + row + "]/td[15]")));
			String txtroom = element.getText();
			room = Integer.parseInt(txtroom);
			total = total + room;
		}

		String expTotalRooms = String.valueOf(total).trim();
		String actTotalRooms = getTotal(14);
		matchingTotal(actTotalRooms, expTotalRooms);
	}

	/**
	 * 
	 * @summary Method to verify total count for future date and today date
	 * @version Created 09/18/2015
	 * @author Brajesh Ahirwar
	 * @param resort
	 * @throws NA
	 * @return NA
	 */

	public void verifySinDayFutureTodayTotal(String resort) {

		pageLoaded(lstResortforinventory);
		initialize();
		selectResort(resort);
		rdoSingle.syncVisible(driver, 30, false);
		pageLoaded(rdoSingle);
		initialize();
		enterdate(getDate("1"));
		pageLoaded();
		initialize();
		clickOnFindButton();
		waitForTblElementSD();
		String Totfuture = getTotal(2);
		clickonBackArrow();
		clickOnFindButton();
		waitForTblElementSD();
		pageLoaded();
		String TotToday = getTotal(2);
		matchingTotal(Totfuture, TotToday);

	}

	/**
	 * 
	 * @summary Method to verify total count for future date
	 * @version Created 09/18/2015
	 * @author Brajesh Ahirwar
	 * @param resort
	 * @throws NA
	 * @return NA
	 */

	public void verifySinDayFutureTotal(String resort) {

		pageLoaded(lstResortforinventory);
		initialize();
		selectResort(resort);
		pageLoaded(rdoSingle);
		initialize();
		pageLoaded(rdoSingle);
		initialize();
		clickonForwardArrow();
		clickOnFindButton();
		waitForTblElementSD();
		matchCountSingleDay();

	}

	public void matchingTotal(String act, String exp) {
		Assert.assertTrue(act.equalsIgnoreCase(exp), "Expected Room : [" + exp + "] , but Found : [" + act + "]");
	}

	public String getTotal(int col) {

		Element element = new ElementImpl(driver.findElement(
				By.xpath("//table[@id='invSummaryTabForm:parentSingleDayTableId']/tfoot/tr[1]/td[" + col + "]")));
		element.highlight(driver);
		String txtroom = element.getText().trim();
		return txtroom;
	}

	public void clickonBackArrow() {
		btnBackwardArrow.highlight(driver);
		btnBackwardArrow.jsClick(driver);
	}

	public void clickonForwardArrow() {
		btnForwardArrow.highlight(driver);
		btnForwardArrow.jsClick(driver);
	}

	public void verifyRoom(String resort, String date) {
		pageLoaded(lstResortforinventory);
		initialize();
		selectResort(resort);
		pageLoaded(rdoMulti);
		initialize();
		selectMultiDayRadio();
		pageLoaded(rdoMulti);
		initialize();
		enterdate(getDate(date));
		pageLoaded();
		clickOnFindButton();
		matchTotalRoomAvailable();
	}

	public void selectResort(String resort) {
		lstResortforinventory.syncVisible(driver);
		lstResortforinventory.select(resort);
		lstResortforinventory.sendKeys(Keys.TAB);
		Sleeper.sleep(2000);

	}

	public String getDate(String day) {
		String a = DateTimeConversion.ConvertToDate(day);
		return a;
	}

	public void selectMultiDayRadio() {
		Sleeper.sleep(2000);
		rdoMulti.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterdate(String date) {

		txtDate.syncVisible(driver, 40, false);
		txtDate.safeSet(date);
		Sleeper.sleep(1000);
	}

	public void clickOnFindButton() {
		Sleeper.sleep(2000);
		btnFind.syncEnabled(driver);
		btnFind.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		tblInvSummarySingTable.syncVisible(driver);
		pageLoaded(tblInvSummarySingTable);
		initialize();

	}

	/**
	 * 
	 * @summary Method to verify total count for first calendar day, Multiple
	 *          Day
	 * @version Created 09/18/2015
	 * @author Brajesh Ahirwar
	 * @param
	 * @throws NA
	 * @return NA
	 */

	public void matchTotalRoomAvailable() {
		int room = 0;
		int total = 0;
		List<WebElement> rows = driver
				.findElements(By.xpath("//table[@id='invSummaryTabForm:parentTableId']/tbody/tr[*]"));
		int sizeofRow = rows.size();
		for (int row = 1; row <= sizeofRow; row++) {

			Element availableRoom = new ElementImpl(driver.findElement(
					By.xpath("//table[@id='invSummaryTabForm:parentTableId']/tbody/tr[" + row + "]/td[3]")));
			String txtAvailroom = availableRoom.getText();
			room = Integer.parseInt(txtAvailroom);
			total = total + room;
		}
		String totalRooms = String.valueOf(total).trim();
		matchwithTotalRoom(totalRooms, 2);
	}

	public void validateSingNotOverSold() {
		List<WebElement> rows = driver
				.findElements(By.xpath("//table[@id='invSummaryTabForm:parentSingleDayTableId']/tbody/tr[*]"));
		int sizeofRow = rows.size();
		for (int row = 1; row <= sizeofRow; row++) {

			Element element = new ElementImpl(driver.findElement(
					By.xpath("//table[@id='invSummaryTabForm:parentSingleDayTableId']/tbody/tr[" + row + "]/td[15]")));
			String txtRoom = element.getText().trim();
			if (!txtRoom.startsWith("-")) {
				Element elemCheckBox = new ElementImpl(driver.findElement(By.xpath(
						"//table[@id='invSummaryTabForm:parentSingleDayTableId']/tbody/tr[" + row + "]/td[1]/input")));
				elemCheckBox.click();
				pageLoaded();
				initialize();
				clickOnAdjRmBtn();
				pageLoaded();
				initialize();
				Sleeper.sleep(1000);
				pageLoaded(tblORTNotSelected);
				Element elelement = new ElementImpl(driver.findElement(By.className("scrollableContentXY")));
				String errorMsg = elelement.getText().trim();
				Boolean result = errorMsg.contains("Oversold Room Types not selected for adjustments.");
				btnAdjustOk.jsClick(driver);
				Assert.assertTrue(result,
						"Expected Room : [Oversold Room Types not selected for adjustments] , but Found : [" + errorMsg
								+ "]");

				break;
			}
		}

	}

	public void clickOnAdjRmBtn() {
		btnAdjustRoomCount.highlight(driver);
		btnAdjustRoomCount.jsClick(driver);
	}

	public void matchOverSoldRoomMulti() {
		List<WebElement> rows = driver
				.findElements(By.xpath("//table[@id='invSummaryTabForm:parentTableId']/tbody/tr[*]"));
		int sizeofRow = rows.size();
		for (int row = 1; row <= sizeofRow; row++) {
			Element element = new ElementImpl(driver.findElement(
					By.xpath("//table[@id='invSummaryTabForm:parentTableId']/tbody/tr[" + row + "]/td[15]")));
			System.out.println("Total : " + element);
		}

	}

	public void matchwithTotalRoom(String expTotal, int col) {

		Element element = new ElementImpl(driver
				.findElement(By.xpath("//table[@id='invSummaryTabForm:parentTableId']/tfoot/tr[1]/td[" + col + "]")));
		String txtroom = element.getText().trim();
		Assert.assertTrue(txtroom.equalsIgnoreCase(expTotal),
				"Expected Room : [" + expTotal + "] , but Found : [" + txtroom + "]");

	}

	/**
	 * 
	 * @summary Method to verify Total room available for today date and future
	 *          date
	 * @version Created 09/24/2015
	 * @author Brajesh Ahirwar
	 * @param resort
	 * @throws NA
	 * @return NA
	 */

	public void verifyMulDayNaviBackArrow(String resort, String date) {

		pageLoaded(lstResortforinventory);
		initialize();
		selectResort(resort);
		pageLoaded(rdoMulti);
		initialize();
		selectMultiDayRadio();
		pageLoaded(rdoMulti);
		initialize();
		enterdate(getDate(date));
		pageLoaded();
		clickOnFindButton();
		matchTotalRoomAvailable();
		pageLoaded(rdoMulti);
		initialize();
		enterdate(getDate("0"));
		pageLoaded();
		initialize();
		clickOnFindButton();
		matchTotalRoomAvailable();

	}

	public void verifyMulDayNaviForArrow(String resort, String date) {

		pageLoaded(lstResortforinventory);
		initialize();
		selectResort(resort);
		pageLoaded(rdoMulti);
		initialize();
		selectMultiDayRadio();
		pageLoaded(rdoMulti);
		initialize();
		enterdate(getDate(date));
		pageLoaded();
		clickOnFindButton();
		matchTotalRoomAvailable();

	}

	/**
	 * 
	 * @summary Method to verify Over sold Room Types not selected for
	 *          adjustments.
	 * @version Created 09/24/2015
	 * @author Brajesh Ahirwar
	 * @param resort
	 * @throws NA
	 * @return NA
	 */

	public void verifySinDyOSTNS_for_Adju(String resort, String date) {

		pageLoaded(lstResortforinventory);
		initialize();
		selectResort(resort);
		pageLoaded(rdoSingle);
		initialize();
		enterdate(getDate(date));
		pageLoaded();
		clickOnFindButton();
		waitForTblElementSD();
		validateSingNotOverSold();

	}

	/**
	 * 
	 * @summary Method to verify Over sold Room Types not selected for
	 *          adjustments.
	 * @version Created 09/24/2015
	 * @author Brajesh Ahirwar
	 * @param resort
	 * @throws NA
	 * @return NA
	 * @throws JSONException,InvocationTargetException,IllegalAccessException,
	 *             NoSuchMethodException,ClassNotFoundException
	 */

	/*public void verifySinDyOSTS_for_Adju(JSONObject mainJson, String resort, String environment) throws JSONException,
			ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

		pageLoaded(lstResortforinventory);
		initialize();
		selectResort(resort);
		pageLoaded(rdoSingle);
		initialize();
		enterdate(getDate("0"));
		pageLoaded();
		clickOnFindButton();
		waitForTblElementSD();
		int rowNumberNeg = macthNegativeSign();
		if (rowNumberNeg > 0) {
			// Get a room title for over sold room
			String roomTitleNeg = getRoomTitle(rowNumberNeg).trim();
			// Get the Lilo Availability for over sold room
			String liloAvailabilityNeg = liloAvailability(rowNumberNeg).trim();
			// Quick Book for Over sold room
			String reservation = quickBook(mainJson, environment, roomTitleNeg);
			reservationToBackInventory(rowNumberNeg, reservation, roomTitleNeg, liloAvailabilityNeg);
		} else {
			String reservation = "";
			int rowNumber = macthNotOversoldSign();
			// Get a room title for over sold room
			String roomTitlePos = getRoomTitle(rowNumber).trim();
			// Get the Lilo Availability for not over sold room
			String liloAvailabilityNOS = liloAvailability(rowNumber).trim();
			int lanos = Integer.parseInt(liloAvailabilityNOS);
			for (int bookreserv = 0; bookreserv <= lanos; bookreserv++) {
				reservation = quickBook(mainJson, environment, roomTitlePos);
			}
			enterdate(getDate("0"));
			clickOnFindButton();
			waitForTblElementSD();
			pageLoaded();
			initialize();
			reservationToBackInventory(rowNumber, reservation, roomTitlePos, "0");

		}
	}*/

	public void reservationToBackInventory(int rowNumberNeg, String reservation, String roomTitleNeg,
			String liloAvailabilityNeg) {
		clickonCkeckBox(rowNumberNeg);
		waitForTabletoLoad();
		serachReservationToAdjust(reservation);
		pageLoaded(lstResortforinventory);
		initialize();
		enterdate(getDate("0"));
		clickOnFindButton();
		waitForTblElementSD();
		waitForIsTabletoLoad();
		matchResortLiloAvailability(roomTitleNeg, liloAvailabilityNeg, rowNumberNeg);

	}

	public void matchResortLiloAvailability(String roomTitleExp, String laExp, int rownumber) {
		String roomTitleAct = getRoomTitle(rownumber).trim();
		// Get the Lilo Availability for over sold room
		String laAct = liloAvailability(rownumber).trim();

		if (roomTitleAct.equalsIgnoreCase(roomTitleExp)) {
			boolean liloAvail = laAct.equalsIgnoreCase(laExp);
			Assert.assertTrue(liloAvail, "Expected Lilo Availability is :[" + laExp + "]  but found : [" + laAct + "]");
		} else {
			Assert.assertTrue(false, "Resort Title is not matching");
		}
	}

	public void clickonCkeckBox(int row) {
		Element element = new ElementImpl(driver.findElement(
				By.xpath("//table[@id='invSummaryTabForm:parentSingleDayTableId']/tbody/tr[" + row + "]/td[1]/input")));
		element.jsClick(driver);
		btnAdjustRoomCount.syncEnabled(driver);
		btnAdjustRoomCount.highlight(driver);
		btnAdjustRoomCount.jsClick(driver);
	}

	public String liloAvailability(int row) {
		Element element = new ElementImpl(driver.findElement(
				By.xpath("//table[@id='invSummaryTabForm:parentSingleDayTableId']/tbody/tr[" + row + "]/td[15]")));
		element.highlight(driver);
		String txtroomCapacity = element.getText();
		return txtroomCapacity;
	}

	public void waitForTabletoLoad() {
		int counter = 0;
		do {
			txtDateFilter.highlight(driver);
			counter++;
			pageLoaded(txtDateFilter);
			initialize();
		} while (txtDateFilter.getAttribute("value").equalsIgnoreCase("") && counter < 10);

		tblAdjustRoomCountTable.syncVisible(driver);

	}

	// Wait for Inventory summary table
	public void waitForIsTabletoLoad() {
		int counter = 0;
		do {
			txtDate.highlight(driver);
			counter++;
			pageLoaded(txtDate);
			initialize();
		} while (txtDate.getAttribute("value").equalsIgnoreCase("") && counter < 10);

		tblAdjustRoomCountTable.syncVisible(driver);
	}

	// Wait for Inventory summary table
	public void waitForTblElementSD() {

		int counter = 0;
		do {
			counter++;
			pageLoaded(txtDate);
			initialize();
		} while (txtLiloAvailabilitySD.getText().equalsIgnoreCase("") && counter < 10);
		tblAdjustRoomCountTable.syncVisible(driver);
	}

	public void waitForTblElementMD() {

		int counter = 0;
		do {

			counter++;
			pageLoaded(txtDate);
			initialize();
		} while (txtLiloAvailabilityMD.getText().equalsIgnoreCase("") && counter < 10);
		tblInvSummary.syncVisible(driver);
	}

	public int randomNumber() {
		new RandomStringUtils();
		int randNumber = Integer.parseInt(RandomStringUtils.randomNumeric(1));
		return randNumber;
	}

	// Method to search a reservation on Adjust Inventory Page
	public void serachReservationToAdjust(String reservation) {

		String xpathTable = "//table[@id='adjustRoomCountsPageForm:adjustRoomCountTableId']/tbody/tr[*]";
		List<WebElement> rows = driver.findElements(By.xpath(xpathTable));
		int sizeofRow = rows.size();
		for (int row = 1; row <= sizeofRow; row++) {

			String tblElement = "//table[@id='adjustRoomCountsPageForm:adjustRoomCountTableId']/tbody/tr[" + row
					+ "]/td[2]";
			Element element = new ElementImpl(driver.findElement(By.xpath(tblElement)));
			String actReservation = element.getText().trim();
			if (actReservation.equalsIgnoreCase(reservation)) {
				int idNumber = row - 1;
				Element eleCheckBox = new ElementImpl(driver.findElement(
						By.id("adjustRoomCountsPageForm:adjustRoomCountTableId:" + idNumber + ":checkBoxId")));
				eleCheckBox.jsClick(driver);
				Sleeper.sleep(500);
				String idList = "adjustRoomCountsPageForm:adjustRoomCountTableId:" + idNumber + ":roomTypeListId";
				int randomList = randomNumber();
				new Select(driver.findElement(By.id(idList))).selectByIndex(randomList);
				clickOnApprovedButton();
				try {
					List<WebElement> elementList = driver
							.findElements(By.cssSelector("input[id^=\"adaRoomTypeAdjustmentConfirmForm:j_id\"]"));
					Iterator<WebElement> iterator = elementList.iterator();
					while (iterator.hasNext()) {
						Element btnYes = new ElementImpl(iterator.next());
						btnYes.jsClick(driver);
						break;
					}

					clickonAdjustment();

				} catch (Exception e) {
					clickonAdjustment();
				}
				clickOnBackToInventory();

			}

		}

	}

	public void clickonAdjustment() {
		Element tblAdjustment = new ElementImpl(
				driver.findElement(By.id("adjustmentResultPopupModalPanelContentTable")));
		tblAdjustment.highlight(driver);
		pageLoaded(tblAdjustment);
		Element btnYes = new ElementImpl(driver.findElement(By.id("adjustmentResultForm:okBtnId")));
		btnYes.jsClick(driver);
		Sleeper.sleep(1000);
	}

	public String getRoomTitle(int row) {
		String tblElement = "//table[@id='invSummaryTabForm:parentSingleDayTableId']/tbody/tr[" + row + "]/td[2]";
		Element element = new ElementImpl(driver.findElement(By.xpath(tblElement)));
		element.highlight(driver);
		String eleTitle = element.getText();
		System.out.println(eleTitle);
		return eleTitle;

	}

	/*public String quickBook(JSONObject mainJson, String environment, String roomTitle)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, JSONException {
		JSONObject json_accommodation = mainJson.getJSONObject("accommodationInfo");
		String resort = json_accommodation.getString("resort");
		String roomTypeCode = getRoomTypeCode(resort, roomTitle);
		TestReporter.logScenario("Quick book using API's");
		StageResortReservations_Json stageReservation = new StageResortReservations_Json();
		TestReporter.logStep("Make Quickbook Reservation");
		status = stageReservation.bookResForInventory(mainJson, environment, roomTypeCode);
		reservationNumber = status.get("Reservation");
		return reservationNumber;

	}*/
/*
	public String getRoomTypeCode(String resort, String roomTitle) throws JSONException {
		String a = resort.concat(":").concat(roomTitle);
		String roomTypeCode = datatable.getDataParameter("ResortRoomtypeCode", a, "Room_Type_Code");
		return roomTypeCode;

	}*/

	public int macthNegativeSign() {

		List<WebElement> rows = driver
				.findElements(By.xpath("//table[@id='invSummaryTabForm:parentSingleDayTableId']/tbody/tr[*]"));
		int sizeofRow = rows.size();
		int rowNum = 0;
		for (int row = 1; row <= sizeofRow; row++) {
			Element element = new ElementImpl(driver.findElement(
					By.xpath("//table[@id='invSummaryTabForm:parentSingleDayTableId']/tbody/tr[" + row + "]/td[15]")));
			element.highlight(driver);
			String txtRoom = element.getText().trim();
			if (txtRoom.startsWith("-")) {
				return row;
			}
		}

		return rowNum;
	}

	public int macthNotOversoldSign() {
		List<WebElement> rows = driver
				.findElements(By.xpath("//table[@id='invSummaryTabForm:parentSingleDayTableId']/tbody/tr[*]"));
		int sizeofRow = rows.size();
		int rowNum = 0;

		for (int row1 = 0; row1 <= sizeofRow; row1++) {
			for (int row = 1; row <= sizeofRow; row++) {
				Element element = new ElementImpl(driver.findElement(By.xpath(
						"//table[@id='invSummaryTabForm:parentSingleDayTableId']/tbody/tr[" + row + "]/td[15]")));
				element.highlight(driver);
				String txtRoom = element.getText().trim();
				int intform = Integer.parseInt(txtRoom);
				if (!txtRoom.startsWith("-")) {
					if (intform == row1) {
						return row;
					}

				}
			}
		}
		return rowNum;
	}

	public void verifyMultiDay(String resort, String date) {
		pageLoaded(lstResortforinventory);
		initialize();
		selectResort(resort);
		pageLoaded(rdoMulti);
		initialize();
		selectMultiDayRadio();
		pageLoaded(rdoMulti);
		initialize();
		enterdate(getDate(date));
		pageLoaded();
		clickOnFindButton();

		matchOverSoldRoomMulti();
	}

	public void clickOnBackToInventory() {
		btnBackToInventory.syncVisible(driver);
		btnBackToInventory.jsClick(driver);
		Sleeper.sleep(1000);
	}

	public void clickOnApprovedButton() {
		btnAdjustApproved.syncEnabled(driver);
		btnAdjustApproved.jsClick(driver);
		Sleeper.sleep(1000);
	}

}

