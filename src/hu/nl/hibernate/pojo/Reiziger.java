package hu.nl.hibernate.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="REIZIGER")
public class Reiziger {
	
	@Id
	private int reizigerid;
	
	private String voorletters;
	private String tussenvoegsel;
	private String achternaam;
	private Date gebortedatum;	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "reiziger")
	private List<OvChipkaart> ovChipkaarten;
	
	public Reiziger() {
		
	}

	public Reiziger (int reizigerid, String voorletters, String tussenvoegsel, String achternaam, Date gebortedatum) {
		this.reizigerid = reizigerid;
		this.voorletters = voorletters;
		this.tussenvoegsel = tussenvoegsel;
		this.achternaam = achternaam;
		this.gebortedatum = gebortedatum;		
		ovChipkaarten = new ArrayList<OvChipkaart>();
	}

	public int getReizigerId() { return reizigerid; }
	public void setReizigerId(int reizigerid) { this.reizigerid = reizigerid; }

	public String getVoorletters() { return voorletters; }
	public void setVoorletters(String voorletters) { this.voorletters = voorletters; }

	public String getTussenvoegsel() { return tussenvoegsel; }
	public void setTussenvoegsel(String tussenvoegsel) { this.tussenvoegsel = tussenvoegsel; }

	public String getAchternaam() { return achternaam; }
	public void setAchternaam(String achternaam) { this.achternaam = achternaam; }

	public Date getGeboortedatum() { return gebortedatum; }
	public void setGeboortedatum(Date geboortedatum) { this.gebortedatum = geboortedatum; }	

	public List<OvChipkaart> getOvChipkaarten() { return ovChipkaarten; }
	public void setOvChipkaarten(List<OvChipkaart> ovChipkaarten) { this.ovChipkaarten = ovChipkaarten; }
	public void addOvChipkaart(OvChipkaart ovChipkaart) { ovChipkaarten.add(ovChipkaart); }	
	public void removeOvChipkaart(OvChipkaart ovChipkaart) { ovChipkaarten.remove(ovChipkaart); }
	
	public String toString() {
		String s = "";
		s += "ReizigerId: " + this.reizigerid;
		s += "\nVoorletters: " + this.voorletters;
		s += "\nTussenvoegsel: " + this.tussenvoegsel;
		s += "\nAchternaam: " + this.achternaam;
		s += "\nGeboortedatum: " + this.gebortedatum;
		s += "\nOvChipkaarten: ";
		for (OvChipkaart ovChipkaart : this.ovChipkaarten)
			s += "\n" + ovChipkaart.getKaartNummer();
		
		
		return s + "\n";		
	}
	
	public boolean equals(Object object) {
		if (object instanceof Reiziger) {
			Reiziger reiziger = (Reiziger) object;
			if (	reiziger.getVoorletters().equals(this.voorletters) &&
					reiziger.getTussenvoegsel().equals(this.tussenvoegsel) &&
					reiziger.getAchternaam().equals(this.achternaam) &&
					reiziger.getGeboortedatum().equals(this.gebortedatum) &&
					reiziger.getReizigerId() == this.reizigerid &&
					reiziger.getOvChipkaarten().equals(this.ovChipkaarten))
				return true;
		}
		return false;
	}

}
