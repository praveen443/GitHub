package apps.lilo.housekeeping;

import java.util.List;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;

/**
 * 
 * @summary Contains the page methods & objects for the Manage Sections page
 * @version Created 10/21/2014
 * @author Jessica Marshall
 */
public class ManageSectionsPage {
	//
	// Page Fields
	//
	private Datatable datatable = new Datatable();
	private WebDriver driver;
	
	// *************************************
	// *** Page Elements ***
	// *************************************

	// Print
	@FindBy(id = "manageSections:print")
	private Button btnPrint;
	
	// Save
	@FindBy(id = "manageSections:save")
	private Button btnSave;
	
	// Close
	@FindBy(id = "manageSections:close")
	private Button btnClose;
	
	// Create
	@FindBy(id = "manageSections:create")
	private Button btnCreate;
	
	// Resize
	@FindBy(id = "manageSections:resize")
	private Button btnResize;
	
	// Section size textbox
	@FindBy(id = "manageSections:j_id85")
	private Textbox txtSectionSize;
	
	// Sections summary table
	@FindBy(id = "manageSections:sectionsDatatable")
	private Webtable tblSectionSummary;
	
	// Sections detail table
	@FindBy(id = "manageSections:sectionsDetailDatatable")
	private Webtable tblSectionDetail;
	
	// Confirm continue save (discrepancy pop up)
	@FindBy(id = "confirmModalPanelCDiv")
	private Element eleContinueSave;
	
	// Confirm continue save yes
	//@FindBy(name = "j_id123:j_id127")
	@FindBy(xpath="//table[@class='panelBackgroundModalPanel']/tbody/tr/td/input[@type='submit'][@value='Yes']")
	private Button btnContinueSaveYes;
	
	// Confirm continue save cancel
	//@FindBy(id = "j_id123:j_id129")
	@FindBy(xpath="//table[@class='panelBackgroundModalPanel']/tbody/tr/td/input[@type='submit'][@value='Cancel']")
	private Button btnContinueSaveCancel;
	
	// Saved successful pop up close
	//@FindBy(id = "j_id224:j_id230")
	@FindBy(xpath="//table[@class='panelBackgroundModalPanel']/tbody/tr/td/input[contains(@onclick,'saveSuccessfulModalPanel')]")
	private Button btnSavedClose;
	
	// Saved successful pop up
	@FindBy(id = "saveSuccessfulModalPanelCDiv")
	private Element eleSavedPopUp;
	
	// Error pop up
	@FindBy(id = "houskeepingErrorModalPanel1CDiv")
	private Element eleErrorPopUp;

	// *********************
	// ** Build page area **
	// *********************
	
	/**
	 * @summary Constructor to initialize the page
	 * @version Created 9/11/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public ManageSectionsPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Constructor to initialize the page
	 * @version Created 9/11/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public ManageSectionsPage initialize() {
		return ElementFactory.initElements(driver,
				ManageSectionsPage.class);
	}

	/**
	 * @summary Constructor to initialize the page
	 * @version Created 9/11/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public boolean loadPage() {
		return new PageLoaded().isPageHTMLLoaded(ManageSectionsPage.class, driver, btnPrint); 
	}
	
	// *****************************************
	// ***Page Interactions ***
	// *****************************************
	
	public void clickPrintButton() {

		btnPrint.syncVisible(driver);
		btnPrint.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickSave(){
		btnSave.syncVisible(driver);
		btnSave.focus(driver);
		btnSave.click();
		//((JavascriptExecutor)driver).executeScript("document.getElementById('manageSections:save').click");

		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickClose(){
		btnClose.syncVisible(driver);
		btnClose.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickCreate(){
		btnCreate.syncVisible(driver);
		btnCreate.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickResize(){
		btnResize.syncVisible(driver);
		btnResize.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickSavedPopupClose(){
		btnSavedClose.syncVisible(driver);
		btnSavedClose.focusClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickContinueSaveYes(){
		btnContinueSaveYes.syncVisible(driver);
		btnContinueSaveYes.focus(driver);
		btnContinueSaveYes.click();
		//((JavascriptExecutor)driver).executeScript("document.getElementById('j_id123:j_id127').click");
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		Sleeper.sleep(2000); //have to put this here
	}
	
	/**
	 * @summary Returns whether the section name value (textbox value) based on the 
	 * 			section number.  The table is ordered by section number so they are 
	 * 			basically row numbers, and they start at 1
	 * @version Created 10/21/2014
	 * @author 	Jessica Marshall
	 * @param 	sectionNum
	 * @throws 	NA
	 * @return 	the section name value
	 */
	public String getSectionName(int sectionNumber){
		
		//Get a list of all the rows
		List<WebElement> rowList = tblSectionSummary.findElements(By.tagName("tr"));
		
		//get a list of all the textboxes for that row,  the section name is the first textbox
		List<WebElement> inputList = rowList.get(sectionNumber-1).findElements(By.tagName("input"));
		
		//return the value of the first textbox
		return inputList.get(0).getAttribute("value"); 
		
		
	}
	
