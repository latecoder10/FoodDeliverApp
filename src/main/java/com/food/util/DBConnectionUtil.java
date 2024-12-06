package com.food.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {
	private static final String DATABASE_URL="jdbc:mysql://localhost:3306/fooddeliveryapp";
	private static final String DATABASE_USER="root";
	private static final String DATABASE_PASSWORD="root";
	
	
	public static Connection getConnection() {
			Connection connection=null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection =DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
				
			} catch (ClassNotFoundException|SQLException e) {
				e.printStackTrace();
			}
			
			return connection;
	}
}
