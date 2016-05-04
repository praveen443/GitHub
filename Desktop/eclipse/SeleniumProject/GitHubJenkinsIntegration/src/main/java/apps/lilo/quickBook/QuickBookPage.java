package apps.lilo.quickBook;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.WebDriverSetup;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import selenium.common.Constants;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;
import utils.date.DateTimeConversion;


/**
 * @summary Contains the page methods & objects to enter QuickBook information
 * @version Created 8/15/2014
 * @author Justin Phlegar
 */
public class QuickBookPage {
	//**********************************
	//*** Main QuickBook Page Fields ***
	//**********************************
	private int loopCount = 0;
	private int timeout = WebDriverSetup.getDefaultTestTimeout();
//	private JSONObject json_accommodation;
//	private JSONObject json_partymixinfo;
//	private JSONArray  json_childage;
	private Datatable datatable = new Datatable();
	private WebDriver driver;
//	private DataProvider_ExcelSheet datapro = new DataProvider_ExcelSheet();

	//************************************
	//*** Main QuickBook Page Elements ***
	//************************************
	//Create Textbox object for Guest First Name 
	@FindBy(id = "quickBookForm:firstNameId")
	private Textbox txtFirstName;	

	//Create Textbox object for Guest Last Name 
	@FindBy(id = "quickBookForm:lastNameId")
	private Textbox txtLastName;	

	//Create Listbox object for Booking Resort
	@FindBy(id = "quickBookForm:resortListId")
	private Listbox lstResort;	

	//Create Textbox object for single child Age
	@FindBy(id = "quickBookForm:childList1:0:childList2:0:childListTextId")
	private Textbox txtChildAge;	

	//Create Listbox object for Resort Room Type
	@FindBy(id = "quickBookForm:roomTypeNameId")
	private Listbox lstRoomType;	

	//Create Textbox object for Arrival Date 
	@FindBy(id = "quickBookForm:arrivalDateButtonInput")
	private Textbox txtArrivalDate;	

	//Create Textbox object for Departure Date Name 
	@FindBy(id = "quickBookForm:departureDateButtonInput")
	private Textbox txtDepartureDate;	

	//Create Textbox object for Number of Nights 
	@FindBy(id = "quickBookForm:nightsId")
	private Textbox txtNumberNights;	

	//Create Textbox object for Number of Adults
	@FindBy(id = "quickBookForm:noOfAdultsId")
	private Textbox txtNumberAdults;	

	//Create Textbox object for Number of Children
	@FindBy(id = "quickBookForm:noOfNonAdultsId")
	private Textbox txtNumberChildren;	

	//Create Textbox object for Number of Rooms
	@FindBy(id = "quickBookForm:noOfRoomsId")
	private Textbox txtNumberRooms;	

	//Create Textbox object for GroupCode 
	@FindBy(id = "quickBookForm:groupCodeID")
	private Textbox txtGroupCode;	

	//Create button object for Group Code Search Button
	@FindBy(id = "quickBookForm:searchGroupButton")
	private Button btnSearchGroup;	

	//Create Textbox object for Group Name 
	@FindBy(id = "quickBookForm:searchGroupButton")
	private Textbox txtGroupName;	

	//Create Button object for Clear Form 
	@FindBy(id = "quickBookForm:clearButton")
	private Button btnClearForm;

	//Create Button object for Quick Book 
	@FindBy(id = "quickBookForm:quickBookButtonId")
	private Button btnQuickbook;	

	//Create Button object for Cancel Quick Book 
	@FindBy(id = "quickBookForm:closeButtonId")
	private Button btnCancelQuickbook;	

	//Create the error pop up for Negative testing leaving out First Name
	@FindBy(id = "PMSRErrorModalPanelContentDiv")
	private Element eleFirstNameErrorScreen;

	//Create the Error Text for Negative testing leaving out First Name
	@FindBy(xpath = ".//*[@id='errorForm']/table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td/table/tbody/tr/td[2]/div/ul")
	private Element eleErrorMessage;

