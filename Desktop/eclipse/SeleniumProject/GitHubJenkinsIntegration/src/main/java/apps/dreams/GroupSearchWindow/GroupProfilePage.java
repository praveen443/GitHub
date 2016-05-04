package apps.dreams.GroupSearchWindow;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.FrameHandler;
import core.WebDriverSetup;
import core.WindowHandler;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Link;
import core.interfaces.Listbox;
import core.interfaces.RadioGroup;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import selenium.common.Constants;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;
import utils.date.DateTimeConversion;

/**
 * @summary Contains the methods & objects for the Dreams UI content frame for
 *          Group Profile page
 * @version Created 03/02/2016
 * @author Praveen Namburi
 */
public class GroupProfilePage {
	
	// ****************************
	// *** Content Frame Fields ***
	// ****************************	
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	private String resortRoomTypes;
	
	// ******************************
	// *** Content Frame Elements ***
	// ******************************
	
	@FindBy(name = "b_New")
	private Button btnNew;
	
	@FindBy(name = "bookingSource")
	private Listbox lstBookingSource;
	
	@FindBy(name = "newBookingSource")
	private Textbox txtnewBookingSource;
	
	@FindBy(name = "blockInfo.securityLevel")
	private Listbox lstBookingAccessSecurityDefault;
	
	@FindBy(name = "blockInfo.preDays")
	private Textbox txtPreDays;
	
	@FindBy(name = "blockInfo.fromDateAsString")
	private Textbox txtBlockStartDate;
	
	@FindBy(name = "blockInfo.toDateAsString")
	private Textbox txtBlockEndDate;
	
	// -----------------------------------------------
	// Display Block/Block Inventory page elements
	// -----------------------------------------------
	
	@FindBy(partialLinkText = "Utilization Codes")
	private Link lnkUtilizationCodes;
	
	@FindBy(partialLinkText = "Packages")
	private Link lnkPackages;
	
	@FindBy(partialLinkText = "Block Family")
	private Link lnkBlockFamily;
	
	@FindBy(xpath ="//*[@id='BLKFAM']//table/tbody/tr[2]/td[1]/a/img")
	private Link lnkParentBlockIDSearch;
	
	// Checkbox ParentDuringAvailEval
	@FindBy(name = "blockInfo.family.checkParentDuringAvailEval")
	private Checkbox chkParentDuringAvaiEval;
	
	//Link Billing Profile
	@FindBy(partialLinkText ="Billing Profile")
	private Link lnkBillingProfile;
	
	//Link Grow
	@FindBy(partialLinkText ="Grow")
	private Link lnkGrow;
	
	// Checkbox GrowthDatesNoInventory
	@FindBy(name = "blockInfo.growConfiguration.bookDatesNotAttached")
	private Checkbox chkGrowthDatesNoInventory;
	
	// Checkbox GrowPrePostDates
	@FindBy(name = "blockInfo.growConfiguration.growOnPrePostDates")
	private Checkbox chkGrowPrePostDates;
	
	// Checkbox ExpandFreesellAvailCheck
	@FindBy(name = "blockInfo.growConfiguration.expandToFreesellAvailCheck")
	private Checkbox chkExpandFreesellAvailCheck;
	
	// Checkbox BookNonBlockRoomType
	@FindBy(name = "blockInfo.growConfiguration.bookRoomTypeNotAttached")
	private Checkbox chkBookNonBlockRoomType;
	
	// Checkbox BookNonBlockRateCategory
	@FindBy(name = "blockInfo.growConfiguration.bookRateCatNotAttached")
	private Checkbox chkBookNonBlockRateCategory;
		
	// Checkbox OverrideFreesellBookCheck
	@FindBy(name = "blockInfo.growConfiguration.overrideFreesellBookCheck")
	private Checkbox chkOverrideFreesellBookCheck;
	
	// Button GoToBlockInventory
	@FindBy(xpath = "//input[@value='Go To Block Inventory']")
	private Button btnGoToBlockInventory;
	
	//------ Filter Resorts window Elements ------
	
	// Listbox Resort 
	@FindBy(name = "selectedResortsArr")
	private Listbox lstResort;
	
	// Button Ok  
	@FindBy(xpath = "//*[@value='OK'][@class='button']")
	private Button btnOK;
	
	//------ Block Inventory setup ------
	
	@FindBy(name = "biSelectedBlockInventoryOperation")
	private Listbox lstAction;
	
	@FindBy(name = "biSelectedBlockOrAllotmentArr")
	private Listbox lstBlockAllot;
	
	@FindBy(name = "sbiSelectedRateCategoriesArr")
	private Listbox lstRateCategory;
	
	@FindBy(name = "biSelectedResortsArr")
	private Listbox lstResortInv;
	
	@FindBy(name = "biResortRoomTypesArr")
	private Listbox lstResortRoomTypes;
	
	@FindBy(partialLinkText = "Add")
	private Link lnkAdd;
	
	//Radio button for BlockInventoryInputType - Start and Extend to
	@FindBy(xpath="//*[@name='blockInventoryInputType'][@onclick='showStartAndExtendToElements()']")
	private RadioGroup radBlockInvStartAndExtendTo;
	
	//Textbox TotalQuantity
	@FindBy(id = "biStartAndExtendToTotalQuantity")
	private Textbox txtTotalQuantity;
	
	//Button Add in BlockInventory
	@FindBy(id = "Add1But1")
	private Button btnAdd;
	
	//Button Save in BlockInventory
	@FindBy(id = "saveInv")
	private Button btnSave;
	
	//****** Allotment Availability and Booking ******
	
	//Link Allotment
	@FindBy(partialLinkText = "Allotment")
	private Link lnkAllotment;
	
	@FindBy(name = "allotAvailRateCateg")
	private Listbox lstAllotAvailRate;
	
	@FindBy(name = "allotAvailAssocRateCateg")
	private Listbox lstAllotAvailableAssociations;
	
	@FindBy(xpath = "//*[@id='LkUp1But'][@onclick='javascript:addToAllotAvail()']")
	private Button btnAddAvailable;

	@FindBy(name = "allotBookingRateCateg")
	private Listbox lstAllotBookingRateCateg;
	
	@FindBy(name = "allotBookingAssocRateCateg")
	private Listbox lstAllotBookingAssocRateCateg;
	
	@FindBy(xpath = "//*[@id='LkUp1But'][@onclick='javascript:addToAllotBooking()']")
	private Button btnAddBooking;
	
	@FindBy(xpath = "//*[@id='ALOT']/table/tbody")
	private Element eleAllotmentTblId;
	
	//****** Packages ******
	
	//Textbox PackageCode
	@FindBy(name = "packageCode")
	private Textbox txtPackageCode;
	
	//Textbox PackageCode
	@FindBy(name = "packageDescription")
	private Textbox txtPackageDescription;
		
	//Textbox PackageCode
	@FindBy(name = "startDateAsString")
	private Textbox txtStartDate;
		
	//Button Add in BlockInventory
	@FindBy(name = "search")
	private Button btnSearch;
	
	//Radiobutton Group Dist Channel 
	@FindBy(xpath = "//*[@value='Groups_Distribution_Channel'][@name='salesChannelType']")
	private RadioGroup radGroupsDistChannel;
	
