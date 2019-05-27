package casus.p3.v3.pojo;

import java.util.ArrayList;

public class Product {
	
	private int productNummer;
	private ArrayList<OvChipkaart> ovChipkaarten;
	
	private String productNaam;
	private String beschrijving;
	private double prijs;	
	
	public Product(int productnummer){
		this.productNummer = productnummer;
		ovChipkaarten = new ArrayList<OvChipkaart>();
	}
	
	public void setProductNummer(int productNummer) { this.productNummer = productNummer; }
	public int getProductNummer() { return this.productNummer; }
	
	public void setProductNaam(String productNaam) { this.productNaam = productNaam; }
	public String getProductNaam() { return this.productNaam; }
	
	public void setBeschrijving(String beschrijving) { this.beschrijving = beschrijving; }
	public String getBeschrijving() { return this.beschrijving; }
	
	public void setPrijs(double prijs) { this.prijs = prijs; }
	public double getPrijs() { return this.prijs; }
	
	public void addOvChipkaart(OvChipkaart ovChipkaart) { ovChipkaarten.add(ovChipkaart); }
	public void removeOvChipkaart(OvChipkaart ovChipkaart) { ovChipkaarten.remove(ovChipkaart); }
	public ArrayList<OvChipkaart> getOvChipkaarten() { return ovChipkaarten; }	
	
	public boolean equals(Object object) {
		if (object instanceof Product) {
			Product product = (Product) object;
			
			if (product.getProductNummer() == this.productNummer &&
					product.getProductNaam().equals(this.productNaam) &&
					product.getBeschrijving().equals(this.beschrijving) &&
					product.getPrijs() == this.prijs)
				return true;
		}
		
		return false;
	}
	
	public String toString() {
		String s = "";
		s += "Productnummer: " + this.productNummer;
		s += "\nProductnaam: " + this.productNaam;
		s += "\nBeschrijving: " + this.beschrijving;
		s += "\nPrijs: " + this.prijs;
		s += "\nOvChipkaarten: ";
		for (OvChipkaart ovChipkaart : ovChipkaarten) {
			s += ovChipkaart.getKaartNummer();
		}
		
		return s;
	}

}
