package it.unisa.quattrocchi.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Questa classe rappresente un ordine.
 * @author quattrocchi.it
 *
 */
public class Order {
	
	public static String DA_SPEDIRE = "Da spedire";
	public static String IN_CORSO = "In corso";
	public static String TERMINATO = "Consegnato";

	private int codice;
	private String statoOrdine, numeroTracking, corriere;
	private Date dataEsecuzione, dataConsegna;
	private Acquirente acquirente;
	private ShippingAddress shippingAddress;
	private CreditCard creditCard;
	private List<ArticoloInOrder> listaDiArticoli;
	private double prezzo;
	
	public Order(int codice, Date dataEsecuzione, double prezzo, String statoOrdine, Date dataConsegna, String numeroTracking, 
			String corriere, Acquirente acquirente, ShippingAddress shippingAddress, CreditCard creditCard,List<ArticoloInOrder> listaDiArticoli) {
		this.codice = codice;
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
	
	public Order(Acquirente acquirente, ShippingAddress shippingAddress, CreditCard creditCard) {
		this.codice = 0;
		this.statoOrdine = Order.DA_SPEDIRE;
		this.numeroTracking = null; 
		this.dataEsecuzione = new Date();
		this.dataConsegna = null;
		this.prezzo = acquirente.getCart().getPrezzo();
		this.corriere= null;
		this.acquirente = acquirente;
		this.shippingAddress =shippingAddress;
		this.creditCard = creditCard;
		
		this.listaDiArticoli = new ArrayList<>();
		
		Map<ArticoloInStock, Integer> map = acquirente.getCart().getArticoli();
		for(ArticoloInStock a : map.keySet()) {
			this.listaDiArticoli.add(new ArticoloInOrder(a, map.get(a)));
		}
	}
	
	public int getCodice() {
		return codice;
	}
	
	public void setCodice(int codice) {
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
	
	public void setDataConsegna(Date dataConsegna2) {
		this.dataConsegna = dataConsegna2;
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
	
	@Override
	public boolean equals(Object o) {
		if(codice == (((Order) o).getCodice())){
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode(){
	    return codice;
	}

}
