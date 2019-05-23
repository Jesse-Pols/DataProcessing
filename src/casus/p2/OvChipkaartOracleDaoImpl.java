package casus.p2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
		
		String query = String.format("INSERT INTO OV_CHIPKAART VALUES(%d, '%s', %d, %f, %d)",
				ovChipkaart.getKaartNummer(),
				ovChipkaart.getGeldigTot(),
				ovChipkaart.getKlasse(),
				ovChipkaart.getSaldo(),
				ovChipkaart.getReizigerId());
		runQuery(query, false);
		return true;
		
	}
	
	public boolean update(OvChipkaart ovChipkaart) {		
		
		String query = "UPDATE OV_CHIPKAART SET ";
		query += "GELDIGTOT='" + ovChipkaart.getGeldigTot();
		query += "', KLASSE=" + ovChipkaart.getKlasse();
		query += ", SALDO=" + ovChipkaart.getSaldo();
		query += ", REIZIGERID=" + ovChipkaart.getReizigerId();
		query += " WHERE KAARTNUMMER=" + ovChipkaart.getKaartNummer();
		runQuery(query, false);		
		return true;
		
	}
	
	public boolean delete(OvChipkaart ovChipkaart) {
		
		String query = String.format("DELETE FROM OV_CHIPKAART WHERE KAARTNUMMER=%d", 
				ovChipkaart.getKaartNummer());			
		runQuery(query, false);
		return true;
		
	}	
}
