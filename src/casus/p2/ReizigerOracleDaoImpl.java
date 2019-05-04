package casus.p2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReizigerOracleDaoImpl extends OracleBaseDao implements ReizigerDao  {
	
	// Establish a statement
	Statement statement = null;
	
	// Basic Queries; If getter = true it will return a ResultSet
	public ResultSet runQuery(String query, boolean getter) {		
		
		ResultSet rs = null;
		
		try {
			
			this.statement = dbConnection.createStatement();
			if (getter) rs = this.statement.executeQuery(query);
			if (!getter) this.statement.executeQuery(query);
			System.out.println("\nQuery Successful--------------\n" + query);
			
		} catch (SQLException e) { System.out.println(e.getMessage()); }
		
		return rs;		
		
	}
	
	public void closeStatement() {
		
		try { if (statement != null) this.statement.close();				
		} catch (Exception e) {	System.out.println(e.getMessage());	}
		
	}
	
	public List<Reiziger> findAll(){
		
		ArrayList<Reiziger> listReizigers = new ArrayList<Reiziger>();
		
		String query = "SELECT * FROM REIZIGER";
		ResultSet rs = runQuery(query, true);
		
		try {
			
			while (rs.next()) {
				
				listReizigers.add(new Reiziger(
						rs.getInt("REIZIGERID"),
						rs.getString("VOORLETTERS"),
						rs.getString("TUSSENVOEGSEL"),
						rs.getString("ACHTERNAAM"),
						rs.getString("GEBORTEDATUM")						
						));	
				
			}
			
		} catch (Exception e) { System.out.println(e.getMessage()); }
		
		this.closeStatement();
		
		return listReizigers;		
		
	}	
	
	ArrayList<Reiziger> reizigers = new ArrayList<Reiziger>();
	
	// Get all reizigers with this gbdatum
	public List<Reiziger> findByGBdatum(String gbdatum){
		
		ArrayList<Reiziger> reizigersGBdatum = new ArrayList<Reiziger>();
		
		for (Reiziger single_reiziger : reizigers) {
			
			if (single_reiziger.getGBdatum().equals(Date.valueOf(gbdatum))) {
				reizigersGBdatum.add(single_reiziger);
			}
			
		}
		
		return reizigersGBdatum;

	}
	
	// Saves new reiziger to list
	public Reiziger save(Reiziger reiziger) {
		
		reizigers.add(reiziger);
		return reiziger;
		
	}
	
	// Updates oldReiziger to newReiziger
	public Reiziger update(Reiziger reiziger) {
		
		int index = reizigers.indexOf(reiziger);
		
		reizigers.set(index, reiziger);
		
		return reiziger;
		
	}
	
	// Deletes reiziger from reizigers
	public boolean delete(Reiziger reiziger) {
		
		for (int i = 0; i < reizigers.size(); i++) {
			
			if (reizigers.get(i).equals(reiziger)) {
				reizigers.remove(i);
			}
			
		}

		return false;
		
	}
	
}
