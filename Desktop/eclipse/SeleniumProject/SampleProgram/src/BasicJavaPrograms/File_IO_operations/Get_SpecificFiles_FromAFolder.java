package BasicJavaPrograms.File_IO_operations;

import java.io.File;
import java.io.FilenameFilter;

public class Get_SpecificFiles_FromAFolder {

	public static void main(String[] args){
	
		//Program to filter the files by file extensions and show the file names?
		//Description: Below example shows how to get specific files from a folder. 
		//Sometimes we need to pic only specific extensions from the given folder. 
		//Implement FilenameFilter class and override accept() method, and add your filter logic here. 
		//Pass this object to list() method to get specific file extensions.
		File file = new File("C:/Users/praveen/Documents/PaySlips_2015");
        String[] files = file.list(new FilenameFilter() {
             
          public boolean accept(File dir, String name) {
                if(name.toLowerCase().endsWith(".pdf")){
                    return true;
                } else {
                    return false;
                }
            }
        });
        
        for(String f:files){
            System.out.println(f);
        }
	}
}
