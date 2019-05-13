package casus.p3;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import casus.oracle.OracleGlobal;
import casus.pojo.OvChipkaart;
import casus.pojo.Reiziger;

public class ReizigerOracleDaoImpl extends OracleGlobal implements ReizigerDao {
	
	public Reiziger getReizigerById(int reizigerId) {
		
		Reiziger reiziger = null;
		
		String query = String.format("SELECT * FROM REIZIGER WHERE REIZIGERID=%d", reizigerId);
		ResultSet rs = runQuery(query, true);
		
		try {
			
			while (rs.next()) {
				
				reiziger = new Reiziger(
						rs.getInt("REIZIGERID"),
						rs.getString("VOORLETTERS"),
						rs.getString("TUSSENVOEGSEL"),
						rs.getString("ACHTERNAAM"),
						rs.getString("GEBORTEDATUM")						
						);
				
			}
			
		} catch (Exception e) { System.out.println(e.getMessage()); }
		
		closeStatement();
		
		List<OvChipkaart> ovChipkaarten = this.getOvChipkaartByReiziger(reizigerId);
		reiziger.setOvChipkaarten(ovChipkaarten);
		
		return reiziger;
		
	}
	
	public List<OvChipkaart> getOvChipkaartByReiziger(int reizigerId) {
		
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
			
		} catch (Exception e) { System.out.println(e.getMessage()); }
		
		closeStatement();
		
		return listOvChipkaart;
		
	}
	
	public boolean save (Reiziger reiziger) {
		
		String query = String.format("INSERT INTO REIZIGER VALUES(%d, '%s', '%s', '%s', '%s')", 
				reiziger.getReizigerId(),
				reiziger.getVoorletters(),
				reiziger.getTussenvoegsel(),
				reiziger.getAchternaam(),
				reiziger.getGeboortedatum());
		runQuery(query, false);
		closeStatement();
		return true;
		
	}
	
	public boolean update (Reiziger reiziger) {
		
		String query = String.format("UPDATE REIZIGER SET VOORLETTERS='%s', TUSSENVOEGSEL='%s', ACHTERNAAM='%s', GEBORTEDATUM='%s' WHERE REIZIGERID=%d", 
				reiziger.getVoorletters(),
				reiziger.getTussenvoegsel(),
				reiziger.getAchternaam(),
				reiziger.getGeboortedatum(),
				reiziger.getReizigerId());
		runQuery(query, false);
		closeStatement();
		return true;
		
	}
	
	public boolean delete (Reiziger reiziger) {
		
		String query = String.format("DELETE FROM REIZIGER WHERE REIZIGERID=%d", 
				reiziger.getReizigerId());
		runQuery(query, false);
		closeStatement();
		return true;
		
	}	
	
}
