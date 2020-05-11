package by.oskerko.lcac.service.impl;

import java.util.List;
import java.util.Set;

import by.oskerko.lcac.bean.Flight;
import by.oskerko.lcac.bean.Order;
import by.oskerko.lcac.bean.Ticket;
import by.oskerko.lcac.bean.User;
import by.oskerko.lcac.dao.AdminDao;
import by.oskerko.lcac.dao.DaoException;
import by.oskerko.lcac.dao.DaoProvider;
import by.oskerko.lcac.service.AdminService;
import by.oskerko.lcac.service.ServiceException;
import by.oskerko.lcac.service.validation.DataValidator;

public class AdminServiceImpl implements AdminService {

	private static final DataValidator validator = DataValidator.getInstance();
	private static final DaoProvider daoProvider = DaoProvider.getInstance();

	@Override
	public Set<Flight> getAllFlights(String strDeparture) throws ServiceException {

		if (!validator.checkFlight(strDeparture)) {
			throw new ServiceException("пока так: данные невалидны (это в константы не выносят)");
		} else {
			Set<Flight> flightsSet;
			AdminDao adminDao = daoProvider.getAdminDao();

			try {
				flightsSet = adminDao.getAllFlights(strDeparture);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}

			return flightsSet;
		}

	}
	
	@Override
	public Set<Flight> getAllFlights(String strDateFrom, String strDateTo) throws ServiceException {
		
		if (!validator.checkDeleteFlight(strDateFrom, strDateTo)) {
			throw new ServiceException("пока так: данные невалидны (это в константы не выносят)");
		} else {
			Set<Flight> flightsSet;
			AdminDao adminDao = daoProvider.getAdminDao();

			try {
				flightsSet = adminDao.getAllFlights(strDateFrom, strDateTo);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}

			return flightsSet;
		}
	}


	@Override
	public List<User> getAllUsers() throws ServiceException {

		List<User> usersList;
		AdminDao adminDao = DaoProvider.getInstance().getAdminDao();

		try {
			usersList = adminDao.getAllUsers();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

		return usersList;
	}

	@Override
	public List<Ticket> getTicketInfo(int flightNumber) throws ServiceException {

		if (!validator.checkFlightNumber(flightNumber)) {
			throw new ServiceException("пока так: данные невалидны (это в константы не выносят)");
		} else {
			List<Ticket> ticketsList;

			AdminDao adminDao = DaoProvider.getInstance().getAdminDao();

			try {
				ticketsList = adminDao.getTicketInfo(flightNumber);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}

			return ticketsList;
		}
	}

	@Override
	public List<Order> getAllOrders(String strDateFrom, String strDateTo) throws ServiceException {

		if (!validator.checkGetAllOrders(strDateFrom, strDateTo)) {
			throw new ServiceException("пока так: данные невалидны (это в константы не выносят)");
		} else {
			List<Order> ordersList;

			AdminDao adminDao = DaoProvider.getInstance().getAdminDao();

			try {
				ordersList = adminDao.getAllOrders(strDateFrom, strDateTo);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
			return ordersList;
		}
	}

	@Override
	public Flight addNewFlight (String origin, String destination, String timeDeparture,
			String timeArrival, String numberOfSeats, String emptySeats, String distance) throws ServiceException {
		if (!validator.checkAddNewFlight (origin, destination, timeDeparture,
				timeArrival, numberOfSeats, emptySeats, distance)) {
			throw new ServiceException("пока так: данные невалидны (это в константы не выносят)");
		} else {
			Flight flight;
			
			AdminDao adminDao = DaoProvider.getInstance().getAdminDao();

			try {
				flight = adminDao.addNewFlight(origin, destination, timeDeparture,
						timeArrival, numberOfSeats, emptySeats, distance);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
			return flight;
		}		
	}

	@Override
	public boolean deleteSelectedFlight(String[] flightsNumArr) throws ServiceException {

		if (!validator.checkDeleteSelectedFlight (flightsNumArr)) {
			throw new ServiceException("пока так: данные невалидны (это в константы не выносят)");
		} else {
			boolean isTrue;
			
			AdminDao adminDao = DaoProvider.getInstance().getAdminDao();

			try {
				isTrue = adminDao.deleteSelectedFlight(flightsNumArr);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
			return isTrue;
		}		
	}
}
