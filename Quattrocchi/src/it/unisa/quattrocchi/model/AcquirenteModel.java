package it.unisa.quattrocchi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.entity.ArticoloInStock;
import it.unisa.quattrocchi.entity.Cart;

public class AcquirenteModel {
	
	private static final String TABLE_NAME_ACQUIRENTE = "quattrocchidb.acquirente";
	private static final String TABLE_NAME_ARTICOLOINCARRELLO = "quattrocchidb.articoloincarrello";
	
	private static ArticoloInStockModel asModel = new ArticoloInStockModel();
	
	public Acquirente doRetriveById(String userName) throws SQLException {
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
	
	public Acquirente checkLogin(String userName, String password) throws SQLException {
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

	public Cart doRetrieveCartByUser(String userid) throws SQLException {
		Connection conn = null;
		PreparedStatement stm = null;
		Cart bean = null;
		Map<ArticoloInStock, Integer> list = new HashMap<>();
		
		String query = "SELECT * FROM " + TABLE_NAME_ARTICOLOINCARRELLO + " WHERE Acquirente = ?;";
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			stm.setString(1, userid);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("ArticoloInStock");
				int n = rs.getInt("Quantita");
				
				list.put(asModel.doRetrieveByIdInStock(id), n);
			}
			bean = new Cart(list);
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
	
	public void updateAcquirente(Acquirente toUpdate) throws SQLException{
		Connection conn = null;
		PreparedStatement stm = null;
		
		String username = toUpdate.getUsername();
		String password = toUpdate.getPassword();
		String nome = toUpdate.getNome();
		String cognome = toUpdate.getCognome();
		String email = toUpdate.getEmail();
		Date dataNascita = toUpdate.getDataNascita();
		
		Cart carrello = toUpdate.getCart();
		
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
			stm.close();
			conn.commit();
		}finally {
			try {
				if(stm != null)
					stm.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		return;
	}
	
	public void updateCart(Acquirente acquirente) throws SQLException{
		Connection conn = null;
		PreparedStatement stm = null;
	
		dropCart(acquirente);
		
		String acq = acquirente.getUsername();
		Map<ArticoloInStock,Integer> mappa = acquirente.getCart().getArticoli();
		Set<ArticoloInStock> articoli = mappa.keySet();
		
		
		String query = "insert into " + TABLE_NAME_ARTICOLOINCARRELLO + " values(?,?,?)";
		
		for(ArticoloInStock a: articoli) {
			String codiceArticolo = a.getCodice();
			int quantità = mappa.get(a);
			
			try {
				conn = DriverManagerConnectionPool.getConnection();
				stm = conn.prepareStatement(query);
				
				stm.setString(1, acq);
				stm.setString(2, codiceArticolo);
				stm.setInt(3, quantità);
				
				stm.executeUpdate();
				stm.close();
				conn.close();
				
			}finally {
				try {
					if(stm != null)
						stm.close();
				}finally {
					DriverManagerConnectionPool.releaseConnection(conn);
				}
			}	
		}
	}
	
	public void dropCart(Acquirente acquirente) throws SQLException{
		Connection conn = null;
		PreparedStatement stm = null;
		
		String acq = acquirente.getUsername();
		
		String query = "delete from " + TABLE_NAME_ARTICOLOINCARRELLO + 
				" where Acquirente = ?";
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			
			stm.setString(1, acq);
			System.out.println(stm);
			
			stm.executeUpdate();
			stm.clearBatch();
			conn.close();
		}finally {
			try {
				if(stm != null)
					stm.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		
		
	}

}
