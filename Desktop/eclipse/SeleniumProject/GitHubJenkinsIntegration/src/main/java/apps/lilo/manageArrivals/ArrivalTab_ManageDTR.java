package apps.lilo.manageArrivals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

import apps.lilo.assignRoom.AssignRoomPage;
import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.WebDriverSetup;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Label;
import core.interfaces.Link;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import selenium.common.CustomVerification;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

/**
 * @summary Contains the page methods & objects found in the Manage Arrivals-Arrival Tab page
 * @version Created 11/05/2014
 * @author Waightstill W Avery
 */
public class ArrivalTab_ManageDTR {

	//*******************************
	//*** Arrival Tab Page Fields ***
	//*******************************
	private int timeout = WebDriverSetup.getDefaultTestTimeout();
	private int loopCounter = 0;
	private Datatable datatable = new Datatable();
	private WebDriver driver;
	private CustomVerification custom = new CustomVerification();

	//*********************************
	//*** Arrival Tab Page Elements ***
	//*********************************

	//Go search button
	@FindBy(id = "manageArrivalForm:goSearchSelectedResortIdsForArrivals")
	private Link lnkGoSearch;

	@FindBy(linkText = "Assign")
	private Link lnkAssign;

	//Filter textbox
	@FindBy(id = "filter_input")
	private Textbox txtFilter;

	//Search Date textbox
	@FindBy(id = "manageArrivalForm:selectDateInput")
	private Textbox txtDateSearch;

	//Arriving Accommodations webtable
	@FindBy(id = "arrivingAccommodationsDataTable1")
	private Webtable tblArrivingAccommodations;

	//Notifications button
	@FindBy(id = "manageArrivalForm:notificationID")
	private Button btnNotifications;

	//Arrival Resort listbox
	@FindBy(id = "adjustRoomCountAppMenu:selectedResortIdForArrival")
	private Listbox lstArrivalResort;

	//Loading image
	@FindBy(id = "manageArrivalForm:mngArrInPageStatusRegion:status.start")
	private Element eleLoading;

	//Refresh button
	@FindBy(id = "manageArrivalForm:butRefresh")
	private Link lnkRefesh;

	@FindBy(xpath="//table[@id='arrivingAccommodationsDataTable1']/tbody/tr[4]/td[5]/a")
	private Element searchedReservationNumber;

	//Arrival Accommodation table
	@FindBy(id = "arrivingAccommodationsDataTable1")
	private Webtable tblArriving;
	
	//Missing DTR Icon
	@FindBy(xpath="//img[@src='/PMS/pms/images/MissingDTRCriteria.png']")
	private Label missingDTRIcon;

	//Reservation Details Popup
	@FindBy(id = "viewResPopupModalPanelContentTable")
	private Webtable tblResPopupModal;

	//Arrivals Text 
	@FindBy(id = "arrivalsTab_lbl")
	private Label lblArrivals;
	
	//Override All Exceptions Checkbox
	@FindBy(id="DTrEligibilityForm:criteriaDetail:selectAllcheckbox")
	private Checkbox SelectCriteriaCheckBox;
	
	//Save Exceptions button
	@FindBy(id="DTrEligibilityForm:saveExceptionsButtonId")
	private Button SaveExceptionBtn;
	
	//Close the Exception Dialog
	@FindBy(id="DTrEligibilityForm:closeButtonId")
	private Button CloseExceptionBtn;
	
	//History Tab Link
	@FindBy(id="historyTab_lbl")
	private Link HistoryLink;
	
	@FindBy(id="reservationHistoryFrm:reservationHistoryList:tb")
	private Webtable ReservationHistoryTable;
	
	@FindBy(xpath="//img[@src='/PMS/pms/images/NewOLCI.png']")
	private Label OLCI_Icon;
	
	@FindBy(id="roomTabFrm:exitButtonIdOne")
	private Button ExitBtn;

