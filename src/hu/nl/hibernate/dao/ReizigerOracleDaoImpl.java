package hu.nl.hibernate.dao;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hu.nl.hibernate.interfaces.ReizigerDao;
import hu.nl.hibernate.pojo.Reiziger;

public class ReizigerOracleDaoImpl extends OracleBaseDao implements ReizigerDao {
	
	private static final String sqlObject = "select new hu.nl.hibernate.pojo.Reiziger(r.reizigerid, r.voorletters, r.tussenvoegsel, r.achternaam, r.gebortedatum) from Reiziger r ";
	
	@SuppressWarnings("unchecked")
	public List<Reiziger> findAll() {
		List<Reiziger> reizigers = null;
		OvChipkaartOracleDaoImpl odoci = new OvChipkaartOracleDaoImpl();
		
		try {
			reizigers = session.createQuery(sqlObject).getResultList();
		} catch (Exception ex)
		{ System.out.println("ReizigerOracleDaoImpl/findAll() Query Failed: " + ex); }	
		
		try {
			for (Reiziger reiziger : reizigers)
				reiziger.setOvChipkaarten(odoci.findByReizigerId(reiziger.getReizigerId()));
		} catch (Exception ex)
		{ System.out.println("ReizigerOracleDaoImpl/findAll() Failed: " + ex); }
		
		return reizigers;
		
	}
	
	
	@SuppressWarnings("rawtypes")
	public Reiziger findById(int reizigerId) {		
		Reiziger reiziger = null;
		OvChipkaartOracleDaoImpl odoci = new OvChipkaartOracleDaoImpl();
		
		try {
			Query query = session.createQuery(sqlObject + " where reizigerid = ?1");
			query.setParameter(1, reizigerId);
			reiziger = (Reiziger) query.getResultList().get(0);
		} catch (Exception ex)
		{ System.out.println("ReizigerOracleDaoImpl/find() Query Failed: " + ex); }
		
		try {
			reiziger.setOvChipkaarten(odoci.findByReizigerId(reiziger.getReizigerId()));
		} catch (Exception ex)
		{ System.out.println("ReizigerOracleDaoImpl/find() Failed: " + ex); }
		
		return reiziger;
		
	}
	
	
	public boolean save (Reiziger reiziger) {
		
		try {
			Transaction t = session.beginTransaction();
			session.save(reiziger);
			t.commit();
		} catch (Exception ex)
		{ System.out.println("ReizigerOracleDaoImpl/save() Failed: " + ex); return false; }
		return true;
		
	}
	
	public boolean update (Reiziger reiziger) {
		
		try {
			Transaction t = session.beginTransaction();
			session.update(reiziger);
			t.commit();
		} catch (Exception ex)
		{ System.out.println("ReizigerOracleDaoImpl/update() Failed: " + ex); return false; }
		return true;
		
	}
	
	public boolean delete (Reiziger reiziger) {
		
		try {
			Transaction t = session.beginTransaction();
			session.delete(reiziger);
			t.commit();
		} catch (Exception ex)
		{ System.out.println("ReizigerOracleDaoImpl/delete() Failed: " + ex); return false; }
		return true;
		
	}
	
}
