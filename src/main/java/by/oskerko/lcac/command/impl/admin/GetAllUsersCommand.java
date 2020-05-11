package by.oskerko.lcac.command.impl.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.oskerko.lcac.bean.User;
import by.oskerko.lcac.command.Command;
import by.oskerko.lcac.controller.JSPPageName;
import by.oskerko.lcac.service.AdminService;
import by.oskerko.lcac.service.ServiceException;
import by.oskerko.lcac.service.ServiceProvider;

public class GetAllUsersCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		User user;
		String goToPage;
		List<User> usersList;
		
		HttpSession session = request.getSession(false);
		user = (User) session.getAttribute("user");
		
		if(user.getRole().equalsIgnoreCase("admin")) {
			goToPage = "/WEB-INF/jsp/admin/all_users.jsp";
		} else {
			goToPage = "/error_page.jsp";
		}
		
		AdminService adminService = ServiceProvider.getInstance().getAdminService();
		
		try {
			usersList = adminService.getAllUsers();
			boolean isSuccessful = true;
			request.setAttribute("isSuccessful", isSuccessful);
			request.setAttribute("usersList", usersList);
			RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSPPageName.ERROR_PAGE);
			requestDispatcher.forward(request, response);
			e.printStackTrace();
		}
		
	}

}
