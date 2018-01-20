package test.it.unisa.quattrocchi.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.Statement;

import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.entity.ShippingAddress;
import it.unisa.quattrocchi.model.AcquirenteModel;
import it.unisa.quattrocchi.model.DriverManagerConnectionPool;

public class ShippingAddressModel_jdbc {
	
	static AcquirenteModel acquirenteModel = new AcquirenteModel();
	
	private static Statement stmt;
	private static Connection conn;
	
	static {
		String db = "quattrocchidbtest";
		String username = "progetto";
		String password = "pw";
		String ip = "localhost";
		String port = "3306";
		
		try {
			//String url = "jdbc:mysql://" + ip + "/" + db;
			
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://"+ ip+":"+ port+"/"+db, username, password);
			stmt = (Statement) conn.createStatement();
			conn.setAutoCommit(false);
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	private static final String TABLE_NAME_ADDRESS = "indirizzospedizione";
	
	public ShippingAddress doRetrieveById(String idShip) throws SQLException {
		Connection connection = null;
		PreparedStatement stm = null;
		ShippingAddress bean = null;
		
		String query = "SELECT * FROM " + TABLE_NAME_ADDRESS + 
				" WHERE Id = ?;";
		
		try {
			connection = conn;
			stm = connection.prepareStatement(query);
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
			connection.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return bean;
	}
	
	public List<ShippingAddress> doRetrieveByUser(String username) throws SQLException{
		Connection connection = null;
		PreparedStatement stm = null;
		List<ShippingAddress> beans = new ArrayList<>();
		
		String query = "SELECT * FROM " + TABLE_NAME_ADDRESS + " where Acquirente = ?;";
		
		try {
			connection = conn;
			stm = connection.prepareStatement(query);
			stm.setString(1, username);
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
			connection.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return beans;
	}
	
	public void createShippingAddress(ShippingAddress toCreate) throws SQLException{
		//String codice = null;
		Connection connection = null;
		PreparedStatement stm = null;
		
		String query = "INSERT INTO " + TABLE_NAME_ADDRESS + 
				" (Id,Stato,Provincia,CAP,Indirizzo,NumeroCivico,Acquirente)"+
				" VALUES(?,?,?,?,?,?,?);";
		
		int id = Integer.parseInt(toCreate.getCodice());
		String stato = toCreate.getStato();
		String provincia = toCreate.getProvincia();
		int cap = toCreate.getCap();
		String indirizzo = toCreate.getIndirizzo();
		int numeroCivico = toCreate.getNC();
		String acquirente = toCreate.getAcq().getUsername();
		
		try {
			connection = conn;
			stm = connection.prepareStatement(query);
			
			stm.setInt(1, id);
			stm.setString(2, stato);
			stm.setString(3, provincia);
			stm.setInt(4, cap);
			stm.setString(5, indirizzo);
			stm.setInt(6, numeroCivico);
			stm.setString(7, acquirente);
			
			stm.executeUpdate();
			stm.close();
			conn.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	public void updateShippingAddress(ShippingAddress toUpdate) throws SQLException{
		Connection connection = null;
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
			connection = conn;
			stm = connection.prepareStatement(query);
			
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
		}catch(Exception e) {
			e.printStackTrace();
		}
		return;
	}

}
