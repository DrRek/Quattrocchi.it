package it.unisa.quattrocchi.entity;


/**
 * Questa classe rappresenta un indirizzo di spedizione.
 * @author quattrocchi.it
 *
 */
public class ShippingAddress {
	
	private String stato,indirizzo, provincia;
	private int nc,cap, codice;
	private Acquirente acq;

	public ShippingAddress(int codice,String stato, String indirizzo, int cap, String provincia,int nc,Acquirente acq) {
		this.codice = codice;
		this.stato = stato;
		this.indirizzo = indirizzo;
		this.cap = cap;
		this.provincia = provincia;
		this.nc = nc;
		this.acq = acq;
	}
	
	public ShippingAddress(String stato, String indirizzo, int cap2, String provincia,int civico,Acquirente acq) {
		this.codice = 0;
		this.stato = stato;
		this.indirizzo = indirizzo;
		this.cap = cap2;
		this.provincia = provincia;
		this.nc = civico;
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
	
	public int getCodice() {
		return codice;
	}
	
	public void setCodice(int id) {
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
