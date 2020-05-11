package by.oskerko.lcac.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.oskerko.lcac.command.Command;
import by.oskerko.lcac.controller.RequestParameterName;

public class LocalizationWebInfCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String local = request.getParameter(RequestParameterName.REQ_PARAM_LOCAL);
		String path = request.getParameter(RequestParameterName.REQ_PARAM_PATH);
		
		HttpSession session = request.getSession(false);
		session.setAttribute("local", local);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

}
