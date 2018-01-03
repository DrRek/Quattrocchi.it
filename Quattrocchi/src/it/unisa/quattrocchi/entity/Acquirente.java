package it.unisa.quattrocchi.entity;

import java.util.Date;
import java.util.List;

public class Acquirente extends User{

	private Date dataNascita;
	private String codiceFiscale;
	private List<CreditCard> carteDiCredito;
	private List<ShippingAddress> indirizziDiSpedizione;
	private Cart carrello;
	
	public Acquirente(String username, String password, String nome, String cognome, String email, Date dataNascita, String codiceFiscale, Cart carrello){
		super(username, password, nome, cognome, email);
		this.dataNascita = dataNascita;
		this.codiceFiscale = codiceFiscale;
		this.carteDiCredito = null;
		this.indirizziDiSpedizione = null;
		this.carrello = carrello;
	}
	
	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public List<CreditCard> getCarteDiCredito() {
		return carteDiCredito;
	}

	public void setCarteDiCredito(List<CreditCard> carteDiCredito) {
		this.carteDiCredito = carteDiCredito;
	}

	public List<ShippingAddress> getIndirizziDiSpedizione() {
		return indirizziDiSpedizione;
	}

	public void setIndirizziDiSpedizione(List<ShippingAddress> indirizziDiSpedizione) {
		this.indirizziDiSpedizione = indirizziDiSpedizione;
	}
}
