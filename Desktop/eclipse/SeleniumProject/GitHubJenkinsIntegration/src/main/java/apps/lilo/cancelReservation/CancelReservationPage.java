package apps.lilo.cancelReservation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;
import utils.date.DateTimeConversion;


/**
 * This class contains elements and element interactions for a given page of a web application
 * 
 * @summary Contains the page methods & objects for Quickbook Cancel Reservation window
 * @version Created 10/28/2015
 * @author  Praveen Namburi
 */
public class CancelReservationPage {

	//***************************************************
	//*** Main QuickbookCancelReinstate Page Fields ***
	//***************************************************	
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	//**************************************************
	//*** Main Cancel reservation Page Elements ***
	//**************************************************

	//Create Textbox object for Guest First Name 
	@FindBy(id = "quickBookForm:firstNameId")
	private Textbox txtFirstName;	

	//Create Textbox object for Guest Last Name 
	@FindBy(id = "quickBookForm:lastNameId")
	private Textbox txtLastName;									 

/*	//Create Textbox object for RequetsedBy textbox field
	@FindBy(name = "cancelResForm:j_id612")
	private Textbox txtRequestedBy;*/
	
	//Create Textbox object for RequetsedBy textbox field

	//Create Textbox object for RequetsedBy textbox field 
	@FindBy(xpath = "//*[@id='cancelResForm']/table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr[3]/td[2]/input")
	private Textbox txtRequestedBy;

	/*//Create Textbox object for RequetsedBy textbox field
	@FindBy(name = "cancelResForm:j_id612")
	private Textbox txtRequestedBy;*/
	
	//Create Listbox object for Reason ListBox
	@FindBy(id = "cancelResForm:reasons")
	private Listbox lstReason;

	//Create Element object for Cancel reservation PopUp
	@FindBy(id = "cancelReinstResModalPanelContentDiv")
	private Element eleCancelReservationPOPUP;

	//Create Button object for the Cancel button
	@FindBy(id = "cancelResForm:cancelResButtonId")
	private Button btnCancelReservation;

	//Create Checkbox object for the Waive checkbox
	@FindBy(id = "cancelResForm:waiveCheckBox")
	private Checkbox chkWaiveBox;


	//-
	//Create Text box object for ArrivalDate Input 
	@FindBy(id = "modResDateFormId:arrivalDateButtonInput")
	private Textbox txtArrivalDate;

	//Create Text box object for ArrivalDate Input 
	@FindBy(id = "modResDateFormId:departureDateButtonInput")
	private Textbox txtDeptDate;

	//Create Text box object for no of nights input 
	@FindBy(id = "modResDateFormId:nightsId")
	private Textbox txtNights;

	//go click on submit button
	@FindBy(id = "modResActionsFormId:submit1")
	private Button btnSubmit;

	//go click on button Yes
	@FindBy(id = "additionalCostForm:yesId")
	private Button btnPopUpYes;

	//go click on button cancel
	@FindBy(id = "forModResClose:close")
	private Button btnClose;

	//go click on refresh icon 
	@FindBy(id = "refreshReservationFrm:refreshViewRes")
	private Element eleRefresh;

	//go get arrival date Text As present
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:0:arrDate")
	private Checkbox  eleArrivalDate;

	//go get arrival date Text As present
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:1:arrDate")
	private Checkbox  eleArrivalDate1;

	//go get dept date text as present
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:0:deptDate")
	private Checkbox eleDeptDate;

	//go get dept date text as present
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:1:deptDate")
	private Checkbox eleDeptDate1;
	//go click on radio Button 
	@FindBy(id = "modResFormPanelSelectionId:selectedPanelId:1")
	private Element rdioResortAndRoom;

	//go get modified resort name
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:0:resortName")
	private Element txtResortName;
	
	//creating object for selecting cancellation reason
	@FindBy(id = "cancelResForm:reasons")
	private Element optionCanReason;
	
	//@FindBy(name = "cancelResForm:j_id612")
	@FindBy(xpath = "//*[@id='cancelResForm']/table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr[3]/td[2]/input")
	private Textbox txtReqBY;
	
	@FindBy(id = "cancelResForm:waiveCheckBox")
	private Element chkWaives;
	
	@FindBy(id = "cancelResForm:cancelResButtonId")
	private Element btnCancelRes;
	
