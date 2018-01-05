package it.unisa.quattrocchi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.unisa.quattrocchi.entity.ShippingAddress;

public class ShippingAddressModel {
	
	private static final String TABLE_NAME_ADDRESS = "quattrocchidb.indirizzospedizione";
	
	public ShippingAddress doRetrieveById(String idShip) throws SQLException {
		Connection conn = null;
		PreparedStatement stm = null;
		ShippingAddress bean = null;
		
		String query = "SELECT * FROM " + TABLE_NAME_ADDRESS + 
				"WHERE Id = ?;";
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			stm.setString(1, idShip);
			
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {
				String codice = rs.getString("Id");
				String stato = rs.getString("Stato");
				String provincia = rs.getString("Provincia");
				int cap = rs.getInt("CAP");
				String indirizzo = rs.getString("Indirizzo");
				int nc = rs.getInt("NumeroCivico");
				//Acquirente acq = ??
				
				bean = new ShippingAddress(codice,stato,indirizzo,cap,provincia,nc,null);
			}
			
			stm.close();
			rs.close();
			conn.commit();
		}finally {
			try {
				if(stm != null)
					stm.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		
		return bean;
	}
	
	public List<ShippingAddress> doRetrieveByUser(String user) throws SQLException{
		Connection conn = null;
		PreparedStatement stm = null;
		List<ShippingAddress> beans = new ArrayList<>();
		
		String query = "SELECT * FROM " + TABLE_NAME_ADDRESS + ";";
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {
				String codice = rs.getString("Id");
				String stato = rs.getString("Stato");
				String provincia = rs.getString("Provincia");
				int cap = rs.getInt("CAP");
				String indirizzo = rs.getString("Indirizzo");
				int nc = rs.getInt("NumeroCivico");
				//Acquirente acq = ??
				
				beans.add(new ShippingAddress(codice,stato,indirizzo,cap,provincia,nc,null));
			}
			stm.close();
			rs.close();
			conn.commit();
		}finally {
			try {
				if(stm != null)
					stm.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		
		return beans;
	}
}
