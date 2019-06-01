package hu.nl.hibernate;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
  private static SessionFactory factory;
  public static void main(String[] args) throws SQLException, ParseException {
	  
      try {
        factory = new Configuration().configure().buildSessionFactory();
      } catch (Throwable ex) {
        System.err.println("Failed to create sessionFactory object." + ex);
        throw new ExceptionInInitializerError(ex);
      }
      Session session = factory.openSession();
      
      // Transaction for saving a Log
      Transaction t = session.beginTransaction();      
      Log log = new Log(1,"Hibernate works!");
      session.save(log);
      t.commit();  
      System.out.println("successfully saved");
      
      // CRUD without Annotations
      t = session.beginTransaction();
      Reiziger reiziger = new Reiziger(54321, "L", "vVeen");
      
      // Create
      session.save(reiziger);
      
      // Read
      String sql = "select new "
      		+ "hu.nl.hibernate.Reiziger("
      		+ "r.reizigerid,"
      		+ "r.voorletters,"
      		+ "r.achternaam"
      		+ ") from Reiziger r";
      List<Reiziger> reizigers = session.createQuery(sql, Reiziger.class).getResultList();
      for (Reiziger tempReiziger : reizigers)
    	  System.out.println("Reiziger in List: " + tempReiziger);
      
      // Update
      reiziger.setAchterNaam("VanVeen");
      session.update(reiziger);
      
      // Delete
      session.delete(reiziger);
      
      // Commit
      t.commit();
      System.out.println("Success");     
      
      // CRUD with Annotations
      t = session.beginTransaction();
      OvChipkaart ovChipkaart = new OvChipkaart(4, 2);
      
      // Create
      session.save(ovChipkaart);
      
      // Read
      String sql2 = "select new "
        		+ "hu.nl.hibernate.OvChipkaart("
        		+ "o.kaartnummer,"
        		+ "o.klasse"
        		+ ") from OvChipkaart o";
        List<OvChipkaart> ovChipkaarten = session.createQuery(sql2, OvChipkaart.class).getResultList();
        for (OvChipkaart tempOvChipkaart : ovChipkaarten) {
        	System.out.println("OvChipkaart in List: " + tempOvChipkaart);
        }    	  
      
        // Update
        ovChipkaart.setKlasse(1);
        session.update(ovChipkaart);
        
        // Delete
        session.delete(ovChipkaart);
      
      // Commit
      t.commit();
      System.out.println("Success");
      
      factory.close();  
      session.close();   
      
  }  
}