	//Radiobutton RSR Dist Channel 
	@FindBy(xpath = "//input[@value='RSR_Distribution_Channel'][@name='salesChannelType']")
	private RadioGroup radRSRDistChannel;
		
	//Search Results table id
	@FindBy(xpath = ".//*[@id='RESULTS']/table[2]/tbody")
	private WebElement eleSearchResultsTbl;
	
	//****** Custom package elements ******
	
	@FindBy(name = "selectedResortsArray")
	private Listbox lstPkgResort;
	
	@FindBy(name = "selectedRoomTypesArray")
	private Listbox lstPkgRoomTypes;
	
	@FindBy(name = "selectedResortRoomTypesArray")
	private Listbox lstResortRoomTypesArray;
	
	//Radiobutton configType
	@FindBy(xpath = "//*[@name='configType'][@value='START-EXTEND']")
	private RadioGroup radConfigType;
	
	//Textbox From date
	@FindBy(name = "startExtendFromDateAsString")
	private Textbox txtPkgFromDate;
		
	//Textbox To Date
	@FindBy(name = "startExtendToDateAsString")
	private Textbox txtPkgToDate;
	
	//Radiobutton CustomRate
	@FindBy(xpath = "//*[@value='CUSTOM'][@name='startExtendRateFlag']")
	private RadioGroup radCustomRate;
		
	//Radiobutton Additional Adult Charge
	@FindBy(xpath = "//*[@value='CUSTOM'][@name='startExtendAdditionalAdultChargeFlag']")
	private RadioGroup radAdultCharge;

	//Textbox CustomRate
	@FindBy(name = "startExtendRateAsString")
	private Textbox txtCustomRate;
			
	//Textbox AdditionalAdultCharge
	@FindBy(name = "startExtendAdditionalAdultChargeAsString")
	private Textbox txtAdditionalAdultCharge;
	
	//Button Update Custom Package
	@FindBy(xpath = "//*[@name='update'][@onclick='updateStartExtend()']")
	private Button btnUpdate;

	//Link Clear
	@FindBy(partialLinkText = "Clear")
	private Link lnkclear;
	
	//Button ConfirmAll Custom Package
	@FindBy(name="confirmAll")
	private Button btnConfirmAll;
	
	//****** Trim elements ******
	
	//Link Trim
	@FindBy(partialLinkText = "Trim")
	private Link lnkTrim;
	
	//Textbox Trim Day
	@FindBy(name = "blockInfo.trimConfiguration.blockDay")
	private Textbox txtTrimDay;
		
	//Textbox Trim Date
	@FindBy(name = "blockInfo.trimConfiguration.blockDateAsString")
	private Textbox txtTrimDate;
	
	//Checkbox AutoTrim 
	@FindBy(name = "blockInfo.trimConfiguration.autoTrim")
	private Checkbox chkAutoTrim;
	
	//****** Advanced Trim elements ******
	
	//Link AdvancedTrim
	@FindBy(partialLinkText = "Advanced Trim")
	private Link lnkAdvancedTrim;
	
	//Textbox Trim Date
	@FindBy(name = "triggerDateAsString")
	private Textbox txtAdvTrimDate;
	
	//Radiobutton Additional Adult Charge
	@FindBy(xpath = "//*[@value='byQuantity'][@name='autoReduceType']")
	private RadioGroup radAutoReduceTypeByQuantity;
	
	//Textbox AutoReduceUnit
	@FindBy(name = "autoReduceUnit")
	private Textbox txtAutoReduceUnit;
	
	@FindBy(name = "selectedCombination")
	private Listbox lstAutoReduceRoomType;
	
	@FindBy(name = "selectedResortRoomRate")
	private Listbox lstAutoReduceSelectedRoomType;
	
	//Link AddResort
	@FindBy(partialLinkText = "Add")
	private Link lnkAddResort;
	
	//Button AddReduceRoomType
	@FindBy(xpath = "//*[@name='b_AddAutoReduce'][@onclick='processAdvanceTrim()']")
	private Button btnAddReduceRoomType;
	
	//Link Auto Reduce by a Quantity
	@FindBy(partialLinkText = "Auto Reduce by a Quantity")
	private Link lnkAutoReduceByaQuantity;
	
	//Webtable in Advanced Trim section for AutoReduceByQuantity
	@FindBy(xpath = "//*[@id='TRMBY']/table/tbody")
	private Webtable tblAutoReduceByaQuantity;
	
	@FindBy(xpath = "html/body/form/table/tbody/tr[1]/td/div")
	private Element eleResortText;
	
	//Button AddNewSalesChannel
	@FindBy(xpath = "//input[@value='Add New Sales Channel']")
	private Button btnAddNewSalesChannel;
	
	//Textbox NewSalesChannel
	@FindBy(name = "newSalesChannel")
	private Textbox txtNewSalesChannel;
	
	//Listbox SalesZone
	@FindBy(name = "salesZone")
	private Listbox lstSalesZone;
	
	//Listbox DistributionChannel
	@FindBy(name = "distributor")
	private Listbox lstDistributionChannel;
	
	//Listbox MemberShip
	@FindBy(name = "membership")
	private Listbox lstMemberShip;
	
	//Checkbox RSRFlag 
	@FindBy(name = "rsrFlag")
	private Checkbox chkRSRFlag;
	
	//Button Add in AddSalesChannel window
	@FindBy(name="Add")
	private Button btnAddSalesChannel;
	
	//Button ShowHideBlocksList
	@FindBy(xpath="//input[@name='ShowBlocksListButton']")
	private Button btnShowHideBlocksList;
	
	@FindBy(xpath = "//input[@value='Edit'][@name='GroupAction']")
	private Button btnEditGroup;
	
	@FindBy(xpath =".//*[@id='RESULTS']/table[2]/tbody")
	private Webtable searchedPkgresultsTbl;
	
	// *********************
	// ** Build page area **
	// *********************

	/**
	 * @summary Constructor to initialize the frame
	 * @version Created 03/02/2016
	 * @author Praveen Namburi
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public GroupProfilePage(WebDriver driver) {
		this.driver = driver;
		FrameHandler.findAndSwitchToFrame(driver, "contentFrame");
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);
	}
	
	private GroupProfilePage initialize() {
		FrameHandler.findAndSwitchToFrame(driver, "contentFrame");
		return ElementFactory.initElements(driver, this.getClass());
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnNew);
	}
	
	public boolean isPageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnSearch);
	}
	
	public boolean isPageLoadedBlockInventory(){
		FrameHandler.findAndSwitchToFrame(driver, "contentFrame");
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnGoToBlockInventory);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}
	
	// ***********************************
	// *** Content Frame Interactions ***
	// ***********************************

	/**
	 * @summary Method to enter BookingSource and Block Management.
	 * @version Created 03/02/2016
	 * @author Praveen Namburi
	 * @param scenario
	 * @return NA
	 */
	public void setBookingSourceAndBlockManagement(String scenario) {
		datatable.setVirtualtablePage("BlockManagementAndBookingSrc");
		datatable.setVirtualtableScenario(scenario);

		String bookingSRC  = datatable.getDataParameter("BookingSRC");
		String bookingSource  = datatable.getDataParameter("BookingSource");
		String newBookingSource = datatable.getDataParameter("NewBookingSource");
		String bookingSecurityDefault = datatable.getDataParameter("SecurityDefault");
		String preDays = datatable.getDataParameter("PreDays");
		String bookingDates = datatable.getDataParameter("SetBookingDates");
		String daysOut = datatable.getDataParameter("DaysOut");
		String numberNights = datatable.getDataParameter("NumberOfNights");
		
		//Set Booking source information.
		if(bookingSRC.equalsIgnoreCase("true")){
			pageLoaded(lstBookingSource);
			lstBookingSource.select(bookingSource);
			txtnewBookingSource.safeSet(newBookingSource);
			pageLoaded(lstBookingAccessSecurityDefault);
			lstBookingAccessSecurityDefault.select(bookingSecurityDefault);
		}
		
		//Enter pre-days in Block management
		pageLoaded(txtPreDays);
		txtPreDays.highlight(driver);
		txtPreDays.safeSet(preDays);
		
		//Set Booking Start and End dates.
		if(bookingDates.equalsIgnoreCase("true")){
			setBlockStartAndEndDates(daysOut, numberNights);
		}
	}
	
