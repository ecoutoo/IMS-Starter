package com.qa.ims.persistence.domain;

public class Order {
	
	private Long custId;
	private Long ordId;
	private Long lineId;
	private Long itemId;
	private Long quantity;
	private Double ordCost;
	
	public Order(Long custId) {
		this.setCustId(custId);
	}
	
	public Order (Long ordId, Long custId) {
		this.setOrdId(ordId);
		this.setCustId(custId);
	}
	
	public Order (Long custId, Long itemId, Long quantity) {
		this.setItemId(itemId);
		this.setCustId(custId);
		this.setQuantity(quantity);
	}
	
	
	public Order(Long ordId, Long lineId, Long itemId, Long quantity) {
		this.setOrdId(ordId);
		this.setLineId(lineId);
		this.setItemId(itemId);
		this.setQuantity(quantity);
	}
	
	public Order(Long ordId, Long custId, Long lineId, Long itemId, Long quantity) {
		this.setOrdId(ordId);
		this.setCustId(custId);
		this.setLineId(lineId);
		this.setItemId(itemId);
		this.setQuantity(quantity);
	}
	
	public Order(Long ordId, Long custId, Long lineId, Long itemId, Long quantity, Double ordCost) {
		this.setOrdId(ordId);
		this.setCustId(custId);
		this.setLineId(lineId);
		this.setItemId(itemId);
		this.setQuantity(quantity);
		this.setOrdCost(ordCost);
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}
	
	public Long getOrdId() {
		return ordId;
	}

	public void setOrdId(Long ordId) {
		this.ordId = ordId;
	}
	
	public Long getLineId() {
		return lineId;
	}

	public void setLineId(Long lineId) {
		this.lineId = lineId;
	}
	
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	public Double getOrdCost() {
		return ordCost;
	}

	public void setOrdCost(Double ordCost) {
		this.ordCost = ordCost;
	}

	@Override
	public String toString() {
		return "order id:" + ordId + " customer id:" + custId + " orderline id:" + lineId + " item id:" + itemId + " quantity:" + quantity + " order cost:" + ordCost;
	}

	public String toStringOrder() {
		return "order id:" + ordId + " customer id:" + custId + " cost:" + ordCost;
	}
	
	public String toStringOrderLine() {
		return "orderline:" + lineId + " item id:" + itemId + " quantity:" + quantity;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((custId == null) ? 0 : custId.hashCode());
		result = prime * result + ((ordId == null) ? 0 : ordId.hashCode());
		result = prime * result + ((lineId == null) ? 0 : lineId.hashCode());
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((ordCost == null) ? 0 : ordCost.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (custId == null) {
			if (other.custId != null)
				return false;
		} else if (!custId.equals(other.custId))
			return false;
		if (ordId == null) {
			if (other.ordId != null)
				return false;
		} else if (!ordId.equals(other.ordId))
			return false;
		if (lineId == null) {
			if (other.lineId != null)
				return false;
		} else if (!lineId.equals(other.lineId))
			return false;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (ordCost == null) {
			if (other.ordCost != null)
				return false;
		} else if (!ordCost.equals(other.ordCost))
			return false;
		return true;
	}

}
