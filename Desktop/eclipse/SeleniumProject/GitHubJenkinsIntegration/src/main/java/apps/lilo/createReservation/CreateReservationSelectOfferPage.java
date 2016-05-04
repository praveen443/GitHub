package apps.lilo.createReservation;

import java.util.List;

import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.WebDriverSetup;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Webtable;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;
/**
 * 
 * @summary Contains the page methods & objects for the select offer page during
 *          the create reservation process
 * @version Created 9/15/2014
 * @author Jessica Marshall
 */
public class CreateReservationSelectOfferPage {
	// ************************************
	// *** Create Reservation Select Offer Page Elements ***
	// ************************************
	private Datatable datatable = new Datatable();
	private WebDriver driver;
	
	// ************************************
	// *** Create Reservation Select Offer Page Elements ***
	// ************************************

	// back to discover
	@FindBy(id = "createResSearchResultsForm:butDiscover")
	private Button btnDiscover;

	// select offers
	@FindBy(xpath = ".//*[@id='createResSearchResultsForm:selectOffersButton']")
	private Button btnSelect;

	// cancel
	@FindBy(id = "createResSearchResultsForm:cancelButton")
	private Button btnCancel;

	// offers table
	@FindBy(id = "createResSearchResultsForm:searchOfferResultsDataTable")
	private Webtable tblOffers;

	//Create Button object for Clear button
	@FindBy(id = "roomPartyMixDistributionModalPanelContentDiv")
	private Element elePartyMix;

	//create button object for Submit button
	@FindBy(xpath = ".//*[@id='partyMixBodyForm:submitButton']")
	private Button btnSubmit;

	//Create Button object for Clear button
	@FindBy(id = "createResModalPanelContentDiv")
	private Element eleOffers;

	//First Offer Hold button
	@FindBy(id = "createResSearchResultsForm:searchOfferResultsDataTable:0:holdOffer")
	private Button btnFirstOfferHold;

	//Search results for drop down element
	@FindBy(id = "createResSearchResultsForm:searchOfferResultsDataTable:0:selectedNoOfRoomsToFreeze")
	private Element ddnoOfRoomsToFreeze;

	//Error pop up header text
	@FindBy(id = "PMSRErrorModalPanelHeader")
	private Element eleErrorPopupHeader;

	//First Offer Hold button
	@FindBy(xpath = ".//*[@id='errorForm']/table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td/table/tbody/tr/td[2]/div/ul/li")
	private Element eleErrorPopupMessage;

	//First Offer Hold button
	@FindBy(id = "errorForm:okButtonId")
	private Element eleErrorPopupOk;

	//*********************
	//** Build page area **
	//*********************

	public CreateReservationSelectOfferPage(WebDriver driver){
		this.driver = driver;	
		ElementFactory.initElements(driver, this);	  
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/*	private CreateReservationSelectOfferPage initialize(WebDriver driver) {
	    return ElementFactory.initElements(driver, CreateReservationSelectOfferPage.class);	        
	}*/

	public boolean pageLoaded(){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnSelect);
	}

