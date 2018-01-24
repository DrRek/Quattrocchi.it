package test.it.unisa.quattrocchi.db.articolo_in_order;

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
import test.it.unisa.quattrocchi.db.DatabaseProperty;

public class ArticoloInOrderTestCase extends DBTestCase {
	 
    public ArticoloInOrderTestCase(String name) {
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
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/test/it/unisa/quattrocchi/db/acquirente/retrieve_acquirente_by_username_oracle.xml"));
        ITable expectedTable = expectedDataSet.getTable(AcquirenteModel.TABLE_NAME_ACQUIRENTE);

        Assertion.assertEquals(expectedTable, actualTable);
    }
    
    public void testCheckLoginByCredential() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(AcquirenteModel.CHECK_LOGIN_BY_CREDENTIALS);
		stm.setString(1, "Expos");
		stm.setString(2, "Informatica");
		
		ITable actualTable = connection.createTable("check_login_by_credentials", stm);
        
        // get the expected table values
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/test/it/unisa/quattrocchi/db/acquirente/check_login_by_credentials_oracle.xml"));
        ITable expectedTable = expectedDataSet.getTable(AcquirenteModel.TABLE_NAME_ACQUIRENTE);

        Assertion.assertEquals(expectedTable, actualTable);
    }
    
    public void testUpdateAcquirente() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(AcquirenteModel.UPDATE_ACQUIRENTE);
		stm.setString(1, "prova1");
		stm.setString(2, "prova2");
		stm.setString(3, "prova3");
		stm.setString(4, "prova4");
		stm.setString(5, "2020-02-20");
		stm.setString(6, "Expos");
		
		stm.executeUpdate();
		
		ITable actualTable = connection.createDataSet().getTable(AcquirenteModel.TABLE_NAME_ACQUIRENTE);
        
        // get the expected table values
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/test/it/unisa/quattrocchi/db/acquirente/update_acquirente_oracle.xml"));
        ITable expectedTable = expectedDataSet.getTable(AcquirenteModel.TABLE_NAME_ACQUIRENTE);

        Assertion.assertEquals(expectedTable, actualTable);
    }
    
    public void testRetrieveCartByUser() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(AcquirenteModel.RETRIEVE_CART_BY_USER);
		stm.setString(1, "Expos");
		
		ITable actualTable = connection.createTable("check_login_by_credentials", stm);
        
        // get the expected table values
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/test/it/unisa/quattrocchi/db/acquirente/retrieve_cart_by_user_oracle.xml"));
        ITable expectedTable = expectedDataSet.getTable(AcquirenteModel.TABLE_NAME_ARTICOLOINCARRELLO);

        Assertion.assertEquals(expectedTable, actualTable);
    }
    
    public void testInsertIntoCart() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(AcquirenteModel.INSERT_INTO_CART);
		stm.setString(1, "Expos");
		stm.setString(2, "998");
		stm.setInt(3, 10);
		
		stm.executeUpdate();
		
		ITable actualTable = connection.createDataSet().getTable(AcquirenteModel.TABLE_NAME_ARTICOLOINCARRELLO);
        
        // get the expected table values
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/test/it/unisa/quattrocchi/db/acquirente/insert_into_cart_oracle.xml"));
        ITable expectedTable = expectedDataSet.getTable(AcquirenteModel.TABLE_NAME_ARTICOLOINCARRELLO);

        Assertion.assertEquals(expectedTable, actualTable);
    }
    
    public void testDeleteCart() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(AcquirenteModel.DELETE_CART);
		stm.setString(1, "Expos");
		
		stm.executeUpdate();
		
		ITable actualTable = connection.createDataSet().getTable(AcquirenteModel.TABLE_NAME_ARTICOLOINCARRELLO);
        
        // get the expected table values
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/test/it/unisa/quattrocchi/db/acquirente/delete_cart_oracle.xml"));
        ITable expectedTable = expectedDataSet.getTable(AcquirenteModel.TABLE_NAME_ARTICOLOINCARRELLO);

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
