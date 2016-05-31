package TestCases;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.Test;

import Utility.TestReporter;
import au.com.bytecode.opencsv.CSVReader;

public class Read_CSV_FileUsingJava {
	public static String csvFilePath = "C://Users//praveen//Desktop//verifyFooterlinks.csv";
	
	@Test
	public void readDataFromCSVFile() throws IOException{
		TestReporter.log("<-------- Started the TestCase execution - Read_CSV_FileUsingJava. -------->");
		Read_CSV_FileUsingJava readCSVfile = new Read_CSV_FileUsingJava();
		readCSVfile.readContentFromCSVFile(csvFilePath);
		TestReporter.log("<-------- Ended the TestCase execution - Read_CSV_FileUsingJava. -------->");
	}
	
	/**
	 * @summary: Method to read the data/content from CSV file.
	 * @author: Praveen Namburi, @version: Created 31-05-2016.
	 */
	public void readContentFromCSVFile(String csvFilePath) throws IOException{

        //Loads the CSV File.
        @SuppressWarnings("resource")
	    CSVReader csvFileReader = new CSVReader(new FileReader(csvFilePath));
        
        //Take the content into list
        List<String[]> csvContentList = csvFileReader.readAll();
        System.out.println("Total rows which we have is "+csvContentList.size());
        
        //Create the Iterator Reference
        Iterator<String[]> csvContentIterator = csvContentList.iterator();
        while(csvContentIterator.hasNext()){
	    String[] strListValue = csvContentIterator.next();
		   for(int i=0; i<strListValue.length;i++){
		       System.out.println("Value is : "+strListValue[i]);
		   }
        }
    }

}
