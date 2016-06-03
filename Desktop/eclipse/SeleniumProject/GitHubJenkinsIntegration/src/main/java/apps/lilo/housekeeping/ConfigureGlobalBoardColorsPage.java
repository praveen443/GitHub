package apps.lilo.housekeeping;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.lilo.bankIn.BankInPage;
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
 * @summary Contains the page methods & objects for the configure global board colors page page
 * @version Created 10/13/2014
 * @author Jessica Marshall
 */
public class ConfigureGlobalBoardColorsPage {
	//
	// Page Fields
	//
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	
	// *************************************
	// *** HousekeepingLinksPage Elements ***
	// *************************************
	
	// Select board type 
	@FindBy(id = "headerForm:selectBoardType")
	private Button btnSelectBoard;
	
	// Board colors table
	@FindBy(id = "manageBreaks:statusColorList:tb")
	private Webtable tblBoardColors;
	
	// Save
	@FindBy(id = "manageBreaks:save")
	private Button btnSave;
	
	// Close
	@FindBy(id = "manageBreaks:close")
	private Button btnClose;
	
	// Save import picture
	@FindBy(id = "importPictureForm:save")
	private Button btnSaveImport;
	
	// close import picture
	@FindBy(id = "importPictureForm:close")
	private Button btnCloseImport;
	
	//clear the existing import picture
	@FindBy(id = "importPictureForm:clear")
	private Button btnClearImage;
	
	// Saved successful panel pop up
	@FindBy(id = "saveSuccessfulModalPanelCDiv")
	private Element eleSavedPopUp;
	
	// Saved successful pop up close
	@FindBy(id = "j_id158:j_id164")
	private Button btnCloseSavedPopUp;
	
	// *********************
	// ** Build page area **
	// *********************
	
	/**
	 * @summary Constructor to initialize the page
	 * @version Created 10/10/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public ConfigureGlobalBoardColorsPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Constructor to initialize the page
	 * @version Created 10/10/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public ConfigureGlobalBoardColorsPage initialize() {
		return ElementFactory.initElements(driver,
				ConfigureGlobalBoardColorsPage.class);
	}

	/**
	 * @summary Constructor to initialize the page
	 * @version Created 10/10/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public boolean loadPage() {
		return new PageLoaded().isPageHTMLLoaded(BankInPage.class, driver, btnSave); 
	}

	// *****************************************
	// ***Page Interactions ***
	// *****************************************
	
		
		public void clickSave() {
			btnSave.syncVisible(driver);
			btnSave.click();
			new ProcessingYourRequest().WaitForProcessRequest(driver);
						
		}	
		
		public void clickClose() {
			btnClose.syncVisible(driver);
			btnClose.click();
			new ProcessingYourRequest().WaitForProcessRequest(driver);
						
		}
		
		public void clickSavedPopUpClose() {
			btnCloseSavedPopUp.syncVisible(driver);
			btnCloseSavedPopUp.click();
			new ProcessingYourRequest().WaitForProcessRequest(driver);
						
		}
		
		public boolean setRoomStatusImages(String scenario){
			/*datatable.setDatatableSheet("SetBoardImages");
			datatable.setDatatableRow(datatable.getRowContains(scenario, 0));*/
			datatable.setVirtualtablePage("SetBoardImages");
			datatable.setVirtualtableScenario(scenario);
			
			int rowNum = 0;
			String roomStatus = datatable.getDataParameter("RoomStatus");
			String image = datatable.getDataParameter("Image");
					
			//Find the row that has the room status 
			rowNum = tblBoardColors.getRowThatContainsCellText(driver, roomStatus, 1);
			
			//if not found then return false
			if (rowNum == 0)
				return false;
			
			//create the image button element
			((JavascriptExecutor)driver).executeScript("document.getElementsByName('manageBreaks:statusColorList:" + (rowNum-1) + ":j_id41')[0].click()");
			Sleeper.sleep(1000);	
			
			//driver.findElement(By.name("manageBreaks:statusColorList:" + (rowNum-1) + ":j_id41")).click();
			
			//select an image
			btnSaveImport.syncVisible(driver);
			
			driver.findElement(By.cssSelector("img[title='" + image + "']")).click();
			
