package apps.dreams.GroupSearchWindow;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.dreams.GuestSearchWindow.GuestPartyWindow;
import apps.dreams.GuestSearchWindow.GuestSearch;
import apps.dreams.PleaseWait.PleaseWait;
import core.AlertHandler;
import core.FrameHandler;
import core.WindowHandler;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Link;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.ButtonImpl;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Randomness;
import utils.TestReporter;
import utils.date.DateTimeConversion;

/**
 * @summary Contains the methods & objects for the Dreams UI content frame for
 *          Group page
 * @version Created 01/29/2016
 * @author Venkatesh Atmakuri
 */
public class GroupPageContentFrame {
	
	// ****************************
	// *** Content Frame Fields ***
	// ****************************	
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	private String homeResort;
	
	// ******************************
	// *** Content Frame Elements ***
	// ******************************
	
	@FindBy(name = "groupInfo.code")
	private Textbox txtGroupInfoCode;
	
	@FindBy(name = "groupInfo.name")
	private Textbox txtGroupName;
	
	@FindBy(name = "groupInfo.alias")
	private Textbox txtGroupAlias;
	
	@FindBy(name = "groupInfo.reservationMethod")
	private Listbox lstReservationMethod;
	
	@FindBy(name = "groupInfo.homeResort")
	private Listbox lstHomeResort;
	
	@FindBy(name = "groupInfo.bookedByGRO")
	private Listbox lstResevationOffice;
	
	@FindBy(id = "grpStatus")
	private Listbox lstGroupStatus;
	
	@FindBy(name = "groupInfo.arrivalDateAsString")
	private Textbox txtGroupArrivalDate;
	
	@FindBy(name = "groupInfo.departureDateAsString")
	private Textbox txtGroupDepartDate;
	
	@FindBy(id = "callsAcceptedDateAsString")
	private Textbox txtCallsAcceptedDate;
	
	@FindBy(partialLinkText = "Organization")
	private Link lnkOrganization;
	
	@FindBy(partialLinkText = "Comment Entry")
	private Link lnkCommentEntry;
	
	@FindBy(xpath = "//*[@id='ORG']/table/tbody/tr[2]/td[1]/a/img[@src = '/dpms/images/i_search.png']")
	private Link lnkOrgSearch;
	
	@FindBy(name = "filter.agencyId")
	private Textbox txtOrgSearchAgentID;
	
	@FindBy(name = "Search")
	private Button eleOrgAgentSearch;
	
	@FindBy(partialLinkText = "Contact")
	private Link lnkContact;
	
	@FindBy(partialLinkText = "Group Confirmations")
	private Link lnkGroupConfirmations;
	
	@FindBy(xpath ="//*[@id='CONTACT']/table/tbody/tr[2]/td[1]/a/img")
	private Link lnkContactSearch;
	
	@FindBy(name = "contactFormUtilList.displayableContacts[0].groupContactRoleTO.role")
	private Listbox lstContactRole;
	
	@FindBy(name = "contactFormUtilList.displayableContacts[1].groupContactRoleTO.role")
	private Listbox lstContactRole2;
	
	@FindBy(partialLinkText = "Group Information Details")
	private Link lnkGrpInfoDetails;
	
	@FindBy(name = "groupInfo.opportunityType")
	private Listbox lstGrpInfoOpportunityType;
	
	@FindBy(name = "groupInfo.businessSource")
	private Listbox lstGrpInfoBusinessSource;
	
	@FindBy(name = "groupInfo.mainArrivalDateAsString")
	private Textbox txtGrpInfoArrivalDate;
	
	@FindBy(name = "groupInfo.mainDepartureDateAsString")
	private Textbox txtGrpInfoDepartureDate;
	
	@FindBy(name = "groupInfo.arrivalTimeAsString")
	private Textbox txtGrpInfoArrivalTime;
	
	@FindBy(name = "groupInfo.departureTimeAsString")
	private Textbox txtGrpInfoDepartureTime;
	
	@FindBy(name = "groupInfo.peakRoomCount")
	private Textbox txtGrpInfoPeakRoomCount;
	
	@FindBy(name = "groupInfo.totalRoomNights")
	private Textbox txtGrpInfoTotalRoomNights;
	
	@FindBy(name = "groupInfo.salesDepartment")
	private Textbox txtGrpInfoSalesDepartment;
	
	@FindBy(name = "groupInfo.salesManager")
	private Textbox txtGrpInfoSalesManager;
	
	@FindBy(name = "groupInfo.salesPhone")
	private Textbox txtGrpInfoSalesPhone;
	
	@FindBy(name = "groupInfo.serviceDepartment")
	private Textbox txtGrpInfoServiceDepartment;
	
	@FindBy(name = "groupInfo.serviceManager")
	private Textbox txtGrpInfoServiceManager;
	
	@FindBy(name = "groupInfo.servicePhone")
	private Textbox txtGrpInfoServicePhone;
	
	@FindBy(name = "Ok")
	private Button btnOk;
	
	@FindBy(name = "b_Yes")
	private Button btnYes;
	
	@FindBy(xpath = "//*[@id='b_AddAutoReduce'][@value='Edit']")
	private Button btnEdit;
	
	@FindBy(partialLinkText = "Edit")
	private Link lnkEdit;
	
	@FindBy(partialLinkText = "Special Considerations")
	private Link lnkSplConsiderations;
	
	@FindBy(partialLinkText = "Contracted Resorts, Rates, and Packages")
	private Link lnkContractedResortsAndRates;
	
	@FindBy(name = "groupInfo.specialConsiderations.taxExemptType")
	private Listbox lstGrpTaxExmtType;
	
	@FindBy(id = "taxExemptId")
	private Textbox txtTaxExempttId;
	
	@FindBy(name = "groupInfo.groAgentName")
	private Textbox txtGRO_Agent;
	
	@FindBy(partialLinkText = "Affiliated Profiles")
	private Link lnkAffiliatedProfiles;
	
	@FindBy(partialLinkText = "Attrition Information")
	private Link lnkAttritionInformation;
	
	@FindBy(partialLinkText = "Block")
	private Link lnkBlock;
	
	@FindBy(name = "affGroupCode")
	private Textbox txtAffGroupCode;
	
	@FindBy(xpath = "//*[@name= 'b_AddAutoReduce'] [@value='Add'] [@onclick='addAffiliatedProfile()']")
	private Button btnAffAddButton;
	
	@FindBy(xpath = "//*[@name= 'b_AddAutoReduce'] [@value='Add'] [@onclick='addAttrition()']")
	private Button btnAttritionAdd;
	
	@FindBy(xpath = "//*[@name= 'b_LookUp1'] [@value='Add'] [@onclick='addGroupComments()']")
	private Button btnCommentEntryAdd;
	
	@FindBy(xpath = "//*[@id = 'b_AddAutoReduce'] [@value='Add'] [@onclick='addCodedRemark()']")
	private Button btnCodedRemarkAdd;
	
	@FindBy(xpath = "//*[@id='BLOCKLIST']/table/tbody/tr/td/table/tbody/tr[3]/td[2]/div/input")
	private Button btnEditBlock;
	
	@FindBy(xpath = "//*a[text()='Delete'][@onclick='deleteAttrion(0)']")
	private Link lnkAttritionDelete;
	
	@FindBy(id = "AFFPROFILES")
	private Element eleAffDiv;
	
	@FindBy(id = "orgdiv")
	private Element eleOrgResultTable;
	
