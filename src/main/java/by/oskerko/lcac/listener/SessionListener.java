package by.oskerko.lcac.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class SessionListener implements ServletRequestListener {

	public void requestInitialized(ServletRequestEvent event) {
		System.out.println("я лиснер. я - пустышка");
		
	}

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("я лиснер. я - пустышка. метод: void requestDestroyed");
		
	}
	
	/*
	 * @Override public void sessionCreated(HttpSessionEvent event) {
	 * 
	 * HttpSession session = event.getSession(); event.get String local =
	 * request.getParameter(RequestParameterName.REQ_PARAM_LOCAL);
	 * session.setAttribute(local, "local");
	 * 
	 * }
	 */
}