	/**
	 * @summary: Method to set the block start and end dates.
	 * @author: Praveen Namburi, @version: Created 3/14/2016
	 * @param daysOut, @param numberNights
	 */
	public void setBlockStartAndEndDates(String daysOut, String numberNights){
		
		String blockStartDate = DateTimeConversion.getDaysOut(daysOut, "MM/dd/yyyy");
		String blockEndDate = DateTimeConversion.getDaysOut(numberNights, "MM/dd/yyyy");
		TestReporter.log("Block Start Date : " + blockStartDate);
		TestReporter.log("Block End Date : " + blockEndDate);
		
		txtBlockStartDate.highlight(driver);
		txtBlockStartDate.safeSet(blockStartDate);
		
		txtBlockEndDate.highlight(driver);
		txtBlockEndDate.safeSet(blockEndDate);
	}
	

	/**
	 * @summary: Method to click on button New in Group Profile page.
	 * @version: Created 03/02/2016
	 * @author Praveen Namburi, @return NA
	 */
	public void clickbtnNew(){
		pageLoaded(btnNew);
		btnNew.highlight(driver);
		btnNew.jsClick(driver);
	}
	
	/**
	 * @summary: Method to validate Error Message
	 * @author: Praveen Namburi, @version: Created 03/02/2016
	 * @param scenario
	 */
	public void validateErrorMessage(String scenario){
			
		datatable.setVirtualtablePage("ErrorMessages");
		datatable.setVirtualtableScenario(scenario);

		String expectedError = datatable.getDataParameter("Message");
			
		pageLoaded();
		WebElement errorElement = driver.findElement(By.xpath("//*[@class = 'errorMsg']"));
		Element error = new ElementImpl(errorElement);
		error.highlight(driver);
			
		String errorText[];
		errorText = error.getText().split("-");
		TestReporter.log(errorText[0]+"-"+errorText[1]);
		TestReporter.log("Expected error msg is : " + expectedError);
		TestReporter.assertTrue((expectedError.trim().contains((errorText[0]+"-"+errorText[1]).trim())), 
				"Error displayed as expected");
	}
	
	/**
	 * @summary: Method to click on Link UtilizationCodes in 
	 *           Display block/ Block Inventory page.
	 * @author: Praveen namburi, @version: Created 3/4/2016
	 */
	public void clickUtilizationCodes(){
		pageLoaded(lnkUtilizationCodes);
		lnkUtilizationCodes.highlight(driver);
		lnkUtilizationCodes.jsClick(driver);
	}
	
	/**
	 * @summary: Method to click on Link AdvancedTrim.
	 * @author: Praveen namburi, @version: Created 3/18/2016
	 */
	public void clickAdvancedTrim(){
		pageLoaded(lnkAdvancedTrim);
		lnkAdvancedTrim.highlight(driver);
		lnkAdvancedTrim.jsClick(driver);
	}
	
	public void setAdvancedTrimDate(String daysOut){
		String advancedTrimDate = DateTimeConversion.getDaysOut(daysOut, "MM/dd/yyyy");
		TestReporter.log("Advanced Trim date : " + advancedTrimDate);
		
		pageLoaded(txtAdvTrimDate);
		txtAdvTrimDate.highlight(driver);
		txtAdvTrimDate.safeSet(advancedTrimDate);
	}

	/**
	 * @summary: Method to set the Advanced Trim details.
	 * @author : Praveen Namburi, @version: Created 3/18/2016
	 * @param scenario
	 */
	public void setAdvancedTrimDetails(String scenario){
		datatable.setVirtualtablePage("AdvancedTrimAndTrimdetails");
		datatable.setVirtualtableScenario(scenario);
		
		String daysOut = datatable.getDataParameter("DaysOut");
		String setAutoReduceByQuantity = datatable.getDataParameter("AutoReduceByQuantity");
		String autoReduceUnit = datatable.getDataParameter("AutoReduceUnit");
		
		//Click on link AdvancedTrim
		clickAdvancedTrim();
		
		setAdvancedTrimDate(daysOut);
		if(setAutoReduceByQuantity.equalsIgnoreCase("ON")){
			pageLoaded(radAutoReduceTypeByQuantity);
			radAutoReduceTypeByQuantity.highlight(driver);
			radAutoReduceTypeByQuantity.click();
		}
		txtAutoReduceUnit.highlight(driver);
		txtAutoReduceUnit.safeSet(autoReduceUnit);
		// Click on the available option in Roomtype Listbox.
		pageLoaded(lstAutoReduceRoomType);
		List<WebElement> options = lstAutoReduceRoomType.getOptions();
		TestReporter.log("Total count of options in RoomType listbox : "+options.size());
		options.get(0).click();
		
		//Click on Add link
		do {
			initialize();
			try{
				lnkAddResort.syncEnabled(driver, 5,false);
			}catch (Exception e) {
				pageLoaded(lnkAddResort);
			}
		} while (!lnkAddResort.isEnabled() & lnkAddResort.isDisplayed());
		TestReporter.assertTrue(lnkAddResort.syncEnabled(driver),
				"Add Resort link is not enabled after selecting RoomType value.");
		
		lnkAddResort.highlight(driver);
		lnkAddResort.jsClick(driver);
		
		pageLoaded(btnAddReduceRoomType);
		btnAddReduceRoomType.highlight(driver);
		btnAddReduceRoomType.jsClick(driver);
	}
	
	/**
	 * @summary: Method to click on link AutoReduceByaQuantity.
	 * @author: Praveen Namburi, @version: Created 3/18/2016s
	 */
	public void clickAutoReduceByaQuantity(){
		initialize();
		lnkAutoReduceByaQuantity.syncVisible(driver);
		lnkAutoReduceByaQuantity.highlight(driver);
		lnkAutoReduceByaQuantity.jsClick(driver);
	}
	
