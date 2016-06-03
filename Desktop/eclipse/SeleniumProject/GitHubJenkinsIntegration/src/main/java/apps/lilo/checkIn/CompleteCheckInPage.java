package apps.lilo.checkIn;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.print.attribute.standard.MediaSize.NA;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.WebDriverSetup;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Label;
import core.interfaces.Link;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.CheckboxImpl;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;

/**
 * @summary Contains the page methods & objects to complete the check-in process
 * @version Created 09/01/2014
 * @author Waightstill W. Avery
 */
public class CompleteCheckInPage {

	//***************************************
	//*** Main CompeteCheckIn Page Fields ***
	//***************************************
	private String strCheckInDate;
	private String strCheckOutDate;
	private String strTravelPlanID;
	private String strTravelPlanStatus;
	private Datatable datatable = new Datatable();
	private WebDriver driver;
	
	//*****************************************
	//*** Main CompeteCheckIn Page Elements ***
	//*****************************************
	//Printer Encoder Cancel button
	@FindBy(id = "printEncodeForm:defaultPrintEncodCancelId")
	private Button btnPrinterEncoderClose;

	//Printer Encoder Submit button
	@FindBy(id = "printEncodeForm:defaultPrintEncodSubmitId")
	private Button btnPrinterEncoderSubmit;

	//Travel Plan status
	@FindBy(id = "travelSummeryFrm:travelPlanStatus1")
	private Label lblTravelPlanStatus;

	//Check In Date
	@FindBy(id = "travelSummeryFrm:chkInDtId")
	private Label lblCheckInDate;

	//Check Out Date
	@FindBy(id = "travelSummeryFrm:chkOutDtId")
	private Label lblCheckOutDate;

	//Complete Check-In button
	@FindBy(id = "newMediaEncodeForm:completeCheckinId")
	private Button btnCompleteCheckIn;

	//Exit button
	@FindBy(id = "newMediaEncodeForm:exitButtonId")
	private Button btnExit;

	//Tickets button
	@FindBy(id = "newMediaEncodeForm:ticketsButton")
	private Button btnTickets;

	//Refresh Reservation image
	@FindBy(id = "refreshReservationFrm:refreshViewRes")
	private Button btnRefreshReservation;

	//Webtable bell services
	@FindBy(id = "guestBellServiceModalPanelContentTable")
	private Webtable tblBellServiceModel;

	//Celebrations button
	@FindBy(id = "newMediaEncodeForm:celebrationsId")
	private Button btnCelebrations;

	//Encode button
	@FindBy(id = "newMediaEncodeForm:encode")
	private Button btnEncode;

	//Travel Plan ID
	@FindBy(id = "travelSummeryFrm:travelPlanId")
	private Label lblTravelPlanID;

	//Bell Service Yes button
	@FindBy(id = "bellserviceConfirmPopupForm:yes")
	private Button btnBellServiceYes;

	//Bell Service No button
	@FindBy(id = "bellserviceConfirmPopupForm:no")
	private Button btnBellServiceNo;

	//PIN Confirmation Content webtable
	@FindBy(id = "pinSetUpConfirmForm:pinSetUpConfirmModalPanelContentTable")
	private Webtable tblPinSetupContent;

	//PIN setup No button
	//@FindBy(css = "input[src^=\"/PMS/pms/images/but_no_popup.gif\"]")
	//DJS@FindByNG(ngButtonText = "No")
	//	WWA@FindBy(id = "pinSetUpConfirmForm:j_id22366")
	@FindBy(id = "pinSetUpConfirmForm.*")
	private Button btnPinSetupNo;

	//PIN setup Yes button
	//DJS	@FindBy(css = "input[src^=\"/PMS/pms/images/but_yes_popup.gif\"]")
	//WWA	@FindBy(id = "pinSetUpConfirmForm:j_id22365")
	@FindBy(xpath = "/html/body/div[6]/div[2]/div/div[2]/table/tbody/tr[2]/td/table/tbody/tr[1]/td[1]/table/tbody/tr[2]/td/div/table/tbody/tr/td/input[1]")
	private Button btnPinSetupYes;