	@FindBy(xpath = "//*[@id='AFFPROFILES']/table/tbody")
	private Element eleAffTable;
	
	@FindBy(xpath = "//*[@id='CONTACT']/table/tbody")
	private Element eleContactTable;
	
	@FindBy(xpath = "//*[@id='ATTRITION']/table/tbody")
	private Element eleAttritionTable;
	
	@FindBy(xpath = "//*[@id='CODEDREMARKS']/table/tbody")
	private Element eleCodedRemarksTable;
	
	@FindBy(xpath = "//*[@id='COMMENTS']/table/tbody")
	private Element eleGeneralCommentsTable;
	
	@FindBy(name = "groupInfo.wholeSaler")
	private Checkbox chkWholeSaler;
	
	@FindBy(name = "groupInfo.profileComplete")
	private Checkbox chkProfileComplete;
	
	@FindBy(name = "groupInfo.specialConsiderations.minimumLOSAsString")
	private Listbox lstMinLOS;
	
	@FindBy(name = "groupInfo.specialConsiderations.exhaustHomeResortFirst")
	private Checkbox chkEHRFirst; 
	
	@FindBy(name="groupInfo.specialConsiderations.magicalExpress")
	private Checkbox  chkDMEIndicator;
	
	@FindBy(name = "groupInfo.specialConsiderations.delegateChargingPreviliges")
	private Checkbox chkDCPreviliges;
	
	@FindBy(name = "groupInfo.specialConsiderations.hideRate")
	private Checkbox chkHideRate;
	
	@FindBy(name = "groupInfo.specialConsiderations.expressCheckout")
	private Checkbox chkExpCheckOut;
	
	@FindBy(name = "groupInfo.specialConsiderations.additionalAdultAmount")
	private Textbox txtAAAmount;
	
	@FindBy(name = "groupInfo.specialConsiderations.commissionable")
	private Checkbox chkCommissionable;
	
	@FindBy(name = "groupInfo.specialConsiderations.freeTimeLevel")
	private Listbox lstFReeTimeLevel;
	
	@FindBy(name = "groupInfo.specialConsiderations.collectVoucher")
	private Checkbox chkCollectVoucher;
	
	@FindBy(name = "groupInfo.specialConsiderations.reservationGuaranteed")
	private Checkbox chkResGuaranteed;
	
	@FindBy(name = "contractedResort")
	private Textbox txtContractedResort;
	
	@FindBy(name = "contractedRoomType")
	private Textbox txtContractedRoomType;
	
	@FindBy(name = "contractedPackage")
	private Textbox txtContractedPackage;
	
	@FindBy(name = "contractedFromDateStr")
	private Textbox txtContractedFromDateStr;
	
	@FindBy(name = "contractedToDateStr")
	private Textbox txtContractedToDateStr;
	
	@FindBy(name = "contractedRate")
	private Textbox txtContractedRate;
	
	@FindBy(name = "contractedAdultCharge")
	private Textbox txtContractedAdultCharge;
	
	@FindBy(id = "b_AddAutoReduce")
	private Button btnContractedAdd;
	
	@FindBy(id = "balance")
	private Checkbox chkGroupBalance;
	
	@FindBy(name = "groupInfo.confirmationDeliveryMethodAsString")
	private Listbox lstDeliveryMethod;
	
	@FindBy(name = "groupInfo.confirmationDestination")
	private Listbox lstDestination;
	
	@FindBy(name = "attritionScheduleDays")
	private Textbox txtAttritionScheduleDays;
	
	@FindBy(name = "attritionDateStr")
	private Textbox txtAttritionDate;
	
	@FindBy(name = "attritionPercent")
	private Textbox txtAttritionPercent;
	
	@FindBy(name = "attritionNotes")
	private Textbox txtAttritionNotes;
	
	@FindBy(name = "commentLevel")
	private Listbox lstCommentLevel;
	
	@FindBy(name = "commentSection")
	private Listbox lstCommentSection;
	
	@FindBy(name = "commentType")
	private Listbox lstCommentType;
	
	@FindBy(name = "commentRoutings")
	private Listbox lstCommentRoutings;
	
	@FindBy(name = "commentText")
	private Textbox txtCommentText;
	
	@FindBy(name = "fromGuest")
	private Textbox txtFromGuest;
	
	@FindBy(name = "toGuest")
	private Textbox txtToGuest;
	
	@FindBy(partialLinkText = "General Comments")
	private Link lnkGeneralComments;
	
	@FindBy(partialLinkText = "Coded Remarks")
	private Link lnkCodedRemarks;
	
	@FindBy(name = "groupInfo.commentList.groupGenericComments[0].text")
	private Textbox txtGroupComments;
	
	@FindBy(name = "codedRemarksLevelAsString")
	private Listbox lstCodedRemarksLevel;
	
	@FindBy(name = "codedRemarksType")
	private Listbox lstCodedRemarksType;
	
	@FindBy(name = "codedRemark")
	private Listbox lstCodedRemark;
	
	@FindBy(name = "b_New")
	private Button btnNew;
	
	// *********************
	// ** Build page area **
	// *********************

	/**
	 * @summary Constructor to initialize the frame
	 * @version Created 09/10/2014
	 * @author Waightstill W Avery
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public GroupPageContentFrame(WebDriver driver) {
		this.driver = driver;
		FrameHandler.findAndSwitchToFrame(driver, "contentFrame");
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);
	}
	
	private GroupPageContentFrame initialize() {
		FrameHandler.findAndSwitchToFrame(driver, "contentFrame");
		return ElementFactory.initElements(driver, this.getClass());
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, txtGroupInfoCode);
	}
	
	public boolean ispageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnEdit);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}
	
	// *****************************************
	// *** Content Frame Interactions ***
	// *****************************************

	/**
	 * @summary Method to enter Group Profile data
	 * @version Created 02/1/2016
	 * @author Venkatesh Atmakuri
	 * @param scenario
	 * @return Unique Group Profile Id
	 */
	public String enterGroupProfile(String scenario) {
		datatable.setVirtualtablePage("GroupProfiles");
		datatable.setVirtualtableScenario(scenario);

		String groupNumber;
		boolean validGroupNumber = false;
		do{
			groupNumber = Randomness.randomNumber(10);
			if(!groupNumber.startsWith("0")) validGroupNumber = true;
		}while(!validGroupNumber);
		String groupName = datatable.getDataParameter("GroupName")+groupNumber;
		String groupAliasName = datatable.getDataParameter("GroupAliasName");
		String groupStatus = datatable.getDataParameter("GroupStatus");
		String callsAcceptedDate = datatable.getDataParameter("CallsAcceptedDate");
		String reservationMethod = datatable.getDataParameter("ReservationMethod");
		homeResort = datatable.getDataParameter("HomeResort");
		String reservationOffice = datatable.getDataParameter("ReservationOffice");
		String GROAgent = datatable.getDataParameter("GROAgent");
//		String profileComplete = datatable.getDataParameter("ProfileComplete");
		String wholeSalerFlag = datatable.getDataParameter("WholeSalerFlag");
		String daysOut = datatable.getDataParameter("DaysOut");
		String numberNights = datatable.getDataParameter("NumberOfNights");
		String callsAcceptedDTCondition = datatable.getDataParameter("SetCallsAccepted");
		
		pageLoaded(txtGroupInfoCode);
		txtGroupInfoCode.safeSet(groupNumber);
		txtGroupName.safeSet(groupName);
		txtGroupAlias.safeSet(groupAliasName);
		setArrivalAndDepartDates(daysOut,numberNights);
		//Added the below if-condition to enter CallsAcceptedDate if needed.
		//Updated on 2-19-2016 - Praveen
		if(callsAcceptedDTCondition.equalsIgnoreCase("true")){
			setCallsAcceptedDate(callsAcceptedDate);
		}

		//modified 02/11/2016 - Venkatesh
		if(groupStatus.equalsIgnoreCase("Cancelled")){
			try{
				lstGroupStatus.select(groupStatus);
				acceptAlert();
			}catch(UnhandledAlertException | NoAlertPresentException e1){
				try{
					lstGroupStatus.select(groupStatus);
					acceptAlert();
				}catch(UnhandledAlertException | NoAlertPresentException e2){
				}
			}
		}else{
			lstGroupStatus.select(groupStatus);
		}
		
//		lstGroupStatus.select(groupStatus);
		lstReservationMethod.select(reservationMethod);
		lstHomeResort.select(homeResort);
		
		if(wholeSalerFlag.equalsIgnoreCase("ON"))
			chkWholeSaler.check();
		
		pageLoaded(txtGRO_Agent);
		txtGRO_Agent.isDisplayed();
		txtGRO_Agent.highlight(driver);
		txtGRO_Agent.safeSet(GROAgent);
		pageLoaded(lstResevationOffice);
		lstResevationOffice.select(reservationOffice);
		
		return groupNumber;
	}
	
