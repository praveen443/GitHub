package apps.lilo.createReservation;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

/**
 * @author Dennis Stagliano
 * @Summary This class allows you to input the reservation information
 * 			Resort, Resort Type, Dates, Guests and Rooms and Package Information
 * @version 11/23/2015 Dennis Stagliano - original
 * @version xx/xx/xxxx SecondContributorNameHere -Brief summary of changes here
 * 
 */
 
public class CreateReservationCreateResortCriteria {
	//
	// Page fields
	//
	private int KeyRow = 0;	
	private boolean found = false;
	private int row_num = 0;
	private String ResortName = "";
	private Datatable datatable = new Datatable();
	
	//
	// Page Elements
	//
	//Clear Button
	@FindBy(id = "createResCriteriaForm:clearButton")
	private Button btnClearCriteria;
	
	//Accessible Room 
	@FindBy(id = "createResCriteriaForm:accessibleCheckBoxId")
	private Checkbox chkAccessibleRoomChkbox;
	
	//Resort Name 
	@FindBy(id = "createResCriteriaForm:singleSelectResortName")
	private Textbox txtSingleResortTxtBox;
	
	//Resort Type
	@FindBy(id = "createResCriteriaForm:singleSelectRoomTypeInput")
	private Textbox txtSingleResortRoomTypeTxtBox;
	
	//Arrival Date
	@FindBy(id = "createResCriteriaForm:arrivalDateButtonInput")
	private Textbox txtArrivalDate;
	
	//Departure Date
	@FindBy(id = "createResCriteriaForm:departureDateButtonInput")
	private Textbox txtDepartureDate;
	
	//Number Of Nights
	@FindBy(id = "createResCriteriaForm:noOfNightsInput")
	private Textbox txtNumberOfNights;
	
	//Number of Adults
	@FindBy(id = "createResCriteriaForm:searchCritNoOfAdults")
	private Textbox txtNumberOfAdults;
	
	//Number of Children
	@FindBy(id = "createResCriteriaForm:searchCritNoOfChildren")
	private Textbox txtNumberOfChildren;
	
	//Child Ages
	@FindBy(id = "createResCriteriaForm:childAges:0:childAgeInput")
	private Textbox txtChildAge;
	
	//Number of Rooms
	@FindBy(id = "createResCriteriaForm:noOfRoomsInput")
	private Textbox txtNumberOfRooms;
	
	//Affiliations
	@FindBy(id = "createResCriteriaForm:affiliationsSelect")
	private Listbox lstAffiliations;
	
	//Group Name
	@FindBy(id = "createResCriteriaForm:createResgroupCodeName")
	private Textbox txtGroupNameTxtBox;
	
	//Search Group Button
	@FindBy(id = "createResCriteriaForm:searchGroupButton")
	private Button btnSearchGroupBtn;
	
	//Group Code Text Box
	@FindBy(id = "createResCriteriaForm:createResgroupCodeID")
	private Textbox txtGroupCodeTxtBox;
	
	//Rsr Checkbox
	@FindBy(id = "createResCriteriaForm:rsrCheckBox")
	private Checkbox chkRSRChkBox;
	
	//Resorts popup
	@FindBy(id = "createResCriteriaForm:displayResortsPopupButton")
	private Button btnDisplayResortsPopup;
	
	//Cancel button
	@FindBy(id = "defaultRHSPanelForm:cancelButton")
	private Button btnCancel;
	
	//Submit button
	@FindBy(id = "selectResortsForm:selectResortSubmit")
	private Button btnSubmit;
	
	//Availability Button
	@FindBy(id = "defaultRHSPanelForm:searchOfferButton")
	private Button btnAvailability;
	
	//Create Res Search Button
	@FindBy(id = "createResGuestSearchForm:createGuestButton")
	private Button btnCreateResGuestSearch;
	
	//*********************
	//** Build page area **
	//*********************
	//Declare a local WebDriver
	private WebDriver driver;
		
