package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order>{

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long ordId = resultSet.getLong("ord_id");
		Long custId = resultSet.getLong("cust_id");
		Long lineId = resultSet.getLong("line_id");
		Long itemId = resultSet.getLong("item_id");
		Long quantity = resultSet.getLong("quantity");
		return new Order(ordId, custId, lineId, itemId, quantity);
	}
	
	public Order modelCalculateOrderCost(ResultSet resultSet, Order order) throws SQLException {
		Long ordId = order.getOrdId();
		Long custId = order.getCustId();
		Long lineId = order.getLineId();
		Long itemId = order.getItemId();
		Long quantity =	order.getQuantity();
		//LOGGER.info(resultSet.getString(0)); //Cost table not found?
		Long ordCost = resultSet.getLong("cost");
		return new Order(ordId, custId, lineId, itemId, quantity, ordCost);
	}
	
	public Order modelFromOrderResultSet(ResultSet resultSet) throws SQLException {
		Long ordId = resultSet.getLong("ord_id");
		Long custId = resultSet.getLong("cust_id");
		return new Order(ordId, custId);
	}
	
	public Order modelFromOrderLineResultSet(ResultSet resultSet) throws SQLException {
		Long ordId = resultSet.getLong("ord_id");
		Long lineId = resultSet.getLong("line_id");
		Long itemId = resultSet.getLong("item_id");
		Long quantity = resultSet.getLong("quantity");
		return new Order(ordId, lineId, itemId, quantity);
	}	

	/**
	 * Reads all Orders from the database
	 * 
	 * @return A list of Orders
	 */
	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT DISTINCT orderlines.line_id, orderlines.ord_id, orders.cust_id, orderlines.item_id, orderlines.quantity FROM orders, orderlines WHERE orderlines.ord_id = orders.ord_id ORDER BY orderlines.ord_id");) {
			List<Order> Orders = new ArrayList<>();
			while (resultSet.next()) {
				//Orders.add(modelCalculateOrderCost(resultSet, calculateOrderCost(modelFromResultSet(resultSet))));
				Orders.add(modelFromResultSet(resultSet));
			}
			return Orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY ord_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public Order readLatestOrder() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY ord_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromOrderResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public Order readLatestOrderLine() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orderlines ORDER BY line_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromOrderLineResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public Order calculateOrderCost(Order order) { //SQL to calculate the cost of a whole order
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT SUM(items.item_price*orderlines.quantity) AS cost FROM items,orderlines WHERE (items.item_id=orderlines.item_id AND orderlines.ord_id = ?)");) {
			statement.setLong(1, order.getOrdId());
			//LOGGER.info("TEST: " + order.getOrdId());
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelCalculateOrderCost(resultSet, order);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	//Creates a order line in the database
	
	public Order createOrderLine(Order Order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orderlines(ord_id, item_id, quantity) VALUES (?, ?, ?)");) {
			statement.setLong(1, Order.getOrdId());
			statement.setLong(2, Order.getItemId());
			statement.setLong(3, Order.getQuantity());
			statement.executeUpdate();
			return readLatestOrderLine();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a order in the database
	 * 
	 * @param Order - takes in a Order object. id will be ignored
	 */
	@Override
	public Order create(Order Order) {
		try (Connection connection = DBUtils.getInstance().getConnection();			
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO orders(cust_id) VALUES (?)");) {
			statement.setLong(1, Order.getCustId());
			statement.executeUpdate();
			return readLatestOrder();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE ord_id = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromOrderResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates a Order in the database
	 * 
	 * @param Order - takes in a Order object, the id field will be used to
	 *                 update that Order in the database
	 * @return
	 */
	@Override
	public Order update(Order Order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE orders SET cust_id = ? WHERE ord_id = ?");) {
			statement.setLong(1, Order.getCustId());
			statement.setLong(2, Order.getOrdId());
			statement.executeUpdate();
			return read(Order.getOrdId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes a order in the database
	 * 
	 * @param id - id of the Order
	 */
	@Override
	public int delete(long id) { 
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE ord_id = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
	public int deleteOrderLines(Long id) { //This method deletes the order lines
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orderlines WHERE ord_id = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
	public int deleteOneOrderLine(Long itemId, Long ordId) { //This method deletes a single order line
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orderlines WHERE item_id = ? AND ord_id = ?");) {
			statement.setLong(1, itemId);
			statement.setLong(2, ordId);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
}
