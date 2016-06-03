package apps.lilo.logout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.lilo.bankOut.BankInUsingAPI;
import apps.lilo.bankOut.BankOutUsingAPI;
import core.interfaces.Button;
import core.interfaces.Label;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

public class LiloLogout {
	@FindBy(id = "header1Form:logoutText")
	private Button btnLogout;
	
	@FindBy(id = "header1Form:userName")
	private Label lblUser;
	
    @FindBy(xpath = "//form[@id='header1Form']/table/tbody/tr/td[2]/div[1]")
	private  Label lblResort;
	
	
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	
	public LiloLogout(WebDriver driver){
	       this.driver = driver;
	       ElementFactory.initElements(driver, this);
	   	datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}
	
	public LiloLogout initialize() {
		return ElementFactory.initElements(driver,
				this.getClass());
	}

	public boolean pageLoaded(WebDriver driver){
	       return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnLogout);        
	}

	public void clickLogout(){
		
		pageLoaded(driver);
		btnLogout.highlight(driver);
		btnLogout.jsClick(driver);
		Sleeper.sleep(4000);
	}
	
	 /**
	    * 
	    * @summary Method to verify user name and resort on resort function page. 
	    * 	
	    * @version Created 09/18/2015
	    * @author  Brajesh Ahirwar
	    * @param   resort, user , environment and location
	    * @throws NA
	    * @return  if user and resort is correct returns true, if anyone is not matched return false
	 * @throws Exception 
	    * 
	    */
	
	
	public  boolean txtMatchUser(String resort, String user,String environment,String locationId) throws Exception{
		Boolean isUserMatched = false;
		Sleeper.sleep(2000);
		waitForElement(lblUser);
	    waitForElement(lblResort);
	    String actUserName = getUserName();
	    String expUser = user.toLowerCase().trim();
	    TestReporter.logStep("User Name  :::"+expUser);
	    String actResort = lblResort.getText().trim();
	    TestReporter.logStep("Resort Name  :::"+actResort);
	    //If  User Name is correct 
	    if(actUserName.equalsIgnoreCase(expUser))
	    {
	    	//If resort is correct
	    	if(actResort.equalsIgnoreCase(resort))
	    	{
	    	   return isUserMatched = true;
	    	}
	       else
	    	{
	    		//Bank Out 
	    		BankOutUsingAPI bankOut = new BankOutUsingAPI();
	    	    bankOut.bankOut(environment,user);
	    		LiloLogout logout = new LiloLogout(driver);
	    	    logout.initialize();
	    	    logout.pageLoaded(driver);
	    		logout.clickLogout();
	    	    return isUserMatched;
	          }
	        //if user Name is not correct BankIn and bank Out
	      }else
	      {
	    	
	    	  BankOutUsingAPI bankOut = new BankOutUsingAPI();
	    	  bankOut.bankOut(environment,actUserName);
	    	  Sleeper.sleep(500);
	    	  BankInUsingAPI bankIn = new BankInUsingAPI();
	  	      bankIn.bankIn(environment,user,locationId);
	  	      LiloLogout logout = new LiloLogout(driver);
    	      logout.initialize();
    	      logout.pageLoaded(driver);
    		  logout.clickLogout();
    		  return isUserMatched;
	    	  
	      }
	
	}
	
	public String getUserName()
	{
		
		String txtUser = lblUser.getText().trim();
	    String txtRemovespace [] = txtUser.split(": ");
		String txtUserName = txtRemovespace[1].trim();
		return txtUserName;
	}
	
	public void waitForElement(Label element)
	{
		 int counter =0;
	   	    do{
	   	    	
	   	    	element.syncVisible(driver, 60, false);
	   		    counter++;
				pageLoaded(driver);
				initialize();
			   
			}while(element.getText().equalsIgnoreCase("") && counter <10);
		
	}
	
}

