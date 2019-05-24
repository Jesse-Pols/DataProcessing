package casus.p3.v2;

import casus.p3.v2.dao.OvChipkaartOracleDaoImpl;
import casus.p3.v2.dao.ProductOracleDaoImpl;
import casus.p3.v2.dao.ReizigerOracleDaoImpl;
import casus.p3.v2.pojo.OvChipkaart;

public class Main {
	public static void main(String[] args) {
		
		System.out.println("Started");	
		
		OvChipkaartOracleDaoImpl odoci = new OvChipkaartOracleDaoImpl();
		ProductOracleDaoImpl podi = new ProductOracleDaoImpl();
		ReizigerOracleDaoImpl rodi = new ReizigerOracleDaoImpl();
		
		// Vind een Ov chipkaart
		OvChipkaart ov_1 = odoci.findByKaartNummer(35283);
		
		// Vind een product
		//Product p_1 = podi.findByProductNummer(1);
		
		
		
		
		
		//ReizigerOracleDaoImpl rodi = new ReizigerOracleDaoImpl();
		//OvChipkaartOracleDaoImpl ocodi = new OvChipkaartOracleDaoImpl();
		
		
		//Reiziger reiziger_1 = new Reiziger(6, "J", null, "Pols", "22-SEP-1945");
		//OvChipkaart ov_1 = new OvChipkaart(12345, "12-OCT-1984", 2, 10.5, reiziger_1);
		
		//rodi.save(reiziger_1);
		
		//reiziger_1.setAchternaam("Iets anders");
		
		///rodi.update(reiziger_1);
		
		//Reiziger reiziger_2 = rodi.findById(11);
		//if (reiziger_2 != null) System.out.println("Reiziger 2 is gekopieerd van reiziger 1, achternaam: " + reiziger_2.getAchternaam());
		
		//ocodi.save(ov_1);
		//ocodi.delete(ov_1);
		
		//rodi.delete(reiziger_1);
		
		//rodi.closeConnection();
		
		//ocodi.save(new OvChipkaart(12345,"22-OCT-1999",2,30.5));
		
		
		
		//ReizigerOracleDaoImpl rodi = new ReizigerOracleDaoImpl();	
		//OvChipkaartOracleDaoImpl ocodi = new OvChipkaartOracleDaoImpl();	
		
		
		
		/*
		Reiziger reiziger_2 = rodi.getReizigerByReizigerId(2, ocodi);
		System.out.println(reiziger_2.getOvChipkaarten());
		
		OvChipkaart ovchipkaart_1 = ocodi.getOvChipkaartByKaartNr(35283, rodi);
		System.out.println(ovchipkaart_1.getReiziger());
		
		Reiziger reiziger_6 = new Reiziger(6, "J", null, "Pols", "22-OCT-1999");
		rodi.save(reiziger_6);
		reiziger_6.setAchternaam("Kanis");
		rodi.update(reiziger_6);
		rodi.delete(reiziger_6);
		*/
		
		System.out.println("Ended");
		
	}
}
