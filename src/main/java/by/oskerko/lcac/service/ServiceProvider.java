package by.oskerko.lcac.service;

import by.oskerko.lcac.service.impl.AdminServiceImpl;
import by.oskerko.lcac.service.impl.OrderServiceImpl;
import by.oskerko.lcac.service.impl.UserServiceImpl;

public class ServiceProvider {

	private static final ServiceProvider instance = new ServiceProvider();
	private final UserService userService = new UserServiceImpl();
	private final OrderService orderService = new OrderServiceImpl();
	private final AdminService adminService = new AdminServiceImpl();
	
	private ServiceProvider() {}
	
	public static ServiceProvider getInstance() {
		return instance;
	}
	
	public UserService getUserService() {
		return userService;
	}
	
	public OrderService getOrderService() {
		return orderService;
	}
	
	public AdminService getAdminService() {
		return adminService;
	}
}
