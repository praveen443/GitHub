package apps.alc.existingReservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import core.interfaces.Element;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

/**
 * This class contains elements and element interactions for a given page of a web application
 * 
 * @author Waightstill W Avery
 * @version 12/15/2015	 Waightstill W Avery - original
 */
public class ExistingReservationTabNavigation {
	//*******************
	//	Page Class Fields
	//*******************
	//Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	
	//*********************
	//	Page Class Elements
	//*********************
	@FindBy(id = "SearchReservationByGuestTab_lbl")
	private Element eleGuestSearchTab;
	
	@FindBy(id = "SearchReservationByVenueTab_lbl")
	private Element eleVenueSearchTab;
	
	@FindBy(id = "SearchReservationByPaymentTab_lbl")
	private Element elePaymentSearchTab;
    

    //*********************
    //** Build page area **
    //*********************
    /**
    * @summary Constructor to initialize the page
 * @author Waightstill W Avery
 * @version 12/15/2015	 Waightstill W Avery - original
    * @param  driver
    */
    public ExistingReservationTabNavigation(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}

    /**
    * @summary Method to initialize all proxy elements for this page
 * @author Waightstill W Avery
 * @version 12/15/2015	 Waightstill W Avery - original
    * @return an instance of this page with the proxy elements initialized
    */
	public ExistingReservationTabNavigation initialize() {
		return ElementFactory.initElements(driver, this.getClass());	        
	}

    /**
    * @summary Method to determine if a page is loaded
 * @author Waightstill W Avery
 * @version 12/15/2015	 Waightstill W Avery - original
    * @return Boolean, true if the page is loaded, false otherwise
    */
    public boolean pageLoaded(){
    	return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, eleGuestSearchTab); 
    }  

    /**
    * @summary Method to determine if a page is loaded
 * @author Waightstill W Avery
 * @version 12/15/2015	 Waightstill W Avery - original
	* @param element - Element to be used to determine if the page is loaded
    * @return Boolean, true if the page is loaded, false otherwise
    */
    public boolean pageLoaded(Element element){
    	return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element); 
    }  

	//********************
	//	Page Class Methods
	//********************

    public void clickGuestsearchTab(){
    	initialize();
    	pageLoaded(eleGuestSearchTab);
    	eleGuestSearchTab.jsClick(driver);
    }

    public void clickVenuesearchTab(){
    	initialize();
    	pageLoaded(eleVenueSearchTab);
    	eleVenueSearchTab.jsClick(driver);
    }

    public void clickPaymentSearchTab(){
    	initialize();
    	pageLoaded(elePaymentSearchTab);
    	elePaymentSearchTab.jsClick(driver);
    }
}

