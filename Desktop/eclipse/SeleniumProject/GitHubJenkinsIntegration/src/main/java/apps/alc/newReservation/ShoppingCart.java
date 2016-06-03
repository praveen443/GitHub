package apps.alc.newReservation;

import java.util.List;

import org.omg.SendingContext.RunTime;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import apps.alc.pleaseWait.PleaseWait;
import core.WebDriverSetup;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Label;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.TextboxImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

/**
 * 
 * @summary Contains the page methods & objects for the search guest page during
 *          the new reservation process
 * @version Created 10/02/2014
 * @author Waightstill W Avery
 */
public class ShoppingCart {
	// ****************************
	// *** Shopping Cart Fields ***
	// ****************************
	private Datatable datatable = new Datatable();
	private WebDriver driver;
	private String price;
	private String selectedOfferDetails;
	private String totalPrice;
	private String pattern = "none";
	
	// ************************************
	// *** Main Page Elements ***
	// ************************************
	@FindBy(id = "shoppingCartForm:book")
	private Button btnBook;

	@FindBy(id = "shoppingCartForm:continueShopping")
	private Button btnContinueShopping;

	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:policyAndSpielCommandButton")
	private Button btnPolicy;

	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:venueInfoCommandButton")
	private Button btnVenueInformation;

	@FindBy(xpath = "//*[@id=\"shoppingCartForm:shoppingCartRepeat:0:offerInShopingCartOutputPanel\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td")
	private Label lblPrice;

	@FindBy(xpath = ".//*[@id=\"shoppingCartForm:shoppingCartRepeat:0:offerInShopingCartOutputPanel\"]/table/tbody/tr[1]/td[1]")
	private Label lblSelectedOfferDetail;

	@FindBy(xpath = ".//*[@id='shoppingCartForm:shoppingCartTotalPriceOutputPanel']/table/tbody/tr[2]/td")
	private Element eleTotalPrice;

	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:PolicyDetailsTable")
	private Webtable tblPolicy;

	@FindBy(id = "shoppingCartForm:chkAllcb")
	private Checkbox chkCheckAll;

	//Empty cart button
	@FindBy(name = "shoppingCartForm:shopCartNewEmptyCart")
	private Button btnEmptyCart;

	//Requests button
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:requestsCommandButton")
	private Button btnRequests;

	//Internal Comment
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:XCRCommentShoppingCart")
	private Textbox txtInternalComment;

	//External Comment
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:XCRCommentShoppingCart1")
	private Textbox txtExternalComment;

	@FindBy(xpath = ".//*[@id='shoppingCartForm:shoppingCartRepeat:0:shoppingCartVenueInfoOutputPanel']/table")
	private Webtable venueInfo; 
	
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:shoppingCartVenueInfoOutputPanel")
	private Webtable venueOutput;

	//------ Guest Requests Info page Elements ------
	//Booster seat Check-box
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:guestreq1:0")
	private Checkbox chkBoosterSeat;

	//HighChair Check-box
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:guestreq1:1")
	private Checkbox chkHighChair;

	//Requesthighchair Check-box
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:guestreq1:2")
	private Checkbox chkRequesthighchair;

	//RequestTwoHighChairs Check-box
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:guestreq1:3")
	private Checkbox chkRequestTwoHighChairs;

	//------ Guest Requests Service Info page Elements ------
	//HearingLoss Check-box
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:shoppingcartguestreqspecialneeds:0")
	private Checkbox chkHearingLoss;

	//Limited mobility Check-box
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:shoppingcartguestreqspecialneeds:1")
	private Checkbox chkLimitedmobility;

	//Non-Apparent disability Check-box
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:shoppingcartguestreqspecialneeds:2")
	private Checkbox chkNonApparentdisability;

	//Oxygen tankuse Check-box
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:shoppingcartguestreqspecialneeds:3")
	private Checkbox chkOxygentankuse;

	//Service Animal Check-box
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:shoppingcartguestreqspecialneeds:4")
	private Checkbox chkServiceAnimal;

	//Special Dietary Request Check-box
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:shoppingcartguestreqspecialneeds:5")
	private Checkbox chkSpecialDietaryRequest;

	//Visual disability Check-box
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:shoppingcartguestreqspecialneeds:6")
	private Checkbox chkVisualdisability;

	//Wheelchair accessibility Check-box
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:shoppingcartguestreqspecialneeds:7")
	private Checkbox chkWheelchairaccessibility;

	//------ Food Alergies Info page Elements ------
	//Corn Check-box
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:guestreq2:0")
	private Checkbox chkCorn;

	//Egg Check-box
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:guestreq2:1")
	private Checkbox chkEgg;

	//Fish Check-box
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:guestreq2:2")
	private Checkbox chkFish;

	//Intolerance-Gluten Check-box
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:guestreq2:3")
	private Checkbox chkIntoleranceGluten;

