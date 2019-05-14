package casus.p3;

import java.util.List;

import casus.pojo.OvChipkaart;

public interface OvChipkaartDao {
	
	public OvChipkaart getOvChipkaartByKaartNr (int kaartNummer, ReizigerOracleDaoImpl rodi);
	public List<OvChipkaart> getOvChipkaartByReizigerId (int reizigerId);
	
	public boolean save (OvChipkaart ovChipkaart);
	public boolean update (OvChipkaart ovChipkaart);
	public boolean delete (OvChipkaart ovChipkaart);

}