	public String getHomeResort(){
		return homeResort;
	}
	
	/**
	 * @summary Method to set arrival and Depart dates based on dayto and number of nights to stay
	 * @version Created 02/1/2016
	 * @author Venkatesh Atmakuri
	 * @param scenarios
	 * @return NA
	 */
	public void setArrivalAndDepartDates(String daysOut, String numberNights){
		
		String groupArrivalDate = "";
		// Utilize try-catch blocks for negative scenarios where non-numeric
		// values are passed
		try{
			groupArrivalDate = DateTimeConversion.getDaysOut(daysOut, "MM/dd/yyyy");
		}catch(NumberFormatException nfe){
			groupArrivalDate = daysOut;
		}
		String groupDepartDate = "";
		try{if(daysOut == "0"){
			groupDepartDate = DateTimeConversion.getDaysOut(numberNights, "MM/dd/yyyy");
		}else{
			int A=Integer.parseInt(numberNights)+Integer.parseInt(daysOut);
			String Value=""+A;
			groupDepartDate = DateTimeConversion.getDaysOut(Value, "MM/dd/yyyy");
		}
			 
		}catch(NumberFormatException nfe){
			groupDepartDate = numberNights;
		}
		TestReporter.log("Arrival Date : " + groupArrivalDate);
		TestReporter.log("Depart Date : " + groupDepartDate);
		
		txtGroupArrivalDate.highlight(driver);
		txtGroupArrivalDate.safeSet(groupArrivalDate);
		
		txtGroupDepartDate.highlight(driver);
		txtGroupDepartDate.safeSet(groupDepartDate);
	}
	
	/**
	 * @summary Method to set Contracted From and To dates based on dayto and number of nights.
	 * @version Created 02/25/2016
	 * @author Praveen Namburi, @param daysOut
	 * @param numberNights
	 */
	public void setContractedFromAndToDates(String daysOut, String numberNights){
		
		String contractedFromDate = DateTimeConversion.getDaysOut(daysOut, "MM/dd/yyyy");
		String contractedToDate = DateTimeConversion.getDaysOut(numberNights, "MM/dd/yyyy");
		TestReporter.log("Contracted FromDate : " + contractedFromDate);
		TestReporter.log("Contracted ToDate : " + contractedToDate);
		
		txtContractedFromDateStr.highlight(driver);
		txtContractedFromDateStr.safeSet(contractedFromDate);
		
		txtContractedToDateStr.highlight(driver);
		txtContractedToDateStr.safeSet(contractedToDate);
	}
	
	public void setAttritionInfoDate(String daysOut){
		
		String attritionDate = DateTimeConversion.getDaysOut(daysOut, "MM/dd/yyyy");
		TestReporter.log("Attrition Date : " + attritionDate);
		
		txtAttritionDate.highlight(driver);
		txtAttritionDate.safeSet(attritionDate);
		
	}
	
	/**
	 * @summary: Method to set CallsAcceptedDate based on number of nights to stay
	 * @version: Created 2-19-2016
	 * @author: Praveen Namburi
	 * @param numberOfNights
	 */
	public void setCallsAcceptedDate(String callsAcceptedDate){
		
		String CallsAcceptedDate = DateTimeConversion.getDaysOut(callsAcceptedDate, "MM/dd/yyyy");
		TestReporter.log("Calls Accepted Date : " + CallsAcceptedDate);
		
		txtCallsAcceptedDate.highlight(driver);
		txtCallsAcceptedDate.safeSet(CallsAcceptedDate);
	}
	
	public void clickOrganization(){
		pageLoaded(lnkOrganization);
		lnkOrganization.highlight(driver);
		lnkOrganization.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}
	
	/**
	 * @summary Method to search agent id - organizattion search window
	 * @version Created 02/1/2016
	 * @author Venkatesh Atmakuri
	 * @param scenario
	 * @return NA
	 */
	public void clickOrganizationSearch(String scenario){
		
		datatable.setVirtualtablePage("OrganizationSearch");
		datatable.setVirtualtableScenario(scenario);

		String agencyId = datatable.getDataParameter("AgencyId");
		
		clickOrganization();
		pageLoaded(lnkOrgSearch);
		lnkOrgSearch.jsClick(driver);
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Organization Search");
		pageLoaded(txtOrgSearchAgentID);
		txtOrgSearchAgentID.highlight(driver);
		txtOrgSearchAgentID.safeSet(agencyId);
		eleOrgAgentSearch.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
		List<WebElement> elements = driver.findElements(By.tagName("a"));
		
		
		for(WebElement element : elements){
			Element e = new ElementImpl(element);
			if(e.syncVisible(driver, 1, false)){
				e.jsClick(driver);
				break;
			}
		}
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
    	WindowHandler.waitUntilWindowExistsTitleContains(driver, "Disney Reservation");
	}
	
	public void clickContact(){
		pageLoaded(lnkContact);
		lnkContact.jsClick(driver);
	}
	
	public void clickContactSearch(){
		clickContact();
		pageLoaded(lnkContactSearch);
		try{
			lnkContactSearch.click();
//			lnkContactSearch.sendKeys(Keys.ENTER);
		}catch(Exception e){
			new PageLoaded().isDomComplete(driver);
			pageLoaded(lnkContactSearch);
			lnkContactSearch.syncVisible(driver);
			lnkContactSearch.click();
//			lnkContactSearch.sendKeys(Keys.ENTER);
		}
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Guest Search");
	}
	
	public void clickContactSearchWithoutLink(){
		pageLoaded(lnkContactSearch);
		try{
			lnkContactSearch.click();
		}catch(Exception e){
			new PageLoaded().isDomComplete(driver);
			pageLoaded(lnkContactSearch);
			lnkContactSearch.syncVisible(driver);
			lnkContactSearch.click();
		}
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Guest Search");
	}
	
