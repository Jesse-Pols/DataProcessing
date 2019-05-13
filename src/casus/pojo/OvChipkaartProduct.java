package casus.pojo;

import java.sql.Date;

public class OvChipkaartProduct {
	
	private int ovproductID;
	private int kaartNummer;
	private int productNummer;
	private String reisproductStatus;
	private String lastUpdate;
	
	public OvChipkaartProduct (int ovproductID, String reisproductStatus, String lastUpdate) {
		this.ovproductID = ovproductID;
		this.reisproductStatus = reisproductStatus;
		this.lastUpdate = lastUpdate;
	}

	public int getOvproductID() {
		return ovproductID;
	}

	public void setOvproductID(int ovproductID) {
		this.ovproductID = ovproductID;
	}

	public String getReisproductStatus() {
		return reisproductStatus;
	}

	public void setReisproductStatus(String reisproductStatus) {
		this.reisproductStatus = reisproductStatus;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getKaartNummer() {
		return kaartNummer;
	}

	public void setKaartNummer(int kaartNummer) {
		this.kaartNummer = kaartNummer;
	}

	public int getProductNummer() {
		return productNummer;
	}

	public void setProductNummer(int productNummer) {
		this.productNummer = productNummer;
	}	

}
