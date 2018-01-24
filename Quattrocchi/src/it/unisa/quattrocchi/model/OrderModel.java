package it.unisa.quattrocchi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.entity.ArticoloInOrder;
import it.unisa.quattrocchi.entity.CreditCard;
import it.unisa.quattrocchi.entity.Order;
import it.unisa.quattrocchi.entity.ShippingAddress;


/**
 * 
 * @author quattrocchi.it
 * Questa classe è un manager che si occupa di interagire con il database.
 * Gestisce le query riguardanti gli ordini.
 */
public class OrderModel {
	
	public static final String TABLE_NAME_ORDER = "quattrocchidb.ordine";
	
	public static String RETRIEVE_ORDER_BY_ID = "SELECT * FROM " + TABLE_NAME_ORDER + " WHERE Codice = ?;";
	public static String RETRIEVE_ALL_ORDER = "SELECT * FROM " + TABLE_NAME_ORDER + ";";
	public static String INSERT_ORDER = "INSERT INTO "+TABLE_NAME_ORDER+" (DataEsecuzione,Prezzo,IndirizzoSpedizione,CartaCredito,Acquirente,StatoOrdine,DataConsegna,NumeroTracking,Corriere) VALUES(?,?,?,?,?,?,?,?,?);";
	public static String UPDATE_ORDER = "update "+TABLE_NAME_ORDER+" set StatoOrdine = ?, DataConsegna = ?, NumeroTracking = ?,Corriere = ? where Codice =?;";
	public static String SELECT_ORDER_BY_ACQUIRENTE = "SELECT * FROM " + TABLE_NAME_ORDER + " where Acquirente = ? order by DataEsecuzione;";
	
