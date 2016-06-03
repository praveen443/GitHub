package BasicJavaPrograms.File_IO_operations;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @summary: Program to read file content line by line in java.
 * @author praveen varma,@version: Created 24-05-2016
 * @Description: Description: Below example shows how to read file content line by line. 
 * To get this, you have to use BufferedReader object.
 * By calling readLine() method you can get file content line by line.
 * readLine() returns one line at each iteration, we have to iterate it till it returns null.
 */
public class ReadFileContent_LineByLine {

	public static void main(String[] args) {
		BufferedReader bufferReader = null;
		String strLine="";
		String fileName = "C:/Users/praveen/Desktop/sql.txt";
		
		try {
			  bufferReader = new BufferedReader(new FileReader(fileName));
			  while ((strLine = bufferReader.readLine())!=null) {
	                  System.out.println(strLine);
	          }
		} catch (FileNotFoundException e) {
			System.err.println("Unable to find the file: " + fileName + e);
		} catch (IOException e1) {
			System.err.println("Unable to find the file: " + fileName + e1);
		}
	}

}
