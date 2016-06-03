package apps.alc.LeftNavigationBar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.alc.pleaseWait.PleaseWait;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.TestReporter;

/**
 * This class contains elements and element interactions for a given page of a
 * web application
 * 
 * @author Marella Satish
 * @version 01/08/2016 Marella Satish - original
 */
public class ModifyGuestInformation {
	// *******************
	// Page Class Fields
	// *******************
	// Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	// *********************
	// Page Class Elements
	// *********************
	
	@FindBy(id = "editGuestDetailsModalPanelDetailsForm:editGuestDetailsModelPanelCmdBtnSave")
	private Button btnSave;
	
	@FindBy(id = "editGuestDetailsModalPanelDetailsForm:editGuestDetailsModelPanelCmdBtnCancel")
	private Button btnCancel;
	
	/*
	 * Guest details
	 */
	@FindBy(id = "editGuestDetailsModalPanelDetailsForm:edittitleOptions")
	private Listbox lstTitle;

	@FindBy(id = "editGuestDetailsModalPanelDetailsForm:editfirstName")
	private Textbox txtFirstName;

	@FindBy(id = "editGuestDetailsModalPanelDetailsForm:editlastName")
	private Textbox txtLastName;

	@FindBy(id = "editGuestDetailsModalPanelDetailsForm:editmiddleInitial")
	private Textbox txtMiddleInitial;

	@FindBy(id = "editGuestDetailsModalPanelDetailsForm:editsuffixOptions")
	private Listbox lstSuffix;

	@FindBy(id = "editGuestDetailsModalPanelDetailsForm:editcountryOptions")
	private Listbox lstCountry;

	@FindBy(id = "editGuestDetailsModalPanelDetailsForm:editaddress")
	private Textbox txtAddress1;

	@FindBy(id = "editGuestDetailsModalPanelDetailsForm:editaddress2")
	private Textbox txtAddress2;

	@FindBy(id = "editGuestDetailsModalPanelDetailsForm:editzip")
	private Textbox txtPostalCode;

	@FindBy(id = "editGuestDetailsModalPanelDetailsForm:editcity")
	private Textbox txtCity;

	@FindBy(id = "editGuestDetailsModalPanelDetailsForm:editstateOptions")
	private Listbox lstState;

	@FindBy(id = "editGuestDetailsModalPanelDetailsForm:edithomephone")
	private Textbox txtPhoneNumber;

	@FindBy(id = "editGuestDetailsModalPanelDetailsForm:editemail")
	private Textbox txtEmail;

	@FindBy(id = "editGuestDetailsModalPanelDetailsForm:editcellphone")
	private Textbox txtCellNumber;

	/*
	 * Local information
	 */

	@FindBy(id = "editGuestDetailsModalPanelDetailsForm:editlocation")
	private Textbox txtLocation;

	@FindBy(id = "editGuestDetailsModalPanelDetailsForm:editlocalPhone")
	private Textbox txtLocalPhone;
	

	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Marella Satish
	 * @version 01/08/2016 Marella Satish - original
	 * @param driver
	 */
	public ModifyGuestInformation(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Marella Satish
	 * @version 01/08/2016 Marella Satish - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public ModifyGuestInformation initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Marella Satish
	 * @version 01/08/2016 Marella Satish - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnSave);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Marella Satish
	 * @version 01/08/2016 Marella Satish - original
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
	
   /**
	*  @summary Methods to click Save button
	 * @author Marella Satish
	 * @version 01/11/2016 Marella Satish - original
	 * @param NA
	 * @return NA
	 */
	public void clickSave(){
		pageLoaded(btnSave);
		btnSave.highlight(driver);
		btnSave.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}
	
	/**
	 * @summary Methods to click Cancel button
	 * @author Marella Satish
	 * @version 01/11/2016 Marella Satish - original
	 * @param NA
	 * @return NA
	 */
	public void clickCancel(){
		initialize();
		pageLoaded(btnCancel);
		btnCancel.highlight(driver);
		btnCancel.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}
	
