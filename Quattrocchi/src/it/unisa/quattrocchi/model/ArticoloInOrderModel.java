package it.unisa.quattrocchi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import it.unisa.quattrocchi.entity.ArticoloInOrder;

/**
 * @author quattrocchi.it
 * Questa classe è un manager che si occupa di interagire con il database.
 * Gestisce le query riguardanti gli articoli in ordine.
 */
public class ArticoloInOrderModel {
	
	private static final String TABLE_NAME_ARTICOLOINORDINE = "quattrocchidb.articoloinorder";
	
	/**
	 * Questo metodo si occupa di verificare se nel database è presente un articolo in ordine
	 * tramite una codice specifico preso in input.
	 * @param codiceProdotto un oggetto codiceProdotto di tipo <strong>String</strong>
	 * @return un oggetto di tipo <strong>ArticoloInOrder</strong>, altrimenti null.
	 * @throws SQLException
	 */
	public ArticoloInOrder doRetrieveByIdInOrder(String codiceProdotto) throws SQLException{
		
		Connection conn = null;
		PreparedStatement stm = null;
		ArticoloInOrder bean = null;
		
		String query = "SELCT * FROM " + TABLE_NAME_ARTICOLOINORDINE + "WHERE Codice = ?";
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			stm.setString(1, codiceProdotto);
			
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				String codice = rs.getString("Codice");
				String modello = rs.getString("Modello");
				String marca = rs.getString("Marca");
				String img1 = rs.getString("Img1");
				String img2 = rs.getString("Img2");
				String img3 = rs.getString("Img3");
				String descrizione = rs.getString("Descrizione");
				double prezzo = rs.getDouble("Prezzo");
				int quantità = rs.getInt("Quantità");
				
				bean = new ArticoloInOrder(codice,modello,marca,img1,img2,img3,descrizione,prezzo,quantità);
			}
			
			stm.close();
			rs.close();
			conn.commit();
		}finally {
			try {
				if (stm != null)
					stm.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		return bean;
	}
	
	
	/**
	 * Questo metodo si occupa di effettuare la ricerca di tutti gli articoli in ordine 
	 * presenti nel database.
	 * @return una lista di articoli in ordine di tipo <strong>ArticoloInOrder</strong>, altrimenti null.
	 * @throws SQLException
	 */
	public List<ArticoloInOrder> doRetrieveAllInOrder() throws SQLException{
		Connection conn = null;
		PreparedStatement stm = null;
		List<ArticoloInOrder> beans = new ArrayList<>();
		
		String query = "SELECT * FROM " + TABLE_NAME_ARTICOLOINORDINE + ";";
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				String codice = rs.getString("Codice");
				String modello = rs.getString("Modello");
				String marca = rs.getString("Marca");
				String img1 = rs.getString("Img1");
				String img2 = rs.getString("Img2");
				String img3 = rs.getString("Img3");
				String descrizione = rs.getString("Descrizione");
				double prezzo = rs.getDouble("Prezzo");
				int quantità = rs.getInt("Quantità");
				
				beans.add(new ArticoloInOrder(codice,modello,marca,img1,img2,img3,descrizione,prezzo,quantità));
			}
			
			stm.close();
			rs.close();
			conn.commit();
		}finally {
			try {
				if (stm != null)
					stm.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		return beans;
	}
	
	
	/**
	 * Questo metodo si occupa di effettuare la ricerca di tutti gli articoli in ordine
	 * associati ad un determinato ordine presenti nel database.
	 * @param codiceOrdine un oggetto codiceOrdine di tipo <strong>String</strong>
	 * @return una lista di articoli in ordine di tipo <strong>ArticoloInOrder</strong>, altrimenti null.
	 * @throws SQLException
	 */
	public List<ArticoloInOrder> restituisciArticoliAssociatiAdUnOrdine(String codiceOrdine) throws SQLException{
		Connection conn = null;
		PreparedStatement stm = null;
		List<ArticoloInOrder> beans = new ArrayList<>();
		
		String query = "SELECT * FROM " + TABLE_NAME_ARTICOLOINORDINE + " WHERE Ordine = ?;";
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			stm.setString(1, codiceOrdine);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				String codice = rs.getString("Codice");
				String modello = rs.getString("Modello");
				String marca = rs.getString("Marca");
				String img1 = rs.getString("Img1");
				String img2 = rs.getString("Img2");
				String img3 = rs.getString("Img3");
				String descrizione = rs.getString("Descrizione");
				double prezzo = rs.getDouble("Prezzo");
				int quantità = rs.getInt("Quantita");
				
				if(codiceOrdine.equals("999")) {
					System.out.println("Articolo aggiunto: " + codice + " " + modello + " " + marca);
				}
				
				beans.add(new ArticoloInOrder(codice,modello,marca,img1,img2,img3,descrizione,prezzo,quantità));
				
			}
			
			stm.close();
			rs.close();
			conn.commit();
		}finally {
			try {
				if (stm != null)
					stm.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		return beans;
	}
}
