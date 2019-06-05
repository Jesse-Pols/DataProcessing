package hu.nl.hibernate.interfaces;

import hu.nl.hibernate.pojo.OvChipkaart;

public interface OvChipkaartDao {

	//public OvChipkaart find(int kaartNummer);
	//public ArrayList<OvChipkaart> findAll();
	//public ArrayList<OvChipkaart> findByReiziger(Reiziger reiziger);
	
	public boolean save(OvChipkaart ovChipkaart);
	public boolean update(OvChipkaart ovChipkaart);
	public boolean delete(OvChipkaart ovChipkaart);
	
}
