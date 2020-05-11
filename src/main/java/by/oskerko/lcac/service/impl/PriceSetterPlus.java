package by.oskerko.lcac.service.impl;

import java.math.BigDecimal;

public class PriceSetterPlus {
	
	public BigDecimal plusPrice(BigDecimal price, String priorityRegistration, String isBaggage) {
		
		BigDecimal totalPrice = price;
		BigDecimal coeff = new BigDecimal(10);
		
		if(priorityRegistration.equalsIgnoreCase("Yes")) {
			BigDecimal prPrice = price.divide(coeff, 2, BigDecimal.ROUND_HALF_UP);
			totalPrice = totalPrice.add(prPrice);
		}
		
		if(isBaggage.equalsIgnoreCase("Yes")) {
			BigDecimal baggagePrice = price.divide(coeff, 2, BigDecimal.ROUND_HALF_UP);
			totalPrice = totalPrice.add(baggagePrice);
		}
		
		return totalPrice;
	}

}
