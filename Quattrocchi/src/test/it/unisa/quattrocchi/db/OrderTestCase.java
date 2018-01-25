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

import it.unisa.quattrocchi.model.AcquirenteModel;
import it.unisa.quattrocchi.model.OrderModel;

public class OrderTestCase extends DBTestCase {
	 
    public OrderTestCase(String name) {
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
    
    public void testRetrieveOrderById() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(OrderModel.RETRIEVE_ORDER_BY_ID);
		stm.setInt(1, 997);
		
		ITable actualTable = connection.createTable("retrieve_order_by_id", stm);
        
        // get the expected table values
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/test/it/unisa/quattrocchi/db/order/retrieve_order_by_id_oracle.xml"));
        ITable expectedTable = expectedDataSet.getTable(OrderModel.TABLE_NAME_ORDER);

        Assertion.assertEquals(expectedTable, actualTable);
    }
    
    public void testRetrieveAllOrder() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(OrderModel.RETRIEVE_ALL_ORDER);
		
		ITable actualTable = connection.createTable("retrieve_all_order", stm);
        
        // get the expected table values
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/test/it/unisa/quattrocchi/db/order/retrieve_all_order.xml"));
        ITable expectedTable = expectedDataSet.getTable(OrderModel.TABLE_NAME_ORDER);

        Assertion.assertEquals(expectedTable, actualTable);
    }

    public void testRetrieveOrderByAcquirente() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(OrderModel.RETRIEVE_ORDER_BY_ACQUIRENTE);
		stm.setString(1, "AntosxA");
		
		ITable actualTable = connection.createTable("retrieve_order_by_acquirente", stm);
        
        // get the expected table values
		ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(new File("src/test/it/unisa/quattrocchi/db/order/retrieve_order_by_acquirente.xml")));
        expectedDataSet.addReplacementObject("NULL", null);
        ITable expectedTable = expectedDataSet.getTable(OrderModel.TABLE_NAME_ORDER);

        Assertion.assertEquals(expectedTable, actualTable);
    }
    
    public void testInsertOrder() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(OrderModel.INSERT_ORDER);
		stm.setString(1, "2020-02-20");
		stm.setDouble(2, 123.45);
		stm.setInt(3, 998);
		stm.setInt(4, 998);
		stm.setString(5, "Expos");
		stm.setString(6, "Da spedire");
		stm.setNull(7, java.sql.Types.DATE);
		stm.setNull(8, java.sql.Types.VARCHAR);
		stm.setNull(9, java.sql.Types.VARCHAR);
		
		stm.executeUpdate();
		
		ITable actualTable = connection.createDataSet().getTable(OrderModel.TABLE_NAME_ORDER);
        
        // get the expected table values
		ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(new File("src/test/it/unisa/quattrocchi/db/order/insert_order_oracle.xml")));
        expectedDataSet.addReplacementObject("NULL", null);
        ITable expectedTable = expectedDataSet.getTable(OrderModel.TABLE_NAME_ORDER);

        Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, new String[] {"Codice"});
    }
    
    public void testUpdateOrder() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(OrderModel.UPDATE_ORDER);
		stm.setString(1, "In corso");
		stm.setString(2, "2020-02-20");
		stm.setString(3, "123456");
		stm.setString(4, "corriere corridore");
		stm.setInt(5, 999);
		
		stm.executeUpdate();
		
		ITable actualTable = connection.createDataSet().getTable(OrderModel.TABLE_NAME_ORDER);
        
        // get the expected table values
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/test/it/unisa/quattrocchi/db/order/update_order_oracle.xml"));
        ITable expectedTable = expectedDataSet.getTable(OrderModel.TABLE_NAME_ORDER);

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
