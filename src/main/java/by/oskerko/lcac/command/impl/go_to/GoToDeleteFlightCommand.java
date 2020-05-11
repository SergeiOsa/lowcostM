package by.oskerko.lcac.command.impl.go_to;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.oskerko.lcac.bean.User;
import by.oskerko.lcac.command.Command;
import by.oskerko.lcac.controller.JSPPageName;

public class GoToDeleteFlightCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		User user;
		String goToPage;
		
		HttpSession session = request.getSession(false);
		user = (User) session.getAttribute("user");
		
		if (user.getRole().equalsIgnoreCase("admin")) {
			goToPage = JSPPageName.DELETE_FLIGHT_PAGE;
		} else {
			goToPage = JSPPageName.ERROR_PAGE;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
		dispatcher.forward(request, response);
	}

}
