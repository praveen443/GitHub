/*package apps.lilo.housekeeping;

import java.text.DecimalFormat;
import java.util.List;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.lilo.NavigationBar;
import apps.lilo.bankIn.BankInPage;
import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Label;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.dataFactory.ResortInfo;

*//**
 * 
 * @summary Contains the page methods & objects for the Housekeeper 
 * 			Daily Schedule page
 * @version Created 10/6/2014
 * @author Jessica Marshall
 *//*
public class HousekeeperDailySchedulePage {	
	//
	// Page Fields
	//
	//Used to calculate the maximum mertis
	private int totalWDWBreakMinutes = 60;
	private int totalDVCBreakMinutes = 90;
	private Datatable datatable = new Datatable();
	private WebDriver driver;
	
	// *************************************
	// *** Page Elements ***
	// *************************************

	// Search by text box
	@FindBy(id = "manageHousekeepingDailyschedule:filterValueText")
	private Textbox txtSearch;
	
	// Drop down box of search by options
	@FindBy(name = "manageHousekeepingDailyschedule:j_id33")
	private Listbox lstSearchBy;
	
	// Clear
	@FindBy(id = "manageHousekeepingDailyschedule:clear")
	private Button btnClear;
	
	// select date
	@FindBy(id = "manageHousekeepingDailyschedule:selectedDateInputDate")
	private Button txtDate;
	
	// Drop down box of role options
	@FindBy(name = "manageHousekeepingDailyschedule:j_id49")
	private Listbox lstRole;
	
	// Table of all the housekeepers daily schedule list	
	@FindBy(id = "manageHousekeepingDailyschedule:housekeepingScheduleList:tb")
	private Webtable tblHousekeepers;
	
	// Rows in tblHousekeepers
	@FindBy (xpath = "//tbody[@id='manageHousekeepingDailyschedule:housekeepingScheduleList:tb']/tr")
	private List<WebElement> tblHouseKeeperRows;
	
	// label that contains the housekeeper count
	@FindBy(id = "manageHousekeepingDailyschedule:housekeeperCount")
	private Label lblCount;
	
	// delete
	@FindBy(id = "manageHousekeepingDailyschedule:delete")
	private Button btnDelete;
	
	// add
	@FindBy(id = "manageHousekeepingDailyschedule:add")
	private Button btnAdd;
	
	// print
	@FindBy(id = "manageHousekeepingDailyschedule:print")
	private Button btnPrint;
	
	// save
	@FindBy(id = "manageHousekeepingDailyschedule:save")
	private Button btnSave;
	
	// close
	@FindBy(id = "manageHousekeepingDailyschedule:close")
	private Button btnClose;
	
	// Table of the suggestions that populate when searching by name
	@FindBy(id = "manageHousekeepingDailyschedule:suggestionBoxId:suggest")
	private Webtable tblSuggestions;
	
	// saved successful pop up
	@FindBy(id = "saveSuccessfulModalPanelCDiv")
	private Element eleSavedPopUp;
	
	// saved successful pop up close
	@FindBy(id = "j_id249:j_id255")
	private Button btnSavedPopUpClose;
	
	// Resort name label
	@FindBy(id = "headerForm:resortName")
	private Label lblResortName;
	
	// Table element that contains the page scrolling elements, <,<<, etc
	@FindBy(id = "manageHousekeepingDailyschedule:housekeeperTableScroller_table")
	private Webtable tblTableScroller;
	
	// Table buttons for tblTableScroller
	@FindBy(xpath = "//table[@id='manageHousekeepingDailyschedule:housekeeperTableScroller_table']/tbody/tr/td")
	private List<WebElement> eleNavigationBar;	
		
	// *********************
	// ** Build page area **
	// *********************
	public WebDriver getDriver(){
		return driver;
	}
	*//**
	 * @summary Constructor to initialize the page
	 * @version Created 9/11/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @throws NA
	 * @return NA
	 *//*
	public HousekeeperDailySchedulePage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	*//**
	 * @summary Constructor to initialize the page
	 * @version Created 9/11/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @throws NA
	 * @return NA
	 *//*
	public HousekeeperDailySchedulePage initialize() {
		return ElementFactory.initElements(driver,
				HousekeeperDailySchedulePage.class);
	}

	*//**
	 * @summary Constructor to initialize the page
	 * @version Created 9/11/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @return 
	 * @throws NA
	 * @return NA
	 *//*
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
		Sleeper.sleep(1000);
	}
	
	public void clickClose() {
		btnClose.syncVisible(driver);
		btnClose.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);		
	}
	
	public void clickAdd() {
		btnAdd.syncVisible(driver);
		btnAdd.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);		
	}
	
	public void clickPrint() {
		btnPrint.syncVisible(driver);
		btnPrint.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);		
	}
	
	public void clickDelete() {
		btnDelete.syncVisible(driver);
		btnDelete.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);		
	}
	
	*//**
	 * @summary Searches by name and then clicks the autopopulated suggestion.
	 * 			Returns a true if the name was found and clicked, returns a 
	 * 			false if the name does not autopopulate
	 * @version Created 10/6/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @throws NA
	 * @return NA
	 *//*
	public boolean searchByName(String name){
		int rowNum;
		txtSearch.syncVisible(driver);
		txtSearch.click();        	
		txtSearch.sendKeys(Keys.CONTROL + "a");
		txtSearch.sendKeys(name);
		Sleeper.sleep(1000);
		
		//refresh the elements as the suggestion drop down has refreshed
		initialize();
		
		//wait for the suggestions to display
		tblSuggestions.syncVisible(driver);
		
		//click on the row that matches the name
		rowNum = tblSuggestions.getRowThatContainsCellText(driver, name, 1);
		//if nothing comes back, then return a false
		if (rowNum == 0)
			return false;
		tblSuggestions.clickCell(driver, rowNum, 1);
		Sleeper.sleep(1000);
		
		//refresh the elements
		initialize();
		return true;
		
	}
	
	*//**
	 * @summary Searches through the displayed rows for the list of housekeepers table
	 * 			based on the personnel ID (which is a unique ID) and for the row that it finds
	 * 			clicks the corresponding edit button.  If no row is found for matching personnel ID
	 * 			then it returns false, else returns true
	 * @version Created 10/6/2014
	 * @author 	Jessica Marshall
	 * @param 	ID
	 * @throws 	NA
	 * @return 	true if row found, false if not
	 *//*
	public boolean searchAndClickEditByPersonnelID (String ID){
		initialize();
		int rowNum = 0;
		rowNum = tblHousekeepers.getRowWithCellText(driver, ID, 3);
		
		//if 0 comes back, then the row was not found
		if (rowNum == 0)
			return false;
		
		//decrementing rowNum by 1 as the edit button row values start at 0
		rowNum--;
		
		List <WebElement> buttonList = driver.findElements(By.cssSelector
				("input[id^='manageHousekeepingDailyschedule:housekeepingScheduleList:'][id$='editButton']"));
		buttonList.get(rowNum).click();
		buttonList.get(rowNum).getAttribute("id");
		//driver.findElement(By.id("manageHousekeepingDailyschedule:housekeepingScheduleList:" + rowNum + ":editButton")).click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		return true;
	}
	
	*//**
	 * @summary Gets the current housekeeper count based off the label displayed at the bottom
	 * 			of the table, returns an integer value
	 * @version Created 10/6/2014
	 * @author 	Jessica Marshall
	 * @param 	NA
	 * @throws 	NA
	 * @return 	integer housekeeper count
	 *//*
	public int getHousekeeperCount() {
		int count = 0;
		lblCount.syncVisible(driver);
		String [] splitArray = lblCount.getText().split(":");
		count = Integer.parseInt(splitArray[1].trim());
		return count;
	}
	
	public boolean isSavedPopUpDisplayed(){
		return eleSavedPopUp.isDisplayed();
	}
	
	public void clickSavedPopUpClose(){
		btnSavedPopUpClose.syncVisible(driver);
		btnSavedPopUpClose.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);	
	}
	
	*//**
	 * @summary Gets the value in the hours column for the row that contains the personnel ID.
	 * 			Returns null if no row was found for that personnel ID
	 * @version Created 10/6/2014
	 * @author 	Jessica Marshall
	 * @param 	Personnel ID
	 * @throws 	NA
	 * @return 	String hours or null
	 *//*
	public String getHoursByPersonnelID(String ID){
		int rowNum = 0;
		rowNum = tblHousekeepers.getRowWithCellText(driver, ID, 3);
		
		//if 0 comes back, then the row was not found
		if (rowNum == 0)
			return null;
		
		return tblHousekeepers.getCellData(driver, rowNum, 6).trim();
	}
	
	*//**
	 * @summary Gets the text value in the merits column in the text box for the row
	 * 			that contains the personnel ID.
	 * 			Returns null if no row was found for that personnel ID
	 * @version Created 10/8/2014
	 * @author 	Jessica Marshall
	 * @param 	Personnel ID
	 * @throws 	NA
	 * @return 	String merits or null
	 *//*
	public String getMeritsByPersonnelID(String ID){
		int rowNum = 0;
		rowNum = tblHousekeepers.getRowWithCellText(driver, ID, 3);
		
		
		//if 0 comes back, then the row was not found
		if (rowNum == 0)
			return null;
		
		//decrementing row number as elements start in table at 0
		rowNum--;
		
		//return the text value found in the merits text box for that row
		List <WebElement> textBoxList = driver.findElements(By.cssSelector
				("input[id^='manageHousekeepingDailyschedule:housekeepingScheduleList:'][id$='adjustedMeritsText']"));
		return textBoxList.get(rowNum).getAttribute("value").trim();

		//return driver.findElement(By.id("manageHousekeepingDailyschedule:housekeepingScheduleList:" 
		//		+ rowNum + ":adjustedMeritsText")).getAttribute("value").trim();
		
	}
	
	*//**
	 * @summary Deletes a housekeeper from the schedule based on the personnel ID that is passed
	 * 			Returns false if no row was found for that personnel ID
	 * @version Created 10/8/2014
	 * @author 	Jessica Marshall
	 * @param 	Personnel ID
	 * @throws 	NA
	 * @return 	
	 *//*
	public Boolean deleteHouskeeperByPersonnelID(String ID){
		int rowNum = 0;
		rowNum = tblHousekeepers.getRowWithCellText(driver, ID, 3);
			
		//if 0 comes back, then the row was not found
		if (rowNum == 0)
			return false;
		
		//Click the row 
		tblHousekeepers.clickCell(driver, rowNum, 1);

		//Click delete
		clickDelete();
		
		//return the text value found in the merits text box for that row
		return true;
		
	}
	public String getResort() {
		lblResortName.syncVisible(driver);
		return lblResortName.getText().trim();
	}
	
	public Boolean verifyMaximumMerits(Double meritMultiplier){
		String resort = getResort();
		int breakMinutes = 0;
		double expectedMaxMerits;
		Double scheduledMinutes;
		boolean meritsCorrect = true;
		DecimalFormat formatter = new DecimalFormat("#0.00");
		
		if(resort.equals("Aulani, Disney Vacation Club Villas, Ko Olina, Hawaii"))
			breakMinutes = totalDVCBreakMinutes-30;
		else if(ResortInfo.isDVCResort(resort))
			breakMinutes = totalDVCBreakMinutes;
		else
			breakMinutes = totalWDWBreakMinutes;
		
		// Get the number of pages
		NavigationBar navigate = new NavigationBar(eleNavigationBar);
		navigate.LAST();		
		int pages = navigate.getActivePage();
		navigate.FIRST();
		
		//Go through each page and verify 
		for (int i  = 1; i <= pages ; i++){
			for (int row = 0 ; row < tblHouseKeeperRows.size() ; row++){
				List<WebElement> columns = tblHouseKeeperRows.get(row).findElements(By.tagName("td"));
				
				String workingTime = columns.get(5).getText();
				if (!workingTime.equals("00:00")){
					//convert the scheduled hours to minutes
					scheduledMinutes = convertHoursToMinutes(workingTime);
					
					//take in account the break minutes
					scheduledMinutes = scheduledMinutes - breakMinutes;
					
					//Calculate what the Maximum merits should be
					expectedMaxMerits = scheduledMinutes/meritMultiplier;
					
					//Verify that the UI displays the correct maximum mertis
					if (!formatter.format(expectedMaxMerits).equals(columns.get(7).getText())) {
						meritsCorrect = false;
						System.out.println("Page number: " + i);
						System.out.println("Row number: " +  (row + 1));
						System.out.println("Expected maximum merits: " + formatter.format(expectedMaxMerits));
						System.out.println("Actual maximum merits: " + columns.get(7).getText());
					}	
				}
			}
			navigate.NEXT();		
		}
		//Go through each page and verify 
		for (int i  = 1; i <= getNumOfTablePages(); i++){
			for (int x = 1; x <= tblHousekeepers.getRowCount(); x++){
				
				//convert the scheduled hours to minutes
				scheduledMinutes = convertHoursToMinutes(tblHousekeepers.getCellData(driver, x, 6));
				
				//take in account the break minutes
				scheduledMinutes = scheduledMinutes - breakMinutes;
				
				//Calculate what the Maximum merits should be
				expectedMaxMerits = scheduledMinutes/meritMultiplier;

				//Verify that the UI displays the correct maximum mertis
				if (!formatter.format(expectedMaxMerits).equals(tblHousekeepers.getCellData(driver, x, 8))) {
					meritsCorrect = false;
					System.out.println("Page number: " + i);
					System.out.println("Row number: " + x);
					System.out.println("Expected maximum merits: " + formatter.format(expectedMaxMerits));
					System.out.println("Actual maximum merits: " + tblHousekeepers.getCellData(driver, x, 8));
				}
				
			}
		}
		
		
		return meritsCorrect;
	}
	
	*//**
	 * @summary Converts the scheduled hours from the daily schedule into minutes.  The format that is
	 * 			passed in will be 05:00, 10:15, 08:30, 06:45, etc
	 * @version Created 10/10/2014
	 * @author 	Jessica Marshall
	 * @param 	The scheduled hours from the daily schedule table
	 * @throws 	NA
	 * @return 	double minutes
	 *//*
	public Double convertHoursToMinutes(String hours){

		if (hours.endsWith("15"))
			hours = hours.replace("15", "25");
		if (hours.endsWith("30"))
			hours = hours.replace("30", "50");
		if (hours.endsWith("45"))
			hours = hours.replace("45", "75");
		hours = hours.replace(":", ".");
		return Double.parseDouble(hours) * 60;			
	}
	
	*//**
	 * @summary Gets the number of pages in the daily schedule
	 * @version Created 10/10/2014
	 * @author 	Jessica Marshall
	 * @param 	NA
	 * @throws 	NA
	 * @return 	integer number of pages
	 *//*
	public int getNumOfTablePages(){
		int count = 0;
		//dr-dscr-act rich-datascr-act 
		count = tblTableScroller.findElements(By.cssSelector("td[class *= 'dr-dscr']")).size();
		//6 of the rows in the table are not pages they are <,<< etc
		return count-6;
	}
	
	*//**
	 * @summary Create an interface for the navigation bar
	 * @version Created 10/15/2014
	 * @author 	Justin Phlegar
	 * @param 	NA
	 * @throws 	NA
	 *//*
	private enum NavigationBar{
		FIRST {
			@Override public void activate() { 
				eleNavigationBar.get(0).click(); 
				new ProcessingYourRequest().WaitForProcessRequest(driver);
				TestReporter.log("Clicked Navigation Bar First Button.");
				
	          }
		},
		
		PREVIOUS {
			@Override public void activate() { 
				eleNavigationBar.get(1).click(); 
				new ProcessingYourRequest().WaitForProcessRequest(driver);
				TestReporter.log("Clicked Navigation Bar Previous Button.");
				
	          }
		},
		
		NEXT {
			@Override public void activate() { 
				eleNavigationBar.get(eleNavigationBar.size() -2).click();
				new ProcessingYourRequest().WaitForProcessRequest(driver);
				TestReporter.log("Clicked Navigation Bar Next Button.");
				
	          }
		},
		
		LAST {
			@Override public void activate() { 				
				eleNavigationBar.get(eleNavigationBar.size() -1).click(); 
				new ProcessingYourRequest().WaitForProcessRequest(driver);
				TestReporter.log("Clicked Navigation Bar Last Button.");
	          }
		}
		;
		
		public abstract void activate();
		
		public static int getActivePage(){
			for (int c = 0 ; c < eleNavigationBar.size() ; c++){
				if (eleNavigationBar.get(c).getAttribute("class").contains("dr-dscr-act")){
					return Integer.parseInt(eleNavigationBar.get(c).getText());
				}
			}
			return 0;
		 }
	}
}


*/