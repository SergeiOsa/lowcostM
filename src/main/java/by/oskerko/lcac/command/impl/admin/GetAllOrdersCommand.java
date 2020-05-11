package by.oskerko.lcac.command.impl.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.oskerko.lcac.bean.Order;
import by.oskerko.lcac.bean.User;
import by.oskerko.lcac.command.Command;
import by.oskerko.lcac.controller.JSPPageName;
import by.oskerko.lcac.controller.RequestParameterName;
import by.oskerko.lcac.service.AdminService;
import by.oskerko.lcac.service.ServiceException;
import by.oskerko.lcac.service.ServiceProvider;

public class GetAllOrdersCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		User user;
		List<Order> ordersList;
		String goToPage;
		String strDateFrom;
		String strDateTo;
		
		HttpSession session = request.getSession(false);
		user = (User) session.getAttribute("user");
		
		strDateFrom = request.getParameter(RequestParameterName.REQ_PARAM_DATE_FROM);
		strDateTo = request.getParameter(RequestParameterName.REQ_PARAM_DATE_TO);
		
		if(user.getRole().equalsIgnoreCase("admin")) {
			goToPage = "/WEB-INF/jsp/admin/all_orders.jsp";
		} else {
			goToPage = "/error_page.jsp";
		}
		
		AdminService adminService = ServiceProvider.getInstance().getAdminService();
		
		try {
			ordersList = adminService.getAllOrders(strDateFrom, strDateTo);
			boolean isSuccessful = true;
			request.setAttribute("ordersList", ordersList);
			request.setAttribute("isSuccessful", isSuccessful);
			RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSPPageName.ERROR_PAGE);
			requestDispatcher.forward(request, response);
			e.printStackTrace();
		}
		
	}

}
