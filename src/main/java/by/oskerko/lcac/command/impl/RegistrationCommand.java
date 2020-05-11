package by.oskerko.lcac.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.oskerko.lcac.bean.User;
import by.oskerko.lcac.command.Command;
import by.oskerko.lcac.controller.JSPPageName;
import by.oskerko.lcac.controller.RequestParameterName;
import by.oskerko.lcac.service.ServiceException;
import by.oskerko.lcac.service.ServiceProvider;
import by.oskerko.lcac.service.UserService;

public class RegistrationCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String login;
		String password;
		String name;
		String surname;
		String email;

		login = request.getParameter(RequestParameterName.REQ_PARAM_LOGIN);
		password = request.getParameter(RequestParameterName.REQ_PARAM_PASS);
		name = request.getParameter(RequestParameterName.REQ_PARAM_NAME);
		surname = request.getParameter(RequestParameterName.REQ_PARAM_SURNAME);
		email = request.getParameter(RequestParameterName.REQ_PARAM_EMAIL);

		UserService userService = ServiceProvider.getInstance().getUserService();

		User user = null;
		
		try {
			user = userService.registration(login, password, name, surname, email);
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.AUTH_USER_PAGE);
			request.setAttribute("user", user);
			dispatcher.forward(request, response);

		} catch (ServiceException e) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSPPageName.ERROR_PAGE);
			requestDispatcher.forward(request, response);
			e.printStackTrace();
		}
		
	}
	
}
