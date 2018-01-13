package it.unisa.quattrocchi.entity;

import java.util.Map;

public class Cart {
	
	private double prezzo;
	private Map<ArticoloInStock, Integer> articoli;
	
	public Cart(Map<ArticoloInStock, Integer> articoli) {
		this.articoli = articoli;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public Map<ArticoloInStock, Integer> getArticoli() {
		return articoli;
	}

	public void setArticoli(Map<ArticoloInStock, Integer> articoli) {
		this.articoli = articoli;
	}
	
	public int getNumeroDiArticoli() {
		return articoli.size();
	}
}
