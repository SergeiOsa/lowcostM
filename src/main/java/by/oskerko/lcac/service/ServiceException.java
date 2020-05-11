package by.oskerko.lcac.service;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public ServiceException() {
	}

	public ServiceException(String messege) {
		super(messege);
	}

	public ServiceException(Exception e) {
		super(e);
	}

	public ServiceException(String messege, Exception e) {
		super(messege, e);
	}

}
