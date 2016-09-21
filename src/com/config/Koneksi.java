package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
	private final String myDriver = "com.mysql.jdbc.Driver";
	private final String myUrl = "jdbc:mysql://127.0.0.1:3306/db_pegawai";
	private final String username = "root";
	private final String password = "r00?00t";
	private Connection conn = null;
	
	public Connection getKoneksi(){
		try {
			Class.forName(myDriver);
			try {
				conn = DriverManager.getConnection(myUrl,username,password);
			} catch (SQLException e) {
				System.out.println("Sql error");
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not Ready");
			e.printStackTrace();
		}
		
		return conn;
	}
}
