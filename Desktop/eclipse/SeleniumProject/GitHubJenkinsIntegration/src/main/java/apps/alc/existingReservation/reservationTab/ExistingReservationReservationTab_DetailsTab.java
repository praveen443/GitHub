package apps.alc.existingReservation.reservationTab;

import java.util.List;

import org.openqa.selenium.By;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.alc.pleaseWait.PleaseWait;
import core.WebDriverSetup;
import core.WindowHandler;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Link;
import core.interfaces.Label;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Sleeper;
import utils.TestReporter;
import utils.Datatable;

/**
 * This class contains elements and element interactions for a given page of a
 * web application
 * 
 * @author Waightstill W Avery
 * @version 12/15/2015 Waightstill W Avery - original
 */
public class ExistingReservationReservationTab_DetailsTab {
	// *******************
	// Page Class Fields
	// *******************
	// Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	// *********************
	// Page Class Elements
	// *********************
	@FindBy(id = "existingReservationForm:resViewFolio")
	private Button btnViewFolio;

	@FindBy(id = "existingReservationForm:resDetailsEdit")
	private Button btnEditReservation;

	@FindBy(id = "existingReservationForm:resDetailsEdit2")
	private Button btnEditVenueInformation;

	@FindBy(id = "existingReservationForm:reservationDetailsInfoPanel_header")
	private Element eleReservationDetailsInfoPanel;

	// button Add
	@FindBy(id = "existingReservationForm:resDetailsComments")
	private Button btnAdd;

	/* XCR Comments */

	@FindBy(id = "guestListForm1:cancelComment")
	private Button btnCancel;

	@FindBy(id = "existingReservationForm:resDetailsComments")
	private Element radExternal;

	@FindBy(id = "existingReservationForm:resDetailsComments")
	private Element radInternal;

	@FindBy(id = "guestListForm1:resXCRComment")
	private Textbox eleComment;

	@FindBy(id = "guestListForm1:saveComment")
	private Button btnSave;

	@FindBy(id = "existingReservationForm:noOfAdults")
	private Element eleNoofAdults;

	@FindBy(id = "existingReservationForm:noOfNonAdults")
	private Element eleNoOfchilds;

	@FindBy(id = "existingReservationForm:productList:0:productId")
	private Element eleResActivity;

	@FindBy(linkText = "Extra Care")
	private Link lnkExtraCare;

	@FindBy(id = "existingReservationForm:noOfAdults")
	private Element eleNoOfAdults;

	@FindBy(id = "existingReservationForm:productList:0:productId")
	private Element eleProductId;

	@FindBy(id = "existingReservationForm:productList:0:productFacilityId")
	private Element eleProductFacilityId;

	// get the details for the facility or area
	@FindBy(id = "existingReservationForm:productList:0:productFacilityId")
	private Element eleProductFacility;

	// get the details for the activity or product
	@FindBy(id = "existingReservationForm:productList:0:productId")
	private Element eleProduct;

	// get the total party size
	@FindBy(id = "existingReservationForm:partySize")
	private Element eleTotalPartySize;

	// get number of adults
	@FindBy(id = "existingReservationForm:noOfAdults")
	private Element eleAdultNumber;

	// get number of children
	@FindBy(id = "existingReservationForm:noOfNonAdults")
	private Element eleChildNumber;

	// object for cancel button
	@FindBy(id = "existingReservationForm:resDetailsCancel")
	private Button btnCancelReservation;

	// Object for the cancel popup for contact name
	@FindBy(id = "cancelConfirm:contactName")
	private Textbox txtcancelContactName;

	// Cancel reason options drop down
	@FindBy(id = "cancelConfirm:cancelReasonOptions")
	private Listbox lstCancelReason;

	// Confirm Yes button
	@FindBy(id = "cancelConfirm:cancelConfirmCancel")
	private Button btnYes;

	// Confirm No button
	@FindBy(id = "cancelConfirm:cancelConfirmNo")
	private Button btnNo;

	// Button Done
	@FindBy(id = "logoutForm1:j_id18")
	private Button btnDone;

	// Left Panel Items to Sync
	@FindBy(id = "guestInfoLeftPanel")
	private Label lblGuestInfoLeftPanel;

	@FindBy(id = "sb_header")
	private Label lblLftPanelHeader;

	@FindBy(id = "editguestFNameText")
	private Label lblLftPanelFName;

	// Button Edit for Special Guest Requests
	@FindBy(id = "existingReservationForm:resDetailsEdit4")
	private Button btnGuestRequestsEdit;

