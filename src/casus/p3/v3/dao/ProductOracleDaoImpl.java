package casus.p3.v3.dao;

import java.util.ArrayList;

import casus.p3.v3.interfaces.ProductDao;
import casus.p3.v3.pojo.OvChipkaart;
import casus.p3.v3.pojo.Product;

public class ProductOracleDaoImpl extends OracleBaseDao implements ProductDao {
	
	public ArrayList<Product> findAll() {
		
		ArrayList<Product> producten = new ArrayList<Product>();
		rodi = new ReizigerOracleDaoImpl();
		odoci = new OvChipkaartOracleDaoImpl();
		
		try {
			ps = dbConnection.prepareStatement(
					"SELECT ovproduct.productnummer, ovkaart.kaartnummer, p.beschrijving, p.prijs "
					+ "FROM ov_chipkaart_product ovproduct "
					+ "JOIN product p "
					+ "ON ovproduct.productnummer = p.productnummer "
					+ "JOIN ov_chipkaart ovkaart "
					+ "ON ovproduct.kaartnummer = ovkaart.kaartnummer");
			rs = ps.executeQuery();
		} catch (Exception e)
		{ System.out.println("ProductOracleDaoImpl/findAll()/Query Failed: " + e.getMessage()); }
		
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
		{ System.out.println("ProductOracleDaoImpl/findAll()/rs.next() Failed: " + e.getMessage()); }
		
		return producten;
		
	}
	
	public ArrayList<Product> findByKaartNummer(int kaartNummer){
		
		ArrayList<Product> producten = new ArrayList<Product>();	
		rodi = new ReizigerOracleDaoImpl();
		odoci = new OvChipkaartOracleDaoImpl();
		
		try {
			ps = dbConnection.prepareStatement(
					"SELECT ovproduct.productnummer, p.beschrijving, p.prijs "
					+ "FROM ov_chipkaart_product ovproduct "
					+ "JOIN product p "
					+ "ON ovproduct.productnummer = p.productnummer "
					+ "JOIN ov_chipkaart ovkaart "
					+ "ON ocproduct.kaartnummer = ovkaart.kaartnummer "
					+ "WHERE ovkaart.kaartnummer=?");
			ps.setInt(1, kaartNummer);
			rs = ps.executeQuery();
		} catch (Exception e)
		{ System.out.println("ProductOracleDaoImpl/findByKaartNummer()/Query Failed: " + e.getMessage()); }
		
		try {
			while (rs.next()) {
				
				int productNummer = rs.getInt(1);
				boolean productNummerExists = false;
				
				for (Product product : producten) {
					if (product.getProductNummer() == productNummer) {
						productNummerExists = true;
						OvChipkaart ovChipkaart = odoci.find(kaartNummer);
						if (ovChipkaart != null) product.addOvChipkaart(ovChipkaart);
					}
				}
				
				if (!productNummerExists) {
					Product product = new Product(productNummer);
					OvChipkaart ovChipkaart = odoci.find(kaartNummer);
					if (ovChipkaart != null) product.addOvChipkaart(ovChipkaart);
					product.setBeschrijving(rs.getString(2));
					product.setPrijs(rs.getDouble(3));
					producten.add(product);
				}				
			}
		} catch (Exception e)
		{ System.out.println("ProductOracleDaoImpl/findByKaartNummer()/rs.next() Failed: " + e.getMessage()); }
		
		return producten;
	}
	
	public Product findByProductNummer(int productNummer) {
		return findByProductNummer(productNummer, true);
	}
	
	public Product findByProductNummer(int productNummer, boolean recurse) {
		
		Product product = null;
		rodi = new ReizigerOracleDaoImpl();
		odoci = new OvChipkaartOracleDaoImpl();
		
		try {
			ps = dbConnection.prepareStatement(
					"SELECT p.productnummer, ovkaart.kaartnummer, p.productnaam, p.beschrijving, p.prijs "
					+ "FROM ov_chipkaart_product ovproduct "
					+ "RIGHT OUTER JOIN product p "
					+ "ON ovproduct.productnummer = p.productnummer "
					+ "LEFT OUTER JOIN ov_chipkaart ovkaart "
					+ "ON ovproduct.kaartnummer = ovkaart.kaartnummer "
					+ "WHERE p.productnummer=?");
			ps.setInt(1, productNummer);
			rs = ps.executeQuery();
		} catch (Exception e)
		{ System.out.println("ProductOracleDaoImpl/findByProductNummer()/Query Failed: " + e.getMessage()); }
		
		try {
			while(rs.next()) {
				
				if (product == null) {
					product = new Product(rs.getInt(1));
					product.setProductNaam(rs.getString(3));
					product.setBeschrijving(rs.getString(4));
					product.setPrijs(rs.getDouble(5));
				}
				
				if (recurse) {
					OvChipkaart ovChipkaart = odoci.find(rs.getInt(2), false);
					if (ovChipkaart != null) product.addOvChipkaart(ovChipkaart);
				}				
			}
		} catch (Exception e)
		{ System.out.println("ProductOracleDaoImpl/findByProductNummer()/rs.next() Failed: " + e.getMessage()); }
		
		return product;
	}
	