			btnSaveImport.click();
			btnSaveImport.syncHidden(driver);
			return true;
				
		}
		
		public boolean setRoomStatusColor(String scenario){
			/*datatable.setDatatableSheet("SetBoardColors");
			datatable.setDatatableRow(datatable.getRowContains(scenario, 0));*/
			datatable.setVirtualtablePage("SetBoardColors");
			datatable.setVirtualtableScenario(scenario);
			int rowNum = 0;
			String roomStatus = datatable.getDataParameter("RoomStatus");
			String color = datatable.getDataParameter("Color");
					
			//Find the row that has the room status 
			rowNum = tblBoardColors.getRowThatContainsCellText(driver, roomStatus, 1);
			
			//if not found then return false
			if (rowNum == 0)
				return false;
			
			//create the text box element
			driver.findElement(By.name("manageBreaks:statusColorList:" + (rowNum-1) + ":j_id38")).clear();
			driver.findElement(By.name("manageBreaks:statusColorList:" + (rowNum-1) + ":j_id38")).sendKeys(color);
			
			
			return true;
		}
		
		public Map<String, String> getBoardColors(){
			Map<String, String> hm = new HashMap<String, String>();
			List <WebElement> statusList = driver.findElements(By.cssSelector("td[id^='manageBreaks:statusColorList:'][id$=':status']"));
			List <WebElement> colorList = driver.findElements(By.cssSelector("input[name^='manageBreaks:statusColorList:'][name$=':j_id38']"));
					
			for (int i = 0; i < tblBoardColors.getRowCount(); i++ ){
				hm.put(statusList.get(i).getText().trim(), colorList.get(i).getAttribute("value"));
			}
			return hm;
		}
		
		public void resetBoardColors(Map<String, String> hm){
			List <WebElement> statusList = driver.findElements(By.cssSelector("td[id^='manageBreaks:statusColorList:'][id$=':status']"));
			List <WebElement> colorList = driver.findElements(By.cssSelector("input[name^='manageBreaks:statusColorList:'][name$=':j_id38']"));
			int rowNum = 0;
			//Create a set of the entries
			Set<Entry<String, String>> set = hm.entrySet();
			//Create an iterator
			Iterator<Entry<String, String>> i = set.iterator();
			//Go through
			while(i.hasNext()){
				Map.Entry<String, String> me = (Map.Entry<String, String>)i.next();
				
				//Get the rown number that contains the room status
				for(WebElement element: statusList){
					if (element.getText().trim().equalsIgnoreCase(me.getKey().toString()))
						rowNum = statusList.indexOf(element);
						break;
				}
				//rowNum = tblBoardColors.getRowWithCellText(driver, me.getKey().toString(), 1);
				
				//Check to see if the color needs to be reset to original value
				//Textbox txtColor = (Textbox) driver.findElement(By.name("manageBreaks:statusColorList:" + (rowNum-1) + ":j_id38"));
				//colorList.get(rowNum-1).getAttribute(name)
				/*if (!txtColor.getAttribute("value").equalsIgnoreCase(me.getValue().toString())){
					txtColor.safeSet(me.getValue().toString());
				}*/
				
				if (!colorList.get(rowNum).getAttribute("value").equals(me.getValue().toString())){
					colorList.get(rowNum).clear();
					colorList.get(rowNum).sendKeys(me.getValue().toString());
				}
			}
			
			
		}
		
		public boolean isSavedPopUpDisplayed(){
			return eleSavedPopUp.isDisplayed();
		}
		
		public String getColorForRoomStatus(String roomStatus){
			int rowNum;
			rowNum = tblBoardColors.getRowThatContainsCellText(driver, roomStatus, 1);
			return driver.findElement(By.name("manageBreaks:statusColorList:" + (rowNum-1) + ":j_id38")).getAttribute("value");
		}
		
		public String getImageForRoomStatus(String roomStatus){
			tblBoardColors.syncVisible(driver);
			//manageBreaks:statusColorList:0:importPicture
			int rowNum;
			rowNum = tblBoardColors.getRowThatContainsCellText(driver, roomStatus, 1);
			WebElement imageDataCell = driver.findElement(By.id("manageBreaks:statusColorList:" + (rowNum-1) + ":importPicture"));
			List<WebElement> childs = imageDataCell.findElements(By.cssSelector("*"));
			
			//If there is an image present, there should be 3 child objects.  Return null if no image is present, 
			//if an image is present, return the name of the file
			if (childs.size()==3){
				return childs.get(2).getAttribute("src");
			}
			return null;
		}
		
		public boolean resetBoardImage(String roomStatus, String image){
			initialize();
			int rowNum = 0;
					
			//Find the row that has the room status 
			rowNum = tblBoardColors.getRowThatContainsCellText(driver, roomStatus, 1);
			
			//if not found then return false
			if (rowNum == 0)
				return false;
			
			//create the image button element
			//driver.findElement(By.name("manageBreaks:statusColorList:" + (rowNum-1) + ":j_id41")).click();
			((JavascriptExecutor)driver).executeScript("document.getElementsByName('manageBreaks:statusColorList:" + (rowNum-1) + ":j_id41')[0].click()");
			Sleeper.sleep(1000);	
			
			//select an image
			btnSaveImport.syncVisible(driver);
			
			//if the image value is null, then need to clear any existing image
			if (image == null){
				btnClearImage.click();
			}else
				driver.findElement(By.cssSelector("img[title='" + image + "']")).click();
			
			btnSaveImport.click();
			btnSaveImport.syncHidden(driver);
			return true;
		}
		
		public boolean revertRoomStatusColor(String roomStatus, String color){
		
			int rowNum = 0;
				
			//Find the row that has the room status 
			rowNum = tblBoardColors.getRowThatContainsCellText(driver, roomStatus, 1);
			
			//if not found then return false
			if (rowNum == 0)
				return false;
			
			//create the text box element
			driver.findElement(By.name("manageBreaks:statusColorList:" + (rowNum-1) + ":j_id38")).clear();
			driver.findElement(By.name("manageBreaks:statusColorList:" + (rowNum-1) + ":j_id38")).sendKeys(color);
			
			
			return true;
		}
}