	/*//Create the OK button for Negative testing leaving out FirstName
    @FindBy(id = "errorForm:okButtonId")
    private Button btnErrorOKButton;*/

	// error pop up Ok
	@FindBy(id = "errorForm:okButtonId")
	private Button btnErrorPopupOk;

	//Error popup
	@FindBy(id = "PMSRErrorModalPanelContentDiv")
	private Element eleError;

	@FindBy(className="scrollableContentXY")
	private Element eleError_1;
	
	@FindBy(id="quickBookForm:groupCodeName") private Textbox txtGroupNameCode;


	//*********************
	//** Build page area **
	//*********************
	/**
	 * 
	 * @summary Constructor to initialize the page
	 * @version Created 8/15/2014
	 * @author Justin Phlegar
	 * @param  driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public QuickBookPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public  void initialize() {
		//	return ElementFactory.initElements(driver, QuickBookPage.class);	        
	}

	public boolean pageLoaded(){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, txtFirstName); 
	}    

	public boolean pageLoaded(Element element){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element); 
	}  

	//****************************************    
	//*** Main QuickBook Page Interactions ***
	//****************************************

	/**
	 * 
	 * @summary Method to enter accommodation information into the quick book window
	 * @version Created 8/15/2014
	 * @author Justin Phlegar
	 * @param  driver
	 * @throws Exception if datatable scenarios or parameters are not found
	 * @return NA
	 * 
	 */

	private void enterAccommodationInfo(String scenario) {
		String arrivalDate = "";
		datatable.setVirtualtablePage("EnterQuickBookAccommodationInfo");
		datatable.setVirtualtableScenario(scenario);

		/*initialize();
		pageLoaded(lstResort);*/
		lstResort.select(datatable.getDataParameter("Resort"));
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		Sleeper.sleep(500);

		initialize();
		pageLoaded(lstRoomType);
		lstRoomType.select(datatable.getDataParameter("RoomType"));
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		//DateTimeConversion convertDate = new DateTimeConversion();
		//arrivalDate = convertDate.ConvertToDate(datatable.getDataParameter("DaysOut"));
		arrivalDate = DateTimeConversion.ConvertToDate(datatable.getDataParameter("DaysOut"));
		txtArrivalDate.safeSet(arrivalDate);
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		//Putting a hard wait here as occasionally it still doesn't enter the number of nights
		Sleeper.sleep(1000);
		txtNumberNights.safeSet(datatable.getDataParameter("NumberNights"));	
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		Sleeper.sleep(1000);
		txtNumberRooms.set(datatable.getDataParameter("NumberRooms"));
	}
	public void enterAccommodationInfo_xl(int row) throws Exception {
		/*String arrivalDate = "";
		DataProvider_ExcelSheet datapro = new DataProvider_ExcelSheet();


		lstResort.select(datapro.getCellContent(row,8).trim());
		new ProcessingYourRequest().WaitForProcessRequest(driver);


		lstRoomType.select(datapro.getCellContent(row,9).trim());
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		arrivalDate = DateTimeConversion.ConvertToDate(datapro.getCellContent(row,10).trim());
		pageLoaded(txtArrivalDate);
		txtArrivalDate.safeSet(arrivalDate);
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		pageLoaded(txtNumberNights);

		txtNumberNights.safeSet(datapro.getCellContent(row,11).trim());	
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		pageLoaded(txtNumberRooms);
		txtNumberRooms.set(datapro.getCellContent(row,12).trim());*/
	}

//	public void enterAccommodationInfo_json(JSONObject myjson) throws Exception {
//		String arrivalDate = "";
//
//		json_accommodation = myjson.getJSONObject("accommodationInfo");
//		lstResort.select(json_accommodation.getString("resort_name"));
//		new ProcessingYourRequest().WaitForProcessRequest(driver);
//
//
//		lstRoomType.select(json_accommodation.getString("resort_room"));
//		new ProcessingYourRequest().WaitForProcessRequest(driver);
//
//		arrivalDate = DateTimeConversion.ConvertToDate(json_accommodation.getString("arrival_date"));
//		pageLoaded(txtArrivalDate);
//		txtArrivalDate.safeSet(arrivalDate);
//		new ProcessingYourRequest().WaitForProcessRequest(driver);
//
//		pageLoaded(txtNumberNights);
//
//		txtNumberNights.safeSet(json_accommodation.getString("number_nights"));	
//		new ProcessingYourRequest().WaitForProcessRequest(driver);
//		pageLoaded(txtNumberRooms);
//
//		txtNumberRooms.set(json_accommodation.getString("number_rooms"));	
//
//	}



