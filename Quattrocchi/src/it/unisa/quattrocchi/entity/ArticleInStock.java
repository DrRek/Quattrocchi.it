package it.unisa.quattrocchi.entity;

public class ArticleInStock extends Article{

	int disponibilità;
	
	public ArticleInStock(String codice, String modello, String marca, String img1, String img2, String img3, String descrizione, double prezzo, int disponibilità) {
		super(codice, modello, marca, img1, img2, img3, descrizione, prezzo);
		this.disponibilità = disponibilità;
	}

	public int getDisponibilità() {
		return disponibilità;
	}

	public void setDisponibilità(int disponibilità) {
		this.disponibilità = disponibilità;
	}
}
