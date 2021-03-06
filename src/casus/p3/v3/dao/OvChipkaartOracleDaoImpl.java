package casus.p3.v3.dao;

import java.util.ArrayList;

import casus.p3.v3.interfaces.OvChipkaartDao;
import casus.p3.v3.pojo.OvChipkaart;
import casus.p3.v3.pojo.Product;
import casus.p3.v3.pojo.Reiziger;

public class OvChipkaartOracleDaoImpl extends OracleBaseDao implements OvChipkaartDao {
	
	public ArrayList<OvChipkaart> findAll() {
		System.out.println("Finding All OvChipkaarten...");
		
		ArrayList<OvChipkaart> ovChipkaarten = new ArrayList<OvChipkaart>();
		ReizigerOracleDaoImpl rodi = new ReizigerOracleDaoImpl();
		ProductOracleDaoImpl podi = new ProductOracleDaoImpl();
		
		try {
			ps = dbConnection.prepareStatement(
					"SELECT ovkaart.kaartnummer, ovkaart.geldigtot, ovkaart.klasse, ovkaart.saldo, ovkaart.reizigerid, p.productnummer "
					+ "FROM ov_chipkaart_product ovproduct "
					+ "RIGHT OUTER JOIN ov_chipkaart ovkaart "
					+ "ON ovproduct.kaartnummer = ovkaart.kaartnummer "
					+ "LEFT OUTER JOIN product p "
					+ "ON p.productnummer = ovproduct.productnummer");
			rs = ps.executeQuery();
		} catch (Exception e)
		{ System.out.println("OvChipkaartOracleDaoImpl/findAll()/Query Failed: \n" + e.getMessage()); }
		
		try {
			while(rs.next()) {
				
				boolean kaartExists = false;
				int kaartNummer = rs.getInt(1);
				OvChipkaart ovChipkaart = null;
				
				// Checkt of kaart al in het lijstje staat
				for (OvChipkaart tempOvChipkaart : ovChipkaarten) {
					if (kaartNummer == tempOvChipkaart.getKaartNummer()) {
						ovChipkaart = tempOvChipkaart;
						kaartExists = true;
					}
				}
				
				// Kaart staat niet in het lijstje
				if (!kaartExists) {
					ovChipkaart = new OvChipkaart(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getDouble(4));
					
					Reiziger reiziger = rodi.find(rs.getInt(5));
					ovChipkaart.setReiziger(reiziger);
					
					Product product = podi.findByProductNummer(rs.getInt(6), false);
					if (product != null) ovChipkaart.addProduct(product);
					
					ovChipkaarten.add(ovChipkaart);				
				}
			}
		} catch (Exception e)
		{ System.out.println("OvChipkaartOracleDaoImpl/findAll()/while(rs.next()) Failed: \n" + e.getMessage()); }
		
		try { ps.close(); rs.close(); } catch (Exception e)
		{ System.out.println("Had Trouble Closing ps & rs: " + e.getMessage()); }
		
		return ovChipkaarten;
		
	}
	
	public ArrayList<OvChipkaart> findAllByReizigerId(int reizigerId) {
		return findAllByReizigerId(reizigerId, false);
	}
	
	public ArrayList<OvChipkaart> findAllByReizigerId(int reizigerId, boolean recurse){
		System.out.println("Finding All OvChipkaarten By ReizigerId...");
		
		ArrayList<OvChipkaart> ovChipkaarten = new ArrayList<OvChipkaart>();
		ReizigerOracleDaoImpl rodi = new ReizigerOracleDaoImpl();
		ProductOracleDaoImpl podi = new ProductOracleDaoImpl();
		
		try {
			ps = dbConnection.prepareStatement(
					"SELECT ovkaart.kaartnummer, ovkaart.geldigtot, ovkaart.klasse, ovkaart.saldo, ovkaart.reizigerid, p.productnummer "
					+ "FROM ov_chipkaart_product ovproduct "
					+ "RIGHT OUTER JOIN ov_chipkaart ovkaart "
					+ "ON ovproduct.kaartnummer = ovkaart.kaartnummer "
					+ "LEFT OUTER JOIN product p "
					+ "ON p.productnummer = ovproduct.productnummer "
					+ "WHERE ovkaart.reizigerid=?");
			ps.setInt(1, reizigerId);
			rs = ps.executeQuery();
		} catch (Exception e)
		{ System.out.println("OvChipkaartOracleDaoImpl/findAllByReizigerId()/Query Failed: " + e.getMessage()); }
		
		try {
			while (rs.next()) {
				
				boolean kaartExists = false;
				int kaartNummer = rs.getInt(1);
				OvChipkaart ovChipkaart = null;
				
				for (OvChipkaart tempOvChipkaart : ovChipkaarten) {
					if (kaartNummer == tempOvChipkaart.getKaartNummer()) {
						ovChipkaart = tempOvChipkaart;
						kaartExists = true;
					}
				}
				
				if (!kaartExists) {
					ovChipkaart = new OvChipkaart(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getDouble(4));
					if (recurse) {
						Reiziger reiziger = rodi.find(rs.getInt(5), false);
						ovChipkaart.setReiziger(reiziger);
					}
					Product product = podi.findByProductNummer(rs.getInt(6), false);
					if (product != null) ovChipkaart.addProduct(product);
					
					ovChipkaarten.add(ovChipkaart);
				}				
			}
		} catch (Exception e)
		{ System.out.println("OvChipkaartOracleDaoImpl/findAllByReizigerId()/rs.next() Failed: " + e.getMessage()); }
		
		try { ps.close(); rs.close(); } catch (Exception e)
		{ System.out.println("Had Trouble Closing ps & rs: " + e.getMessage()); }
		
		return ovChipkaarten;
	}
	
