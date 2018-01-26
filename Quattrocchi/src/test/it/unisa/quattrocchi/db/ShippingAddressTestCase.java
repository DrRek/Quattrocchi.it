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

import it.unisa.quattrocchi.model.CreditCardModel;
import it.unisa.quattrocchi.model.ShippingAddressModel;

public class ShippingAddressTestCase extends DBTestCase {
	 
    public ShippingAddressTestCase(String name) {
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
    
    public void testRetrieveShippingAddressById() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(ShippingAddressModel.SELECT_SHIPPING_ADDRESS_BY_ID);
		stm.setInt(1, 998);
		
		ITable actualTable = connection.createTable("retrieve_shipping_address_by_id", stm);
        
        // get the expected table values
		ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(new File("test/it/unisa/quattrocchi/db/shipping_address/retrieve_shipping_address_by_id_oracle.xml")));
        expectedDataSet.addReplacementObject("NULL", null);
        ITable expectedTable = expectedDataSet.getTable(ShippingAddressModel.TABLE_NAME_ADDRESS);
        
        Assertion.assertEquals(expectedTable, actualTable);
    }
    
    public void testRetrieveCreditCardByAcquirente() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(ShippingAddressModel.SELECT_ALL_SHIPING_ADDRESS_BY_ACQUIRENTE);
		stm.setString(1, "Expos");
		
		ITable actualTable = connection.createTable("retrieve_shipping_address_by_acquirente", stm);
        
        // get the expected table values
		ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(new File("test/it/unisa/quattrocchi/db/shipping_address/retrieve_shipping_address_by_acquirente_oracle.xml")));
        expectedDataSet.addReplacementObject("NULL", null);
        ITable expectedTable = expectedDataSet.getTable(ShippingAddressModel.TABLE_NAME_ADDRESS);
        
        Assertion.assertEquals(expectedTable, actualTable);
    }
    
    public void testInsertShippingAddress() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(ShippingAddressModel.CREATE_SHIPPING_ADDRESS);
		stm.setNull(1, java.sql.Types.INTEGER);
		stm.setString(2, "prova 1");
		stm.setString(3, "p2");
		stm.setString(4, "33333");
		stm.setString(5, "via della prova 4");
		stm.setString(6, "55");
		stm.setString(7, "Expos");
		
		stm.executeUpdate();
		
		ITable actualTable = connection.createDataSet().getTable(ShippingAddressModel.TABLE_NAME_ADDRESS);
        
        // get the expected table values
		ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(new File("test/it/unisa/quattrocchi/db/shipping_address/insert_into_shipping_address_oracle.xml")));
		expectedDataSet.addReplacementObject("NULL", null);
		ITable expectedTable = expectedDataSet.getTable(ShippingAddressModel.TABLE_NAME_ADDRESS);

		Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, new String[] {"Id"});
    }
    
    public void testUpdateShippingAddress() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(ShippingAddressModel.UPDATE_SHIPPING_ADDRESS);
		stm.setString(1, "prova 1");
		stm.setString(2, "p2");
		stm.setString(3, "33333");
		stm.setString(4, "via della prova 4");
		stm.setInt(5, 55);
		stm.setInt(6, 998);
		stm.setString(7, "Expos");
		
		stm.executeUpdate();
		
		ITable actualTable = connection.createDataSet().getTable(ShippingAddressModel.TABLE_NAME_ADDRESS);
        
        // get the expected table values
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("test/it/unisa/quattrocchi/db/shipping_address/update_shipping_address_oracle.xml"));
        ITable expectedTable = expectedDataSet.getTable(ShippingAddressModel.TABLE_NAME_ADDRESS);

        Assertion.assertEquals(expectedTable, actualTable);
    }
    
    public void testDeleteShippingAddress() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(ShippingAddressModel.DELETE_SHIPPING_ADDRESS);
		stm.setInt(1, 1000);
		
		stm.executeUpdate();
		
		ITable actualTable = connection.createDataSet().getTable(ShippingAddressModel.TABLE_NAME_ADDRESS);
        
        // get the expected table values
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("test/it/unisa/quattrocchi/db/shipping_address/delete_shipping_address_oracle.xml"));
        ITable expectedTable = expectedDataSet.getTable(ShippingAddressModel.TABLE_NAME_ADDRESS);

        Assertion.assertEquals(expectedTable, actualTable);
    }
    
    public void testShippingAddressSetAcquirenteNull() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(ShippingAddressModel.SET_SHIPPING_ADDRESS_ACQUIRENTE_NULL);
		stm.setInt(1, 998);
		
		stm.executeUpdate();
		
		ITable actualTable = connection.createDataSet().getTable(ShippingAddressModel.TABLE_NAME_ADDRESS);
        
        // get the expected table values
		ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(new File("test/it/unisa/quattrocchi/db/shipping_address/shipping_address_set_acquirente_null_oracle.xml")));
		expectedDataSet.addReplacementObject("NULL", null);
        ITable expectedTable = expectedDataSet.getTable(ShippingAddressModel.TABLE_NAME_ADDRESS);

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
