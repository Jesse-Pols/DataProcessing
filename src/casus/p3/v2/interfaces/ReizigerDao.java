package casus.p3.v2.interfaces;

import casus.p3.v2.pojo.Reiziger;

public interface ReizigerDao {
	
	//public Reiziger getReizigerByReizigerId(int reizigerId, OvChipkaartOracleDaoImpl ocodi);
	
	public boolean save(Reiziger reiziger);
	public boolean update (Reiziger reiziger);
	public boolean delete (Reiziger reiziger);

}
