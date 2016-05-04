package utils;

import org.testng.Assert;

public class TestAssert extends Assert {
	
	static public void assertSoft(boolean success, String message, StringBuilder messages) {
		  if (!success) messages.append("\n"+message);
	}
	 
	static public void assertEmpty(StringBuilder sb) {
	  if (sb.length() > 0) {
	    throw new AssertionError(sb.toString());
	  }
	}

}

