package apps.alc.existingReservation;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.alc.pleaseWait.PleaseWait;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Label;
import core.interfaces.Textbox;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.TextboxImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

/**
 * This class contains elements and element interactions for a given page of a
 * web application
 * 
 * @author Waightstill W Avery
 * @version 12/a15/2015 Waightstill W Avery - original
 */
public class ExistingReservationShoppingCartPage {
	// *******************
	// Page Class Fields
	// *******************
	// Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	// *********************
	// Page Class Elements
	// *********************
	@FindBy(id = "modifyshoppingCartForm:chkAllmodcb")
	private Checkbox chkTermsAndConditions;
	
	@FindBy(xpath = "/html/body/div[2]/div[7]/div[2]/table/tbody/tr[2]/td[1]/table/tbody/tr/td/table/tbody/tr[2]/td[6]/table/tbody/tr/td/form/div[2]/div/div/table/tbody/tr/td[2]/input")
	private Button btnModify;
	
	@FindBy(id = "modifyshoppingCartForm:j_id1819")
	private Button btnClickModify;
	
	@FindBy(id = "modifyshoppingCartForm:modifyshoppingCartRepeat:0:modifyvenueInfoCommandButton")
	private Button btnVenueInfo;
	
	@FindBy(id = "modifyshoppingCartForm:modifyshoppingCartRepeat:0:modifyofferInShopingCartOutputPanel")
	private Label lblofferDetails;
	
	@FindBy(id = "modifyshoppingCartForm:modifyshoppingCartButtonPanel_body")
	private WebElement eleBottomButtonPanel;
	
	@FindBy(id = "modifyshoppingCartForm:modifyshoppingCartRepeat:0:modifyrequestsCommandButton")
	private Button btnDefaultRequestsButton;
	
	@FindBy(id = "modifyshoppingCartForm:modifyshoppingCartRepeat:0:modXCRCommentShoppingCart")
	private Textbox txtInternalComment;
	
	//Elements for Requests sections 
	
