package it.unisa.quattrocchi.model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.entity.ArticoloInStock;
import it.unisa.quattrocchi.entity.Cart;


/**
 * 
 * @author quattrocchi.it
 * Questa classe è un manager che si occupa di interagire con il database.
 * Gestisce le query riguardanti l'acquirente e il carrello ad esso associato.
 *
 */
public class AcquirenteModel {
	
	private static final String TABLE_NAME_ACQUIRENTE = "quattrocchidb.acquirente";
	private static final String TABLE_NAME_ARTICOLOINCARRELLO = "quattrocchidb.articoloincarrello";
	
	public static final String RETRIEVE_ACQUIRENTE_BY_USERNAME = "SELECT * FROM acquirente WHERE Username = ?;";
	
	private static ArticoloInStockModel asModel = new ArticoloInStockModel();
	
	
	/**
	 * Questo metodo si occupa di verificare se nel database è presente un Acquirente
	 * tramite una username specifica presa in input.
	 * @param userName un oggetto userName di tipo <strong>String</strong> 
	 * @return un oggetto di tipo <strong>Acquirente</strong>, altrimenti null.
	 * @throws SQLException
	 * 
	 * @precondition usernaName != null e corrisponde all'identificativo di un user nel database.
	 */
	public Acquirente doRetriveById(String userName) throws SQLException {
		if(userName==null) {
			return null;
		}
		
		Connection conn = null;
		PreparedStatement stm = null;
		Acquirente bean = null;
		
		String query = "SELECT * FROM " + TABLE_NAME_ACQUIRENTE + " WHERE Username = ?;";
		
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
				Date dataNascita = rs.getDate("DataNascita");
				
				bean = new Acquirente(username,password,nome,cognome,email,dataNascita);
			}
			rs.close();
			
		}finally {
			try {
				conn.commit();
				if(stm!= null)
					stm.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		return bean;
	}
	
	
	/**
	 * Questo metodo si occupa di verificare se i dati immessi dall'utente per effettuare
	 * il login sono presenti nel database.
	 * @param userName un oggetto userName di tipo <strong>String</strong>
	 * @param password un oggetto password di tipo <strong>String</strong>
	 * @return un oggetto di tipo <strong>Acquirente</strong>, altrimenti null.
	 * @throws SQLException
	 * 
	 * @precondition	usernaName != null e corrisponde all'identificativo di un user nel database.
	 * 					password != null e corrisponde alla password dell'username specificato nel database.
	 */
	public Acquirente checkLogin(String userName, String password) throws SQLException {
		
		if(userName==null || password==null) {
			return null;
		}
		
		Connection conn = null;
		PreparedStatement stm = null;
		Acquirente bean = null;
		
		String query = "SELECT * FROM " + TABLE_NAME_ACQUIRENTE + " WHERE Username = ? AND Pwd = ?;";
		
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
				Date dataNascita = rs.getDate("DataNascita");
				
				bean = new Acquirente(username,pwd,nome,cognome,email,dataNascita);
			}
			
			rs.close();
			
		}finally {
			try {
				conn.commit();
				if(stm!= null)
					stm.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		return bean;
	}
	
	
	/**
	 * Questo metodo si occupa di verificare se il carrello associato ad un acquirente è presente nel database.
	 * @param userName un oggetto userName di tipo <strong>String</strong>
	 * @return un oggetto di tipo <strong>Carrello</strong>, altrimenti null.
	 * @throws SQLException
	 * 
	 * @precondition usernaName != null e corrisponde all'identificativo di un user nel database.
	 */
	public Cart doRetrieveCartByUser(String userName) throws SQLException {
		if(userName == null) {
			return null;
		}
		
		Connection conn = null;
		PreparedStatement stm = null;
		Cart bean = null;
		Map<ArticoloInStock, Integer> list = new HashMap<>();
		
		String query = "SELECT * FROM " + TABLE_NAME_ARTICOLOINCARRELLO + " WHERE Acquirente = ?;";
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			stm.setString(1, userName);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("ArticoloInStock");
				int n = rs.getInt("Quantita");
				
				list.put(asModel.doRetrieveByIdInStock(id), n);
			}
			bean = new Cart(list);
			
			rs.close();
			
		}finally {
			try {
				conn.commit();
				if(stm!= null)
					stm.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		return bean;
	}
	
	
	/**
	 * Questo metodo si occupa di aggiornare i dati di un Acquirente presente nel database.
	 * @param toUpdate un oggetto toUpdate di tipo <strong>Acquirente</strong>
	 * @throws SQLException
	 * 
	 * @precondition toUpdate != null.
	 */
	public void updateAcquirente(Acquirente toUpdate) throws SQLException{
		if(toUpdate==null) {
			return;
		}
		
		Connection conn = null;
		PreparedStatement stm = null;
		
		String username = toUpdate.getUsername();
		String password = toUpdate.getPassword();
		String nome = toUpdate.getNome();
		String cognome = toUpdate.getCognome();
		String email = toUpdate.getEmail();
		Date dataNascita = toUpdate.getDataNascita();
		
		String query = "update " + TABLE_NAME_ACQUIRENTE + " set Pwd = ?, Nome = ?, Cognome = ?,Email = ?, DataNascita = ?" +
		" where Username = ?;";
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			
			stm.setString(1, password);
			stm.setString(2, nome);
			stm.setString(3, cognome);
			stm.setString(4, email);
			stm.setDate(5, (java.sql.Date) dataNascita);
			stm.setString(6, username);
			
			stm.executeUpdate();
		}finally {
			try {
				conn.commit();
				if(stm != null)
					stm.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		return;
	}
	
	
	/**
	 * Questo metodo si occupa di aggiornare la lista degli articoli di un carrello associato ad un Acquirente presente nel database. 
	 * @param acquirente un oggetto acquirente di tipo <strong>Acquirente</strong>
	 * @throws SQLException
	 * 
	 * @precondition	acquirente != null.
	 * 					acquirente.getCart()!=null.
	 */
	public void updateCart(Acquirente acquirente) throws SQLException{
		if(acquirente == null || acquirente.getCart() == null) {
			return;
		}
		
		Connection conn = null;
		PreparedStatement stm = null;
	
		dropCart(acquirente);
		
		String acq = acquirente.getUsername();
		Map<ArticoloInStock,Integer> mappa = acquirente.getCart().getArticoli();
		Set<ArticoloInStock> articoli = mappa.keySet();
		
		
		String query = "insert into " + TABLE_NAME_ARTICOLOINCARRELLO + " values(?,?,?)";
		
		for(ArticoloInStock a: articoli) {
			int codiceArticolo = a.getCodice();
			int quantità = mappa.get(a);
			
			try {
				conn = DriverManagerConnectionPool.getConnection();
				stm = conn.prepareStatement(query);
				
				stm.setString(1, acq);
				stm.setInt(2, codiceArticolo);
				stm.setInt(3, quantità);
				
				stm.executeUpdate();
				
			}finally {
				try {
					if(stm != null)
						conn.commit();
						stm.close();
				}finally {
					DriverManagerConnectionPool.releaseConnection(conn);
				}
			}	
		}
	}
	
	
	/**
	 * Questo metodo si occupa di svuotare un carrello associato ad un acquirente presente nel database.
	 * @param acquirente un oggetto acquirente di tipo <strong>Acquirente</strong>
	 * @throws SQLException
	 * 
	 * @precondition	acquirente != null.
	 */
	public void dropCart(Acquirente acquirente) throws SQLException{
		if(acquirente == null) {
			return;
		}
		
		Connection conn = null;
		PreparedStatement stm = null;
		
		String acq = acquirente.getUsername();
		
		String query = "delete from " + TABLE_NAME_ARTICOLOINCARRELLO + 
				" where Acquirente = ?";
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			
			stm.setString(1, acq);
			
			stm.executeUpdate();
		}finally {
			try {
				conn.commit();
				if(stm != null)
					stm.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
	}

}
