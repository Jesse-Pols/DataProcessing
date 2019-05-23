package casus.p2;

import java.util.ArrayList;

public class Reiziger {
	
	private int reizigerId;
	private String voorLetters;
	private String tussenVoegsel;
	private String achterNaam;
	private String gbDatum;
	private ArrayList<OvChipkaart> allOvChipkaarten = new ArrayList<OvChipkaart>();
	
	public Reiziger(int reizigerId, String voorLetters, String tussenVoegsel, String achterNaam, String gbDatum) {
		this.reizigerId = reizigerId;
		this.voorLetters = voorLetters;
		this.tussenVoegsel = tussenVoegsel;
		this.achterNaam = achterNaam;
		this.gbDatum = gbDatum;
	}
	
	public int getReizigerID() { return this.reizigerId; }
	public void setReizigerID(int reizigerID) { this.reizigerId = reizigerID; }
	
	public String getVoorLetters() { return this.voorLetters; }
	public void setVoorLetters(String voorLetters) { this.voorLetters = voorLetters; }
	
	public String getTussenVoegsel() { return this.tussenVoegsel; }
	public void setTussenVoegsel(String tussenVoegsel) { this.tussenVoegsel = tussenVoegsel; }
	
	public String getAchterNaam() { return this.achterNaam; }
	public void setAchterNaam(String achterNaam) { this.achterNaam = achterNaam; }
	
	public String getGBdatum() { return this.gbDatum; }
	public void setGBdatum(String gbdatum) { this.gbDatum = gbdatum; }
	
	public ArrayList<OvChipkaart> getOvChipkaartList() { return this.allOvChipkaarten; }
	public void addOvChipkaart(OvChipkaart newOvChipkaart) {
		
		boolean listContainsItem = false;
		for (OvChipkaart singleOvChipkaart : allOvChipkaarten) {
			if (singleOvChipkaart.equals(newOvChipkaart)) {
				listContainsItem = true;
			}
		}
		
		if (listContainsItem == false) this.allOvChipkaarten.add(newOvChipkaart); 
		
	}
	public void removeOvChipkaart(OvChipkaart oldOvChipkaart) {
		
		for (int i = 0; i < allOvChipkaarten.size(); i++) {
			if (allOvChipkaarten.get(i).equals(oldOvChipkaart)) {
				allOvChipkaarten.remove(i);
			}
		}
		
	}
	
	public boolean equals (Object anderObject) {
		boolean gelijkeObjecten = false;
		
		if (anderObject instanceof Reiziger) {
			Reiziger andereReiziger = (Reiziger) anderObject; 
			
			// ID is always unique, even when the two objects are identical.
			if (this.reizigerId == andereReiziger.reizigerId && 
					this.voorLetters.equals(andereReiziger.voorLetters) &&
					this.tussenVoegsel.equals(andereReiziger.tussenVoegsel) &&
					this.achterNaam.equals(andereReiziger.achterNaam) &&
					this.gbDatum.equals(andereReiziger.gbDatum)) {
				gelijkeObjecten = true;
			}
		}
		
		return gelijkeObjecten;
	}

}
