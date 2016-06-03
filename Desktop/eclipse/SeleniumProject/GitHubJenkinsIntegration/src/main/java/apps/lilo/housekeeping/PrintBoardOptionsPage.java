package apps.lilo.housekeeping;

import java.util.List;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Textbox;
import core.interfaces.impl.CheckboxImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

/**
 * 
 * @summary Contains the page methods & objects for the Print board options page
 * @version Created 10/3/2014
 * @author Jessica Marshall
 */
public class PrintBoardOptionsPage {
	//
	// Page Fields
	//
	private Datatable datatable = new Datatable();
	private WebDriver driver;
	
	// *************************************
	// *** Page Elements ***
	// *************************************

	// Print
	@FindBy(name = "printoptionForm:j_id61")
	private Button btnPrint;
	
	// Close
	@FindBy(id = "printoptionForm:optionsClose")
	private Button btnClose;
	
	// Additional instructions
	@FindBy(id = "printoptionForm:pictureSection:0:instructionName")
	private Textbox txtInstructions;
	
	// Number of rows to sort by
	@FindBy(id = "printoptionForm:j_id46")
	private Textbox txtNumRows;
	
	// Error panel pop up
	@FindBy(id = "houskeepingErrorModalPanelContentTable")
	private Element eleErrorPopUp;
	
	// Error panel pop up ok
	@FindBy(id = "j_id117:j_id122")
	private Button btnOk;
	
	// Work bench report checkbox
	@FindBy(id = "printoptionForm:workBennchCheckbox")
	private Checkbox chkWorkBenchReport;
	
	// Select All Boards to Print option 
	@FindBy(id = "printoptionForm:printAllBoardCheckbox")
	private Checkbox chkPrintAllBoards;
	
	// Select All Print On Board options
	@FindBy(id = "printoptionForm:printAllCheckbox")
	private Checkbox chkPrintAllBoardOptions;
	
	//Container for all Print Board Options
	@FindBy(xpath="//span[@id='printoptionForm:printOptionOutputPanel']/table/tbody/tr/td")
	private List<WebElement> chkPrintBoardOptions;
	
	//Container for all Print Board Options
	@FindBy(xpath="//table[@id='printoptionForm:boardNumberTable:0:boardNumberCheckbox']/tbody/tr/td")
	private List<WebElement> chkPrintBoards;
		
	// Available housekeeper report checkbox
	@FindBy(id = "printoptionForm:availHskprsCheckbox")
	private Checkbox chkAvailHousekeeperReport;
	
	// Board summary report checkbox
	@FindBy(id = "printoptionForm:availHskprsCheckbox")
	private Checkbox chkSummaryReport;
	
	// Close print preview
	@FindBy(id = "printBoard:closeLink")
	private Button btnClosePreview;
	
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
	public PrintBoardOptionsPage(WebDriver driver) {
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
	public PrintBoardOptionsPage initialize() {
		return ElementFactory.initElements(driver,
				PrintBoardOptionsPage.class);
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
		return new PageLoaded().isPageHTMLLoaded(PrintBoardOptionsPage.class, driver, btnPrint); 
	}
	
	// *****************************************
	// ***Page Interactions ***
	// *****************************************
	
	public void clickPrintButton() {		
		btnPrint.syncVisible(driver);		
		//((JavascriptExecutor)driver).executeScript("document.getElementById('printoptionForm:j_id61').click()");
		//btnPrint.mouseClick();
		btnPrint.click();
	}
	
	/**
	 * @summary Checks one of the many print option checkboxes on the print options page,
	 * 			pass in the text that displays for the checkbox you want to check
	 * @version Created 10/3/2014
	 * @author Jessica Marshall
	 * @param checkboxText 
	 * @throws NA
	 * @return NA
	 */	
	public void checkAPrintBoardOption(String checkboxText){
		
		List <WebElement> checkBoxList = driver.findElements(By.cssSelector(("input[type = 'checkbox']")));
		for (WebElement checkbox:checkBoxList){
			//System.out.println(checkbox.getText());
			if (checkbox.getText().equalsIgnoreCase(checkboxText)){
				checkbox.click();
			}
		}
	}
	
	public void checkPrintBoardOptions(String checkboxText){
		String labelText = ""; 
		if (checkboxText.equalsIgnoreCase("all")){
			chkPrintAllBoardOptions.check();
		}else{
			for(int row = 0 ; row < chkPrintBoardOptions.size() ; row++){
				labelText = chkPrintBoardOptions.get(row).findElement(By.cssSelector("label")).getText();
				if (labelText.trim().equalsIgnoreCase(checkboxText)){
					new CheckboxImpl(chkPrintBoardOptions.get(row).findElement(By.cssSelector("input"))).click();
					break;
				}			
			}
		}
	}
	
	public void checkPrintBoard(String checkboxText){
		String labelText = ""; 
		if (checkboxText.equalsIgnoreCase("all")){
			chkPrintAllBoards.check();
		}else{
			for(int row = 0 ; row < chkPrintBoards.size() ; row++){
				labelText = chkPrintBoards.get(row).findElement(By.cssSelector("label")).getText();
				if (labelText.equalsIgnoreCase(checkboxText)){
					new CheckboxImpl(chkPrintBoards.get(row).findElement(By.cssSelector("input"))).click();
					break;
				}			
			}
		}
	}
	
	public void clickClosePrintPreview(){
		btnClosePreview.syncVisible(driver);
		btnClosePreview.click();
	}
	
	public void clickClose(){
		btnClose.syncVisible(driver);
		btnClose.click();
	}
	
	public void checkWorkBenchReport(){
		chkWorkBenchReport.syncVisible(driver);
		chkWorkBenchReport.check();
	}
	
	public void checkPrintAllBoards(){
		chkPrintAllBoards.syncVisible(driver);
		chkPrintAllBoards.check();
	}
}

