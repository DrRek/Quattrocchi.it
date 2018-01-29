package test.it.unisa.quattrocchi.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unisa.quattrocchi.entity.ArticoloInOrder;
import it.unisa.quattrocchi.entity.ArticoloInStock;
import it.unisa.quattrocchi.entity.Order;
import it.unisa.quattrocchi.model.ArticoloInOrderModel;
import it.unisa.quattrocchi.model.ArticoloInStockModel;
import it.unisa.quattrocchi.model.OrderModel;

class ArticoloInOrderModelTest {
	
	private static ArticoloInOrderModel articoloInOrderModel;
	private static ArticoloInStockModel articoloInStockModel;
	private static OrderModel orderModel;
	
	static {
		articoloInOrderModel = new ArticoloInOrderModel();
		orderModel = new OrderModel();
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
		ArticoloInOrder articolo = articoloInOrderModel.doRetrieveByIdInOrder("999");
		assertNotNull(articolo);
		assertEquals(articolo.getCodice(),999);
		assertEquals(articolo.getMarca(),"GreenVision");
		assertEquals(articolo.getModello(),"Occhiali iGreen");
		assertEquals(articolo.getQuantit�(),1);
	}
	
	@Test
	public void TestRicercaTutti() throws SQLException {
		List<ArticoloInOrder> articoli = articoloInOrderModel.doRetrieveAllInOrder();
		assertNotNull(articoli);
		assertEquals(articoli.size(),4);
		assertEquals(articoli.get(0).getCodice(),996);
		assertEquals(articoli.get(0).getModello(),"Flak Draft Prizm Golf sport");
		assertEquals(articoli.get(1).getCodice(),997);
		assertEquals(articoli.get(1).getModello(),"Wayfarer Folding Classic");
	}
	
	@Test
	public void TestRicercaArticoliPerOrdine() throws SQLException {
		List<ArticoloInOrder> articoli = articoloInOrderModel.restituisciArticoliAssociatiAdUnOrdine(999);
		assertNotNull(articoli);
		assertEquals(articoli.size(),2);
		List<ArticoloInOrder> articoli1 = articoloInOrderModel.restituisciArticoliAssociatiAdUnOrdine(998);
		assertNotNull(articoli1);
		assertEquals(articoli1.size(),1);
	}

	
	@Test
	public void TestAggiungiArticolo() throws SQLException {
		Order o = orderModel.doRetrieveById(999);
		assertNotNull(o);
		ArticoloInStock ais = articoloInStockModel.doRetrieveByIdInStock(994);
		assertNotNull(ais);
		ArticoloInOrder aio = new ArticoloInOrder(ais,1);
		assertNotNull(aio);
		articoloInOrderModel.addArticle(aio, o);
		Order o1 = orderModel.doRetrieveById(999);
		assertNotNull(o1);
		List<ArticoloInOrder> laio = articoloInOrderModel.restituisciArticoliAssociatiAdUnOrdine(o1.getCodice());
		assertNotNull(laio);
		assertTrue(laio.contains(aio));
		
	}
}