	/**
	 * @summary Methods to enter guest information
	 * @author Marella Satish
	 * @version 01/11/2016 Marella Satish - original
	 * @param Datatable values
	 * @return NA
	 */
	public void enterGuestInformation(String scenario) {
		datatable.setVirtualtablePage("NewEditGuest");
		datatable.setVirtualtableScenario(scenario);
		
		String postalCode = datatable.getDataParameter("PostalCode");
		String homePhone = datatable.getDataParameter("HomePhone");
		String country = datatable.getDataParameter("Country");
		String email = datatable.getDataParameter("Email");
		String address1 = datatable.getDataParameter("Address1");
		String address2 = datatable.getDataParameter("Address2");
		String city = datatable.getDataParameter("City");
		String state = datatable.getDataParameter("State");
		String cellPhone = datatable.getDataParameter("CellPhone");
	
		pageLoaded(btnSave);
		lstCountry.select(country);
		txtAddress1.setValidate(driver, address1);
		txtAddress2.setValidate(driver, address2);
		txtCity.setValidate(driver, city);
		lstState.select(state);
		txtPostalCode.safeSetValidate(driver, postalCode);
		txtPhoneNumber.setValidate(driver, homePhone);
		txtEmail.setValidate(driver, email);
		txtCellNumber.setValidate(driver, cellPhone);
	}
	
	/**
	 * @summary Methods to get Postal Code
	 * @author Marella Satish
	 * @version 01/11/2016 Marella Satish - original
	 * @param 
	 * @return  Postal Code as String 
	 */
	public String getPostalCode(){
		pageLoaded(txtPostalCode);
		String PostalCode = txtPostalCode.getText().trim();		
		return PostalCode; 
		
	}
	
	/**
	 * @summary Methods to get Home Phone
	 * @author Marella Satish
	 * @version 01/11/2016 Marella Satish - original
	 * @param 
	 * @return  Home Phone as String 
	 */
	public String getHomePhone(){
		pageLoaded(txtPostalCode);
		String PostalCode = txtPhoneNumber.getText().trim();		
		return PostalCode; 
		
	}
	
	/**
	 * @summary Methods to get Country
	 * @author Marella Satish
	 * @version 01/11/2016 Marella Satish - original
	 * @param 
	 * @return Country as String 
	 */
	public String getCountry(){
		pageLoaded(lstCountry);
		String Country = lstCountry.getFirstSelectedOption().getText().trim();		
		return Country; 
		
	}
	
	/**
	 * @summary Methods to get Email
	 * @author Marella Satish
	 * @version 01/11/2016 Marella Satish - original
	 * @param 
	 * @return Email as String 
	 */
	public String getEmail(){
		pageLoaded(txtEmail);
		String Email = txtEmail.getText().trim();		
		return Email; 
		
	}
	
	/**
	 * @summary Methods to get Address1
	 * @author Marella Satish
	 * @version 01/11/2016 Marella Satish - original
	 * @param 
	 * @return  Address1 as String 
	 */
	public String getAddress1(){
		pageLoaded(txtAddress1);
		String Address1 = txtAddress1.getText().trim();		
		return Address1; 
		
	}
	
	/**
	 * @summary Methods to get Address2
	 * @author Marella Satish
	 * @version 01/11/2016 Marella Satish - original
	 * @param 
	 * @return  Address2 as String 
	 */
	public String getAddress2(){
		pageLoaded(txtAddress2);
		String Address2 = txtAddress2.getText().trim();		
		return Address2; 
		
	}
	
	/**
	 * @summary Methods to get City
	 * @author Marella Satish
	 * @version 01/11/2016 Marella Satish - original
	 * @param 
	 * @return  City as String 
	 */
	public String getCity(){
		pageLoaded(txtCity);
		String City = txtCity.getText().trim();		
		return City; 
		
	}
	
