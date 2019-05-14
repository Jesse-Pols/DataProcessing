package casus.p3;

import java.util.List;

import casus.pojo.OvChipkaartProduct;

public interface OvChipkaartProductDao {
	
	public List<Integer> getProductNummerByKaartNummer(int kaartNummer);
	public List<Integer> getKaartNummerByProductNummer(int productNummer);
	
	public boolean save (OvChipkaartProduct ovChipkaartProduct);
	public boolean update (OvChipkaartProduct ovChipkaartProduct);
	public boolean delete (OvChipkaartProduct ovChipkaartProduct);

}
