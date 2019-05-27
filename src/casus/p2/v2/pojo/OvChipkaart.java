package casus.p2.v2.pojo;

import casus.p2.v2.pojo.Reiziger;

public class OvChipkaart {
	
	private int kaartNummer;
	private int klasse;
	private double saldo;
	private String geldigTot;
	
	private Reiziger reiziger;
	
	public OvChipkaart (int kaartNummer, String geldigTot, int klasse, double saldo) {
		this.kaartNummer = kaartNummer;
		this.geldigTot = geldigTot;
		this.klasse = klasse;
		this.saldo = saldo;
	}

	public int getKaartNummer() { return kaartNummer; }
	public void setKaartNummer(int kaartNummer) { this.kaartNummer = kaartNummer; }

	public String getGeldigTot() { return geldigTot; }
	public void setGeldigTot(String geldigTot) { this.geldigTot = geldigTot; }

	public int getKlasse() { return klasse; }
	public void setKlasse(int klasse) { this.klasse = klasse; }

	public double getSaldo() { return saldo;	}
	public void setSaldo(double saldo) { this.saldo = saldo;	}

	public Reiziger getReiziger() { return reiziger; }
	public void setReiziger(Reiziger reiziger) { this.reiziger = reiziger; }	
	
	public String toString() {
		String s = "";
		s += "Kaartnummer: " + this.kaartNummer;
		s += "\nKlasse: " + this.klasse;
		s += "\nSaldo: " + this.saldo;
		s += "\nGeldig tot: " + this.geldigTot;
		s += "\nReiziger: " + reiziger.getVoorLetters() + " ";
		if (reiziger.getTussenVoegsel() != null || reiziger.getTussenVoegsel() != "")
			s += reiziger.getTussenVoegsel() + " ";
		s += reiziger.getAchterNaam();
		
		return s + "\n";
	}

}
