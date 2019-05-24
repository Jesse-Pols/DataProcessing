package casus.p3.v1;

public interface ReizigerDao {
	
	public Reiziger getReizigerByReizigerId(int reizigerId, OvChipkaartOracleDaoImpl ocodi);
	
	public boolean save(Reiziger reiziger);
	public boolean update (Reiziger reiziger);
	public boolean delete (Reiziger reiziger);

}
