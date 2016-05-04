package apps.lilo.manageArrivals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

import apps.lilo.assignRoom.AssignRoomPage;
import apps.lilo.processingYourRequest.ProcessingYourRequest;
import apps.lilo.reservationDetails.ReservationDetailsPage;
import core.WebDriverSetup;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Label;
import core.interfaces.Link;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import selenium.common.CustomVerification;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

/**
 * @summary Contains the page methods & objects found in the Manage
 *          Arrivals-Arrival Tab page
 * @version Created 11/05/2014
 * @author Waightstill W Avery
 */
public class ArrivalTab {

	// *******************************
	// *** Arrival Tab Page Fields ***
	// *******************************
	private int timeout = WebDriverSetup.getDefaultTestTimeout();
	private int loopCounter = 0;
	private int iSize;
	private List<WebElement> resortRadioGroup;
	private String resortLocationValue;
	private Datatable datatable = new Datatable();
	private WebDriver driver;
	private CustomVerification custom = new CustomVerification();

	// *********************************
	// *** Arrival Tab Page Elements ***
	// *********************************

	// Go search button
	@FindBy(id = "manageArrivalForm:goSearchSelectedResortIdsForArrivals")
	private Link lnkGoSearch;

	@FindBy(linkText = "Assign")
	private Link lnkAssign;

	// Filter textbox
	@FindBy(id = "filter_input")
	private Textbox txtFilter;

	// Search Date textbox
	@FindBy(id = "manageArrivalForm:selectDateInput")
	private Textbox txtDateSearch;

	// Arriving Accommodations webtable
	@FindBy(id = "arrivingAccommodationsDataTable1")
	private Webtable tblArrivingAccommodations;

	// Notifications button
	@FindBy(id = "manageArrivalForm:notificationID")
	private Button btnNotifications;

	// Arrival Resort listbox
	@FindBy(id = "adjustRoomCountAppMenu:selectedResortIdForArrival")
	private Listbox lstArrivalResort;

	// Loading image
	@FindBy(id = "manageArrivalForm:mngArrInPageStatusRegion:status.start")
	private Element eleLoading;

	// Refresh button
	@FindBy(id = "manageArrivalForm:butRefresh")
	private Link lnkRefesh;

	@FindBy(xpath = "//table[@id='arrivingAccommodationsDataTable1']/tbody/tr[4]/td[5]/a")
	private Element searchedReservationNumber;

	// Arrival Accommodation table
	@FindBy(id = "arrivingAccommodationsDataTable1")
	private Webtable tblArriving;

	// Reservation Details Popup
	@FindBy(id = "viewResPopupModalPanelContentTable")
	private Webtable tblResPopupModal;

	// Arrivals Text
	@FindBy(id = "arrivalsTab_lbl")
	private Label lblArrivals;

	// Element Notification Off
	@FindBy(id = "manageArrivalForm:ofNotofication")
	private Element ofNotofication;

	// Element Notification On
	@FindBy(id = "manageArrivalForm:onNotofication")
	private Element onNotofication;

	// Guest Notification Table
	@FindBy(id = "guestOffNotificationConfirmationAllPopupModalPanelContentTable")
	private Webtable tblOffNotification;

	// Guest Notification Table
	@FindBy(id = "guestOnNotificationConfirmationPopupModalPanelContentTable")
	private Webtable tblOnNotification;

	// go options notifications
	@FindBy(id = "manageArrivalForm:notificationID")
	private Element btnNotificationsOption;

	// go options notifications -off
	@FindBy(id = "manageArrivalForm:offNotoficationId")
	private Element btnNotificationsOption_off;

	// go options notifications -off
	@FindBy(id = "manageArrivalForm:onNotoficationId")
	private Element btnNotificationsOption_on;

	// go pop up yes for notifications - off
	@FindBy(id = "OffNotificationConfirmationAllPopupForm:okButtonId")
	private Element btnYes_notificationOff;

	// go pop up yes for notifications - off
	// .//*[@id='OnNotificationConfirmationPopupForm:okButtonId']
	@FindBy(id = "OnNotificationConfirmationPopupForm:okButtonId")
	private Element btnYes_notificationOn;

	// go text verify notification
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:j_id159")
	private Element txtNotification_off;

	// Button inside Pop-up Window
	@FindBy(id = "OffNotificationConfirmationAllPopupForm:okButtonId")
	private Button btnWarningYesOff;

	// Button inside Pop-up Window
	@FindBy(id = "OnNotificationConfirmationPopupForm:okButtonId")
	private Button btnWarningYesOn;

	// Button inside Pop-up Window
	@FindBy(id = "viewResPopupBody:closeButton")
	private Button btnClose;

	// Button CheckIn kay
	@FindBy(id = "manageArrivalForm:butCheckIn")
	private Button btnCheckIn;

	// Button Encode
	@FindBy(id = "manageArrivalForm:butCutKeys")
	private Button btnEncode;

	// Button Print Collateral
	@FindBy(xpath = ".//*[@id='manageArrivalForm:printCollatralButton']")
	private Button btnPrintCollateral;

	// Button Hide Selected
	@FindBy(id = "manageArrivalForm:butClear")
	private Button btnHideSelected;
	
	//Manage Arrival Resort Radio Button filter
  	@FindBy(id = "manageArrivalForm:radio_filters")
  	private Element eleArrivalRadioFilter;
  	
  	@FindBy(id="search_result_search_container") private Button btnClearSelected;

	// *********************
	// ** Build page area **
	// *********************

	/**
	 * 
	 * @summary Constructor to initialize the page
	 * @version Created 11/05/2014
	 * @author Waightstill W Avery
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public ArrivalTab(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public boolean pageLoaded(WebDriver driver) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				lnkGoSearch);
	}

	public boolean pageLoaded(WebDriver driver, Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				element);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				lnkGoSearch);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				element);
	}

	public ArrivalTab initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	// *************************************
	// *** Arrival Tab Page Interactions ***
	// *************************************
	/**
	 * 
	 * @summary Method to select a view radio button
	 * @version Created 11/05/2014
	 * @author Waightstill W Avery
	 * @param view
	 *            - name of the desired view (KNOWN VIEWS: "Show All", "OLCI",
	 *            "DTR")
	 * @throws NA
	 * @return NA
	 */
	public void selectViewRadioButton(String view) {
		/*
		 * Known values at time of method creation: Show All OLCI DTR
		 */
		List<WebElement> elementList = driver.findElements(By
				.cssSelector("input[id^=\"manageArrivalForm:j_id\"]"));
		Iterator<WebElement> iterator = elementList.iterator();
		boolean buttonFound = false;

		while (iterator.hasNext() && !buttonFound) {
			Element element = new ElementImpl(iterator.next());
			System.out.println("Element" + element);
			element.highlight(driver);
			if (element.getAttribute("value").equalsIgnoreCase(view)) {
				element.highlight(driver);
				buttonFound = true;
				element.click();
				loadingImageHidden();
			}
		}
		Assert.assertEquals(buttonFound, true, "The radio button with value "
				+ view + " was not found\n");
	}

	/**
	 * 
	 * @summary Method to select
	 * @version Created 11/05/2014
	 * @author Waightstill W Avery
	 * @param view
	 *            - name of the desired view (KNOWN VIEWS: "Show All", "OLCI",
	 *            "DTR")
	 * @throws NA
	 * @return NA
	 */
	public void changeSearchView(String view) {
		selectViewRadioButton(view);
		clickSearchGo();
	}

	/**
	 * 
	 * @summary Method to determine if the loading image is hidden
	 * @version Created 11/05/2014
	 * @author Waightstill W Avery
	 * @param NA
	 * @throws NA
	 * @return NA
	 */
	private void loadingImageHidden() {
		loopCounter = 0;
		while (eleLoading.getAttribute("style").equalsIgnoreCase("")) {
			Sleeper.sleep(500);
			Assert.assertNotEquals(
					loopCounter++,
					timeout,
					"The loading image was not hidden after "
							+ String.valueOf(timeout / 2) + " seconds.\n");
		}
	}

	private void clickSearchGo() {
		lnkGoSearch.syncEnabled(driver, 40, false);
		lnkGoSearch.jsClick(driver);
		Sleeper.sleep(3000);
		// new ProcessingYourRequest().WaitForProcessRequest(driver);
		pageLoaded(driver);

	}

