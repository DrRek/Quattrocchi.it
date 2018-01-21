package test.it.unisa.quattrocchi.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.entity.Order;
import it.unisa.quattrocchi.model.AcquirenteModel;
import it.unisa.quattrocchi.model.OrderModel;

class OrderModelTest {

	private static OrderModel orderModel;
	private static AcquirenteModel acquirenteModel;
	
	static {
		orderModel = new OrderModel();
		acquirenteModel = new AcquirenteModel();
	}

	@Test
	public void TestRicercaPerId() throws SQLException {
		Order ordine = orderModel.doRetrieveById(997);
		assertNotNull(ordine);
		assertEquals(ordine.getCodice(),997);
		assertEquals(ordine.getCorriere(),"Bartolini");
		assertEquals(ordine.getCreditCard().getIdCarta(),"999");
		assertEquals(ordine.getAcquirente().getUsername(),"AntosxA");
		assertEquals(ordine.getStatoOrdine(),"Consegnato");
		assertEquals(ordine.getNumeroTracking(),"TR514");
		assertEquals(ordine.getPrezzo(),30);
	}
	
	@Test
	public void TestRicercaTutti() throws SQLException {
		List<Order> ordini = orderModel.doRetrieveAll();
		assertEquals(ordini.size(),3);
		assertEquals(ordini.get(0).getAcquirente().getUsername(),"AntosxA");
		assertEquals(ordini.get(1).getAcquirente().getUsername(),"Expos");
		assertEquals(ordini.get(2).getAcquirente().getUsername(),"AntosxA");
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void TestUpdate() throws SQLException, InterruptedException {
		Order ordine = orderModel.doRetrieveById(999);
		assertNotNull(ordine);
		ordine.setDataConsegna(new Date(118,0,21));
		ordine.setNumeroTracking("TR020");
		ordine.setCorriere("GLS");
		orderModel.updateOrder(ordine);
		Order toUpdate = orderModel.doRetrieveById(999);
		assertNotNull(toUpdate);
		assertEquals(toUpdate.getCorriere(),"GLS");
		assertEquals(toUpdate.getNumeroTracking(),"TR020");
		assertEquals(toUpdate.getDataConsegna(),new Date(118,0,21));
	}
	
	@Test
	public void TestRicercaPerAcquirente() throws SQLException {
		Acquirente antonio = acquirenteModel.doRetriveById("AntosxA");
		Acquirente ivan = acquirenteModel.doRetriveById("Expos");
		assertNotNull(antonio);
		assertNotNull(ivan);
		List<Order> ordiniAntonio = orderModel.doRetrieveByAcquirente(antonio);
		List<Order> ordiniIvan = orderModel.doRetrieveByAcquirente(ivan);
		assertNotNull(ordiniAntonio);
		assertNotNull(ordiniIvan);
		assertEquals(ordiniAntonio.size(),2);
		assertEquals(ordiniIvan.size(),1);
		assertEquals(ordiniAntonio.get(0).getAcquirente().getUsername(),antonio.getUsername());
		assertEquals(ordiniAntonio.get(0).getCodice(),999);
		assertEquals(ordiniAntonio.get(1).getAcquirente().getUsername(),antonio.getUsername());
		assertEquals(ordiniAntonio.get(1).getCodice(),997);
		assertEquals(ordiniIvan.get(0).getAcquirente().getUsername(),ivan.getUsername());
		assertEquals(ordiniIvan.get(0).getCodice(),998);
	}
	
	@Test
	public void TestCreaOrdine() throws SQLException {
		//Da fare con LucaRè
	}
}
