package com.home.telusko.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection conn = null;
	public static String url = "jdbc:mysql://localhost:3306/quiz";
	public static String username = "root";
	public static String password = "root";
	public static String driver = "com.mysql.cj.jdbc.Driver";
	
	public static Connection getConnecttio() {
		try {
			if(conn == null) {
				Class.forName(driver);
				conn =DriverManager.getConnection(url, username, password);
			}
		} catch (Exception e) {
			System.err.println(" Exception inside DBConnection.getConnecttio() "+ e);
		}
		return conn;
	}
	
	public static void main(String[] args) {
		Connection conn =  DBConnection.getConnecttio();
		System.out.println("Connection Establishment :: "+ conn);
	}
}