	public void clickGroupConfirmations(){
		pageLoaded(lnkGroupConfirmations);
		lnkGroupConfirmations.highlight(driver);
		lnkGroupConfirmations.jsClick(driver);
	}
/**
    * 
    * @summary Method to search guest and create new guest with address details
    * @version Created 02/03/2016
    * @author Venkatesh Atmakuri
    * @param  Scenario
    * @throws NA
    * @return NA
    * 
    */
	public void guestSearch(String scenario){
		
		GuestSearch guestSearch = new GuestSearch(driver);
		guestSearch.enterSearchCriteria(scenario);
		guestSearch.clickNew();

		GuestPartyWindow guestParty = new GuestPartyWindow(driver);
		TestReporter.assertTrue(guestParty.pageLoaded(), "The Guest Party Window was not loaded.");
		guestParty.enterPrimaryGuestInfo(scenario);
	}
	
	public void guestSearchNameOnly(String scenario){
		
		GuestSearch guestSearch = new GuestSearch(driver);
		guestSearch.enterSearchCriteriaNameOnly(scenario);
		guestSearch.clickNew();

		GuestPartyWindow guestParty = new GuestPartyWindow(driver);
		TestReporter.assertTrue(guestParty.pageLoaded(), "The Guest Party Window was not loaded.");
		guestParty.enterPrimaryGuestInfoNameTitleOnly(scenario);
	}
	
	/**
	    * 
	    * @summary Method to selet role - Contact
	    * @version Created 02/03/2016
	    * @author Venkatesh Atmakuri
	    * @param  Scenario
	    * @throws NA
	    * @return NA
	    * 
	    */
	public void selectContactRole(String scenario){
		
		datatable.setVirtualtablePage("PrimaryGuestList");
		datatable.setVirtualtableScenario(scenario);

		String role = datatable.getDataParameter("Role");
		
		pageLoaded(lstContactRole);
		lstContactRole.isDisplayed();
		lstContactRole.select(role);
	}
	
	/**
	 * @summary: Method to select Second role - Contact
	 * @author: Praveen namburi, @version: Created 2-24-2016
	 * @param scenario
	 */
	public void selectSecondContactRole(String scenario){
		datatable.setVirtualtablePage("PrimaryGuestList");
		datatable.setVirtualtableScenario(scenario);

		String role = datatable.getDataParameter("Role2");
		System.out.println(role);
		
		pageLoaded(lstContactRole2);
		lstContactRole2.isDisplayed();
		lstContactRole2.select(role);
	}
	
	public void clickGrpInfoDetails(){
		pageLoaded(lnkGrpInfoDetails);
		lnkGrpInfoDetails.jsClick(driver);
	}
	
/**
    * @summary Method to select values under Group Information Link
    * @version Created 02/04/2016
    * @author Venkatesh Atmakuri
    * @param  Scenario
    * @throws NA
    * @return NA
    */
	public void grpInformationDetails(String scenario){
		datatable.setVirtualtablePage("GroupInformationDetails");
		datatable.setVirtualtableScenario(scenario);
		
		String guestMainDate = datatable.getDataParameter("GuestMainDate");
		String daysOut = datatable.getDataParameter("DaysOut");
		String numberOfNights = datatable.getDataParameter("NumberOfNights");
		String groupArrivalTime = datatable.getDataParameter("GroupArrivalTime");
		String groupDepartureTime = datatable.getDataParameter("GroupDepartureTime");
		String peakRoomCount = datatable.getDataParameter("PeakRoomCount");
		String totalRoomNights = datatable.getDataParameter("TotalRoomNights");
		String opportunityType = datatable.getDataParameter("OpportunityType");
		String businessSource = datatable.getDataParameter("BusinessSource");
		String salesDepartment = datatable.getDataParameter("SalesDepartment");
		String salesManager = datatable.getDataParameter("SalesManager");
		String salesPhone = datatable.getDataParameter("SalesPhone");
		String serviceDepartment = datatable.getDataParameter("ServiceDepartment");
		String serviceManager = datatable.getDataParameter("ServiceManager");
		String servicePhone = datatable.getDataParameter("ServicePhone");
		
		clickGrpInfoDetails();
		if(guestMainDate.equalsIgnoreCase("true")){
		//setGrpInfoArrivalAndDepartDates(daysOut,numberOfNights);
		setArrivalAndDepartDates(daysOut,numberOfNights);
		}
		
		txtGrpInfoArrivalTime.safeSet(groupArrivalTime);
		txtGrpInfoDepartureTime.safeSet(groupDepartureTime);
		txtGrpInfoPeakRoomCount.safeSet(peakRoomCount);
		txtGrpInfoTotalRoomNights.safeSet(totalRoomNights);
		lstGrpInfoOpportunityType.select(opportunityType);
		lstGrpInfoBusinessSource.select(businessSource);
		txtGrpInfoSalesDepartment.safeSet(salesDepartment);
		txtGrpInfoSalesManager.safeSet(salesManager);
		txtGrpInfoSalesPhone.safeSet(salesPhone);
		txtGrpInfoServiceDepartment.safeSet(serviceDepartment);
		txtGrpInfoServiceManager.safeSet(serviceManager);
		txtGrpInfoServicePhone.safeSet(servicePhone);
		
	}
	
	/**
	 * @summary Method to set arrival and Depart dates based on dayto and number of nights to stay
	 * @version Created 02/18/2016
	 * @author Venkatesh Atmakuri
	 * @param scenarios
	 * @return NA
	 */
	public void setGrpInfoArrivalAndDepartDates(String daysOut, String numberOfNights){
		
		String groupArrivalDate = DateTimeConversion.getDaysOut(daysOut, "MM/dd/yyyy");
		String groupDepartDate = DateTimeConversion.getDaysOut(numberOfNights, "MM/dd/yyyy");
		TestReporter.log("Group Information Main Arrival Date : " + groupArrivalDate);
		TestReporter.log("Group Information Main Depart Date : " + groupDepartDate);
		
		txtGrpInfoArrivalDate.highlight(driver);
		txtGrpInfoArrivalDate.safeSet(groupArrivalDate);
		
		txtGrpInfoDepartureDate.highlight(driver);
		txtGrpInfoDepartureDate.safeSet(groupDepartDate);
	}
	
