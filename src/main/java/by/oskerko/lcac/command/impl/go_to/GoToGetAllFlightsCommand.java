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

public class GoToGetAllFlightsCommand implements Command {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		User user;
		String goToPage = null;
		
		HttpSession session = request.getSession(false);
		user = (User) session.getAttribute("user");
		
		if (null == user) {
			goToPage = JSPPageName.ERROR_PAGE;
		}

		if(null != user) {
			goToPage = JSPPageName.ALL_FLIGHTS_PAGE;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
		request.setAttribute("user", user);
		dispatcher.forward(request, response);
	}

}