	public void save(Product product) {
		
		rodi = new ReizigerOracleDaoImpl();
		odoci = new OvChipkaartOracleDaoImpl();
		
		try {
			ps = dbConnection.prepareStatement(
					"INSERT INTO product VALUES (?, ?, ?, ?, ?)");
			ps.setInt(1, product.getProductNummer());
			ps.setString(2, product.getProductNaam());
			ps.setString(3, product.getBeschrijving());
			ps.setDouble(4, product.getPrijs());
			ps.executeQuery();
		} catch (Exception e)
		{ System.out.println("ProductOracleDaoImpl/save()/Query Failed: " + e.getMessage()); }
		
		try {
			for (OvChipkaart ovChipkaart : product.getOvChipkaarten()) {
				
				int ovProductId = GetNewOvProductId();
				
				ps = dbConnection.prepareStatement(
						"INSERT INTO ov_chipkaart_product VALUES (?, ?, ?, 'leeg', ?)");
				ps.setInt(1, ovProductId);
				ps.setInt(2, ovChipkaart.getKaartNummer());
				ps.setInt(3, product.getProductNummer());
				java.util.Date date = new java.util.Date();
				ps.setDate(4,  new java.sql.Date(date.getTime()));
				ps.executeQuery();
			}
			
		} catch (Exception e)
		{ System.out.println("ProductOracleDaoImpl/save()/Query Failed: " + e.getMessage()); }	
	}
	
	public void update(Product product) {
		
		rodi = new ReizigerOracleDaoImpl();
		odoci = new OvChipkaartOracleDaoImpl();
		
		try {
			
			ps = dbConnection.prepareStatement(
					"UPDATE product SET productnaam=?, beschrijving=?, prijs=? WHERE productnummer=?");
			ps.setString(1, product.getProductNaam());
			ps.setString(2, product.getBeschrijving());
			ps.setDouble(3, product.getPrijs());
			ps.setInt(4, product.getProductNummer());
			ps.executeQuery();
			
		} catch (Exception e)
		{ System.out.println("ProductOracleDaoImpl/update()/Query Failed: " + e.getMessage()); }
		
		try {
			
			ps = dbConnection.prepareStatement(
					"SELECT kaartnummer FROM ov_chipkaart_product WHERE productnummer=?");
			ps.setInt(1, product.getProductNummer());
			rs = ps.executeQuery();
			
		} catch (Exception e)
		{ System.out.println("ProductOracleDaoImpl/update()/Query Failed: " + e.getMessage()); }
		
		ArrayList<Integer> localKaartNummers = new ArrayList<Integer>();
		ArrayList<Integer> databaseKaartNummers = new ArrayList<Integer>();
		
		try {
			for (OvChipkaart ovChipkaart : product.getOvChipkaarten()) {
				localKaartNummers.add(ovChipkaart.getKaartNummer());
			}
			while(rs.next()) {
				databaseKaartNummers.add(rs.getInt(1));
			}
			for (int kaartNummer : localKaartNummers) {
				if (!databaseKaartNummers.contains(kaartNummer)) {
					
					int ovProductId = GetNewOvProductId();
					
					ps = dbConnection.prepareStatement(
							"INSERT INTO ov_chipkaart_product VALUES (?, ?, ?, 'leeg', ?)");
					ps.setInt(1, ovProductId);
					ps.setInt(2, kaartNummer);
					ps.setInt(3, product.getProductNummer());
					java.util.Date date = new java.util.Date();
					ps.setDate(4, new java.sql.Date(date.getTime()));
					ps.executeQuery();
					
				}
			}
			for (int kaartNummer : databaseKaartNummers) {
				if (!localKaartNummers.contains(kaartNummer) && databaseKaartNummers.contains(kaartNummer)) {
					ps = dbConnection.prepareStatement(
							"DELETE FROM ov_chipkart_product WHERE kaartnummer=? AND productnummer=?");
					ps.setInt(1, kaartNummer);
					ps.setInt(2, product.getProductNummer());
					ps.executeQuery();
				}
			}
		} catch (Exception e)
		{ System.out.println("ProductOracleDaoImpl/update()/rs.next() Failed: " + e.getMessage()); }
		
	}
	
	public void delete(Product product) {
		
		rodi = new ReizigerOracleDaoImpl();
		odoci = new OvChipkaartOracleDaoImpl();
		
		try {
			
			int productNummer = product.getProductNummer();
			for (OvChipkaart ovChipkaart : product.getOvChipkaarten()) {
				ps = dbConnection.prepareStatement(
						"DELETE FROM ov_chipkaart_product WHERE kaartnummer=? AND productnummer=?");
				ps.setInt(1, ovChipkaart.getKaartNummer());
				ps.setInt(2, productNummer);
				ps.executeQuery();
			}
			
			ps = dbConnection.prepareStatement(
					"DELETE FROM product WHERE productnummer=?");
			ps.setInt(1, productNummer);
			ps.executeQuery();
			
		} catch (Exception e)
		{ System.out.println("ProductOracleDaoImpl/delete()/Query Failed: " + e.getMessage()); }
		
	}
	
	public int GetNewOvProductId() {
		
		int newId = 0;
		try {
			ps = dbConnection.prepareStatement(
					"SELECT ovproductid FROM ov_chipkaart_product");
			rs = ps.executeQuery();
		} catch (Exception e)
		{ System.out.println("ProductOracleDaoImpl/GetNewOvProductId()/Query Failed: " + e.getMessage()); }
		
		try {
			while (rs.next()) {
				int id = rs.getInt(1);
				if (id > newId) newId = id;
			}
		} catch (Exception e)
		{ System.out.println("ProductOracleDaoImpl/GetNewOvProductId()/rs.next() Failed: " + e.getMessage()); }
		
		newId++;
		
		return newId;
		
	}
}
