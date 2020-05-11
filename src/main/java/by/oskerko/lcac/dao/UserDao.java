package by.oskerko.lcac.dao;

import java.util.Set;

import by.oskerko.lcac.bean.Report;
import by.oskerko.lcac.bean.User;

public interface UserDao {
	
	User logination (String login, String password) throws DaoException;
	User registration (String login, String password, String name, String surname, String email) throws DaoException;
	User getUser(int id)  throws DaoException;
	boolean editUserProfile(int id, String name, String surname, String email) throws DaoException;
	boolean editUserProfile(int id, String name, String surname, String email, String password,
			String newPassword) throws DaoException;
	boolean changePassword(int id, String password, String newPassword) throws DaoException;
	Set<Report> getReportList(int id) throws DaoException;
}
