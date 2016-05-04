package apps.lilo.housekeeping;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.date.DateTimeConversion;

/**
 * 
 * @summary Contains the page methods & objects for the Add Housekeeper schedule page
 * @version Created 10/6/2014
 * @author Jessica Marshall
 */
public class AddHousekeeperSchedulePage {
	//
	// Page Fields
	//
	private String lastName;
	private String personnelID;
	private Datatable datatable = new Datatable();
	private WebDriver driver;
	
	// *************************************
	// *** HousekeepingLinksPage Elements ***
	// *************************************
	
	// Last name 
	@FindBy(id = "addDailyHouekeeperScheduleForm:housekeeperLastNameText")
	private Textbox txtLastName;
	
	// First name 
	@FindBy(id = "addDailyHouekeeperScheduleForm:housekeeperFirstNameText")
	private Textbox txtFirstName;
	
	// Senority date
	@FindBy(id = "addDailyHouekeeperScheduleForm:seniorityDateTextInputDate")
	private Textbox txtSenority;
	
	// Personnel ID - must be unique
	@FindBy(id = "addDailyHouekeeperScheduleForm:pernerNumberText")
	private Textbox txtPersonnel;
	
	// Housekeeper role select
	@FindBy(id = "addDailyHouekeeperScheduleForm:housekeeperRoleMenu")
	private Listbox lstRole;
	
	// Scheduled hours ( in the format HH:MM i.e. 08:30)
	@FindBy(id = "addDailyHouekeeperScheduleForm:scheduledHoursText")
	private Textbox txtHours;
	
	// Adjusted merits
	@FindBy(id = "addDailyHouekeeperScheduleForm:adjustedMeritsText")
	private Textbox txtMerits;

	// Save
	@FindBy(id = "addDailyHouekeeperScheduleForm:save")
	private Button btnSave;
	
	// Cancel
	@FindBy(id = "addDailyHouekeeperScheduleForm:close")
	private Button btnCancel;
	
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
	public AddHousekeeperSchedulePage(WebDriver driver) {
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
	public AddHousekeeperSchedulePage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
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
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnSave); 
	}

	// *****************************************
	// ***Page Interactions ***
	// *****************************************
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPersonnelID() {
		return personnelID;
	}

	public void setPersonnelID(String personnelID) {
		this.personnelID = personnelID;
	}
	
	public void clickSave() {
		btnSave.syncVisible(driver);
		btnSave.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
					
	}
	
	public void clickCancel() {
		btnCancel.syncVisible(driver);
		btnCancel.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
					
	}
	
	public void enterHousekeeperInfo(String scenario){
		/*datatable.setDatatableSheet("AddHousekeeperSchedule");
		datatable.setDatatableRow(datatable.getRowContains(scenario, 0));*/
		datatable.setVirtualtablePage("AddHousekeeperSchedule");
		datatable.setVirtualtableScenario(scenario);
		String senorityDate = "";
		senorityDate = DateTimeConversion.ConvertToDate(datatable.getDataParameter("SenorityDaysOut"));
		
		setLastName(datatable.getDataParameter("LastName"));
		setPersonnelID(datatable.getDataParameter("PersonnelID"));
		
		txtLastName.syncVisible(driver);
		txtLastName.set(getLastName());
		txtFirstName.set(datatable.getDataParameter("FirstName"));
		txtSenority.set(senorityDate);
		txtPersonnel.set(getPersonnelID());
		lstRole.select(datatable.getDataParameter("Role"));
		txtHours.set(datatable.getDataParameter("Hours"));
		txtMerits.set(datatable.getDataParameter("Merits"));
		
	}
	
	public void editHousekeeperInfo(String scenario){
		/*datatable.setDatatableSheet("EditHousekeeperSchedule");
		datatable.setDatatableRow(datatable.getRowContains(scenario, 0));*/
		
		datatable.setVirtualtablePage("EditHousekeeperSchedule");
		datatable.setVirtualtableScenario(scenario);
		
		String senorityDate = "";
		
		//only convert date if not null
		if (!datatable.getDataParameter("SenorityDaysOut").isEmpty()){
			senorityDate = DateTimeConversion.ConvertToDate(datatable.getDataParameter("SenorityDaysOut"));
		}
		
		setLastName(datatable.getDataParameter("LastName"));
		setPersonnelID(datatable.getDataParameter("PersonnelID"));
		
		txtLastName.syncVisible(driver);
		txtLastName.set(getLastName());
		txtFirstName.set(datatable.getDataParameter("FirstName"));
		txtSenority.set(senorityDate);
		txtPersonnel.set(getPersonnelID());
		lstRole.select(datatable.getDataParameter("Role"));
		txtHours.set(datatable.getDataParameter("Hours"));
		txtMerits.set(datatable.getDataParameter("Merits"));
		
	}
	
	
}

