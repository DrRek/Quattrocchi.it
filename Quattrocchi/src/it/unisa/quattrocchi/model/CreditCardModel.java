package it.unisa.quattrocchi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.entity.CreditCard;


/**
 * 
 * @author quattrocchi.it
 * Questa classe � un manager che si occupa di interagire con il database.
 * Gestisce le query riguardanti le carte di credito.
 */
public class CreditCardModel {

	public static final String TABLE_NAME_CREDITCARD = "cartacredito";
	
	public static final String SELECT_CREDIT_CARD_BY_ID = "SELECT * FROM "+TABLE_NAME_CREDITCARD+" WHERE IdCarta = ?;";
	public static final String SELECT_ALL_CREDIT_CARD_BY_ACQUIRENTE = "SELECT * FROM "+TABLE_NAME_CREDITCARD+" WHERE Acquirente = ?;";
	public static final String INSET_CREDIT_CARD = "INSERT INTO "+TABLE_NAME_CREDITCARD+" (NumeroCC,Intestatario,Circuito,DataScadenza,CvcCvv,Acquirente) VALUES(?,?,?,?,?,?);";
	public static final String UPDATE_CREDIT_CARD = "update "+TABLE_NAME_CREDITCARD+" set NumeroCC = ?, Intestatario = ?,Circuito = ?,DataScadenza = ?,CvcCvv = ? where IdCarta = ? AND Acquirente = ?;";
	public static final String DELETE_CREDIT_CARD = "delete from "+TABLE_NAME_CREDITCARD+" where IdCarta = ?;";
	public static final String SET_CREDIT_CART_ACQUIRENTE_NULL = "update "+TABLE_NAME_CREDITCARD+" set Acquirente = NULL where IdCarta = ?;";
	
	static AcquirenteModel acquirenteModel = new AcquirenteModel();
	
	/**
	 * Questo metodo si occupa di effettuare la ricerca di una carta di credito
	 * tramite id preso in input.
	 * @param idCarta un oggetto idCarta di tipo <strong>String</strong>
	 * @return un oggetto di tipo <strong>CreditCard</strong>, altrimenti null.
	 * @throws SQLException
	 * 
	 * @precondition idCarta != 0 e corrisponde ad una carta presente nel database.
	 */
	public CreditCard doRetrieveById(int idCarta) throws SQLException {
		if(idCarta == 0) {
			return null;
		}
		
		Connection conn = null;
		PreparedStatement stm = null;
		CreditCard bean = null;
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(SELECT_CREDIT_CARD_BY_ID);
			stm.setInt(1, idCarta);
			
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
	 * 
	 * @precondition username != null.
	 */
	public List<CreditCard> doRetrieveByUser(String username) throws SQLException{
		if(username == null) {
			return null;
		}
		Connection conn = null;
		PreparedStatement stm = null;
		List<CreditCard> beans = new ArrayList<>();
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(SELECT_ALL_CREDIT_CARD_BY_ACQUIRENTE);
			stm.setString(1, username);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				int idCarta  = rs.getInt("IdCarta");
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
	 * 
	 * @precondition toCreate != null.
	 */
	public void createCreditCard(CreditCard toCreate) throws SQLException{
		if(toCreate == null) {
			return;
		}
		
		Connection conn = null;
		PreparedStatement stm = null;
		
		String numeroCC = toCreate.getNumeroCC();
		String intestatario = toCreate.getIntestatario();
		String circuito = toCreate.getCircuito();
		Date dataScadenza = toCreate.getDataScadenza();
		int cvccvv = toCreate.getCvv();
		String acquirente = toCreate.getAcquirente().getUsername();
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(INSET_CREDIT_CARD, Statement.RETURN_GENERATED_KEYS);
			
			stm.setString(1, numeroCC);
			stm.setString(2, intestatario);
			stm.setString(3, circuito);
			stm.setDate(4, new java.sql.Date(dataScadenza.getTime()));
			stm.setInt(5, cvccvv);
			stm.setString(6, acquirente);
			
			stm.executeUpdate();
			
			ResultSet rs = stm.getGeneratedKeys();
			if(rs.next()) {
				toCreate.setIdCarta(rs.getInt(1));
			}
			
			rs.close();
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
	 * 
	 * @precondition toUpdate != null.
	 */
	public void updateCreditCard(CreditCard toUpdate) throws SQLException{
		if(toUpdate == null) {
			return;
		}
		
		Connection conn = null;
		PreparedStatement stm = null;
		
		int idCarta = toUpdate.getIdCarta();
		String numeroCC = toUpdate.getNumeroCC();
		String intestatario = toUpdate.getIntestatario();
		String circuito = toUpdate.getCircuito();
		Date dataScadenza = toUpdate.getDataScadenza();
		int cvccvv = toUpdate.getCvv();
		String acquirente = toUpdate.getAcquirente().getUsername();
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(UPDATE_CREDIT_CARD);
			
			stm.setString(1, numeroCC);
			stm.setString(2, intestatario);
			stm.setString(3, circuito);
			stm.setDate(4, new java.sql.Date(dataScadenza.getTime()));
			stm.setInt(5, cvccvv);
			stm.setInt(6, idCarta);
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
	 * @param id un oggetto toDelete di tipo <strong>CreditCard</strong>
	 * @throws SQLException
	 * 
	 * @precondition id != 0 e corrisponde ad una carta presente nel database.
	 */
	public void deleteCreditCard(int id) throws SQLException{
		if(id == 0) {
			return;
		}
		
		Connection conn = null;
		PreparedStatement stm = null;
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(DELETE_CREDIT_CARD);
			
			stm.setInt(1, id);
			
			stm.executeUpdate();
			stm.close();
			conn.commit();
		} catch(MySQLIntegrityConstraintViolationException e){
			stm.close();
			conn.commit();
			
			stm = conn.prepareStatement(SET_CREDIT_CART_ACQUIRENTE_NULL);
			stm.setInt(1, id);
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