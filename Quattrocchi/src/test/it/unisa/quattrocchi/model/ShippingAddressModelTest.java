package test.it.unisa.quattrocchi.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.entity.ShippingAddress;
import it.unisa.quattrocchi.model.AcquirenteModel;
import it.unisa.quattrocchi.model.ShippingAddressModel;

class ShippingAddressModelTest {
	
	private static ShippingAddressModel shippingAddressModel;
	private static AcquirenteModel acquirenteModel;
	
	static {
		shippingAddressModel = new ShippingAddressModel();
		acquirenteModel = new AcquirenteModel();
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
		ShippingAddress sa = shippingAddressModel.doRetrieveById("998");
		assertNotNull(sa);
		assertEquals(sa.getCodice(),998);
		assertEquals(sa.getStato(),"Italia");
		assertEquals(sa.getProvincia(),"AV");
		assertEquals(sa.getCap(),80060);
		assertEquals(sa.getIndirizzo(),"via Mondragone");
		assertEquals(sa.getNC(),101);
		assertEquals(sa.getAcq().getUsername(),
				acquirenteModel.doRetriveById(sa.getAcq().getUsername()).getUsername());
	}
	
	@Test
	public void TestRicercaPerUser() throws SQLException {
		Acquirente ivan = acquirenteModel.doRetriveById("Expos");
		assertNotNull(ivan);
		String username = ivan.getUsername();
		assertEquals(username,"Expos");
		List<ShippingAddress> indirizzi = shippingAddressModel.doRetrieveByUser(username);
		assertNotNull(indirizzi);
		assertEquals(indirizzi.size(),2);
		assertEquals(indirizzi.get(0).getCodice(),998);
		assertEquals(indirizzi.get(0).getStato(),"Italia");
		assertEquals(indirizzi.get(0).getProvincia(),"AV");
		assertEquals(indirizzi.get(0).getCap(),80060);
		assertEquals(indirizzi.get(0).getIndirizzo(),"via Mondragone");
		assertEquals(indirizzi.get(0).getNC(),101);
		assertEquals(indirizzi.get(0).getAcq().getUsername(),
				acquirenteModel.doRetriveById(indirizzi.get(0).getAcq().getUsername()).getUsername());
	}
	
	@Test
	public void TestAggionraIndirizzo() throws SQLException {
		ShippingAddress toUpdate = shippingAddressModel.doRetrieveById("999");
		toUpdate.setCap(80500);
		toUpdate.setNC(60);
		toUpdate.setProvincia("SA");
		shippingAddressModel.updateShippingAddress(toUpdate);
		ShippingAddress aggiornato = shippingAddressModel.doRetrieveById("999");
		assertNotNull(aggiornato);
		assertEquals(aggiornato.getCap(),80500);
		assertEquals(aggiornato.getNC(),60);
		assertEquals(aggiornato.getProvincia(),"SA");
	}
	
	
	@Test
	public void TestCreaIndirizzo() throws SQLException {
		List<ShippingAddress> indirizziPrima = shippingAddressModel.doRetrieveAll();
		assertNotNull(indirizziPrima);
		assertEquals(indirizziPrima.size(),3);
		Acquirente antonio = acquirenteModel.doRetriveById("AntosxA");
		assertNotNull(antonio);
		assertEquals(antonio.getUsername(),"AntosxA");
		ShippingAddress indirizzo = new ShippingAddress("Italia","via Dante",80034,"NA",9,antonio);
		assertNotNull(indirizzo);
		shippingAddressModel.createShippingAddress(indirizzo);
		List<ShippingAddress> indirizziDopo = shippingAddressModel.doRetrieveAll();
		assertNotNull(indirizziDopo);
		assertEquals(indirizziDopo.size(),4);
	}
	
	
	@Test
	public void TestCancellaIndirizzo() throws SQLException {
		List<ShippingAddress> indirizziPrima = shippingAddressModel.doRetrieveAll();
		assertNotNull(indirizziPrima);
		assertEquals(indirizziPrima.size(),3);
		shippingAddressModel.deleteShippingAddress(1000);
		List<ShippingAddress> indirizziAggiornati = shippingAddressModel.doRetrieveAll();
		assertNotNull(indirizziAggiornati);
		assertEquals(indirizziAggiornati.size(),2);
	}
}