	/**
	* @summary Constructor to initialize the page
	* @version Created 11/22/2014
	* @author  Dennis Stagliano
	* @param   driver
	* @throws  NA
	* @return  NA
	*/	
	public CreateReservationCreateResortCriteria(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}
	
	public CreateReservationCreateResortCriteria initialize() {
		return ElementFactory.initElements(driver, CreateReservationCreateResortCriteria.class);	        
	 }
	
	public boolean pageLoaded(){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnClearCriteria); 
	}    
	
	public boolean pageLoaded(Element element){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element); 
	}  
	
	//*********************************************************
	// *** Create Resort Criteria Page Interactions ***
	// *********************************************************
	
	/**
	 * @summary This method indicates the datatable to select
	 * @version Created 11/24/2014
	 * @author  Dennis Stagliano
	 * @param   scenario
	 * @throws  NA
	 * @return  NA
	 */
	public void ResortCriteriaDataSetup(String scenario){	
		datatable.setVirtualtablePage("CreateReservation_ResortCriteriaSetup");
		datatable.setVirtualtableScenario(scenario);
		Sleeper.sleep(1000);
	}
	
	/**
	 * @summary This method clears the resort name and resort type fields
	 * @version Created 11/22/2014
	 * @author  Dennis Stagliano
	 * @param   NA
	 * @throws  NA
	 * @return  NA
	 */
	public void clearResortNameAndType(){
		btnClearCriteria.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
			//Verify that the fields are clear
		initialize();
		    if(txtSingleResortTxtBox.getText().isEmpty() == false){
		    	TestReporter.logStep("Field was not cleared");
		    }
		    if(txtSingleResortRoomTypeTxtBox.getText().isEmpty() == false){
		    	TestReporter.logStep("Resort Type Field was not cleared");
		    }
	}
	
	/**
	 * @summary This method populates the Resort Criteria form
	 * @version Created 11/24/2014
	 * @author  Dennis Stagliano
	 * @param   NA
	 * @throws  NA
	 * @return  NA
	 */
	public void populateResortCriteriaForm(){	
		txtSingleResortTxtBox.safeSet(datatable.getDataParameter("ResortName"));
		txtSingleResortRoomTypeTxtBox.safeSet(datatable.getDataParameter("ResortType"));
		txtArrivalDate.safeSet(datatable.getDataParameter("ArrivalDt"));
		txtDepartureDate.safeSet(datatable.getDataParameter("DepartureDt"));
		txtNumberOfNights.safeSet(datatable.getDataParameter("NumNights"));
		txtNumberOfAdults.safeSet(datatable.getDataParameter("NumAdults"));
		txtNumberOfRooms.safeSet(datatable.getDataParameter("NumRooms"));
		txtNumberOfChildren.safeSet(datatable.getDataParameter("NumChildren"));
		
		
		//.//*[@id='createResCriteriaForm:childAges:0:childAgeInput']
		String AgeList[] = datatable.getDataParameter("ChildAges").split(";");
		int size = AgeList.length;
		System.out.println("The number of children listed is -  " + size);
			if(size > 1){
				for(int i=0; i<size; i++){
					String AgeXpath = ".//*[@id='createResCriteriaForm:childAges:"+i+":childAgeInput']";
					WebElement inputAge = driver.findElement(By.xpath(AgeXpath));
					inputAge.sendKeys(AgeList[i]);
				}
			}		
	}
		
	/**
	 * @summary This method clicks on the display resorts pop up button which is a button with only an arrow
	 * @version Created 11/24/2014
	 * @author  Dennis Stagliano
	 * @param   NA
	 * @throws  NA
	 * @return  NA
	 */
	public void clickToDisplayResortsPopup(){
		//Click to activate Display Resorts Popup screen to select a new resorty
		initialize();
		btnDisplayResortsPopup.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);	
	}
	
	/**
	 * @summary This method clicks the Cancel Button
	 * @version Created 11/24/2014
	 * @author  Dennis Stagliano
	 * @param   NA
	 * @throws  NA
	 * @return  NA
	 */
	//Clicks the Cancel Button
	public void clickCancel(){
		initialize();
		btnCancel.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	/**
	 * @summary This method checks for the resort in the window to add a resort
	 * 			and searches for the resort name retrieved from the VT.  Once it finds
	 * 			it, it clicks on the add button
	 * @version Created 11/27/2014
	 * @author  Dennis Stagliano
	 * @param   Resort Selection scenario name
	 * @throws  NA
	 * @return  NA
	 */
	public void selectResortByName(String ResortSelection){	
		//System.out.println("ResortSelection = " + ResortSelection);
		initialize();
		//pull from Virtual Table
		ResortName = datatable.getDataParameter("ResortName");
		Sleeper.sleep(2000);
	    System.out.println("The Resort Name from Virtual table = " + ResortName);
		
		//Identify the table
		WebElement SelectionTable = driver.findElement(By.id("selectResortsForm:availableResortsDataTable:tb"));
		
		//Get the table rows in a collection
		List<WebElement> trcollection = SelectionTable.findElements(By.tagName("tr"));
		//System.out.println("Number of rows in this table = " + trcollection.size());	
		//loop through each <td> in a row of the table using trElement and the trcollection in the 
		//enhanced loop below looking for the target ResortName.
		for(WebElement trElement : trcollection)
		{
			List<WebElement> tdcollection = trElement.findElements(By.tagName("td"));
			//System.out.println("Number of columns = " + tdcollection.size(+1));		
			//Set the column number
			for(WebElement tdElement : tdcollection)
			{
				//System.out.println("row # " + row_num+ ", col # " +col_num+ " text = " + tdElement.getText());
				String resort = tdElement.getText();
				if(resort.matches(ResortName)){
					System.out.println("FOUND target resort");
					//The KeyRow determines what row the input tag we need to extract the id from.  This will give us
					//the id to capture for the add button.
					KeyRow = row_num;
					//System.out.println("key row - " + KeyRow);
					found = true;			
					break;							
				}//end if
			}
			if(found == true){
				break;
				}
		row_num++;
		}	
		//Create a collection for all of the input tags in the original table
		List<WebElement> inputs = SelectionTable.findElements(By.tagName("input"));
		System.out.println("Number of inputs tags found is - " + inputs.size());
		//Go directly to the row where the resort was found and click the add button
		for(@SuppressWarnings("unused") WebElement inputid : inputs){			
			String addButtonid = inputs.get(KeyRow).getAttribute("id");
			//click on add button
			//System.out.println("id = " + addButtonid);
			WebElement addButton = driver.findElement(By.id(addButtonid));		
			WebDriverWait wait = new WebDriverWait(driver, 6);
			WebElement addResort = wait.until(ExpectedConditions
					 .elementToBeClickable(addButton));
			addResort.click();	
			break;
		}					
	}
	
	/**
	 * @summary This method clicks the Submit Button
	 * @version Created 11/24/2014
	 * @author  Dennis Stagliano
	 * @param   NA
	 * @throws  NA
	 * @return  NA
	 */
	public void clkSubmitToAddResort(){
		initialize();
		btnSubmit.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	/**
	 * @summary This method clicks the Find Availability Button
	 * @version Created 11/24/2014
	 * @author  Dennis Stagliano
	 * @param   NA
	 * @throws  NA
	 * @return  NA
	 */
	public void clkAvailabilityButton(){
		initialize();
		btnAvailability.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);	
	}
	
	/**
	 * @summary This method clicks the Create New Button on the Resort Availability screen
	 * @version Created 11/30/2014
	 * @author  Dennis Stagliano
	 * @param   NA
	 * @throws  NA
	 * @return  NA
	 */
	public void clkCreateResGuestSearchForm(){
		initialize();
		btnCreateResGuestSearch.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	

}//end class	
