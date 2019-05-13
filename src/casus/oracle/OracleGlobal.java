package casus.oracle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleGlobal extends OracleBaseDao {
	
	private Statement statement = null; 
	
	public ResultSet runQuery(String query, boolean getter) {
		
		ResultSet rs = null;
		
		try {
			
			this.statement = dbConnection.createStatement();
			if (getter) rs = this.statement.executeQuery(query);
			if (!getter) this.statement.executeQuery(query);
			System.out.println("\nQuery Successful----------------\n" + query);			
			
		} catch (SQLException e) { System.out.println("\n" + e.getMessage()); }
		
		return rs;
		
	}
	
	public void closeStatement() { 
		
		try { if (statement != null) this.statement.close();		
		} catch (Exception e) { System.out.println(e.getMessage()); }
		
	}

}
