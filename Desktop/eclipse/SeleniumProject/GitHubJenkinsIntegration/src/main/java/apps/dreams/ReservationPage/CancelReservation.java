package apps.dreams.ReservationPage;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import core.WindowHandler;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

/**
 * @summary Contains the methods & objects for the Cancel Reservation in Dreams UI
 * @version Created 03/18/2016
 * @author Lalitha Bandha
 */

public class CancelReservation {
	// ****************************
	// *** Content Frame Fields ***
	// ****************************
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	// ******************************
	// *** Content Frame Elements ***
	// ******************************

	//Contact name 
	@FindBy(name = "contactName") 
	private Textbox txtContactName;

	// select Cancellation Reason
	@FindBy(id = "resortName") 
	private Listbox lstCancellationReason;

	// Web page element for Ok button
	@FindBy(name = "b_Ok")
	private Button btnAlertOk;

	// Web page Element for Yes popup
	@FindBy(name = "b_Yes")
	private Button btnErrorYes;


	public CancelReservation(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);
	}


	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, txtContactName);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				element);
	}

	public CancelReservation initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}



	public void enterContactName(String scenario1){
		datatable.setVirtualtablePage("PrimaryGuest");
		datatable.setVirtualtableScenario(scenario1);
		// Provide contact name 
		txtContactName.safeSet(datatable.getDataParameter("FirstName"));
	}

	/**
	 * @summary Method to Perform Cancel Reservation - Cancel Reservation Window
	 * @version Created 03/19/2016
	 * @author Lalitha Banda
	 * @param  DataTable scenario
	 * @throws NA
	 * @return NA
	 */
	// Cancel reservation window 
	public void perform_CancelRES(String scenario,String scenario1){

		datatable.setVirtualtablePage("Reservation");
		datatable.setVirtualtableScenario(scenario);

		txtContactName.highlight(driver);
		lstCancellationReason.select(datatable.getDataParameter("CancellationReason"));
		enterContactName(scenario1);
		btnAlertOk.click();
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 3);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,"WARNING");
		btnErrorYes.highlight(driver);
		btnErrorYes.click();

		// switching back to parent window
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,"Disney Reservation Entry and Management System");
	}
}
