package apps.alc.newReservation;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import apps.alc.pleaseWait.PleaseWait;
import core.WebDriverSetup;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Label;
import core.interfaces.Link;
import core.interfaces.RadioGroup;
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
 * @version Created 10/02/2014
 * @author Waightstill W Avery
 */

public class Offers {
	// *********************
	// *** Offers Fields ***
	// *********************
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	
	// ************************************
	// *** Main Page Elements ***
	// ************************************
	@FindBy(id = "offerSetForm:shoppingCartButton")
	private Button btnGoToShoppingCart;

	@FindBy(xpath = "//*[@id=\"offerSetForm:offerSetOutputPanel\"]/div/table")
	private Webtable tblOffersTable;

	@FindBy(id = "offerSetForm:offerSetRepeat:0:offerSynopsis")
	private Label lblDefaultOffer;

	@FindBy(id = "offerSetForm:offerSetRepeat:0:thawCommandButton")
	private Button btnDefaultReleaseButton;

	@FindBy(id = "offerSetForm:offerSetRepeat:0:freezeCommandButton")
	private Button btnDefaultHoldButton;

	// Table that contains all of the details in offers page
	@FindBy(xpath = ".//*[@id='offerSetForm:offerSetOutputPanel']/div/table/tbody/tr/td/table/")
	private Element eleOffersTable;

	// To choose the first radio btn to hold the selected offer
	@FindBy(xpath = ".//*[@id='tseExactMatchForm']/table/tbody/tr/td[1]/input")
	private RadioGroup radSelectOfferTime1;

	// To choose the second radio btn to hold the selected offer
	@FindBy(xpath = ".//*[@id='tseExactMatchForm']/table/tbody/tr/td[2]/input")
	private RadioGroup radSelectOfferTime2;

	// Buton Hold for the selected offer
	@FindBy(xpath = "//*[@id='tseExactMatchForm']/table/tbody/tr/td[4]/input[2]")
	private Button btnHoldForSelectedOffer;

	// Button GoToCart
	@FindBy(id = "tseResults:tseGoToCart")
	private Button btnGoToCart;

	// To select the first timeslot in first row from the offers table
	@FindBy(xpath = ".//*[@id='offerList']/tr[1]/td/table/tbody/tr/td[1]/input")
	private RadioGroup radSelectFirstTimeSlot;

	// To click on the link - All Clear Filters
	@FindBy(id = "tseClearFilters")
	private Link lnkClearFilters;

	// WebElements List in OffersSetform
	@FindBy(xpath = ".//*[@id='offerSetForm']//div/table/tbody/tr/td/table")
	private List<WebElement> eleOffersSetform;

	// *********************
	// ** Build page area **
	// *********************

	public Offers(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}

