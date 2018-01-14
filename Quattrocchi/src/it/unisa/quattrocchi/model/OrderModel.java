package it.unisa.quattrocchi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.entity.ArticoloInOrder;
import it.unisa.quattrocchi.entity.CreditCard;
import it.unisa.quattrocchi.entity.Order;
import it.unisa.quattrocchi.entity.ShippingAddress;

public class OrderModel {
	
	static ShippingAddressModel shippingAddressModel = new ShippingAddressModel();
	static CreditCardModel creditCardModel = new CreditCardModel();
	static AcquirenteModel acquirenteModel = new AcquirenteModel();
	static ArticoloInOrderModel articoloInOrderModel = new ArticoloInOrderModel();
	
	private static final String TABLE_NAME_ORDER = "quattrocchidb.ordine";
	
	public Order doRetrieveById(String idOrder) throws SQLException {
		Connection conn = null;
		PreparedStatement stm = null;
		Order bean = null;
		List<ArticoloInOrder> listaArticoliInOrdine = new ArrayList<>();
		listaArticoliInOrdine = articoloInOrderModel.restituisciArticoliAssociatiAdUnOrdine(idOrder);
	
		
		String query = "SELECT * FROM " + TABLE_NAME_ORDER + " WHERE Codice = ?;";
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			stm.setString(1, idOrder);
			
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {
				String codice = rs.getString("Codice");
				Date dataEx = rs.getDate("DataEsecuzione");
				double prezzo = rs.getDouble("Prezzo");
				ShippingAddress indirizzo = shippingAddressModel.doRetrieveById(rs.getString("IndirizzoSpedizione"));
				CreditCard carta = creditCardModel.doRetrieveById(rs.getString("CartaCredito"));
				Acquirente acq = acquirenteModel.doRetriveById(rs.getString("Acquirente"));
				String statoOrdine = rs.getString("StatoOrdine");
				Date dataConsegna = rs.getDate("DataConsegna");
				String numTracking = rs.getString("NumeroTracking");
				String corriere = rs.getString("Corriere");
				
				bean = new Order(codice,dataEx,prezzo,statoOrdine,dataConsegna,numTracking,corriere,acq,indirizzo,carta,listaArticoliInOrdine);
				
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

	public List<Order> doRetrieveAll() throws SQLException{
		Connection conn = null;
		PreparedStatement stm = null;
		List<Order> beans = new ArrayList<>();
		
		
		
		String query = "SELECT * FROM " + TABLE_NAME_ORDER + ";";
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				String codice = rs.getString("Codice");
				Date dataEx = rs.getDate("DataEsecuzione");
				double prezzo = rs.getDouble("Prezzo");
				ShippingAddress indirizzo = shippingAddressModel.doRetrieveById(rs.getString("IndirizzoSpedizione"));
				CreditCard carta = creditCardModel.doRetrieveById(rs.getString("CartaCredito"));
				Acquirente acq = acquirenteModel.doRetriveById(rs.getString("Acquirente"));
				String statoOrdine = rs.getString("StatoOrdine");
				Date dataConsegna = rs.getDate("DataConsegna");
				String numTracking = rs.getString("NumeroTracking");
				String corriere = rs.getString("Corriere");
				
				List<ArticoloInOrder> listaArticoliAssociata = new ArrayList<>();
				listaArticoliAssociata = articoloInOrderModel.restituisciArticoliAssociatiAdUnOrdine(codice);
				
				beans.add(new Order(codice,dataEx,prezzo,statoOrdine,dataConsegna,numTracking,corriere,acq,indirizzo,carta,listaArticoliAssociata));
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
	
	public void createOrder(Order toCreate) throws SQLException {
		
		Connection conn = null;
		PreparedStatement stm = null;
		
		String query = "INSERT INTO " + TABLE_NAME_ORDER + 
				" (Codice,DataEsecuzione,Prezzo,IndirizzoSpedizione,CartaCredito,Acquirente,StatoOrdine,DataConsegna,NumeroTracking,Corriere)"
				+ " VALUES(?,?,?,?,?,?,?,?,?,?);";
		
		String codice = toCreate.getCodice();
		Date dataEx = toCreate.getDataEsecuzione();
		double prezzo = toCreate.getPrezzo();
		String statoOrdine = toCreate.getStatoOrdine();
		Date dataConsegna = toCreate.getDataConsegna();
		String numTrack = toCreate.getNumeroTracking();
		String corriere = toCreate.getCodice();
		String acq = toCreate.getAcquirente().getUsername();
		String sa = toCreate.getShippingAddress().getCodice();
		String cc = toCreate.getCreditCard().getIdCarta();
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			
			stm.setString(1, codice);
			stm.setDate(2, (java.sql.Date) dataEx);
			stm.setDouble(3, prezzo);
			stm.setString(4, statoOrdine);
			stm.setDate(5, (java.sql.Date) dataConsegna);
			stm.setString(6, numTrack);
			stm.setString(7, corriere);
			stm.setString(8, acq);
			stm.setString(9, sa);
			stm.setString(10, cc);
			
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
	
	public void updateOrder(Order toUpdate) throws SQLException{
		Connection conn = null;
		PreparedStatement stm = null;
		
		String idOrder = toUpdate.getCodice();
		String statoOrdine = toUpdate.getStatoOrdine();
		Date dataConsegna = toUpdate.getDataConsegna();
		String numTrack = toUpdate.getNumeroTracking();
		String corriere = toUpdate.getCorriere();
		
		
		String query = "update " + TABLE_NAME_ORDER + " set StatoOrdine = ?, DataConsegna = ?, NumeroTracking = ?,Corriere = ?" 
		+ " where Codice =?;";
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			
			stm.setString(1,statoOrdine);
			stm.setDate(2, (java.sql.Date) dataConsegna);
			stm.setString(3, numTrack);
			stm.setString(4, corriere);
			stm.setString(5, idOrder);
			
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
	
	
	
}
