package casus.p3.v2.dao;

import java.sql.PreparedStatement;

import casus.p3.v2.interfaces.ReizigerDao;
import casus.p3.v2.pojo.Reiziger;

public class ReizigerOracleDaoImpl extends OracleBaseDao implements ReizigerDao{
	
	public boolean save(Reiziger reiziger) {
		
		try {
			PreparedStatement insertReiziger = dbConnection.prepareStatement(
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
			
		} catch (Exception e) { System.out.println("Error -> Couldn't delete Reiziger: " + e.getMessage()); return false; };		
		
		System.out.println("Deleted Reiziger");
		return true;
		
	}

}
