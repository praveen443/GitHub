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
import core.interfaces.impl.ButtonImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Mouse;
import utils.Sleeper;
import utils.TestReporter;

public class CleaningSequencePage {
	//
	// Page Fields
	//
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	
	
	 * Create Breaks Column positions
	 
	@SuppressWarnings("unused")
	private final int BREAK_TYPE = 0;
	@SuppressWarnings("unused")
	private final int BREAK_PRIORITY = 1;
	@SuppressWarnings("unused")
	private final int BREAK_LIMIT = 2;
	@SuppressWarnings("unused")
	private final int INCREMENTAL_VALUE = 3;

	//
	// Page Elements
	//
	// Break table 
	@FindBy(xpath = "//tbody[@id='cleaningSequence:breakTable:tb']/tr")
	private List<WebElement> tblBreaks;

	// Break table 
	@FindBy(xpath = "//tbody[@id='cleaningSequence:roomTable:tb']/tr")
	private List<WebElement> tblUnassignedRooms;

	// Break table 
	@FindBy(xpath = "//tbody[@id='cleaningSequence:cleaningSeqTable:tb']/tr")
	private List<WebElement> tblCleaningSequence;
	
	// Break table 
	@FindBy(xpath = "//table[@id='cleaningSequence:cleaningSeqTable']/thead")
	private WebElement tblCleaningSequenceHeader;
	
	//Navigation bar for Unassigned Rooms
	@FindBy(xpath = "//table[@id='cleaningSequence:roomTableScroller_table']/tbody/tr/td")
	private List<WebElement> eleUnassignedRoomsNavigationBar;
	
	//Navigation bar for Unassigned Rooms
	@FindBy(xpath = "//table[@id='cleaningSequence:cleaningSeqTableScroller_table']/tbody/tr/td")
	private List<WebElement> eleCleaningSequenceNavigationBar;
		
	// Select Button
	@FindBy(xpath="//input[@type='button'][@value='select >']")
	private Button btnSelect;
	
	// Remove Button
	@FindBy(xpath="//input[@type='button'][@value='< remove  ']")
	private Button btnRemove;
	
	// Up Button
	@FindBy(xpath="//input[@type='button'][@value='Up  ']")
	private Button btnUp;
	
	// Down Button
	@FindBy(xpath="//input[@type='button'][@value='Down  ']")
	private Button btnDown;
	
	// Save Button
	@FindBy(id ="cleaningSequence:save")
	private Button btnSave;
	
	// Close Button
	@FindBy(id ="cleaningSequence:close")
	private Button btnClose;
	
	// Information Saved Button
	@FindBy(xpath="//table[@id='saveSuccessfulModalPanelContentTable']/tbody/tr/td/form/table[@class='panelBackgroundModalPanel']/tbody/tr/td/input[@type='button'][@value='Close']")
	private Button btnInfoSavedClose;
		
	// Warning Yes Button
	@FindBy(xpath="//table[@class='panelBackgroundModalPanel']/tbody/tr/td/input[@type='submit'][@value='Yes']")
	private Button btnYesWarningPopup;
	
	//Error popup
	@FindBy(id = "houskeepingErrorModalPanel1ContentTable")
	private Element eleErrorPopup;
	
	// *********************
	// ** Build page area **
	// *********************
	
	public CleaningSequencePage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public CleaningSequencePage initialize() {
		return ElementFactory.initElements(driver,
				this.getClass());
	}

