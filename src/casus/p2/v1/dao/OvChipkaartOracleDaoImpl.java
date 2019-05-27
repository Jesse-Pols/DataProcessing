package casus.p2.v1.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import casus.p2.v1.interfaces.OvChipkaartDao;
import casus.p2.v1.pojo.OvChipkaart;
import casus.p2.v1.pojo.Reiziger;

public class OvChipkaartOracleDaoImpl extends OracleBaseDao implements OvChipkaartDao  {
	
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
	
	// Find Specific OvChipkaart in table by key
	public OvChipkaart findOvChipkaart(int kaartNummer) {		

		OvChipkaart ovChipkaart = null;
		
		String query = String.format("SELECT * FROM OV_CHIPKAART WHERE KAARTNUMMER=%d", kaartNummer);
		ResultSet rs = runQuery(query, true);		
		
		try {
			
			while (rs.next()) {
				
				ovChipkaart = new OvChipkaart(
						rs.getInt("KAARTNUMMER"),
						rs.getString("GELDIGTOT"),
						rs.getInt("KLASSE"),
						rs.getFloat("SALDO"),
						rs.getInt("REIZIGERID")						
						);
				
			}
			
		} catch (Exception e) { System.out.println(e.getMessage()); }
		
		this.closeStatement();
		
		query = String.format("SELECT * FROM REIZIGER WHERE REIZIGERID=%d", ovChipkaart.getReizigerId());
		rs = runQuery(query, true);
		
		try {
		
			while (rs.next()) {
				
				Reiziger reiziger = new Reiziger(
						rs.getInt("REIZIGERID"),
						rs.getString("VOORLETTERS"),
						rs.getString("TUSSENVOEGSEL"),
						rs.getString("ACHTERNAAM"),
						rs.getString("GEBORTEDATUM")						
						);
				ovChipkaart.setReiziger(reiziger);
				
			}
			
		} catch (Exception e) { System.out.println(e.getMessage()); }
		
		this.closeStatement();
		
		return ovChipkaart;
		
	}
	
	public List<OvChipkaart> findByReiziger(Reiziger reiziger) {
		
		int reizigerId = reiziger.getReizigerID();
		ArrayList<OvChipkaart> listOvChipkaart = new ArrayList<OvChipkaart>();
		
		String query = String.format("SELECT * FROM OV_CHIPKAART WHERE REIZIGERID=%d", reizigerId);
		ResultSet rs = runQuery(query, true);
		
		try {
			
			while (rs.next()) {
				
				listOvChipkaart.add(new OvChipkaart(
						rs.getInt("KAARTNUMMER"),
						rs.getString("GELDIGTOT"),
						rs.getInt("KLASSE"),
						rs.getFloat("SALDO"),
						rs.getInt("REIZIGERID")						
						));
				
			}
			
		} catch (SQLException e) { System.out.println(e.getMessage()); } 
		
		this.closeStatement();
		
		for(OvChipkaart ovChipkaart : listOvChipkaart) {
			
			query = String.format("SELECT * FROM REIZIGER WHERE REIZIGERID=%d", ovChipkaart.getReiziger());
			rs = runQuery(query, true);
			
			try {
				
				while (rs.next()) {
					
					reiziger = new Reiziger(
							rs.getInt("REIZIGERID"),
							rs.getString("VOORLETTERS"),
							rs.getString("TUSSENVOEGSEL"),
							rs.getString("ACHTERNAAM"),
							rs.getString("GEBORTEDATUM")						
							);
					ovChipkaart.setReiziger(reiziger);
					
				}
				
			} catch (Exception e) { System.out.println(e.getMessage()); }
			
			this.closeStatement();
			
		}
		
		return listOvChipkaart;
		
	}
	
	public boolean save(OvChipkaart ovChipkaart) {
		
		try {
			
			PreparedStatement queryOvChipkaart = dbConnection.prepareStatement(
					"INSERT INTO OV_CHIPKAART VALUES(?,?,?,?,?)");
			queryOvChipkaart.setInt(1, ovChipkaart.getKaartNummer());
			queryOvChipkaart.setString(2, ovChipkaart.getGeldigTot());
			queryOvChipkaart.setInt(3, ovChipkaart.getKlasse());
			queryOvChipkaart.setFloat(4, ovChipkaart.getSaldo());
			queryOvChipkaart.setInt(5, ovChipkaart.getReizigerId());
			queryOvChipkaart.executeUpdate();
			queryOvChipkaart.close();
			
		} catch (Exception e) { System.out.println(e.getMessage()); return false; }
		
		return true;
		
	}
	
	public boolean update(OvChipkaart ovChipkaart) {		
		
		try {
			
			PreparedStatement queryOvChipkaart = dbConnection.prepareStatement(
					"UPDATE OV_CHIPKAART SET GELDIGTOT=?, KLASSE=?, SALDO=?, REIZIGERID=? WHERE KAARTNUMMER=?");
			queryOvChipkaart.setString(1, ovChipkaart.getGeldigTot());
			queryOvChipkaart.setInt(2, ovChipkaart.getKlasse());
			queryOvChipkaart.setFloat(3, ovChipkaart.getSaldo());
			queryOvChipkaart.setInt(4, ovChipkaart.getReizigerId());
			queryOvChipkaart.setInt(5, ovChipkaart.getKaartNummer());
			queryOvChipkaart.executeUpdate();	
			queryOvChipkaart.close();
			
		} catch (Exception e) { System.out.println(e.getMessage()); return false; }
		
		return true;
		
	}
	
	public boolean delete(OvChipkaart ovChipkaart) {
		
		
		try {
			
			PreparedStatement queryOvChipkaart = dbConnection.prepareStatement(
					"DELETE FROM OV_CHIPKAART WHERE KAARTNUMMER=?");
			queryOvChipkaart.setInt(1, ovChipkaart.getKaartNummer());;
			queryOvChipkaart.executeUpdate();	
			queryOvChipkaart.close();
			
		} catch (Exception e) { System.out.println(e.getMessage()); return false; }
		
		return true;
		
	}	
}
