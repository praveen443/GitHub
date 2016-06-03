package apps.lilo.bankIn;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.lilo.bankOut.BankOutPage;
import apps.lilo.resortFunctions.ResortFunctionsPage;
import apps.lilo.switchLocation.SwitchLocationPage;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Label;
import core.interfaces.Listbox;
import core.interfaces.Webtable;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

/**
 * @summary Contains the page methods & objects to bank-in to a resort
 * @version Created 8/15/2014
 * @author Justin Phlegar
 */
public class BankInPage {
	//
	// Page Fields
	//
	private Datatable datatable = new Datatable();
	private WebDriver driver;
	
	// **********************************
	// *** Main Bank In Page Elements ***
	// **********************************

	// Create Element for Bank In Popup Modal
	@FindBy(id = "bankInModalPanelCDiv")
	private Element eleBankInModal;

	// Create List object for Bank In Locations
	@FindBy(id = "bankInForm:locationType")
	private Listbox lstLocations;

	// Create List object for Till Types
	@FindBy(id = "bankInForm:tillType")
	private Listbox lstTillTypes;

	// Create Label for Change Funds
	@FindBy(id = "bankInForm:tillValueId")
	private Label labChangeFunds;

	// Create Button for Bank In
	@FindBy(id = "bankInForm:bankIn")
	private Button btnBankIn;

	// Create Button for Cancel Bank In
	@FindBy(id = "bankInForm:cancelBankIn")
	private Button btnCancelBankIn;

	// BanlIn Table
	@FindBy(id = "bankInModalPanelContentTable")
	private Webtable tblbankInModalPanelContentTable;

	// Bank In Label
	@FindBy(xpath = "//div[@id='bankInModalPanelHeader']/table/tbody/tr/td[2]/div")
	private Label lblBankIn;

	// *******************************
	// ***Already Banked In objects***
	// *******************************
	// Create Element for Location Resort
	@FindBy(css = "//*[@id=bankInForm]/table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td")
	private Label labBankInLocation;

	// Create Button for BankIn Ok
	@FindBy(id = "bankInForm:routeId")
	private Button btnContinueBankIn;

	// Grab the Go Button
	@FindBy(className = "go")
	private Button btnGo;

	// Create List object for Bank In Locations in FDO
	@FindBy(id = "resortDrodown")
	private Listbox dropDown;
	
	@FindBy(css = "#bankInForm > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1)")
	private Label lblBankedInLocation;

	// *********************
	// ** Build page area **
	// *********************

