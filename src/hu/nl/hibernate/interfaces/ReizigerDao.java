package hu.nl.hibernate.interfaces;

import hu.nl.hibernate.pojo.Reiziger;

public interface ReizigerDao {
	
	//public Reiziger find(int reizigerId);
	//public ArrayList<Reiziger> findAll();
	//public ArrayList<Reiziger> findByGbDatum(String gbdatum);
	
	public boolean save(Reiziger reiziger);
	public boolean update(Reiziger reiziger);
	public boolean delete(Reiziger reiziger);
}
