package apps.dreams.AccommodationWrapUp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import core.FrameHandler;
import core.WindowHandler;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

/**
 * @summary Contains the methods & objects for the Guest Search popup window
 * @version Created 09/22/2014
 * @author Waightstill W. Avery
 */
public class GuestSearchWindow {
	private Datatable datatable = new Datatable();
	private WebDriver driver;
	
	// *******************************************
	// *** Accommodation WrapUp Frame Elements ***
	// *******************************************
	@FindBy(name = "selectedSalutation")
	private Listbox lstTitle;

	@FindBy(name = "individualFilterTO.lastName")
	private Textbox txtLastName;
	
	@FindBy(name = "individualTO.middleName")
	private Textbox txtMiddleName;
	
	@FindBy(name = "individualFilterTO.firstName")
	private Textbox txtFirstName;
	
	@FindBy(name = "individualFilterTO.zipCode")
	private Textbox txtZipCode;
	
	@FindBy(name = "addressTO.addressLine1")
	private Textbox txtAddress;
	
	@FindBy(name = "individualFilterTO.city")
	private Textbox txtCity;
	
	@FindBy(name = "individualFilterTO.state")
	private Textbox txtState;
	
	@FindBy(name = "individualFilterTO.country")
	private Listbox lstCountry;
	
	@FindBy(name = "phoneTO.number")
	private Textbox txtPhoneNumber;
	
	@FindBy(name = "selectedPhoneLocatorType")
	private Listbox lstPhoneType;
	
	@FindBy(name = "selectedEmailLocatorType")
	private Listbox lstEmailType;
	
	@FindBy(name = "emailTO.address")
	private Textbox txtEmail;
	
	@FindBy(name = "New")
	private Button btnNew;
	
	@FindBy(name = "Search")
	private Button btnSearch;

	// *********************
	// ** Build page area **
	// *********************

	/**
	 * 
	 * @summary Constructor to initialize the window
	 * @version Created 09/10/2014
	 * @author Waightstill W Avery
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public GuestSearchWindow(WebDriver driver) {
		this.driver = driver;
		//FrameHandler.findAndSwitchToFrame(driver, "contentFrame");
		//swapToApplication(driver);
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);
	}

	public  GuestSearchWindow initialize() {
		FrameHandler.findAndSwitchToFrame(driver, "contentFrame");
		return ElementFactory.initElements(driver, this.getClass());
	}
	
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnNew);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	// ***********************************************
	// *** Accommodation WrapUp Frame Interactions ***
	// ***********************************************

	/**
	 * 
	 * @summary Method to add aan adult guest
	 * @version Created 09/10/2014
	 * @author Waightstill W Avery
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void addAdultGuest(String adultTitle, String adultFirstName,
			String adultMiddleName, String adultLastName,
			String adultZip, String adultAddress, String adultCity,
			String adultState, String adultPhoneType,
			String adultPhoneNumber, String adultEmailType,
			String adultEmail) {
	}

	public static void swapToApplication(WebDriver driver) {
		new WindowHandler().setCurrentWindow(driver);
		new WindowHandler().swapToWindow(driver, "Guest Search");
	}
}

