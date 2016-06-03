package apps.lilo.quickBookChkinAddSpecialRequest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Textbox;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.TextboxImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import selenium.common.Constants;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

/**
 * This class contains elements and element interactions for a given page of a web application
 * 
 * @author YourNameHere
 * @version 9/25/2015	 YourNameHere - original
 * @version 9/30/2015	 SecondContributorNameHere
 * 						-Brief summary of changes here
 * @version 10/2/2015	 ThirdContributorNameHere
 * 						-Brief summary of changes here
 */
public class SpecialRequestPage {
	//*******************
	//	Page Class Fields
	//*******************
	private String Xpath = ".//*[@id='pinSetUpConfirmForm:pinSetUpConfirmModalPanelContentTable']/tbody/tr[2]/td/table/tbody/tr[1]/td[1]/table/tbody/tr[2]/td/div/table/tbody/tr/td/input";
	private String XpathLocatorOfDropDown = ".//*[@id='specialRequestForm']/table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr[1]/td[2]/select/option";
	private String getSrcAttribute;
	private String getID;
	private String GetRooNumber;
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	//*********************
	//	Page Class Elements
	//*********************
	//Create Text box Object for entering First Name of Quick book Page
	@FindBy(id = "quickBookForm:firstNameId")
	private Textbox txFirstName;	

	//Create Text box Object for entering First Name of Quick book Page
	@FindBy(id = "quickBookForm:lastNameId")
	private Textbox txtLastName;

	//Create Text box Object for entering First Name of Quick book Page
	@FindBy(id = "quickBookForm:quickBookButtonId")
	private Button btnQuickBook;

	//Create Text box Object for entering First Name of Quick book Page
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:0:accomodationCheckBox")
	private Element chkNextToReservation;

	//Create Text box Object for entering First Name of Quick book Page
	@FindBy(id = "roomTabFrm:proToChkInButtonId")
	private Button btnProceedToCheckIn;

	//Create Text box Object for entering First Name of Quick book Page
	@FindBy(xpath = ".//*[@id='viewGuestForm:guestList:0:j_id14321']/input")
	private Button chkNextToPrimaryGuest;

	//Create Text box Object for entering First Name of Quick book Page
	@FindBy(id = "viewGuestForm:ZipID")
	private Textbox txtZip;

	//Create Text box Object for entering First Name of Quick book Page
	@FindBy(id = "viewGuestForm:address1Id")
	private Textbox txtAddLine;

	//Create Text box Object for entering First Name of Quick book Page
	@FindBy(id = "viewGuestForm:phoneType1")
	private Element selectItemPhoneType;

	//Create Text box Object for entering First Name of Quick book Page
	@FindBy(xpath = ".//*[@id='viewGuestForm:phoneType1']/option[4]")
	private Element selectItemMobile;

	//Create Text box Object for entering First Name of Quick book Page
	@FindBy(id = "viewGuestForm:phoneNumber1")
	private Textbox txtPhoneNumber;

	//Create Text box Object for entering First Name of Quick book Page
	@FindBy(id = "viewGuestForm:emailTypeId")
	private Element selectItemEmailType;

	//Create Text box Object for entering First Name of Quick book Page
	@FindBy(xpath = ".//*[@id='viewGuestForm:emailTypeId']/option[2]")
	private Element selectItemEmail;

	//Create Text box Object for entering First Name of Quick book Page
	@FindBy(id = "viewGuestForm:emailAddress")
	private Textbox txtEmail;

	//Create page Object for Save Button 
	@FindBy(id = "viewGuestForm:saveButton")
	private Button btnSave;

	//Create page Object for Proceed  Button 
	@FindBy(id = "viewGuestForm:proToChkInButtonId")
	private Button btnProceed;

	//Create page Object for - next Proceed  Button 
	@FindBy(id = "applyPymntForm:nextId")
	private Button btnNextProceed;

	//Create page Object for - Yes button exist on pop up 
	@FindBy(id = "balanceDueCheckinForm:okButtonId1")
	private Button btnYes;

