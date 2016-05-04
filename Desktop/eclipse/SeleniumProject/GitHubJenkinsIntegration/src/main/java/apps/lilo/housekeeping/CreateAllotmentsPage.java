package apps.lilo.housekeeping;

import java.util.List;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Webtable;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;

/**
 * 
 * @summary Contains the page methods & objects for the Create allotments page
 * @version Created 10/16/2014
 * @author Jessica Marshall
 */
public class CreateAllotmentsPage {
	//
	// Page Fields
	//
	private Datatable datatable = new Datatable();
	private WebDriver driver;
	
	// *************************************
	// *** Page Elements ***
	// *************************************	

	// Add status button
	@FindBy(id = "manageAllotments:addStatus")
	private Button btnAddStatus;
	
	// save button
	@FindBy(id = "manageAllotments:save")
	private Button btnSave;
	
	// close button
	@FindBy(id = "manageAllotments:close")
	private Button btnClose;
	
	// Room type allotments tab
	@FindBy(id = "manageAllotments:roomTypeTab_lbl")
	private Element eleRoomAllotTab;
	
	// Room attribute increments tab
	@FindBy(id = "manageAllotments:roomAllotmentTab_lbl")
	private Element eleRoomAttributeTab;
	
	// Room type allotments table
	@FindBy(id = "manageAllotments:roomAllDatatable")
	private Webtable tblAllotmentTable;
	
	// Add room attribute
	@FindBy(id = "manageAllotments:Add")
	private Button btnAddAttribute;
	
	// Delete room attribute
	@FindBy(id = "manageAllotments:delete")
	private Button btnDeleteAttribute;

	// Room attributes table
	@FindBy(id = "manageAllotments:roomAttributeAllotmentDataTable")
	private Webtable tblRoomAttributes;
	
	// Select room attribute ok button
	@FindBy(id = "roomAttributeNameForm:ok")
	private Button btnSelectAttributeOk;
	
	// Select room attribute close button
	@FindBy(id = "roomAttributeNameForm:close")
	private Button btnSelectAttributeClose;
	
	// TAble of radio buttons for room type attribute
	@FindBy(id = "roomAttributeNameForm:roomAttributeSelection")
	private Webtable tblRadioAttributes;
	
	// Popup that confirms whether you want to continue
	@FindBy(id = "confirmSectionchangeModalPanelContainer")
	private Element eleConfirmContinue;
	
	// Pop up confirm continue yes button
	@FindBy(id = "j_id96:j_id99:j_id105")
	private Button btnConfirmContinueYes;
	
	// Pop up confirm continue no button
	@FindBy(id = "j_id96:j_id99:j_id107")
	private Button btnConfirmContinueNo;
	
	// Popup that displays saved successfully
	@FindBy(id = "saveSuccessfulModalPanelContainer")
	private Element eleSavedPopUp;
	
	// Saved pop up close 
	@FindBy(id = "j_id202:j_id208")
	private Button btnSavedPopUpClose;
	
	// Table for the header of the table for the room attributes tab
	@FindBy(id = "manageAllotments:roomAttributeAllotmentHeaderDataTable")
	private Element tblHeaderTable;
	
