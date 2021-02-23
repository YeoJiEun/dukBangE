package controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Agent;
import model.Customer;
import model.User;
import model.service.ExistingUserException;
import model.service.UserManager;

public class RegisterUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String type = request.getParameter("type");
    	
    	User user;
    	if(type.equals("2")) {
    		user = new Agent(
    				request.getParameter("type"),
    				request.getParameter("name"),
    				request.getParameter("email"),
    				request.getParameter("phone"),
    				request.getParameter("password"),
    	    		request.getParameter("addr"),
    	    		request.getParameter("property")
    	    			//회원가입창
    				);
    	}
    	else {
    		user = new Customer(		
			request.getParameter("type"),
			request.getParameter("name"),
			request.getParameter("email"),
			request.getParameter("phone"),
			request.getParameter("password"),
    		request.getParameter("addr")
    			//회원가입창
			);
    	}
    	
        log.debug("Create User : {}", user);

		try {
			UserManager manager = UserManager.getInstance(Integer.parseInt(request.getParameter("type")));
			if(user instanceof Customer) {
				Customer c = (Customer)user;
				manager.create(c);
			}
			else if(user instanceof Agent) {
				Agent a = (Agent)user;
				manager.create(a);
			}
	        return "redirect:/user/main";	// 성공 시 메인 화면으로 redirect
	        
		} catch (ExistingUserException e) {	// 예외 발생 시 입력 form으로 forwarding
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			return "/user/registerForm.jsp";
		}
    }
}
