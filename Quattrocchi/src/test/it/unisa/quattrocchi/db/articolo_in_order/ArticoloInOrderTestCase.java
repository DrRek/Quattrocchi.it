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

import it.unisa.quattrocchi.entity.ArticoloInOrder;
import it.unisa.quattrocchi.model.AcquirenteModel;
import it.unisa.quattrocchi.model.ArticoloInOrderModel;
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
    
    public void testRetrieveArticoloInOrderById() throws Exception{
        IDatabaseConnection connection = getConnection();
  
		PreparedStatement stm = connection.getConnection().prepareStatement(ArticoloInOrderModel.RETRIEVE_BY_ID);
		stm.setInt(1, 996);
		
		ITable actualTable = connection.createTable("retrieve_articolo_in_order_by_id", stm);
        
        // get the expected table values
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/test/it/unisa/quattrocchi/db/articolo_in_order/retrieve_articolo_in_ordine_by_id_oracle.xml"));
        ITable expectedTable = expectedDataSet.getTable(ArticoloInOrderModel.TABLE_NAME_ARTICOLOINORDINE);

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
