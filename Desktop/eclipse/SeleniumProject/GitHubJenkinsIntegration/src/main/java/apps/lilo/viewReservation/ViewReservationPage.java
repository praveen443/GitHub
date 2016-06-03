package apps.lilo.viewReservation;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.SettlementUI.SettlementUIWindow;
import apps.lilo.processingYourRequest.ProcessingYourRequest;
import apps.paymentUI.PaymentUIWindow;
import apps.paymentUI.PaymentUIWindow.paymentMethods;
import apps.paymentUI.PaymentUIWindow.paymentTypes;
import core.WebDriverSetup;
import core.WindowHandler;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Label;
import core.interfaces.Link;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.ButtonImpl;
import core.interfaces.impl.internal.ElementFactory;
//import org.custommonkey.*;
import core.interfaces.impl.internal.PageLoaded;
import selenium.common.Constants;
import utils.Datatable;
import utils.Sleeper;

/**
 * @summary Contains the page methods & objects to interact with the reservation details page
 * @version Created 08/25/2014
 * @author Waightstill W. Avery
 */
public class ViewReservationPage {

	//******************************
	//*** Main viewReservation Page Fields ***
	//******************************
	private String strAmountDueAtCheckIn = "";
	private String strRoomNumberID = "";
	private String strRoomStatus = "";
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	
	//******************************
	//*** Main viewReservation Page Elements ***
	//******************************
	//Grab the Proceed button
	@FindBy(id = "applyPymntForm:nextId")
	private Button btnProceed;

	//Grab the amount due at checkin
	/*@FindBy(id = "applyPymntForm:respPartyList:0:openMiniFolioId")*/
	@FindBy(id = "applyPymntForm:respPartyList:0:accLimitId")
	private Link lnkAmountDueAtCheckIn;

	//Grab the room number
	@FindBy(id = "applyPymntForm:accommodationList:0:subTable:0:roomNumberId")
	private Link lnkRoomNumberID;

	//Grab the "Assign" room number object
	@FindBy(id = "applyPymntForm:accommodationList:0:subTable:0:assignRoomId")
	private Link lnkAssignRoomID;

	//Grab the Express Checkout checkbox
	@FindBy(id = "applyPymntForm:expressCheckoutChkBox")
	private Checkbox chkExpressCheckout;

	//Grab the Settlement button
	@FindBy(id = "applyPymntForm:settlementmethodsId")
	private Button btnSettlementMethod;

	//Grab the Take Payment button
	@FindBy(id = "applyPymntForm:takePayment")
	private Link btnTakePayment;

	//Grab the Refresh object
	@FindBy(id = "refreshReservationFrm:refreshViewRes")
	private Button btnRefreshReservation;

	//Grab the room status
	@FindBy(id = "applyPymntForm:accommodationList:0:subTable:0:roomStatusId")
	private Webtable tblRoomStatus;

	//Grab the Payment UI URL
	/*@FindBy(css = "span[id^=\"commentsDisplayFormId:commentDisplay:0:j_id\"]")*/
	@FindBy(id = "applyPymntForm:hiddenPaymentURLId")
	private Label lblPaymentUIURL;

	//Grab the Balance Due Yes button
	@FindBy(id = "balanceDueCheckinForm:okButtonId1")
	private Button btnBalanceDueYes;

	//Grab the Balance Due No button
	@FindBy(id = "balanceDueCheckinForm:closeButtonId")
	private Button btnBalanceDueNo;


	//Grab the Find button
	@FindBy(id = "invSummaryTabForm:findButtonId")
	private Button btnFind;

	//Grab the Back Arrow button
	@FindBy(id = "invSummaryTabForm:preDateBtnId")
	private Button btnBackArrow;


	//Grab the Forward Arrow button
	@FindBy(id = "invSummaryTabForm:postDateBtnId")
	private Button btnForwardArrow;

	//Grab the Box Inventory button
	@FindBy(id = "invSummaryTabForm:boxInventoryButtonId")
	private Button btnboxInventory;

