package it.unisa.quattrocchi.entity;

public class GestoreOrdini extends User{

	private String matricola;
	
	public GestoreOrdini(String username, String password, String nome, String cognome, String email, String matricola) {
		super(username, password, nome, cognome, email);
		 this.matricola = matricola;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	
	
}