	 /**
		* @summary Method to verify whether added Resort/RoomType exists 
		* 		   in AutoReduceByQuantitytable before deleting the Info.
		* @version Created 03/18/2016
		* @author Praveen Namburi
		*/
		public void validateAutoReduceByQuantityInfoBeforeDeletingResort(){
			 pageLoaded(tblAutoReduceByaQuantity);
			 tblAutoReduceByaQuantity.syncVisible(driver);
			 List<WebElement> AutoReduceByQuantityTblRows = tblAutoReduceByaQuantity.findElements(By.tagName("tr"));
			 TestReporter.log("Total rows in AutoReduceByaQuantity table before Deleting the First record : "
			                  + AutoReduceByQuantityTblRows.size());
			 TestReporter.assertTrue(AutoReduceByQuantityTblRows.size()>1, 
					 "Added AutoReduce Resort/RoomType Info exists in Webtable.");
		}
		
		/**
		 * @summary: Method to delete the added Resort/RoomType in Autoreduce by quantity table.
		 * @author: Praveen Namburi, @version: Created 03/18/2016
		 */
		public void deleteResortInAutoreduceByQuantity(){
			pageLoaded(tblAutoReduceByaQuantity);
			tblAutoReduceByaQuantity.highlight(driver);
			  
			  List<WebElement> links = tblAutoReduceByaQuantity.findElements(By.tagName("a"));
			  TestReporter.log("Total no. of links found in AutoReduceByaQuantity table : "+links.size());
			  
			  for(int LinkIterator = 0;LinkIterator<=links.size();LinkIterator++){
				  if(links.get(LinkIterator).getText().equalsIgnoreCase("Delete")){
					  links.get(LinkIterator).click();
					  break;
				  }			  
			  }	
		}
		
		public void clickAutoReducedResortLink(String scenario){
			  datatable.setVirtualtablePage("BlockManagementAndBookingSrc");
			  datatable.setVirtualtableScenario(scenario);

			  String resort  = datatable.getDataParameter("SelectResort");
			
			  pageLoaded(tblAutoReduceByaQuantity);
			  tblAutoReduceByaQuantity.highlight(driver);
			  
			  List<WebElement> links = tblAutoReduceByaQuantity.findElements(By.tagName("a"));
			  TestReporter.log("Total no. of links found in AutoReduceByaQuantity table : "+links.size());
			  
			  for(int LinkIterator = 0;LinkIterator<=links.size();LinkIterator++){
				  if(links.get(LinkIterator).getText().contains(resort)){
					  links.get(LinkIterator).click();
					  break;
				  }			  
			  }	
			  
			  WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
			  WindowHandler.waitUntilWindowExistsTitleContains(driver, resort);
			  
			  pageLoaded(eleResortText);
			  eleResortText.highlight(driver);
			  String resorttext = eleResortText.getText();
			  TestReporter.log("Captured Resort/RoomType is : "+resorttext);
			  //Used to close the Window
			  driver.close();
			  
			  WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		      WindowHandler.waitUntilWindowExistsTitleContains(driver, "Disney Reservation");
			  
		}
		
	   /**
		* @summary Method to verify whether the added Resort/RoomType Info is deleted from the 
		* 			AutoReduceByQuantity table.
		* @version Created 03/18/2016
		* @author Praveen Namburi, @return NA 
		*/
		public void validateAutoReduceByQuantityInfoAfterDeletingResort(){
			 pageLoaded(tblAutoReduceByaQuantity);
			 tblAutoReduceByaQuantity.syncVisible(driver);
			 List<WebElement> AutoReduceByQuantityTblRows = tblAutoReduceByaQuantity.findElements(By.tagName("tr"));
			 TestReporter.log("Total rows in AutoReduceByaQuantity table After Deleting the First record : "
					 	+ AutoReduceByQuantityTblRows.size());
			 TestReporter.assertTrue(AutoReduceByQuantityTblRows.size()<2,
					 "Added AutoReduce Resort/RoomType Info is deleted from Webtable.");
		}
	
	/**
	 * @summary: Method to click on Link Trim.
	 * @author: Praveen namburi, @version: Created 3/17/2016
	 */
	public void clickTrim(){
		pageLoaded(lnkTrim);
		lnkTrim.highlight(driver);
		lnkTrim.jsClick(driver);
	}
	
	/**
	 * @summary: Method to set the Trim details.
	 * @author : Praveen Namburi, @version: Created 3/17/2016
	 * @param scenario
	 */
	public void setTrimDetails(String scenario){
		datatable.setVirtualtablePage("AdvancedTrimAndTrimdetails");
		datatable.setVirtualtableScenario(scenario);
		
		String setAutoTrim = datatable.getDataParameter("SetAutoTrim");
		String trimDay = datatable.getDataParameter("TrimDay");
		
		//click on link Trim
		clickTrim();
		if(setAutoTrim.equalsIgnoreCase("ON")){
			pageLoaded(chkAutoTrim);
			chkAutoTrim.highlight(driver);
			chkAutoTrim.check();
		}
		txtTrimDay.highlight(driver);
		txtTrimDay.safeSet(trimDay);
	}
	
	/**
	 * @summary: Method to click on Link Packages.
	 * @author: Praveen namburi, @version: Created 3/14/2016
	 */
	public void clickPackages(){
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Disney Reservation");
		pageLoaded(lnkPackages);
		lnkPackages.highlight(driver);
		String winHandleBefore = driver.getWindowHandle();
		lnkPackages.focus(driver);
		lnkPackages.sendKeys(Keys.ENTER);
		Sleeper.sleep(3000);
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		//WindowHandler.waitUntilWindowExistsTitleContains(driver, "--");
		
		for(String handle : driver.getWindowHandles()){
			if(!handle.equalsIgnoreCase(winHandleBefore)){
				driver.switchTo().window(handle);
				break;
			}
		}
		
		boolean loaded = false;
		int counter = 0;
		int maxTries = 60;
		do{
			try{
				loaded = new PageLoaded().isDomComplete(driver, 1);
			}catch(Exception e){
				
			}
			counter++;
		}while(!loaded && counter <= maxTries);
		TestReporter.assertTrue(loaded, "The pacakge page did not load.");
	}
	
	public String capturePackageCode(String scenario){
		System.out.println();
		String packageCode = "";
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Disney Reservation");
		
		datatable.setVirtualtablePage("GroupPackagesInfo");
		datatable.setVirtualtableScenario(scenario);
//		String parentWindowHandle = driver.getWindowHandle();
		packageCode = datatable.getDataParameter("PackageName");		
		clickPackages();
		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		List<WebElement> elements = null;
		int count = 0;
		long maxTries = Constants.ELEMENT_TIMEOUT;
		if(packageCode.contains("GRP AND CONV")) packageCode = packageCode.replace("GRP AND CONV", "Groups and Conventions");
		do{
			try{
				elements = driver.findElements(By.xpath("//td[text()='"+packageCode+"']"));
			}catch(Exception e){
				Sleeper.sleep(1000);
				pageLoaded();
			}
			count++;
		}while(count <= maxTries && elements.size() < 1);
		
		WebElement row = null;
		try{
			row = elements.get(0).findElement(By.xpath(".."));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		packageCode = row.getText().split(" ")[1].trim();
		driver.close();
//		try{
//			elements = driver.findElements(By.partialLinkText("Edit"));
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		String secondWinHandle = driver.getWindowHandle();
//		elements.get(0).sendKeys(Keys.ENTER);
//		PleaseWait.WaitForPleaseWait(driver);
//		
//		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
//		
//		for(String handle : driver.getWindowHandles()){
//			if(handle.equalsIgnoreCase(secondWinHandle)){
//				driver.switchTo().window(handle);
//				break;
//			}
//		}
//		
//		try{
//			//clickConfirmAllInCustomPkg();
//			elements = driver.findElements(By.name("confirmAll"));
//			elements.get(0).sendKeys(Keys.ENTER);
//			PleaseWait.WaitForPleaseWait(driver);
//		}catch(Exception e){
//			e.printStackTrace();
//		}

		driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT, TimeUnit.SECONDS);
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Disney Reservation");

		return packageCode;
	}
	
