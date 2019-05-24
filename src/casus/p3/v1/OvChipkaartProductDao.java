package casus.p3.v1;

import java.util.List;

public interface OvChipkaartProductDao {
	
	public List<Integer> getProductNummerByKaartNummer(int kaartNummer);
	public List<Integer> getKaartNummerByProductNummer(int productNummer);
	
	public boolean save (OvChipkaartProduct ovChipkaartProduct);
	public boolean update (OvChipkaartProduct ovChipkaartProduct);
	public boolean delete (OvChipkaartProduct ovChipkaartProduct);

}
