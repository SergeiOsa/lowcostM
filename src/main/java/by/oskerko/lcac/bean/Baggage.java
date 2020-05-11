package by.oskerko.lcac.bean;

import java.io.Serializable;

public class Baggage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int baggageId;
	private int weight;

	public Baggage() {}
	
	public Baggage(int baggageId, int weight) {
		this.baggageId = baggageId;
		this.weight = weight;
	}

	public int getBaggageId() {
		return baggageId;
	}

	public void setBaggageId(int baggageId) {
		this.baggageId = baggageId;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + baggageId;
		result = prime * result + weight;
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
		Baggage other = (Baggage) obj;
		if (baggageId != other.baggageId)
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [baggageId=" + baggageId + ", weight=" + weight + "]";
	}
	
}
