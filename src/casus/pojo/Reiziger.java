package casus.pojo;

import java.util.List;

public class Reiziger {
	
	private int reizigerId;
	private String voorletters;
	private String tussenvoegsel;
	private String achternaam;
	private String geboortedatum;
	
	private List<OvChipkaart> ovChipkaarten;
	private List<Adres> addresses;

	public Reiziger (int reizigerId, String voorletters, String tussenvoegsel, String achternaam, String geboortedatum) {
		this.reizigerId = reizigerId;
		this.voorletters = voorletters;
		this.tussenvoegsel = tussenvoegsel;
		this.achternaam = achternaam;
		this.geboortedatum = geboortedatum;
	}
	
	public List<Adres> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Adres> addresses) {
		this.addresses = addresses;
	}

	public int getReizigerId() {
		return reizigerId;
	}

	public void setReizigerId(int reizigerId) {
		this.reizigerId = reizigerId;
	}

	public String getVoorletters() {
		return voorletters;
	}

	public void setVoorletters(String voorletters) {
		this.voorletters = voorletters;
	}

	public String getTussenvoegsel() {
		return tussenvoegsel;
	}

	public void setTussenvoegsel(String tussenvoegsel) {
		this.tussenvoegsel = tussenvoegsel;
	}

	public String getAchternaam() {
		return achternaam;
	}

	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	public String getGeboortedatum() {
		return geboortedatum;
	}

	public void setGeboortedatum(String geboortedatum) {
		this.geboortedatum = geboortedatum;
	}
	
	public List<OvChipkaart> getOvChipkaarten() {
		return ovChipkaarten;
	}
	
	public void setOvChipkaarten(List<OvChipkaart> ovChipkaarten) {
		this.ovChipkaarten = ovChipkaarten;
	}

}