	public OvChipkaart find(int kaartNummer) {
		return find(kaartNummer, true);
	}
	
	public OvChipkaart find(int kaartNummer, boolean recurse) {
		System.out.println("Finding OvChipkaart...");
		
		OvChipkaart ovChipkaart = null;
		ReizigerOracleDaoImpl rodi = new ReizigerOracleDaoImpl();
		ProductOracleDaoImpl podi = new ProductOracleDaoImpl();
		
		try {
			ps = dbConnection.prepareStatement(
					"SELECT ovkaart.geldigtot, ovkaart.klasse, ovkaart.saldo, ovkaart.reizigerid, p.productnummer "
					+ "FROM ov_chipkaart_product ovproduct "
					+ "RIGHT OUTER JOIN ov_chipkaart ovkaart "
					+ "ON ovproduct.kaartnummer = ovkaart.kaartnummer "
					+ "LEFT OUTER JOIN product p "
					+ "ON p.productnummer = ovproduct.productnummer "
					+ "WHERE ovkaart.kaartnummer=?");
			ps.setInt(1, kaartNummer);
			rs = ps.executeQuery();
		} catch (Exception e)
		{ System.out.println("OvChipkaartOracleDaoImpl/find()/Query Failed: " + e.getMessage()); }
		
		try {
			while(rs.next()) {
				ovChipkaart = new OvChipkaart(kaartNummer, rs.getDate(1), rs.getInt(2), rs.getDouble(3));
				Reiziger reiziger = rodi.find(rs.getInt(4), false);
				ovChipkaart.setReiziger(reiziger);
				
				if (recurse) {
					Product product = podi.findByProductNummer(rs.getInt(5), false);
					if (product != null) ovChipkaart.addProduct(product);
				}
			}
		} catch (Exception e)
		{ System.out.println("OvChipkaartOracleDaoImpl/find()/rs.next() Failed: " + e.getMessage()); }
		
		try { ps.close(); rs.close(); } catch (Exception e)
		{ System.out.println("Had Trouble Closing ps & rs: " + e.getMessage()); }
		
		return ovChipkaart;
		
	}
	
	public void save(OvChipkaart ovChipkaart) {
		System.out.println("Saving OvChipkaart...");
		
		ProductOracleDaoImpl podi = new ProductOracleDaoImpl();
		
		try {
			ps = dbConnection.prepareStatement(
					"INSERT INTO ov_chipkaart VALUES (?, ?, ?, ?, ?)");
			ps.setInt(1, ovChipkaart.getKaartNummer());
			ps.setDate(2, ovChipkaart.getGeldigTot());
			ps.setInt(3, ovChipkaart.getKlasse());
			ps.setDouble(4, ovChipkaart.getSaldo());
			ps.setInt(5, ovChipkaart.getReiziger().getReizigerId());
			ps.executeQuery();			
		} catch (Exception e)
		{ System.out.println("OvChipkaartOracleDaoImpl/save()/Query/insertOvChipkaart Failed: " + e.getMessage()); }
		
		try {
			for (Product product : ovChipkaart.getProducten()) {
				ps = dbConnection.prepareStatement(
						"INSERT INTO ov_chipkaart_product VALUES (?, ?, ?, 'leeg', ?)");
				ps.setInt(1, podi.GetNewOvProductId());
				ps.setInt(2,  ovChipkaart.getKaartNummer());
				ps.setInt(3, product.getProductNummer());
				java.util.Date date = new java.util.Date();
				ps.setDate(4,  new java.sql.Date(date.getTime()));
				ps.executeQuery();
			}			
		} catch (Exception e)
		{ System.out.println("OvChipkaartOracleDaoImpl/save()/Query/insertProduct Failed: " + e.getMessage()); }
		
		try { ps.close(); } catch (Exception e)
		{ System.out.println("Had Trouble Closing ps & rs: " + e.getMessage()); }
		
	}
	
