package BasicJavaPrograms.File_IO_operations;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @summary: Program to write string content to a file in java.
 * @author praveen varma,@version: Created 24-05-2016
 */
public class WriteStringContent_ToAFile {

	public static void main(String[] args){
		//Program to write string content to a file in java
		BufferedWriter bufferedWriter = null;
		try {
			String strContent = "This example shows how to write string content to a file.";
			File file = new File("C:/Users/praveen/Desktop/sql.txt");
			
			// check if file exist, otherwise create the file before writing
            if (!file.exists()) {
                file.createNewFile();
            }
            
            Writer writer = new FileWriter(file);
            bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(strContent);
            
		} catch (IOException e) {
			System.err.println(e);
			
		} finally{
			try {
				if(bufferedWriter!=null) bufferedWriter.close();
			} catch (Exception e2) {
				System.err.println(e2);
			}
		}
	}
}
