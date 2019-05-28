package casus.p3.v3.dao;

import java.util.ArrayList;

import casus.p3.v3.interfaces.ReizigerDao;
import casus.p3.v3.pojo.OvChipkaart;
import casus.p3.v3.pojo.Reiziger;

public class ReizigerOracleDaoImpl extends OracleBaseDao implements ReizigerDao {
	
	public ArrayList<Reiziger> findAll() {
		
		ArrayList<Reiziger> reizigers = new ArrayList<Reiziger>();
		podi = new ProductOracleDaoImpl();
		odoci = new OvChipkaartOracleDaoImpl();
		
		try {
			ps = dbConnection.prepareStatement(
					"SELECT reizigerid, voorletters, tussenvoegsel, achternaam, gebortedatum FROM reiziger");
			rs = ps.executeQuery();
			
		} catch (Exception e)
		{ System.out.println("ReizigerOracleDaoImpl/findAll() Failed: " + e.getMessage()); }
		
		try {
			while (rs.next()) {
				Reiziger reiziger = new Reiziger(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
				for (OvChipkaart ovChipkaart : odoci.findAllByReizigerId(reiziger.getReizigerId())) {
					if (ovChipkaart != null) reiziger.addOvChipkaart(ovChipkaart);
				}				
				reizigers.add(reiziger);
			}
			rs.close();
		} catch (Exception e)
		{ System.out.println("ReizigerOracleDaoImpl/findAll() Failed: " + e.getMessage()); }
		
		return reizigers;
		
	}
	
	public ArrayList<Reiziger> findByGbDatum (String gbDatum) {
		
		ArrayList<Reiziger> reizigers = new ArrayList<Reiziger>();
		podi = new ProductOracleDaoImpl();
		odoci = new OvChipkaartOracleDaoImpl();
		
		try {
			ps = dbConnection.prepareStatement(
					"SELECT reizigerid, voorletters, tussenvoegsel, achternaam, gebortedatum FROM reiziger WHERE gebortedatum=?");
			ps.setDate(1,  java.sql.Date.valueOf(gbDatum));
			rs = ps.executeQuery();
			
		} catch (Exception e)
		{ System.out.println("ReizigerOracleDaoImpl/findByGbDatum() Failed: " + e.getMessage()); }
		
		try {
			while (rs.next()) {
				Reiziger reiziger = new Reiziger(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
				for (OvChipkaart ovChipkaart : odoci.findAllByReizigerId(reiziger.getReizigerId())) {
					if (ovChipkaart != null) {
						reiziger.addOvChipkaart(ovChipkaart);
						ovChipkaart.setReiziger(reiziger);
					}
				}
				reizigers.add(reiziger);
			}
			rs.close();
		} catch (Exception e)
		{ System.out.println("ReizigerOracleDaoImpl/findByGbDatum() Failed: " + e.getMessage()); }
		
		return reizigers;
		
	}
	
	public Reiziger find(int reizigerId) {
		return find(reizigerId, false);
	}
	
	public Reiziger find(int reizigerId, boolean recurse) {
		
		Reiziger reiziger = null;
		podi = new ProductOracleDaoImpl();
		odoci = new OvChipkaartOracleDaoImpl();
		
		try {
			ps = dbConnection.prepareStatement(
					"SELECT reizigerid, voorletters, tussenvoegsel, achternaam, gebortedatum FROM reiziger WHERE reizigerid=?");
			ps.setInt(1, reizigerId);
			rs = ps.executeQuery();
			
		} catch (Exception e)
		{ System.out.println("ReizigerOracleDaoImpl/find() Failed: " + e.getMessage()); }
		
		try {
			while (rs.next()) {
				reiziger = new Reiziger(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
				for (OvChipkaart ovChipkaart : odoci.findAllByReizigerId(reiziger.getReizigerId(), recurse)) {
					if (ovChipkaart != null) {
						ovChipkaart.setReiziger(reiziger);
						reiziger.addOvChipkaart(ovChipkaart);
					}
				}
			}
		} catch (Exception e)
		{ System.out.println("ReizigerOracleDaoImpl/find() Failed: " + e.getMessage()); }
		
		return reiziger;
		
	}
	
	public void save(Reiziger reiziger) {
		
		podi = new ProductOracleDaoImpl();
		odoci = new OvChipkaartOracleDaoImpl();
		
		try {			
			ps = dbConnection.prepareStatement(
					"INSERT INTO reiziger VALUES (?, ?, ?, ?, ?)");
			ps.setInt(1, reiziger.getReizigerId());
			ps.setString(2,  reiziger.getVoorletters());
			ps.setString(3,  reiziger.getTussenvoegsel());
			ps.setString(4,  reiziger.getAchternaam());
			ps.setDate(5, reiziger.getGeboortedatum());			
			ps.executeQuery();
			
		} catch (Exception e)
		{ System.out.println("ReizigerOracleDaoImpl/save() Failed: " + e.getMessage()); }
		
	}

	public void update(Reiziger reiziger) {
		
		podi = new ProductOracleDaoImpl();
		odoci = new OvChipkaartOracleDaoImpl();
		
		try {
			ps = dbConnection.prepareStatement(
					"UPDATE reiziger SET voorletters=?, tussenvoegsel=?, achternaam=?, gebortedatum=? WHERE reizigerid=?");
			ps.setString(1,  reiziger.getVoorletters());
			ps.setString(2,  reiziger.getTussenvoegsel());
			ps.setString(3,  reiziger.getAchternaam());
			ps.setDate(4, reiziger.getGeboortedatum());
			ps.setInt(5, reiziger.getReizigerId());
			ps.executeQuery();
			
		} catch (Exception e)
		{ System.out.println("ReizigerOracleDaoImpl/update() Failed: " + e.getMessage()); }
	}

	public void delete(int reizigerId) {
		
		podi = new ProductOracleDaoImpl();
		odoci = new OvChipkaartOracleDaoImpl();
		
		try {
			ArrayList<OvChipkaart> ovChipkaarten = odoci.findAllByReizigerId(reizigerId);
			
			for (OvChipkaart ovChipkaart : ovChipkaarten)
				odoci.delete(ovChipkaart.getKaartNummer());			
		} catch (Exception e)
		{ System.out.println("ReizigerOracleDaoImpl/delete() Failed: " + e.getMessage()); }
		
		try {
			ps = dbConnection.prepareStatement(
					"DELETE FROM reiziger WHERE reizigerid=?");
			ps.setInt(1, reizigerId);
			ps.executeQuery();
			
		} catch (Exception e)
		{ System.out.println("ReizigerOracleDaoImpl/delete() Failed: " + e.getMessage()); }
		
	}

}
