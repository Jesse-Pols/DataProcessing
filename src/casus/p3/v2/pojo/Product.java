package casus.p3.v2.pojo;

import java.util.ArrayList;
import java.util.List;

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

}
