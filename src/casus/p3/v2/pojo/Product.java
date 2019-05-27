package casus.p3.v2.pojo;

import java.util.ArrayList;

public class Product {
	
	private int productNummer;
	private String productNaam;
	private String beschrijving;
	private double prijs;
	
	private ArrayList<OvChipkaart> ovChipkaarten;
	
	public Product(int productNummer, String productNaam, String beschrijving, double prijs) {
		this.productNummer = productNummer;
		this.productNaam = productNaam;
		this.beschrijving = beschrijving;
		this.prijs = prijs;
		
		ovChipkaarten = new ArrayList<OvChipkaart>();
	}
	
	public void addOvChipkaart(OvChipkaart ovChipkaart) {
		ovChipkaarten.add(ovChipkaart);
	}
	
	public void removeOvChipkaart(OvChipkaart ovChipkaart) {
		ovChipkaarten.remove(ovChipkaart);
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

	public double getPrijs() {
		return prijs;
	}

	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}	
	
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
		for (OvChipkaart ovChipkaart : this.ovChipkaarten)
			s += ovChipkaart.getKaartNummer() + " ";
		
		return s + "\n";
	}

}
