package casus.p2;

import java.util.List;

public interface ReizigerDao {
	
	public List<Reiziger> findAll();
	public List<Reiziger> findByGBdatum(String gbdatum);
	
	public boolean save(Reiziger reiziger);
	public boolean update(Reiziger reiziger);
	public boolean delete(Reiziger reiziger);
	
}
