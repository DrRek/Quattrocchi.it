package it.unisa.quattrocchi.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public CreditCard checkCC(int creditCardID) {
		for(CreditCard c : cc) {
			if(c.getIdCarta() == creditCardID) {
				return c;
			}
		}
		return null;
	}

	public ShippingAddress checkSA(int shippingAddressID) {
		for(ShippingAddress s : shipAdd) {
			if(s.getCodice() == shippingAddressID) {
				return s;
			}
		}
		return null;
	}

	public void resetCart() {
		cart = new Cart(new HashMap<ArticoloInStock, Integer>());
	}

	public void addShippingAddress(ShippingAddress sa) {
		if(shipAdd==null) shipAdd=new ArrayList<>();
		shipAdd.add(sa);
	}

	public void addCreditCard(CreditCard creditCard) {
		if(cc==null) cc=new ArrayList<>();
		cc.add(creditCard);
	}

	public void removeShippingAddress(int id) {
		for(int i=0; i<shipAdd.size(); i++) {
			if(shipAdd.get(i).getCodice() == id) {
				shipAdd.remove(i);
			}
		}
		return;
	}

	public void removeCartAddress(int id) {
		for(int i=0; i<cc.size(); i++) {
			if(cc.get(i).getIdCarta() == id) {
				cc.remove(i);
			}
		}
		return;
	}
}
