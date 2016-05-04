package apps.alc.newReservation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import apps.alc.pleaseWait.PleaseWait;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Link;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

/**
 * 
 * @summary Contains the page methods & objects for the search guest page during
 *          the new reservation process
 * @version Created 9/26/2014
 * @author Jessica Marshall
 */
public class SearchGuestPage {
	// **************************
	// *** SearchGuest Fields ***
	// **************************
	private WebDriver driver;
	
	// ************************************
	// *** Main Page Elements ***
	// ************************************
	private Datatable datatable = new Datatable();
	// Find
	@FindBy(id = "guestSearchForm:findGuestProfileButton")
	private Button btnFind;

	// Clear search
	@FindBy(id = "guestResultForm:commandBtnClearSrch")
	private Button btnClear;

	// Create new guest
	@FindBy(id = "guestResultForm:searchGuestProfileCreateCmdBtn")
	private Button btnCreateNew;

	/*
	 * Guest Details Search
	 */
	// First name
	@FindBy(id = "guestSearchForm:firstName")
	private Textbox txtFirstName;

	// Last name
	@FindBy(id = "guestSearchForm:lastName")
	private Textbox txtLastName;

	// Postal code
	@FindBy(id = "guestSearchForm:zip")
	private Textbox txtPostalCode;

	// Phone number
	@FindBy(id = "guestSearchForm:phone")
	private Textbox txtPhoneNumber;

	// Country
	@FindBy(id = "guestSearchForm:countryOptions")
	private Listbox lstCountry;

	// Email
	@FindBy(id = "guestSearchForm:email")
	private Textbox txtEmail;

	/*
	 * Room Reservation Search
	 */
	// Reservation number
	@FindBy(id = "guestSearchForm:resortReservationId")
	private Textbox txtReservationNumber;

	// Resort
	@FindBy(css = "input[name^=\"guestSearchForm:j_id\"]")
	private Listbox lstResort;

	// Arrival date
	@FindBy(id = "guestSearchForm:guestSearchArrivalDateInput")
	private Textbox txtArrivalDate;

	// TAG
	@FindBy(id = "guestSearchForm:tag")
	private Textbox txtTag;

	// Existing reservation check box
	@FindBy(id = "guestSearchForm:checkBoxForExistingReservation")
	private Checkbox chkExistingRes;

	// Logout
	@FindBy(id = "logoutForm:closeLink")
	private Link lnkLogout;

	// Guest Search - Invalid element
	// Guest search error
	@FindBy(id = "guestSearchForm:errorMessageGuestSearch_body")
	private Element eleGuestError;

	// Guest No results
	@FindBy(id = "guestSearchForm:noResultsLabel")
	private Element eleGuestNoResults;

	// Search Guest tab
	@FindBy(id = "searchProfileTab_lbl")
	private Element eleSearchGuestTab;

	// Guest Search Results value
	@FindBy(xpath = ".//*[@id='guestResultForm:guestSearchResultsPanel_body']/span[2]")
	private Element eleGetGuestSearchResults;

	// *********************
	// ** Build page area **
	// *********************

