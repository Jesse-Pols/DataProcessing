package casus.p3.v3.dao;

import java.util.ArrayList;

import casus.p3.v3.interfaces.ReizigerDao;
import casus.p3.v3.pojo.OvChipkaart;
import casus.p3.v3.pojo.Reiziger;

public class ReizigerOracleDaoImpl extends OracleBaseDao implements ReizigerDao {
	
	public ArrayList<Reiziger> findAll() {
		System.out.println("Finding All Reizigers...");
		
		ArrayList<Reiziger> reizigers = new ArrayList<Reiziger>();
		OvChipkaartOracleDaoImpl odoci = new OvChipkaartOracleDaoImpl();
		
		try {
			ps = dbConnection.prepareStatement("SELECT reizigerid, voorletters, tussenvoegsel, achternaam, gebortedatum FROM reiziger");
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
		
		try { ps.close(); rs.close(); } catch (Exception e)
		{ System.out.println("Had Trouble Closing ps & rs: " + e.getMessage()); }
		
		return reizigers;
		
	}
	
	public ArrayList<Reiziger> findByGbDatum (String gbDatum) {
		System.out.println("Finding All Reizigers By GbDatum...");
		
		ArrayList<Reiziger> reizigers = new ArrayList<Reiziger>();
		OvChipkaartOracleDaoImpl odoci = new OvChipkaartOracleDaoImpl();
		
		try {
			ps = dbConnection.prepareStatement("SELECT reizigerid, voorletters, tussenvoegsel, achternaam, gebortedatum FROM reiziger WHERE gebortedatum=?");
			ps.setString(1, gbDatum);
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
		
		try { ps.close(); rs.close(); } catch (Exception e)
		{ System.out.println("Had Trouble Closing ps & rs: " + e.getMessage()); }
		
		return reizigers;
		
	}
	
	public Reiziger find(int reizigerId) {
		return find(reizigerId, false);
	}
	
	public Reiziger find(int reizigerId, boolean recurse) {
		System.out.println("Finding Reiziger...");
		
		Reiziger reiziger = null;
		OvChipkaartOracleDaoImpl odoci = new OvChipkaartOracleDaoImpl();
		
		try {
			ps = dbConnection.prepareStatement("SELECT reizigerid, voorletters, tussenvoegsel, achternaam, gebortedatum FROM reiziger WHERE reizigerId=?");
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
		
		try { ps.close(); rs.close(); } catch (Exception e)
		{ System.out.println("Had Trouble Closing ps & rs: " + e.getMessage()); }
		
		return reiziger;
		
	}
	
	public void save(Reiziger reiziger) {
		System.out.println("Saving Reiziger...");
		
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
		
		try { ps.close(); } catch (Exception e)
		{ System.out.println("Had Trouble Closing ps & rs: " + e.getMessage()); }
		
	}

	public void update(Reiziger reiziger) {
		System.out.println("Updating Reiziger...");
		
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
		
		try { ps.close(); } catch (Exception e)
		{ System.out.println("Had Trouble Closing ps & rs: " + e.getMessage()); }
	}

	public void delete(Reiziger reiziger) {
		System.out.println("Deleting Reiziger...");
		
		OvChipkaartOracleDaoImpl odoci = new OvChipkaartOracleDaoImpl();
		
		try {
			ArrayList<OvChipkaart> ovChipkaarten = odoci.findAllByReizigerId(reiziger.getReizigerId());
			
			for (OvChipkaart ovChipkaart : ovChipkaarten)
				odoci.delete(ovChipkaart.getKaartNummer());			
		} catch (Exception e)
		{ System.out.println("ReizigerOracleDaoImpl/delete() Failed: " + e.getMessage()); }
		
		try {
			ps = dbConnection.prepareStatement(
					"DELETE FROM reiziger WHERE reizigerid=?");
			ps.setInt(1, reiziger.getReizigerId());
			ps.executeQuery();
			
		} catch (Exception e)
		{ System.out.println("ReizigerOracleDaoImpl/delete() Failed: " + e.getMessage()); }
		
		try { ps.close(); } catch (Exception e)
		{ System.out.println("Had Trouble Closing ps & rs: " + e.getMessage()); }
		
	}

}
