package hu.nl.hibernate.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Transaction;

import hu.nl.hibernate.interfaces.OvChipkaartDao;
import hu.nl.hibernate.pojo.OvChipkaart;

public class OvChipkaartOracleDaoImpl extends OracleBaseDao implements OvChipkaartDao {
	
	private static final String sqlObject = "select new hu.nl.hibernate.pojo.OvChipkaart(ov.kaartnummer, ov.geldigtot, ov.klasse, ov.saldo, ov.reiziger) from OvChipkaart ov ";

	@SuppressWarnings("unchecked")
	public List<OvChipkaart> findAll(){
		List<OvChipkaart> ovChipkaarten = null;
		
		try {
			ovChipkaarten = session.createQuery(sqlObject).getResultList();
		} catch (Exception ex)
		{ System.out.println("OvChipkaartOracleDaoImpl/findAll() Query Failed: " + ex); }
		
		return ovChipkaarten;	
		
	}
	
	@SuppressWarnings("unchecked")
	public List<OvChipkaart> findByReizigerId(int reizigerId) {
		
		List<OvChipkaart> ovChipkaarten = null;
		
		try {
			Query query = session.createQuery(sqlObject + " where ov.reiziger.reizigerid = ?1");
			query.setParameter(1, reizigerId);
			ovChipkaarten = query.getResultList();
		} catch (Exception ex)
		{ System.out.println("ReizigerOracleDaoImpl/find() Query Failed: " + ex); }
		
		return ovChipkaarten;
		
	}
	
	public OvChipkaart findById(int kaartNummer) {
		OvChipkaart ovChipkaart = null;
		
		try {
			Query query = session.createQuery(sqlObject + "where kaartnummer = ?1");
			query.setParameter(1, kaartNummer);
			ovChipkaart = (OvChipkaart) query.getResultList().get(0);
		} catch (Exception ex)
		{ System.out.println("OvChipkaartOracleDaoImpl/findById() Query Failed: " + ex); }
		
	    return ovChipkaart;
		
	}
	
	public boolean save (OvChipkaart ovChipkaart) {
		
		try {
			Transaction t = session.beginTransaction();
			session.save(ovChipkaart);
			t.commit();
		} catch (Exception ex)
		{ System.out.println("OvChipkaartOracleDaoImpl/save() Failed: " + ex); return false; }
		return true;
		
	}
	
	public boolean update (OvChipkaart ovChipkaart) {
		
		try {
			Transaction t = session.beginTransaction();
			session.update(ovChipkaart);
			t.commit();
		} catch (Exception ex)
		{ System.out.println("OvChipkaartOracleDaoImpl/update() Failed: " + ex); return false; }
		return true;
		
	}
	
	public boolean delete (OvChipkaart ovChipkaart) {
		
		try {
			Transaction t = session.beginTransaction();
			session.delete(ovChipkaart);
			t.commit();
		} catch (Exception ex)
		{ System.out.println("OvChipkaartOracleDaoImpl/delete() Failed: " + ex); return false; }
		return true;
		
	}

}
