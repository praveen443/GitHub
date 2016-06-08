package javaSystemProperties;

import org.testng.annotations.Test;

public class PrintSystemProperties  {
  
  @Test
  public void printSystemProperties() {
	 //prints java-version
	 String java_version = System.getProperty("java.version");
	 System.out.println("Java version ----> "+ java_version);
		 
	 //prints the project base directory
	 String baseDirectory = System.getProperty("user.dir");
	 System.out.println("Base-Directory/path ----> "+ baseDirectory);
	 
	 String user_name = System.getProperty("user.name");
	 System.out.println("User name ----> "+ user_name);
	 
	 String user_home = System.getProperty("user.home");
	 System.out.println("User directory path ----> "+ user_home);
	 
	 
  }
  
}
