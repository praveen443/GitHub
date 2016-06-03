package apps.lilo.viewRoomAvailablility;

import java.util.List;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Link;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.CheckboxImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

/**
 * This class contains elements and element interactions for a given page of a
 * web application
 * 
 * @author Waightstill W Avery
 * @version 10/27/2015 Waightstill W Avery - original
 */
public class ViewRoomAvailabilityPage {
	// *******************
	// Page Class Fields
	// *******************
	/*
	 * Class fields go here (int, Strings, List<>, etc)
	 */
	// Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	// *********************
	// Page Class Elements
	// *********************
	//Search Criteria link
	@FindBy(id = "roomTableForm:roomSearchFilterId")
	private Textbox lnkSearchCriteria;
	
	//Room Search webtable
	@FindBy(id = "roomSearchPopupIdContentTable")
	private Webtable tblRoomSearch;
	
	//Room Status List webtable
	@FindBy(id = "roomSearchPopupForm:roomStatusListId")
	private Webtable tblRoomStatusList;
	
	//Clean/Vacant checkbox
	@FindBy(id = "roomSearchPopupForm:roomStatusListId:0:roomStatusAttribChkBoxId")
	private Checkbox chkCleanVacant;
	
	//Inpected/Vacant checkbox
	@FindBy(id = "roomSearchPopupForm:roomStatusListId:14:roomStatusAttribChkBoxId")
	private Checkbox chkInspectedVacant;
	
	//Chow Rooms button
	@FindBy(id = "roomSearchPopupForm:findRoomButtonId1")
	private Button btnShowRooms;
	
	//Room Table IDs webtable
	@FindBy(id = "roomTableForm:roomTableId:tb")
	private Webtable tblRoomTableIDsBody;	
	
	//Exit button 
	@FindBy(id = "j_id162:resortsExitButton")
	private Link lnkExit;
	
	//Grab the RoomType value after searching for the room number 
	@FindBy(id="roomTableForm:mainRoomDetailsDescId")
	private Element eleGetRoomTypeValue;

	/**
	 * Page objects  for  Link Room availability 
	 * 
	 */

