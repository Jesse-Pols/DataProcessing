package casus.p3.v2.dao;

import java.sql.PreparedStatement;

import casus.p3.v2.interfaces.OvChipkaartDao;
import casus.p3.v2.pojo.OvChipkaart;

public class OvChipkaartOracleDaoImpl extends OracleBaseDao implements OvChipkaartDao {	
	
	public boolean save(OvChipkaart ovChipkaart) {
		
		try {			
			PreparedStatement insertOvChipkaart = dbConnection.prepareStatement(
					"INSERT INTO OV_CHIPKAART VALUES(?,?,?,?,?)");
			
			insertOvChipkaart.setInt(1, ovChipkaart.getKaartNummer());
			insertOvChipkaart.setString(2, ovChipkaart.getGeldigTot());
			insertOvChipkaart.setInt(3, ovChipkaart.getKlasse());
			insertOvChipkaart.setFloat(4, ovChipkaart.getSaldo());
			insertOvChipkaart.setInt(5, ovChipkaart.getReiziger().getReizigerId());
			
			insertOvChipkaart.executeUpdate();
			insertOvChipkaart.close();			
		} catch (Exception e) { System.out.println("Error -> Couldn't save OVchipkaart: " + e.getMessage()); return false; };
		
		return true;
		
	}
	
	/*
	
	public boolean update(OvChipkaart ovChipkaart) {
		
		try {
			PreparedStatement updateOvChipkaart = dbConnection.prepareStatement(
					"UPDATE OV_CHIPKAART SET GELDIGTOT=?, KLASSE=?, SALDO=?, REIZIGERID=? WHERE KAARTNUMMER=?");
			
		} catch (Exception e) { System.out.println("Error -> Couldn't update OVchipkaart: " + e.getMessage()); return false; };
		
	}
	
	public boolean delete(OvChipkaart ovChipkaart) {
		
		try {
			PreparedStatement deleteOvChipkaart = dbConnection.prepareStatement(
					"DELETE FROM OV_CHIPKAART WHERE KAARTNUMMER=?");			
			
		} catch (Exception e) { System.out.println("Error -> Couldn't delete OVchipkaart: " + e.getMessage()); return false; };
		
	}
	
	*/
	

}