	/**
	 * @summary updates the section name value.  based on the 
	 * 			section number.  The table is ordered by section number so they are 
	 * 			basically row numbers, and they start at 1  
	 * @version Created 10/21/2014
	 * @author 	Jessica Marshall
	 * @param 	sectionNum, sectionName
	 * @throws 	NA
	 * @return 	NA
	 */
	public void updateSectionName(int sectionNumber, String sectionName){

		//Get a list of all the rows
		List<WebElement> rowList = tblSectionSummary.findElements(By.tagName("tr"));
		
		//get a list of all the textboxes for that row,  the section name is the first textbox
		List<WebElement> inputList = rowList.get(sectionNumber-1).findElements(By.tagName("input"));
		
		//update the section name textbox
		inputList.get(0).clear();
		inputList.get(0).sendKeys(sectionName);
		
	}
	
	/**
	 * @summary Handles the saved successful pop up, syncs and closes it
	 * @version Created 10/21/2014
	 * @author 	Jessica Marshall
	 * @param 	NA
	 * @throws 	NA
	 * @return 	True if saved successful pop up displays, false if not
	 */
	public boolean handleSavedSuccessfulPopUp(){
		Sleeper.sleep(1000);
		loadPage();
		initialize();
		boolean found = btnSavedClose.syncVisible(driver);
		clickSavedPopupClose();
		return found;
	}
	
	/**
	 * @summary Handles the continue to save pop up, syncs & clicks the yes button
	 * @version Created 10/21/2014
	 * @author 	Jessica Marshall
	 * @param 	NA
	 * @throws 	NA
	 * @return 	NA
	 */
	public void handleContinueSavePopUp(){
		boolean found;
		found = eleContinueSave.syncVisible(driver, 3, false);
		if (found){
			Sleeper.sleep(1000);
			clickContinueSaveYes();
		}
	}
	
	
	/**
	 * @summary Returns the max late checkout value (textbox value) based on the 
	 * 			section number.  The table is ordered by section number so they are 
	 * 			basically row numbers, and they start at 1
	 * @version Created 10/21/2014
	 * @author 	Jessica Marshall
	 * @param 	sectionNum
	 * @throws 	NA
	 * @return 	the max late checkout value
	 */
	public int getMaxLateCheckoutValue(int sectionNumber){
		loadPage();
		tblSectionSummary.syncVisible(driver);
		
		//Get a list of all the rows
		List<WebElement> rowList = tblSectionSummary.findElements(By.tagName("tr"));
		
		//get a list of all the textboxes for that row,  the section name is the first textbox
		List<WebElement> inputList = rowList.get(sectionNumber-1).findElements(By.tagName("input"));
		
		//return the value of the last textbox
		return Integer.parseInt(inputList.get(2).getAttribute("value")); 
		
		
	}
	
	/**
	 * @summary updates the max late checkout value.  based on the 
	 * 			section number.  The table is ordered by section number so they are 
	 * 			basically row numbers, and they start at 1  
	 * @version Created 10/21/2014
	 * @author 	Jessica Marshall
	 * @param 	sectionNum, maxValue
	 * @throws 	NA
	 * @return 	NA
	 */
	public void updateMaxLateCheckoutValue(int sectionNumber, int maxValue){
		loadPage();
		tblSectionSummary.syncVisible(driver);
		//Get a list of all the rows
		List<WebElement> rowList = tblSectionSummary.findElements(By.tagName("tr"));
		
		//get a list of all the textboxes for that row,  the section name is the first textbox
		List<WebElement> inputList = rowList.get(sectionNumber-1).findElements(By.tagName("input"));
		
		//update the section name textbox
		inputList.get(2).clear();
		inputList.get(2).sendKeys(String.valueOf(maxValue));
		clickCreate();
		
	}
	
	
	/**
	 * @summary verifies that the section detail table displays when you click on
	 * 			a row in the section summary table
	 * @version Created 10/21/2014
	 * @author 	Jessica Marshall
	 * @param 	sectionNum, maxValue
	 * @throws 	NA
	 * @return 	NA
	 */
	public boolean verifySectionDetailsDisplays(int sectionNumber){
		
		tblSectionSummary.syncVisible(driver);
		
		//Get a list of all the rows
		List<WebElement> rowList = tblSectionSummary.findElements(By.tagName("tr"));
		
		//Get a list of all the cells and click the first one
		List<WebElement> cellList = rowList.get(sectionNumber-1).findElements(By.tagName("td"));
		cellList.get(0).click();
		
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		loadPage();
		
		return tblSectionDetail.syncVisible(driver, 10, false);
		
		
	}
	
	public void updateSectionSize(int sectionSize){
		
		txtSectionSize.syncVisible(driver);
		//txtSectionSize.highlight(driver);
		txtSectionSize.focus(driver);
		//txtSectionSize.set(String.valueOf(sectionSize));
		//txtSectionSize.sendKeys(String.valueOf(sectionSize));
		((JavascriptExecutor)driver).executeScript("document.getElementById('manageSections:j_id85').setAttribute('value', " + String.valueOf(sectionSize) + " )");
		clickCreate();
		
	}
	
	public boolean isErrorDisplayed(){
		return eleErrorPopUp.syncVisible(driver, 3, false);
	}

	

}

