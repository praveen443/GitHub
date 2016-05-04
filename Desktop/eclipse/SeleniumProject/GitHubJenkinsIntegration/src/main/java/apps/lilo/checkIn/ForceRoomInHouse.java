package apps.lilo.checkIn;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Label;
import core.interfaces.Link;
import core.interfaces.Webtable;
import core.interfaces.impl.internal.ElementFactory;

/**
 * @summary Contains the page methods & objects to force a room status to In
 *          House
 * @version Created 9/11/2014
 * @author Waightstill W. Avery
 */
public class ForceRoomInHouse {

	// ************************************
	// *** Main ForceRoomInHouse Fields ***
	// ************************************
	private String strReserationStatus;
	private WebDriver driver;

	// **************************************
	// *** Main ForceRoomInHouse Elements ***
	// **************************************

	// Proceed To Check-In button
	@FindBy(id = "roomTabFrm:proToChkInButtonId")
	private Button btnProceedToCheckIn;

	// Reservation status link
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:0:resStatusLink")
	private Link lnkReservationStatus;

	// Check Into Dirty Room Yes button
	@FindBy(id = "confirmationCheckingInSubmitForm:checkInYesButtonId")
	private Button btnCheckInDirtyRoomYes;

	// Reservation status label
	// @FindBy(id =
	// "roomTabFrm:travelPlanSegmentViewId:0:subTable:0:reservationStatusId")
	// @FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:0:j_id28323")
	// @FindBy(css =
	// "span[id^=\"#roomTabFrm:travelPlanSegmentViewId:0:subTable:0:reservationStatusId\"]")
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:0:reservationStatusId")
	private Label lblReservationStatus;

	// Master Table
	@FindBy(id = "pmsMasterHTMLTable")
	private Webtable tblMasterTable;

	// *********************
	// ** Build page area **
	// *********************

	/**
	 * 
	 * @summary Constructor to initialize the page
	 * @version Created 9/11/2014
	 * @author Waightstill W Avery
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public ForceRoomInHouse(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
	}

	/**
	 * 
	 * @summary Method to grab all of the elements on the page
	 * @version Created 9/11/2014
	 * @author Waightstill W Avery
	 * @param driver
	 * @throws NA
	 * @return All elements of the current page
	 * 
	 */
	private ForceRoomInHouse initialize(WebDriver driver) {
		return ElementFactory.initElements(driver, ForceRoomInHouse.class);
	}

	/**
	 * 
	 * @summary Method that ensure the page is loaded, the "Proceed To Check-In"
	 *          button is visible
	 * @version Created 9/11/2014
	 * @author Waightstill W Avery
	 * @param NA
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void ForceRoomInHousePageLoaded() {
		while (!btnProceedToCheckIn.syncVisible(driver)) {
			initialize(driver);
		}
		btnProceedToCheckIn.syncVisible(driver);
	}

	// *********************************************
	// *** Main CompeteCheckIn Page Interactions ***
	// *********************************************

	/**
	 * 
	 * @summary Method that forces a reservation In House if it is not already.
	 *          This is only designed for reservations that have a room assigned
	 * @version Created 9/11/2014
	 * @author Waightstill W Avery
	 * @param NA
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void forceInHouse() {
		if (isInHouse()) {
			Reporter.log("The reservation is already In-House", true);
		} else {
			clickCheckingIn();
			clickCheckInDirtyRoomYes();
			captureReservationLabelStatus();
			Assert.assertEquals(getReservationLabelStatus(), "In-House");
		}
	}

	/**
	 * 
	 * @summary Method that determines if a reservation is In-House.
	 * @version Created 9/11/2014
	 * @author Waightstill W Avery
	 * @param NA
	 * @throws NA
	 * @return true if In-House, false otherwise
	 * 
	 */
	private boolean isInHouse() {
		boolean blnInHouse = false;
		ForceRoomInHousePageLoaded();
		if (lblReservationStatus.syncVisible(driver, 5, false)) {
			if (!lblReservationStatus.getAttribute("text").equalsIgnoreCase(
					"In-House")) {
				blnInHouse = true;
			}
		} else if (lnkReservationStatus.syncVisible(driver, 5, false)) {
			if (getReservationLinkStatus().equalsIgnoreCase("Checking In")) {
				blnInHouse = false;
			}
		}
		// if(tblMasterTable.syncPresent(driver, timeout, returnError)){
		//
		// }
		return blnInHouse;
	}

	private String getReservationLinkStatus() {
		return strReserationStatus;
	}

	private void clickCheckingIn() {
		lnkReservationStatus.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	private void clickCheckInDirtyRoomYes() {
		btnCheckInDirtyRoomYes.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	private void captureReservationLabelStatus() {
		strReserationStatus = lblReservationStatus.getAttribute("text");
	}

	private String getReservationLabelStatus() {
		return strReserationStatus;
	}

}

