package by.oskerko.lcac.command.impl;

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
import by.oskerko.lcac.service.ServiceException;
import by.oskerko.lcac.service.ServiceProvider;
import by.oskerko.lcac.service.UserService;

public class LoginationCommand implements Command {
		
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
				
		String login;
		String password;
				
		login = request.getParameter(RequestParameterName.REQ_PARAM_LOGIN);
		password = request.getParameter(RequestParameterName.REQ_PARAM_PASS);

		UserService userService = ServiceProvider.getInstance().getUserService();

		User user = null;

		String goToPage = null;

		HttpSession session;
		session = request.getSession(true);
		
		try {
			user = userService.logination(login, password);
			if (null != user) {
				session.setAttribute("user", user);
				goToPage = "/WEB-INF/jsp/auth_user.jsp";
			} else {
				request.setAttribute("loginErrorMessege", "Incorrect login or password");
				goToPage = "logination.jsp";
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
			request.setAttribute("user", user);
			dispatcher.forward(request, response);

		} catch (ServiceException e) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSPPageName.ERROR_PAGE);
			requestDispatcher.forward(request, response);
			e.printStackTrace();
		}

	}

}
