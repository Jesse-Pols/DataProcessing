package casus.p3;

import casus.pojo.OvChipkaart;

public interface OvChipkaartDao {
	
	public OvChipkaart getOvChipkaartById (int kaartNummer) {
		
		OvChipkaart ovChipkaart = null;
		
		String query = String.format("SELECT * FROM OV_CHIPKAART WHERE KAARTNUMMER", args)
		
	}
	
	public boolean save (OvChipkaart ovChipkaart);
	public boolean update (OvChipkaart ovChipkaart);
	public boolean delete (OvChipkaart ovChipkaart);

}
