package apps.lilo.mediaTab;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.WebDriverSetup;
import core.WindowHandler;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Label;
import core.interfaces.Link;
import core.interfaces.Webtable;
import core.interfaces.impl.*;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Regex;
import utils.Sleeper;
import utils.TestReporter;

/**
 * @summary Contains the elements and methods to interact with the LILO UI Media
 *          tab
 * @version Created 10/03/2014
 * @author Waightstill W. Avery
 */

public class MediaTab {
	// ************************
	// *** Media Tab Fields ***
	// ************************
	private int loopCounter;
	private Datatable datatable = new Datatable();
	private WebDriver driver;

	// **************************
	// *** Media Tab Elements ***
	// **************************

	@FindBy(id = "newMediaEncodeForm:encode")
	private Link lnkEncode;

	@FindBy(id = "newMediaEncodeForm:replace")
	private Link lnkReplace;

	@FindBy(id = "newMediaEncodeForm:addGuest")
	private Link lnkAddGuest;

	@FindBy(id = "newMediaEncodeForm:magicbandBtn")
	private Button btnMagicBands;

	@FindBy(id = "newMediaEncodeForm:printBtn")
	private Button btnPrint;

	@FindBy(id = "newMediaEncodeForm:refreshbtnId")
	private Link lnkRefresh;

	@FindBy(id = "newMediaEncodeForm:viewHistory")
	private Link lnkHistory;

	@FindBy(id = "newMediaEncodeForm:kttwDetails:0:media:0:mediaRowCheckBoxId")
	private Checkbox chkPrimaryguestMediaCheckbox;

	@FindBy(id = "printEncodeForm:defaultPrintEncodSubmitId")
	private Button btnEncodeSubmit;

	@FindBy(id = "printEncodeForm:defaultPrintEncodCancelId")
	private Button btnCancel;

	@FindBy(id = "printEncodeForm:encoderDetails")
	private Webtable tblEncoderDetails;

	// @FindBy(id = "newMediaEncodeForm:kttwDetails")
	@FindBy(xpath = "//table[contains(@id, 'newMediaEncodeForm:kttwDetails')]")
	private Webtable tblKttwDetails;

	@FindBy(id = "newMediaEncodeForm:kttwDetails:tb")
	private Webtable tblKttwBody;

	// Encoder popup
	@FindBy(id = "printEncodeForm:encoderDetails")
	private Element eleEncoderPage;

	// Encoder popup options
	@FindBy(xpath = "//table[@id='printEncodeForm:encoderDetails']/tbody/tr")
	private List<WebElement> eleEncoderRows;

	// Printer Encoder Submit button
	@FindBy(id = "printEncodeForm:defaultPrintEncodSubmitId")
	private Button btnPrinterEncoderSubmit;

	// Room Access table
	// @FindBy(xpath =
	// "/html/body/div[5]/div[2]/div/div[2]/table/tbody/tr[2]/td/form/table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td/table/tbody/tr/td/div/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td/div/div/table")
	@FindBy(id = "accessForm:roomAccessTabPanel")
	private Webtable tblRoomAccess;

	// Access Room Submit
	@FindBy(id = "accessForm:selectId")
	private Button btnRoomAccessSubmit;

	// Recommend button
	@FindBy(id = "manageTicketsForm:recommendTicketsButton")
	private Button btnRecommend;

	// Recommend button
	@FindBy(id = "manageTicketsForm:issueBandOnlyButtonId")
	private Button btnIssueMagicBand;

	// Manage Ticket Window
	@FindBy(id = "manageTicketsModelPanelContentDiv")
	private Element eleManageTicketWindow;

	// Manage Ticket Table
	@FindBy(id = "manageTicketsForm:accommodationTicketsListTable:tb")
	private Webtable tblManaheTicket;

	// Manage Ticket Close
	@FindBy(id = "closeManageTicketsForm:exitQuickTabButtonId1")
	private Button btnCloseManageTicket;

	// Tickets button
	@FindBy(id = "newMediaEncodeForm:ticketsButton")
	private Button btnTicket;

	// Manage Ticket Close window OK confirmation
	@FindBy(id = "confirmationPopupForm:okButtonId")
	private Button btnOk;

	// Click Celebrations
	@FindBy(id = "newMediaEncodeForm:celebrationsId")
	private Link lnkCelebrations;

	// Access Room Close
	@FindBy(id = "accessForm:close")
	private Button btnRoomAccessClose;

	// Celebrations EXIT
	@FindBy(id = "WWYCForm:Exit")
	private Element eleCelebrationsExit;

	// Celebrations EXIT Yes
	@FindBy(id = "confirmExitForm:confirm_exit_yes")
	private Element eleCelebrationsExitYes;

	//Check the check-box in the first row encode media 
	@FindBy(id="newMediaEncodeForm:kttwDetails:0:rowCheckBoxId")
	private Checkbox chkfirstrownewMediaCheckBox;
	
	//Click on the first row access link in media encode table
	@FindBy(id="newMediaEncodeForm:kttwDetails:0:editRoomAccess")
	private Link lnkFirstRowAccess;
	
	//Created object for clicking on Image - Refresh.
	@FindBy(id="refreshReservationFrm:refreshViewRes")
	private Element eleRefreshImage;

	// Issue New
	@FindBy(id = "newMediaEncodeForm:issue_new:anchor")
	private Element eleIssueNew;
	
	// Update
	@FindBy(id = "newMediaEncodeForm:update:anchor")
	private Element eleUpdate;
	
	// Encode Band popup
	@FindBy(id = "issueEncodeBandPopupTitle")
	private Element eleEncodeBandPopup;
	
	// Button close
	@FindBy(id = "buttonsInIssueBandForm:closeIssueEncodeBandButtonId2")
	private Button btnClosePopup;

	//View Coupons Link
	@FindBy(id = "newMediaEncodeForm:ticketscoupons")
	private Link lnkViewCoupons; 
	
	//Adult Count
	@FindBy(id = "newMediaEncodeForm:adultCountId")
	private Label lblAdultCount;
	
	//Valid Charge Account Icon class
		private String validChargeAccountIcon = "statusGreen";
		private String pinNotSetup = "statusYellow";

