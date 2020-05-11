package by.oskerko.lcac.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.oskerko.lcac.command.Command;
import by.oskerko.lcac.controller.JSPPageName;

public class FindFlightCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.INDEX_PAGE);
		dispatcher.forward(request, response);
		
	}
	
}
