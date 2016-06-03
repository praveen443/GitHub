package apps.dreams.Alert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import core.WindowHandler;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;


/**
* @summary Contains the methods & objects for the Dreams UI Alert window
* @version Created 11/04/2014
* @author Waightstill W. Avery
*/
public class AlertWindow {
	
	//***************************
	//*** Alert Window Fields ***
	//***************************
	String errorMessage = "";
	
	//*****************************
	//*** Alert Window Elements ***
	//*****************************
	private Datatable datatable = new Datatable();
	private  WebDriver driver;

	//Yes button
	@FindBy(name = "b_Yes")
	private  Button btnYes;
	
	//No button
	@FindBy(name = "b_No")
	private  Button btnNo;
	
	//Error message
	@FindBy(id = "errorStr")
	private  Element eleErrorMessage;
	
    //*********************
    //** Build page area **
    //*********************

    /**
    * 
    * @summary Constructor to initialize the window
    * @version Created 09/26/2014
    * @author Waightstill W Avery
    * @param  driver
    * @throws NA
    * @return NA
    * 
    */
	public AlertWindow(WebDriver driver){
		this.driver = driver;
		WindowHandler.swapToWindowWithTimeout(driver, "WARNING", 10);
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);
	}
	
	public boolean pageLoaded(WebDriver driver, Element element){
		WindowHandler.swapToWindowWithTimeout(driver, "WARNING", 10);
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);        
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, eleErrorMessage);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	public AlertWindow initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}


	//*********************************
	//*** Alert Window Interactions ***
	//*********************************
	
	/*
	 * Methods to capture and return the error message
	 */
	public void captureErrorMessage(){
		errorMessage = eleErrorMessage.getText();
	}
	public String getErrorMEssage(){
		captureErrorMessage();
		return errorMessage;
	}
	
	public void clickYes(){
		btnYes.jsClick(driver);
	}
	
	public void clickNo(){
		btnNo.click();
	}
}

