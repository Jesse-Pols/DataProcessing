package casus.p2.v2.interfaces;

import java.util.ArrayList;

import casus.p2.v2.pojo.OvChipkaart;
import casus.p2.v2.pojo.Reiziger;

public interface OvChipkaartDao {

	public OvChipkaart find(int kaartNummer);
	public ArrayList<OvChipkaart> findAll();
	public ArrayList<OvChipkaart> findByReiziger(Reiziger reiziger);
	
	public boolean save(OvChipkaart ovChipkaart);
	public boolean update(OvChipkaart ovChipkaart);
	public boolean delete(OvChipkaart ovChipkaart);
	
}
