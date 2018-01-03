package it.unisa.quattrocchi.entity;

public class ArticleInStock extends Article{

	int disponibilità;
	
	public ArticleInStock(String modello, String marca, String img1, String img2, String img3, String descrizione, int disponibilità, double prezzo) {
		super(modello, marca, img1, img2, img3, descrizione, prezzo);
		this.disponibilità = disponibilità;
	}

	public int getDisponibilità() {
		return disponibilità;
	}

	public void setDisponibilità(int disponibilità) {
		this.disponibilità = disponibilità;
	}
}
