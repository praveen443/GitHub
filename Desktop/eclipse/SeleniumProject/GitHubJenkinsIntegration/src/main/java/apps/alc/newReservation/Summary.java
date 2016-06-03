package apps.alc.newReservation;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import apps.alc.pleaseWait.PleaseWait;
import core.WebDriverSetup;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Label;
import core.interfaces.Webtable;
import core.interfaces.impl.ButtonImpl;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

/**
 * 
 * @summary Contains the page methods & objects for the search guest page during
 *          the new reservation process
 * @version Created 10/03/2014
 * @author Waightstill W Avery
 */
public class Summary {
	// **********************************
	// *** Main Summary Page Fields ***
	// **********************************
	private WebDriver driver;
	private String reservationNumber = "";
	private String reservationDetails = "";
	private Datatable datatable = new Datatable();

	// ************************************
	// *** Main PaymentUI Page Elements ***
	// ************************************
	@FindBy(id = "reservationSummaryForm:reservationSummaryRepeat:0:policyAndSpielCommandButton")
	private Button btnPolicyAndSpiel;

	@FindBy(id = "reservationSummaryForm:reservationSummaryRepeat:0:bookedReservationSummaryOutputPanel")
	private Label lblReservationDetails;

	@FindBy(css = "input[id^=\"logoutForm1:j_id18\"]")
	private Button btnDone;

	@FindBy(id = "processingModalPanelContentTable")
	private Element eleProcessingRequest;

	@FindBy(id = "logoutForm1:marketingPrefID")
	private Button btnPtoiMarketingPreferencesRequired;

	@FindBy(id = "logoutForm1:editMarketingPrefID")
	private Button btnPtoiEditMarketingPreferences;

	@FindBy(xpath = "/html/body/div[5]/div[2]/div/div[2]/table/tbody/tr[2]/td/form/table[1]")
	private Webtable tblPtoiSpiel;

	@FindBy(xpath = "/html/body/div[5]/div[2]/div/div[2]/table/tbody/tr[2]/td/form/table[2]/tbody/tr/td[2]/table/tbody/tr/td/input[1]")
	private Button btnPtoiInfo;

	@FindBy(xpath = "//input[@value = 'Save']")
	private Button btnPtoiSave;

	@FindBy(xpath = "/html/body/div[6]/div[2]/div/div[2]/table/tbody/tr[2]/td/form/table[1]")
	private Webtable tblPtoiInfo;

	@FindBy(xpath = "/html/body/div[6]/div[2]/div/div[2]/table/tbody/tr[2]/td/form/table[2]/tbody/tr/td/input")
	private Button btnPtoiInfoClose;

	@FindBy(xpath = "/html/body/div[5]/div[2]/div/div[2]/table/tbody/tr[2]/td/form/table[2]/tbody/tr/td[1]/table/tbody/tr/td/input[1]")
	private Button btnPtoiUnableToConfirm;

	@FindBy(xpath = "//input[@value='Third Party CC']")
	private Button btnPtoiThirdPartyCC;

	@FindBy(xpath = "/html/body/div[6]/div[2]/div/div[2]/table/tbody/tr[2]/td/form/span")
	private Element elePtoiGuestName;

	@FindBy(xpath = "//*[@id='reservationSummaryForm:reservationSummaryRepeat:0:bookedReservationSummaryOutputPanel']/table/tbody/tr[1]/td[1]")
	private Element eleResDetails;

	@FindBy(xpath = "//*[@id='reservationSummaryForm:reservationSummaryRepeat:0:offerItemSynopsis']/table/tbody/tr[1]/td")
	private Element eleItemSynopsis;

	@FindBy(xpath = "//*[@id='reservationSummaryForm:reservationSummaryRepeat:0:offerItemSynopsis1']/table/tbody/tr[1]/td")
	private Element eleDinnerShowItemSynopsis;

	// get Resv number
	@FindBy(xpath = "//*[@id='reservationSummaryForm:reservationSummaryRepeat:0:bookedReservationSummaryOutputPanel']/table/tbody/tr[1]/td[1]/b")
	private Element eleGetResNumber;

	// PTOI panel id
	@FindBy(id = "ptoiQuestionPanelIdShadowDiv")
	private Element elePTOIPanelId;

	@FindBy(xpath = "//*[@id='reservationSummaryForm:reservationSummaryTotalPriceOutputPanel']/table/tbody/tr[2]/td[2]")
	private Element eleTotalPaid;

