package it.unisa.quattrocchi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import it.unisa.quattrocchi.entity.ArticoloInStock;


/**
 * 
 * @author quattrocchi.it
 * Questa classe è un manager che si occupa di interagire con il database.
 * Gestisce le query riguardanti gli articoli in stock. 
 */
public class ArticoloInStockModel {
	
	private static final String TABLE_NAME_CATALOGO = "quattrocchidb.articoloinstock";

	/**
	 * Questo metodo si occupa di verificare se nel database è presente un articolo in stock
	 * tramite una codice specifico preso in input.
	 * @param codiceProdotto un oggetto codiceProdotto di tipo <strong>String</strong>
	 * @return un oggetto di tipo <strong>ArticoloInStock</strong>, altrimenti null.
	 * @throws SQLException
	 */
	public ArticoloInStock doRetrieveByIdInStock(String codiceProdotto) throws SQLException {
		Connection conn = null;
		PreparedStatement stm = null;
		ArticoloInStock bean = null;

		String query = "SELECT * FROM " + TABLE_NAME_CATALOGO + " WHERE Codice = ?;";

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
				Double prezzo = rs.getDouble("Prezzo");
				int disponibilita = rs.getInt("Disponibilita");

				bean = new ArticoloInStock(codice, modello, marca, img1, img2, img3, descrizione, prezzo, disponibilita);
			}

			stm.close();
			rs.close();
			conn.commit();
		} finally {
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
	 * Questo metodo si occupa di effettuare la ricerca di tutti gli articoli in stock
	 * presenti nel database.
	 * @return una lista di articoli in stock di tipo <strong>ArticoloInStock</strong>, altrimenti null.
	 * @throws SQLException
	 */
	public List<ArticoloInStock> doRetrieveAllInStock() throws SQLException{
		Connection conn = null;
		PreparedStatement stm = null;
		List<ArticoloInStock> beans = new ArrayList<>();

		String query = "SELECT * FROM " + TABLE_NAME_CATALOGO + ";";

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
				Double prezzo = rs.getDouble("Prezzo");
				int disponibilita = rs.getInt("Disponibilita");

				beans.add(new ArticoloInStock(codice, modello, marca, img1, img2, img3, descrizione, prezzo, disponibilita));
			}

			stm.close();
			rs.close();
			conn.commit();
		} finally {
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
	 * Questo metodo si occupa di effettuare la ricerca di articoli in stock tramite
	 * una stringa immessa dall'utente come parametro di ricerca.
	 * @param daCercare un oggetto daCercare di tipo <strong>String</strong>
	 * @return una lista di articoli in stock di tipo <strong>ArticoloInStock</strong>, altrimenti null.
	 * @throws SQLException
	 */
	public List<ArticoloInStock> doRetrieveSimpleSearch(String daCercare) throws SQLException{
		String query = "select * from "+TABLE_NAME_CATALOGO + " where (Modello LIKE ?) or (Marca LIKE ?) or (Descrizione LIKE ?)";
		
		Connection conn = null;
		PreparedStatement stm = null;
		List<ArticoloInStock> beans = new ArrayList<>();

		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			
			daCercare = "%"+daCercare+"%";
			stm.setString(1, daCercare);
			stm.setString(2, daCercare);
			stm.setString(3, daCercare);
			
			ResultSet rs = stm.executeQuery();

			while(rs.next()) {
				String codice = rs.getString("Codice");
				String modello = rs.getString("Modello");
				String marca = rs.getString("Marca");
				String img1 = rs.getString("Img1");
				String img2 = rs.getString("Img2");
				String img3 = rs.getString("Img3");
				String descrizione = rs.getString("Descrizione");
				Double prezzo = rs.getDouble("Prezzo");
				int disponibilita = rs.getInt("Disponibilita");

				beans.add(new ArticoloInStock(codice, modello, marca, img1, img2, img3, descrizione, prezzo, disponibilita));
			}

			stm.close();
			rs.close();
			conn.commit();
		} finally {
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
	 * Questo metodo si occupa di effettuare la ricerca di prodotti che matchano con i vari parametri
	 * immessi dall'utente nell'apposito form.
	 * @param daCercare un oggetto daCercare di tipo <strong>String</strong>
	 * @param marca un oggetto marca di tipo <strong>String</strong>
	 * @param minPrice un oggetto minPrice di tipo <strong>double</strong>
	 * @param maxPrice un oggetto maxPrice di tipo <strong>double</strong>
	 * @param colore un oggetto colore di tipo <strong>String</strong>
	 * @return una lista di articoli in stock di tipo <strong>ArticoloInStock</strong>, altrimenti null.
	 * @throws SQLException
	 */
	public List<ArticoloInStock> doRetrieveAdvancedSearch(String daCercare, String marca, double minPrice, double maxPrice, String colore) throws SQLException{
		String query = "select * from "+TABLE_NAME_CATALOGO + " where ((Modello LIKE ?) or (Marca LIKE ?) or (Descrizione LIKE ?))"
				+ " and (Prezzo >= ? and Prezzo <= ?) and (Marca LIKE ?) and ((Modello LIKE ?) or (Descrizione LIKE ?))";
		
		daCercare = "%"+daCercare+"%";
		marca = "%"+marca+"%";
		colore = "%"+colore+"%";
		
		Connection conn = null;
		PreparedStatement stm = null;
		List<ArticoloInStock> beans = new ArrayList<>();

		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			stm.setString(1, daCercare);
			stm.setString(2, daCercare);
			stm.setString(3, daCercare);
			stm.setDouble(4, minPrice);
			stm.setDouble(5, maxPrice);
			stm.setString(6, marca);
			stm.setString(7, colore);
			stm.setString(8, colore);
			
			ResultSet rs = stm.executeQuery();

			while(rs.next()) {
				String codice = rs.getString("Codice");
				String modello = rs.getString("Modello");
				String marca1 = rs.getString("Marca");
				String img1 = rs.getString("Img1");
				String img2 = rs.getString("Img2");
				String img3 = rs.getString("Img3");
				String descrizione = rs.getString("Descrizione");
				Double prezzo = rs.getDouble("Prezzo");
				int disponibilita = rs.getInt("Disponibilita");

				beans.add(new ArticoloInStock(codice, modello, marca1, img1, img2, img3, descrizione, prezzo, disponibilita));
			}

			stm.close();
			rs.close();
			conn.commit();
		} finally {
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
