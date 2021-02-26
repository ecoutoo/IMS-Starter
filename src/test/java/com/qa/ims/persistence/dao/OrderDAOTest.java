package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {
	
	private final OrderDAO DAO = new OrderDAO();
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Order created = new Order(2L, 1L);
		assertEquals(created, DAO.create(created));
	}
	
	@Test
	public void testCreateOrderLine() {
		final Order created = new Order(1L, 2L, 1L, 1L);
		assertEquals(created, DAO.createOrderLine(created));
	}
	
	@Test
	public void testCalculateOrderCost() {
		final Order created = new Order(1L, 1L, 1L, 1L, 2L);
		final Order result = new Order(1L, 1L, 1L, 1L, 2L, 3.00);
		assertEquals(result, DAO.calculateOrderCost(created));
	}

	@Test
	public void testReadAll() {
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(1L, 1L, 1L, 1L, 2L));
		assertEquals(expected, DAO.readAll());
	}


	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new Order(ID, 1L), DAO.read(ID));
	}
	
	@Test
	public void testReadLatestOrderLine() {
		final long ID = 1L;
		assertEquals(new Order(ID, 1L, 1L, 2L), DAO.readLatestOrderLine());
	}
	
	@Test
	public void testUpdate() {
		final Order updated = new Order(1L, 1L);
		assertEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
	
	@Test
	public void testDeleteOrderLines() {
		assertEquals(1, DAO.deleteOrderLines(1L));
	}
	
	@Test
	public void testDeleteOneOrderLine() {
		assertEquals(1, DAO.deleteOneOrderLine(1L, 1L));
	}

}