	//Create page Object for - Complete Check in
	@FindBy(id = "newMediaEncodeForm:completeCheckinId")
	private Button btnCompleteCheckin;

	//Create page Object for - Complete Check in
	@FindBy(id = "roomTabFrm:proToChkInButtonId")
	private Button btnCompleteCheckin_roomTab;

	//Create page Object for - Complete Check in
	@FindBy(id = "errorForm:okButtonId")
	private Button ErrorForm;

	//Create Page Object for Cancel Button
	@FindBy(id = "printEncodeForm:defaultPrintEncodCancelId")
	private Button btnCancelPrinterScreen;

	//Create Page Object for No Bell Service
	@FindBy(id = "bellserviceConfirmPopupForm:no")
	private Button btnNoBellService;

	//Create Page Object for Add Special request
	@FindBy(id = "specialRequestId:addspecialRequests")
	private Button btnAddSpecialRequest;

	//Create Page Object for Add Special request
	@FindBy(id = "specialRequestForm:requestTypeRadio:1")
	private Element radioDelivary;

	//Create Page Object for Add Special request
	@FindBy(xpath = ".//*[@id='specialRequestForm:serviceMenu']/select")
	private Element SelectItemService;

	//Create Page Object for Add Special request
	@FindBy(xpath = ".//*[@id='specialRequestForm:serviceMenu']/select/option[21]")
	private Element SelectCRIBItemService;

	//Create Page Object for FreeForm Comment text input 
	@FindBy(id = "specialRequestForm:specialRequest")
	private Textbox txtFreeformComment;

	//Create Page Object for FreeForm Comment text input 
	@FindBy(id = "specialRequestForm:messageButtonId1")
	private Element btnSubmit;

	//Create Page Object for text confirmation message
	@FindBy(id = "saveSuccessfulForm:confirmationMsgId")
	private Element txtConformMsg;

	//Create Page object for button OK
	@FindBy(id = "saveSuccessfulForm:saveSuccessfulOkBtnId")
	private Element btnOK;

	//Create Page object for button OK
	@FindBy(id = "specialRequestId:flatDetailViewList:0:editspecialrequest")
	private Element lnkEdit;

	//Create Page object for button OK
	@FindBy(id = "specialRequestForm:closeButtonId1")
	private Element btnClose;

	//Validation textPtesent 
	//Create Page object for button OK
	@FindBy(xpath = ".//*[@id='specialRequestId:specialRequestPanel']/table/tbody/tr[1]/td[1]/b[1]")
	private Element txtPresent;

	//*********************
	//** Build page area **
	//*********************	

	/**
	 * @summary Constructor to initialize the page
	 * @author Lalitha Banda
	 * @version 10/28/2015	 YourNameHere - original
	 * @param  driver
	 */
	public SpecialRequestPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Lalitha Banda
	 * @version 10/28/2015	 YourNameHere - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public SpecialRequestPage initialize() {
		return ElementFactory.initElements(driver,
				this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Lalitha Banda
	 * @version 10/28/2015	 YourNameHere - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded(){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, txFirstName); 
	}  

	public boolean pageLoaded(Element element){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element); 
	}


	//*************************************************
	//	Page Recommend and Manage Tickets Class Methods
	//*************************************************

	/**
	 * Describe here what the following method does
	 */
	/**
	 * 
	 * @summary Method to add special request information for quick book check in 
	 * @version Created 10/28/2015
	 * @author Lalitha Banda
	 * @param  driver
	 * @throws Exception if data table scenarios or parameters are not found
	 * @return NA
	 * @throws InterruptedException 
	 * 
	 */


