/*package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import selenium.Constants;




public class DataProvider_ExcelSheet {
	
	public static String inputfile = "C:\\Selenium\\Test_data\\ManageRoomAssignment_Manual\\DataProvider.xls";
	public String outputfile = "C:\\Selenium\\Test_data\\ManageRoomAssignment_Manual\\DataProvider_output.xls";
	public  String path;
	public  FileInputStream fis = null;
	public  FileOutputStream fileOut =null;
	public String sheet_name ="Test Sheet";
	
	//Method will Return Cell Content need to pass Content and column Number 
	public String getTestData(String cellContent,int col_number ) throws Exception
	{
        
	    FileInputStream input = new FileInputStream(Constants.outputfile);
	  
	    HSSFWorkbook wb = new HSSFWorkbook(input);
	    HSSFSheet s = wb.getSheet(sheet_name);
	    
	     int row_no = findRow(s, cellContent);
	  
	     int col_no = findColumn(s, cellContent);
	  
        
	    
	    FileInputStream fi = new FileInputStream(Constants.outputfile);
		Workbook w = Workbook.getWorkbook(fi); 
		Sheet s1 = w.getSheet(sheet_name);
	   
		String path = System.getProperty("user.home");
	    
	    //Sheet s1 = w.getSheet("Test Data");
	    String testdata = s1.getCell(col_no+col_number,row_no).getContents(); 
	  
	    return testdata;
		
			
	}
	
	public String getCellContent(int row,int col_number ) throws Exception
	{
        FileInputStream fi = new FileInputStream(Constants.outputfile);
		Workbook w = Workbook.getWorkbook(fi); 
		Sheet s1 = w.getSheet(sheet_name);
        //Sheet s1 = w.getSheet("Test Data");
	    String testdata = s1.getCell(col_number,row).getContents(); 
	  
	    return testdata;
     }
	
	public String getCellContent_output(int row,int col_number ) throws Exception
	{
        FileInputStream fi = new FileInputStream(Constants.outputfile);
		Workbook w = Workbook.getWorkbook(fi); 
		Sheet s1 = w.getSheet(sheet_name);
        //Sheet s1 = w.getSheet("Test Data");
	    String testdata = s1.getCell(col_number,row).getContents(); 
	   
	    return testdata;
     }
	public int row_number(String cellContent) throws Exception
	{
         //String fileName = outputfile;
		  
	    InputStream input = new FileInputStream(Constants.outputfile);
	    HSSFWorkbook wb = new HSSFWorkbook(input);
	    HSSFSheet s = wb.getSheet(sheet_name);
	    int  row_no_content = findRow(s, cellContent);
	    
	    return row_no_content;
     }
	

	public int findRow(HSSFSheet sheet, String cellContent) {
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
                  
                    return row.getRowNum();  
                    }
                }
            }
        }               
        return 0;
    }
	
	 public int findColumn(HSSFSheet sheet, String cellContent) {
	        for (Row row : sheet) {
	            for (Cell cell : row) {
	                if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
	                    if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
	                    	 System.out.println("The String 11 " + cellContent + " Row Number is : " + row.getRowNum()); 
	                    	  System.out.println("The String 22 " + cellContent + " Column Number is : " + cell.getColumnIndex());
	                        return cell.getColumnIndex();  
	                    }
	                }
	            }
	        }               
	        return 0;
	    }	
		
    public int find (String cellContent) throws Exception{
   
  
    FileInputStream input = new FileInputStream(Constants.outputfile);
    HSSFWorkbook wb = new HSSFWorkbook(input);
    HSSFSheet s = wb.getSheet(sheet_name);
    int row_no = findRow(s, cellContent);
   
    int col_no = findColumn(s, cellContent);
   
    return row_no;
  	}
  	
    public  Sheet findsheet () throws Exception{
  		FileInputStream fi = new FileInputStream(Constants.outputfile);
  		Workbook w = Workbook.getWorkbook(fi); 
  		Sheet s = w.getSheet(sheet_name);
  		return s;
  	  	}
  	
   
    public  void writeDataintoExcel(int row , int column , String result) throws Exception{
	    
	 //removeCell(row , column);
	 FileInputStream file = new FileInputStream(new File(Constants.outputfile));
     HSSFWorkbook workbook = new HSSFWorkbook(file);
     HSSFSheet sheet1 = workbook.getSheet(sheet_name);
    
     Row row1 = sheet1.getRow(row);

     Cell cell = row1.createCell(column);
     cell.setCellValue(result);
     file.close();

     FileOutputStream outFile =new FileOutputStream(new File(Constants.outputfile));
     workbook.write(outFile);
     outFile.close();


}
 
     public  void removeCell(int row , int column) throws Exception{
	    
	 FileInputStream file = new FileInputStream(new File(Constants.outputfile));
     HSSFWorkbook workbook = new HSSFWorkbook(file);
     HSSFSheet sheet1 = workbook.getSheet(sheet_name);
    
     Row row1 = sheet1.getRow(row);

     Cell cell =row1.getCell(column);
     row1.removeCell(cell);
    

file.close();

FileOutputStream outFile =new FileOutputStream(new File(Constants.outputfile));
workbook.write(outFile);
outFile.close();


}
 
 
 public String getColumnData(String sheetName,int row_no,String cellContent) throws IOException
	{
		
		FileInputStream input = new FileInputStream(Constants.outputfile);
		  
	    HSSFWorkbook wb = new HSSFWorkbook(input);
	    HSSFSheet sheet = wb.getSheet(sheetName);
	   
	  
       for (Row row : sheet)
     {
          if(row.getRowNum()==row_no)
     	{
             for(int i= 0 ; i<=row.getLastCellNum();i++)
     		{
     		
     			Cell cell=null;
     	            cell = row.getCell(i);
     			if(cell==null)
     			{
     				return "";
     			
     			}else if(cell.getCellType()==Cell.CELL_TYPE_STRING)
     		  {
     			  System.out.println("Cell Number :["+i+"] ::::" +cell.getStringCellValue().trim());
     			  if(cell.getStringCellValue().trim().equalsIgnoreCase(cellContent))
     			  {  
     				int row_number =row.getRowNum()+1;
     		
     				if(sheet.getRow(row_number).getCell(i).getCellType()==Cell.CELL_TYPE_STRING)
     				    {
     					         String vop = sheet.getRow(row_number).getCell(i).getStringCellValue().trim();
     	        				
     	        				 return vop;
     	                }
                       else if(sheet.getRow(row_number).getCell(i).getCellType()==Cell.CELL_TYPE_NUMERIC)
     				  {
     			      
 	                     if(sheet.getRow(row_number).getCell(i).getCellType()==Cell.CELL_TYPE_NUMERIC)
 	                      { 	  
 	                         String vop = sheet.getRow(row_number).getCell(i).toString();
	        				    
	        				     return  vop.substring(0,1);
	        				  }
 	                   }
                     }
                 } 
     			  
               }
    
 }//if	
	
     }//for loop
     return null; 
     
	}	//method
 

	
}     
*/