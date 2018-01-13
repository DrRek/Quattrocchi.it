package it.unisa.quattrocchi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
