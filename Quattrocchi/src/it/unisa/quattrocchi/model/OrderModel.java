package it.unisa.quattrocchi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.unisa.quattrocchi.entity.Order;

public class OrderModel {
	private static final String TABLE_NAME_ORDER = "quattrocchidb.ordine";
	
	public Order doRetrieveById(String idOrder) throws SQLException {
		Connection conn = null;
		PreparedStatement stm = null;
		Order bean = null;
		
		String query = "SELECT * FROM" + TABLE_NAME_ORDER + "WHERE Codice = ?;";
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			stm.setString(1, idOrder);
			
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {
				String codice = rs.getString("Codice");
				Date dataEx = rs.getDate("DataEsecuzione");
				double prezzo = rs.getDouble("");
				//ShippingAddress indirizzo = ??
				//CreditCard carta = ??
				//Acquirente acq = ??
				String statoOrdine = rs.getString("StatoOrdine");
				Date dataConegna = rs.getDate("DataConsegna");
				String numTracking = rs.getString("NumeroTracking");
				String corriere = rs.getString("Corriere");
				
				// bean.add(new Order()); <-- aggiungere parametri
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

	public List<Order> doRetrieveAll(){
		Connection conn = null;
		PreparedStatement stm = null;
		List<Order> beans = new ArrayList<>();
		
		String query = "SELECT * FROM" + TABLE_NAME_ORDER + ";";
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {
				String codice = rs.getString("Codice");
				Date dataEx = rs.getDate("DataEsecuzione");
				double prezzo = rs.getDouble("");
				//ShippingAddress indirizzo = ??
				//CreditCard carta = ??
				//Acquirente acq = ??
				String statoOrdine = rs.getString("StatoOrdine");
				Date dataConegna = rs.getDate("DataConsegna");
				String numTracking = rs.getString("NumeroTracking");
				String corriere = rs.getString("Corriere");
				
				// beans.add(new Order()); <-- aggiungere parametri
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
		return null;
	}
	
	public void createOrder(Order toCreate) {
		
		return;
	}
	
	public void updateOrder(Order toUpdate) {
		
		return;
	}
}
