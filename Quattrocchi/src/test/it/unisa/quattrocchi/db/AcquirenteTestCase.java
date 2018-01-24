package test.it.unisa.quattrocchi.db;

import java.io.File;
import java.sql.PreparedStatement;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import it.unisa.quattrocchi.model.AcquirenteModel;

public class AcquirenteTestCase extends DBTestCase {
	 
    public AcquirenteTestCase(String name) {
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
    
    public void testRetrieveAcquirenteByUsername() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(AcquirenteModel.RETRIEVE_ACQUIRENTE_BY_USERNAME);
		stm.setString(1, "Expos");
		
		ITable actualTable = connection.createTable("retrieve_acquirente_by_username", stm);
        
        // get the expected table values
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/test/it/unisa/quattrocchi/db/retrieve_user_by_id_oracle.xml"));
        ITable expectedTable = expectedDataSet.getTable("Acquirente");

        Assertion.assertEquals(expectedTable, actualTable);
    }
    
    public void testCheckLoginByCredential() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(AcquirenteModel.CHECK_LOGIN_BY_CREDENTIALS);
		stm.setString(1, "Expos");
		stm.setString(2, "Informatica");
		
		ITable actualTable = connection.createTable("check_login_by_credentials", stm);
        
        // get the expected table values
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/test/it/unisa/quattrocchi/db/retrieve_user_by_id_oracle.xml"));
        ITable expectedTable = expectedDataSet.getTable("Acquirente");

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
