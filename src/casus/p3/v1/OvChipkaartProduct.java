package casus.p3.v1;

public class OvChipkaartProduct {
	
	private int ovproductID;
	private int kaartNummer;
	private int productNummer;
	private String reisproductStatus;
	private String lastUpdate;
	
	private OvChipkaart ovChipkaart;
	private Product product;
	
	public OvChipkaartProduct (int kaartNummer, int productNummer) {
		this.kaartNummer = kaartNummer;
		this.productNummer = productNummer;
	}

	public OvChipkaart getOvChipkaart() {
		return ovChipkaart;
	}

	public void setOvChipkaart(OvChipkaart ovChipkaart) {
		this.ovChipkaart = ovChipkaart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getOvproductID() {
		return ovproductID;
	}

	public void setOvproductID(int ovproductID) {
		this.ovproductID = ovproductID;
	}

	public String getReisproductStatus() {
		return reisproductStatus;
	}

	public void setReisproductStatus(String reisproductStatus) {
		this.reisproductStatus = reisproductStatus;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getKaartNummer() {
		return kaartNummer;
	}

	public void setKaartNummer(int kaartNummer) {
		this.kaartNummer = kaartNummer;
	}

	public int getProductNummer() {
		return productNummer;
	}

	public void setProductNummer(int productNummer) {
		this.productNummer = productNummer;
	}	

}
