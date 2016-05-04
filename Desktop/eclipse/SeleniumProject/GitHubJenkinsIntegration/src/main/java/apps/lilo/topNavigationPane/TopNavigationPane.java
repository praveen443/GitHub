package apps.lilo.topNavigationPane;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Label;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

public class TopNavigationPane {
	//**********************************
	//*** Top Navigation Pane Fields ***
	//**********************************
	public final String NEWARRIVAL = "New Arrival";
	public final String INHOUSE = "In House";
	public final String CHECKEDOUT = "Checked Out";
	public final String FULLSEARCH = "Full Search";
	
	//************************************
	//*** Top Navigation Pane Elements ***
	//************************************
	//Create Label object for Campus Location (ie "Walt Disney World", Disneyland Resort")
    @FindBy(css = "td.sysMenuBarLHS:nth-child(1) > div:nth-child(1)")
    private Label lblCampus;
    
	//Create Label object for Bank In Resort Location
    @FindBy(css = "td.sysMenuBarLHS:nth-child(2) > div:nth-child(1)")
    private Label lblLocation;    

	//Create Label object for Login Role
    @FindBy(css = "td.sysMenuBarLHS:nth-child(3) > div:nth-child(1)")
    private Label lblRole;    
    
	//Create Button object for Lock Button
    @FindBy(id = "header1Form:j_id14")
    private Button btnLock;   

	//Create Button object for Home Button
    @FindBy(id = "header1Form:homeId")
    private Button btnHome;   

	//Create Button object for Logout Button
    @FindBy(id = "logoutLink")
    private Button btnLogout;   

	//Create Listbox object for Search Options
    @FindBy(id = "headerForm:searchTypeList")
    private Listbox lstSearchOptions;
    
	//Create Textbox object for Search Textbox
    @FindBy(id = "headerForm:suggest")
    private Textbox txtSearchField;     

    //Create Button object for Touch to Search
    @FindBy(id = "headerForm:searchReservationTapSearch")
    private Button btnTouchToSearch;    
    
    @FindBy(id = "headerForm:sugesstionResRegion:status.start")
    private Element icnLoading;
	
    //*********************
    //** Build page area **
    //*********************	
    private WebDriver driver;
    private Datatable datatable = new Datatable();

	public TopNavigationPane(WebDriver driver){
		this.driver = driver;
		ElementFactory.initElements(driver, this);
	    datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);	
	}
	
	public static TopNavigationPane initialize(WebDriver driver) {
	    return ElementFactory.initElements(driver, TopNavigationPane.class);	        
	}  
    public void EnterSearchScreen(){
    	lstSearchOptions.select("Full Search");
    	icnLoading.syncHidden(driver);
    }

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnLogout);
	}
	
	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}
	
	public TopNavigationPane initialize() {
		return ElementFactory.initElements(driver,
				this.getClass());
	}

	//****************************************
	//*** Top Navigation Pane Interactions ***
	//****************************************
    public String getBankedInCampus(){			
		return lblCampus.getText().toString();		
	}
	
    public  String getBankedInLocation(){			
		return lblLocation.getText().toString();		
	}
	
    public  String getLoginRole(){		
		return lblRole.getText().toString();		
	}	
	
    public  void clickLockButton(){
		btnLock.click();
	}
	
    public void clickHomeButton(){
    	initialize();
    	pageLoaded(btnHome);
		btnHome.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
    public  void clickLogOutButton(){
		btnLogout.click();
	}	
	
    public void selectSearchType(String text){
    	pageLoaded(lstSearchOptions);
		lstSearchOptions.select(text);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
    public  void setSearchText(String text){
		txtSearchField.set(text);
	}
}