	// Button Edit for Special Guest Requests
	@FindBy(id = "existingReservationForm:resDetailsEdit")
	private Button btnResDetailsEdit;

	@FindBy(xpath = "//*[@id='existingReservationForm:addOnPanel_body']/table/tbody/tr/td/input")
	private Button btnEditAddOn;

	@FindBy(id = "modifyAddOnsForm:modifyAddOnsOutPanel")
	private Element eleAddOnFormDiv;

	@FindBy(xpath = "//*[@id='modifyAddOnsForm:modifyAddOnsOutPanel']/table/tbody/tr[3]/td[2]/select")
	private Listbox lstAddOnProductName;

	@FindBy(xpath = "//*[@id='modifyAddOnsForm:modifyAddOnsOutPanel']/table/tbody/tr[4]/td[2]/select")
	private Listbox lstAddOnProductMenu;

	@FindBy(id = "modifyAddOnsForm:addOnProductQuantityInput")
	private Textbox txtAddOnProductQuantity;

	@FindBy(id = "modifyAddOnsForm:addOnProductPriceOutput")
	private Label lblAddOnUnitPrice;

	@FindBy(id = "modifyAddOnsForm:revenueTypeList:1:addonProductList:1:modAddOnPolicies")
	private Element eleAddOnPolicies;

	@FindBy(id = "modifyAddOnsForm:modAddOnSave")
	private Button btnAddOnSave;

	@FindBy(xpath = "//*[@id='existingReservationForm:addOnPanel_body']/table/tbody/tr/td/span")
	private Element eleModifiedAddOnProduct;

	@FindBy(xpath = "//div[@id='existingReservationForm:celebPanel_header']/a")
	private Link lnkCelebrations;

	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
	 * @param driver
	 */
	public ExistingReservationReservationTab_DetailsTab(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public ExistingReservationReservationTab_DetailsTab initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnViewFolio);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
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

	public String getReservationDetailsHeaderInformation() {
		eleReservationDetailsInfoPanel.getText();
		return eleReservationDetailsInfoPanel.getText();
	}

