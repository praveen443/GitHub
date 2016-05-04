package apps.dreams.GroupSearchWindow;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.dreams.PleaseWait.PleaseWait;
import core.FrameHandler;
import core.WebDriverSetup;
import core.WindowHandler;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import selenium.common.Constants;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;
import utils.date.DateTimeConversion;

/**
 * @Summary: Contains the methods & objects for the Dreams UI
 * @Precondition: N/A
 * @Author: Venkatesh Atmakuri
 * @Version: Created 02/01/2016
 * @Return: N/A
 */
public class GroupProfileSearch {

	// ****************************
	// *** Content Frame Fields ***
	// ****************************
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	// ******************************
	// *** Content Frame Elements ***
	// ******************************

	@FindBy(id = "b_AddAutoReduce")
	private Button btnNew;

	@FindBy(id = "b_AddAutoReduce")
	private Button btnEdit;

	@FindBy(id = "LkUp1But")
	private Button btnSearch;

	@FindBy(name = "criteria.groupCode")
	private Textbox txtGrpCode;

	@FindBy(xpath = "html/body/form/div/table/tbody")
	private Element eleGrpSearchResultTable;

	// Textbox GroupName
	@FindBy(name = "criteria.groupName")
	private Textbox txtGroupName;

	// Textbox DateRangeStart
	@FindBy(name = "criteria.startDateAsString")
	private Textbox txtDateRangeStart;

	// Textbox DateRangeEnd
	@FindBy(name = "criteria.endDateAsString")
	private Textbox txtDateRangeEnd;

	// Textbox GroupAliasName
	@FindBy(name = "criteria.aliasName")
	private Textbox txtGroupAliasName;

	// Textbox TravelAgencyName
	@FindBy(name = "criteria.agentName")
	private Textbox txtTravelAgencyName;

	// Textbox AgencyId
	@FindBy(name = "criteria.agencyId")
	private Textbox txtAgencyId;

	// Listbox GroupStatus
	@FindBy(name = "criteria.groupStageAsString")
	private Listbox lstGroupStatus;

	// Textbox AccountName
	@FindBy(name = "criteria.accountName")
	private Textbox txtAccountName;

	// Textbox SalesMgrName
	@FindBy(name = "criteria.salesMgrName")
	private Textbox txtSalesMgrName;

	// Textbox ServiceMgrName
	@FindBy(name = "criteria.serviceMgrName")
	private Textbox txtServiceMgrName;

	// Listbox HomeHostel
	@FindBy(name = "criteria.homeHotelAsInt")
	private Listbox lstHomeHostel;

	// Checkbox ServiceMgrName
	@FindBy(name = "criteria.wholesaler")
	private Checkbox chkWholesaler;

	// listBox Role for Contact Information
	@FindBy(name = "contactFormUtilList.displayableContacts[0].groupContactRoleTO.role")
	private Listbox lstContactRole;

	@FindBy(id = "errorStr")
	private Element eleErrorMsg;

	@FindBy(name = "b_yes")
	private Button btnAlertOk;

	// *********************
	// ** Build page area **
	// *********************

	/**
	 * @summary Constructor to initialize the window
	 * @version Created 09/26/2014
	 * @author Waightstill W Avery
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public GroupProfileSearch(WebDriver driver) {
		this.driver = driver;
		try{
			FrameHandler.findAndSwitchToFrame(driver, "contentFrame");
			ElementFactory.initElements(driver, this);
		}catch(Exception e){
			Sleeper.sleep(2000);
			WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
			WindowHandler.waitUntilWindowExistsTitleContains(driver, "Disney Reservation");
			boolean pageLoaded = false;
			int count = 0;
			int maxTries = (int)Constants.ELEMENT_TIMEOUT;
			do{
				try{
					new PageLoaded().isDomComplete(driver);
				}catch(NoSuchWindowException nswe){
					Sleeper.sleep(1000);
					count++;
				}
			}while(!pageLoaded && count <= maxTries);
			TestReporter.assertTrue(pageLoaded, "The Group Profile Search page did not load.");
			FrameHandler.findAndSwitchToFrame(driver, "contentFrame");
			ElementFactory.initElements(driver, this);
		}
		datatable.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnNew);
	}

	public boolean isPageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnSearch);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				element);
	}

	public GroupProfileSearch initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	// *****************************************
	// *** Content Frame Interactions ***
	// *****************************************

	public void clickNew() {
		pageLoaded(btnNew);
		btnNew.syncVisible(driver);
		btnNew.highlight(driver);
		btnNew.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	/**
	 * @summary Method to enter group code
	 * @version Created 02/05/2016
	 * @author Venkatesh Atmakuri
	 * @param Scenario
	 * @throws NA
	 * @return NA
	 */
	public void enterGroupCode(String groupID) {
		pageLoaded(txtGrpCode);
		txtGrpCode.safeSet(groupID);
	}

