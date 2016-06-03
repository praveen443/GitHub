package utils.dataFactory.database.databaseImpl;

import utils.dataFactory.database.Database;

public class OracleDatabase extends Database {
	private String dbGeneralUsername = "APPDEV_RO";
	private String dbGeneralPassword = "APPDEV_RO#1";
	private String dbGoMasterUsername = "ods_sync_ro";
	private String dbGoMasterPassword = "ods_sync_ro5";
	private String dbMcUsername = "MC";
	private String dbMcPassword = "mcadmin";
	
	public OracleDatabase(String environment, String tnsName){
		switch (environment.toLowerCase()) {
		case "snowwhite":
			environment = "SNOW_WHITE";
			break;
		case "evilqueen":
			environment = "EVIL_QUEEN";
			break;
		default:
			break;
		}
		setDbDriver("oracle.jdbc.driver.OracleDriver");
		setDbConnectionString("jdbc:oracle:thin:@" + environment.toUpperCase() + "." + tnsName.toUpperCase());
		
		switch(tnsName.toLowerCase().replace("_", "")){
		case "dreams":
			setDbUserName(dbGeneralUsername);
			setDbPassword(dbGeneralPassword);
			break;
		case "dreamslog":
			setDbUserName(dbGeneralUsername);
			setDbPassword(dbGeneralPassword);
			break;
		case "sales":
			setDbUserName(dbGeneralUsername);
			setDbPassword(dbGeneralPassword);
			break;
		case "recommender":
			setDbUserName(dbGeneralUsername);
			setDbPassword(dbGeneralPassword);
			break;
		case "eailog":
			setDbUserName(dbGeneralUsername);
			setDbPassword(dbGeneralPassword);
			break;
		case "gomaster":
			setDbUserName(dbGoMasterUsername);
			setDbPassword(dbGoMasterPassword);
			break;
		case "dvcpoints":
			setDbUserName(dbGeneralUsername);
			setDbPassword(dbGeneralPassword);
			break;
		case "celebrations":
			setDbUserName(dbMcUsername);
			setDbPassword(dbMcPassword);
			break;
		}
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