	public OvChipkaart update(OvChipkaart ovChipkaart) {
		System.out.println("Updating OvChipkaart...");
		
		ProductOracleDaoImpl podi = new ProductOracleDaoImpl();
		
		try {
			ps = dbConnection.prepareStatement(
					"UPDATE ov_chipkaart SET geldigtot=?, klasse=?, saldo=?, reizigerid=? WHERE kaartnummer=?");
			ps.setDate(1,  ovChipkaart.getGeldigTot());
			ps.setInt(2, ovChipkaart.getKlasse());
			ps.setDouble(3, ovChipkaart.getSaldo());
			ps.setInt(4, ovChipkaart.getReiziger().getReizigerId());
			ps.setInt(5, ovChipkaart.getKaartNummer());
			ps.executeQuery();			
			
		} catch (Exception e)
		{ System.out.println("OvChipkaartOracleDaoImpl/update()/Query/updateOvChipkaart Failed: " + e.getMessage()); }
		
		try {
			ps = dbConnection.prepareStatement(
					"SELECT productnummer FROM ov_chipkaart_product WHERE kaartnummer=?");
			ps.setInt(1, ovChipkaart.getKaartNummer());
			rs = ps.executeQuery();
			
			
			ArrayList<Integer> localProductNummers = new ArrayList<Integer>();
			ArrayList<Integer> databaseProductNummers = new ArrayList<Integer>();
			
			if (ovChipkaart != null && ovChipkaart.getProducten() != null) {
				for (Product product : ovChipkaart.getProducten()) {
					if (product != null) localProductNummers.add(product.getProductNummer());
				}
			}
			
			while (rs.next()) {
				databaseProductNummers.add(rs.getInt(1));
			}
			
			for (int productNummer : localProductNummers) {
				if (!databaseProductNummers.contains(productNummer)) {
					int ovProductId = podi.GetNewOvProductId();
					
					ps = dbConnection.prepareStatement(
							"INSERT INTO ov_chipkaart_product VALUES (?, ?, ?, 'leeg', ?)");
					ps.setInt(1, ovProductId);
					ps.setInt(2, ovChipkaart.getKaartNummer());
					ps.setInt(3, productNummer);
					java.util.Date date = new java.util.Date();
					ps.setDate(4, new java.sql.Date(date.getTime()));
					ps.executeQuery();
					
				}
			}
			
			for (int productNummer: databaseProductNummers) {
				if (!localProductNummers.contains(productNummer) && databaseProductNummers.contains(productNummer)) {
					ps = dbConnection.prepareStatement("DELETE FROM ov_chipkaart_product WHERE kaartnummer=? AND productnummer=?");
					ps.setInt(1, ovChipkaart.getKaartNummer());
					ps.setInt(2, productNummer);
					ps.executeQuery();
					
				}
			}				
		} catch (Exception e)
		{ System.out.println("OvChipkaartOracleDaoImpl/update()/Query/insertordeleteProduct Failed: " + e.getMessage()); }
		
		try { ps.close(); rs.close(); } catch (Exception e)
		{ System.out.println("Had Trouble Closing ps & rs: " + e.getMessage()); }
		
		return find(ovChipkaart.getKaartNummer());
	}
	
	public void delete(int kaartNummer) {
		OvChipkaart ovChipkaart = this.find(kaartNummer);
		delete(ovChipkaart);
	}
	
	public void delete(OvChipkaart ovChipkaart) {
		System.out.println("Deleting OvChipkaart...");
		
		try {
			for (Product product : ovChipkaart.getProducten()) {
				ps = dbConnection.prepareStatement(
						"DELETE FROM ov_chipkaart_product WHERE kaartnummer=? and productnummer=?");
				ps.setInt(1, ovChipkaart.getKaartNummer());
				ps.setInt(2, product.getProductNummer());
				ps.executeQuery();
				
			}
			
			ps = dbConnection.prepareStatement(
					"DELETE FROM ov_chipkaart WHERE kaartnummer=?");
			ps.setInt(1, ovChipkaart.getKaartNummer());
			ps.executeQuery();
								
		} catch (Exception e)
		{ System.out.println("OvChipkaartOracleDaoImpl/delete()/Query Failed: " + e.getMessage()); }
		
		try { ps.close(); } catch (Exception e)
		{ System.out.println("Had Trouble Closing ps & rs: " + e.getMessage()); }
		
	}
		
}
