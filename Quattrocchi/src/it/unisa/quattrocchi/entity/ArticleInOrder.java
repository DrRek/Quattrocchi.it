package it.unisa.quattrocchi.entity;

public class ArticleInOrder extends Article {
	
	int quantità;
	
	public ArticleInOrder(String codice, String modello, String marca, String img1, String img2, String img3, String descrizione, double prezzo, int quantità) {
		super(codice, modello, marca, img1, img2, img3, descrizione, prezzo);
		this.quantità=quantità;
	}

	public int getQuantità() {
		return quantità;
	}

	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}
}
