package casus.p3.v3;

import java.util.ArrayList;

import casus.p3.v3.dao.OvChipkaartOracleDaoImpl;
import casus.p3.v3.dao.ProductOracleDaoImpl;
import casus.p3.v3.dao.ReizigerOracleDaoImpl;
import casus.p3.v3.pojo.OvChipkaart;

public class Main {
	public static void main(String[] args) {
		
		ReizigerOracleDaoImpl rodi = new ReizigerOracleDaoImpl();
		ProductOracleDaoImpl podi = new ProductOracleDaoImpl();
		OvChipkaartOracleDaoImpl odoci = new OvChipkaartOracleDaoImpl();
		
		System.out.println("Started");
		
		ArrayList<OvChipkaart> ovChipkaarten = odoci.findAll();
		System.out.println(ovChipkaarten);
		
		//OvChipkaart ov_1 = odoci.find(79625);
		//Reiziger r_1 = rodi.find(1);
		//Product p_1 = podi.findByProductNummer(3);
		
		//System.out.println("OV_1: \n" + ov_1);
		//System.out.println("R_1: \n" + r_1);
		//System.out.println("P_1: \n" + p_1);
		
		/*
		p_1.removeOvChipkaart(ov_1);
		
		Product p_2 = new Product(7);
		p_2.setPrijs(100.05);
		p_2.setProductNaam("Voorbeeld");
		p_2.setBeschrijving("Lorem Ipsum");
		
		podi.save(p_2);
		
		podi.delete(p_2);
		*/
		
		System.out.println("Finished");
		
	}
}