	@FindBy(xpath = "//*[@id='reservationSummaryForm:reservationSummaryTotalPriceOutputPanel']/table/tbody/tr[2]/td[3]")
	private Element eleTotalCost;

	@FindBy(xpath = "//*[@id='reservationSummaryForm:reservationSummaryTotalPriceOutputPanel']/table/tbody/tr[3]")
	private Element eleDifferenceOwed;

	// *********************
	// ** Build page area **
	// *********************
	public Summary(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnPolicyAndSpiel);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	public Summary initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	// **************************************
	// *** Main CheckIn Page Interactions ***
	// **************************************
	public void captureSummaryDetails() {
		captureReservationNumber();
		captureReservationDetails();
	}

	private void captureReservationNumber() {
		captureReservationDetails();
		int start = reservationDetails.indexOf("Reservation Number: ");
		String anchor = "Reservation Number: ";
		int anchorLength = anchor.length();
		int reservationNumberLength = 12;
		reservationNumber = reservationDetails.substring(start + anchorLength,
				start + anchorLength + reservationNumberLength);
		Reporter.log("Reservation number [" + reservationNumber + "]", true);
	}

	public String getReservationNumber() {
		if (reservationNumber.isEmpty())
			captureReservationNumber();
		return reservationNumber;
	}

	private void captureReservationDetails() {
		pageLoaded(lblReservationDetails);
		reservationDetails = lblReservationDetails.getText();
	}

	public String getReservationDetails() {
		return reservationDetails;
	}

	/**
	 * @summary This method gets the reservation summary details from summary
	 *          tab page
	 * @author Marella Satish
	 * @version 01/06/2015 Marella Satish - original
	 * @param na
	 * @return summaryDetails as string
	 */
	public String getSummaryDetails() {
		pageLoaded();
		String summaryDetails = eleResDetails.getText().toLowerCase();
		return summaryDetails;
	}

	/**
	 * @summary This method gets the number of adults from summary tab page
	 * @author Marella Satish
	 * @version 01/06/2015 Marella Satish - original
	 * @param na
	 * @return NoOfAdults as string
	 */
	public String getNumberOfAdults() {
		pageLoaded();
		String AdultCount[];
		String Adults = eleItemSynopsis.getText();
		AdultCount = StringUtils.split(Adults, " ");
		String NoOfAdults = AdultCount[0].trim();
		TestReporter.logStep("Number of adults reserved : " + NoOfAdults);
		return NoOfAdults;
	}

	/**
	 * @summary This method gets the number of childs from summary tab page
	 * @author Marella Satish
	 * @version 01/06/2015 Marella Satish - original
	 * @param
	 * @return NoOfChilds as string
	 */
	public String getNumberOfChilds() {
		pageLoaded();
		String ChildCount[];
		String Childs = eleItemSynopsis.getText();
		ChildCount = StringUtils.split(Childs, " ");
		String NoOfChilds = ChildCount[2].trim();
		TestReporter.logStep("Number of children reserved : " + NoOfChilds);
		return NoOfChilds;

	}

	/**
	 * @sumary : Method to capture the Reservtion number for future use.
	 * @return : String getResvnumber, @version : 1-7-2016.
	 */
	public String getTheReservationNumber() {
		pageLoaded();
		String getResvnumber = eleGetResNumber.getText();
		return getResvnumber;
	}

	public void clickDone() {
		List<WebElement> dones = driver.findElements(By.cssSelector("input[value='Done']"));
		for(WebElement weDone : dones){
			Button d = new ButtonImpl(weDone);
			if(d.syncVisible(driver, 1, false)) {
				btnDone = d;
				break;
			}
		}
		pageLoaded(btnDone);
		btnDone.jsClick(driver);
		// PleaseWait.WaitForPleaseWait(driver);
		SearchGuestPage sgp = new SearchGuestPage(driver);
		sgp.pageLoaded();
	}

