package it.unisa.quattrocchi.entity;

public class GestoreOrdini {
	private String username;
	private String password;
	private String nome;
	private String cognome;
	private String email;
	private String matricola;
	
	public GestoreOrdini(String username, String password, String nome, String cognome, String email,
			String matricola) {
		super();
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.matricola = matricola;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMatricola() {
		return matricola;
	}
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	@Override
	public String toString() {
		return "GestoreOrdini [username=" + username + ", password=" + password + ", nome=" + nome + ", cognome="
				+ cognome + ", email=" + email + ", matricola=" + matricola + "]";
	}
	
	

}
