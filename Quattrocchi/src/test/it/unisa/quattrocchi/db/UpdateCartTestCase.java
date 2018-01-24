package test.it.unisa.quattrocchi.db;

import java.io.File;
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.model.AcquirenteModel;

public class UpdateCartTestCase extends DBTestCase {
	
	private AcquirenteModel acquirenteModel;
	 
    public UpdateCartTestCase(String name) {
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

		acquirenteModel = new AcquirenteModel();
    }
 
    public void testInsert() throws Exception {
        Acquirente acquirente = acquirenteModel.doRetriveById("Expos");
        acquirente.resetCart();
        
        acquirenteModel.updateCart(acquirente);
        
        // get the actual table values
        IDatabaseConnection connection = getConnection();
        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("ArticoloInCarrello");
 
        // get the expected table values
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/test/it/unisa/quattrocchi/db/update_cart_oracle.xml"));
        ITable expectedTable = expectedDataSet.getTable("ArticoloInCarrello");
 
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
