package apps.lilo.housekeeping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import core.interfaces.Button;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import utils.Datatable;

public class HouseKeeperDailySchedulerPage {
	//
	// Page Fields
	//
	private Datatable datatable = new Datatable();
	private static WebDriver driver;
	
	// ******************************************************
	// *** House Keeper Daily Scheduler Page Elements ***
	// ******************************************************
	// Main Elements
	// Grab Add Housekeeper Button
	@FindBy(id = "manageHousekeepingDailyschedule:add")
	private static Button btnAddHouseKeeper;

	// Grab Delete Housekeeper Button
	@FindBy(id = "manageHousekeepingDailyschedule:delete")
	private static Button btnDeleteHouseKeeper;

	// Grab Search Text Field
	@FindBy(id = "manageHousekeepingDailyschedule:filterValueText")
	private static Textbox txtSearchForHouseKeeper;

	// Secondary Elements
	// Grab Save Housekeeper Button

	// Grab Cancel Housekeeper Button

	// Grab Housekeeper Last Name Text Field

	// Grab Housekeeper First Name Text Field

	// Grab Housekeeper Seniority Text Field

	// Grab Housekeeper Personnel ID Text Field

	// Grab Housekeeper Role Field

	// Grab Housekeeper Scheduled Hours Text Field

	// Grab Housekeeper Adjusted Merrits Text Field

	// *********************
	// ** Build page area **
	// *********************

	/**
	 * @summary Constructor to initialize the page
	 * @version Created 9/11/2014
	 * @author Brian Buckhana
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public HouseKeeperDailySchedulerPage(WebDriver driver) {
		HouseKeeperDailySchedulerPage.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Constructor to initialize the page
	 * @version Created 9/11/2014
	 * @author Brian Buckhana
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public static HouseKeeperDailySchedulerPage initialize() {
		return ElementFactory.initElements(driver,
				HouseKeeperDailySchedulerPage.class);
	}

	// *********************
	// ** Main Functions **
	// *********************
	// Adds a Housekeeper account
	public void AddHouseKeeper(String scenario) throws Exception {
		/*datatable.setDatatableSheet("CheckInInfo"); // Need a dataTable for
													// this.
		datatable.setDatatableRow(datatable.getRowContains(scenario, 0));*/

		datatable.setVirtualtablePage("CheckInInfo");
		datatable.setVirtualtableScenario(scenario);
		
//		final String[] arrHouseKeeperLastName = datatable.getDataParameter(
//				"HouseKeeperLastName").split(";");
//		final String[] arrHouseKeeperFirstName = datatable.getDataParameter(
//				"HouseKeeperFirstName").split(";");
//		final String[] arrHouseKeeperSeniority = datatable.getDataParameter(
//				"HouseKeeperSeniority").split(";");
//		final String[] arrHouseKeeperPersonnelID = datatable.getDataParameter(
//				"HouseKeeperPersonnelID").split(";");
		// final String[] arrHouseKeeperRole =
		// datatable.getDataParameter("HouseKeeperRole").split(";");
//		final String[] arrHouseKeeperScheduledHours = datatable.getDataParameter("HouseKeeperScheduledHours").split(";");
//		final String[] arrHouseKeeperAdjustedMerrits = datatable.getDataParameter("HouseKeeperAdjustedMerrits").split(";");

		// Click Add HouseKeeper Button

		// Verify HouseKeeper fields dialogue box loaded

		// Enter HouseKeeper information

		// Click Save HouseKeeper button

	}

	// Removes a Housekeeper account
	public void RemoveHouseKeeper() throws Exception {

	}

	// Updates a Housekeeper account
	public void UpdateHouseKeeper(String scenario) throws Exception {

	}
}

