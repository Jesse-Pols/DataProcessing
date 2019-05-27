package casus.p3.v3.pojo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OvChipkaart {
	
	private int kaartNummer;
	private Date geldigTot;
	private int klasse;
	private double saldo;
	
	private Reiziger reiziger;
	private ArrayList<Product> producten;
	
	public OvChipkaart (int kaartNummer, Date geldigTot, int klasse, double saldo) {
		this.kaartNummer = kaartNummer;
		this.geldigTot = geldigTot;
		this.klasse = klasse;
		this.saldo = saldo;
		producten = new ArrayList<Product>();
	}

	public int getKaartNummer() {
		return kaartNummer;
	}

	public void setKaartNummer(int kaartNummer) {
		this.kaartNummer = kaartNummer;
	}

	public Date getGeldigTot() {
		return geldigTot;
	}

	public void setGeldigTot(Date geldigTot) {
		this.geldigTot = geldigTot;
	}

	public int getKlasse() {
		return klasse;
	}

	public void setKlasse(int klasse) {
		this.klasse = klasse;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public Reiziger getReiziger() {
		return reiziger;
	}
	
	public void setReiziger(Reiziger reiziger) {
		this.reiziger = reiziger;
	}
	
	public List<Product> getProducten() {
		return producten;
	}
	
	public void addProduct(Product product) {
		this.producten.add(product);
	}
	
	public void removeProduct(Product product) {
		this.producten.remove(product);
	}
	
	public String toString() {
		String s = "";
		s += "Kaartnummer: " + this.kaartNummer;
		s += "\nGeldig tot: " + this.geldigTot;
		s += "\nKlasse: " + this.klasse;
		s += "\nSaldo: " + this.saldo;
		
		s += "\nReiziger: " + this.reiziger.getVoorletters() + " ";
		if (this.reiziger.getTussenvoegsel() != null || this.reiziger.getTussenvoegsel() == "")
			s += this.reiziger.getTussenvoegsel() + " ";
		s += this.reiziger.getAchternaam();
		
		s += "\nProducten: ";
		for (Product product : this.producten)
			s += product.getProductNaam() + " " + product.getProductNummer();		
		
		return s + "\n";
	}

}
