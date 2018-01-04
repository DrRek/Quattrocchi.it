package it.unisa.quattrocchi.entity;

public class ArticleInOrder extends Article {
	
	int quantit�;
	
	public ArticleInOrder(String codice, String modello, String marca, String img1, String img2, String img3, String descrizione, double prezzo, int quantit�) {
		super(codice, modello, marca, img1, img2, img3, descrizione, prezzo);
		this.quantit�=quantit�;
	}

	public int getQuantit�() {
		return quantit�;
	}

	public void setQuantit�(int quantit�) {
		this.quantit� = quantit�;
	}
}
