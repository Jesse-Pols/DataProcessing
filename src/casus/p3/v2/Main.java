package casus.p3.v2;

import casus.p3.v2.dao.OvChipkaartOracleDaoImpl;
import casus.p3.v2.dao.ReizigerOracleDaoImpl;
import casus.p3.v2.pojo.Reiziger;

public class Main {
	public static void main(String[] args) {
		
		ReizigerOracleDaoImpl rodi = new ReizigerOracleDaoImpl();
		OvChipkaartOracleDaoImpl ocodi = new OvChipkaartOracleDaoImpl();
		
		Reiziger reiziger_1 = new Reiziger(7, "J", null, "Pols", "22-SEP-1945");
		
		rodi.save(reiziger_1);
		
		rodi.closeConnection();
		
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
		
	}
}
