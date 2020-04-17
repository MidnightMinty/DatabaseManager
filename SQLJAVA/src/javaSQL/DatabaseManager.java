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
		    
		    System.out.println("Hello, Welcome to Database Manager by Edgar");
		    System.out.print("user_name : ");
		    user = user_input.next();
		    //System.out.println("password : ");
		    pass = "";
		    url = "jdbc:mysql://localhost:3306/tal";

		    
		    //Open a connection
			 Connection conn = DriverManager.getConnection(url, user, pass);
			
			 while(true){
			 System.out.println("Please select from these 4 options");
			 System.out.println("Insert Representative(1)");
			 System.out.println("Update Client Credit Limit(2)");
			 System.out.println("Average Balance Report(3)");
			 System.out.println("Quoted Price Report(4)");
			 System.out.println("Exit(5)");
				 
			 int choice = user_input.nextInt();
			 switch(choice) {
			  case 1:
				  InsertRep(conn, "26", "Gonzalez", "Edgar", "Wiltshire St.", "Bowling Green", "KY", "42101", "5000.0", "0.04" );
				  break;
			  case 2:
				  UpdateCreditLimit(conn, " Brookings Direct", 5000);       
			      break;
			  case 3:
				  AverageBalanceReport(conn);    
				  break;
			  case 4:
				  QuotedPriceReport(conn, "Toys Galore");    
			      break;
			  case 5:
			    //close the connection
			  	System.out.println("Thank You for Using My Service!");
			  	SystemExit();
			    conn.close();
			    user_input.close();
			    break;
			  default:	    
				  break;
			 	}
			 } 

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
	 
	 public static void AverageBalanceReport(Connection conn) throws SQLException
	 { 
		 //Call stored procedure
		 String sql = "CALL AverageValueReport()";
		 
		 //Open Connection
		 PreparedStatement stmt2 = conn.prepareStatement(sql); 
		 
		 //execute prepared statement
		 ResultSet rset = stmt2.executeQuery(sql);
			  
	     //precess the query result
		 while (rset.next ()) {
			       				 
		 System.out.println (rset.getString ("LastName")+  "  " + rset.getString("FirstName")+  "  " + rset.getString("numCustomer")+  "  " + rset.getString("avgBalance"));
					 
		 } 
	 }
	 
	 public static void QuotedPriceReport(Connection conn, String name) throws SQLException
	 {  
		 //Call stored procedure
		 String sql = "CALL QuotedPriceReport('"+ name +"')";
		 
		 //Open Connection
		 PreparedStatement stmt2 = conn.prepareStatement(sql); 
		 
		 //execute prepared statement
		 ResultSet rset = stmt2.executeQuery(sql);
			  
	     //precess the query result
		 while (rset.next ()) {
			       				 
		 System.out.println ("The total Quoted Price is: " + rset.getString("TOTALQUOTEDPRICE") + " for " + name);
		 } 
	 }
	 
	 public static void SystemExit() 
	 {
		 System.exit(0);
	 }

}
