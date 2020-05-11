package by.oskerko.lcac.dao;

public class DaoException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public DaoException() {
	}

	public DaoException(String messege) {
		super(messege);
	}

	public DaoException(Exception e) {
		super(e);
	}

	public DaoException(String messege, Exception e) {
		super(messege, e);
	}
}
