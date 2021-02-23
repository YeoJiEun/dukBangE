package controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.User;
import model.service.UserManager;

public class UserInfoController implements Controller{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String email = UserSessionUtils.getUserFromSession(request.getSession());
    	String type = (String) request.getSession().getAttribute("type");
    	
		try {
			UserManager manager = UserManager.getInstance(Integer.parseInt(type));
			User user = manager.findUser(email);

			request.setAttribute("userInfo", user);
			
            return "/user/userInfo.jsp";			
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
