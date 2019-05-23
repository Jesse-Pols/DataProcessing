package casus.p3.v2.interfaces;

import casus.p3.v2.pojo.OvChipkaart;

public interface OvChipkaartDao {
	
	//public OvChipkaart getOvChipkaartByKaartNr (int kaartNummer, ReizigerOracleDaoImpl rodi);
	//public List<OvChipkaart> getOvChipkaartByReizigerId (int reizigerId);
	
	public boolean save (OvChipkaart ovChipkaart);
	//public boolean update (OvChipkaart ovChipkaart);
	//public boolean delete (OvChipkaart ovChipkaart);

}
