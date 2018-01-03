package it.unisa.quattrocchi.entity;

public class ArticleInOrder extends Article {
	
	int quantit�;
	
	public ArticleInOrder(String modello, String marca, String img1, String img2, String img3, String descrizione, int quantit�, double prezzo) {
		super(modello, marca, img1, img2, img3, descrizione, prezzo);
		this.quantit�=quantit�;
	}

	public int getQuantit�() {
		return quantit�;
	}

	public void setQuantit�(int quantit�) {
		this.quantit� = quantit�;
	}
}
