package casus.p2;

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
		
		query = String.format("SELECT * FROM OV_CHIPKAART WHERE REIZIGERID=%d", args);
		
		return listReizigers;		
		
	}	
	
	public List<Reiziger> findByGBdatum(String gbDatum){
		
		ArrayList<Reiziger> listReizigers = new ArrayList<Reiziger>();
		
		String query = String.format("SELECT * FROM REIZIGER WHERE GEBORTEDATUM='%s'", gbDatum);
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
		
		for (Reiziger reiziger : listReizigers) {
			
			ArrayList<OvChipkaart> listOvChipkaarten = new ArrayList<OvChipkaart>();
			
			query = String.format("SELECT * FROM OV_CHIPKAART WHERE REIZIGERID=%d", reiziger.getReizigerID());
			rs = runQuery(query, true);
			
			try {
				
				while (rs.next()) {
					
					listOvChipkaarten.add(new OvChipkaart(
							
							));
					
				}
				
			}
			
		}
		
		return listReizigers;		
		
	}
	
	public List<OvChipkaart> findAllOvchipkaarten(int reizigerId) {
		
		ArrayList<OvChipkaart> ovChipkaartList = new ArrayList<OvChipkaart>();
		String query = String.format("SELECT * FROM OV_CHIPKAART WHERE REIZIGERID=%d", reizigerId);
		ResultSet rs = runQuery(query, true);
		
		try {
			
			while (rs.next()) {
				
				ovChipkaartList.add();
				
			}
			
		}
		
	}
	
	public boolean save(Reiziger reiziger) {
		
		String query = String.format("INSERT INTO REIZIGER VALUES('%s', '%s', '%s', '%s', '%s')",
				reiziger.getReizigerID(),
				reiziger.getVoorLetters(),
				reiziger.getTussenVoegsel(),
				reiziger.getAchterNaam(),
				reiziger.getGBdatum());
		runQuery(query, false);
		return true;
		
	}
	
	public boolean update(Reiziger reiziger) {		
		
		String query = "UPDATE REIZIGER SET ";
		query += "VOORLETTERS='" + reiziger.getVoorLetters();
		query += "', TUSSENVOEGSEL='" + reiziger.getTussenVoegsel();
		query += "', ACHTERNAAM='" + reiziger.getAchterNaam();
		query += "', GEBORTEDATUM='" + reiziger.getGBdatum();
		query += "' WHERE REIZIGERID=" + reiziger.getReizigerID();

		runQuery(query, false);		
		return true;
		
	}
	
	public boolean delete(Reiziger reiziger) {
		
		String query = String.format("DELETE FROM REIZIGER WHERE REIZIGERID='%s'", 
				reiziger.getReizigerID());			
		runQuery(query, false);
		return true;
		
	}	
	
}
