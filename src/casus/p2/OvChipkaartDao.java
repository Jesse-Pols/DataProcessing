package casus.p2;

import java.util.List;

public interface OvChipkaartDao {
	
	public OvChipkaart findOvChipkaart(int kaartNummer);
	public List<OvChipkaart> findByReiziger(Reiziger reiziger);
	
	public boolean save(OvChipkaart ovChipkaart);
	public boolean update(OvChipkaart ovChipkaart);
	public boolean delete(OvChipkaart ovChipkaart);
	
}
