package by.oskerko.lcac.dao;

import by.oskerko.lcac.dao.impl.SQLAdminDao;
import by.oskerko.lcac.dao.impl.SQLOrderDao;
import by.oskerko.lcac.dao.impl.SQLUserDao;

public class DaoProvider {
	
	private static final DaoProvider instance = new DaoProvider();
	private final UserDao userDao = new SQLUserDao();
	private final OrderDao orderDao = new SQLOrderDao();
	private final AdminDao AdminDao = new SQLAdminDao();
	
	private DaoProvider() {}
	
	public static DaoProvider getInstance() {
		return instance;
	}
	
	public UserDao getUserDao() {
		return userDao;
	}
	
	public OrderDao getOrderDao() {
		return orderDao;
	}
	
	public AdminDao getAdminDao() {
		return AdminDao;
	}
	
}
