package apps.lilo.createReservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

/**
 * 
 * @summary Contains the page methods & objects for the Guest search page during
 *          the create reservation process
 * @version Created 9/17/2014
 * @author Jessica Marshall
 */
public class CreateReservationGuestSearchPage {
	//
	// Page Fields
	//
	private Datatable datatable = new Datatable();
	private WebDriver driver;
	
	// ************************************
	// *** Main QuickBook Page Elements ***
	// ************************************

	// Last name
	@FindBy(id = "createResGuestSearchForm:guestSearchLastName")
	private Textbox txtLastName;

	// First name
	@FindBy(id = "createResGuestSearchForm:guestSearchFirstName")
	private Textbox txtFirstName;

	// Zip code
	@FindBy(id = "createResGuestSearchForm:guestSearchPostalCode")
	private Textbox txtZipCode;

	// Country
	@FindBy(id = "createResGuestSearchForm:guestSearchCountryCode")
	private Listbox lstCountry;

	// Find guest
	@FindBy(id = "createResGuestSearchForm:guestSearchButton")
	private Button btnFind;

	// Create new guest
	@FindBy(id = "createResGuestSearchForm:createGuestButton")
	private Button btnCreateNew;

	// Previous form
	@FindBy(id = "createResGuestSearchForm:backToSerchOfferResults")
	private Button btnPrevious;
	
	// Select guest
	@FindBy(id = "createResGuestSearchForm:selectGuestButton")
	private Button btnSelect;
	
	// Cancel
	@FindBy(id = "createResGuestSearchForm:cancelButton")
	private Button btnCancel;
	
	//*********************
    //** Build page area **
    //*********************

	public CreateReservationGuestSearchPage(WebDriver driver){
		this.driver = driver;	
		ElementFactory.initElements(driver, this);	 
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH); 
	}

	@SuppressWarnings("unused")
	private CreateReservationGuestSearchPage initialize(WebDriver driver) {
	    return ElementFactory.initElements(driver, CreateReservationGuestSearchPage.class);	        
	}
	
	/**
	 * @summary: Method to determine the button - 'create new' exists on the page.
	 * @author : Paveen Namburi
	 * @version: Created 11/6/2015
	 * @param  : button
	 * @return : Boolean -True, if the button exists. Else it will throw as false.
	 */
	public boolean pageLoaded(Button button){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnCreateNew);
	}
	
	public boolean pageLoaded(){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnSelect);
	}
	
	public boolean pageLoaded(Element element){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}
	
	public CreateReservationGuestSearchPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}
	
    //**************************************
  	//*** Main CheckIn Page Interactions ***
  	//************************************** 
	
	public void clickCancel() {
		btnCancel.highlight(driver);
		btnCancel.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickCreateNew() {
		btnCreateNew.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickSelect() {
		btnSelect.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickPrevious() {
		btnPrevious.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickFind() {
		btnFind.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	/**
	    * 
	    * @summary Method to search the guest with out LastName
	    * @version Created 11/25/2014
	    * @author Venkatesh A
	    * @param  NA
	    * @throws NA
	    * @return NA
	    * 
	    */
	public void searchGuest(String scenario){
		datatable.setVirtualtablePage("ManageGuests");
		datatable.setVirtualtableScenario(scenario);
		txtFirstName.safeSet(datatable.getDataParameter("FirstName"));
		txtLastName.safeSet(datatable.getDataParameter("LastName"));
		txtZipCode.safeSet(datatable.getDataParameter("ZipCode"));
		btnFind.click();
	}
}

