package apps.lilo.WillCall;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequestPopup;
import core.AlertHandler;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.TextboxImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;

/**
 * This class contains elements and element interactions for the Bell Service
 * page in Lilo UI
 * 
 * @author Venkatesh Athmakuri
 * @version 12/07/2015 Venkatesh Athmakuri - original
 */

public class WillCallTicketsPage {
	// *******************
	// Page Class Fields
	// *******************
	// Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	private Textbox txtFirstName;
	private Textbox txtLastName;
	private Textbox txtPostalCode;

	// *********************
	// Page Class Elements
	// *********************

	// Find Tickets Button
	@FindBy(id = "willCallTicketForm:willCallTicketsButton")
	private Button btnFindTickets;

	// Cancel Ticket Button
	@FindBy(id = "willCallTicketForm:cancelWillCallTicketsButton")
	private Button btnCancel;

	//Will Call Tickets left panel table
	@FindBy(xpath = "//span[@id='willCallTicketForm:searchWillCallTicketPanel']/table/tbody/tr[1]/td/table/tbody")
	private Webtable tblWillCallTicketsTable;
	
	//Will Call Tickets Add Guest button
	@FindBy(id = "willCallTicketForm:addGuests")
	private Button btnAddGuest;

	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Venkatesh Athmakuri
	 * @version 12/07/2015 Venkatesh Athmakuri - original
	 * @param driver
	 */
	public WillCallTicketsPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Venkatesh Athmakuri
	 * @version 12/07/2015 Venkatesh Athmakuri - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public WillCallTicketsPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Venkatesh Athmakuri
	 * @version 12/07/2015 Venkatesh Athmakuri - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnFindTickets);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Venkatesh Athmakuri
	 * @version 12/07/2015 Venkatesh Athmakuri - original
	 * @param element
	 *            - Element to be used to determine if the page is loaded
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				element);
	}

	// ********************
	// Page Class Methods
	// ********************

	public void clickFindTicketsButton() {
		pageLoaded(btnFindTickets);
		btnFindTickets.highlight(driver);
		btnFindTickets.click();
	}

	public void clickCancelButton() {
		pageLoaded(btnCancel);
		btnCancel.highlight(driver);
		btnCancel.click();
	}

	public void addGuest(String scenario) {
		datatable.setVirtualtablePage("WillCall");
		datatable.setVirtualtableScenario(scenario);
		
		String firstName = datatable.getDataParameter("FirstName");	
		String lastName	= datatable.getDataParameter("LastName");
		String postalCode = datatable.getDataParameter("PostalCode");
		
		findTextboxes();

		pageLoaded(txtFirstName);
		txtFirstName.syncVisible(driver);
		txtFirstName.set(firstName);
		txtLastName.set(lastName);
		txtPostalCode.set(postalCode);
		
		clickAddGuests();
	}

	private void findTextboxes() {
		initialize();
		List<WebElement> rows = null;
		try{
			Element element = new ElementImpl(driver.findElement(By.xpath("//span[@id='willCallTicketForm:searchWillCallTicketPanel']/table/tbody/tr[1]/td/table/tbody")));
			element.highlight(driver);
			rows = tblWillCallTicketsTable.findElements(By.xpath("tr"));
		}catch(Exception e){
			
		}
		for (WebElement row : rows) {
			String text = row.getText();
			switch (text.toLowerCase()) {
			case "first name":
				txtFirstName = new TextboxImpl(row.findElement(By
						.xpath("td[2]/input")));
				txtFirstName.highlight(driver);
				break;
			case "last name":
				txtLastName = new TextboxImpl(row.findElement(By
						.xpath("td[2]/input")));
				txtLastName.highlight(driver);
				break;
			case "postal code":
				txtPostalCode = new TextboxImpl(row.findElement(By
						.xpath("td[2]/input")));
				txtPostalCode.highlight(driver);
				break;
			default:
				break;
			}
		}
	}
	
	private void clickAddGuests(){
		pageLoaded(btnAddGuest);
		btnAddGuest.syncVisible(driver);
		btnAddGuest.jsClick(driver);
		try{
			new ProcessingYourRequestPopup(driver).popupIsVisible();
		}catch(UnhandledAlertException uae){
			try{
				AlertHandler.handleAlerts(driver, 20);
			}catch(NoAlertPresentException nape){
				nape.printStackTrace();
			}
		}
		try{
			new PageLoaded().isDomComplete(driver);
		}catch(Exception e){
			Sleeper.sleep(2000);
		}
	}
}