	/**
	 * @summary: Method to verify whether the searched package info exists in the table.
	 * @author: Praveen Namburi, @version: Created 3/22/2016
	 * @param scenario
	 */
	public void verifySearchedPackageDetails(String scenario){
		datatable.setVirtualtablePage("GroupPackagesInfo");
		datatable.setVirtualtableScenario(scenario);
		System.out.println();
		String packageName = datatable.getDataParameter("PackageName");
		
		pageLoaded(searchedPkgresultsTbl);
		searchedPkgresultsTbl.syncVisible(driver);
		List<WebElement> pkgResultsTableRows = null;
		try{
			pkgResultsTableRows = searchedPkgresultsTbl.findElements(By.tagName("tr"));
		}catch(Exception e){
			
		}
		TestReporter.log("Total rows in pkg Searched Results table : "+pkgResultsTableRows.size());
		
		
		
		
		TestReporter.assertTrue(pkgResultsTableRows.size() > 1,
				"Searched package results table doesnot contains the Expexted text : "+packageName+" ");
		
		boolean packageFound = false;
		int numRows = pkgResultsTableRows.size();
		for (int Try = 0; Try < 2; Try++) {
			for (int rows = 0; rows < numRows; rows++) {
				if (pkgResultsTableRows.get(rows).getText().contains(packageName)) {
					TestReporter.log("Searched Package details exists in the table.");
					packageFound = true;
					break;
				}

			}
			if(packageFound){
				break;
			}else{
				if(packageName.contains("GRP AND CONV")){
					packageName = packageName.replace("GRP AND CONV", "Groups and Conventions");
				}
			}
		}
		//Used the below line to close the Group package window.
		driver.close();
		
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Disney Reservation");
	}
	
	/**
	 * @summary: Method to search the package code.
	 * @author: Praveen Namburi, @version: Created 3/15/2016 
	 * @param scenario
	 */
	public void searchPackageCode(String scenario){
		datatable.setVirtualtablePage("GroupPackagesInfo");
		datatable.setVirtualtableScenario(scenario);
		
		String setGroupsChannel = datatable.getDataParameter("GroupsDistChannel");
		String setRSRChannel = datatable.getDataParameter("RSRDistChannel");
		String packageCode = datatable.getDataParameter("PackageCode");
		String packageDesc = datatable.getDataParameter("PackageDescription");
		String setStartDate = datatable.getDataParameter("StartDate");
		String daysOut = datatable.getDataParameter("DaysOut");

		if(setGroupsChannel.equalsIgnoreCase("ON")){
			pageLoaded(radGroupsDistChannel);
			radGroupsDistChannel.highlight(driver);
			radGroupsDistChannel.jsClick(driver);
		}else if (setRSRChannel.equalsIgnoreCase("ON")) {
			pageLoaded(radRSRDistChannel);
			radRSRDistChannel.highlight(driver);
			radRSRDistChannel.jsClick(driver);
		}
		
		pageLoaded(txtPackageCode);
		txtPackageCode.highlight(driver);
		txtPackageCode.safeSet(packageCode);
		txtPackageDescription.safeSet(packageDesc);
		if(setStartDate.equalsIgnoreCase("True")){
			setPackageStartDate(daysOut);
		}
		//Click on button search in packages window.
		clickbtnSearch();
	}

	/**
	 * @summary: Method to click on the Customize link based on the package name from datatable.
	 * @author: Praveen namburi, @version: Created 3/16/2016.
	 */
	public void clickCustomizeLink(String scenario){
		datatable.setVirtualtablePage("GroupPackagesInfo");
		datatable.setVirtualtableScenario(scenario);
		
		String packageName = datatable.getDataParameter("PackageName");
		
		List<WebElement> totalRows = eleSearchResultsTbl.findElements(By.tagName("tr"));
		TestReporter.log("Total no. of rows in Search Results Table is : "
						+totalRows.size());
		TestReporter.assertGreaterThanZero(totalRows.size());
		
		for(int row=1; row<=totalRows.size(); row++){
			if(totalRows.get((row)).getText().contains(packageName)){
				System.out.println(totalRows.get((row)).getText());
				String customizeLink = "//*[@id='RESULTS']/table[2]/tbody/tr["+(row+1)+"]/td[1]/a";
				Element link = new ElementImpl(driver.findElement(By.xpath(customizeLink)));
				if (link.syncVisible(driver, 3, false)) {
					link.scrollIntoView(driver);
					link.highlight(driver);
					link.jsClick(driver);
				    break;
			    }
		    }
	    }
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
	}
	
	/**
	 * @summary: Method to set,update and clear the Custom package.
	 * @author: Praveen Namburi, @version: Created 3/16/2016
	 * @param scenario
	 */
	public void updateAndClearCustomPackage(String scenario){
		datatable.setVirtualtablePage("GroupPackagesInfo");
		datatable.setVirtualtableScenario(scenario);
		
		String resort = datatable.getDataParameter("Resort");
		String roomTypes = datatable.getDataParameter("RoomTypes");
		String setConfigType = datatable.getDataParameter("SetConfigType");
		String daysOut = datatable.getDataParameter("DaysOut");
		String numOfNights = datatable.getDataParameter("NumOfNights");
		String customRate = datatable.getDataParameter("CustomRate");
		String additionalAdultCharge = datatable.getDataParameter("AdditionalAdultCharge");
		
		//Select Resort
		pageLoaded(lstPkgResort);
		lstPkgResort.isEnabled();
		lstPkgResort.highlight(driver);
		lstPkgResort.select(resort);
		
		//Select RoomType
		do {
			try{
				lstPkgRoomTypes.syncEnabled(driver, 5,false);
			}catch (Exception e) {
				pageLoaded(lstPkgRoomTypes);
			}
		} while (!lstPkgRoomTypes.isEnabled() & lstPkgRoomTypes.isDisplayed());
		TestReporter.assertTrue(lstPkgRoomTypes.syncEnabled(driver),
				"Listbox PackageRoomTypes is not visible.");
		pageLoaded(lstPkgRoomTypes);
		lstPkgRoomTypes.highlight(driver);
		lstPkgRoomTypes.select(roomTypes);
		
		//Verify the selected resort room type listbox is enabled
		do {
			try{
				lstResortRoomTypesArray.syncEnabled(driver, 5,false);
			}catch (Exception e) {
				pageLoaded(lstResortRoomTypesArray);
			}
		} while (!lstResortRoomTypesArray.isEnabled() & lstResortRoomTypesArray.isDisplayed());
		TestReporter.assertTrue(lstResortRoomTypesArray.syncEnabled(driver),
				"listbox ResortRoomTypes Array is not enabled");
		
		if(setConfigType.equalsIgnoreCase("ON")){
			pageLoaded(radConfigType);
			radConfigType.highlight(driver);
			radConfigType.focusClick(driver);
			
			// Textbox - 'From date' to be Visible within 4 sec's.
			do {
				  try{
					  txtPkgFromDate.syncVisible(driver, 4,false);
					}catch (Exception e) {
						pageLoaded(txtPkgFromDate);
					}
			} while (!txtPkgFromDate.isEnabled() & txtPkgFromDate.isDisplayed());
					TestReporter.assertTrue(txtPkgFromDate.syncVisible(driver),
					"Package From date textbox is not visible after selecting "
					+ "the ConfigType - 'Start-extend' radio option.");
			
			//Set package from and to dates
			setPackageFromAndTOdates(daysOut, numOfNights);
			
			pageLoaded(radCustomRate);
			radCustomRate.highlight(driver);
			radCustomRate.jsClick(driver);
			pageLoaded(txtCustomRate);
			txtCustomRate.syncEnabled(driver, 3, false);
			txtCustomRate.safeSet(customRate);
			
			pageLoaded(radAdultCharge);
			radAdultCharge.highlight(driver);
			radAdultCharge.jsClick(driver);
			pageLoaded(txtAdditionalAdultCharge);
			txtAdditionalAdultCharge.syncEnabled(driver, 3, false);
			txtAdditionalAdultCharge.safeSet(additionalAdultCharge);
		}
		
		//Click on Update button for custom package.
		clickbtnUpdateCustomPackage();
		
		//Click on link Clear.
		clearSelectedRoomTypes();
	}
	
