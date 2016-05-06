package cucumberStepDefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @summary: Created a sample test using Cucumber with Java.
 * @author praveen varma, @version: Created 06-05-2016
 */
public class GoogleCalTest {

	//**************************
	// page Fields
	//**************************
	protected WebDriver driver;

	
	//**************************
	// page Elements
	//**************************
	@FindBy(id="sb_ifc0")
	private WebElement eleSearchTextbox;
	
	@FindBy(name="btnK")
	private WebElement btnGoogleSearch;
	
	@FindBy(id="cwos")
	private WebElement txtResult;
	
	
	//**************************
	// Test class Methods
	//**************************
	
	/**
	 * @summary: Method to Open the Firefox browser.
	 * @author: Praveen Namburi, @version: Created 06-05-2016
	 */
	@Before 
	public void setup() { 
		driver = new FirefoxDriver(); 
	}
	
	
	/**
	 * @summary: Method to launch the Google home page.
	 * @author: Praveen Namburi, @version: Created 06-05-2016
	 */
	@Given("^launch the google homepage$") 
	public void launch_the_google_homepage() { 
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.google.com");
	}
	
	
	/**
	 * @summary: Method to enter the text in search-box
	 * 			and click on Search button.
	 * @author: Praveen Namburi, @version: Created 06-05-2016
	 * @param inputText
	 */
	@When("^enter the value in textbox$")
	public void enter_the_value_in_textbox(String inputText) {
		eleSearchTextbox.sendKeys(inputText);
		btnGoogleSearch.click();
	}
	
	
	/**
	 * @summary: Method Get the Actual value from the calculator.
	 * @author: Praveen Namburi, @version: Created 06-05-2016
	 * @param expectedValue
	 */
	@Then("^should get correct result \"([^\"]*)\"$")
	public void should_get_correct_result(String expectedValue){
		//Get the resulted output value
		String getActualValue = txtResult.getText();
		
		//Verify that result of 2+2 is 4
		Assert.assertEquals(getActualValue, expectedValue);
		
		driver.close();
	}
	
	/**
	 * @summary: Method to close all the current instances.
	 * @author: Praveen Namburi, @version: Created 06-05-2016
	 */
	@After
	public void closeBrowser() {
		driver.quit();
	}
	
}
