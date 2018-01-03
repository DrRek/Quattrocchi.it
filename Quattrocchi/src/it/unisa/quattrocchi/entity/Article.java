package it.unisa.quattrocchi.entity;

public abstract class Article {
	
	private String modello, marca, img1, img2, img3, descrizione;
	private double prezzo;
	
	public Article(String modello, String marca, String img1, String img2, String img3, String descrizione, double prezzo) {
		this.modello=modello;
		this.marca=marca;
		this.img1=img1;
		this.img2=img2;
		this.img3=img3;
		this.descrizione=descrizione;
		this.prezzo=prezzo;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	public String getImg3() {
		return img3;
	}

	public void setImg3(String img3) {
		this.img3 = img3;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public double getPrezzo() {
		return prezzo;
	}
	
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
}
