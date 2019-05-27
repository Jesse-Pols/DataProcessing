package casus.p2.v2.pojo;

import java.util.ArrayList;

public class Reiziger {
	
	private int reizigerId;
	private String voorLetters;
	private String tussenVoegsel;
	private String achterNaam;
	private String gbDatum;
	
	private ArrayList<OvChipkaart> ovChipkaarten = null;
	
	public Reiziger(int reizigerId, String voorLetters, String tussenVoegsel, String achterNaam, String gbDatum) {
		this.reizigerId = reizigerId;
		this.voorLetters = voorLetters;
		this.tussenVoegsel = tussenVoegsel;
		this.achterNaam = achterNaam;
		this.gbDatum = gbDatum;
		
		ovChipkaarten = new ArrayList<OvChipkaart>();
	}

	public int getReizigerId() { return reizigerId; }
	public void setReizigerId(int reizigerId) {	this.reizigerId = reizigerId; }
	
	public String getVoorLetters() { return voorLetters; }
	public void setVoorLetters(String voorLetters) { this.voorLetters = voorLetters; }
	
	public String getTussenVoegsel() {	return tussenVoegsel; }
	public void setTussenVoegsel(String tussenVoegsel) { this.tussenVoegsel = tussenVoegsel; }

	public String getAchterNaam() { return achterNaam; }
	public void setAchterNaam(String achterNaam) { this.achterNaam = achterNaam; }

	public String getGbDatum() { return gbDatum; }
	public void setGbDatum(String gbDatum) { this.gbDatum = gbDatum; }

	public ArrayList<OvChipkaart> getOvChipkaarten() { return ovChipkaarten; }
	public void setOvChipkaarten(ArrayList<OvChipkaart> ovChipkaarten) { this.ovChipkaarten = ovChipkaarten; }
	
	public String toString() {
		String s = "";
		s += "ReizigerId: " + this.reizigerId;
		s += "\nVoorletters: " + this.voorLetters;
		s += "\nTussenvoegsel: " + this.tussenVoegsel;
		s += "\nAchternaam: " + this.achterNaam;
		s += "\nGeboortedatum: " + this.gbDatum;
		s += "\nOvChipkaarten: \n";
		for (OvChipkaart ovChipkaart : this.ovChipkaarten)
			s += ovChipkaart.getKaartNummer() + " ";
		
		return s + "\n";
	}

}
