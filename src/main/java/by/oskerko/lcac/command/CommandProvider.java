package by.oskerko.lcac.command;

import java.util.HashMap;
import java.util.Map;

import by.oskerko.lcac.command.impl.BookingCommand;
import by.oskerko.lcac.command.impl.BookingStep1Command;
import by.oskerko.lcac.command.impl.BookingStep2Command;
import by.oskerko.lcac.command.impl.ChangePasswordCommand;
import by.oskerko.lcac.command.impl.ChooseFlightCommand;
import by.oskerko.lcac.command.impl.EditUserProfileCommand;
import by.oskerko.lcac.command.impl.FindFlightCommand;
import by.oskerko.lcac.command.impl.LoginationCommand;
import by.oskerko.lcac.command.impl.LogoutCommand;
import by.oskerko.lcac.command.impl.NoSuchCommand;
import by.oskerko.lcac.command.impl.PaymentCommand;
import by.oskerko.lcac.command.impl.RegistrationCommand;
import by.oskerko.lcac.command.impl.SearchCommand;
import by.oskerko.lcac.command.impl.admin.AddNewFlightCommand;
import by.oskerko.lcac.command.impl.admin.DeleteFlightCommand;
import by.oskerko.lcac.command.impl.admin.DeleteSelectedCommand;
import by.oskerko.lcac.command.impl.admin.GetAllFlightsCommand;
import by.oskerko.lcac.command.impl.admin.GetAllOrdersCommand;
import by.oskerko.lcac.command.impl.admin.GetAllUsersCommand;
import by.oskerko.lcac.command.impl.admin.GetTicketInfoCommand;
import by.oskerko.lcac.command.impl.go_to.GoToAddNewFlightCommand;
import by.oskerko.lcac.command.impl.go_to.GoToAuthUserCommand;
import by.oskerko.lcac.command.impl.go_to.GoToChangePasswordCommand;
import by.oskerko.lcac.command.impl.go_to.GoToDeleteFlightCommand;
import by.oskerko.lcac.command.impl.go_to.GoToEditUserProfileCommand;
import by.oskerko.lcac.command.impl.go_to.GoToGetAllFlightsCommand;
import by.oskerko.lcac.command.impl.go_to.GoToGetAllOrdersCommand;
import by.oskerko.lcac.command.impl.go_to.GoToLoginCommand;
import by.oskerko.lcac.command.impl.GetReportCommand;
import by.oskerko.lcac.command.impl.LocalizationCommand;
import by.oskerko.lcac.command.impl.LocalizationWebInfCommand;

public class CommandProvider {

	 private static final CommandProvider instance = new CommandProvider(); 
	 
	  private Map<CommandName, Command> commands = new HashMap<>(); 
	 
	  public CommandProvider() { 
	    commands.put(CommandName.LOGINATION, new LoginationCommand());
	    commands.put(CommandName.REGISTRATION, new RegistrationCommand());
	    commands.put(CommandName.LOGOUT, new LogoutCommand());
	    commands.put(CommandName.SEARCH, new SearchCommand());
	    commands.put(CommandName.CHOOSE_FLIGHT, new ChooseFlightCommand());
	    commands.put(CommandName.BOOKING_STEP_1, new BookingStep1Command());
	    commands.put(CommandName.BOOKING_STEP_2, new BookingStep2Command());
	    commands.put(CommandName.BOOKING, new BookingCommand());
	    commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand()); 
	    commands.put(CommandName.PAYMENT, new PaymentCommand());
	    commands.put(CommandName.GO_TO_AUTH_USER, new GoToAuthUserCommand());
	    commands.put(CommandName.FIND_FLIGHT, new FindFlightCommand());
	    commands.put(CommandName.GO_TO_LOGIN, new GoToLoginCommand());
	    commands.put(CommandName.GO_TO_EDIT_USER_PROFILE, new GoToEditUserProfileCommand());
	    commands.put(CommandName.EDIT_USER_PROFILE, new EditUserProfileCommand());
	    commands.put(CommandName.GO_TO_CHANGE_PASSWORD, new GoToChangePasswordCommand());
	    commands.put(CommandName.CHANGE_PASSWORD, new ChangePasswordCommand());	    
	    commands.put(CommandName.GET_REPORT, new GetReportCommand());
	    commands.put(CommandName.GET_ALL_USERS, new GetAllUsersCommand());
	    commands.put(CommandName.GO_TO_GET_ALL_FLIGHTS, new GoToGetAllFlightsCommand());
	    commands.put(CommandName.GET_ALL_FLIGHTS, new GetAllFlightsCommand());
	    commands.put(CommandName.GO_TO_GET_ALL_ORDERS, new GoToGetAllOrdersCommand());
	    commands.put(CommandName.GET_ALL_ORDERS, new GetAllOrdersCommand());
	    commands.put(CommandName.ADD_NEW_FLIGHT, new AddNewFlightCommand());
	    commands.put(CommandName.GO_TO_ADD_NEW_FLIGHT, new GoToAddNewFlightCommand());
	    commands.put(CommandName.GO_TO_DELETE_FLIGHT, new GoToDeleteFlightCommand());
	    commands.put(CommandName.DELETE_FLIGHT, new DeleteFlightCommand());
	    commands.put(CommandName.DELETE_SELECTED, new DeleteSelectedCommand());
	    commands.put(CommandName.GET_TICKET_INFO, new GetTicketInfoCommand());
	    commands.put(CommandName.LOCALIZATION, new LocalizationCommand());
	    commands.put(CommandName.LOCALIZATION_WEB_INF, new LocalizationWebInfCommand());

	  } 
	 
	  public static CommandProvider getInstance() { 
	    return instance; 
	  } 
	   
	  public Command getCommand(String commandName){ 
	    CommandName name = CommandName.valueOf(commandName.toUpperCase()); 
	    Command command; 
	    if ( null != name){ 
	      command = commands.get(name); 
	    } else{ 
	      command = commands.get(CommandName.NO_SUCH_COMMAND); 
	    } 
	    return command;     
	  } 
}
