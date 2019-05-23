package casus.p3.v2.interfaces;

import casus.p3.v2.pojo.Product;

public interface ProductDao {
	
	public Product getProductByProductNr(int productNummer);
	
	public boolean save(Product product);
	public boolean update(Product product);
	public boolean delete(Product product);

}
