package it.unisa.quattrocchi.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * 
 * @author quattrocchi.it
 * Questa è una classe che rappresenta l'Acquirente.
 */
public class Acquirente {
	
	private String username;
	private String password;
	private String nome;
	private String cognome;
	private String email;
	private Date dataNascita;
	private ArrayList<CreditCard> cc;
	private ArrayList<ShippingAddress> shipAdd;
	private Cart cart;
	
	public Acquirente(String username, String password, String nome, String cognome, String email, Date dataNascita) {
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.dataNascita = dataNascita;	
	}
	
	public ArrayList<ShippingAddress> shipAdd() {
		return this.shipAdd;
	}
	
	public void setShipAdd(List<ShippingAddress> shipAdd) {
		this.shipAdd = (ArrayList<ShippingAddress>) shipAdd;
	}
	
	public ArrayList<CreditCard> getCc() {
		return this.cc;
	}
	
	public void setCc(List<CreditCard> cc) {
		this.cc = (ArrayList<CreditCard>) cc;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getDataNascita() {
		return dataNascita;
	}
	
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	@Override
	public String toString() {
		return "Acquirente [username=" + username + ", password=" + password + ", nome=" + nome + ", cognome=" + cognome
				+ ", email=" + email + ", dataNascita=" + dataNascita + "]";
	}

	/**
	 * Questo metodo controlla se la carta scelta per il pagamento esiste nella lista delle carte dell'Acquirente
	 * @param creditCardID un oggetto di tipi <strong>Integer</strong>
	 * @return un oggetto c di tipo <strong>CreditCard</strong>
	 */
	public CreditCard checkCC(int creditCardID) {
		for(CreditCard c : cc) {
			if(c.getIdCarta() == creditCardID) {
				return c;
			}
		}
		return null;
	}

	/**
	 * Questo metodo controlla l'indirizzo scelto per il checkout esiste nella lista degli indirizzi dell'Acquirente
	 * @param shippingAddressID un oggetto ti tipo <strong>Integer</strong>
	 * @return un oggetto s di tipo <strong>ShippingAddress</strong>
	 */
	public ShippingAddress checkSA(int shippingAddressID) {
		for(ShippingAddress s : shipAdd) {
			if(s.getCodice() == shippingAddressID) {
				return s;
			}
		}
		return null;
	}

	/**
	 * Resetta il carrello dopo aver completato un ordine.
	 */
	public void resetCart() {
		cart = new Cart(new HashMap<ArticoloInStock, Integer>());
	}

	
	/**
	 * Questo metodo aggiunge un indirizzo di spedizione alla lista degli indirizzi dell'Acquirente.
	 * @param sa un oggetto di tipo <strong>ShippingAddress</strong>
	 */
	public void addShippingAddress(ShippingAddress sa) {
		if(shipAdd==null) shipAdd=new ArrayList<>();
		shipAdd.add(sa);
	}

	/**
	 * Questo metodo aggiunge una carta di credito alla lista delle carte dell'Acquirente.
	 * @param creditCard un oggetto di tipo <strong>CreditCard</strong>
	 */
	public void addCreditCard(CreditCard creditCard) {
		if(cc==null) cc=new ArrayList<>();
		cc.add(creditCard);
	}

	/**
	 * Questo metodo rimuove un indirizzo di spedizione dalla lista degli indirizzi dell'Acquirente.
	 * @param id un oggetto di tipo <strong>Integer</strong>
	 * @return <strong>true</strong> se è stato rimosso correttamente, altrimentni <strong>false</strong>
	 */
	public boolean removeShippingAddress(int id) {
		for(int i=0; i<shipAdd.size(); i++) {
			if(shipAdd.get(i).getCodice() == id) {
				shipAdd.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Questo metodo rimuove una carta di credito dalla lista delle carte dell'Acquirente.
	 * @param id un oggetto di tipo <strong>Integer</strong>
	 * @return <strong>true</strong> se è stata rimossa correttamente, altrimentni <strong>false</strong>
	 */
	public boolean removeCartAddress(int id) {
		for(int i=0; i<cc.size(); i++) {
			if(cc.get(i).getIdCarta() == id) {
				cc.remove(i);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean equals(Object o) {
		if(username.equals((((Acquirente) o).getUsername()))){
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode(){
	    return username.hashCode();
	}
}
