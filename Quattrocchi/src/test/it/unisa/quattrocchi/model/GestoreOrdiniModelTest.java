package test.it.unisa.quattrocchi.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unisa.quattrocchi.entity.GestoreOrdini;
import it.unisa.quattrocchi.model.GestoreOrdiniModel;

class GestoreOrdiniModelTest {
	
	private static GestoreOrdiniModel gestoreOrdiniModel;
	
	static {
		gestoreOrdiniModel = new GestoreOrdiniModel();
	}
	
	@BeforeEach
	public void setUp() throws Exception{
		DatabaseHelper.initializeDatabase();
    }

	@AfterEach
    public void tearDown() throws Exception{
		DatabaseHelper.initializeDatabase();
    }

	@Test
	public void TestRicercaPerId() throws SQLException {
		GestoreOrdini vigal = gestoreOrdiniModel.doRetriveById("ViGal");
		assertNotNull(vigal);
		assertEquals(vigal.getUsername(),"ViGal");
		assertEquals(vigal.getPassword(),"Capra");
		assertEquals(vigal.getNome(),"Vincenzo");
		assertEquals(vigal.getCognome(),"Gallicchio");
		assertEquals(vigal.getEmail(),"vincenzogallicchio@yahoo.it");
		assertEquals(vigal.getMatricola(),"GO0001");
	}
	
	@Test
	public void TestCheckLogin() throws SQLException {
		GestoreOrdini vigal = gestoreOrdiniModel.doRetriveById("ViGal");
		String username = vigal.getUsername();
		String password = vigal.getPassword();
		assertNotNull(vigal);
		assertNotNull(username);
		assertNotNull(password);
		GestoreOrdini gestore  = gestoreOrdiniModel.checkLogin(username, password);
		assertNotNull(gestore);
		assertEquals(gestore.getUsername(),vigal.getUsername());
		assertEquals(gestore.getPassword(),vigal.getPassword());
		assertEquals(gestore.getNome(),vigal.getNome());
		assertEquals(gestore.getCognome(),vigal.getCognome());
		assertEquals(gestore.getEmail(),vigal.getEmail());
		assertEquals(gestore.getMatricola(),vigal.getMatricola());
	}
}
