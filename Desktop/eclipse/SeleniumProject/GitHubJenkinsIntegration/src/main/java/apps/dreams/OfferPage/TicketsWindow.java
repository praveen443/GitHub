package apps.dreams.OfferPage;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.dreams.PleaseWait.PleaseWait;
import core.WindowHandler;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.ButtonImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

/**
 * @summary Contains the methods & objects for the Dreams UI Tickets Window
 * @version Created 11/05/2014
 * @author Waightstill W. Avery
 */

public class TicketsWindow {
	// ****************************
	// *** Content Frame Fields ***
	// ****************************
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	private String winHandleBefore;

	// ******************************
	// *** Content Frame Elements ***
	// ******************************

	@FindBy(name = "b_Yes")
	private Button btnYes;

	// OK button
	@FindBy(name = "Ok")
	private Button btnOk;

	// OK button
	@FindBy(xpath = "//INPUT[contains(@value,'Ok')]")
	private Button btnOK;

	// Bypass Tickets button
	@FindBy(name = "b_SkipDory")
	private Button btnBypassTickets;

	// Perform Discovery button
	@FindBy(name = "Perform Discovery")
	private Button btnPerformDiscovery;

	// Perform Discovery button
	@FindBy(id = "LkUp1But")
	private Button btnperformDiscovery;

	// Select All button
	@FindBy(xpath = "//INPUT[contains(@value,'Select')]")
	private Button btnSelect;

	// Select All button
	@FindBy(name = "b_Select")
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

	// Ticket Selection checkbox
	@FindBy(id = "baseTicketSelected")
	private Checkbox chkTicketSelection;

	// *********************
	// ** Build page area **
	// *********************

	/**
	 * 
	 * @summary Constructor to initialize the frame
	 * @version Created 11/05/2014
	 * @author Waightstill W Avery
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public TicketsWindow(WebDriver driver) {
		winHandleBefore = driver.getTitle();
		new WindowHandler().swapToWindow(driver, "Ticketing");
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnSelectAll);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				element);
	}

	public TicketsWindow initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	// *****************************************
	// *** Content Frame Interactions ***
	// *****************************************
	public void highlightObjects() {

		/*
		 * Element element = new
		 * ElementImpl(driver.findElement(By.xpath("//input[contains(@name,'Ok')"
		 * ))); List<WebElement> list =
		 * driver.findElements(By.xpath("//input[contains(@type,'button')"));
		 */

		btnBypassTickets.highlight(driver);
		btnOk.highlight(driver);
		btnPerformDiscovery.highlight(driver);
		btnSelectAll.highlight(driver);
		chkAccommodation.highlight(driver);
		lstNumberOfDays.highlight(driver);
		lstTicketGroup.highlight(driver);
		txtTicketDescription.highlight(driver);

		clickBypassTickets();

		swapToParentWindow();
	}

	public void swapToParentWindow() {
		WindowHandler.swapToWindowWithTimeout(driver, winHandleBefore, 10);
	}

	private void clickBypassTickets() {
		pageLoaded(btnBypassTickets);
		btnBypassTickets.jsClick(driver);
		PleaseWait.WaitForPleaseWaitWithTimeout(driver);

		// String Child_window_title;
		String Parent_Window_Handle = driver.getWindowHandle();
		String Child_Window_Handle = null;
		Set<String> s = driver.getWindowHandles();

		Iterator<String> itr = s.iterator();
		while (itr.hasNext()) {
			String temp_Handle = itr.next();
			if (!(temp_Handle.equalsIgnoreCase(Parent_Window_Handle))) {
				Child_Window_Handle = temp_Handle;
			}
			/*
			 * else { Child_Window_Handle = temp_Handle; }
			 */
		}
		driver.switchTo().window(Child_Window_Handle);
		// Child_window_title = driver.getTitle();
		clickYes();
		driver.switchTo().window(Parent_Window_Handle);

	}

	/**
	 * @summary Method to click Yes button from Warning window
	 * @version Created 02/08/2016
	 * @author Marella Satish
	 * @param NA
	 * @throws NA
	 * @return NA
	 */
	public void clickYes() {
		pageLoaded(btnYes);
		btnYes.jsClick(driver);
	}

	/**
	 * @summary Method to click ByPassTickets
	 * @version Created 02/08/2016
	 * @author Marella Satish
	 * @param NA
	 * @throws NA
	 * @return NA
	 */
	public void clickBypassTkts() {
		pageLoaded(btnBypassTickets);
		btnBypassTickets.highlight(driver);
		btnBypassTickets.click();
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 3);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "WARNING");
		clickYes();
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Disney Reservation Entry and Management System");
	}

	/**
	 * @Summary: Method for package Ticket Selection
	 * @Precondition: N/A
	 * @Author: Chinagudaba Pawan
	 * @Version: N/A
	 * @Return: N/A
	 */
	public void selectTicket(String scenario) {
		datatable.setVirtualtablePage("Tickets");
		datatable.setVirtualtableScenario(scenario);
		String numberofDays = datatable.getDataParameter("NumberOfDays");
		String twoTickets = datatable.getDataParameter("TwoTickets");
		String ticketDescription = datatable
				.getDataParameter("TicketDescription");

		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,"Present Offers-Ticketing");
		
		if(twoTickets.equalsIgnoreCase("true")){
			chkAccommodation.click();
			List<WebElement> checkbox = driver.findElements(By.name("accommodationToList"));
	        ((WebElement) checkbox.get(1)).click();
		}else{
			if(chkAccommodation.isEnabled())chkAccommodation.click();
		}
		
		PleaseWait.WaitForPleaseWait(driver);
		pageLoaded(lstNumberOfDays);
		lstNumberOfDays.select(numberofDays);

		pageLoaded(txtTicketDescription);
		txtTicketDescription.safeSet(ticketDescription);

		btnperformDiscovery.jsClick(driver);
		pageLoaded(chkTicketSelection);
		chkTicketSelection.jsClick(driver);
		getButtons("Select");

		PleaseWait.WaitForPleaseWait(driver);
		btnOK.jsClick(driver);
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Disney Reservation Entry and Management System");

	}

	
	/**
	 * @Summary: Method for reading specified buttons
	 * @Precondition: N/A
	 * @Author: Chinagudaba Pawan
	 * @Version: N/A
	 * @Return: N/A
	 */

	// Reading web element type button
	public void getButtons(String inputButtonValue) {
		List<WebElement> buttons = driver.findElements(By.tagName("input"));
		for (WebElement button : buttons) {
			if (button.getAttribute("value").equalsIgnoreCase(inputButtonValue)) {
				Button btn = new ButtonImpl(button);
				btn.jsClick(driver);
				break;
			}
		}
	}

}
