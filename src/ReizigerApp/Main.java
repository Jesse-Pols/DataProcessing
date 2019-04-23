package ReizigerApp;

import java.sql.Date;

public class Main {
	public static void main(String[] args) {
		
		ReizigerOracleDaoImpl rodi = new ReizigerOracleDaoImpl();
		
		Reiziger reiziger_1 = new Reiziger("Jesse Pols", Date.valueOf("1999-10-22"));
		Reiziger reiziger_2 = new Reiziger("Geerten Pols", Date.valueOf("1991-7-12"));
		Reiziger reiziger_3 = new Reiziger("Maarten Pols", Date.valueOf("1989-5-10"));
		
		rodi.save(reiziger_1);
		rodi.save(reiziger_2);
		rodi.save(reiziger_3);
		
		rodi.delete(reiziger_2);
		rodi.update(reiziger_3);
		
		System.out.println("\nAlle Reizigers: \n" + rodi.findAll());
		System.out.println("\nReizigers met gbdatum '1999-10-22': \n" + rodi.findByGBdatum("1999-10-22"));	
		
	}
	
}
