package it.unisa.quattrocchi.entity;

import java.util.Date;

public class CreditCard {

	private String numeroCC, intestatario, circuito, cvv;
	private Date dataScadenza;
	public CreditCard(String numeroCC, String intestatario, String circuito, Date dataScadenza, String cvv) {
		super();
		this.numeroCC = numeroCC;
		this.intestatario = intestatario;
		this.circuito = circuito;
		this.cvv = cvv;
		this.dataScadenza = dataScadenza;
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
	
	public String getCvv() {
		return cvv;
	}
	
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	public Date getDataScadenza() {
		return dataScadenza;
	}
	
	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	
	
	
}
