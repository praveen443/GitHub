package apps.alc.topNavigationBar;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.alc.newReservation.SearchGuestPage;
import apps.alc.pleaseWait.PleaseWait;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Link;
import core.interfaces.impl.ButtonImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

/**
 * This class contains elements and element interactions for the top pane of the ALC application
 * 
 * @author Waightstill W Avery
 * @version 12/09/2015	 Waightstill W Avery - original
 */
public class TopNavigationBar {
	//*******************
	//	Page Class Fields
	//*******************
	//Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	//*********************
	//	Page Class Elements
	//*********************
	@FindBy(id = "logoutForm:closeLink")
	private Link lnkLogout;

	@FindBy(id = "logoutForm1:headerPanelID")
	private Element eleLogoutFormHeaderPanel;

	//go click on Done 
	@FindBy(id = "logoutForm1:j_id18")
	private Button btnDone;
	
	@FindBy(id = "logoutForm1:quickBookCheckBoxId")
	private Checkbox chkQuickBook;
	
    //*********************
    //** Build page area **
    //*********************
    /**
    * @summary Constructor to initialize the page
    * @author YourNameHere
    * @version 9/25/2015	 YourNameHere - original
    * @param  driver
    */
    public TopNavigationBar(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author YourNameHere
	 * @version 9/25/2015	 YourNameHere - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public  TopNavigationBar initialize() {
		return ElementFactory.initElements(driver, this.getClass());	        
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author YourNameHere
	 * @version 9/25/2015	 YourNameHere - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded(){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, lnkLogout); 
	}  

	/**
	 * @summary Method to determine if a page is loaded
	 * @author YourNameHere
	 * @version 9/25/2015	 YourNameHere - original
	 * @param element - Element to be used to determine if the page is loaded
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded(Element element){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element); 
	}  

	//********************
	//	Page Class Methods
	//********************

	public void clickLogout(){
		initialize();
		pageLoaded(lnkLogout);
		lnkLogout.jsClick(driver);
	}

	public void clickDone(){
		List<WebElement> dones = driver.findElements(By.cssSelector("input[value='Done']"));
		for(WebElement weDone : dones){
			Button d = new ButtonImpl(weDone);
			if(d.syncVisible(driver, 1, false)) {
				btnDone = d;
				break;
			}
		}
		pageLoaded(btnDone);
		btnDone.jsClick(driver);
		// PleaseWait.WaitForPleaseWait(driver);
		SearchGuestPage sgp = new SearchGuestPage(driver);
		sgp.pageLoaded();
	}

    
    
    /**
     * @summary : Method to check the QuickBook checkbox.
     * @author  : Praveen Namburi.
     * @version : Created 01/04/2016
     */
    public void clickQuickBookCheckBox(){
    	pageLoaded(chkQuickBook);
    	chkQuickBook.syncVisible(driver);
    	chkQuickBook.highlight(driver);
    	chkQuickBook.jsToggle(driver);
    	PleaseWait.WaitForPleaseWait(driver);
    }
}

