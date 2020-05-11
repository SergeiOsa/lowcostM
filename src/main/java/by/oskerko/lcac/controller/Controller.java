package by.oskerko.lcac.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import by.oskerko.lcac.command.Command;
import by.oskerko.lcac.command.CommandProvider;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final CommandProvider commandProvider = CommandProvider.getInstance();
	
	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String commandName;
		Command command;
		
		commandName = request.getParameter(RequestParameterName.REQ_PARAM_COMMAND_NAME);
		command = commandProvider.getCommand(commandName);

		command.execute(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String commandName;
		Command command;
		
		commandName = request.getParameter(RequestParameterName.REQ_PARAM_COMMAND_NAME);
		command = commandProvider.getCommand(commandName);

		command.execute(request, response);

	}

}
