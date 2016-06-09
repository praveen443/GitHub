package javaSystemProperties;

import java.util.Properties;

import org.testng.annotations.Test;

public class PrintSystemProperties  {
  
  @Test
  public void printSystemProperties() {
	
	 //******************************************************************
	 //JRE related system properties
	 //****************************************************************** 
	 //JRE version, e.g., 1.7.0_09.
	 String java_version = System.getProperty("java.version");
	 System.out.println("Java version ----> "+ java_version);
	 
	 //JRE home directory, e.g., “C:\Program Files\Java\jdk1.7.0_09\jre”.
	 String java_home = System.getProperty("java.home");
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
	 //the OS’s version, e.g., “6.1”.
	 String os_version = System.getProperty("os.version");
	 System.out.println("Operating System version ----> "+os_version);
	 
	 //the OS’s name, e.g., “Windows 7”.
	 String os_Name = System.getProperty("os.name");
	 System.out.println("Operating System name ----> "+os_Name);
	 
	 //the OS’s architecture, e.g., “x86”.
	 String os_architecture = System.getProperty("os.arch");
	 System.out.println("OS Architecture ----> "+os_architecture);
	 
	 //******************************************************************
	 // List all System properties
	 //******************************************************************
	 Properties pros = System.getProperties();
     pros.list(System.out);
     
     //******************************************************************
  }
  
}