	/*//click on link search criteria : Room availability page
	@FindBy(id = "roomTableForm:roomSearchFilterId")
	private Link lnkSearchcriteria;

	//go select check box clean occupied
	@FindBy(id = "roomSearchPopupForm:roomStatusListId:0:roomStatusAttribChkBoxId")
	private Link chkCleanOccupied;

	//go select check box Inspected ,vacant
	@FindBy(id = "roomSearchPopupForm:roomStatusListId:15:roomStatusAttribChkBoxId")
	private Link chkInspectedVacant;
*/
	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Waightstill W Avery
	 * @version 10/27/2015 Waightstill W Avery - original
	 * @param driver
	 */
	public ViewRoomAvailabilityPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Waightstill W Avery
	 * @version 10/27/2015 Waightstill W Avery - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public ViewRoomAvailabilityPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 10/27/2015 Waightstill W Avery - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, lnkSearchCriteria);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 10/27/2015 Waightstill W Avery - original
	 * @param element
	 *            - Element to be used to determine if the page is loaded
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	// ********************
	// Page Class Methods
	// ********************
	
	/**
	 * This method clicks the Search Criteria link and waits for the Room Search table to be displayed.
	 * @author Waightstill W Avery - 10/28/2015 - original
	 */
	private void clickSearchCriteria(){
		Sleeper.sleep(5000);
		initialize();
		pageLoaded(lnkSearchCriteria);
		lnkSearchCriteria.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		pageLoaded(tblRoomSearch);
		tblRoomSearch.syncVisible(driver);
		initialize();
	}
	
	/**
	 * This method iterates through all checkboxes in the Room Status list table and checks the boxes that are indicated by the String array argument
	 * @param values - array of strings corresponding to the checkbox values as the appear in the application
	 * @author Waightstill W Avery - 10/28/2015 - original
	 */
	private void selectRoomSearchStatusByValue(String[] values){
		List<WebElement> checkboxes = tblRoomStatusList.findElements(By.xpath("descendant::td/input[@type='checkbox']"));
		List<WebElement> checkboxValues = tblRoomStatusList.findElements(By.xpath("descendant::td/span[@class='generalLabel']"));
		int loopCounter = 0;
		try{
			for(WebElement checkbox : checkboxes){
				System.out.println(checkboxValues.get(loopCounter).getText());
				for(String value : values){
					if(checkboxValues.get(loopCounter).getText().equalsIgnoreCase(value)){
						new CheckboxImpl(checkbox).scrollIntoView(driver);
						TestReporter.log("Toggling on the checkbox that contains the text ["+ value +"].");
						new CheckboxImpl(checkbox).toggle();
					}
				}
				loopCounter++;
			}
		}catch(Exception e){
			
		}
	}
	
	/**
	 * This method clicks the Show Rooms button to generate the room search results
	 * @author Waightstill W Avery - 10/28/2015 - original
	 */
	private void clickShowRooms(){
		btnShowRooms.highlight(driver);
		btnShowRooms.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	/**
	 * This method uses a user-defined search criteria to determine if any rooms are available that meet the search criteria.  If no rooms are found, the test will fail.
	 * @param status - array of strings corresponding to the checkbox values as the appear in the application
	 * @author Waightstill W Avery - 10/28/2015 - original
	 */
//	public void searchForAvailableRoomsByRoomStatus(String[] status){
	public String searchForAvailableRoomsByRoomStatus(String scenario){
		String roomNumber = "";
		
		datatable.setVirtualtablePage("ViewRoomAvailability");
		datatable.setVirtualtableScenario(scenario);
		
		String searchCriteria = datatable.getDataParameter("RoomSearchCriteria");
		String returnRoomNumber = datatable.getDataParameter("SelectRoomNumber");
		String[] searchCriteriaArray = searchCriteria.split(";");
		
		clickSearchCriteria();
		selectRoomSearchStatusByValue(searchCriteriaArray);
		clickShowRooms();
		TestReporter.assertTrue(ensureRoomsAreDisplayed(), "No rooms were found to be available for the search criteria ["+searchCriteria+"].");
		
		if(returnRoomNumber.equalsIgnoreCase("true")){
			roomNumber = getAvailableRoomNumber();
		}
		
		return roomNumber;
	}
	
	/**
	 * This method determines if any rooms are displayed by grabbing and testing the number of rows in the table
	 * @return boolean true if rooms are found, false otherwise
	 * @author Waightstill W Avery - 10/28/2015 - original
	 */
	private boolean ensureRoomsAreDisplayed(){
		boolean roomsDisplayed = false;
		initialize();
		pageLoaded(tblRoomTableIDsBody);
		if(tblRoomTableIDsBody.getRowCount() >= 1){
			roomsDisplayed = true;
		}
		TestReporter.log("Number of rooms avaiable on the first page: " + tblRoomTableIDsBody.getRowCount());
		
		return roomsDisplayed;
	}
	
	private String getAvailableRoomNumber(){
		String roomNumber = driver.findElement(By.id("roomTableForm:roomTableId:0:roomNumberId")).getText();
		TestReporter.log("Grabbing room number ["+roomNumber+"] for future use");
		return roomNumber;
	}
	
	/**
	 * @summary : Method to click on Exit button.
	 * @version : Created 11/28/2015
	 * @author  : Praveen Namburi
	 * @return  : NA
	 */
	public void clickbtnExit(){	
		pageLoaded(lnkExit);
		lnkExit.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	/**
	 * @summary : Calling method in method to get the room id from view Room Availability page
	 * @author  : Praveen Namburi
	 * @version : Created 11/30/2015
	 * @return  : String
	 */
	public String getAvailableRoomID(){
		
		return getAvailableRoomNumber();
         
	}
	
	/**
	 * @summary : Created this method to capture the roomtype text for future use.
	 * @author  : Praveen Namburi
	 * @return  : String RoomType value 
	 * @version : Created 12/16/2015
	 */
	public String getAvailableRoomtype(){
		pageLoaded(eleGetRoomTypeValue);
		eleGetRoomTypeValue.syncVisible(driver);
		eleGetRoomTypeValue.highlight(driver);
		String grabRoomTypeValue = eleGetRoomTypeValue.getText();
		
		return grabRoomTypeValue;
	}
	
	
	/**
	 * 
	 * @summary Method to verify availability of rooms
	 * @version Created 12/15/2015
	 * @author Lalitha Banda
	 * @param NA
	 * @throws NA
	 * @return NA
	 * 
	 */	
	
	public void verifyRoomAvailability(){
		String[] options = new String[]{"CLEAN, VACANT","INSPECTED, VACANT"};
		clickSearchCriteria();
		selectRoomSearchStatusByValue(options);
		clickShowRooms();
		boolean getRoomavailStatus = ensureRoomsAreDisplayed();
		TestReporter.assertTrue(getRoomavailStatus, "Specified Search having no rooms as available!!");

	}
}

