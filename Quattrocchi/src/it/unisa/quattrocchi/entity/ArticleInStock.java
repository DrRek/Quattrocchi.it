package it.unisa.quattrocchi.entity;

public class ArticleInStock extends Article{

	int disponibilitā;
	
	public ArticleInStock(String modello, String marca, String img1, String img2, String img3, String descrizione, int disponibilitā, double prezzo) {
		super(modello, marca, img1, img2, img3, descrizione, prezzo);
		this.disponibilitā = disponibilitā;
	}

	public int getDisponibilitā() {
		return disponibilitā;
	}

	public void setDisponibilitā(int disponibilitā) {
		this.disponibilitā = disponibilitā;
	}
}