	/**
	 * Handle the PTOI popup should it appear
	 * 
	 * @param scenario
	 *            - scenario describing how to handle the popup
	 */
	public void handlePtoi(String scenario, String guestName) {
		datatable.setVirtualtablePage("Summary");
		datatable.setVirtualtableScenario(scenario);

		String emailOptIn = datatable.getDataParameter("EmailOptIn");
		String mailOptIn = datatable.getDataParameter("MailOptIn");
		String thirdParty = datatable.getDataParameter("ThirdParty");
		String unableToConfirm = datatable.getDataParameter("UnableToConfirm");
		String validateInfo = datatable.getDataParameter("ValidateInfo");
		String validateSpiel = datatable.getDataParameter("ValidateSpiel");
		String expectedSpiel = datatable.getDataParameter("Spiel");
		String expectedInfo = datatable.getDataParameter("Info");

		initialize();
		pageLoaded(btnPtoiMarketingPreferencesRequired);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// Determine if the PTOI button exists which requires
		if (btnPtoiMarketingPreferencesRequired.syncVisible(driver)) {
			btnPtoiMarketingPreferencesRequired.jsClick(driver);
			PleaseWait.WaitForPleaseWait(driver);
			pageLoaded(btnPtoiUnableToConfirm);
			btnPtoiUnableToConfirm.highlight(driver);
			btnPtoiUnableToConfirm.syncVisible(driver);

			// Determine if the spiel is to be validated
			if (validateSpiel.equalsIgnoreCase("true")) {
				ptoiValidateSpiel(expectedSpiel);
			}

			// Determine if the info is to be validated
			if (validateInfo.equalsIgnoreCase("true")) {
				ptoiValidateInformation(expectedInfo);
			}

			// Determine if Unable To Confirm
			if (unableToConfirm.equalsIgnoreCase("true")) {
				ptoiClickUnableToConfirm();
				ptoiVerifyMarketingPreferencesSaved(emailOptIn, mailOptIn, thirdParty, unableToConfirm);
			}
			// Determine if using a third party credit card
			else if (thirdParty.equalsIgnoreCase("true")) {
				ptoiClickThirdParty();
				ptoiVerifyMarketingPreferencesSaved(emailOptIn, mailOptIn, thirdParty, unableToConfirm);
			}
			// Determine if Marketing Preferences are to be entered
			else if (!mailOptIn.isEmpty()) {
				ptoiEnterMarketingPreferences(emailOptIn, mailOptIn);
				ptoiVerifyMarketingPreferencesSaved(emailOptIn, mailOptIn, thirdParty, unableToConfirm);
			}
			// For all other cases, handle the PTOI popup
			else {
				ptoiClickUnableToConfirm();
			}
		} else {
			TestReporter.log("PTOI Marketing Preferences button is not visible.");
		}

		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
	}

	public void verifyNoPtoi() {
		boolean isFound = false;
		initialize();
		pageLoaded(btnPtoiMarketingPreferencesRequired);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// Determine if the PTOI button exists which requires
		if (btnPtoiMarketingPreferencesRequired.syncVisible(driver)) {
			isFound = true;
		}
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
		TestReporter.assertFalse(isFound, "The PTOI button was found when it was not expected.");
	}

	/**
	 * This method validates the text contained in the Spiel
	 * 
	 * @param expectedSpiel
	 *            - expected information contents
	 */
	private void ptoiValidateSpiel(String expectedSpiel) {
		boolean spielConfirmed = false;
		String actualSpiel = tblPtoiSpiel.getText().trim().replace(" ", "");
		if (actualSpiel.contains(expectedSpiel.trim().replace(" ", ""))) {
			spielConfirmed = true;
		}

		TestReporter.assertTrue(spielConfirmed,
				"The expected spiel was not found. The actual spiel is [" + actualSpiel + "].");
	}

	/**
	 * This method validates the text contained in the Information window
	 * 
	 * @param expectedInfo
	 */
	private void ptoiValidateInformation(String expectedInfo) {
		boolean infoConfirmed = false;
		String actualInfo;

		btnPtoiInfo.jsClick(driver);
		pageLoaded(tblPtoiInfo);
		actualInfo = tblPtoiInfo.getText().trim().replace(" ", "");
		if (actualInfo.contains(expectedInfo.trim().replace(" ", ""))) {
			infoConfirmed = true;
		}

		TestReporter.assertTrue(infoConfirmed,
				"The expected info was not found. The actual spiel is [" + actualInfo + "].");

		btnPtoiInfoClose.jsClick(driver);
	}

	/**
	 * This method clicks the Unable To Confirm button and syncs to the Summary
	 * page being loaded
	 */
	private void ptoiClickUnableToConfirm() {
		btnPtoiUnableToConfirm.jsClick(driver);
		pageLoaded(btnPtoiEditMarketingPreferences);
	}

