package BasicJavaPrograms.File_IO_operations;

import java.io.File;

/**
 * @summary: Program to get list of all file names from a folder in java.
 * @author : Praveen varma, @version: Created 24-05-2016
 * @Descrition: Below example shows how to get list of all file names from the given folder. 
 * First create File object by passing folder path. 
 * Call list() method on file object to get list of file names in the folder.
 */
public class Get_ListofAllFileNames_fromGivenFolder {
	
	//Program to get list of all file names from a folder in java.
	public void getFileNamesFromGivenFolder(){
			File file = new File("C:/Users/praveen/Documents/SeleniumLibraries_Jarfiles");
			String[] fileList = file.list();
			for(String fileName: fileList){
				System.out.println(fileName);
			}
	}

	//Program to get list of all file objects from a folder in java.
	public void getFilesListFromGivenFolder(){
		File file = new File("C:/Users/praveen/Documents/QuieTrack Docs");
		String[] fileList = file.list();
		for(String fileName: fileList){
			System.out.println(fileName);
		}
    }
	
	public static void main(String[] args) {
		Get_ListofAllFileNames_fromGivenFolder getListOfFileNames = 
				new Get_ListofAllFileNames_fromGivenFolder();
		System.out.println("----------------------------------------------------------");
		getListOfFileNames.getFileNamesFromGivenFolder();
		System.out.println("----------------------------------------------------------");
		getListOfFileNames.getFilesListFromGivenFolder();
		System.out.println("----------------------------------------------------------");
	}

}
