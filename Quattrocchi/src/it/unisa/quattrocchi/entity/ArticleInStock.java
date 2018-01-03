package it.unisa.quattrocchi.entity;

public class ArticleInStock extends Article{

	int disponibilit�;
	
	public ArticleInStock(String modello, String marca, String img1, String img2, String img3, String descrizione, int disponibilit�, double prezzo) {
		super(modello, marca, img1, img2, img3, descrizione, prezzo);
		this.disponibilit� = disponibilit�;
	}

	public int getDisponibilit�() {
		return disponibilit�;
	}

	public void setDisponibilit�(int disponibilit�) {
		this.disponibilit� = disponibilit�;
	}
}
