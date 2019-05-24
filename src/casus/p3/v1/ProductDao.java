package casus.p3.v1;

public interface ProductDao {
	
	public Product getProductByProductNr(int productNummer);
	
	public boolean save(Product product);
	public boolean update(Product product);
	public boolean delete(Product product);

}
