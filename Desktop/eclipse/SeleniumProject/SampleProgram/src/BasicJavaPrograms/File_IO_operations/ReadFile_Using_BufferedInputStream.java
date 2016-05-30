package BasicJavaPrograms.File_IO_operations;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @summary: Program to read file using BufferedInputStream.
 * @author praveen varma
 *
 */
public class ReadFile_Using_BufferedInputStream {

	@SuppressWarnings("deprecation")
	public static void main(String[] args){
		//Program to read a file using BufferedInputStream.
		//Below example shows how to read a file using BufferedInputStream.
		InputStream is = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		
		try {
			
			is = new FileInputStream("C:/Users/praveen/Desktop/sql.txt");
			bis = new BufferedInputStream(is);
			dis = new DataInputStream(bis);
			String temp = null;
			
			while((temp = dis.readLine())!=null){
				System.out.println(temp);
			}
		
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e1) {
			System.err.println(e1);
		}
	}
}
