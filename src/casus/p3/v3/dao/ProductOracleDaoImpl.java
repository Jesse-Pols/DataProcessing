package casus.p3.v3.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import casus.p3.v3.interfaces.ProductDao;
import casus.p3.v3.pojo.OvChipkaart;
import casus.p3.v3.pojo.Product;

public class ProductOracleDaoImpl extends OracleBaseDao implements ProductDao {
	/*
	
	public ArrayList<Product> findAll() {
		
		ArrayList<Product> producten = new ArrayList<Product>();
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		OvChipkaartOracleDaoImpl odoci = null;
		ReizigerOracleDaoImpl rodi = null;
		
		try {
			ps = dbConnection.prepareStatement(
					"SELECT ovproduct.productnummer, ovkaart.kaartnummer, p.beschrijving, p.prijs "
					+ "FROM ov_chipkaart_product ovproduct "
					+ "JOIN product p "
					+ "ON ovproduct.productnummer = p.productnummer "
					+ "JOIN ov_chipkaart ovkaart "
					+ "ON ovproduct.kaartnummer = ovkaart.kaartnummer");
			rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				odoci = new OvChipkaartOracleDaoImpl();
				rodi = new ReizigerOracleDaoImpl();
			}
		} catch (Exception e)
		{ e.printStackTrace(); }
		
		try {
			while (rs.next()) {
				
				int productNummer = rs.getInt(1);
				boolean productNummerExists = false;		
				
				// Check if product is already in list
				for (Product product : producten) {
					if (product.getProductNummer() == productNummer) {
						productNummerExists = true;
						OvChipkaart ovChipkaart = odoci.find(rs.getInt(2));
						if (ovChipkaart != null) product.addOvChipkaart(ovChipkaart);
					}
				}
				
				// If product isnt in list yet
				if (!productNummerExists) {
					Product product = new Product(productNummer);
					OvChipkaart ovChipkaart = odoci.find(rs.getInt(2));
					if (ovChipkaart != null) product.addOvChipkaart(ovChipkaart);
					
					product.setBeschrijving(rs.getString(3));
					product.setPrijs(rs.getDouble(4));
					
					producten.add(product);
				}
				
			}
		} catch (Exception e)
		{ e.printStackTrace(); }
		
		return producten;
		
	}
	
	public ArrayList<Product> findByKaartNummer(int kaartNummer){
		
		ArrayList<Product> producten = new ArrayList<Product>();
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		OvChipkaartOracleDaoImpl odoci = null;
		ReizigerOracleDaoImpl rodi = null;
		
		
		return producten;
	}
*/
}