	/**
	 * @summary: Method to set Custom package dates from and to.
	 * @author: Praveen namburi, @version: Created 3/16/2016
	 * @param daysOut, @param numOfNights
	 */
	public void setPackageFromAndTOdates(String daysOut, String numOfNights){
		String packageFromDate = DateTimeConversion.getDaysOut(daysOut, "MM/dd/yyyy");
		String packageToDate = DateTimeConversion.getDaysOut(numOfNights, "MM/dd/yyyy");
		TestReporter.log("Package From date : " + packageFromDate);
		TestReporter.log("Package To date : " + packageToDate);
		
		txtPkgFromDate.highlight(driver);
		txtPkgFromDate.safeSet(packageFromDate);
		
		txtPkgToDate.highlight(driver);
		txtPkgToDate.safeSet(packageToDate);
	}
	
	public void setPackageStartDate(String daysOut){
		String packageStartDate = DateTimeConversion.getDaysOut(daysOut, "MM/dd/yyyy");
		TestReporter.log("Package Start date : " + packageStartDate);
		
		txtStartDate.highlight(driver);
		txtStartDate.safeSet(packageStartDate);
	}

	/**
	 * @summary: Method to click on Update button in Custom Package.
	 * @author: Praveen Namburi, @version: Created 3/16/2016
	 */
	public void clickbtnUpdateCustomPackage(){
		pageLoaded(btnUpdate);
		btnUpdate.isEnabled();
		btnUpdate.highlight(driver);
		btnUpdate.jsClick(driver);
	}
	
	public void clearSelectedRoomTypes(){
		pageLoaded(lnkclear);
		lnkclear.syncVisible(driver);
		lnkclear.highlight(driver);
		lnkclear.jsClick(driver);
	}
	
	/**
	 * @summary: Method to click ConfirmAll button in Custom package window.
	 * @author: praveen namburi, @version: created 3/16/2016
	 */
	public void clickConfirmAllInCustomPkg(){
		pageLoaded(btnConfirmAll);
		btnConfirmAll.isEnabled();
		btnConfirmAll.highlight(driver);
		btnConfirmAll.jsClick(driver);
		
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
    	WindowHandler.waitUntilWindowExistsTitleContains(driver, "Disney Reservation");
	}

	public void clickbtnSearch(){
		pageLoaded(btnSearch);
		btnSearch.highlight(driver);
		btnSearch.jsClick(driver);
	}
	
	/**
	 * @summary: Method to click on link Allotment.
	 * @author: Praveen Namburi,@version: Created 3/11/2016.
	 */
	public void clickAllotment(){
		pageLoaded(lnkAllotment);
		lnkAllotment.highlight(driver);
		lnkAllotment.jsClick(driver);
	}
	
	/**
	 * @summary: Method to click on button Add in AllotmentAvailability.
	 * @author: Praveen Namburi,@version: Created 3/11/2016.
	 */
	public void clickAddAvailable(){
		pageLoaded(btnAddAvailable);
		btnAddAvailable.highlight(driver);
		btnAddAvailable.jsClick(driver);
	}
	
	/**
	 * @summary: Method to click on button Add in AllotmentBooking.
	 * @author: Praveen Namburi,@version: Created 3/11/2016.
	 */
	public void clickAddBooking(){
		pageLoaded(btnAddBooking);
		btnAddBooking.highlight(driver);
		btnAddBooking.jsClick(driver);
	}
	
	/**
	 * @summary: Method to select and Add Allotment Availability and Booking.
	 * @author: Praveen Namburi, @version: Created 3/11/2016
	 */
	public void addAllotmentAvailabilityAndBooking(String scenario){
		datatable.setVirtualtablePage("AllotmentAvailabilityAndBooking");
		datatable.setVirtualtableScenario(scenario);

		String availableRate = datatable.getDataParameter("AvailableRate");
		String availableAssociations = datatable.getDataParameter("AvailableAssociations");
		String bookingRate = datatable.getDataParameter("BookingRate");
		String bookingAssociations = datatable.getDataParameter("BookingAssociations");
		
		initialize();
		clickAllotment();
		
		//Select and Add allotment availability.
		pageLoaded(lstAllotAvailRate);
		lstAllotAvailRate.isEnabled();
		lstAllotAvailRate.highlight(driver);
		lstAllotAvailRate.select(availableRate);
		lstAllotAvailableAssociations.select(availableAssociations);
		clickAddAvailable();
		//Select and Add allotment booking.
		do {
			initialize();
			try{
				lstAllotBookingRateCateg.syncVisible(driver, 5,false);
			}catch (Exception e) {
				pageLoaded(lstAllotBookingRateCateg);
			}
		} while (!lstAllotBookingRateCateg.isEnabled() & lstAllotBookingRateCateg.isDisplayed());
		TestReporter.assertTrue(lstAllotBookingRateCateg.syncEnabled(driver),
				"Listbox AllotBookingRateCateg is not visible.");
		lstAllotBookingRateCateg.isEnabled();
		lstAllotBookingRateCateg.highlight(driver);
		lstAllotBookingRateCateg.select(bookingRate);
		lstAllotBookingAssocRateCateg.select(bookingAssociations);
		clickAddBooking();
	}
	
	/**
	 * @summary: Method to click on Link BlockFamily. 
	 * @author: Praveen namburi, @version: Created 3/4/2016
	 */
	public void clickBlockFamily(){
		pageLoaded(lnkBlockFamily);
		lnkBlockFamily.highlight(driver);
		lnkBlockFamily.jsClick(driver);
	}
	