	// Grab the Password Text Box
	@FindBy(id = "inputTextboxId")
	private Textbox txtSearchCriteria;

	// Grab the Search button
	@FindBy(xpath="//div[@class='searchButton']")
	private Button btnSearch;

	// Grab the Arrival Date Calendar
	@FindBy(id = "arrivalDate")
	private Button btnArrivalDate;

	// Grab the Departure Date Calendar
	@FindBy(id = "departureDate")
	private Button btnDepartureDate;

	//Error Popup Header element
	@FindBy(id = "PMSRErrorModalPanelHeader")
	private Element eleErrorHeader;

	//Error Popup Ok button
	@FindBy(id = "errorForm:okButtonId")
	private Button btnErrorOk;

	//Settlement Method form
	@FindBy(id = "settlementMethodForm")
	private Element eleSettlementMethodForm;

	//Settlement UI Hidden URL
	@FindBy(id = "settlementMethodForm:hiddenSettlementURLId")
	private Element eleSettlementUiUrl;

	//*********************
	//** Build page area **
	//*********************

	/**
	 * 
	 * @summary Constructor to initialize the page
	 * @version Created 08/25/2014
	 * @author Waightstill W Avery
	 * @param  driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public ViewReservationPage(WebDriver driver){
		this.driver = driver;	
		ElementFactory.initElements(driver, this);  
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	private ViewReservationPage initialize(WebDriver driver) {
		return ElementFactory.initElements(driver, ViewReservationPage.class);	        
	}

	private ViewReservationPage initialize() {
		return ElementFactory.initElements(driver, ViewReservationPage.class);	        
	}

	public boolean BalanceDuePopUpLoaded(){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnBalanceDueYes); 
	} 

	public boolean viewReservationPageLoaded(){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnProceed); 
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				element);
	}

	public boolean pageLoaded(){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnProceed); 
	}

	//**************************************
	//*** Main CheckIn Page Interactions ***
	//**************************************

	/**
	 * 
	 * @summary Method to call other methods to capture reservation details and information
	 * @version Created 08/25/2014
	 * @author Waightstill W Avery
	 * @param  NA
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void captureReservationDetails(){
		viewReservationPageLoaded();
		captureAmountDueAtCheckIn();
		captureRoomID();
		captureRoomStatus();
	}
	public void clickSettlementMethod() {
		initialize();
		viewReservationPageLoaded();
		btnSettlementMethod.jsClick(driver);
		//		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/*
	 * *Define Capture and Get methods for room ID
	 */
	private void captureRoomID(){
		initialize(driver);
		if(lnkRoomNumberID.syncVisible(driver)){
			strRoomNumberID = lnkRoomNumberID.getText();
		}else if (lnkAssignRoomID.syncVisible(driver)) {
			strRoomNumberID = lnkAssignRoomID.getText();
		}
	}
	public String getRoomNumberID(){
		return strRoomNumberID;
	}

	/*
	 * *Define Capture and Get methods for room status
	 */
	private void captureRoomStatus(){
		strRoomStatus = tblRoomStatus.getText();
	}
	public String getRoomStatus(){
		return strRoomStatus;
	}

	/*
	 * *Define Capture and Get methods for amount due at check-in
	 */
	private void captureAmountDueAtCheckIn(){
		strAmountDueAtCheckIn = lnkAmountDueAtCheckIn.getText();
	}

	public String captureAmount_DueAtCheckIn(){
		strAmountDueAtCheckIn = lnkAmountDueAtCheckIn.getText();
		return strAmountDueAtCheckIn;
	}


	public String getAmountDueAtCheckIn(){
		return strAmountDueAtCheckIn;
	}
	