	/**
	 * 
	 * @summary Constructor to initialize the page
	 * @version Created 8/15/2014
	 * @author Justin Phlegar
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public BankInPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public BankInPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, eleBankInModal);
	}
	
    /**
    * @summary Method to determine if a page is loaded
    * @author Waightstill W Avery
    * @version 11/05/2015	 Waightstill W Avery - original
	* @param element - Element to be used to determine if the page is loaded
    * @return Boolean, true if the page is loaded, false otherwise
    */
    public boolean pageLoaded(Element element){
    	return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element); 
    }

	// *****************************
	// ***Banked In Interactions ***
	// *****************************

	/**
	 * 
	 * @summary MEthod to determine if a LILO user is already banked in
	 * @version Created 8/15/2014
	 * @author Justin Phlegar
	 * @param NA
	 * @throws NA
	 * @return true if the user is banked in, false otehrwise
	 * 
	 */
	private boolean isUserBankedIn() {
		eleBankInModal.syncVisible(driver);
		Sleeper.sleep(1000);
		String text = eleBankInModal.getText();
		return text.contains("You are already banked in this location.");
	}

	private void clickBankIn() {
		initialize();
		if (isUserBankedIn()) {
			pageLoaded(btnContinueBankIn);
			btnContinueBankIn.syncVisible(driver);
			Sleeper.sleep(1000);
			btnContinueBankIn.jsClick(driver);
		} else {
			pageLoaded(btnBankIn);
			btnBankIn.syncVisible(driver);
			btnBankIn.jsClick(driver);
		}
	}

	public void clickBankInDirect() {
		if (isUserBankedIn()) {
			btnContinueBankIn.jsClick(driver);
		} else {
			btnBankIn.jsClick(driver);
		}
	}

	/**
	 * 
	 * @summary Method to bank into a resort. If the user is not already banked
	 *          in, the resort and role are chosen and then banked in
	 * @version Created 9/11/2014
	 * @author Waightstill W Avery
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	@Deprecated
	public void bankIn(String resort, String role) {
		String inputRole = "";

		if (isUserBankedIn()) {
			clickBankIn();
		} else {
			lstLocations.select(resort);
			Sleeper.sleep(1000);

			if (role.indexOf("NOTILL") > 0) {
				inputRole = "No Till";
			} else if (role.indexOf("MANAGER") > 0) {
				inputRole = "Manager";
			}
			lstTillTypes.select(inputRole);
			clickBankIn();
		}
	}

	public void bankInFDO(String resort) {
		Sleeper.sleep(5000);
		try {
			if (dropDown.isDisplayed()) {
				dropDown.highlight(driver);
				// pageLoaded(dropDown);
				dropDown.select(resort);
				TestReporter.logStep("when user is Banked-Out");
				btnGo.highlight(driver);
				btnGo.click();
				Sleeper.sleep(500);

			}
		} catch (Exception e) {
			TestReporter.logStep("when user is Banked-In");
			// e.printStackTrace();
		}
	}

	/**
	 * 
	 * @summary Method to bank into a resort. If the user is not already banked
	 *          in, the resort, role are chosen and then banked in switch to
	 *          location if resort is not matched
	 * @version Created 9/10/2015
	 * @author Brajesh Ahirwar
	 * @param resort
	 *            and role
	 * @throws NA
	 * @return NA
	 * 
	 */

	public boolean isBankInPage() {
		tblbankInModalPanelContentTable.syncVisible(driver, 30, false);
		String lblBank = lblBankIn.getText().trim();
		return lblBank.contains("Bank In");

	}

	public void clickOnOk() {
		tblbankInModalPanelContentTable.syncVisible(driver, 30, false);
		btnContinueBankIn.jsClick(driver);
		Sleeper.sleep(1000);
	}
	
	
	
	
	
	
	
	
	/**
	 * This method banks into the desired resort.  2 conditions are tested
	 * 1. Is the user bank in.  If not, bank-in to the desired resort will commence.
	 * 2. If banked-in, is the user banked in at the correct resort. 
	 * 		If so, bank-in will commence.  If not, bank-in will commence, 
	 * 		the bank out process will be invoked, the switch locations process 
	 * 		will be invoked, and then bank-in can commence
	 * @author Waightstill W Avery
	 * @version 11/05/2015 - Waightstill W Avery - Original
	 * 
	 */
	public void bankInVerifyResort(String resort, String role){
		TestReporter.log("Current Location: " + lblBankedInLocation.getText().split(":")[1].trim());
		System.out.println("Current Location: " + lblBankedInLocation.getText().split(":")[1].trim());
		if(lblBankedInLocation.getText().split(":")[1].trim().contains("1001")){
			TestReporter.logFailure("An error occurred while trying to bank-in. Bank-in modal text is: ["+lblBankedInLocation.getText()+"].");
		}
		TestReporter.log("Desired Resort: " + resort);
	//	System.out.println("Desired Resort: " + resort);
		TestReporter.log("Desired Till: " + role);
	//	System.out.println("Desired Till: " + role);
		//Determine if the user is already banked-in
		if (isUserBankedIn()) {
			//Determine if the user is banked-in to the correct resort
			if(isUserAtCorrectResort(resort)){
				TestReporter.log("Banked into the correct location");
			//	System.out.println("Banked into the correct location");
				clickBankIn();
			}
			//If not, then bank-in, bank-out and switch-locations
			else{
				TestReporter.log("Switch locations");
		//		System.out.println("Switch locations");
				bankInToSwitchLocations(resort, role);
			}
		}
		//If the user is not already banked-in, then select the resort and role and complete bank-in
		else {
			selectResortAndRole(resort, role);
		}
	}
	
    /**
    * @summary Method to select a resort and role and click Bank-In
    * @author Waightstill W Avery
    * @version 11/05/2015	 Waightstill W Avery - original
	* @param resort - name of the resort as it is found in the application
	* @param role - name of th till type to select
    */
	private void selectResortAndRole(String resort, String role){
		String inputRole = "";
		
		initialize();
		pageLoaded(lstLocations);
		lstLocations.syncVisible(driver);
		lstLocations.select(resort);
		Sleeper.sleep(1000);

		if (role.indexOf("NOTILL") > 0) {
			inputRole = "No Till";
		} else if (role.indexOf("MANAGER") > 0) {
			inputRole = "Manager";
		}
		lstTillTypes.select(inputRole);
		clickBankIn();
	}
	
    /**
    * @summary Method to determine if the user is at the correct resort
    * @author Waightstill W Avery
    * @version 11/05/2015	 Waightstill W Avery - original
	* @param resort - name of the resort with which to verify the user's current location
    * @return Boolean, true if the user is banked into the correct resort
    */
	private boolean isUserAtCorrectResort(String resort){
		String bankInLabelText = lblBankedInLocation.getText().split(":")[1].trim();
		TestReporter.log("Current Location: " + bankInLabelText);
		System.out.println("Current Location: " + bankInLabelText);
		return bankInLabelText.toLowerCase().contains(resort.toLowerCase());
	}
	
    /**
    * @summary Method to bank-in, switch locations and bank-in to the new location
    * @author Waightstill W Avery
    * @version 11/05/2015	 Waightstill W Avery - original
	* @param resort - resort to swithc to and bank-in
	* @param role - till type to use
    */
	private void bankInToSwitchLocations(String resort, String role){
		clickBankIn();
		
		ResortFunctionsPage rfp = new ResortFunctionsPage(driver);
		rfp.verifyBankOutLinkDisplayed();
		rfp.enterBankout();
		
		BankOutPage bankOut = new BankOutPage(driver);
		bankOut.bankOut("Final Deposit");
		
		rfp.verifySwitchLocationDisplayed();
		rfp.enterSwitchLocations();
		
		SwitchLocationPage switchLoc = new SwitchLocationPage(driver);
		switchLoc.switchLocations(resort, role);
		
		TestReporter.assertTrue(rfp.verifyBankOutLinkDisplayed(), "The Bank-Out link was not displayed as expected.");
	}

}

