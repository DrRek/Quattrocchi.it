package it.unisa.quattrocchi.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

	private String codice, statoOrdine, numeroTracking, corriere;
	private Date dataEsecuzione, dataConsegna;
	private Acquirente acquirente;
	private ShippingAddress shippingAddress;
	private CreditCard creditCard;
	private List<ArticoloInOrder> listaDiArticoli = new ArrayList<>();
	
	
	private double prezzo;
	public Order(Date dataEsecuzione, double prezzo, String statoOrdine, Date dataConsegna, String numeroTracking, 
			String corriere, Acquirente acquirente, ShippingAddress shippingAddress, CreditCard creditCard,List<ArticoloInOrder> listaDiArticoli) {
		this.codice = null;
		this.statoOrdine = statoOrdine;
		this.numeroTracking = numeroTracking; 
		this.dataEsecuzione = dataEsecuzione;
		this.dataConsegna = dataConsegna;
		this.prezzo = prezzo;
		this.corriere= corriere;
		this.acquirente = acquirente;
		this.shippingAddress =shippingAddress;
		this.creditCard = creditCard;
		this.listaDiArticoli = listaDiArticoli;
	}
	
	public String getCodice() {
		return codice;
	}
	
	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	public String getStatoOrdine() {
		return statoOrdine;
	}
	
	public void setStatoOrdine(String statoOrdine) {
		this.statoOrdine = statoOrdine;
	}
	
	public String getNumeroTracking() {
		return numeroTracking;
	}
	
	public void setNumeroTracking(String numeroTracking) {
		this.numeroTracking = numeroTracking;
	}
	
	public String getCorriere() {
		return corriere;
	}
	
	public void setCorriere(String corriere) {
		this.corriere = corriere;
	}
	
	public Date getDataEsecuzione() {
		return dataEsecuzione;
	}
	
	public void setDataEsecuzione(Date dataEsecuzione) {
		this.dataEsecuzione = dataEsecuzione;
	}
	
	public Date getDataConsegna() {
		return dataConsegna;
	}
	
	public void setDataConsegna(Date dataConsegna) {
		this.dataConsegna = dataConsegna;
	}
	
	public double getPrezzo() {
		return prezzo;
	}
	
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public Acquirente getAcquirente() {
		return acquirente;
	}

	public void setAcquirente(Acquirente acquirente) {
		this.acquirente = acquirente;
	}

	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
	public List<ArticoloInOrder> getListaArticoliInOrdine(){
		return listaDiArticoli;
	}
	
	public void setListaArticoliInOrdine(List<ArticoloInOrder> listaNuova) {
		this.listaDiArticoli = listaNuova;
	}

	

}