	public boolean pageLoaded(Element element){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	public CreateReservationSelectOfferPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	//**************************************
	//*** Create Reservation Select Offer Page Interactions ***
	//************************************** 


	public void clickCancel() {
		btnCancel.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickDiscover() {
		btnDiscover.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickSelect() 
	{
		initialize();
		//Commenting out as hard sleeps are discouraged
//		Sleeper.sleep(3000);
		pageLoaded(btnSelect);
		btnSelect.syncVisible(driver);
		btnSelect.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * 
	 * @summary Selects an offer from offer results by the room type specified
	 *          in the datatable
	 * 
	 * @version Created 9/11/2014
	 * @author Jessica Marshall
	 * @param The
	 *            datatable scenario
	 * @throws Exception
	 * @return True if the offer is found & false if the offer is not found
	 * 
	 */

	public boolean selectOfferByRoomType(String scenario) {
		datatable.setVirtualtablePage("CreateResSelectOfferPage");
		datatable.setVirtualtableScenario(scenario);
		int row = 0;
		boolean found = false;
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
//		// Move the mouse to the top-left corner of the screen to prevent issues
//		// with searching for a particualr row
//		Actions builder=new Actions(driver);
//		builder.moveToElement(driver.findElement(By.id("createResSearchResultsForm:cancelButton"))).build().perform();

		//Get all the offer rows in the table in a list
		List <WebElement> tableDataList = driver.findElements(By.cssSelector("td[id *= 'createResSearchResultsForm:searchOfferResultsDataTable']"));
		//Go through each offer row element in the list and see if it contains the room type specified in the datatable
		for (WebElement element:tableDataList){
			//if it contains the room type then click the element & exit for
			if (element.getText().contains(datatable.getDataParameter("RoomType"))){
				//Commenting below line to incorporate the element into the element factory
//				((JavascriptExecutor)driver).executeScript("document.getElementById('createResSearchResultsForm:searchOfferResultsDataTable:" + row + ":offerColumn').click()");
				WebElement wElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("createResSearchResultsForm:searchOfferResultsDataTable:" + row + ":offerColumn")));
				Element eElement = new ElementImpl(wElement);
				eElement.syncVisible(driver);
				eElement.scrollIntoView(driver);
 			    eElement.highlight(driver);
//				eElement.jsClick(driver);
 			   eElement.click();
				//element.click();
				Sleeper.sleep(1000);
				found = true;
				break;
			}
			//mark that found is false and increment the row counter
			found = false;
			row ++;
		}

		//if non of the offers contain the room type, then return a false 
		if (!found){
			return false;
		}

		new ProcessingYourRequest().WaitForProcessRequest(driver);
		Sleeper.sleep(3000);


		//Click the hold button for that row
		WebElement wElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("createResSearchResultsForm:searchOfferResultsDataTable:" + row + ":holdOffer")));
		//driver.findElement(By.id("createResSearchResultsForm:searchOfferResultsDataTable:" + row + ":holdOffer")).click();

		// Commenting out the below line as the element may not actually be
		// clicked in some instances. Chosing instead to use the element factory
		//((JavascriptExecutor)driver).executeScript("document.getElementById('createResSearchResultsForm:searchOfferResultsDataTable:" + row + ":holdOffer').click()");
		Element element = new ElementImpl(wElement);
		element.syncVisible(driver);
//		element.scrollIntoView(driver);
		element.highlight(driver);
		element.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		Sleeper.sleep(1000);

		System.out.println(btnSelect.isEnabled());
		found = true;

		return found;
	}

	public void selectOffer(String scenario) {
		datatable.setVirtualtablePage("CreateResSelectOfferPage");
		datatable.setVirtualtableScenario(scenario);
		
		String SetPartyMix = datatable.getDataParameter("SubmitPartyMix");	
		TestReporter.assertEquals(selectOfferByRoomType(scenario), true, "The room type was not found in the offers list.");
		
		// Added the below if-condition to click on Submit button for the PartyMix pop-up
		if(SetPartyMix.equalsIgnoreCase("true")){
			initialize();
			pageLoaded(elePartyMix);
			elePartyMix.syncVisible(driver);
			btnSubmit.jsClick(driver);
			new ProcessingYourRequest().WaitForProcessRequest(driver, 60);
		}
		clickSelect();
	}

	/**
	 * 
	 * @summary Validates the partymix popup if exists and clicks on the submit at the time of 
	 * selecting an offer from offer page while creating a reservation
	 * @version Created 11/13/2015
	 * @author Marella Satish
	 * @param  NA
	 * @throws NA
	 * @return NA
	 * 
	 */

