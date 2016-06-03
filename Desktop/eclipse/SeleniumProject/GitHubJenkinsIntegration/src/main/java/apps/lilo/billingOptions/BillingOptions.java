package apps.lilo.billingOptions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.interfaces.Element;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

/**
 * This class contains elements and element interactions for the Bell Service page in Lilo UI 
 * 
 * @author Venkatesh Athmakuri
 * @version 11/18/2015 Venkatesh Athmakuri - original
 */
public class BillingOptions {
	// *******************
	// Page Class Fields
	// *******************
	// Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private boolean terminalValue;
	private String TerminalID;
	private Datatable datatable = new Datatable();

	// *********************
	// Page Class Elements
	// *********************
	
	
	/*//Capturing Terminal ID
	@FindBy(xpath = "//td[contains(@id,'resPartyChargeAllocFrm:settlementMethodTable:0:j_id')]")
	private static Element textTerminalID;*/
	
	//Capturing Terminal ID
	@FindBy(id = "resPartyChargeAllocFrm:settlementMethodTable:tb")
	private static Element textTerminalID;
	
	
	
	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Venkatesh Athmakuri
	 * @version 11/18/2015 Venkatesh Athmakuri - original
	 * @param driver
	 */
	public BillingOptions(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Venkatesh Athmakuri
	 * @version 11/18/2015 Venkatesh Athmakuri - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public BillingOptions initialize(){
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Venkatesh Athmakuri
	 * @version 11/18/2015 Venkatesh Athmakuri - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, textTerminalID);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Venkatesh Athmakuri
	 * @version 11/18/2015 Venkatesh Athmakuri - original
	 * @param element
	 *            - Element to be used to determine if the page is loaded
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	// ********************
	// Page Class Methods
	// ********************
	
	/**
	    * 
	    * @summary Method to Validate Terminal ID
	    * @version Created 11/18/2014
	    * @author Venkatesh A
	    * @param  NA
	    * @throws NA
	    * @return NA
	    * 
	    */
	
	public boolean Verify_terminalID(){
		
		List<WebElement> tds = textTerminalID.findElements(By.tagName("td"));
		System.out.println( "Terminal Table total rows : "+tds.size());
		System.out.println("Terminal Id : "+tds.get(2).getText());
		
		
		// Terminal id 
		pageLoaded(textTerminalID);
		terminalValue = false;
		TerminalID = tds.get(2).getText();
		System.out.println("Terminal ID:"+TerminalID);
		
		if(!TerminalID.equalsIgnoreCase("0001")){
			return terminalValue = true;
		}
		return terminalValue;
	}
	
	
}

