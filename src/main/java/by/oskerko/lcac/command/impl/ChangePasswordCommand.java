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

public class ChangePasswordCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		User user;
		int id;
		String password;
		String newPassword;
		String newPassword2;

		HttpSession session = request.getSession(false);
		user = (User) session.getAttribute("user");
		id = user.getId();

		password = request.getParameter(RequestParameterName.REQ_PARAM_PASS);
		newPassword = request.getParameter(RequestParameterName.REQ_PARAM_NEW_PASS);
		newPassword2 = request.getParameter(RequestParameterName.REQ_PARAM_NEW_PASS2);

		if (newPassword.equals(newPassword2)) {
			UserService userService = ServiceProvider.getInstance().getUserService();
			try {
				boolean isSuccessful = userService.changePassword(id, password, newPassword);
				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.CHANGE_PASSWORD_PAGE);
				request.setAttribute("isSuccessful", isSuccessful);
				dispatcher.forward(request, response);
			} catch (ServiceException e) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSPPageName.ERROR_PAGE);
				requestDispatcher.forward(request, response);
				e.printStackTrace();
			}
		} else {
			boolean wrongPass = true;
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.CHANGE_PASSWORD_PAGE);
			request.setAttribute("wrongPass", wrongPass);
			dispatcher.forward(request, response);
		}
	}

}
