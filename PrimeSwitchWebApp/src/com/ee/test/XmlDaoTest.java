package com.ee.test;

import org.junit.Test;
import com.ee.dao.TranHandler;
import com.ee.entity.Mfb;

public class XmlDaoTest {
	
	@Test
	public void test(){
		Mfb c = new Mfb (); 
		
		c.setId("008");
		c.setName("Tarique");
		c.setMfi_branch_id("Lag Island");
		c.setSuspense_account("000222333");
		c.setCharge_account("444555666");
		c.setWsdl_location("http://127.0.0.1:86/PrimeWebService/");
		
//		UserBean.addUser();
		
		TranHandler.save(c);
	    	
		
//		TranHandler.deletebyid("005");
	}

}
