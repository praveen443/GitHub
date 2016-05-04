package apps.lilo.switchLocation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;


/**
 * This class contains elements and element interactions for a given page of a web application
 * 
 * @author YourNameHere
 * @version 9/25/2015	 YourNameHere - original
 * @version 9/30/2015	 SecondContributorNameHere
 * 						-Brief summary of changes here
 * @version 10/2/2015	 ThirdContributorNameHere
 * 						-Brief summary of changes here
 */
public class SwitchLocationPage {
	//*******************
	//	Page Class Fields
	//*******************
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	
	//*********************
	//	Page Class Elements
	//*********************
	/*
	 *   FindBy elements go here  
	 */
	@FindBy(id = "switchLocationForm:routeId")
	private Button btnSwitchLocation;
	
	@FindBy(id = "switchLocationForm:locationType")
	private Listbox lstLocation;
	
	@FindBy(id = "switchLocationForm:tillType")
	private Listbox lstTillType;
    
	@FindBy(id = "switchLocationForm:bankIn")
	private Button btnBankIn;

    //*********************
    //** Build page area **
    //*********************
    /**
    * @summary Constructor to initialize the page
    * @author YourNameHere
    * @version 9/25/2015	 YourNameHere - original
    * @param  driver
    */
    public SwitchLocationPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

    /**
    * @summary Method to initialize all proxy elements for this page
    * @author YourNameHere
    * @version 9/25/2015	 YourNameHere - original
    * @return an instance of this page with the proxy elements initialized
    */
	public  SwitchLocationPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());	        
	}

    /**
    * @summary Method to determine if a page is loaded
    * @author YourNameHere
    * @version 9/25/2015	 YourNameHere - original
    * @return Boolean, true if the page is loaded, false otherwise
    */
    public boolean pageLoaded(){
    	return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnSwitchLocation); 
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
    
    public void switchLocations(String resort, String role){
    	String inputRole = "";
    	
    	if (role.indexOf("NOTILL") > 0) {
			inputRole = "No Till";
		} else if (role.indexOf("MANAGER") > 0) {
			inputRole = "Manager";
		}
    	
    	initialize();
    	pageLoaded(lstLocation);
    	lstLocation.syncVisible(driver);
    	lstLocation.select(resort);
    	Sleeper.sleep(1000);
    	initialize();
    	pageLoaded(lstTillType);
    	lstTillType.select(inputRole);
    	btnBankIn.jsClick(driver);
    }
}

