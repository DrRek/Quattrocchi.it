package test.it.unisa.quattrocchi.db;

import java.io.File;
import java.sql.PreparedStatement;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import it.unisa.quattrocchi.model.ArticoloInOrderModel;
import it.unisa.quattrocchi.model.GestoreOrdiniModel;

public class GestoreOrdiniTestCase extends DBTestCase {
	 
    public GestoreOrdiniTestCase(String name) {
        super(name);
        System.setProperty(
          PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
          DatabaseProperty.DATABASE_DRIVER);
        System.setProperty(
          PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
          DatabaseProperty.DATABASE_URL);
        System.setProperty(
          PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
          DatabaseProperty.DATABASE_USERNAME);
        System.setProperty(
          PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
          DatabaseProperty.DATABASE_PASSWORD);
    }
    
    public void testRetrieveGestoreOrdiniByUsername() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(GestoreOrdiniModel.RETRIEVE_GESTORE_ORDINI_BY_USERNAME);
		stm.setString(1, "ViGal");
		
		ITable actualTable = connection.createTable("retrieve_gestore_ordini_by_username", stm);
        
        // get the expected table values
		ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(new File("src/test/it/unisa/quattrocchi/db/gestore_ordini/retrieve_gestore_ordini_by_username.xml")));
        expectedDataSet.addReplacementObject("NULL", null);
        ITable expectedTable = expectedDataSet.getTable(GestoreOrdiniModel.TABLE_NAME_GESTOREORDINI);
        
        Assertion.assertEquals(expectedTable, actualTable);
    }
    
    public void testRetrieveGestoreOrdiniByCredentials() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(GestoreOrdiniModel.RETRIEVE_GESTORE_ORDINI_BY_CREDENTIALS);
		stm.setString(1, "ViGal");
		stm.setString(2, "Capra");
		
		ITable actualTable = connection.createTable("retrieve_gestore_ordini_by_credentials", stm);
        
        // get the expected table values
		ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(new File("src/test/it/unisa/quattrocchi/db/gestore_ordini/retrieve_gestore_ordini_by_credentials.xml")));
        expectedDataSet.addReplacementObject("NULL", null);
        ITable expectedTable = expectedDataSet.getTable(GestoreOrdiniModel.TABLE_NAME_GESTOREORDINI);
        
        Assertion.assertEquals(expectedTable, actualTable);
    }
    
    /*
     * (non-Javadoc)
     * @see org.dbunit.DatabaseTestCase#getDataSet()
     */
    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new File("src/test/it/unisa/quattrocchi/db/db_init.xml"));
    }
}
