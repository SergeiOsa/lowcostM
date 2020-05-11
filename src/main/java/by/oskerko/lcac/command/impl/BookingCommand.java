package by.oskerko.lcac.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.oskerko.lcac.bean.Flight;
import by.oskerko.lcac.bean.Order;
import by.oskerko.lcac.bean.Ticket;
import by.oskerko.lcac.bean.User;
import by.oskerko.lcac.command.Command;
import by.oskerko.lcac.controller.JSPPageName;
import by.oskerko.lcac.controller.RequestParameterName;
import by.oskerko.lcac.service.OrderService;
import by.oskerko.lcac.service.ServiceException;
import by.oskerko.lcac.service.ServiceProvider;

public class BookingCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		User user;
		Order order = null;
		Ticket ticket;
		Flight flight = null;
		int clientId;
		int flightNumber;
		int orderId;
		
		HttpSession session = request.getSession(false);
		
		user = (User) session.getAttribute(RequestParameterName.REQ_PARAM_USER);
		ticket = (Ticket) session.getAttribute(RequestParameterName.REQ_PARAM_TICKET);
		clientId = user.getId();
		flightNumber = ticket.getFlightNumber();
		
		OrderService orderService = ServiceProvider.getInstance().getOrderService();
		try {
			flight = orderService.getFlight(flightNumber);
			order = orderService.booking(ticket, clientId);
			orderId = order.getOrderId();
			ticket.setOrderId(orderId);
			
		} catch (ServiceException e) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSPPageName.ERROR_PAGE);
			requestDispatcher.forward(request, response);
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.PAYMENT_PAGE);
		request.setAttribute("order", order);
		request.setAttribute("flight", flight);
		dispatcher.forward(request, response);
		
	}

}
