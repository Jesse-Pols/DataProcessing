package casus.p2.v2.interfaces;

import java.util.ArrayList;

import casus.p2.v2.pojo.Reiziger;

public interface ReizigerDao {
	
	public Reiziger find(int reizigerId);
	public ArrayList<Reiziger> findAll();
	public ArrayList<Reiziger> findByGbDatum(String gbdatum);
	
	public boolean save(Reiziger reiziger);
	public boolean update(Reiziger reiziger);
	public boolean delete(Reiziger reiziger);
}
