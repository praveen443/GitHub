package apps.alc.LeftNavigationBar;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.alc.pleaseWait.PleaseWait;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.TestReporter;

/**
 * This class contains elements and element interactions for a given page of a
 * web application
 * 
 * @author Lalitha Banda
 * @version 12/29/2015 Lalitha Banda - original
 */
public class GuestInformationPage {


	//*******************
	//	Page Class Fields
	//*******************
	//Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	//*********************
	//	Page Class Elements
	//*********************

	//reading visible text for first name last name 
	@FindBy(id = "editguestFNameText")
	private Element txtFirstName_LastName;

	//reading visible text for Address 
	@FindBy(id = "editguestAddress1Text")
	private Element txtAddress;

	//reading visible text for city 
	@FindBy(id = "editguestCityText")
	private Element txtCity;

	//reading visible text for zip code 
	@FindBy(id = "editguestZipText")
	private Element txtZipcode;

	//reading visible text for state 
	@FindBy(id = "editguestStateText")
	private Element txtState;

	//reading visible text for country 
	@FindBy(id = "editguestCountryText")
	private Element txtCountry;

	//Edit Butoon 
	@FindBy(id = "a4jAlertFrm:guestInfoEditButton")
	private Button btnEdit;
	

	//*********************
	//** Build page area **
	//*********************
	/**
	 * @summary Constructor to initialize the page
	 * @author YourNameHere
	 * @version 12/28/2015	 YourNameHere - original
	 * @param  driver
	 */
	public GuestInformationPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author YourNameHere
	 * @version 12/28/2015	 YourNameHere - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public  GuestInformationPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());	        
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author YourNameHere
	 * @version 12/28/2015	 YourNameHere - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded(){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, txtFirstName_LastName); 
	}  

	/**
	 * @summary Method to determine if a page is loaded
	 * @author YourNameHere
	 * @version 12/28/2015	 YourNameHere - original
	 * @param element - Element to be used to determine if the page is loaded
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded(Element element){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element); 
	}  

	//********************
	//	Page Class Methods
	//********************
	
	/**
	 * 
	 * @summary Method to verify guest information details
	 * @version Created 12/29/2015
	 * @author Lalitha Banda
	 * @throws NA
	 * @return NA
	 * 
	 */
	public String readFirstName_LastName(){
		pageLoaded(txtFirstName_LastName);
		txtFirstName_LastName.highlight(driver);
		return txtFirstName_LastName.getText();
	}

	public String readAddress(){
		pageLoaded(txtAddress);
		txtAddress.highlight(driver);
		return txtAddress.getText();
	}

	public String readCity(){
		pageLoaded(txtCity);
		txtCity.highlight(driver);
		return txtCity.getText();
	}

	public String readState(){
		pageLoaded(txtState);
		txtState.highlight(driver);
		return txtState.getText();
	}

	public String readZipCode(){
		pageLoaded(txtZipcode);
		txtZipcode.highlight(driver);
		return txtZipcode.getText();
	}

	public String readCountry(){
		pageLoaded(txtCountry);
		txtCountry.highlight(driver);
		return txtCountry.getText();
	}


	public void verify_FirstNameLastName(String Expected){
		String Actual = readFirstName_LastName();
		TestReporter.assertTrue(Actual.equalsIgnoreCase(Expected), "Primary guest first name and last name is not matched as expected "+"["+Actual+"]"+":"+"["+Expected+"]");
	}

	public void verify_Address(String Expected){
		String Actual = readAddress();
		TestReporter.assertTrue(Actual.equalsIgnoreCase(Expected), "Primary guest address is not matched as expected "+"["+Actual+"]"+":"+"["+Expected+"]");
	}

	public void verify_City(String Expected){
		String Actual = readCity();
		TestReporter.assertTrue(Actual.equalsIgnoreCase(Expected), "Primary guest city  is not matched as expected "+"["+Actual+"]"+":"+"["+Expected+"]");
	}

	public void verify_State(String Expected){
		String Actual = readState();
		TestReporter.assertTrue(Actual.trim().equalsIgnoreCase(Expected), "Primary guest State is not matched as expected "+"["+Actual+"]"+":"+"["+Expected+"]");
	}


	public void verify_ZipCode(String Expected){
		String Actual = readZipCode();
		TestReporter.assertTrue(Actual.equalsIgnoreCase(Expected), "Primary guest zipcode is not matched as expected "+"["+Actual+"]"+":"+"["+Expected+"]");
	}


	public void verify_Country(String Expected){
		String Actual = readCountry();
		TestReporter.assertTrue(Actual.equalsIgnoreCase(Expected), "first name and last name is not matched as expected "+"["+Actual+"]"+":"+"["+Expected+"]");
	}
	
	/**
	 *  @summary Methods to click Edit button
	 * @author Marella Satish
	 * @version 01/08/2016 Marella Satish - original
	 * @param NA
	 * @return NA
	 */
	public void clickEdit(){
		pageLoaded(btnEdit);
		btnEdit.highlight(driver);
		btnEdit.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}
}


