package apps.lilo.modifyReservation;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.WebDriverSetup;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Link;
import core.interfaces.Listbox;
import core.interfaces.RadioGroup;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

public class ModifyReservationPage {
	//
	// ModifyReservation Fields
	//
	//Modified Room Type
	private String modRoomType;
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	private int timeout = WebDriverSetup.getDefaultTestTimeout();
	private int loopCount = 0;
	
	//
	// ModifyReservation Elements
	//
	// submit
	@FindBy(id = "modResActionsFormId:submit1")
	private Button btnSubmit;

	// take payment
	@FindBy(id = "modResTakePayFormId:takePayment")
	private Button btnTakePayment;

	// Yes button inside Undo Upgrade table
	@FindBy(id = "undoUpgradeForm:yesId")
	private Button btnYes;

	// Close
	@FindBy(id = "forModResClose:close")
	private Button btnClose;

	// Webtable
	@FindBy(id = "undoUpgradeModalPanelContentTable")
	private Webtable tblUndoUpgrade;

	// Text box IATA Number
	@FindBy(id = "modResIataFormId:selectediataNumberOutputText")
	private Textbox txtIATANumber;

	// Room type link
	@FindBy(id = "modResDescId:genricRoomTypeTxtId")
	private Link lnkRoomType;

	// History button
	@FindBy(id = "modResFormPanelSelectionId:viewhistory")
	private Button btnHistory;

	// ADA accessible checkbox
	@FindBy(id = "xyz:adaStatusCheckBoxId")
	private Button chkADA;

	// resort
	@FindBy(id = "modResResortFormId:resortNameId")
	private Listbox lstResort;

	// modify reason
	@FindBy(id = "modResResortFormId:modifyReservationReasonDropdown")
	private Listbox lstReason;
	
	@FindBy(id = "modResResortFormId:wholeSaleNumberOutputText")
	private Textbox txtWholeSaleNumber;
	
	// room type
	@FindBy(id = "modResResortFormId:roomTypeNameId")
	private Listbox lstRoomType;

	// Resort and Room Type Radio Button
	@FindBy(id = "modResFormPanelSelectionId:selectedPanelId:1")
	private RadioGroup radSelectResortandRoomType;

	// Button Modify

	@FindBy(id = "modResFormPanelSelectionId:undoUpgrade")
	private Button btnUndoUpgrade;

	// Resort and Room Type Radio Button
	@FindBy(id = "modResFormPanelSelectionId:selectedPanelId")
	private RadioGroup radModifyType;
	
	// PartyMix radio button
	@FindBy(id = "modResFormPanelSelectionId:selectedPanelId:2")
	private RadioGroup radModifyPartyMix;
	
	// Add Guest Button
	@FindBy(id = "modResPartyFormId:addGuestId")
	private Button btnAddNewGuestButton;
	
	// Adult count text
	@FindBy(id = "modResPartyFormId:guestCountAdultOutputText")
	private Element eleGuestCountText;
	
	//Select VIP Level Radio Button
	@FindBy(id = "modResFormPanelSelectionId:selectedPanelId:4")
	private Element eleVIPLevel;
	
    //Create Listbox object for VIPLevel
    @FindBy(id = "modResVipFormId:vipLevelList")
    private Listbox lstViplevel;
    
  //-------------------------------------------
  	// Undo Upgrade confirmation PopUp elements
  	//-----------------------------------------
  	
  	// Undo Upgrade confirmation PopUp panel
  	@FindBy(id="undoUpgradeModalPanelContentDiv")
  	private Element eleUndoUpGradePanel;

  	// Get inner text from Undo upgrade pop-up
  	@FindBy(xpath = "//*[@id='undoUpgradeForm']/table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td/table/tbody/tr/td[2]")
  	private Element eleGetUndoUpGradePopUpText;

  	//Button yes in undo upgrade
  	@FindBy(id="undoUpgradeForm:yesId")
  	private Button btnYesUndoUpGrade;
  	
  	//Button No in undo upgrade
  	@FindBy(id="undoUpgradeForm:cancelId")
  	private Button btnNoUndoUpGrade;
  	
