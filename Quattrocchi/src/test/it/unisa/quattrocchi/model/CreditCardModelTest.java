package test.it.unisa.quattrocchi.model;

import static org.junit.jupiter.api.Assertions.*;


import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.entity.CreditCard;
import it.unisa.quattrocchi.model.AcquirenteModel;
import it.unisa.quattrocchi.model.CreditCardModel;

class CreditCardModelTest {
	
	private static CreditCardModel creditCardModel;
	private static AcquirenteModel acquirenteModel;
	
	static {
		creditCardModel = new CreditCardModel();
		acquirenteModel = new AcquirenteModel();
	}

	@Test
	public void TestRicercaPerId() throws SQLException {
		CreditCard cc = creditCardModel.doRetrieveById("997");
		assertNotNull(cc);
		assertEquals(cc.getIdCarta(),"997");
		assertEquals(cc.getAcquirente().getUsername(),"AntosxA");
		assertEquals(cc.getCircuito(),"VISA");
		assertEquals(cc.getNumeroCC(),"4023654123214120");
		assertEquals(cc.getCvv(),101);
	}
	
	@Test
	public void TestRicercaPerUser() throws SQLException {
		List<CreditCard> carte = creditCardModel.doRetrieveByUser("AntosxA");
		assertNotNull(carte);
		assertEquals(carte.size(),2);
	}
	
	@Test
	public void TestCreaCarta() throws SQLException {
		Acquirente a = acquirenteModel.doRetriveById("Expos");
		assertNotNull(a);
		@SuppressWarnings("deprecation")
		CreditCard cc = new CreditCard("996","4023654123214150","Ivan Esposito",
				"VISA",new Date(118,0,21),524,a);
		assertNotNull(cc);
		List<CreditCard> carteIvanPrima = creditCardModel.doRetrieveByUser(a.getUsername());
		assertNotNull(carteIvanPrima);
		assertEquals(carteIvanPrima.size(),1);
		creditCardModel.createCreditCard(cc);
		List<CreditCard> carteIvan = creditCardModel.doRetrieveByUser(a.getUsername());
		assertNotNull(carteIvan);
		assertEquals(carteIvan.size(),2);
	}
	
	
	//problema con la data
	@Test
	public void TestAggiornaCart() throws SQLException {
		CreditCard cc = creditCardModel.doRetrieveById("999");
		assertNotNull(cc);
		cc.setNumeroCC("4023660000058965");
		cc.setIntestatario("Luigi Piccolo");
		cc.setCircuito("MasterCard");
		cc.setCvv(321);
		creditCardModel.updateCreditCard(cc);
		CreditCard ccUpdate = creditCardModel.doRetrieveById("999");
		assertNotNull(ccUpdate);
		assertEquals(ccUpdate.getIdCarta(),"999");
		assertEquals(ccUpdate.getIntestatario(),"Luigi Piccolo");
		assertEquals(ccUpdate.getNumeroCC(),"4023660000058965");
		assertEquals(ccUpdate.getCircuito(),"MasterCard");
		assertEquals(ccUpdate.getCvv(),321);
	}

}
