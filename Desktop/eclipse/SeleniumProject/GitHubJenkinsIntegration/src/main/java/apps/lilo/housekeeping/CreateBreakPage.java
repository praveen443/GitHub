package apps.lilo.housekeeping;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.WebDriverSetup;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Textbox;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.TextboxImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.TestReporter;

public class CreateBreakPage {
	//
	// Page Fields
	//
	private Datatable datatable = new Datatable();
	private WebDriver driver;
	
	/*
	 * Create Breaks Column positions
	 */
	private final int BREAK_TYPE = 0;
	private final int BREAK_PRIORITY = 1;
	private final int BREAK_LIMIT = 2;
	private final int INCREMENTAL_VALUE = 3;
	private String breakName;
	
	//
	// Page Elements
	//
	// Add Break Button
	@FindBy(id="manageBreaks:add")
	private Button btnAddBreak;

	// Delete Break Button	
	@FindBy(id="manageBreaks:delete")
	private Button btnDeleteBreak;
	
	// Save Button
	@FindBy(id="manageBreaks:save")
	private Button btnSave;
	
	// Close Button
	@FindBy(id="manageBreaks:close")
	private Button btnClose;
	
	// Information Saved Button
	@FindBy(xpath="//table[@class='panelBackgroundModalPanel']/tbody/tr/td/input[contains(@onclick,'saveSuccessfulModalPanel')]")
	private Button btnInfoSavedClose;
		
	// Warning Yes Button
	@FindBy(xpath="//table[@class='panelBackgroundModalPanel']/tbody/tr/td/input[@type='submit'][@value='Yes']")
	private Button btnYesWarningPopup;
		
	// Warning Yes Button
	@FindBy(xpath="//table[@class='panelBackgroundModalPanel']/tbody/tr/td/input[@type='button'][@value='OK']")
	private Button btnErrorPopupOK;
	// Break table 
	@FindBy(xpath = "//tbody[@id='manageBreaks:breakList:tb']/tr")
	private List<WebElement> tblBreaks;
	
	//Error popup
	@FindBy(id = "houskeepingErrorModalPanelContentTable")
	private Element eleErrorPopup;
	
	// *********************
	// ** Build page area **
	// *********************
	
	public CreateBreakPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public CreateBreakPage initialize() {
		return ElementFactory.initElements(driver,
				this.getClass());
	}