	static ShippingAddressModel shippingAddressModel = new ShippingAddressModel();
	static CreditCardModel creditCardModel = new CreditCardModel();
	static AcquirenteModel acquirenteModel = new AcquirenteModel();
	static ArticoloInOrderModel articoloInOrderModel = new ArticoloInOrderModel();
	
	
	/**
	 * Questo metodo si occupa di effettuare la ricerca di un ordine per id.
	 * @param idOrder un oggetto idOrder di tipo <strong>String</strong>
	 * @return un oggetto di tipo <strong>Order</strong>, altrimenti null.
	 * @throws SQLException
	 * 
	 * @precondition idOrder != 0 e corrisponde ad un ordine presente nel database.
	 */
	public Order doRetrieveById(int idOrder) throws SQLException {
		if(idOrder == 0) {
			return null;
		}
		
		Connection conn = null;
		PreparedStatement stm = null;
		Order bean = null;
		List<ArticoloInOrder> listaArticoliInOrdine = new ArrayList<>();
		listaArticoliInOrdine = articoloInOrderModel.restituisciArticoliAssociatiAdUnOrdine(idOrder);
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(RETRIEVE_ORDER_BY_ID);
			stm.setInt(1, idOrder);
			
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {
				int codice = rs.getInt("Codice");
				Date dataEx = rs.getDate("DataEsecuzione");
				double prezzo = rs.getDouble("Prezzo");
				ShippingAddress indirizzo = shippingAddressModel.doRetrieveById(rs.getString("IndirizzoSpedizione"));
				CreditCard carta = creditCardModel.doRetrieveById(rs.getInt("CartaCredito"));
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

	
	/**
	 * Questo metodo si occupa di effettuare la ricerca di tutti gli ordini.
	 * @return una lista di ordini di tipo <strong>Order</strong>, altrimenti null.
	 * @throws SQLException
	 */
	public List<Order> doRetrieveAll() throws SQLException{
		Connection conn = null;
		PreparedStatement stm = null;
		List<Order> beans = new ArrayList<>();
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(RETRIEVE_ALL_ORDER);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				int codice = rs.getInt("Codice");
				Date dataEx = rs.getDate("DataEsecuzione");
				double prezzo = rs.getDouble("Prezzo");
				ShippingAddress indirizzo = shippingAddressModel.doRetrieveById(rs.getString("IndirizzoSpedizione"));
				CreditCard carta = creditCardModel.doRetrieveById(rs.getInt("CartaCredito"));
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
	
	
	/**
	 * Questo metodo si occupa di rendere persistente un nuovo ordine.
	 * @param toCreate un oggetto toCreate di tipo <strong>Order</strong>
	 * @throws SQLException
	 * 
	 * @precondition toCreate != null
	 */
	public void createOrder(Order toCreate) throws SQLException {
		if(toCreate == null) {
			return;
		}
		
		Connection conn = null;
		PreparedStatement stm = null;
		
		Date dataEx = toCreate.getDataEsecuzione();
		double prezzo = toCreate.getPrezzo();
		String statoOrdine = toCreate.getStatoOrdine();
		Date dataConsegna = toCreate.getDataConsegna();
		String numTrack = toCreate.getNumeroTracking();
		String corriere = toCreate.getCorriere();
		String acq = toCreate.getAcquirente().getUsername();
		int sa = toCreate.getShippingAddress().getCodice();
		int cc = toCreate.getCreditCard().getIdCarta();
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
			
			stm.setDate(1, new java.sql.Date(dataEx.getTime()));
			stm.setDouble(2, prezzo);
			stm.setInt(3, sa);
			stm.setInt(4, cc);
			stm.setString(5, acq);
			stm.setString(6, statoOrdine);
			
			java.sql.Date consegna = null;
			if(dataConsegna!=null) {
				consegna = new java.sql.Date(dataConsegna.getTime());
			}
			stm.setDate(7, consegna);
			
			stm.setString(8, numTrack);
			stm.setString(9, corriere);
			
			stm.executeUpdate();
			ResultSet rs = stm.getGeneratedKeys();
			if(rs.next()) {
				toCreate.setCodice(rs.getInt(1));
				
			}
			rs.close();
			stm.close();
			conn.commit();
			
			for(ArticoloInOrder a : toCreate.getListaArticoliInOrdine()) {
				articoloInOrderModel.addArticle(a, toCreate);
			}
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
	 * Questo metodo si occupa di effettuare l'aggiornamento di un ordine.
	 * @param toUpdate un oggetto toUpdate di tipo <strong>Order</strong>
	 * @throws SQLException
	 * 
	 * @precondition toUpdate != null;
	 */
	public void updateOrder(Order toUpdate) throws SQLException{
		if(toUpdate == null) {
			return;
		}
		Connection conn = null;
		PreparedStatement stm = null;
		
		int idOrder = toUpdate.getCodice();
		String statoOrdine = toUpdate.getStatoOrdine();
		Date dataConsegna = toUpdate.getDataConsegna();
		String numTrack = toUpdate.getNumeroTracking();
		String corriere = toUpdate.getCorriere();
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(UPDATE_ORDER);
			
			stm.setString(1,statoOrdine);
			stm.setDate(2, (java.sql.Date) dataConsegna);
			stm.setString(3, numTrack);
			stm.setString(4, corriere);
			stm.setInt(5, idOrder);
			
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
	 * Questo metodo si occupa di ricercare tutti gli ordini effettuati da un determinato acquirente.
	 * @param acquirente un oggetto acquirente di tipo <strong>Acquirente</strong>
	 * @return una lista di ordini di tipo <strong>Order</strong>, altrimenti null.
	 * @throws SQLException
	 * 
	 * @precondition acquirente != null.
	 */
	public List<Order> doRetrieveByAcquirente(Acquirente acquirente) throws SQLException {
		if(acquirente == null) {
			return null;
		}
		
		Connection conn = null;
		PreparedStatement stm = null;
		List<Order> beans = new ArrayList<>();
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(SELECT_ORDER_BY_ACQUIRENTE);
			stm.setString(1, acquirente.getUsername());
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				int codice = rs.getInt("Codice");
				Date dataEx = rs.getDate("DataEsecuzione");
				double prezzo = rs.getDouble("Prezzo");
				ShippingAddress indirizzo = shippingAddressModel.doRetrieveById(rs.getString("IndirizzoSpedizione"));
				CreditCard carta = creditCardModel.doRetrieveById(rs.getInt("CartaCredito"));
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
}
