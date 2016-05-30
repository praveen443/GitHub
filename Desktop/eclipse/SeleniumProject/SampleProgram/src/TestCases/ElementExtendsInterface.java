package TestCases;

import Interfaces.ElementImpl;
import Utils.TestReporter;


public class ElementExtendsInterface implements ElementImpl {
	public void safeSetSecure() {
		System.out.println("Hi");
	}

	public void sendKeys() {
		System.out.println("Everyone");
	}

	public static void main(String[] args){
		ElementExtendsInterface mouseHover = new ElementExtendsInterface();
		mouseHover.safeSetSecure();
		mouseHover.sendKeys();
		//Sample TestReporter class implemented in the utility package
		TestReporter.logStep("Hi Everyone");
		TestReporter.logScenario("First TestCase");
	}

}


//public static WebDriver driver;
//public static String strURL = "http://www.onlinestore.toolsqa.com";
//public static void main(String[] args) throws ElementNotFoundException, Exception {
	// TODO Auto-generated method stub
	
	/*driver = new FirefoxDriver();
	driver.manage().window().maximize();
	driver.get(strURL);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
*/
	/*//Element for Link Product Category
	WebElement element = driver.findElement(By.linkText("Product Category"));
	ElementImpl.safeSetSecure();
	Actions actions = new Actions(driver);
	actions.moveToElement(element).build().perform();
	
	WebDriverWait wait = new WebDriverWait(driver, 10);
	try {
		wait.until(ExpectedConditions.elementToBeClickable(By.id("LOGIN"))).click();
	
	} catch (ElementNotFoundException e) {
		// TODO: handle exception
		System.out.println("The Login button was not found on the page");
		throw e;
	}catch (WebDriverException  e) {
		// TODO: handle exception
		System.out.println("Unknown WebDriver failure when interacting with the Login button. Please refer to stacktrace for more details");
		throw e;
	}
	
	driver.findElement(By.linkText("iPads")).click();*/