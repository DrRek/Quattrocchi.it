package it.unisa.quattrocchi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import it.unisa.quattrocchi.entity.ArticoloInOrder;
import it.unisa.quattrocchi.entity.Order;

/**
 * @author quattrocchi.it
 * Questa classe è un manager che si occupa di interagire con il database.
 * Gestisce le query riguardanti gli articoli in ordine.
 */
public class ArticoloInOrderModel {
	
	public static final String TABLE_NAME_ARTICOLOINORDINE = "articoloinorder";
	
	public static final String RETRIEVE_BY_ID = "SELECT * FROM "+TABLE_NAME_ARTICOLOINORDINE+" WHERE Codice = ?;";
	public static final String RETRIEVE_ALL = "SELECT * FROM "+TABLE_NAME_ARTICOLOINORDINE+";";
	public static final String RETRIEVE_BY_ORDER = "SELECT * FROM "+TABLE_NAME_ARTICOLOINORDINE+" WHERE Ordine = ?;";
	public static final String INSERT_ARTICLE_BY_ORDER = "INSERT INTO "+TABLE_NAME_ARTICOLOINORDINE+" (Modello,Marca,Img1,Img2,Img3,Descrizione,Prezzo,Quantita,Ordine) VALUES(?,?,?,?,?,?,?,?,?);";

	
	/**
	 * Questo metodo si occupa di verificare se nel database è presente un articolo in ordine
	 * tramite una codice specifico preso in input.
	 * @param codiceProdotto un oggetto codiceProdotto di tipo <strong>String</strong>
	 * @return un oggetto di tipo <strong>ArticoloInOrder</strong>, altrimenti null.
	 * @throws SQLException
	 * 
	 * @precondition codiceProdotto != null e corrisponde ad un articolo nel database.
	 */
	public ArticoloInOrder doRetrieveByIdInOrder(String codiceProdotto) throws SQLException{
		if(codiceProdotto == null) {
			return null;
		}
		
		Connection conn = null;
		PreparedStatement stm = null;
		ArticoloInOrder bean = null;
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(RETRIEVE_BY_ID);
			stm.setInt(1, Integer.parseInt(codiceProdotto));
			
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				int codice = rs.getInt("Codice");
				String modello = rs.getString("Modello");
				String marca = rs.getString("Marca");
				String img1 = rs.getString("Img1");
				String img2 = rs.getString("Img2");
				String img3 = rs.getString("Img3");
				String descrizione = rs.getString("Descrizione");
				double prezzo = rs.getDouble("Prezzo");
				int quantità = rs.getInt("Quantita");
				
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
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(RETRIEVE_ALL);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				int codice = rs.getInt("Codice");
				String modello = rs.getString("Modello");
				String marca = rs.getString("Marca");
				String img1 = rs.getString("Img1");
				String img2 = rs.getString("Img2");
				String img3 = rs.getString("Img3");
				String descrizione = rs.getString("Descrizione");
				double prezzo = rs.getDouble("Prezzo");
				int quantità = rs.getInt("Quantita");
				
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
	 * @param codice2 un oggetto codiceOrdine di tipo <strong>String</strong>
	 * @return una lista di articoli in ordine di tipo <strong>ArticoloInOrder</strong>, altrimenti null.
	 * @throws SQLException
	 * 
	 * @precondition idOrder != 0 e corrisponde ad un ordine presente nel database.
	 */
	public List<ArticoloInOrder> restituisciArticoliAssociatiAdUnOrdine(int idOrder) throws SQLException{
		if(idOrder == 0) {
			return null;
		}
		
		Connection conn = null;
		PreparedStatement stm = null;
		List<ArticoloInOrder> beans = new ArrayList<>();
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(RETRIEVE_BY_ORDER);
			stm.setInt(1, idOrder);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				idOrder = rs.getInt("Codice");
				String modello = rs.getString("Modello");
				String marca = rs.getString("Marca");
				String img1 = rs.getString("Img1");
				String img2 = rs.getString("Img2");
				String img3 = rs.getString("Img3");
				String descrizione = rs.getString("Descrizione");
				double prezzo = rs.getDouble("Prezzo");
				int quantità = rs.getInt("Quantita");
				
				beans.add(new ArticoloInOrder(idOrder,modello,marca,img1,img2,img3,descrizione,prezzo,quantità));
				
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
	 * Questo metodo di occupa di aggiungere un articolo ad un ordine.
	 * @param a un oggetto a di tipo <strong>ArticoloInOrder</strong>
	 * @param o un oggetto o di tipo <strong>Order</strong>
	 * @throws SQLException
	 * 
	 * @precondition 	a != null.
	 * 					o != null.
	 */
	public void addArticle(ArticoloInOrder a, Order o) throws SQLException{
		if(a == null || o == null) {
			return;
		}
		
		Connection conn = null;
		PreparedStatement stm = null;
		
		String modello = a.getModello();
		String marca = a.getMarca();
		String img1 = a.getImg1();
		String img2 = a.getImg2();
		String img3 = a.getImg3();
		String descrizione = a.getDescrizione();
		double prezzo = a.getPrezzo();
		int quantità = a.getQuantità();
		int codiceOrdine = o.getCodice();
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(INSERT_ARTICLE_BY_ORDER, Statement.RETURN_GENERATED_KEYS);
			
			stm.setString(1, modello);
			stm.setString(2, marca);
			stm.setString(3, img1);
			stm.setString(4, img2);
			stm.setString(5, img3);
			stm.setString(6, descrizione);
			stm.setDouble(7, prezzo);
			stm.setInt(8, quantità);
			stm.setInt(9, codiceOrdine);
			
			stm.executeUpdate();
			
			ResultSet rs = stm.getGeneratedKeys();
			if(rs.next()) {
				a.setCodice(rs.getInt(1));
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
}
