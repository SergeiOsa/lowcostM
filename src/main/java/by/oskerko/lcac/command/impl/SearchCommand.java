package by.oskerko.lcac.command.impl;

import java.io.IOException;
import java.util.Set;

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

public class SearchCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String origin;
		String destination;
		String strDeparture;

		origin = request.getParameter(RequestParameterName.REQ_PARAM_ORIGIN);
		destination = request.getParameter(RequestParameterName.REQ_PARAM_DESTINATION);
		strDeparture = request.getParameter(RequestParameterName.REQ_PARAM_DEPARTURE);
		
		OrderService flightService = ServiceProvider.getInstance().getOrderService();

		Set<PreOrder> preOrdersSet = null;

		try {
			preOrdersSet = flightService.search(origin, destination, strDeparture);

			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.FOUND_FLIGHTS_PAGE);
			request.setAttribute("preOrdersSet", preOrdersSet);
			dispatcher.forward(request, response);

		} catch (ServiceException e) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSPPageName.ERROR_PAGE);
			requestDispatcher.forward(request, response);
			e.printStackTrace();
		}

	}

}
