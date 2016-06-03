package utils.dataFactory.database.databaseImpl;

import utils.dataFactory.database.Database;

public class SQLServerDatabase extends Database {
	public SQLServerDatabase(String environment, String host, String port, String dbName){
		setDbDriver("org.gjt.mm.mysql.Driver");
		setDbConnectionString("jdbc:mysql://" + host + ":" + port + "/"+ dbName);
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

