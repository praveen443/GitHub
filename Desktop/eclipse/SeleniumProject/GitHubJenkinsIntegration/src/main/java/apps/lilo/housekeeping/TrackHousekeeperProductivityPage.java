package apps.lilo.housekeeping;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.*;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

public class TrackHousekeeperProductivityPage {
	//
	// Page Fields
	//
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	// Easy Column references
	private final int BOARD = 1;
	private final int ASSIGNED = 2;
	@SuppressWarnings("unused")
	private final int ROOMS_COMPLETE = 3;
	@SuppressWarnings("unused")
	private final int CHECKOUT_COMPLETE = 4;
	@SuppressWarnings("unused")
	private final int CHECKOUTS = 5;
	private final int CURRENT_ROOM = 6;
	
	//
	// Page Elements
	//
	//Listbox containing options for search type
	@FindBy(id = "trackProductivity:attributeTypeListId")
	private Listbox lstSearchType;
	
	//Textbox for search value
	@FindBy(id = "trackProductivity:searchTxtId")
	private Textbox txtSearchbox;
	
	//Button to call search
	@FindBy(id = "trackProductivity:searchButtonId")
	private Button btnSearch;
	
	//Label for percentage Occupancy
	@FindBy(id = "trackProductivity:resortOccupancyLbl")
	private Label lblOccupancyPercentage;
	
	//Label for number of checkouts
	@FindBy(id = "trackProductivity:resortCheckoutLbl")
	private Label lblNumberCheckouts;
	
	//Label for number of checkins
	@FindBy(id = "trackProductivity:resortCheckinLbl")
	private Label lblNumberCheckins;
	
	//Productivity Webtable body
	@FindBy(id = "trackProductivity:trackProductivityList:tb")
	private Webtable tblProductivity;

	//Label for rooms complete
	@FindBy(id = "trackProductivity:roomCompleteFtrLbl1")
	private Label lblRoomsComplete;

	//Label for checkouts complete
	@FindBy(id = "trackProductivity:checkoutFtrLbl1")
	private Label lblCheckoutsComplete;
	
	//Exit button
	@FindBy(id="trackProductivity:trackProductivityCloseButtonId")
	private Button btnClose;
	
	// *********************
	// ** Build page area **
	// *********************

	public TrackHousekeeperProductivityPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public TrackHousekeeperProductivityPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	public boolean loadPage() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, txtSearchbox); 
	}

	// *******************************************
	// ***Track Productivity Page Interactions ***
	// *******************************************
	
	public void searchForBoard(String board){			
		lstSearchType.select("Board");
		txtSearchbox.set(board);
		btnSearch.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);	
	}
	
	public void searchForHousekeeper(String housekeeper){			
		lstSearchType.select("Housekeeper");
		txtSearchbox.set(housekeeper);
		btnSearch.click();		
		new ProcessingYourRequest().WaitForProcessRequest(driver);	
	}
	
	public void searchForPartialHousekeeperName(String housekeeper){			
		lstSearchType.select("Housekeeper");
		txtSearchbox.set(housekeeper.substring(0, housekeeper.length()/2));
		btnSearch.click();		
		new ProcessingYourRequest().WaitForProcessRequest(driver);	
	}
	
	
	public void searchForHousekeeperLowercase(String housekeeper){			
		lstSearchType.select("Housekeeper");
		txtSearchbox.set(housekeeper.toLowerCase());
		btnSearch.click();		
		new ProcessingYourRequest().WaitForProcessRequest(driver);	
	}
	
	
	public void searchForHousekeeperUppercase(String housekeeper){			
		lstSearchType.select("Housekeeper");
		txtSearchbox.set(housekeeper.toUpperCase());
		btnSearch.click();		
		new ProcessingYourRequest().WaitForProcessRequest(driver);	
	}
	public void searchForRoomNumber(String room){			
		lstSearchType.select("Room Number");
		txtSearchbox.set(room);
		btnSearch.click();		
		new ProcessingYourRequest().WaitForProcessRequest(driver);	
	}
	
	public String getTextInRowForBoardColumn(int row){		
		return getTextFromProductivityTable(row, BOARD);
	}
	
	public String getTextInRowForAssignedColumn(int row){		
		return getTextFromProductivityTable(row, ASSIGNED);
	}	

	public String getTextInRowForCurrentRoomColumn(int row){		
		return getTextFromProductivityTable(row, CURRENT_ROOM);
	}	
	
	public int getRowForTextInBoardColumn(String text){		
		return getRowFromProductivityTable(text, BOARD);
	}
	
	public int getRowForTextInAssignedColumn(String text){		
		return getRowFromProductivityTable(text, ASSIGNED);
	}
	
	public int getRowForTextInCurrentRoomColumn(String text){		
		return getRowFromProductivityTable(text, CURRENT_ROOM);
	}
	
	public boolean rowTextHighlighted(int row){
		return getCellHighlightStatusFromProductivityTable(row, 1);
	}
	
	private String getTextFromProductivityTable(int row, int column){
		//List<WebElement> rows =tblProductivity.findElements(By.tagName("tr"));
		List<WebElement> rows =tblProductivity.findElements(By.xpath("//tbody[@id='trackProductivity:trackProductivityList:tb']/tr"));
		List<WebElement> columns = rows.get(row-1).findElements(By.tagName("td"));		
		return columns.get(column-1).getText();
	}
	
	private int getRowFromProductivityTable(String text, int column){
		int rowNum = 0;
		//List<WebElement> rows =tblProductivity.findElements(By.tagName("tr"));;
		List<WebElement> rows =tblProductivity.findElements(By.xpath("//tbody[@id='trackProductivity:trackProductivityList:tb']/tr"));
		for (rowNum = 0 ; rowNum < rows.size() ; rowNum++ ){
			List<WebElement> row = rows.get(rowNum).findElements(By.tagName("td"));
			if (row.get(column-1).getText().contains(text)){
				break;
			}			
		}		
		
		return rowNum+1;
	}
	
	private boolean getCellHighlightStatusFromProductivityTable(int row, int column){
		//List<WebElement> rows =tblProductivity.findElements(By.tagName("tr"));
		List<WebElement> rows =tblProductivity.findElements(By.xpath("//tbody[@id='trackProductivity:trackProductivityList:tb']/tr"));
		List<WebElement> columns = rows.get(row -1).findElements(By.tagName("td"));		
		WebElement cell = columns.get(column-1).findElement(By.tagName("span"));
		if (cell.getAttribute("style").contains("BACKGROUND-COLOR")){
			return true;
		}else{
			return false;
		}
	}
	
}

