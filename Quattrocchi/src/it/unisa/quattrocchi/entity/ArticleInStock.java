package it.unisa.quattrocchi.entity;

public class ArticleInStock extends Article{

	int disponibilit�;
	
	public ArticleInStock(String codice, String modello, String marca, String img1, String img2, String img3, String descrizione, double prezzo, int disponibilit�) {
		super(codice, modello, marca, img1, img2, img3, descrizione, prezzo);
		this.disponibilit� = disponibilit�;
	}

	public int getDisponibilit�() {
		return disponibilit�;
	}

	public void setDisponibilit�(int disponibilit�) {
		this.disponibilit� = disponibilit�;
	}
}
