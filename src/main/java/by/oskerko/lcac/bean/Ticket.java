package by.oskerko.lcac.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;

	private int ticketId;
	private int flightNumber;
	private BigDecimal price;
	private String booked;
	private String paid;
	private String priorityRegistration;
	private String isBaggage;
	private int baggageId;
	private int orderId;
	private String name;
	private String surname;
	private String passport;
	
	public Ticket() {}
	
	public Ticket(int flightNumber, BigDecimal price, String booked, String paid,
			String priorityRegistration, String isBaggage) {
		this.flightNumber = flightNumber;
		this.price = price;
		this.booked = booked;
		this.paid = paid;
		this.priorityRegistration = priorityRegistration;
		this.isBaggage = isBaggage;
	}
	
	public Ticket(int ticketId, int flightNumber, BigDecimal price, String booked,
			String paid, String priorityRegistration, int baggageId, int orderId, String name, String surname) {
		
		this.ticketId = ticketId;
		this.flightNumber = flightNumber;
		this.price = price;
		this.booked = booked;
		this.paid = paid;
		this.priorityRegistration = priorityRegistration;
		this.baggageId = baggageId;
		this.orderId = orderId;
		this.name = name;
		this.surname = surname;
	}
	
	public Ticket(int ticketId, int flightNumber, BigDecimal price, String booked,
			String paid, String priorityRegistration, int baggageId, int orderId, String name, String surname, String passport) {
		
		this.ticketId = ticketId;
		this.flightNumber = flightNumber;
		this.price = price;
		this.booked = booked;
		this.paid = paid;
		this.priorityRegistration = priorityRegistration;
		this.baggageId = baggageId;
		this.orderId = orderId;
		this.name = name;
		this.surname = surname;
		this.passport = passport;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getBooked() {
		return booked;
	}

	public void setBooked(String booked) {
		this.booked = booked;
	}

	public String getPaid() {
		return paid;
	}

	public void setPaid(String paid) {
		this.paid = paid;
	}

	public String getPriorityRegistration() {
		return priorityRegistration;
	}

	public void setPriorityRegistration(String priorityRegistration) {
		this.priorityRegistration = priorityRegistration;
	}

	public String getIsBaggage() {
		return isBaggage;
	}

	public void setIsBaggage(String isBaggage) {
		this.isBaggage = isBaggage;
	}

	public int getBaggageId() {
		return baggageId;
	}

	public void setBaggageId(int baggageId) {
		this.baggageId = baggageId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + baggageId;
		result = prime * result + ((booked == null) ? 0 : booked.hashCode());
		result = prime * result + flightNumber;
		result = prime * result + ((isBaggage == null) ? 0 : isBaggage.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + orderId;
		result = prime * result + ((paid == null) ? 0 : paid.hashCode());
		result = prime * result + ((passport == null) ? 0 : passport.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((priorityRegistration == null) ? 0 : priorityRegistration.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ticketId;
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
		Ticket other = (Ticket) obj;
		if (baggageId != other.baggageId)
			return false;
		if (booked == null) {
			if (other.booked != null)
				return false;
		} else if (!booked.equals(other.booked))
			return false;
		if (flightNumber != other.flightNumber)
			return false;
		if (isBaggage == null) {
			if (other.isBaggage != null)
				return false;
		} else if (!isBaggage.equals(other.isBaggage))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (orderId != other.orderId)
			return false;
		if (paid == null) {
			if (other.paid != null)
				return false;
		} else if (!paid.equals(other.paid))
			return false;
		if (passport == null) {
			if (other.passport != null)
				return false;
		} else if (!passport.equals(other.passport))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (priorityRegistration == null) {
			if (other.priorityRegistration != null)
				return false;
		} else if (!priorityRegistration.equals(other.priorityRegistration))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (ticketId != other.ticketId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [ticketId=" + ticketId + ", flightNumber=" + flightNumber + ", price=" + price + ", booked="
				+ booked + ", paid=" + paid + ", priorityRegistration=" + priorityRegistration + ", isBaggage="
				+ isBaggage + ", baggageId=" + baggageId + ", orderId=" + orderId + ", name=" + name + ", surname="
				+ surname + ", passport=" + passport + "]";
	}

}