	/**
	 * This method clicks the Third Party CC button and syncs to the Summary
	 * page bring loaded
	 */
	private void ptoiClickThirdParty() {
		btnPtoiThirdPartyCC.jsClick(driver);
		initialize();
		pageLoaded(btnPtoiEditMarketingPreferences);
	}

	/**
	 * This method enters and saves the marketing preferences
	 * 
	 * @param emailOptIn
	 *            - email marketing preference
	 * @param mailOptIn
	 *            - mail marketing preference
	 */
	private void ptoiEnterMarketingPreferences(String emailOptIn, String mailOptIn) {
		List<WebElement> optOuts = driver.findElements(By.xpath("//input[@value='Opt-Out']"));
		Element elePtoiEmailOptOut = new ElementImpl(optOuts.get(0));
		Element elePtoiMailOptOut = new ElementImpl(optOuts.get(1));
		List<WebElement> optIns = driver.findElements(By.xpath("//input[@value='Opt-In']"));
		Element elePtoiEmailOptIn = new ElementImpl(optIns.get(0));
		Element elePtoiMailOptIn = new ElementImpl(optIns.get(1));

		// Process the email marketing preferences
		if (emailOptIn.equalsIgnoreCase("true")) {
			TestReporter.assertTrue(elePtoiEmailOptIn.syncVisible(driver),
					"The email opt-in radio button was not visible.");
			elePtoiEmailOptIn.highlight(driver);
			elePtoiEmailOptIn.jsClick(driver);
		} else {
			TestReporter.assertTrue(elePtoiEmailOptOut.syncVisible(driver),
					"The email opt-in radio button was not visible.");
			elePtoiEmailOptOut.highlight(driver);
			elePtoiEmailOptOut.jsClick(driver);
		}

		Sleeper.sleep(500);

		// Process the mail marketing preferences
		if (mailOptIn.equalsIgnoreCase("true")) {
			TestReporter.assertTrue(elePtoiMailOptIn.syncVisible(driver),
					"The mail opt-in radio button was not visible.");
			elePtoiMailOptIn.highlight(driver);
			elePtoiMailOptIn.jsClick(driver);
		} else {
			TestReporter.assertTrue(elePtoiMailOptOut.syncVisible(driver),
					"The mail opt-out radio button was not visible.");
			elePtoiMailOptOut.highlight(driver);
			elePtoiMailOptOut.jsClick(driver);
		}

		Sleeper.sleep(500);

		ptoiClickSave();
	}

	private void ptoiClickSave() {
		initialize();
		pageLoaded(btnPtoiSave);
		// btnPtoiSave.syncEnabled(driver);
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		List<WebElement> saves = driver.findElements(By.xpath("//input[@value = 'Save']"));
		for (WebElement save : saves) {
			Element ele = new ElementImpl(save);
			if (ele.syncVisible(driver, 1, false)) {
				ele.highlight(driver);
				ele.jsClick(driver);
			}
		}
		initialize();
		pageLoaded(btnPtoiEditMarketingPreferences);
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
	}

	private void ptoiVerifyMarketingPreferencesSaved(String emailOptIn, String mailOptIn, String thirdParty,
			String unableToConfirm) {
		initialize();
		pageLoaded(btnPtoiEditMarketingPreferences);
		btnPtoiEditMarketingPreferences.syncVisible(driver);
		btnPtoiEditMarketingPreferences.jsClick(driver);
		initialize();

		// Determine if third party. If so, no marketing preferences should be
		// entered
		if (thirdParty.equalsIgnoreCase("true")) {
			ptoiVerifyThirdParty();
		}
		// Determine if unable to confirm. If so, both marketing preferences
		// should be opt-out
		else if (unableToConfirm.equalsIgnoreCase("true")) {
			ptoiVerifyUnableToConfirm();
		}
		// Otherwise, verify marketing preferences were saved
		else {
			ptoiVerifyOptInOptOut(emailOptIn, mailOptIn);
		}
	}

