package utils;

import java.util.Hashtable;
import java.util.HashMap;


public class TestUtil {
	

	public static HashMap<String, String> store_data=new HashMap<String, String>();
	

	public static Object[][] getData(String testCase, Excel_Reader xls) {

		// row on which test is lying
		// rows of data
		// cols of data
		// extract data

		// row on which test is lying
		int testStartRowNum = 1;

		while (!xls.getCellData("Data", 0, testStartRowNum).equals(testCase)) {

			testStartRowNum++;

		}

		// System.out.println("Test Case "+testCase+" starts from row number "+
		// testStartRowNum);

		// rows of data

		int dataStartRowNum = testStartRowNum + 2;
		int rows = 0;

		while (!xls.getCellData("Data", 0, dataStartRowNum + rows).equals("")) {

			rows++;

		}

		// System.out.println("Total rows in test case "+testCase+" are "+
		// rows);

		// cols of data

		int colStartRowNum = testStartRowNum + 1;
		int cols = 0;

		while (!xls.getCellData("Data", cols, colStartRowNum).equals("")) {

			cols++;

		}

		// System.out.println("Total cols in test case "+testCase+" are "+
		// cols);

		Object testData[][] = new Object[rows][1];
		int index = 0;
		Hashtable<String, String> table = null;
		// extract data

		for (int rNum = dataStartRowNum; rNum < dataStartRowNum + rows; rNum++) {

			table = new Hashtable<String, String>();// init for every row

			for (int cNum = 0; cNum < cols; cNum++) {

				String key = xls.getCellData("Data", cNum, colStartRowNum);
				String value = xls.getCellData("Data", cNum, rNum);
				// System.out.print(value+"---");

				// fill the table
				table.put(key, value);
			}
			// System.out.println("");
			testData[index][0] = table;
			index++;
		}

		// System.out.println("*****************");

		return testData;

	}

	public static Object[][] getData1(String testCase, Excel_Reader xls) {

		// row on which test is lying
		// rows of data
		// cols of data
		// extract data

		// row on which test is lying
		int testRowNum = 1;

		while (!xls.getCellData("Data", 0, testRowNum).equals(testCase)) {

			testRowNum++;

		}

		Object testData1[][] = new Object[1][1];

		Hashtable<String, String> table = new Hashtable<String, String>();
		// extract data

		for (int colNum = 1; colNum < xls.getColumnCount("Data"); colNum++) {

			String key = xls.getCellData("Data", colNum, 1);

			String value = xls.getCellData("Data", colNum, testRowNum);

			table.put(key, value);

		}

		testData1[0][0] = table;

		return testData1;

	}

	public static Object[][] getData2(String testCase, Excel_Reader xls) {

		// row on which test is lying
		// rows of data
		// cols of data
		// extract data

		// row on which test is lying
		int testRowNum = 1;

		while (!xls.getCellData("Data1", 0, testRowNum).equals(testCase)) {

			testRowNum++;

		}

		// rows of data

		int dataStartRowNum = testRowNum;
		int rows = 0;

		while (!xls.getCellData("Data1", 1, dataStartRowNum + rows).equals("")) {

			rows++;

		}


		Object testData2[][] = new Object[rows][1];

		int index = 0;

		Hashtable<String, String> table = null;
		
		// extract data
	

		for (int rNum = dataStartRowNum; rNum < dataStartRowNum + rows; rNum++) {

			table = new Hashtable<String, String>();
			
			 String rowNum=Integer.toString(rNum);
			
			 table.put("rNum",rowNum);
		
			for (int colNum=1; colNum < xls.getColumnCount("Data1"); colNum++) {

				String key = xls.getCellData("Data1", colNum, 1);

				String value = xls.getCellData("Data1", colNum, rNum);

				table.put(key, value);
				
			}
			testData2[index][0] = table;
			index++;

		}

		return testData2;

	}

	public static Object[][] getData_MM(String testCase, Excel_Reader xls) {

		// row on which test is lying
		// rows of data
		// cols of data
		// extract data

		// row on which test is lying
		int testRowNum = 1;

		while (!xls.getCellData("TicketsValidations", 0, testRowNum).equals(
				testCase)) {

			testRowNum++;

		}
		
		// rows of data

		int dataStartRowNum = testRowNum+1;
		int rows = 0;

		while (!xls.getCellData("TicketsValidations", 0, dataStartRowNum + rows).equals("")) {

			rows++;

		}
		
		//System.out.println("No.of rows: "+rows);

		Object testData_MM[][] = new Object[rows][1];
		int index = 0;
		
		Hashtable<String, String> table = new Hashtable<String, String>();
		// extract data

		for (int colNum = 2; colNum < xls.getColumnCount("Web Transfer 5"); colNum++) {

			String key = xls.getCellData("Web Transfer 5", colNum, 5);

			String value = xls
					.getCellData("Web Transfer 5", colNum, testRowNum);

			table.put(key, value);

		}

		for (int rNum = dataStartRowNum; rNum < dataStartRowNum + rows; rNum++) {

			table = new Hashtable<String, String>();
			
			 String rowNum=Integer.toString(rNum);
			
			 table.put("rNum",rowNum);
		
			for (int colNum=0; colNum < xls.getColumnCount("TicketsValidations"); colNum++) {

				String key = xls.getCellData("TicketsValidations", colNum, testRowNum);

				String value = xls.getCellData("TicketsValidations", colNum, rNum);

				table.put(key, value);
				
			}
			testData_MM[index][0] = table;
			index++;

		}
		return testData_MM;

	}
	
}

