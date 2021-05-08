package com.dbutils;
import java.sql.*;



public class CrudOperation 
{
private static Connection con;
public static Connection createConnection()
{
	try {
		Class.forName("com.mysql.jdbc.Driver");  //create the object of driver class
		                                         //factory method->create object
		
		con= DriverManager.getConnection("jdbc:mysql://localhost:3306/vehicle","root","root");   //connective URL
		
	//getConnection returns the reference variable of database

}
	catch(ClassNotFoundException| SQLException cse)
	{
	System.out.println(cse);	
	}
	return con;
	}
public static void main(String[] args) {
	Connection con=createConnection();
	System.out.println(con);
}


}