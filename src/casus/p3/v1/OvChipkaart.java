package casus.p3.v1;

import java.util.List;

public class OvChipkaart {
	
	private int kaartNummer;
	private String geldigTot;
	private int klasse;
	private float saldo;
	private int reizigerId;
	
	private Reiziger reiziger;
	private List<OvChipkaartProduct> ovChipkaartProducten;
	
	public OvChipkaart (int kaartNummer, String geldigTot, int klasse, float saldo, int reizigerId) {
		this.kaartNummer = kaartNummer;
		this.geldigTot = geldigTot;
		this.klasse = klasse;
		this.saldo = saldo;
		this.reizigerId = reizigerId;
	}

	public int getKaartNummer() {
		return kaartNummer;
	}

	public void setKaartNummer(int kaartNummer) {
		this.kaartNummer = kaartNummer;
	}

	public String getGeldigTot() {
		return geldigTot;
	}

	public void setGeldigTot(String geldigTot) {
		this.geldigTot = geldigTot;
	}

	public int getKlasse() {
		return klasse;
	}

	public void setKlasse(int klasse) {
		this.klasse = klasse;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	
	public int getReizigerId() {
		return reizigerId;
	}
	
	public void setReizigerId(int reizigerId) {
		this.reizigerId = reizigerId;
	}
	
	public Reiziger getReiziger() {
		return reiziger;
	}
	
	public void setReiziger(Reiziger reiziger) {
		this.reiziger = reiziger;
	}
	
	public List<OvChipkaartProduct> getOvChipkaartProducten() {
		return ovChipkaartProducten;
	}
	
	public void setOvChipkaartProducten(List<OvChipkaartProduct> ovChipkaartProducten) {
		this.ovChipkaartProducten = ovChipkaartProducten;
	}

}
