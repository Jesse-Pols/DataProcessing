package casus.p1;

import java.sql.Date;

public class Reiziger {
	
	private String naam;
	private Date gbdatum;
	
	public Reiziger(String naam, Date gbdatum) {
		this.naam = naam;
		this.gbdatum = gbdatum;
	}
	
	public String getNaam() { return this.naam; }
	public void setNaam(String naam) { this.naam = naam; }
	
	public Date getGBdatum() { return this.gbdatum; }
	public void setGBdatum(Date gbdatum) { this.gbdatum = gbdatum; }
	
	public boolean equals (Object anderObject) {
		boolean gelijkeObjecten = false;
		
		if (anderObject instanceof Reiziger) {
			Reiziger andereReiziger = (Reiziger) anderObject; 
			
			if (this.naam.equals(andereReiziger.naam) &&
					this.gbdatum.equals(andereReiziger.gbdatum)) {
				gelijkeObjecten = true;
			}
		}
		
		return gelijkeObjecten;
	}

}
