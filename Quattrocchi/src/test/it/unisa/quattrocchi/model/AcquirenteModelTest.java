package test.it.unisa.quattrocchi.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.entity.ArticoloInStock;
import it.unisa.quattrocchi.entity.Cart;
import it.unisa.quattrocchi.model.AcquirenteModel;
import it.unisa.quattrocchi.model.ArticoloInStockModel;

class AcquirenteModelTest {
	
	private static AcquirenteModel acquirenteModel;
	private static ArticoloInStockModel articoloInStockModel;
	
	static {
		acquirenteModel = new AcquirenteModel();
		articoloInStockModel = new ArticoloInStockModel();
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
		Acquirente a = acquirenteModel.doRetriveById("AntosxA");
		assertNotNull(a);
		assertEquals(a.getNome(),"Antonio");
		assertEquals(a.getCognome(),"Spera");
		assertEquals(a.getUsername(),"AntosxA");
		assertEquals(a.getPassword(),"Forzajuve");
	}
	
	@Test
	public void TestCheckLogin() throws SQLException {
		Acquirente a = acquirenteModel.doRetriveById("AntosxA");
		assertNotNull(a);
		String username = a.getUsername();
		String password = a.getPassword();
		Acquirente b = acquirenteModel.checkLogin(username, password);
		assertNotNull(b);
		assertEquals(b.getUsername(),a.getUsername());	
	}
	
	@Test
	public void TestCarreloPerUtente() throws SQLException {
		Acquirente a = acquirenteModel.doRetriveById("AntosxA");
		assertNotNull(a);
		String username = a.getUsername();
		Cart c = acquirenteModel.doRetrieveCartByUser(username);
		assertNotNull(c);
	}

	@Test
	public void TestAggiornaAcquirente() throws SQLException {
		Acquirente a = acquirenteModel.doRetriveById("Expos");
		assertNotNull(a);
		a.setPassword("sonoivan");
		a.setEmail("ivanesposito96@miao.it");
		acquirenteModel.updateAcquirente(a);
		Acquirente b = acquirenteModel.doRetriveById("Expos");
		assertNotNull(b);
		assertEquals(b.getPassword(),"sonoivan");
		assertEquals(b.getEmail(),"ivanesposito96@miao.it");
	}
	
	
	@Test
	public void TestAggiornaCarrello() throws SQLException {
		Acquirente a = acquirenteModel.doRetriveById("Expos");
		assertNotNull(a);
		Cart c = acquirenteModel.doRetrieveCartByUser(a.getUsername());
		assertNotNull(c);
		assertEquals(c.getArticoli().size(),1);
		ArticoloInStock ais = articoloInStockModel.doRetrieveByIdInStock(999);
		c.addArticle(ais);
		a.setCart(c);
		acquirenteModel.updateCart(a);
		Acquirente b = acquirenteModel.doRetriveById("Expos");
		assertNotNull(b);
		Cart c1 = acquirenteModel.doRetrieveCartByUser(b.getUsername());
		assertNotNull(c1);
		assertEquals(c1.getArticoli().size(),2);
		assertTrue(c1.getArticoli().containsKey(ais));
	}
	
	@Test
	public void TestCancellaCarrello() throws SQLException {
		Acquirente a = acquirenteModel.doRetriveById("AntosxA");
		assertNotNull(a);
		acquirenteModel.dropCart(a);
		assertEquals(a.getCart(),null);
	}
}
