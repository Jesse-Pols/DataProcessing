package casus.p3;

import java.sql.ResultSet;

import casus.oracle.OracleGlobal;
import casus.pojo.Product;

public class ProductOracleDaoImpl extends OracleGlobal implements ProductDao {
	
	public Product getProductByProductNr(int productNummer) {
		
		Product product = null;
		
		String query = String.format("SELECT * FROM PRODUCT WHERE PRODUCTNUMMER=%d", productNummer);
		ResultSet rs = runQuery(query, false);
		
		try {
			
			while (rs.next()) {
				
				product = new Product(
						rs.getInt("PRODUCTNUMMER"),
						rs.getString("PRODUCTNAAM"),
						rs.getString("BESCHRIJVING"),
						rs.getFloat("PRIJS")						
						);
				
			}
			
		} catch (Exception e) { System.out.println(e.getMessage()); }
		
		closeStatement();
		
		return product;
		
	}
	
	public boolean save (Product product) {
		
		String query = String.format("INSERT INTO PRODUCT VALUES(%d, '%s', '%s', %f)", 
				product.getProductNummer(),
				product.getProductNaam(),
				product.getBeschrijving(),
				product.getPrijs());
		runQuery(query, false);
		closeStatement();
		return true;
		
	}
	
	public boolean update (Product product) {
		
		String query = String.format("UPDATE PRODUCT SET PRODUCTNAAM='%s', BESCHRIJVING='%s', PRIJS=%f WHERE PRODUCTNUMMER=%d", 
				product.getProductNaam(),
				product.getBeschrijving(),
				product.getPrijs(),
				product.getProductNummer());
		runQuery(query, false);
		closeStatement();
		return true;
		
	}
	
	public boolean delete (Product product) {
		
		String query = String.format("DELETE FROM PRODUCT WHERE PRODUCTNUMMER=%d", 
				product.getProductNummer());
		runQuery(query, false);
		closeStatement();
		return true;
		
	}

}
