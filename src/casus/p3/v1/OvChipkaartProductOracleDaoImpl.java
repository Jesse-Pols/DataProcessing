package casus.p3.v1;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OvChipkaartProductOracleDaoImpl extends OracleGlobal implements OvChipkaartProductDao {
	
	public List<Integer> getProductNummerByKaartNummer(int kaartNummer) {
		
		List<Integer> result = new ArrayList<Integer>();
		
		String query = String.format("SELECT PRODUCTNUMMER FROM OV_CHIPKAART_PRODUCT WHERE KAARTNUMMER=%d", kaartNummer);
		ResultSet rs = runQuery(query, true);
		
		try { 
			
			while (rs.next()) {
				
				result.add(rs.getInt("PRODUCTNUMMER")); 
				
			}
			
		} catch (Exception e) { System.out.println(e.getMessage()); }
		
		closeStatement();
		return result;
		
	}
	
	public List<Integer> getKaartNummerByProductNummer(int productNummer) {
		
		List<Integer> result = new ArrayList<Integer>();
		
		String query = String.format("SELECT KAARTNUMMER FROM OV_CHIPKAART_PRODUCT WHERE PRODUCTNUMMER=%d", productNummer);
		ResultSet rs = runQuery(query, true);
		
		try {
			
			while (rs.next()) {
				
				result.add(rs.getInt("KAARTNUMMER"));
				
			}
		} catch (Exception e) { System.out.println(e.getMessage()); }
		
		closeStatement();
		return result;
		
	}
	
	public boolean save (OvChipkaartProduct ovChipkaartProduct) {
		
		String query = String.format("INSERT INTO OV_CHIPKAART_PRODUCT VALUES(%d, %d, %d, '%s', '%s')", 
				ovChipkaartProduct.getOvproductID(),
				ovChipkaartProduct.getKaartNummer(),
				ovChipkaartProduct.getProductNummer(),
				ovChipkaartProduct.getReisproductStatus(),
				ovChipkaartProduct.getLastUpdate());
		runQuery(query, false);
		closeStatement();		
		return true;
		
	}
	
	public boolean update (OvChipkaartProduct ovChipkaartProduct) {
		
		String query = String.format("UPDATE OV_CHIPKAART_PRODUCT SET KAARTNUMMER=%d, PRODUCTNUMMER=%d, REISPRODUCTSTATUS='%s', LASTUPDATE='%s' WHERE OVPRODUCTID=%d", 
				ovChipkaartProduct.getKaartNummer(),
				ovChipkaartProduct.getProductNummer(),
				ovChipkaartProduct.getReisproductStatus(),
				ovChipkaartProduct.getLastUpdate(),
				ovChipkaartProduct.getOvproductID());
		runQuery(query, false);
		closeStatement();
		return true;
		
	}
	
	public boolean delete (OvChipkaartProduct ovChipkaartProduct) {
		
		String query = String.format("DELETE FROM OV_CHIPKAART_PRODUCT WHERE OVPRODUCTID=%d", 
				ovChipkaartProduct.getOvproductID());
		runQuery(query, false);
		closeStatement();
		return true;
		
	}

}
