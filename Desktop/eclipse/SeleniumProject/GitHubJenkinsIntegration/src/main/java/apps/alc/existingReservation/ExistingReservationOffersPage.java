package apps.alc.existingReservation;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.alc.pleaseWait.PleaseWait;
import core.WebDriverSetup;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.TestReporter;

/**
 * This class contains elements and element interactions for a given page of a
 * web application
 * 
 * @author Waightstill W Avery
 * @version 12/15/2015 Waightstill W Avery - original
 */
public class ExistingReservationOffersPage {
	// *******************
	// Page Class Fields
	// *******************
	// Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private StopWatch stopwatch = new StopWatch();
	private Datatable datatable = new Datatable();
	// *********************
	// Page Class Elements
	// *********************
	@FindBy(id = "modifyofferSetForm:OfferSet_ShoppingCart")
	private Button btnGoToShoppingCart;

	@FindBy(id = "modifyofferSetForm:modifyofferSetOutputPanel")
	private Element eleOffersTable;

	@FindBy(id = "modifyofferSetForm:modifyofferSetRepeat:0:modifythawCommandButton")
	private Button btnDefaultOfferRelease;

	@FindBy(id = "modifyofferSetForm:modifyofferSetRepeat:0:modifyfreezeCommandButton")
	private Button btnDefaultOfferHold;

	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
	 * @param driver
	 */
	public ExistingReservationOffersPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public ExistingReservationOffersPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnGoToShoppingCart);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
	 * @param element
	 *            - Element to be used to determine if the page is loaded
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	// ********************
	// Page Class Methods
	// ********************
	public void goToShoppingCart() {
		pageLoaded(btnGoToShoppingCart);
		btnGoToShoppingCart.highlight(driver);
		btnGoToShoppingCart.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void selectOfferByVenue(String venue) {
		boolean offerFound = false;
		boolean defalutOfferIsPreferredOffer = false;
		WebElement preferredOffer = null;
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		List<WebElement> offers = eleOffersTable.findElements(By.xpath("div/table/tbody/tr/td/table"));
		for (WebElement offer : offers) {
			if (offer.getText().toLowerCase().contains(venue.toLowerCase())
					&& new ElementImpl(offer).syncVisible(driver, 1, false)) {
				offerFound = true;
				preferredOffer = offer;
				break;
			}
		}
		TestReporter.assertTrue(offerFound, "No offer was found to have the venue [" + venue + "].");

		// Determine if the default offer is the preferred offer by searching
		// for the "Release" button
		WebElement release = driver.findElement(By.cssSelector("input[@value = 'Release']"));
		if (release != null) {
			defalutOfferIsPreferredOffer = new ElementImpl(release).syncVisible(driver, 3, false);
		}
		// If the default offer is the preferred offer, continue to the shopping
		// cart. Otherwise, release the default offer, hold the preferred offer,
		// and continue to the shopping cart
		if (!defalutOfferIsPreferredOffer) {
			releaseDefaultOffer();
			quotePreferredOffer(preferredOffer);
			holdPreferredOffer(preferredOffer);
		}
		clickGoToShoppingCart();
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
	}

	private void clickGoToShoppingCart() {
		initialize();
		pageLoaded(btnGoToShoppingCart);
		btnGoToShoppingCart.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	private void releaseDefaultOffer() {
		initialize();
		pageLoaded(btnDefaultOfferRelease);
		btnDefaultOfferRelease.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
		stopwatch.start();
		do {
			try {
				initialize();
				btnDefaultOfferHold.syncVisible(driver, 1, false);
			} catch (Exception e) {
				initialize();
			}
		} while (btnDefaultOfferHold == null && stopwatch.getTime() < 20000);
		stopwatch.stop();
		stopwatch.reset();
	}

	private void quotePreferredOffer(WebElement preferredOffer) {
		Element order = new ElementImpl(preferredOffer);
		initialize();
		pageLoaded(order);
		Element quoteOffer = new ElementImpl(order.findElement(By.cssSelector("input[@value = 'QuoteOffer']")));
		quoteOffer.highlight(driver);
		quoteOffer.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);

		Element holdOffer = null;
		stopwatch.start();
		do {
			try {
				holdOffer = new ElementImpl(order.findElement(By.cssSelector("input[@value = 'Hold']")));
			} catch (Exception e) {
				initialize();
			}
		} while (holdOffer == null && stopwatch.getTime() < 20000);
		stopwatch.stop();
		stopwatch.reset();
	}

	private void holdPreferredOffer(WebElement preferredOffer) {
		Element order = new ElementImpl(preferredOffer);
		initialize();
		pageLoaded(order);
		Element holdOffer = new ElementImpl(order.findElement(By.cssSelector("input[@value = 'Hold']")));
		holdOffer.highlight(driver);
		holdOffer.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);

		Element releaseOffer = null;
		stopwatch.start();
		do {
			try {
				releaseOffer = new ElementImpl(order.findElement(By.cssSelector("input[@value = 'Release']")));
			} catch (Exception e) {
				initialize();
			}
		} while (releaseOffer == null && stopwatch.getTime() < 20000);
		stopwatch.stop();
		stopwatch.reset();
	}
	
	public void selectOffer(String scenario) {
		/*
		 * datatable.setDatatableSheet("Offers");
		 * datatable.setDatatableRow(datatable.getRowContains(scenario, 0));
		 */
		datatable.setVirtualtablePage("Offers");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded();

		if (datatable.getDataParameter("Facility").toLowerCase().equalsIgnoreCase("any")) {
			clickShoppingCart();
		}
	}
		
	public void clickShoppingCart(){
		clickGoToShoppingCart();
	}
}

