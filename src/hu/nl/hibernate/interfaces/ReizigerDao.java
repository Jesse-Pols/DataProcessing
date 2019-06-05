package hu.nl.hibernate.interfaces;

import java.util.List;

import hu.nl.hibernate.pojo.Reiziger;

public interface ReizigerDao {
	
	public List<Reiziger> findAll();
	public Reiziger findById(int reizigerId);
	
	public boolean save(Reiziger reiziger);
	public boolean update(Reiziger reiziger);
	public boolean delete(Reiziger reiziger);
}