	/**
	 * @summary Methods to get State
	 * @author Marella Satish
	 * @version 01/11/2016 Marella Satish - original
	 * @param 
	 * @return  State as String 
	 */
	public String getState(){
		pageLoaded(lstState);
		String State = lstState.getFirstSelectedOption().getText().trim();		
		return State; 
		
	}
	
	/**
	 * @summary Methods to get cell phone number
	 * @author Marella Satish
	 * @version 01/11/2016 Marella Satish - original
	 * @param 
	 * @return  Cell Number as String 
	 */
	public String getCellphone(){
		pageLoaded(txtCellNumber);
		String CellNumber = txtCellNumber.getText().trim();		
		return CellNumber; 
		
	}
	
	/**
	 * @summary Methods to Validate whether the guest information entered was as expected
	 * @author Marella Satish
	 * @version 01/12/2016 Marella Satish - original
	 * @param Datatable value
	 * @return  
	 */
	public void validateGuestInfo(String scenario){
		datatable.setVirtualtablePage("NewEditGuest");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded();
		String postalCode = datatable.getDataParameter("PostalCode");
		String homePhone = datatable.getDataParameter("HomePhone");
		String country = datatable.getDataParameter("Country");
		String email = datatable.getDataParameter("Email");
		String address1 = datatable.getDataParameter("Address1");
		String address2 = datatable.getDataParameter("Address2");
		String city = datatable.getDataParameter("City");
		String state = datatable.getDataParameter("State");
		String cellPhone = datatable.getDataParameter("CellPhone");

		String ExpAddress = (getAddress1()+" "+getAddress2()).trim().toLowerCase();
		String ActAddress = (address1+" "+address2).trim().toLowerCase();
		
		//Validate Address1 & Address2
		TestReporter.assertEquals(ExpAddress, ActAddress, "Expected address[ "+ExpAddress+" ] is not same as Actual address[ "+ActAddress+" ]");
		TestReporter.logStep("Address1 validated and found correct after modify");
		
		//Validate Postal Code
		TestReporter.assertEquals(getPostalCode(), postalCode, "Expected Postal Code[ "+getPostalCode()+" ] is not same as Actual Postal Code [ "+postalCode+" ]");
		TestReporter.logStep("Postal Code validated and found correct after modify");
		
		//Validate City
		TestReporter.assertEquals(getCity(), city, "Expected City[ "+getCity()+" ] is not same as Actual City[ "+city+" ]");
		TestReporter.logStep("City validated and found correct after modify");
		
		//Validate State
		TestReporter.assertEquals(getState(), state, "Expected State[ "+getState()+" ] is not same as Actual State[ "+state+" ]");
		TestReporter.logStep("State validated and found correct after modify");
		
		//Validate Country
		TestReporter.assertEquals(getCountry(), country, "Expected Country[ "+getCountry()+" ] is not same as Actual Country[ "+country+" ]");
		TestReporter.logStep("Country validated and found correct after modify");
		
		//Validate Home Phone
		TestReporter.assertEquals(getHomePhone(), homePhone, "Expected Home Phone[ "+getHomePhone()+" ] is not same as Actual Home Phone[ "+homePhone+" ]");
		TestReporter.logStep("Home Phone validated and found correct after modify");
		
		//Validate Email
		TestReporter.assertEquals(getEmail(), email, "Expected Email[ "+getEmail()+" ] is not same as Actual Email[ "+email+" ]");
		TestReporter.logStep("Email validated and found correct after modify");
		
		//Validate Cell Phone
		TestReporter.assertEquals(getCellphone(), cellPhone, "Expected Cell Phone[ "+getCellphone()+" ] is not same as Actual Cell Phones[ "+cellPhone+" ]");
		TestReporter.logStep("Cell Phone validated and found correct after modify");
		
	}
	
}

