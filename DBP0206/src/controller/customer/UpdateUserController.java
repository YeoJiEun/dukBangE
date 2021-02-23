package controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Agent;
import model.Customer;
import model.User;
import model.service.ExistingException;
import model.service.ExistingUserException;
import model.service.UserManager;
import model.service.UserNotFoundException;

public class UpdateUserController implements Controller{
	
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User a= (User)request.getSession().getAttribute("who");
		String type = a.getUSER_NO();
		String change_pw = (String)request.getParameter("password");
		String change_phone = (String)request.getParameter("phone");
		String change_addr = (String)request.getParameter("addr");
		UserManager manager = UserManager.getInstance(Integer.parseInt(type));
		//User user;
		System.out.println(type);
		Customer user_c = null;
		Agent user_a = null;
		if(type.equals("2")) {
		 user_a = (Agent)request.getSession().getAttribute("who");
		 System.out.println("agent Á¤º¸ ¹Þ¾Æ¿È\n");
		 user_a.setADDR(change_addr);
		 user_a.setPHONE(change_phone);
		 user_a.setPWD(change_pw); 
		}
		else {
			user_c = (Customer)request.getSession().getAttribute("who");
			 System.out.println("Customer Á¤º¸ ¹Þ¾Æ¿È\n");
			 System.out.println(user_c.getPWD()+ user_c.getPHONE());
			 user_c.setADDR(change_addr);
			 user_c.setPHONE(change_phone);
			 user_c.setPWD(change_pw); 
		}
		if(type.equals("2")) 
			manager.update(user_a);
		else if(type.equals("1"))
			manager.update(user_c);
		return "/user/main.jsp";
	
	 }
}
