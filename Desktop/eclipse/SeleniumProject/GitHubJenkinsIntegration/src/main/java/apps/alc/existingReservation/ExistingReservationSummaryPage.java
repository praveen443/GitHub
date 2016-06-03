package apps.alc.existingReservation;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import apps.alc.pleaseWait.PleaseWait;
import core.WebDriverSetup;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Label;
import core.interfaces.Webtable;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

/**
 * This class contains elements and element interactions for a given page of a web application
 * 
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
 */
public class ExistingReservationSummaryPage {
	//*******************
	//	Page Class Fields
	//*******************
	//Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private String reservationDetails;
	private String reservationNumber = "";
	private Datatable datatable = new Datatable();
	
	//*********************
	//	Page Class Elements
	//*********************
	@FindBy(id = "modreservationSummaryForm")
	private Element eleModifyReservationSummaryForm;;
    
	@FindBy(id = "logoutForm1:marketingPrefID")
	private Button btnPtoiMarketingPreferencesRequired;
	
	@FindBy(css = "input[value='Unable To Confirm']")
	private Button btnPtoiUnableToConfirm;

	@FindBy(id = "ptoiQuestionPanelIdContentDiv")
	private Element tblPtoiSpiel;
	
	@FindBy(css = "input[value='Info']")
	private Button btnPtoiInfo;
	
	@FindBy(id = "ptoiQuestionSubPanelIdContentTable")
	private Webtable tblPtoiInfo;
	
	@FindBy(xpath = "/html/body/div[6]/div[2]/div/div[2]/table/tbody/tr[2]/td/form/table[2]/tbody/tr/td/input")
	private Button btnPtoiInfoClose;
	
	@FindBy(id = "logoutForm1:editMarketingPrefID")	
	private Button btnPtoiEditMarketingPreferences;
	
	@FindBy(css = "input[value='Third Party CC']")
	private Button btnPtoiThirdPartyCC;
	
	@FindBy(xpath = "/html/body/div[6]/div[2]/div/div[2]/table/tbody/tr[2]/td/form/table[2]/tbody/tr/td[2]/table/tbody/tr/td/input[2]")
	private Button btnPtoiSave;

	@FindBy(id = "modreservationSummaryForm:modreservationSummaryRepeat:0:modbookedReservationSummaryOutputPanel")
	private Label lblReservationDetails;
	
	@FindBy(xpath = "//*[@id='reservationSummaryForm:reservationSummaryTotalPriceOutputPanel']/table/tbody/tr[2]/td[2]")
	private Element eleTotalPaid;
	
	@FindBy(xpath = "//*[@id='reservationSummaryForm:reservationSummaryTotalPriceOutputPanel']/table/tbody/tr[2]/td[3]")
	private Element eleTotalCost;
	
	@FindBy(xpath = "//*[@id='reservationSummaryForm:reservationSummaryTotalPriceOutputPanel']/table/tbody/tr[3]")
	private Element eleDifferenceOwed;
	