	// *********************
	// ** Build page area **
	// *********************
	/**
	 * 
	 * @summary Constructor to initialize the page
	 * @version Created 10/03/2014
	 * @author Waightstill W Avery
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public MediaTab(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, lnkEncode);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}
	
	public boolean pageLoaded(Checkbox checkbox) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, checkbox);
	}
	
	public boolean pageLoaded(Link link){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(),driver,link);
	}

	public MediaTab initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	// **************************************
	// *** Main CheckIn Page Interactions ***
	// **************************************

	public void clickEncode() {
		int count = 0;
		while (lnkEncode.syncDisabled(driver, 1, false)) {
			Sleeper.sleep(1000);
			count++;
			if (count == WebDriverSetup.getDefaultTestTimeout())
				break;
		}

		lnkEncode.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * 
	 * @summary Method to select specific media IDs
	 * @version Created 10/03/2014
	 * @author Waightstill W Avery
	 * @param scenario
	 *            - determine datatable row
	 * @param mediaIds
	 *            - media IDs to select
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void selectMedia(String scenario, String[] mediaIds) {
		datatable.setVirtualtablePage("Media");
		datatable.setVirtualtableScenario(scenario);
		initialize();
		pageLoaded();
		int loopCounter;
		int mediaIdCounter;
		int iterations = tblKttwDetails.getRowCount();
		for (loopCounter = 0; loopCounter < iterations; loopCounter++) {
			// Ensure you are working with a row that has the correct number of
			// columns for a media ID to be found
			if (tblKttwDetails.getColumnCount(driver, loopCounter) >= 3) {
				// Verify that this row has one of the IDs the need to be
				// selected; if so, select the associated checkbox
				for (mediaIdCounter = 0; mediaIdCounter < mediaIds.length; mediaIdCounter++) {
					// tblKttwDetails.highlight(driver);
					if (tblKttwDetails.getCellData(driver, loopCounter, 4).contains(mediaIds[mediaIdCounter])) {
						WebElement tableCell = tblKttwDetails.getCell(driver, loopCounter, 4);
						String mediaID = tableCell.getAttribute("id");
						String[] mediaIdSplit = mediaID.split(":");
						String guestNumber = mediaIdSplit[2];
						String guestMediaIdNumber = mediaIdSplit[4];
						String checkboxId = "newMediaEncodeForm:kttwDetails:" + guestNumber + ":media:"
								+ guestMediaIdNumber + ":mediaRowCheckBoxId";
						Checkbox kttwCheckbox = new CheckboxImpl(driver.findElement(By.id(checkboxId)));
						kttwCheckbox.jsToggle(driver);
						Sleeper.sleep(5000);

						initialize();
					}
				}
			}
		}
	}

	/**
	 * 
	 * @summary Method to select a particular number of media
	 * @version Created 10/03/2014
	 * @author Waightstill W Avery
	 * @param scenario
	 *            - determine datatable row
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void selectMediaToReplace(String scenario) {
		datatable.setVirtualtablePage("Media");
		datatable.setVirtualtableScenario(scenario);
		String selector = datatable.getDataParameter("ReplaceMedia");
		int loopCounter;
		switch (selector.toLowerCase()) {
		case "all":
		case "":
			List<WebElement> checks = driver.findElements(By.xpath("//input[@title='mediaRowCheckBox']"));
			for (loopCounter = 0; loopCounter < checks.size(); loopCounter++) {
				Checkbox nextCheckbox = new CheckboxImpl(checks.get(loopCounter));
				nextCheckbox.highlight(driver);
				nextCheckbox.jsToggle(driver);
				Sleeper.sleep(3000);
				pageLoaded();
				initialize();
				checks = driver.findElements(By.xpath("//input[@title='mediaRowCheckBox']"));
			}
			break;
		case "one":
		case "1":
			String id = "newMediaEncodeForm:kttwDetails:0:media:0:mediaRowCheckBoxId";
			Checkbox kttwCheckbox = new CheckboxImpl(driver.findElement(By.id(id)));
			kttwCheckbox.jsToggle(driver);
			break;
		default:
			break;
		}
	}

	/**
	 * 
	 * @summary Method to select a particular number of media
	 * @version Created 10/03/2014
	 * @author Waightstill W Avery
	 * @param scenario
	 *            - determine datatable row
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void selectMediaToEncode(String scenario) {
		datatable.setVirtualtablePage("Media");
		datatable.setVirtualtableScenario(scenario);
		String selector = datatable.getDataParameter("NumberOfGuestsToEncode");
		int loopCounter;
		switch (selector.toLowerCase()) {
		case "all":
		case "":
			List<WebElement> checks = driver.findElements(By.xpath("//input[@title='mediaRowCheckBox']"));
			for (loopCounter = 0; loopCounter < checks.size(); loopCounter++) {
				Checkbox nextCheckbox = new CheckboxImpl(checks.get(loopCounter));
				nextCheckbox.highlight(driver);
				nextCheckbox.jsToggle(driver);
				Sleeper.sleep(3000);
				pageLoaded();
				initialize();
				checks = driver.findElements(By.xpath("//input[@title='mediaRowCheckBox']"));
			}
			break;
		case "one":
		case "1":
			String id = "newMediaEncodeForm:kttwDetails:0:media:0:mediaRowCheckBoxId";
			Checkbox kttwCheckbox = new CheckboxImpl(driver.findElement(By.id(id)));
			kttwCheckbox.jsToggle(driver);
			break;
		case "two":
		case "2":
			List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@title='mediaRowCheckBox']"));
			for (loopCounter = 0; loopCounter < 2; loopCounter++) {
				Checkbox nextCheckbox = new CheckboxImpl(checkboxes.get(loopCounter));
				nextCheckbox.highlight(driver);
				nextCheckbox.jsToggle(driver);
				Sleeper.sleep(3000);
				pageLoaded();
				initialize();
				checkboxes = driver.findElements(By.xpath("//input[@title='mediaRowCheckBox']"));
			}
		default:
			break;
		}
	}

	public void clickReplace() {
		lnkReplace.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * 
	 * @summary Method to select the encoder with which to encode the media.
	 *          Typical encoder name is "QA Stub Encoder"
	 * @version Created 10/03/2014
	 * @author Waightstill W Avery
	 * @param scenario
	 *            - determine datatable row
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void selectEncoder(String scenario) {
		datatable.setVirtualtablePage("Media");
		datatable.setVirtualtableScenario(scenario);
		String encoder = datatable.getDataParameter("Encoder");
		for (int row = 0; row < eleEncoderRows.size(); row++) {
			List<WebElement> cell = eleEncoderRows.get(row).findElements(By.tagName("td"));
			if (cell.get(1).getText().equalsIgnoreCase(encoder)) {
				new CheckboxImpl(cell.get(0).findElement(By.tagName("input"))).jsToggle(driver);
				break;
			}
		}
	}

	/**
	 * 
	 * @summary Method to validate media status
	 * @version Created 10/03/2014
	 * @author Waightstill W Avery
	 * @param scenario
	 *            - determine datatable row
	 * @param expectedStatus
	 *            - expected media status (EX: "encoded" or "notencoded"
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void validateMediaStatus(String scenario, String expectedStatus) {
		datatable.setVirtualtablePage("Media");
		datatable.setVirtualtableScenario(scenario);
		String selector = datatable.getDataParameter("ReplaceMedia");
		String status = "";
		if (expectedStatus.equalsIgnoreCase("encoded")) {
			status = "statusGreen";
		} else if (expectedStatus.equalsIgnoreCase("notencoded")) {
			status = "statusYellow";
		}

		List<WebElement> rows = driver.findElements(By.xpath("//tr[@class='dr-subtable-firstrow']"));
		switch (selector.toLowerCase()) {
		case "all":
			for (int row = 0; row < rows.size(); row++) {
				WebElement cell = rows.get(row).findElement(By.tagName("img"));
				if (cell.getAttribute("class").toString().equalsIgnoreCase(status)) {
					TestReporter.log("Status was [ " + expectedStatus + " ] as expected.");
				} else {
					throw new RuntimeException("Status of Guest " + (row + 1) + " was not " + expectedStatus);
				}
			}
			break;
		case "one":
			String id = "newMediaEncodeForm:kttwDetails:0:media:0:iconStatus";
			WebElement cell = driver.findElement(By.id(id));
			if (cell.getAttribute("class").toString().equalsIgnoreCase(status)) {
				TestReporter.log("Status was [ " + expectedStatus + " ] as expected.");
			} else {
				throw new RuntimeException("Status of Guest 1 was not " + expectedStatus);
			}
			break;
		default:
			break;
		}
	}

	public void clickEncoderSubmit() {
		btnPrinterEncoderSubmit.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * 
	 * @summary Method to verify the Encode button is disabled
	 * @version Created 10/03/2014
	 * @author Waightstill W Avery
	 * @param NA
	 * @throws NA
	 * @return true if disabled, false otherwise
	 * 
	 */
	public boolean verifyEncodeDisabled() {
		boolean isDisabled = false;
		
		int counter = 0;
		do{
			Sleeper.sleep(1000);
			if (lnkEncode.getAttribute("class").contains("disabled-true")) {
				isDisabled = true;
				break;
			}
			counter++;
		}while(!isDisabled && counter < WebDriverSetup.getDefaultTestTimeout());

		return isDisabled;
	}

