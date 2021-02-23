package controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.customer.UserSessionUtils;
import model.Agent;
import model.Customer;
import model.User;
import model.service.UserManager;

public class LoginController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	User user = new User();
    	
    	if (request.getMethod().equals("GET")) {
    		return "redirect:/user/loginForm.jsp";		// login form ��û���� redirect
        }
    	
    	String email = request.getParameter("ID");
		String password = request.getParameter("PW");
		String type = request.getParameter("userType");
		try {
			UserManager manager = UserManager.getInstance(Integer.parseInt(type));
			user = manager.login(email, password);
				
			// ���ǿ� ����� ���̵� ����
			HttpSession session = request.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, email);
            session.setAttribute("userNo", user.getNO());
            session.setAttribute("type", type);
            if(user instanceof Customer) {
				Customer c = (Customer)user;
				session.setAttribute("who", c);
			}
			else if(user instanceof Agent) {
				Agent a = (Agent)user;
				session.setAttribute("who", a);
			}
            
            return "/user/main";			
		} catch (Exception e) {
			/* UserNotFoundException�̳� PasswordMismatchException �߻� ��
			 * �ٽ� login form�� ����ڿ��� �����ϰ� ���� �޼����� ���
			 */
            request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
            return "/user/loginForm.jsp";			
		}	
		
        
    }
}
