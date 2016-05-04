package apps.dreams.OfferPage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.dreams.PleaseWait.PleaseWait;
import core.FrameHandler;
import core.WindowHandler;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Link;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.CheckboxImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

/**
 * @summary Contains the methods & objects for the Dreams UI content frame for
 *          offers
 * @version Created 09/10/2014
 * @author Waightstill W. Avery
 */
public class OfferPageContentFrame {

	// ******************************
	// *** Content Frame Elements ***
	// ******************************
	private String winHandleBefore;
	private Datatable datatable = new Datatable();
	private WebDriver driver;

	// ************///////
	@FindBy(name = "b_Select")
	private Button btnSelectOffer;

	@FindBy(id = "selectedForFreeze")
	private Checkbox chkSelectOffer;

	// Brajesh ahirwar added on 20/03 8:32

	// OK button
	@FindBy(name = "Ok")
	private Button btnOk;

	// Bypass Tickets button
	@FindBy(name = "Bypass Tickets")
	private Button btnBypassTickets;

	// Perform Discovery button
	@FindBy(xpath = "//input[@value='Perform Discovery']")
	private Button btnPerformDiscovery;

	// Select All button
	@FindBy(name = "Select All")
	private Button btnSelectAll;

	// Accommodation checkbox
	@FindBy(name = "accommodationToList")
	private Checkbox chkAccommodation;

	// Ticket Group listbox
	@FindBy(name = "discoveryTicketPreferenceTO.ticketGroup")
	private Listbox lstTicketGroup;

	// Number of Days listbox
	@FindBy(name = "discoveryTicketPreferenceTO.numOfDays")
	private Listbox lstNumberOfDays;

	// Ticket Description textbox
	@FindBy(name = "discoveryTicketPreferenceTO.ticketDescription")
	private Textbox txtTicketDescription;

	@FindBy(xpath = "//form[@name='OfferForm']//table[5]/tbody/tr[2]/td[1]/div/input")
	private Textbox txtText;

	// Accommodation checkbox Select All
	@FindBy(name = "travelPlanTO.currentTravelPlanSegment.facilityOrderTOList[0].accommodationToList[0].selected")
	private Checkbox chktravelPlanTO;

	@FindBy(name = "travelPlanTO.currentTravelPlanSegment.facilityOrderTOList[0].accommodationToList[0].roomType.guaranteedByForDisplay")
	private Listbox lstGuaranteedBy;

	@FindBy(linkText  = "*")
	private Link lnkTaxExempt;

	@FindBy(name = "taxExemptDetails.taxExemptType")
	private  Listbox lstTaxExemptType;

	@FindBy(name = "taxExemptDetails.taxExemptCertificateNumber")
	private Textbox txtTaxExemptId;

	@FindBy(xpath = "//*[@class= 'button']  [@value='Ok']")
	private  Button btnTaxOk;

	@FindBy(id  = "presentedoffer")
	private Link lnkPresentOffers;

	@FindBy(name = "vipLevelForDisplay")
	private Listbox lstVipLevel;

	@FindBy(name = "contactName")
	private Textbox txtContactName;

	@FindBy(name = "b_Re-Discovery")
	private Button btnReDiscovery;

	// Ticket Selection checkbox
	@FindBy(id = "baseTicketSelected")
	private Checkbox chkTicketSelection;
	// *********************
	// ** Build page area **
	// *********************

