/*package apps.lilo.housekeeping;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import apps.lilo.NavigationBar;
import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

public class HousekeepingAssignments {
	//
	// Page Fields
	//
	private Datatable datatable = new Datatable();
	private WebDriver driver;
	
	
	 * Section Assignment Column positions
	 
	@SuppressWarnings("unused")
	private final int SECTION_NUMBER = 0;
	@SuppressWarnings("unused")
	private final int SECTION_NAME = 1;
	private final int PRIMARY = 2;
	private final int SECONDARY = 3;
	private final int BACKUP = 4;
	

	
	 * Housekeeper List Column positions
	 
	private final int HOUSEKEEPER_NAME = 0;
	@SuppressWarnings("unused")
	private final int SENORITY = 1;
	@SuppressWarnings("unused")
	private final int ROLE = 2;

	//
	// Page Elements
	//
	//Search List box
	@FindBy(css = "select[name^='manageHskAssignment'][class='dropDowntext']")
	private Listbox lstSearchOptions;
	
	//Search textbox
	@FindBy(id = "manageHskAssignment:filterValueText")
	private Textbox txtSearch;
	
	//Clear Search Button
	@FindBy(id = "manageHskAssignment:clear")
	private Button btnClearSearch;	
	
	//print button
	@FindBy(id = "manageHskAssignment:print")
	private Button btnPrint;
	
	//Save button
	@FindBy(id = "manageHskAssignment:save")
	private Button btnSave;
	
	//Close button
	@FindBy(id = "manageHskAssignment:close")
	private Button btnClose;
	
	// Sections table 
	@FindBy(xpath = "//tbody[@id='manageHskAssignment:sectionsList:tb']/tr")
	private List<WebElement> tblSections;
	
	// Table buttons for tblTableScroller
	//@FindBy(id = "manageHskAssignment:housekeeperTableScroller_table")
	@FindBy(xpath = "//table[@id='manageHskAssignment:housekeeperTableScroller_table']/tbody/tr/td")
	private List<WebElement> eleSectionsNavigationBar;
	
	// Housekeepers table 
	@FindBy(xpath = "//tbody[@id='manageHskAssignment:housekeeperList:tb']/tr")
	private List<WebElement> tblHousekeepers;
	
	//Error popup
	@FindBy(id = "houskeepingErrorModalPanel1ContentTable")
	private Element eleErrorPopup;
	
	// *********************
	// ** Build page area **
	// *********************	
	
	public HousekeepingAssignments(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public HousekeepingAssignments initialize() {
		return ElementFactory.initElements(driver,
				this.getClass());
	}

	public boolean loadPage() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnSave); 
	}

	// ********************************************
	// ***Housekeeping Assignments Interactions ***
	// ********************************************
	private int[] findFirstAvailiblePrimaryAssignmentRow(){

		int firstRow[] = {1,0};
		
		
		 * Determine last page
		 
		
		NavigationBar navigateSections = new NavigationBar(eleSectionsNavigationBar);
		navigateSections.LAST();
		int lastPage = navigateSections.getActivePage();			
		
		
		 * Loop through all pages till a blank primary is found
		 
		for(navigateSections.FIRST(); navigateSections.getActivePage() < lastPage ; navigateSections.NEXT()){
			for (int row = 0 ; row < tblSections.size() ; row ++){
				List<WebElement> columns = tblSections.get(row).findElements(By.tagName("td"));		
				WebElement cell = columns.get(PRIMARY).findElement(By.tagName("span"));
				if (cell.getText().isEmpty()){
					firstRow[0] =  navigateSections.getActivePage();
					firstRow[1] =  row;
					return firstRow;
				}
			}
		}
		
		return firstRow;
	}
	

	
	public void dragAndDropHousekeeperToSection(int housekeeperRow, String sectionAssignment, boolean expectError){
		NavigationBar navigateSections = new NavigationBar(eleSectionsNavigationBar);
		
		int startRow = 0;
		int startPage = 1;
		int[] firstAvailibleRow = findFirstAvailiblePrimaryAssignmentRow();
		if (sectionAssignment.equalsIgnoreCase("primary")){
			
			startPage = firstAvailibleRow[0];
			startRow= firstAvailibleRow[1];
		}else{
			startPage =  navigateSections.getActivePage();
			startRow= firstAvailibleRow[1]-1;
		}
		System.out.println("Start page: " + startPage);
		//navigateSections.navigateTo(startPage);
		WebElement housekeeperCell = null;
		WebElement sectionCell = null;
		
		//for (int row = 0 ; row < tblHousekeepers.size() ; row ++){
			List<WebElement> hkColumns = tblHousekeepers.get(housekeeperRow-1).findElements(By.tagName("td"));		
			housekeeperCell = hkColumns.get(HOUSEKEEPER_NAME).findElement(By.tagName("span"));			
		//}
		
		int sectionColumn;
		
		switch(sectionAssignment.toLowerCase()){
			case "primary": sectionColumn = PRIMARY; break;
			case "secondary": sectionColumn = SECONDARY; break;
			case "backup": sectionColumn = BACKUP; break;
			default: throw new RuntimeException( sectionAssignment + " is not a valid option. Primary, Second, or Backup needs to be used.");
		}
		

		//for (int row = startRow ; row < tblSections.size() ; row ++){
			List<WebElement> columns = tblSections.get(startRow).findElements(By.tagName("td"));		
			sectionCell = columns.get(sectionColumn).findElement(By.tagName("span"));			
		//}
		
			new ElementImpl(housekeeperCell).highlight(driver);
			new ElementImpl(sectionCell).highlight(driver);
		//new Mouse(driver).dragAndDrop(housekeeperCell, sectionCell);
			Actions builder = new Actions(driver);
			//Have to use the depreciated pause as otherwise it doesn't wait long enough 
			//for the element to get a green check mark before releasing and won't display in the table
			@SuppressWarnings("deprecation")
			Action dragAndDrop = builder.clickAndHold(housekeeperCell)
					.moveToElement(sectionCell)
					.pause(2000)
					.release(sectionCell)
					.build();
			dragAndDrop.perform();
		
		Sleeper.sleep(1000);
		
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		
		checkForError(expectError);
		
	}
	
	public String getHousekeeperNameFromList(int row){
		List<WebElement> columns = tblHousekeepers.get(row-1).findElements(By.tagName("td"));
		return columns.get(HOUSEKEEPER_NAME).getText();
	}
	
	public int getHousekeeperRowFromList(String name){
		for (int row = 0; row < tblHousekeepers.size(); row++){
			List<WebElement> columns = tblHousekeepers.get(row).findElements(By.tagName("td"));
				if (columns.get(HOUSEKEEPER_NAME).getText().equalsIgnoreCase(name)){
					return row;
				}
		}
		return 0;
	}
	
	public boolean validateHousekeeperNotInList(String name){
		for (int row = 0; row < tblHousekeepers.size(); row++){
			List<WebElement> columns = tblHousekeepers.get(row).findElements(By.tagName("td"));
				if (columns.get(HOUSEKEEPER_NAME).getText().equalsIgnoreCase(name)){
					return true;
				}
		}
		return false;
	}
	
	
	public void checkForError(boolean expectError){
		if(expectError){
			if(eleErrorPopup.syncVisible(driver, 5, false)){
				TestReporter.log("Found error message as expected");
			}else{
				throw new RuntimeException("Error message was not found after drag and dropping the housekeeper when expected");
			}
		}else{
			if(eleErrorPopup.isDisplayed()){
				throw new RuntimeException("Error message was found after drag and dropping the housekeeper. Error message: " + eleErrorPopup.getText());
			}else{
				TestReporter.log("Found not error message as expected");
			}
		}
	}
}

*/