	/**
	 * 
	 * @summary Method to enter party mix information into the quick book window
	 * @version Created 5/18/2015
	 * @author Brajesh Ahirwar
	 * @param  driver
	 * @throws Exception if datatable scenarios or parameters are not found
	 * @return NA
	 * 
	 */
	private void enterPartyMixInfo(String scenario) {	
		datatable.setVirtualtablePage("EnterQuickBookPartyMixInfo");
		datatable.setVirtualtableScenario(scenario);

		txtFirstName.set(datatable.getDataParameter("FirstName"));
		txtLastName.set(datatable.getDataParameter("LastName"));

		txtNumberAdults.set(datatable.getDataParameter("NumberAdults"));
		txtNumberChildren.safeSet(datatable.getDataParameter("NumberChildren"));	
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

//	private void enterPartyMixInfo_xl(int row) throws Exception{	
//
//		txtFirstName.set(datapro.getCellContent(row,13).trim());
//		txtLastName.set(datapro.getCellContent(row,14).trim());
//		txtNumberAdults.set(datapro.getCellContent(row,15).trim());
//		//txtNumberChildren.safeSet(datapro.getCellContent(row,16).trim());
//		if(!datapro.getCellContent(row,16).trim().isEmpty() ){
//			if(Integer.parseInt(datapro.getCellContent(row,16).trim()) > 0){
//				enterChildAges();
//			}
//		}
//
//		new ProcessingYourRequest().WaitForProcessRequest(driver);
//	}

	public void enterChildAges()
	{


		pageLoaded(txtChildAge);
		txtChildAge.set("12");
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	/**
	 * 
	 * @summary Method to enter party mix information into the quick book window
	 * @version Created 8/15/2014
	 * @author Justin Phlegar
	 * @param  driver
	 * @throws Exception if datatable scenarios or parameters are not found
	 * @return NA
	 * 
	 */
	private void enterGroupInfo(String scenario) throws Exception{	
		datatable.setVirtualtablePage("EnterQuickBookGroupInfo");
		datatable.setVirtualtableScenario(scenario);

		txtGroupCode.set(datatable.getDataParameter("GroupNumber"));
		btnSearchGroup.jsClick(driver);
		loopCount = 0;
		do{
			Sleeper.sleep(1000);
			Assert.assertNotEquals(loopCount++, timeout, "The group name was not populated after ["+String.valueOf(timeout)+"] seconds.");
		}while(txtGroupName.getAttribute("value").isEmpty());
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

//	private void enterPartyMixInfo_json(JSONObject myjson) throws Exception{	
//
//		json_partymixinfo = myjson.getJSONObject("partymixinfo");
//		int ages = json_partymixinfo.getInt("number_child");
//		String numberofChildren = String.valueOf(json_partymixinfo.getInt("number_child"));
//		txtFirstName.set(json_partymixinfo.getString("first_name"));
//		txtLastName.set(json_partymixinfo.getString("last_name"));
//		txtNumberAdults.set(json_partymixinfo.getString("number_adult"));
//
//		txtNumberChildren.safeSet(numberofChildren);
//		pageLoaded(txtChildAge);
//		initialize();
//		if(ages> 0)
//		{
//			for(int i=1;i<=ages;i++)
//			{
//				pageLoaded(txtChildAge);
//				initialize();
//				Element element = new ElementImpl(driver.findElement(By.xpath("//div[@id='quickBookForm:childTxtBox']/input["+i+"]")));
//				json_childage = json_partymixinfo.getJSONArray("child_age");
//				String childage =String.valueOf(json_childage.getJSONObject(i-1).getInt("age"));
//				element.sendKeys(String.valueOf(childage));
//				pageLoaded(txtChildAge);
//				new ProcessingYourRequest().WaitForProcessRequest(driver);
//			}	
//
//
//		}
//		new ProcessingYourRequest().WaitForProcessRequest(driver);
//	}

//	public void enterChildAges_json(int numberofchild,int row ) throws Exception
//	{
//		for(int i=1;i<=numberofchild;i++)
//		{
//			pageLoaded(txtChildAge);
//			initialize();
//			Element element = new ElementImpl(driver.findElement(By.xpath("//div[@id='quickBookForm:childTxtBox']/input["+i+"]")));
//			element.sendKeys(datapro.getCellContent(row,16+i).trim());
//			pageLoaded(txtChildAge);
//			new ProcessingYourRequest().WaitForProcessRequest(driver);
//		}
//
//
//	}

	/**
	 * 
	 * @summary Method that calls methods that enter accommodation and party mix info into the quick book window
	 * @version Created 8/15/2014
	 * @author Justin Phlegar
	 * @param  driver
	 * @throws Exception if datatable scenarios or parameters are not found
	 * @return NA
	 * 
	 */
	public void enterQuickBookInfo(String strAccommodationInfo, String strPartyMixInfo) {
		enterAccommodationInfo(strAccommodationInfo);
		enterPartyMixInfo(strPartyMixInfo);
	}

//	public void enterQuickBookInfo_xl(int row) throws Exception{
//		enterAccommodationInfo_xl(row);
//		enterPartyMixInfo_xl(row);
//	}

	/**
	 * 
	 * @summary Method that calls methods that enter accommodation, party mix and group info into the quick book window
	 * @version Created 12/03/2014
	 * @author Waightstill W Avery
	 * @param  strAccommodationInfo - accommodation scenario
	 * @param  strPartyMixInfo - party mix scenario
	 * @param  strGroupInfo - group info scenario
	 * @throws Exception if datatable scenarios or parameters are not found
	 * @return NA
	 * 
	 */
	public void enterQuickBookInfo(String strAccommodationInfo, String strPartyMixInfo, String strGroupInfo) throws Exception{
		enterAccommodationInfo(strAccommodationInfo);
		enterPartyMixInfo(strPartyMixInfo);
		enterGroupInfo(strGroupInfo);
	}

//	public void enterQuickBookInfo_json(JSONObject myjson ) throws Exception
//	{
//		enterAccommodationInfo_json(myjson);
//		enterPartyMixInfo_json(myjson);
//	}

	/**
	 * 
	 * @summary Method that calls methods to enter accommodation info, party mix info and child age into the quick book window
	 * 		    and creates a new Quickbook.
	 * @version Created 11/6/2015
	 * @author  Praveen Namburi
	 * @param   driver
	 * @throws  Exception if datatable scenarios or parameters are not found
	 * @return  NA
	 * 
	 */
	public void enterQuickBookData(String strAccommodationInfo, String strPartyMixInfo) {
		enterAccommodationInfo(strAccommodationInfo);
		enterPartyMixInfo(strPartyMixInfo);

		//Calling method inside the another method for providing the child age
		enterChildAges();

		//Click on QuickBook button.    	   	
		TestReporter.logStep("Click on Quickbook button.");
		clickOnQuickbookButton();
	}

	public void clickQuickbookButton(){
		initialize();
		pageLoaded(btnQuickbook);
		btnQuickbook.syncVisible(driver);
		btnQuickbook.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver, 60);	
		checkForBookingErrors();
	}

	private void checkForBookingErrors(){
		boolean noErrors = true;
		if(eleError.syncVisible(driver, 2, false)) noErrors = false;
		TestReporter.assertTrue(noErrors, "An error occurred while booking a Quickbook res.  Error text: ["+eleError.getText()+"].");
	}

	public void clickCancelQuickbookButton(){
		btnCancelQuickbook.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickClearFormButton(){
		btnClearForm.click();
	}

	public void clickOnQuickbookButton(){
		pageLoaded(btnQuickbook);
		btnQuickbook.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);				
	}

	/**
	 * 
	 * @summary Gets the guest full name from the datatable
	 * @version Created 11/30/2015
	 * @author Marella Satish
	 * @param  NA
	 * @throws NA
	 * @return String as Guest Full Name
	 * 
	 */
	public String getGuestFullName(String scenario){
		datatable.setVirtualtablePage("EnterQuickBookPartyMixInfo");
		datatable.setVirtualtableScenario(scenario);

		return datatable.getDataParameter("FirstName").trim()+ " " + datatable.getDataParameter("LastName").trim();
	}

	/**
	 * @summary : Created method to select the roomtype value from the quickbook page.
	 * @author  : Praveen Namburi
	 * @version : Created 12/17/2015
	 */
	public void selectRoomType(String roomType){
		pageLoaded(lstRoomType);
		new Select(lstRoomType).selectByVisibleText(roomType);
	}

	/**
	 * @summary: Created this method to select the roomtype based on the param value
	 * 			 and then to enter the quickbook info. 
				 (Used this method in the test - Lilo_NGE_Flow_20_ModifyRes_AddDayGuest )
	 * @param  : AccommodationInfo,PartyMixInfo,roomType
	 * @throws : Exception
	 */
	public void selectRoomTypeAndsetQuickBookInfo(String AccommodationInfo,String PartyMixInfo,
			String roomType) {

		enterAccommodationInfo(AccommodationInfo);    	
		selectRoomType(roomType);
		enterPartyMixInfo(PartyMixInfo);
	}

	/* *//**
	 * 
	 * @summary Method to negative test leaving out the First Name
	 * @version Created 12/14/2015
	 * @author Dennis Stagliano
	 * @param  scenario
	 * @return 
	 * @throws Exception if datatable scenarios or parameters are not found
	 *//*
	public String negativeTestFirstName(String scenario){
		datatable.setVirtualtablePage("QuickBookFirstNameNegativeTest");
		datatable.setVirtualtableScenario(scenario);

		initialize();

		//Enter the Last Name
		txtLastName.safeSet(datatable.getDataParameter("LastName"));
		Sleeper.sleep(1000);
		//Select the resort
	    new Select(lstResort).selectByVisibleText(datatable.getDataParameter("SelectResort"));
	    Sleeper.sleep(1000);
	    //Select the Room Type
	    new Select(lstRoomType).selectByVisibleText(datatable.getDataParameter("SelectRoomType"));
	    Sleeper.sleep(1000);
	    //Click on the QuickBook Button
	    clickOnQuickbookButton();
	    Sleeper.sleep(1000);
	    String errorMessage =  eleErrorMessage.getText();

		return errorMessage;
	}

}
	  */




	/**
	 * 
	 * @summary method to handle error message
	 * @version Created 02/24/2016
	 * @author Sowmya Ch
	 * @param  NA
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void negativeTestFirstName(String errorMesssage,String Scenario){
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
		datatable.setVirtualtablePage("QuickBookFirstNameNegativeTest");
		datatable.setVirtualtableScenario(Scenario);

		String NumberAdults = datatable.getDataParameter("NumAdults");
		String NumberChildren = datatable.getDataParameter("NumChildren");
		String NumberRooms = datatable.getDataParameter("NumRooms");
		String ChildrenAges = datatable.getDataParameter("ChildAge");
		String NumberNights = datatable.getDataParameter("NumNights");
		String FirstNameInput = datatable.getDataParameter("Firstname");
		String LastNameInput = datatable.getDataParameter("Lastname");


		switch(errorMesssage.trim()){
		case "First Name is a required field":
			LastName(LastNameInput);
			clickOnQuickbookButton();
			TestReporter.logStep("Displaying error Message :" +eleErrorMessage.getText());
			TestReporter.assertTrue(errorMesssage.contains( eleErrorMessage.getText()), "Specified error message is not available as Expected!!");
			btnErrorPopupOk.highlight(driver);
			btnErrorPopupOk.click();
			break;
		case "Last Name is a required field":
			FirstName(FirstNameInput);
			clickOnQuickbookButton();
			TestReporter.logStep("Displaying error Message :" +eleErrorMessage.getText());
			TestReporter.assertTrue(errorMesssage.contains( eleErrorMessage.getText()), "Specified error message is not available as Expected!!");   
			btnErrorPopupOk.highlight(driver);
			btnErrorPopupOk.click();
			break;
		case "Nights is a required field":
			FirstName(FirstNameInput);
			LastName(LastNameInput);
			txtNumberNights.clear();
			clickOnQuickbookButton();
			TestReporter.assertTrue(errorMesssage.contains( eleErrorMessage.getText()), "Specified error message is not available as Expected!!");
			TestReporter.logStep("Displaying error Message "+eleErrorMessage.getText());
			btnErrorPopupOk.highlight(driver);
			btnErrorPopupOk.click();
			break;

		case "Rooms is a required field":
			FirstName(FirstNameInput);
			LastName(LastNameInput);
			numberNights(NumberNights);
			numberAdults(NumberAdults);
			txtNumberRooms.clear();
			clickOnQuickbookButton();
			eleErrorMessage.syncVisible(driver);
			String errmsg1 = eleErrorMessage.getText();
			TestReporter.logStep("Iteration - 12 errormsg : "+errmsg1);
			TestReporter.assertTrue(errmsg1.contains(errorMesssage), "Specified error message is not available as Expected!!");
			TestReporter.logStep("Displaying error Message :" +eleErrorMessage.getText());
			btnErrorPopupOk.highlight(driver);
			btnErrorPopupOk.click();
			break;
		case "Child Age is a required field":
			FirstName(FirstNameInput);
			LastName(LastNameInput);
			numberNights(NumberNights);
			numberAdults(NumberAdults);
			numberChildren(NumberChildren);
			txtChildAge.clear();
			clickOnQuickbookButton();
			TestReporter.assertTrue(errorMesssage.contains( eleErrorMessage.getText()), "Specified error message is not available as Expected!!");
			TestReporter.logStep("Displaying error Message :" +eleErrorMessage.getText());
			btnErrorPopupOk.highlight(driver);
			btnErrorPopupOk.click();
			break;

		case "You can not book a reservation for past date less than one day from today":
			FirstName(FirstNameInput);
			LastName(LastNameInput);
			setPastArrivalDate();
			clickOnQuickbookButton();
			TestReporter.logStep("Displaying error Message :" +eleErrorMessage.getText());
			boolean status=eleErrorMessage.getText().contains(errorMesssage); 
			TestReporter.assertTrue(status, "Specified error message is not available as Expected!!");
			btnErrorPopupOk.highlight(driver);
			btnErrorPopupOk.click();
			break;

		case "can not be before arrival date":
			FirstName(FirstNameInput);
			LastName(LastNameInput);
			setPastArrivalDate();
			setDeptDate(); 
			clickOnQuickbookButton();
			TestReporter.logStep("Displaying error Message :" +eleErrorMessage.getText());
			TestReporter.assertTrue(eleErrorMessage.getText().contains(errorMesssage), "Specified error message is not available as Expected!!");
			btnErrorPopupOk.highlight(driver);
			btnErrorPopupOk.click();
			break;

		case "LENGTH OF STAY ERROR.THE REQUESTED NUMBER OF NIGHTS  IS MORE THAN THE MAXIMUM LOS":
			pageLoaded();
			FirstName(FirstNameInput);
			LastName(LastNameInput);
			numberNights(NumberNights);
			numberAdults(NumberAdults);
			clickOnQuickbookButton();
			TestReporter.assertTrue(eleErrorMessage.getText().contains("Length of Stay error"), "Specified error message is not available as Expected!!");
			TestReporter.logStep("Displaying error Message :" +eleErrorMessage.getText());
			btnErrorPopupOk.highlight(driver);
			btnErrorPopupOk.click();
			break;


		case "Adults is a required field":
			FirstName(FirstNameInput);
			LastName(LastNameInput);
			numberNights(NumberNights);
			numberAdults(NumberAdults);
			txtNumberAdults.clear();
			setPastArrivalDate();
			setDeptDate();
			clickOnQuickbookButton();
			btnErrorPopupOk.highlight(driver);
			btnErrorPopupOk.click();
			TestReporter.assertTrue(errorMesssage.contains( eleErrorMessage.getText()), "Specified error message is not available as Expected!!");
			TestReporter.logStep("Displaying error Message :" +eleErrorMessage.getText());

			break;

		case "Unexpected Error occurred":
			FirstName(FirstNameInput);
			LastName(LastNameInput);
			numberNights(NumberNights);
			txtNumberAdults.clear();
			clickOnQuickbookButton();
			TestReporter.assertTrue(eleErrorMessage.getText().contains(errorMesssage), "Specified error message is not available as Expected!!");
			TestReporter.logStep("Displaying error Message :" +eleErrorMessage.getText());
			btnErrorPopupOk.highlight(driver);
			btnErrorPopupOk.click();
			break;

		case "Nights is numeric value":
			FirstName(FirstNameInput);
			LastName(LastNameInput);
			numberNights(NumberNights);
			clickOnQuickbookButton();
			TestReporter.assertTrue(eleErrorMessage.getText().contains(errorMesssage), "Specified error message is not available as Expected!!");
			TestReporter.logStep("Displaying error Message :" +eleErrorMessage.getText());
			btnErrorPopupOk.highlight(driver);
			btnErrorPopupOk.click();
			break;

		case "Number of room(s) is greater than Adult(s)":
			FirstName(FirstNameInput);
			LastName(LastNameInput);
			numberNights(NumberNights);
			numberAdults(NumberAdults);
			numberRooms(NumberRooms);
			clickOnQuickbookButton();
			TestReporter.assertTrue(eleErrorMessage.getText().contains(errorMesssage), "Specified error message is not available as Expected!!");
			TestReporter.logStep("Displaying error Message :" +eleErrorMessage.getText());
			btnErrorPopupOk.highlight(driver);
			btnErrorPopupOk.click();
			break;

		case "Children Age cannot be Greater than 17":
			FirstName(FirstNameInput);
			LastName(LastNameInput);
			numberNights(NumberNights);
			numberAdults(NumberAdults);
			numberChildren(NumberChildren);
			numberRooms(NumberRooms);
			childAge(ChildrenAges);
			clickOnQuickbookButton();
			TestReporter.assertTrue(eleErrorMessage.getText().contains(errorMesssage), "Specified error message is not available as Expected!!");
			TestReporter.logStep("Displaying error Message :" +eleErrorMessage.getText());
			btnErrorPopupOk.highlight(driver);
			btnErrorPopupOk.click();
			break;

		case "is an incorrect value, please enter a numeric value in this field":
			if(Scenario.contains("SR9")){
				numberNights(NumberNights);
				numberAdults(NumberAdults);
				FirstName(FirstNameInput);
				LastName(LastNameInput);
				TestReporter.assertTrue(eleErrorMessage.getText().contains(errorMesssage), "Specified error message is not available as Expected!!");
				TestReporter.logStep("Displaying error Message :" +eleErrorMessage.getText());
			}else if(Scenario.contains("SR12")){
				numberNights(NumberNights);
				numberAdults(NumberAdults);
				FirstName(FirstNameInput);
				LastName(LastNameInput);
				numberChildren(NumberChildren);
				eleErrorMessage.syncVisible(driver);
				String errmsg = eleErrorMessage.getText();
				TestReporter.logStep("Iteration - 12 errormsg : "+errmsg);
				TestReporter.assertTrue(errorMesssage.contains(errmsg), "Specified error message is not available as Expected!!");
				TestReporter.logStep("Displaying error Message :" +eleErrorMessage.getText());

			}else if(Scenario.contains("SR13")){
				numberNights(NumberNights);
				numberAdults(NumberAdults);
				FirstName(FirstNameInput);
				LastName(LastNameInput);
				numberChildren(NumberChildren);		
				childAge(ChildrenAges);
				TestReporter.assertTrue(errorMesssage.contains( eleErrorMessage.getText()), "Specified error message is not available as Expected!!");
				TestReporter.logStep("Displaying error Message :" +eleErrorMessage.getText());

			}else{
				numberNights(NumberNights);
				numberAdults(NumberAdults);
				numberRooms(NumberRooms);
				FirstName(FirstNameInput);
				LastName(LastNameInput);	
				TestReporter.assertTrue(errorMesssage.contains( eleErrorMessage.getText()), "Specified error message is not available as Expected!!");
				TestReporter.logStep("Displaying error Message :" +eleErrorMessage.getText());

			}
			clickOnQuickbookButton();
			break;
		default:break;
		}

	}

	public void FirstName(String FirstName){
		txtFirstName.safeSet(FirstName);	
	}
	public void LastName(String LastName){
		txtLastName.safeSet(LastName);
	}

	public void numberAdults(String NumberAdults){
		txtNumberAdults.safeSet(NumberAdults);
	}
	public void numberChildren(String NumberChildren){
		txtNumberChildren.safeSet(NumberChildren);
	}
	public void childAge(String ChildAge){
		txtChildAge.safeSet(ChildAge);
	}

	public void numberNights(String NumberNights){
		txtNumberNights.safeSet(NumberNights);
	}
	public void numberRooms(String NumberRooms){
		txtNumberRooms.safeSet(NumberRooms);
	}

	public void setPastArrivalDate() {

		DateTimeConversion convertDate = new DateTimeConversion();
		String arrivalDate = convertDate.getYearsOut("-2", "MM/dd/yyyy");
		TestReporter.log("Invalid Arrival Date" + arrivalDate);
		txtArrivalDate.safeSet(arrivalDate);

	}

	public  static String setPastDeptDate() {

		String deptDate = DateTimeConversion.getDaysOut("-5", "MM/dd/yyyy");
		TestReporter.log("Dept Date " + deptDate);
		return deptDate;

	}

	public void setDeptDate(){
		txtDepartureDate.highlight(driver);
		txtDepartureDate.safeSet(setPastDeptDate());

	}

	public void enterGroupNumber(String environment, String groupType){
//		datatable.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);
//		datatable.setVirtualtablePage("GroupNumbers");
//		datatable.setVirtualtableScenario(scenario + " " + environment);
//		
//		String groupNumber = datatable.getDataParameter("GroupNumber");

//		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	/*	
		GroupNumbers group = new GroupNumbers(environment, groupType);
		String groupNumber = group.getGroupNumber();
		
		txtGroupCode.syncVisible(driver);
		txtGroupCode.safeSet(groupNumber);
		
		boolean groupNameEmpty = false;
		int counter = 0;
		do{
			try{
				groupNameEmpty = txtGroupNameCode.getText().isEmpty();
			}catch(Exception e){
				Sleeper.sleep(1000);
				counter++;
			}
		}while(counter < Constants.ELEMENT_TIMEOUT && groupNameEmpty);*/
	}
}