	public void clickonYesButton() {

		PleaseWait.WaitForPleaseWait(driver);
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "WARNING");
		clickYes();		
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,"Disney Reservation Entry and Management System");
	}
	
	public void clickYes() {
		System.out.println(driver.getTitle());
		pageLoaded(btnYes);
		
		List<WebElement> yesElements = driver.findElements(By.name("b_Yes"));
		for(WebElement yes : yesElements){
			Element yesButton = new ElementImpl(yes);
			if(yesButton.syncVisible(driver, 1, false)){
				yesButton.highlight(driver);
				yesButton.jsClick(driver);
				PleaseWait.WaitForPleaseWait(driver);
				break;
			}
		}
	}
	
	public void clickEdit(){
		pageLoaded(btnEdit);
		btnEdit.highlight(driver);
		btnEdit.syncVisible(driver);
		btnEdit.syncEnabled(driver);
		btnEdit.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}
	
	/**
	 * @summary: Method to click on Edit link in GroupProfile page 
	 * 			 which comes after clicking on contact link.
	 * @author: praveen namburi
	 * @version: Created 2-23-2016
	 */
	public void clickEditLink(){
		pageLoaded(lnkEdit);
		lnkEdit.syncVisible(driver);
		btnEdit.highlight(driver);
		lnkEdit.jsClick(driver);
		//PleaseWait.WaitForPleaseWait(driver);	
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Party");
		
	}
	
	public void clickSplConsiderations(){
		pageLoaded(lnkSplConsiderations);
		lnkSplConsiderations.syncVisible(driver);
		lnkSplConsiderations.syncEnabled(driver);
		lnkSplConsiderations.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}
	
  /**
    * @summary Method to set SpecialConsiderations values
    * @version Created 02/18/2016
    * @author Venkatesh Atmakuri
    * @param  Scenario
    * @throws NA
    * @return NA
    */
	public void specialConsiderations(String scenario){
		datatable.setVirtualtablePage("SpecialConsiderations");
		datatable.setVirtualtableScenario(scenario);

//		String minLOS = datatable.getDataParameter("MinLOS");
//		String eHResortFirst = datatable.getDataParameter("EHResortFirst");
//		String taxExemptType = datatable.getDataParameter("TaxExemptType");
//		String dMEIndicator = datatable.getDataParameter("DMEIndicator");
//		String dCPrivleges = datatable.getDataParameter("DCPrivleges");
//		String hideRate = datatable.getDataParameter("HideRate");
//		String expressCheckOut = datatable.getDataParameter("ExpressCheckOut");
//		String AAAmount = datatable.getDataParameter("AAAmount");
		String commissionable = datatable.getDataParameter("Commissionable");
//		String freeTimeLEvel = datatable.getDataParameter("FreeTimeLEvel");
//		String collectVoucher = datatable.getDataParameter("CollectVoucher");
		String reservationGarunteed = datatable.getDataParameter("ReservationGarunteed");
		
		pageLoaded(lstMinLOS);
				
		if(commissionable.equalsIgnoreCase("ON")){
			chkCommissionable.check();
		}
		
		if(reservationGarunteed.equalsIgnoreCase("ON")){
			chkResGuaranteed.check();
		}

	}
	
	
  /**
    * @summary Method to select group tax exempt type
    * @version Created 02/08/2016
    * @author Venkatesh Atmakuri
    * @param  Scenario
    * @throws NA
    * @return NA
    */
	public void setGrpTaxExmtType(String scenario){
		datatable.setVirtualtablePage("SpecialConsiderations");
		datatable.setVirtualtableScenario(scenario);

		String taxExemptType = datatable.getDataParameter("TaxExemptType");
		pageLoaded(lstGrpTaxExmtType);
		lstGrpTaxExmtType.select(taxExemptType);
	}
	
  /**
    * @summary Method to enter group tax exempt id
    * @version Created 02/08/2016
    * @author Venkatesh Atmakuri
    * @param  Scenario
    * @throws NA
    * @return NA
    */
	public String enterTaxExemptId(){
		pageLoaded(txtTaxExempttId);
		String taxExemptId = Randomness.randomNumber(4);
		txtTaxExempttId.safeSet(taxExemptId);
		return taxExemptId;
	}
	
  /**
    * @summary Method to edit Group Details
    * @version Created 02/08/2016
    * @author Venkatesh Atmakuri
    * @param  Scenario
    * @throws NA
    * @return NA
    */
	public void editGroupDetails(String scenario){
		FrameHandler.findAndSwitchToFrame(driver, "contentFrame");
		datatable.setVirtualtablePage("GroupProfiles");
		datatable.setVirtualtableScenario(scenario);
System.out.println();
		String groupName = datatable.getDataParameter("GroupName");
		String groupAliasName = datatable.getDataParameter("GroupAliasName");
		String groupStatus = datatable.getDataParameter("GroupStatus");
//		String callsAcceptedDate = datatable.getDataParameter("CallsAcceptedDate");
		String reservationMethod = datatable.getDataParameter("ReservationMethod");
		String homeResort = datatable.getDataParameter("HomeResort");
		String reservationOffice = datatable.getDataParameter("ReservationOffice");
		String GROAgent = datatable.getDataParameter("GROAgent");
		String profileComplete = datatable.getDataParameter("ProfileComplete");
		String wholeSalerFlag = datatable.getDataParameter("WholeSalerFlag");
		String daysOut = datatable.getDataParameter("DaysOut");
		String numberNights = datatable.getDataParameter("NumberOfNights");
		String setCallsAccepted = datatable.getDataParameter("SetCallsAccepted");
		String setCallsDaysOut = datatable.getDataParameter("CallsAcceptedDate");
		
//		System.out.println();
		//txtGroupName.highlight(driver);
		pageLoaded(txtGroupName);
		txtGroupName.safeSet(groupName);
		txtGroupAlias.safeSet(groupAliasName);
		setArrivalAndDepartDates(daysOut,numberNights);
		//modified 02/11/2016 - Venkatesh
		if(groupStatus.equalsIgnoreCase("Cancelled")){
			try{
				lstGroupStatus.select(groupStatus);
				acceptAlert();
			}catch(UnhandledAlertException | NoAlertPresentException e1){
				try{
					lstGroupStatus.select(groupStatus);
					acceptAlert();
				}catch(UnhandledAlertException | NoAlertPresentException e2){
				}
			}
		}
		else{
			lstGroupStatus.select(groupStatus);
		}
		
		if(setCallsAccepted.equalsIgnoreCase("edit")) txtCallsAcceptedDate.safeSet(DateTimeConversion.getDaysOut(setCallsDaysOut, "MM/dd/yyyy"));
		
		lstGroupStatus.select(groupStatus);
		lstReservationMethod.select(reservationMethod);
		lstHomeResort.select(homeResort);
		pageLoaded(lstResevationOffice);
		lstResevationOffice.select(reservationOffice);
		pageLoaded(txtGRO_Agent);
		txtGRO_Agent.isDisplayed();
		txtGRO_Agent.highlight(driver);
		txtGRO_Agent.safeSet(GROAgent);
		
		//Added below if-condition to check the Profile complete checkbox.
		//Updated by Praveen - 02-22-2016
		if(profileComplete.equalsIgnoreCase("ON")){
			chkProfileComplete.check();
		}
		
		if(wholeSalerFlag.equalsIgnoreCase("ON"))
		chkWholeSaler.check();
		txtGroupName.safeSet(groupName);
		lstGroupStatus.select(groupStatus);
		txtGRO_Agent.safeSet(GROAgent);
	}
	
	public void clickAffiliatedProfiles(){
		pageLoaded(lnkAffiliatedProfiles);
		lnkAffiliatedProfiles.click();
		PleaseWait.WaitForPleaseWait(driver);
	}
	
	public void clickAttritionInformation(){
		pageLoaded(lnkAttritionInformation);
		lnkAttritionInformation.jsClick(driver);
	}
	
	public void clickAttritionAdd(){
		pageLoaded(btnAttritionAdd);
		btnAttritionAdd.syncEnabled(driver);
		btnAttritionAdd.jsClick(driver);
	}
	
	public void clickCommentEntryAdd(){
		pageLoaded(btnCommentEntryAdd);
		btnCommentEntryAdd.syncEnabled(driver);
		btnCommentEntryAdd.highlight(driver);
		btnCommentEntryAdd.jsClick(driver);
	}
	
	public void clickGeneralComments(){
		pageLoaded(lnkGeneralComments);
		lnkGeneralComments.highlight(driver);
		lnkGeneralComments.jsClick(driver);
	}
	
	public void clickCodedRemarks(){
		pageLoaded(lnkCodedRemarks);
		lnkCodedRemarks.highlight(driver);
		lnkCodedRemarks.jsClick(driver);
	}
	
	public void clickCodedRemarksAdd(){
		pageLoaded(btnCodedRemarkAdd);
		btnCodedRemarkAdd.highlight(driver);
		btnCodedRemarkAdd.jsClick(driver);
		
	}
	
	public void clickBlock(){
		pageLoaded(lnkBlock);
		lnkBlock.highlight(driver);
		lnkBlock.jsClick(driver);
	}
	
   /**
    * @summary Method to enter affiliated Code
    * @version Created 02/08/2016
    * @author Venkatesh Atmakuri
    * @param  Scenario
    * @throws NA
    * @return NA
    */
	public void enterAffiliatedCode(String affGroupCode){
		pageLoaded(txtAffGroupCode);
		txtAffGroupCode.isDisplayed();
		txtAffGroupCode.isEnabled();
		txtAffGroupCode.highlight(driver);
		txtAffGroupCode.safeSet(affGroupCode);
	}
	
	public void clickAffAddButton(){
		pageLoaded(btnAffAddButton);
		btnAffAddButton.highlight(driver);
		btnAffAddButton.click();
		PleaseWait.WaitForPleaseWait(driver);
	}
	
  /**
    * @summary Method to validate added Affiliated Code
    * @version Created 02/09/2016
    * @author Venkatesh Atmakuri
    * @param  Scenario
    * @throws NA
    * @return NA
    */
	public void validateAffAddCode(){
		pageLoaded(eleAffTable);
		List<WebElement> affRowElements = eleAffTable.findElements(By.tagName("tr"));
		System.out.println("After adding the Affiliated : "+affRowElements.size());
		TestReporter.assertTrue(affRowElements.size()>2, "AffiliatedCode Added");
	}
	
  /**
    * @summary Method to validate Deleted or not Affiliated Code
    * @version Created 02/09/2016
    * @author Venkatesh Atmakuri
    * @param  Scenario
    * @throws NA
    * @return NA
    */
	public void validateDeleteAffCode(){
		pageLoaded(eleAffTable);
		List<WebElement> affRowElements = eleAffTable.findElements(By.tagName("tr"));
		System.out.println("After adding the Affiliated : "+affRowElements.size());
		TestReporter.assertTrue(affRowElements.size()<=2, "AffiliatedCode Deleted");
	}
	
  /**
    * @summary Method to delete added Affiliated Code
    * @version Created 02/09/2016
    * @author Venkatesh Atmakuri
    * @param  Scenario
    * @throws NA
    * @return NA
    */
	public void clickAffDelete(){
		pageLoaded(eleAffDiv);
		eleAffDiv.highlight(driver);
		List<WebElement> elements = eleAffDiv.findElements(By.tagName("a"));
		System.out.println("A elements found : "+elements.size());
		
		for(int LinkIterator = 1; LinkIterator<=elements.size(); LinkIterator++){
			if(elements.get(LinkIterator).getText().trim().equalsIgnoreCase("Delete")){
				elements.get(LinkIterator).click();
				break;
			}
		}
		
		acceptAlert();
	}
	
	public String acceptAlert(){
		initialize();
		TestReporter.logStep("Control in Alert");
		Alert alert=driver.switchTo().alert();
		String altMsg = alert.getText();
		TestReporter.log("Alert text message is : "+altMsg);
		alert.accept();
		TestReporter.log("Control in Alert and Msg "+altMsg);
		return altMsg;
	}
	
	 /**
    * @summary Method to validate Error Message
    * @version Created 02/12/2016
    * @author Venkatesh Atmakuri
    * @param  Scenario
    * @throws NA
    * @return NA
    */
	public void validateErrorMessage(String scenario){
		datatable.setVirtualtablePage("ErrorMessages");
		datatable.setVirtualtableScenario(scenario);

		String expectedError = datatable.getDataParameter("Message");
		String actualError = "";
		String[] arrErrors = expectedError.split(";");
		try{arrErrors = expectedError.split(";");}catch(Exception e){}
		pageLoaded();
		List<WebElement> errorElements;
		
		try{
			errorElements = driver.findElements(By.xpath("//*[@class = 'errorMsg']")); 
			for(WebElement error : errorElements){
				actualError += error.getText();
			}
		}catch(Exception e){
			
		}
		
		for(String err : arrErrors){
			TestReporter.log(expectedError);
			TestReporter.assertTrue((actualError.trim().contains(err.trim())), "The expected error message ["+err+"] was not found in the actual error message(s) ["+actualError+"].");
		}
	}
	
	public void validateErrorMessageContained(String scenario){
		
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
		TestReporter.log(expectedError);
		TestReporter.assertTrue((expectedError.trim().contains(error.getText().trim())), "Error ["+error.getText()+"]displayed as expected ["+expectedError+"].");
	}
	
   /**
	* @summary Method to verify whether contacts details are added to the table.
	* @version Created 02/24/2016
	* @author Praveen Namburi
	*/
	public void validateContactInfoBeforeDeleteRecord(){
			pageLoaded(eleContactTable);
			eleContactTable.syncVisible(driver);
			List<WebElement> contactTableRows = eleContactTable.findElements(By.tagName("tr"));
			TestReporter.log("Total rows in contact table before Deleting the First record : "+contactTableRows.size());
			TestReporter.assertTrue(contactTableRows.size()>3, "Added contact Records exist in table.");
	}
		
	/**
	 * @summary Method to verify whether contacts record is deleted from the table.
	 * @version Created 02/24/2016
	 * @author Praveen Namburi, @return NA 
	 */
	public void validateContactInfoAfterDeleteRecord(){
		    pageLoaded(eleContactTable);
		    eleContactTable.syncVisible(driver);
			List<WebElement> affRowElements = eleContactTable.findElements(By.tagName("tr"));
			System.out.println("Total rows in contact table After Deleting the First record : "+affRowElements.size());
			TestReporter.assertTrue(affRowElements.size()<5, "Guest Contact Record is deleted from the table.");
	}
		
	/**
	 * @summary Method to delete added Guestcontact info from Contact table.
	 * @version Created 02/24/2016
	 * @author Praveen Namburi, @return NA
	 */
	public void clickContactDelete(){
		  pageLoaded(eleContactTable);
		  eleContactTable.highlight(driver);
		  List<WebElement> elements = eleContactTable.findElements(By.tagName("a"));
		  System.out.println("Total link elements found in Contact table : "+elements.size());
			
		  for(int LinkIterator = 1; LinkIterator<=elements.size(); LinkIterator++){
			  if(elements.get(LinkIterator).getText().trim().equalsIgnoreCase("Delete")){
				 elements.get(LinkIterator).click();
				 break;
			  }
		  }
		acceptAlert();
	}
	
	/**
	 * @summary: Method to click on the first delete link from Attrition table.
	 * @author: Praveen Namburi, @version: Created 2-26-2016
	 */
	public void clickAttritionDelete(){
		  pageLoaded(eleAttritionTable);
		  eleAttritionTable.highlight(driver);
		  
		  List<WebElement> links = eleAttritionTable.findElements(By.tagName("a"));
		  TestReporter.log("Total no. of links found in Attrition table : "+links.size());
		  
		  for(int LinkIterator = 1;LinkIterator<=links.size();LinkIterator++){
			  if(links.get(LinkIterator).getText().trim().equalsIgnoreCase("Delete")){
				  links.get(LinkIterator).click();
				  break;
			  }			  
		  }	
		  //If Alert pop-up is displayed, then accept it.
		  if(AlertHandler.isAlertPresent(driver, 5)){
			AlertHandler.handleAlerts(driver, 5);
		  }
	}
	
	/**
	 * @summary: Method to click on link delete in CodedRemarks table.
	 * @author: praveen namburi, @version: Created 03-1-2016.
	 */
	public void clickCodedRemarkDelete(){
		pageLoaded(eleCodedRemarksTable);
		eleCodedRemarksTable.highlight(driver);
		  
		  List<WebElement> links = eleCodedRemarksTable.findElements(By.tagName("a"));
		  TestReporter.log("Total no. of links found in CodedRemarks table : "+links.size());
		  
		  for(int LinkIterator = 0;LinkIterator<=links.size();LinkIterator++){
			  if(links.get(LinkIterator).getText().equalsIgnoreCase("Delete")){
				  links.get(LinkIterator).click();
				  break;
			  }			  
		  }	
		  //If Alert pop-up is displayed, then accept it.
		  if(AlertHandler.isAlertPresent(driver, 5)){
			AlertHandler.handleAlerts(driver, 5);
		  }
	}
	
	public void clickEditBlock(){
		pageLoaded(btnEditBlock);
		btnEditBlock.highlight(driver);
		btnEditBlock.jsClick(driver);
	}
	
	/**
	 * @summary: Method to click on link delete in GeneralComments table.
	 * @author: praveen namburi, @version: Created 03-1-2016.
	 */
	public void clickGeneralCommentsDelete(){
		pageLoaded(eleGeneralCommentsTable);
		eleGeneralCommentsTable.highlight(driver);
		  
		  List<WebElement> links = eleGeneralCommentsTable.findElements(By.tagName("a"));
		  TestReporter.log("Total no. of links found in GeneralComments table : "+links.size());
		  
		  for(int LinkIterator = 0;LinkIterator<=links.size();LinkIterator++){
			  if(links.get(LinkIterator).getText().equalsIgnoreCase("Delete")){
				  links.get(LinkIterator).click();
				  break;
			  }			  
		  }	
		  //If Alert pop-up is displayed, then accept it.
		  if(AlertHandler.isAlertPresent(driver, 5)){
			AlertHandler.handleAlerts(driver, 5);
		  }
	}
	
   /**
	* @summary Method to verify whether AttritionInfo is added to the table.
	* @version Created 02/26/2016
	* @author Praveen Namburi
	*/
	public void validateAttritionInfoBeforeDeleteRecord(){
		 pageLoaded(eleAttritionTable);
		 eleAttritionTable.syncVisible(driver);
		 List<WebElement> AttritonTblRows = eleAttritionTable.findElements(By.tagName("tr"));
		 TestReporter.log("Total rows in Attrition table before Deleting the First record : "+AttritonTblRows.size());
		 TestReporter.assertTrue(AttritonTblRows.size()>3, "Added AttritionInfo exists in Webtable.");
	}
			
   /**
	* @summary Method to verify whether Added AttritonInfo is deleted from the table.
	* @version Created 02/26/2016
	* @author Praveen Namburi, @return NA 
	*/
	public void validateAttritionInfoAfterDeleteRecord(){
		 pageLoaded(eleAttritionTable);
		 eleAttritionTable.syncVisible(driver);
		 List<WebElement> AttritonTblRows = eleAttritionTable.findElements(By.tagName("tr"));
		 TestReporter.log("Total rows in Attrition table After Deleting the First record : "+AttritonTblRows.size());
		 TestReporter.assertTrue(AttritonTblRows.size()<4, "Added AttritionInfo is deleted from the Webtable.");
	}
		
	/**
	 * @summary: Method to click on link - Contracted Resorts,Rates And Packages.
	 * @author: Praveen Namburi, @version: Created 2-25-2016
	 */
	public void clickContractedResortsAndRates(){
		pageLoaded(lnkContractedResortsAndRates);
		lnkContractedResortsAndRates.syncVisible(driver);
		lnkContractedResortsAndRates.highlight(driver);
		lnkContractedResortsAndRates.jsClick(driver);
	}
	
	/**
	 * @summary: Method to click on Contracted Add button.
	 * @author: Praveen Namburi, @version: Created 02-25-2016
	 */
	public void clickContractedAdd(){
		// Up to 6 buttons have the same html id. Iterate through each one to
		// determine which one is visbile and has the value 'Add'
		List<WebElement> adds = driver.findElements(By.id("b_AddAutoReduce"));
		for(WebElement add : adds){
			Button e = new ButtonImpl(add);
			if(e.syncVisible(driver, 1, false)){
				try{
					if(e.getAttribute("value").equalsIgnoreCase("add")){
						btnContractedAdd = e;
						break;
					}
				}catch(Exception ex){}
			}
		}
		pageLoaded(btnContractedAdd);
		btnContractedAdd.highlight(driver);
		btnContractedAdd.jsClick(driver);
	}
	
	/**
	 * @summary: Method to enter and add Contracted Resorts, Rates and Package Details
	 * @version : Created 2-25-2016, @author: Praveen Namburi
	 * @param : scenario
	 */
	public void setContractedResortsPackageDetails(String scenario){
		datatable.setVirtualtablePage("ContractedResortRatesAndPackages");
		datatable.setVirtualtableScenario(scenario);

		String resort = datatable.getDataParameter("Resort");
		String roomType = datatable.getDataParameter("RoomType");
		String Package = datatable.getDataParameter("Package");
		String daysOut = datatable.getDataParameter("DaysOut");
		String numberNights = datatable.getDataParameter("NumberOfNights");
		String rate = datatable.getDataParameter("Rate");
		String adultsCharge = datatable.getDataParameter("AdultsCharge");
		
		//Click on the link Contracted Resorts,RatesAndPackages.
		clickContractedResortsAndRates();
		
		//Enter Contracted resorts, rates and package details
		pageLoaded(txtContractedResort);
		txtContractedResort.syncVisible(driver);
		txtContractedResort.safeSet(resort);
		txtContractedRoomType.safeSet(roomType);
		txtContractedPackage.safeSet(Package);		
		
		//Enter Contracted From and To dates here
		setContractedFromAndToDates(daysOut,numberNights);
		txtContractedRate.safeSet(rate);
		txtContractedAdultCharge.safeSet(adultsCharge);

		//Click on Contracted add button.
		clickContractedAdd();
		
	}
	
	/**
	 * @summary: Method to provide Group Confirmations info.
	 * @author: Praveen Namburi, @version: Created 02-25-2016
	 * @param scenario
	 */
	public void enterGroupConfirmations(String scenario){
		datatable.setVirtualtablePage("GroupConfirmations");
		datatable.setVirtualtableScenario(scenario);
		
		String deliverymethod = datatable.getDataParameter("DeliveryMethod");
		String destination = datatable.getDataParameter("Destination");
		
		//Click on the Link group Confirmations.
		clickGroupConfirmations();
		
		pageLoaded(lstDeliveryMethod);
		lstDeliveryMethod.syncVisible(driver);
		lstDeliveryMethod.select(deliverymethod);
		
		pageLoaded(lstDestination);
		lstDestination.select(destination);
		
		pageLoaded(chkGroupBalance);
		chkGroupBalance.highlight(driver);
		chkGroupBalance.check();

	}
	
	/**
	 * @summary: Method to provide Attrition Information.
	 * @author: Praveen Namburi, @version: Created 02-25-2016
	 * @param scenario
	 */
	public void enterAttritionInformations(String scenario){
		datatable.setVirtualtablePage("AttritionInformation");
		datatable.setVirtualtableScenario(scenario);
		
		String scheduleDays = datatable.getDataParameter("ScheduleDays");
		String daysOut = datatable.getDataParameter("DaysOut");
		String percent = datatable.getDataParameter("Percent");
		String notes = datatable.getDataParameter("Notes");
		
		//Click on the Link group Confirmations.
		clickAttritionInformation();
		
		pageLoaded(txtAttritionScheduleDays);
		txtAttritionScheduleDays.syncVisible(driver);
		txtAttritionScheduleDays.safeSet(scheduleDays);
		
		pageLoaded(txtAttritionDate);

		setAttritionInfoDate(daysOut);
		txtAttritionPercent.safeSet(percent);
		txtAttritionNotes.safeSet(notes);
		
		//Click on Attrition Add button
		clickAttritionAdd();
	
	}
	
	/**
	* @summary Method to Edit Contact Info
	* @version Created 02/25/2016
	* @author Lalitha Banda
	* @param  NA
	* @throws NA
	* @return NA
	*/
		
	public void editGroupProfileContactInfo(){
		clickEdit();
		clickContact();
	}
	
	/**
	 * @summary: Method to click on link CommentEntry.
	 * @version: Created 2-29-2016, @author: Praveen namburi.
	 */
	public void clickCommentEntry(){
		pageLoaded(lnkCommentEntry);
		lnkCommentEntry.highlight(driver);
		lnkCommentEntry.jsClick(driver);
	}
	
	/**
	 * @summary: Method to add CommentEntry details
	 * @author: praveen namburi, @version: Created 2-29-2016
	 * @param scenario
	 */
	public void addCommentEntryDetails(String scenario){
		datatable.setVirtualtablePage("CommentEntryInfo");
		datatable.setVirtualtableScenario(scenario);
		
		String commentLevel = datatable.getDataParameter("Level");
		String commentSection = datatable.getDataParameter("Section");
		String commentType = datatable.getDataParameter("Type");
		String commentRouting = datatable.getDataParameter("Routing");
		String description = datatable.getDataParameter("Description");
		String fromGuest = datatable.getDataParameter("FromGuest");
		String toGuest = datatable.getDataParameter("ToGuest");
		
		//Click on the Link CommentEntry.
		clickCommentEntry();
		
		initialize();
		lstCommentLevel.syncVisible(driver);
		lstCommentLevel.syncEnabled(driver,4,false);
		lstCommentLevel.select(commentLevel);
		
		initialize();
		lstCommentSection.isEnabled();
		//lstCommentSection.syncEnabled(driver,5,false);
		lstCommentSection.select(commentSection);
		
		initialize();
		lstCommentType.isEnabled();
		//lstCommentType.syncEnabled(driver,5,false);
		lstCommentType.select(commentType);
		
		initialize();
		lstCommentRoutings.isEnabled();
		//lstCommentRoutings.syncEnabled(driver,5,false);
		lstCommentRoutings.select(commentRouting);
		
		txtCommentText.safeSet(description);
		txtFromGuest.safeSet(fromGuest);
		txtToGuest.safeSet(toGuest);
		
		//Click on CommentEntry Add button
		clickCommentEntryAdd();
	}
	
	/**
	 * @summary: Method to set GeneralComments info.
	 * @author: praveen namburi, @version: Created 02-29-2016
	 * @param scenario
	 */
	public void setGeneralComments(String scenario){
		datatable.setVirtualtablePage("CodedRemarksAndGeneralComments");
		datatable.setVirtualtableScenario(scenario);
		
		String description = datatable.getDataParameter("GeneralCommentDescription");
		
		clickGeneralComments();
		
		pageLoaded(txtGroupComments);
		txtGroupComments.safeSet(description);
	}

	/**
	 * @summary: Method to add coded remarks info.
	 * @author: Praveen Namburi, @version: Created 02-29-2016
	 * @param scenario
	 */
	public void setCodedRemarks(String scenario){
		datatable.setVirtualtablePage("CodedRemarksAndGeneralComments");
		datatable.setVirtualtableScenario(scenario);
		
		String commentLevel = datatable.getDataParameter("CommentLevel");
		String type = datatable.getDataParameter("Type");
		String codedRemark = datatable.getDataParameter("CodedRemark");
		
		clickCodedRemarks();
		
		pageLoaded(lstCodedRemarksLevel);
		lstCodedRemarksLevel.syncEnabled(driver);
		lstCodedRemarksLevel.select(commentLevel);
		
		pageLoaded(lstCodedRemarksType);
		lstCodedRemarksType.syncEnabled(driver);
		lstCodedRemarksType.select(type);
		
		pageLoaded(lstCodedRemark);
		lstCodedRemark.syncVisible(driver);
		lstCodedRemark.syncEnabled(driver, 3, false);
		lstCodedRemark.select(codedRemark);
		
		clickCodedRemarksAdd();
		
	}
	
   /**
	* @summary Method to verify whether CodedRemarks Info is added to the table.
	* @version Created 03/01/2016
	* @author Praveen Namburi
	*/
	public void validateCodedRemarksInfoBeforeDeleteRecord(){
		 pageLoaded(eleCodedRemarksTable);
		 eleCodedRemarksTable.syncVisible(driver);
		 List<WebElement> CodedRemarksTblRows = eleCodedRemarksTable.findElements(By.tagName("tr"));
		 TestReporter.log("Total rows in CodedRemarks table before Deleting the First record : "
		                  + CodedRemarksTblRows.size());
		 TestReporter.assertTrue(CodedRemarksTblRows.size()>3, "Added CodedRemarks Info exists in Webtable.");
	}
				
   /**
	* @summary Method to verify whether Added CodedRemarks Info is deleted from the table.
	* @version Created 03/01/2016
	* @author Praveen Namburi, @return NA 
	*/
	public void validateCodedRemarksInfoAfterDeleteRecord(){
		 pageLoaded(eleCodedRemarksTable);
		 eleCodedRemarksTable.syncVisible(driver);
		 List<WebElement> AttritonTblRows = eleCodedRemarksTable.findElements(By.tagName("tr"));
		 TestReporter.log("Total rows in CodedRemarks table After Deleting the First record : "
				 	+ AttritonTblRows.size());
		 TestReporter.assertTrue(AttritonTblRows.size()<4,"Added CodedRemarks Info is deleted from Webtable.");
	}
	
	/**
	* @summary Method to verify whether GeneralComments Info is added to the table.
	* @version Created 03/01/2016
	* @author Praveen Namburi
	*/
	public void validateGeneralCommentsInfoBeforeDeleteRecord(){
		 pageLoaded(eleGeneralCommentsTable);
		 eleGeneralCommentsTable.syncVisible(driver);
		 List<WebElement> CodedRemarksTblRows = eleGeneralCommentsTable.findElements(By.tagName("tr"));
		 TestReporter.log("Total rows in GeneralComments table before Deleting the First record : "
		                  + CodedRemarksTblRows.size());
		 TestReporter.assertTrue(CodedRemarksTblRows.size()>1, "Added GeneralComments Info exists in Webtable.");
	}
				
   /**
	* @summary Method to verify whether Added GeneralComments Info is deleted from the table.
	* @version Created 03/01/2016
	* @author Praveen Namburi, @return NA 
	*/
	public void validateGeneralCommentsInfoAfterDeleteRecord(){
		 pageLoaded(eleGeneralCommentsTable);
		 eleGeneralCommentsTable.syncVisible(driver);
		 List<WebElement> AttritonTblRows = eleGeneralCommentsTable.findElements(By.tagName("tr"));
		 TestReporter.log("Total rows in GeneralComments table After Deleting the First record : "
				 	+ AttritonTblRows.size());
		 TestReporter.assertTrue(AttritonTblRows.size()<2,"Added GeneralComments Info is deleted from Webtable.");
	}
	
}

