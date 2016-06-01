package Utils;
public class Datatable {

	public static int getCurrentScenario() {
			int currentScenarioRow = 0;
			int listScenarios[] = null;
			@SuppressWarnings("null")
			int scenario = listScenarios[currentScenarioRow];
			currentScenarioRow++;
			return scenario;
		}
}	
/*package Utils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Datatable {



	import composite.core.WebDriverSetup;
	import utils.dataFactory.VirtualTable;
	import utils.dataFactory.ResortInfo.ResortColumns;
	import utils.dataFactory.database.Recordset;

		//public static String LILO_MASTER_DATA_PATH = "Z:\\Automation\\Selenium\\Data\\Page Scenarios\\Lilo\\Lilo_Master_Data.xlsx";
		//public static String LILO_MASTER_DATA_PATH = "com/disney/composite/apps/lilo/masterdata/Lilo_Master_Data.xlsx";
		private static String tdmURL = "QAAUTO_";
		public static String LILO_MASTER_DATA_PATH = "MASTER_DATA_LILO_";
		public static String DREAMS_MASTER_DATA_PATH = "MASTER_DATA_DREAMS_";
		//public static String ALC_MASTER_DATA_PATH = "Z:\\Automation\\Selenium\\Data\\Page Scenarios\\ALC\\ALC_Master_Data.xlsx";
		public static String ALC_MASTER_DATA_PATH = "MASTER_DATA_ALC_";
		public static String DVC_MASTER_DATA_PATH = "MASTER_DATA_DVC_";
		public static String FDO_MASTER_DATA_PATH = "MASTER_DATA_FDO_";
		
		//private static Recordset virtualTable = null;
	//	private static String virtualTablePath;
		//private static String virtualTablePage; 
	//	private static String virtualTableScenario;
		
		private static String datatablePath;
		private static String datatableSheet;
		private static int datatableRow;
	//	private static Object[][] datatableData;

		private static XSSFSheet ExcelWSheet;
		private static XSSFWorkbook ExcelWBook;
		private static XSSFCell Cell;
	//	private static XSSFRow Row;

		private static String[] listScenarios;
	//	private static String currentScenario = "";
		private static int currentScenarioRow = 0;
		
		public static String virtualTableForQuickSearch;	//DJS - need separate virtual table for certain scenarios


		private static Recordset getInfo(String table, String searchTest){
			return VirtualTable.compileJSON(tdmURL + virtualTablePath + table, new VirtualTable().getRows(tdmURL + virtualTablePath+ table, "Scenario", searchTest ));
		}
		
		public static void setVirtualtablePath(String path){
			virtualTablePath = path;
		}
		
		public static void setVirtualtablePage(String page){
			virtualTablePage = page;
		}
		
		public static void setVirtualtableScenario(String scenario){
			virtualTableScenario = scenario;
			virtualTable = getInfo(virtualTablePage, scenario);
		}
		
		public static String getDataParameter(String field) {
			return virtualTable.getValue(field);
		}
		public static Object[][] getTestScenarios(String sheetName) {
			Recordset rs = VirtualTable.compileXML(tdmURL + "UI_TEST_SCENARIO_DATA_"+WebDriverSetup.getApplicationUnderTest().toUpperCase()+"_" + sheetName, new VirtualTable().getAllTestRows(tdmURL + "UI_TEST_SCENARIO_DATA_LILO_"+ sheetName));
			rs.print();
			return removeRowsWithRowNumber(rs.getArray(), 0);
		}
		
		*//**
		 * Get the scenarios for an application from the specified table.  Restrict the scenarios to the matching environment or 
		 * for scenarios with "All" or no environment specified
		 * 
		 * @param application
		 * @param tableName
		 * @param environment
		 * @return scenarios to test
		 *//*
		public static Object[][] getTestScenariosByAppEnvironment(String application, String tableName, String environment) {
			if (application.isEmpty()) throw new RuntimeException("The Application is blank");
			if (tableName.isEmpty()) throw new RuntimeException("The Table name is blank");
			Recordset rs = VirtualTable.compileXML(tdmURL + "UI_TEST_SCENARIO_DATA_"+application.toUpperCase()+"_" + tableName, new VirtualTable().getAllTestRows(tdmURL + "UI_TEST_SCENARIO_DATA_"+application.toUpperCase()+"_" +  tableName));
			rs.print();
			return getRowsWithThisEnvironment(rs, environment);
		}
		
		public static Object[][] getTestScenariosByApp(String application, String tableName) {
			if (application.isEmpty()) throw new RuntimeException("The Application is blank");
			if (tableName.isEmpty()) throw new RuntimeException("The Table name is blank");
			Recordset rs = VirtualTable.compileXML(tdmURL + "UI_TEST_SCENARIO_DATA_"+application.toUpperCase()+"_" + tableName, new VirtualTable().getAllTestRows(tdmURL + "UI_TEST_SCENARIO_DATA_"+application.toUpperCase()+"_" +  tableName));
			rs.print();
			return removeRowsWithRowNumber(rs.getArray(), 0);
		}
		
		public static Object[][] getTestScenariosByTbl(String application, String tableName) {
			if (application.isEmpty()) throw new RuntimeException("The Application is blank");
			if (tableName.isEmpty()) throw new RuntimeException("The Table name is blank");
			Recordset rs = VirtualTable.compileXML(tdmURL + "UI_TEST_SCENARIO_DATA_"+application.toUpperCase()+"_" + tableName, new VirtualTable().getAllTestRows(tdmURL + "UI_TEST_SCENARIO_DATA_"+application.toUpperCase()+"_" +  tableName));
			//rs.print();
			return removeRowsWithRowNumber(rs.getArray(), 0);
		}
		
		public static Object[][] getEpicTestScenarios(String tableName) {
			if (tableName.isEmpty()) throw new RuntimeException("The Table name is blank");
			Recordset rs = VirtualTable.compileXML(tdmURL + "UI_TEST_SCENARIO_DATA_EPIC"+"_" + tableName, new VirtualTable().getAllTestRows(tdmURL + "UI_TEST_SCENARIO_DATA_EPIC"+"_" +  tableName));
			rs.print();
			return removeRowsWithRowNumber(rs.getArray(), 0);
		}
		
		private static Object[][] getRowsWithThisEnvironment(Recordset rs, String environment) {
			List<Object[]> rowsToKeep = new ArrayList<Object[]>(rs.getRowCount());
	        for( int i =0; i<rs.getRowCount()+1; i++){
	        	String env = rs.getValue("environment", i).toLowerCase();
	        	if (env.trim().isEmpty() || env.equals("all") || env.equals(environment.toLowerCase())) { 
	            	Object[] row = rs.getArray()[i];
	            	rowsToKeep.add(row);
	            }
	        }

	        Object[][] array= new Object[rowsToKeep.size()][];
	        for(int i=0; i < rowsToKeep.size(); i++)
	        {
	        	array[i] = rowsToKeep.get(i);
	        }
	        return array;
		}
		private static  Object[][] removeRowsWithRowNumber(Object[][] array, double rowNotToBeAdd)
		    {
		        List<Object[]> rowsToKeep = new ArrayList<Object[]>(array.length);
		        for( int i =0; i<array.length; i++){
		            if(i!=rowNotToBeAdd){
		            	Object[] row = array[i];
		            rowsToKeep.add(row);
		            }
		        }

		        array= new Object[rowsToKeep.size()][];
		        for(int i=0; i < rowsToKeep.size(); i++)
		        {
		        	array[i] = rowsToKeep.get(i);
		        }
		        return array;
		    }

		public static String getDataParameter(String sheet, String scenario,
			String field) {
			setVirtualtablePage(sheet);
			setVirtualtableScenario(scenario);
			return virtualTable.getValue(field);
		}

		public static void setDatatablePath(String path) {
			datatablePath = path;
		}

		public static void setDatatableSheet(String sheet) {
			datatableSheet = sheet;
			Datatable.setExcelFile();
		}

		public static void setDatatableRow(int row) {
			datatableRow = row;
			Datatable.setTableInfo();
		}

		private void setCurrentScenario(String scenario) {
			currentScenario = scenario;
		}

		private static void addScenarioList(String scenario) {
			if (listScenarios == null) {
				listScenarios = new String[1];
			} else {
				listScenarios = Arrays.copyOf(listScenarios,
						listScenarios.length + 1);
			}
			listScenarios[listScenarios.length - 1] = scenario;
		}
		
		public static int getCurrentScenarioRow(){

			return currentScenarioRow;
		}

		public static String getCurrentScenario() {
			String scenario = listScenarios[currentScenarioRow];
			currentScenarioRow++;
			return scenario;
		}

		public static Object[][] getTableArray(String SheetName, int iTestCaseRow) {
			String[][] tabArray = null;
			try {
				FileInputStream ExcelFile = new FileInputStream(datatablePath);
				// Access the required test data sheet
				ExcelWBook = new XSSFWorkbook(ExcelFile);
				ExcelWSheet = ExcelWBook.getSheet(SheetName);

				int startCol = 1;
				int ci = 0, cj = 0;
				int totalRows = 1;
				int totalCols = ExcelWSheet.getRow(iTestCaseRow).getLastCellNum() - 1;
				tabArray = new String[totalRows][totalCols];
				for (int j = startCol; j <= totalCols; j++, cj++) {
					if (j == startCol)
						addScenarioList(getCellData(iTestCaseRow, j - 1));
					tabArray[ci][cj] = getCellData(iTestCaseRow, j);
					System.out.println(tabArray[ci][cj]);
				}

			} catch (FileNotFoundException e) {
				System.out.println("Could not read the Excel sheet");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Could not read the Excel sheet");
				e.printStackTrace();
			}
			return (tabArray);
		}

		public static Object[][] getTestScenarios(String FilePath, String SheetName) {

			String[][] tabArray = null;
			if(!FilePath.contains("Z:")){
				// Get the file location from the project main/resources folder
				FilePath =  Datatable.class.getResource(FilePath).getPath();
		
				// in case file path has a %20 for a whitespace, replace with actual
				// whitespace
				FilePath = FilePath.replace("%20", " ");
			}
			try {

				FileInputStream ExcelFile = new FileInputStream(FilePath);

				// Access the required test data sheet

				ExcelWBook = new XSSFWorkbook(ExcelFile);

				ExcelWSheet = ExcelWBook.getSheet(SheetName);

				int startRow = 1;

				int startCol = 1;

				int ci, cj;

				int totalRows = ExcelWSheet.getLastRowNum();

				// you can write a function as well to get Column count

				int totalCols = ExcelWSheet.getRow(startRow).getLastCellNum() - 1;

				tabArray = new String[totalRows][totalCols];

				ci = 0;

				for (int i = startRow; i <= totalRows; i++, ci++) {
					cj = 0;
					System.out.println("Test Scenario: " + getCellData(i, 1));
					for (int j = startCol; j <= totalCols; j++, cj++) {
						if (j == startCol)
							addScenarioList(getCellData(i, j - 1));
						tabArray[ci][cj] = getCellData(i, j);
						System.out.println(getCellData(0,j) + ": " + tabArray[ci][cj]);
					}
					System.out.println("");
				}
			} catch (FileNotFoundException e) {
				System.out.println("Could not read the Excel sheet");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Could not read the Excel sheet");
				e.printStackTrace();
			}
			return (tabArray);
		}

		public static Object[][] getTestScenarios(String FilePath,
				String SheetName, int rowToRun) {

			String[][] tabArray = null;

			try {

				FileInputStream ExcelFile = new FileInputStream(FilePath);

				// Access the required test data sheet

				ExcelWBook = new XSSFWorkbook(ExcelFile);

				ExcelWSheet = ExcelWBook.getSheet(SheetName);

				int startRow = 1;

				int startCol = 1;

				int ci, cj;

				// int totalRows = ExcelWSheet.getLastRowNum();

				// you can write a function as well to get Column count

				int totalCols = ExcelWSheet.getRow(startRow).getLastCellNum() - 1;

				tabArray = new String[1][totalCols];

				ci = 0;

				// for (int i=startRow;i<=totalRows;i++, ci++) {
				cj = 0;
				System.out.println("Test Scenario: " + getCellData(rowToRun, 1));
				for (int j = startCol; j <= totalCols; j++, cj++) {
					if (j == startCol)
						addScenarioList(getCellData(rowToRun, j - 1));
					tabArray[ci][cj] = getCellData(rowToRun, j);
					System.out.println(tabArray[ci][cj]);
				}
				System.out.println("");
			}
			// }
			catch (FileNotFoundException e) {
				System.out.println("Could not read the Excel sheet");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Could not read the Excel sheet");
				e.printStackTrace();
			}
			return (tabArray);
		}

		// This method is to read the test data from the Excel cell, in this we are
		// passing parameters as Row num and Col num
		public static String getCellData(int RowNum, int ColNum) {
			try {
				Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
				String CellData = Cell.getStringCellValue();
				return CellData;
			} catch (Exception e) {
				return "";
			}
		}

		public static String getTestCaseName(String sTestCase) {
			String value = sTestCase;
			try {
				int posi = value.indexOf("@");
				value = value.substring(0, posi);
				posi = value.lastIndexOf(".");
				value = value.substring(posi + 1);
				return value;
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}

		public static int getRowContains(String sTestCaseName, int colNum) {
			int i;
			try {
				int rowCount = Datatable.getRowUsed();
				for (i = 0; i < rowCount; i++) {
					if (Datatable.getCellData(i, colNum).equalsIgnoreCase(
							sTestCaseName)) {
						break;
					}
				}
				return i;
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}

		public static int getRowUsed() {
			try {
				int RowCount = ExcelWSheet.getLastRowNum();
				return RowCount;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw new RuntimeException(e.getMessage());
			}

		}

		// This method is to set the File path and to open the Excel file, Pass
		// Excel Path and Sheetname as Arguments to this method
		public static void setExcelFile() {
			try {
				// Open the Excel file
				FileInputStream ExcelFile = new FileInputStream(datatablePath);
				// Access the required test data sheet
				ExcelWBook = new XSSFWorkbook(ExcelFile);
				ExcelWSheet = ExcelWBook.getSheet(datatableSheet);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}

		public static void setExcelFile(String SheetName) {
			try {
				// Open the Excel file
				FileInputStream ExcelFile = new FileInputStream(datatablePath);
				// Access the required test data sheet
				ExcelWBook = new XSSFWorkbook(ExcelFile);
				ExcelWSheet = ExcelWBook.getSheet(SheetName);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}

		public static void setTableInfo() {
			String[][] tabArray = null;
			try {
				FileInputStream ExcelFile = new FileInputStream(datatablePath);
				// Access the required test data sheet
				ExcelWBook = new XSSFWorkbook(ExcelFile);
				ExcelWSheet = ExcelWBook.getSheet(datatableSheet);
				XSSFFormulaEvaluator.evaluateAllFormulaCells(ExcelWBook);

				int startCol = 1;
				int totalCols = ExcelWSheet.getRow(datatableRow).getLastCellNum() - 1;

				tabArray = new String[totalCols][2];
				for (int col = startCol; col <= totalCols; col++) {
					ExcelWSheet.getRow(datatableRow).getCell(col);

					tabArray[col - 1][0] = getCellData(0, col);
					if (ExcelWSheet
							.getRow(datatableRow)
							.getCell(
									col,
									ExcelWSheet.getRow(datatableRow).CREATE_NULL_AS_BLANK)
							.getCellType() == Cell.CELL_TYPE_NUMERIC) {
						ExcelWSheet
								.getRow(datatableRow)
								.getCell(col)
								.setCellType(
										ExcelWSheet.getRow(datatableRow).getCell(
												col).CELL_TYPE_STRING);
						tabArray[col - 1][1] = String.valueOf(ExcelWSheet.getRow(
								datatableRow).getCell(col));
					} else if (ExcelWSheet
							.getRow(datatableRow)
							.getCell(
									col,
									ExcelWSheet.getRow(datatableRow).CREATE_NULL_AS_BLANK)
							.getCellType() == Cell.CELL_TYPE_FORMULA) {
						ExcelWSheet
								.getRow(datatableRow)
								.getCell(col)
								.setCellType(
										ExcelWSheet.getRow(datatableRow).getCell(
												col).CELL_TYPE_FORMULA);
						XSSFCell cell = ExcelWSheet.getRow(datatableRow).getCell(
								col);
						tabArray[col - 1][1] = String.valueOf(cell
								.getStringCellValue());

					} else if (ExcelWSheet
							.getRow(datatableRow)
							.getCell(
									col,
									ExcelWSheet.getRow(datatableRow).CREATE_NULL_AS_BLANK)
							.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
						ExcelWSheet
								.getRow(datatableRow)
								.getCell(col)
								.setCellType(
										ExcelWSheet.getRow(datatableRow).getCell(
												col).CELL_TYPE_STRING);
						tabArray[col - 1][1] = String.valueOf(ExcelWSheet.getRow(
								datatableRow).getCell(col));
					} else if (ExcelWSheet.getRow(datatableRow).getCell(col)
							.getCellType() == Cell.CELL_TYPE_BLANK) {
						tabArray[col - 1][1] = "";

					} else {

						tabArray[col - 1][1] = getCellData(datatableRow, col);
					}

					System.out.println(tabArray[col - 1][0] + " | "
							+ tabArray[col - 1][1]);

				}

			} catch (FileNotFoundException e) {
				System.out.println("Could not read the Excel sheet");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Could not read the Excel sheet");
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			//datatableData = tabArray;
		}


		
		public static String getDataParameter(String field) {
			String value = "";
			for (int row = 0; row < datatableData.length; row++) {
				if (field.equals(datatableData[row][0].toString())) {
					value = datatableData[row][1].toString();
					break;
				}
			}
			return value;
		}

		public static String getDataParameter(String sheet, String scenario,
				String field) {
			String value = "";
			setDatatableSheet(sheet);
			setDatatableRow(Datatable.getRowContains(scenario, 0));
			for (int row = 0; row < datatableData.length; row++) {
				if (field.equals(datatableData[row][0].toString())) {
					value = datatableData[row][1].toString();
					break;
				}
			}
			return value;
		}
		
	}

*/