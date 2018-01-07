package it.unisa.quattrocchi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import it.unisa.quattrocchi.entity.Acquirente;

public class AcquirenteModel {
	
	private static final String TABLE_NAME_ACQUIRENTE = "quattrocchidb.acquirente";
	
	public Acquirente doRetriveById(String userName) throws SQLException {
		Connection conn = null;
		PreparedStatement stm = null;
		Acquirente bean = null;
		
		String query = "SELECT * FROM " + TABLE_NAME_ACQUIRENTE + "WHERE Username = ?;";
		
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

}
