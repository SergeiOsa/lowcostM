package by.oskerko.lcac.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.oskerko.lcac.bean.Flight;
import by.oskerko.lcac.bean.Ticket;
import by.oskerko.lcac.command.Command;
import by.oskerko.lcac.controller.JSPPageName;
import by.oskerko.lcac.controller.RequestParameterName;
import by.oskerko.lcac.service.OrderService;
import by.oskerko.lcac.service.ServiceException;
import by.oskerko.lcac.service.ServiceProvider;

public class BookingStep2Command implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String name;
		String surname;
		String passport;
		Ticket ticket;
		Flight flight = null;
		int flightNumber;
		
		HttpSession session = request.getSession(false);
		
		name = request.getParameter(RequestParameterName.REQ_PARAM_NAME);
		surname = request.getParameter(RequestParameterName.REQ_PARAM_SURNAME);
		passport = request.getParameter(RequestParameterName.REQ_PARAM_PASSPORT);
		ticket = (Ticket)session.getAttribute(RequestParameterName.REQ_PARAM_TICKET);
		
		OrderService orderService = ServiceProvider.getInstance().getOrderService();
		ticket = orderService.bookingStep2(ticket, name, surname, passport);
		flightNumber = ticket.getFlightNumber();
		try {
			flight = orderService.getFlight(flightNumber);
		} catch (ServiceException e) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSPPageName.ERROR_PAGE);
			requestDispatcher.forward(request, response);
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.CHECK_BOOKING_PAGE);
		request.setAttribute("ticket", ticket);
		request.setAttribute("flight", flight);
		dispatcher.forward(request, response);
	}

}
