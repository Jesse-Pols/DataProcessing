package casus.p3.v2.pojo;

import java.util.ArrayList;
import java.util.List;

public class Reiziger {
	
	private int reizigerId;
	private String voorletters;
	private String tussenvoegsel;
	private String achternaam;
	private String geboortedatum;
	
	private ArrayList<OvChipkaart> ovChipkaarten;

	public Reiziger (int reizigerId, String voorletters, String tussenvoegsel, String achternaam, String geboortedatum) {
		this.reizigerId = reizigerId;
		this.voorletters = voorletters;
		this.tussenvoegsel = tussenvoegsel;
		this.achternaam = achternaam;
		this.geboortedatum = geboortedatum;
		
		ovChipkaarten = new ArrayList<OvChipkaart>();
	}
	
	public void addOvChipkaart(OvChipkaart ovChipkaart) {
		ovChipkaarten.add(ovChipkaart);
	}
	
	public void removeOvChipkaart(OvChipkaart ovChipkaart) {
		ovChipkaarten.remove(ovChipkaart);
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

}
