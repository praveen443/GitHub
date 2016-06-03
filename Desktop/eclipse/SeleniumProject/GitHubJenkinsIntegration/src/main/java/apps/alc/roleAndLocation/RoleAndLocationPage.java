package apps.alc.roleAndLocation;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.WebDriverSetup;
import core.WindowHandler;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;

/**
 * 
 * @summary Contains the page methods & objects for the ALC login page
 * @version Created 9/26/2014
 * @author Jessica Marshall
 */
public class RoleAndLocationPage {

	// ************************************
	// *** Main Page Elements ***
	// ************************************
	private Datatable datatable = new Datatable();
	private WebDriver driver;

	// Role
	@FindBy(id = "roleAndLocationSelectionForm:roleOptions")
	private Listbox lstRole;

	// Location
	@FindBy(id = "roleAndLocationSelectionForm:locationOptions")
	private Listbox lstLocation;

	// Go
	@FindBy(id = "roleAndLocationSelectionForm:goButton")
	private Button btnGo;

	// Logout
	@FindBy(id = "roleAndLocationSelectionForm:loButton")
	private Button btnLogout;

	// *********************
	// ** Build page area **
	// *********************

	public RoleAndLocationPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}

	public RoleAndLocationPage initialize(WebDriver driver) {
		return ElementFactory.initElements(driver, RoleAndLocationPage.class);
	}

	public RoleAndLocationPage initialize() {
		return ElementFactory.initElements(driver, RoleAndLocationPage.class);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnGo);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	// **************************************
	// *** Main CheckIn Page Interactions ***
	// **************************************

	public void clickGo() {
		btnGo.click();
	}

	public void clickLogout() {
		btnLogout.click();
	}

	public void selectRoleAndLocation(String scenario) {
		datatable.setVirtualtablePage("ALCLogin");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded(lstRole);
		lstRole.syncVisible(driver);

		String role = datatable.getDataParameter("Role");
		String location = datatable.getDataParameter("Location");

		lstRole.select(role);
		Sleeper.sleep(2000);
		pageLoaded(lstLocation);
		lstLocation.select(location);
		
		Sleeper.sleep(2000);
		
		pageLoaded(btnGo);
		List<WebElement> gos = driver.findElements(By.cssSelector("input[value='GO']"));
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		for(WebElement go : gos){
			Element element = new ElementImpl(go);
			if(element.syncVisible(driver, 3, false)){
				element.focus(driver);
				element.sendKeys(Keys.ENTER);
				//element.jsClick(driver);
			}
		}
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
		
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "SEServer", 30);
	}
	
}

