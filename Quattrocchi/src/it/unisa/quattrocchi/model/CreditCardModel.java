package it.unisa.quattrocchi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.entity.CreditCard;
import it.unisa.quattrocchi.entity.ShippingAddress;

public class CreditCardModel {
	static AcquirenteModel acquirenteModel = new AcquirenteModel();

	
	private static final String TABLE_NAME_CREDITCARD = "quattrocchidb.cartacredito";
	
	public CreditCard doRetrieveById(String idCarta) throws SQLException {
		Connection conn = null;
		PreparedStatement stm = null;
		CreditCard bean = null;
		
		String query = "SELECT * FROM " + TABLE_NAME_CREDITCARD + 
				" WHERE Id = ?;";
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			stm.setString(1, idCarta);
			
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {
				String numeroCC  = rs.getString("NumeroCC"); //da cambiare nel db
				String intestatario = rs.getString("Intestatario");
				String circuito = rs.getString("Circuito");
				Date dataScadenza = rs.getTimestamp("DataScadenza");
				int cvv = rs.getInt("CvcCvv");
				String indirizzo = rs.getString("Indirizzo");
				Acquirente acq = acquirenteModel.doRetriveById(rs.getString("Acquirente"));
				
				bean = new CreditCard(idCarta, numeroCC, intestatario, circuito , dataScadenza, cvv, acq);
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
	
	public List<CreditCard> doRetrieveByUser(String acquirente) throws SQLException{
		Connection conn = null;
		PreparedStatement stm = null;
		List<CreditCard> beans = new ArrayList<>();
		
		String query = "SELECT * FROM " + TABLE_NAME_CREDITCARD + " WHERE acquirente = ?;";
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			stm.setString(1, acquirente);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				String idCarta  = rs.getString("IdCarta");
				String numeroCC  = rs.getString("NumeroCC"); //da cambiare nel db
				String intestatario = rs.getString("Intestatario");
				String circuito = rs.getString("Circuito");
				Date dataScadenza = rs.getTimestamp("DataScadenza");
				int cvv = rs.getInt("CvcCvv");
				Acquirente acq = acquirenteModel.doRetriveById(rs.getString("Acquirente"));
				
				beans.add(new CreditCard(idCarta, numeroCC, intestatario, circuito , dataScadenza, cvv, acq));
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