package apps.lilo.modifyReservation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.impl.CheckboxImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

/*
 * @author Dennis Stagliano
 * @version 11/09/2015 Dennis Stagliano - original
 * @version xx/xx/xxxx SecondContributorNameHere -Brief summary of changes here
 * @version xx/xx/xxxx ThirdContributorNameHere -Brief summary of changes here
 */

public class Lilo_RecommendAndManageTickets {
	//
	// RecommendAndManageTickets Fields
	//
	private String firstNameGet;
	private String lastNameGet;
	private String formattedName;
	final String MyTableId = "manageTicketsForm:accommodationTicketsListTable";
	private Datatable datatable = new Datatable();
	private WebDriver driver;

	//
	// RecommendAndManageTickets Elements
	//
	@FindBy(id = "manageTicketsForm:modifyTicketsButton:anchor")
	private Button btnAnchor;

	@FindBy(name = "manageTicketsForm:recommendTicketsButton")
	private Button btnRecommend;

	@FindBy(id = "searchTicketsForm:searchButton")
	private Button btnclSearch;

	@FindBy(id = "searchTicketsForm:searchTicketsResultsTable:0:kttwTickets:0:noOfAdultKTTWTicketsSelectcomboboxButton")
	private Button btnSearchCombo;

	// First Ticket Offer listbox button
	@FindBy(id = "searchTicketsForm:searchTicketsResultsTable:0:kttwTickets:0:noOfAdultKTTWTicketsSelectcomboboxButton")
	private Button btnFirstTicketOffer;

	// First Ticket Offer Select List element
	@FindBy(id = "searchTicketsForm:searchTicketsResultsTable:0:kttwTickets:0:noOfAdultKTTWTicketsSelectlist")
	private Element eleFirstTicketOfferSelectList;

	// Proceed button after selecting ticket offer
	@FindBy(id = "searchTicketsForm:proceedButton")
	private Button btnProceed;

	// dropdown to match purchaser
	@FindBy(id = "searchTicketsForm:guestDistributionTable:0:kttwTicketsDistribution:0:adultGuestKttwTicketSelect")
	private Listbox lstSelectToMatch;

	@FindBy(id = "searchTicketsForm:purchaseButton")
	private Button btnPurchase;

	@FindBy(id = "completeDayGuestTicketForm:cancelAddTickets")
	private Button btnContinueWOPay;

	@FindBy(id = "completeDayGuestTicketForm:cancelUpgradeTickets")
	private Button btnCancelUpgrade;

	@FindBy(id = "recommendTicketsInfoPopupPanelForm:okButton")
	private Button btnokInfoPopup;

	@FindBy(xpath = "//input[starts-with(@name,'manageTicketsForm:accommodationTicketsListTable:0:ticketComponentDetailsTable:1:ticketComponentDetailsSubTable:0:')]")
	private Checkbox chkEntitlement;

	@FindBy(id = "manageTicketsForm:existingticketsBtn")
	private Button btnExistingTickets;

	@FindBy(id = "searchTicketsForm:searchButton")
	private Button btnsearchTicketsForm;

	@FindBy(id = "closeManageTicketsForm:exitQuickTabButtonId1")
	private Button btnCloseForm;

	@FindBy(id = "confirmationPopupForm:okButtonId")
	private Button btnPopUpok;

	@FindBy(id = "recommendTicketsInfoPopupPanelForm:okButton")
	private Button btnRecommendPopupOKButton;

	@FindBy(id = "header1Form:logoutText")
	private Button btnLogOutUser;

	@FindBy(xpath = "//input[starts-with(@id, 'manageTicketsForm:accommodationTicketsListTable:0:ticketComponentDetailsTable:1:ticketComponentDetailsSubTable:0:j_id')]")
	private String strEntNum1;
	// *********************
	// ** Build page area **
	// *********************

	public Lilo_RecommendAndManageTickets(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public Lilo_RecommendAndManageTickets initialize() {
		return ElementFactory.initElements(driver, Lilo_RecommendAndManageTickets.class);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnRecommend);
	}