	/**
	 * 
	 * @summary Method to verify the Encode button is enabled
	 * @version Created 10/03/2014
	 * @author Waightstill W Avery
	 * @param NA
	 * @throws NA
	 * @return true if disabled, false otherwise
	 * 
	 */
	public boolean verifyEncodeEnabled() {
//		boolean isEnabled = false;
		pageLoaded(lnkEncode);
//		if (lnkEncode.getAttribute("class").contains("disabled-false")) {
//			isEnabled = true;
//		}

//		return isEnabled;
		return lnkEncode.syncEnabled(driver);
	}

	/**
	 * 
	 * @summary Method to verify the Replace button is enabled
	 * @version Created 10/03/2014
	 * @author Waightstill W Avery
	 * @param NA
	 * @throws NA
	 * @return true if disabled, false otherwise
	 * 
	 */
	public boolean verifyReplaceEnabled() {
		boolean isEnabled = false;

		if (lnkReplace.getAttribute("class").contains("disabled-false")) {
			isEnabled = true;
		}

		return isEnabled;
	}

	/**
	 * 
	 * @summary Method to verify the Replace button is disabled
	 * @version Created 10/03/2014
	 * @author Waightstill W Avery
	 * @param NA
	 * @throws NA
	 * @return true if disabled, false otherwise
	 * 
	 */
	public boolean verifyReplaceDisabled() {
		boolean isDisabled = false;

		if (lnkReplace.getAttribute("class").contains("disabled-true")) {
			isDisabled = true;
		}

		return isDisabled;
	}

