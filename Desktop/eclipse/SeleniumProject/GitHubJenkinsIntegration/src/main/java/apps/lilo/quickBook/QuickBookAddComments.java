package apps.lilo.quickBook;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.TestReporter;

/**
 * This class contains elements and element interactions for a given page of a
 * web application
 *
 * @summary Contains the page methods & objects to enter Guest REservation
 *          comments information
 * @version Created 10/28/2015
 * @author Marella Satish
 */

	/**
	 * @summary Constructor to initialize the page
	 * @author Marella Satish
	 * @version Created 10/28/2015
	 * @param driver
	 */
	 
	public class QuickBookAddComments {

		
		//*******************
		//	Page Class Fields
		//*******************
		
		private boolean FixedCommentStatus;
		private boolean CommentStatus;
		private boolean AllCommentStatus;
		private String Comment_BeforeAdd;	
		private String Comment_AfterAdd;  
	    private String FixedComment_BeforeAdd; 
		private String FixedComment_AfterAdd;  
		private String[] AllComments;
		private int CommentsCount;
		private Datatable datatable = new Datatable();
		
		//Declare a local WebDriver to be used by class method
		//*********************
		//	Page Class Elements
		//*********************
		    
	  //Create Button object for Add from Comment panel
	    @FindBy(id = "commentsDisplayFormId:addComments")
	    private Button btnAddComments;
	    
	  //Fixed Comment Select List element
	    @FindBy(id = "addCommentsForm:selFixedCommentTypeId")
	    private Listbox lstFixedComment;
	    
	  //Create Textbox object for Comment text area 
	    @FindBy(id = "addCommentsForm:commentTextArea")
	    private Textbox txtComment;	
	    
	  //Create Button object for Submit from Reservation Comments Window
	    @FindBy(id = "addCommentsForm:submitId2")
	    private Button btnSubmitComment;
	    
	  //Create Element object for comments in Comments panel
	  //  @FindBy(id = "commentsDisplayFormId:commentDisplay:0:j_id3581")
	   // private Element eleComment;
	    
	  //Create Element object for comments in Comments panel
	    @FindBy(id = "commentsDisplayFormId:commentsDisplayPanel")
	    private Element eleCommentsPanel;  
	    
	   
	    //*********************
	    //** Build page area **
	    //*********************
	    private WebDriver driver;
	    //DataProvider_ExcelSheet datapro = new DataProvider_ExcelSheet();

	    /**
	    * @summary Constructor to initialize the page
	    * @author Marella Satish
	    * @version Created 10/28/2015	 
	    * @param  driver
	    */
	    public QuickBookAddComments(WebDriver driver) {
			this.driver = driver;
			ElementFactory.initElements(driver, this);
			datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
		}

	    /**
	    * @summary Method to initialize all proxy elements for this page
	    * @author Marella Satish
	    * @version Created 10/28/2015
	    * @return an instance of this page with the proxy elements initialized
	    */
		public QuickBookAddComments initialize() {
			return ElementFactory.initElements(driver,
					this.getClass());
		}

	    /**
	    * @summary Method to determine if a page is loaded
	    * @author Marella Satish
	    * @version Created 10/28/2015
	    * @return Boolean, true if the page is loaded, false otherwise
	    */
	    public boolean pageLoaded(){
	    	return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnAddComments); 
	    }  
	    
	    public boolean pageLoaded(Element element){
			return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element); 
		}

	   
		//*************************************************
		//	Recommend AddGuest Page and Manage Tickets Class Methods
		//*************************************************
		
			/**
			    * 
			    * @summary Method to Add Comments Guest information
			    * @version Created 10/28/2015
			    * @author Marella Satish
			    * @param  driver
			    * @throws Exception if datatable scenarios or parameters are not found
			    * @return NA
			 * @throws InterruptedException 
			    * 
			    */
	        
		public void addComments(String scenario) {
		    	System.out.println();
				//Set Virtual table page 
//				datatable.setVirtualtablePage("QuickBookGuestInfo");
				datatable.setVirtualtablePage("GuestReservationComment");
			
				//SEt Virtual table Scenario
				datatable.setVirtualtableScenario(scenario);
				    
				//Validates if the Add Comments page loaded
				pageLoaded(btnAddComments);
				btnAddComments.jsClick(driver);
				
				
				pageLoaded(lstFixedComment);
			//	System.out.println("Sync List "+pageLoaded(lstFixedComment));
//				lstFixedComment.syncPresent(driver);
				
				
				 //Selecting the given comment from the list
			//	TestReporter.logStep("Value: " + datatable.getDataParameter("FixedComment"));
//				lstFixedComment.select(datatable.getDataParameter("FixedComment"));
				List<WebElement> options = lstFixedComment.findElements(By.xpath("option"));
				boolean found = false;
				for(WebElement option : options){
					Element e = new ElementImpl(option);
					if(!e.getText().isEmpty() && !e.getText().toLowerCase().contains("select")){
						lstFixedComment.select(e.getText());
						found = true;
						break;
					}
				}
				TestReporter.assertTrue(found, "No non-empty string option was found in the Fixed Comment listbox.");
		//		initialize();
				pageLoaded(txtComment);
				
				//Get the text Fixed comment dynamically
				TestReporter.logStep("Data in comment area : "+txtComment.getText());
				FixedComment_BeforeAdd = txtComment.getText(); 
				TestReporter.logStep("Fixed Comment before "+FixedComment_BeforeAdd);
				
				//Click on Submit button
				btnSubmitComment.jsClick(driver); 
				btnSubmitComment.syncHidden(driver);
				initialize();
				
				pageLoaded(eleCommentsPanel);
				FixedComment_AfterAdd =  eleCommentsPanel.getText(); 
				  
			//	TestReporter.logStep("Fixed Comment after "+FixedComment_AfterAdd);
				  
				//Validating the Fixed comment added in the Comment Panel
				FixedCommentStatus = FixedComment_AfterAdd.toLowerCase().contains(FixedComment_BeforeAdd.toLowerCase());
				Assert.assertTrue(FixedCommentStatus);	  
				  
				//Validates if the Add comments page loaded
				pageLoaded(btnAddComments);
				btnAddComments.jsClick(driver);
				pageLoaded(txtComment);
				
				//Retrieving the Comment from datatable
				Comment_BeforeAdd = datatable.getDataParameter("Comment");
				txtComment.safeSet(Comment_BeforeAdd);
				
				TestReporter.logStep("Comment before "+Comment_BeforeAdd);
				
				//Click on submit button
				btnSubmitComment.jsClick(driver); 
				btnSubmitComment.syncHidden(driver);
				 initialize();
				 
//				 pageLoaded(eleCommentsPanel);
		//		 Sleeper.sleep(3000);
				Comment_AfterAdd =  eleCommentsPanel.getText(); 
				TestReporter.logStep("Comment after "+Comment_AfterAdd);
				
		//		Sleeper.sleep(2000);
		//		
//				pageLoaded(btnAddComments);
				//Validating the comment added in the Comment Panel
				CommentStatus =Comment_AfterAdd.toLowerCase().contains(Comment_BeforeAdd.toLowerCase());
				Assert.assertTrue(CommentStatus);
				
				//Getting the total comment =s count from the comment panel
				CommentsCount = eleCommentsPanel.findElements(By.tagName("a")).size();
				AllComments = new String[CommentsCount]; 
				
				//Iterating the values and validating total comments in the comment panel 
				for(int iterator=0;iterator<=CommentsCount-3;iterator++){
					//Sleeper.sleep(3000);
					AllComments[iterator] = driver.findElement(By.xpath("//span[contains(@id,'commentsDisplayFormId:commentDisplay:"+iterator+"')]")).getText();
					
					AllCommentStatus = (AllComments[iterator].toLowerCase().contains(Comment_BeforeAdd.toLowerCase()) || AllComments[iterator].toLowerCase().contains(FixedComment_BeforeAdd.toLowerCase()));
					TestReporter.logStep(iterator+":"+AllComments[iterator] );
					Assert.assertTrue(AllCommentStatus);
				}
		  
		 }
	    


}