	//Intolerance-PKU Check-box
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:guestreq2:4")
	private Checkbox chkIntolerancePKU;

	//MilkDairy Check-box
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:guestreq2:5")
	private Checkbox chkMilkDairy;

	//Peanut Check-box
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:guestreq2:6")
	private Checkbox chkPeanut;

	//Shellfish Check-box
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:guestreq2:7")
	private Checkbox chkShellfish;

	//Soy Check-box
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:guestreq2:8")
	private Checkbox chkSoy;

	//TreeNut Check-box
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:guestreq2:9")
	private Checkbox chkTreeNut;

	//Wheat Gluten Check-box
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:guestreq2:10")
	private Checkbox chkWheatGluten;

	//------ Comments and Other Allergies page Elements ------
	//Textbox XCR InternalComment
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:XCRCommentShoppingCart" )
	private Textbox txtXCRInternalComment;

	//Textbox XCR ExternalComment
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:XCRCommentShoppingCart1")
	private Textbox txtXCRExternalComment;
	

	//Textbox OtherAllergy
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:OtherAllergyShoppingCart1")
	private Textbox txtOtherAllergy;

	//------ Child first name,last name d input objects -----//
	//Text box first name - child
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:shoppingCartOfferChildNameAndAgeRepeat:0:shopCartNewCFirstName")
	private Textbox txtChild1Fname;

	//Text box last name - child
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:shoppingCartOfferChildNameAndAgeRepeat:0:shopCartNewCLastName")
	private Textbox txtChild1Lname;

	//Text box first name - child
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:shoppingCartOfferChildNameAndAgeRepeat:1:shopCartNewCFirstName")
	private Textbox txtChild2Fname;

	//Text box last name - child
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:shoppingCartOfferChildNameAndAgeRepeat:1:shopCartNewCLastName")
	private Textbox txtChild12Lname;
	
	//Label for number of children and adults
	@FindBy(xpath = ".//*[@id='shoppingCartForm:shoppingCartRepeat:0:offerItemSynopsis']/table/tbody/tr[1]/td")
	private Label lblNumAdultsChildren;
	
	//Add-On button
	@FindBy(id = "shoppingCartForm:shoppingCartRepeat:0:addonCommandButton")
	private Button btnAddOn;
	
	//---- PageElements for Add-on products page ----
	//Add-On Content Panel 
	@FindBy(id = "modifyAddOnsPanelContentDiv")
	private Element eleAddOnPanel;
	
	//Add-On save button. 
	@FindBy(id = "modifyAddOnsForm:modAddOnSave")
	private Button btnAddOnSave;
	
	//Listbox AddOnProductName
	@FindBy(xpath = "//*[@id='modifyAddOnsForm:modifyAddOnsOutPanel']/table/tbody/tr[3]/td[2]/select")
	private Listbox lstAddOnProductName;
	
	//Listbox AddOnProductMenu
	@FindBy(xpath = "//*[@id='modifyAddOnsForm:selectAddOnProductMenu']")
	private Listbox lstAddOnProductMenu;
	
	//Elements to determine the radio selected or screen - For Venue Information	
	@FindBy(id = "guestPreferencesForm:captureGuestPreferenceServiceSegment")
	private Element eleTableService;
	
	@FindBy(id = "guestPreferencesForm:tseFacility")
	private Element eleTableSearch;
	
	@FindBy(id = "guestPreferencesForm:captureGuestPreferencetool1")
	private Element eleDinnerShows;
	
	@FindBy(id = "guestPreferencesForm:guestPrfContActivityNameRC2")
	private Listbox lstChildrensActivities;
	
	@FindBy(id = "guestPreferencesForm:guestPrfContActivityNameRC2")
	private Listbox lstRecreation;

	//Textbox AddOnProductQuantity
	@FindBy(id = "modifyAddOnsForm:addOnProductQuantityInput")
	private Textbox txtaddOnProductQuantity;
	
	//Label AddOnUnitPrice
	@FindBy(id = "modifyAddOnsForm:revenueTypeList:1:addonProductList:1:modAddOnPolicies")
	private Label lblAddOnUnitPrice;
	
	//Element AddOnPolicies
	@FindBy(id = "modifyAddOnsForm:revenueTypeList:1:addonProductList:1:modAddOnPolicies")
	private Element eleAddOnPolicies;
	
	//Element get text after saving the add-on details 
	@FindBy(xpath = ".//*[@id='shoppingCartForm:shoppingCartRepeat:0:offerInShopingCartOutputPanel']/table/tbody/tr[2]/td[1]/table/tbody/tr[3]/td")
	private Listbox eleAddONProduct;
	
	//Shopping cart error
	@FindBy(id = "shoppingCartForm:errorMessageCart_body") private Element eleErrors;
	
