package hu.nl.hibernate;

public class Reiziger {
	
    private int reizigerid;
    private String voorletters;
    private String achternaam;

    public Reiziger() {
    }

    public Reiziger(int reizigerid, String voorletters, String achternaam) {
        this.reizigerid = reizigerid;
        this.voorletters = voorletters;
        this.achternaam = achternaam;
    }

    public int getReizigerId() {
		return reizigerid;
	}

	public void setReizigerId(int reizigerid) {
		this.reizigerid = reizigerid;
	}

	public String getVoorLetters() {
		return voorletters;
	}

	public void setVoorLetters(String voorletters) {
		this.voorletters = voorletters;
	}

	public String getAchterNaam() {
		return achternaam;
	}

	public void setAchterNaam(String achternaam) {
		this.achternaam = achternaam;
	}

	@Override
    public String toString() {
		String s = "";
		s += "\nReizigerID: " + this.reizigerid;
		s += "\nVoorletters: " + this.voorletters;
		s += "\nAchternaam: " + this.achternaam;
		return s + "\n";
    }
}
