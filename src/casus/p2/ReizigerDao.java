package casus.p2;

import java.sql.Connection;
import java.util.List;

public interface ReizigerDao {
	
	public List<Reiziger> findAll();
	public List<Reiziger> findByGBdatum(String gbdatum);
	
	public Reiziger save(Reiziger reiziger);
	public Reiziger update(Reiziger reiziger);
	public boolean delete(Reiziger reiziger);
	
}