	@FindBy(id = "modifyAddOnsForm:revenueTypeList:1:addonProductList:0:modAddOnPolicies")
	private Listbox lstPolicies;
	
	// *********************
	// ** Build page area **
	// *********************
	public ShoppingCart(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnBook);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	public ShoppingCart initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	// **************************************
	// *** Main CheckIn Page Interactions ***
	// **************************************
	public void captureDetails() {
		capturePrice();
		captureSelectedOfferDetails();
		captureTotalPrice();
	}

	private void capturePrice() {
		pageLoaded(lblPrice);
		lblPrice.highlight(driver);
		price = lblPrice.getText();
	}

	/**
	 * @summary Method that returns just the selected offer title
	 * @version Created 1/03/2016
	 * @author Stagliano, Dennis
	 * @param NA
	 * @returns offerTitle
	 */
	public String returnSelectedOfferTitle(){
		String offerTitle;
		lblSelectedOfferDetail.syncVisible(driver);
		offerTitle = lblSelectedOfferDetail.getText();
		return offerTitle;
	}

	/**
	 * @summary Method that returns just the selected offer total price
	 * @version Created 1/03/2016
	 * @author Stagliano, Dennis
	 * @param NA
	 * @returns offerTotalPrice
	 */
	public String returnSelectedOfferTotalPrice(){
		eleTotalPrice.syncVisible(driver);		
		totalPrice = eleTotalPrice.getText();
		return totalPrice;
	}

	public String getPrice() {
		return price;
	}

	private void captureSelectedOfferDetails() {
		lblSelectedOfferDetail.highlight(driver);
		selectedOfferDetails = lblSelectedOfferDetail.getText();
	}

	public String getSelectedOfferDetails() {
		return selectedOfferDetails;
	}

