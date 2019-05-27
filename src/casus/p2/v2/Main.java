package casus.p2.v2;

import java.sql.SQLException;
import java.util.ArrayList;

import casus.p2.v2.dao.OvChipkaartOracleDaoImpl;
import casus.p2.v2.dao.ReizigerOracleDaoImpl;
import casus.p2.v2.pojo.OvChipkaart;
import casus.p2.v2.pojo.Reiziger;

public class Main {
	public static void main(String[] args) throws SQLException {
		
		OvChipkaartOracleDaoImpl odoci = new OvChipkaartOracleDaoImpl();
		ReizigerOracleDaoImpl rodi = new ReizigerOracleDaoImpl();
		
		ArrayList<Reiziger> reizigers = rodi.findAll();
		System.out.println("\nAlle Reizigers:");
		for (Reiziger reiziger : reizigers)
			System.out.println(reiziger.toString());
		
		ArrayList<OvChipkaart> ovChipkaarten = odoci.findAll();
		System.out.println("\nAlle OvChipkaarten:");
		for (OvChipkaart ovChipkaart : ovChipkaarten)
			System.out.println(ovChipkaart.toString());
		
		Reiziger r_1 = new Reiziger(6, "J", "", "Pols", "22-OCT-99");
		rodi.save(r_1);		
		
		OvChipkaart ov_1 = new OvChipkaart(12346, "13-FEB-00", 2, 25.5);
		ov_1.setReiziger(r_1);
		odoci.save(ov_1);
		
		ov_1.setKlasse(1);
		odoci.update(ov_1);
		System.out.println("ov_1: \n" + ov_1);
		
		r_1.setAchterNaam("Kanis");
		rodi.update(r_1);
		System.out.println("r_1: \n" + r_1);
		
		odoci.delete(ov_1);
		rodi.delete(r_1);
		
		OvChipkaart ov_2 = odoci.find(79625);
		Reiziger r_2 = rodi.find(1);
		
		System.out.println(ov_2);
		System.out.println(r_2);
		
		odoci.closeConnection();
		
	}
	
}
