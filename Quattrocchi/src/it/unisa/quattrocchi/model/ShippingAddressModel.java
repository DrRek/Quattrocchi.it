package it.unisa.quattrocchi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.entity.ShippingAddress;


/**
 * 
 * @author quattrocchi.it
 * Questa classe � un manager che si occupa di interagire con il database.
 * Gestisce le query riguardanti gli indirizzi di spedizione.
 */
public class ShippingAddressModel {
	
	private static final String TABLE_NAME_ADDRESS = "quattrocchidb.indirizzospedizione";
	static AcquirenteModel acquirenteModel = new AcquirenteModel();
	
	
	/**
	 * Questo metodo si occupa di effettuare la ricerca di un indirizzo di spedizione per id.
	 * @param idShip un oggetto idShip di tipo <strong>String</strong>
	 * @return un oggetto di tipo <strong>ShippingAddress</strong>, altrimenti null.
	 * @throws SQLException
	 */
	public ShippingAddress doRetrieveById(String idShip) throws SQLException {
		Connection conn = null;
		PreparedStatement stm = null;
		ShippingAddress bean = null;
		
		String query = "SELECT * FROM " + TABLE_NAME_ADDRESS + 
				" WHERE Id = ?;";
		
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
				Acquirente acq = acquirenteModel.doRetriveById(rs.getString("Acquirente"));
				
				bean = new ShippingAddress(codice,stato,indirizzo,cap,provincia,nc,acq);
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
	 * Questo metodo si occupa di effettuare la ricerca degli indirizzi di spedizione per username.
	 * @param username un oggetto username di tipo <strong>String</strong>
	 * @return una lista di indirizzi di spedizione di tipo <strong>ArticoloInStock</strong>, altrimenti null.
	 * @throws SQLException
	 */
	public List<ShippingAddress> doRetrieveByUser(String username) throws SQLException{
		Connection conn = null;
		PreparedStatement stm = null;
		List<ShippingAddress> beans = new ArrayList<>();
		
		String query = "SELECT * FROM " + TABLE_NAME_ADDRESS + ";";
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				String codice = rs.getString("Id");
				String stato = rs.getString("Stato");
				String provincia = rs.getString("Provincia");
				int cap = rs.getInt("CAP");
				String indirizzo = rs.getString("Indirizzo");
				int nc = rs.getInt("NumeroCivico");
				Acquirente acq = acquirenteModel.doRetriveById(username);
				
				beans.add(new ShippingAddress(codice,stato,indirizzo,cap,provincia,nc,acq));
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
	 * Questo metodo si occupa di rendere persistente un nuovo indirizzo di spedizione.
	 * @param toCreate un oggetto toCreate di tipo <strong>ShippingAddress</strong>
	 * @throws SQLException
	 */
	public String createShippingAddress(ShippingAddress toCreate) throws SQLException{
		String codice = null;
		Connection conn = null;
		PreparedStatement stm = null;
		
		String query = "INSERT INTO " + TABLE_NAME_ADDRESS + 
				" (Id,Stato,Provincia,CAP,Indirizzo,NumeroCivico,Acquirente)"+
				" VALUES(?,?,?,?,?,?,?);";
		
		String id = toCreate.getCodice();
		String stato = toCreate.getStato();
		String provincia = toCreate.getProvincia();
		int cap = toCreate.getCap();
		String indirizzo = toCreate.getIndirizzo();
		int numeroCivico = toCreate.getNC();
		String acquirente = toCreate.getAcq().getUsername();
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			
			stm.setString(1, id);
			stm.setString(2, stato);
			stm.setString(3, provincia);
			stm.setInt(4, cap);
			stm.setString(5, indirizzo);
			stm.setInt(6, numeroCivico);
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
		
		query = "Select * from " + TABLE_NAME_ADDRESS +
				" where Stato = ? and Provincia = ? and CAP = ? and Indirizzo = ? and NumeroCivico = ? and Acquirente = ?";
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			
			stm.setString(1, stato);
			stm.setString(2, provincia);
			stm.setInt(3, cap);
			stm.setString(4, indirizzo);
			stm.setInt(5, numeroCivico);
			stm.setString(6, acquirente);
			
			System.out.println(stm);
			
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				 codice= rs.getString("id");
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
		return codice;
	}
	
	
	/**
	 * Questo metodo si occupa di effettuare l'aggiornamento di un indirizzo di spezione.
	 * @param toUpdate un oggetto toUpdate di tipo <strong>ShippingAddress</strong>
	 * @throws SQLException
	 */
	public void updateShippingAddress(ShippingAddress toUpdate) throws SQLException{
		Connection conn = null;
		PreparedStatement stm = null;
		
		String id = toUpdate.getCodice();
		String stato = toUpdate.getStato();
		String provincia = toUpdate.getProvincia();
		int cap = toUpdate.getCap();
		String indirizzo = toUpdate.getIndirizzo();
		int numeroCivico = toUpdate.getNC();
		String acquirente = toUpdate.getAcq().getUsername();
		
		String query = "update " + TABLE_NAME_ADDRESS + " set Stato = ?, Provincia = ?, CAP = ?, Indirizzo = ?, NumeroCivico = ?"
				+ " where Id = ? AND Acquirente = ?";
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			
			stm.setString(1, stato);
			stm.setString(2, provincia);
			stm.setInt(3, cap);
			stm.setString(4, indirizzo);
			stm.setInt(5, numeroCivico);
			stm.setString(6, id);
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
	 * Questo metodo si occupa di effettuare la rimozione di un indirizzo di spedizione.
	 * @param codice un oggetto toDelete di tipo <strong>ShippingAddress</strong>
	 * @throws SQLException
	 */
	public void deleteShippingAddress(String codice) throws SQLException{
		Connection conn = null;
		PreparedStatement stm = null;
		
		String query = "delete from " + TABLE_NAME_ADDRESS + " where Id = ?;";
		
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
