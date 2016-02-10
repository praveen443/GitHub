//package WithoutTestNG_TestCases;

/*import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hslf.model.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.testng.annotations.DataProvider;
*/
//package WithoutTestNG_TestCases;
//
//import java.io.IOException;
//
//public class ReadDataFromExcelSheet 
//{
//
//	//public static String PathOfExcelFile1;
//
//	public static void main(String[] args) throws IOException 
//	{		
//			
//		//Calling function to read the data from excel sheet and pick the random cell value
//		//Utility.ExcelFunctions.ReadDataFromExcel( PathOfExcelFile1, Utility.Constant.SheetName);			
//	}
//	
//}

/*public class ReadDataFromExcelSheet 
{

	public  String sheetName="Sheet1";
	
	
	@DataProvider(name="empLogin")
	public Object[][] loginData() {
		Object[][] arrayObject = getExcelData("D:/sampledoc.xls","Sheet1");
		return arrayObject;
	}
	
public String[][] getExcelData(String fileName, String sheetName) 
{
		String[][] arrayExcelData = null;
		try {
			FileInputStream fs = new FileInputStream(fileName);
			Workbook wb = Workbook.getWorkbook(fs);
			Sheet sh = wb.getSheet(sheetName);

			int totalNoOfCols = sh.getColumns();
			int totalNoOfRows = sh.getRows();
			
			arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];
			
			for (int i= 1 ; i < totalNoOfRows; i++) {

				for (int j=0; j < totalNoOfCols; j++) {
					arrayExcelData[i-1][j] = sh.getCell(j, i).getContents();
				}

			}
		} catch(FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} 
		return arrayExcelData;
	}
}
*/