	private void ptoiVerifyThirdParty() {
		initialize();
		Sleeper.sleep(4000);
		pageLoaded(btnPtoiThirdPartyCC);
		// driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		btnPtoiThirdPartyCC.syncVisible(driver);
		btnPtoiThirdPartyCC.highlight(driver);
		// driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(),
		// TimeUnit.SECONDS);
		List<WebElement> optOuts = driver.findElements(By.xpath("//input[@value='Opt-Out']"));
		Element elePtoiEmailOptOut = new ElementImpl(optOuts.get(0));
		Element elePtoiMailOptOut = new ElementImpl(optOuts.get(1));
		List<WebElement> optIns = driver.findElements(By.xpath("//input[@value='Opt-In']"));
		Element elePtoiEmailOptIn = new ElementImpl(optIns.get(0));
		Element elePtoiMailOptIn = new ElementImpl(optIns.get(1));

		System.out.println("Validating preferences");
		try {
			elePtoiEmailOptIn.highlight(driver);
			TestReporter.assertFalse(elePtoiEmailOptIn.isSelected(),
					"The email opt-in marketing preference was selected when no selection was expected.");
			elePtoiEmailOptOut.highlight(driver);
			TestReporter.assertFalse(elePtoiEmailOptOut.isSelected(),
					"The email opt-out marketing preference was selected when no selection was expected.");
			elePtoiMailOptIn.highlight(driver);
			TestReporter.assertFalse(elePtoiMailOptIn.isSelected(),
					"The mail opt-in marketing preference was selected when no selection was expected.");
			elePtoiMailOptOut.highlight(driver);
			TestReporter.assertFalse(elePtoiMailOptOut.isSelected(),
					"The mail opt-out marketing preference was selected when no selection was expected.");
		} catch (Exception e) {
			System.out.println("Grumble Grumble");

		}
		ptoiClickThirdParty();
	}

	private void ptoiVerifyUnableToConfirm() {
		List<WebElement> optOuts = driver.findElements(By.xpath("//input[@value='Opt-Out']"));
		Element elePtoiEmailOptOut = new ElementImpl(optOuts.get(0));
		Element elePtoiMailOptOut = new ElementImpl(optOuts.get(1));

		System.out.println("Validating preferences");
		try {
			TestReporter.assertTrue(elePtoiEmailOptOut.isSelected(),
					"The email opt-out marketing prefernce was not selected as expected.");
			TestReporter.assertTrue(elePtoiMailOptOut.isSelected(),
					"The mail opt-out marketing prefernce was not selected as expected.");
		} catch (Exception e) {
			System.out.println("Grumble Grumble");

		}
		ptoiClickUnableToConfirm();
	}

	private void ptoiVerifyOptInOptOut(String emailOptIn, String mailOptIn) {
		List<WebElement> optOuts = null;
		do {
			System.out.println("Grabbing Opt-Outs.");
			Sleeper.sleep(100);
			optOuts = driver.findElements(By.xpath("//input[@value='Opt-Out']"));
		} while (optOuts.size() < 2);
		System.out.println("Opt-Outs found.");
		Element elePtoiEmailOptOut = new ElementImpl(optOuts.get(0));
		Element elePtoiMailOptOut = new ElementImpl(optOuts.get(1));
		List<WebElement> optIns = driver.findElements(By.xpath("//input[@value='Opt-In']"));
		Element elePtoiEmailOptIn = new ElementImpl(optIns.get(0));
		Element elePtoiMailOptIn = new ElementImpl(optIns.get(1));

		System.out.println("Validating preferences");
		try {
			// Validate the email marketing preferences
			if (emailOptIn.equalsIgnoreCase("true")) {
				TestReporter.assertTrue(elePtoiEmailOptIn.isSelected(),
						"The email opt-in marketing preference was not selected as expected.");
			} else {
				TestReporter.assertTrue(elePtoiEmailOptOut.isSelected(),
						"The email opt-out marketing preference was not selected as expected.");
			}

			// Validate the mail marketing preferences
			if (mailOptIn.equalsIgnoreCase("true")) {
				TestReporter.assertTrue(elePtoiMailOptIn.isSelected(),
						"The mail opt-in marketing preference was not selected as expected.");
			} else {
				TestReporter.assertTrue(elePtoiMailOptOut.isSelected(),
						"The mail opt-out marketing preference was not selected as expected.");
			}
		} catch (Exception e) {
			System.out.println("Grumble Grumble");
		}

		ptoiClickSave();
	}