	private void captureTotalPrice() {
		totalPrice = eleTotalPrice.getText();
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void bookReservation(String scenario){		
		datatable.setVirtualtablePage("ShoppingCart");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded();

		String venueInformation = datatable.getDataParameter("VenueInformation");
		String book = datatable.getDataParameter("Book");
		String acceptPolicy = datatable.getDataParameter("AcceptPolicy");
		String addOn = datatable.getDataParameter("AddOn");
		
		if (venueInformation.toLowerCase().equalsIgnoreCase("true")) {
			clickVenueInformation();
			enterVenueInformation(scenario);
		}
		
		if (addOn.toLowerCase().equalsIgnoreCase("true")) {
			clickAddOnbutton();
			addOnProducts(scenario);
			validatingAddOnProduct(scenario);
		}
		
		if (acceptPolicy.toLowerCase().equalsIgnoreCase("true")) {
			if (chkCheckAll.syncVisible(driver, 4, false)) {
				acceptAllTerms();
			} else {
				acceptPolicy();
			}
		}

		if (book.toLowerCase().equalsIgnoreCase("true")) {
			clickBook();
		}
	}

	private void clickVenueInformation() {
		btnVenueInformation.click();
		PleaseWait.WaitForPleaseWait(driver);
		pageLoaded();
	}

	/**
	 * @summary Method that populates the Venue Information form that arrives
	 * 		    after clicking the Venue Information button.  This will loop through
	 * 			the Adult First and Last Names as well as the Child First and Last Names.
	 * 		
	 * @version Created 1/03/2016 / Updated 1/7 to account for an empty string[] returns 1
	 *          Updated 1/20 - Updated error handling
	 * 			Updated 1/24 - id's have a different pattern depending on the service. For example recreation has
	 * 			a different pattern for the text box id's than children activities.  The method gets all of 
	 * 			the inputs and detects what pattern the input id's will be to avoid an Element not Found exception.
	 * 			This method prepares and validates the data to make sure the data can be split as well as the length of first 
	 * 			names equals the length of the last names
	 * 			given for each adult and each child.
	 * @author Stagliano, Dennis
	 * @param Scenario in ShoppingCart data table passed from BookReservation method
	 * @throws RunTime Exception 
	 * @returns NA
	 */
	private void enterVenueInformation(String scenario){		
		datatable.setVirtualtablePage("ShoppingCart");
		datatable.setVirtualtableScenario(scenario);
		//set arrays to null
		String adultFirstNames[] = null;
		String adultLastNames[] = null;
		String childFirstNames[] = null;
		String childLastNames[] = null;
		int aFirstLen, aLastLen, cFirstLen;
		aFirstLen = 0;
		cFirstLen = 0;
		
		//get the pattern of the id because id's change based on what service selected
		pattern = getIdPattern();
		
		//Adult section
		//if data is not empty split the data strings
		if(!datatable.getDataParameter("Adult_First").isEmpty() && !datatable.getDataParameter("Adult_Last").isEmpty()){
			adultFirstNames = datatable.getDataParameter("Adult_First").split(";");	
			adultLastNames = datatable.getDataParameter("Adult_Last").split(";");
			aFirstLen = adultFirstNames.length;
			aLastLen = adultLastNames.length;
			
			//if length of first and last names don't match throw error
			if(aFirstLen != aLastLen){
	   			TestReporter.logFailure("Mismatch with data arrays. [" + aFirstLen + "] adult first names, [" + aLastLen + "] adult last names");
	   			throw new RuntimeException("Mismatch with data arrays. [" + aFirstLen + "] adult first names found, [" + aLastLen + "] adult last names found");					    
			}
			//if adult first and last name length match continue on.
			if(aFirstLen == aLastLen){		
   				enterAdults(adultFirstNames, adultLastNames, aFirstLen, pattern); 
			}			 				   
		}
		//Children section
		//if data is not empty split the data strings
		if(!datatable.getDataParameter("Child_First").isEmpty() && !datatable.getDataParameter("Child_Last").isEmpty()){
			childFirstNames = datatable.getDataParameter("Child_First").split(";");		
			childLastNames = datatable.getDataParameter("Child_Last").split(";");
			cFirstLen = childFirstNames.length;			

			//if length of first and last names don't match throw error
			if(childFirstNames.length != childLastNames.length){
	   			TestReporter.logFailure("Mismatch with data arrays. [" + childFirstNames.length+ "] child first names, [" + childLastNames.length+ "] child last names");
	   			throw new RuntimeException("Mismatch with data arrays. [" + childFirstNames.length+ "] child first names found, [" + childLastNames.length+ "] child last names found");					    
			}
			//if length of first and last names don't match throw error
			if(childFirstNames.length == childLastNames.length){		
   				enterChildren(childFirstNames, childLastNames, cFirstLen, pattern); 
			}
		}
	}

	/**
	 * @summary Method that populates the first and last names for each adult in the reservation, first and last names.
	 * 			The method uses a loop for the count of each adult and utilizes the pattern id that was passed.
	 * 		
	 * @version 1/22/16
	 * @author Stagliano, Dennis
	 * @param adultFirstNames[], adultLastNames[], aFirstLen (length of split to adultFirstNames[])  pattern (Type of id's to use
	 *        for the text box elements.
	 * @throws NA
	 * @returns NA
	 */
	
  private void enterAdults(String[] adultFirstNames, String[] adultLastNames, int aFirstLen, String pattern){  
	int a;	
	String afid, alid;
	   for(a = 0; a<aFirstLen; a++){
		   if(pattern == "A"){
			   //If pattern A was detected, run this for first names
			   afid = "shoppingCartForm:shoppingCartRepeat:0:shoppingCartOfferAdultNameRepeat1:"+a+":shopCartNewFirstName";
			   alid = "shoppingCartForm:shoppingCartRepeat:0:shoppingCartOfferAdultNameRepeat1:"+a+":shopCartNewLasstName";	
			   new TextboxImpl(driver.findElement(By.id(afid))).set(adultFirstNames[a]);
			   new TextboxImpl(driver.findElement(By.id(alid))).set(adultLastNames[a]);		   
		   		}
		   //If pattern B was detected, run this for last names
		   if(pattern == "B"){
			   afid = "shoppingCartForm:shoppingCartRepeat:0:shoppingCartOfferAdultNameRepeat:"+a+":shopCartNewFirstName";
			   alid = "shoppingCartForm:shoppingCartRepeat:0:shoppingCartOfferAdultNameRepeat:"+a+":shopCartNewLasstName";
			   new TextboxImpl(driver.findElement(By.id(afid))).set(adultFirstNames[a]);		
			   new TextboxImpl(driver.findElement(By.id(alid))).set(adultLastNames[a]);
		   } 
	   }	  
	}
  /**
	 * @summary Method that populates the first and last names for each child in the reservation, first and last names.
	 * 			The method uses a loop for the count of each child and utilizes the pattern id that was passed.
	 * 		
	 * @version 1/22/16
	 * @author Stagliano, Dennis
	 * @param childFirstNames[], childLastNames[], cFirstLen (length of split to childFirstNames[])  pattern (Type of id's to use
	 *        for the text box elements.
	 * @throws NA
	 * @returns NA
	 */
 private void enterChildren(String[] childFirstNames, String[] childLastNames, int cFirstLen, String pattern){
	initialize();	  
	int c;	
	String cfid, clid;
	   for(c = 0; c<cFirstLen; c++){
		   if(pattern == "A"){
			 //If pattern A was detected, run this for first names for children
			   cfid = "shoppingCartForm:shoppingCartRepeat:0:shoppingCartOfferChildNameAndAgeRepeat1:"+c+":shopCartNewCFirstName";
			   clid = "shoppingCartForm:shoppingCartRepeat:0:shoppingCartOfferChildNameAndAgeRepeat1:"+c+":shopCartNewCLastName";	
			   new TextboxImpl(driver.findElement(By.id(cfid))).set(childFirstNames[c]);
			   new TextboxImpl(driver.findElement(By.id(clid))).set(childLastNames[c]);
		   		}
		 //If pattern A was detected, run this for last names for children
		   if(pattern == "B"){
			   cfid = "shoppingCartForm:shoppingCartRepeat:0:shoppingCartOfferChildNameAndAgeRepeat:"+c+":shopCartNewCFirstName";
			   clid = "shoppingCartForm:shoppingCartRepeat:0:shoppingCartOfferChildNameAndAgeRepeat:"+c+":shopCartNewCLastName";
			   new TextboxImpl(driver.findElement(By.id(cfid))).set(childFirstNames[c]);
			   new TextboxImpl(driver.findElement(By.id(clid))).set(childLastNames[c]);
		   } 
	   }
 } 
 /**
	 * @summary Method that populates the proper id pattern to use for the first and last name text boxes. Patterns of id's
	 *          vary depending on the service.
	 *          Difference in patterns are ...  "Repeat1:0:" and "Repeat:0:"
	 * 		
	 * @version 1/22/16
	 * @author Stagliano, Dennis
	 * @param NA
	 * @throws NA
	 * @returns pattern label A or B
	 */
 	private String getIdPattern(){
 		 initialize();	
 		 WebElement venueOutput = driver.findElement(By.id("shoppingCartForm:shoppingCartRepeat:0:shoppingCartVenueInfoOutputPanel"));
 		 List<WebElement> inputCollection = venueOutput.findElements(By.tagName("input"));	 
 		    for (@SuppressWarnings("unused") WebElement inputid : inputCollection){			
 			  String idtext = inputCollection.get(0).getAttribute("id");
 			       if(idtext.contains("AdultNameRepeat1:0:shopCartNewFirstName") || 
 			    	 (idtext.contains("ChildNameAndAgeRepeat1:0:shopCartNewCFirstName"))){
 			    	   pattern = "A";
 			       }else{	    	   	     
 			    	   pattern = "B";		    	  
 			       }
 			      TestReporter.logStep("Element ID Pattern detected - Pattern [ " + pattern + " ]" + idtext);
 			       break;
 		    }
 		  return pattern;
 	}
 	
	private void clickBook() {
		//btnBook.jsClick(driver);
		btnBook.highlight(driver);
		btnBook.syncVisible(driver);
		btnBook.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
		
		checkForErrors();
	}
	
	private void checkForErrors(){
		boolean noErrors = true;
		String errorMessage = "";
		if(eleErrors.syncVisible(driver, 3, false)){
			noErrors = false;
			errorMessage = eleErrors.getText();
		}
		TestReporter.assertTrue(noErrors, "An error message was returned in the UI when trying to book.  The error message is ["+errorMessage+"].");
	}

	private void clickPolicyButton() {
		btnPolicy.click();
		PleaseWait.WaitForPleaseWait(driver);
		if (!tblPolicy.syncVisible(driver, 2, false)) {
			btnPolicy.jsClick(driver);
			PleaseWait.WaitForPleaseWait(driver);
		}
		Assert.assertEquals(pageLoaded(tblPolicy), true, "The policy page was not loaded as expected.");
	}

	private void acceptPolicy() {
		int loopCounter;
		int rowCount;
		ElementImpl objElement;
		String checkboxId = "shoppingCartForm:shoppingCartRepeat:0:PolicyDetailsTable:{index}:pricecb";
		WebElement we;
		// WebElement element;

		clickPolicyButton();
		rowCount = tblPolicy.getRowCount();

		for (loopCounter = 0; loopCounter < rowCount - 1; loopCounter++) {
			checkboxId = "shoppingCartForm:shoppingCartRepeat:0:PolicyDetailsTable:" + loopCounter + ":pricecb";
			we = driver.findElement(By.id(checkboxId));
			// element = driver.findElement(By.id("id_of_element"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", we);
			objElement = new ElementImpl(we);
			do {
				objElement.jsClick(driver);
				Sleeper.sleep(100);
			} while (!objElement.isSelected());
		}
	}

	private void acceptAllTerms() {
		initialize();
		pageLoaded(chkCheckAll);
		if(chkCheckAll.syncVisible(driver)){
		   //chkCheckAll.syncVisible(driver);
		   chkCheckAll.jsToggle(driver);
		}
	}

	public void clickEmptyShoopingCartButton(){
		btnEmptyCart.click();
	}

	public void clickRequests(){
		pageLoaded(btnRequests);
		btnRequests.jsClick(driver);
	}

	/**
	 * @summary Methods to perform book operation in shopping cart screen
	 * @author Lalitha Banda
	 * @version 12/5/2015 Lalitha Banda - original
	 * @param NA
	 * @return NA
	 */

	public void internalComment(){
		txtInternalComment.safeSet("Test Internal");
	}

	public void externalComment(){
		txtExternalComment.safeSet("Test External");
	}


	public void BookOffer(){
		TestReporter.logStep("Selected Offer Details :"+getSelectedOfferDetails());
		TestReporter.logStep("Selected Offer Details :"+getTotalPrice());
		TestReporter.logStep("Selected Offer Details :"+getPrice());

		clickRequests();
		internalComment();
		externalComment();
		acceptAllTerms();
		clickBook();
	}

	public void clickbtnRequests(){
		Sleeper.sleep(2000);
		pageLoaded(btnRequests);
		btnRequests.syncVisible(driver);
		btnRequests.click();  
	}

	/**
	 * @summary : Calling methods in method to add Requests in the Shopping cart page.
	 * @author : Praveen Namburi, @version: Created 1/5/2016, @param : scenario.
	 */
	public void addRequests(String scenario){
		SelectGuestRequests(scenario);
		SelectGuestRequestsService(scenario);
		SelectFoodAlergies(scenario);
		enterCommentsAndOtherAllergies(scenario);
		setAllTermsAndConditions(scenario);
	}

	/**
	 * @summary : Method to select the Guest Requests check-boxes.
	 * @author : Praveen Namburi, @version: Created 1/5/2016, @param : scenario.
	 */
	public void SelectGuestRequests(String scenario){
		datatable.setVirtualtablePage("AddRequestsInShoppingCart");
		datatable.setVirtualtableScenario(scenario);

		pageLoaded(chkBoosterSeat);
		//Select the check-boxes based on the condition from datatable.
		if(datatable.getDataParameter("BoosterSeat").equalsIgnoreCase("True")){
			chkBoosterSeat.highlight(driver);
			chkBoosterSeat.jsToggle(driver);
		}
		if(datatable.getDataParameter("HighChair").equalsIgnoreCase("True")){
			chkHighChair.highlight(driver);
			chkHighChair.jsToggle(driver);
		}
		if(datatable.getDataParameter("Requesthighchair").equalsIgnoreCase("True")){
			chkRequesthighchair.highlight(driver);
			chkRequesthighchair.jsToggle(driver);
		}
		if(datatable.getDataParameter("RequestTwoHighChairs").equalsIgnoreCase("True")){
			chkRequestTwoHighChairs.highlight(driver);
			chkRequestTwoHighChairs.jsToggle(driver);
		}

	}

	/**
	 * @summary : Method to select the Guest Requests Service check-boxes.
	 * @author : Praveen Namburi, @version: Created 1/5/2016, @param : scenario.
	 */
	public void SelectGuestRequestsService(String scenario){
		datatable.setVirtualtablePage("AddRequestsInShoppingCart");
		datatable.setVirtualtableScenario(scenario);

		pageLoaded(chkHearingLoss);
		//Select the check-boxes based on the condition from datatable.
		if(datatable.getDataParameter("HearingLoss").equalsIgnoreCase("True")){
			chkHearingLoss.highlight(driver);
			chkHearingLoss.jsToggle(driver);
		}
		if(datatable.getDataParameter("Limitedmobility").equalsIgnoreCase("True")){
			chkLimitedmobility.highlight(driver);
			chkLimitedmobility.jsToggle(driver);
		}
		if(datatable.getDataParameter("NonApparentdisability").equalsIgnoreCase("True")){
			chkNonApparentdisability.highlight(driver);
			chkNonApparentdisability.jsToggle(driver);
		}
		if(datatable.getDataParameter("Oxygentankuse").equalsIgnoreCase("True")){
			chkOxygentankuse.highlight(driver);
			chkOxygentankuse.jsToggle(driver);
		}
		if(datatable.getDataParameter("ServiceAnimal").equalsIgnoreCase("True")){
			chkServiceAnimal.highlight(driver);
			chkServiceAnimal.jsToggle(driver);
		}
		if(datatable.getDataParameter("SpecialDietaryRequest").equalsIgnoreCase("True")){
			chkSpecialDietaryRequest.highlight(driver);
			chkSpecialDietaryRequest.jsToggle(driver);
		}
		if(datatable.getDataParameter("Visualdisability").equalsIgnoreCase("True")){
			chkVisualdisability.highlight(driver);
			chkVisualdisability.jsToggle(driver);
		}
		if(datatable.getDataParameter("Wheelchairaccessibility").equalsIgnoreCase("True")){
			chkWheelchairaccessibility.highlight(driver);
			chkWheelchairaccessibility.jsToggle(driver);
		}

	}

	/**
	 * @summary : Method to select the Food Alergies check-boxes.
	 * @author : Praveen Namburi, @version: Created 1/5/2016, @param : scenario.
	 */
	public void SelectFoodAlergies(String scenario){
		datatable.setVirtualtablePage("AddRequestsInShoppingCart");
		datatable.setVirtualtableScenario(scenario);

		pageLoaded(chkCorn);
		//Select the Food Alergies check-boxes based on the condition from datatable.
		if(datatable.getDataParameter("Corn").equalsIgnoreCase("True")){
			chkCorn.highlight(driver);
			chkCorn.jsToggle(driver);
		}
		if(datatable.getDataParameter("Egg").equalsIgnoreCase("True")){
			chkEgg.highlight(driver);
			chkEgg.jsToggle(driver);
		}
		if(datatable.getDataParameter("Fish").equalsIgnoreCase("True")){
			chkFish.highlight(driver);
			chkFish.jsToggle(driver);
		}
		if(datatable.getDataParameter("IntoleranceGluten").equalsIgnoreCase("True")){
			chkIntoleranceGluten.highlight(driver);
			chkIntoleranceGluten.jsToggle(driver);
		}
		if(datatable.getDataParameter("IntolerancePKU").equalsIgnoreCase("True")){
			chkIntolerancePKU.highlight(driver);
			chkIntolerancePKU.jsToggle(driver);
		}
		if(datatable.getDataParameter("MilkDairy").equalsIgnoreCase("True")){
			chkMilkDairy.highlight(driver);
			chkMilkDairy.jsToggle(driver);
		}
		if(datatable.getDataParameter("Peanut").equalsIgnoreCase("True")){
			chkPeanut.highlight(driver);
			chkPeanut.jsToggle(driver);
		}
		if(datatable.getDataParameter("Shellfish").equalsIgnoreCase("True")){
			chkShellfish.highlight(driver);
			chkShellfish.jsToggle(driver);
		}
		if(datatable.getDataParameter("Soy").equalsIgnoreCase("True")){
			chkSoy.highlight(driver);
			chkSoy.jsToggle(driver);
		}
		if(datatable.getDataParameter("TreeNut").equalsIgnoreCase("True")){
			chkTreeNut.highlight(driver);
			chkTreeNut.jsToggle(driver);
		}
		if(datatable.getDataParameter("WheatGluten").equalsIgnoreCase("True")){
			chkWheatGluten.highlight(driver);
			chkWheatGluten.jsToggle(driver);
		}

	}

	/**
	 * @summary : Method to select AllTerms And Conditions check-box.
	 * @author : Praveen Namburi, @version: Created 1/5/2016, @param : scenario.
	 */
	public void setAllTermsAndConditions(String scenario){
		datatable.setVirtualtablePage("AddRequestsInShoppingCart");
		datatable.setVirtualtableScenario(scenario);

		initialize();
		//Select AllTerms and Conditions check-box based on the condition from datatable.
		if(datatable.getDataParameter("AllTermsConditions").equalsIgnoreCase("True")){
			pageLoaded(chkCheckAll);
			chkCheckAll.highlight(driver);
			chkCheckAll.jsToggle(driver);
		}
	}

	/**
	 * @summary : Method to enter the Comments and Other Allergies Info.
	 * @author : Praveen Namburi, @version: Created 1/5/2016, @param : scenario.
	 */
	public void enterCommentsAndOtherAllergies(String scenario){
		datatable.setVirtualtablePage("AddRequestsInShoppingCart");
		datatable.setVirtualtableScenario(scenario);

		String XCRInternalComment = datatable.getDataParameter("XCRInternalComment");
		String XCRExternalComment = datatable.getDataParameter("XCRExternalComment");
		String OtherAllergy = datatable.getDataParameter("OtherAllergies");

		pageLoaded(txtXCRInternalComment);
		txtXCRInternalComment.safeSet(XCRInternalComment);
		pageLoaded(txtXCRExternalComment);
		txtXCRExternalComment.safeSet(XCRExternalComment);
		pageLoaded(txtOtherAllergy);
		txtOtherAllergy.safeSet(OtherAllergy);
	}

	/**
	 * @summary: Calling methods in method to capture total price
	 * 			  and add special requests and click on Book.
	 * @author : Praveen Namburi, @version: Created 1/5/2016, @param : scenario.
	 */
	public void addRequestsAndBook(String scenario){
		addRequests(scenario);
		clickBook();
	}

	/**
	 * 
	 * @summary Method to send an F5
	 * @version Created 1/04/2015
	 * @author Stagliano, Dennis
	 */
	public void f5Refresh(){
		Actions actionObject = new Actions(driver);
		actionObject.sendKeys(Keys.F5);
		actionObject.perform();
	}
	
	public void acceptAllTermsAndBook(){
		acceptAllTerms();
		clickBook();
	}
	
	public void clickBookButton() {
		initialize();
		btnBook.highlight(driver);
		btnBook.click();
		PleaseWait.WaitForPleaseWait(driver);
	}
	
	/**
	 * @summary: Method to click on AddON button.
	 * @version: Created 1-25-2016, @author: Praveen namburi.
	 */
	public void clickAddOnbutton() {
		pageLoaded(btnAddOn);
		btnAddOn.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}
	
	/**
	 * @summary: Method to add the add-on products and validate it.
	 * @version: Created 1-25-2016 , @author: Praveen Namburi - Original.
	 * @param scenario
	 */
	public void addTheAddOnsAndValidateIt(String scenario){
		datatable.setVirtualtablePage("ShoppingCart");	
		datatable.setVirtualtableScenario(scenario);
		
		initialize();
		clickAddOnbutton();
		addOnProducts(scenario);
		f5Refresh();
		//validatingAddOnProduct(scenario);
	}
	
	/**
	 * @summary Method to AddOn Products - ShoppingCart
	 * @author  Praveen Namburi
	 * @version 1/25/2016 Praveen Namburi - original
	 * @param Scenario
	 * @return NA
	 */
	public void addOnProducts(String scenario){
		datatable.setVirtualtablePage("ShoppingCart");	
		datatable.setVirtualtableScenario(scenario);
		
		String addOnProduct = datatable.getDataParameter("AddOnProduct");
		String addOnMenu = datatable.getDataParameter("AddOnMenu");
		
		pageLoaded(eleAddOnPanel);
		eleAddOnPanel.syncVisible(driver);
			
		pageLoaded(lstAddOnProductName);
		lstAddOnProductName.select(addOnProduct);
		int counter = 0;
		boolean isMultipleOptions = false;
		do{
			Sleeper.sleep(1000);
			counter++;
			try{
				isMultipleOptions = (lstAddOnProductMenu.getOptions().size() > 1);
			}catch(Exception sere){
				pageLoaded(lstAddOnProductMenu);
			}
		}while(!isMultipleOptions && counter < WebDriverSetup.getDefaultTestTimeout());
		TestReporter.assertTrue(counter < WebDriverSetup.getDefaultTestTimeout(), "No options were loaded for the second product listbox.");
		
		// Try searching for the exact addOn text. If not found, determine if an
		// option exists that contains the addOn text.
		try{
			lstAddOnProductMenu.select(addOnMenu);
		}catch(NoSuchElementException nsee){
			lstAddOnProductMenu.selectOptionTextContains(addOnMenu);
		}
		//Allow some time for the policies table to load
		Sleeper.sleep(3000);
		new PageLoaded().isDomComplete(driver);
		
		List<WebElement> policies = null;
		try{
			policies = driver.findElements(By.cssSelector("div[id$='AddOnPolicies]"));
		}catch(NoSuchElementException nsee){
			//policies = driver.findElements(By.cssSelector("TEXTAREA[id$='AddOnPolicies]"));
			policies  = driver.findElements(By.xpath("//*[contains(@id,'modifyAddOnsForm:revenueTypeList:1:addonProductList')]"));
		}
		Sleeper.sleep(3000);
		TestReporter.assertGreaterThanZero(policies.size());
		
		for(WebElement policy : policies){
			Element p = new ElementImpl(policy);
			if(p.syncVisible(driver, 1, false)) eleAddOnPolicies = p;
		}
		
		pageLoaded(eleAddOnPolicies);
		
		eleAddOnPolicies.syncVisible(driver);
		pageLoaded(txtaddOnProductQuantity);
		TestReporter.log(" Product Quantity : "+txtaddOnProductQuantity.getText());
		pageLoaded(lblAddOnUnitPrice);
		TestReporter.log(" Unit Price : "+lblAddOnUnitPrice.getText());
		
		//click on Save button.
		if(btnAddOnSave.syncVisible(driver)){
			btnAddOnSave.highlight(driver);
			btnAddOnSave.jsClick(driver);
		}
	}
	
	/**
	 * @summary Method to validate AddOn Products - ShoppingCart
	 * @author Praveen Namburi
	 * @version 1/25/2016 Praveen Namburi - original
	 * @param Scenario
	 * @return NA
	 */
	public void validatingAddOnProduct(String scenario){
		datatable.setVirtualtablePage("ShoppingCart");	
		datatable.setVirtualtableScenario(scenario);
		String addOnMenu = datatable.getDataParameter("AddOnMenu");
		
		List<WebElement> addons = driver.findElements(By.cssSelector("input[id*='addOnProductListRepeat']"));
		boolean valueFound = false;
		for(WebElement addon : addons){
			if(addon.findElement(By.xpath("..")).getText().contains(addOnMenu)) valueFound = true;
		}
		TestReporter.assertTrue(valueFound, "The AddOn Product ["+addOnMenu+"] was not validated Successfully.");
	}
}