	/**
	 * 
	 * @summary Method to validate that particular media IDs are inactive
	 * @version Created 10/03/2014
	 * @author Waightstill W Avery
	 * @param mediaIDs
	 *            - String list of media IDs to validate
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void validateInactiveMedia(String[] mediaIDs) {
		tblKttwDetails.getRowCount();
		int numberOfMedia = mediaIDs.length;
		int row;
		String mediaIdHtmlId = "";
		String firstIdentifier = "";
		String secondIdentifier = "";
		String[] mediaIdHtmlIdArray;
		String imageHtmlId = "";
		for (int loopCounter = 0; loopCounter < numberOfMedia; loopCounter++) {
			row = tblKttwDetails.getRowWithCellText(driver, mediaIDs[loopCounter], 4);
			Element element = new ElementImpl(tblKttwDetails.getCell(driver, row, 4));
			element.highlight(driver);
			mediaIdHtmlId = element.getAttribute("id");
			mediaIdHtmlIdArray = mediaIdHtmlId.split(":");
			firstIdentifier = mediaIdHtmlIdArray[2];
			secondIdentifier = mediaIdHtmlIdArray[4];
			imageHtmlId = "newMediaEncodeForm:kttwDetails:" + firstIdentifier + ":media:" + secondIdentifier
					+ ":iconStatus";
			element = new ElementImpl(driver.findElement(By.id(imageHtmlId)));

			Assert.assertEquals(element.getAttribute("alt").contains("Inactive"), true, "The media ID "
					+ mediaIDs[loopCounter] + " was found to be Active when it was expected to be Inactive\n");
		}
	}

	/**
	 * 
	 * @summary Method to validate that particular media IDs are active
	 * @version Created 10/03/2014
	 * @author Waightstill W Avery
	 * @param mediaIDs
	 *            - String list of media IDs to validate
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void validateActiveMedia(String[] mediaIDs) {
		tblKttwDetails.getRowCount();
		int numberOfMedia = mediaIDs.length;
		int row;
		String mediaIdHtmlId = "";
		String firstIdentifier = "";
		String secondIdentifier = "";
		String[] mediaIdHtmlIdArray;
		String imageHtmlId = "";
		for (int loopCounter = 0; loopCounter < numberOfMedia; loopCounter++) {
			row = tblKttwDetails.getRowWithCellText(driver, mediaIDs[loopCounter], 4);
			Element element = new ElementImpl(tblKttwDetails.getCell(driver, row, 4));
			mediaIdHtmlId = element.getAttribute("id");
			mediaIdHtmlIdArray = mediaIdHtmlId.split(":");
			firstIdentifier = mediaIdHtmlIdArray[2];
			secondIdentifier = mediaIdHtmlIdArray[4];
			imageHtmlId = "newMediaEncodeForm:kttwDetails:" + firstIdentifier + ":media:" + secondIdentifier
					+ ":iconStatus";
			element = new ElementImpl(driver.findElement(By.id(imageHtmlId)));

			Assert.assertNotEquals(element.getAttribute("alt").contains("Inactive"), true, "The media ID "
					+ mediaIDs[loopCounter] + " was found to be Inactive when it was expected to be Active\n");
		}
	}

	/**
	 * 
	 * @summary Method to capture and return replacement media IDs
	 * @version Created 10/03/2014
	 * @author Waightstill W Avery
	 * @param scenario
	 *            - determine datatable row
	 * @param existingMediaIDs
	 *            - list of existing media IDs that were replaced
	 * @throws NA
	 * @return String list of replacement media IDs
	 * 
	 */
	public String[] getReplacementMediaIDs(String scenario, String[] existingMediaIDs) {
		datatable.setVirtualtablePage("Media");
		datatable.setVirtualtableScenario(scenario);
		int rowCounter = 0;
		int numberOfGuests = Integer.parseInt(datatable.getDataParameter("ReplaceMedia"));
		// int numberOfGuests = 2;
		int existingMediaLoopCounter = 0;
		int numberOfExistingMedia = existingMediaIDs.length;
		int numberOfRows = tblKttwDetails.getRowCount();
		int newMediaIdCounter = 0;
		Regex regex = new Regex();
		boolean isNew = true;

		String[] newMediaIDs = new String[numberOfGuests];
		// Iterate through each table row
		for (rowCounter = 0; rowCounter < numberOfRows; rowCounter++) {
			isNew = true;
			if (newMediaIdCounter == numberOfGuests) {
				break;
			}
			// Determine if the row has the minimal number of necessary columns
			// and if its contents are a number
			try {
				if (tblKttwDetails.getColumnCount(driver, rowCounter) >= 3
						&& regex.match("[0-9]+", tblKttwDetails.getCellData(driver, rowCounter, 4))) {
					// Iterate through each existing media ID to determine if
					// the value is new
					for (existingMediaLoopCounter = 0; existingMediaLoopCounter < numberOfExistingMedia; existingMediaLoopCounter++) {
						// Determine if the value is new. If so, capture the
						// number
						if (tblKttwDetails.getCellData(driver, rowCounter, 4)
								.contains(existingMediaIDs[existingMediaLoopCounter])) {
							isNew = false;
							break;
						}
					}
					if (isNew) {
						newMediaIDs[newMediaIdCounter] = tblKttwDetails.getCellData(driver, rowCounter, 4);
						newMediaIdCounter++;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}

		return newMediaIDs;
	}

	/**
	 * 
	 * @summary Method to capture media numbers. Typically this will be invoked
	 *          during, or immediately after, checkin, when only the original
	 *          media IDs are assigned
	 * @version Created 10/03/2014
	 * @author Waightstill W Avery
	 * @param scenario
	 *            - determine datatable row
	 * @throws NA
	 * @return NA
	 * 
	 */
	public String[] captureMediaNumbers(String scenario) {
		datatable.setVirtualtablePage("Media");
		datatable.setVirtualtableScenario(scenario);

		int loopCounter;
		int numberOfGuests = Integer.parseInt(datatable.getDataParameter("NumberOfGuestsToEncode"));
		String[] mediaNumbers = new String[numberOfGuests];

		pageLoaded();
		for (loopCounter = 0; loopCounter < numberOfGuests; loopCounter++) {
			mediaNumbers[loopCounter] = tblKttwDetails.getCellData(driver, 3 * (loopCounter + 1), 4);
		}
		return mediaNumbers;
	}

	/**
	 * 
	 * @summary Method to capture a sepcific number of media numbers. Typically
	 *          this will be invoked during, or immediately after, checkin, when
	 *          only the original media IDs are assigned
	 * @version Created 10/03/2014
	 * @author Waightstill W Avery
	 * @param numberOfGuests
	 *            - number of guest for which to grab media numbers
	 * @throws NA
	 * @return String list of media numbers
	 * 
	 */
	public String[] captureMediaNumbers(int numberOfGuests) {
		int loopCounter;
		String[] mediaNumbers = new String[numberOfGuests];

		pageLoaded();
		for (loopCounter = 0; loopCounter < numberOfGuests; loopCounter++) {
			mediaNumbers[loopCounter] = tblKttwDetails.getCellData(driver, 3 * (loopCounter + 1), 4);
		}
		return mediaNumbers;
	}

	/**
	 * 
	 * @summary Method to validate media access for a particular media ID
	 * @version Created 10/03/2014
	 * @author Waightstill W Avery
	 * @param accessId
	 *            - access ID to validate (typically a room or door number)
	 * @param expectedResult
	 *            - boolean value indicating if the access ID is expected to be
	 *            found (true if yes, false if no)
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void validateMediaAccess(String accessId, boolean expectedResult) {
		int rowCount = tblKttwDetails.getRowCount();
		int rowLoopCounter;
		String tableCellHtmlId;
		String[] tableCellHtmlIdArray;
		Regex regex = new Regex();
		String linkAccessHtmlId;
		boolean accessFound = false;

		for (rowLoopCounter = 0; rowLoopCounter < rowCount; rowLoopCounter++) {
			if (tblKttwDetails.getColumnCount(driver, rowLoopCounter) >= 3
					&& regex.match("[0-9]+", tblKttwDetails.getCellData(driver, rowLoopCounter, 4).trim())) {
				Element element = new ElementImpl(tblKttwDetails.getCell(driver, rowLoopCounter, 4));
				tableCellHtmlId = element.getAttribute("id");
				tableCellHtmlIdArray = tableCellHtmlId.split(":");
				linkAccessHtmlId = "newMediaEncodeForm:kttwDetails:" + tableCellHtmlIdArray[2] + ":media:"
						+ tableCellHtmlIdArray[4] + ":editRoomAccess";
				Link link = new LinkImpl(driver.findElement(By.id(linkAccessHtmlId)));
				// link.highlight(driver);
				link.jsClick(driver);
				new ProcessingYourRequest().WaitForProcessRequest(driver);

				accessFound = searchForAccessIdInRoomAccess(accessId);

				Assert.assertEquals(accessFound, expectedResult, "Access was not found for ID " + accessId + "\n");
				break;
			}
		}
	}

	/**
	 * 
	 * @summary Method to validate access for all KTTW numbers (i.e. ensure all
	 *          guest can be given access)
	 * @version Created 10/03/2014
	 * @author Waightstill W Avery
	 * @param accessId
	 *            - access ID to validate (typically a room or door number)
	 * @param expectedResult
	 *            - boolean value indicating if the access ID is expected to be
	 *            found (true if yes, false if no)
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void validateMediaAccessForAllGuests(String accessId, boolean expectedResult) {
		int rowCount = tblKttwDetails.getRowCount();
		int rowLoopCounter;
		String tableCellHtmlId;
		String[] tableCellHtmlIdArray;
		Regex regex = new Regex();
		String linkAccessHtmlId;

		// Loop through each row in the table to find all KTTW card numbers
		for (rowLoopCounter = 0; rowLoopCounter < rowCount; rowLoopCounter++) {
			// If the row has more than 3 columns and the fourth column contains
			// a number,
			// then a KTTW card number has been found
			if (tblKttwDetails.getColumnCount(driver, rowLoopCounter) >= 3
					&& regex.match("[0-9]+", tblKttwDetails.getCellData(driver, rowLoopCounter, 4).trim())) {
				// Reset the boolean to false
				boolean accessFound = false;
				// Grab the table cell with the number
				Element element = new ElementImpl(tblKttwDetails.getCell(driver, rowLoopCounter, 4));
				// Decompose the cell html id to get the indices that will help
				// locate the access link
				tableCellHtmlId = element.getAttribute("id");
				tableCellHtmlIdArray = tableCellHtmlId.split(":");
				linkAccessHtmlId = "newMediaEncodeForm:kttwDetails:" + tableCellHtmlIdArray[2] + ":media:"
						+ tableCellHtmlIdArray[4] + ":editRoomAccess";
				// Grab the access link for this KTTW number
				Link link = new LinkImpl(driver.findElement(By.id(linkAccessHtmlId)));
				// Click the link
				link.jsClick(driver);
				new ProcessingYourRequest().WaitForProcessRequest(driver);

				// Verify that the access id is found in the room access table
				// for this KTTW card number
				accessFound = searchForAccessIdInRoomAccess(accessId);
				Assert.assertEquals(accessFound, expectedResult, "Access was not found for ID " + accessId + "\n");
			}
		}
	}

	/**
	 * 
	 * @summary Method to validate access for all XBand numbers (i.e. ensure all
	 *          guest can be given access)
	 * @version Created 10/03/2014
	 * @author Waightstill W Avery
	 * @param accessId
	 *            - access ID to validate (typically a room or door number)
	 * @param expectedResult
	 *            - boolean value indicating if the access ID is expected to be
	 *            found (true if yes, false if no)
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void validateXBandMediaAccessForAllGuests(String accessId, boolean expectedResult, String[] mediaId) {
		int rowCount = tblKttwDetails.getRowCount();
		int rowLoopCounter;
		String tableCellHtmlId;
		String[] tableCellHtmlIdArray;
		String linkAccessHtmlId;

		// Loop through each row in the table to find all KTTW card numbers
		for (rowLoopCounter = 0; rowLoopCounter < rowCount; rowLoopCounter++) {
			// Determine if the row has more than 4 columns - helps avoid
			// exceptions later
			if (tblKttwDetails.getColumnCount(driver, rowLoopCounter) >= 4) {
				// Reset the boolean values
				boolean isXband = false;
				boolean accessFound = false;
				// Grab the table cell with the number
				Element element = new ElementImpl(tblKttwDetails.getCell(driver, rowLoopCounter, 5));
				for (loopCounter = 0; loopCounter < mediaId.length; loopCounter++) {
					if (element.getText().contentEquals(mediaId[loopCounter])) {
						isXband = true;
						break;
					}
				}

				if (isXband) {
					// Decompose the cell html id to get the indices that will
					// help locate the access link
					tableCellHtmlId = element.getAttribute("id");
					tableCellHtmlIdArray = tableCellHtmlId.split(":");
					linkAccessHtmlId = "newMediaEncodeForm:kttwDetails:" + tableCellHtmlIdArray[2] + ":media:"
							+ tableCellHtmlIdArray[4] + ":editRoomAccess";
					// Grab the access link for this KTTW number
					Link link = new LinkImpl(driver.findElement(By.id(linkAccessHtmlId)));
					link.highlight(driver);
					// Click the link
					link.jsClick(driver);
					new ProcessingYourRequest().WaitForProcessRequest(driver);
					;

					// Verify that the access id is found in the room access
					// table for this KTTW card number
					accessFound = searchForAccessIdInRoomAccess(accessId);
					Assert.assertEquals(accessFound, expectedResult, "Access was not found for ID " + accessId + "\n");
				}
			}
		}
	}

	public void clickAddGuest() {
		lnkAddGuest.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void AddDayGuest(String scenario) {
		clickAddGuest();
		List<WebElement> eleGuestRows = tblKttwBody.findElements(By.xpath("tr[@class='dr-table-firstrow']"));
		Element lastRow = new ElementImpl(eleGuestRows.get(eleGuestRows.size() - 1));
		new ListboxImpl(lastRow.findElement(By.xpath("//select[contains(@name, 'prefixSelect')]"))).select("Mr.");
		new TextboxImpl(lastRow.findElement(By.xpath("//input[contains(@name, 'guestFirstNameId')]"))).set("New");
		new TextboxImpl(lastRow.findElement(By.xpath("//input[contains(@name, 'guestLastNameId')]"))).set("Guest");
		new ListboxImpl(lastRow.findElement(By.xpath("//select[contains(@name, 'suffixSelect')]"))).select("Suffix");
		new LinkImpl(lastRow.findElement(By.xpath("//a[contains(@name, 'editRoomAccess')]"))).jsClick(driver);

		pageLoaded(btnRoomAccessClose);
		initialize();

		new LinkImpl(lastRow.findElement(By.xpath("//input[contains(@onclick, 'javascript:checkAll(this)')]")))
				.jsClick(driver);
		clickRoomAccessSubmit();

		initialize();
		new ButtonImpl(lastRow.findElement(By.xpath("//input[contains(@name, 'addGuest')]"))).jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * 
	 * @summary Method to search the Room Access table for a particular access
	 *          ID
	 * @version Created 10/03/2014
	 * @author Waightstill W Avery
	 * @param accessId
	 *            - access ID to validate (typically a room or door number)
	 * @throws NA
	 * @return true if found, false otherwise
	 * 
	 */
	private boolean searchForAccessIdInRoomAccess(String accessId) {
		pageLoaded(btnRoomAccessClose);
		initialize();
		boolean accessFound = false;

		// Verify that the access id is contained in the text
		// TODO: Make this more robust by iterating through each row of the
		// table.
		// "//img[contains(@src, 'Processing_Window_Overlay')]"
		// Webtable table1 = new
		// WebtableImpl(driver.findElement(By.xpath("//table[contains(@id='accessForm:roomAccessTabPanel')]")));
		// Webtable table = new
		// WebtableImpl(table1.findElement(By.xpath("//div/table")));

		if (tblRoomAccess.getText().contains(accessId)) {
			accessFound = true;
		}
		clickRoomAccessClose();

		return accessFound;
	}

	private void clickRoomAccessSubmit() {
		btnRoomAccessClose.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	private void clickRoomAccessClose() {
		btnRoomAccessClose.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * 
	 * @summary Method to capture media numbers. Typically this will be invoked
	 *          during, or immediately after, checkin, when only the original
	 *          media IDs are assigned
	 * @version Created 10/03/2014
	 * @author Waightstill W Avery
	 * @param scenario
	 *            - determine datatable row
	 * @throws NA
	 * @return NA
	 * 
	 */
	public String[] captureAllXbandMediaNumbers(int numberOfGuests) {
		int loopCounter;
		String[] mediaNumbers = new String[numberOfGuests];

		for (loopCounter = 0; loopCounter < numberOfGuests; loopCounter++) {
			mediaNumbers[loopCounter] = tblKttwDetails.getCellData(driver, 4 * (loopCounter + 1), 5);
		}

		return mediaNumbers;
	}

	/**
	 * 
	 * @summary Method to add additional access to media
	 * @version Created 11/08/2014
	 * @author Waightstill W Avery
	 * @throws NA
	 * @return NA
	 * 
	 */

	public void addResourceAccessToAllMedia() {
		List<WebElement> roomAccessLinks = driver.findElements(By.cssSelector("input[id$=\"RoomAccess\""));
		for (loopCounter = 0; loopCounter < roomAccessLinks.size(); loopCounter++) {
			Link link = new LinkImpl(roomAccessLinks.get(loopCounter));
			link.highlight(driver);
		}
	}

	/**
	 * 
	 * @summary Method to add additional access to media
	 * @version Created 11/08/2014
	 * @author Waightstill W Avery
	 * @param scenario
	 *            - used to determine datatable row
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void addResourceAccessToAGuest(String scenario) {
		datatable.setVirtualtablePage("Media");
		datatable.setVirtualtableScenario(scenario);
		String floor = datatable.getDataParameter("UpdateAccessIDs");

		boolean accessFound = false;

		MediaTab mediaTab = new MediaTab(driver);
		mediaTab.pageLoaded();
		initialize();

		String htmlId = "newMediaEncodeForm:kttwDetails:0:editRoomAccess";
		Link element = new LinkImpl(driver.findElement(By.id(htmlId)));
		element.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		RoomAccessWindow roomAccess = new RoomAccessWindow(driver);
		accessFound = roomAccess.addFloorAccess(floor);

		Assert.assertEquals(accessFound, true, "Access was not found for floor [" + floor + "]\n");

	}

	/**
	 * 
	 * @summary Method to encode media
	 * @version Created 11/11/2014
	 * @author Waightstill W Avery
	 * @param scenario
	 *            - used to determine datatable row
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void encodeMedia(String scenario) {
		datatable.setVirtualtablePage("Media");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded(lnkEncode);
		selectMediaToEncode(scenario);
		Assert.assertEquals(verifyEncodeEnabled(), true);
		clickEncode();
		selectEncoder(scenario);
		clickEncoderSubmit();
		pageLoaded();
	}

	/**
	 * 
	 * @summary Method to verify the History button is enabled
	 * @version Created 11/05/2015
	 * @author Atmakuri Venkatesh
	 * @param NA
	 * @throws NA
	 * @return true if disabled, false otherwise
	 * 
	 */
	public boolean verifyHistoryEnabled() {
		boolean isEnabled = false;
		pageLoaded(lnkHistory);
		Sleeper.sleep(3000);
		if (lnkHistory.getAttribute("class").contains("disabled-false")) {
			isEnabled = true;
		}

		return isEnabled;
	}

	/**
	 * 
	 * @summary Method to verify the History button is Disabled
	 * @version Created 11/05/2015
	 * @author Atmakuri Venkatesh
	 * @param NA
	 * @throws NA
	 * @return true if disabled, false otherwise
	 * 
	 */
	public boolean verifyHistoryDisabled() {
		boolean isEnabled = true;
		pageLoaded(lnkHistory);
		Sleeper.sleep(3000);
		initialize();
		if (lnkHistory.getAttribute("class").contains("disabled-true")) {
			isEnabled = false;
		}

		return isEnabled;
	}

	/**
	 * 
	 * @summary Method to verify the Recommend button is disabled
	 * @version Created 10/03/2014
	 * @author Atmakuri Venkatesh
	 * @param NA
	 * @throws NA
	 * @return true if disabled, false otherwise
	 * 
	 */
	public boolean verifyRecommendEnable() {
		boolean isDisabled = false;
		int counter = 0;
		do{
			Sleeper.sleep(1000);
			if (btnRecommend.getAttribute("class").contains("disabled-false")) {
				isDisabled = true;
				break;
			}
			counter++;
		}while(!isDisabled && counter < WebDriverSetup.getDefaultTestTimeout());

		return isDisabled;
	}

	/**
	 * 
	 * @summary Method to verify the Recommend button is disabled
	 * @version Created 10/03/2014
	 * @author Atmakuri Venkatesh
	 * @param NA
	 * @throws NA
	 * @return true if disabled, false otherwise
	 * 
	 */
	public boolean verifyRecommendDisable() {
		boolean isDisabled = false;
		Sleeper.sleep(3000);
		if (btnRecommend.getAttribute("class").contains("disabled-true")) {
			isDisabled = true;
		}

		return isDisabled;
	}

	/**
	 * 
	 * @summary Method to verify the Issue Magic Band button is disabled
	 * @version Created 10/03/2014
	 * @author Atmakuri Venkatesh
	 * @param NA
	 * @throws NA
	 * @return true if disabled, false otherwise
	 * 
	 */
	public boolean verifyIssueMagicBandEnable() {
		boolean isDisabled = false;
		Sleeper.sleep(3000);
		if (btnIssueMagicBand.getAttribute("class").contains("disabled-false")) {
			isDisabled = true;
		}

		return isDisabled;
	}

	/**
	 * 
	 * @summary Method to verify the Issue Magic Band button is disabled
	 * @version Created 10/03/2014
	 * @author Atmakuri Venkatesh
	 * @param NA
	 * @throws NA
	 * @return true if disabled, false otherwise
	 * 
	 */
	public boolean verifyIssueMagicBandDisable() {
		boolean isDisabled = false;
		Sleeper.sleep(3000);
		if (btnIssueMagicBand.getAttribute("class").contains("disabled-true")) {
			isDisabled = true;
		}

		return isDisabled;
	}

	/**
	 * 
	 * @summary Method to verify Guest sub Rows Collapsed or not
	 * @version Created 11/05/2015
	 * @author Atmakuri Venkatesh
	 * @param NA
	 * @throws NA
	 * @return true if disabled, false otherwise
	 * 
	 */
	public void mediaTableCollapseValidation(String scenario) {
		datatable.setVirtualtablePage("EnterQuickBookPartyMixInfo");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded(tblKttwBody);

		List<WebElement> rowCount = tblKttwBody.findElements(By.tagName("tr"));
		int GuestCount = Integer.parseInt(datatable.getDataParameter("NumberAdults"));
		for (int i = 1; i <= rowCount.size(); i = (i + 2)) {

			if (GuestCount > 0) {
				Element element = new ElementImpl(driver.findElement(By
						.xpath("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[1]/table/tbody/tr[3]/td/span/span/table/tbody/tr[2]/td/table/tbody/tr[2]/td/table/tbody/tr/td/form/span/table/tbody/tr[1]/td/div/table/tbody/tr[2]/td/div/div/table/tbody/tr["
								+ i + "]/td[1]/input[1]")));
				element.syncVisible(driver);
				element.highlight(driver);
				element.jsClick(driver);
				pageLoaded(tblKttwBody);
				Sleeper.sleep(2000);
				TestReporter.assertEquals(driver.findElement(By
						.xpath("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[1]/table/tbody/tr[3]/td/span/span/table/tbody/tr[2]/td/table/tbody/tr[2]/td/table/tbody/tr/td/form/span/table/tbody/tr[1]/td/div/table/tbody/tr[2]/td/div/div/table/tbody/tr["
								+ i + "]/td[1]/input[1]"))
						.getAttribute("class"), "level1_off", "Row ["+i+"] was not found to have collapsed.");
				GuestCount = GuestCount - 1;
			} else {
				break;
			}
		}
	}

	/**
	 * 
	 * @summary Method to verify Guest sub Rows Expanded or not
	 * @version Created 11/05/2015
	 * @author Atmakuri Venkatesh
	 * @param NA
	 * @throws NA
	 * @return true if disabled, false otherwise
	 * 
	 */
	public void mediaTableExpandValidation(String scenario) {
		datatable.setVirtualtablePage("EnterQuickBookPartyMixInfo");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded(tblKttwBody);

		List<WebElement> rowCount = tblKttwBody.findElements(By.tagName("tr"));
		int ExpandGuestCount = Integer.parseInt(datatable.getDataParameter("NumberAdults"));
		for (int i = 1; i <= rowCount.size(); i = (i + 3)) {
			if (ExpandGuestCount > 0) {
				Element element = new ElementImpl(driver.findElement(By
						.xpath("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[1]/table/tbody/tr[3]/td/span/span/table/tbody/tr[2]/td/table/tbody/tr[2]/td/table/tbody/tr/td/form/span/table/tbody/tr[1]/td/div/table/tbody/tr[2]/td/div/div/table/tbody/tr["
								+ i + "]/td[1]/input[1]")));
				element.jsClick(driver);
				pageLoaded(tblKttwBody);
				Sleeper.sleep(2000);
				TestReporter.assertEquals(driver.findElement(By
						.xpath("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td[1]/table/tbody/tr[3]/td/span/span/table/tbody/tr[2]/td/table/tbody/tr[2]/td/table/tbody/tr/td/form/span/table/tbody/tr[1]/td/div/table/tbody/tr[2]/td/div/div/table/tbody/tr["
								+ i + "]/td[1]/input[1]"))
						.getAttribute("class"), "level1_on", "Row ["+i+"] was not found to have expanded.");
				ExpandGuestCount = ExpandGuestCount - 1;

			} else {
				break;
			}
		}
	}

	public void clickMediaCheck() {
		//Grab all media row checkboxes
		List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[id$='mediaRowCheckBoxId']"));
		TestReporter.assertGreaterThanZero(checkboxes.size());
		TestReporter.log("Number of media row checkboxes: ["+checkboxes.size()+"]");
		//Toggle the first media checkbox
		for(WebElement checkbox : checkboxes){
			Checkbox element = new CheckboxImpl(checkbox);
			if(element.syncVisible(driver, 1, false)){
				element.highlight(driver);
				element.jsToggle(driver);
				break;
			}
		}
//		pageLoaded(chkPrimaryguestMediaCheckbox);
//		chkPrimaryguestMediaCheckbox.syncVisible(driver);
//		chkPrimaryguestMediaCheckbox.highlight(driver);
//		chkPrimaryguestMediaCheckbox.jsClick(driver);
	}

	public void clickAddGuestAndVerifyAfterRefresh(String scenario) {
		clickAddGuest();
		pageLoaded(tblKttwBody);
//		initialize();
		datatable.setVirtualtablePage("EnterQuickBookPartyMixInfo");
		datatable.setVirtualtableScenario(scenario);
		System.out.println("====>" + datatable.getDataParameter("NumberAdults"));
		System.out
				.println("newMediaEncodeForm:kttwDetails:" + datatable.getDataParameter("NumberAdults") + ":addGuest");
		Element element = new ElementImpl(driver
				.findElement(By.id(
						"newMediaEncodeForm:kttwDetails:" + datatable.getDataParameter("NumberAdults") + ":addGuest")));
		TestReporter.assertTrue(element.syncVisible(driver), "The Day Guest row was not found");
		TestReporter.logStep("New Add Guest Row Displayed");
		List<WebElement> rowCountBeforRefresh = tblKttwBody.findElements(By.tagName("tr"));
		int countBefore = rowCountBeforRefresh.size();
		clickRefresh();
		pageLoaded(tblKttwBody);
//		initialize();
		List<WebElement> rowCountAfterRefresh = tblKttwBody.findElements(By.tagName("tr"));
		int countAfter = rowCountAfterRefresh.size();
		System.out.println("====>" + rowCountAfterRefresh);
		TestReporter.assertTrue(countAfter < countBefore, "The number of rows before refresh ["+rowCountBeforRefresh.size()+"] do not match the number of rows after refresh ["+rowCountAfterRefresh.size()+"].");
		//Commenting out the below code as assertions are needed here
//		if ((rowCountAfterRefresh) != (rowCountBeforRefresh)) {
//			TestReporter.logStep("New Add Guest Row Disappeared!");
//		}

	}

	public void clickRefresh() {
		lnkRefresh.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/*
	 * public void clickTicket() { btnTicket.click(); }
	 */

	/**
	 * 
	 * @summary Method to Check the link in magic band enable or Disable
	 * @version Created 11/04/2015
	 * @author Atmakuri Venkatesh
	 * @param NA
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void validatingMagicBandAnchorPopUP() {
		boolean isVisible = false;
		pageLoaded(chkPrimaryguestMediaCheckbox);
//		initialize();
		pageLoaded(btnMagicBands);
		btnMagicBands.jsClick(driver);
		Element element = new ElementImpl(driver.findElement(By.id("newMediaEncodeForm:issue_new")));
		
		int counter = 0;
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		do{
			if(element.syncVisible(driver, 1, false)){
				isVisible = true;
				break;
			}
			counter++;
		}while(!isVisible && counter < WebDriverSetup.getDefaultTestTimeout());
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
		TestReporter.assertTrue(element.syncVisible(driver), "The Magic Band popup was not displayed.");
	}

	/**
	 * 
	 * @summary Method to Check the REcommended and issue Magic BAnd Buttons
	 *          enable or Disable
	 * @version Created 11/05/2015
	 * @author Atmakuri Venkatesh
	 * @param Scenario
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void verifyTicketWindowRecommendAndIssueMagicBandButtons(String scenario) {
		datatable.setVirtualtablePage("EnterQuickBookPartyMixInfo");
		datatable.setVirtualtableScenario(scenario);
		clickTicket();
		pageLoaded(eleManageTicketWindow);
		Element element = new ElementImpl(driver.findElement(By.id("manageTicketsForm:accommodationTicketsListTable:0:ticketComponentDetailsTable:"
				+ (Integer.parseInt(datatable.getDataParameter("NumberAdults")) - 1) + ":ticketDetailSelected2")));
		element.jsClick(driver);
		pageLoaded(btnRecommend);
//		initialize();
		TestReporter.assertTrue(verifyRecommendEnable(), "Recommend Button Not Enabled");
		TestReporter.assertTrue(verifyIssueMagicBandEnable(), "Recommend Button Not Enabled");
		TestReporter.logStep("Recommend and IssueMagicBand Buttons Enable");
		initialize();
		pageLoaded(btnCloseManageTicket);
		btnCloseManageTicket.scrollIntoView(driver);
		btnCloseManageTicket.syncVisible(driver);
		btnCloseManageTicket.highlight(driver);
		btnCloseManageTicket.click();
		pageLoaded(btnOk);
		btnOk.click();
		pageLoaded(lnkCelebrations);
		initialize();
	}

	public void clickTicket() {
		btnTicket.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickCelebrationsAndClose() {
		// //Grab the window handle prior to the new window opening
		String winHandleBefore = driver.getWindowHandle();

		pageLoaded(lnkCelebrations);
//		initialize();
		lnkCelebrations.jsClick(driver);
		// lnkCelebrations.click();

		/*
		 * When launching MC from Lilo, a total of 3 windows can exist at the
		 * same time. The logic beloe waits for 3 windows to appear, then wait
		 * for the extra window to disappear. This will continue until the
		 * default test timeout is reached
		 */
		// Wait for the third window to appear
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		do {
			Sleeper.sleep(100);
		} while ((driver.getWindowHandles().size() < 3)
				&& (stopwatch.getTime() < WebDriverSetup.getDefaultTestTimeout()));
		stopwatch.stop();
		stopwatch.reset();

		// Wait for the third window to disappear. This will continue until the
		// default test timeout is reached
		stopwatch.start();
		do {
			Sleeper.sleep(100);
		} while ((driver.getWindowHandles().size() == 3)
				&& (stopwatch.getTime() < WebDriverSetup.getDefaultTestTimeout()));
		stopwatch.stop();
		stopwatch.reset();

		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Magical Celebrations");

		// Iterate through the window handles until the Magical Celebrations
		// window is found
//		for (String handle : drbiggiver.getWindowHandles()) {
//			if (driver.switchTo().window(handle).getTitle().contains("Magical Celebrations")) {
//				break;
//			}
//		}

		// click the exit button
//		initialize();
		pageLoaded(eleCelebrationsExit);
		eleCelebrationsExit.syncVisible(driver);
		eleCelebrationsExit.highlight(driver);
		eleCelebrationsExit.jsClick(driver);

		// Sync until the yes button is visible and click it
//		initialize();
//		new PageLoaded().isDomComplete();
		System.out.println(driver.getTitle());
		System.out.println(driver.getWindowHandles().size());
		pageLoaded(eleCelebrationsExitYes);
		eleCelebrationsExitYes.syncVisible(driver);
		eleCelebrationsExitYes.highlight(driver);
		eleCelebrationsExitYes.jsClick(driver);

		// Switch back to the parent window
		driver.switchTo().window(winHandleBefore);
//		initialize();
		pageLoaded(lnkCelebrations);
	}

	/**
	 * Verifies the charge account status on the Media Tab
	 * Currently 3 values for the class attribute can be seen:
	 * 
	 * statusRed - A PIN and Charge Account have not been created.
	 * statusYellow - A PIN has not been established for this guest.
	 * statusGreen - A PIN and Charge Account have been successfully created.
	 * 
	 * @param scenario
	 */
	public void verifyChargeAccounts(String scenario,String pinUpdated){
		datatable.setVirtualtablePage("Media");
		datatable.setVirtualtableScenario(scenario);
		
		int numberMatched = 0;
		String numberOfCAs = datatable.getDataParameter("NumberOfChargeAccounts");
		String[] caStatus = datatable.getDataParameter("ChargeAccountStatus").split(";");
		TestReporter.assertTrue(Integer.parseInt(numberOfCAs) == caStatus.length, "The number of charge accounts ["+numberOfCAs+"] does not match the number of charge account status' to verify ["+caStatus.length+"].");
		
		List<WebElement> icons = tblKttwBody.findElements(By.xpath("tr/td[2]/img"));
		System.out.println("Number of Icons Found: " + icons.size());
		
		
		//Iterate through each icon found on the page
		for(WebElement icon : icons){
			//Iterate through each expected value
			for(int status = 0; status < caStatus.length; status++){
				//Determine if a match is found
				if(icon.getAttribute("class").equalsIgnoreCase(caStatus[status])){
					caStatus[status] = "matched";
					numberMatched++;
					break;
				}
			}
		}
		
		//Compile a string a status' for reporting
		String strStatus = "";
		switch (pinUpdated.toLowerCase()) {
		case "true":
			strStatus = validChargeAccountIcon;
			break;
		case "false":
			strStatus = pinNotSetup;
			break;
		default:
			break;
		}
		for(String status : caStatus){
			strStatus += status + ";";
		}
		strStatus = strStatus.substring(0, strStatus.length()-1);
		TestReporter.assertTrue(numberMatched == Integer.parseInt(numberOfCAs), "The charge account status' ["+strStatus+"] were not found. The expected number of charge accounts ["+numberOfCAs+"] did not equal the number of status' matched is ["+String.valueOf(numberMatched)+"].");
	}
	
	/**
	 * 
	 * @summary Method to check the primary check-box and to provide the Resource access to another Guest
	 * @version Created 11/9/2015
	 * @author  Praveen Namburi
	 * @param   NA
	 * @throws  NA
	 * @return  NA
	 * @throws InterruptedException 
	 * 
	 */
	public void addResourceGuestAccess() {
		
		initialize();
		
	    pageLoaded(chkfirstrownewMediaCheckBox);
	    chkfirstrownewMediaCheckBox.click();	
	    new ProcessingYourRequest().WaitForProcessRequest(driver);
	
	    //Clcik on the first access link in the encode media table
	    pageLoaded(lnkFirstRowAccess);
	    lnkFirstRowAccess.jsClick(driver);	
	    new ProcessingYourRequest().WaitForProcessRequest(driver);

	}
	
	/**
     * 
     * @summary Method to verify Whetehr the History button is enabled 
     * @version Created 11/08/2015
     * @author  Praveen Namburi
     * @param   NA
     * @throws  NA
     * @return  true if disabled, false otherwise
     * 
     */
	 public boolean verifyHistoryButtonIsEnabled(){
     boolean isEnabled = false;
     pageLoaded(lnkHistory);
     Sleeper.sleep(3000);
     if(lnkHistory.getAttribute("class").contains("disabled-false")){
       isEnabled = true;
     }

       return isEnabled;
     }
	 
	 /**
	    * 
	    * @summary Method to check the primary guest access and to click on History button 
	    * @version Created 11/09/2015
	    * @author  Praveen Namburi
	    * @param   NA
	    * @throws  NA
	    * @return  NA
	    * 
	    */ 	 
	 	public void navigateToViewHistory(){

	 		//After refreshing the page, check the first media check-box
	 		pageLoaded(chkfirstrownewMediaCheckBox);
		    chkfirstrownewMediaCheckBox.jsClick(driver);
		    new ProcessingYourRequest().WaitForProcessRequest(driver);
		    
		    Sleeper.sleep(2000);
		    //Verfiy the History buton is enabled
		    verifyHistoryButtonIsEnabled();
		    clickHistoryLink();
	 	}
	 	
	 	/**
	 	 * @summary : Method to click on History link in RoomTab page.
	 	 * @author  : Praveen Namburi
	 	 * @version : Created 11/10/2015
	 	 */
	 	public void clickHistoryLink(){
			
			pageLoaded(lnkHistory);
			lnkHistory.syncVisible(driver);
			lnkHistory.click();
			
			new ProcessingYourRequest().WaitForProcessRequest(driver);
		}
	 	
	 	/**
	 	 * @summary : Method to click on Refresh link in RoomTab page.
	 	 * @author  : Praveen Namburi
	 	 * @version : Created 11/10/2015
	 	 */
	 	public void clickRefreshLink(){
			
			pageLoaded(lnkRefresh);
			lnkRefresh.syncVisible(driver);
			lnkRefresh.click();
			
			new ProcessingYourRequest().WaitForProcessRequest(driver);
		}
	 	
	 	/*
		 * @summary Method to select firstRow of media 
		 * @author Marella Satish
		 * @version 12/15/2015 Marella Satish - original
		 * @param NA
		 * @return NA
		 */
		public void checkFirstMedia() {
			
			initialize();
			pageLoaded();
			chkfirstrownewMediaCheckBox.check();
			Sleeper.sleep(500);
		}
		
	 	/**
	 	 * @summary: Enter day guest details for the newly created row
	 	 * @author : Praveen Namburi
	 	 * @version: created 12/16/2015
	 	 * @param  : scenario
	 	 */
	 	public void enterAddDayGuestDetails(String scenario){
	 		datatable.setVirtualtablePage("EnterDayGuestInfo");
			datatable.setVirtualtableScenario(scenario);
	 		
	 		initialize();	 		
	 		clickAddGuest();	 		
	 		pageLoaded(tblKttwBody);
	 		
			List<WebElement> eleGuestRows = tblKttwBody.findElements(By.tagName("tr"));
			int rowsCount = eleGuestRows.size();
			TestReporter.logStep("Total no. of rows in the table is :  "+rowsCount);
			
			//Enter the day guest details from datatable
			Element lastRow = new ElementImpl(eleGuestRows.get(eleGuestRows.size()-1));
			new ListboxImpl(lastRow.findElement(By.xpath("//select[contains(@name, 'prefixSelect')]"))).select(datatable.getDataParameter("Title"));
			new TextboxImpl(lastRow.findElement(By.xpath("//input[contains(@name, 'guestFirstNameId')]"))).set(datatable.getDataParameter("FirstName"));
			new TextboxImpl(lastRow.findElement(By.xpath("//input[contains(@name, 'guestLastNameId')]"))).set(datatable.getDataParameter("LastName"));
			new ListboxImpl(lastRow.findElement(By.xpath("//select[contains(@name, 'suffixSelect')]"))).select(datatable.getDataParameter("Suffix"));
			
			//To click on the access media link for the day guest user
			if(datatable.getDataParameter("SelectAccess").equalsIgnoreCase("TRUE")){
				new LinkImpl(lastRow.findElement(By.xpath("//a[contains(@name, 'editRoomAccess')]"))).jsClick(driver);
			}
	 		
	 	}
	 	
	 	/**
	 	 * @Summary : Created method to capture the number of day guests.
	 	 * @author  : Praveen Namburi
	 	 * @version : Created 12-16-2015
	 	 * @return  : String getCountOfGuests
	 	 */
	 	public String getCountOfGuests(){
	 		String xpath = ".//*[@id='newMediaEncodeForm:adultCountId']";
	 		String NumOfGuests = driver.findElement(By.xpath(xpath)).getText();
	 		
	 		return NumOfGuests;
	 	}
	 	
	 	/**
	 	 * @summary : Method to click on the Refresh image
	 	 * @version : Created 12-17-2015
	 	 * @author  : Praveen Namburi.
	 	 */
	 	public void clickImageRefresh(){
	 		pageLoaded(eleRefreshImage);
	 		eleRefreshImage.syncVisible(driver);
	 		eleRefreshImage.highlight(driver);
	 		eleRefreshImage.jsClick(driver);
	 		
	 		new ProcessingYourRequest().WaitForProcessRequest(driver);
	 	}
	 	
		/** @summary Method to click Magic band button 
		 * @author Marella Satish
		 * @version 12/15/2015 Marella Satish - original
		 * @param NA
		 * @return NA
		 */
		public void clickMagicBand() {
			
			initialize();
			pageLoaded(btnMagicBands);
			btnMagicBands.click();

		}

		/**
		 * @summary Method to click Issue New link from Magic Band popup
		 * @author Marella Satish
		 * @version 12/15/2015 Marella Satish - original
		 * @param NA
		 * @return NA
		 */
		public void clickIssueNew() {			
			checkFirstMedia();
			clickMagicBand();
			initialize();
			pageLoaded(eleIssueNew);
			eleIssueNew.highlight(driver);
			eleIssueNew.jsClick(driver);
			try{
				new ProcessingYourRequest().WaitForProcessRequest(driver);
			}catch(UnhandledAlertException uae){}

		}
		
		/**
		 * @summary Method to Check the update link in magic band id Disabled
		 * @author Marella Satish
		 * @version 12/15/2015 Marella Satish - original
		 * @param NA
		 * @return NA
		 */
		public void validatingUpdateAnchorPopUPLink() {
			
			 clickMagicBand();
			 eleUpdate.highlight(driver);
			 boolean UpdateLinkStatus = eleUpdate.getAttribute("class").contains("disabled");
			 TestReporter.logStep("Update Link Status : "+UpdateLinkStatus);
			 TestReporter.assertTrue(UpdateLinkStatus, "Magic Band Anchor link Update is Enabled");
			 TestReporter.logStep("Magic Band Anchor link Update is Enabled");
		}
		
		/**
		 * @summary Method to validate the magic band after issue
		 * @author Marella Satish
		 * @version 12/15/2015 Marella Satish - original
		 * @param NA
		 * @return NA
		 */
		public void validateIssueAndEncodeMagicBand(){
			clickIssueNew();
			initialize();
			pageLoaded(btnClosePopup);
			boolean EncodeBandPopupStatus = eleEncodeBandPopup.isDisplayed();
			TestReporter.logStep("EncodeBandPopupStatus : "+EncodeBandPopupStatus);
			TestReporter.assertTrue(EncodeBandPopupStatus, "No Popup displays to handle");
			 TestReporter.logStep("Issue and Encode New MagicBand Handled");
			btnClosePopup.highlight(driver);
			btnClosePopup.click();
			new ProcessingYourRequest().WaitForProcessRequest(driver);
			
		}
			
		/**
	 	 * @summary : Method to return the Adult count
	 	 * @author  : Dennis Stagliano
	 	 * @version : Created 12/22/2015
	 	 */
	 	public String countOfAdults(){
	 		pageLoaded(lblAdultCount);
	 		return lblAdultCount.getText();	 			 
	 	}
}

