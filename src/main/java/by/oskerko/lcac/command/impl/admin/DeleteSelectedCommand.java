package by.oskerko.lcac.command.impl.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.oskerko.lcac.bean.User;
import by.oskerko.lcac.command.Command;
import by.oskerko.lcac.controller.JSPPageName;
import by.oskerko.lcac.controller.RequestParameterName;
import by.oskerko.lcac.service.AdminService;
import by.oskerko.lcac.service.ServiceException;
import by.oskerko.lcac.service.ServiceProvider;

public class DeleteSelectedCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		User user;
		String[] flightsNumArr;	
		String goToPage;
		boolean isTrue;
		
		HttpSession session = request.getSession(false);
		user = (User) session.getAttribute("user");
		
		if (user.getRole().equalsIgnoreCase("admin")) {
			goToPage = JSPPageName.DELETE_SELECTED_PAGE;
		} else {
			goToPage = JSPPageName.ERROR_PAGE;
		}
		
		flightsNumArr = request.getParameterValues(RequestParameterName.REQ_PARAM_CHECKBOX_TEST);
		
		AdminService adminService = ServiceProvider.getInstance().getAdminService();
		
		try {
			isTrue = adminService.deleteSelectedFlight(flightsNumArr);
			request.setAttribute("isTrue", isTrue);
			RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
			dispatcher.forward(request, response);
			
		} catch (ServiceException e) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSPPageName.ERROR_PAGE);
			requestDispatcher.forward(request, response);
			e.printStackTrace();
		}
		
		
	}

}
