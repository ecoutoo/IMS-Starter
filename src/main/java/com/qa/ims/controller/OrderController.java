package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order>{

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO OrderDAO;
	private Utils utils;

	public OrderController(OrderDAO OrderDAO, Utils utils) {
		super();
		this.OrderDAO = OrderDAO;
		this.utils = utils;
	}

	/**
	 * Reads all Orders to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> Orders = OrderDAO.readAll();
		Long OrdNum = 0L;
		for (Order Order : Orders) {					
			//LOGGER.info(Order);
			if (Order.getOrdId() != OrdNum) {
				Order = OrderDAO.calculateOrderCost(Order);
				OrdNum = Order.getOrdId();
				LOGGER.info("--------------------------------------------------");
				LOGGER.info(Order.toStringOrder());
			}
			LOGGER.info(Order.toStringOrderLine());
		}
		return Orders;
	}

	/**
	 * Creates a Order by taking in user input
	 */
	@Override
	public Order create() {
		String answer = null;
		LOGGER.info("Please enter a customer id");
		Long custId = utils.getLong();
		Order Order = OrderDAO.create(new Order(custId)); //Creates the order also returns order object with ordId
		do { //Loop for each item added to the order
			LOGGER.info("Please enter a item id");
			Long itemId = utils.getLong();
			Order.setItemId(itemId);
			LOGGER.info("Please enter the item quantity");
			Long quantity = utils.getLong();
			Order.setQuantity(quantity);
			OrderDAO.createOrderLine(Order); //Creates the order line
			LOGGER.info("Order item added");
			LOGGER.info("Would you like to add more items to the order?");
			LOGGER.info("YES");
			LOGGER.info("RETURN");
			answer = utils.getString();
		} while (!answer.toUpperCase().equals("RETURN"));
		LOGGER.info("Order completed");
		return Order;
	}

	/**
	 * Updates an existing Order by taking in user input
	 */
	@Override
	public Order update() {
		String answer = null;
		LOGGER.info("Please enter the id of the order you would like to update");
		Long ordId = utils.getLong();
		LOGGER.info("Please set a customer id");
		Long custId = utils.getLong();
		Order Order = OrderDAO.update(new Order(ordId, custId)); //Updates the order also returns order object with ordId
		do {
			LOGGER.info("What would you like to update in this order?");
			LOGGER.info("ADD (Add Item)");
			LOGGER.info("DELETE (Delete Item)");
			LOGGER.info("RETURN");	
			answer = utils.getString();
			if (answer.toUpperCase().equals("ADD")) {
				LOGGER.info("Please enter a item id");
				Long itemId = utils.getLong();
				Order.setItemId(itemId);
				LOGGER.info("Please enter the item quantity");
				Long quantity = utils.getLong();
				Order.setQuantity(quantity);
				OrderDAO.createOrderLine(Order); //Creates the order line
				LOGGER.info("Order item added");
			}
			else if (answer.toUpperCase().equals("DELETE")) {
				LOGGER.info("Please enter a item id to delete");
				Long itemId = utils.getLong();
				OrderDAO.deleteOneOrderLine(itemId, ordId);
				LOGGER.info("Order line deleted");
			}
		} while (!answer.toUpperCase().equals("RETURN"));
		LOGGER.info("Order updated");
		return Order;
	}

	/**
	 * Deletes an existing Order by the id of the Order
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = utils.getLong();
		OrderDAO.deleteOrderLines(id);
		return OrderDAO.delete(id);
	}

	
}