	//Close Special Requests window
	@FindBy(id = "specialRequestCheckInForm:specialRequestCheckingCloseButtonId")
	private Button btnSpecialRequestClose;

	//Encode Submit
	@FindBy(id = "printEncodeForm:defaultPrintEncodSubmitId")
	private Button btnEncodeSubmit;

	//Primary guest media encode checkbox
	@FindBy(id = "newMediaEncodeForm:kttwDetails:0:media:0:mediaRowCheckBoxId")
	private Checkbox chkPrimaryEncode;

	//Encoder Table
	@FindBy(id = "printEncodeForm:encoderDetails")
	private Webtable tblEncoderList;

	@FindBy(id = "newMediaEncodeForm:kttwDetails")
	private Webtable tblMediaEncode;

	//Room Status
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:0:roomStatusCommandLink")
	private Link lnkRoomStatus;

	//proceed button 
	@FindBy(id = "applyPymntForm:nextId")
	private Button btnProceed;

	//Webtable pin Setup 
	@FindBy(id = "pinSetUpConfirmForm:pinSetUpConfirmModalPanelContentTable")
	private Webtable tblPinSetup;

	//Create page Object for - Yes button exist on pop up 
	@FindBy(id = "balanceDueCheckinForm:okButtonId1")
	private Button btnYes;

	@FindBy(xpath = "//table/tbody/tr[1]/td[2]/select")
	private Listbox lstBellServiceType;

	@FindBy(xpath = "//table/tbody/tr[2]/td[2]/input")
	private Textbox txtBagQuantity;

	@FindBy(xpath = "//table/tbody/tr[3]/td[2]/select")
	private Listbox lstBagSource;

	@FindBy(xpath = "//table/tbody/tr[3]/td[3]/input")
	private Textbox txtBagSourceLocation;

	@FindBy(xpath = ".//table[@id = 'guestLocationTable']/tbody/tr/td[2]/select")
	private Listbox lstGuestLocation;

	@FindBy(xpath = ".//table[@id = 'guestLocationTable']/tbody/tr/td[3]/input")
	private Textbox txtGuestLocationNumber;

	@FindBy(xpath = "//table/tbody/tr[5]/td[2]/select")
	private Listbox lstBagDestinationLocation;

	@FindBy(xpath = "//table/tbody/tr[5]/td[3]/input")
	private Textbox txtBagDestinationLocationNumber;

	@FindBy(name="bellservicePopupForm:bellServiceButtonIdForCheckin")
	private Button btnBellServicesSubmit;
	
	@FindBy(name="alertPopupForm:okBtnId")
	private Button btnBellServicesOK;

	//*********************
	//** Build page area **
	//*********************
	

	/**
	 * 
	 * @summary Constructor to initialize the page
	 * @version Created 09/01/2014
	 * @author Waightstill W Avery
	 * @param  driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public CompleteCheckInPage(WebDriver driver){
		this.driver = driver;	
		ElementFactory.initElements(driver, this);  
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	private CompleteCheckInPage initialize(WebDriver driver) {
		return ElementFactory.initElements(driver, CompleteCheckInPage.class);	        
	}

	private CompleteCheckInPage initialize() {
		return ElementFactory.initElements(driver, CompleteCheckInPage.class);	        
	}

	public boolean pageLoaded(){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnCompleteCheckIn); 
	}

	public boolean pageLoaded(Element element){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	//*********************************************
	//*** Main CompeteCheckIn Page Interactions ***
	//*********************************************

	/**
	 * 
	 * @summary Method to call other methods that capture details at check in
	 * @version Created 09/01/2014
	 * @author Waightstill W Avery
	 * @param  NA
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void captureCheckInDetails(){
		pageLoaded();
		captureCheckInDate();
		captureCheckOutDate();
		captureTravelPlanID();
		captureTravelPlanStatus();
		//captureReservationStatus();
	}

	/**
	 * 
	 * @summary Method to call other methods that capture details after check in
	 * @version Created 09/01/2014
	 * @author Waightstill W Avery
	 * @param NA
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void captureCheckInDetailsAfterCheckIn(){
		pageLoaded(lnkRoomStatus);
		captureCheckInDate();
		captureCheckOutDate();
		captureTravelPlanID();
		captureTravelPlanStatus();
	}

	/*
	 * *Methods to capture and return check-in date
	 */
	private void captureCheckInDate(){
		strCheckInDate = lblCheckInDate.getText();
	}	
	public  String getCheckInDate(){
		return strCheckInDate;
	}

