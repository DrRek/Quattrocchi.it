package it.unisa.quattrocchi.entity;


import java.util.Map;

/**
 * Questa classe rappresenta il carrello
 * @author quattrocchi.it
 *
 */
public class Cart {
	
	private double prezzo;
	private Map<ArticoloInStock, Integer> articoli;
	
	public Cart(Map<ArticoloInStock, Integer> articoli) {
		this.articoli = articoli;
	}

	public double getPrezzo() {
		double prezzo = 0;
		for(ArticoloInStock i : articoli.keySet()) {
			prezzo= prezzo + (i.getPrezzo()*articoli.get(i));
		}
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
	
	/**
	 * Questo metodo restituisce il numero degli articoli presenti nel carrello.
	 * @return un oggetto size di tipo <strong>Integer</strong>
	 */
	public int getNumeroDiArticoli() {
		int size = 0;
		for(int i : articoli.values()) {
			size+=i;
		}
		return size;
	}

	/**
	 * Questo metodo consente di aggiungere un articolo al carrello.
	 * @param a un oggetto di tipo <strong>ArticoloInStock</strong>
	 */
	public void addArticle(ArticoloInStock a) {
		articoli.put(a, articoli.getOrDefault(a, 0)+1);
	}

	/**
	 * Questo metodo consente di rimuovere un articolo dal carrello.
	 * @param articolo un oggetto di tipo <strong>ArticoloInStock</strong>
	 */
	public void removeArticle(ArticoloInStock articolo) {
		articoli.remove(articolo);
	}

	/**
	 * Questo metodo consente di aggiornare un articolo e la sua quantità nel carrello.
	 * @param articolo un oggetto di tipo <strong>ArticoloInStock</strong>
	 * @param quantità un oggetto di tipo <strong>Integer</strong>
	 */
	public void setArticle(ArticoloInStock articolo, int quantità) {
		articoli.put(articolo, quantità);
	}

	/**
	 * Questo metodo consente di aggiornare il carrello.
	 * @param toIntegrate un oggetto di tipo <strong>Cart</strong>
	 */
	public void mergeCart(Cart toIntegrate) {
		Map<ArticoloInStock, Integer> temp = toIntegrate.getArticoli();
		for(ArticoloInStock a : temp.keySet()) {
			articoli.put(a, temp.get(a));
		}
	}
}
