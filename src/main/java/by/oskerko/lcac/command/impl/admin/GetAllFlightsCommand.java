package by.oskerko.lcac.command.impl.admin;

import java.io.IOException;
import java.util.Set;

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

public class GetAllFlightsCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String strDeparture;
		Set<Flight> flightsSet = null;
		User user;
		String goToPage;
		
		HttpSession session = request.getSession(false);
		user = (User) session.getAttribute("user");
		
		if(user.getRole().equalsIgnoreCase("admin")) {

			goToPage = "/WEB-INF/jsp/admin/all_flights.jsp";
		} else {
			goToPage = "/error_page.jsp";
		}
		
		strDeparture = request.getParameter(RequestParameterName.REQ_PARAM_DEPARTURE);
		
		AdminService adminService = ServiceProvider.getInstance().getAdminService();
		
		try {
			flightsSet = adminService.getAllFlights(strDeparture);
			boolean isSuccessful = true; //нужно тру засунуть в дао или сервис
			RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
			request.setAttribute("flightsSet", flightsSet);
			request.setAttribute("isSuccessful", isSuccessful);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSPPageName.ERROR_PAGE);
			requestDispatcher.forward(request, response);
			e.printStackTrace();
		}
		
	}

}
