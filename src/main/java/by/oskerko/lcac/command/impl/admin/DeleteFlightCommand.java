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
import by.oskerko.lcac.service.AdminService;
import by.oskerko.lcac.service.ServiceException;
import by.oskerko.lcac.service.ServiceProvider;

public class DeleteFlightCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		User user;
		String goToPage;
		Set<Flight> flightsSet;
		String strDateFrom;
		String strDateTo;
		int[] flightsArr = new int[0];
		
		HttpSession session = request.getSession(false);
		user = (User) session.getAttribute("user");
		
		strDateFrom = request.getParameter("dateFrom");
		strDateTo = request.getParameter("dateTo");
		
		if (user.getRole().equalsIgnoreCase("admin")) {
			goToPage = JSPPageName.DELETE_FLIGHT_PAGE;
		} else {
			goToPage = JSPPageName.ERROR_PAGE;
		}
		
		AdminService adminService = ServiceProvider.getInstance().getAdminService();
		try {
			flightsSet = adminService.getAllFlights(strDateFrom, strDateTo);
			boolean isSuccessful = true;
			request.setAttribute("flightsArr", flightsArr);
			request.setAttribute("isSuccessful", isSuccessful);
			request.setAttribute("flightsSet", flightsSet);
			RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSPPageName.ERROR_PAGE);
			requestDispatcher.forward(request, response);
			e.printStackTrace();
		}
	}

}
