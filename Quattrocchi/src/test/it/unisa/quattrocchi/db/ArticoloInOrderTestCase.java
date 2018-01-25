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
    
    public void testRetrieveArticoloInOrderById() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(ArticoloInOrderModel.RETRIEVE_BY_ID);
		stm.setInt(1, 996);
		
		ITable actualTable = connection.createTable("retrieve_articolo_in_order_by_id", stm);
        
        // get the expected table values
		ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(new File("src/test/it/unisa/quattrocchi/db/articolo_in_order/retrieve_articolo_in_ordine_by_id_oracle.xml")));
        expectedDataSet.addReplacementObject("NULL", null);
        ITable expectedTable = expectedDataSet.getTable(ArticoloInOrderModel.TABLE_NAME_ARTICOLOINORDINE);
        
        Assertion.assertEquals(expectedTable, actualTable);
    }
    
    public void testRetrieveAllArticoloInOrder() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(ArticoloInOrderModel.RETRIEVE_ALL);
		
		ITable actualTable = connection.createTable("retrieve_all_articolo_in_order", stm);
        
        // get the expected table values
		ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(new File("src/test/it/unisa/quattrocchi/db/articolo_in_order/retrieve_all_articolo_in_ordine_oracle.xml")));
        expectedDataSet.addReplacementObject("NULL", null);
        ITable expectedTable = expectedDataSet.getTable(ArticoloInOrderModel.TABLE_NAME_ARTICOLOINORDINE);
        
        Assertion.assertEquals(expectedTable, actualTable);
    }
    
    public void testRetrieveArticoloInOrderByOrder() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(ArticoloInOrderModel.RETRIEVE_BY_ORDER);
		stm.setInt(1, 999);
		
		ITable actualTable = connection.createTable("retrieve_articolo_in_order_by_order", stm);
        
        // get the expected table values
		ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(new File("src/test/it/unisa/quattrocchi/db/articolo_in_order/retrieve_articolo_in_ordine_by_order_oracle.xml")));
        expectedDataSet.addReplacementObject("NULL", null);
        ITable expectedTable = expectedDataSet.getTable(ArticoloInOrderModel.TABLE_NAME_ARTICOLOINORDINE);
        
        Assertion.assertEquals(expectedTable, actualTable);
    }
    
    public void testInsertArticleByOrder() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(ArticoloInOrderModel.INSERT_ARTICLE_BY_ORDER);
		stm.setInt(1, 1);
		stm.setString(2, "prova 1");
		stm.setString(3, "prova 2");
		stm.setString(4, "prova 3");
		stm.setString(5, "prova 4");
		stm.setString(6, "prova 5");
		stm.setDouble(7, 6.78);
		stm.setInt(8, 9);
		stm.setInt(9, 999);
		
		stm.executeUpdate();
		
		ITable actualTable = connection.createDataSet().getTable(ArticoloInOrderModel.TABLE_NAME_ARTICOLOINORDINE);
        
        // get the expected table values
		ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(new File("src/test/it/unisa/quattrocchi/db/articolo_in_order/insert_into_articolo_in_ordine_oracle.xml")));
		expectedDataSet.addReplacementObject("NULL", null);
		ITable expectedTable = expectedDataSet.getTable(ArticoloInOrderModel.TABLE_NAME_ARTICOLOINORDINE);

		Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, new String[] {"Codice"});
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
