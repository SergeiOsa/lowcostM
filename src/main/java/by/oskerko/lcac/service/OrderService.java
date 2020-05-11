package by.oskerko.lcac.service;

import java.math.BigDecimal;
import java.util.Set;

import by.oskerko.lcac.bean.Flight;
import by.oskerko.lcac.bean.Order;
import by.oskerko.lcac.bean.PreOrder;
import by.oskerko.lcac.bean.Ticket;

public interface OrderService {

	Set<PreOrder> search(String origin, String destination, String strDeparture) throws ServiceException;
	PreOrder chooseFlight(int flightNumber, BigDecimal price) throws ServiceException;
	Ticket bookingStep1(int clientId, int flightNumber,  BigDecimal price,
			String priorityRegistration, String isBaggage);
	Ticket bookingStep2(Ticket ticket, String name, String surname, String passport);
	Flight getFlight(int flightNumber) throws ServiceException;
	Order booking(Ticket ticket, int clientId) throws ServiceException;
	boolean payment(BigDecimal price, int clientId, Ticket ticket) throws ServiceException;
}
