package apps.lilo.leftNavigationPane;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import core.interfaces.Element;
import core.interfaces.Link;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

public class LiloLeftNavigationPane {
	//
	// Page Fields
	//
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	
	//*************************************
	//*** Left Navigation Pane Elements ***
	//*************************************
	//Create Link object for Administration
    @FindBy(id="menuForm:administrationLinkId")
    private Link lnkAdministration;
	
    //Create Link object for Reports
    @FindBy(id="menuForm:reports1")
    private Link lnkReports;
    
	//Create Link object for Housekeeping
    @FindBy(id="menuForm:hkLink")
    private Link lnkHousekeeping;
        
	//Create Link object for Resort Functions
    @FindBy(id="menuForm:resortFunctionsLinkId1")
    private Link lnkResortFunctions;
	    
	//Create Link object for Group Functions
	@FindBy(linkText = "Group Functions")
	private Link lnkGroupFunctions;  
	
    //*********************
    //** Build page area **
    //*********************

	public LiloLeftNavigationPane(WebDriver driver){
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}
	
    public boolean pageLoaded(WebDriver driver){
    	return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, lnkAdministration);        
    }
	
    public boolean pageLoaded(WebDriver driver, Element element){
    	return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, lnkAdministration);        
    }
    
    public boolean pageLoaded(){
    	return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, lnkAdministration);        
    }
    
	public LiloLeftNavigationPane initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	//*****************************************
	//*** Left Navigation Pane Interactions ***
	//*****************************************
	public void enterAdministrationPage(){
		pageLoaded(driver, lnkAdministration);
		lnkAdministration.jsClick(driver);
	}
	
	public void enterReportsPage(){
		pageLoaded(driver, lnkReports);
		lnkReports.jsClick(driver);
	}
	
	public void enterHousekeepingPage(){
		pageLoaded(driver, lnkHousekeeping);
		lnkHousekeeping.jsClick(driver);
	}
	
	public void enterResortFunctionsPage(){
		pageLoaded(driver, lnkResortFunctions);
		lnkResortFunctions.jsClick(driver);
	}
	
	public void enterGroupFunctionsPage(){
		initialize();
		pageLoaded(lnkGroupFunctions);
		lnkGroupFunctions.syncVisible(driver);
		lnkGroupFunctions.syncEnabled(driver);
		lnkGroupFunctions.highlight(driver);
		lnkGroupFunctions.jsClick(driver);
		Sleeper.sleep(2000);
		if(driver.getTitle().contains("HTTP 403 Forbidden")){
			TestReporter.assertTrue(false, "An error occurred clicking the 'Group Functions' link. Page title: ["+driver.getTitle()+"].");
		}
	}
}

