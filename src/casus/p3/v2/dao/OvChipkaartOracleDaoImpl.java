package casus.p3.v2.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import casus.p3.v2.interfaces.OvChipkaartDao;
import casus.p3.v2.pojo.OvChipkaart;
import casus.p3.v2.pojo.Product;
import casus.p3.v2.pojo.Reiziger;

public class OvChipkaartOracleDaoImpl extends OracleBaseDao implements OvChipkaartDao {	
	
	public OvChipkaart findByKaartNummer(int kaartNummer) {
		return findByKaartNummer(kaartNummer, true);
	}
	
	public OvChipkaart findByKaartNummer(int kaartNummer, boolean recurse) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		OvChipkaart ovChipkaart = null;
		Reiziger reiziger = null;
		
		try {
			
			ps = dbConnection.prepareStatement(
					"SELECT ovkaart.GELDIGTOT, ovkaart.KLASSE, ovkaart.SALDO, ovkaart.REIZIGERID, p.PRODUCTNUMMER "
					+ "FROM ov_chipkaart_product ovkaartproduct "
					+ "RIGHT OUTER JOIN ov_chipkaart ovkaart "
					+ "ON ovkaartproduct.KAARTNUMMER = ovkaart.KAARTNUMMER "
					+ "LEFT OUTER JOIN product p "
					+ "ON p.PRODUCTNUMMER = ovkaartproduct.PRODUCTNUMMER "
					+ "WHERE ovkaart.KAARTNUMMER=?");
			ps.setInt(1, kaartNummer);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				ReizigerOracleDaoImpl rodi = new ReizigerOracleDaoImpl();
				
				ovChipkaart = new OvChipkaart(kaartNummer, rs.getString(1), rs.getInt(2), rs.getDouble(3));
				reiziger = rodi.findById(rs.getInt(4), false);
				ovChipkaart.setReiziger(reiziger);
				
				if (recurse) {
					
					ProductOracleDaoImpl podi = new ProductOracleDaoImpl();
					Product product = podi.findByProductNummer(rs.getInt(5), false);
					if (product != null) {
						ovChipkaart.addProduct(product);
					}
					
				}
				
			}
			
			
		} catch (Exception e) { System.out.println("Error -> Couldn't find OVchipkaart By Kaartnummer: " + e.getMessage()); }
		finally	{ try { ps.close(); rs.close(); } catch (Exception e) { System.out.println(e.getMessage()); }};			
		
		return ovChipkaart;
		
	}
	
	public ArrayList<OvChipkaart> findByReizigerId(int reizigerId){
		return findByReizigerId(reizigerId, false);
	}
	
	public ArrayList<OvChipkaart> findByReizigerId(int reizigerId, boolean recurse) {
		
		ArrayList<OvChipkaart> ovChipkaarten = new ArrayList<OvChipkaart>();
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			
			ps = dbConnection.prepareStatement(
					"SELECT ovkaart.KAARTNUMMER, ovkaart.GELDIGTOT, ovkaart.KLASSE, ovkaart.SALDO, ovkaart.REIZIGERID, p.PRODUCTNUMMER "
					+ "FROM ov_chipkaart_product ovkaartproduct "
					+ "RIGHT OUTER JOIN ov_chipkaart ovkaart "
					+ "ON ovkaartproduct.KAARTNUMMER = ovkaart.KAARTNUMMER "
					+ "LEFT OUTER JOIN product p "
					+ "ON p.PRODUCTNUMMER = ovkaartproduct.PRODUCTNUMMER "
					+ "WHERE ovkaart.REIZIGERID=?");
			ps.setInt(1, reizigerId);
			rs = ps.executeQuery();		
			
			while (rs.next()) {
				
				boolean kaartExists = false;
				OvChipkaart ovChipkaart = null;
				
				ReizigerOracleDaoImpl rodi = new ReizigerOracleDaoImpl();
				ProductOracleDaoImpl podi = new ProductOracleDaoImpl();
				
				for (OvChipkaart tempOvChipkaart : ovChipkaarten) {
					if (rs.getInt(1) == tempOvChipkaart.getKaartNummer()) {
						ovChipkaart = tempOvChipkaart;
						kaartExists = true;
					}
				}
				
				if (!kaartExists) {
					ovChipkaart = new OvChipkaart(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4));
					
					if (recurse) {
					
						Reiziger reiziger = rodi.findById(rs.getInt(5), false);
						ovChipkaart.setReiziger(reiziger);
						
					}
					
					ovChipkaarten.add(ovChipkaart);
					
				}				
				
				Product product = podi.findByProductNummer(rs.getInt(6), false);
				if (product != null) ovChipkaart.addProduct(product);
				
			}
			
		} catch (Exception e) { System.out.println("Error -> Couldn't find OVchipkaart By ReizigerId: " + e.getMessage()); }
		finally	{ try { ps.close(); rs.close(); } catch (Exception e) { System.out.println(e.getMessage()); }};
		
		return ovChipkaarten;
		
	}
	
	
	
	
	
	
	/*
	
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
		
		System.out.println("OV Saved");
		return true;
		
	}
	
	public boolean delete(OvChipkaart ovChipkaart) {
		
		try {
			PreparedStatement deleteOvChipkaart = dbConnection.prepareStatement(
					"DELETE FROM OV_CHIPKAART WHERE KAARTNUMMER=?");
			
			deleteOvChipkaart.setInt(1, ovChipkaart.getKaartNummer());
			
			deleteOvChipkaart.executeUpdate();
			deleteOvChipkaart.close();			
		} catch (Exception e) { System.out.println("Error -> Couldn't delete OVchipkaart: " + e.getMessage()); return false; };
		
		System.out.println("Deleted OV");
		return true;
		
	}
	*/
	
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
