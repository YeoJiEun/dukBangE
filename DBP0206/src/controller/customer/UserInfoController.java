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
			/* UserNotFoundException이나 PasswordMismatchException 발생 시
			 * 다시 login form을 사용자에게 전송하고 오류 메세지도 출력
			 */
            request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
            return "/user/loginForm.jsp";			
		}	
	}
}
