package hu.nl.hibernate.interfaces;

import java.util.List;

import hu.nl.hibernate.pojo.OvChipkaart;

public interface OvChipkaartDao {

	public List<OvChipkaart> findAll();
	public List<OvChipkaart> findByReizigerId(int reizigerId);
	public OvChipkaart findById(int kaartNummer);
	
	public boolean save(OvChipkaart ovChipkaart);
	public boolean update(OvChipkaart ovChipkaart);
	public boolean delete(OvChipkaart ovChipkaart);
	
}