	/**
	 * 
	 * @summary Constructor to initialize the frame
	 * @version Created 09/10/2014
	 * @author Waightstill W Avery
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public OfferPageContentFrame(WebDriver driver) {
		this.driver = driver;
		FrameHandler.findAndSwitchToFrame(driver, "contentFrame");
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnSelectOffer);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	public OfferPageContentFrame initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	// *****************************************
	// *** Content Frame Interactions ***
	// *****************************************

	/**
	 * 
	 * @summary Method to select an offer. Calls other methods such as
	 *          selectDefaultOffer() based on the scenario
	 * @version Created 09/10/2014
	 * @author Waightstill W Avery
	 * @param scenario
	 *            - key for choosing which offer to select
	 * @throws Exception
	 *             surrounding datatable access
	 * @return NA
	 * 
	 */
	public void selectOffer(String scenario)  {
		datatable.setVirtualtablePage("OffersPage");
		datatable.setVirtualtableScenario(scenario);
		String selectOfferType = datatable.getDataParameter("SelectOffer");
		System.out.println("Offer type :" + selectOfferType);
		switch (selectOfferType) {
		case "First":
			selectDefaultOffer();
			break;
		default:
			checkOfferCheckbox(selectOfferType);
			break;
		}
	}

	/**
	 * 
	 * @summary Method to select the default, or top-most, offer and then clicks
	 *          select
	 * @version Created 09/10/2014
	 * @author Waightstill W Avery
	 * @param NA
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void selectDefaultOffer() {
		pageLoaded(chkSelectOffer);
		chkSelectOffer.jsToggle(driver);
		clickSelect();
	}

	private void clickSelect() {
		pageLoaded(btnSelectOffer);
		// ((JavascriptExecutor)driver).executeScript("document.getElementById(\"b_Select\").click();");
		btnSelectOffer.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
		// Sleeper.sleep(3000);

	}

	public void travelPlanTO() {
		List<WebElement> allFormChildElements = driver.findElements(By.xpath("//form[@name='OfferForm']/*"));

		for (WebElement item : allFormChildElements) {

			if (item.getTagName().equals("input")) {
				switch (item.getAttribute("type")) {
				case "text":
					System.out.println("text" + item.getText());
					break;
				case "checkbox":
					System.out.println("chkbox" + item.getText());
					break;
					// and so on
				}
			} else if (item.getTagName().equals("select")) {
				// select an item from the select list
			}
		}

	}

	public void travelPlanGuranteedBy() {

		txtText.jsClick(driver);

	}

	public void swapToParentWindow() {
		WindowHandler.swapToWindowWithTimeout(driver, winHandleBefore, 10);
	}

	/**
	 * 
	 * @summary Method to select an offer based on some criteria (room type, ADA
	 *          accessible, etc)
	 * @version Created 09/10/2014
	 * @author Waightstill W Avery
	 * @param scenario
	 *            - key for choosing which offer to select
	 * @throws NA
	 * @return NA
	 * 
	 */
	private void checkOfferCheckbox(String scenario)  {
		// TODO write code to select check box by room type
	}

	/**
	 * @summary Method to enter all the reservation details in offers page
	 * @version Created 02/08/2016
	 * @author Marella Satish
	 * @param Datatable scenario
	 * @throws NA
	 * @return NA
	 */
	public void enterReservationDetails(String scenario) { 
		datatable.setVirtualtablePage("OffersPage");
		datatable.setVirtualtableScenario(scenario);
		String contactName = datatable.getDataParameter("ContactName");
		String vipLevel = datatable.getDataParameter("VIPLevel");
		String taxExemptStatus = datatable.getDataParameter("TaxExempt");

		pageLoaded(txtContactName);
		txtContactName.safeSet(contactName);

		pageLoaded(lstVipLevel);
		lstVipLevel.select(vipLevel);
		PleaseWait.WaitForPleaseWait(driver);
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,"Disney Reservation Entry and Management System");