	@FindBy(xpath = ".//*[@id='roomTabFrm:travelPlanSegmentViewId:0:subTable:0:reservationStatusId']")
	private Element txtConfMsg;
	
	//.//*[@id='modResResortFormId:roomTypeNameId']
		@FindBy(id = "modResResortFormId:roomTypeNameId")
		private Element lstRoomType;

	//***************************
	//***** Build page area *****
	//***************************


	/**
	 * 
	 * @summary Constructor to initialize the QuickbookCancelReinstatePage.
	 * @version Created 10/28/2015
	 * @author  Praveen Namburi
	 * @param   driver
	 * @throws  NA
	 * @return  NA
	 * 
	 */
	public CancelReservationPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public  CancelReservationPage initialize() {
		return ElementFactory.initElements(driver, CancelReservationPage.class);	        
	}


	/**
	 *@summary  : Method to determine if the Cancel Reservation form is displayed.
	 * @version : Created 10/28/2015
	 * @author  : Praveen Namburi 
	 * @return  : Boolean - true, if the page is loaded.Else will return false.
	 */
	public boolean pageLoaded(){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnCancelReservation); 
	}

	public boolean pageLoaded(Button button){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, button); 
	}

	public boolean pageLoaded(Element element){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element); 
	}  

	//******************************************************  
	//*** Main QuickbookCancelReinstatePage Interactions ***
	//******************************************************

	/**
	 * 
	 * @summary Created Method to cancel the Reservation
	 * @version Created 10/28/2015
	 * @author  Praveen Namburi
	 * @param   driver
	 * @throws  Exception if datatable scenarios or parameters are not found
	 * @return  NA
	 * 
	 */
	public void cancelreservation(String scenario) {
		//Setup the virtual table name and scenarion name
		datatable.setVirtualtablePage("CancelReservationPage");
		datatable.setVirtualtableScenario(scenario);

		//Find the Cancel Reservation popup and highlight the element
		pageLoaded(eleCancelReservationPOPUP);
		eleCancelReservationPOPUP.highlight(driver);

		//Select the option "Air Not available" in the Reason Listbox
		TestReporter.logStep("Select the option from Reason list "+datatable.getDataParameter("SelectOption"));
		//lstReason.click();
		pageLoaded(lstReason);

		lstReason.select(datatable.getDataParameter("SelectOption"));
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		
		//Set Guest user name from virtual Table
		//txtRequestedBy.click();
		txtRequestedBy.highlight(driver);
		txtRequestedBy.set(datatable.getDataParameter("FirstName")); 					 			    

		//Click on the Waive check-Box.
		TestReporter.logStep("Click on Waive Checkbox");
		pageLoaded(chkWaiveBox);
		chkWaiveBox.click();

		//Click on canel Reservation button.
		TestReporter.logStep("Click on canel Reservation button");
		clickCancelReservationButton(); 		
	}

	/**
	 * 
	 * @summary Created the below Method to click on the cancel reservation button and to process the request
	 * @version Created 10/28/2015
	 * @author  Praveen Namburi
	 * @param   NA
	 * @return  NA
	 * 
	 */

	public void clickCancelReservationButton(){
		pageLoaded(btnCancelReservation);
		btnCancelReservation.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}      




	/**
	 * 
	 * @summary Created the below Method verify ModifyDates under Modify reservation for arrival Dates
	 * @version Created 12/03/2015
	 * @author  Lalitha Banda
	 * @param   NA
	 * @return  NA
	 * 
	 */

	public void performModification(String NoOfRooms){
		Sleeper.sleep(1000);
		txtNights.safeSet(NoOfRooms);
		btnSubmit.highlight(driver);
		btnSubmit.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		btnPopUpYes.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
//		Sleeper.sleep(5000);
		btnClose.highlight(driver);
		btnClose.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		pageLoaded(eleRefresh);
		eleRefresh.highlight(driver);
		eleRefresh.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}


	public void VerifyModifiedDates(String inputstring){

		if(inputstring.equalsIgnoreCase("ArrivalDate")){
			String getDate = DateTimeConversion.getDaysOut("1","MM/dd/yyyy");
			initialize();
			txtArrivalDate.safeSet(getDate);
			performModification("1");
			String ActualDate = eleArrivalDate.getText()+"-"+eleDeptDate.getText();
			System.out.println(ActualDate);
			String ExpectedDate = DateTimeConversion.coversionStringToDateFormat(getDate);
			System.out.println(ExpectedDate);
			TestReporter.assertTrue(ActualDate.contains(ExpectedDate.substring(1, ExpectedDate.length()-1)), "Arrival date is Not Modified");

		}else if (inputstring.equalsIgnoreCase("DeptDate")){

			String getDate = DateTimeConversion.getDaysOut("4","MM/dd/yyyy");
			txtDeptDate.safeSet(getDate);
			performModification("4");
			String ActualDate = eleArrivalDate1.getText()+"-"+eleDeptDate1.getText();
			System.out.println(ActualDate);
			String ExpectedDate = DateTimeConversion.coversionStringToDateFormat(getDate);
			System.out.println(ExpectedDate);
			TestReporter.assertTrue(ActualDate.contains(ExpectedDate.substring(1, ExpectedDate.length()-1)), "Departure Date is Not Modified!!");
		}
	}

	

	/**
	 * 
	 * @summary Created the below Method verify Resorts and Rooms modification
	 * @version Created 12/03/2015
	 * @author  Lalitha Banda
	 * @param   NA
	 * @return  NA
	 * 
	 */
	
	
	public void perform_ModResPopup_Submit(){
		btnSubmit.highlight(driver);
		btnSubmit.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		btnPopUpYes.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		Sleeper.sleep(3000);
		btnClose.highlight(driver);
		btnClose.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		pageLoaded(eleRefresh);
		eleRefresh.highlight(driver);
		eleRefresh.jsClick(driver);
	}
	
	public void EnterResortAndRoomtype(){
		rdioResortAndRoom.jsClick(driver);
	}

	public void verifyResortAndRoom(){
		EnterResortAndRoomtype();
		new Select(driver.findElement(By.id("modResResortFormId:resortNameId"))).selectByVisibleText("Disney's Beach Club Villas");
		perform_ModResPopup_Submit();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		System.out.println(txtResortName.getText());
		TestReporter.assertTrue(txtResortName.getText().contains("Disney's Beach Club Villas"),"Resort is not Modified as Expected!!");
	}
	/**
	 * 
	 * @summary Created the below Method to perform cancel reservation
	 * @version Created 12/03/2015
	 * @author  Lalitha Banda
	 * @param   NA
	 * @return  NA
	 * 
	 */
	
		
	public void CancelReservation(){
		new Select(driver.findElement(By.id("cancelResForm:reasons"))).selectByVisibleText("Air not available");
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		txtReqBY.safeSet("Lalitha");
		chkWaives.click();
		btnCancelRes.highlight(driver);
		btnCancelRes.click();
		pageLoaded(eleRefresh);
		eleRefresh.highlight(driver);
		eleRefresh.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);	
	}
	
	/**
	 * 
	 * @summary Created the below Method to verify cancel reservation
	 * @version Created 12/04/2015
	 * @author  Lalitha Banda
	 * @param   NA
	 * @return  NA
	 * 
	 */
	
	public void verify_CancelReservation(){
		pageLoaded(txtConfMsg);
		txtConfMsg.highlight(driver);
		TestReporter.assertTrue(txtConfMsg.getText().equalsIgnoreCase("Cancelled"), "Reservation is Not Canceled");
		TestReporter.logStep("Reservation Cancelled Successfully");
	}
	
	
	/**
	 * 
	 * @summary Created the below Method to Select room Typer in Modify reservation Popup
	 * @version Created 12/17/2015
	 * @author  Lalitha Banda
	 * @param   NA
	 * @return  NA
	 * 
	 */
	
	public void select_RoomType(){
		 pageLoaded(lstRoomType);
		 Select sel = new Select(lstRoomType);	 
		 WebElement eleBefore = sel.getFirstSelectedOption();
		 lstRoomType.highlight(driver);
		 TestReporter.log( "Room Type Before : "+eleBefore.getText());
		 sel.selectByVisibleText("2 Bedroom Villa");
		 lstRoomType.highlight(driver);
		 WebElement eleAfter = sel.getFirstSelectedOption();
		 TestReporter.log( "Room Type After : "+eleAfter.getText());
		}
	

}