	//*********************
	//** Build page area **
	//*********************
	/**
	 * 
	 * @summary Constructor to initialize the page
	 * @version Created 11/05/2014
	 * @author Waightstill W Avery
	 * @param  driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public ArrivalTab_ManageDTR (WebDriver driver){
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public boolean pageLoaded(WebDriver driver){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, lnkGoSearch);        
	}

	public boolean pageLoaded(WebDriver driver, Element element){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);        
	}

	public boolean pageLoaded(){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, lnkGoSearch);        
	}

	public boolean pageLoaded(Element element){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);        
	}

	public ArrivalTab_ManageDTR initialize() {
		return ElementFactory.initElements(driver,
				this.getClass());
	}

	//*************************************
	//*** Arrival Tab Page Interactions ***
	//*************************************
	/**
	 * 
	 * @summary Method to select a view radio button
	 * @version Created 11/05/2014
	 * @author Waightstill W Avery
	 * @param  view - name of the desired view (KNOWN VIEWS: "Show All", "OLCI", "DTR")
	 * @throws NA
	 * @return NA
	 */
	public void selectViewRadioButton(String view){
		/*
		 * Known values at time of method creation:
		 * 	Show All
		 * 	OLCI
		 * 	DTR
		 */
		List<WebElement> elementList = driver.findElements(By.cssSelector("input[id^=\"manageArrivalForm:j_id\"]"));
		Iterator<WebElement> iterator = elementList.iterator();
		boolean buttonFound = false;

		while(iterator.hasNext() && !buttonFound){
			Element element = new ElementImpl(iterator.next());
			System.out.println("Element" +element);
			element.highlight(driver);
			if(element.getAttribute("value").equalsIgnoreCase(view)){
				element.highlight(driver);
				buttonFound = true;
				element.click();
				loadingImageHidden();
			}
		}
		Assert.assertEquals(buttonFound, true, "The radio button with value "+view+" was not found\n");
	}

	/**
	 * 
	 * @summary Method to select
	 * @version Created 11/05/2014
	 * @author Waightstill W Avery
	 * @param  view - name of the desired view (KNOWN VIEWS: "Show All", "OLCI", "DTR")
	 * @throws NA
	 * @return NA
	 */
	public void changeSearchView(String view){
		selectViewRadioButton(view);
		clickSearchGo();
	}

	/**
	 * 
	 * @summary Method to determine if the loading image is hidden
	 * @version Created 11/05/2014
	 * @author Waightstill W Avery
	 * @param  NA
	 * @throws NA
	 * @return NA
	 */
	private void loadingImageHidden(){
		loopCounter = 0;
		while(eleLoading.getAttribute("style").equalsIgnoreCase("")){
			Sleeper.sleep(500);
			Assert.assertNotEquals(loopCounter++, timeout, "The loading image was not hidden after " 
					+String.valueOf(timeout/2)+ " seconds.\n");
		}
	}

	private void clickSearchGo(){
		lnkGoSearch.syncEnabled(driver, 40, false);
		lnkGoSearch.jsClick(driver);
		//new ProcessingYourRequest().WaitForProcessRequest(driver);
		pageLoaded(driver);
	}


