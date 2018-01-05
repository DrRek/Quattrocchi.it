package it.unisa.quattrocchi.entity;

public class ShippingAddress {
	
	private String stato,indirizzo, provincia,codice;
	private int nc,cap;
	private Acquirente acq;

	public ShippingAddress(String codice,String stato, String indirizzo, int cap, String provincia,int nc,Acquirente acq) {
		this.codice = codice;
		this.stato = stato;
		this.indirizzo = indirizzo;
		this.cap = cap;
		this.provincia = provincia;
		this.nc = nc;
		this.acq = acq;
	}
	
	public Acquirente getAcq() {
		return acq;
	}
	
	public void setAcq(Acquirente a) {
		this.acq = a;
	}
	
	public int getNC() {
		return nc;
	}
	
	public void setNC(int nc) {
		this.nc = nc;
	}
	
	public String getCodice() {
		return codice;
	}
	
	public void setCodice(String id) {
		this.codice = id;
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

	public int getCap() {
		return cap;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	

}
