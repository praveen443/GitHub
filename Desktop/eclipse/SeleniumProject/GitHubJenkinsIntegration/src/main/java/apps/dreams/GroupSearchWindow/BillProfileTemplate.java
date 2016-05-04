package apps.dreams.GroupSearchWindow;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.dreams.PleaseWait.PleaseWait;
import core.WindowHandler;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.TestReporter;
import utils.date.DateTimeConversion;

/**
 * @summary Contains the methods & objects for the Dreams UI content frame for
 *          Bill Profile Template window.
 * @version Created 03/02/2016
 * @author Praveen Namburi
 */
public class BillProfileTemplate {
	
	// ****************************
	// *** Content Frame Fields ***
	// ****************************	
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	
	// ******************************
	// *** Content Frame Elements ***
	// ******************************
	
	@FindBy(name="Add")
	private Button btnAdd;
	
	@FindBy(name = "billingDescription")
	private Textbox txtBillDescription;
	
	@FindBy(name = "facilityIdStr")
	private Listbox lstFacilityId;
	
	@FindBy(name = "billCode")
	private Textbox txtBillCode;
	
	@FindBy(name = "billingStartDateAsString")
	private Textbox txtBillingStartDate;
	
	@FindBy(name = "billingEndDateAsString")
	private Textbox txtBillingEndDate;
	
	@FindBy(xpath = "html/body/form/table/tbody")
	private WebElement eleBillingProfileTbl;
	
	@FindBy(name = "okButton")
	private Button btnOk;
	
	// *********************
	// ** Build page area **
	// *********************

	/**
	 * @summary Constructor to initialize the frame
	 * @version Created 03/02/2016
	 * @author Praveen Namburi
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public BillProfileTemplate(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);
	}
	
	private BillProfileTemplate initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnAdd);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}
	
	// ***********************************
	// *** Content Frame Interactions ***
	// ***********************************

	/**
	 * @summary: Method to click on Add button in BillProfileTemplate.
	 * @version: Created 03/08/2016
	 * @author Praveen Namburi, @return NA
	 */
	public void clickbtnAdd(){
		pageLoaded(btnAdd);
		btnAdd.highlight(driver);
		btnAdd.jsClick(driver);
	}
	
	public void clickbtnOk(){
		pageLoaded(btnOk);
		btnOk.highlight(driver);
		btnOk.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
    	WindowHandler.waitUntilWindowExistsTitleContains(driver, "Disney Reservation");
	}
	
	/**
	 * @summary Method to set start and end dates based on daysOut and Num.Of nights to stay
	 * @version Created 03/08/2016, @author Praveen namburi 
	 * @param scenarios, @return NA
	 */
	public void setStartAndEndDates(String daysOut, String numberNights){
		
		String startDate = DateTimeConversion.getDaysOut(daysOut, "MM/dd/yyyy");
		String endDate = DateTimeConversion.getDaysOut(numberNights, "MM/dd/yyyy");
		TestReporter.log("Start Date : " + startDate);
		TestReporter.log("End Date : " + endDate);
		
		txtBillingStartDate.highlight(driver);
		txtBillingStartDate.safeSet(startDate);
		
		txtBillingEndDate.highlight(driver);
		txtBillingEndDate.safeSet(endDate);
	}
	
	/**
	 * @summary Method to enter Billing Profile Template details and click on add button.
	 * @version Created 03/08/2016, @author Praveen Namburi
	 * @param scenario, @return NA
	 */
	public void addBillingProfileTemplateDetails(String scenario) {
		datatable.setVirtualtablePage("BillingProfileTemplate");
		datatable.setVirtualtableScenario(scenario);

		String facilityID  = datatable.getDataParameter("FacilityId");
		String billCode  = datatable.getDataParameter("BillCode");
		String billDescription = datatable.getDataParameter("BillDescription");
		String daysOut = datatable.getDataParameter("DaysOut");
		String numOfNights = datatable.getDataParameter("NumOfNights");
		
		initialize();
		pageLoaded(lstFacilityId);
		lstFacilityId.isEnabled();
		lstFacilityId.select(facilityID);
		pageLoaded(txtBillCode);
		txtBillCode.safeSet(billCode);
		txtBillDescription.safeSet(billDescription);
		setStartAndEndDates(daysOut, numOfNights);
		
		//click on Add button.
		clickbtnAdd();
		
	}
	
	/**
	 * @summary: Method to validate First time added billing profile template details.
	 * @author: Praveen Namburi, @version: Created 03/02/2016
	 * @param scenario
	 */
	public void verifyFirstBillingProfileDetailsIsAdded(){
		/*WebElement formId = driver.findElement(By.xpath("html/body/form/table/tbody"));*/
		List<WebElement> totalRows = eleBillingProfileTbl.findElements(By.tagName("tr"));
		TestReporter.log("Total no. of rows after adding Billing profile Template details: "
						+totalRows.size());
		TestReporter.assertGreaterThanZero(totalRows.size());
		
		//If billling profile is added for the first time, then there will be total of 8 rows.
		TestReporter.assertTrue(totalRows.size() > 7, 
		"Added the First Billing Profile Template details in the table.");
	}
	
	/**
	 * @summary: Method to validate Second time added billing profile template details.
	 * @author: Praveen Namburi, @version: Created 03/02/2016
	 * @param scenario
	 */
	public void verifySecondBillingProfileDetailsIsAdded(){
		/*WebElement formId = driver.findElement(By.xpath("html/body/form/table/tbody"));*/
		List<WebElement> totalRows = eleBillingProfileTbl.findElements(By.tagName("tr"));
		TestReporter.log("Total no. of rows after adding Billing profile Template details: "
						+totalRows.size());
		TestReporter.assertGreaterThanZero(totalRows.size());
		
		//If billling profile is added for the second time, then there will be total of 9 rows.
		TestReporter.assertTrue(totalRows.size() > 8, 
		"Added the Second Billing Profile Template details in the table.");
	}
	
	/**
	 * @summary: Method to verify the added Billing profile Template is deleted.
	 *@author: praveen Namburi,@Version: created 3/9/2016
	 */
	public void verifyBillingProfileIsDeleted(){
		//Verifying the rows before deleting the Billing profile template.
		List<WebElement> beforeRowsCount = eleBillingProfileTbl.findElements(By.tagName("tr"));
		TestReporter.log("Total no. of rows Before Deleting Billing profile Template is : "
						+beforeRowsCount.size());
		TestReporter.assertTrue(beforeRowsCount.size() > 7, "Added billing profile details Exist in table.");
		
		List<WebElement> delButtons = driver.findElements(By.xpath("//input[@value='Del']"));
		TestReporter.log("Total no. of delete buttons : "+delButtons.size());
		for(WebElement del : delButtons){
			Element delButton = new ElementImpl(del);
			if(delButton.syncVisible(driver, 2, false)){
				delButton.highlight(driver);
				delButton.jsClick(driver);
				break;
			}
		}
		
		initialize();
		//Verifying the rows after deleting the Billing profile template.
		List<WebElement> afterRowsCount = eleBillingProfileTbl.findElements(By.tagName("tr"));
		TestReporter.log("Total no. of rows Before Deleting Billing profile Template is : "
						+afterRowsCount.size());
		TestReporter.assertTrue(afterRowsCount.size() < 8, 
				"Deleted the added billing profile Template from the table.");
			
	}
	
}



