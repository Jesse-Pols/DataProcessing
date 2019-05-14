package casus.p3;

import casus.pojo.Product;

public interface ProductDao {
	
	public Product getProductByProductNr(int productNummer);
	
	public boolean save(Product product);
	public boolean update(Product product);
	public boolean delete(Product product);

}
