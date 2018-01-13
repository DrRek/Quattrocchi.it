package it.unisa.quattrocchi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.unisa.quattrocchi.entity.ArticoloInStock;

public class ArticleModel {
	
	private static final String TABLE_NAME_CATALOGO = "quattrocchidb.articoloinstock";
	private static final String TABLE_NAME_ORDINE = "quattrocchidb.articoloinorder";
	private static final String TABLE_NAME_CARRELLO = "quattrocchidb.articoloincarrello";
	
	
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
			
			System.out.println(stm);
			
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
			
	public List<ArticoloInStock> doRetrieveAdvancedSearch(String daCercare, String marcaDaCercare, double minPrice, double maxPrice, String colore) throws SQLException{
		String query = "select * from "+TABLE_NAME_CATALOGO + " where ((Modello LIKE ?) or (Marca LIKE ?) or (Descrizione LIKE ?))"
				+ " and (Prezzo >= ? and Prezzo <= ?) and (Marca LIKE ?) and ((Modello LIKE ?) or (Descrizione LIKE ?))";
		
		daCercare = "%"+daCercare+"%";
		marcaDaCercare = "%"+marcaDaCercare+"%";
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
			stm.setString(6, marcaDaCercare);
			stm.setString(7, colore);
			stm.setString(8, colore);
			
			System.out.println(stm);
			
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
}
