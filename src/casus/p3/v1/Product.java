package casus.p3.v1;

import java.util.List;

public class Product {
	
	private int productNummer;
	private String productNaam;
	private String beschrijving;
	private float prijs;
	
	private List<OvChipkaartProduct> ovChipkaartProducten;
	
	public Product(int productNummer, String productNaam, String beschrijving, float prijs) {
		this.productNummer = productNummer;
		this.productNaam = productNaam;
		this.beschrijving = beschrijving;
		this.prijs = prijs;
	}

	public int getProductNummer() {
		return productNummer;
	}

	public void setProductNummer(int productNummer) {
		this.productNummer = productNummer;
	}

	public String getProductNaam() {
		return productNaam;
	}

	public void setProductNaam(String productNaam) {
		this.productNaam = productNaam;
	}

	public String getBeschrijving() {
		return beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	public float getPrijs() {
		return prijs;
	}

	public void setPrijs(float prijs) {
		this.prijs = prijs;
	}

	public List<OvChipkaartProduct> getOvChipkaartProducten() {
		return ovChipkaartProducten;
	}

	public void setOvChipkaartProducten(List<OvChipkaartProduct> ovChipkaartProducten) {
		this.ovChipkaartProducten = ovChipkaartProducten;
	}
	
	

}