	public void clickSearch() {
		btnSearch.click();
		PleaseWait.WaitForPleaseWait(driver);
	}

	/**
	 * @summary Method to validate group search result
	 * @version Created 02/08/2016
	 * @author Venkatesh Atmakuri
	 * @param Scenario
	 * @throws NA
	 * @return NA
	 */
	public void validateGroupSearchResult(String groupID) {
		try {
			FrameHandler.findAndSwitchToFrame(driver, "contentFrame");
			Element groupLink = null;
			List<WebElement> links = driver.findElements(By
					.xpath("//a[text()='" + groupID + "']"));
			TestReporter.assertGreaterThanZero(links.size());
			boolean found = false;
			for (WebElement link : links) {
				Element e = new ElementImpl(link);
				if (e.syncVisible(driver, 1, false)) {
					groupLink = e;
					found = true;
					break;
				}
			}
			TestReporter.assertTrue(found,
					"No link was found for group number [" + groupID + "].");
			groupLink.jsClick(driver);
			PleaseWait.WaitForPleaseWait(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// List<WebElement> linkelements =
		// eleGrpSearchResultTable.findElements(By.tagName("a"));
		// System.out.println("Group Search Result Count : "+linkelements.size());
		// if(linkelements.size()>0)
		// {
		// for(WebElement linkgrpResult : linkelements)
		// {
		// Element lnkElement = new ElementImpl(linkgrpResult);
		// if(lnkElement.syncVisible(driver, 1, false))
		// {
		// lnkElement.highlight(driver);
		// TestReporter.assertTrue(groupID.contentEquals(lnkElement.getText().trim()),
		// "Expected Group ID [ "+lnkElement.getText().trim()+" ] found in Search reasult");
		// lnkElement.jsClick(driver);
		// break;
		// }
		// }
		// }
	}

	private void clickGroupNumberLink(String groupNumber) {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		Element groupLink = null;
		;
		List<WebElement> links = driver.findElements(By.linkText(groupNumber));
		boolean found = false;
		for (WebElement link : links) {
			Element e = new ElementImpl(link);
			if (e.syncVisible(driver, 1, false)) {
				groupLink = e;
				found = true;
				break;
			}
		}
		TestReporter.assertTrue(found, "No link was found for group number ["
				+ groupNumber + "].");
		groupLink.jsClick(driver);
		driver.manage()
				.timeouts()
				.implicitlyWait(WebDriverSetup.getDefaultTestTimeout(),
						TimeUnit.SECONDS);
	}

	/**
	 * @summary Method to validate error message - Group Search Profile Page
	 * @version Created 02/12/2016
	 * @author Venkatesh Atmakuri
	 * @param Scenario
	 * @throws NA
	 * @return NA
	 */
	public void validateErrorMessage(String scenario) {

		datatable.setVirtualtablePage("ErrorMessages");
		datatable.setVirtualtableScenario(scenario);
//System.out.println();
		String expectedError = datatable.getDataParameter("Message");
		String errorLocation = datatable.getDataParameter("HeaderError");

		if (errorLocation.equalsIgnoreCase("true")) {
			pageLoaded();
			WebElement errorElement = driver.findElement(By
					.xpath("//*[@class = 'errorMsg']"));
			Element error = new ElementImpl(errorElement);
			error.highlight(driver);

			String errorText[];
			errorText = error.getText().split("-");
			TestReporter.log(errorText[0] + "-" + errorText[1]);
			TestReporter.log(expectedError);
			TestReporter.assertTrue((expectedError.trim()
					.contains((errorText[0] + "-" + errorText[1]).trim())),
					"Error displayed as expected");
		} else {
			if ((WindowHandler.waitUntilNumberOfWindowsAre(driver, 2))
					&& (WindowHandler.waitUntilWindowExistsTitleContains(
							driver, "Alert"))) {
				String getAlertMessage = eleErrorMsg.getText();
				TestReporter.log("Alert Message : " + getAlertMessage);
				TestReporter.assertEquals(expectedError, getAlertMessage,
						"The expected message" + "[ " + expectedError
								+ " ] is not same as actual message[ "
								+ getAlertMessage + " ]");
				btnAlertOk.highlight(driver);
				btnAlertOk.click();
				TestReporter.log("Alert Handled");
				WindowHandler.waitUntilWindowExistsTitleContains(driver,
						"Disney Reservation Entry and Management System");
			}
		}
	}

	/**
	 * @summary Method to set Dates Start and End Ranges based on dayto and
	 *          number of nights to stay
	 * @version Created 02/22/2016
	 * @author Praveen Namburi
	 * @param scenarios
	 * @return NA
	 */
	public void setDatesStartAndEndRange(String daysOut, String numberNights) {
		String dateRangeStart = "";
		String dateRangeEnd = "";
		try{
			dateRangeStart = DateTimeConversion.getDaysOut(daysOut,
					"MM/dd/yyyy");
		}catch(NumberFormatException nfe){
			dateRangeStart = daysOut;
		}
		try{
			dateRangeEnd = DateTimeConversion.getDaysOut(numberNights,
					"MM/dd/yyyy");
		}catch(NumberFormatException nfe){
			dateRangeEnd = numberNights;
		}
		TestReporter.log("Arrival Date : " + dateRangeStart);
		TestReporter.log("Depart Date : " + dateRangeEnd);

		txtDateRangeStart.highlight(driver);
		txtDateRangeStart.safeSet(dateRangeStart);

		txtDateRangeEnd.highlight(driver);
		txtDateRangeEnd.safeSet(dateRangeEnd);
	}

	/**
	 * @summary: Method to enter Group profile search and click on button Search
	 *           or new based on the condition from datatable.
	 * @author : praveen Namburi
	 * @version: Created 2-22-2016
	 * @param scenario
	 * @param groupID
	 */
	public void enterGroupProfileAndSearch(String scenario, String groupID) {
		datatable.setVirtualtablePage("GroupProfileSearch");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded(btnSearch);

		// String groupNumber = Randomness.randomNumber(10);
		String groupName = datatable.getDataParameter("GroupName") + groupID;
		// String groupName = datatable.getDataParameter("GroupName");
		String daysOut = datatable.getDataParameter("DaysOut");
		String numberNights = datatable.getDataParameter("NumberOfNights");
		String groupAliasName = datatable.getDataParameter("GroupAliasName");
		String travelAgencyName = datatable
				.getDataParameter("TravelAgencyName");
		String agencyId = datatable.getDataParameter("AgencyId");
		String groupStatus = datatable.getDataParameter("GroupStatus");
		String accountName = datatable.getDataParameter("AccountName");
		String salesMgrName = datatable.getDataParameter("SalesMgrName");
		String serviceMgrName = datatable.getDataParameter("ServiceMgrName");
		String homeHostel = datatable.getDataParameter("HomeHostel");
		String setWholeSaler = datatable.getDataParameter("WholeSaler");
		String searchButton = datatable.getDataParameter("SearchButton");
		String negative = datatable.getDataParameter("Negative");
		String tabForError = datatable.getDataParameter("TabForError");

		txtGrpCode.safeSet(groupID);
		txtGroupName.safeSet(groupName);
		setDatesStartAndEndRange(daysOut, numberNights);
		txtGroupAliasName.safeSet(groupAliasName);
		txtTravelAgencyName.safeSet(travelAgencyName);
		txtAgencyId.safeSet(agencyId);
		lstGroupStatus.select(groupStatus);
		txtAccountName.safeSet(accountName);
		txtSalesMgrName.safeSet(salesMgrName);
		txtServiceMgrName.safeSet(serviceMgrName);
		lstHomeHostel.select(homeHostel);

		if (setWholeSaler.equalsIgnoreCase("true")) {
			chkWholesaler.click();
		}

		if (searchButton.equalsIgnoreCase("true")) {
			// Click on Button Search.
			clickSearch();

		} else if (tabForError.equalsIgnoreCase("true")) {

		} else {
			// Click on Button New.
			clickNew();
		}

		if (negative.equalsIgnoreCase("true")) {
			validateErrorMessage(scenario);
		}
	}

	/**
	 * @summary Method to search group profile using groupID
	 * @version Created 02/25/2016
	 * @author Lalitha Banda
	 * @param Scenario
	 *            ,GroupID
	 * @throws NA
	 * @return NA
	 */

	public void searchGroupProfile(String scenario, String GroupNumber) {
		enterGroupProfileAndSearch(scenario, GroupNumber);
		pageLoaded();
		clickGroupNumberLink(GroupNumber.trim());
	}

	// Providing Group contact Role under edit section
	public void selectRoleForGroupProfileContact(String scenario) {
		datatable.setVirtualtablePage("GroupInformationDetails");
		datatable.setVirtualtableScenario(scenario);

		// Providing Contact Role Information
		String opportunityType = datatable.getDataParameter("OpportunityType");
		lstContactRole.select(opportunityType);

	}
}

