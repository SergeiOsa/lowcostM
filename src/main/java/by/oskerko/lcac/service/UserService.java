package by.oskerko.lcac.service;

import java.util.List;

import by.oskerko.lcac.bean.Report;
import by.oskerko.lcac.bean.User;

public interface UserService {

	User logination(String login, String password) throws ServiceException;
	User registration(String login, String password, String name, String surname, String email) throws ServiceException;
	User editUserProfile(int id, String name, String surname, String email) throws ServiceException;
	boolean changePassword(int id, String password, String newPassword) throws ServiceException;
	List<Report> getReportList(int id)  throws ServiceException;
}
