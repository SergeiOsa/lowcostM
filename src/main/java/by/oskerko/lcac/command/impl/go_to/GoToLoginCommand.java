package by.oskerko.lcac.command.impl.go_to;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.oskerko.lcac.command.Command;
import by.oskerko.lcac.controller.JSPPageName;

public class GoToLoginCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.LOGINATION_PAGE);
		dispatcher.forward(request, response);
		
	}

}
