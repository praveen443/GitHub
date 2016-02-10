package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Properties {
  
  //Initialize my objects in page
  public Properties(WebDriver driver)
  {
		//this-->className.class ,,, if u want to instialize Pagefactory in @Test 
		PageFactory.initElements(driver,this );
  }
   
//----------------------------------------------------------------------------------------
// ***************  Objects & their properties in the Login Page ****************
//----------------------------------------------------------------------------------------
	
	//Username element
	@CacheLookup
	@FindBy(id="username")
	public WebElement Username;
	
	//Password element
    @FindBy(id="login")
    public WebElement Password;
    
    //Password element
    @FindBy(className="form-button")
    public WebElement LoginButton;
    
//-----------------------------------------------------------------------------------------
// ***************  Objects & their properties in the HOME Page ****************
//----------------------------------------------------------------------------------------
    
    //To locate the Incoming Message pop-up window.
    @CacheLookup
    @FindBy(xpath="//div[@id='message-popup-window']")
    public WebElement POPWindow;
	
    //Close the Pop-Window
    @CacheLookup
    @FindBy(xpath="//a[@title='close']")
    public WebElement ClosePOPWindow;
    
    //LogOut text link
    @CacheLookup
    @FindBy(className="link-logout")
    public WebElement TextLink_LogOut;
	
    //Mouse hover on the sales tab in the topmenu
    @CacheLookup
    @FindBy(xpath=".//*[@id='nav']/li[2]/a/span")
    public WebElement SalesTab;
	
    //To Locate 'OrdersLink' under the 'sales' tab in the homepage.
    @CacheLookup
    @FindBy(xpath=".//*[@id='nav']/li[2]/ul/li[1]/a/span")
    public WebElement OrdersLink;
	
    //To identify the Logo - 'Magento-Enterprise' in the homePage.
    @CacheLookup
    @FindBy(className="logo")
    public WebElement MagentoLOGO;
	
    //To identify the Gift Wrapping link under the 'sales' tab in the homepage.
    @CacheLookup
    @FindBy(partialLinkText="Gift Wrapping")
    public WebElement Textlink_GiftWrapping;
    
    //To locate 'Manage Customers' link under the 'Customers' Tab in the home Page. 
    @CacheLookup
    @FindBy(partialLinkText="Manage Customers")
    public WebElement TextLink_ManageCustomers;
    
    //To locate 'Customers' tab in the Top Menu. 
    @CacheLookup
    @FindBy(partialLinkText="Customers")
    public WebElement CustomersTab;
   
    //To locate 'Manage Customer Attributes' under the 'Attributes' in the 'Customers' section.
    @CacheLookup
    @FindBy(partialLinkText="Manage Customer Attributes")
    public WebElement ManageCustomerAttributes;
    
    //To locate 'Manage Customer Address Attributes' under the 'Attributes' in the 'Customers' section.
    @CacheLookup
    @FindBy(partialLinkText="Manage Customer Address Attributes")
    public WebElement ManageCustomerAddressAttributes;
    
//-----------------------------------------------------------------------------------------------
// ************ Objects & their properties in the Manage Gift Wrapping Page *************
//-----------------------------------------------------------------------------------------------

    //To locate the 'Add Gift Wrapping' button.
    @CacheLookup
    @FindBy(xpath="//button[@title='Add Gift Wrapping']")
    public WebElement Button_AddGiftWrapping;
    
    //To Locate the 'Select All' text link.
    @CacheLookup
    @FindBy(partialLinkText="Select All")
    public WebElement TextLink_SelectAll;
	
    //To Locate the 'Submit' button.
    @CacheLookup
    @FindBy(xpath="//button[@title='Submit']")
    public WebElement Button_Submit;
	
    //To identify the 'Actions' dropdown list.
    @CacheLookup
    @FindBy(id="giftwrappingGrid_massaction-select")
    public WebElement dropdown_Actions;
    
    //To Locate the 'dropdown2_Status' list.
    @CacheLookup
    @FindBy(xpath="//select[@id='visibility'][@name='status']")
    public WebElement dropdown2_Status;	
    
    //To Locate the 'dropdown1_Status' list.
    @CacheLookup
    @FindBy(id="giftwrappingGrid_filter_status")
    public WebElement dropdown1_Status;	

    //To Click on any random 'edit' link from the webtable
    @CacheLookup
    @FindBy(xpath=".//*[@id='giftwrappingGrid_table']/tbody/tr[1]")
    public WebElement Randomtextlink_Edit;	
    
//-----------------------------------------------------------------------------------------------
// ************* Objects & their properties in the New Gift Wrapping Page ***************
//-----------------------------------------------------------------------------------------------
   
    //Used to locate the Gift Wrapping design edit-box.
    @CacheLookup
    @FindBy(id="design")
    public WebElement InputBox_GiftWrappingDesign;
    
    //Used to locate the Status DropdownList.
    @CacheLookup
    @FindBy(id="status")
    public WebElement SelectDropdownValue_Status;
	
    //Used to locate the Element 'Websites' List.
    @CacheLookup
    @FindBy(id="website_ids")
    public WebElement SelectDropdownValue_Websites;
    
    //Used to locate the 'price' edit box .
    @CacheLookup
    @FindBy(id="base_price")
    public WebElement InputBox_Price;
    
    //Used to locate the 'Save' button.
    @CacheLookup
    @FindBy(xpath="//button[@title='Save']")
    public WebElement Button_Save;
    
//-----------------------------------------------------------------------------------------------
// ************* Objects & their properties in the Manage Customers Page ***************
//-----------------------------------------------------------------------------------------------
  
    //Used to locate the 'Save' button.
    @CacheLookup
    @FindBy(xpath="//button[@title='Add New Customer']")
    public WebElement Button_AddNewCustomer;
     
    //Used to locate the webtable table which contains rows and columns
    @CacheLookup
    @FindBy(xpath=".//*[@id='customerGrid_table']/tbody")
    public WebElement htmlWebTable;
    
    //Used to locate the 'Save Customer' button in the Manage Customers Page.
    @CacheLookup
    @FindBy(xpath="//button[@title='Save Customer']")
    public WebElement Button_SaveCustomer; 
    
    //Used to get the 'valid message'.
    @CacheLookup
    @FindBy(xpath="//li[@class='success-msg']")
    public WebElement ValidTextMessage;
    
    //Used to locate the Name field
    @CacheLookup
    @FindBy(id="customerGrid_filter_name")
    public WebElement ValidCustomerName;
    
    //Used to locate the 'Search' button 
    @CacheLookup
    @FindBy(xpath="//button[@title='Search']")
    public WebElement Button_Search;
    
    //Used to locate the 'Search' button 
    @CacheLookup
    @FindBy(partialLinkText="Edit")
    public WebElement TextLink_Edit;
    
    //Used to locate the 'Account Information' tab.
    @CacheLookup
    @FindBy(id="customer_info_tabs_account")
    public WebElement Tab_AccountInformation;
    
    //Used to locate the default text from the dropdown field - 'group'.
    @CacheLookup
    @FindBy(xpath="//select[@id='_accountgroup_id']//option[@selected='selected']")
    public WebElement gettextfromdropdown;
    
    //Used to locate the 'delete Customer' button in the Manage Customers Page.
    @CacheLookup
    @FindBy(xpath="//button[@title='Delete Customer']")
    public WebElement Button_DeleteCustomer; 
    
//-----------------------------------------------------------------------------------------------
// ************* Objects & their properties in the New Customer Page ***************
//-----------------------------------------------------------------------------------------------
   
    //To locate the field 'First Name'.
    @CacheLookup
    @FindBy(id="_accountfirstname")
    public WebElement InputBox_AccountFirstName;
    
    //To locate the field 'Last Name'.
    @CacheLookup
    @FindBy(id="_accountlastname")
    public WebElement InputBox_AccountLastName;
    
    //Used to locate the Email id input-box.
    @CacheLookup
    @FindBy(id="_accountemail")
    public WebElement InputBox_AccountEmail;
    
	//To locate the Associate dropdown field.
    @CacheLookup
    @FindBy(id="_accountwebsite_id")
    public WebElement dropdown_AssociateValue;
    
    //To locate the dropdown field - 'Group' in the New Customer Page 
    @CacheLookup
    @FindBy(id="_accountgroup_id")
    public WebElement dropdown_GroupValue;
    
    //To locate the Password field.
    @CacheLookup
    @FindBy(id="_accountpassword")
    public WebElement InputBox_AccountPassword;
    
//-----------------------------------------------------------------------------------------------
// ************* Objects & their properties in the 'Manage Customer Attributes' Page ************
//-----------------------------------------------------------------------------------------------
    
    //Used to locate the 'delete Customer' button in the Manage Customer Attributes Page.
    @CacheLookup
    @FindBy(xpath="//button[@title='Add New Attribute']")
    public WebElement Button_AddNewAttribute; 
    
    //Used to locate the 'Save Attribute' button in the Manage Customer Attributes Page.
    @CacheLookup
    @FindBy(xpath="//button[@title='Save Attribute']")
    public WebElement Button_SaveAttribute; 
	
    //Used to locate the 'Reset Filter' button in the Manage Customer Attributes Page.
    @CacheLookup
    @FindBy(xpath="//button[@title='Reset Filter']")
    public WebElement Button_ResetFilter; 
    
    //Used to locate the 'html webtable' in the Manage Customer Attributes Page before reset.
    @CacheLookup
    @FindBy(xpath=".//*[@id='customerAttributeGrid_table']/tbody/tr")
    public WebElement BeforeReset_htmlWebTable; 
    
    //Used to locate the 'html webtable' in the Manage Customer Attributes Page after reset.
    @CacheLookup
    @FindBy(xpath=".//*[@id='customerAttributeGrid_table']/tbody/tr")
    public WebElement AfterReset_htmlWebTable;
    
//---------------------------------------------------------------------------------------------------------------------
//*** Objects & their properties in the 'New Customer Attribute'and 'New Customer Address Attribute' Page************
//---------------------------------------------------------------------------------------------------------------------

    //Used to locate the 'Admin' edit-box in the New Customer Attribute Page.
    @CacheLookup
    @FindBy(name="frontend_label[0]")
    public WebElement Input_Admin; 
    
    //Used to locate the 'English' edit-box in the New Customer Attribute Page.
    @CacheLookup
    @FindBy(name="frontend_label[1]")
    public WebElement Input_English; 
    
    //Used to locate the 'Attribute Code*' edit-box in the New Customer Attribute Page.
    @CacheLookup
    @FindBy(id="attribute_code")
    public WebElement Input_AttributeCode; 
    
    //Used to locate the 'Sort Order*' edit-box in the New Customer Attribute Page.
    @CacheLookup
    @FindBy(id="sort_order")
    public WebElement Input_SortOrder; 
    
//-----------------------------------------------------------------------------------------------
    
}