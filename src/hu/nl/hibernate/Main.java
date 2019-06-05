package hu.nl.hibernate;

import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import hu.nl.hibernate.dao.OvChipkaartOracleDaoImpl;
import hu.nl.hibernate.dao.ReizigerOracleDaoImpl;
import hu.nl.hibernate.pojo.OvChipkaart;
import hu.nl.hibernate.pojo.Reiziger;

public class Main {
  public static void main(String[] args) throws SQLException, ParseException {
      
      OvChipkaartOracleDaoImpl odoci = new OvChipkaartOracleDaoImpl();
      ReizigerOracleDaoImpl rodi = new ReizigerOracleDaoImpl();  
      
      List<OvChipkaart> allOvChipkaarten = odoci.findAll();
      List<OvChipkaart> allOvChipkaartenByReizigerId = odoci.findByReizigerId(2);
      List<Reiziger> allReizigers = rodi.findAll();
      
      Reiziger r_1 = rodi.findById(2);      
      OvChipkaart ov_1 = odoci.findById(79625);
      
      System.out.println("\nAlle ovchipkaarten: \n" + allOvChipkaarten);
      System.out.println("\nAlle Reizigers: \n" + allReizigers);
      System.out.println("\nReiziger met reizigerid 2: \n" + r_1);
      System.out.println("\nOvkaart met kaartnummer 79625: \n" + ov_1);
      System.out.println("\nAlle Ovkaarten met reizigerid 2: \n" + allOvChipkaartenByReizigerId);
      
      Reiziger reiziger = new Reiziger(6, "L", "van", "Veen", new Date());
      OvChipkaart ovChipkaart = new OvChipkaart(12345, new Date(), 2, 100.05, reiziger);
      ovChipkaart.setReiziger(reiziger);
      
      rodi.save(reiziger);
      odoci.save(ovChipkaart);
      
      reiziger.setTussenvoegsel("v");
      rodi.update(reiziger);
      
      ovChipkaart.setKlasse(1);
      odoci.update(ovChipkaart);
      
      odoci.delete(ovChipkaart);
      rodi.delete(reiziger);     
      
      rodi.closeConnection();
      System.out.println("Success!");   
      System.exit(0);
      
  }  
}
