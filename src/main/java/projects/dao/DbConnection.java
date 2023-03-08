package projects.dao;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;

import projects.exception.DbException;

public class DbConnection {
	private static  String HOST = "localhost";
	private static String PASSWORD = "projects";
	private static int PORT = 3306;
	private static String SCHEMA = "projects";
	private static String USER = "projects";
	
/////////////////////////////////////////////////////////		
/////////////////////////////////////////////////////////		
// 					create a method 
	public static Connection getConnection() {
// 		this method should also return a java.sql.connection object
/////////////////////////////////////////////////////////			
/////////////////////////////////////////////////////////		
//			Create a String variable named uri that contains the MySQL connection URI.
	
			String url = String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s",HOST, PORT, SCHEMA, USER, PASSWORD);
		
/////////////////////////////////////////////////////////		
/////////////////////////////////////////////////////////		
/////////////////////////////////////////////////////////		
/////////////////////////////////////////////////////////		
//			Call DriverManager to obtain a connection. Pass the connection string (URI)
//			to DriverManager.getConnection().Surround the call to DriverManager.getConnection()
//			with a try/catch block. The catch block should catch SQLException.
			System.out.println("Connecting with url=" + url);
			
			try {
			Connection conn = DriverManager.getConnection(url); 
			System.out.println("Sucessfully obtained connection!"); 
			return conn; 
			
			} catch (SQLException e) {
				System.out.println("Error"); 
				throw new DbException(e); 
		    }
				}	
/////////////////////////////////////////////////////////		
/////////////////////////////////////////////////////////			
//	Print a message to the console (System.out.println) if the connection is successful.
//	Print an error message to the console if the connection fails. Throw a DbException if the connection fails.
	
	
	
	



}
