package by.oskerko.lcac.dao.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import by.oskerko.lcac.dao.DaoException;

public class ConnectionPool {

	private static final ConnectionPool instance = new ConnectionPool();

	private DBResourceManager dbrm = DBResourceManager.getInstance();
	private int poolSize = Integer.valueOf(dbrm.getValue(DBParameter.POOL_SIZE));
	private String driver = dbrm.getValue(DBParameter.DB_DRIVER);
	private String url = dbrm.getValue(DBParameter.DB_URL);
	private String login = dbrm.getValue(DBParameter.DB_LOGIN);
	private String password = dbrm.getValue(DBParameter.DB_PASSWORD);

	private BlockingQueue<Connection> connections = new ArrayBlockingQueue<Connection>(poolSize);

	private ConnectionPool () {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			// не подумал пока как быть с этими excep
			e1.printStackTrace();
		}

		for (int i = 0; i < poolSize; i++) {
			try {
				connections.add(DriverManager.getConnection(url, login, password));
			} catch (SQLException e) {
				// не подумал пока как быть с этими excep
				e.printStackTrace();
			}
		}
		
	}
	
	public static ConnectionPool getInstance() {
		return instance;
	}

	public Connection take() throws DaoException { // можем вместо конекта вернуть налл. это не правильно
		Connection con = null;
		try {
			con = connections.take();
		} catch (InterruptedException e) {
			throw new DaoException(e);
		}
		return con;
	}

	public void realese(Connection con) throws DaoException { // здесь можем не вернуть ничего в лист. не правильно это
																// Сергей Александрович
		if (con != null) {
			try {
				con.setAutoCommit(true);
				connections.add(con);
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
	}
	
}
