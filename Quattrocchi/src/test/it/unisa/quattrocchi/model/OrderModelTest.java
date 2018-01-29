package test.it.unisa.quattrocchi.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.entity.Cart;
import it.unisa.quattrocchi.entity.CreditCard;
import it.unisa.quattrocchi.entity.Order;
import it.unisa.quattrocchi.entity.ShippingAddress;
import it.unisa.quattrocchi.model.AcquirenteModel;
import it.unisa.quattrocchi.model.CreditCardModel;
import it.unisa.quattrocchi.model.OrderModel;
import it.unisa.quattrocchi.model.ShippingAddressModel;

class OrderModelTest {

	private static OrderModel orderModel;
	private static AcquirenteModel acquirenteModel;
	private static ShippingAddressModel shippingAddressModel;
	private static CreditCardModel creditCardModel;
	
	static {
		orderModel = new OrderModel();
		acquirenteModel = new AcquirenteModel();
		shippingAddressModel = new ShippingAddressModel();
		creditCardModel = new CreditCardModel();
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
		Order ordine = orderModel.doRetrieveById(997);
		assertNotNull(ordine);
		assertEquals(ordine.getCodice(),997);
		assertEquals(ordine.getCorriere(),"Bartolini");
		assertEquals(ordine.getCreditCard().getIdCarta(),999);
		assertEquals(ordine.getAcquirente().getUsername(),"AntosxA");
		assertEquals(ordine.getStatoOrdine(),"Consegnato");
		assertEquals(ordine.getNumeroTracking(),"TR514");
		assertEquals(ordine.getPrezzo(),30);
	}
	
	@Test
	public void TestRicercaTutti() throws SQLException {
		List<Order> ordini = orderModel.doRetrieveAll();
		assertEquals(ordini.size(),orderModel.doRetrieveAll().size());
		assertEquals(ordini.get(0).getAcquirente().getUsername(),"AntosxA");
		assertEquals(ordini.get(1).getAcquirente().getUsername(),"Expos");
		assertEquals(ordini.get(2).getAcquirente().getUsername(),"AntosxA");
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void TestUpdate() throws SQLException {
		Order ordine = orderModel.doRetrieveById(999);
		assertNotNull(ordine);
		ordine.setDataConsegna(new Date(118,0,21));
		ordine.setNumeroTracking("TR100");
		ordine.setCorriere("SDA");
		orderModel.updateOrder(ordine);
		Order toUpdate = orderModel.doRetrieveById(999);
		assertNotNull(toUpdate);
		assertEquals(toUpdate.getCorriere(),"SDA");
		assertEquals(toUpdate.getNumeroTracking(),"TR100");
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
		Acquirente a = acquirenteModel.doRetriveById("Expos");
		assertNotNull(a);
		Cart c = acquirenteModel.doRetrieveCartByUser(a.getUsername());
		a.setCart(c);
		List<ShippingAddress> sa = shippingAddressModel.doRetrieveByUser(a.getUsername());
		assertNotNull(sa);
		List<CreditCard> cc = creditCardModel.doRetrieveByUser(a.getUsername());
		assertNotNull(cc);
		Order o = new Order(a,sa.get(0),cc.get(0));
		assertNotNull(o);
		orderModel.createOrder(o);
		List<Order> listo = orderModel.doRetrieveByAcquirente(a);
		assertNotNull(listo);
		assertTrue(listo.contains(o));
	}
}