	// Guest List Table Body
	@FindBy(id = "modResPartyFormId:guestList:tb")
	private Element eleGuestListBody;
	
	// Additional Cost Pop Up
	@FindBy(id = "additionalCostModalPanelContentTable")
	private Element eleAdditionalCostPopUp;
	
	//Additional Cost Pop Up Yes Button
	@FindBy(id = "additionalCostForm:yesId")
	private Button additionalCostYes;

	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @version Created 10/10/2014
	 * @author Justin Phlegar
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public ModifyReservationPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public ModifyReservationPage initialize(WebDriver driver) {
		return ElementFactory.initElements(driver, ModifyReservationPage.class);
	}

	private ModifyReservationPage initialize() {
		return ElementFactory.initElements(driver, ModifyReservationPage.class);
	}

	public void ModifyReservationPageLoaded() {

		while (!btnClose.syncVisible(driver)) {
			initialize();
		}
		btnClose.syncVisible(driver);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnClose);
	}

	public boolean pageLoaded(WebDriver driver) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnClose);
	}

	public boolean loadPage() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnClose);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	// *****************************************
	// ***Page Interactions ***
	// *****************************************

	public void clickSubmit() {
		initialize();
		pageLoaded(btnSubmit);
		btnSubmit.syncEnabled(driver);
		btnSubmit.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickClose() {
		initialize();
		pageLoaded(btnClose);
		btnClose.syncEnabled(driver);
		btnClose.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	// click on Room and Resort
	public void clickonResortandRoomchageToReason(int rowNumber) throws Exception {
		selectResortandRoomRadio(rowNumber);
		selectResortandRoomType(rowNumber);
		selectNoCharge();
		selectReason();
		clickSubmit();
		verifyundoUpgradeButtonEnabled();
		clickClose();

	}

	public void selectResortandRoomRadio(int rowNumber) throws Exception {
		/*DataProvider_ExcelSheet datapro = new DataProvider_ExcelSheet();
		List<WebElement> elementList = driver.findElements(By.name("modResFormPanelSelectionId:selectedPanelId"));
		Iterator<WebElement> iterator = elementList.iterator();
		while (iterator.hasNext()) {
			Element element = new ElementImpl(iterator.next());
			if (element.getAttribute("value").equalsIgnoreCase(datapro.getCellContent(rowNumber, 11).trim())) {
				element.click();
			}
		}
		pageLoaded(btnClose);
		initialize();*/
	}

	public void selectResortandRoomType(int row_Number) throws Exception {
		/*DataProvider_ExcelSheet datapro = new DataProvider_ExcelSheet();
		// lstResort.select("Disney's Coronado Springs Resort");
		System.out.println("RsortName from excel sheet " + datapro.getCellContent(row_Number, 9).trim());
		lstResort.select(datapro.getCellContent(row_Number, 9).trim());

		Sleeper.sleep(3000);
		pageLoaded(lstRoomType);
		// initialize();
		lstRoomType.highlight(driver);
		// lstRoomType.select("Casitas - Executive Suite");
		// new
		// Select(driver.findElement(By.id("modResResortFormId:roomTypeNameId"))).selectByVisibleText("Standard
		// View");
		// pageLoaded();
		String RoomType = datapro.getCellContent(row_Number, 10).trim();

		loopCount = 0;
		do {
			// lstRoomType.select("Standard View");

			lstRoomType.select(RoomType);

			TestReporter.assertNotEquals(loopCount++, timeout,
					"The loading image is still displayed after selecting the Resort Type " + RoomType
							+ " and after a duration of " + String.valueOf(timeout / 2) + " seconds.");
		} while (lstRoomType.getFirstSelectedOption().equals(RoomType));
		// pageLoaded(lstResortList);
		initialize();
		new ProcessingYourRequest().WaitForProcessRequest(driver);*/

	}

	public void selectNoCharge() {
		List<WebElement> elementList = driver.findElements(By.name("modResResortFormId:modifyResChargeRadioButton"));
		Iterator<WebElement> iterator = elementList.iterator();
		while (iterator.hasNext()) {
			Element element = new ElementImpl(iterator.next());
			if (element.getAttribute("value").equalsIgnoreCase("No Charge")) {
				element.click();
			}
		}
		initialize();
		pageLoaded(lstReason);
		// initialize();
	}

	
	public void selectChargeOption() {
		List<WebElement> elementList = driver.findElements(By.name("modResResortFormId:modifyResChargeRadioButton"));
		Iterator<WebElement> iterator = elementList.iterator();
		while (iterator.hasNext()) {
			Element element = new ElementImpl(iterator.next());
			if (element.getAttribute("value").equalsIgnoreCase("Charge To Wholesaler")) {
				element.click();
			}
		}
		//initialize();
		pageLoaded(txtWholeSaleNumber);
		// initialize();
	}
	
	public void selectReason() {
		lstReason.select("For Counts");
		pageLoaded(btnClose);
		initialize();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void selectReason(String reason) {
		lstReason.select(reason);
		pageLoaded(btnClose);
		initialize();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void setAuthCode(String text) {
		txtWholeSaleNumber.set(text);
		pageLoaded(btnClose);
		initialize();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void verifyundoUpgradeButtonEnabled() {
		initialize();
		pageLoaded(btnUndoUpgrade);
		TestReporter.assertTrue(btnUndoUpgrade.syncEnabled(driver), "Undo Upgrade button is not Enabled");
	}

	public void clickOnUndoUpgrade() {
		pageLoaded(btnUndoUpgrade);
		initialize();
		btnUndoUpgrade.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		pageLoaded(tblUndoUpgrade);
		driver.switchTo().defaultContent();

		btnYes.jsClick(driver);
		clickSubmit();
		clickClose();

	}
	
	public void upgradeRoomNoCharge(String scenario){
		datatable.setVirtualtablePage("ModifyReservation");
		datatable.setVirtualtableScenario(scenario);
		
		String modificationType = datatable.getDataParameter("ModifyRadioValue");
		String resort = datatable.getDataParameter("Resort");
		String roomType = datatable.getDataParameter("RoomType");
		String noChargeReason = datatable.getDataParameter("NoChargeReason");
		
		selectModificationTypeByName(modificationType);
		selectResortAndRoomType(resort, roomType);
		selectNoCharge();
		selectReason(noChargeReason);
		clickSubmit();
		clickClose();
	}
	
	public void upgradeRoomWHSlCharge(String scenario){
		datatable.setVirtualtablePage("ModifyReservation");
		datatable.setVirtualtableScenario(scenario);
		
		String modificationType = datatable.getDataParameter("ModifyRadioValue");
		String resort = datatable.getDataParameter("Resort");
		String roomType = datatable.getDataParameter("RoomType");
		String authCode = datatable.getDataParameter("WholesaleNum");
		
		selectModificationTypeByName(modificationType);
		selectResortAndRoomType(resort, roomType);
		selectChargeOption();
		setAuthCode(authCode);
		clickSubmit();
		clickClose();
	}
	
	

	private void selectModificationTypeByName(String modType){
		initialize();
		List<WebElement> buttons = radModifyType.findElements(By.xpath("tbody/tr/td/input"));
		List<WebElement> texts = radModifyType.findElements(By.xpath("tbody/tr/td/label"));
		for(int counter = 0; counter < buttons.size(); counter++){
			new ElementImpl(buttons.get(counter)).highlight(driver);
			new ElementImpl(texts.get(counter)).highlight(driver);
			if(texts.get(counter).getText().equalsIgnoreCase(modType)){
				buttons.get(counter).click();
				break;
			}
		}
	}
	
	private void selectResortAndRoomType(String resort, String roomType){
		modRoomType = roomType;
		initialize();
		pageLoaded(lstResort);
		lstResort.select(resort);
		
		initialize();
		pageLoaded(lstRoomType);
		lstRoomType.select(roomType);
	}
	
	public String getModifiedRoomType(){
		return modRoomType;
	}
	
	/**
	 * @summary Public Method to select the modification type radio by its name
	 * 			using Virtual table
	 * @version Created 12/21/2015
	 * @author Dennis Stagliano
	 * @param scenario
	 * @throws NA
	 * @return NA
	 */
	public void selectModificationTypeByTheName(String scenario){
		initialize();
		
		datatable.setVirtualtablePage("ModifyReservation");
		datatable.setVirtualtableScenario(scenario);
		String modifyText = datatable.getDataParameter("ModifyRadioValue");
		
		List<WebElement> buttons = radModifyType.findElements(By.xpath("tbody/tr/td/input"));
		List<WebElement> texts = radModifyType.findElements(By.xpath("tbody/tr/td/label"));
		for(int counter = 0; counter < buttons.size(); counter++){
			new ElementImpl(buttons.get(counter)).highlight(driver);
			new ElementImpl(texts.get(counter)).highlight(driver);
			if(texts.get(counter).getText().equalsIgnoreCase(modifyText)){
				buttons.get(counter).click();
				break;
			}
		}
	}
	
	/**
	 * @summary Simple Method to click Radio Button PartyMix for modification
	 * @version Created 12/21/2015
	 * @author Dennis Stagliano
	 * @param NA
	 * @throws NA
	 * @return NA
	 */
	public void selectRadioPartyMix(){
		pageLoaded(radModifyPartyMix);
		radModifyPartyMix.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	/**
	 * @summary Method to add new guest to Party Mix Criteria
	 * @version Created 12/21/2015
	 * @author Dennis Stagliano
	 * @param scenario
	 * @throws NA
	 * @return NA
	 */
	public void addNewGuestToPartyMixCriteria(String scenario){
		initialize();
		pageLoaded(btnAddNewGuestButton);
		//Click the Add Guest button
		btnAddNewGuestButton.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		
		//Create integer variable to change the String GuestCount to int
		//and subtract 1 to get the index location of the new guest
		int newGuestCount;
		String GuestCount = eleGuestCountText.getText();
		//This will detect how many guests are in the list by utilizing the newGuestCount variable
		//The guest list is zero(0) based
		//You cannot assume the new guest will always be the last in the list
		newGuestCount = Integer.parseInt(GuestCount) - 1;
		
		//int newGuestCountIndex = newGuestCount - 1;
		System.out.println("The index of the New Guest is " + newGuestCount);
		//Convert the newGuestCountIndex back to string to be able to build id
		//String Indexid = String.valueOf(newGuestCountIndex);
		
		//Getting new guest information
		datatable.setVirtualtablePage("ModifyReservation");
		datatable.setVirtualtableScenario(scenario);
		String firstName = datatable.getDataParameter("NewGuestFirstName");
		String lastName = datatable.getDataParameter("NewGuestLastName");
		String ageType = datatable.getDataParameter("NewGuestAge");
		
		//Select the new guest to add the properties(NewGuestFirstName,NewGuestLastName,NewGuestAge)
		//If the guest list is 3, then the new guest to modify will be indexed at 2, since the guest
		//list is zero(0) based.
		//modResPartyFormId:guestList:2:agetTypeSelect
		driver.findElement(By.id("modResPartyFormId:guestList:"+newGuestCount+":firstNameInput")).sendKeys(firstName);
		Sleeper.sleep(1000);
		driver.findElement(By.id("modResPartyFormId:guestList:"+newGuestCount+":lastNameInput")).sendKeys(lastName);
		Sleeper.sleep(1000);
		Select selectAgeType = new Select(driver.findElement
				(By.id("modResPartyFormId:guestList:"+newGuestCount+":agetTypeSelect")));
		selectAgeType.selectByVisibleText(ageType);
		
		//Determines if the Submit button is to be clicked
			if(datatable.getDataParameter("SubmitOption").equalsIgnoreCase("True")){
				clickSubmit();
				TestReporter.logStep("Detecting for an Additional Cost Pop Up");
				Sleeper.sleep(2000);
				   if(eleAdditionalCostPopUp.isDisplayed()){
					   //Capture the Additional Cost Text
					   TestReporter.logStep("Accepting an Additional Cost and reSubmitting");
					   additionalCostYes();
				   }
			}
			//Resubmitting
			clickSubmit();
	}
		
	/**
	 * @summary : Method to select VIPLevel Radio Button
	 * @author  : Venkatesh
	 * @version : Created 12/18/2015
	 * @param   : NA
	 */
	public void selectVipLevel(){
		pageLoaded(eleVIPLevel);
		initialize(driver);
		//eleVIPLevel.highlight(driver);
		eleVIPLevel.jsClick(driver);
	}
	
	/**
	 * @summary : Method to select VIPLevel Value from list
	 * @author  : Venkatesh
	 * @version : Created 12/18/2015
	 * @param   : Scenario
	 */
	public void selectVipLevelValue(String scenario){
		datatable.setVirtualtablePage("ModifyReservation");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded(lstViplevel);
		initialize(driver);
		PageLoaded pl = new PageLoaded(driver);
		pl.isDomComplete();
		lstViplevel.select(datatable.getDataParameter("VIPLevel"));
		new ProcessingYourRequest().WaitForProcessRequest(driver);	
		System.out.println("Geting VipLevel Value : "+lstViplevel.getFirstSelectedOption().getText());
	}
	
	/**
	 * @summary : Method to Validate VIPLevel Value from list
	 * @author  : Venkatesh
	 * @version : Created 12/18/2015
	 * @param   : Scenario
	 */
	public void validateVIPLevel(String scenario){
		datatable.setVirtualtablePage("ModifyReservation");
		datatable.setVirtualtableScenario(scenario);
		TestReporter.assertTrue(lstViplevel.getFirstSelectedOption().getText().equalsIgnoreCase(datatable.getDataParameter("VIPLevel")), "Failed to validate VIPLevel Update");
	}

	/**
	 * @summary : Verifying whether the Room type is modified after the reservation.
	 * @author  : Praveen Namburi
	 * @version : Created 12/09/2015
	 * @param   : RoomTypeBeforeModifyingResv
	 */
	public void verifyRoomTypeIsModified(String RoomTypeBeforeModifyingResv){
		initialize();
		
		//Get the Modified RoomType text.
		String RoomTypeAfterModifyingResv = getModifiedRoomType();
		TestReporter.logStep("RoomType before modifying Resv is : "+RoomTypeBeforeModifyingResv);
		TestReporter.logStep("RoomType after modifying Resv is : "+RoomTypeAfterModifyingResv);
		
		//Verify the RoomType after modification.
		TestReporter.logStep("Validating the Room type after modifying the reservation.");
		TestReporter.assertTrue((RoomTypeBeforeModifyingResv != RoomTypeAfterModifyingResv), 
								 "The RoomType value is not modified.");		

	}
	
	/**
	 * @summary : Created method to handle the undo upgrade Confirmation pop-up.
	 * @param   : scenario
	 * @author  : Praveen Namburi
	 * @version : Created 12-18-2015
	 */
	public void handleUndoUpgradeConfirmationPOUP(String scenario){
		 datatable.setVirtualtablePage("ModifyReservation");
		 datatable.setVirtualtableScenario(scenario);
		 
		 TestReporter.logStep("Handling the Confirmation pop-up.");
		 TestReporter.logStep("Synchronizing for Undo-Upgrade pop-up.");
		 initialize();
		 
		 pageLoaded(tblUndoUpgrade);
		 if(!tblUndoUpgrade.isDisplayed()){
			 TestReporter.logStep("Undo UpGrade Confirmation pop-up is not loaded.");	 
			 
		 }else{			 
			 
			 TestReporter.logStep("Undo UpGrade Confirmation pop-up is loaded.");
			 //get the pop-up text message
			 pageLoaded(eleGetUndoUpGradePopUpText);
			 String getPopupText = eleGetUndoUpGradePopUpText.getText();
			 TestReporter.logStep("Pop-Up text message is : "+ getPopupText);
			 
			  if(datatable.getDataParameter("SelectOption").equalsIgnoreCase("TRUE")){
				 pageLoaded(btnYesUndoUpGrade);
				 btnYesUndoUpGrade.syncVisible(driver);
				 btnYesUndoUpGrade.highlight(driver);
				 TestReporter.logStep("Accepting the Undo Upgrade Pop-up.");
				 btnYesUndoUpGrade.jsClick(driver);
				 
			  }else{				 
				 pageLoaded(btnNoUndoUpGrade);
				 btnNoUndoUpGrade.syncVisible(driver);
				 btnNoUndoUpGrade.highlight(driver);
				 TestReporter.logStep("Cancelling the Undo upgrade pop-up.");
				 btnNoUndoUpGrade.jsClick(driver);
		      }
		 }
		 
	}

	/**
	 * @summary : Method to click on Undo upgrade button.
	 * @version : Created 12-18-2015
	 * @author  : Praveen Namburi.
	 */
	public void clickBtnUndoUpgrade(){
		TestReporter.logStep("Clicking on Undo UpGrade button.");
		pageLoaded(btnUndoUpgrade);
		initialize();
		btnUndoUpgrade.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void refresh(){
		driver.findElement(By.id("refreshReservationFrm:refreshViewRes")).click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
     /**
	 * @summary : Created method to verify and click on the undo upgrade button.
	 * @author  : Praveen Namburi.
	 * @version : Created 12-18-2015
	 */
	public void verifyAndClickUndoupgrade(){
		verifyundoUpgradeButtonEnabled();
		clickOnUndoUpgrade();
	}
	
	public void clickUndoUpGradeButton(){
		pageLoaded(btnUndoUpgrade);
		btnUndoUpgrade.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);

	}
	
	/**
	 * @summary : Created method to click on button Undo upgrade and handle the pop-up
	 * 			  and to close the Modify reservation page.
	 * @version : Created 12-18-2015
	 * @author  : Praveen Namburi
	 * @param   : scenario
	 */
	public void undoUpgradeRoomNoCharge(String scenario){
		initialize();		
		verifyundoUpgradeButtonEnabled();
		clickUndoUpGradeButton();
		handleUndoUpgradeConfirmationPOUP(scenario);
		//close modify reservation page.
		Sleeper.sleep(2000);
		clickClose();
	}
	
	/**
	 * @summary: Method to capture the roomtype while upgrading the room with NoCharge.
	 * @author : Praveen Namburi
	 * @version: Created 12-21-2015
	 * @param  : resort
	 * @param  : roomType
	 */
	public void selectAndcaptureResortAndRoomType(String resort, String roomType){
		modRoomType = roomType;
		
		initialize();
		pageLoaded(lstRoomType);
		Select selectOption = new Select(lstRoomType);	 
		WebElement eleBefore = selectOption.getFirstSelectedOption();
		lstRoomType.highlight(driver);
		TestReporter.log( "Room Type Before modification : "+eleBefore.getText());
		Sleeper.sleep(1000);
		lstRoomType.select(roomType);
		Sleeper.sleep(1000);
		lstRoomType.highlight(driver);
		WebElement eleAfter = selectOption.getFirstSelectedOption();
		TestReporter.log( "Room Type After modification : "+eleAfter.getText());
	}
	
	/**
	 * @summary : Created method to capture the room type while upgrading room Nocharge.
	 * @author  : Praveen Namburi
	 * @version : Created 12-21-2015
	 * @param 	: scenario
	 */
	public void upgradingRoomNoChargeAndgetRoomType(String scenario){
		datatable.setVirtualtablePage("ModifyReservation");
		datatable.setVirtualtableScenario(scenario);
		
		String modificationType = datatable.getDataParameter("ModifyRadioValue");
		String resort = datatable.getDataParameter("Resort");
		String roomType = datatable.getDataParameter("RoomType");
		String noChargeReason = datatable.getDataParameter("NoChargeReason");
		
		selectModificationTypeByName(modificationType);
		selectAndcaptureResortAndRoomType(resort, roomType); //Added this method to capture the roomtype
		selectNoCharge();
		selectReason(noChargeReason);
		clickSubmit();
		verifyundoUpgradeButtonEnabled();
		clickClose();
	}

	/**
	 * @summary Method to Accept an Additional Cost Pop Up
	 * @version Created 12/21/2015
	 * @author Dennis Stagliano
	 * @param NA
	 * @throws NA
	 * @return NA
	 */
	private void additionalCostYes(){
		additionalCostYes.syncEnabled(driver);
		additionalCostYes.click();
	}
	
	
}


