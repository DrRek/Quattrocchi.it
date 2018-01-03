package it.unisa.quattrocchi.entity;

public class ShippingAddress {
	
	private String stato,indirizzo, cap, provincia;

	public ShippingAddress(String stato, String indirizzo, String cAP, String provincia) {
		this.stato = stato;
		this.indirizzo = indirizzo;
		this.cap = cap;
		this.provincia = provincia;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	

}
