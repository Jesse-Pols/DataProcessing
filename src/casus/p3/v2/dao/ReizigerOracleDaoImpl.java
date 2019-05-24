package casus.p3.v2.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import casus.p3.v2.interfaces.ReizigerDao;
import casus.p3.v2.pojo.OvChipkaart;
import casus.p3.v2.pojo.Reiziger;

public class ReizigerOracleDaoImpl extends OracleBaseDao implements ReizigerDao{
	
	public Reiziger findById(int reizigerId) {
		return findById(reizigerId, false);
	}
	
	public Reiziger findById(int reizigerId, boolean recurse) {
		
		Reiziger reiziger = null;
		ArrayList<OvChipkaart> ovChipkaarten = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		OvChipkaartOracleDaoImpl odoci = null;
		
		try {
			
			ps = dbConnection.prepareStatement(
					"select * from REIZIGER where REIZIGERID=?");
			ps.setInt(1, reizigerId);
			rs = ps.executeQuery();
			if (rs.isBeforeFirst()) odoci = new OvChipkaartOracleDaoImpl();
			
		} catch (Exception e)
		{ System.out.println("Reiziger FindById -> Query Failed: " + e.getMessage()); e.printStackTrace(); }
		
		try {
		while (rs.next()) {			

				reiziger = new Reiziger(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				ovChipkaarten = odoci.findByReizigerId(rs.getInt(1), recurse);
				
				if (ovChipkaarten != null) {					
					for (OvChipkaart ovChipkaart : ovChipkaarten) {
						if (ovChipkaart != null) {
							ovChipkaart.setReiziger(reiziger);
							reiziger.addOvChipkaart(ovChipkaart);
						}						
					}					
				}		
		}
		} catch (Exception e)
		{ System.out.println(e.getMessage()); e.printStackTrace(); }
		
		try { ps.close(); rs.close(); }
		catch (Exception e) { System.out.println(e.getMessage()); e.printStackTrace(); }

		return reiziger;
		
	}
	
	/*
	PreparedStatement insertReiziger;
	PreparedStatement deleteReiziger;
	
	public Reiziger findById(int reizigerId) {
		
		Reiziger reiziger = null;
		
		try {
			PreparedStatement findReiziger = dbConnection.prepareStatement(
					"SELECT * FROM REIZIGER WHERE REIZIGERID=?");
			
			findReiziger.setInt(1, reizigerId);
			
			ResultSet rs = findReiziger.executeQuery();
			while (rs.next()) {
				reiziger = new Reiziger(
						rs.getInt("REIZIGERID"),
						rs.getString("VOORLETTERS"),
						rs.getString("TUSSENVOEGSEL"),
						rs.getString("ACHTERNAAM"),
						rs.getString("GEBORTEDATUM")
						);
			}
			
			rs.close();
			findReiziger.close();
		} catch (Exception e) { System.out.println("Error -> Couldn't find Reiziger: " + e.getMessage()); };
		
		if (reiziger != null) System.out.println("Reiziger Found");
		if (reiziger == null) System.out.println("Reiziger Not Found");
		return reiziger;
		
	}
	
	public boolean save(Reiziger reiziger) {
		
		try {
			insertReiziger = dbConnection.prepareStatement(
					"INSERT INTO REIZIGER VALUES(?,?,?,?,?)");
			
			insertReiziger.setInt(1, reiziger.getReizigerId());
			insertReiziger.setString(2, reiziger.getVoorletters());
			insertReiziger.setString(3, reiziger.getTussenvoegsel());
			insertReiziger.setString(4, reiziger.getAchternaam());
			insertReiziger.setString(5, reiziger.getGeboortedatum());
			
			insertReiziger.executeUpdate();
			insertReiziger.close();
		} catch (Exception e) { System.out.println("Error -> Couldn't save Reiziger: " + e.getMessage()); return false; };
		
		System.out.println("Inserted Reiziger");
		return true;
		
	}
	
	public boolean update(Reiziger reiziger) {
		
		try {
			PreparedStatement updateReiziger = dbConnection.prepareStatement(
					"UPDATE REIZIGER SET VOORLETTERS=?, TUSSENVOEGSEL=?, ACHTERNAAM=?, GEBORTEDATUM=? WHERE REIZIGERID=?");
			
			updateReiziger.setString(1, reiziger.getVoorletters());
			updateReiziger.setString(2, reiziger.getTussenvoegsel());
			updateReiziger.setString(3, reiziger.getAchternaam());
			updateReiziger.setString(4, reiziger.getGeboortedatum());
			updateReiziger.setInt(5, reiziger.getReizigerId());
						
			updateReiziger.executeUpdate();
			updateReiziger.close();
		} catch (Exception e) { System.out.println("Error -> Couldn't update Reiziger: " + e.getMessage()); return false; };
		
		System.out.println("Updated Reiziger");
		return true;
		
	}
	
	public boolean delete(Reiziger reiziger) {
		
		try {
			deleteReiziger = dbConnection.prepareStatement(
					"DELETE FROM REIZIGER WHERE REIZIGERID=?");
			
			deleteReiziger.setInt(1, reiziger.getReizigerId());
			
			deleteReiziger.executeUpdate();
			deleteReiziger.close();
		} catch (Exception e) { System.out.println("Error -> Couldn't delete Reiziger: " + e.getMessage()); return false; };		
		
		System.out.println("Deleted Reiziger");
		return true;
		
	}
	*/

}