	/*
	 * *Methods to capture and return check-out date
	 */
	private void captureCheckOutDate(){
		strCheckOutDate = lblCheckOutDate.getText();
	}	
	public String getCheckOutDate(){
		return strCheckOutDate;
	}

	/*
	 * *Methods to capture and return travel plan ID
	 */
	private void captureTravelPlanID(){
		strTravelPlanID = lblTravelPlanID.getText();
	}	
	public String getTravelPlanID(){
		return strTravelPlanID;
	}

	/*
	 * *Methods to capture and return travel plan status
	 */
	private void captureTravelPlanStatus(){
		strTravelPlanStatus = lblTravelPlanStatus.getText();
	}	
	public String getTravelPlanStatus(){
		return strTravelPlanStatus;
	}

	/**
	 * 
	 * @summary Method to close the printer encoder window
	 * @version Created 09/01/2014
	 * @author Waightstill W Avery
	 * @param  NA
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void closePrinterEncoder(){
		//		initialize(driver);
		pageLoaded(btnPrinterEncoderClose);
		btnPrinterEncoderClose.syncEnabled(driver);
		btnPrinterEncoderClose.syncVisible(driver);
		btnPrinterEncoderClose.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickCompleteCheckIn(){
		//		initialize();
		pageLoaded(btnCompleteCheckIn);
		btnCompleteCheckIn.syncVisible(driver);
		btnCompleteCheckIn.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver, 40);
	}

	/**
	 * 
	 * @summary Method to interact with the bell service window
	 * @version Created 09/01/2014
	 * @author Waightstill W Avery
	 * @param  scenario - used to determine which button to click
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void clickBellService(String scenario){
		datatable.setVirtualtablePage("CompleteCheckIn");
		datatable.setVirtualtableScenario(scenario);

		initialize(driver);
		//		pageLoaded(btnBellServiceNo);
		//		pageLoaded();
		//		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		if(btnBellServiceNo.syncVisible(driver, 10, false)){
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			if(btnBellServiceNo.syncVisible(driver, 5, false)){
				System.out.println("Syncing to Bell Service No");
				System.out.println("Bell Service No sync complete");		
				if(datatable.getDataParameter("BelLService").equalsIgnoreCase("FALSE")){
					btnBellServiceNo.jsClick(driver);
				}else{
					btnBellServiceYes.jsClick(driver);
				}
			}
			//		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
			new ProcessingYourRequest().WaitForProcessRequest(driver);
		}
	}

	public void clickBellService_json() {
		initialize(driver);
		pageLoaded(tblBellServiceModel);
		tblBellServiceModel.syncVisible(driver,1,false);
		tblBellServiceModel.highlight(driver);
		initialize(driver);
		btnBellServiceNo.syncEnabled(driver);

		//if(datatable.getDataParameter("BelLService").equalsIgnoreCase("FALSE")){
		btnBellServiceNo.highlight(driver);
		Sleeper.sleep(2000);
		btnBellServiceNo.jsClick(driver);
		Sleeper.sleep(2000);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}	


	/**
	 * 
	 * @summary Method to interact with the PIN setup window
	 * @version Created 09/01/2014
	 * @author Waightstill W Avery
	 * @param  scenario - used to determine which button to click
	 * @throws NA
	 * @return NA
	 * 
	 */	
	public void clickPinSetup(String scenario){
		datatable.setVirtualtablePage("CompleteCheckIn");
		datatable.setVirtualtableScenario(scenario);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		System.out.println("Syncing to PIN No to be visible");
		tblPinSetupContent.syncVisible(driver);
		tblPinSetupContent.highlight(driver);
		System.out.println("PIN sync complete");		

		List<WebElement> elems = driver.findElements(By.cssSelector("input[id^='pinSetUpConfirmForm']"));
		/*
		 * Uncomment the below code to debug the PIN setup buttons
		 */
		//		System.out.println("Number of elements: " + elems.size());
		//		int counter = 0;
		//		for(WebElement ele : elems){
		//			System.out.println("Element " + counter);
		//			new ElementImpl(ele).highlight(driver);
		//			Sleeper.sleep(5000);
		//			counter++;
		//		}

		if(datatable.getDataParameter("PinSetup").equalsIgnoreCase("FALSE")){
			System.out.println("Clicking pin no");
			Element btnNo = new ElementImpl(elems.get(4));
			btnNo.highlight(driver);
			btnNo.jsClick(driver);
		}else{
			System.out.println("Clicking pin yes");
			Element btnYes = new ElementImpl(elems.get(4));
			btnYes.highlight(driver);
			btnYes.jsClick(driver);
		}
		Sleeper.sleep(2000);
		System.out.println("pin click complete");
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * 
	 * @summary Method used to call other methods to complete the check-in process
	 * @version Created 09/01/2014
	 * @author Waightstill W Avery
	 * @param  scenario - used to pass the scenario to methods called within (PIN setup, bell service, etc)
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void finishCheckInProcess(String scenario){

		//		closePrinterEncoder();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		System.out.println("Syncing to first printer button");
		if(btnPrinterEncoderClose.syncVisible(driver)){
			closePrinterEncoder();
		}
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
		Sleeper.sleep(2000);
		clickCompleteCheckIn();
		System.out.println("Closing second printer window");
		closePrinterEncoder();

		System.out.println("Syncing to special requests");
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		if(btnSpecialRequestClose.syncVisible(driver, 5, false)){
			closeSpecialRequests();
		}
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);

		pageLoaded(tblPinSetupContent);
		initialize(driver);

		System.out.println("Closing PIN window");
		//		try {
		clickPinSetup(scenario);
		//		} catch (Exception e) {
		//			e.printStackTrace();
		//		}
		System.out.println("Closing bell service window");
		//		try {
		clickBellService(scenario);
		//		} catch (Exception e) {
		//			e.printStackTrace();
		//		}
	}


