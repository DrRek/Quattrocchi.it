package test.it.unisa.quattrocchi.model;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import it.unisa.quattrocchi.model.DriverManagerConnectionPool;

public class DatabaseHelper {
	public static void initializeDatabase() throws SQLException {

		DriverManagerConnectionPool.setTest(true);
		Connection conn = DriverManagerConnectionPool.getConnection();
		
		try {
	        IDatabaseConnection connection = new DatabaseConnection(conn);
	        FlatXmlDataSet dataSet = new FlatXmlDataSetBuilder().build(new File("test/it/unisa/quattrocchi/db/db_init.xml"));
	        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);

		} catch (Exception e) {
			System.err.println("Assicurati che il server sia spento e nessun altro stia usando il db\n"+"The error is " + e.getMessage());
		}
		
		conn.commit();
		DriverManagerConnectionPool.releaseConnection(conn);
	}
}
