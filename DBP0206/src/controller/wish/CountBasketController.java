package controller.wish;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Item;
import model.User;
import model.service.WishManager;

public class CountBasketController implements Controller {
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception 
	{
	   User user = (User)request.getSession().getAttribute("who");    
		
	   	try {
				WishManager manager = WishManager.getInstance();
				int count = manager.countBasket(Integer.parseInt(user.getNO()));	// 而ㅵ뜝�듅�뙋�삕�떚 �뜝�룞�삕�뜝�룞�삕 �뜝�떙�궪�삕			
				request.setAttribute("count", count);	// 而ㅵ뜝�듅�뙋�삕�떚 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕				
		    	return "redirect:/user/wishList.jsp";
		    }
	   	catch(Exception e) {
	    	request.setAttribute("findFailed", true);
	   		request.setAttribute("exception", e);
	   		return "/user/wishList.jsp";
	   	}
	   
   }
}
