package casus.p3;

import java.util.List;

import casus.pojo.OvChipkaart;
import casus.pojo.Reiziger;

public interface ReizigerDao {
	
	public List<OvChipkaart> getOvChipkaartByReiziger(int reizigerId);
	
	public boolean save(Reiziger reiziger);
	public boolean update (Reiziger reiziger);
	public boolean delete (Reiziger reiziger);

}