    //*********************
    //** Build page area **
    //*********************
    /**
    * @summary Constructor to initialize the page
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
    * @param  driver
    */
    public ExistingReservationSummaryPage(WebDriver driver) {
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
	public  ExistingReservationSummaryPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());	        
	}

    /**
    * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
    * @return Boolean, true if the page is loaded, false otherwise
    */
    public boolean pageLoaded(){
    	return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, eleModifyReservationSummaryForm); 
    }  

    /**
    * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
	* @param element - Element to be used to determine if the page is loaded
    * @return Boolean, true if the page is loaded, false otherwise
    */
    public boolean pageLoaded(Element element){
    	return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element); 
    }  

	//********************
	//	Page Class Methods
	//********************

	
	/**
	 * Handle the PTOI popup should it appear
	 * @param scenario - scenario describing how to handle the popup
	 */
	public void handlePtoi(String scenario, String guestName){
		datatable.setVirtualtablePage("Summary");
		datatable.setVirtualtableScenario(scenario);
		
		String emailOptIn = datatable.getDataParameter("EmailOptIn");
		String mailOptIn = datatable.getDataParameter("MailOptIn");
		String thirdParty = datatable.getDataParameter("ThirdParty");
		String unableToConfirm = datatable.getDataParameter("UnableToConfirm");
		String validateInfo = datatable.getDataParameter("ValidateInfo");
		String validateSpiel = datatable.getDataParameter("ValidateSpiel");
		String expectedSpiel = datatable.getDataParameter("Spiel");
		String expectedInfo = datatable.getDataParameter("Info");
		
		initialize();
		pageLoaded(btnPtoiMarketingPreferencesRequired);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//Determine if the PTOI button exists which requires
		if(btnPtoiMarketingPreferencesRequired.syncVisible(driver)){
			btnPtoiMarketingPreferencesRequired.jsClick(driver);
			PleaseWait.WaitForPleaseWait(driver);
			pageLoaded(btnPtoiUnableToConfirm);
			btnPtoiUnableToConfirm.highlight(driver);
			btnPtoiUnableToConfirm.syncVisible(driver);
			TestReporter.assertTrue(tblPtoiSpiel.getText().contains(guestName), "The primary guest name ["+guestName+"] was not found in the PTOI page.");
			
			//Determine if the spiel is to be validated
			if(validateSpiel.equalsIgnoreCase("true")){
				ptoiValidateSpiel(expectedSpiel);
			}
			
			//Determine if the info is to be validated
			if(validateInfo.equalsIgnoreCase("true")){
				ptoiValidateInformation(expectedInfo);
			}
			
			//Determine if Unable To Confirm
			if(unableToConfirm.equalsIgnoreCase("true")){
				ptoiClickUnableToConfirm();
				ptoiVerifyMarketingPreferencesSaved(emailOptIn, mailOptIn, thirdParty, unableToConfirm);
			}
			//Determine if using a third party credit card
			else if(thirdParty.equalsIgnoreCase("true")){
				ptoiClickThirdParty();
				ptoiVerifyMarketingPreferencesSaved(emailOptIn, mailOptIn, thirdParty, unableToConfirm);
			}
			//Determine if Marketing Preferences are to be entered
			else if(!mailOptIn.isEmpty()){
				ptoiEnterMarketingPreferences(emailOptIn, mailOptIn);
				ptoiVerifyMarketingPreferencesSaved(emailOptIn, mailOptIn, thirdParty, unableToConfirm);
			}
			//For all other cases, handle the PTOI popup
			else{
				ptoiClickUnableToConfirm();
			}
		}else{
			TestReporter.log("PTOI Marketing Preferences button is not visible.");
		}
		
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
	}
	
	/**
	 * This method validates the text contained in the Spiel
	 * @param expectedSpiel - expected information contents
	 */
	private void ptoiValidateSpiel(String expectedSpiel){
		boolean spielConfirmed = false;
		String actualSpiel = tblPtoiSpiel.getText().trim().replace(" ", "");
		if(actualSpiel.contains(expectedSpiel.trim().replace(" ", ""))){
			spielConfirmed = true;
		}
		
		TestReporter.assertTrue(spielConfirmed, "The expected spiel was not found. The actual spiel is ["+actualSpiel+"].");
	}
	
	/**
	 * This method validates the text contained in the Information window
	 * @param expectedInfo
	 */
	private void ptoiValidateInformation(String expectedInfo){
		boolean infoConfirmed = false;
		String actualInfo;
		
		btnPtoiInfo.jsClick(driver);
		pageLoaded(tblPtoiInfo);
		actualInfo = tblPtoiInfo.getText().trim().replace(" ", "");
		if(actualInfo.contains(expectedInfo.trim().replace(" ", ""))){
			infoConfirmed = true;
		}
		
		TestReporter.assertTrue(infoConfirmed, "The expected info was not found. The actual spiel is ["+actualInfo+"].");
		
		btnPtoiInfoClose.jsClick(driver);
	}
	
	/**
	 * This method clicks the Unable To Confirm button and syncs to the Summary page being loaded
	 */
	private void ptoiClickUnableToConfirm(){
		btnPtoiUnableToConfirm.jsClick(driver);
		initialize();
		pageLoaded(btnPtoiEditMarketingPreferences);
	}

	/**
	 * This method clicks the Third Party CC button and syncs to the Summary page bring loaded
	 */
	private void ptoiClickThirdParty(){
		btnPtoiThirdPartyCC.jsClick(driver);
		initialize();
		pageLoaded(btnPtoiEditMarketingPreferences);
	}
	
	/**
	 * This method enters and saves the marketing preferences
	 * @param emailOptIn - email marketing preference
	 * @param mailOptIn - mail marketing preference
	 */
	private void ptoiEnterMarketingPreferences(String emailOptIn, String mailOptIn){
		List<WebElement> optOuts = driver.findElements(By.xpath("//input[@value='Opt-Out']"));
		Element elePtoiEmailOptOut = new ElementImpl(optOuts.get(0));
		Element elePtoiMailOptOut = new ElementImpl(optOuts.get(1));
		List<WebElement> optIns = driver.findElements(By.xpath("//input[@value='Opt-In']"));
		Element elePtoiEmailOptIn = new ElementImpl(optIns.get(0));
		Element elePtoiMailOptIn = new ElementImpl(optIns.get(1));
		
		//Process the email marketing preferences
		if(emailOptIn.equalsIgnoreCase("true")){
			TestReporter.assertTrue(elePtoiEmailOptIn.syncVisible(driver), "The email opt-in radio button was not visible.");
			elePtoiEmailOptIn.highlight(driver);
			elePtoiEmailOptIn.jsClick(driver);
		}else{
			TestReporter.assertTrue(elePtoiEmailOptOut.syncVisible(driver), "The email opt-in radio button was not visible.");
			elePtoiEmailOptOut.highlight(driver);
			elePtoiEmailOptOut.jsClick(driver);
		}
		
		Sleeper.sleep(500);
		
		//Process the mail marketing preferences
		if(mailOptIn.equalsIgnoreCase("true")){
			TestReporter.assertTrue(elePtoiMailOptIn.syncVisible(driver), "The mail opt-in radio button was not visible.");
			elePtoiMailOptIn.highlight(driver);
			elePtoiMailOptIn.jsClick(driver);
		}else{
			TestReporter.assertTrue(elePtoiMailOptOut.syncVisible(driver), "The mail opt-out radio button was not visible.");
			elePtoiMailOptOut.highlight(driver);
			elePtoiMailOptOut.jsClick(driver);
		}
		
		Sleeper.sleep(500);
		
		ptoiClickSave();
	}
	
	private void ptoiClickSave(){
		initialize();
		pageLoaded(btnPtoiSave);
//		btnPtoiSave.syncEnabled(driver);
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		List<WebElement> saves = driver.findElements(By.xpath("//input[@value = 'Save']"));
		for(WebElement save : saves){
			Element ele = new ElementImpl(save);
			if(ele.syncVisible(driver, 1, false)){
				ele.highlight(driver);
				ele.jsClick(driver);
			}
		}
		initialize();
		pageLoaded(btnPtoiEditMarketingPreferences);
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
	}
	
	private void ptoiVerifyMarketingPreferencesSaved(String emailOptIn, String mailOptIn, String thirdParty, String unableToConfirm){
		initialize();
		pageLoaded(btnPtoiEditMarketingPreferences);
		btnPtoiEditMarketingPreferences.syncVisible(driver);
		btnPtoiEditMarketingPreferences.jsClick(driver);
		initialize();
		
		//Determine if third party. If so, no marketing preferences should be entered
		if(thirdParty.equalsIgnoreCase("true")){
			ptoiVerifyThirdParty();
		}
		//Determine if unable to confirm. If so, both marketing preferences should be opt-out
		else if(unableToConfirm.equalsIgnoreCase("true")){
			ptoiVerifyUnableToConfirm();
		}
		//Otherwise, verify marketing preferences were saved
		else{
			ptoiVerifyOptInOptOut(emailOptIn, mailOptIn);
		}
	}
	
	private void ptoiVerifyThirdParty(){
		List<WebElement> optOuts = driver.findElements(By.xpath("//input[@value='Opt-Out']"));
		Element elePtoiEmailOptOut = new ElementImpl(optOuts.get(0));
		Element elePtoiMailOptOut = new ElementImpl(optOuts.get(1));
		List<WebElement> optIns = driver.findElements(By.xpath("//input[@value='Opt-In']"));
		Element elePtoiEmailOptIn = new ElementImpl(optIns.get(0));
		Element elePtoiMailOptIn = new ElementImpl(optIns.get(1));
		
		System.out.println("Validating preferences");
		
		try{
			TestReporter.assertFalse(elePtoiEmailOptIn.isSelected(), "The email opt-in marketing preference was selected when no selection was expected.");
			TestReporter.assertFalse(elePtoiEmailOptOut.isSelected(), "The email opt-out marketing preference was selected when no selection was expected.");
			TestReporter.assertFalse(elePtoiMailOptIn.isSelected(), "The mail opt-in marketing preference was selected when no selection was expected.");
			TestReporter.assertFalse(elePtoiMailOptOut.isSelected(), "The mail opt-out marketing preference was selected when no selection was expected.");			
		}catch(Exception e){
			System.out.println("Grumble Grumble");
			
		}
		ptoiClickThirdParty();
	}
	
	private void ptoiVerifyUnableToConfirm(){
		List<WebElement> optOuts = driver.findElements(By.xpath("//input[@value='Opt-Out']"));
		Element elePtoiEmailOptOut = new ElementImpl(optOuts.get(0));
		Element elePtoiMailOptOut = new ElementImpl(optOuts.get(1));
		
		System.out.println("Validating preferences");
		try{
			TestReporter.assertTrue(elePtoiEmailOptOut.isSelected(), "The email opt-out marketing prefernce was not selected as expected.");
			TestReporter.assertTrue(elePtoiMailOptOut.isSelected(), "The mail opt-out marketing prefernce was not selected as expected.");
		}catch(Exception e){
			System.out.println("Grumble Grumble");
			
		}
		ptoiClickUnableToConfirm();
	}
	
	private void ptoiVerifyOptInOptOut(String emailOptIn, String mailOptIn){
		List<WebElement> optOuts = null;
		do{
			System.out.println("Grabbing Opt-Outs.");
			Sleeper.sleep(100);
			optOuts = driver.findElements(By.xpath("//input[@value='Opt-Out']"));
		}while(optOuts.size() < 2);
		System.out.println("Opt-Outs found.");
		Element elePtoiEmailOptOut = new ElementImpl(optOuts.get(0));
		Element elePtoiMailOptOut = new ElementImpl(optOuts.get(1));
		List<WebElement> optIns = driver.findElements(By.xpath("//input[@value='Opt-In']"));
		Element elePtoiEmailOptIn = new ElementImpl(optIns.get(0));
		Element elePtoiMailOptIn = new ElementImpl(optIns.get(1));
		
		System.out.println("Validating preferences");
		try{
			//Validate the email marketing preferences
			if(emailOptIn.equalsIgnoreCase("true")){
				TestReporter.assertTrue(elePtoiEmailOptIn.isSelected(), "The email opt-in marketing preference was not selected as expected.");
			}else{
				TestReporter.assertTrue(elePtoiEmailOptOut.isSelected(), "The email opt-out marketing preference was not selected as expected.");
			}
			
			//Validate the mail marketing preferences
			if(mailOptIn.equalsIgnoreCase("true")){
				TestReporter.assertTrue(elePtoiMailOptIn.isSelected(), "The mail opt-in marketing preference was not selected as expected.");
			}else{
				TestReporter.assertTrue(elePtoiMailOptOut.isSelected(), "The mail opt-out marketing preference was not selected as expected.");
			}
		}catch(Exception e){
			System.out.println("Grumble Grumble");
		}
		
		ptoiClickSave();
	}

	private String captureReservationNumber() {
		captureReservationDetails();
		int start = reservationDetails.indexOf("Reservation Number: ");
		String anchor = "Reservation Number: ";
		int anchorLength = anchor.length();
		int reservationNumberLength = 12;
		reservationNumber = reservationDetails.substring(start + anchorLength,
				start + anchorLength + reservationNumberLength);
		Reporter.log("Reservation number [" + reservationNumber + "]", true);
		return reservationNumber;
	}

	public String getReservationNumber() {
		if(reservationNumber.isEmpty()) captureReservationNumber();
		return reservationNumber;
	}

	private void captureReservationDetails() {
		reservationDetails = lblReservationDetails.getText();
	}
	
	/**
	 * @summary: Method to get Payment or Balance Due Details
	 * @author : Venkatesh Atmakuri - Original.
	 * @version: created 1-22-2016.
	 * @param   NA
	 * @return  NA
	 */
	public void getPaymentOrBalanceDetails(){
		initialize();
		pageLoaded(eleTotalPaid);
		eleTotalCost.highlight(driver);
		TestReporter.logStep(" Total Cost : "+eleTotalCost.getText());
		eleTotalPaid.highlight(driver);
		TestReporter.logStep(" Total Paid : "+eleTotalPaid.getText());
		eleDifferenceOwed.highlight(driver);
		TestReporter.logStep(eleDifferenceOwed.getText());
	}

	public String existingReservation_SummaryDetails(){
		String ResNo = captureReservationNumber();
		return ResNo;
	}
	/**
	 * @summary: Method to report complete summary of an existing reservation
	 * @author : Stagliano, Dennis - Original.
	 * @version: created 2-2-2016.
	 * @param   NA
	 * @return  NA
	 */
	public void ReportCompleteSummaryDetails(){
		initialize();	
		List<WebElement> trCollection = lblReservationDetails.findElements(By.tagName("tr"));
		StringBuffer sb = new StringBuffer(" ");
		for (WebElement trow : trCollection) {
			List<WebElement> tdcoll = trow.findElements(By.tagName("td"));
			for (WebElement tdElement : tdcoll) {
				    if(!tdElement.getText().isEmpty()){
				    	sb.append(System.getProperty("line.separator"));			
						sb.append(tdElement.getText() + " ");
				    }			
			}
		}
		TestReporter.log(sb.toString());
	}
	
	
}

