package casus.pojo;

public class Adres {
	
	public int getReizigerId() {
		return reizigerId;
	}

	public void setReizigerId(int reizigerId) {
		this.reizigerId = reizigerId;
	}

	private int adresId;
	private String postcode;
	private String huisnummer;
	private String straat;
	private String woonplaats;
	private int reizigerId;
	
	public Adres (int adresId, String postcode, String huisnummer, String straat, String woonplaats) {
		this.adresId = adresId;
		this.postcode = postcode;
		this.huisnummer = huisnummer;
		this.straat = straat;
		this.woonplaats = woonplaats;
	}

	public int getAdresId() {
		return adresId;
	}

	public void setAdresId(int adresId) {
		this.adresId = adresId;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getHuisnummer() {
		return huisnummer;
	}

	public void setHuisnummer(String huisnummer) {
		this.huisnummer = huisnummer;
	}

	public String getStraat() {
		return straat;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}

	public String getWoonplaats() {
		return woonplaats;
	}

	public void setWoonplaats(String woonplaats) {
		this.woonplaats = woonplaats;
	}

}
