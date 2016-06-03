package apps.dreams.travelPlanSearch;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import apps.dreams.PleaseWait.PleaseWait;
import core.FrameHandler;
import core.WindowHandler;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Link;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.CheckboxImpl;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import selenium.common.Constants;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

/**
 * @summary Contains the methods & objects for the Dreams UI travel plan search
 *          window
 * @version Created 10/28/2014
 * @author Jessica Marshall
 */
public class TravelPlanSearchPageMagick {
	// ****************************
	// *** Content Frame Fields ***
	// ****************************
	private WebDriver driver;
	private int loopCounter = 0;
	private Datatable datatable = new Datatable();

	// ******************************
	// *** Content Frame Elements ***
	// ******************************

	@FindBy(name = "Search")
	private Button btnSearch;

	@FindBy(name = "Clear")
	private Button btnClear;

	@FindBy(name = "travelPlanSegmentId")
	private Textbox txtReservationNum;

	@FindBy(name = "externalRefNumber")
	private Checkbox chkExternalRefNum;

	@FindBy(name = "travelPlanViewTO.travelPeriodStartDate")
	private Textbox txtArrivalDate;

	@FindBy(name = "travelPlanViewTO.travelPeriodEndDate")
	private Textbox txtDeptDate;

	@FindBy(name = "dateMatchFlag")
	private Checkbox chkDateExactMatch;

	@FindBy(name = "travelPlanViewTO.guestLastName")
	private Textbox txtLastName;

	@FindBy(name = "travelPlanViewTO.guestFirstName")
	private Textbox txtFirstName;

	@FindBy(name = "primaryGuestOnly")
	private Checkbox chkPrimaryGuest;

	@FindBy(name = "exactMatch")
	private Checkbox chkGuestExactMatch;

	@FindBy(name = "postalCode")
	private Textbox txtZipCode;

	@FindBy(id = "postalCode")
	private Textbox txtZipcode;

	@FindBy(id = "lastName")
	private Textbox txtLastname;

	@FindBy(id = "firstName")
	private Textbox txtFirstname;

	@FindBy(id = "resvNo")
	private Textbox txtReservationnum;

	@FindBy(name = "travelPlanViewTO.travelStatusForDisplay")
	private Listbox lstReservationStatus;

	@FindBy(name = "travelPlanViewTO.roomType.accommodationFacilityTO.resortCode")
	private Listbox lstResortName;

	@FindBy(name = "travelPlanViewTO.viplevelForDisplay")
	private Listbox lstVIPlevel;

	@FindBy(name = "travelPlanViewTO.childId")
	private Textbox txtChildID;

	@FindBy(name = "travelPlanViewTO.group.groupDescription")
	private Textbox txtGroupName;

	@FindBy(name = "travelPlanViewTO.group.groupCode")
	private Textbox txtGroupNum;

	@FindBy(name = "travelPlanViewTO.teamName")
	private Textbox txtTeamName;

	@FindBy(name = "travelPlanViewTO.gatheringName")
	private Textbox txtGatheringName;

	@FindBy(name = "travelPlanViewTO.gatheringId")
	private Textbox txtGatheringID;

	@FindBy(name = "travelPlanViewTO.agencyId")
	private Textbox txtAgencyID;

	// Search results table
	@FindBy(xpath = "//*[@id='firstLayer']/table[3]")
	private Webtable tblSearchResults;

	// Mass modify button in search results
	@FindBy(name = "b_modify")
	private Button btnMassModify;

	// Select All checkbox to select all reservations from teh search
	@FindBy(id = "b_SelectAll")
	private Checkbox chkSelectAll;

	@FindBy(partialLinkText = "MagicBand Preferences")
	private Link MagicBand_linkTest;

	@FindBy(id = "selectAllMB")
	private Checkbox selectOptout;

	@FindBy(name = "guestIndex0")
	private Checkbox GuestIndexZero;

	@FindBy(xpath = "html/body/table/tbody/tr[4]/td/a/b")
	private Link lnkTravelPlanSearch;

	// *********************
	// ** Build page area **
	// *********************

