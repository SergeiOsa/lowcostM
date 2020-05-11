package by.oskerko.lcac.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.oskerko.lcac.bean.Baggage;
import by.oskerko.lcac.bean.Flight;
import by.oskerko.lcac.bean.Order;
import by.oskerko.lcac.bean.PreOrder;
import by.oskerko.lcac.bean.Ticket;
import by.oskerko.lcac.dao.DaoException;
import by.oskerko.lcac.dao.OrderDao;
import by.oskerko.lcac.dao.connectionpool.ConnectionPool;

public class SQLOrderDao implements OrderDao {

	@Override
	public List<Flight> search(String origin, String destination, String strDeparture) throws DaoException {

		Flight flight;
		List<Flight> flightsList = new ArrayList<Flight>();

		Date date = new Date();
		Timestamp departure = null;

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

		String sqlSearchFlights = "SELECT flight_number, departure_place, arrival_place, time_departure,"
				+ " time_arrival, number_of_seats, empty_seats, distance" + " FROM flight WHERE departure_place = '"
				+ origin + "' AND arrival_place = '" + destination + "' AND time_departure > '" + departure
				+ "' AND empty_seats > 0";

		int flightNumber = 0;
		Timestamp selectedDeparture = null;
		Timestamp arrival = null;
		int numberOfSeats = 0;
		int emptySeats = 0;
		int distance = 0;

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		ConnectionPool connectionPool = ConnectionPool.getInstance();
		con = connectionPool.take();

		try {
			st = con.prepareStatement(sqlSearchFlights);
			rs = st.executeQuery(sqlSearchFlights);

			while (rs.next()) {
				flightNumber = rs.getInt("flight_number");
				selectedDeparture = rs.getTimestamp("time_departure");
				arrival = rs.getTimestamp("time_arrival");
				numberOfSeats = rs.getInt("number_of_seats");
				emptySeats = rs.getInt("empty_seats");
				distance = rs.getInt("distance");

				if (selectedDeparture.after(date)) {
					flight = new Flight(flightNumber, origin, destination, selectedDeparture, arrival, numberOfSeats,
							emptySeats, distance);
					flightsList.add(flight);
				}

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

		return flightsList;
	}

	@Override
	public PreOrder chooseFlight(int flightNumber, BigDecimal price) throws DaoException {

		PreOrder preOrder;

		String sqlFlight;

		sqlFlight = "SELECT * FROM flight WHERE flight_number = '" + flightNumber + "'";

		Flight flight = null;
		String origin = null;
		String destination = null;
		Timestamp departure = null;
		Timestamp arrival = null;
		int numberOfSeats = 0;
		int emptySeats = 0;
		int distance = 0;

		Connection con;
		Statement st = null;
		ResultSet rs = null;

		ConnectionPool connectionPool = ConnectionPool.getInstance();
		con = connectionPool.take();

		try {
			st = con.prepareStatement(sqlFlight);
			rs = st.executeQuery(sqlFlight);
			while (rs.next()) {
				origin = rs.getString("departure_place");
				destination = rs.getString("arrival_place");
				departure = rs.getTimestamp("time_departure");
				arrival = rs.getTimestamp("time_arrival");
				numberOfSeats = rs.getInt("number_of_seats");
				emptySeats = rs.getInt("empty_seats");
				distance = rs.getInt("distance");
			}
			flight = new Flight(flightNumber, origin, destination, departure, arrival, numberOfSeats, emptySeats,
					distance);
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				throw new DaoException(e);
			}
			connectionPool.realese(con);
		}

		preOrder = new PreOrder(flight, price);

		return preOrder;
	}

	@Override
	public Flight getFlight(int flightNumber) throws DaoException {

		Flight flight;
		String origin = null;
		String destination = null;
		Timestamp departure = null;
		Timestamp arrival = null;
		int numberOfSeats = 0;
		int emptySeats = 0;
		int distance = 0;

		String sqlFlight;
		sqlFlight = "SELECT * FROM flight WHERE flight_number = '" + flightNumber + "'";

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			con = connectionPool.take();
			st = con.prepareStatement(sqlFlight);
			rs = st.executeQuery(sqlFlight);
			while (rs.next()) {
				origin = rs.getString("departure_place");
				destination = rs.getString("arrival_place");
				departure = rs.getTimestamp("time_departure");
				arrival = rs.getTimestamp("time_arrival");
				numberOfSeats = rs.getInt("number_of_seats");
				emptySeats = rs.getInt("empty_seats");
				distance = rs.getInt("distance");
			}
			flight = new Flight(flightNumber, origin, destination, departure, arrival, numberOfSeats, emptySeats,
					distance);
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				throw new DaoException(e);
			}
			connectionPool.realese(con);
		}

