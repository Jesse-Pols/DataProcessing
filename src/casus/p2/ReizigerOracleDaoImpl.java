package casus.p2;

import java.sql.PreparedStatement;
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
		
		for (Reiziger reiziger : listReizigers) {
			
			ArrayList<OvChipkaart> listOvChipkaarten = new ArrayList<OvChipkaart>();
			
			query = String.format("SELECT * FROM OV_CHIPKAART WHERE REIZIGERID=%d", reiziger.getReizigerID());
			rs = runQuery(query, true);
			
			try {
				
				while (rs.next()) {
					
					listOvChipkaarten.add(new OvChipkaart(
							rs.getInt("KAARTNUMMER"),
							rs.getString("GELDIGTOT"),
							rs.getInt("KLASSE"),
							rs.getFloat("SALDO"),
							rs.getInt("REIZIGERID")
							));
					
				}
				
			} catch (Exception e) { System.out.println(e.getMessage()); }
			
		}
		
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
							rs.getInt("KAARTNUMMER"),
							rs.getString("GELDIGTOT"),
							rs.getInt("KLASSE"),
							rs.getFloat("SALDO"),
							rs.getInt("REIZIGERID")
							));
					
				}
				
			} catch (Exception e) { System.out.println(e.getMessage()); }
			
		}
		
		return listReizigers;		
		
	}
	
	public boolean save(Reiziger reiziger) {
		
		try {
			
			PreparedStatement queryReiziger = dbConnection.prepareStatement(
					"INSERT INTO REIZIGER VALUES(?,?,?,?,?)");
			queryReiziger.setInt(1, reiziger.getReizigerID());
			queryReiziger.setString(2, reiziger.getVoorLetters());
			queryReiziger.setString(3, reiziger.getTussenVoegsel());
			queryReiziger.setString(4, reiziger.getAchterNaam());
			queryReiziger.setString(5, reiziger.getGBdatum());
			queryReiziger.executeUpdate();
			queryReiziger.close();
			
		} catch (Exception e) { System.out.println(e.getMessage()); return false; }
		
		return true;
		
	}
	
	public boolean update(Reiziger reiziger) {		
		
		try {
			
			PreparedStatement queryReiziger = dbConnection.prepareStatement(
					"UPDATE REIZIGER SET VOORLETTERS=?, TUSSENVOEGSEL=?, ACHTERNAAM=?, GEBORTEDATUM=? WHERE REIZIGERID=?");
			queryReiziger.setString(1, reiziger.getVoorLetters());
			queryReiziger.setString(2, reiziger.getTussenVoegsel());
			queryReiziger.setString(3, reiziger.getAchterNaam());
			queryReiziger.setString(4, reiziger.getGBdatum());
			queryReiziger.setInt(5, reiziger.getReizigerID());
			queryReiziger.executeUpdate();
			queryReiziger.close();
			
		} catch (Exception e) { System.out.println(e.getMessage()); return false; }
		
		return true;

	}
	
	public boolean delete(Reiziger reiziger) {
		
		
		try {
			
			PreparedStatement queryReiziger = dbConnection.prepareStatement(
					"DELETE * FROM REIZIGER WHERE REIZIGERID=?");
			queryReiziger.setInt(1, reiziger.getReizigerID());;
			queryReiziger.executeUpdate();	
			queryReiziger.close();
			
		} catch (Exception e) { System.out.println(e.getMessage()); return false; }
		
		return true;
		
	}	
	
}
