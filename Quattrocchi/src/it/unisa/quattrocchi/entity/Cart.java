package it.unisa.quattrocchi.entity;

import java.util.List;

public class Cart {
	
	private double prezzo;
	private List<Article> articoli;
	
	public Cart(double prezzo) {
		this.prezzo = prezzo;
		this.articoli = null;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public List<Article> getArticoli() {
		return articoli;
	}

	public void setArticoli(List<Article> articoli) {
		this.articoli = articoli;
	}
	
	public int getNumeroDiArticoli() {
		return articoli.size();
	}
}