	public boolean loadPage() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnRecommend);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	// *********************************************************
	// *** Recommend and Manage Tickets Interactions ***
	// *********************************************************

	public void recommendAndManageTickets(String Scenario) {

		// Get the data already in Virtual table for locating Quick Book first
		// and last name
		// Will look for the proper check box based on the names

		datatable.setVirtualtablePage("EnterQuickBookPartyMixInfo");
		datatable.setVirtualtableScenario(Scenario);
		firstNameGet = datatable.getDataParameter("FirstName").trim();
		lastNameGet = datatable.getDataParameter("LastName").trim();
//		Sleeper.sleep(3000);
		// The first and last name must be formatted in order for the
		// recognition from the source code
		// Browser renders it differently from what the server sends back, but
		// it is consistent.
		formattedName = "";
		formattedName = firstNameGet + " &nbsp;" + lastNameGet;
		formattedName.trim();
		System.out.println("Formatted Name for search = " + formattedName);

	}

	public void getSelectid() {
//		Sleeper.sleep(2000);
		formattedName = firstNameGet + " " + lastNameGet;
		System.out.println(formattedName);
		List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[id$=':ticketDetailSelected2']"));
		for(WebElement checkbox : checkboxes){
			Checkbox cb = new CheckboxImpl(checkbox);
			if(cb.syncVisible(driver, 1, false)){
				if(cb.findElement(By.xpath("../../td[3]")).getText().replace(" ", "").equalsIgnoreCase(formattedName.replace(" ", ""))){
					cb.jsToggle(driver);
					break;
				}
			}
		}
//		// get the source into a variable
//		source = "";
//		source = driver.getPageSource().toString();
//		// slowing this down
//		Sleeper.sleep(3000);
//
//		// using page source to find location of first and last name
//		fd = 0;
//		fd = source.indexOf(formattedName); // get from table - You must enter
//											// the values
//		// as they are in the source to be able to eliminate dynamic id
//		// starting the index of for the section where the checkboxes and values
//		// are.
//		start = 0;
//		start = fd - 1188;
//
//		// creating the substring with the start location and the ending
//		// location where the first and last name was found
//		str = "";
//		str = source.substring(start, fd);
//		x = 0;
//		do {
//			foundLoc = 0;
//			String id = "";
//			foundLoc = str.indexOf("manageTicketsForm:accommodationTicketsListTable:0:ticketComponentDetailsTable:" + x
//					+ ":ticketDetailSelected2 type=checkbox");
//			System.out.println("Starting Index = " + foundLoc);
//			if (foundLoc > 0) {
//				TestReporter.logStep("Found the id of ticket");
//				// if found, then, I'm setting a string that we are looking for
//				// and chopping off the type=checkbox and trimming spaces
//				// notice I'm using x also as a variable integer to determine
//				// what components table to look in
//				String foundMatch = "manageTicketsForm:accommodationTicketsListTable:0:ticketComponentDetailsTable:" + x
//						+ ":ticketDetailSelected2 type=checkbox";
//
//				id = StringUtils.removeEnd(foundMatch, "type=checkbox");
//				id = id.trim();
//				// select the checkbox. This findElement command is local to
//				// this method
//				driver.findElement(By.id(id)).click();
//				// This sleep seems to be necessary due to the app running very
//				// slow in test at this part
//				Sleeper.sleep(4000);
//				found = true;
//
//			} else {
//				System.out.println("not found in row " + x + " . Moving on to next row.");
//				found = false;
//				x++; // go to the next row for next checkbox using x as a
//						// counter
//
//				if (x > 10) { // If it has search at least 10 rows in the table,
//								// then the table should be cleared out
//								// of uncesessary tickets
//					break;
//				}
//			}
//		} while (found == false);
//
//		// If looked in more than 10 rows, then I want to throw exception
//		if (x > 10) {
//			TestReporter.logStep("Exceeded number of table rows allowed or not found. Please check table of tickets");
//			throw new Exception("Exceeded rows or not found");
//		} // throws an exception

	}

	public void clkRecommend() {
		// If reached this point, then we can move on to click the recommend
		// button
		// Recommend button
		pageLoaded(btnRecommend);
		if (btnRecommend.syncEnabled(driver)) {
			btnRecommend.syncVisible(driver);
			btnRecommend.highlight(driver);
			btnRecommend.jsClick(driver);
			new ProcessingYourRequest().WaitForProcessRequest(driver);
		}
	}

	public void clkSearch() {
		// Search button
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		WebElement searchbtn = wait.until(ExpectedConditions.elementToBeClickable(btnclSearch));
		//Bringing this element into the ElementFactory
//		searchbtn.click();
		pageLoaded(btnclSearch);
		btnclSearch.syncVisible(driver);
		btnclSearch.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void selectFirstTicketOffer() {
		pageLoaded(btnFirstTicketOffer);
		btnFirstTicketOffer.syncVisible(driver);
		btnFirstTicketOffer.click();
//		 pageLoaded();
		pageLoaded(eleFirstTicketOfferSelectList);
		eleFirstTicketOfferSelectList.syncVisible(driver);
		eleFirstTicketOfferSelectList.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void ProceedButton() {
		btnProceed.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void selectGuestToMatch() {
		pageLoaded(lstSelectToMatch);
		formattedName = firstNameGet + " " + lastNameGet;
//		List<WebElement> listboxes = driver.findElements(By.cssSelector("select[id$='adultGuestKttwTicketSelect']"));
//		for(WebElement listbox : listboxes){
//			Listbox lb = new ListboxImpl(listbox);
//			if(lb.syncVisible(driver, 1, false)){
//				lb.highlight(driver);
//				for(WebElement option : lb.getOptions()){
//					System.out.println(option.getText());
//				}
//			}
//		}

		lstSelectToMatch.syncVisible(driver);
		lstSelectToMatch.highlight(driver);
		lstSelectToMatch.select(formattedName);
		Sleeper.sleep(1000);
	}

	public void clkPurchase() {
		btnPurchase.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void continueWithOutPayment() {
		btnContinueWOPay.jsClick(driver);
		Sleeper.sleep(1000);
	}

	public void cancelUpgradeContinueWOPayment() {
		pageLoaded(btnCancelUpgrade);
		btnCancelUpgrade.syncVisible(driver);
		btnCancelUpgrade.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void InfoPopup() {
		if (btnokInfoPopup.isDisplayed()) {
			btnokInfoPopup.jsClick(driver);
			new ProcessingYourRequest().WaitForProcessRequest(driver);
		} else {
			TestReporter.logStep("InfoPopup dialog did not display");
		}
	}

	public void selectingTickets(String partyMixScenario) throws Exception {
		// goes back to the getSelectID to search through the tickets table
		// and select the correct ticket based on the PartyMixScenario data
		getSelectid();
	}

	public void entitlements() {

		// click on checkbox on entitlements subtable
		initialize();
		chkEntitlement.syncVisible(driver);
		chkEntitlement.jsClick(driver);
		// Capture the Entitlement # in the subtable

		WebElement mytable = driver.findElement(By.id(MyTableId));
		String regex = "[0-9]{17}";
		// locate rows of table above
		List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
		int rows_count = rows_table.size();
		// System.out.println("Number of <tr> rows of mytable is " +
		// (rows_count-1));

		for (int row = 1; row < rows_count; row++) {
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td")); // locate
																								// columns/cells
																								// of
																								// the
																								// row
																								// by
																								// "td"
			int columns_count = Columns_row.size(); // calculate number of
													// columns in the specific
													// row
			// System.out.println("Number of cells in the row " + row + " are "
			// + columns_count);

			// This will get the value for each cell
			for (int column = 0; column < columns_count; column++) {
				String celtext = Columns_row.get(column).getText();// retrieve
																	// text from
																	// cell
				if (celtext.matches(regex)) {
					TestReporter.logStep("Getting the Entitlenumber");
					System.out.println("Entitlement Number = " + celtext);
				}
			} // end for

		} // end for
		Sleeper.sleep(2000);
	}

	public void modifyLink() {
		// clicks on existing tickets button to modify
		pageLoaded(btnExistingTickets);
		btnExistingTickets.jsClick(driver);
		Sleeper.sleep(1000);
		// This clicks on the modify js popup
		btnAnchor.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void manageSearchTicketsFormBtn() {
		pageLoaded(btnsearchTicketsForm);
		btnsearchTicketsForm.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void recommendOKButton() {
		// initialize();
		pageLoaded(btnRecommendPopupOKButton);
		btnRecommendPopupOKButton.syncVisible(driver);
		btnRecommendPopupOKButton.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void closeout() {
		btnCloseForm.syncVisible(driver);
		btnCloseForm.jsClick(driver);
		// Commenting out for a more dynamic approach
		// Sleeper.sleep(2000);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void Confirm() {
		initialize();
		pageLoaded(btnPopUpok);
		btnPopUpok.syncVisible(driver);
		btnPopUpok.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
}

