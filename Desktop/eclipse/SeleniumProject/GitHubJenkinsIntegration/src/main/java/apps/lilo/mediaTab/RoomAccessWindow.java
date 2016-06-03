package apps.lilo.mediaTab;

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
import core.interfaces.Webtable;
import core.interfaces.impl.CheckboxImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.TestReporter;

/**
* @summary Contains the elements and methods to interact with the LILO UI Media tab
* @version Created 11/10/2014
* @author Waightstill W. Avery
*/

public class RoomAccessWindow {
	//**************************
	//*** Room Access Fields ***
	//**************************
	private int loopCounter;
	private Datatable datatable = new Datatable();
	private WebDriver driver;
	
	//****************************
	//*** Room Access Elements ***
	//****************************		
	//Room Access Submit button
	@FindBy(id = "accessForm:selectId")
	private Button btnSubmit;
	
	//Room Access Cancel button
	@FindBy(id = "accessForm:close")
	private Button btnCancel;
	
	//Room Access webtable rows
	@FindBy(xpath = "//div[@class='tbl-container']/table/tbody/tr")
	private List<WebElement> eleRoomAccess;
	
	//Room Access webtable rows
	@FindBy(xpath = "//div[@class='tbl-container']/table[contains(@id,'accessForm')]")
	private Webtable tblRoomAccess;
	
	//Created object for clicking on submit button while adding the Day guest user
	@FindBy(id="newMediaEncodeForm:kttwDetails:1:addGuest")
	private Button btnSubmitForAddDayGuest;
	
	//*********************
    //** Build page area **
    //*********************
    /**
    * 
    * @summary Constructor to initialize the page
    * @version Created 11/10/2014
    * @author Waightstill W Avery
    * @param  driver
    * @throws NA
    * @return NA
    * 
    */
	public RoomAccessWindow(WebDriver driver){
		this.driver = driver;	
		ElementFactory.initElements(driver, this); 
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH); 
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnSubmit);
	}
	
	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}
	
	public RoomAccessWindow initialize() {
		return ElementFactory.initElements(driver,
				this.getClass());
	}
		     
    //***************************************
  	//*** Room Access Window Interactions ***
  	//***************************************
    /**
    * 
    * @summary Method to select all options that will grant the guest access to an additional floor
    * @version Created 11/10/2014
    * @author Waightstill W Avery
    * @param  NA
    * @throws NA
    * @return String array of floors to which access has been added
    * 
    */
	public String[] selectAllFloorsForAccess(){
		String[] accessIds = new String[10];
		int accessIdCounter = 0;
		
		for(loopCounter = 1; loopCounter < eleRoomAccess.size(); loopCounter++){
			if(eleRoomAccess.get(loopCounter).getText().toLowerCase().contains("floor")){
				String[] accessIdArray = eleRoomAccess.get(loopCounter).getText().split(" ");
				accessIds[accessIdCounter] = accessIdArray[0].replaceAll("th|rd|nd", "");
				accessIdCounter++;
				Checkbox checkbox = new CheckboxImpl(eleRoomAccess.get(loopCounter).findElement(By.tagName("input")));
				checkbox.toggle();
			}
		}
		return accessIds;
	}

    /**
    * 
    * @summary Method to select all floors and add them to the guest access
    * @version Created 11/10/2014
    * @author Waightstill W Avery
    * @param  NA
    * @throws NA
    * @return String array of floors to which access has been added 
    * 
    */
	public String[] addAllFloorsForAccess(){
		String[] accessIds = selectAllFloorsForAccess();
		clickSubmit();
		
		return accessIds;
	}

    /**
    * 
    * @summary Method to select a specific floor to which to add guest access
    * @version Created 11/10/2014
    * @author Waightstill W Avery
    * @param  floor - floor to add guest access
    * @throws NA
    * @return String array of floors to which access has been added 
    * 
    */
	public boolean addFloorAccess(String floor){
		boolean accessFound = selectAFloorForAccess(floor);
		clickSubmit();
		
		return accessFound;
	}
	
	private void clickSubmit(){
		btnSubmit.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

    /**
    * 
    * @summary Method to select  particular floor to which to add guest access
    * @version Created 11/10/2014
    * @author Waightstill W Avery
    * @param  NA
    * @throws NA
    * @return true if access is found, false otherwise 
    * 
    */
	public boolean selectAFloorForAccess(String floor){
		boolean accessFound = false;
		
		for(loopCounter = 1; loopCounter < eleRoomAccess.size(); loopCounter++){
			if(eleRoomAccess.get(loopCounter).getText().toLowerCase().contains("floor")){
				if(eleRoomAccess.get(loopCounter).getText().toLowerCase().contains(floor)){
					Checkbox checkbox = new CheckboxImpl(eleRoomAccess.get(loopCounter).findElement(By.tagName("input")));
					checkbox.toggle();
					accessFound = true;
				}
			}
		}
		
		return accessFound;
	}
	
	/**
	 * 
	 * @summary Method to check the check-boxes in Room Access details window and submit the room access
	 * 			for guest user.
	 * @version Created 11/12/2015
	 * @author  Praveen Namburi
	 * @param   NA
	 * @throws  NA
	 * @return  NA 
	 * 
	 */
	public void checkSecondRoomAccessAndSubmit(){
		pageLoaded(tblRoomAccess);
		for(int row = 2 ; row <= tblRoomAccess.getRowCount() ; row++){
			new CheckboxImpl(tblRoomAccess.getCell(driver, row, 1).findElement(By.tagName("input"))).check();
		}
		
		btnSubmit.click();
		
	}	
	
	/**
	 * @summary: Method to click on button Submit.
	 * @author : Praven Namburi
	 * @version: Created 11/09/2015
	 */
	public void clickButtonSubmit(){
		pageLoaded(btnSubmit);
		btnSubmit.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	/**
	 * @summary : Method to provide the room access for the day guest user.
	 * @author  : Praveen Namburi.
	 * @version : Created 12-16-2015
	 * @return  : NA
	 */
	public void giveRoomAccessForDayGuest(){
		pageLoaded(tblRoomAccess);
		for(int row = 2 ; row <= tblRoomAccess.getRowCount() ; row++){
			new CheckboxImpl(tblRoomAccess.getCell(driver, row, 1).findElement(By.tagName("input"))).check();
		}
		
		
		TestReporter.logStep("Clicking on Submit button in RoomAccess window.");
		btnSubmit.click();
					
	}
	
	/**
	 * @summary : Method to click on submit button while adding the day guest user.
	 * @author  : Praveen Namburi.
	 * @version : Created 12-16-2015
	 */
	public void clickSubmitBtnforDayGuest(){
		initialize();
		pageLoaded(btnSubmitForAddDayGuest);
		btnSubmitForAddDayGuest.syncVisible(driver);
		btnSubmitForAddDayGuest.highlight(driver);
		btnSubmitForAddDayGuest.jsClick(driver);
		
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
}

		
		
	
	