	public void clickEdit() {
		initialize();
		pageLoaded(btnEditReservation);
		Sleeper.sleep(1000);
		btnEditReservation.jsClick(driver);
		btnEditReservation.highlight(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	/**
	 * @summary Methods to click discover button
	 * @author Lalitha Banda
	 * @version 12/4/2015 Lalitha Banda - original
	 * @param NA
	 * @return NA
	 */

	public void getDetails() {
		initialize();
		pageLoaded(eleResActivity);
		TestReporter.logStep("Reservation Details : "
				+ getReservationDetailsHeaderInformation());
		pageLoaded(eleResActivity);
		TestReporter.logStep("Activity Details : " + eleResActivity.getText());
		pageLoaded(eleNoofAdults);
		TestReporter.logStep("Adults : " + eleNoofAdults.getText());
		pageLoaded(eleNoOfchilds);
		TestReporter.logStep("Childs : " + eleNoOfchilds.getText());
	}

	/**
	 * @summary Methods to click discover button
	 * @author Lalitha Banda
	 * @version 12/5/2015 Lalitha Banda - original
	 * @param NA
	 * @return NA
	 */
	public void clickAdd() {
		initialize();
		pageLoaded(btnAdd);
		btnAdd.highlight(driver);
		btnAdd.click();
		PleaseWait.WaitForPleaseWait(driver);
	}

	/**
	 * @summary Methods to click discover button
	 * @author Lalitha Banda
	 * @version 12/5/2015 Lalitha Banda - original
	 * @param NA
	 * @return NA
	 */
	public void clickSave() {
		initialize();
		pageLoaded(btnSave);
		btnSave.highlight(driver);
		btnSave.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	/**
	 * @summary Methods to click discover button
	 * @author Lalitha Banda
	 * @version 12/5/2015 Lalitha Banda - original
	 * @param NA
	 * @return NA
	 */
	public void clickCancel() {
		initialize();
		pageLoaded(btnCancel);
		btnCancel.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	/**
	 * @summary Methods to click discover button
	 * @author Lalitha Banda
	 * @version 12/6/2015 Lalitha Banda - original
	 * @param NA
	 * @return NA
	 */

	public void clickExtraCare() {
		initialize();
		pageLoaded(lnkExtraCare);
		lnkExtraCare.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	/**
	 * @summary Methods to click Edit button for Special Guest Requests
	 * @author Praveen Namburi
	 * @version 1/11/2016 Praveen Namburi - original
	 * @param NA
	 */
	public void clickSpecialRequestEditbtn() {
		initialize();
		pageLoaded(btnGuestRequestsEdit);
		btnGuestRequestsEdit.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);

	}

	/**
	 * @summary Methods to click Edit button in Reservation details.
	 * @author Praveen Namburi.
	 * @version 1/12/2016 Praveen Namburi - original
	 */
	public void clickEditInResvDetails() {
		pageLoaded(btnResDetailsEdit);
		btnResDetailsEdit.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	/**
	 * @summary Methods to add notifications under reservations -> details
	 *          screen
	 * @author Lalitha Banda
	 * @version 12/6/2015 Lalitha Banda - original
	 * @param NA
	 * @return NA
	 */
	public void addNotifications(String inputComment1, String inputComment2) {
		clickAdd();
		eleComment.highlight(driver);
		eleComment.safeSet(inputComment1);
		eleComment.clear();
		eleComment.safeSet(inputComment2);
		clickSave();

	}

	/**
	 * @summary Methods to verify added notifications unders Reservation ->
	 *          details screen
	 * @author Lalitha Banda
	 * @version 12/6/2015 Lalitha Banda - original
	 * @param NA
	 * @return NA
	 */
	public boolean verify_Notifications(String inputComment1,
			String inputComment2) {
		String Xpath = "//form/div[1]/div/div/table/tbody/tr";
		boolean ReturnValue = false;
		Sleeper.sleep(3000);
		clickAdd();
		List<WebElement> Trs = driver.findElements(By.xpath(Xpath));
		for (WebElement input : Trs) {
			TestReporter.logStep(input.getText());
			if (input.getText().contains(inputComment2)) {
				ReturnValue = true;
				break;
			}
		}
		clickSave();
		return ReturnValue;
	}

	/**
	 * @summary This method gets the number of adults from existing reservation-
	 *          reservation - details tab page
	 * @author Marella Satish
	 * @version 01/06/2015 Marella Satish - original
	 * @param na
	 * @return NoOfAdults as string
	 */
	public String getNumberOfAdults() {

		pageLoaded();
		String AdultCount[];
		String Adults = eleNoOfAdults.getText();
		AdultCount = StringUtils.split(Adults, " ");
		String NoOfAdults = AdultCount[0].trim();
		TestReporter.logStep("Number of adults reserved after search : "
				+ NoOfAdults);
		return NoOfAdults;
	}

	/**
	 * @summary This method gets the number of childs from existing reservation-
	 *          reservation - details tab page
	 * @author Marella Satish
	 * @version 01/06/2015 Marella Satish - original
	 * @param na
	 * @return NoOfChilds as string
	 */
	public String getNumberOfChilds() {

		pageLoaded();
		String ChildCount[];
		String Childs = eleNoOfchilds.getText();
		ChildCount = StringUtils.split(Childs, " ");
		String NoOfChilds = ChildCount[0].trim();
		TestReporter.logStep("Number of children reserved after search : "
				+ NoOfChilds);
		return NoOfChilds;

	}

	/**
	 * @summary This method gets the product facility id from existing
	 *          reservation- reservation - details tab page
	 * @author Marella Satish
	 * @version 01/06/2015 Marella Satish - original
	 * @param na
	 * @return ProductFacilityId as string
	 */
	public String getProductFacilityId() {

		pageLoaded();
		String ProductFacilityId = eleProductFacilityId.getText().toLowerCase()
				.replace(":", "-");
		TestReporter.logStep("Product Facility Id : " + ProductFacilityId);
		return ProductFacilityId;
	}

	/**
	 * @summary This method gets the product id from existing reservation-
	 *          reservation - details tab page
	 * @author Marella Satish
	 * @version 01/06/2015 Marella Satish - original
	 * @param na
	 * @return ProductId as string
	 */
	public String getProductId() {

		pageLoaded();
		String ProductId = eleProductId.getText().toLowerCase();
		TestReporter.logStep("Product Id : " + ProductId);
		return ProductId;

	}

	/**
	 * @summary This method validates the reservation details from existing
	 *          reservation- reservation - details tab page
	 * @author Marella Satish
	 * @version 01/06/2015 Marella Satish - original
	 * @param ProductFacId
	 *            , ProductId, Adults, Childs
	 * @return na
	 */
	public void validateReservationDetails(String ProductFacId,
			String ProductId, String Adults, String Childs) {

		boolean ProFacIdStatus = ProductFacId.contains(getProductFacilityId());
		TestReporter.logStep("Product FacilityId Status : " + ProFacIdStatus);
		TestReporter.assertTrue(ProFacIdStatus,
				"The Product Facility Id doesn't match");
		TestReporter.logStep("Product Facilty Id validated successfully");

		boolean ProIdStatus = ProductId.contains(getProductId());
		TestReporter.logStep("Product Id Status : " + ProIdStatus);
		TestReporter.assertTrue(ProIdStatus, "The Product Id doesn't match");
		TestReporter.logStep("Product Id validated successfully");

		TestReporter.assertEquals(getNumberOfAdults(), Adults,
				"Adult Count doesn't match");
		TestReporter.logStep("Adults count validated successfully");

		TestReporter.assertEquals(getNumberOfChilds(), Childs,
				"Child Count doesn't match");
		TestReporter.logStep("Childs count validated successfully");
		TestReporter.logStep("Reservation details validated and found correct");
		;
	}

	/**
	 * @summary Method to print out the complete reservation details
	 *          StringBuilder
	 * 
	 * @author Stagliano, Dennis
	 * @version 1/5/201 Stagliano, Dennis - original
	 * @param NA
	 * @return NA
	 */
	public String getCompleteFirstReservationDetails() {
		pageLoaded();
		String reservationHeader = getReservationDetailsHeaderInformation();
		String area = getProductFacility();
		String activity = getProductActivity();
		String partySize = getTotalPartySize();
		String adultNum = getAdultNumber();
		String childNum = getchildNumber();
		// Set up a String object
		StringBuffer sb = new StringBuffer(
				"************************************************************");
		sb.append(System.getProperty("line.separator"));
		sb.append("************************************************************");
		sb.append(System.getProperty("line.separator"));
		sb.append("Listing the default reservation details for first selected reservation"
				+ "\n");
		sb.append("Reservation Header = " + reservationHeader + "\n");
		sb.append("Area = " + area + "\n");
		sb.append("Activity = " + activity + "\n");
		sb.append("Total party size = " + partySize + "\n");
		sb.append("Number of Adults = " + adultNum
				+ " and Number of Children = " + childNum);
		sb.append(System.getProperty("line.separator"));
		sb.append("************************************************************");
		sb.append(System.getProperty("line.separator"));
		sb.append("************************************************************");
		String details = sb.toString();
		return details;
	}

	/**
	 * @summary Method to get the Facility name and add to the StringBuffer
	 * @author Stagliano, Dennis
	 * @version 1/5/201 Stagliano, Dennis - original
	 * @param NA
	 * @return NA
	 */
	private String getProductFacility() {
		initialize();
		pageLoaded(eleProductFacility);
		Sleeper.sleep(500);
		return eleProductFacility.getText();
	}

	/**
	 * @summary Method to get the product name and add to the StringBuffer
	 * @author Stagliano, Dennis
	 * @version 1/5/201 Stagliano, Dennis - original
	 * @param NA
	 * @return NA
	 */
	private String getProductActivity() {
		initialize();
		pageLoaded(eleProduct);
		Sleeper.sleep(500);
		return eleProduct.getText();
	}

	/**
	 * @summary Method to get the total party size and add to the StringBuilder
	 * @author Stagliano, Dennis
	 * @version 1/5/201 Stagliano, Dennis - original
	 * @param NA
	 * @return NA
	 */
	private String getTotalPartySize() {
		initialize();
		pageLoaded(eleTotalPartySize);
		Sleeper.sleep(500);
		return eleTotalPartySize.getText();
	}

	/**
	 * @summary Method to get the number of adults and add to the StringBuilder
	 * @author Stagliano, Dennis
	 * @version 1/5/201 Stagliano, Dennis - original
	 * @param NA
	 * @return NA
	 */
	private String getAdultNumber() {
		initialize();
		pageLoaded(eleAdultNumber);
		Sleeper.sleep(500);
		return eleAdultNumber.getText();
	}

	/**
	 * @summary Method to get the number of children and add to the
	 *          StringBuilder
	 * @author Stagliano, Dennis
	 * @version 1/5/201 Stagliano, Dennis - original
	 * @param NA
	 * @return NA
	 */
	private String getchildNumber() {
		initialize();
		pageLoaded(eleChildNumber);
		Sleeper.sleep(500);
		return eleChildNumber.getText();
	}

	/**
	 * @summary Method to get click the cancel button from the Reservation
	 *          Details Tab and add inputs to the cancel popup for Contact Name,
	 *          Reason for Cancel and whether you want to click Yes or No to
	 *          cancel the reservation. NOTE: This is coded for the first
	 *          reservation that appears, If necessary, this will need code to
	 *          cancel multiple reservations or reservation of choice.
	 * @author Stagliano, Dennis
	 * @version 1/5/201 Stagliano, Dennis - original
	 * @param NA
	 * @return NA
	 */
	public void cancelExistingReservationDetailsTab(String contactName,
			String reasonForCancel) {

		// datatable.setVirtualtablePath(datatable.ALC_MASTER_DATA_PATH);
		// datatable.setVirtualtablePage("CancelReservationDetailsTab");
		// datatable.setVirtualtableScenario(scenario);
		clickCancelButton();
		// Sleeper.sleep(2000);
		pageLoaded(txtcancelContactName);
		// Sleeper.sleep(2000);

		// String contactName = datatable.getDataParameter("ContactName");
		// String reasonForCancel =
		// datatable.getDataParameter("ReasonForCancel");
		// String confirmCancel = datatable.getDataParameter("ConfirmCancel");

		// Sleeper.sleep(1000);
		// Set the ContactName
		if (contactName != null) {
			txtcancelContactName.set(contactName);
		}
		if (reasonForCancel != null) {
			lstCancelReason.select(reasonForCancel);
			// click the confirm buttons
			pageLoaded(btnYes);
			btnYes.jsClick(driver);
			PleaseWait.WaitForPleaseWait(driver);
		}

	}

	/**
	 * @summary Private Method to click cancel button on Details Tab
	 * @author Stagliano, Dennis
	 * @version 1/5/201 Stagliano, Dennis - original
	 * @param NA
	 * @return NA
	 */
	private void clickCancelButton() {
		pageLoaded(btnCancelReservation);
		btnCancelReservation.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	/**
	 * @summary This Method to validate cancelled Reservation
	 * @author Venkatesh Atmakuri
	 * @version 1/8/2016 Venkatesh Atmakuri - original
	 * @param NA
	 * @return NA
	 */
	public void validateCancelReservation() {
		String cancelledResHeaderInfo = getReservationDetailsHeaderInformation();
		TestReporter.logStep("Cancelled Reservation Header :: ["
				+ cancelledResHeaderInfo + " ]");
		TestReporter.assertTrue(cancelledResHeaderInfo.contains("Cancelled"),
				cancelledResHeaderInfo + " Not Cancelled");
	}

	/**
	 * @sumary : Method to click on button Cancel for navigating to cancel Resv
	 *         page.
	 * @author: Praveen namburi, @version : 1-7-2016.
	 */
	public void clickbtnCancel() {
		clickCancelButton();
	}

	/**
	 * @summary Method to Modify existing AddOn Products - ShoppingCart
	 * @author Venkatesh Atmakuri
	 * @version 1/11/2015 Venkatesh Atmakuri - original
	 * @param Scenario
	 * @return NA
	 */
	public void ModifyaddOnProducts(String scenario) {
		datatable.setVirtualtablePage("ShoppingCart");
		datatable.setVirtualtableScenario(scenario);

		String ModifyaddOnProduct = datatable
				.getDataParameter("ModifyAddOnProduct");
		String ModifyaddOnMenu = datatable.getDataParameter("ModifyAddOnMenu");

		btnEditAddOn.highlight(driver);
		btnEditAddOn.jsClick(driver);
		// pageLoaded(eleAddOnFormDiv);
		// eleAddOnFormDiv.syncVisible(driver);
		// eleAddOnFormDiv.highlight(driver);
		//
		// //Verifying for the required element is displayed or not
		// if(eleAddOnFormDiv.isDisplayed())
		// {
		//
		// WebElement select =
		// driver.findElement(By.xpath("//*[@id='modifyAddOnsForm:modifyAddOnsOutPanel']/table/tbody/tr[3]/td[2]/select"));
		// List<WebElement> options = select.findElements(By.tagName("option"));
		// for (WebElement option : options) {
		// if("".equals(option.getText())){
		// option.click();
		// break;
		// }
		// }
		//
		// initialize();
		// Sleeper.sleep(3000);
		// lstAddOnProductName.select(ModifyaddOnProduct);
		// Sleeper.sleep(3000);
		// initialize();
		//
		// eleAddOnFormDiv.highlight(driver);
		// lstAddOnProductMenu.select(ModifyaddOnMenu);
		// initialize();
		// Sleeper.sleep(3000);
		// eleAddOnPolicies.isDisplayed();
		// TestReporter.logStep("Modified Product Quantity : "+txtAddOnProductQuantity.getText());
		// TestReporter.logStep("Modified Unit Price : "+lblAddOnUnitPrice.getText());
		// btnAddOnSave.highlight(driver);
		// btnAddOnSave.click();
		// }

		pageLoaded(eleAddOnFormDiv);
		// eleAddOnFormDiv.syncVisible(driver);

		if (!eleAddOnFormDiv.syncVisible(driver, 10, false)) {
			pageLoaded(eleAddOnFormDiv);
			eleAddOnFormDiv.syncVisible(driver);
		}

		WebElement select = driver
				.findElement(By
						.xpath("//*[@id='modifyAddOnsForm:modifyAddOnsOutPanel']/table/tbody/tr[3]/td[2]/select"));
		List<WebElement> options = select.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if ("".equals(option.getText())) {
				option.click();
				break;
			}
		}

		pageLoaded(lstAddOnProductName);
		lstAddOnProductName.select(ModifyaddOnProduct);
		int counter = 0;
		boolean isMultipleOptions = false;
		do {
			Sleeper.sleep(1000);
			counter++;
			try {
				isMultipleOptions = (lstAddOnProductMenu.getOptions().size() > 1);
			} catch (Exception sere) {
				pageLoaded(lstAddOnProductMenu);
			}
		} while (!isMultipleOptions
				&& counter < WebDriverSetup.getDefaultTestTimeout());
		TestReporter.assertTrue(
				counter < WebDriverSetup.getDefaultTestTimeout(),
				"No options were loaded for the second product listbox.");

		lstAddOnProductMenu.select(ModifyaddOnMenu);
		pageLoaded(eleAddOnPolicies);

		eleAddOnPolicies.syncVisible(driver);
		pageLoaded(txtAddOnProductQuantity);
		TestReporter.logStep(" Product Quantity : "
				+ txtAddOnProductQuantity.getText());
		pageLoaded(lblAddOnUnitPrice);
		TestReporter.logStep(" Unit Price : " + lblAddOnUnitPrice.getText());

		// click on Save button.
		if (btnAddOnSave.syncVisible(driver)) {
			btnAddOnSave.highlight(driver);
			btnAddOnSave.jsClick(driver);
		}
	}

	/**
	 * @summary Method to Modify existing AddOn Products - ShoppingCart
	 * @author Venkatesh Atmakuri
	 * @version 1/11/2015 Venkatesh Atmakuri - original
	 * @param Scenario
	 * @return NA
	 */
	public void validatingModifiedAddOnProduct(String scenario, String condition) {
		datatable.setVirtualtablePage("ShoppingCart");
		datatable.setVirtualtableScenario(scenario);
		String addOnModMenu = null;
		if (condition.equalsIgnoreCase("beforeModify")) {
			addOnModMenu = datatable.getDataParameter("AddOnMenu");
		} else {
			addOnModMenu = datatable.getDataParameter("ModifyAddOnMenu");
		}
		pageLoaded(eleModifiedAddOnProduct);
		eleModifiedAddOnProduct.highlight(driver);
		TestReporter.logStep("addOnmenuText : " + addOnModMenu);
		eleModifiedAddOnProduct.highlight(driver);
		TestReporter.logStep("Getting Modified Add On Name : "
				+ eleModifiedAddOnProduct.getText());
		TestReporter.assertTrue(
				eleModifiedAddOnProduct.getText().contains(addOnModMenu),
				"AddOn Product mismatched");
	}

	/**
	 * @summary Clicks Done Button
	 * @author Stagliano, Dennis
	 * @version 1/12/2016 Stagliano, Dennis - original
	 */
	public void clickDone() {
		initialize();
		btnDone.click();
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void launchMagicalCelebrations() {
		pageLoaded(lnkCelebrations);
		lnkCelebrations.scrollIntoView(driver);
		lnkCelebrations.highlight(driver);
		lnkCelebrations.syncVisible(driver);
		lnkCelebrations.jsClick(driver);

		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Magical Celebrations");

		Sleeper.sleep(3000);

		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Magical Celebrations");

		new PageLoaded().isDomComplete(driver);
		new PageLoaded().isDomInteractive(driver);
	}
}


