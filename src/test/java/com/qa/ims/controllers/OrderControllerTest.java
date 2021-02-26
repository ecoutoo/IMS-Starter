package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	
	@Mock
	private Utils utils;

	@Mock
	private OrderDAO dao;

	@InjectMocks
	private OrderController controller;

	@Test 
	public void testCreate() {
		final Long ORD_ID = 1L, CUST_ID = 1L, ITEM_ID = 1L, QUANTITY = 10L;
		final String ANSWER1 = "YES", ANSWER2 = "RETURN";
		final Order created1 = new Order(CUST_ID);
		final Order created2 = new Order(ORD_ID, CUST_ID);
		final Order created3 = new Order(ORD_ID, CUST_ID);
		created3.setItemId(ITEM_ID);
		created3.setQuantity(QUANTITY);

		Mockito.when(utils.getLong()).thenReturn(CUST_ID, ITEM_ID, QUANTITY, ITEM_ID, QUANTITY);
		Mockito.when(utils.getString()).thenReturn(ANSWER1, ANSWER2);
		Mockito.when(dao.create(created1)).thenReturn(created2);

		assertEquals(created3, controller.create());

		Mockito.verify(utils, Mockito.times(5)).getLong();
		Mockito.verify(utils, Mockito.times(2)).getString();
		Mockito.verify(dao, Mockito.times(1)).create(created1);
		Mockito.verify(dao, Mockito.times(2)).createOrderLine(created2);
	}

	@Test
	public void testReadAll() {
		List<Order> Orders = new ArrayList<>();
		Orders.add(new Order(1L, 2L, 5L, 1L, 10L, 30.50));
		Order read1 = new Order(1L, 2L, 5L, 1L, 10L, 30.50);

		Mockito.when(dao.readAll()).thenReturn(Orders);
		Mockito.when(dao.calculateOrderCost(read1)).thenReturn(read1);

		assertEquals(Orders, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}
	
	@Test
	public void testUpdateReturn() {
		final String ANSWER = "RETURN";
		
		Order updated = new Order(1L, 2L);

		Mockito.when(this.utils.getLong()).thenReturn(updated.getOrdId(),updated.getCustId());
		Mockito.when(this.utils.getString()).thenReturn(ANSWER);
		Mockito.when(this.dao.update(updated)).thenReturn(updated);

		assertEquals(updated, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(2)).getLong();
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
	}
	
	@Test
	public void testUpdateAdd() {
		final Long ITEM_ID = 1L, QUANTITY = 10L;
		final String ANSWER1 = "ADD", ANSWER2 = "RETURN";
		
		Order updated1 = new Order(1L, 2L);
		Order updated2 = new Order(1L, 2L);
		updated2.setItemId(ITEM_ID);
		updated2.setQuantity(QUANTITY);

		Mockito.when(this.utils.getLong()).thenReturn(updated1.getOrdId(),updated1.getCustId(), ITEM_ID, QUANTITY);
		Mockito.when(this.utils.getString()).thenReturn(ANSWER1, ANSWER2);
		Mockito.when(this.dao.update(updated1)).thenReturn(updated1);

		assertEquals(updated1, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(4)).getLong();
		Mockito.verify(utils, Mockito.times(2)).getString();
		Mockito.verify(this.dao, Mockito.times(0)).update(updated1);
		//Mockito.verify(this.dao, Mockito.times(1)).createOrderLine(updated2);
	}
	
	@Test
	public void testUpdateDelete() {
		final Long ITEM_ID = 1L;
		final String ANSWER1 = "DELETE", ANSWER2 = "RETURN";
		
		Order updated1 = new Order(1L, 2L);

		Mockito.when(this.utils.getLong()).thenReturn(updated1.getOrdId(),updated1.getCustId(), ITEM_ID);
		Mockito.when(this.utils.getString()).thenReturn(ANSWER1, ANSWER2);
		Mockito.when(this.dao.update(updated1)).thenReturn(updated1);

		assertEquals(updated1, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(3)).getLong();
		Mockito.verify(utils, Mockito.times(2)).getString();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated1);
	}

	@Test
	public void testDelete() {
		final long ID = 1L;

		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(dao.delete(ID)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(ID);
	}

}