	/**
	 * @summary: Method to click on Link Grow. 
	 * @author: Praveen namburi, @version: Created 3/9/2016
	 */
	public void clickGrow(){
		pageLoaded(lnkGrow);
		lnkGrow.highlight(driver);
		lnkGrow.jsClick(driver);
	}
	
	public void clickbtnShowHideBlocksList(){
		pageLoaded(btnShowHideBlocksList);
		btnShowHideBlocksList.isEnabled();
		btnShowHideBlocksList.highlight(driver);
		btnShowHideBlocksList.click();
	}
	
	public void clickEditGroup(){
		pageLoaded(btnEditGroup);
		btnEditGroup.isEnabled();
		btnEditGroup.highlight(driver);
		btnEditGroup.jsClick(driver);
	
		GroupPageContentFrame grpPageContent = new GroupPageContentFrame(driver);
		grpPageContent.acceptAlert();
		
	    //If Alert pop-up is displayed, then accept it.
	   /* if(AlertHandler.isAlertPresent(driver, 10)){
		   AlertHandler.handleAlerts(driver, 10);
	    }*/
	}

	/**
	 * @summary: Method to click on button Go-To-Inventory in Block Management. 
	 * @author: Praveen namburi, @version: Created 3/10/2016
	 */
	public void clickbtnGoToInventory(){
		pageLoaded(btnGoToBlockInventory);
		btnGoToBlockInventory.highlight(driver);
//		btnGoToBlockInventory.jsClick(driver);
		btnGoToBlockInventory.focus(driver);
		btnGoToBlockInventory.sendKeys(Keys.ENTER);
		
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Sign In");
	}
	
	/**
	 * @summary: Method to select the resort in 'FilterResort for BlockInventory' Window. 
	 * @author: Praveen Namburi, @version: Created 3/10/2016
	 * @param scenario
	 */
	public void seletFilterResort(String scenario){
		datatable.setVirtualtablePage("BlockManagementAndBookingSrc");
		datatable.setVirtualtableScenario(scenario);

		String resort  = datatable.getDataParameter("SelectResort");
		
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Filter Resorts");
		
		//initialize();
		lstResort.isEnabled();
		lstResort.highlight(driver);
		lstResort.select(resort);
		
		//Click on OK button to switch out of the window.
		clickbtnOk();
	}
	
	/**
	 * @summary: Method to select and add the block inventory details.
	 * @author: praveen namburi, @version: Created 3/11/2016 
	 * @param scenario
	 */
	public void addBlockInventorySetup(String scenario){
		datatable.setVirtualtablePage("BlockManagementAndBookingSrc");
		datatable.setVirtualtableScenario(scenario);

		String action = datatable.getDataParameter("Action");
		String blockAllot = datatable.getDataParameter("BlockAllot");
		String rateCategory = datatable.getDataParameter("RateCategory");
//		String resortInv = datatable.getDataParameter("ResortInv");
		resortRoomTypes = datatable.getDataParameter("ResortRoomTypes");
		String totalQuantity = datatable.getDataParameter("TotalQuantity");
		
		pageLoaded(lstAction);
		lstAction.select(action);
		lstBlockAllot.select(blockAllot);
		lstRateCategory.sendKeys(rateCategory);
		
		pageLoaded(lstResortInv);
		List<WebElement> options = lstResortInv.getOptions();
		TestReporter.log("Total count of options in Resort listbox : "+options.size());
		options.get(0).click();
		
		do {
			 initialize();
			 try {
				
				lstResortRoomTypes.syncEnabled(driver, 5, false);
				List<WebElement> resortRoomTypeList = lstResortRoomTypes.getOptions();
				TestReporter.log("Total count of options in ResortRoomType listbox : "
							+resortRoomTypeList.size());
				
			 } catch (Exception e) {
				 pageLoaded(lstResortRoomTypes);
			 }
			
		} while (!lstResortRoomTypes.isEnabled() & lstResortRoomTypes.isDisplayed());
		TestReporter.assertTrue(lstResortRoomTypes.getOptions().size() > 1, 
				"No options were loaded for the ResortroomTypes listbox.");
		
		lstResortRoomTypes.highlight(driver);
		lstResortRoomTypes.select(resortRoomTypes);
		
		//Click on Add link
		do {
			initialize();
			try{
				lnkAdd.syncEnabled(driver, 4,false);
			}catch (Exception e) {
				pageLoaded(lnkAdd);
			}
		} while (!lnkAdd.isEnabled() & lnkAdd.isDisplayed());
		TestReporter.assertTrue(lnkAdd.syncEnabled(driver),
				"Add link is not enabled after selecting ResortRoomType value.");
		
		lnkAdd.highlight(driver);
		lnkAdd.jsClick(driver);
		
		//Select the Radio button - 'Start and extend To' for BlockInventoryRoomType
		pageLoaded(radBlockInvStartAndExtendTo);
		radBlockInvStartAndExtendTo.highlight(driver);
		radBlockInvStartAndExtendTo.click();
		
		//Set the value in Total quantity textbox.
		do {
			  initialize();
			  try{
					txtTotalQuantity.syncVisible(driver, 4,false);
				}catch (Exception e) {
					pageLoaded(txtTotalQuantity);
				}
		} while (!txtTotalQuantity.isEnabled() & txtTotalQuantity.isDisplayed());
				TestReporter.assertTrue(txtTotalQuantity.syncVisible(driver),
				"TOTAL QUANTITY textbox is not visible after selecting the BlockInvIputType - 'Start and extend To' - radio option.");
	
		txtTotalQuantity.isEnabled();
		txtTotalQuantity.highlight(driver);
		txtTotalQuantity.safeSet(totalQuantity);
		
		clickbtnAdd();
		
	}
	
	public String getRoomType(){
		return resortRoomTypes;
	}
	
	/**
	 * @summary: Method to click on button Add in BlockInventory.
	 * @author: praveen namburi, @version: Created 3/11/2016
	 */
	public void clickbtnAdd(){
		pageLoaded(btnAdd);
		btnAdd.isEnabled();
		btnAdd.highlight(driver);
		btnAdd.jsClick(driver);
	}
	
	/**
	 * @summary: Method to click on Save button in BlockInventory.
	 * @author: praveen namburi, @version: Created 3/11/2016
	 */
	public void clickbtnSave(){
		initialize();
		btnSave.isEnabled();
		btnSave.highlight(driver);
		btnSave.jsClick(driver);
	}
	
	/**
	 * @summary: Method to verify whether the added allotment details exists in table before deleting them.
	 * @author: Praveen Namburi, @version: Created 3/11/2016.
	 */
	public void verifyAddedAllotmentBeforeDeleting(){
		initialize();
		List<WebElement> allotmentTblRows = eleAllotmentTblId.findElements(By.tagName("tr"));
		TestReporter.log("Total no. of rows in Allotment Table is : "
						+allotmentTblRows.size());
		TestReporter.assertGreaterThanZero(allotmentTblRows.size());
		
		//List<WebElement> delButtons = eleAllotmentTblId.findElements(By.tagName("a"));
		List<WebElement> delButtons = driver.findElements(By.partialLinkText("Del"));
		TestReporter.log("Total no. of delete buttons before deleting the added Allotment: "+delButtons.size());
		TestReporter.assertTrue(delButtons.size() > 1, "Added Allotment details exists in the table.");
		for(WebElement del : delButtons){
			Element delButton = new ElementImpl(del);
			if(delButton.syncVisible(driver, 2, false)){
				delButton.highlight(driver);
				delButton.jsClick(driver);
				break;
			}
		}
	}
	
