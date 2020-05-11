package by.oskerko.lcac.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.oskerko.lcac.bean.Report;
import by.oskerko.lcac.bean.User;
import by.oskerko.lcac.command.Command;
import by.oskerko.lcac.controller.JSPPageName;
import by.oskerko.lcac.service.ServiceException;
import by.oskerko.lcac.service.ServiceProvider;
import by.oskerko.lcac.service.UserService;

public class GetReportCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		User user;
		int id;
		List<Report> reportList = null;

		HttpSession session = request.getSession(false);
		user = (User) session.getAttribute("user");
		id = user.getId();

		UserService userService = ServiceProvider.getInstance().getUserService();
		try {
			reportList = userService.getReportList(id);
		} catch (ServiceException e) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSPPageName.ERROR_PAGE);
			requestDispatcher.forward(request, response);
			e.printStackTrace();
		}
		
		request.setAttribute("reportList", reportList);
		RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.REPORT_PAGE);
		dispatcher.forward(request, response);
	}

}