		if (taxExemptStatus.equalsIgnoreCase("TRUE")) {
			enterTaxExemptDetails(scenario);
		}
	}

	/**
	 * @summary Method to click TaxExemptType link
	 * @version Created 02/08/2016
	 * @author Marella Satish
	 * @param NA
	 * @throws NA
	 * @return NA
	 */
	public void clickTaxExemptType(){
		pageLoaded(lnkTaxExempt);
		lnkTaxExempt.highlight(driver);
		lnkTaxExempt.click();
	}

	/**
	 * @summary Method to enter Tax Exempt Details
	 * @version Created 02/08/2016
	 * @author Marella Satish
	 * @param Datatable scenario
	 * @throws NA
	 * @return NA
	 */
	public void enterTaxExemptDetails(String scenario) {
		datatable.setVirtualtablePage("OffersPage");
		datatable.setVirtualtableScenario(scenario);

		String taxExemptType = datatable.getDataParameter("TaxExemptType");
		String taxExemptId = datatable.getDataParameter("TaxExemptID");

		clickTaxExemptType();

		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,"TaxExemptDetailsUI");

		pageLoaded(lstTaxExemptType);
		lstTaxExemptType.select(taxExemptType);

		pageLoaded(txtTaxExemptId);
		txtTaxExemptId.safeSet(taxExemptId);

		clickOk();
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,"Disney Reservation Entry and Management System");
		clickPresentOffers();
	}

	/**
	 * @summary Method to click OK button
	 * @version Created 02/08/2016
	 * @author Marella Satish
	 * @param NA
	 * @throws NA
	 * @return NA
	 */
	public void clickOk() {
		btnTaxOk.highlight(driver);
		pageLoaded(btnTaxOk);
		btnTaxOk.click();
	}

	/**
	 * @summary Method to click Present Offers link
	 * @version Created 02/08/2016
	 * @author Marella Satish
	 * @param NA
	 * @throws NA
	 * @return NA
	 */
	public void clickPresentOffers() {
		lnkPresentOffers.highlight(driver);
		pageLoaded(lnkPresentOffers);
		lnkPresentOffers.click();
	}

	public void clickReDiscovery() {
		pageLoaded(btnReDiscovery);
		btnReDiscovery.highlight(driver);
		btnReDiscovery.click();
		PleaseWait.WaitForPleaseWait(driver);

	}

	/**
	 * @summary Method to Click on New button of KTTW Tickets
	 * @version Created 03/22/2016
	 * @author Sabitha Adama
	 */
	//Method to set values of New KTTW Tickets
	public void setTicketInfo(String scenario) {
		datatable.setVirtualtablePage("Tickets");
		datatable.setVirtualtableScenario(scenario);
		String numberofDays = datatable.getDataParameter("NumberOfDays");
		String ticketDescription = datatable
				.getDataParameter("TicketDescription");
		String ticketGroup=datatable.getDataParameter("TicketGroup");
		String ticketOption=datatable.getDataParameter("TicketOption");
		
		pageLoaded(lstTicketGroup);
		lstTicketGroup.select(ticketGroup);

		pageLoaded(lstNumberOfDays);
		lstNumberOfDays.select(numberofDays);

		pageLoaded(txtTicketDescription);
		txtTicketDescription.safeSet(ticketDescription);

		btnPerformDiscovery.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);

		if(ticketOption.isEmpty()){
			pageLoaded(chkTicketSelection);
			chkTicketSelection.highlight(driver);
			chkTicketSelection.click();
		}else{
			pageLoaded(chkTicketSelection);
			Checkbox ticket = new CheckboxImpl(driver.findElement(By.xpath("//*[contains(text(),'" + ticketOption +"')]/input[@id='baseTicketSelected']")));
			ticket.highlight(driver);
			ticket.check();
		}

		getButtons("javascript:doSelectClicked('TICKET')");

	}

	/**
	 * @summary Method to Click on New button of KTTW Tickets
	 * @version Created 03/23/2016
	 * @author Sabitha Adama
	 */
	// Reading web element type button
	public void getButtons(String inputButtonValue) {
		List<WebElement> buttons = driver.findElements(By.name("b_Select"));
		for (WebElement button : buttons) {
			if (button.getAttribute("onclick").equalsIgnoreCase(inputButtonValue)) {
				button.click();
				break;
			}
		}
	}
}

