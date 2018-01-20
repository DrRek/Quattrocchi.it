package Test_Model;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import Bean.CartaBean;
import Bean.IndirizzoBean;
import Bean.OrdineBean;
import Bean.ProdottoOrdineBean;

public class OrdineModelTest_jdbc {

	private static OrdineModel_jdbc model;

	static {
		model = new OrdineModel_jdbc();
	}

	@Test
	public void TestDoSave() throws SQLException {
		
		
		OrdineBean ordine = new OrdineBean();
        java.util.Date utilDate = new java.util.Date();
        ordine.setData(new Date(utilDate.getTime()));
        ordine.setCorriere("");
        ordine.setNumOrdine(model.generaCodice());
        ordine.setStato("in preparazione");
        ordine.setDataConsegna(null);
        ordine.setProdotti(new ArrayList<ProdottoOrdineBean>());
        ordine.setTracking("");
        ordine.setTotale(100);
        ordine.setIndirizzo(new IndirizzoBean("Via Marigliano", "Marigliano", 80034, "Paolo", "Rossi",2, "1783764782"));
        ordine.setCarta(new CartaBean("01/17","1234567890123456","Antonio Spera",0));
        ordine.setUser("Antonio");
        model.doSave(ordine.getIndirizzo(), ordine.getCarta(), "in preparazione", ordine.getCorriere(), ordine.getTracking(), ordine.getTotale(), ordine.getUser());
        
	}
	
	@Test
	public void TestRicercaPerId() throws SQLException {
		OrdineBean ordine = model.doRetrieveByKey(0);
		assertNotNull(ordine);
		assertEquals(ordine.getNumOrdine(),0);
	}
	
	@Test 
	public void TestOrdiniUtenti() throws SQLException {
        ArrayList<OrdineBean> ordini = model.ordiniUtente("Antonio");
        assertEquals(1,ordini.size());
        assertEquals(0,ordini.get(0).getNumOrdine());
	}
	
	@Test
	public void TestAggiornaOrdine() throws SQLException {
		 java.util.Date utilDate = new java.util.Date();
		model.aggiorna(0, "1234567890", new Date(118,0,19), "GLS");
		
		OrdineBean ordine = model.doRetrieveByKey(0);
		assertEquals(ordine.getStato(),"spedito");
		assertEquals(ordine.getTracking(),"1234567890");
		assertEquals(ordine.getDataConsegna(),new Date(118,0,19));
		assertEquals(ordine.getCorriere(),"GLS");
		
	}


}
