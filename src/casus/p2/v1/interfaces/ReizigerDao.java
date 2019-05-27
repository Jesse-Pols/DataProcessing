package casus.p2.v1.interfaces;

import java.util.List;

import casus.p2.v1.pojo.Reiziger;

public interface ReizigerDao {
	
	public List<Reiziger> findAll();
	public List<Reiziger> findByGBdatum(String gbdatum);
	
	public boolean save(Reiziger reiziger);
	public boolean update(Reiziger reiziger);
	public boolean delete(Reiziger reiziger);
	
}
