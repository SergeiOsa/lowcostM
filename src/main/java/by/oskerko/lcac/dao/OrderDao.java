package by.oskerko.lcac.dao;

import java.math.BigDecimal;
import java.util.List;

import by.oskerko.lcac.bean.Baggage;
import by.oskerko.lcac.bean.Flight;
import by.oskerko.lcac.bean.Order;
import by.oskerko.lcac.bean.PreOrder;
import by.oskerko.lcac.bean.Ticket;

public interface OrderDao {

	List<Flight> search (String origin, String destination, String strDeparture) throws DaoException;
	PreOrder chooseFlight(int flightNumber, BigDecimal price) throws DaoException;
	Flight getFlight(int flightNumber) throws DaoException;
	Baggage createBaggage(String isBaggage)  throws DaoException;
	Order createOrder(int clientId) throws DaoException;
	Ticket createTicket(Ticket ticket, Flight flight) throws DaoException;
	BigDecimal getUserBalance(int clientId) throws DaoException;
	boolean ticketPayment(BigDecimal price, int clientId, Ticket ticket) throws DaoException;
	
	//Ticket booking(int clientId, int flightNumber,  BigDecimal price,
			//String priorityRegistration, String isBaggage) throws DaoException;
}