	public void finishCheckInProcess_json(JSONObject myobject){


		closePrinterEncoder();
		clickCompleteCheckIn();
		closePrinterEncoder();
		try {
			clickPinSetup();
		} catch (Exception e) {
			e.printStackTrace();
		}


		try {
			clickBellService_json();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public void closeSpecialRequests(){
		btnSpecialRequestClose.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * 
	 * @summary Method to encode media for all guests
	 * @version Created 10/30/2014
	 * @author Waightstill W Avery
	 * @param  NA
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void encodeMedia(String scenario){
		if(btnPrinterEncoderClose.syncVisible(driver)){
			/*if(WebDriverSetup.browser.toString().equalsIgnoreCase("firefox")){
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("document.getElementById(\"printEncodeForm:defaultPrintEncodCancelId\").click();");	
			}else{*/
			btnPrinterEncoderClose.jsClick(driver);
			//}
		}
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		encodeMediaForAllGuests(scenario);
	}

	/**
	 * 
	 * @summary Method to encode media for all guests
	 * @version Created 10/30/2014
	 * @author Waightstill W Avery
	 * @param scenario - used to determine which datatable row to use
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void encodeMediaForAllGuests(String scenario){
		datatable.setVirtualtablePage("CompleteCheckIn");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded();

		String encoderName = datatable.getDataParameter("Encoder");
		int numberOfGuests = Integer.parseInt(datatable.getDataParameter("NumberOfGuests"));
		int loopCounter;

		//Select all media checkboxes
		for(loopCounter = 0; loopCounter < numberOfGuests; loopCounter++){
			Checkbox element = new CheckboxImpl(driver.findElement(By.id("newMediaEncodeForm:kttwDetails:" +String.valueOf(loopCounter)+ ":media:0:mediaRowCheckBoxId")));
			element.jsToggle(driver);
			Sleeper.sleep(3000);
		}

		do{
			Sleeper.sleep(500);
			initialize(driver);
			pageLoaded(btnEncode);
		}while(btnEncode.getAttribute("class").contains("disabled-true"));

		clickEncode();
		selectEncoder(encoderName);
	}


	public void clickEncode(){
		do{
			pageLoaded();
			initialize(driver);
		}while(btnEncode.syncDisabled(driver, 1, false));

		do{
			btnEncode.jsClick(driver);
			Sleeper.sleep(100);
		}while(tblEncoderList.syncHidden(driver, 1, false));
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		pageLoaded();
	}

	public void clickEncodeSubmit(){
		btnEncodeSubmit.jsClick(driver);
		//new ProcessingYourRequest().WaitForProcessRequest(driver);
		do{
			Sleeper.sleep(500);
		}while(tblEncoderList.elementWired());
	}


	public void clickProceedButton()
	{

		//btnProceed.syncEnabled(driver,3, false);
		pageLoaded(btnProceed);
		btnProceed.highlight(driver);
		btnProceed.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);


	}

	/**
	 * 
	 * @summary Method to select an encoder with which to encode media
	 * @version Created 10/30/2014
	 * @author Waightstill W Avery
	 * @param encoder - name of the encoder to use
	 * @throws NA
	 * @return NA
	 * 
	 */
	private void selectEncoder(String encoder){
		pageLoaded();
		int row = tblEncoderList.getRowWithCellText(driver, encoder);
		Checkbox encoderCheckbox = new CheckboxImpl(driver.findElement(By.id("printEncodeForm:encoderDetails:" +String.valueOf(row-2)+ ":masModeCheckbox")));
		encoderCheckbox.jsToggle(driver);

		clickEncodeSubmit();
	}

	/**
	 * 
	 * @summary Method to capture media numbers that were encoded
	 * @version Created 10/30/2014
	 * @author Waightstill W Avery
	 * @param scenario - used to determine which datatable row to use
	 * @throws NA
	 * @return media numbers that were encoded
	 * 
	 */
	public String[] captureEncodedMediaNumbers(String scenario){
		datatable.setVirtualtablePage("CompleteCheckIn");
		datatable.setVirtualtableScenario(scenario);
		int loopCounter;
		int numberOfGuests = Integer.parseInt(datatable.getDataParameter("NumberOfGuests"));
		String[] mediaNumbers = new String[numberOfGuests];

		if(btnPrinterEncoderClose.syncVisible(driver)){
			closePrinterEncoder();
		}

		pageLoaded();
		for(loopCounter = 0; loopCounter < numberOfGuests; loopCounter++){
			mediaNumbers[loopCounter] = tblMediaEncode.getCellData(driver, 3*(loopCounter + 1), 4);
		}
		return mediaNumbers;
	}

	/**
	 * 
	 * @summary Method to capture media numbers
	 * @version Created 10/30/2014
	 * @author Waightstill W Avery
	 * @param scenario - used to determine which datatable row to use
	 * @throws NA
	 * @return media numbers
	 * 
	 */
	public String[] captureMediaNumbers(String scenario){
		datatable.setVirtualtablePage("CompleteCheckIn");
		datatable.setVirtualtableScenario(scenario);
		int loopCounter;
		int numberOfGuests = Integer.parseInt(datatable.getDataParameter("NumberOfGuests"));
		String[] mediaNumbers = new String[numberOfGuests];

		if(btnPrinterEncoderClose.syncVisible(driver)){
			closePrinterEncoder();
		}

		pageLoaded();
		initialize(driver);
		for(loopCounter = 0; loopCounter < numberOfGuests; loopCounter++){
			mediaNumbers[loopCounter] = tblMediaEncode.getCellData(driver, 3*(loopCounter + 1), 4);
		}
		return mediaNumbers;
	}

	public void clickPinSetup() {

		tblPinSetup.syncVisible(driver, 45, false);
		initialize(driver);
		List<WebElement>  lstPin = driver.findElements(By.cssSelector("input[name^=\"pinSetUpConfirmForm:\"]"));

		for(WebElement we : lstPin){
			Element element = new ElementImpl(we);
			if(element.syncVisible(driver, 1, false)){
				element.jsClick(driver);
				break;
			}
		}

		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * 
	 * @summary Method to click BalanceDue Yes Button
	 * @version Created 11/19/2015
	 * @author Venkatesh Atmakuri
	 * @param  NA
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void clickBalanceDueYes(){
		initialize();
		pageLoaded(btnYes);
		btnYes.syncVisible(driver);
		btnYes.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/*
	 * *Methods to capture and return reservation status
	 */
	//	private void captureReservationStatus(){
	//		strReservationStatus = lblReservationStatus.getText();
	//	}
	//	
	//	public String getReservationStatus(){
	//		return strReservationStatus;
	//	}
	
	/**
	 * 
	 * @summary Method to click on Bell services and to provide bell services inputs
	 * @version Created 4/6/2015
	 * @author Sabitha Adama
	 * 
	 */
	public void setBellServices(String scenario){
		datatable.setVirtualtablePage("CompleteCheckIn");
		datatable.setVirtualtableScenario(scenario);
		datatable.getDataParameter("BelLService");
		String servicetype = datatable.getDataParameter("ServiceType");
		String bagquantity = datatable.getDataParameter("BagQuantity");
		String bagsourcelocation = datatable.getDataParameter("BagSource");
		String bagsourcelocationnumber = datatable.getDataParameter("BagSourceLocation");
		String guestlocation = datatable.getDataParameter("GuestLocation");
		String guestlocationnumber = datatable.getDataParameter("GuestLocationNumber");
		String bagdestinationlocation = datatable.getDataParameter("BagDestinationLocation");
		String bagdestinationnumber = datatable.getDataParameter("BagDestinationLocationNumber");


		initialize(driver);
		if(btnBellServiceNo.syncVisible(driver, 10, false)){
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			if(btnBellServiceNo.syncVisible(driver, 5, false)){
				System.out.println("Syncing to Bell Service No");
				System.out.println("Bell Service No sync complete");		
				if(datatable.getDataParameter("BelLService").equalsIgnoreCase("FALSE")){
					btnBellServiceNo.jsClick(driver);
				}else{
					btnBellServiceYes.jsClick(driver);

					pageLoaded(lstBellServiceType);
					lstBellServiceType.select(servicetype);

					pageLoaded(txtBagQuantity);
					txtBagQuantity.safeSet(bagquantity);

					pageLoaded(lstBagSource);
					lstBagSource.select(bagsourcelocation);

					pageLoaded(txtBagSourceLocation);
					txtBagSourceLocation.safeSet(bagsourcelocationnumber);

					pageLoaded(lstGuestLocation);
					lstGuestLocation.select(guestlocation);

					pageLoaded(txtGuestLocationNumber);
					txtGuestLocationNumber.safeSet(guestlocationnumber);

					pageLoaded(lstBagDestinationLocation);
					lstBagDestinationLocation.select(bagdestinationlocation);

					pageLoaded(txtBagDestinationLocationNumber);
					txtBagDestinationLocationNumber.highlight(driver);
					txtBagDestinationLocationNumber.safeSet(bagdestinationnumber);

					pageLoaded(btnBellServicesSubmit);
					btnBellServicesSubmit.highlight(driver);
					btnBellServicesSubmit.click();
					
					pageLoaded(btnBellServicesOK);
					btnBellServicesOK.highlight(driver);
					btnBellServicesOK.click();
				}
			}
			new ProcessingYourRequest().WaitForProcessRequest(driver);
		}


	}


}

