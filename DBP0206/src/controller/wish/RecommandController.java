package controller.wish;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Item;
import model.User;
import model.service.WishManager;

public class RecommandController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(RecommandController.class);
	
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		//int c_no = Integer.parseInt(request.getParameter("c_no"));
		User user = (User)request.getSession().getAttribute("who");
		try {
			WishManager manager = WishManager.getInstance();
			
			
			
			List<Item> item = new ArrayList<Item>();
			
			item = manager.recommand(Integer.parseInt(user.getNO()));	
			request.setAttribute("recommand", item);				
			
	   
	        return "/user/recommand.jsp";	
	        // ���� �� �Ź� ȭ������ redirect 
	        
		} catch (Exception e) {		// ���� �߻� �� �Է� form���� forwarding
            request.setAttribute("RecommandFailed", true);
			request.setAttribute("exception", e);
		
			return "redirect:/user/wish";
		}
	
	
	
	}

}
