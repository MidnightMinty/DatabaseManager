package javaSQL;
import java.io.*; 
import java.sql.*;
import java.util.Scanner;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DatabaseManager {

	
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
		    
		    //Open a connection
			 Connection conn = DriverManager.getConnection(url, user, pass);
		    
		    //execute a query
		    //Statement stmt = conn.createStatement (); 
		    
		    		    
		    //InsertRep(conn, "26", "Gonzalez", "Edgar", "Wiltshire St.", "Bowling Green", "KY", "42101", "5000.0", "0.04" );
			 UpdateCreditLimit(conn, " Brookings Direct", 5000);
		    
		    //close the connection
		    conn.close();
		  }

	 public static void InsertRep(Connection conn, String repNum, String lastName, String firstName, String street, String city, String state, String postalCode, String commision, String rate) throws SQLException 
	 {
		 //Call stored procedure
		 String sql = "CALL InsertRep('"+ repNum +"', '"+ lastName +"', '"+ firstName +"', '"+ street +"', '"+ city +"', '"+ state +"', '"+ postalCode +"', '"+ commision +"', '"+ rate+ "')";
		 
		 //Open Connection
		 PreparedStatement stmt2 = conn.prepareStatement(sql); 
		 
		 //execute prepared statement
		 stmt2.executeQuery();
		 
		 System.out.println("Representative Number: " + repNum + " has been added to the table!");
		 
	 }
	 
	 public static void UpdateCreditLimit(Connection conn, String customerName, int creditLimit) throws SQLException 
	 {
		 //Call stored procedure
		 String sql = "CALL UpdateCredit('"+ creditLimit +"', '"+ customerName + "')";
		 
		 //Open Connection
		 PreparedStatement stmt2 = conn.prepareStatement(sql); 
		 
		 //execute prepared statement
		 stmt2.executeQuery();
		 
		 System.out.println("The new credit limit for " + customerName + " is " + creditLimit);
		 
	 }
	 
	 public static void AverageBalanceReport()
	 { 
		 
	 }
	 
	 public static void QuotedPriceReport()
	 {  
		 
	 }
	 
	 public static void SystemExit() 
	 {
		 
	 }

}
