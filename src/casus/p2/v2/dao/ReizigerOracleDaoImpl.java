package casus.p2.v2.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import casus.p2.v2.interfaces.ReizigerDao;
import casus.p2.v2.pojo.OvChipkaart;
import casus.p2.v2.pojo.Reiziger;

public class ReizigerOracleDaoImpl extends OracleBaseDao implements ReizigerDao {
	
	public Reiziger find(int reizigerId) {
		System.out.println("Trying to find reiziger: " + reizigerId);
		
		Reiziger reiziger = null;
		ArrayList<OvChipkaart> ovChipkaarten = new ArrayList<OvChipkaart>();
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		OvChipkaartOracleDaoImpl odoci = null;
		
		try {
			ps = dbConnection.prepareStatement(
					"SELECT voorletters, tussenvoegsel, achternaam, gebortedatum FROM reiziger WHERE reizigerid=?");
			ps.setInt(1, reizigerId);
			rs = ps.executeQuery();
			if (rs.isBeforeFirst()) odoci = new OvChipkaartOracleDaoImpl();
		} catch (Exception e)
		{ e.printStackTrace(); }
		
		try {
			while(rs.next()) {
				reiziger = new Reiziger(
						reizigerId,
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4)
						);
				
				ovChipkaarten = odoci.findByReiziger(reiziger);
				reiziger.setOvChipkaarten(ovChipkaarten);
			}
		} catch (Exception e)
		{ e.printStackTrace(); }
		
		try { ps.close(); rs.close(); } catch (Exception e)
		{ e.printStackTrace(); }
		
		return reiziger;
		
	}
	
	// Get all Reizigers
	public ArrayList<Reiziger> findAll(){
		System.out.println("Trying to find all Reizigers...");
		
		ArrayList<Reiziger> reizigers = new ArrayList<Reiziger>();
		ArrayList<OvChipkaart> ovChipkaarten = new ArrayList<OvChipkaart>();
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		OvChipkaartOracleDaoImpl odoci = null;
		
		try {
			ps = dbConnection.prepareStatement(
					"SELECT reizigerid, voorletters, tussenvoegsel, achternaam, gebortedatum FROM reiziger");
			rs = ps.executeQuery();			
			if (rs.isBeforeFirst()) odoci = new OvChipkaartOracleDaoImpl();
		} catch (Exception e)
		{ System.out.println(e.getMessage()); e.printStackTrace(); }
		
		try {
			while (rs.next()) {
				reizigers.add(new Reiziger(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5)
						));
			}
		} catch (Exception e)
		{ System.out.println(e.getMessage()); e.printStackTrace(); }
		
		try {
			// Give all Reizigers a list OvChipkaarten
			for (Reiziger reiziger : reizigers) {
				ovChipkaarten = odoci.findByReiziger(reiziger); 
				reiziger.setOvChipkaarten(ovChipkaarten);
			}			
		} catch (Exception e)
		{ e.printStackTrace(); }
		
		try { ps.close(); rs.close(); } catch (Exception e)
		{ System.out.println(e.getMessage()); e.printStackTrace(); }
		
		return reizigers;		
	}
	
	// Get Reiziger by Geboortedatum
	public ArrayList<Reiziger> findByGbDatum(String gbDatum) {
		System.out.println("Trying to find by gbdate");
		
		ArrayList<Reiziger> reizigers = null;
		ArrayList<OvChipkaart> ovChipkaarten = new ArrayList<OvChipkaart>();
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		OvChipkaartOracleDaoImpl odoci = null;
		
		try {
			ps = dbConnection.prepareStatement(
					"SELECT reizigerid, voorletters, tussenvoegsel, achternaam FROM reiziger WHERE gebortedatum=?");
			ps.setString(1, gbDatum);
			rs = ps.executeQuery();
			if (rs.isBeforeFirst()) odoci = new OvChipkaartOracleDaoImpl();
		} catch (Exception e)
		{ e.printStackTrace(); }
		
		try {
			while (rs.next()) {
				Reiziger reiziger = new Reiziger(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						gbDatum
						);
				ovChipkaarten = odoci.findByReiziger(reiziger);
				reiziger.setOvChipkaarten(ovChipkaarten);
				reizigers.add(reiziger);
			}
		} catch (Exception e)
		{ e.printStackTrace(); }
		
		try { ps.close(); rs.close(); } catch (Exception e)
		{ e.printStackTrace(); }
		
		return reizigers;
		
	}
	
	public boolean save(Reiziger reiziger) {
		
		try {
			PreparedStatement ps = dbConnection.prepareStatement(
					"INSERT INTO reiziger VALUES (?, ?, ?, ?, ?)");
			ps.setInt(1, reiziger.getReizigerId());
			ps.setString(2, reiziger.getVoorLetters());
			ps.setString(3, reiziger.getTussenVoegsel());
			ps.setString(4, reiziger.getAchterNaam());
			ps.setString(5, reiziger.getGbDatum());
			
			ps.executeQuery();
		} catch (Exception e)
		{ e.printStackTrace(); }
		
		return true;
		
	}
	
	public boolean update(Reiziger reiziger) {
		
		try {
			PreparedStatement ps = dbConnection.prepareStatement(
					"UPDATE reiziger SET voorletters=?, tussenvoegsel=?, achternaam=?, gebortedatum=? WHERE reizigerid=?");
			ps.setString(1, reiziger.getVoorLetters());
			ps.setString(2, reiziger.getTussenVoegsel());
			ps.setString(3, reiziger.getAchterNaam());
			ps.setString(4, reiziger.getGbDatum());
			ps.setInt(5, reiziger.getReizigerId());
			ps.executeQuery();
			ps.close();
		} catch (Exception e)
		{ e.printStackTrace(); }
		
		return true;
		
	}
	
	public boolean delete (Reiziger reiziger) {
		
		try {
			PreparedStatement ps = dbConnection.prepareStatement(
					"DELETE FROM reiziger WHERE reizigerid=?");
			ps.setInt(1, reiziger.getReizigerId());
			ps.executeQuery();
			ps.close();
		} catch (Exception e)
		{ e.printStackTrace(); }
		
		return true;
		
	}

}
