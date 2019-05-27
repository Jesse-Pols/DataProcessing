package casus.p3.v2.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import casus.p3.v2.interfaces.ProductDao;
import casus.p3.v2.pojo.OvChipkaart;
import casus.p3.v2.pojo.Product;

public class ProductOracleDaoImpl extends OracleBaseDao implements ProductDao {
	
	/*
	public Product findByProductNummer(int productNummer) {
		return findByProductNummer(productNummer, true);
	}
	
	public Product findByProductNummer(int productNummer, boolean recurse) {
		
		Product product = null;
		OvChipkaart ovChipkaart = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		OvChipkaartOracleDaoImpl odoci = null;
		
		try {
			
			ps = dbConnection.prepareStatement(
					"SELECT p.PRODUCTNUMMER, ovkaart.KAARTNUMMER, p.PRODUCTNAAM, p.BESCHRIJVING, p.PRIJS "
					+ "FROM ov_chipkaart_product ovkaartproduct "
					+ "RIGHT OUTER JOIN product p "
					+ "ON ovkaartproduct.PRODUCTNUMMER = p.PRODUCTNUMMER "
					+ "LEFT OUTER JOIN ov_chipkaart ovkaart "
					+ "ON ovkaartproduct.KAARTNUMMER = ovkaart.KAARTNUMMER "
					+ "WHERE p.PRODUCTNUMMER = ?");
			ps.setInt(1, productNummer);
			rs = ps.executeQuery();
			
			if (rs.isBeforeFirst()) odoci = new OvChipkaartOracleDaoImpl();
			
			while (rs.next()) {
							
				if (product == null) product = new Product(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getDouble(5));
				if (recurse) {
					ovChipkaart = odoci.findByKaartNummer(rs.getInt(2));
					if (ovChipkaart != null) product.addOvChipkaart(ovChipkaart);
				}				
				
			}
			
		} catch (Exception e) { System.out.println("Error -> Couldn't find Product By Nummer: " + e.getMessage()); }
		finally	{ try { ps.close(); rs.close(); } catch (Exception e) { System.out.println(e.getMessage()); }};		
		
		return product;
		
	}
	*/

}
