package by.oskerko.lcac.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class ReportForAdmin extends Report implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int orderId;
	private int clientId;
	private Timestamp orderTime;
	
	public ReportForAdmin () {}
	
	public ReportForAdmin (int orderId, int clientId, Timestamp orderTime) {
		this.orderId = orderId;
		this.clientId = clientId;
		this.orderTime = orderTime;
	}
	
	public ReportForAdmin (int orderId, int clientId, Timestamp orderTime, int ticketId, String origin,String destination,
			Timestamp departure, String name, String surname, String priorityRegistration, int baggageId, BigDecimal price) {
		
		super(ticketId, origin, destination, departure, name, surname, priorityRegistration, baggageId, price);
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
		int result = super.hashCode();
		result = prime * result + clientId;
		result = prime * result + orderId;
		result = prime * result + ((orderTime == null) ? 0 : orderTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReportForAdmin other = (ReportForAdmin) obj;
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
