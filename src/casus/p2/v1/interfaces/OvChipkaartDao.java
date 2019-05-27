package casus.p2.v1.interfaces;

import java.util.List;

import casus.p2.v1.pojo.OvChipkaart;
import casus.p2.v1.pojo.Reiziger;

public interface OvChipkaartDao {
	
	public OvChipkaart findOvChipkaart(int kaartNummer);
	public List<OvChipkaart> findByReiziger(Reiziger reiziger);
	
	public boolean save(OvChipkaart ovChipkaart);
	public boolean update(OvChipkaart ovChipkaart);
	public boolean delete(OvChipkaart ovChipkaart);
	
}