		return flight;
	}

	@Override
	public Baggage createBaggage(String isBaggage) throws DaoException {

		Baggage baggage;

		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int baggageId = 0;
		int weight = 8;
		if (isBaggage.equalsIgnoreCase("Yes")) {
			weight = 20;
		}

		String sqlNewBaggage = "INSERT INTO baggage (weight) VALUES ('" + weight + "')";

		try {
			con = connectionPool.take();
			ps = con.prepareStatement(sqlNewBaggage, Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate(sqlNewBaggage, Statement.RETURN_GENERATED_KEYS);
			rs = ps.getGeneratedKeys();
			rs.next();
			baggageId = rs.getInt(1);

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				if (null != rs) {
					rs.close();
				}
				if (null != ps) {
					ps.close();
				}
			} catch (SQLException e) {
				throw new DaoException(e);
			}
			connectionPool.realese(con);
		}

		baggage = new Baggage(baggageId, weight);

		return baggage;
	}

	@Override
	public Order createOrder(int clientId) throws DaoException {

		Order order = null;
		int orderId = 0;

		Date date = new Date();
		Timestamp orderTime = new Timestamp(date.getTime());

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		String sqlNewOrder = "INSERT INTO ordering (client_id ,order_time) VALUES (?,?)";

		try {
			con = connectionPool.take();
			ps = con.prepareStatement(sqlNewOrder, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, clientId);
			ps.setTimestamp(2, orderTime);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			rs.next();
			orderId = rs.getInt(1);
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				if (null != rs) {
					rs.close();
				}
				if (null != ps) {
					ps.close();
				}
			} catch (SQLException e) {
				throw new DaoException(e);
			}
			connectionPool.realese(con);
		}

		order = new Order(orderId, clientId, orderTime);

		return order;
	}

	@Override
	public Ticket createTicket(Ticket ticket, Flight flight) throws DaoException {

		int ticketId = 0;
		int emptySeats;
		int flightNumber;

		emptySeats = flight.getEmptySeats();
		flightNumber = flight.getFlightNumber();

		if (emptySeats < 1) {
			return null;
		} // return null????? подумай С.А.

		String sqlNewTicket = "INSERT INTO ticket (flight_number, price, booked, paid, priority_registration, baggage_id,"
				+ " order_id, name, surname, passport) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String sqlUpdateFlight = "UPDATE flight SET empty_seats = empty_seats - 1 WHERE flight_number = '"
				+ flightNumber + "'";

		/*
		 * этот код умегьшал кол-во своб мест в бд try { prep =
		 * con.prepareStatement(sqlUpdateFlight); prep.executeUpdate(); } catch
		 * (SQLException e) { throw new DaoException(e); }
		 */

		ConnectionPool connectionPool = ConnectionPool.getInstance();

		Connection con = null;
		PreparedStatement psTicket = null;
		PreparedStatement psFlight = null;
		ResultSet rs = null;

		try {
			con = connectionPool.take();
			con.setAutoCommit(false);
			psTicket = con.prepareStatement(sqlNewTicket, Statement.RETURN_GENERATED_KEYS);
			psTicket.setInt(1, ticket.getFlightNumber());
			psTicket.setBigDecimal(2, ticket.getPrice());
			psTicket.setString(3, ticket.getBooked());
			psTicket.setString(4, ticket.getPaid());
			psTicket.setString(5, ticket.getPriorityRegistration());
			psTicket.setInt(6, ticket.getBaggageId());
			psTicket.setInt(7, ticket.getOrderId());
			psTicket.setString(8, ticket.getName());
			psTicket.setString(9, ticket.getSurname());
			psTicket.setString(10, ticket.getPassport());
			psTicket.executeUpdate();
			rs = psTicket.getGeneratedKeys();
			rs.next();
			ticketId = rs.getInt(1);
			ticket.setTicketId(ticketId);
			psFlight = con.prepareStatement(sqlUpdateFlight);
			psFlight.executeUpdate();
			con.commit();

		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new DaoException(e1);
			}
			throw new DaoException(e);

		} finally {
			try {
				if (null != rs) {
					rs.close();
				}
				if (null != psTicket) {
					psTicket.close();
				}
				if (null != psFlight) {
					psFlight.close();
				}
				con.setAutoCommit(true);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
			connectionPool.realese(con);
		}

		return ticket;
	}

	@Override
	public BigDecimal getUserBalance(int clientId) throws DaoException {

		BigDecimal userBalance = null;

		String sqlGetUserBalance = "SELECT balance FROM client WHERE id = '" + clientId + "'";

		ConnectionPool connectionPool = ConnectionPool.getInstance();

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			con = connectionPool.take();
			st = con.prepareStatement(sqlGetUserBalance);
			rs = st.executeQuery(sqlGetUserBalance);
			while (rs.next()) {
				userBalance = rs.getBigDecimal("balance");
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

		return userBalance;
	}

	@Override
	public boolean ticketPayment(BigDecimal price, int clientId, Ticket ticket) throws DaoException {

		// отнять сумму билета из balance

		boolean isChange = false;
		int ticketId;

		ticketId = ticket.getTicketId();

		String sqlChangeBalance = "UPDATE client SET balance = balance - " + price + " WHERE id = " + clientId;
		String sqlChangePaid = "UPDATE ticket SET paid = 'Yes' WHERE ticket_id = " + ticketId;

		ConnectionPool connectionPool = ConnectionPool.getInstance();

		Connection con = null;
		Statement stBalance = null;
		Statement stIsPaid = null;

		try {
			con = connectionPool.take();
			con.setAutoCommit(false);
			stBalance = con.prepareStatement(sqlChangeBalance);
			stBalance.executeUpdate(sqlChangeBalance);
			stIsPaid = con.prepareStatement(sqlChangePaid);
			stIsPaid.executeUpdate(sqlChangePaid);
			con.commit();

		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new DaoException(e1);
			}
			throw new DaoException(e);

		} finally {
			try {
				if (null != stBalance) {
					stBalance.close();
				}
				con.setAutoCommit(true);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
			connectionPool.realese(con);
		}

		isChange = true;
		return isChange;
	}
}
