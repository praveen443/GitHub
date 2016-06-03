package apps.lilo.housekeeping;

import java.util.List;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.interfaces.Button;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

/**
 * 
 * @summary Contains the page methods & objects for the Print board options page
 * @version Created 10/16/2014
 * @author Jessica Marshall
 */
public class PrintPreviewAMBoardsPage {
	//
	// Page Fields
	//
	private Datatable datatable = new Datatable();
	private WebDriver driver;
	
	// *************************************
	// *** Page Elements ***
	// *************************************
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
	public PrintPreviewAMBoardsPage(WebDriver driver) {
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
	public PrintPreviewAMBoardsPage initialize() {
		return ElementFactory.initElements(driver,
				PrintPreviewAMBoardsPage.class);
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
		return new PageLoaded().isPageHTMLLoaded(PrintPreviewAMBoardsPage.class, driver, btnClosePreview); 
	}
	
	// *****************************************
	// ***Page Interactions ***
	// *****************************************
	/**
	 * @summary Checks one of the many print option checkboxes on the print options page,
	 * 			pass in the text that displays for the checkbox you want to check
	 * @version Created 10/3/2014
	 * @author Jessica Marshall
	 * @param checkboxText 
	 * @throws NA
	 * @return NA
	 */	

	
	public void clickClosePrintPreview(){
		btnClosePreview.syncVisible(driver);
		btnClosePreview.click();
		btnClosePreview.syncHidden(driver);
	}
	
	public boolean verifyImagesForRoomStatus(String roomStatus, String image){
		String displayedStatus;
		//Get a list of all the different tables, each board has its own table
		//printBoard:printBoardTable:3:assignedRoomsTable
		List<WebElement> boardTableList = driver.findElements(By.cssSelector("table[id*= 'printBoard:printBoardTable:']"));
		for (WebElement boardTable: boardTableList){
			
			//Get a list of all the rows
			//dr-table-firstrow rich-table-firstrow 
			List<WebElement> tableRowList = boardTable.findElements(By.cssSelector(".dr-table-firstrow rich-table-firstrow"));
			
			//Get the status for that row
			//printBoard:printBoardTable:0:assignedRoomsTable:1:Status
			for (WebElement tableRow: tableRowList){
				displayedStatus = tableRow.findElement(By.cssSelector("span[id^='printBoard:printBoardTable:'][id$='Status']")).getText();
				System.out.println("Displayed status: " + displayedStatus);
				//if the status matches the expected status, then check to see if an image is displayed
				if (displayedStatus.equalsIgnoreCase(roomStatus)){
					if (tableRow.findElement(By.tagName("img")).isDisplayed()){
						
						//if the image is displyaed, make sure it is the correct image
						System.out.println("Actual: " + tableRow.findElement(By.tagName("img")).getAttribute("title"));
						System.out.println("Expected: " + image);
						if (tableRow.findElement(By.tagName("img")).getAttribute("title").equalsIgnoreCase(image)) {
							return true;
						}else
							return false;
					}else
						return false;
				}
			}
		}
		return false;
	}
	

	public boolean verifyAllGuestNamesNotNull(){
		String displayedName;
		boolean valid= true;
		//Get a list of all the different tables, each board has its own table
		//printBoard:printBoardTable:3:assignedRoomsTable
		List<WebElement> boardTableList = driver.findElements(By.cssSelector("table[id*= 'printBoard:printBoardTable:']"));
		for (WebElement boardTable: boardTableList){
			
			//Get a list of all the rows
			//dr-table-firstrow rich-table-firstrow 
			List<WebElement> tableRowList = boardTable.findElements(By.cssSelector(".dr-table-firstrow"));
			
			//Get the status for that row
			//printBoard:printBoardTable:0:assignedRoomsTable:1:Status
			for (WebElement tableRow: tableRowList){
				displayedName= tableRow.findElement(By.cssSelector("span[id^='printBoard:printBoardTable:'][id$='currentGuestName']")).getText();

				//if the status matches the expected status, then check to see if an image is displayed
				if (displayedName.toString().toLowerCase().contains("null")){
					valid = false;
				}
			}
		}
		return valid;
	}
	

	public boolean verifyInstructionsDispley(String instruction){

		//Get a list of all the housekeeping instructions
		//printBoard:printBoardTable:0:dailyInstructionID:1:addtionalInsText
		//printBoard:printBoardTable:0:dailyInstructionID:0:addtionalInsText
		List<WebElement> instructionList = driver.findElements(By.xpath("//span[contains(@id, 'dailyInstructionI')]"));
		for (WebElement element: instructionList){
			if (element.getText().equalsIgnoreCase(instruction)){
				return true;
			}
			
		}
		return false;
	}
}