	public Offers initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnGoToShoppingCart);
	}

	/**
	 * @summary : Method to determine if the Offers page is loaded.
	 * @author : Praveen Namburi
	 * @version : 01/22/2016
	 * @return : Boolean, true if the page is loaded, false otherwise
	 */
	public boolean IsPageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnGoToCart);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				element);
	}

	// **************************************
	// *** Main CheckIn Page Interactions ***
	// **************************************

	public void selectOffer(String scenario) {
		/*
		 * datatable.setDatatableSheet("Offers");
		 * datatable.setDatatableRow(datatable.getRowContains(scenario, 0));
		 */
		datatable.setVirtualtablePage("Offers");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded();

		if (datatable.getDataParameter("Facility").toLowerCase()
				.equalsIgnoreCase("any")) {
			selectFirstOffer();
		}
	}

	private void selectFirstOffer() {
		clickGoToShoppingCart();
	}

	private void clickGoToShoppingCart() {
		pageLoaded(btnGoToShoppingCart);
		btnGoToShoppingCart.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	public String getDefaultOfferValue() {
		pageLoaded(lblDefaultOffer);
		String defaultOfferValue = lblDefaultOffer.getText();
		TestReporter.log("Default Offer Value : " + defaultOfferValue);
		btnGoToShoppingCart.syncEnabled(driver);
		btnGoToShoppingCart.focusClick(driver);
		return defaultOfferValue;
	}

	/**
	 * @summary Method to select the Release button for the first selected offer
	 * @version Created 1/04/2016
	 * @author Stagliano, Dennis
	 * @return NA
	 * @param NA
	 */
	public void releaseFirstSelectedOffer() {
		pageLoaded(btnDefaultReleaseButton);
		btnDefaultReleaseButton.syncEnabled(driver);
		Sleeper.sleep(1000);
		btnDefaultReleaseButton.focusClick(driver);
	}

	/**
	 * @summary Method to select the Hold button for the first selected offer
	 * @version Created 1/04/2016
	 * @author Stagliano, Dennis
	 * @return NA
	 * @param NA
	 */
	public void holdFirstSelectedOffer() {
		pageLoaded(btnDefaultReleaseButton);
		btnDefaultHoldButton.syncEnabled(driver);
		Sleeper.sleep(1000);
		btnDefaultHoldButton.focusClick(driver);
	}

	// public void getDefaultOfferDetails(){

	// List<WebElement> offers =
	// eleTableDefaultOffers.findElements(By.tagName("td"));
	// System.out.println("Number of cells " + offers.size());

	// }
	/**
	 * @summary : Method to click on button Go-To-Cart.
	 * @author: Praveen Namburi, @version: 1-22-2016
	 */
	public void clickGoToCart() {
		pageLoaded(btnGoToCart);
		btnGoToCart.syncEnabled(driver);
		btnGoToCart.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	/**
	 * @Summary: Methd to choose the offer time and click Hold button for the
	 *           selected offer and Go-To-Cart.
	 * @author: Praveen Namburi, @version: Created 1-26-2016
	 * @param scenario
	 */
	public void selectTheOfferAndGotoCart(String scenario) {
		datatable.setVirtualtablePage("Offers");
		datatable.setVirtualtableScenario(scenario);

		WebElement exactMatchTable = null;
		try {
			exactMatchTable = new WebDriverWait(driver, 5)
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("tseExactMatchArea")));
		} catch (TimeoutException toe) {
		}

		if (exactMatchTable != null) {
			// Determine if an exact match was found. If so, select the first
			// time available
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			if (exactMatchTable != null) {
				List<WebElement> timeSlots = exactMatchTable.findElements(By
						.name("matchingTimeSlot"));
				TestReporter.assertGreaterThanZero(timeSlots.size());
				for (WebElement timeSlot : timeSlots) {
					Element slot = new ElementImpl(timeSlot);
					if (slot.syncVisible(driver, 1, false)) {
						slot.click();
						break;
					}
				}
			}

			List<WebElement> holds = exactMatchTable.findElements(By
					.cssSelector("input[value='Hold']"));
			for (WebElement hold : holds) {
				Button button = new ButtonImpl(hold);
				if (button.syncVisible(driver, 1, false)) {
					button.jsClick(driver);
					PleaseWait.WaitForPleaseWait(driver, 60);
					break;
				}

			}
			// If an exact match was not found, select the first offer in the
			// offers table
		} else {

			initialize();
			if (lnkClearFilters.syncVisible(driver, 4, false)) {
				lnkClearFilters.highlight(driver);
				lnkClearFilters.jsClick(driver);
				PleaseWait.WaitForPleaseWait(driver);
			}

			WebElement offers = driver.findElement(By
					.cssSelector("table[id='offers']"));
			TestReporter.assertNotNull(offers,
					"The offers table was not found.");

			// TODO Grab the first row in the table
			// TODO Select the first time in that row
			// TODO Click the 'Hold' in that row
			List<WebElement> totalRows = offers.findElements(By
					.xpath("tbody/tr"));
			TestReporter.logStep("Total no. of rows in Offers table : "
					+ totalRows.size());
			TestReporter.assertGreaterThanZero(totalRows.size());

			for (@SuppressWarnings("unused") WebElement row : totalRows) {
				WebElement offerList = driver.findElement(By
						.xpath(".//*[@id='offerList']"));
				List<WebElement> inputs = offerList.findElements(By
						.tagName("input"));
				for (WebElement input : inputs) {
					Element element = new ElementImpl(input);
					element.highlight(driver);
					element.jsClick(driver);
					Button button = new ButtonImpl(driver.findElement(By
							.cssSelector("input[value='Hold']")));
					if (button.syncVisible(driver, 2, false)) {
						button.highlight(driver);
						button.jsClick(driver);
						PleaseWait.WaitForPleaseWait(driver);
						break;
					}
					break;
				}
				break;
			}
		}
		driver.manage()
				.timeouts()
				.implicitlyWait(WebDriverSetup.getDefaultTestTimeout(),
						TimeUnit.SECONDS);

		try {
			new PageLoaded().isDomComplete(driver);
		} catch (Exception e) {
			Sleeper.sleep(2000);
			initialize();
		}

		// Click on GoToCart button.
		clickGoToCart();

	}

	/**
	 * @Summary: Method to click on the hold button that contains the row with
	 *           the text - WILLL CALL.
	 * @author: praveen Namburi, @version: Created 2-8-2016
	 */
	public void selectCirqueOfferAndGoToCart(String scenario) {
		datatable.setVirtualtablePage("Offers");
		datatable.setVirtualtableScenario(scenario);
		System.out.println(scenario);

		WebElement offersTable = driver.findElement(By
				.cssSelector("span[id='offerSetForm:offerSetOutputPanel']"));
		TestReporter.assertNotNull(offersTable,
				"The offers table was not found.");

		int totalRows = eleOffersSetform.size();
		TestReporter.log("Total no. of rows in Offers table : " + totalRows);
		TestReporter.assertGreaterThanZero(totalRows);

		for (int row = 0; row <= totalRows; row++) {
			String elementID = ".//*[@id='offerSetForm:offerSetRepeat:" + row
					+ ":offerItemSynopsis']/table/tbody/tr[1]";

			WebElement getTR = driver.findElement(By.xpath(elementID));
			String inputText = getTR.getText();
			if (inputText.contains(datatable.getDataParameter("ChooseOffer"))) {
				TestReporter
						.log("Clicking on the hold button for the row that contains the text ["
								+ datatable.getDataParameter("ChooseOffer")
								+ "].");
				String holdbtn = "offerSetForm:offerSetRepeat:" + row
						+ ":freezeCommandButton";
				Button button = new ButtonImpl(driver.findElement(By
						.id(holdbtn)));
				if (button.syncVisible(driver, 2, false)) {
					button.scrollIntoView(driver);
					button.highlight(driver);
					button.jsClick(driver);
					PleaseWait.WaitForPleaseWait(driver, 10, false);
				}
				driver.manage()
						.timeouts()
						.implicitlyWait(WebDriverSetup.getDefaultTestTimeout(),
								TimeUnit.SECONDS);
				clickGoToShoppingCart();
				break;
			}
		}
	}
}

