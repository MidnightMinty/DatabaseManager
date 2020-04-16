package javaSQL;
import java.io.*; 
import java.sql.*;
import java.util.Scanner;

public class SQLtest {

	
	 public static void main (String args [])
		      throws SQLException, IOException { 

		    try {
		      //register JDBC driver
		    	Class.forName ("com.mysql.cj.jdbc.Driver");
		    } catch (ClassNotFoundException e) {
		        System.out.println ("Cannot load the driver"); 
			     System.exit(1);  //abnormal termination
		      }

		    //get the user name and password for the database
		    String user, pass, url;
		    Scanner user_input = new Scanner( System.in );
		    		   		   
		    System.out.print("user_name : ");
		    user = user_input.next();
		    System.out.println("password : ");
		    pass = "";
		    url = "jdbc:mysql://localhost:3306/tal";
		    user_input.close();
		    
		    //OPen a connection
			 Connection conn = DriverManager.getConnection(url, user, pass);
		    
		    //execute a query
		    Statement stmt = conn.createStatement (); 
		   
		    String sql ="SELECT * from REP";
		   		    
		    ResultSet rset = stmt.executeQuery(sql);
		  
          //precess the query result
		    while (rset.next ()) {
		       				 
			 System.out.println (rset.getString ("LastName")+  "  " + rset.getString("FirstName"));
				 
		    } 
		    
		    //close the connection
		    conn.close();
		  }

		
}
