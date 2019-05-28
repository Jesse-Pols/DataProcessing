package casus.p2.v2.dao;

import java.util.ArrayList;

import casus.p2.v2.interfaces.OvChipkaartDao;
import casus.p2.v2.pojo.OvChipkaart;
import casus.p2.v2.pojo.Reiziger;

public class OvChipkaartOracleDaoImpl extends OracleBaseDao implements OvChipkaartDao {
	
	// Find OvChipkaart
	public OvChipkaart find(int kaartNummer) {
		System.out.println("Trying to find kaart: " + kaartNummer);
		
		ReizigerOracleDaoImpl rodi = null;
		
		OvChipkaart ovChipkaart = null;
		Reiziger reiziger = null;
		
		try {
			ps = dbConnection.prepareStatement(
					"SELECT geldigtot, klasse, saldo, reizigerid FROM ov_chipkaart WHERE kaartnummer=?");
			ps.setInt(1, kaartNummer);
			rs = ps.executeQuery();
			if (rs.isBeforeFirst()) rodi = new ReizigerOracleDaoImpl();
		} catch (Exception e)
		{ e.printStackTrace(); }
		
		try {
			while (rs.next()) {
				ovChipkaart = new OvChipkaart(
						kaartNummer,
						rs.getString(1),
						rs.getInt(2),
						rs.getDouble(3)
						);
				reiziger = rodi.find(rs.getInt(4));
				ovChipkaart.setReiziger(reiziger);
			}
		} catch (Exception e)
		{ e.printStackTrace(); }
		
		try { ps.close(); rs.close(); } catch (Exception e)
		{ e.printStackTrace(); }
		
		return ovChipkaart; 	
		
	}
	
	
	// Get all OvChipkaarten
	public ArrayList<OvChipkaart> findAll(){
		System.out.println("Trying to find all OvChipkaarten...");
		
		ArrayList<OvChipkaart> ovChipkaarten = new ArrayList<OvChipkaart>();
		ArrayList<Reiziger> reizigers = new ArrayList<Reiziger>();
		
		ReizigerOracleDaoImpl rodi = null;
		
		try {
			ps = dbConnection.prepareStatement(
					"SELECT kaartnummer, geldigtot, klasse, saldo, reizigerid FROM ov_chipkaart");
			rs = ps.executeQuery();
			if (rs.isBeforeFirst()) rodi = new ReizigerOracleDaoImpl();
		} catch (Exception e)
		{ e.printStackTrace(); }
		
		try {
			while (rs.next()) {
				OvChipkaart ovChipkaart = new OvChipkaart(
						rs.getInt(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getFloat(4)						
						);
				reizigers = rodi.findAll();
				for (Reiziger reiziger : reizigers) {
					if (reiziger.getReizigerId() == rs.getInt(5))
						ovChipkaart.setReiziger(reiziger);
				}
				
				ovChipkaarten.add(ovChipkaart);
			}
		} catch (Exception e)
		{ e.printStackTrace(); }
		
		try { ps.close(); rs.close(); } catch (Exception e)
		{ e.printStackTrace(); }
		
		return ovChipkaarten;		
	}
	
	// Get all OvChipkaarten connected to this Reiziger
	public ArrayList<OvChipkaart> findByReiziger(Reiziger reiziger){
		System.out.println("Trying to find OvChipkaarten by Reiziger: " + reiziger.getReizigerId());
		
		ArrayList<OvChipkaart> ovChipkaarten = new ArrayList<OvChipkaart>();
		
		try {
			ps = dbConnection.prepareStatement(
					"SELECT kaartnummer, geldigtot, klasse, saldo FROM ov_chipkaart WHERE reizigerid=?");
			ps.setInt(1, reiziger.getReizigerId());
			rs = ps.executeQuery();			
		} catch (Exception e)
		{ e.printStackTrace(); }
		
		try {
			while (rs.next()) {
				OvChipkaart ovChipkaart = new OvChipkaart(
						rs.getInt(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getDouble(4)
						);
				ovChipkaart.setReiziger(reiziger);	
				ovChipkaarten.add(ovChipkaart);				
			}
		} catch (Exception e)
		{ System.out.println(e.getMessage()); e.printStackTrace(); }
		
		try { ps.close(); rs.close(); } catch (Exception e)
		{ System.out.println(e.getMessage()); e.printStackTrace(); }
		
		return ovChipkaarten;
		
	}
	
	public boolean save (OvChipkaart ovChipkaart) {
				
		try {
			ps = dbConnection.prepareStatement(
					"INSERT INTO ov_chipkaart VALUES (?, ?, ?, ?, ?)");
			ps.setInt(1, ovChipkaart.getKaartNummer());
			ps.setString(2, ovChipkaart.getGeldigTot());
			ps.setInt(3, ovChipkaart.getKlasse());
			ps.setDouble(4, ovChipkaart.getSaldo());
			ps.setInt(5, ovChipkaart.getReiziger().getReizigerId());
			
			ps.executeQuery();
			ps.close();
		} catch (Exception e)
		{ e.printStackTrace(); }
		
		return true;		
	}
	
	public boolean update (OvChipkaart ovChipkaart) {
		
		try {
			ps = dbConnection.prepareStatement(
					"UPDATE ov_chipkaart SET geldigtot=?, klasse=?, saldo=?, reizigerid=? WHERE kaartnummer=?");
			ps.setString(1, ovChipkaart.getGeldigTot());
			ps.setInt(2, ovChipkaart.getKlasse());
			ps.setDouble(3, ovChipkaart.getSaldo());
			ps.setInt(4, ovChipkaart.getReiziger().getReizigerId());
			ps.setInt(5, ovChipkaart.getKaartNummer());
			ps.executeQuery();
			ps.close();			
		} catch (Exception e)
		{ e.printStackTrace(); }
		
		return true;
		
	}
	
	public boolean delete (OvChipkaart ovChipkaart) {
		
		try {
			ps = dbConnection.prepareStatement(
					"DELETE FROM ov_chipkaart WHERE kaartnummer=?");
			ps.setInt(1, ovChipkaart.getKaartNummer());
			ps.executeQuery();
			ps.close();
		} catch (Exception e)
		{ e.printStackTrace(); }
		
		return true;
	}

}
