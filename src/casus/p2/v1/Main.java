package casus.p2.v1;

import java.sql.SQLException;
import java.util.List;

import casus.p2.v1.dao.OvChipkaartOracleDaoImpl;
import casus.p2.v1.dao.ReizigerOracleDaoImpl;
import casus.p2.v1.pojo.OvChipkaart;
import casus.p2.v1.pojo.Reiziger;

public class Main {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws SQLException {
		
		ReizigerOracleDaoImpl rodi = new ReizigerOracleDaoImpl();
		OvChipkaartOracleDaoImpl ocodi = new OvChipkaartOracleDaoImpl();

		Reiziger reiziger_1 = new Reiziger(6, "J", "", "Kanis", "22-OCT-99");		
		OvChipkaart ovChipkaart_1 = new OvChipkaart(42002, "12-MAY-99", 2, 3, 6);
		
		// Save
		rodi.save(reiziger_1);
		ocodi.save(ovChipkaart_1);
		
		// Update
		ovChipkaart_1.setKlasse(8);
		reiziger_1.setAchterNaam("Kanis");
		
		ocodi.update(ovChipkaart_1);
		rodi.update(reiziger_1);
		
		// Find all reizigers
		List<Reiziger> allReizigers = rodi.findAll();
		List<Reiziger> allReizigerByBirth = rodi.findByGBdatum("22-OCT-99");
		
		// Find OV Chipkaarten
		OvChipkaart ovChipkaart = ocodi.findOvChipkaart(68514);
		List<OvChipkaart> ovChipkaartByReiziger = ocodi.findByReiziger(reiziger_1);
		
		// Delete
		ocodi.delete(ovChipkaart_1);
		rodi.delete(reiziger_1);
		
		ocodi.closeConnection();
		
	}
	
}
