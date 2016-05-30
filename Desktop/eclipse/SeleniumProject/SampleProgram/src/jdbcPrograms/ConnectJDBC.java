package jdbcPrograms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectJDBC {

	public static void  main(String[] args) throws  ClassNotFoundException, SQLException {                                                  
       
		//Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"        
        String dbUrl = "jdbc:mysql://localhost:3306/employeemgmt";                   

        //Database Username     
        String username = "root";   
         
        //Database Password     
        String password = "D1$NeY4321";             

        //Query to Execute      
        String query = "SELECT * FROM Persons WHERE PersonID='2';"; 
        
        /* SELECT * FROM Persons;
         * SELECT * FROM Persons WHERE PersonID!='2';
         * SELECT * FROM Persons WHERE PersonID='2';
         * INSERT INTO Persons ('PersonID','FirstName','LastName','Address','City') VALUES (5,'Ramu','Vegesna','Kukatpally','Hyderabad');
         */
        
        //Load mysql jdbc driver
        //Class.forName("com.mysql.jdbc.Driver");
        
        //Create Connection to DB
        Connection connectionObj = DriverManager.getConnection(dbUrl, username, password);
         
        if (connectionObj != null) {
            System.out.println("Connected to the database - 'employeemgmt'");
        }
        
        //Create Statement Object
        Statement statementObj = connectionObj.createStatement();

        // Execute the SQL Query. Store results in ResultSet    
        ResultSet resultSet = statementObj.executeQuery(query);
        
       while (resultSet.next()) {
			String personID = resultSet.getString(1);
			String firstName = resultSet.getString(2);
			String lastName = resultSet.getString(3);
			String address = resultSet.getString(4);
			String city = resultSet.getString(5);
			
			System. out.println(personID+"  "+firstName+" "+lastName+" "+address+" "+city);
		}
        
        // closing DB Connection       
        connectionObj.close();
            
}
}
