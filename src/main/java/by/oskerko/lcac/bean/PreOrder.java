package by.oskerko.lcac.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class PreOrder implements Serializable, Comparable<PreOrder> {
	
	private static final long serialVersionUID = 1L;
	
	private Flight flight;
	private BigDecimal price;
	
	public PreOrder() {}
	
	public PreOrder(Flight flight, BigDecimal price) {
		this.flight = flight;
		this.price = price;
	}

	@Override
	public int compareTo(PreOrder o) {
		return flight.getDeparture().compareTo(o.flight.getDeparture());
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
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
		result = prime * result + ((flight == null) ? 0 : flight.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
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
		PreOrder other = (PreOrder) obj;
		if (flight == null) {
			if (other.flight != null)
				return false;
		} else if (!flight.equals(other.flight))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [flight=" + flight + ", price=" + price + "]";
	}
	

}
