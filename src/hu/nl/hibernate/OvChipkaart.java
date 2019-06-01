package hu.nl.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="OV_CHIPKAART")
public class OvChipkaart {
	
	@Id
	private int kaartnummer;
	@Column
	private int klasse;
	
	public OvChipkaart(int kaartnummer, int klasse) {
		this.kaartnummer = kaartnummer;
		this.klasse = klasse;
	}
	
	public int getKaartnummer() {
		return this.kaartnummer;
	}
	
	public void setKaartnummer(int kaartnummer) {
		this.kaartnummer = kaartnummer;
	}
	
	public int getKlasse() {
		return this.klasse;
	}
	
	public void setKlasse(int klasse) {
		this.klasse = klasse;
	}
	
	public String toString() {
		String s = "";
		s += "\nKaartnummer: " + this.kaartnummer;
		s += "\nKlasse: " + this.klasse;
		return s + "\n";
	}

}
