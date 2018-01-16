package it.unisa.quattrocchi.entity;

import java.util.Date;

public class CreditCard {

	private String numeroCC, intestatario, circuito,idCarta;
	private int cvv;
	private Date dataScadenza;
	private Acquirente acquirente;
	
	public CreditCard(String idCarta,String numeroCC, String intestatario, String circuito, Date dataScadenza, int cvv, Acquirente acquirente) {
		this.idCarta = idCarta;
		this.numeroCC = numeroCC;
		this.intestatario = intestatario;
		this.circuito = circuito;
		this.cvv = cvv;
		this.dataScadenza = dataScadenza;
		this.acquirente = acquirente;
	}
	
	public CreditCard(String numeroCC, String intestatario, String circuito, Date dataScadenza, int cvv, Acquirente acquirente) {
		this.idCarta = null;
		this.numeroCC = numeroCC;
		this.intestatario = intestatario;
		this.circuito = circuito;
		this.cvv = cvv;
		this.dataScadenza = dataScadenza;
		this.acquirente = acquirente;
	}
	
	public String getIdCarta() {
		return idCarta;
	}
	
	public void setIdCarta(String id) {
		this.idCarta = id;
	}
	
	public String getNumeroCC() {
		return numeroCC;
	}
	
	public void setNumeroCC(String numeroCC) {
		this.numeroCC = numeroCC;
	}
	
	public String getIntestatario() {
		return intestatario;
	}
	
	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}
	
	public String getCircuito() {
		return circuito;
	}
	
	public void setCircuito(String circuito) {
		this.circuito = circuito;
	}
	
	public int getCvv() {
		return cvv;
	}
	
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	
	public Date getDataScadenza() {
		return dataScadenza;
	}
	
	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public Acquirente getAcquirente() {
		return acquirente;
	}

	public void setAcquirente(Acquirente acquirente) {
		this.acquirente = acquirente;
	}
	
	public String getLastCC() {
		return numeroCC.substring(12);
	}
	
}
