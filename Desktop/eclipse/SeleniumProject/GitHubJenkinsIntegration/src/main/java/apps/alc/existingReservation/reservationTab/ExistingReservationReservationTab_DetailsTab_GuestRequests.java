package apps.alc.existingReservation.reservationTab;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.alc.pleaseWait.PleaseWait;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

/**
 * This class contains elements and element interactions for a given page of a
 * web application
 * 
 * @author Praveen Namburi.
 * @version 01/11/2016 Praveen namburi - original
 */
public class ExistingReservationReservationTab_DetailsTab_GuestRequests {
	// *******************
	// Page Class Fields
	// *******************
	// Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	// *********************
	// Page Class Elements
	// *********************
	// ------ Guest Requests Info page Elements ------
	// Booster seat Check-box
	@FindBy(id = "guestRequestForm:guestreqRD:0")
	private Checkbox chkBoosterSeat;

	// HighChair Check-box
	@FindBy(id = "guestRequestForm:guestreqRD:1")
	private Checkbox chkHighChair;

	// Requesthighchair Check-box
	@FindBy(id = "guestRequestForm:guestreqRD:2")
	private Checkbox chkRequesthighchair;

	// RequestTwoHighChairs Check-box
	@FindBy(id = "guestRequestForm:guestreqRD:3")
	private Checkbox chkRequestTwoHighChairs;

	// ------ Guest Requests Service Info page Elements ------
	// HearingLoss Check-box
	@FindBy(id = "guestRequestForm:guestreqspecialneedsRD:0")
	private Checkbox chkHearingLoss;

	// Limited mobility Check-box
	@FindBy(id = "guestRequestForm:guestreqspecialneedsRD:1")
	private Checkbox chkLimitedmobility;

	// Non-Apparent disability Check-box
	@FindBy(id = "guestRequestForm:guestreqspecialneedsRD:2")
	private Checkbox chkNonApparentdisability;

	// Oxygen tankuse Check-box
	@FindBy(id = "guestRequestForm:guestreqspecialneedsRD:3")
	private Checkbox chkOxygentankuse;

	// Service Animal Check-box
	@FindBy(id = "guestRequestForm:guestreqspecialneedsRD:4")
	private Checkbox chkServiceAnimal;

	// Special Dietary Request Check-box
	@FindBy(id = "guestRequestForm:guestreqspecialneedsRD:5")
	private Checkbox chkSpecialDietaryRequest;

	// Visual disability Check-box
	@FindBy(id = "guestRequestForm:guestreqspecialneedsRD:6")
	private Checkbox chkVisualdisability;

	// Wheelchair accessibility Check-box
	@FindBy(id = "guestRequestForm:guestreqspecialneedsRD:7")
	private Checkbox chkWheelchairaccessibility;

	// ------ Food Alergies Info page Elements ------
	// Corn Check-box
	@FindBy(id = "guestRequestForm:guestreq2RD:0")
	private Checkbox chkCorn;

	// Egg Check-box
	@FindBy(id = "guestRequestForm:guestreq2RD:1")
	private Checkbox chkEgg;

	// Fish Check-box
	@FindBy(id = "guestRequestForm:guestreq2RD:2")
	private Checkbox chkFish;

	// Intolerance-Gluten Check-box
	@FindBy(id = "guestRequestForm:guestreq2RD:3")
	private Checkbox chkIntoleranceGluten;

	// Intolerance-PKU Check-box
	@FindBy(id = "guestRequestForm:guestreq2RD:4")
	private Checkbox chkIntolerancePKU;

	// MilkDairy Check-box
	@FindBy(id = "guestRequestForm:guestreq2RD:5")
	private Checkbox chkMilkDairy;

	// Peanut Check-box
	@FindBy(id = "guestRequestForm:guestreq2RD:6")
	private Checkbox chkPeanut;

	// Shellfish Check-box
	@FindBy(id = "guestRequestForm:guestreq2RD:7")
	private Checkbox chkShellfish;

	// Soy Check-box
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:guestreq2:8")
	private Checkbox chkSoy;

	// TreeNut Check-box
	@FindBy(id = "guestRequestForm:guestreq2RD:9")
	private Checkbox chkTreeNut;

	// Wheat Gluten Check-box
	@FindBy(id = "guestRequestForm:guestreq2RD:10")
	private Checkbox chkWheatGluten;

