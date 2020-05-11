package by.oskerko.lcac.command.impl.admin;

import java.io.IOException;
import java.util.List;

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
import by.oskerko.lcac.service.AdminService;
import by.oskerko.lcac.service.ServiceException;
import by.oskerko.lcac.service.ServiceProvider;

public class GetTicketInfoCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		User user;
		List<Ticket> ticketsList = null;
		int flightNumber;
		String goToPage;
		
		HttpSession session = request.getSession(false);
		user = (User) session.getAttribute("user");
		if (user.getRole().equalsIgnoreCase("admin")) {
			goToPage =  "/WEB-INF/jsp/admin/ticket_info.jsp";
		} else {
			goToPage = "/error_page.jsp";
		}
		
		AdminService adminService = ServiceProvider.getInstance().getAdminService();
		
		try {
			flightNumber = Integer.valueOf(request.getParameter(RequestParameterName.REQ_PARAM_FLIGHT_NUMBER));
			ticketsList = adminService.getTicketInfo(flightNumber);
			request.setAttribute("flightNumber", flightNumber);
			request.setAttribute("ticketsList", ticketsList);
			RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSPPageName.ERROR_PAGE);
			requestDispatcher.forward(request, response);
			e.printStackTrace();
		}
		
	}

}
