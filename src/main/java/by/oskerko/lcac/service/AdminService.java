package by.oskerko.lcac.service;

import java.util.List;
import java.util.Set;

import by.oskerko.lcac.bean.Flight;
import by.oskerko.lcac.bean.Order;
import by.oskerko.lcac.bean.Ticket;
import by.oskerko.lcac.bean.User;

public interface AdminService {

	Set<Flight> getAllFlights (String strDeparture) throws ServiceException;
	Set<Flight> getAllFlights (String strDateFrom, String strDateTo) throws ServiceException;
	List<User> getAllUsers() throws ServiceException;
	List<Ticket> getTicketInfo(int flightNumber) throws ServiceException;
	List<Order> getAllOrders(String strDateFrom, String strDateTo) throws ServiceException;
	Flight addNewFlight (String origin, String destination, String timeDeparture, String timeArrival,
			String numberOfSeats, String emptySeats, String distance ) throws ServiceException;
	boolean deleteSelectedFlight(String[] flightsNumArr) throws ServiceException;
}
