package by.oskerko.lcac.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Report implements Serializable, Comparable<Report> {

	private static final long serialVersionUID = 1L;
	
	private int ticketId;
	private String origin;
	private String destination;
	private Timestamp departure;
	private String name;
	private String surname;
	private String priorityRegistration;
	private int baggageId;
	private BigDecimal price;
	
	public Report() {}
	
	public Report(int ticketId, String origin, String destination, Timestamp departure,
						String name, String surname, String priorityRegistration, int baggageId, BigDecimal price) {
		
		this.ticketId = ticketId;
		this.origin = origin;
		this.destination = destination;
		this.departure = departure;
		this.name = name;
		this.surname = surname;
		this.priorityRegistration = priorityRegistration;
		this.baggageId = baggageId;
		this.price = price;
	}
	
	@Override
	public int compareTo(Report o) {
		return departure.compareTo(o.getDeparture());
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Timestamp getDeparture() {
		return departure;
	}

	public void setDeparture(Timestamp departure) {
		this.departure = departure;
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

	public String getPriorityRegistration() {
		return priorityRegistration;
	}

	public void setPriorityRegistration(String priorityRegistration) {
		this.priorityRegistration = priorityRegistration;
	}

	public int getBaggageId() {
		return baggageId;
	}

	public void setBaggageId(int baggageId) {
		this.baggageId = baggageId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + baggageId;
		result = prime * result + ((departure == null) ? 0 : departure.hashCode());
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
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
		Report other = (Report) obj;
		if (baggageId != other.baggageId)
			return false;
		if (departure == null) {
			if (other.departure != null)
				return false;
		} else if (!departure.equals(other.departure))
			return false;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (origin == null) {
			if (other.origin != null)
				return false;
		} else if (!origin.equals(other.origin))
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
		return this.getClass().getSimpleName() + " [ticketId=" + ticketId + ", origin=" + origin + ", destination=" + destination + ", departure="
				+ departure + ", name=" + name + ", surname=" + surname + ", priorityRegistration="
				+ priorityRegistration + ", baggageId=" + baggageId + ", price=" + price + "]";
	}

	

	
}