	public SearchGuestPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}

	public SearchGuestPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnFind);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	// **************************************
	// *** Main CheckIn Page Interactions ***
	// **************************************

	public void clickFind() {
		btnFind.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	private void clickCreateNewGuest() {
		int counter = 0;
		while (counter++ < 10) {
			try {
				btnCreateNew.focus(driver);
				if (btnCreateNew.isEnabled()) {
					btnCreateNew.jsClick(driver);
					counter = 10;
					break;
				} else {
					Sleeper.sleep(100);
					TestReporter.log("SearchGuestPage: btnCreateNew NOT enabled on try " + counter);
				}
			} catch (Exception e) {
				TestReporter.log("Exception trying to focus and enable btnCreateNew: " + e.getMessage());
			}
		}
		// btnCreateNew.jsClick(driver); orig
		// btnCreateNew.focusClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	/**
	 * @version Created 12/28/2015
	 * @author Marella Satish - Enhanced Added functionality to validate invalid
	 *         guest info
	 */
	public void searchGuest(String scenario) {
		/*
		 * datatable.setDatatableSheet("GuestSearch");
		 * datatable.setDatatableRow(datatable.getRowContains(scenario, 0));
		 */
		datatable.setVirtualtablePage("GuestSearch");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded();

		String firstName = datatable.getDataParameter("FirstName");
		String lastName = datatable.getDataParameter("LastName");
		String postalCode = datatable.getDataParameter("PostalCode");
		String phoneNumber = datatable.getDataParameter("Phone");
		String country = datatable.getDataParameter("Country");
		String email = datatable.getDataParameter("Email");
		String reservationNumber = datatable.getDataParameter("ReservationNumber");
		String resort = datatable.getDataParameter("Resort");
		String arrival = datatable.getDataParameter("Arrival");
		String existingRes = datatable.getDataParameter("ExistingReservation");
		String searchExistingGuest = datatable.getDataParameter("SearchExistingGuest");
		String createNewGuest = datatable.getDataParameter("CreateNewGuest");
		String invalidGuestSearch = datatable.getDataParameter("InvalidGuestSearch");
		String selectGuest = datatable.getDataParameter("SelectGuest");

		// Determine which search flow is needed
		if (existingRes.toLowerCase().equalsIgnoreCase("true")) {
			txtReservationNumber.set(reservationNumber);
			if (!resort.equalsIgnoreCase("")) {
				lstResort.select(resort);
			}
			txtArrivalDate.set(arrival);
			chkExistingRes.checkValidate(driver);
			clickFind();
		} else if (createNewGuest.toLowerCase().equalsIgnoreCase("true")
				|| searchExistingGuest.toLowerCase().equalsIgnoreCase("true")) {
			
			pageLoaded(txtFirstName);
			txtFirstName.focus(driver);
			txtFirstName.setValidate(driver, firstName);
			txtLastName.setValidate(driver, lastName);
			txtPostalCode.set(postalCode);
			txtPhoneNumber.set(phoneNumber);
			lstCountry.select(country);
			txtEmail.set(email);

			clickFind();

			// Check if the search results are invalid
			if (invalidGuestSearch.toLowerCase().equalsIgnoreCase("true")) {
				checkInvalidGuestSearch();
			} else if (createNewGuest.toLowerCase().equalsIgnoreCase("true")) {
				if (selectGuest.toLowerCase().equalsIgnoreCase("true")) {
					getGuestSearchResultsValue();
					selectGuestSearchRecord();

				} else {

					clickCreateNewGuest();
				}

			} else if (searchExistingGuest.toLowerCase().equalsIgnoreCase("true")) {
				selectGuest();
			}
		}

	}

	private void selectGuest() {

	}

	public void clickLogout() {
		lnkLogout.click();
	}

	public void clickClearSearch() {
		Sleeper.sleep(3000);
		initialize();
		btnClear.isDisplayed();
		btnClear.click();
		initialize();
		pageLoaded(txtFirstName);
		txtFirstName.focus(driver);
	}

	public void checkInvalidGuestSearch() {

		initialize();
		pageLoaded();

		TestReporter.assertTrue((eleGuestError.isDisplayed()) || (eleGuestNoResults.isDisplayed()),
				"Guest Search not displayed");
		TestReporter.logStep(
				"Please enter last name and one or more of the following: postal code, phone, email to get valid Guest Information");

	}

	public void selectGuestSearchRecord() {
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.id("guestResultForm:guestProfileResultsTable:f:0"));

		// Double click
		action.doubleClick(element).perform();
		PleaseWait.WaitForPleaseWait(driver);
	}

	public boolean verify_ExistingReservation(String ResNo) {
		boolean Returnvalue = false;
		WebElement ele = driver.findElement(By.id("ReservationResultByGuestForm:ReservationResultsByGuestTable:n"));
		List<WebElement> trs = ele.findElements(By.tagName("tr"));
		for (WebElement tr : trs) {
			if (tr.getText().contains(ResNo)) {
				Returnvalue = true;
				break;
			}
		}
		return Returnvalue;
	}

	/**
	 * @summary : Method to click on SearchGuest tab.
	 * @author : Praveen Namburi.
	 * @version : Created 1/4/2015.
	 */
	public void clickSearchGuestTab() {
		pageLoaded(eleSearchGuestTab);
		eleSearchGuestTab.syncEnabled(driver);
		eleSearchGuestTab.highlight(driver);
		eleSearchGuestTab.click();
	}

	/**
	 * @summary : Method to capture the Guest search result value.
	 * @author : Praveen Namburi , @version : Created 1/4/2015.
	 */
	public void getGuestSearchResultsValue() {
		pageLoaded(eleGetGuestSearchResults);
		String GuestsearchResult = eleGetGuestSearchResults.getText();
		TestReporter.logStep("Guest Search results count is : " + GuestsearchResult);
	}

}


