package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderTest {

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Order.class).verify();
	}

	
	@Test
	public void testConstructorThree() {
		final Long QUANTITY = 5L;
		Order order = new Order(1L, 2L, 5L);
		
		assertEquals(QUANTITY, order.getQuantity());
	}
	
	@Test
	public void testConstructorFour() {
		final Long QUANTITY = 5L;
		Order order = new Order(1L, 1L, 2L, 5L);
		
		assertEquals(QUANTITY, order.getQuantity());
	}
	
	@Test
	public void testConstructorFive() {
		final Long QUANTITY = 5L;
		Order order = new Order(1L, 1L, 1L, 2L, 5L);
		
		assertEquals(QUANTITY, order.getQuantity());
	}
	
	@Test
	public void testGetters() {
		final Long LINE_ID = 3L, ITEM_ID = 4L;
		final Double COST = 20.00;
		final String OUTPUT = "order id:1 customer id:2 orderline id:3 item id:4 quantity:5 order cost:20.0";
		Order order = new Order(1L, 2L, 3L, 4L, 5L, 20.00);
		
		assertEquals(COST, order.getOrdCost());
		assertEquals(ITEM_ID, order.getItemId());
		assertEquals(LINE_ID, order.getLineId());
		assertEquals(OUTPUT, order.toString());
	}
	
}
