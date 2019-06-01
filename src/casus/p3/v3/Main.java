package casus.p3.v3;

import java.sql.Date;
import java.util.ArrayList;

import casus.p3.v3.dao.OvChipkaartOracleDaoImpl;
import casus.p3.v3.dao.ProductOracleDaoImpl;
import casus.p3.v3.dao.ReizigerOracleDaoImpl;
import casus.p3.v3.pojo.OvChipkaart;
import casus.p3.v3.pojo.Reiziger;
import casus.p3.v3.pojo.Product;

public class Main {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		ReizigerOracleDaoImpl rodi = new ReizigerOracleDaoImpl();
		ProductOracleDaoImpl podi = new ProductOracleDaoImpl();
		OvChipkaartOracleDaoImpl odoci = new OvChipkaartOracleDaoImpl();
		
		System.out.println("Started");
		
		System.out.println("Finding OvChipkaarten:");
		ArrayList<OvChipkaart> ovChipkaarten = odoci.findAll();		
		ArrayList<OvChipkaart> ovChipkaartenByReiziger1 = odoci.findAllByReizigerId(1);
		OvChipkaart ov_1 = odoci.find(79625);
		
		System.out.println("\nFinding Reizigers:");
		ArrayList<Reiziger> reizigers = rodi.findAll();
		ArrayList<Reiziger> reizigersByGbDatum = rodi.findByGbDatum("22-OCT-02");
		Reiziger r_1 = rodi.find(1);
		
		System.out.println("\nFinding Producten:");
		ArrayList<Product> producten = podi.findAll();
		ArrayList<Product> productenByKaartNummer = podi.findByKaartNummer(79625);
		Product p_1 = podi.findByProductNummer(1);
		
		// CRUD FUNCTIONS		
		Date date = new Date(new java.util.Date().getTime());
		OvChipkaart ov_2 = new OvChipkaart(12345, date, 2, 25.5);
		ov_2.setReiziger(r_1);
		Reiziger r_2 = new Reiziger(6, "J", "", "POLS", date);		
		Product p_2 = new Product(7);
		p_2.setBeschrijving("Lorem Ipsum");
		p_2.setPrijs(100.05);
		p_2.setProductNaam("Voorbeeld");
		
		System.out.println("\nCRUD OvChipkaart ov_2:");
		odoci.save(ov_2);
		ov_2.setKlasse(1);
		odoci.update(ov_2);
		odoci.delete(ov_2);
		
		System.out.println("\nCRUD Reiziger:");
		rodi.save(r_2);
		r_2.setAchternaam("KANIS");
		rodi.update(r_2);
		rodi.delete(r_2);

		System.out.println("\nCRUD Product:");
		podi.save(p_2);
		p_2.setPrijs(100.10);
		podi.update(p_2);
		podi.delete(p_2);
		
		System.out.println("Finished");
		
	}
}