	public void AddSpecialRequest(String scenario){
		datatable.setVirtualtablePage("PrimaryGuestInfo");
		datatable.setVirtualtableScenario(scenario);
		
		String qty = datatable.getDataParameter("ServiceQuantity").trim();
		String service = datatable.getDataParameter("ItemService").trim();

		
		//Click on Add Special Request
		pageLoaded(btnAddSpecialRequest);
		btnAddSpecialRequest.syncVisible(driver);
		btnAddSpecialRequest.focus(driver);
		btnAddSpecialRequest.sendKeys(Keys.ENTER);

		//Verify the Room Number in the drop down list 
		pageLoaded(radioDelivary);
		radioDelivary.syncVisible(driver);
		TestReporter.logStep("Verify the Room Number in the drop down list ");
		GetRooNumber = driver.findElement(By.xpath(XpathLocatorOfDropDown)).getText();
		TestReporter.logStep("Room Number Available in the DropDown List "+GetRooNumber);

		//Click on DELivary radio Button 
		pageLoaded(radioDelivary);
		radioDelivary.click();

		//Select CRID option from Item service 
		TestReporter.logStep("Select CRID option from Item service");
		SelectItemService.click();
		Sleeper.sleep(3000);
		try{
			List<WebElement> options = driver.findElements(By.xpath("//span[@id='specialRequestForm:serviceMenu']/select/option"));
			System.out.println(options.size());
			for(WebElement option : options){
				if(option.getText().equalsIgnoreCase(service)){
					new ElementImpl(option).click();
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		List<WebElement> popupBodies = driver.findElements(By.xpath("//td[@class='popupBody'][@rowSpan='1'][@colSpan='1']"));
		Textbox quantity = null;
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		for(WebElement popupBody : popupBodies){
			Element e = new ElementImpl(popupBody);
			if(e.syncVisible(driver, 1, false)){
				try{
					quantity = new TextboxImpl(e.findElement(By.xpath("table/tbody/tr/td/table/tbody/tr/td[3]/input")));
				}catch(Exception err){
					err.printStackTrace();
				}
				break;
			}
		}
		driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT, TimeUnit.SECONDS);
		quantity.highlight(driver);
		quantity.safeSet(qty);
		
		//Enter Free form Comment 
		TestReporter.logStep("Enter Free form Comment ");
		
		txtFreeformComment.safeSet(datatable.getDataParameter("FreeFormComment"));


		//Click button submit 
		pageLoaded(btnSubmit);
		btnSubmit.jsClick(driver);

		//Verify confirmation message 
		TestReporter.logStep("Validating the Confirmation message");
		pageLoaded(txtConformMsg);
		TestReporter.logStep(txtConformMsg.getText());

		//Click on Button OK
		Sleeper.sleep(3000);
		btnOK.click();

	}


	//Method to click on radio button next to reservation 
	public void chkNexttoReservation(){
		// Click on check box next to the Reservation 
		chkNextToReservation.click();
	}
	

	//Method to click on complete check in button 
	public void completeCheckin(){
		//Click yes on PopUp Window
		pageLoaded(btnYes);
		btnYes.jsClick(driver);

		//Click Complete CheckIn button
		pageLoaded(btnCompleteCheckin);
		btnCompleteCheckin.jsClick(driver);
	}





	//This method performs complete check in process
	public void verifyCompleteCheckin(){

		//Click on Button Proceed
		pageLoaded(btnProceed);
		btnProceed.jsClick(driver);


		//Click on Button next Proceed
		pageLoaded(btnNextProceed);
		btnNextProceed.jsClick(driver);


		//clicking on complete check in button 
		completeCheckin();

		//Click on Button Cancel Printer screen 
		pageLoaded(btnCancelPrinterScreen);
		btnCancelPrinterScreen.jsClick(driver); 

		//Click on No : PIN Setup 
		List<WebElement> inputsofPINSetUp = driver.findElements(By.xpath(Xpath));
		for(WebElement input : inputsofPINSetUp){
			getSrcAttribute =input.getAttribute("src");
			if(getSrcAttribute.contains("no_popup")){
				getID  = input.getAttribute("id");
				driver.findElement(By.id(getID)).click();
			}

			//Click Complete CheckIn button
			btnCompleteCheckin_roomTab.syncVisible(driver);
			btnCompleteCheckin_roomTab.jsClick(driver);


		}
	}


}
