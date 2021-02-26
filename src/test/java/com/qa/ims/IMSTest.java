package com.qa.ims;

//import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.CustomerController;
import com.qa.ims.controller.ItemController;
import com.qa.ims.controller.OrderController;
//import com.qa.ims.persistence.domain.Domain;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class IMSTest {
	
	@Mock
	private Utils utils;

	@Mock
	private CustomerController customers;
	
	@Mock
	private ItemController items;
	
	@Mock
	private OrderController orders;
	
	@InjectMocks
	private IMS ims;
	
	@Test
	public void testIMSSystems() {
		
		//final String ANSWER1 = "YES", ANSWER2 = "STOP";
		//final Domain 
		
		//Mockito.when(utils.getString()).thenReturn(ANSWER1);
		//Mockito.when(Domain.getDomain(utils)).thenReturn(ANSWER2);
		
		//assertEquals(1, ims.imsSystem());
		
		//Mockito.verify(utils, Mockito.times(2)).getString();
		
	}

}
