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

public class EditUserProfileCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		User user;
		int id;
		String name;
		String surname;
		String email;
		
		boolean isSuccessful;

		HttpSession session = request.getSession(false);
		user = (User) session.getAttribute("user");
		id = user.getId();

		name = request.getParameter(RequestParameterName.REQ_PARAM_NAME);
		surname = request.getParameter(RequestParameterName.REQ_PARAM_SURNAME);
		email = request.getParameter(RequestParameterName.REQ_PARAM_EMAIL);

		UserService userService = ServiceProvider.getInstance().getUserService();
			try {
				user = userService.editUserProfile(id, name, surname, email);
			} catch (ServiceException e) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSPPageName.ERROR_PAGE);
				requestDispatcher.forward(request, response);
				e.printStackTrace();
			}
			
			if (user != null) {
				isSuccessful = true;
				session.setAttribute("user", user);
			} else {
				isSuccessful = false;
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.EDIT_USER_PROFILE_PAGE);
			request.setAttribute("isSuccessful", isSuccessful);
			dispatcher.forward(request, response);
	}

}
