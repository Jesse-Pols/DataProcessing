package hu.nl.hibernate.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="OV_CHIPKAART")
public class OvChipkaart {

	@Id
	private int kaartnummer;
	
	private int klasse;
	private double saldo;	
	private Date geldigtot;
	
	@ManyToOne()
	@JoinColumn(name = "reizigerid", nullable = false)
	private Reiziger reiziger;
	
	public OvChipkaart (int kaartnummer, Date geldigtot, int klasse, double saldo, Reiziger reiziger) {
		this.kaartnummer = kaartnummer;
		this.geldigtot = geldigtot;
		this.klasse = klasse;
		this.saldo = saldo;
		this.reiziger = reiziger;
	}

	public int getKaartNummer() { return this.kaartnummer; }
	public void setKaartNummer(int kaartnummer) { this.kaartnummer = kaartnummer; }

	public Date getGeldigTot() { return this.geldigtot; }
	public void setGeldigTot(Date geldigtot) { this.geldigtot = geldigtot; }

	public int getKlasse() { return this.klasse;	}
	public void setKlasse(int klasse) { this.klasse = klasse; }

	public double getSaldo() { return this.saldo; }
	public void setSaldo(double saldo) { this.saldo = saldo; }
	
	public Reiziger getReiziger() {	return reiziger; }	
	public void setReiziger(Reiziger reiziger) { this.reiziger = reiziger; }
	
	public String toString() {
		String s = "";
		s += "Kaartnummer: " + this.kaartnummer;
		s += "\nGeldig tot: " + this.geldigtot;
		s += "\nKlasse: " + this.klasse;
		s += "\nSaldo: " + this.saldo;
		
		try {
			s += "\nReiziger: " + this.reiziger.getVoorletters() + " ";
			if (this.reiziger.getTussenvoegsel() != null || this.reiziger.getTussenvoegsel() == "")
				s += this.reiziger.getTussenvoegsel() + " ";
			s += this.reiziger.getAchternaam();		
		} catch (Exception ex)
		{ System.out.println("No Reiziger Defined in this ovchipkaart -> toString() Failed: " + ex); }
		
		return s + "\n";
	}
	
	public boolean equals(Object object) {
		if (object instanceof OvChipkaart) {
			OvChipkaart ovChipkaart = (OvChipkaart) object;
			
			if (	ovChipkaart.getKaartNummer() == this.kaartnummer &&
					ovChipkaart.getGeldigTot().equals(this.geldigtot) &&
					ovChipkaart.getSaldo() == this.saldo &&
					ovChipkaart.getKlasse() == this.klasse &&
					ovChipkaart.getReiziger().equals(this.reiziger))
				return true;
		}
		return false;
	}
}
