package utils.dataFactory.database.databaseImpl;

import org.testng.annotations.Test;

import utils.TestReporter;
import utils.dataFactory.database.Database;
import utils.dataFactory.database.Recordset;

public class DB2Database extends Database {
	private String dbGeneralUsername = "QAAUTO1";
	private String dbGeneralPassword = "Disney1";
	private String baseConnectIonString = "jdbc:as400://";
	private String baseDbDriver = "com.ibm.as400.access.AS400JDBCDriver";
	
	
	public DB2Database(String environment, String dbName){
		if(dbName != null && !dbName.isEmpty()){
			//Determine if the database connection is to be for DVC Wishes
			if(dbName.trim().replace("_", "").equalsIgnoreCase("dvcwishes")){
				switch(environment.toLowerCase()){
				case "sleepy":
					dbName = "AS400FO";
					break;
				case "grumpy":
					dbName = "AS400UO";
					break;
				case "bashful":
					dbName = "AS400BO";
					break;
				default:
					TestReporter.logFailure("The environment ["+environment+"] is not valid.");
					break;
				}	
			}
		}else{
			TestReporter.logFailure("A DB Name must be provided.");
		}
		
		setDbDriver(baseDbDriver);
		setDbConnectionString(baseConnectIonString + dbName);
		
		setDbUserName(dbGeneralUsername);
		setDbPassword(dbGeneralPassword);
	}

	@Override
	protected void setDbDriver(String driver) {
		super.strDriver = driver;	
	}

	@Override
	protected void setDbConnectionString(String connection) {
		super.strConnectionString = connection;
	}
}

