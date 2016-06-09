package javaSystemProperties;

import org.testng.annotations.Test;

public class PrintSystemProperties  {
  
  @Test
  public void printSystemProperties() {
	 //prints java-version
	 String java_version = System.getProperty("java.version");
	 System.out.println("Java version ----> "+ java_version);
	 
	 //******************************************************************	 
	 //User related system properties
	 //******************************************************************
	 String baseDirectory = System.getProperty("user.dir");
	 System.out.println("Base-Directory/path ----> "+ baseDirectory);
	 
	 String user_name = System.getProperty("user.name");
	 System.out.println("User name ----> "+ user_name);
	 
	 String user_home = System.getProperty("user.home");
	 System.out.println("User directory path ----> "+ user_home);
	 
	 //******************************************************************
	 // OS related system properties
	 //******************************************************************
	 String os_version = System.getProperty("os.version");
	 System.out.println("Operating System version ----> "+os_version);
	 
	 String os_Name = System.getProperty("os.name");
	 System.out.println("Operating System name ----> "+os_Name);
	 
	 String os_architecture = System.getProperty("os.arch");
	 System.out.println("OS Architecture ----> "+os_architecture);
  }
  
}