	/**
	 * 
	 * @summary Method to enter a string with which to filter the displayed
	 *          arrivals
	 * @version Created 11/05/2014
	 * @author Waightstill W Avery
	 * @param filter
	 *            - string to use for the filter
	 * @throws NA
	 * @return NA
	 */
	public void enterFilterSearch(String filter) {
		txtFilter.highlight(driver);
		txtFilter.safeSet(filter);
		Sleeper.sleep(1000);
		pageLoaded(driver);

		Sleeper.sleep(4000);
		String serached_element = driver
				.findElement(
						By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr[4]/td[5]/a"))
				.getText().trim();
		Element element = new ElementImpl(
				driver.findElement(By
						.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr[4]/td[5]/a")));
		element.highlight(driver);
		// searchedReservationNumber.highlight(driver);
		System.out.println("Element " + serached_element);
	}

	// By brajesh 15/2
	public boolean enterFilterSearch1(String filter) {
		boolean isReservationNumberFound = false;
		txtFilter.highlight(driver);
		txtFilter.safeSet(filter);
		Sleeper.sleep(1000);
		pageLoaded(driver);

		try {
			// Highlight the Matching Table element present inside the Table
			String serached_element = driver
					.findElement(
							By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr[4]/td[5]/a"))
					.getText().trim();
			Element element = new ElementImpl(
					driver.findElement(By
							.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr[4]/td[5]/a")));
			element.highlight(driver);
			System.out.println("Element " + element);

			if (serached_element.contains(filter)) {
				isReservationNumberFound = true;
				System.out.println("True " + element);
			}
		} catch (ElementNotFoundException e) {
			Reporter.log("The Element was not found", false);
			System.out.println("False");
			e.printStackTrace();
		}

		return isReservationNumberFound;
	}

	/**
	 * 
	 * @summary Method to refresh the page
	 * @version Created 11/05/2014
	 * @author Waightstill W Avery
	 * @param NA
	 * @throws NA
	 * @return NA
	 */
	public void refreshPage() {
		clickRefresh();
		pageLoaded(driver);
	}

	private void clickRefresh() {
		lnkRefesh.syncEnabled(driver, 45, false);
		lnkRefesh.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * 
	 * @summary Method to select the arrival resort
	 * @version Created 11/05/2014
	 * @author Waightstill W Avery
	 * @param resort
	 *            - name of the desired result as it appears in the list (EX:
	 *            Disney's Contemporary Resort)
	 * @throws NA
	 * @return NA
	 */
	public void selectArrivalResort(String resort) {
		lstArrivalResort.select(resort);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		pageLoaded(driver);
	}

	/**
	 * 
	 * @summary Method to enter an arrival date for which to search
	 * @version Created 11/05/2014
	 * @author Waightstill W Avery
	 * @param arrivalDate
	 *            - string of the arrival date (EX: 11/05/2014")
	 * @throws NA
	 * @return NA
	 */
	public void enterArrivalDate(String arrivalDate) {

		txtDateSearch.syncVisible(driver, 40, false);
		txtDateSearch.safeSet(arrivalDate);
		loadingImageHidden();
		pageLoaded(driver);
	}

	/**
	 * 
	 * @summary Method to locate the DTR icon in the arrivals table
	 * @version Created 11/05/2014
	 * @author Waightstill W Avery
	 * @param expectedToBeFound
	 *            - boolean value used to determine if the icon is expected to
	 *            be found
	 * @param reservationNumber
	 *            - reservation with which to search for an associated DTR image
	 * @throws NA
	 * @return true if icon is found; false otherwise
	 */
	public boolean locateDtrIcon(boolean expectedToBeFound,
			String reservationNumber) {
		boolean isIconFound = false;
		int row;
		int column = 0;
		int columnCount;

		columnCount = tblArrivingAccommodations.getColumnCount(driver, 1);
		for (int counter = 1; counter <= columnCount; counter++) {
			Element tableCell = new ElementImpl(
					tblArrivingAccommodations.getCell(driver, 1, counter));
			tableCell.highlight(driver);
			if (tableCell.getText().contains("DTR")) {
				column = counter;
			}
		}

		row = tblArrivingAccommodations.getRowWithCellText(driver,
				reservationNumber);
		Element cell = new ElementImpl(tblArrivingAccommodations.getCell(
				driver, row, column));
		try {
			if (cell.getAttribute("src").contains("BPD")) {
				isIconFound = true;
			}
		} catch (ElementNotFoundException e) {
			Reporter.log("The DTR icon was not found", true);
			e.printStackTrace();
		}

		return isIconFound;
	}

	/**
	 * 
	 * @summary Method to locate the OLCI icon in the arrivals table
	 * @version Created 11/05/2014
	 * @author Waightstill W Avery
	 * @param expectedToBeFound
	 *            - boolean value used to determine if the icon is expected to
	 *            be found
	 * @param reservationNumber
	 *            - reservation with which to search for an associated OLCI
	 *            image
	 * @throws NA
	 * @return true if the icon is found, false otherwise
	 */
	public boolean locateOlciIcon(boolean expectedToBeFound,
			String reservationNumber) {
		boolean isIconFound = false;
		int row;
		int column = 0;
		int columnCount;

		columnCount = tblArrivingAccommodations.getColumnCount(driver, 1);
		for (int counter = 1; counter <= columnCount; counter++) {
			Element tableCell = new ElementImpl(
					tblArrivingAccommodations.getCell(driver, 1, counter));
			tableCell.highlight(driver);
			if (tableCell.getText().contains("OLCI")) {
				column = counter;
			}
		}

		row = tblArrivingAccommodations.getRowWithCellText(driver,
				reservationNumber);
		Element cell = new ElementImpl(tblArrivingAccommodations.getCell(
				driver, row, column));

		try {
			if (cell.getAttribute("src").contains("OLCI")) {
				isIconFound = true;
			}
		} catch (ElementNotFoundException e) {
			Reporter.log("The OLCI icon was not found", true);
			e.printStackTrace();
		}

		return isIconFound;
	}

	public void verifyOlciDtrBlankAscendDescend() {
		/*
		 * The page can load and then the UI code for RTC33615 is invoked and
		 * reloads the page. For this reason, a hard wait may be neccessary
		 */
		Sleeper.sleep(2000);
		boolean dtrFound = false;
		boolean olciFound = false;
		boolean blankFound = false;
		boolean[] descIsFound = { dtrFound, olciFound, blankFound };

		isElementNull(tblArrivingAccommodations,
				"The Arriving Accommodations webtable was not displayed after ["
						+ String.valueOf(timeout) + "] seconds");
		List<WebElement> rowList = driver
				.findElements(By
						.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr"));
		int rows = rowList.size();
		for (loopCounter = 0; loopCounter < rows; loopCounter++) {
			List<WebElement> dtrOlciImage = driver
					.findElements(By
							.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["
									+ String.valueOf(loopCounter + 1)
									+ "]/td[10]/img"));
			System.out.println(dtrOlciImage);
			descIsFound = isDtrOlciBlankFoundDescending(dtrOlciImage,
					descIsFound);
		}

		dtrFound = false;
		olciFound = false;
		blankFound = false;
		boolean[] ascIsFound = { dtrFound, olciFound, blankFound };
		Element element = new ElementImpl(
				driver.findElement(By
						.xpath("//table[@id='arrivingAccommodationsDataTable1']/thead/tr/th[10]/div/i")));
		do {
			element.highlight(driver);
			element.click();
			new ProcessingYourRequest().WaitForProcessRequest(driver);
			isElementNull(tblArrivingAccommodations,
					"The Arriving Accommodations webtable was not displayed after ["
							+ String.valueOf(timeout) + "] seconds");
		} while (!(driver
				.findElement(
						By.xpath("//table[@id='arrivingAccommodationsDataTable1']/thead/tr/th[10]"))
				.getAttribute("class").contains("Asc")));
		rowList = driver
				.findElements(By
						.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr"));
		rows = rowList.size();
		for (loopCounter = 0; loopCounter < rows; loopCounter++) {
			List<WebElement> dtrOlciImage = driver
					.findElements(By
							.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["
									+ String.valueOf(loopCounter + 1)
									+ "]/td[10]/img"));
			ascIsFound = isDtrOlciBlankFoundAscending(dtrOlciImage, ascIsFound);
		}
	}

	private boolean[] isDtrOlciBlankFoundDescending(List<WebElement> list,
			boolean[] found) {
		// Determine if an image is found
		if (list.size() > 0) {
			// If OLCI is found...
			if (list.get(0).getAttribute("Alt")
					.equalsIgnoreCase("online check in")) {
				found[1] = true;
				Assert.assertNotEquals(
						found[2],
						true,
						"An OLCI reservation was unexpectedly found after an standard reservation was found.");
				// If DTR is found
			} else {
				found[0] = true;
				Assert.assertNotEquals(found[1], true,
						"A DTR reservation was unexpectedly found after an OLCI reservation was found.");
				Assert.assertNotEquals(
						found[2],
						true,
						"A DTR reservation was unexpectedly found after a standard reservation was found.");
			}
			// If not, a blank value was found
		} else {
			found[2] = true;
		}

		return found;
	}

	private boolean[] isDtrOlciBlankFoundAscending(List<WebElement> list,
			boolean[] found) {
		// Determine if an image is found
		if (list.size() > 0) {
			// If OLCI is found...
			if (list.get(0).getAttribute("Alt")
					.equalsIgnoreCase("online check in")) {
				found[1] = true;
				Assert.assertNotEquals(
						found[0],
						true,
						"An OLCI reservation was unexpectedly found after an DTR reservation was found.");
				// If DTR is found
			} else {
				found[0] = true;
			}
			// If not, a blank value was found
		} else {
			found[2] = true;
			Assert.assertNotEquals(
					found[0],
					true,
					"A standard reservation was unexpectedly found after a DTR reservation was found.");
			Assert.assertNotEquals(
					found[1],
					true,
					"A standard reservation was unexpectedly found after an OLCI reservation was found.");
		}

		return found;
	}

	private void isElementNull(Element element, String errorMessage) {
		int timeoutCounter = 0;

		do {
			Sleeper.sleep(1000);
			Assert.assertNotEquals(timeoutCounter++, timeout, errorMessage);
			pageLoaded(element);
			initialize();
		} while (element == null);
	}

	public void verifyOlciOnlyReservations() {
		boolean isNonOlciFound = false;
		/*
		 * The page can load and then the UI code for RTC33615 is invoked and
		 * reloads the page. For this reason, a hard wait may be neccessary
		 */
		Sleeper.sleep(2000);
		pageLoaded();
		isElementNull(tblArrivingAccommodations,
				"The Arriving Accommodations webtable was not displayed after ["
						+ String.valueOf(timeout) + "] seconds");

		Element element = new ElementImpl(
				driver.findElement(By
						.xpath("//span[@id='manageArrivalForm:radio_filters']/table/tbody/tr/td[2]/input")));
		element.jsClick(driver);
		isLoadingImageVisible();
		clickSearchGo();
		isElementNull(tblArrivingAccommodations,
				"The Arriving Accommodations webtable was not displayed after ["
						+ String.valueOf(timeout) + "] seconds");

		List<WebElement> rowList = driver
				.findElements(By
						.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr"));
		int rows = rowList.size();
		for (loopCounter = 0; loopCounter < rows; loopCounter++) {
			List<WebElement> dtrOlciImage = driver
					.findElements(By
							.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["
									+ String.valueOf(loopCounter + 1)
									+ "]/td[10]/img"));
			if (!dtrOlciImage.get(0).getAttribute("alt")
					.equalsIgnoreCase("online check in")) {
				isNonOlciFound = true;
				String resNumber = driver
						.findElement(
								By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["
										+ String.valueOf(loopCounter + 1)
										+ "]/td[5]/a")).getText();
				Assert.assertNotEquals(
						isNonOlciFound,
						true,
						"Reservation ["
								+ String.valueOf(loopCounter)
								+ 1
								+ "] with reservation number ["
								+ resNumber
								+ "] listed in the Arriving Accommodations webtable was not an OLCI reservation");
			}
		}
	}

	private void isLoadingImageVisible() {
		Element loadingImage = new ElementImpl(driver.findElement(By
				.xpath("//img[@src='/PMS/pms/images/please_wait_blue.gif']")));
		loadingImage.syncVisible(driver, 1, false);
	}

	public void verifyRefreshViewReservationOrder() {

		/*
		 * The page can load and then the UI code for RTC33615 is invoked and
		 * reloads the page. For this reason, a hard wait may be neccessary
		 */
		Sleeper.sleep(2000);
		boolean dtrFound = false;
		boolean olciFound = false;
		boolean blankFound = false;
		boolean[] descIsFound = { dtrFound, olciFound, blankFound };

		isElementNull(tblArrivingAccommodations,
				"The Arriving Accommodations webtable was not displayed after ["
						+ String.valueOf(timeout) + "] seconds");
		clickRefreshView();
		List<WebElement> rowList = driver
				.findElements(By
						.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr"));
		int rows = rowList.size();
		for (loopCounter = 0; loopCounter < rows; loopCounter++) {
			List<WebElement> dtrOlciImage = driver
					.findElements(By
							.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["
									+ String.valueOf(loopCounter + 1)
									+ "]/td[10]/img"));
			descIsFound = isDtrOlciBlankFoundDescending(dtrOlciImage,
					descIsFound);
		}
	}

	private void clickRefreshView() {
		lnkRefesh.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		pageLoaded(tblArrivingAccommodations);
		initialize();
	}

	// Method Ascending and descending Order
	public void clickonNameReservationAccommRoom() {

		for (int j = 4; j <= 7; j++) {
			// Xpath for Table Header element Name, Reservation,Accomm, Room
			String tableHeader = ".//table[@id='arrivingAccommodationsDataTable1']/thead/tr/th["
					+ j + "]/div";
			Element element = new ElementImpl(driver.findElement(By
					.xpath(tableHeader)));
			element.highlight(driver);
			driver.findElement(By.xpath(tableHeader)).click();

			Sleeper.sleep(1000);
			clickRefreshView();
			Sleeper.sleep(6000);

		}
	}

	/**
	 * 
	 * @summary Method to enter a string with which to filter the displayed
	 *          arrivals
	 * @version Created 03/12/2015
	 * @author Brajesh Ahirwar
	 * @param filter
	 *            - string to use for the filter
	 * @throws NA
	 * @return NA
	 */

	public void serachByFilter(String filter, String arrivalDate, String match,
			int td)

	{
		serachReservation(filter, arrivalDate);
		WebElement table = driver.findElement(By
				.id("arrivingAccommodationsDataTable1"));
		// Now get all the TR elements from the table
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		int rows = allRows.size();
		int counter = 0;
		for (int i = 1; i < rows; i++) {
			String actual = driver
					.findElement(
							By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["
									+ i + "]/td[" + td + "]")).getText().trim();
			if (actual.equalsIgnoreCase(match)) {
				counter++;

			} else {

			}

		}
		if (counter >= 1) {

			Assert.assertEquals(true, true, "Expected Value " + match
					+ " is Matched with  Actual");
		} else {

			Assert.assertEquals(false, true, "Expected Value " + match
					+ " is Matched with  Actual");

		}

	}

	public boolean todayDateMatch() {
		refreshPage();
		boolean todaydatematch = false;

		// String todayDate = "02/28/2015";
		String todayDate = systemDatePicker();
		txtDateSearch.highlight(driver);
		String defaultDate_TodayArrival = txtDateSearch.getText();
		if (defaultDate_TodayArrival.contains(todayDate)) {
			return true;
		} else {
			return todaydatematch;
		}
	}

	// Return System Date
	public String systemDatePicker() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		return dateFormat.format(date); // 02/27/2015
	}

	public void searchByName(String filter, String arrivalDate, String match) {
		serachByFilter(filter, arrivalDate, match, 4);
	}

	public void searchByReservationNumber(String filter, String arrivalDate,
			String match) {
		serachByFilter(filter, arrivalDate, match, 5);

	}

	public void SearchByAccommStatus(String filter, String arrivalDate,
			String match) {
		serachByFilter(filter, arrivalDate, match, 6);
	}

	public void SearchByRoom(String filter, String arrivalDate, String match) {
		serachByFilter(filter, arrivalDate, match, 7);

	}

	public void houseKeepingStatus(String filter, String arrivalDate,
			String match) {
		serachByFilter(filter, arrivalDate, match, 8);

	}

	public void verifyNotificationStatus(String filter, String arrivalDate,
			String match) {

		serachByFilter(filter, arrivalDate, match, 9);
	}

	public void verifyETATime(String filter, String arrivalDate, String match) {
		serachByFilter(filter, arrivalDate, match, 11);

	}

	public void verifyArrivalStatus(String filter, String arrivalDate,
			String match) {

		serachByFilter(filter, arrivalDate, match, 12);
	}

	public void verifySource(String filter, String arrivalDate, String match) {
		serachByFilter(filter, arrivalDate, match, 13);

	}

	public void verifyETDDate(String filter, String arrivalDate, String match) {
		serachByFilter(filter, arrivalDate, match, 14);

	}

	public void serachByGruopCode(String filter, String arrivalDate,
			String match) {
		serachByFilter(filter, arrivalDate, match, 16);

	}

	public void assign_Room(String filter, String arrivalDate, String match)

	{
		clickRefresh();
		enterArrivalDate(arrivalDate);
		Sleeper.sleep(2000);
		clickSearchGo();
		Sleeper.sleep(4000);
		pageLoaded(driver);
		txtFilter.safeSet(filter);
		Sleeper.sleep(5000);
		pageLoaded(driver);
		isElementNull(lnkAssign,
				"The Find Available Room Popup window was not displayed after ["
						+ String.valueOf(timeout) + "] seconds.");
		lnkAssign.jsClick(driver);
		Sleeper.sleep(10000);
		ArrayList<String> tabs;
		tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0)); // switch to the first window
		AssignRoomPage assignRoom = new AssignRoomPage(driver);
		// assignRoom.clickSearchCriteria();
		assignRoom.assignVacantRoom();
		// assignRoom.clickAlternateRoomsTab();
	}

	/**
	 * 
	 * @summary Method to verify Arrivals on Arrival tab
	 * @version Created 08/17/2015
	 * @author Brajesh Ahirwar
	 * @param expectedToBeFound
	 *            - boolean value used to determine if the expected to be found
	 * @param reservationNumber
	 *            - reservation with which to search for Validation
	 * @throws NA
	 * @return true if the status is found, on Failure it log a defect in to
	 *         TestNG report file
	 *
	 */

	public void verifyArrivals(Map<String, String> status) {
		String reserNumber = status.get("Reservation");
		String arrivalDate = status.get("arrivalDate");
		serachReservation(reserNumber, arrivalDate);

		tblArrivingAccommodations.syncVisible(driver, 40, false);

		WebElement table = driver.findElement(By
				.id("arrivingAccommodationsDataTable1"));
		// Now get all the TR elements from the table
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		int rows = allRows.size();
		for (int row = 1; row < rows; row++) {

			String actual = driver
					.findElement(
							By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["
									+ row + "]/td[5]")).getText().trim();
			if (actual.equalsIgnoreCase(reserNumber)) {
				matchingStatus(row, status);
				break;
			}

		}
		custom.checkForVerificationErrors();
	}

	// Method to search resrvation number
	public void serachReservation(String reserNumber, String arrivalDate) {
		// Click On Refresh button to Refresh a Page
		clickRefresh();
		// Enter arrival date
		enterArrivalDate(arrivalDate);
		// click On Go button
		clickSearchGo();
		int counter = 0;
		do {
			txtFilter.sendKeys(reserNumber);
			loopCounter++;
			pageLoaded(driver);
			initialize();

		} while (txtFilter.getAttribute("value").equalsIgnoreCase("")
				&& counter < 10);

		tblArriving.syncVisible(driver, 60, false);
		pageLoaded(driver);
		initialize();
	}

	public void matchingStatus(int row, Map<String, String> status) {

		for (Entry<String, String> arrivalStatus : status.entrySet()) {
			String getKeyValue = arrivalStatus.getKey().trim();

			switch (getKeyValue) {

			case "VIP":
				matchArrivals(row, 2, status.get("VIP"));
				break;
			case "Name":
				matchArrivals(row, 4, status.get("Name"));
				break;
			case "Reservation":
				matchArrivals(row, 5, status.get("Reservation"));
				break;
			case "AccommStatus":
				matchArrivals(row, 6, status.get("AccommStatus"));
				break;
			case "Room":
				matchArrivals(row, 7, status.get("Room"));

				break;
			case "HK Status":
				matchArrivals(row, 8, status.get("HK Status"));

				break;
			case "Notif":
				matchArrivals(row, 9, status.get("Notif"));

				break;
			case "DTR/OLCI":
				matchArrivals(row, 10, status.get("DTR/OLCI"));

				break;
			case "ETA":
				matchArrivals(row, 11, status.get("ETA"));

				break;
			case "Arrival Status":
				matchArrivals(row, 12, status.get("Arrival Status"));

				break;
			case "Source":
				matchArrivals(row, 13, status.get("Source"));
				System.out.println("We are Source");
				break;
			case "ETD":
				matchArrivals(row, 14, status.get("ETD"));

				break;
			case "Grp":
				matchArrivals(row, 16, status.get("Grp"));
				break;

			}
		}
	}

	/**
	 * 
	 * @summary Method to match individual status of arrival
	 * @version Created 08/17/2015
	 * @author Brajesh Ahirwar
	 * @param individual
	 *            row number,col number and expected value
	 * 
	 * @throws NA
	 * @return true if the status is found,on failure log a defect into TestNG
	 *         file
	 *
	 */

	public void matchArrivals(int row, int col, String expected) {
		String actual = driver
				.findElement(
						By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["
								+ row + "]/td[" + col + "]")).getText().trim();
		if (!actual.equalsIgnoreCase(expected)) {
			custom.verifyTrue(false, "Expected Value is : [" + expected
					+ "]  but found : [" + actual + "]");
		}

	}

	/**
	 * 
	 * @summary Method to verify Reservation Details on Arrivals Tab
	 * @version Created 08/18/2015
	 * @author Brajesh Ahirwar
	 * @param expectedToBeFound
	 *            - boolean value used to determine if the expected to be found
	 * @param reservationNumber
	 *            - reservation with which to search for Validation
	 * @throws NA
	 * @return true if the status is found, on Failure it log a defect in to
	 *         TestNG report file
	 *
	 */
	public void verifyReservationDetails(Map<String, String> status) {

		String reserNumber = status.get("Reservation");
		String arrivalDate = status.get("arrivalDate");
		serachReservation(reserNumber, arrivalDate);
		WebElement table = driver.findElement(By
				.id("arrivingAccommodationsDataTable1"));
		// Now get all the TR elements from the table
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		int rows = allRows.size();
		for (int row = 1; row < rows; row++) {

			String actual = driver
					.findElement(
							By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["
									+ row + "]/td[5]")).getText().trim();
			if (actual.equalsIgnoreCase(reserNumber)) {

				String linkName = status.get("linkName");
				clickOnLinkTest(linkName);
				tblResPopupModal.syncVisible(driver, 30, false);
				verifyResrvationDetails(status);
				btnClose.jsClick(driver);
				break;
			}

		}
		custom.checkForVerificationErrors();
	}

	public void clickOnLinkTest(String linkText) {

		Element lnkName = new ElementImpl(driver.findElement(By
				.linkText(linkText)));

		lnkName.jsClick(driver);
	}

	/**
	 * 
	 * @summary Method to verify reservation details on Reservation Details
	 *          Pop-up window
	 * @version Created 08/18/2015
	 * @author Brajesh Ahirwar
	 * @param expectedToBeFound
	 *            - boolean value used to determine if the expected to be found
	 * @param reservationNumber
	 *            - reservation number with which to search for Validation
	 * @throws NA
	 * @return true if the status is found, on Failure it log a defect in to
	 *         TestNG report file
	 *
	 */

	public void verifyResrvationDetails(Map<String, String> status) {
		Element txtGuestName;
		String reservationNumber = status.get("Reservation");
		String guestName = status.get("firstlastName");
		// Now get all the TR elements from the table
		List<WebElement> allRows = driver.findElements(By
				.xpath("//td[@class ='popupBody']/table/tbody/*"));
		int rows = allRows.size();
		System.out.println("Rows : " + rows);
		for (int row = 1; row < rows; row++) {
			switch (row) {
			case 1:

				int counter = 0;
				do {
					txtGuestName = new ElementImpl(driver.findElement(By
							.xpath("//td[@class ='popupBody']/table/tbody/tr["
									+ row + "]/td/table/tbody/tr/td")));
					txtGuestName.highlight(driver);
					loopCounter++;
					pageLoaded(txtGuestName);
					initialize();
				} while (txtGuestName.getText().equalsIgnoreCase("")
						&& counter < 10);
				String actual = txtGuestName.getText();
				verfiyActualExpected(actual, guestName);
				break;
			case 2:
				Element txtReservation = new ElementImpl(driver.findElement(By
						.xpath("//td[@class ='popupBody']/table/tbody/tr["
								+ row + "]/td")));
				String getReservation = txtReservation.getText().trim();
				String reservervation[] = getReservation.split(" ");
				String resNumber = reservervation[2];
				verfiyActualExpected(resNumber, reservationNumber);
				break;

			}

		}
	}

	public void verfiyActualExpected(String actual, String expected) {
		custom.verifyTrue(actual.equalsIgnoreCase(expected),
				"Expected Value is : [" + expected + "]  but found : ["
						+ actual + "]");

	}

	public void verifyBackToArrivals(Map<String, String> status) {

		String reserNumber = status.get("Reservation");
		String arrivalDate = status.get("arrivalDate");
		serachReservation(reserNumber, arrivalDate);
		WebElement table = driver.findElement(By
				.id("arrivingAccommodationsDataTable1"));
		// Now get all the TR elements from the table
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		int rows = allRows.size();
		for (int row = 1; row < rows; row++) {

			String actual = driver
					.findElement(
							By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["
									+ row + "]/td[5]")).getText().trim();
			if (actual.equalsIgnoreCase(reserNumber)) {
				clickOnLinkTest(reserNumber);
				validateReservation(reserNumber);
				txtManageArrivalMatching();
				break;
			}

		}
		custom.checkForVerificationErrors();
	}

	// Method to Verify reservation number on reservation details page
	public void validateReservation(String reserervationExp) {

		ReservationDetailsPage resDetailsPage = new ReservationDetailsPage(
				driver);
		resDetailsPage.resevationExitButton();
		String reservationAct = resDetailsPage.captureReservation();
		verfiyActualExpected(reservationAct, reserervationExp);
		resDetailsPage.clickOnBackToArrivals();
	}

	// Method to match Manage Arrivals Tab on Arrivals Page
	public void txtManageArrivalMatching() {
		int counter = 0;
		do {
			lblArrivals.syncVisible(driver, 60, false);
			loopCounter++;
			pageLoaded(driver);
			initialize();

		} while (lblArrivals.getText().equalsIgnoreCase("") && counter < 10);

		String actual = lblArrivals.getText().trim();
		verfiyActualExpected(actual, "Arrivals");
	}

	/**
	 * 
	 * @summary Method to Validate Notification Turn single reservation Off
	 * @version Created 08/24/2015
	 * @author Brajesh Ahirwar
	 * @param Reservation
	 *            Number , Arrival Date
	 * @throws NA
	 * @return NA
	 */
	public void notificationTurnSingleReservationOff(Map<String, String> status) {

		String reserNumber = status.get("Reservation");
		String arrivalDate = status.get("arrivalDate");
		// Click On Refresh button to Refresh a Page
		clickRefresh();
		// Enter arrival date
		enterArrivalDate(arrivalDate);
		// click On Go button
		clickSearchGo();
		// Method to match reservation number,Notification and click on check
		// Box against matching
		clickOnCheckBox(reserNumber, "On");
		pageLoaded(driver);
		initialize();
		mouseOverClick("Off");
		// Method to click reservation link and validate Notification on Room
		// Tab
		clickLnkMatchArrivals(reserNumber, "Off");
		// Log the error on Failure
		custom.checkForVerificationErrors();
	}

	public void toggleCheckBox(int row) {

		Element chkBox = new ElementImpl(
				driver.findElement(By
						.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["
								+ row + "]/td[1]/span/input")));
		chkBox.highlight(driver);
		chkBox.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		pageLoaded(driver);

	}

	// Mouse over Notification button and click on the element On or Off
	public void mouseOverClick(String notification) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
		js.executeScript(mouseOverScript, btnNotifications);
		Sleeper.sleep(500);

		if (notification == "Off") {

			ofNotofication.syncEnabled(driver, 40, false);

			ofNotofication.jsClick(driver);
			clickOnOffYes(btnWarningYesOff, tblOffNotification);
		} else {
			onNotofication.syncEnabled(driver, 40, false);
			onNotofication.jsClick(driver);
			clickOnOffYes(btnWarningYesOn, tblOnNotification);
		}

		pageLoaded(driver);
		initialize();
		Sleeper.sleep(4000);

	}

	public void clickOnOffYes(Button btnWarning, Webtable notification) {
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		notification.syncVisible(driver, 60, false);
		notification.highlight(driver);
		btnWarning.syncEnabled(driver, 50, false);
		btnWarning.highlight(driver);
		btnWarning.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);

	}

	// Method to Verify reservation number on reservation details page
	public void validateNotification(String reserervationExp,
			String notificationExp) {

		ReservationDetailsPage resDetailsPage = new ReservationDetailsPage(
				driver);
		resDetailsPage.resevationExitButton();
		String reservationAct = resDetailsPage.captureReservation();
		String notificationStatusAct = resDetailsPage
				.getReservationStatusOnandOff();
		verfiyActualExpected(reservationAct, reserervationExp);
		verfiyActualExpected(notificationStatusAct, notificationExp);

	}

	/**
	 * 
	 * @summary Method to Validate Notification Turn single reservation On
	 * @version Created 08/25/2015
	 * @author Brajesh Ahirwar
	 * @param Reservation
	 *            Number , Arrival Date
	 * @throws NA
	 * @return NA
	 */
	public void notificationTurnSingleReservationOn(Map<String, String> status) {

		String reserNumber = status.get("Reservation");
		String arrivalDate = status.get("arrivalDate");
		// Click On Refresh button to Refresh a Page
		clickRefresh();
		// Enter arrival date
		enterArrivalDate(arrivalDate);
		// click On Go button
		clickSearchGo();
		// Method to match reservation number,Notification and click on check
		// Box against matching
		clickOnCheckBox(reserNumber, "On");
		pageLoaded(driver);
		initialize();
		// Method to change Notification from 'On' to 'Off'
		mouseOverClick("Off");
		// Method to click reservation link and validate Notification on Room
		// Tab
		clickLnkMatchArrivals(reserNumber, "Off");
		pageLoaded(driver);
		initialize();
		// ***********************Notification !!!!!!!--On--!!!! for multiple
		// reservation*****************************
		// Click On Refresh button to Refresh a Page
		clickRefresh();
		// Enter arrival date
		enterArrivalDate(arrivalDate);
		// click On Go button
		clickSearchGo();
		// Method to match reservation number,Notification and click on check
		// Box against matching
		clickOnCheckBox(reserNumber, "Off");
		pageLoaded(driver);
		initialize();
		// Method to change Notification from 'Off' to 'On'
		mouseOverClick("On");
		// Method to click reservation link and validate Notification on Room
		// Tab
		clickLnkMatchArrivals(reserNumber, "On");
		// Log the error on Failure
		custom.checkForVerificationErrors();

	}

	public String txtAttivals(int row, int col) {

		Element txtActual = new ElementImpl(
				driver.findElement(By
						.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["
								+ row + "]/td[" + col + "]")));
		String getActual = txtActual.getText().trim();
		return getActual;

	}

	/**
	 * 
	 * @summary Method to Validate Notification Turn Multiple reservation Off
	 * @version Created 08/28/2015
	 * @author Brajesh Ahirwar
	 * @param Reservation
	 *            Number , Arrival Date
	 * @throws NA
	 * @return NA
	 */

	public void notificationTurnMultipleReservationOff(
			Map<String, String> reservationInfo) {

		String reservationOne = reservationInfo.get("reservationFirst");
		String reservationTwo = reservationInfo.get("reservationSecond");
		String arrivalDate = reservationInfo.get("arrivalDate");
		// Click On Refresh button to Refresh a Page
		clickRefresh();
		// Enter arrival date
		enterArrivalDate(arrivalDate);
		// click On Go button
		clickSearchGo();
		// Method to match reservation number,Notification and click on check
		// Box against matching
		clickOnCheckBox(reservationOne, "On");
		pageLoaded(driver);
		initialize();
		clickOnCheckBox(reservationTwo, "On");
		// Method to change Notification from 'On' to 'Off'
		mouseOverClick("Off");
		// Method to click reservation link and validate Notification on Room
		// Tab
		clickLnkMatchArrivals(reservationOne, "Off");
		pageLoaded(driver);
		initialize();
		clickLnkMatchArrivals(reservationTwo, "Off");
	}

	/**
	 * 
	 * @summary Method to Validate Notification Turn Multiple reservation On
	 * @version Created 08/27/2015
	 * @author Brajesh Ahirwar
	 * @param Reservation
	 *            Number , Arrival Date
	 * @throws NA
	 * @return NA
	 */

	public void notificationTurnMultipleReservationOn(
			Map<String, String> reservationInfo) {

		String reservationOne = reservationInfo.get("reservationFirst");
		String reservationTwo = reservationInfo.get("reservationSecond");
		String arrivalDate = reservationInfo.get("arrivalDate");
		// Click On Refresh button to Refresh a Page
		clickRefresh();
		// Enter arrival date
		enterArrivalDate(arrivalDate);
		// click On Go button
		clickSearchGo();
		// Method to match reservation number,Notification and click on check
		// Box against matching
		clickOnCheckBox(reservationOne, "On");
		pageLoaded(driver);
		initialize();
		clickOnCheckBox(reservationTwo, "On");
		// Method to change Notification from 'On' to 'Off'
		mouseOverClick("Off");
		// Method to click reservation link and validate Notification on Room
		// Tab
		clickLnkMatchArrivals(reservationOne, "Off");
		pageLoaded(driver);
		initialize();
		clickLnkMatchArrivals(reservationTwo, "Off");
		// ***********************Notification !!!!!!!--On--!!!! for multiple
		// reservation*****************************
		// Click On Refresh button to Refresh a Page
		clickRefresh();
		// Enter arrival date
		enterArrivalDate(arrivalDate);
		// click On Go button
		clickSearchGo();
		// Method to match reservation number,Notification and click on check
		// Box against matching
		clickOnCheckBox(reservationOne, "Off");
		pageLoaded(driver);
		initialize();
		clickOnCheckBox(reservationTwo, "Off");
		// Method to change Notification from 'Off' to 'On'
		mouseOverClick("On");
		// Method to click reservation link and validate Notification on Room
		// Tab
		clickLnkMatchArrivals(reservationOne, "On");
		pageLoaded(driver);
		initialize();
		clickLnkMatchArrivals(reservationTwo, "On");

	}

	// Method to match the reservation number and check the check box against
	// matching
	public void clickOnCheckBox(String reserNumber, String notifi) {
		WebElement table = driver.findElement(By
				.id("arrivingAccommodationsDataTable1"));
		// Now get all the TR elements from the table
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		int rows = allRows.size();
		for (int row = 1; row < rows; row++) {

			String actual = txtAttivals(row, 5);
			if (actual.equalsIgnoreCase(reserNumber)) {
				String txtActual = txtAttivals(row, 9);
				if (txtActual.equalsIgnoreCase(notifi)) {
					// Click on Ckeck Box
					toggleCheckBox(row);
					btnNotifications.syncVisible(driver, 80, false);

				} else {
					custom.verifyTrue(false,
							"Expected status is :[Off] but found : ["
									+ txtActual + "]");
				}
				break;

			}
		}

	}

	// Method to match the arrival status,click on reservation number,match
	// notification on Room tab and back to arrivals
	public void clickLnkMatchArrivals(String reserNumber, String notif) {
		clickOnLinkTest(reserNumber);
		// Validate reservation number on Room tab
		validateNotification(reserNumber, notif);
		ReservationDetailsPage resDetailsPage = new ReservationDetailsPage(
				driver);
		resDetailsPage.clickOnBackToArrivals();
		// Verify Arrival Tab on Manage arrival page
		txtManageArrivalMatching();
	}

	/**
	 * 
	 * @summary Method to Assign a room for reservation and Validate on Manage
	 *          Arrival Page
	 * @version Created 09/02/2015
	 * @author Brajesh Ahirwar
	 * @param Reservation
	 *            Number , Arrival Date
	 * @throws NA
	 * @return NA
	 */

	public void assignRoom(String reservation, String arrivalDate) {

		serachReservation(reservation, arrivalDate);
		clickOnLinkTest("Assign");
		TestReporter.logStep("Assign a room for Reservation");
		AssignRoomPage assign = new AssignRoomPage(driver);
		assign.assignRoomPageLoaded();
		String roomNumber = assign.chooseVacantRoomToAssign();
		serachReservation(reservation, arrivalDate);
		tblArrivingAccommodations.syncVisible(driver, 40, false);

		WebElement table = driver.findElement(By
				.id("arrivingAccommodationsDataTable1"));
		// Now get all the TR elements from the table
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		int rows = allRows.size();
		for (int row = 1; row < rows; row++) {

			String actual = driver
					.findElement(
							By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["
									+ row + "]/td[5]")).getText().trim();
			if (actual.equalsIgnoreCase(reservation)) {
				String assignedRoom = txtAttivals(row, 7);
				Assert.assertTrue(assignedRoom.equalsIgnoreCase(roomNumber),
						"Expected Room : [" + roomNumber + "] but Found : ["
								+ assignedRoom + "]");
				break;
			}

		}

	}

	/**
	 * 
	 * @summary Method to Assign a room for reservation and verify house keeping
	 *          status
	 * @version Created 09/30/2015
	 * @author Brajesh Ahirwar
	 * @param Reservation
	 *            Number , Arrival Date
	 * @throws NA
	 * @return NA
	 */

	public void hkStatusMatching(String reservation, String arrivalDate) {
		serachReservation(reservation, arrivalDate);
		clickOnLinkTest("Assign");
		TestReporter.logStep("Assign a room for Reservation");
		AssignRoomPage assign = new AssignRoomPage(driver);
		assign.assignRoomPageLoaded();
		String roomNumber = assign.chooseVacantRoomToAssign();
		clickRefresh();
		pageLoaded();
		serachReservation(reservation, arrivalDate);
		tblArrivingAccommodations.syncVisible(driver, 40, false);

		WebElement table = driver.findElement(By
				.id("arrivingAccommodationsDataTable1"));
		// Now get all the TR elements from the table
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		int rows = allRows.size();
		for (int row = 1; row < rows; row++) {

			String actual = driver
					.findElement(
							By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["
									+ row + "]/td[5]")).getText().trim();
			if (actual.equalsIgnoreCase(reservation)) {
				String hkstatus = txtAttivals(row, 8);
				TestReporter.logStep("Verify House Keeping status------");
				Assert.assertTrue(hkstatus.equalsIgnoreCase("CLEAN"),
						"Expected HKStatus : [" + roomNumber
								+ "] but Found : [" + hkstatus + "]");
				break;
			}

		}

	}

	/**
	 * 
	 * @summary Method to Validate CheckIn Reservation
	 * @version Created 10/20/2015
	 * @author Brajesh Ahirwar
	 * @param Reservation
	 *            Number , Arrival Date
	 * @throws NA
	 * @return NA
	 */
	public void checkInReservation(String guestName) {
		pageLoaded();
		matchResNumandCheckIn(guestName);
		clickOnCheckInBtn();
		matchUserName(guestName);

	}

	public void matchResNumandCheckIn(String guestName) {

		WebElement table = driver.findElement(By
				.id("arrivingAccommodationsDataTable1"));
		// Now get all the TR elements from the table
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		int rows = allRows.size();
		for (int row = 1; row < rows; row++) {

			String actual = txtAttivals(row, 4);
			if (actual.equalsIgnoreCase(guestName)) {
				// Click on Ckeck Box
				toggleCheckBox(row);
				btnCheckIn.syncVisible(driver, 80, false);
				break;
			}
		}

	}

	public void matchUserName(String guestName) {

		WebElement table = driver.findElement(By
				.id("arrivingAccommodationsDataTable1"));
		// Now get all the TR elements from the table
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		int rows = allRows.size();

		for (int row = 1; row < rows; row++) {
			String actual = driver
					.findElement(
							By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["
									+ row + "]/td[4]")).getText().trim();
			if (actual.equalsIgnoreCase(guestName)) {

				Assert.assertEquals(false, true, "Expected Value " + guestName
						+ " is  Matched with  Actual [" + actual + "]");

			}
		}

	}

	public void enterFilterSearch_ArrivalTab(String filter) {
		txtFilter.highlight(driver);
		txtFilter.safeSet(filter);
		Sleeper.sleep(4000);
	}

	public void validateArrivalTabRecords_AndSelect(String ResNumber) {

		WebElement table = driver.findElement(By
				.id("arrivingAccommodationsDataTable1"));

		// Now get all the TR elements from the table
		List<WebElement> allRows = table.findElements(By.tagName("tr"));

		for (int row = 0; row < allRows.size(); row++) {
			if (allRows.get(row).getText().trim().contains(ResNumber)) {
				System.out.println("actual row size " + row);
				toggleCheckBox(row);
				break;
			}
		}

	}

	public void notifications(String notification_option) {

		// This method handles the notification section under Arrival tab
		switch (notification_option) {

		case "OFF":
			// Clicking on notifications
			pageLoaded(btnNotificationsOption);
			btnNotificationsOption.click();

			// Clicked on off notifications
			pageLoaded(btnNotificationsOption_off);
			btnNotificationsOption_off.jsClick(driver);

			// Clicking on Yes
			pageLoaded(btnYes_notificationOff);
			btnYes_notificationOff.click();

			break;

		case "ON":
			// Clicking on notifications
			pageLoaded(btnNotificationsOption);
			btnNotificationsOption.syncVisible(driver);
			btnNotificationsOption.click();

			// Clicked on off notifications
			pageLoaded(btnNotificationsOption_on);
			btnNotificationsOption_on.jsClick(driver);

			// Clicking on Yes
			pageLoaded(btnYes_notificationOn);
			btnYes_notificationOn.click();
			break;

		default:
			System.out.println("No Option Provided!!");
			break;
		}

	}

	public void verify_Notification(String notification_option) {
		// Reworking the location of the Room Read Notification image and
		// subsequent validation
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		WebElement element = driver.findElement(By.xpath("//img[contains(@src,'onNotification') or contains(@src,'offNotification')]"));
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
		String[] srcParts = element.getAttribute("src").split("/");
		String imageType = srcParts[srcParts.length-1].split("Notification.png")[0];
		TestReporter.assertEquals(imageType.toUpperCase(),
				notification_option.toUpperCase(), "The actual notification ["
						+ imageType.toUpperCase()
						+ "] was note that which was expected ["
						+ notification_option + "]");
	}

	// -----------------------------------------

	public void performVerifyFilter() {
		// Define an array of search values, obtained from the first row in the
		// table
		String[] getFilterInputs = getsearchInputs();
		System.out.println("1D Array Search inputs count : "
				+ getFilterInputs.length);

		// Use the array of search value, obtained from the first row in the
		// table, to determine the number of reservation expected to meet the
		// search criteria
		String[][] valueCount = getsearchCounts(getFilterInputs);
		System.out.println("2D Array Value Count : " + valueCount.length);

		int filterInputCounter = 0;
		for (String input : getFilterInputs) {
			if (!input.isEmpty()) {
				System.out.println(input);

				// Verify Filter
				txtFilter.safeSet(input);
				// Define the number of actual records
				int actualRecords = getRecords();
				// define the number of expected records
				int expectedRecords = Integer
						.parseInt(valueCount[filterInputCounter][1]);
				System.out.println(actualRecords + " : " + expectedRecords);
				System.out.println(actualRecords
						+ "  Records for search input - " + input);
				Assert.assertEquals(
						actualRecords,
						expectedRecords,
						"The expected number of records ["
								+ String.valueOf(expectedRecords)
								+ "] did not match the actual number of records ["
								+ String.valueOf(actualRecords) + "].");
			}

			filterInputCounter++;
		}

	}

	// ---------------------------------
	public String[][] getsearchCounts(String[] values) {

		String[][] allCells = null;
		// Define an array to hold the values for all cells
		String[][] valueCounts = new String[values.length][2];

		String Xpath = ".//*[@id='arrivingAccommodationsDataTable1']/tbody";
		WebElement Webtable = driver.findElement(By.xpath(Xpath));

		List<WebElement> TotalRowCount = Webtable
				.findElements(By.tagName("tr"));
		int NoofRecords_row = TotalRowCount.size();
		System.out.println("no of Rows : " + NoofRecords_row);

		// Determine the number of columns in each row
		List<WebElement> TotalColCount = Webtable.findElements(By
				.xpath("tr[1]/td"));
		int NoofRecords_col = TotalColCount.size();
		System.out.println("no of coloumns : " + NoofRecords_col);

		// Define the dimensions of the array dynamically
		allCells = new String[NoofRecords_row][NoofRecords_col];

		System.out.println("Grabbing all cell values.");
		// Iterate through all rows in the table
		for (int row = 1; row <= NoofRecords_row; row++) {
			// Iterate through each column
			for (int col = 1; col <= NoofRecords_col; col++) {

				List<WebElement> cellData = driver.findElements(By.xpath(Xpath
						+ "/tr[" + row + "]/td[" + col + "]"));
				// Inspect each cell
				for (WebElement input : cellData) {
					// Add the cell data to the array
					// if(!input.getText().isEmpty()&& (input.getText()!=null)){
					if (input.getText() != null) {
						allCells[row - 1][col - 1] = input.getText();

						// System.out.println("cell Data : "+allCells[row-1][col-1]);
					}
				}

			}

		}

		// Using the predefined array that contains the values found in the
		// first
		// row, determine the expected number of reservations that have the same
		// value as the first row
		// Define a counter to track the number of matches
		int counter;
		// Iterate through each value in the values array
		for (int val = 0; val < values.length; val++) {
			// Reset the counter
			counter = 0;
			// Iterate through each row in the table
			for (int row = 0; row < NoofRecords_row; row++) {
				// Iterate through each column in the table
				for (int col = 0; col < allCells[row].length; col++) {
					// If a cell value matches the value from the first row,
					// increment the counter
					if (allCells[row][col].trim().equals(values[val].trim())) {
						// System.out.println("val: " + val);
						// System.out.println("row: " + row);
						// System.out.println("col: " + col);
						System.out.println(allCells[row][col] + " : "
								+ values[val]);
						counter++;
					}
				}
			}
			// Assign the search value
			valueCounts[val][0] = values[val];
			// Assign the count value
			valueCounts[val][1] = String.valueOf(counter);
		}

		return valueCounts;
	}

	// ---------------------------------------

	/**
	 * 
	 * @summary Method to get the records of MannageArrivals Table Filter inputs
	 *          of First Row
	 * @version Created 11/3/2015
	 * @author Lalitha Banda
	 * @param NA
	 * @throws NA
	 * @return String[]
	 */
	public String[] getsearchInputs() {
		String value = "";
		String[] ArrayofValues = null;

		String Xpath = ".//*[@id='arrivingAccommodationsDataTable1']/tbody";
		for (int row = 1; row <= 1;) {
			for (int col = 1; col <= 16; col++) {
				List<WebElement> cellData = driver.findElements(By.xpath(Xpath
						+ "/tr[" + row + "]/td[" + col + "]"));
				for (WebElement input : cellData) {
					if (!input.getText().isEmpty() && input.getText() != null) {
						// System.out.println(input.getText().trim());
						value = value + input.getText().trim() + ";";
					}
				}

			}

			break;
		}

		ArrayofValues = value.split(";");

		return ArrayofValues;
	}

	// ---------------------------------
	/**
	 * 
	 * @summary Method to get the records of MannageArrivals Table
	 * @version Created 11/2/2015
	 * @author Lalitha Banda
	 * @param NA
	 * @throws NA
	 * @return Integer
	 */

	public int getRecords() {

		int ReturnValue = 0;

		String Xpath = ".//*[@id='arrivingAccommodationsDataTable1']/tbody";
		WebElement Webtable = driver.findElement(By.xpath(Xpath));

		List<WebElement> TotalRowCount = Webtable
				.findElements(By.tagName("tr"));
		int NoofRecords = TotalRowCount.size();

		for (WebElement RowData : TotalRowCount) {
			if (!RowData.getAttribute("style")
					.equalsIgnoreCase("DISPLAY: none")) {

			} else {
				NoofRecords--;
			}

			ReturnValue = NoofRecords;
		}
		return ReturnValue;
	}

	public void clickOnCheckInBtn() {
		btnCheckIn.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		tblArriving.syncVisible(driver, 60, false);
		pageLoaded(driver);
		initialize();

	}

	/**
	 * 
	 * @summary Method to validate the button enabled/disabled in the Arrivals
	 *          tab page
	 * @version Created 11/10/2015
	 * @author Marella Satish
	 * @param locator
	 *            name
	 * @throws NA
	 * @return true if disabled, false otherwise
	 */

	private boolean validateButtonsEnabledOrDisabled(Element locatorName) {

		boolean isEnabled = false;
		pageLoaded(locatorName);
		Sleeper.sleep(3000);
		if (locatorName.getAttribute("class").contains("disabled-false")) {
			isEnabled = true;
		}
		return isEnabled;
	}

	public boolean checkCheckInButtonEnableOrDisable() {
		pageLoaded(btnCheckIn);
		initialize();
		return validateButtonsEnabledOrDisabled(btnCheckIn);
	}

	public boolean checkEncodeButtonEnableOrDisable() {
		pageLoaded(btnEncode);
		initialize();
		return validateButtonsEnabledOrDisabled(btnEncode);
	}

	public boolean checkPrintCollateralButtonEnableOrDisable() {
		pageLoaded(btnPrintCollateral);
		initialize();
		return validateButtonsEnabledOrDisabled(btnPrintCollateral);
	}

	public boolean checkNotificationsButtonEnableOrDisable() {
		pageLoaded(btnNotifications);
		initialize();
		return validateButtonsEnabledOrDisabled(btnNotifications);
	}

	public boolean checkHideSelectedButtonEnableOrDisable() {
		pageLoaded(btnHideSelected);
		initialize();
		return validateButtonsEnabledOrDisabled(btnHideSelected);
	}

	/**
	 * 
	 * @summary Method to validate the records and selecting first record in the
	 *          Arrivals tab page
	 * @version Created 11/10/2015
	 * @author Marella Satish
	 * @param NA
	 * @throws NA
	 * @return NA
	 */
	public void validateRecordsAndSelect() {

		WebElement table = driver.findElement(By
				.id("arrivingAccommodationsDataTable1"));

		// Now get all the TR elements from the table
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		int CheckRow = 1;
		int rows = allRows.size();
		System.out.println(rows);
		if (rows > 1) {
			toggleCheckBox(CheckRow);
		} else {
			TestReporter.logStep("No records found to select");
		}

	}
	
	/**
	 * 
	 * @summary Method to select Resort Location 
	 * @version Created 11/12/2015
	 * @author  Atmakuri Venkatesh
	 * @param   Resort Location
	 * @throws  NA
	 * @return  NA
	 */


	public void clickResortResevationType(String scenario){
		datatable.setVirtualtablePage("ManageArrival");
	    datatable.setVirtualtableScenario(scenario);
	   pageLoaded(eleArrivalRadioFilter);
	   initialize();
		resortRadioGroup = eleArrivalRadioFilter.findElements(By.tagName("input"));
		 
		 // This will tell you the number of RadioButtons are present
		 
		  iSize = resortRadioGroup.size();
		  
		  System.out.println("Radio Filter Count : "+iSize);
		 
		  
		 // Start the loop from first Radio Button to last Radio Button
		 
		 for(int i=0; i < iSize ; i++ ){
		 
			 // Store the Radio Button name to the string variable, using 'Value' attribute
		 
			 resortLocationValue = ((WebElement) resortRadioGroup.get(i)).getAttribute("value");
			 // Select the Radio Button it the value of the Radio Button is same what you are looking for
		 
			 if (resortLocationValue.equalsIgnoreCase(datatable.getDataParameter("ReservationType"))){
		 
				 ((WebElement) resortRadioGroup.get(i)).click();
		 
				 // This will take the execution out of for loop
		 
				 break;
		 
				 }
		 
			}
	}

		/**
		 * 
		 * @summary Method to validate the DTR records in the Arrivals tab page
		 * @version Created 11/10/2015
		 * @author Atmakuri Venkatesh
		 * @param  Records count before filter
		 * @throws NA
		 * @return NA
		 */
		public void validateFilterRecords(int recordsBeforeFilter)
		{
			pageLoaded();
			initialize();
		    int actualRows = getRecords();
		    System.out.println("Expected Records : "+recordsBeforeFilter);
		    System.out.println("Actual Records : "+actualRows);
		    TestReporter.assertEquals(getExpectedRowsByFilterType(), actualRows, "The expected number of rows ["+recordsBeforeFilter+"] did not match the actual number of rows ["+actualRows+"].");
			TestReporter.logStep("No Records Found in Arrivals tab page");
			
		}

		
		private int getExpectedRowsByFilterType(){
			int rows = 0;
			//Grab the webtable containing all reservations
			String Xpath = ".//*[@id='arrivingAccommodationsDataTable1']/tbody";
			WebElement Webtable = driver.findElement(By.xpath(Xpath));

			//Grab all of the rows
			List<WebElement> TotalRowCount = Webtable.findElements(By.tagName("tr"));
			int NoofRecords = TotalRowCount.size();
			
			//Iterate through each row and determine if the reservation type (OLCI/DTR) is present
			for(int row = 1; row < NoofRecords; row++){
				//System.out.println("Reservation Type"+datatable.getDataParameter("DtrReservationType"));
				if(Webtable.findElement(By.xpath("tr["+row+"]/td[10]")).getText().equalsIgnoreCase(datatable.getDataParameter("ReservationType"))){
					rows++;
				}
			}
			TestReporter.log("Expected number of reservations: " + rows);
			return rows;
		}
		
		/**
		 * 
		 * @summary Method to validate the records based on filter in the Arrivals tab page
		 * @version Created 11/19/2015
		 * @author Atmakuri Venkatesh
		 * @param  Records count After filter
		 * @throws NA
		 * @return NA
		 */
		public void validateRecordsAfterFilter(int recordsBeforeFilter,String scenario)
		{
			pageLoaded();
			initialize();
		    int actualRows = getRecords();
		    System.out.println("Expected Records : "+recordsBeforeFilter);
		    System.out.println("Actual Records : "+actualRows);
		    TestReporter.assertEquals(getRowsByFilterType(scenario), actualRows, "The expected number of rows ["+recordsBeforeFilter+"] did not match the actual number of rows ["+actualRows+"].");
			//TestReporter.logStep("No Records Found in Arrivals tab page");
			
		}
		
		/**
		 * 
		 * @summary Method to validate the records based on filter type  in the Arrivals tab page
		 * @version Created 11/19/2015
		 * @author Atmakuri Venkatesh
		 * @param  Records count before filter
		 * @throws NA
		 * @return NA
		 */
		public int getRowsByFilterType(String scenario){
			datatable.setVirtualtablePage("ManageArrival");
		    datatable.setVirtualtableScenario(scenario);
			int rows = 0;
			//Grab the webtable containing all reservations
			String Xpath = ".//*[@id='arrivingAccommodationsDataTable1']/tbody";
			WebElement Webtable = driver.findElement(By.xpath(Xpath));

			//Grab all of the rows
			List<WebElement> TotalRowCount = Webtable.findElements(By.tagName("tr"));
			int NoofRecords = TotalRowCount.size();
			
			//Iterate through each row and determine if the reservation type (OLCI/DTR) is present
			for(int row = 1; row < NoofRecords; row++){
				//System.out.println("Reservation Type "+datatable.getDataParameter("ReservationType"));
				if(Webtable.findElement(By.xpath("tr["+row+"]/td[10]")).getText().equalsIgnoreCase(datatable.getDataParameter("ReservationType"))){
					rows++;
				}
			}
			TestReporter.log("Expected number of "+datatable.getDataParameter("ReservationType")+" reservations Records are : " + rows);
			return rows;
		}
		
		public void clickSearchGoButton(){
			clickSearchGo();
		}
		
		public void enterAndClearFilter(){
			try{new PageLoaded().isDomComplete(driver);}catch(Exception e){}
			pageLoaded(tblArrivingAccommodations);
			tblArrivingAccommodations.syncVisible(driver);
			
			String guestName = grabGuestName(1);
			int rowsBeforeFilter = getRecords();
			enterFilterSearch(guestName);
			int rowsAfterFilter = getRecords();
			TestReporter.assertTrue(rowsBeforeFilter >= rowsAfterFilter, "The number of rows before the filter ["+rowsBeforeFilter+"] is less than the number of rows after the filter ["+rowsAfterFilter+"].");
			clickClearSelected();
			int rowsAfterClearSelected = getRecords();
			TestReporter.assertTrue(rowsAfterClearSelected >= rowsAfterFilter, "The number of rows before clearing the filter ["+rowsAfterFilter+"] is greater than the number of rows after clearing the filter ["+rowsAfterClearSelected+"].");
			TestReporter.assertEquals(rowsAfterClearSelected, rowsBeforeFilter, "The number of rows before setting the filter ["+rowsBeforeFilter+"] does not equal the number of rows after clearing the filter ["+rowsAfterClearSelected+"].");
		}
		
		private void clickClearSelected(){
			pageLoaded(btnClearSelected);
			btnClearSelected.syncVisible(driver);
			btnClearSelected.jsClick(driver);
		}
		
		private String grabGuestName(String rowNum){
			int counter = 0;
			List<WebElement> rows = tblArrivingAccommodations.findElements(By.xpath("tbody/tr"));
			for(WebElement row : rows){
				if(String.valueOf(counter + 1).equalsIgnoreCase(rowNum)){
					return row.findElement(By.xpath("td[4]/a")).getText();
				}else{
					counter++;
				}
			}
			return null;
		}
		
		private String grabGuestName(int rowNum){
			return grabGuestName(String.valueOf(rowNum));
		}
}
