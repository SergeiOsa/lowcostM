package by.oskerko.lcac.command.impl;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.oskerko.lcac.bean.Flight;
import by.oskerko.lcac.bean.Ticket;
import by.oskerko.lcac.bean.User;
import by.oskerko.lcac.command.Command;
import by.oskerko.lcac.controller.JSPPageName;
import by.oskerko.lcac.service.OrderService;
import by.oskerko.lcac.service.ServiceException;
import by.oskerko.lcac.service.ServiceProvider;

public class PaymentCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Ticket ticket;
		User user;
		Flight flight = null;
		BigDecimal price;
		int clientId;
		int flightNumber;

		boolean paymentStatus = false;

		String goToPage;

		HttpSession session = request.getSession(false);
		user = (User) session.getAttribute("user");
		clientId = user.getId();
		ticket = (Ticket) session.getAttribute("ticket");
		price = ticket.getPrice();
		flightNumber = ticket.getFlightNumber();

		OrderService orderService = ServiceProvider.getInstance().getOrderService();
		try {
			flight = orderService.getFlight(flightNumber);
			paymentStatus = orderService.payment(price, clientId, ticket);
		} catch (ServiceException e) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSPPageName.ERROR_PAGE);
			requestDispatcher.forward(request, response);
			e.printStackTrace();
		}

		System.out.println(paymentStatus);
		if (paymentStatus == false) {
			goToPage = JSPPageName.PAYMENT_PAGE;
		} else {
			goToPage = JSPPageName.PAY_REPORT_PAGE;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
		request.setAttribute("flight", flight);
		request.setAttribute("paymentStatus", paymentStatus);
		request.setAttribute("ticket", ticket);
		dispatcher.forward(request, response);

	}

}