	public TravelPlanSearchPageMagick(WebDriver driver) {
		this.driver = driver;
		FrameHandler.findAndSwitchToFrame(driver, "contentFrame");
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnSearch);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				element);
	}

	public TravelPlanSearchPageMagick initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	// *****************************************
	// *** Content Frame Interactions ***
	// *****************************************

	public void clickSearch() {
		pageLoaded(btnSearch);
		initialize();
		btnSearch.syncVisible(driver);
		btnSearch.click();
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void clickClear() {
		pageLoaded(btnClear);
		initialize();
		btnClear.syncVisible(driver);
		btnClear.click();
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void clickMassModify() {
		pageLoaded(btnMassModify);
		initialize();
		btnMassModify.syncVisible(driver);
		btnMassModify.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void searchByReservationNumGuest(String reservationNum,String guestlastname, String guestfirstname) {
		pageLoaded(txtReservationNum);
		initialize();
		PleaseWait.WaitForPleaseWait(driver);
		txtReservationNum.syncVisible(driver);
		txtReservationNum.set(reservationNum);
		txtFirstName.set(guestfirstname);
		txtLastName.set(guestlastname);

		clickSearch();

	}

	public void clickonResevationNumber() {
		Sleeper.sleep(5000);
		Element lnkName1 = new ElementImpl(driver.findElement(By
				.id("firstLayer")));
		lnkName1.highlight(driver);
		String linkText = "//div[@id='firstLayer']/table[3]/tbody/tr[3]/td[2]/div/a";
		Element lnkName = new ElementImpl(
				driver.findElement(By.xpath(linkText)));
		lnkName.highlight(driver);
		lnkName.jsClick(driver);
		Sleeper.sleep(5000);
	}

	public void MagicBand_click() {
		pageLoaded(MagicBand_linkTest);
		initialize();
		MagicBand_linkTest.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	/**
	 * @summary method for the Dreams UI travel plan search entering
	 *          reservation,lastname,1stname and zipcode
	 * @version Created 03/03/2016
	 * @author Pawan Chinagudaba
	 * @param Datatable
	 *            Scenario
	 */

	public void searchReservationwithZpcode(String scenario,String reservationNumber) {
		datatable.setVirtualtablePage("PrimaryGuest");
		datatable.setVirtualtableScenario(scenario);

		String guestfirstname = datatable.getDataParameter("FirstName");
		String guestlastname = datatable.getDataParameter("LastName");
		String guestZipcode = datatable.getDataParameter("Zip");

		pageLoaded(txtReservationNum);
		initialize();
		txtReservationNum.syncVisible(driver);
		txtReservationNum.set(reservationNumber);
		txtZipcode.set(guestZipcode);
		txtFirstname.set(guestfirstname);
		txtLastname.set(guestlastname);

		clickSearch();
		PleaseWait.WaitForPleaseWait(driver);
		try{
			WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
			WindowHandler.waitUntilWindowExistsTitleContains(driver, "Disney Reservation");
		}catch(Exception e){
			Sleeper.sleep(3000);
		}
		clickReservationFromSearchResults(reservationNumber);
	}

	public void searchReservation(String reservationNumber) {
		pageLoaded(txtReservationNum);
		txtReservationNum.syncVisible(driver);
		txtReservationNum.set(reservationNumber);

		clickSearch();
		PleaseWait.WaitForPleaseWait(driver);
		clickReservationFromSearchResults(reservationNumber);
	}

	public void selectoptout() {
		pageLoaded(selectOptout);
		initialize();
		selectOptout.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void selectoptoutOptthreeofone() {
		pageLoaded(GuestIndexZero);
		initialize();
		Sleeper.sleep(5000);

		GuestIndexZero.click();
		Sleeper.sleep(5000);
		PleaseWait.WaitForPleaseWait(driver);
	}

	/**
	 * @summary Does a search by reservation number. If the boolean value of
	 *          externalRef is true, then will check the checkbox. Arrival date
	 *          & departure date are required fields so must enter values in
	 *          those.
	 * @version Created 10/28/2014
	 * @author Jessica Marshall
	 * @param reservationNum
	 *            , externalREf, arrivalDate, deptDate
	 * @throws NA
	 * @return NA
	 */
	public void searchByReservationNum(String reservationNum,
			boolean externalRef, String arrivalDate, String deptDate) {
		pageLoaded(txtReservationNum);
		initialize();
		txtReservationNum.syncVisible(driver);
		txtReservationNum.set(reservationNum);
		checkExternalRefNum(externalRef);
		txtArrivalDate.set(arrivalDate);
		txtDeptDate.set(deptDate);
		clickSearch();
	}

	/**
	 * @summary Checks the external reference checkbox if the boolean paramter
	 *          is true
	 * @version Created 10/28/2014
	 * @author Jessica Marshall
	 * @param externalRef
	 * @throws NA
	 * @return NA
	 */
	public void checkExternalRefNum(boolean externalRef) {
		if (externalRef) {
			chkExternalRefNum.check();
		}
	}

	public boolean clickReservationFromSearchResults(String reservationNum) {
		FrameHandler.findAndSwitchToFrame(driver, "contentFrame");
		boolean pageLoaded = false;
		int counter = 0;
		do {
			try {
				pageLoaded = pageLoaded(tblSearchResults);
			} catch (Exception e) {
				Sleeper.sleep(1000);
			}
		} while (counter <= Constants.ELEMENT_TIMEOUT && !pageLoaded);
		TestReporter.assertTrue(counter <= Constants.ELEMENT_TIMEOUT
				&& pageLoaded,
				"The Search results table was not loaded after ["
						+ Constants.ELEMENT_TIMEOUT + "] attempts");

		tblSearchResults.syncVisible(driver);

		// Get a list of of all the reservation number links in the table
		List<WebElement> linkList = tblSearchResults.findElements(By
				.tagName("a"));

		// if there are not rows, then return false
		if (linkList.isEmpty())
			return false;

		for (WebElement element : linkList) {
			if (element.getText().equals(reservationNum)) {
				element.click();
				tblSearchResults.syncHidden(driver);
				// new PleaseWait().WaitForPleaseWait(driver);
				return true;
			}
		}

		return false;
	}

	public void selectAllReservations() {
		chkSelectAll.highlight(driver);
		chkSelectAll.focus(driver);
		chkSelectAll.toggle();
	}

	public boolean checkReservationFromSearchResults(String reservationNum) {
		initialize();

		tblSearchResults.syncVisible(driver);

		// Get a list of of all the rows in the table
		// List <WebElement> rowList =
		// tblSearchResults.findElements(By.tagName("tr"));

		// if there are not rows, then return false
		/*
		 * if (rowList.isEmpty()) return false;
		 * 
		 * //If the reservation is found, then check the check box next to it
		 * for (WebElement element:rowList){ //get a list of the cells List
		 * <WebElement> cellList = element.findElements(By.tagName("td")); if
		 * (!cellList.isEmpty()){ //verify the reservation number matches if
		 * (cellList.get(1).getText().equals(reservationNum)){ //if it's the
		 * right row, then check the checkbox
		 * cellList.get(0).findElement(By.cssSelector
		 * ("input[type='checkbox']")).click(); return true; } } }
		 */
		List<WebElement> we = driver.findElements(By.name("selectAllMB"));
		we.size();

		// If there are no checkboxes, return false
		if (we.size() == 0) {
			return false;
		}

		// If the reservation is found, then check the check box next to it
		for (loopCounter = 0; loopCounter < we.size(); loopCounter++) {
			Checkbox checkbox = new CheckboxImpl(we.get(loopCounter));
			if (checkbox.getAttribute("onclick").contains(reservationNum)) {
				// checkbox.highlight(driver);
				checkbox.jsToggle(driver);
				return true;
			}
		}
		Reporter.log("Reservation number: " + reservationNum
				+ " was not found in list of search results");
		return false;
	}

	public boolean massModifyAReservation(String reservationNum) {

		// in the search results, check the reservation, if its not found, then
		// return false
		if (!checkReservationFromSearchResults(reservationNum))
			return false;

		// Click the mass modify button
		clickMassModify();

		// Verify a new Lilo window is opened & swap to it.
		return WindowHandler.swapToWindowWithTimeout(driver, "Lilo", 10);

	}

	public boolean massModifyAllReservations() {
		selectAllReservations();

		// Click the mass modify button
		clickMassModify();

		// Verify a new Lilo window is opened & swap to it.
		return WindowHandler.swapToWindowWithTimeout(driver, "Lilo", 10);
	}
}

