//*********************************************************************************************
//--------------------------------   EXCEL UTILITY FUNCTIONS  ---------------------------------
//*********************************************************************************************

package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


//Purpose: Function to read the data from excel sheet and pick the random cell value
public class ExcelFunctions 
{	
	public static void ReadDataFromExcel(String PathOfExcelFile, String SheetName) throws IOException
    {
   	  
      try {
    			
	    	//Get the path of the excel file
	    	String PathOfExcelFile1 = System.getProperty("user.dir")+ Utility.Constant.FileName;
	    	System.out.println("Path of excel file is : "+PathOfExcelFile1);
	    			
	    	//Path_ExcelFile: C:\Users\praveen\Desktop\eclipse\MagentoApp\TestData\Magento_TestData.xlsx
	    	//Create an object of file class to open xlsx file 
			File file = new File(PathOfExcelFile1);
			
			//Create an object of Fileinputstream class to read excel file
			FileInputStream fileInputStream = new FileInputStream(file);
			
	        Workbook ReadExcelWorkbook = null;
			
			//Find the extension name of the excel file by spliting file name in substring and getting only extension name
			String ExtensionNameOfTheExcelFile = PathOfExcelFile1.substring(PathOfExcelFile1.indexOf("."));
			
			//Verifying whether the extension of the excel file is .xls or .xlsx		
			//Used if-Condition to check whether the file is .xls or .xlsx file 
			if (ExtensionNameOfTheExcelFile.equals(".xlsx"))
			{
				//If the extension of the file is .xlsx, then create an object of XSSF workbook class
				ReadExcelWorkbook = new XSSFWorkbook(fileInputStream);
				System.out.println("File extension is : .xlsx");
				
			} else if(ExtensionNameOfTheExcelFile.equals(".xls")) 
			{
				//If the extension of the file is .xls, then create an object of HSSF workbook class
				ReadExcelWorkbook = new HSSFWorkbook(fileInputStream);
				System.out.println("File extension is : .xls");
			}								
			
			//Read the excelsheet inside the workbook by its name
			Sheet ReadExcelSheet = ReadExcelWorkbook.getSheet(SheetName);
			
			//Find the number of rows in the excelsheet - "SearchWithOrderNo"
			int rowCount = ReadExcelSheet.getLastRowNum()-3;
			System.out.println("Total no. of rows in the sheet - "+SheetName+" is : "+rowCount);
			
			//Create a loop over all the rows of excel file to read it
			//Pick the random row number from the excel sheet
			 for (int i = 0; i < rowCount; i++) 
			 {
			       
				 //NOTE:We cannot directly print the numeric values from the excel sheet.
				 //For fetching the string value of a numeric or boolean or date cell, use DataFormatter instead.
				 //And according to the DataFormatter API
	
				 //DataFormatter contains methods for formatting the value stored in an Cell. This can be useful for reports and GUI presentations when you need to display data exactly as it appears in Excel. Supported formats include currency, SSN, percentages, decimals, dates, phone numbers, zip codes, etc.
				 //So, right way to show numeric cell's value is as following:
			        
			        //Create a loop to print cell values in a row
				 
			        Row row = ReadExcelSheet.getRow(i);
			        for (int j = 0; j < row.getLastCellNum(); j++)		        
			        {			 
			            //To Print excel data in console
			        	//I have created the DataFormatter using the default local
			        	DataFormatter formatter = new DataFormatter(); 
			        	Cell cell = ReadExcelSheet.getRow(i).getCell(0);
			        	String cellValue = formatter.formatCellValue(cell); //Returns the formatted value of a cell as a String regardless of the cell type.
			        	System.out.println(cellValue);
			        	CellReference cr = new CellReference("A1");
			        	XSSFRow row1 = (XSSFRow) ReadExcelSheet.getRow(cr.getRow());
			        	XSSFCell cell1 = row1.getCell(cr.getCol());
			        	switch (cell1.getCellType()) {
			        	    case XSSFCell.CELL_TYPE_NUMERIC:
			        	        System.out.println(cell1.getRawValue());
			        	        break;
			        	    case XSSFCell.CELL_TYPE_BOOLEAN:
			        	        System.out.println(cell1.getBooleanCellValue());
			        	        break;
			        	    case XSSFCell.CELL_TYPE_STRING:
			        	        System.out.println(cell1.getStringCellValue());
			        	        break;
			        	    default:
			        	        System.out.println(cell1.getRawValue());
			        	}
		             }
	             
		         }
		 
        	
   	    }catch (IOException e) 
	         {
	            e.printStackTrace();
             }
		
   }
    
}
    

    

