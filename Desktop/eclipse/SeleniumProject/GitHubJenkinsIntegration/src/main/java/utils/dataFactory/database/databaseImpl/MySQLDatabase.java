package utils.dataFactory.database.databaseImpl;

import utils.dataFactory.database.Database;

public class MySQLDatabase extends Database {
	public MySQLDatabase(String environment, String host, String port, String dbName){
		setDbDriver("com.microsoft.jdbc.sqlserver.SQLServerDriver");
		setDbConnectionString("jdbc:microsoft:sqlserver://" + host + ":" + port + "[;DatabaseName="+ dbName + "]");		
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
