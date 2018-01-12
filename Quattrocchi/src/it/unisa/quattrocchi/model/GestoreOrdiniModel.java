package it.unisa.quattrocchi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.unisa.quattrocchi.entity.GestoreOrdini;

public class GestoreOrdiniModel {
	
	private static final String TABLE_NAME_GESTOREORDINI = "quattrocchidb.gestoreordini";
	
	public GestoreOrdini doRetriveById(String userName) throws SQLException {
		Connection conn = null;
		PreparedStatement stm = null;
		GestoreOrdini bean = null;
		
		String query = "SELECT * FROM " + TABLE_NAME_GESTOREORDINI + "WHERE Username = ?;";
		
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
	
	public GestoreOrdini checkLogin(String userName, String password) throws SQLException {
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
