package by.oskerko.lcac.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Set;
import java.util.TreeSet;

import by.oskerko.lcac.bean.Report;
import by.oskerko.lcac.bean.User;
import by.oskerko.lcac.dao.DaoException;
import by.oskerko.lcac.dao.UserDao;
import by.oskerko.lcac.dao.connectionpool.ConnectionPool;

public class SQLUserDao implements UserDao {

	@Override
	public User logination(String login, String password) throws DaoException {

		User user = null;

		String sql = "SELECT id, name, surname, role, email FROM client WHERE login = '" + login + "' AND password = '"
				+ password + "'";

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			con = connectionPool.take();
			st = con.prepareStatement(sql);
			rs = st.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String role = rs.getString("role");
				String email = rs.getString("email");
				user = new User(id, name, surname, role, email);
			}
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

		return user;
	}

	@Override
	public User registration(String login, String password, String name, String surname, String email)
			throws DaoException {

		User user = null;

		int newId = 0;
		String newName = null;
		String newSurname = null;
		String newRole = null;
		String newEmail = null;

		String sqlNewUser;
		String sqlGetUser;

		sqlNewUser = "INSERT INTO client (name, surname, login, password, role, email) VALUES ('" + name + "', '"
				+ surname + "', '" + login + "', '" + password + "', 'user', '" + email + "')";
		sqlGetUser = "SELECT id, name, surname, role, email FROM client WHERE login = '" + login + "' AND password = '"
				+ password + "'";

		Connection con = null;
		Statement stNewUser = null;
		Statement stGetUser = null;
		ResultSet rs = null;

		ConnectionPool connectionPool = ConnectionPool.getInstance();
		con = connectionPool.take();
		try {
			stNewUser = con.prepareStatement(sqlNewUser);
			stNewUser.executeUpdate(sqlNewUser);

			stGetUser = con.prepareStatement(sqlGetUser);
			rs = stGetUser.executeQuery(sqlGetUser);

			while (rs.next()) {
				newId = rs.getInt("id");
				newName = rs.getString("name");
				newSurname = rs.getString("surname");
				newRole = rs.getString("role");
				newEmail = rs.getString("email");
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				rs.close();
				stNewUser.close();
				stGetUser.close();
			} catch (SQLException e) {
				throw new DaoException(e);
			}
			connectionPool.realese(con);
		}

		user = new User(newId, newName, newSurname, newRole, newEmail);

		return user;
	}

	@Override
	public boolean editUserProfile(int id, String name, String surname, String email)
			throws DaoException {

		boolean isEdited = false;

		String sqlEditUserProfile = "UPDATE client SET name = '" + name + "', surname = '" + surname + "', email = '"
				+ email + "' WHERE id = " + id;

		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection con = null;
		Statement st = null;

		try {
			con = connectionPool.take();
			st = con.prepareStatement(sqlEditUserProfile);
			int result = st.executeUpdate(sqlEditUserProfile);
			if (result != 0) {
				isEdited = true;
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			if (null != st) {
				try {
					st.close();
				} catch (SQLException e) {
					throw new DaoException(e);
				}
				connectionPool.realese(con);
			}
		}

		return isEdited;
	}

	@Override
	public boolean editUserProfile(int id, String name, String surname, String email, String password,
			String newPassword) throws DaoException {

		boolean isEdited = false;

		String sqlEditUserProfile2 = "UPDATE client SET name = '" + name + "', surname = '" + surname + "', email = '"
				+ email + "', password = '" + newPassword + "' WHERE id = " + id + " AND password = '" + password + "'";

		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection con = null;
		Statement st = null;

		try {
			con = connectionPool.take();
			st = con.prepareStatement(sqlEditUserProfile2);
			int result = st.executeUpdate(sqlEditUserProfile2);
			if (result != 0) {
				isEdited = true;
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			if (null != st) {
				try {
					st.close();
				} catch (SQLException e) {
					throw new DaoException(e);
				}
				connectionPool.realese(con);
			}
		}

		return isEdited;
	}

	@Override
	public User getUser(int id) throws DaoException {

		User user = null;
		String sqlGetUser = "SELECT name, surname, role, email FROM client WHERE id = " + id;

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			con = connectionPool.take();
			st = con.prepareStatement(sqlGetUser);
			rs = st.executeQuery(sqlGetUser);
			while (rs.next()) {
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String role = rs.getString("role");
				String email = rs.getString("email");
				user = new User(id, name, surname, role, email);
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
		return user;
	}

	@Override
	public Set<Report> getReportList(int id) throws DaoException {

		Set<Report> reportList = new TreeSet<>();

		String sqlShowTicketList = "SELECT ticket.ticket_id, flight.departure_place, flight.arrival_place, flight.time_departure,"
				+ " ticket.name, ticket.surname, ticket.priority_registration, ticket.baggage_id, ticket.price"
				+ " FROM ticket JOIN flight ON ticket.flight_number = flight.flight_number"
				+ " WHERE ticket.order_id IN (SELECT ordering.order_id FROM ordering WHERE ordering.client_id = " + id
				+ ");";

		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			con = connectionPool.take();
			st = con.prepareStatement(sqlShowTicketList);
			rs = st.executeQuery(sqlShowTicketList);
			while (rs.next()) {
				int ticketId = rs.getInt("ticket_id");
				String origin = rs.getString("departure_place");
				String destination = rs.getString("arrival_place");
				Timestamp departure = rs.getTimestamp("time_departure");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String priorityRegistration = rs.getString("priority_registration");
				int baggageId = rs.getInt("baggage_id");
				BigDecimal price = rs.getBigDecimal("price");
				Report report = new Report(ticketId, origin, destination, departure, name, surname,
						priorityRegistration, baggageId, price);
				reportList.add(report);
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

		return reportList;
	}

	@Override
	public boolean changePassword(int id, String password, String newPassword) throws DaoException {

		boolean isEdited = false;
				
		String sqlChangePassword = "UPDATE client SET password = '" + newPassword + "' WHERE id = " + id + " AND password = '" + password + "'";

		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection con = null;
		Statement st = null;

		try {
			con = connectionPool.take();
			st = con.prepareStatement(sqlChangePassword);
			int result = st.executeUpdate(sqlChangePassword);
			if (result != 0) {
				isEdited = true;
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			if (null != st) {
				try {
					st.close();
				} catch (SQLException e) {
					throw new DaoException(e);
				}
				connectionPool.realese(con);
			}
		}
		
		return isEdited;
	}

}
