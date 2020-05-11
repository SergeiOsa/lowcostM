package by.oskerko.lcac.dao;

import java.util.List;
import java.util.Set;

import by.oskerko.lcac.bean.Flight;
import by.oskerko.lcac.bean.Order;
import by.oskerko.lcac.bean.Ticket;
import by.oskerko.lcac.bean.User;

public interface AdminDao {

	Set<Flight> getAllFlights (String strDeparture) throws DaoException;
	Set<Flight> getAllFlights (String strDateFrom, String strDateTo) throws DaoException;
	List<User> getAllUsers() throws DaoException;
	List<Ticket> getTicketInfo(int flightNumber) throws DaoException;
	List<Order> getAllOrders(String strDateFrom, String strDateTo) throws DaoException;
	Flight addNewFlight (String origrin, String destination, String timeDeparture, String timeArrival,
			String numberOfSeats, String emptySeats, String distance ) throws DaoException;
	boolean deleteSelectedFlight(String[] flightsNumArr) throws DaoException;
}
