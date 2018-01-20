package test.it.unisa.quattrocchi.model;

import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.entity.ShippingAddress;
import it.unisa.quattrocchi.model.AcquirenteModel;

import static org.junit.Assert.*;


import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

public class ShippingAddressModelTest_jdbc {
	
	private static ShippingAddressModel_jdbc shippingModel;
	private static AcquirenteModel acqModel;
	
	static {
		shippingModel = new ShippingAddressModel_jdbc();
		acqModel = new AcquirenteModel();
	}
	
	@Test
	public void TestRicercaPerId() throws SQLException{
		ShippingAddress sa = shippingModel.doRetrieveById("998");
		assertNotNull(sa);
		assertEquals(sa.getIndirizzo(),"via Mondagone");
	}
	
	@Test
	public void TestRicercaPerUser() throws SQLException{
		List<ShippingAddress> sa = shippingModel.doRetrieveByUser("Expos");
		assertEquals(sa.size(), 1);
		assertEquals(sa.get(0).getIndirizzo(), "via Mondagone");
	}
	
	@Test
	public void TestCreaShippingAddress() throws SQLException{
		Acquirente acq = acqModel.doRetriveById("AntosxA");
		assertEquals(acq.getUsername(),"AntosxA");
		ShippingAddress toCreate = new ShippingAddress("997","Italia","via Dante",80034,"NA",9,acq);
		shippingModel.createShippingAddress(toCreate);
		assertEquals(Integer.parseInt(toCreate.getCodice()), 997);
	}
	
	@Test
	public void TestUpdateShippingAddress() throws SQLException{
		ShippingAddress sa = shippingModel.doRetrieveById("999");
		sa.setIndirizzo("via Settembrini nuova");
		sa.setProvincia("BE");
		sa.setCap();
		shippingModel.updateShippingAddress(sa);
		assertEquals(sa.getIndirizzo(),"via Settembrini nuova");
	}
	
	

}
