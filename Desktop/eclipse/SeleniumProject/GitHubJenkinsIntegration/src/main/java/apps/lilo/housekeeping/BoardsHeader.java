package apps.lilo.housekeeping;

import java.sql.Timestamp;
import java.util.List;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Label;
import core.interfaces.Webtable;
import core.interfaces.impl.LabelImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;

public class BoardsHeader {
	//
	// Page Fields
	//
	private Datatable datatable = new Datatable();
	private WebDriver driver;

	//
	// Page Elements
	//
	// Current HK Board Type
	@FindBy(id = "headerForm:boardTypeName")
	private Label lblBoardType;

	// Select board type arrow button
	@FindBy(id = "headerForm:selectBoardType")
	private Button btnSelectBoardType;

	// Table for Board Type options
	@FindBy(id = "boardTypeListForm")
	private Webtable tblBoardTypes;

	// Select Button for Section Board Type popup
	@FindBy(css = "input[\"value=Select\"]")
	private Button btnBoardTypeSelect;

	// CloseButton for Section Board Type popup
	@FindBy(css = "input[\"value=Close\"]")
	private Button btnBoardTypeClose;

	// Error panel
	@FindBy(className = "errorLabel")
	private Label lblErrorMessage;

	// *********************
	// ** Build page area **
	// *********************

	/**
	 * @summary Constructor to initialize the page
	 * @version Created 10/10/2014
	 * @author Justin Phlegar
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public BoardsHeader(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Constructor to initialize the page
	 * @version Created 10/10/2014
	 * @author Justin Phlegar
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public BoardsHeader initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Constructor to initialize the page
	 * @version Created 10/10/2014
	 * @author Justin Phlegar
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public boolean loadPage() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnSelectBoardType);
	}

	// *****************************************
	// ***Page Interactions ***
	// *****************************************

	public boolean selectBoardType(String boardType) {
		boolean found = false;
		if (!(lblBoardType.getText().equalsIgnoreCase(boardType))) {
			btnSelectBoardType.click();
			new ProcessingYourRequest().WaitForProcessRequest(driver);
			tblBoardTypes.syncVisible(driver);
			Sleeper.sleep(2000);

			List<WebElement> lblBoardTypeList = tblBoardTypes.findElements(By
					.className("dr-table-cell"));
			for (WebElement eleBoardType : lblBoardTypeList) {
				if (eleBoardType.getText().equalsIgnoreCase(boardType)) {
					/*Label board = new LabelImpl(eleBoardType);
					board.focus(driver);
					board.click();*/
					// board.focusClick();
					Sleeper.sleep(1000);
					//board.sendKeys(Keys.ENTER);
					// boardTypeListForm:j_id289
					// ((JavascriptExecutor)driver).executeScript("document.getElementById('boardTypeListForm:j_id289').click");
					found = true;
					break;
				}

			}
			if (!found)
				return false;

			new ProcessingYourRequest().WaitForProcessRequest(driver);
			loadPage();

		}

		return true;
	}

	public boolean checkBoardErrors(boolean expectError) {
		boolean valid = true;
		boolean found = lblErrorMessage.syncVisible(driver, 3, false);
		if (expectError == true && found == true) {
			Reporter.log(new Timestamp(new java.util.Date().getTime())
					.toString()
					+ " :: Checking for Board Errors. Error found as expected");
			Reporter.log("Error message: " + lblErrorMessage.getText());
		} else if (expectError == false && found == true) {
			Reporter.log(new Timestamp(new java.util.Date().getTime())
					.toString()
					+ " :: Checking for Board Errors. Error found and not expected");
			Reporter.log("Error message: " + lblErrorMessage.getText());
			valid = false;
		} else if (expectError == true && found == false) {
			Reporter.log(new Timestamp(new java.util.Date().getTime())
					.toString()
					+ " :: Checking for Board Errors. Error not found but expected");
			valid = false;
		} else if (expectError == false && found == false) {
			Reporter.log(new Timestamp(new java.util.Date().getTime())
					.toString()
					+ " :: Checking for Board Errors. Error not found as expected");
		}

		return valid;
	}

}

