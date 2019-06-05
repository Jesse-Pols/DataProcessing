package hu.nl.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class OracleBaseDao {
	
	private static SessionFactory factory;
	Session session = getConnection();
	
	public Session getConnection() {
		
		try {
			OracleBaseDao.factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		return factory.openSession();
		
	}
	
	public void closeConnection() {
		
		try {
			OracleBaseDao.factory.close();
			this.session.close();
		} catch (Throwable ex) {
			System.err.println("Failed to close sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		
	}

}
