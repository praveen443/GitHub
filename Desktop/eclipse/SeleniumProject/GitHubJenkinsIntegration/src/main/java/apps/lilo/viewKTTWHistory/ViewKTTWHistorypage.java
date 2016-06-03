package apps.lilo.viewKTTWHistory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

/**
 * 
 * @summary Contains the page methods & objects for the ViewKTTWHistory page
 *          after adding room access for a guest.
 * 
 * @version Created 11/8/2015
 * @author Praveen Namburi
 */

public class ViewKTTWHistorypage {

	// ******************************************
	// *** Main ViewKTTWHistory Page Elements ***
	// ******************************************
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	// Create Button object to close the KTTW History Form
	@FindBy(id = "kttwHistoryForm:closeButtonId")
	private Button btnKTTWHistoryClose;

	// *************************
	// **** Build page area ****
	// *************************

	public ViewKTTWHistorypage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public ViewKTTWHistorypage initialize(WebDriver driver) {
		return ElementFactory.initElements(driver, ViewKTTWHistorypage.class);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnKTTWHistoryClose);
	}

	// **********************************************
	// *** Main ViewKTTWHistory Page Interactions ***
	// **********************************************

	/**
	 * 
	 * @summary Close the VIew KTTW History page.
	 * 
	 * @version Created 11/08/2015
	 * @author Praveen Namburi
	 * @param NA
	 * @return NA
	 * 
	 */

	public void clickbtnClose() {
		pageLoaded();
		btnKTTWHistoryClose.syncVisible(driver);
		btnKTTWHistoryClose.click();

		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * 
	 * @summary Method to get the total no.of records from the View kTTW History
	 *          page and validate it.
	 * 
	 * @version Created 11/09/2015
	 * @author Praveen Namburi
	 * @param NA
	 * 
	 * @throws Exception
	 * @return NA
	 * 
	 */
	public void getTotalKTTWRecordsAndValidateIt(String scenario) {

		datatable.setVirtualtablePage("EnterQuickBookAccommodationInfo");
		datatable.setVirtualtableScenario(scenario);
		int numOfRooms = Integer.parseInt(datatable
				.getDataParameter("NumberRooms"));

		// Locate the webtable Id
		WebElement table = driver.findElement(By
				.id("kttwHistoryForm:kttwHistoryList:tb"));

		// get the list of rows from the table
		java.util.List<WebElement> Totalrows = table.findElements(By
				.tagName("tr"));

		// get the rowcount
		int getRowscount = Totalrows.size();

		// print the rows count
		System.out.println("Total no. of rows in the KTTW Table is : "
				+ getRowscount);

		// Validating the records in the ViewKTTWHistory page.
		Assert.assertEquals(Totalrows.size(), numOfRooms);

		// Close the KTTW History page.
		clickbtnClose();
	}

}
