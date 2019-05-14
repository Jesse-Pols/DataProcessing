package casus.p3;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import casus.oracle.OracleGlobal;
import casus.pojo.OvChipkaart;
import casus.pojo.Reiziger;

public class OvChipkaartOracleDaoImpl extends OracleGlobal implements OvChipkaartDao {
	
	public OvChipkaart getOvChipkaartByKaartNr (int kaartNummer, ReizigerOracleDaoImpl rodi) {
		
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
		
		closeStatement();
		
		Reiziger reiziger = rodi.getReizigerByReizigerId(ovChipkaart.getReizigerId(), this);
		ovChipkaart.setReiziger(reiziger);
		
		return ovChipkaart;
		
	}
	
	public List<OvChipkaart> getOvChipkaartByReizigerId(int reizigerId) {
		
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
	
	public boolean save (OvChipkaart ovChipkaart) {
		return false;
	}
	
	public boolean update (OvChipkaart ovChipkaart) {
		return false;
	}
	
	public boolean delete (OvChipkaart ovChipkaart) {
		return false;
	}

}
