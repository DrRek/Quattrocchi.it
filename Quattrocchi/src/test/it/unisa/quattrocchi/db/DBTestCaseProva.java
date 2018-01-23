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

public class DBTestCaseProva extends DBTestCase {
	
	private AcquirenteModel acquirenteModel;
	 
    public DBTestCaseProva(String name) {
        super(name);
        System.setProperty(
          PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
          "com.mysql.jdbc.Driver");
        System.setProperty(
          PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
          "jdbc:mysql://127.0.0.1:3306/quattrocchidb");
        System.setProperty(
          PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
          "progetto");
        System.setProperty(
          PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
          "pw");

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
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("last_cart.xml"));
        ITable expectedTable = expectedDataSet.getTable("ArticoloInCarrello");
 
        Assertion.assertEquals(expectedTable, actualTable);
 
    }
    /*
     * (non-Javadoc)
     * @see org.dbunit.DatabaseTestCase#getDataSet()
     */
    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new File("src/test/it/unisa/quattrocchi/db/init-cart.xml"));
    }
}
