package by.oskerko.lcac.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import by.oskerko.lcac.bean.Flight;
import by.oskerko.lcac.bean.Order;
import by.oskerko.lcac.bean.Ticket;
import by.oskerko.lcac.bean.User;
import by.oskerko.lcac.dao.AdminDao;
import by.oskerko.lcac.dao.DaoException;
import by.oskerko.lcac.dao.connectionpool.ConnectionPool;

public class SQLAdminDao implements AdminDao {

	@Override
	public Set<Flight> getAllFlights(String strDeparture) throws DaoException {

		Set<Flight> flightsSet = new TreeSet<Flight>();
		Flight flight;
		int flightNumber = 0;
		String origin = null;
		String destination = null;
		Timestamp departure = null;
		Timestamp arrival = null;
		int numberOfSeats = 0;
		int emptySeats = 0;
		int distance = 0;

		Date date = new Date();

		if (strDeparture.equalsIgnoreCase("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			strDeparture = sdf.format(date);
		}

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			Date parsedDate = dateFormat.parse(strDeparture + " 00:00:00.000");
			departure = new Timestamp(parsedDate.getTime());
		} catch (ParseException e) {
			throw new DaoException(e);
		}

		String sqlGetAllFlights = "SELECT * FROM flight WHERE time_departure > '" + departure + "'";

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			con = connectionPool.take();
			st = con.prepareStatement(sqlGetAllFlights);
			rs = st.executeQuery(sqlGetAllFlights);
			while (rs.next()) {
				flightNumber = rs.getInt("flight_number");
				origin = rs.getString("departure_place");
				destination = rs.getString("arrival_place");
				departure = rs.getTimestamp("time_departure");
				arrival = rs.getTimestamp("time_arrival");
				numberOfSeats = rs.getInt("number_of_seats");
				emptySeats = rs.getInt("empty_seats");
				distance = rs.getInt("distance");

				flight = new Flight(flightNumber, origin, destination, departure, arrival, numberOfSeats, emptySeats,
						distance);
				flightsSet.add(flight);
			}

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				if (null != rs) {
					rs.close();
				}
				if (null != st) {
					st.close();
				}
			} catch (SQLException e) {
				throw new DaoException(e);
			}
			connectionPool.realese(con);
		}

		return flightsSet;
	}

	@Override
	public Set<Flight> getAllFlights(String strDateFrom, String strDateTo) throws DaoException {

		Set<Flight> flightsSet = new TreeSet<Flight>();
		Flight flight;
		int flightNumber = 0;
		String origin = null;
		String destination = null;
		Timestamp departure = null;
		Timestamp arrival = null;
		int numberOfSeats = 0;
		int emptySeats = 0;
		int distance = 0;

		Timestamp dateFrom;
		Timestamp dateTo;
		Date date = new Date();

		if (strDateFrom.equalsIgnoreCase("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			strDateFrom = sdf.format(date);
		}
		if (strDateTo.equalsIgnoreCase("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			strDateTo = sdf.format(date);
		}

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			Date parsedDate = dateFormat.parse(strDateFrom + " 00:00:00.000");
			dateFrom = new Timestamp(parsedDate.getTime());
			parsedDate = dateFormat.parse(strDateTo + " 23:59:59.999");
			dateTo = new Timestamp(parsedDate.getTime());
		} catch (ParseException e) {
			throw new DaoException(e);
		}

		String sqlGetFlights = "SELECT * FROM flight WHERE" + " (time_departure >= '" + dateFrom
				+ "' AND time_arrival <= '" + dateTo + "')";

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			con = connectionPool.take();
			st = con.prepareStatement(sqlGetFlights);
			rs = st.executeQuery(sqlGetFlights);
			while (rs.next()) {
				flightNumber = rs.getInt("flight_number");
				origin = rs.getString("departure_place");
				destination = rs.getString("arrival_place");
				departure = rs.getTimestamp("time_departure");
				arrival = rs.getTimestamp("time_arrival");
				numberOfSeats = rs.getInt("number_of_seats");
				emptySeats = rs.getInt("empty_seats");
				distance = rs.getInt("distance");

				flight = new Flight(flightNumber, origin, destination, departure, arrival, numberOfSeats, emptySeats,
						distance);
				flightsSet.add(flight);
			}

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				if (null != rs) {
					rs.close();
				}
				if (null != st) {
					st.close();
				}
			} catch (SQLException e) {
				throw new DaoException(e);
			}
			connectionPool.realese(con);
		}

		return flightsSet;
	}

	@Override
	public List<User> getAllUsers() throws DaoException {

		User user;
		List<User> usersList = new ArrayList<User>();
		int id = 0;
		String name = null;
		String surname = null;
		String role = null;
		String email = null;

		String sqlGetAllUsers = "SELECT id, name, surname, role, email FROM client";

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			con = connectionPool.take();
			st = con.prepareStatement(sqlGetAllUsers);
			rs = st.executeQuery(sqlGetAllUsers);
			while (rs.next()) {
				id = rs.getInt(1);
				name = rs.getString(2);
				surname = rs.getString(3);
				role = rs.getString(4);
				email = rs.getString(5);
				user = new User(id, name, surname, role, email);
				usersList.add(user);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				if (null != rs) {
					rs.close();
				}
				if (null != st) {
					st.close();
				}
			} catch (SQLException e) {
				throw new DaoException(e);
			}
			connectionPool.realese(con);
		}

		return usersList;
	}

	@Override
	public List<Ticket> getTicketInfo(int flightNumber) throws DaoException {

		List<Ticket> ticketsList = new ArrayList<Ticket>();

		Ticket ticket;
		int ticketId = 0;
		BigDecimal price = null;
		String booked = null;
		String paid = null;
		String priorityRegistration = null;
		int baggageId = 0;
		int orderId = 0;
		String name = null;
		String surname = null;
		String passport = null;

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		String sqlGetTicketInfo = "SELECT ticket_id, price, booked, paid, priority_registration, baggage_id, order_id, name,"
				+ " surname, passport FROM ticket WHERE ticket.flight_number = '" + flightNumber + "'";

		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			con = connectionPool.take();
			st = con.prepareStatement(sqlGetTicketInfo);
			rs = st.executeQuery(sqlGetTicketInfo);
			System.out.println("1q");
			while (rs.next()) {
				ticketId = rs.getInt("ticket_id");
				price = rs.getBigDecimal("price");
				booked = rs.getString("booked");
				paid = rs.getString("paid");
				priorityRegistration = rs.getString("priority_registration");
				baggageId = rs.getInt("baggage_id");
				orderId = rs.getInt("order_id");
				name = rs.getString("name");
				surname = rs.getString("surname");
				passport = rs.getString("passport");

				ticket = new Ticket(ticketId, flightNumber, price, booked, paid, priorityRegistration, baggageId,
						orderId, name, surname, passport);
				ticketsList.add(ticket);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				if (null != rs) {
					rs.close();
				}
				if (null != st) {
					st.close();
				}
			} catch (SQLException e) {
				throw new DaoException(e);
			}
			connectionPool.realese(con);

		}

		return ticketsList;
	}

	@Override
	public List<Order> getAllOrders(String strDateFrom, String strDateTo) throws DaoException {

		List<Order> ordersList = new ArrayList<Order>();
		Order order;
		int orderId = 0;
		int clientId = 0;
		Timestamp orderTime = null;

		Timestamp dateFrom;
		Timestamp dateTo;
		Date date = new Date();

		if (strDateFrom.equalsIgnoreCase("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			strDateFrom = sdf.format(date);
		}
		if (strDateTo.equalsIgnoreCase("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			strDateTo = sdf.format(date);
		}

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			Date parsedDate = dateFormat.parse(strDateFrom + " 00:00:00.000");
			dateFrom = new Timestamp(parsedDate.getTime());
			parsedDate = dateFormat.parse(strDateTo + " 23:59:59.999");
			dateTo = new Timestamp(parsedDate.getTime());
		} catch (ParseException e) {
			throw new DaoException(e);
		}

		String sqlGetAllOrders = "SELECT * FROM ordering WHERE (order_time >= '" + dateFrom + "' AND order_time <= '"
				+ dateTo + "')";

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			con = connectionPool.take();
			st = con.prepareStatement(sqlGetAllOrders);
			rs = st.executeQuery(sqlGetAllOrders);
			while (rs.next()) {
				orderId = rs.getInt("order_id");
				clientId = rs.getInt("client_id");
				;
				orderTime = rs.getTimestamp("order_time");
				order = new Order(orderId, clientId, orderTime);
				ordersList.add(order);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				if (null != rs) {
					rs.close();
				}
				if (null != st) {
					st.close();
				}
			} catch (SQLException e) {
				throw new DaoException(e);
			}
			connectionPool.realese(con);
		}

		return ordersList;
	}

	@Override
	public Flight addNewFlight(String origin, String destination, String timeDeparture, String timeArrival,
			String strNumberOfSeats, String strEmptySeats, String strDistance) throws DaoException {

		Flight flight = null;
		int flightNumber = 0;
		Timestamp departure;
		Timestamp arrival;
		int numberOfSeats;
		int emptySeats;
		int distance;

		numberOfSeats = Integer.valueOf(strNumberOfSeats);
		emptySeats = Integer.valueOf(strEmptySeats);
		distance = Integer.valueOf(strDistance);

		Date date = new Date();

		if (timeDeparture.equalsIgnoreCase("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			timeDeparture = sdf.format(date);
		}
		if (timeArrival.equalsIgnoreCase("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			timeArrival = sdf.format(date);
		}

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			Date parsedDate = dateFormat.parse(timeDeparture + ":00.000");
			departure = new Timestamp(parsedDate.getTime());
			parsedDate = dateFormat.parse(timeArrival + ":00.000");
			arrival = new Timestamp(parsedDate.getTime());
		} catch (ParseException e) {
			throw new DaoException(e);
		}

		String sqlAddNewFlight = "INSERT INTO flight (departure_place, arrival_place, time_departure, time_arrival, "
				+ "number_of_seats, empty_seats, distance) VALUES ('" + origin + "', '" + destination + "', " + "'"
				+ departure + "', '" + arrival + "', '" + numberOfSeats + "', '" + emptySeats + "', '" + distance
				+ "')";
		String sqlGetNewFlight = "SELECT * FROM flight";

		Connection con = null;
		Statement stAddNewFlight = null;
		Statement stGetNewFlight = null;
		ResultSet rs = null;

		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			con = connectionPool.take();
			stAddNewFlight = con.prepareStatement(sqlAddNewFlight);
			stAddNewFlight.executeUpdate(sqlAddNewFlight);

			stGetNewFlight = con.prepareStatement(sqlGetNewFlight);
			rs = stGetNewFlight.executeQuery(sqlGetNewFlight);
			while (rs.next()) {
				flightNumber = rs.getInt("flight_number");
				origin = rs.getString("departure_place");
				destination = rs.getString("arrival_place");
				departure = rs.getTimestamp("time_departure");
				arrival = rs.getTimestamp("time_arrival");
				numberOfSeats = rs.getInt("number_of_seats");
				emptySeats = rs.getInt("empty_seats");
				distance = rs.getInt("distance");
				flight = new Flight(flightNumber, origin, destination, departure, arrival, numberOfSeats, emptySeats,
						distance);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				if (null != rs) {
					rs.close();
				}
				if (null != stAddNewFlight) {
					stAddNewFlight.close();
				}
				if (null != stGetNewFlight) {
					stGetNewFlight.close();
				}
			} catch (SQLException e) {
				throw new DaoException(e);
			}
			connectionPool.realese(con);
		}
		return flight;
	}

	@Override
	public boolean deleteSelectedFlight(String[] flightsNumArr) throws DaoException {

		boolean isTrue = false;
		int flightNumber = 0;

		String sqlDeleteSelectedFlight = null;
		Connection con = null;
		Statement st = null;

		ConnectionPool connectionPool = ConnectionPool.getInstance();
		con = connectionPool.take();

		try {

			for (int i = 0; i < flightsNumArr.length; i++) {
				flightNumber = Integer.valueOf(flightsNumArr[i]);
				sqlDeleteSelectedFlight = "DELETE FROM flight WHERE flight_number = '" + flightNumber + "'";
				st = con.prepareStatement(sqlDeleteSelectedFlight);
				st.executeUpdate(sqlDeleteSelectedFlight);
			}
			isTrue = true;
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			try {
				if (null != st) {
					st.close();
				}
			} catch (SQLException e) {
				throw new DaoException(e);
			}
			connectionPool.realese(con);
		}
		return isTrue;
	}

}
