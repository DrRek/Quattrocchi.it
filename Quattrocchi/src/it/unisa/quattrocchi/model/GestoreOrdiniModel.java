package it.unisa.quattrocchi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.unisa.quattrocchi.entity.GestoreOrdini;


/**
 * 
 * @author quattrocchi.it
 * Questa classe è un manager che si occupa di interagire con il database.
 * Gestisce le query riguardanti i gestori degli ordini.
 */
public class GestoreOrdiniModel {
	
	private static final String TABLE_NAME_GESTOREORDINI = "quattrocchidb.gestoreordini";
	
	
	/**
	 * Questo metodo si occupa di verificare se nel database è presente un GestoreOrdini
	 * tramite una username specifica presa in input.
	 * @param userName un oggetto userName di tipo <strong>String</strong>
	 * @return un oggetto di tipo <strong>GestoreOrdini</strong>, altrimenti null.
	 * @throws SQLException
	 * 
	 * @precondition userName != null e corrisponde ad un gestore nel database.
	 */
	public GestoreOrdini doRetriveById(String userName) throws SQLException {
		if(userName == null) {
			return null;
		}
		Connection conn = null;
		PreparedStatement stm = null;
		GestoreOrdini bean = null;
		
		String query = "SELECT * FROM " + TABLE_NAME_GESTOREORDINI + " WHERE Username = ?;";
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			stm.setString(1, userName);
			
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {
				String username = rs.getString("Username");
				String password = rs.getString("Pwd");
				String nome = rs.getString("Nome");
				String cognome = rs.getString("Cognome");
				String email = rs.getString("Email");
				String matricola = rs.getString("Matricola");
				
				bean = new GestoreOrdini(username,password,nome,cognome,email,matricola);
			}
			
			stm.close();
			rs.close();
			conn.commit();
			
		}finally {
			try {
				if(stm!= null)
					stm.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		return bean;
	}
	
	
	/**
	 * Questo metodo si occupa di verificare se i dati immessi dal gestore ordini per
	 * effettuare il login sono presenti nel database.
	 * @param userName un oggetto userName di tipo <strong>String</strong>
	 * @param password un oggetto password di tipo <strong>String</strong>
	 * @return un oggetto di tipo <strong>GestoreOrdini</strong>, altrimenti null.
	 * @throws SQLException
	 * 
	 * @precondition	usernaName != null e corrisponde all'identificativo di un user nel database.
	 * 					password != null e corrisponde alla password dell'username specificato nel database.
	 */
	public GestoreOrdini checkLogin(String userName, String password) throws SQLException {
		if(userName == null || password == null) {
			return null;
		}
		
		Connection conn = null;
		PreparedStatement stm = null;
		GestoreOrdini bean = null;
		
		String query = "SELECT * FROM " + TABLE_NAME_GESTOREORDINI + " WHERE Username = ? AND Pwd = ?;";
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			stm.setString(1, userName);
			stm.setString(2, password);
			
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {
				String username = rs.getString("Username");
				String pwd = rs.getString("Pwd");
				String nome = rs.getString("Nome");
				String cognome = rs.getString("Cognome");
				String email = rs.getString("Email");
				String matricola = rs.getString("Matricola");
				
				bean = new GestoreOrdini(username,pwd,nome,cognome,email,matricola);
			}
			
			stm.close();
			rs.close();
			conn.commit();
			
		}finally {
			try {
				if(stm!= null)
					stm.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		return bean;
	}

}