	public boolean loadPage() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnSave); 
	}

	// **************************************************
	// ***Housekeeping Cleaning Sequence Interactions ***
	// **************************************************
	
	public int getRowInBreakTypes(String text){
		for (int row = 0 ; row < tblBreaks.size() ; row ++){
			if (tblBreaks.get(row).findElement(By.xpath("td/span")).getText().equalsIgnoreCase(text)) return row;
		}
		return -1;
	}
	
	public int getRowInUnassignedRooms(String text){
		NavigationBar navigate = new NavigationBar(eleUnassignedRoomsNavigationBar);
		navigate.LAST();	
		int pages = navigate.getActivePage();
		for(navigate.FIRST(); navigate.getActivePage() <= pages ; navigate.NEXT()){	
			for (int row = 0 ; row < tblUnassignedRooms.size() ; row ++){
				if (tblUnassignedRooms.get(row).findElement(By.xpath("td/span")).getText().equalsIgnoreCase(text)) return row;
			}
		}
		return -1;
	}
	
	public int getRowInCleaningSequence(String text){
		NavigationBar navigate = new NavigationBar(eleCleaningSequenceNavigationBar);
		navigate.LAST();	
		int pages = navigate.getActivePage();
		for(navigate.FIRST(); navigate.getActivePage() <= pages ; navigate.NEXT()){	
			for (int row = 0 ; row < tblCleaningSequence.size() ; row ++){
				if (tblCleaningSequence.get(row).findElement(By.xpath("td[2]/span")).getText().equalsIgnoreCase(text)) return row;
			}
		}
		return -1;
	}	

	public int getLastRowInCleaningSequence(String text){
		NavigationBar navigate = new NavigationBar(eleCleaningSequenceNavigationBar);
		navigate.LAST();	
				return tblCleaningSequence.size() - 1;
		for (int row = 0 ; row < tblCleaningSequence.size() ; row ++){
			if (tblCleaningSequence.get(tblCleaningSequence.size() - 1).findElement(By.xpath("td[2]/span")).getText().equalsIgnoreCase(text)) return row;
		}
		
		return -1;
	}	
	public String getTextOfRowInCleaningSequnce(String firstOrLastPage){
		NavigationBar navigate = new NavigationBar(eleCleaningSequenceNavigationBar);
		if (firstOrLastPage.equalsIgnoreCase("last")) navigate.LAST();
		return tblCleaningSequence.get(tblCleaningSequence.size() - 1).findElement(By.xpath("td[2]/span")).getText().trim();
	}
	
	public String getTextOfRowInCleaningSequnce(String firstOrLastPage, int row){
		NavigationBar navigate = new NavigationBar(eleCleaningSequenceNavigationBar);
		if (firstOrLastPage.equalsIgnoreCase("last")) navigate.LAST();
		return tblCleaningSequence.get(row).findElement(By.xpath("td[2]/span")).getText().trim();
	}
	
	public void removeFromCleaningSequnce(String text){
		int row = getRowInCleaningSequence(text);
		tblCleaningSequence.get(row).click();
		btnRemove.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	

	public void removeLastRowFromCleaningSequnce(String text){
		int row = getLastRowInCleaningSequence(text);
		new ButtonImpl(tblCleaningSequence.get(row)).focusClick(driver);
		btnRemove.focusClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void moveBreakTypeToCleaningSequence(String text){
		int row = getRowInBreakTypes(text);
		//new ButtonImpl(tblBreaks.get(row)).mouseClick();
		new ButtonImpl(tblBreaks.get(row)).focusClick(driver);
		btnSelect.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void moveUnassignedRoomToCleaningSequence(String text){
		int row = getRowInUnassignedRooms(text);
		tblUnassignedRooms.get(row).click();
		btnSelect.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void dragAndDropBreakTypeToCleaningSequence(String text){
		int row = getRowInBreakTypes(text);
		//new ElementImpl(tblBreaks.get(row)).mouseHover();
		//new ElementImpl(tblBreaks.get(row)).focusClick();
		
		//new Mouse(driver).dragAndDrop(tblBreaks.get(row).findElement(By.xpath("td")), tblCleaningSequenceHeader);
		Actions builder = new Actions(driver);
		Action dragAndDrop = builder.clickAndHold(tblBreaks.get(row).findElement(By.xpath("td")))
				.moveToElement(tblCleaningSequenceHeader)
				.pause(3000)
				.release()
				.build();
		dragAndDrop.perform();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void dragAndDropUnassignedRoomToCleaningSequence(String text){
		int row = getRowInUnassignedRooms(text);
		new Mouse(driver).dragAndDrop(tblUnassignedRooms.get(row), tblCleaningSequence.get(0));
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public boolean validateTextInUnassignedRooms(String text, boolean expectText){
		boolean found = false;
		if (getRowInUnassignedRooms(text) > -1) found = true;
		
		if(expectText == true && found == true) return true;
		else if (expectText == true && found == false) return false;
		else if (expectText == false && found == true) return false;
		else if (expectText == false && found == false) return true;
		
		return false;
	}

	public boolean validateTextInCleaningSequence(String text, boolean expectText){
		boolean found = false;
		if (getRowInCleaningSequence("first") > -1) found = true;
		
		if(expectText == true && found == true) return true;
		else if (expectText == true && found == false) return false;
		else if (expectText == false && found == true) return false;
		else if (expectText == false && found == false) return true;
		
		return false;
	}
	
	public boolean validateTextInCleaningSequenceAfterMove(String text, boolean expectText){
		boolean found = false;
		if (getTextOfRowInCleaningSequnce("last").equalsIgnoreCase(text)) found = true;
		
		if(expectText == true && found == true) return true;
		else if (expectText == true && found == false) return false;
		else if (expectText == false && found == true) return false;
		else if (expectText == false && found == false) return true;
		
		return false;
	}
	
	public boolean clickSave(boolean expectError){
		BoardsHeader header = new BoardsHeader(driver);
		btnSave.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		btnYesWarningPopup.focusClick(driver);	
		Sleeper.sleep(15000);
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
	
	public  void checkForError(boolean expectError){
		if(expectError){
			if(eleErrorPopup.syncVisible(driver,3, false)){
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