	//Validates the partymix popup and clicks on the submit
	public void partyMixPopup(){
		// Commenting out the below code to use syncVisible to determine if the
		// popup is displayed and fail the test if it is not.
//		PartyMix_Status = elePartyMix.isDisplayed();
//		TestReporter.logStep("Party_Mix Status "+PartyMix_Status);
//		if (PartyMix_Status=true) {
//			pageLoaded(elePartyMix);
//			btnSubmit.jsClick(driver);
//			new ProcessingYourRequest().WaitForProcessRequest(driver, 40);
//
//		}else{
//			TestReporter.logStep("No PopUp Found");
//		}
		initialize();
		pageLoaded(elePartyMix);
		elePartyMix.syncVisible(driver);
		btnSubmit.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver, 60);
	}

	/**
	 * @Summary : Created method to initialise the page and find the button select and to click on it.
	 * @version : Created 11/17/2015
	 * @author 	: Praveen Namburi
	 * @param   : NA
	 * @throws  : NA 
	 */
	public void clickbtnSelect() {

		initialize();
		pageLoaded(btnSelect);
		btnSelect.syncVisible(driver);

		StopWatch watch = new StopWatch();
		watch.start();
		do{
			Sleeper.sleep(100);
		}while(watch.getTime() / 1000 < WebDriverSetup.getDefaultTestTimeout() && !btnSelect.getAttribute("class").equalsIgnoreCase("disabled-false"));
		watch.stop();
		watch.reset();
		
		Sleeper.sleep(3000);
		btnSelect.highlight(driver);
		btnSelect.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}


	/**
	 * @Summary : Created method to select the offer based on the Roomtype and submit it.
	 * @version : Created 11/17/2015
	 * @author 	: Praveen Namburi
	 * @param   : Datatable scenario
	 * @throws  : Exception
	 * @Return  : NA 
	 */
	public void selectOfferbasedOnRoomType(String scenario) {
		TestReporter.assertEquals(selectOfferByRoomType(scenario), true, "The room type was not found in the offers list.");
		clickbtnSelect();
	}

	public void selectFirstOffer(){
		initialize();
		pageLoaded(btnFirstOfferHold);
		btnFirstOfferHold.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		clickSelect();
	}

	/**
	 * @Summary : Created method for Reading number of rooms selected in DropDown under createResSearchResultsForm.
	 * @version : Created 11/24/2015
	 * @author 	: Lalitha Banda
	 * @param   : NA
	 * @throws  : Exception
	 * @Return  : String 
	 */

	public String  verify_DD_SearchResultsForm(){
		Select sel = new Select(driver.findElement(By.id("createResSearchResultsForm:searchOfferResultsDataTable:0:selectedNoOfRoomsToFreeze")));
		TestReporter.log(sel.getFirstSelectedOption().getText());
		return  sel.getFirstSelectedOption().getText();
	}

	/**
	 * @Summary : Created method for Handling error Pop up in Create Reservation for Number of adults is less than number rooms to be freeze.
	 * @version : Created 11/24/2015
	 * @author 	: Lalitha Banda
	 * @param   : NA
	 * @throws  : Exception
	 * @Return  : boolean 
	 */

	public boolean handle_ErrorPopup(){
		boolean ReturnValue = false;
		String NoofRooms = verify_DD_SearchResultsForm();
		if(NoofRooms.equals("3") &&eleErrorPopupHeader.isDisplayed()){
			TestReporter.log(eleErrorPopupMessage.getText());
			eleErrorPopupMessage.highlight(driver);
			TestReporter.assertTrue(eleErrorPopupMessage.getText().contains("Number of adults is less than number rooms to be freezed"), "The popup message was not found!!");
			eleErrorPopupOk.highlight(driver);
			eleErrorPopupOk.click();

			ReturnValue = true;
		}
		return ReturnValue;
	}

	public boolean selectOfferByRoomType_HandleErrorPopup(String scenario) {
		datatable.setVirtualtablePage("CreateResSelectOfferPage");
		datatable.setVirtualtableScenario(scenario);
		int row = 0;
		boolean found = false;
		WebDriverWait wait = new WebDriverWait(driver, 15);

		//Get all the offer rows in the table in a list
		List <WebElement> tableDataList = driver.findElements(By.cssSelector("td[id *= 'createResSearchResultsForm:searchOfferResultsDataTable']"));
		//Go through each offer row element in the list and see if it contains the room type specified in the datatable
		for (WebElement element:tableDataList){
			//if it contains the room type then click the element & exit for
			if (element.getText().contains(datatable.getDataParameter("RoomType"))){
				((JavascriptExecutor)driver).executeScript("document.getElementById('createResSearchResultsForm:searchOfferResultsDataTable:" + row + ":offerColumn').click()");
				//element.click();
				Sleeper.sleep(1000);
				found = true;
				break;
			}
			//mark that found is false and increment the row counter
			found = false;
			row ++;
		}

		//if non of the offers contain the room type, then return a false 
		if (!found){
			return false;
		}

		new ProcessingYourRequest().WaitForProcessRequest(driver);
		Sleeper.sleep(3000);


		//Click the hold button for that row
		wait.until(ExpectedConditions.elementToBeClickable(By.id("createResSearchResultsForm:searchOfferResultsDataTable:" + row + ":holdOffer")));
		//driver.findElement(By.id("createResSearchResultsForm:searchOfferResultsDataTable:" + row + ":holdOffer")).click();

		//Reading No of Rooms selected
		verify_DD_SearchResultsForm();

		((JavascriptExecutor)driver).executeScript("document.getElementById('createResSearchResultsForm:searchOfferResultsDataTable:" + row + ":holdOffer').click()");
		Sleeper.sleep(2000);


		boolean getStatus = handle_ErrorPopup();
		if(getStatus){
			clickCancel();
			found = true;
		}else{

			System.out.println(btnSelect.isEnabled());

			found = true;
		}

		return found;
	}


	public boolean selectOfferByRoomType_cancelRes(String scenario) {
		datatable.setVirtualtablePage("CreateResSelectOfferPage");
		datatable.setVirtualtableScenario(scenario);
		int row = 0;
		boolean found = false;
		WebDriverWait wait = new WebDriverWait(driver, 15);

		//Get all the offer rows in the table in a list
		List <WebElement> tableDataList = driver.findElements(By.cssSelector("td[id *= 'createResSearchResultsForm:searchOfferResultsDataTable']"));
		//Go through each offer row element in the list and see if it contains the room type specified in the datatable
		for (WebElement element:tableDataList){
			//if it contains the room type then click the element & exit for
			if (element.getText().contains(datatable.getDataParameter("RoomType"))){
				((JavascriptExecutor)driver).executeScript("document.getElementById('createResSearchResultsForm:searchOfferResultsDataTable:" + row + ":offerColumn').click()");
				//element.click();
				Sleeper.sleep(1000);
				found = true;
				break;
			}
			//mark that found is false and increment the row counter
			found = false;
			row ++;
		}

		//if non of the offers contain the room type, then return a false 
		if (!found){
			return false;
		}

		new ProcessingYourRequest().WaitForProcessRequest(driver);
		Sleeper.sleep(3000);


		//Click the hold button for that row
		wait.until(ExpectedConditions.elementToBeClickable(By.id("createResSearchResultsForm:searchOfferResultsDataTable:" + row + ":holdOffer")));
		//driver.findElement(By.id("createResSearchResultsForm:searchOfferResultsDataTable:" + row + ":holdOffer")).click();

		((JavascriptExecutor)driver).executeScript("document.getElementById('createResSearchResultsForm:searchOfferResultsDataTable:" + row + ":holdOffer').click()");
		Sleeper.sleep(4000);

		partyMixPopup();
		clickSelect();

		found = true;

		return found;
	}
	
	/**
	 * 
	 * @summary Selects an offer from offer results by the room type specified
	 *          in from other method
	 * 
	 * @version Created 12/08/2015
	 * @author Marella Satish
	 * @param Method returned value as parameter
	 * @throws Exception
	 * @return True if the offer is found & false if the offer is not found
	 * 
	 */

	public boolean selectOfferByRoomType_UpdatedRoomType(String roomType) {
//		datatable.setVirtualtablePage("CreateResSelectOfferPage");
//		datatable.setVirtualtableScenario(roomType);
		int row = 0;
		boolean found = false;
		WebDriverWait wait = new WebDriverWait(driver, 15);

		//Get all the offer rows in the table in a list
		List <WebElement> tableDataList = driver.findElements(By.cssSelector("td[id *= 'createResSearchResultsForm:searchOfferResultsDataTable']"));
		//Go through each offer row element in the list and see if it contains the room type specified in the data table
		for (WebElement element:tableDataList){
			//if it contains the room type then click the element & exit for
			if (element.getText().contains(roomType)){
				((JavascriptExecutor)driver).executeScript("document.getElementById('createResSearchResultsForm:searchOfferResultsDataTable:" + row + ":offerColumn').click()");
				//element.click();
				Sleeper.sleep(1000);
				found = true;
				break;
			}
			//mark that found is false and increment the row counter
			found = false;
			row ++;
		}

		//if non of the offers contain the room type, then return a false 
		if (!found){
			return false;
		}

		new ProcessingYourRequest().WaitForProcessRequest(driver);
		Sleeper.sleep(3000);


		//Click the hold button for that row
		wait.until(ExpectedConditions.elementToBeClickable(By.id("createResSearchResultsForm:searchOfferResultsDataTable:" + row + ":holdOffer")));
		//driver.findElement(By.id("createResSearchResultsForm:searchOfferResultsDataTable:" + row + ":holdOffer")).click();

		((JavascriptExecutor)driver).executeScript("document.getElementById('createResSearchResultsForm:searchOfferResultsDataTable:" + row + ":holdOffer').click()");
		Sleeper.sleep(1000);

		System.out.println(btnSelect.isEnabled());
		found = true;

		return found;
	}

	public void selectOffer_RoomType(String roomType) {

		Assert.assertEquals(selectOfferByRoomType_UpdatedRoomType(roomType), true, "The room type was not found in the offers list.");
		clickSelect();
	}

}

