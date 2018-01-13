package it.unisa.quattrocchi.entity;

public class ArticoloInOrder {
	private String codice;
	private String modello;
	private String marca;
	private String img1;
	private String img2;
	private String img3;
	private String descrizione;
	private double prezzo;
	private int quantità;
	
	public ArticoloInOrder(String codice, String modello, String marca, String img1, String img2, String img3,
			String descrizione, double prezzo, int quantità) {
		super();
		this.codice = codice;
		this.modello = modello;
		this.marca = marca;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.quantità = quantità;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
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

	public int getQuantità() {
		return quantità;
	}

	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}

	@Override
	public String toString() {
		return "ArticoloInOrder [codice=" + codice + ", modello=" + modello + ", marca=" + marca + ", descrizione="
				+ descrizione + ", prezzo=" + prezzo + ", quantità=" + quantità + "]";
	}
		

}