	public boolean loadPage() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnSave); 
	}

	// **********************************************
	// ***Housekeeping Create Breaks Interactions ***
	// **********************************************
	
	private void setCellValue(WebElement cell, String text){
		//List<WebElement> columns = tblBreaks.get(row).findElements(By.tagName("td"));
		if (!text.isEmpty()){
			Textbox textCell = new TextboxImpl(cell.findElement(By.tagName("input")));
			textCell.focus(driver);
			textCell.set(text);
		}
	}
		
	private String getCellValue(int row, int column){
		List<WebElement> columns = tblBreaks.get(row).findElements(By.tagName("td"));
		Textbox cell = new TextboxImpl(columns.get(column).findElement(By.tagName("input")));
		return cell.getText();
	}
	
	private void setBreakType(String breakName){
		this.breakName = breakName;
	}
	 
	public String getBreakType(){
		return this.breakName;
	}
	
	private WebElement getBreakCell(WebElement cell){
		Element breakCell = null;
		try{			
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			breakCell = new ElementImpl(cell.findElement(By.tagName("input")));			
		}catch (NoSuchElementException nse){
			//breakCell = cell.findElement(By.tagName("span"));			
			//breakCell = cell;
		}
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
		return breakCell;		
	}
	
	public boolean validateBreakSaved(String text){
		if (getRowNumberForBreakType(text) == -1) return false;
		return true;
	}
	
	public boolean validateBreakRemoved(String text){		
		if (getRowNumberForBreakType(text) == -1) return true;
		return false;
	}
	
	public int getRowNumberForBreakType(String text){
		for (int row = 0 ; row < tblBreaks.size() ; row ++){
			List<WebElement> columns = tblBreaks.get(row).findElements(By.tagName("td"));
			WebElement cell = getBreakCell(columns.get(BREAK_TYPE));
			new ElementImpl(cell).highlight(driver);
			if (cell.getAttribute("value").equalsIgnoreCase(text)) return row; 
		}
		
		return -1;		
	}
	
	public String getBreakPriorityFromRow(int row){
		return getCellValue(row,BREAK_PRIORITY);
	}
	
	public String getBreakLimitFromRow(int row){
		return getCellValue(row,BREAK_LIMIT);
	}

	public String getIncrementalValueFromRow(int row){
		return getCellValue(row,INCREMENTAL_VALUE);
	}
	
	private String findHighestPriority(){
		int highestPriorityFound = 1;
		int currentRowPriority = 0;
		for (int row = 0 ; row < tblBreaks.size() ; row++){
			currentRowPriority = Integer.parseInt(getBreakPriorityFromRow(row));
			if (highestPriorityFound <= currentRowPriority ) highestPriorityFound = currentRowPriority+1 ; 
		}
		return String.valueOf(highestPriorityFound );
	}
	
	public void addBreak(String scenario){
		/*datatable.setDatatableSheet("HouseKeeperBreaks");
		datatable.setDatatableRow(datatable.getRowContains(scenario, 0));*/

		datatable.setVirtualtablePage("HouseKeeperBreaks");
		datatable.setVirtualtableScenario(scenario);
		
		setBreakType(datatable.getDataParameter("BreakType"));
		checkForAndDeleteExistingBreak(getBreakType());
		
		String priority = "";
		String userDesiredPriority = datatable.getDataParameter("BreakPriority");
		if (userDesiredPriority.equalsIgnoreCase("highest")) {
			priority = findHighestPriority();
		}else{
			priority = userDesiredPriority;
		}
		
		btnAddBreak.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		btnAddBreak.syncEnabled(driver);
		int row = tblBreaks.size()-1;
	
		List<WebElement> columns = tblBreaks.get(row).findElements(By.tagName("td"));
		
		setCellValue(columns.get(BREAK_TYPE), datatable.getDataParameter("BreakType"));
		setCellValue(columns.get(BREAK_PRIORITY), priority);
		setCellValue(columns.get(BREAK_LIMIT), datatable.getDataParameter("BreakLimit"));
		setCellValue(columns.get(INCREMENTAL_VALUE), datatable.getDataParameter("IncrementalValue"));

	}
	
	public void updateBreak(String breakToUpdate, String scenario){		
		/*datatable.setDatatableSheet("HouseKeeperBreaks");
		datatable.setDatatableRow(datatable.getRowContains(scenario, 0));*/
		datatable.setVirtualtablePage("HouseKeeperBreaks");
		datatable.setVirtualtableScenario(scenario);
		setBreakType(datatable.getDataParameter("BreakType"));											
		String priority = "";
		String userDesiredPriority = datatable.getDataParameter("BreakPriority");
		if (userDesiredPriority.equalsIgnoreCase("highest")) {
			priority = findHighestPriority();
		}else{
			priority = userDesiredPriority;
		}
		System.out.println("Priority: " + priority);
		
		int row = getRowNumberForBreakType(breakToUpdate);
		
		if (row < 0) throw new RuntimeException("Break with value [ " + breakToUpdate + " ] was not found.");		
		List<WebElement> columns = tblBreaks.get(row).findElements(By.tagName("td"));
		
		setCellValue(columns.get(BREAK_TYPE), datatable.getDataParameter("BreakType"));
		//setCellValue(columns.get(BREAK_PRIORITY), priority);
		setCellValue(columns.get(BREAK_LIMIT), datatable.getDataParameter("BreakLimit"));
		setCellValue(columns.get(INCREMENTAL_VALUE), datatable.getDataParameter("IncrementalValue"));
	}
	
	public void deleteBreak(String breakToDelete){
											
		int row = getRowNumberForBreakType(breakToDelete);
		
		if (row < 0) throw new RuntimeException("Break with value [ " + breakToDelete + " ] was not found.");
		
		tblBreaks.get(row).click();
		btnDeleteBreak.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);		
	}
	
	public void deleteBreak(String breakToDelete, boolean expectError){
		deleteBreak(breakToDelete);
		checkForError(expectError);				
	}
	
	public boolean clickSave(boolean expectError){
		BoardsHeader header = new BoardsHeader(driver);
		btnSave.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		btnYesWarningPopup.focusClick(driver);		
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	
		btnInfoSavedClose.focusClick(driver);
		return header.checkBoardErrors(expectError);
	}

	public boolean clickSave(){
		return clickSave(false);
	}
	
	public void clickClose(){
		btnClose.click();
	}
	
	public void checkForAndDeleteExistingBreak(String breakType){
		if(getRowNumberForBreakType(breakType) > -1) deleteBreak(breakType);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	

	public void checkForError(boolean expectError){
		if(expectError){
			if(eleErrorPopup.syncVisible(driver,3, false)){
				TestReporter.log("Found error message as expected");
				btnErrorPopupOK.focusClick(driver);
			}else{
				throw new RuntimeException("Error message was not found after drag and dropping the housekeeper when expected");
			}
		}else{
			if(eleErrorPopup.isDisplayed()){
				throw new RuntimeException("Error message was found after drag and dropping the housekeeper. Error message: " + eleErrorPopup.getText());
			}else{
				TestReporter.log("Found not error message as expected");
				btnErrorPopupOK.focusClick(driver);
			}
		}
	}
}

