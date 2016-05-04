package apps.lilo.packageInformation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.TextboxImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

/**
 * This class contains elements and element interactions for a given page of a
 * web application
 * 
 * @author Waightstill W Avery
 * @version 11/16/2015 Waightstill W Avery - original
 */
public class PackageInformationPage {
	// *******************
	// Page Class Fields
	// *******************
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	// *********************
	// Page Class Elements
	// *********************
	@FindBy(id = "packageDetailForm:packageDetailViewList:tb")
	private Webtable tblPackageDetailViewList;
	
	@FindBy(xpath = "/html/body/div[5]/div[2]/div/div[2]/table/tbody/tr[2]/td/form/table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td/table/tbody/tr[2]/th/div/div/table/tbody/tr[2]/td[4]/input")
	private Textbox txtResortBase;
	
	@FindBy(xpath = "/html/body/div[5]/div[2]/div/div[2]/table/tbody/tr[2]/td/form/table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td/table/tbody/tr[2]/th/div/div/table/tbody/tr[4]/td[4]/input")
	private Textbox txtSnackBase;
	
	@FindBy(xpath = "/html/body/div[5]/div[2]/div/div[2]/table/tbody/tr[2]/td/form/table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td/table/tbody/tr[2]/th/div/div/table/tbody/tr[6]/td[4]/input")
	private Textbox txtqucikServiceBase;
	
	@FindBy(xpath = "/html/body/div[5]/div[2]/div/div[2]/table/tbody/tr[2]/td/form/table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td/table/tbody/tr[2]/th/div/div/table/tbody/tr[11]/td[4]/input")
	private Textbox txtAccommodationBase;
	
	@FindBy(id = "packageDetailForm:submitId")
	private Button btnSubmit;

	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Waightstill W Avery
	 * @version 11/16/2015 Waightstill W Avery - original
	 * @param driver
	 */
	public PackageInformationPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Waightstill W Avery
	 * @version 11/16/2015 Waightstill W Avery - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public PackageInformationPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 11/16/2015 Waightstill W Avery - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, tblPackageDetailViewList);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W Avery
	 * @version 11/16/2015 Waightstill W Avery - original
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
	
	public void addPackageAdjustment(String scenario){
		datatable.setVirtualtablePage("PackageInformation");
		datatable.setVirtualtableScenario(scenario);
		
		String resortBase = datatable.getDataParameter("ResortMugComponentBase");
		String snackBase = datatable.getDataParameter("SnackComponentBase");
		String quickServiceBase = datatable.getDataParameter("QuickServiceComponentBase");
		String accommodationBase = datatable.getDataParameter("AccommodationBase");
		
		initialize();
		System.out.println("Syncing to page being loaded.");
		pageLoaded();
		System.out.println("Resort Base: " + resortBase);
		txtResortBase = new TextboxImpl(tblPackageDetailViewList.findElement(By.xpath("tr[2]/td[4]/input")));
		txtResortBase.set(resortBase);
		System.out.println("Snack Base: " + snackBase);
		txtSnackBase = new TextboxImpl(tblPackageDetailViewList.findElement(By.xpath("tr[4]/td[4]/input")));
		txtSnackBase.set(snackBase);
		System.out.println("Quick Service Base: " + quickServiceBase);
		txtqucikServiceBase = new TextboxImpl(tblPackageDetailViewList.findElement(By.xpath("tr[6]/td[4]/input")));
		txtqucikServiceBase.set(quickServiceBase);
		System.out.println("Accommodation Base: " + accommodationBase);
		txtAccommodationBase = new TextboxImpl(tblPackageDetailViewList.findElement(By.xpath("tr[11]/td[4]/input")));
		txtAccommodationBase.set(accommodationBase);
		
		clickSubmit();
	}
	
	private void clickSubmit(){
		initialize();
		pageLoaded(btnSubmit);
		btnSubmit.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
}