	/**
	 * 
	 * @summary Method to enter a string with which to filter the displayed arrivals
	 * @version Created 11/05/2014
	 * @author Waightstill W Avery
	 * @param  filter - string to use for the filter
	 * @throws NA
	 * @return NA
	 */
	public void enterFilterSearch(String filter){
		txtFilter.highlight(driver);
		txtFilter.safeSet(filter);
		Sleeper.sleep(1000);
		pageLoaded(driver);

		Sleeper.sleep(4000);
		String serached_element = driver.findElement(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr[4]/td[5]/a")).getText().trim();
		Element element = new ElementImpl(driver.findElement(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr[4]/td[5]/a")));
		element.highlight(driver);
		// searchedReservationNumber.highlight(driver);
		System.out.println("Element " + serached_element);
	}


	// By brajesh 15/2 
	public boolean enterFilterSearch1(String filter){
		boolean isReservationNumberFound = false;
		txtFilter.highlight(driver);
		txtFilter.safeSet(filter);
		Sleeper.sleep(1000);
		pageLoaded(driver);


		try{
			//Highlight the Matching Table element present inside the Table	
			String serached_element = driver.findElement(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr[4]/td[5]/a")).getText().trim();
			Element element = new ElementImpl(driver.findElement(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr[4]/td[5]/a")));
			element.highlight(driver);
			System.out.println("Element " + element);

			if(serached_element.contains(filter)){
				isReservationNumberFound = true;
				System.out.println("True " + element);
			}
		}catch(ElementNotFoundException e){
			Reporter.log("The Element was not found", false);
			System.out.println("False");
			e.printStackTrace();
		}

		return isReservationNumberFound;
	}


	/**
	 * 
	 * @summary Method to refresh the page
	 * @version Created 11/05/2014
	 * @author Waightstill W Avery
	 * @param  NA
	 * @throws NA
	 * @return NA
	 */
	public void refreshPage(){
		clickRefresh();
		pageLoaded(driver);
	}

	private void clickRefresh(){
		lnkRefesh.syncEnabled(driver, 45, false);
		lnkRefesh.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}


	/**
	 * 
	 * @summary Method to select the arrival resort
	 * @version Created 11/05/2014
	 * @author Waightstill W Avery
	 * @param  resort - name of the desired result as it appears in the list (EX: Disney's Contemporary Resort)
	 * @throws NA
	 * @return NA
	 */
	public void selectArrivalResort(String resort){
		lstArrivalResort.select(resort);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		pageLoaded(driver);
	}

	/**
	 * 
	 * @summary Method to enter an arrival date for which to search
	 * @version Created 11/05/2014
	 * @author Waightstill W Avery
	 * @param  arrivalDate - string of the arrival date (EX: 11/05/2014")
	 * @throws NA
	 * @return NA
	 */
	public void enterArrivalDate(String arrivalDate){

		txtDateSearch.syncVisible(driver, 40, false);
		txtDateSearch.safeSet(arrivalDate);
		//loadingImageHidden();
		pageLoaded(driver);
	}


	/**
	 * 
	 * @summary Method to locate the DTR icon in the arrivals table
	 * @version Created 11/05/2014
	 * @author Waightstill W Avery
	 * @param  expectedToBeFound - boolean value used to determine if the icon is expected to be found
	 * @param  reservationNumber - reservation with which to search for an associated DTR image
	 * @throws NA
	 * @return true if icon is found; false otherwise
	 */
	public boolean locateDtrIcon(boolean expectedToBeFound, String reservationNumber){
		boolean isIconFound = false;
		int row;
		int column = 0;
		int columnCount;

		columnCount = tblArrivingAccommodations.getColumnCount(driver, 1);
		for(int counter = 1; counter <= columnCount; counter++){
			Element tableCell = new ElementImpl(tblArrivingAccommodations.getCell(driver, 1, counter));
			tableCell.highlight(driver);
			if(tableCell.getText().contains("DTR")){
				column = counter;
			}
		}

		row = tblArrivingAccommodations.getRowWithCellText(driver, reservationNumber);
		Element cell = new ElementImpl(tblArrivingAccommodations.getCell(driver, row, column));
		try{
			if(cell.getAttribute("src").contains("BPD")){
				isIconFound = true;
			}
		}catch(ElementNotFoundException e){
			Reporter.log("The DTR icon was not found", true);
			e.printStackTrace();
		}

		return isIconFound;
	}

	/**
	 * 
	 * @summary Method to locate the OLCI icon in the arrivals table
	 * @version Created 11/05/2014
	 * @author Waightstill W Avery
	 * @param  expectedToBeFound - boolean value used to determine if the icon is expected to be found
	 * @param  reservationNumber - reservation with which to search for an associated OLCI image
	 * @throws NA
	 * @return true if the icon is found, false otherwise
	 */
	public boolean locateOlciIcon(boolean expectedToBeFound, String reservationNumber){
		boolean isIconFound = false;
		int row;
		int column = 0;
		int columnCount;

		columnCount = tblArrivingAccommodations.getColumnCount(driver, 1);
		for(int counter = 1; counter <= columnCount; counter++){
			Element tableCell = new ElementImpl(tblArrivingAccommodations.getCell(driver, 1, counter));
			tableCell.highlight(driver);
			if(tableCell.getText().contains("OLCI")){
				column = counter;
			}
		}

		row = tblArrivingAccommodations.getRowWithCellText(driver, reservationNumber);
		Element cell = new ElementImpl(tblArrivingAccommodations.getCell(driver, row, column));

		try{
			if(cell.getAttribute("src").contains("OLCI")){
				isIconFound = true;
			}
		}catch(ElementNotFoundException e){
			Reporter.log("The OLCI icon was not found", true);
			e.printStackTrace();
		}

		return isIconFound;
	}

	public void verifyOlciDtrBlankAscendDescend(){
		/*
		 * The page can load and then the UI code for RTC33615 is invoked and
		 * reloads the page. For this reason, a hard wait may be neccessary
		 */
		Sleeper.sleep(2000);
		boolean dtrFound = false;
		boolean olciFound = false;
		boolean blankFound = false;
		boolean[] descIsFound = {dtrFound, olciFound, blankFound};

		isElementNull(tblArrivingAccommodations, "The Arriving Accommodations webtable was not displayed after ["+String.valueOf(timeout)+"] seconds");
		List<WebElement> rowList = driver.findElements(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr"));
		int rows = rowList.size();
		for(loopCounter = 0; loopCounter < rows; loopCounter++){
			List<WebElement> dtrOlciImage = driver.findElements(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["+String.valueOf(loopCounter+1)+"]/td[10]/img"));
			System.out.println(dtrOlciImage);
			descIsFound = isDtrOlciBlankFoundDescending(dtrOlciImage, descIsFound);
		}

		dtrFound = false;
		olciFound = false;
		blankFound = false;
		boolean[] ascIsFound = {dtrFound, olciFound, blankFound};
		Element element = new ElementImpl(driver.findElement(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/thead/tr/th[10]/div/i")));
		do{
			element.highlight(driver);
			element.click();
			new ProcessingYourRequest().WaitForProcessRequest(driver);
			isElementNull(tblArrivingAccommodations, "The Arriving Accommodations webtable was not displayed after ["+String.valueOf(timeout)+"] seconds");
		}while(!(driver.findElement(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/thead/tr/th[10]")).getAttribute("class").contains("Asc")));
		rowList = driver.findElements(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr"));
		rows = rowList.size();
		for(loopCounter = 0; loopCounter < rows; loopCounter++){
			List<WebElement> dtrOlciImage = driver.findElements(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["+String.valueOf(loopCounter+1)+"]/td[10]/img"));
			ascIsFound = isDtrOlciBlankFoundAscending(dtrOlciImage, ascIsFound);
		}
	}

	private boolean[] isDtrOlciBlankFoundDescending(List<WebElement> list, boolean[] found){
		//Determine if an image is found
		if(list.size() > 0){
			//If OLCI is found...
			if(list.get(0).getAttribute("Alt").equalsIgnoreCase("online check in")){
				found[1] = true;
				Assert.assertNotEquals(found[2], true, "An OLCI reservation was unexpectedly found after an standard reservation was found.");
				//If DTR is found
			}else{
				found[0] = true;
				Assert.assertNotEquals(found[1], true, "A DTR reservation was unexpectedly found after an OLCI reservation was found.");
				Assert.assertNotEquals(found[2], true, "A DTR reservation was unexpectedly found after a standard reservation was found.");
			}
			//If not, a blank value was found
		}else{
			found[2] = true;
		}

		return found;
	}

	private boolean[] isDtrOlciBlankFoundAscending(List<WebElement> list, boolean[] found){
		//Determine if an image is found
		if(list.size() > 0){
			//If OLCI is found...
			if(list.get(0).getAttribute("Alt").equalsIgnoreCase("online check in")){
				found[1] = true;
				Assert.assertNotEquals(found[0], true, "An OLCI reservation was unexpectedly found after an DTR reservation was found.");
				//If DTR is found
			}else{
				found[0] = true;
			}
			//If not, a blank value was found
		}else{
			found[2] = true;
			Assert.assertNotEquals(found[0], true, "A standard reservation was unexpectedly found after a DTR reservation was found.");
			Assert.assertNotEquals(found[1], true, "A standard reservation was unexpectedly found after an OLCI reservation was found.");
		}

		return found;
	}

	private void isElementNull(Element element, String errorMessage){
		int timeoutCounter = 0;

		do{
			Sleeper.sleep(1000);
			Assert.assertNotEquals(timeoutCounter++, timeout, errorMessage);
			pageLoaded(element);
			initialize();
		}while(element == null);
	}

	public void verifyOlciOnlyReservations(){
		boolean isNonOlciFound = false;
		/*
		 * The page can load and then the UI code for RTC33615 is invoked and
		 * reloads the page. For this reason, a hard wait may be neccessary
		 */
		Sleeper.sleep(2000);
		pageLoaded();
		isElementNull(tblArrivingAccommodations, "The Arriving Accommodations webtable was not displayed after ["+String.valueOf(timeout)+"] seconds");

		Element element = new ElementImpl(driver.findElement(By.xpath("//span[@id='manageArrivalForm:radio_filters']/table/tbody/tr/td[2]/input")));
		element.jsClick(driver);
		isLoadingImageVisible();
		clickSearchGo();
		isElementNull(tblArrivingAccommodations, "The Arriving Accommodations webtable was not displayed after ["+String.valueOf(timeout)+"] seconds");		

		List<WebElement> rowList = driver.findElements(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr"));
		int rows = rowList.size();
		for(loopCounter = 0; loopCounter < rows; loopCounter++){
			List<WebElement> dtrOlciImage = driver.findElements(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["+String.valueOf(loopCounter+1)+"]/td[10]/img"));
			if(!dtrOlciImage.get(0).getAttribute("alt").equalsIgnoreCase("online check in")){
				isNonOlciFound = true;
				String resNumber = driver.findElement(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["+String.valueOf(loopCounter+1)+"]/td[5]/a")).getText();
				Assert.assertNotEquals(isNonOlciFound, true, "Reservation ["+String.valueOf(loopCounter)+1+"] with reservation number ["+resNumber+"] listed in the Arriving Accommodations webtable was not an OLCI reservation");
			}
		}
	}

	private void isLoadingImageVisible(){
		Element loadingImage = new ElementImpl(driver.findElement(By.xpath("//img[@src='/PMS/pms/images/please_wait_blue.gif']")));
		loadingImage.syncVisible(driver, 1, false);
	}

	public void verifyRefreshViewReservationOrder(){

		/*
		 * The page can load and then the UI code for RTC33615 is invoked and
		 * reloads the page. For this reason, a hard wait may be neccessary
		 */
		Sleeper.sleep(2000);
		boolean dtrFound = false;
		boolean olciFound = false;
		boolean blankFound = false;
		boolean[] descIsFound = {dtrFound, olciFound, blankFound};

		isElementNull(tblArrivingAccommodations, "The Arriving Accommodations webtable was not displayed after ["+String.valueOf(timeout)+"] seconds");
		clickRefreshView();
		List<WebElement> rowList = driver.findElements(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr"));
		int rows = rowList.size();
		for(loopCounter = 0; loopCounter < rows; loopCounter++){
			List<WebElement> dtrOlciImage = driver.findElements(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["+String.valueOf(loopCounter+1)+"]/td[10]/img"));
			descIsFound = isDtrOlciBlankFoundDescending(dtrOlciImage, descIsFound);
		}
	}

	private void clickRefreshView(){
		lnkRefesh.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		pageLoaded(tblArrivingAccommodations);
		initialize();
	}
	//Method Ascending and descending Order
	public void clickonNameReservationAccommRoom()
	{


		for(int j =4;j<=7;j++)
		{
			//Xpath for Table Header element Name, Reservation,Accomm, Room
			String tableHeader= ".//table[@id='arrivingAccommodationsDataTable1']/thead/tr/th["+j+"]/div";
			Element element = new ElementImpl(driver.findElement(By.xpath(tableHeader)));
			element.highlight(driver);
			driver.findElement(By.xpath(tableHeader)).click();

			Sleeper.sleep(1000);
			clickRefreshView();
			Sleeper.sleep(6000);

		}
	}

	/**
	 * 
	 * @summary Method to enter a string with which to filter the displayed arrivals
	 * @version Created 03/12/2015
	 * @author  Brajesh Ahirwar
	 * @param  filter - string to use for the filter
	 * @throws NA
	 * @return NA
	 */

	public void serachByFilter(String filter,String arrivalDate,String match, int td)

	{
		clickRefresh();
		enterArrivalDate(arrivalDate);
		Sleeper.sleep(2000);
		clickSearchGo();
		Sleeper.sleep(4000);
		pageLoaded(driver);
		txtFilter.safeSet(filter);
		Sleeper.sleep(5000);
		pageLoaded(driver);


		WebElement table = driver.findElement(By.id("arrivingAccommodationsDataTable1"));
		// Now get all the TR elements from the table 
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		int rows = allRows.size();
		System.out.println("Row Size :" +rows);
		int counter = 0 ;
		for(int i = 1 ; i<rows;i++)
		{

			String actual= driver.findElement(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["+i+"]/td["+td+"]")).getText().trim();
			if(actual.equalsIgnoreCase(match))
			{
				// String tdValue1= driver.findElement(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["+i+"]/td[4]")).getText().trim();
				System.out.println("Actual " +actual);
				counter++;
				break;
			}else
			{

			}

		}
		if(counter>=1)
		{

			Assert.assertEquals(true, true, "Expected Value "+match+" is Matched with  Actual");
		}
		else
		{

			Assert.assertEquals(false, true, "Expected Value "+match+" is Matched with  Actual");

		}



	}

	public boolean todayDateMatch( )
	{
		refreshPage();
		boolean todaydatematch = false;

		//String todayDate = "02/28/2015";
		String todayDate = systemDatePicker();
		txtDateSearch.highlight(driver);
		String defaultDate_TodayArrival = txtDateSearch.getText();
		if(defaultDate_TodayArrival.contains(todayDate))
		{
			return true;
		}
		else
		{
			return todaydatematch;
		}
	}
	//Return System Date
	public String systemDatePicker()
	{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		return dateFormat.format(date); //02/27/2015 
	}

	public void searchByName(String filter,String arrivalDate,String match)
	{
		serachByFilter(filter,arrivalDate,match,4);
	}

	public void searchByReservationNumber(String filter,String arrivalDate,String match)
	{
		serachByFilter(filter,arrivalDate,match,5);

	}
	public void SearchByAccommStatus(String filter,String arrivalDate,String match)
	{
		serachByFilter(filter,arrivalDate,match,6);
	}
	public void  SearchByRoom(String filter,String arrivalDate,String match)
	{
		serachByFilter(filter,arrivalDate,match,7);

	}

	public void  houseKeepingStatus(String filter,String arrivalDate,String match)
	{
		serachByFilter(filter,arrivalDate,match,8);

	}
	public void  verifyNotificationStatus(String filter,String arrivalDate,String match)
	{

		serachByFilter(filter,arrivalDate,match, 9);
	}
	public void  verifyETATime(String filter,String arrivalDate,String match)
	{
		serachByFilter(filter,arrivalDate,match, 11);

	}
	public void  verifyArrivalStatus(String filter,String arrivalDate,String match)
	{

		serachByFilter(filter,arrivalDate,match, 12);
	}

	public void  verifySource(String filter,String arrivalDate,String match)
	{
		serachByFilter(filter,arrivalDate,match, 13);

	}

	public void  verifyETDDate(String filter,String arrivalDate,String match)
	{
		serachByFilter(filter,arrivalDate,match,14 );

	}

	public void  serachByGruopCode(String filter,String arrivalDate,String match)
	{
		serachByFilter(filter,arrivalDate,match, 16);

	}

	public void assign_Room(String filter,String arrivalDate,String match)

	{
		clickRefresh();
		enterArrivalDate(arrivalDate);
		Sleeper.sleep(2000);
		clickSearchGo();
		Sleeper.sleep(4000);
		pageLoaded(driver);
		txtFilter.safeSet(filter);
		Sleeper.sleep(5000);
		pageLoaded(driver);
		isElementNull(lnkAssign, "The Find Available Room Popup window was not displayed after ["+String.valueOf(timeout)+"] seconds.");
		lnkAssign.jsClick(driver);
		Sleeper.sleep(10000);
		ArrayList<String> tabs;
		tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0)); // switch to the first window 
		AssignRoomPage assignRoom = new AssignRoomPage(driver);
		//assignRoom.clickSearchCriteria();
		assignRoom.assignVacantRoom();
		//assignRoom.clickAlternateRoomsTab();
	}



	/**
	 * 
	 * @summary Method to verify Arrivals on Arrival tab
	 * @version Created 08/17/2015
	 * @author  Brajesh Ahirwar
	 * @param  expectedToBeFound - boolean value used to determine if the expected to be found
	 * @param  reservationNumber - reservation with which to search for Validation
	 * @throws NA
	 * @return true if the status is found, on Failure it log a defect in to TestNG report file
	 *
	 */

	public void verifyArrivals(Map<String,String> status)
	{			
		String reserNumber = status.get("Reservation");
		String arrivalDate = status.get("arrivalDate");
		searchReservation(reserNumber,arrivalDate);

		tblArrivingAccommodations.syncVisible(driver, 40, false);

		WebElement table = driver.findElement(By.id("arrivingAccommodationsDataTable1")); 
		// Now get all the TR elements from the table 
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		int rows = allRows.size();
		for(int row = 1; row<rows;row++)
		{

			String actual= driver.findElement(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["+row+"]/td[5]")).getText().trim();
			if(actual.equalsIgnoreCase(reserNumber))
			{
				matchingStatus(row,status);
				break;
			}

		}
		custom.checkForVerificationErrors();    
	}

	//Method to search reservation number 
	public void searchReservation(String reserNumber, String arrivalDate)
	{
		//Click On Refresh button to Refresh a Page
		clickRefresh();
		//Enter arrival date
		enterArrivalDate(arrivalDate);
		//click On Go button
		clickSearchGo();
		Sleeper.sleep(5000);
		int counter = 0;
		do{
			txtFilter.sendKeys(reserNumber);
			loopCounter++;
			pageLoaded(driver);
			initialize();

		}while(txtFilter.getAttribute("value").equalsIgnoreCase("") && counter <10);
		pageLoaded(driver);
		initialize();
	}

	public void matchingStatus(int row, Map<String,String> status)
	{

		for (Entry<String, String> arrivalStatus : status.entrySet())
		{
			String getKeyValue =arrivalStatus.getKey().trim();

			switch(getKeyValue)
			{

			case "VIP" :matchArrivals(row ,2,status.get("VIP"));
			break;
			case "Name":matchArrivals(row ,4,status.get("Name"));   
			break;
			case "Reservation":matchArrivals(row ,5,status.get("Reservation"));
			break;
			case "AccommStatus":matchArrivals(row ,6,status.get("AccommStatus"));   
			break;
			case "Room": matchArrivals(row ,7,status.get("Room"));    

			break;
			case "HK Status":matchArrivals(row ,8,status.get("HK Status")); 

			break;
			case "Notif": matchArrivals(row ,9,status.get("Notif"));    

			break;
			case "DTR/OLCI":matchArrivals(row ,10,status.get("DTR/OLCI")); 

			break;
			case "ETA": matchArrivals(row ,11,status.get("ETA"));    

			break;
			case "Arrival Status":matchArrivals(row ,12,status.get("Arrival Status")); 

			break;
			case "Source": matchArrivals(row ,13,status.get("Source"));    
			System.out.println("We are Source");
			break;
			case "ETD":matchArrivals(row ,14,status.get("ETD")); 

			break;
			case "Grp" :matchArrivals(row ,16,status.get("Grp")); 
			break;

			}
		}
	} 

	/**
	 * 
	 * @summary Method to match individual status of arrival
	 * @version Created 08/17/2015
	 * @author  Brajesh Ahirwar
	 * @param   individual  row number,col number and expected value

	 * @throws NA
	 * @return true if the status is found,on failure log a defect into TestNG file
	 *
	 */

	public void matchArrivals(int row , int col , String expected)
	{
		String actual= driver.findElement(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["+row+"]/td["+col+"]")).getText().trim();
		if(!actual.equalsIgnoreCase(expected))
		{
			custom.verifyTrue(false, "Expected Value is : ["+expected+"]  but found : ["+actual+"]");
		}

	}

	/**
	 * 
	 * @summary Method to verify Reservation Details on Arrivals Tab
	 * @version Created 08/28/2015
	 * @author  Ankur Dabas
	 * @param  expectedToBeFound - boolean value used to determine if the expected to be found
	 * @param  reservationNumber - reservation with which to search for Validation
	 * @throws NA
	 * @return true if the status is found, on Failure it log a defect in to TestNG report file
	 *
	 */
	public boolean locateReservationDetails(String resNumber, String arrDate)
	{
		boolean locateReservationNumber = true;
		String reservationNumber = resNumber;
		String arrivalDate = arrDate;
		searchReservation(reservationNumber,arrivalDate);
		WebElement table = driver.findElement(By.id("arrivingAccommodationsDataTable1")); 
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		int rows = allRows.size();
		for(int row = 1; row<rows;row++)
		{
			String actual = driver.findElement(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["+row+"]/td[5]")).getText().trim();
			if(actual.equalsIgnoreCase(reservationNumber))
			{
				locateReservationNumber = true;
				break;
			}
			else
			{
				locateReservationNumber = false;
			}

		}

		return locateReservationNumber;
	}




	/**
	 * 
	 * @summary Operations on the Missing DTR icon
	 * @version Created 08/28/2015
	 * @author  Ankur Dabas
	 * @param   NA
	 * @throws  NA
	 * @return  true if the correct DTR icon is found, on Failure it log a defect in to TestNG report file
	 *
	 */         

	public Boolean validate_MissingDTRicon(){
		boolean missingIconStatus = false;
		WebElement table = driver.findElement(By.id("arrivingAccommodationsDataTable1")); 
		// Now get all the TR elements from the table 
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		int rows = allRows.size();
		for(int row = 1; row<rows;row++)
		{
			missingIconStatus = driver.findElement(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["+row+"]/td[10]")).isDisplayed();
			if(missingIconStatus)
			{
				return missingIconStatus;
			}
			else
			{
				missingIconStatus = false;
			}
		}
		
		return missingIconStatus;	
	}
	
	
	public void clickOnMissingDTRIcon(){
		boolean iconStatus;
		WebElement table = driver.findElement(By.id("arrivingAccommodationsDataTable1")); 
		// Now get all the TR elements from the table 
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		int rows = allRows.size();
		for(int row = 1; row<rows;row++)
		{
		iconStatus = driver.findElement(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["+row+"]/td[10]")).isDisplayed();
		if(iconStatus){
			driver.findElement(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["+row+"]/td[10]")).click();
			break;
		}
		}
	}
	
	/**
	 * 
	 * @summary Override all the Exceptions to make the Reservation DTR Eligible
	 * @version Created 09/14/2015
	 * @author  Ankur Dabas
	 * @param   NA
	 * @throws  NA
	 * @return  NA
	 *
	 */   
	
	public void overrideAllExceptions(){
		
		pageLoaded(driver, SelectCriteriaCheckBox);
		TestReporter.log("Select all the Criteria of the DTR");
		SelectCriteriaCheckBox.check();
		
		pageLoaded(driver, SaveExceptionBtn);
		TestReporter.log("Save the Exceptions");
		SaveExceptionBtn.click();
		
		
		pageLoaded(driver, CloseExceptionBtn);
		TestReporter.log("Close the Exception Dialog");
		Element closeBtn = new ElementImpl(driver.findElement(By.id("DTrEligibilityForm:closeButtonId")));
		closeBtn.jsClick(driver);
		//CloseExceptionBtn.click();
		
	}

	public void openReservation(String resNumber){
		
		TestReporter.log("Click on Refresh Button");
		clickRefresh();
		
		TestReporter.log("Clearing the Reservation Number field");
		txtFilter.clear();	
		
		TestReporter.log("Entering the Reservation Number");
		txtFilter.sendKeys(resNumber);
		
		clickOnLinkTest(resNumber);
		
	}
	
	
	public void validate_DTREligibleIcon(){
	    Sleeper.sleep(5000);
	    pageLoaded(driver, ExitBtn);
		Assert.assertEquals(driver.findElement(By.id("roomTabFrm:travelPlanSegmentViewId:0:bypassResortDeskId")).isDisplayed(), true, "DTR Eligible Icon is displayed");
		TestReporter.log("Reservation is now DTR Eligible");
	}
	
	/**
	 * 
	 * @summary Validation of all the criteria on History Tab of Reservation Page
	 * @version Created 09/16/2015
	 * @author  Ankur Dabas
	 * @param   NA
	 * @throws  NA
	 * @return  NA
	 *
	 */   
	
	/*@DataProvider(name = "ManageDTR")
	public Object[][] DTRscenarios() {
		try {
			return Datatable.getTestScenariosByApp("lilo","DTRInformation"); //Defining what table to run from the virtual tables
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/
	
	/*@Test(dataProvider = "ManageDTR",
			description="Contains all the data related to various DTR Scenarios",
			groups = {"regression", "lilo"})*/
	public void validateCriteriaInHistoryTab(String userName, String arrival_Date)
	{
		String firstCriteria = "DTR Override Credit Card on File Paid in Full";
		String secondCriteria = "DTR Override Phone or Email Information on File";
		String thirdCriteria = "DTR Override Magic Band Fulfilled";
		String fourthCriteria = "DTR Override Online Check-In Completed";
		String username = userName;
		String arrivalDate = arrival_Date;
		
		TestReporter.log("Click on the History Tab");
		HistoryLink.click();
		
		Sleeper.sleep(5000);
		Assert.assertTrue(ReservationHistoryTable.getText().contains(firstCriteria), "Experience Media overriden Criteria Name Validation failed");
		System.out.println("Experience Media criteria name fulfilled");
		
		Assert.assertTrue(ReservationHistoryTable.getText().contains(secondCriteria), "Online Checkin overriden Criteria Name Validation failed");
		
		Assert.assertTrue(ReservationHistoryTable.getText().contains(thirdCriteria), "Contact Information overriden Criteria Name Validation failed");
		
		Assert.assertTrue(ReservationHistoryTable.getText().contains(fourthCriteria), "Payment Guarantee overriden Criteria Name Validation failed");
		
		TestReporter.log("All the Criteria names are validated successfully");
		
		for(int i=1;i<ReservationHistoryTable.getRowCount()-1;i++){
			Assert.assertTrue(ReservationHistoryTable.getCell(driver, i, 1).getText().contains(arrivalDate),i+" Criteria's DATE validation failed");
		}
		
		TestReporter.log("DATE validation for all the Criterias is successfull");
		
		for(int i=1; i<ReservationHistoryTable.getRowCount()-1;i++){
			Assert.assertTrue(ReservationHistoryTable.getCell(driver, i, 5).getText().equalsIgnoreCase(username),i+" Criteria's USER_ID validation failed");
		}
		
		TestReporter.log("USER_ID validation for all the Criterias is successfull");
	}
	

	public void validate_OLCIicon(String resNumber, String arrival_Date){
		String reservationNumber = resNumber;
	
		WebElement table = driver.findElement(By.id("arrivingAccommodationsDataTable1")); 
		// Now get all the TR elements from the table 
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		int rows = allRows.size();
		for(int row = 1; row<rows;row++)
		{
			String actual = driver.findElement(By.xpath("//table[@id='arrivingAccommodationsDataTable1']/tbody/tr["+row+"]/td[5]")).getText().trim();
			if(actual.equalsIgnoreCase(reservationNumber))
			{
				OLCI_Icon.highlight(driver);
				Assert.assertEquals(OLCI_Icon.isDisplayed(), true, "Online Check In Icon validation failed");
				TestReporter.log("Online Check In Icon found");
			}
			else
			{
				break;
			}

		}
	}
	
	public void clickOnLinkTest(String linkText)
    {
		 driver.findElement(By.linkText(linkText)).click();
         Sleeper.sleep(3000);
    }


}

