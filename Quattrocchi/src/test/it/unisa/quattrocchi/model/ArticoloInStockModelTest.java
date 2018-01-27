package test.it.unisa.quattrocchi.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unisa.quattrocchi.entity.ArticoloInStock;
import it.unisa.quattrocchi.model.ArticoloInStockModel;

class ArticoloInStockModelTest {
	
	private static ArticoloInStockModel articoloInStockModel;
	
	static {
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
		ArticoloInStock articolo = articoloInStockModel.doRetrieveByIdInStock(999);
		assertNotNull(articolo);
		assertEquals(articolo.getCodice(),999);
		assertEquals(articolo.getMarca(),"GreenVision");
		assertEquals(articolo.getModello(),"Occhiali iGreen");
		assertEquals(articolo.getDisponibilità(),10);
		assertEquals(articolo.getPrezzo(),30);
	}
	
	@Test
	public void TestRicercaTutti() throws SQLException {
		List<ArticoloInStock> articoli = articoloInStockModel.doRetrieveAllInStock();
		assertNotNull(articoli);
		assertEquals(articoli.size(),articoloInStockModel.doRetrieveAllInStock().size());
		assertEquals(articoli.get(0).getCodice(),994);
		assertEquals(articoli.get(0).getModello(),"Spirit titanium");
		assertEquals(articoli.get(1).getCodice(),995);
		assertEquals(articoli.get(1).getModello(),"649 Series PO7649S Vincent Gallo");
		assertEquals(articoli.get(2).getCodice(),996);
		assertEquals(articoli.get(2).getModello(),"Flak Draft Prizm Golf sport");
	}
	
	@Test
	public void TestRicercaSemplice() throws SQLException {
		List<ArticoloInStock> articoli = articoloInStockModel.doRetrieveSimpleSearch("");
		assertNotNull(articoli);
		assertEquals(articoli.size(),articoloInStockModel.doRetrieveAllInStock().size());
		List<ArticoloInStock> ricercaSemplice = articoloInStockModel.doRetrieveSimpleSearch("sp");
		assertNotNull(ricercaSemplice);
		assertEquals(ricercaSemplice.size(),2);
		assertEquals(ricercaSemplice.get(0).getCodice(),994);
		assertEquals(ricercaSemplice.get(0).getModello(),"Spirit titanium");
		assertEquals(ricercaSemplice.get(1).getCodice(),996);
		assertEquals(ricercaSemplice.get(1).getModello(),"Flak Draft Prizm Golf sport");
	}
	
	@Test
	public void TestRicercaAvanzata() throws SQLException {
		List<ArticoloInStock> ricercaAvanzata = articoloInStockModel.doRetrieveAdvancedSearch("sp","Lindberg", 50, 60, "nero");
		assertNotNull(ricercaAvanzata);
		assertEquals(ricercaAvanzata.size(),1);
		assertEquals(ricercaAvanzata.get(0).getCodice(),994);
		assertEquals(ricercaAvanzata.get(0).getMarca(),"Lindberg");
		assertEquals(ricercaAvanzata.get(0).getPrezzo(),55);
		assertEquals(ricercaAvanzata.get(0).getDisponibilità(),60);
		assertTrue(ricercaAvanzata.get(0).getDescrizione().contains("nero"));
	}
	
	@Test
	public void TestUpdateDisponibilità() throws SQLException {
		ArticoloInStock a = articoloInStockModel.doRetrieveByIdInStock(999);
		assertNotNull(a);
		assertEquals(a.getCodice(),999);
		int dispA = a.getDisponibilità();
		a.setDisponibilità(dispA + 1);
		articoloInStockModel.updateDisponibilita(a);
		ArticoloInStock b = articoloInStockModel.doRetrieveByIdInStock(999);
		assertNotNull(b);
		assertEquals(b.getCodice(),999);
		int dispB = a.getDisponibilità();
		assertEquals(dispB,dispA +1);
	}
}
