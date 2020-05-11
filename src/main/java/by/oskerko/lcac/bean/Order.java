package by.oskerko.lcac.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int orderId;
	private int clientId;
	private Timestamp orderTime;
	
	public Order() {}
	
	public Order(int orderId, int clientId, Timestamp orderTime) {
		this.orderId = orderId;
		this.clientId = clientId;
		this.orderTime = orderTime;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + clientId;
		result = prime * result + orderId;
		result = prime * result + ((orderTime == null) ? 0 : orderTime.hashCode());
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
		if (clientId != other.clientId)
			return false;
		if (orderId != other.orderId)
			return false;
		if (orderTime == null) {
			if (other.orderTime != null)
				return false;
		} else if (!orderTime.equals(other.orderTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [orderId=" + orderId + ", clientId=" + clientId + ", orderTime=" + orderTime + "]";
	}
	
}