	@FindBy(id = "modifyshoppingCartForm:modifyshoppingCartRepeat:0:modguestreq1:0")
	private Checkbox chkBoosterSeat;
	
	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
	 * @param driver
	 */
	public ExistingReservationShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public ExistingReservationShoppingCartPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, chkTermsAndConditions);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 12/15/2015 Waightstill W Avery - original
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
	public void acceptTermsAndBook(){
		acceptAllTerms();
		clickModify();
	}
	
	private void acceptAllTerms(){
		initialize();
		pageLoaded(chkTermsAndConditions);
		chkTermsAndConditions.jsToggle(driver);
		
	}
	
	private void clickModify(){
//		List<WebElement> modifies = driver.findElements(By.cssSelector("input[@value = 'Modify']"));
//		
//		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
//		for(WebElement modify : modifies){
//			Element element = new ElementImpl(modify);
//			if(element.syncVisible(driver, 1, false)){
//				element.highlight(driver);element.jsClick(driver);
//				PleaseWait.WaitForPleaseWait(driver);
//				break;
//			}
//		}
//		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
		initialize();
		pageLoaded(btnModify);
		btnModify.highlight(driver);
		btnModify.jsClick(driver);
		
	}
	
	/**
	 * @summary: Method to accept all terms and click on Modify button.
	 * @author: Praveen Namburi, @version: 1-18-2016.
	 */
	public void acceptAllTermsAndModify(){
		acceptAllTerms();
		clickModify();
	}
	
	
	public void clickVenueInformation(){
		initialize();
		pageLoaded(btnVenueInfo);
		btnVenueInfo.highlight(driver);
		btnVenueInfo.jsClick(driver);
	}
	
	
	/**
	 * @summary: Method to perform enter venue information details
	 * @author: Lalitha Bandha
	 * @version: 1/27/2016.
	 */
	
	private void enterVenueInformation(String venueInfoScenario){
		//Adult First and Last Names Section		
		datatable.setVirtualtablePage("ShoppingCart");
		datatable.setVirtualtableScenario(venueInfoScenario);
		String guestInformation = "";
		//Get number of adults from label
		//guestInformation = lblNumAdultsChildren.getText();		
		
		String locator = "modifyshoppingCartForm:modifyshoppingCartRepeat:0:modifyofferInShopingCartOutputPanel";
		// Grab a list of all possible matches. Loop for 10 seconds to allow the
		// elements to load.
		List<WebElement> elements;
		int loopCounter = 0;
		do{
			Sleeper.sleep(1000);
			loopCounter++;
			elements = driver.findElements(By.cssSelector("span[id^='"+locator+"']"));
		}while(elements.size() < 1 && loopCounter < 10);
		//Ensure that at least one element was found
		//TestReporter.assertTrue(elements.size() > 0, "No elements were found using the locator ["+locator+"].");
		//Iterate through all elements to find one that is displayed
		for(WebElement element : elements){
			Element e = new ElementImpl(element);
			// Determine if the element is visible. If so, use the remaining
			// xpath to isolate the party mix information
			if(e.syncVisible(driver, 1, false)){
				e.highlight(driver);
				//Use xpath to isolate the party mix information element and grab it's text
				guestInformation = e.findElement(By.xpath("table/tbody/tr[2]/td[1]/table/tbody/tr[2]/td[1]")).getText();
				System.out.println("Guest Information: "+guestInformation);
				break;
			}
		}		
		
		String adultsNum = guestInformation;
		int numberOfAdults = 0;		
		if(adultsNum.contains(" ")){
			adultsNum = adultsNum.substring(0, adultsNum.indexOf(" "));
			adultsNum = adultsNum.trim();
			if(!adultsNum.equals("0")){
				numberOfAdults = Integer.parseInt(adultsNum);
				String adultFirstNames[] = null;
				String adultLastNames[] = null;

				adultFirstNames = datatable.getDataParameter("Adult_First").split(";");
				adultLastNames = datatable.getDataParameter("Adult_Last").split(";");

				//test for null adult first and last names from VT
				if(adultFirstNames == null){
					TestReporter.logFailure("adultFirstNames cannot be null");
					throw new RuntimeException("null data array. [" + numberOfAdults +  "] adults expected."
							+ "Number of first names muse be = to " + numberOfAdults);				
				}	
				if (adultLastNames == null){
					TestReporter.logFailure("adultLastNames cannot be null");
					throw new RuntimeException("null data array. [" + numberOfAdults +  "] adults expected."
							+ "Number of last names muse be = to " + numberOfAdults);
				}						
				//Test if the number of first names != the number of last names in the String[] arrays.
				if(adultFirstNames.length != adultLastNames.length){			
					TestReporter.logFailure("Mismatch with data arrays. [" + adultFirstNames.length+ "] adult first names, [" + adultLastNames.length+ "] adult last names");
					throw new RuntimeException("Mismatch with data arrays. [" + adultFirstNames.length+ "] adult first names found, [" + adultLastNames.length+ "] adult last names found");						
				} 
				//pass and enter the names
				enterAdults(adultFirstNames, adultLastNames);
			}        	  
		}
		//Child first and last name section     
		String childsNum = guestInformation;
		int numberOfChildren = 0; 
		//Get number of children from the label
		if(childsNum.contains(" ")){
			childsNum = (childsNum.substring(9, 11));
			childsNum = childsNum.trim();
			if(!childsNum.equals("0")){
				numberOfChildren = Integer.parseInt(childsNum);   	        	
				String childFirstNames[] = null;
				String childLastNames[] = null;  

				childFirstNames = datatable.getDataParameter("Child_First").split(";");
				childLastNames = datatable.getDataParameter("Child_Last").split(";");

				//test for null child first and last names from VT
				if(childFirstNames == null){
					TestReporter.logFailure("childFirstNames cannot be null");
					throw new RuntimeException("null data array. [" + numberOfChildren +  "] children expected."
							+ "Number of first names muse be = to " + numberOfChildren);				
				}	
				if (childLastNames == null){
					TestReporter.logFailure("childLastNames cannot be null");
					throw new RuntimeException("null data array. [" + numberOfChildren +  "] children expected."
							+ "Number of last names muse be = to " + numberOfChildren);
				}
				//Test if the number of first names != the number of last names in the String[] arrays.
				if(childFirstNames.length != childLastNames.length){ 				
					TestReporter.logFailure("Mismatch with data arrays. [" + childFirstNames.length+ "] adult first names, [" + childLastNames.length+ "] adult last names");
					throw new RuntimeException("Mismatch with data arrays. [" + childFirstNames.length+ "] child first names found, [" + childLastNames.length+ "] child last names found");						
				}
				//pass and enter the names
				enterChildren(childFirstNames, childLastNames);
			}     	  
		}		
	}				

	private void enterAdults(String[] adultFirstNames, String[] adultLastNames){
		//Enters the adult first and last names
		//Setting name counter
		int adnameCounter = 0;
		//Setting the loop counter
		int adultloopCount = adultFirstNames.length;
		String adultFirstName = "";
		String adultLastName = "";
		try{
			do{
				adultFirstName = adultFirstNames[adnameCounter];
				adultLastName = adultLastNames[adnameCounter];
				String adultFirstid = "modifyshoppingCartForm:modifyshoppingCartRepeat:0:shoppingCartOfferAdultNameRepeat1:"
						+adnameCounter+":modshopCartNewFirstName1";
				String adultLastid = "modifyshoppingCartForm:modifyshoppingCartRepeat:0:shoppingCartOfferAdultNameRepeat1:"
						+adnameCounter+":modshopCartNewLasstName1";
				new TextboxImpl(driver.findElement(By.id(adultFirstid))).set(adultFirstName);
				new TextboxImpl(driver.findElement(By.id(adultLastid))).set(adultLastName);

				adnameCounter++;

			}while(adnameCounter < adultloopCount);		
		}catch(NoSuchElementException nse){
			throw new RuntimeException("Adult with index [" +adnameCounter+ "] was not found. Please ensure number of adults is expected to be [" + adultFirstNames.length + "]");
		}
	}

	private void enterChildren(String[] childFirstNames, String[] childLastNames){
		//Enters the adult first and last names
		//Setting name counter
		int chnameCounter = 0;
		//Setting the variables for the loop
		int childLoopCount = childFirstNames.length;
		String childFirstName = "";
		String childLastName = "";
		try {
			do{
				childFirstName = childFirstNames[chnameCounter];
				childLastName = childLastNames[chnameCounter];
				String childFirstid = "modifyshoppingCartForm:modifyshoppingCartRepeat:0:modshoppingCartOfferChildNameAndAgeRepeat1:"
						+ chnameCounter + ":modshopCartNewCFirstName1";
				String childLastid = "modifyshoppingCartForm:modifyshoppingCartRepeat:0:modshoppingCartOfferChildNameAndAgeRepeat1:"
						+chnameCounter + ":modshopCartNewCLastName1";
				new TextboxImpl(driver.findElement(By.id(childFirstid))).set(childFirstName);
				new TextboxImpl(driver.findElement(By.id(childLastid))).set(childLastName);

				chnameCounter++;   	

			}while(chnameCounter < childLoopCount);	 
		}catch(NoSuchElementException nse){
			throw new RuntimeException("Child with index [" +chnameCounter+ "] was not found. Please ensure number of children is expected to be [" + childFirstNames.length + "]");
		}
	}	
	
	/**
	 * @summary: Method to perform book reservation
	 * @author: Lalitha Bandha
	 * @version: 1/27/2016.
	 */
	
	public void bookReservation(String scenario){
		datatable.setVirtualtablePage("ShoppingCart");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded();

		String venueInformation = datatable.getDataParameter("VenueInformation");
		String acceptPolicy = datatable.getDataParameter("AcceptPolicy");

		if (venueInformation.toLowerCase().equalsIgnoreCase("true")) {
			clickVenueInformation();
			enterVenueInformation(scenario);

		}

		if (acceptPolicy.toLowerCase().equalsIgnoreCase("true")) {
			acceptTermsAndBook();
		}

		
	}
	/**
	 * @summary: Reports the offer details during a modification of a reservation
	 * @author: Original Author - UNKNOWN
	 * @version:2/1/16 - Updated - Needed to convert the string buffer toString();
	 *                   Updated Author - Stagliano, Dennis
	 */
	public void offerDetails(){
		//lblofferDetails
		initialize();
		List<WebElement> trCollection = lblofferDetails.findElements(By.tagName("tr"));
		
		StringBuffer sb = new StringBuffer(" ");
		
		for (WebElement trow : trCollection) {
			List<WebElement> tdcoll = trow.findElements(By.tagName("td"));		
			for (WebElement tdElement : tdcoll){
					sb.append(System.getProperty("line.separator"));
				sb.append(tdElement.getText() + " ");
			}	
		}	
		sb.toString();
		TestReporter.logStep(sb.toString());

	}
	
	public void clickButtonPanelByLabelName(String nameOnLabel){
		List<WebElement> buttonCollection = eleBottomButtonPanel.findElements(By.tagName("input"));
		int cIndex = 0;
		for (@SuppressWarnings("unused") WebElement name : buttonCollection) {
		 String labelText = buttonCollection.get(cIndex).getText();
		 System.out.println("text is " + labelText);
		}
	}
	
	/**
	 * @summary:Clicks the default Requests button for the guest
	 * @author: Original Author - Stagliano, Dennis
	 * @version:2/1/16 -
	 * @param: NA
	 * @return: NA                
	 */
	public void clickDefaultRequestsButton(){
		initialize();
		pageLoaded();
		btnDefaultRequestsButton.highlight(driver);
		btnDefaultRequestsButton.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);

	}	
	
	/**
	 * @summary : Calling methods to add/modify guest Requests in an existing reservation.
	 * NOTE: - If you prefer to clear all checkboxes first, call clearAllRequestCheckBoxes()
	 * @author : Stagliano, Dennis 
	 * @version: Created 2/1/2016
	 * @param : scenario.
	 */
	public void clearAllRequestCheckBoxes(){
		clearGuestRequestsCheckBoxes();
		clearGuestRequestServiceCheckBoxes();
		clearFoodAllergiesCheckBoxes();
	}
	/**
	 * @summary Method to clear add/modify guest Requests check boxes in an existing reservation.
	 * @author : Stagliano, Dennis 
	 * @version: Created 2/1/2016
	 */
	public void clearGuestRequestsCheckBoxes(){
		WebElement guestRequestchkBox = driver.findElement(By.id("modifyshoppingCartForm:modifyshoppingCartRepeat:0:modguestreq1"));
		boolean isGRChecked;	
		List<WebElement> grCheckBoxes = guestRequestchkBox.findElements(By.tagName("input"));
		for(WebElement grBox : grCheckBoxes){			
			isGRChecked = grBox.isSelected();
				if(isGRChecked){
					grBox.click();
				}
			}
	}
	/**
	 * @summary Method to clear add/modify service check boxes in an existing reservation.
	 * @author : Stagliano, Dennis 
	 * @version: Created 2/1/2016
	 */
	public void clearGuestRequestServiceCheckBoxes(){
		WebElement guestRequestSvchkBox = driver.findElement(By.id("modifyshoppingCartForm:modifyshoppingCartRepeat:0:modshoppingcartguestreqspecialneeds"));
		boolean isGRSChecked;
		List<WebElement> grsCheckBoxes = guestRequestSvchkBox.findElements(By.tagName("input"));
			for(WebElement grsbox : grsCheckBoxes){ 
				isGRSChecked = grsbox.isSelected();
				if(isGRSChecked){
					grsbox.click();
				}				
			}
	}
	/**
	 * @summary Method to clear add/modify Food Allergy check boxes in an existing reservation.
	 * @author : Stagliano, Dennis 
	 * @version: Created 2/1/2016
	 */
	public void clearFoodAllergiesCheckBoxes(){
		WebElement guestRequestFoodAllChkBox = driver.findElement(By.id("modifyshoppingCartForm:modifyshoppingCartRepeat:0:modguestreq2"));
		boolean isFAChecked;
		List<WebElement> faCheckBoxes = guestRequestFoodAllChkBox.findElements(By.tagName("input"));
			for(WebElement faBox : faCheckBoxes){ 
				isFAChecked = faBox.isSelected();
				if(isFAChecked){
					faBox.click();
				}				
			}
	}
		

	
}