	/**
	 * 
	 * @summary Method to allow check-in to proceed without payment
	 * @version Created 08/25/2014
	 * @author Justin Phlegar
	 * @param  NA
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void proceedWithoutPayment(){
		initialize();
		pageLoaded(btnProceed);
		btnProceed.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
			

		initialize();
		pageLoaded(btnBalanceDueYes);
		
		btnBalanceDueYes.syncVisible(driver, 5, true);
		btnBalanceDueYes.highlight(driver);
		btnBalanceDueYes.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickProceed(){
		btnProceed.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickAssign(){

		initialize();
		lnkAssignRoomID.highlight(driver);
		pageLoaded(lnkAssignRoomID);
		lnkAssignRoomID.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}


	//------
	public void clickRoomNumber(){

		initialize();
		lnkRoomNumberID.highlight(driver);
		pageLoaded(lnkRoomNumberID);
		lnkRoomNumberID.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickFind(){
		btnFind.highlight(driver);
		btnFind.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickBackArrow(){
		btnBackArrow.highlight(driver);
		btnBackArrow.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickForwardArrow(){
		btnForwardArrow.highlight(driver);
		btnForwardArrow.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void boxInventoryButton(){
		btnboxInventory.sendKeys(Keys.TAB);
	}

	public void searchReservationDetails(String searchElement) {
		pageLoaded(txtSearchCriteria);
		txtSearchCriteria.set(searchElement);
	}

	public void clickSearchButton() {
		pageLoaded(btnSearch);
		btnSearch.highlight(driver);
		btnSearch.click();
	}



	public void enterArrivalDate(String arrivalDate) {
		btnArrivalDate.click();
		WebElement dateWidget1 = driver.findElement(By.id("arrivalDate"));
		List<WebElement> columns1 = dateWidget1.findElements(By.tagName("td"));

		for (WebElement cell0 : columns1) {
			if (cell0.getText().equals(arrivalDate)) {
				cell0.click();
				break;
			}
		}
	}

	public void enterDepartureDate(String departureDate) {
		btnDepartureDate.click();
		WebElement dateWidget2 = driver.findElement(By.xpath(".//*[@id='departureDate']/span/ul/li/table"));
		List<WebElement> columns2 = dateWidget2.findElements(By.tagName("td"));

		for (WebElement cell1 : columns2) {
			if (cell1.getText().equals(departureDate)) {
				cell1.click();
				break;
			}
		}
	}

	public String getDates() {

		Date date = new Date(); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		// Grab Filter Date
		int filterDay = cal.get(Calendar.DAY_OF_MONTH);
		String formattedFilterDay  = filterDay+ "";
		if (filterDay / 10 <= 0) {
			formattedFilterDay = "0"+ filterDay;
		}

		// Grab Arrival Date
		cal.add(Calendar.DATE, -3);
		int arrivalDay = cal.get(Calendar.DAY_OF_MONTH);
		String formattedArrivalDay  = arrivalDay+ "";
		if (arrivalDay / 10 <= 0) {
			formattedArrivalDay = "0"+ arrivalDay;
		}

		// Grab Departure Date
		cal.add(Calendar.DATE, 6);
		int departureDay = cal.get(Calendar.DAY_OF_MONTH);
		String formattedDepartureDay  = departureDay+ "";
		if (departureDay / 10 <= 0) {
			formattedDepartureDay = "0"+ departureDay;
		}

		// Click on Arrival Date Calendar so that date will come
		enterArrivalDate(formattedArrivalDay);

		// Click on Departure Date Calendar so that date will come
		enterDepartureDate(formattedDepartureDay);

		return formattedFilterDay;
	}

	public void searchReservation(final String scenario){
		try{
			datatable.setVirtualtablePage("SearchIndicator"); 
			datatable.setVirtualtableScenario(scenario);		
			String reservationNumber = datatable.getDataParameter("ReservationNumber");	//Defining what table to run from the virtual tables
			searchReservationDetails(reservationNumber);	
			clickSearchButton();
		} catch (Exception e){
			throw new AssertionError(getClass().getSimpleName() +" web elements input not loading properly");
		}

	}

	public void takePayment(String scenario) {
		datatable.setVirtualtablePath(Datatable.PAYMENTUI_MASTER_DATA_PATH);
		datatable.setVirtualtablePage("PaymentUI");
		datatable.setVirtualtableScenario(scenario);
		String winHandleBefore = driver.getWindowHandle();
		paymentTypes paymentType = PaymentUIWindow.getpaymentTypeEnum(datatable.getDataParameter("PaymentType"));
		paymentMethods paymentMethod = PaymentUIWindow.getpaymentMethodEnum(datatable.getDataParameter("PaymentMethod"));
		String status = datatable.getDataParameter("Status");
		String delay = datatable.getDataParameter("Delay");
		boolean incidentals = true;
		if(datatable.getDataParameter("Incidentals").equalsIgnoreCase("false")){
			incidentals = false;
		}
		String amount = datatable.getDataParameter("Amount");
		String expiredCC = datatable.getDataParameter("ExpiredCC"); 
		String primaryGuestInfo = datatable.getDataParameter("UsePrimaryGuestInfo");
		String errorMessage = datatable.getDataParameter("ExpectedErrorMessage");

		btnTakePayment.click();

		do{
			Sleeper.sleep(500);
		}while(driver.getWindowHandles().size() <= 1);

		for(String handle : driver.getWindowHandles()){
			if(driver.switchTo().window(handle).getTitle().contains("Apply Payment")){
				break;
			}
		}		

		PaymentUIWindow paymentUI = new PaymentUIWindow(driver);
		paymentUI.takePayment(paymentType, paymentMethod, status, delay, 
				incidentals, amount, expiredCC, primaryGuestInfo, errorMessage);

		driver.switchTo().window(winHandleBefore);

		new ProcessingYourRequest().WaitForProcessRequest(driver);

		initialize(driver);
		captureAmountDueAtCheckIn();
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}	

	public void handlePrinterWarning(){
		initialize();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		try{
			if(eleErrorHeader.syncVisible(driver, 5, false)){
				btnErrorOk.jsClick(driver);
			}
		}catch(Exception e){

		}
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
	}

	public void verifyChargeAccountStatusFromSettlementUI(String numberOfAccountsToVerify){	
		clickSettlementMethod();

		initialize();
		pageLoaded(eleSettlementMethodForm);
		eleSettlementMethodForm.highlight(driver);

		Button btnAddModify = new ButtonImpl(eleSettlementUiUrl.findElement(By.xpath("../input[@value='Add/Modify']")));
		btnAddModify.highlight(driver);
		btnAddModify.click();
		System.out.println();

		int counter = 0;
		do{
			Sleeper.sleep(500);
			counter++;
		}while(driver.getWindowHandles().size() == 1 && counter < WebDriverSetup.getDefaultTestTimeout()*2);

		for(String windHandle : driver.getWindowHandles()){
			if(driver.switchTo().window(windHandle).getTitle().contains("Set Settlement Method")){
				break;
			}
		}

		SettlementUIWindow settlement = new SettlementUIWindow(driver);
		settlement.launchChargeAccountUI();

		counter = 0;
		do{
			Sleeper.sleep(500);
			counter++;
		}while(driver.getWindowHandles().size() == 2 && counter < WebDriverSetup.getDefaultTestTimeout()*2);

		for(String windHandle : driver.getWindowHandles()){
			if(driver.switchTo().window(windHandle).getTitle().contains("Charge Account UI")){
				break;
			}
		}

		/*ChargeAccountUIWindow chargeAccount = new ChargeAccountUIWindow(driver);
		chargeAccount.verifyAllChargeAccountAreValid(Integer.parseInt(numberOfAccountsToVerify));*/
	}

	public void verifyChargeAccountStatusFromSettlementUI(String numberOfAccountsToVerify, String pinUpdated){	
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Lilo");
		clickSettlementMethod();

		initialize();
		pageLoaded(eleSettlementMethodForm);
		eleSettlementMethodForm.highlight(driver);

		Button btnAddModify = new ButtonImpl(eleSettlementUiUrl.findElement(By.xpath("../input[@value='Add/Modify']")));
		btnAddModify.highlight(driver);
		btnAddModify.focus(driver);
		btnAddModify.sendKeys(Keys.ENTER);
//		btnAddModify.click();
		System.out.println();

		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Set Settlement Method");
//		int counter = 0;
//		do{
//			Sleeper.sleep(500);
//			counter++;
//		}while(driver.getWindowHandles().size() == 1 && counter < WebDriverSetup.getDefaultTestTimeout()*2);
//
//		for(String windHandle : driver.getWindowHandles()){
//			if(driver.switchTo().window(windHandle).getTitle().contains("Set Settlement Method")){
//				break;
//			}
//		}
		boolean loaded = false;
		int count = 0;
		int maxTries = (int)Constants.ELEMENT_TIMEOUT;
		do{
			try{
				loaded = new PageLoaded().isDomComplete(driver, 1);
			}catch(Exception e){
				
			}
			count++;
		}while(!loaded && count <= maxTries);

		SettlementUIWindow settlement = new SettlementUIWindow(driver);
		settlement.launchChargeAccountUI();

		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Charge Account UI");
		
//		int counter = 0;
//		do{
//			Sleeper.sleep(500);
//			counter++;
//		}while(driver.getWindowHandles().size() == 2 && counter < WebDriverSetup.getDefaultTestTimeout()*2);
//
//		for(String windHandle : driver.getWindowHandles()){
//			if(driver.switchTo().window(windHandle).getTitle().contains("Charge Account UI")){
//				break;
//			}
//		}
		loaded = false;
		count = 0;
		maxTries = (int)Constants.ELEMENT_TIMEOUT;
		do{
			try{
				loaded = new PageLoaded().isDomComplete(driver, 1);
			}catch(Exception e){
				
			}
			count++;
		}while(!loaded && count <= maxTries);

		/*ChargeAccountUIWindow chargeAccount = new ChargeAccountUIWindow(driver);
		chargeAccount.verifyAllChargeAccountAreValid(Integer.parseInt(numberOfAccountsToVerify), pinUpdated);*/
	}

	//-----
	public void clickRoom_toVerify(){
		
		String Xpath = "//div/table/tbody/tr[2]/th/div/div/table/tbody/tr[2]/td[1]/a";
		  String getRoom = driver.findElement(By.xpath(Xpath)).getText();
		  System.out.println(getRoom);
		  if(!getRoom.isEmpty()&&getRoom!=null){
		   String  idofRoom =driver.findElement(By.xpath(Xpath)).getAttribute("id");
		      driver.findElement(By.id(idofRoom)).click();
		  }
		/*String getRoom = driver.findElement(By.xpath(Xpath)).getText();
		System.out.println(getRoom);
		if(!getRoom.isEmpty()&&getRoom!=null){
			String  idofRoom =driver.findElement(By.xpath(Xpath)).getAttribute("id");
		    driver.findElement(By.id(idofRoom)).click();
		}
		*/
		
		
		/*if(lnkRoomNumberID.isDisplayed()){
			clickRoomNumber();
		}else{
			clickAssign();
		}*/
	}
	
	 /** 
	    * @summary Method to click the Room Number link if visible
	    * @version Created 12/15/2015
	    * @author Dennis Stagliano
	    * @param  NA
	    * @throws NA
	    * @return NA
	    */
	public void clickRoomNumberLink(){
		if(lnkRoomNumberID.syncVisible(driver)){		
			lnkRoomNumberID.click();}
	}
	
	/** 
	    * @summary Public method to perform a simple initialize();
	    * @version Created 12/21/2015
	    * @author Dennis Stagliano
	    * @param  NA
	    * @throws NA
	    * @return NA
	    */
	public void pageInitialize(){
		initialize();
	}
	
	/**
	 * @Summary - Clicking Yes to Balance Due
	 * @version Created 12/21/2015
	 * @Author - Dennis Stagliano
	 */
	public void clickYesToBalanceDue(){
		//Verify Balance Due Pop Up
		BalanceDuePopUpLoaded();
		btnBalanceDueYes.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
}

