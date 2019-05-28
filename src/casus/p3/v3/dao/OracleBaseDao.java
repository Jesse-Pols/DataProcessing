package casus.p3.v3.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class OracleBaseDao {
	
	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_USER = "C##OV_CASUS";
	private static final String DB_PASSWORD = "admin";
	
	Connection dbConnection = getConnection();
	
	OvChipkaartOracleDaoImpl odoci = null;
	ReizigerOracleDaoImpl rodi = null;
	ProductOracleDaoImpl podi = null;
	
	ResultSet rs = null;
	PreparedStatement ps = null;
	
	protected Connection getConnection(){
		
		Connection dbConnection = null;
		
		try { 
			
			Class.forName(DB_DRIVER);
			
		} catch (ClassNotFoundException e) { System.out.println(e.getMessage()); }
		
		try {
			
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;
			
		} catch (SQLException e) { System.out.println(e.getMessage()); }
		
		return dbConnection;		
		
	}
	
	public void closeConnection() {

		try {
			
			dbConnection.close();
			System.out.println("Connection Closed.");
			
		} catch (Exception e) { e.getMessage(); }
		
	}

}
