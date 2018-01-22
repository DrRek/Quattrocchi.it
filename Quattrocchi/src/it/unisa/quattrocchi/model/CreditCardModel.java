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


/**
 * 
 * @author quattrocchi.it
 * Questa classe è un manager che si occupa di interagire con il database.
 * Gestisce le query riguardanti le carte di credito.
 */
public class CreditCardModel {
	
	static AcquirenteModel acquirenteModel = new AcquirenteModel();

	private static final String TABLE_NAME_CREDITCARD = "quattrocchidb.cartacredito";
	
	
	/**
	 * Questo metodo si occupa di effettuare la ricerca di una carta di credito
	 * tramite id preso in input.
	 * @param idCarta un oggetto idCarta di tipo <strong>String</strong>
	 * @return un oggetto di tipo <strong>CreditCard</strong>, altrimenti null.
	 * @throws SQLException
	 */
	public CreditCard doRetrieveById(String idCarta) throws SQLException {
		Connection conn = null;
		PreparedStatement stm = null;
		CreditCard bean = null;
		
		String query = "SELECT * FROM " + TABLE_NAME_CREDITCARD + 
				" WHERE IdCarta = ?;";
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			stm.setString(1, idCarta);
			
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {
				String numeroCC  = rs.getString("NumeroCC");
				String intestatario = rs.getString("Intestatario");
				String circuito = rs.getString("Circuito");
				Date dataScadenza = rs.getTimestamp("DataScadenza");
				int cvv = rs.getInt("CvcCvv");
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
	
	
	/**
	 * Questo metodo si occupa di effettuare la ricerca delle carte di credito associate
	 * ad un Acquirente tramite user preso in input.
	 * @param username un oggetto username di tipo <strong>String</strong>
	 * @return una lista di carte di credito di tipo <strong>CreditCard</strong>, altrimenti null.
	 * @throws SQLException
	 */
	public List<CreditCard> doRetrieveByUser(String username) throws SQLException{
		Connection conn = null;
		PreparedStatement stm = null;
		List<CreditCard> beans = new ArrayList<>();
		
		String query = "SELECT * FROM " + TABLE_NAME_CREDITCARD + " WHERE Acquirente = ?;";
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			stm.setString(1, username);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				String idCarta  = rs.getString("IdCarta");
				String numeroCC  = rs.getString("NumeroCC"); 
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
	
	/**
	 * Questo metodo si occupa di inserire una nuova carta di credito nel database.
	 * @param toCreate un oggetto toCreate di tipo <strong>CreditCard</strong>
	 * @throws SQLException
	 */
	public void createCreditCard(CreditCard toCreate) throws SQLException{
		Connection conn = null;
		PreparedStatement stm = null;
		
		String query = "INSERT INTO " + TABLE_NAME_CREDITCARD + 
				" (IdCarta,NumeroCC,Intestatario,Circuito,DataScadenza,CvcCvv,Acquirente)" +
				" VALUES(?,?,?,?,?,?,?);";
		
		String codice = toCreate.getIdCarta();
		String numeroCC = toCreate.getNumeroCC();
		String intestatario = toCreate.getIntestatario();
		String circuito = toCreate.getCircuito();
		Date dataScadenza = toCreate.getDataScadenza();
		int cvccvv = toCreate.getCvv();
		String acquirente = toCreate.getAcquirente().getUsername();
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			
			stm.setString(1, codice);
			stm.setString(2, numeroCC);
			stm.setString(3, intestatario);
			stm.setString(4, circuito);
			stm.setDate(5, new java.sql.Date(dataScadenza.getTime()));
			stm.setInt(6, cvccvv);
			stm.setString(7, acquirente);
			
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
	
	/**
	 * Questo metodo si occupa di aggiornare una carta di credito nel database.
	 * @param toUpdate un oggetto toUpdate di tipo <strong>CreditCard</strong>
	 * @throws SQLException
	 */
	public void updateCreditCard(CreditCard toUpdate) throws SQLException{
		Connection conn = null;
		PreparedStatement stm = null;
		
		String idCarta = toUpdate.getIdCarta();
		String numeroCC = toUpdate.getNumeroCC();
		String intestatario = toUpdate.getIntestatario();
		String circuito = toUpdate.getCircuito();
		Date dataScadenza = toUpdate.getDataScadenza();
		int cvccvv = toUpdate.getCvv();
		String acquirente = toUpdate.getAcquirente().getUsername();
		
		String query = "update " + TABLE_NAME_CREDITCARD + " set NumeroCC = ?, Intestatario = ?,Circuito = ?,DataScadenza = ?,CvcCvv = ? "+
		"where IdCarta = ? AND Acquirente = ?;";
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			
			stm.setString(1, numeroCC);
			stm.setString(2, intestatario);
			stm.setString(3, circuito);
			stm.setDate(4, (java.sql.Date) dataScadenza);
			stm.setInt(5, cvccvv);
			stm.setString(6, idCarta);
			stm.setString(7, acquirente);
			
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
	
	
	/**
	 * Questo metodo si occupa di cancellare una carta di credito nel database.
	 * @param codice un oggetto toDelete di tipo <strong>CreditCard</strong>
	 * @throws SQLException
	 */
	public void deleteCreditCard(String codice) throws SQLException{
		Connection conn = null;
		PreparedStatement stm = null;
		
		String query = "delete from " + TABLE_NAME_CREDITCARD + " where IdCarta = ?;";
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			
			stm.setString(1, codice);
			
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