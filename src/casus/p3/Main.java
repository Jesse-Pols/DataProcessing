package casus.p3;

import java.sql.Date;

import casus.pojo.Reiziger;

public class Main {
	public static void main(String[] args) {
		
		ReizigerOracleDaoImpl rodi = new ReizigerOracleDaoImpl();	
		
		Reiziger reiziger_2 = rodi.getReizigerById(2);
		System.out.println(reiziger_2.getOvChipkaarten());
		
		Reiziger reiziger_6 = new Reiziger(6, "J", null, "Pols", "22-OCT-1999");
		rodi.save(reiziger_6);
		reiziger_6.setAchternaam("Kanis");
		rodi.update(reiziger_6);
		rodi.delete(reiziger_6);
		
	}
}
