package utils.dataFactory;

import utils.dataFactory.database.Recordset;

public class ResortInfo {
    
	//private static String tableName = "Metadata_Resorts";
	private static String tableName = "QAAUTO_METADATA_RESORTS";
	
	
	public enum ResortColumns{
		RESORT_NAME{ public String toString() {return "RESORT_NAME";}},
		MINI_CAMPUS{ public String toString() {return "MINI_CAMPUS";}},
		LOCATION_ID{ public String toString() {return "LOCATION_ID";}},
		FACILITY_ID{ public String toString() {return "FACILITY_ID";}},
		FACILITY_CD{ public String toString() {return "FACILITY_CD";}},
		RESORT_CODE{ public String toString() {return "RESORT_CODE";}},
		TRANSACTION_CENTER_ID{ public String toString() {return "TRANSACTION_CENTER_ID";}},
		BANKING_ACCOUNTING_CENTER_ID{ public String toString() {return "BANKING_ACCOUNTING_CENTER_ID";}},
		SOURCE_ACCOUNTING_CENTER_ID{ public String toString() {return "SOURCE_ACCOUNTING_CENTER_ID";}},
		RESORT_CATEGORY{ public String toString() {return "RESORT_CATEGORY";}},
		DVC_RESORT{ public String toString() {return "DVC_RESORT";}},
		CAMPUS_ID{ public String toString() {return "CAMPUS_ID";}},
	}

	private static Recordset getInfo(String table, ResortColumns using, String searchTest){
		return VirtualTable.compileJSON(table, new VirtualTable().getRows(table, using.toString(), searchTest ));
	}
	
	public static String getLocationID(ResortColumns using, String info){
		return getResortInfo(using, info, ResortColumns.LOCATION_ID);
	}
	
	public static String getFacilityID(ResortColumns using, String info){
		return getResortInfo(using, info, ResortColumns.FACILITY_ID);
	}
	
	public static String getFacilityCD(ResortColumns using, String info){
		return getResortCode(using, info);
	}

	public static String getResortCode(ResortColumns using, String info){
		return getResortInfo(using, info, ResortColumns.RESORT_CODE);
	}
	public static String getTransactionAccountCenter(ResortColumns using, String info){
		return getResortInfo(using, info, ResortColumns.TRANSACTION_CENTER_ID);
	}
	
	public static String getBankingAccountCenter(ResortColumns using, String info){
		return getResortInfo(using, info, ResortColumns.BANKING_ACCOUNTING_CENTER_ID);
	}
	
	public static boolean isDVCResort(String resort){
		Recordset resorts = getInfo(tableName, ResortColumns.MINI_CAMPUS, resort );
		boolean isDVC = false;
		if (resorts.getValue(ResortColumns.DVC_RESORT.toString()).equalsIgnoreCase("Y")) isDVC = true;
		return isDVC;
	}
	
	public static String getSourceAccountingCenterId(ResortColumns using, String info){
		return getResortInfo(using, info, ResortColumns.SOURCE_ACCOUNTING_CENTER_ID);
	}
	
	public static String getResortInfo(ResortColumns using, String info, ResortColumns find){
		String searchText = "";
		Recordset resorts =null;
		switch (using){
		case RESORT_NAME:
			resorts = getInfo(tableName, ResortColumns.RESORT_NAME, info);
			break;
		case MINI_CAMPUS:
			resorts = getInfo(tableName, ResortColumns.MINI_CAMPUS, info);
			break;
		case LOCATION_ID:
			resorts = getInfo(tableName, ResortColumns.LOCATION_ID, info);
			break;
		case FACILITY_ID:
			resorts = getInfo(tableName, ResortColumns.FACILITY_ID, info);
			break;
		case TRANSACTION_CENTER_ID:
			resorts = getInfo(tableName, ResortColumns.TRANSACTION_CENTER_ID, info);
			break;
		case BANKING_ACCOUNTING_CENTER_ID:
			resorts = getInfo(tableName, ResortColumns.BANKING_ACCOUNTING_CENTER_ID, info);
			break;
		case SOURCE_ACCOUNTING_CENTER_ID:
			resorts = getInfo(tableName, ResortColumns.SOURCE_ACCOUNTING_CENTER_ID, info);
			break;
		case DVC_RESORT:
			resorts = getInfo(tableName, ResortColumns.DVC_RESORT, info);
			break;
		case RESORT_CATEGORY:
			resorts = getInfo(tableName, ResortColumns.RESORT_CATEGORY, info);
			break;
		case CAMPUS_ID:
			resorts = getInfo(tableName, ResortColumns.CAMPUS_ID, info);
			break;
		}
		
		return resorts.getValue(find.toString());
	}
}


