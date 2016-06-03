package selenium.common;

import org.testng.Assert;
import org.testng.Reporter;

import com.thoughtworks.selenium.SeleneseTestBase;

public class CustomVerification extends SeleneseTestBase{
	
private StringBuffer verificationErrors;


/**
 * constructor since it is public always return new StringBuffer Object
 */

	public CustomVerification()
	 {
         verificationErrors = new StringBuffer();
     }
	
	
	 /**
	   * Asserts that a condition is true. If it isn't,
	   * an AssertionError, with the given message, is thrown.
	   * @param condition the condition to evaluate
	   * @param message the assertion error message
	   */
	
	public void verifyTrue(Boolean b , String msg)
	{
		try
		{
			Assert.assertTrue(b.booleanValue());
	     }catch(Error e)
		{
			verificationErrors.append(e);
			Reporter.log(msg + "");
         }
    }
	
	/**
	   * Asserts that a condition is false. If it isn't,
	   * an AssertionError, with the given message, is thrown.
	   * @param condition the condition to evaluate
	   * @param message the assertion error message
	   */
	public void verifyFalse(Boolean b , String msg)
	{
		try
		{
			Assert.assertFalse(b.booleanValue());
	     }catch(Error e)
		{
			verificationErrors.append(e);
			Reporter.log(msg + "");
         }
     }
	
	/**
	   * Clear out the list of verification errors
	   * @param NA
	   * @param NA
	   */
	 public void clearVerificationErrors()
	 {
		 verificationErrors = new StringBuffer();
		 
	 }
	 
	 /**
	   * Assert that there were  no verification errors during the current test, failing immediately if any are found
	   * @param NA
	   * @param NA
	   */
	 
	
     public void checkForVerificationErrors()
	{
		String verificationErrorString  =verificationErrors.toString();
		clearVerificationErrors();
		if(!"".equals(verificationErrorString))
			fail(verificationErrorString);
      }
     
     
	
	
	

}


