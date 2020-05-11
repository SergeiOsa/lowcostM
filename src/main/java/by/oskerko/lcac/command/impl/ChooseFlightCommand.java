package by.oskerko.lcac.command.impl;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.oskerko.lcac.bean.PreOrder;
import by.oskerko.lcac.command.Command;
import by.oskerko.lcac.controller.JSPPageName;
import by.oskerko.lcac.controller.RequestParameterName;
import by.oskerko.lcac.service.OrderService;
import by.oskerko.lcac.service.ServiceException;
import by.oskerko.lcac.service.ServiceProvider;


public class ChooseFlightCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		PreOrder preOrder = null;
		int flightNumber;
		BigDecimal price;
				
		flightNumber = Integer.valueOf(request.getParameter(RequestParameterName.REQ_PARAM_FLIGHT_NUMBER));
		price = new BigDecimal(request.getParameter(RequestParameterName.REQ_PARAM_PRICE));

		OrderService orderService = ServiceProvider.getInstance().getOrderService();
		
		try {
			preOrder = orderService.chooseFlight(flightNumber, price);
		} catch (ServiceException e) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSPPageName.ERROR_PAGE);
			requestDispatcher.forward(request, response);
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.BOOKING_PAGE);
		request.setAttribute("preOrder", preOrder);
		dispatcher.forward(request, response);
		
	}

}
