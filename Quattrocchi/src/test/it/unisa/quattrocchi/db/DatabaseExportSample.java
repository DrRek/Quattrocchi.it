package test.it.unisa.quattrocchi.db;

import java.io.FileOutputStream;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

import com.mysql.jdbc.Connection;

public class DatabaseExportSample
{
    public static void main(String[] args) throws Exception
    {
        // database connection
        Class driverClass = Class.forName("com.mysql.jdbc.Driver");
        Connection jdbcConnection = (Connection) DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/quattrocchidb", "progetto", "pw");
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

        // full database export
        IDataSet fullDataSet = connection.createDataSet();
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("full.xml"));      
        
    }
}