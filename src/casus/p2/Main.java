package casus.p2;

import java.util.Date;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) throws SQLException {
		
		ReizigerOracleDaoImpl rodi = new ReizigerOracleDaoImpl();
		OvChipkaartOracleDaoImpl ocodi = new OvChipkaartOracleDaoImpl();
		
		OvChipkaart ovChipkaart_1 = new OvChipkaart(42000, "12-MAY-99", 2, 3, 4);
		OvChipkaart ovChipkaart_2 = new OvChipkaart(12345, "22-OCT-99", 3, 5, 7);
		OvChipkaart ovChipkaart_3 = new OvChipkaart(67890, "05-FEB-00", 4, 6, 8);
		
		Reiziger reiziger_1 = new Reiziger(6, "J", "", "Pols", "22-OCT-99");
		Reiziger reiziger_2 = new Reiziger(7, "G", "", "Pols", "12-SEP-91");
		Reiziger reiziger_3 = new Reiziger(8, "Maarten", "", "Pols", "10-MAY-89");
		
		ocodi.save(ovChipkaart_1);
		System.out.println(ocodi.findOvChipkaart(42000));
		
		ovChipkaart_1.setKlasse(8);
		ocodi.update(ovChipkaart_1);
		
		OvChipkaart ovChipkaart2 = ocodi.findOvChipkaart(42000);
		System.out.println(ovChipkaart2.getKlasse());
		
		ocodi.delete(ovChipkaart_1);
		
		ocodi.closeConnection();
		
//		ocodi.findOvChipkaart(19696);
		
//		ocodi.save(ovChipkaart);
		
		//ovChipkaart.setKlasse(10);
//		ocodi.update(ovChipkaart);
		
	//	ocodi.delete(ovChipkaart);
		
		//System.out.println(rodi.findOvChipkaart("46392"));
		
		/*
		

		
		rodi.save(reiziger_1);
		rodi.save(reiziger_2);
		rodi.save(reiziger_3);
		
		rodi.delete(reiziger_2); 
		rodi.update(reiziger_3);
		
		System.out.println("\nAlle Reizigers: \n" + rodi.findAll());
		System.out.println("\nReizigers met gbdatum '1999-10-22': \n" + rodi.findByGBdatum("1999-10-22"));	
		*/
		
	}
	
}
