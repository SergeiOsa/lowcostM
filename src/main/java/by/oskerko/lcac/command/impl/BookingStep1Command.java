package by.oskerko.lcac.command.impl;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.oskerko.lcac.bean.Ticket;
import by.oskerko.lcac.bean.User;
import by.oskerko.lcac.command.Command;
import by.oskerko.lcac.controller.JSPPageName;
import by.oskerko.lcac.controller.RequestParameterName;
import by.oskerko.lcac.service.OrderService;
import by.oskerko.lcac.service.ServiceProvider;

public class BookingStep1Command implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int clientId;
		String priorityRegistration;
		String isBaggage;
		int flightNumber;
		BigDecimal price;
		
		User user;
		Ticket ticket = null;
		
		HttpSession session = request.getSession(false);
		user = (User) session.getAttribute("user");
		clientId = user.getId();
		
		priorityRegistration = request.getParameter(RequestParameterName.REQ_PARAM_PRIORITY_REGISTRATION);
		isBaggage = request.getParameter(RequestParameterName.REQ_PARAM_IS_BAGGAGE);
		flightNumber = Integer.valueOf(request.getParameter(RequestParameterName.REQ_PARAM_FLIGHT_NUMBER));
		price = new BigDecimal(request.getParameter(RequestParameterName.REQ_PARAM_PRICE));
		
		OrderService orderService = ServiceProvider.getInstance().getOrderService();
		
		ticket = orderService.bookingStep1(clientId, flightNumber, price, priorityRegistration, isBaggage);	

		RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.BOOKING_2_PAGE);
		session.setAttribute("ticket", ticket);
		dispatcher.forward(request, response);
	}

}
