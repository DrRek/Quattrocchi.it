package it.unisa.quattrocchi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.unisa.quattrocchi.entity.Article;
import it.unisa.quattrocchi.entity.ArticleInStock;

public class ArticleModel {
	
	private static final String TABLE_NAME_CATALOGO = "quattrocchidb.articoloinstock";
	private static final String TABLE_NAME_ORDINE = "quattrocchidb.articoloinorder";
	private static final String TABLE_NAME_CARRELLO = "quattrocchidb.articoloincarrello";
	
	//Metodo cambiato dall'odd, deve cercare solo nel catalogo
	public Article doRetrieveByIdInStock(String codiceProdotto) throws SQLException {
		Connection conn = null;
		PreparedStatement stm = null;
		Article bean = null;

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
				String descrizione = rs.getString("Descrzione");
				Double prezzo = rs.getDouble("Prezzo");
				int disponibilita = rs.getInt("Disponibilita");

				bean = new ArticleInStock(codice, modello, marca, img1, img2, img3, descrizione, prezzo, disponibilita);
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
	
	//Metodo cambiato dall'odd, deve cercare solo nel catalogo
	public List<Article> doRetrieveAllInStock() throws SQLException{
		Connection conn = null;
		PreparedStatement stm = null;
		List<Article> beans = new ArrayList<>();

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
				String descrizione = rs.getString("Descrzione");
				Double prezzo = rs.getDouble("Prezzo");
				int disponibilita = rs.getInt("Disponibilita");

				beans.add(new ArticleInStock(codice, modello, marca, img1, img2, img3, descrizione, prezzo, disponibilita));
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
	
	public List<Article> doRetrieveSimpleSearch(String daCercare) throws SQLException{
		String query = "select * from "+TABLE_NAME_CATALOGO + "where (Modello LIKE ?) or (Marca LIKE ?) or (Descrizione LIKE ?)";
		Connection conn = null;
		PreparedStatement stm = null;
		List<Article> beans = new ArrayList<>();

		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			stm.setString(0, daCercare);
			stm.setString(1, daCercare);
			stm.setString(2, daCercare);
			
			ResultSet rs = stm.executeQuery();

			if(rs.next()) {
				String codice = rs.getString("Codice");
				String modello = rs.getString("Modello");
				String marca = rs.getString("Marca");
				String img1 = rs.getString("Img1");
				String img2 = rs.getString("Img2");
				String img3 = rs.getString("Img3");
				String descrizione = rs.getString("Descrzione");
				Double prezzo = rs.getDouble("Prezzo");
				int disponibilita = rs.getInt("Disponibilita");

				beans.add(new ArticleInStock(codice, modello, marca, img1, img2, img3, descrizione, prezzo, disponibilita));
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
	
	public List<Article> doRetrieveAdvancedSearch(String daCercare, String marcaDaCercare, double minPrice, double maxPrice, String colore) throws SQLException{
		String query = "select * from "+TABLE_NAME_CATALOGO + "where ((Modello LIKE ?) or (Marca LIKE ?) or (Descrizione LIKE ?))";
		query+=" and (Prezzo >= ? and Prezzo <= ?)";
		if(marcaDaCercare!=null && marcaDaCercare!="") {
			query+=" and (Marca LIKE ?)";
		}
		if(colore!=null && colore!="") {
			query+=" and (Descrizione LIKE ?)";
		}
		
		
		Connection conn = null;
		PreparedStatement stm = null;
		List<Article> beans = new ArrayList<>();

		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(query);
			stm.setString(0, daCercare);
			stm.setString(1, daCercare);
			stm.setString(2, daCercare);
			stm.setDouble(3, minPrice);
			stm.setDouble(4, maxPrice);
			if(marcaDaCercare!=null && marcaDaCercare!="") {
				stm.setString(5, marcaDaCercare);
				if(colore!=null && colore!="") {
					stm.setString(6, colore);
				}
			}else if(colore!=null && colore!="") {
				stm.setString(5, colore);
			}
			
			ResultSet rs = stm.executeQuery();

			if(rs.next()) {
				String codice = rs.getString("Codice");
				String modello = rs.getString("Modello");
				String marca = rs.getString("Marca");
				String img1 = rs.getString("Img1");
				String img2 = rs.getString("Img2");
				String img3 = rs.getString("Img3");
				String descrizione = rs.getString("Descrzione");
				Double prezzo = rs.getDouble("Prezzo");
				int disponibilita = rs.getInt("Disponibilita");

				beans.add(new ArticleInStock(codice, modello, marca, img1, img2, img3, descrizione, prezzo, disponibilita));
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
	
	//cambiato dall'odd, sarebbe il doUpdate
	public void doSave(Article toSave) {
		//In teoria non lo dobbiamo implementare perché non ci serve
		return;
	}
}