	// Textbox OtherAllergy
	@FindBy(id = "guestRequestForm:addOtherAllergy")
	private Textbox txtOtherAllergy;

	// Button Cancel
	@FindBy(id = "guestRequestForm:closeTravelAgent")
	private Button btnCancel;

	// Button Save
	@FindBy(id = "guestRequestForm:ajaxTravelAgent")
	private Button btnSave;

	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Praveen Namburi.
	 * @version 01-11-2016 Praveen Namburi - original
	 * @param driver
	 */
	public ExistingReservationReservationTab_DetailsTab_GuestRequests(
			WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Praveen Namburi.
	 * @version 01-11-2016 Praveen Namburi - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public ExistingReservationReservationTab_DetailsTab_GuestRequests initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Praveen Namburi.
	 * @version 01-11-2016 Praveen Namburi - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnCancel);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Praveen Namburi.
	 * @version 01-11-2016 Praveen Namburi - original
	 * @param element
	 *            - Element to be used to determine if the page is loaded
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				element);
	}

	// ********************
	// Page Class Methods
	// ********************

	// Click on button Save.
	public void clickSave() {
		pageLoaded(btnSave);
		btnSave.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	/**
	 * @summary : Calling methods in method to modify guest requests.
	 * @author : Praveen Namburi, @version: Created 1/11/2016, @param :
	 *         scenario.
	 */
	public void modifyGuestRequests(String scenario) {
		SelectGuestRequests(scenario);
		SelectGuestRequestsService(scenario);
		SelectFoodAlergies(scenario);
		enterOtherAllergies(scenario);

	}

	/**
	 * @summary : Method to select the Guest Requests check-boxes.
	 * @author : Praveen Namburi, @version: Created 1/11/2016, @param :
	 *         scenario.
	 */
	public void SelectGuestRequests(String scenario) {
		datatable.setVirtualtablePage("ModifyGuestRequestsInExistngResev");
		datatable.setVirtualtableScenario(scenario);

		initialize();
		// Select the check-boxes based on the condition from datatable.
		// To check and un-check the check-boxes you should use only true
		// condition in datatable.
		if (datatable.getDataParameter("BoosterSeat").equalsIgnoreCase("True")) {
			pageLoaded(chkBoosterSeat);
			chkBoosterSeat.highlight(driver);
			chkBoosterSeat.jsToggle(driver);
		}
		if (datatable.getDataParameter("HighChair").equalsIgnoreCase("True")) {
			chkHighChair.highlight(driver);
			chkHighChair.jsToggle(driver);
		}
		if (datatable.getDataParameter("Requesthighchair").equalsIgnoreCase(
				"True")) {
			chkRequesthighchair.highlight(driver);
			chkRequesthighchair.jsToggle(driver);
		}
		if (datatable.getDataParameter("RequestTwoHighChairs")
				.equalsIgnoreCase("True")) {
			chkRequestTwoHighChairs.highlight(driver);
			chkRequestTwoHighChairs.jsToggle(driver);
		}

	}

	/**
	 * @summary : Method to select the Guest Requests Service check-boxes.
	 * @author : Praveen Namburi, @version: Created 1/11/2016, @param :
	 *         scenario.
	 */
	public void SelectGuestRequestsService(String scenario) {
		datatable.setVirtualtablePage("ModifyGuestRequestsInExistngResev");
		datatable.setVirtualtableScenario(scenario);

		initialize();
		// Select the check-boxes based on the condition from datatable.
		// To check and un-check the check-boxes, you should use only true
		// condition in datatable.
		if (datatable.getDataParameter("HearingLoss").equalsIgnoreCase("True")) {
			pageLoaded(chkHearingLoss);
			chkHearingLoss.highlight(driver);
			chkHearingLoss.jsToggle(driver);
		}
		if (datatable.getDataParameter("Limitedmobility").equalsIgnoreCase(
				"True")) {
			chkLimitedmobility.highlight(driver);
			chkLimitedmobility.jsToggle(driver);
		}
		if (datatable.getDataParameter("NonApparentdisability")
				.equalsIgnoreCase("True")) {
			chkNonApparentdisability.highlight(driver);
			chkNonApparentdisability.jsToggle(driver);
		}
		if (datatable.getDataParameter("Oxygentankuse")
				.equalsIgnoreCase("True")) {
			chkOxygentankuse.highlight(driver);
			chkOxygentankuse.jsToggle(driver);
		}
		if (datatable.getDataParameter("ServiceAnimal")
				.equalsIgnoreCase("True")) {
			chkServiceAnimal.highlight(driver);
			chkServiceAnimal.jsToggle(driver);
		}
		if (datatable.getDataParameter("SpecialDietaryRequest")
				.equalsIgnoreCase("True")) {
			chkSpecialDietaryRequest.highlight(driver);
			chkSpecialDietaryRequest.jsToggle(driver);
		}
		if (datatable.getDataParameter("Visualdisability").equalsIgnoreCase(
				"True")) {
			chkVisualdisability.highlight(driver);
			chkVisualdisability.jsToggle(driver);
		}
		if (datatable.getDataParameter("Wheelchairaccessibility")
				.equalsIgnoreCase("True")) {
			chkWheelchairaccessibility.highlight(driver);
			chkWheelchairaccessibility.jsToggle(driver);
		}

	}

	/**
	 * @summary : Method to select the Food Alergies check-boxes.
	 * @author : Praveen Namburi, @version: Created 1/11/2016, @param :
	 *         scenario.
	 */
	public void SelectFoodAlergies(String scenario) {
		datatable.setVirtualtablePage("ModifyGuestRequestsInExistngResev");
		datatable.setVirtualtableScenario(scenario);

		initialize();
		// Select the Food Alergies check-boxes based on the condition from
		// datatable.
		// To check and un-check the check-boxes you should use only true
		// condition in datatable.
		if (datatable.getDataParameter("Corn").equalsIgnoreCase("True")) {
			pageLoaded(chkCorn);
			chkCorn.highlight(driver);
			chkCorn.jsToggle(driver);
		}
		if (datatable.getDataParameter("Egg").equalsIgnoreCase("True")) {
			chkEgg.highlight(driver);
			chkEgg.jsToggle(driver);
		}
		if (datatable.getDataParameter("Fish").equalsIgnoreCase("True")) {
			chkFish.highlight(driver);
			chkFish.jsToggle(driver);
		}
		if (datatable.getDataParameter("IntoleranceGluten").equalsIgnoreCase(
				"True")) {
			chkIntoleranceGluten.highlight(driver);
			chkIntoleranceGluten.jsToggle(driver);
		}
		if (datatable.getDataParameter("IntolerancePKU").equalsIgnoreCase(
				"True")) {
			chkIntolerancePKU.highlight(driver);
			chkIntolerancePKU.jsToggle(driver);
		}
		if (datatable.getDataParameter("MilkDairy").equalsIgnoreCase("True")) {
			chkMilkDairy.highlight(driver);
			chkMilkDairy.jsToggle(driver);
		}
		if (datatable.getDataParameter("Peanut").equalsIgnoreCase("True")) {
			chkPeanut.highlight(driver);
			chkPeanut.jsToggle(driver);
		}
		if (datatable.getDataParameter("Shellfish").equalsIgnoreCase("True")) {
			chkShellfish.highlight(driver);
			chkShellfish.jsToggle(driver);
		}
		if (datatable.getDataParameter("Soy").equalsIgnoreCase("True")) {
			chkSoy.highlight(driver);
			chkSoy.jsToggle(driver);
		}
		if (datatable.getDataParameter("TreeNut").equalsIgnoreCase("True")) {
			chkTreeNut.highlight(driver);
			chkTreeNut.jsToggle(driver);
		}
		if (datatable.getDataParameter("WheatGluten").equalsIgnoreCase("True")) {
			chkWheatGluten.highlight(driver);
			chkWheatGluten.jsToggle(driver);
		}

	}

	/**
	 * @summary : Method to enter Other Allergies Info.
	 * @author : Praveen Namburi, @version: Created 1/11/2016, @param :
	 *         scenario.
	 */
	public void enterOtherAllergies(String scenario) {
		datatable.setVirtualtablePage("ModifyGuestRequestsInExistngResev");
		datatable.setVirtualtableScenario(scenario);

		String OtherAllergy = datatable.getDataParameter("OtherAllergies");

		initialize();
		pageLoaded(txtOtherAllergy);
		txtOtherAllergy.safeSet(OtherAllergy);
	}

	/**
	 * @summary: Calling methods in method to modify and save Guest Requests.
	 * @author : Praveen Namburi, @version: Created 1/11/2016, @param :
	 *         scenario.
	 */
	public void modifyAndSaveSpecialRequests(String scenario) {
		modifyGuestRequests(scenario);
		clickSave();
	}

}

