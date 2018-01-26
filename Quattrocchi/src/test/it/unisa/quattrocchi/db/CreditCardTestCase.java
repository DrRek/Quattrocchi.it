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

import it.unisa.quattrocchi.entity.ArticoloInOrder;
import it.unisa.quattrocchi.model.AcquirenteModel;
import it.unisa.quattrocchi.model.ArticoloInOrderModel;
import it.unisa.quattrocchi.model.CreditCardModel;

public class CreditCardTestCase extends DBTestCase {
	 
    public CreditCardTestCase(String name) {
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
    
    public void testRetrieveCreditCardById() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(CreditCardModel.SELECT_CREDIT_CARD_BY_ID);
		stm.setInt(1, 999);
		
		ITable actualTable = connection.createTable("retrieve_credit_card_by_id", stm);
        
        // get the expected table values
		ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(new File("test/it/unisa/quattrocchi/db/credit_card/retrieve_credit_card_by_id_oracle.xml")));
        expectedDataSet.addReplacementObject("NULL", null);
        ITable expectedTable = expectedDataSet.getTable(CreditCardModel.TABLE_NAME_CREDITCARD);
        
        Assertion.assertEquals(expectedTable, actualTable);
    }
    
    public void testRetrieveCreditCardByAcquirente() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(CreditCardModel.SELECT_ALL_CREDIT_CARD_BY_ACQUIRENTE);
		stm.setString(1, "AntosxA");
		
		ITable actualTable = connection.createTable("retrieve_credit_card_by_acquirente", stm);
        
        // get the expected table values
		ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(new File("test/it/unisa/quattrocchi/db/credit_card/retrieve_credit_card_by_acquirente_oracle.xml")));
        expectedDataSet.addReplacementObject("NULL", null);
        ITable expectedTable = expectedDataSet.getTable(CreditCardModel.TABLE_NAME_CREDITCARD);
        
        Assertion.assertEquals(expectedTable, actualTable);
    }
    
    public void testInsertCreditCard() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(CreditCardModel.INSET_CREDIT_CARD);
		stm.setInt(1, 12345);
		stm.setString(2, "prova 1");
		stm.setString(3, "prova 2");
		stm.setString(4, "2020-02-20");
		stm.setString(5, "123");
		stm.setString(6, "Expos");
		
		stm.executeUpdate();
		
		ITable actualTable = connection.createDataSet().getTable(CreditCardModel.TABLE_NAME_CREDITCARD);
        
        // get the expected table values
		ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(new File("test/it/unisa/quattrocchi/db/credit_card/insert_into_credit_card_oracle.xml")));
		expectedDataSet.addReplacementObject("NULL", null);
		ITable expectedTable = expectedDataSet.getTable(CreditCardModel.TABLE_NAME_CREDITCARD);

		Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, new String[] {"IdCarta"});
    }
    
    public void testUpdateCreditCard() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(CreditCardModel.UPDATE_CREDIT_CARD);
		stm.setInt(1, 12345);
		stm.setString(2, "prova 1");
		stm.setString(3, "prova 2");
		stm.setString(4, "2020-02-20");
		stm.setInt(5, 123);
		stm.setInt(6, 999);
		stm.setString(7, "AntosxA");
		
		stm.executeUpdate();
		
		ITable actualTable = connection.createDataSet().getTable(CreditCardModel.TABLE_NAME_CREDITCARD);
        
        // get the expected table values
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("test/it/unisa/quattrocchi/db/credit_card/update_credit_card_oracle.xml"));
        ITable expectedTable = expectedDataSet.getTable(CreditCardModel.TABLE_NAME_CREDITCARD);

        Assertion.assertEquals(expectedTable, actualTable);
    }
    
    public void testDeleteCreditCard() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(CreditCardModel.DELETE_CREDIT_CARD);
		stm.setInt(1, 997);
		
		stm.executeUpdate();
		
		ITable actualTable = connection.createDataSet().getTable(CreditCardModel.TABLE_NAME_CREDITCARD);
        
        // get the expected table values
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("test/it/unisa/quattrocchi/db/credit_card/delete_credit_card_oracle.xml"));
        ITable expectedTable = expectedDataSet.getTable(CreditCardModel.TABLE_NAME_CREDITCARD);

        Assertion.assertEquals(expectedTable, actualTable);
    }
    
    public void testCreditCardSetAcquirenteNull() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(CreditCardModel.SET_CREDIT_CART_ACQUIRENTE_NULL);
		stm.setInt(1, 999);
		
		stm.executeUpdate();
		
		ITable actualTable = connection.createDataSet().getTable(CreditCardModel.TABLE_NAME_CREDITCARD);
        
        // get the expected table values
		ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(new File("test/it/unisa/quattrocchi/db/credit_card/credit_card_set_acquirente_null_oracle.xml")));
		expectedDataSet.addReplacementObject("NULL", null);
        ITable expectedTable = expectedDataSet.getTable(CreditCardModel.TABLE_NAME_CREDITCARD);

        Assertion.assertEquals(expectedTable, actualTable);
    }
    
    
    
    /*
     * (non-Javadoc)
     * @see org.dbunit.DatabaseTestCase#getDataSet()
     */
    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new File("test/it/unisa/quattrocchi/db/db_init.xml"));
    }
}