	/**
	 * @summary This method gets the number of adults from summary tab page D
	 * @author Venkatesh Atmakuri
	 * @version 01/06/2015 Venkatesh Atmakuri - original
	 * @param na
	 * @return NoOfAdults as string
	 */
	public String getNumberOfAdultsDinnerShow() {

		pageLoaded();
		String AdultCount[];
		Element visibleElement = null;
		String NoOfAdults = null;

		// Find all elements containing a certain string in their html id and
		// find the first visible element
		List<WebElement> elements = driver.findElements(
				By.cssSelector("span[id*='reservationSummaryForm:reservationSummaryRepeat:0:offerItemSynopsis']"));
		TestReporter.assertGreaterThanZero(elements.size());
		for (WebElement element : elements) {
			Element e = new ElementImpl(element);
			if (e.syncVisible(driver, 1, false)) {
				visibleElement = e;
				break;
			}
		}

		String Adults = visibleElement.getText();
		AdultCount = StringUtils.split(Adults, ",");

		// Iterate through all array elements to find one containing "Adults"
		for (String element : AdultCount) {
			if (element.toLowerCase().contains("adult")) {
				NoOfAdults = element.split(" ")[0];
				break;
			}
		}

		TestReporter.logStep("Number of adults reserved : " + NoOfAdults);
		return NoOfAdults;
	}

	/**
	 * @summary This method gets the number of childs from summary tab page -
	 *          DinnerShow
	 * @author Venkatesh Atmakuri
	 * @version 01/06/2015 Venkatesh Atmakuri - original
	 * @param NA
	 * @return NoOfChilds as string
	 */
	public String getNumberOfChildsDinnerShow() {
		pageLoaded();
		String ChildCount[];
		String Childs = null;
		String NoOfChilds = null;
		Element visibleElement = null;

		// Find all elements containing a certain string in their html id and
		// find the first visible element
		List<WebElement> elements = driver.findElements(
				By.cssSelector("span[id*='reservationSummaryForm:reservationSummaryRepeat:0:offerItemSynopsis']"));
		TestReporter.assertGreaterThanZero(elements.size());
		for (WebElement element : elements) {
			Element e = new ElementImpl(element);
			if (e.syncVisible(driver, 1, false)) {
				visibleElement = e;
				break;
			}
		}

		Childs = visibleElement.getText();
		ChildCount = StringUtils.split(Childs, ",");

		// Iterate through all array elements to find one containing "Adults"
		for (String element : ChildCount) {
			if (element.toLowerCase().contains("child")) {
				NoOfChilds = element.trim().split(" ")[0];
				break;
			}
		}
		TestReporter.logStep("Number of children reserved : " + NoOfChilds);
		return NoOfChilds;
	}

	/**
	 * @summary: Method to click on MarketingPreferencesrEquired.
	 * @author : Praveen namburi, @version : Created 1-12-2016.
	 */
	public void clickMarketingPreferences() {
		initialize();
		pageLoaded(btnPtoiMarketingPreferencesRequired);
		btnPtoiMarketingPreferencesRequired.syncVisible(driver);
		btnPtoiMarketingPreferencesRequired.jsClick(driver);

	}

	/**
	 * @summary: method to verify PTOI page is displayed and to click on btn
	 *           UnableToConfirm in PTOI Window.
	 */
	public void verifyPTOIPageAndclickUnableToconfirm() {
		initialize();
		verifyPTOIPageisDisplayed();
		pageLoaded(btnPtoiUnableToConfirm);
		ptoiClickUnableToConfirm();
	}

	/**
	 * @summary: Method to verify PTOI page is displayed.
	 * @author : Praveen Namburi - Original.
	 * @version: created 1-12-2016.
	 */
	public void verifyPTOIPageisDisplayed() {

		boolean PTOIpageisFound = false;
		initialize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		pageLoaded(elePTOIPanelId);
		// Determine if the PTOI button exists which requires
		if (btnPtoiUnableToConfirm.syncVisible(driver)) {
			PTOIpageisFound = true;
		}
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
		TestReporter.assertTrue(PTOIpageisFound, "The PTOI page did not load.");
	}

	/**
	 * @summary: Method to get Payment or Balance Due Details
	 * @author : Venkatesh Atmakuri - Original.
	 * @version: created 1-22-2016.
	 * @param NA
	 * @return NA
	 */
	public void getPaymentOrBalanceDetails() {
		initialize();
		pageLoaded(eleTotalPaid);
		eleTotalCost.highlight(driver);
		TestReporter.logStep(" Total Cost : " + eleTotalCost.getText());
		eleTotalPaid.highlight(driver);
		TestReporter.logStep(" Total Paid : " + eleTotalPaid.getText());
		eleDifferenceOwed.highlight(driver);
		TestReporter.logStep(eleDifferenceOwed.getText());
	}
}