	/**
	 * @summary: Method to verify the added allotment info is deleted.
	 * @author: Praveen Namburi, @version: Created 3/11/2016
	 */
	public void verifyAddedAllotmentAfterDeletion(){
		initialize();
		List<WebElement> allotmentTblRows = eleAllotmentTblId.findElements(By.tagName("tr"));
		TestReporter.log("Total no. of rows in Allotment Table is : "
						+allotmentTblRows.size());
		TestReporter.assertGreaterThanZero(allotmentTblRows.size());
		
		List<WebElement> delButton = driver.findElements(By.partialLinkText("Del"));
		TestReporter.log("Total no. of delete buttons after deleting the added Allotment: "+delButton.size());
		TestReporter.assertTrue(delButton.size() < 2, "First added Allotment details are deleted from the table.");
	}
	
	/**
	 * @summary: Method to click on Ok button in 'FilterResort for BlockInventory' Window.
	 * @author: Praveen Namburi, @version: Created 3/10/2016
	 */
	public void clickbtnOk(){
		//initialize();
		pageLoaded(btnOK);
		btnOK.isEnabled();
		btnOK.highlight(driver);
		btnOK.click();
		
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Disney Reservation");
	}
	
	/**
	 * @summary: Method to click on Image Link ParentBlockSearch. 
	 * @author: Praveen namburi, @version: Created 3/4/2016
	 */
	public void clickParentBlockSearch(){
		pageLoaded(lnkParentBlockIDSearch);
		lnkParentBlockIDSearch.highlight(driver);
		lnkParentBlockIDSearch.jsClick(driver);
		
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Group Search");
	}
	
	/**
	 * @summary: Method to click on checkBox - ParentDuringAvailEval.
	 * @author: praveen Namburi, @Version: Created 3-7-2016
	 */
	public void checkParentDuringAvailEval(){
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
    	WindowHandler.waitUntilWindowExistsTitleContains(driver, "Disney Reservation");
		initialize();
		chkParentDuringAvaiEval.isEnabled();
		chkParentDuringAvaiEval.jsToggle(driver);
	}
	
	/**
	 * @summary: Method to click on Link BillingProfile. 
	 * @author: Praveen namburi, @version: Created 3/4/2016
	 */
	public void clickBillingProfile(){
		pageLoaded(lnkBillingProfile);
		lnkBillingProfile.highlight(driver);
		lnkBillingProfile.click();
		
		/*FrameHandler.findAndSwitchToFrame(driver, "contentFrame");*/
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Bill Profile Template");
	}
	
	/**
	 * @summary: Method to Select the Utilization Codes Growth Info checkboxes.
	 * @author: Praveen Namburi, @version: Created 3/9/2016
	 * @param scenario
	 */
	public void selectUtilizationCodesGrowthInfo(String scenario){
		datatable.setVirtualtablePage("SetGrowthInfo");
		datatable.setVirtualtableScenario(scenario);

		String growthDatesNoInventory = datatable.getDataParameter("GrowthDatesNoInventory");
		String growPrePostDates = datatable.getDataParameter("GrowPrePostDates");
		String expandFreesellAvailCheck = datatable.getDataParameter("ExpandFreesellAvailCheck");
		String bookNonBlockRoomType = datatable.getDataParameter("BookNonBlockRoomType");
		String bookNonBlockRateCategory = datatable.getDataParameter("BookNonBlockRateCategory");
		String overrideFreesellBookCheck = datatable.getDataParameter("OverrideFreesellBookCheck");
		
		pageLoaded(chkGrowthDatesNoInventory);
		//Check or Un-check the check-boxes based on the condition from datatable.
		if(!chkGrowthDatesNoInventory.isChecked() & growthDatesNoInventory.equalsIgnoreCase("Check")){
			chkGrowthDatesNoInventory.highlight(driver);
			chkGrowthDatesNoInventory.jsToggle(driver);
		}else if (chkGrowthDatesNoInventory.isChecked() & growthDatesNoInventory.equalsIgnoreCase("Uncheck")) {
			chkGrowthDatesNoInventory.highlight(driver);
			chkGrowthDatesNoInventory.jsToggle(driver);
		}
		
		if(!chkGrowPrePostDates.isChecked() & growPrePostDates.equalsIgnoreCase("Check")){
			chkGrowPrePostDates.highlight(driver);
			chkGrowPrePostDates.jsToggle(driver);
		}else if (chkGrowPrePostDates.isChecked() & growPrePostDates.equalsIgnoreCase("Uncheck")) {
			chkGrowPrePostDates.highlight(driver);
			chkGrowPrePostDates.jsToggle(driver);
		}
		
		if(!chkExpandFreesellAvailCheck.isChecked() &expandFreesellAvailCheck.equalsIgnoreCase("Check")){
			chkExpandFreesellAvailCheck.highlight(driver);
			chkExpandFreesellAvailCheck.jsToggle(driver);
		}else if (chkExpandFreesellAvailCheck.isChecked() & expandFreesellAvailCheck.equalsIgnoreCase("Uncheck")) {
			chkExpandFreesellAvailCheck.highlight(driver);
			chkExpandFreesellAvailCheck.jsToggle(driver);
		}
		
		if(!chkBookNonBlockRoomType.isChecked() & bookNonBlockRoomType.equalsIgnoreCase("Check")){
			chkBookNonBlockRoomType.highlight(driver);
			chkBookNonBlockRoomType.jsToggle(driver);
		}else if (chkBookNonBlockRoomType.isChecked() & bookNonBlockRoomType.equalsIgnoreCase("Uncheck")) {
			chkBookNonBlockRoomType.highlight(driver);
			chkBookNonBlockRoomType.jsToggle(driver);
		}
		
		if(!chkBookNonBlockRateCategory.isChecked() & bookNonBlockRateCategory.equalsIgnoreCase("Check")){
			chkBookNonBlockRateCategory.highlight(driver);
			chkBookNonBlockRateCategory.jsToggle(driver);
		}else if (chkBookNonBlockRateCategory.isChecked() & bookNonBlockRateCategory.equalsIgnoreCase("Uncheck")) {
			chkBookNonBlockRateCategory.highlight(driver);
			chkBookNonBlockRateCategory.jsToggle(driver);
		}
		
		if(!chkOverrideFreesellBookCheck.isChecked() & overrideFreesellBookCheck.equalsIgnoreCase("Check")){
			chkOverrideFreesellBookCheck.highlight(driver);
			chkOverrideFreesellBookCheck.jsToggle(driver);
		}else if (chkOverrideFreesellBookCheck.isChecked() & overrideFreesellBookCheck.equalsIgnoreCase("Uncheck")) {
			chkOverrideFreesellBookCheck.highlight(driver);
			chkOverrideFreesellBookCheck.jsToggle(driver);
		}

	}
	
}