	// Table for the header of the table for the allotments tab
	@FindBy(id = "manageAllotments:roomAllHeaderDatatable")
	private Element tblAllotHeaderTable;
	
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
	public CreateAllotmentsPage(WebDriver driver) {
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
	public CreateAllotmentsPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
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
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnSave); 
	}
	
	// *****************************************
	// ***Page Interactions ***
	// *****************************************
	

	

	public void clickSave(){
		btnSave.syncVisible(driver);
		btnSave.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickClose(){
		btnClose.syncVisible(driver);
		btnClose.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickAddStatus(){
		btnAddStatus.syncVisible(driver);
		btnAddStatus.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickRoomAllotmentTab(){
		eleRoomAllotTab.syncVisible(driver);
		eleRoomAllotTab.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickRoomAttributeTab(){
		initialize();
		eleRoomAttributeTab.syncVisible(driver);
		eleRoomAttributeTab.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickAddAttribute(){
		btnAddAttribute.syncVisible(driver);
		btnAddAttribute.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickDeleteAttribute(){
		btnDeleteAttribute.syncVisible(driver);
		btnDeleteAttribute.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickSelectAttributeOk(){
		btnSelectAttributeOk.syncVisible(driver);
		btnSelectAttributeOk.click();
		btnSelectAttributeOk.syncHidden(driver);
	}
	
	public void clickSelectAttributeClose(){
		btnSelectAttributeClose.syncVisible(driver);
		btnSelectAttributeClose.click();
		btnSelectAttributeClose.syncHidden(driver);
	}
	
	public boolean isConfirmContinuePopUpDisplayed(){
		return eleConfirmContinue.isDisplayed();
	}
	
	public void clickConfirmContinueYes(){
		btnConfirmContinueYes.syncVisible(driver);
		btnConfirmContinueYes.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		btnConfirmContinueYes.syncHidden(driver);
	}
	
	public void clickConfirmContinueNo(){
		btnConfirmContinueNo.syncVisible(driver);
		btnConfirmContinueNo.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		btnConfirmContinueNo.syncHidden(driver);
	}
	
	public void clickSavedPopUpClose(){
		btnSavedPopUpClose.syncVisible(driver);
		btnSavedPopUpClose.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		btnSavedPopUpClose.syncHidden(driver);
	}
	
	/**
	 * @summary Deletes the specified room attribute
	 * @version Created 10/16/2014
	 * @author 	Jessica Marshall
	 * @param 	attribute
	 * @throws 	NA
	 * @return 	false if the attribute is not found in the table, otherwise true
	 */
	public boolean deleteRoomAttribute(String attribute){
		initialize();
		int rowNum = 0;
		//Get the row that has the attribute name
		rowNum = tblRoomAttributes.getRowWithCellText(driver, attribute, 1);
		
		//return false if row not found
		if (rowNum == 0)
			return false;
			
		//Click the row
		tblRoomAttributes.clickCell(driver, rowNum, 1);

		clickDeleteAttribute();
		
		return true;
					
		}
		
	
	/**
	 * @summary Returns whether the specified attribute is listed in the table
	 * @version Created 10/16/2014
	 * @author 	Jessica Marshall
	 * @param 	Datatable scenario
	 * @throws 	NA
	 * @return 	false if the attribute is not found in the table, otherwise true
	 */
	public boolean verifyRoomAttributeDisplayed(String attribute){
		
		if (tblRoomAttributes.getRowWithCellText(driver, attribute, 1)==0)
			return false;
		else
			return true;
	}
	
	/**
	 * @summary Adds a room type attribute based on the parameters from the datatable,
	 * 			if the room type specified is not found in the add room attribute options,
	 * 			then returns a false, otherwise returns a true
	 * @version Created 10/16/2014
	 * @author 	Jessica Marshall
	 * @param 	attribute
	 * @throws 	NA
	 * @return 	true if passed, false if failed
	 */
	public boolean addRoomTypeAttribute(String scenario){
		/*datatable.setDatatableSheet("AddRoomTypeAttribute");
		datatable.setDatatableRow(datatable.getRowContains(scenario, 0));*/
		datatable.setVirtualtablePage("AddRoomTypeAttribute");
		datatable.setVirtualtableScenario(scenario);
		boolean found = false;
		clickAddAttribute();
		
		//wait for pop up to display
		btnSelectAttributeOk.syncVisible(driver);
		Sleeper.sleep(1000);
		
		//click the radio button for the attribute
		//Get a list of all the radio elements
		List<WebElement> radioList = driver.findElements(By.name("roomAttributeNameForm:roomAttributeSelection"));
		//Go through the list and find the one that has a value that matches the attribute
		for (WebElement radio:radioList){
			if (radio.getAttribute("value").equalsIgnoreCase(datatable.getDataParameter("RoomType"))){
				//click the radio button
				
				((JavascriptExecutor)driver).executeScript("document.getElementById('" + radio.getAttribute("id") + "').click()");

				//radio.click();
				found = true;
				break;
			}
			
		}
		
		//if no radio option was found, then stop and return
		if (!found)
			return false;
		
		clickSelectAttributeOk();
		
		return true;
	}
	
	/**
	 * @summary Updates an existing room type attribute values, based on the room type, 
	 * 			and the attribute column you want to update listed in the datatable.  Returns
	 * 			false if either the column attribute specified is not found, or if the attribute
	 * 			row is not found
	 * @version Created 10/17/2014
	 * @author 	Jessica Marshall
	 * @param 	Datatable scenario
	 * @throws 	NA
	 * @return 	false if the attribute/room type is not found in the table, otherwise true
	 */
	public boolean updateRoomAttributeIncrements(String scenario){
		initialize();
		/*datatable.setDatatableSheet("UpdateRoomAttributeIncrements");
		datatable.setDatatableRow(datatable.getRowContains(scenario, 0));*/
		datatable.setVirtualtablePage("UpdateRoomAttributeIncrements");
		datatable.setVirtualtableScenario(scenario);
		
		int colNum = 0;
		String roomType = datatable.getDataParameter("RoomType");
		String roomAttribute = datatable.getDataParameter("RoomAttribute");
		String incrementAmt = datatable.getDataParameter("IncrementAmount");
		
		boolean found = false;
		
		//Find the column number for the column you want to update
		//Get a list of all the column headings
																			
		List<WebElement> headerList = tblHeaderTable.findElements(By.tagName("th"));
		for(WebElement header:headerList){
			if (header.getText().equalsIgnoreCase(roomAttribute)) {
				colNum = headerList.indexOf(header);
				
				found = true;
				break;
			}		
		}
		
		//If the column was not found, then return false	
		if (!found)
			return false;
		
		//Get a list of all the table rows
		List<WebElement> rowList = tblRoomAttributes.findElements(By.tagName("tr"));
		//For each row, check to see if has the room type you are looking for
		for (WebElement row:rowList){
			if(row.findElement(By.tagName("span")).getText().equalsIgnoreCase(roomType)){
				
				//In each row, find the textbox for the column you are updating
				//List<WebElement> textBoxList = rowList.get(rowNum-1).findElements(By.tagName("input"));
				List<WebElement> textBoxList = row.findElements(By.tagName("input"));
				textBoxList.get(colNum).clear();
				textBoxList.get(colNum).sendKeys(incrementAmt);
				return true;
			}
		}	
	
		return false;
	}
	
	/**
	 * @summary Handles the pop up that confirms whether you want to continue after
	 * 			clicking save
	 * @version Created 10/17/2014
	 * @author 	Jessica Marshall
	 * @param 	NA
	 * @throws 	NA
	 * @return 	NA
	 */
	public void handleConfirmContinuePopUp(){
		btnConfirmContinueYes.syncVisible(driver);
		
		((JavascriptExecutor)driver).executeScript("document.getElementById('j_id96:j_id99:j_id105').click()");
		//clickConfirmContinueYes();
		btnConfirmContinueYes.syncHidden(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		Sleeper.sleep(3000);
	}
	
	/**
	 * @summary Handles the pop up that displays the save was successful.  Also returns
	 * 			true if the saved pop up displays, false if not
	 * @version Created 10/17/2014
	 * @author 	Jessica Marshall
	 * @param 	NA
	 * @throws 	NA
	 * @return 	True if the saved pop up displays, false if not
	 */
	public boolean handleSavedSuccessfulPopUp(){
		boolean found = false;

		found = btnSavedPopUpClose.syncVisible(driver);
		if (found)
			clickSavedPopUpClose();
		
		return found;
	}
	
	
	/**
	 * @summary updates the current room type allotment value, based on the room type, 
	 * 			and the allotment column.  Returns false if either the column allotment specified 
	 * 			is not found, or if the allotment row is not found
	 * @version Created 10/20/2014
	 * @author 	Jessica Marshall
	 * @param 	Datatable scenario
	 * @throws 	NA
	 * @return 	false if the attribute/room type is not found in the table, otherwise true
	 */
	public boolean updateRoomTypeAllotment(String scenario){
		initialize();
		/*datatable.setDatatableSheet("UpdateRoomTypeAllotment");
		datatable.setDatatableRow(datatable.getRowContains(scenario, 0));*/
		datatable.setVirtualtablePage("UpdateRoomTypeAllotment");
		datatable.setVirtualtableScenario(scenario);
		
		int colNum = 0;
		String roomType = datatable.getDataParameter("RoomType");
		String roomAllotment = datatable.getDataParameter("RoomAllotment");
		String incrementAmt = datatable.getDataParameter("AllotmentAmount");
		List<WebElement> rowList;
		List<WebElement> headerList;
		boolean found = false;
		
		//Find the column number for the column you want to update
		//Get a list of all the column headings
		
		if(roomAllotment.equalsIgnoreCase("Base Merits")){
			colNum = 0;
			found = true;
		}
		else {
			
			//2 different id/names for this header table depending on whether page has been accessed & saved first, 
			
			if(!driver.findElements(By.id("manageAllotments:roomTypeAllotmentHeaderDataTable")).isEmpty()){
				headerList = driver.findElement(By.id("manageAllotments:roomTypeAllotmentHeaderDataTable")).findElements(By.tagName("th"));
			}
			else if (!driver.findElements(By.id("manageAllotments:roomAllHeaderDatatable")).isEmpty()){	
				headerList = driver.findElement(By.id("manageAllotments:roomAllHeaderDatatable")).findElements(By.tagName("th"));
			}
			else {
				Reporter.log("Allotment table was not displayed");
				return false;
			}
																			

			for(WebElement header:headerList){
				if (header.getText().equalsIgnoreCase(roomAllotment)) {
					colNum = headerList.indexOf(header);
					
					found = true;
					break;
				}		
			}
		}
		
		//If the column was not found, then return false	
		if (!found)
			return false;
		
	
		//Get a list of all the table rows
		//2 different id/names for this table depending on whether page has been accessed & saved first, 
		
		if(!driver.findElements(By.id("manageAllotments:roomAllDatatable")).isEmpty()){
			rowList = driver.findElement(By.id("manageAllotments:roomAllDatatable")).findElements(By.tagName("tr"));
		}
		else if (!driver.findElements(By.id("manageAllotments:roomTypeAllotmentDataTable")).isEmpty()){	
			rowList = driver.findElement(By.id("manageAllotments:roomTypeAllotmentDataTable")).findElements(By.tagName("tr"));
		}
		else {
			Reporter.log("Allotment table was not displayed");
			return false;
		}
		
		 
		

		//For each row, check to see if has the room type you are looking for
		for (WebElement row:rowList){
			if(row.findElement(By.tagName("span")).getText().equalsIgnoreCase(roomType)){
				
				//In each row, find the textbox for the column you are updating
				//List<WebElement> textBoxList = rowList.get(rowNum-1).findElements(By.tagName("input"));
				List<WebElement> textBoxList = row.findElements(By.tagName("input"));
				textBoxList.get(colNum).clear();
				textBoxList.get(colNum).sendKeys(incrementAmt);
				return true;
			}
		}
		
		
	
		return false;
		
	}
	
	
	/**
	 * @summary Gets the current room type allotment value, based on the room type, 
	 * 			and the allotment column.  Returns false if either the column allotment specified 
	 * 			is not found, or if the allotment row is not found
	 * @version Created 10/20/2014
	 * @author 	Jessica Marshall
	 * @param 	roomType, allotmentColumn
	 * @throws 	NA
	 * @return 	false if the attribute/room type is not found in the table, otherwise true
	 */
	public String getRoomTypeAllotmentValue(String roomType, String roomAllotment){
		int colNum = 0;
		boolean found = false;
		List<WebElement> headerList;
		List<WebElement> rowList;
		
		//Find the column number for the column you want to update
		//Get a list of all the column headings
		
		//Base merits is the first column but is sometimes not listed in the table header as a column heading.
		if(roomAllotment.equalsIgnoreCase("Base Merits")){
			colNum = 0;
			found = true;
		}
		else {
			
			//2 different id/names for this header table depending on whether page has been accessed & saved first, 
			
			if(!driver.findElements(By.id("manageAllotments:roomTypeAllotmentHeaderDataTable")).isEmpty()){
				headerList = driver.findElement(By.id("manageAllotments:roomTypeAllotmentHeaderDataTable")).findElements(By.tagName("th"));
			}
			else if (!driver.findElements(By.id("manageAllotments:roomAllHeaderDatatable")).isEmpty()){	
				headerList = driver.findElement(By.id("manageAllotments:roomAllHeaderDatatable")).findElements(By.tagName("th"));
			}
			else {
				Reporter.log("Allotment table was not displayed");
				return "";
			}
			
			//Get a list of all the header columns	
			for(WebElement header:headerList){
				if (header.getText().equalsIgnoreCase(roomAllotment)) {
					colNum = headerList.indexOf(header);
					
					found = true;
					break;
				}		
			}
		}
		
		//If the column was not found, then return false	
		if (!found)
			return "";
		
		if(!driver.findElements(By.id("manageAllotments:roomAllDatatable")).isEmpty()){
			rowList = driver.findElement(By.id("manageAllotments:roomAllDatatable")).findElements(By.tagName("tr"));
		}
		else if (!driver.findElements(By.id("manageAllotments:roomTypeAllotmentDataTable")).isEmpty()){	
			rowList = driver.findElement(By.id("manageAllotments:roomTypeAllotmentDataTable")).findElements(By.tagName("tr"));
		}
		else {
			Reporter.log("Allotment table was not displayed");
			return "";
		}
		

		//For each row, check to see if has the room type you are looking for
		for (WebElement row:rowList){
			if(row.findElement(By.tagName("span")).getText().equalsIgnoreCase(roomType)){
				
				//In each row, find the textbox for the column you are updating
				//List<WebElement> textBoxList = rowList.get(rowNum-1).findElements(By.tagName("input"));
				List<WebElement> textBoxList = row.findElements(By.tagName("input"));
				return textBoxList.get(colNum).getAttribute("value");
			}
		}
		
		
	
		return "";
		
	}
	
	public void deleteAllRoomAttributes(){
		int rowNum = 1;
		//if there are any rows, then delete
		
		if (driver.findElements(By.id("manageAllotments:roomAttributeDatatable")).size() != 0){
			//Get a list of all the table rows
			List<WebElement> rowList = driver.findElement(By.id("manageAllotments:roomAttributeDatatable")).findElements(By.tagName("tr"));
			
			for (@SuppressWarnings("unused") WebElement row:rowList){
				
				initialize();
				tblRoomAttributes.clickCell(driver, rowNum, 1);
				clickDeleteAttribute();
				Sleeper.sleep(1000);
				rowNum++;
			}
		}

		
	}
	
	public boolean revertRoomAllotmentUpdate(String roomType, String roomAllotment, String originalAmount){
		int colNum = 0;
		
		boolean found = false;
		
		//Find the column number for the column you want to update
		//Get a list of all the column headings
																			
		//Find the column number for the column you want to update
		//Get a list of all the column headings
		
		//Base merits is the first column but is sometimes not listed in the table header as a column heading.
		if(roomAllotment.equalsIgnoreCase("Base Merits")){
			colNum = 0;
			found = true;
		}
		else {
																			
			List<WebElement> headerList = driver.findElement(By.id("manageAllotments:roomTypeAllotmentDataTable")).findElements(By.tagName("th"));
			for(WebElement header:headerList){
				if (header.getText().equalsIgnoreCase(roomAllotment)) {
					colNum = headerList.indexOf(header);
					
					found = true;
					break;
				}		
			}
		}
		
		//If the column was not found, then return false	
		if (!found)
			return false;
		
		//Get a list of all the table rows
		List<WebElement> rowList = driver.findElement(By.id("manageAllotments:roomTypeAllotmentDataTable")).findElements(By.tagName("tr"));
		//For each row, check to see if has the room type you are looking for
		for (WebElement row:rowList){
			if(row.findElement(By.tagName("span")).getText().equalsIgnoreCase(roomType)){
				
				//In each row, find the textbox for the column you are updating
				//List<WebElement> textBoxList = rowList.get(rowNum-1).findElements(By.tagName("input"));
				List<WebElement> textBoxList = row.findElements(By.tagName("input"));
				textBoxList.get(colNum).clear();
				textBoxList.get(colNum).sendKeys(originalAmount);
				return true;
			}
		}
		
		return false;
		
	}
	
}

