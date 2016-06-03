package apps.lilo.administration;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.WebDriverSetup;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Link;
import core.interfaces.Webtable;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;

/**
* @summary Contains the elements and methods to interact with the LILO UI Accouting Canter page
* @version Created 10/03/2014
* @author Waightstill W. Avery
*/

public class AccountingCenterPage {
	//**************************
	//*** Accounting Center page Fields ***
	//**************************
	private int timeout = WebDriverSetup.getDefaultTestTimeout();
	private int loopCounter;
	private Datatable datatable = new Datatable();
	private WebDriver driver;

	//**************************
	//*** Accounting Center page Elements ***
	//**************************

	//Search button
	@FindBy(id = "accntCenterSearchForm:searchButtonId")
	private Link lnkSearch;
	
	//Search Results webtable
	@FindBy(id = "accntCenterSearchForm:summaryList")
	private Webtable tblSearchResults;
	
	//Edit button
	@FindBy(id = "accntCenterSearchForm:editButtonId")
	private Link lnkEdit;
	
	//Accounting Center Edit Cancel button
	@FindBy(id = "accntForm:cancelID")
	private Link lnkEditCancel;
	
	//Accounting Center Edit Sumamry List webtable
	@FindBy(id = "accntForm:centralizedSummaryList")
	private Webtable tblEditSummaryList;
	
	//Edit Cancel Confirm Yes button
	@FindBy(id = "confirmationPopupForm:okButtonId")
	private Button btnEditCancelYes;
	
	//*********************
    //** Build page area **
    //*********************

    /**
    * 
    * @summary Constructor to initialize the page
    * @version Created 10/03/2014
    * @author Waightstill W Avery
    * @param  driver
    * @throws NA
    * @return NA
    * 
    */
	public AccountingCenterPage(WebDriver driver){
		this.driver = driver;	
		ElementFactory.initElements(driver, this);  
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, lnkSearch);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	public AccountingCenterPage initialize() {
		return ElementFactory.initElements(driver,
				this.getClass());
	}

    //**************************************
  	//*** Accounting Center Page Interactions ***
  	//**************************************
	
	private void clickSearch(){
		lnkSearch.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		 loopCounter = 0;
		 do{
			 Sleeper.sleep(1000);
			 Assert.assertNotEquals(loopCounter++, timeout, "The search result table was not displayed after ["+String.valueOf(timeout)+"] seconds.");
			 pageLoaded(tblSearchResults);
			 initialize();
		 }while(tblSearchResults == null);
	}
	
	private void selectTheFirstCenterForEditing(){
		clickSearch();
		
		new ElementImpl(driver.findElement(By.xpath("//table[@id='accntCenterSearchForm:summaryList']/tbody/tr[1]"))).jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		
		loopCounter = 0;
		do{
			Sleeper.sleep(1000);
			Assert.assertNotEquals(loopCounter++, timeout, "The Edit button was not displayed after ["+String.valueOf(timeout)+"] seconds.");
			pageLoaded(lnkEdit);
			initialize();
		}while(lnkEdit == null);
		
		clickEdit();
	}
	
	private void clickEdit(){
		lnkEdit.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		
		loopCounter = 0;
		do{
			Sleeper.sleep(1000);
			Assert.assertNotEquals(loopCounter++, timeout, "The Edit Accounting Center window was not displayed after ["+String.valueOf(timeout)+"] seconds.");
			pageLoaded(lnkEditCancel);
			initialize();
		}while(lnkEditCancel == null);
	}
	
	public void verifyAccountinCenterDateFormat(){
		pageLoaded(lnkSearch);
		initialize();
		selectTheFirstCenterForEditing();
		
		pageLoaded(tblEditSummaryList);
		
		Element element = new ElementImpl(driver.findElement(By.xpath("//table[@id='accntForm:centralizedSummaryList']/tbody/tr/td[10]")));
		String beginDate = element.getText();
		element = new ElementImpl(driver.findElement(By.xpath("//table[@id='accntForm:centralizedSummaryList']/tbody/tr/td[11]")));
		String endDate = element.getText();
		
		String[] dates = {beginDate, endDate};
		String[] dateType = {"Begin Date", "End Date"};
		
		for(loopCounter = 0; loopCounter < 2; loopCounter++){
			String[] dateParts = dates[loopCounter].split("/");
			Assert.assertEquals(dateParts[0].length(), 2, "The ["+dateType[0]+"] month format is incorrectly formatted as ["+dateParts[0]+" when a 2-digit format ["+dateType[0]+"was expected");
			Assert.assertEquals(dateParts[1].length(), 2, "The ["+dateType[0]+"] day format is incorrectly formatted as ["+dateParts[1]+" when a 2-digit format ["+dateType[0]+"was expected");
			Assert.assertEquals(dateParts[2].length(), 4, "The ["+dateType[0]+"] year format is incorrectly formatted as ["+dateParts[2]+" when a 2-digit format ["+dateType[0]+"was expected");
		}
		
		cancelEdit();
	}
	
	private void cancelEdit(){
		lnkEditCancel.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		
		loopCounter = 0;
		do{
			Sleeper.sleep(1000);
			Assert.assertNotEquals(loopCounter++, timeout, "The Cancel Confirmation popup was not displayed after ["+String.valueOf(timeout)+"] seconds.");
			pageLoaded(btnEditCancelYes);
			initialize();
		}while(btnEditCancelYes == null);
		
		btnEditCancelYes.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);		
	}
}

