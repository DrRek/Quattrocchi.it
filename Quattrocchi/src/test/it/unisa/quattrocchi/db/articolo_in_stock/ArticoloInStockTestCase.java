package test.it.unisa.quattrocchi.db.articolo_in_stock;

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

import it.unisa.quattrocchi.model.ArticoloInStockModel;
import test.it.unisa.quattrocchi.db.DatabaseProperty;

public class ArticoloInStockTestCase extends DBTestCase {
	 
    public ArticoloInStockTestCase(String name) {
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
    
    public void testRetrieveArticoloInStockById() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(ArticoloInStockModel.RETRIEVE_ARTICOLO_STOCK_BY_ID);
		stm.setInt(1, 999);
		
		ITable actualTable = connection.createTable("retrieve_articolo_in_stock_by_id", stm);
        
        // get the expected table values
		ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(new File("src/test/it/unisa/quattrocchi/db/articolo_in_stock/retrieve_articolo_in_stock_by_id_oracle.xml")));
        expectedDataSet.addReplacementObject("NULL", null);
        ITable expectedTable = expectedDataSet.getTable(ArticoloInStockModel.TABLE_NAME_CATALOGO);
        
        Assertion.assertEquals(expectedTable, actualTable);
    }
    
    public void testRetrieveAllArticoloInStock() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(ArticoloInStockModel.RETRIEVE_ALL_ARTICOLO_STOCK);
		
		ITable actualTable = connection.createTable("retrieve_all_articolo_in_stock", stm);
        
        // get the expected table values
		ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(new File("src/test/it/unisa/quattrocchi/db/articolo_in_stock/retrieve_all_articolo_in_stock_oracle.xml")));
        expectedDataSet.addReplacementObject("NULL", null);
        ITable expectedTable = expectedDataSet.getTable(ArticoloInStockModel.TABLE_NAME_CATALOGO);
        
        Assertion.assertEquals(expectedTable, actualTable);
    }
    
    public void testRetrieveArticoloInStockBySearch() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(ArticoloInStockModel.RETRIEVE_ARTICOLO_STOCK_BY_SEARCH);
		stm.setString(1, "%classic%");
		stm.setString(2, "%classic%");
		stm.setString(3, "%classic%");
		
		ITable actualTable = connection.createTable("retrieve_articolo_in_stock_by_search", stm);
        
        // get the expected table values
		ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(new File("src/test/it/unisa/quattrocchi/db/articolo_in_stock/retrieve_articolo_in_stock_by_search_oracle.xml")));
        expectedDataSet.addReplacementObject("NULL", null);
        ITable expectedTable = expectedDataSet.getTable(ArticoloInStockModel.TABLE_NAME_CATALOGO);
        
        Assertion.assertEquals(expectedTable, actualTable);
    }
    
    public void testRetrieveArticoloInStockByAdvancedSearch() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(ArticoloInStockModel.RETRIEVE_ARTICOLO_STOCK_BY_ADVANCED_SEARCH);
		stm.setString(1, "%classic%");
		stm.setString(2, "%classic%");
		stm.setString(3, "%classic%");
		stm.setDouble(4, 10.0);
		stm.setDouble(5, 100.0);
		stm.setString(6, "%rayban%");
		stm.setString(7, "%%");
		stm.setString(8, "%%");
		
		ITable actualTable = connection.createTable("retrieve_articolo_in_stock_by_advanced_search", stm);
        
        // get the expected table values
		ReplacementDataSet expectedDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(new File("src/test/it/unisa/quattrocchi/db/articolo_in_stock/retrieve_articolo_in_stock_by_advanced_search_oracle.xml")));
        expectedDataSet.addReplacementObject("NULL", null);
        ITable expectedTable = expectedDataSet.getTable(ArticoloInStockModel.TABLE_NAME_CATALOGO);
        
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
