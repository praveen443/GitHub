package apps.dreams.HeaderFrame;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.dreams.PleaseWait.PleaseWait;
import core.FrameHandler;
import core.WindowHandler;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;

/**
* @summary Contains the methods & objects for the Dreams UI header frame
* @version Created 09/10/2014
* @author Waightstill W. Avery
*/
public class HeaderFrame {
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	
	//********************************
	//*** Discovery Frame Elements ***
	//********************************
	@FindBy(name = "b_LogOut")
	private Button btnLogOut;
	
	@FindBy(name = "b_back")
	private Button btnBack;
	
	@FindBy(id = "b_continue")
	private Button btnContinue;
	
	@FindBy(name = "b_confirmAll")
	private Button btnConfirmAll;
	
	@FindBy(name = "b_Yes")
	private Button btnYes;
	
	@FindBy(name = "b_recap")
	private Button btnRecap;
	
	@FindBy(name = "b_Exit")
	private Button btnExit;
	
	@FindBy(name = "b_clear")
	private Button btnClear;
	
    //*********************
    //** Build page area **
    //*********************

    /**
    * 
    * @summary Constructor to initialize the frame
    * @version Created 09/10/2014
    * @author Waightstill W Avery
    * @param  driver
    * @throws NA
    * @return NA
    * 
    */
	public HeaderFrame(WebDriver driver){
		this.driver = driver;
		FrameHandler.findAndSwitchToFrame(driver, "header");
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnLogOut);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	public HeaderFrame initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}	

	//*********************************
	//*** Header Frame Interactions ***
	//*********************************
    public void clickLogOut(){
    	initialize();
    	pageLoaded(btnLogOut);
    	btnLogOut.click();
    	//btnLogOut.jsClick(driver);
    	//((JavascriptExecutor)driver).executeScript("document.getElementById(\"b_LogOut\").click();");
    	PleaseWait.WaitForPleaseWait(driver);
    	clickonYesButton();
    }

    public void clickBack(){
    	initialize();
    	pageLoaded(btnBack);
    	btnBack.jsClick(driver);
    	//((JavascriptExecutor)driver).executeScript("document.getElementById(\"b_back\").click();");
    	PleaseWait.WaitForPleaseWait(driver);
    }
    
    public void clickContinue(){
    //	new PageLoaded().isDomComplete(driver);
    	new PageLoaded().isDomInteractive(driver);
    	pageLoaded(btnContinue);
    	btnContinue.highlight(driver);
    	btnContinue.click();
    	//((JavascriptExecutor)driver).executeScript("document.getElementById(\"b_continue\").click();");
    	PleaseWait.WaitForPleaseWait(driver);
    	try{
    		try{
    			WindowHandler.waitUntilNumberOfWindowsAre(driver, 2, 5);
    		}catch(TimeoutException te){}
    		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
    		WindowHandler.waitUntilWindowExistsTitleContains(driver,"Disney Reservation Entry and Management System");
        	new PageLoaded().isDomComplete(driver);
        	new PageLoaded().isDomInteractive(driver);
    	}catch(Exception e){
    		Sleeper.sleep(5000);
    	}
    }
    
    public void clickContinueError(){
    	new PageLoaded().isDomComplete(driver);
    	new PageLoaded().isDomInteractive(driver);
    	pageLoaded(btnContinue);
    	btnContinue.highlight(driver);
    	btnContinue.click();
    	PleaseWait.WaitForPleaseWait(driver);
    }
    
    public void clickConfirmall(){
    	pageLoaded(btnConfirmAll);
    	btnConfirmAll.syncVisible(driver);
    	btnConfirmAll.syncEnabled(driver);
    	btnConfirmAll.highlight(driver);
    	btnConfirmAll.jsClick(driver);
    	//((JavascriptExecutor)driver).executeScript("document.getElementById(\"b_confirmAll\").click();");
        try{
        	PleaseWait.WaitForPleaseWait(driver);
        }catch(Exception e){
        	
        }
    }
    
    public void clickYes(){
        pageLoaded(btnYes);
        btnYes.jsClick(driver);
    }
       
    public void clickonYesButton() {
	     WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
	     WindowHandler.waitUntilWindowExistsTitleContains(driver, "WARNING");
	     clickYes();
    }

    /**
	 * 
	 * @summary Methods to validate the buttons in Header Frame are in disabled/Enabled state.
	 * @version Created 03/23/2016, @author Praveen Namburi,
	 * @param locator name, @throws NA
	 * @return true if disabled, false otherwise
	 */
	private boolean validateHeaderButtonsareDisabled(Element locatorName) {

		boolean isDisabled = false;
		pageLoaded(locatorName);
		Sleeper.sleep(3000);
		if (!locatorName.isEnabled()) {
			isDisabled = true;
		}
		
		return isDisabled;
	}
	
	private boolean validateHeaderButtonsareEnabled(Element locatorName) {

		boolean isEnabled = false;
		pageLoaded(locatorName);
		Sleeper.sleep(3000);
		if (locatorName.isEnabled()) {
			isEnabled = true;
		}
		return isEnabled;
	}
	
	/**
	 * @summary: Created the below methods to validate whether the
	 * 			 Header buttons are in disabled status
	 * @author: Praveen Namburi, @version: Created 3/23/2016
	 * @return
	 */
	public boolean verifyBackButtonIsDisabled() {
		pageLoaded(btnBack);
		initialize();
		return validateHeaderButtonsareDisabled(btnBack);
	}

	public boolean verifyClearButtonIsDisabled() {
		pageLoaded(btnClear);
		initialize();
		return validateHeaderButtonsareDisabled(btnClear);
	}
	
	public boolean verifyContinueButtonIsDisabled() {
		pageLoaded(btnContinue);
		initialize();
		return validateHeaderButtonsareDisabled(btnContinue);
	}

	public boolean verifyConfirmAllButtonIsDisabled() {
		pageLoaded(btnConfirmAll);
		initialize();
		return validateHeaderButtonsareDisabled(btnConfirmAll);
	}

	public boolean verifyRecapButtonIsDisabled() {
		pageLoaded(btnRecap);
		initialize();
		return validateHeaderButtonsareDisabled(btnRecap);
	}

	public boolean verifyLogOutButtonIsDisabled() {
		pageLoaded(btnLogOut);
		initialize();
		return validateHeaderButtonsareDisabled(btnLogOut);
	}
	
	public boolean verifyExitButtonIsDisabled() {
		pageLoaded(btnExit);
		initialize();
		return validateHeaderButtonsareDisabled(btnExit);
	}

	/**
	 * @summary: Created the below methods to validate for the Header buttons enabled status
	 * @author: Praveen Namburi, @version: Created 3/23/2016
	 * @return
	 */
	public boolean verifyConfirmAllButtonIsEnabled() {
		pageLoaded(btnConfirmAll);
		initialize();
		return validateHeaderButtonsareEnabled(btnConfirmAll);
	}

	public boolean verifyRecapButtonIsEnabled() {
		pageLoaded(btnRecap);
		initialize();
		return validateHeaderButtonsareEnabled(btnRecap);
	}

	public boolean verifyLogOutButtonIsEnabled() {
		pageLoaded(btnLogOut);
		initialize();
		return validateHeaderButtonsareEnabled(btnLogOut);
	}
	
	public boolean verifyExitButtonIsEnabled() {
		pageLoaded(btnExit);
		initialize();
		return validateHeaderButtonsareEnabled(btnExit);
	}
	
   /* *//**
	 * @summary Method to validate buttons enabled or disabled status
	 * @version Created 03/23/2016
	 * @author Praveen Namburi
	 *//*
	public void validateLeftPanelEnabledOrDisabled() {
		List<WebElement> LeftPanel_Links=driver.findElements(By.tagName("input"));
		System.out.println("all links : "+LeftPanel_Links.size());
		for (WebElement webElement : LeftPanel_Links) {
			boolean isDisabled = false;
			//Validate link color to verify the disable status.
			if(!webElement.isEnabled()){
				isDisabled = true;
				TestReporter.assertFalse(isDisabled, webElement.getText().trim()+" buton is disabled");
				TestReporter.log("Left navigation panel is disabled");
			}
			else{
				TestReporter.assertTrue(isDisabled, webElement.getText().trim()+" button is enabled");
				TestReporter.log("Left navigation panel is enabled");
			}
		}
	}
*/
	
}

