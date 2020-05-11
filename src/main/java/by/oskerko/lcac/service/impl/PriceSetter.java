package by.oskerko.lcac.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import by.oskerko.lcac.bean.Flight;
import by.oskerko.lcac.bean.PreOrder;

public class PriceSetter {

	public Set<PreOrder> setPrice(List<Flight> flightList) {

		BigDecimal price = null;
		/*
		 * BigDecimal pricePriorReg = null; BigDecimal priceBaggage = null;
		 */
		BigDecimal thisDistance = null;
		BigDecimal distanceCoeff = new BigDecimal(0.1);
		BigDecimal seatsCoeff = new BigDecimal(1);
		/*
		 * BigDecimal priorRegCoeff = new BigDecimal(0.1); BigDecimal baggageCoeff = new
		 * BigDecimal(0.1);
		 */
		BigDecimal daysCoeff = null;
		Date date = new Date();
		Timestamp nowTime = new Timestamp(date.getTime());

		long time = 0;
		final long oneDay = 86400000;

		PreOrder preOrder;
		Flight flight;
		Set<PreOrder> preOrdersSet = new TreeSet<PreOrder>();

		int distance = 0;
		int emptySeats = 0;
		Timestamp departure = null;

		for (int i = 0; i < flightList.size(); i++) {

			flight = flightList.get(i);
			distance = flight.getDistance();
			emptySeats = flight.getEmptySeats();
			departure = flight.getDeparture();
			
			thisDistance = new BigDecimal(distance);
			price = thisDistance.multiply(distanceCoeff);

			if (emptySeats < 10) {
				seatsCoeff = new BigDecimal(1.5);
			} else if ((emptySeats >= 10) && (emptySeats < 50)) {
				seatsCoeff = new BigDecimal(1.3);
			} else if ((emptySeats >= 50) && (emptySeats < 100)) {
				seatsCoeff = new BigDecimal(1.1);
			} else {
				seatsCoeff = new BigDecimal(1);
			}

			price = price.multiply(seatsCoeff);

			time = departure.getTime() - nowTime.getTime();

			if (time < oneDay) {
				daysCoeff = new BigDecimal(1.5);
			} else if ((time >= oneDay) && (time < (2 * oneDay))) {
				daysCoeff = new BigDecimal(1.4);
			} else if ((time >= (2 * oneDay)) && (time < (5 * oneDay))) {
				daysCoeff = new BigDecimal(1.3);
			} else if ((time >= (5 * oneDay)) && (time < (10 * oneDay))) {
				daysCoeff = new BigDecimal(1.2);
			} else if (time >= (10 * oneDay) && (time < (20 * oneDay))) {
				daysCoeff = new BigDecimal(1.1);
			} else {
				daysCoeff = new BigDecimal(1);
			}

			price = price.multiply(daysCoeff);
			price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
			
			/*
			 * pricePriorReg = price.multiply(priorRegCoeff); pricePriorReg =
			 * pricePriorReg.setScale(2, BigDecimal.ROUND_HALF_UP);
			 * 
			 * priceBaggage = price.multiply(baggageCoeff); priceBaggage =
			 * priceBaggage.setScale(2, BigDecimal.ROUND_HALF_UP);
			 */
			
			preOrder = new PreOrder(flight, price);
			preOrdersSet.add(preOrder);

		}
		return preOrdersSet;
	}

}
