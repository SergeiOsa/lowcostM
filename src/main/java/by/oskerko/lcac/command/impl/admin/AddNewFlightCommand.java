package by.oskerko.lcac.command.impl.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.oskerko.lcac.bean.Flight;
import by.oskerko.lcac.bean.User;
import by.oskerko.lcac.command.Command;
import by.oskerko.lcac.controller.JSPPageName;
import by.oskerko.lcac.controller.RequestParameterName;
import by.oskerko.lcac.service.AdminService;
import by.oskerko.lcac.service.ServiceException;
import by.oskerko.lcac.service.ServiceProvider;

public class AddNewFlightCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {		
		
		User user;
		String goToPage;
		
		Flight flight;
		String origin;
		String destination;
		String timeDeparture;
		String timeArrival;
		String numberOfSeats;
		String emptySeats;
		String distance;
		
		HttpSession session = request.getSession(false);
		user = (User) session.getAttribute("user");
		
		if (user.getRole().equalsIgnoreCase("admin")) {
			goToPage = JSPPageName.NEW_FLIGHT_REPORT_PAGE;
		} else {
			goToPage = JSPPageName.ERROR_PAGE;
		}
		
		origin = request.getParameter(RequestParameterName.REQ_PARAM_ORIGIN);
		destination = request.getParameter(RequestParameterName.REQ_PARAM_DESTINATION);
		timeDeparture = request.getParameter(RequestParameterName.REQ_PARAM_TIME_DEPARTURE);
		timeArrival = request.getParameter(RequestParameterName.REQ_PARAM_TIME_ARRIVAL);
		numberOfSeats = request.getParameter(RequestParameterName.REQ_PARAM_NUMBER_OF_SEATS);
		emptySeats = request.getParameter(RequestParameterName.REQ_PARAM_EMPTY_SEATS);
		distance = request.getParameter(RequestParameterName.REQ_PARAM_DISTANCE);
		
		AdminService adminService = ServiceProvider.getInstance().getAdminService();
		
		try {
			flight = adminService.addNewFlight (origin, destination, timeDeparture, timeArrival,
					numberOfSeats, emptySeats, distance);
			request.setAttribute("flight", flight);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(goToPage);
			requestDispatcher.forward(request, response);
		} catch (ServiceException e) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSPPageName.ERROR_PAGE);
			requestDispatcher.forward(request, response);
			e.printStackTrace();
		}
		
	}

}
