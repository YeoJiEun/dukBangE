package controller.wish;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

import model.Item;
import model.User;
import model.service.WishManager;

public class WishListController implements Controller{
	    @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
	    	
	    	User user = (User)request.getSession().getAttribute("who");    
	
	    	try {
				WishManager manager = WishManager.getInstance();
				System.out.println(user.getNO());
				List<Item> item = manager.findWishList(Integer.parseInt(user.getNO()));	
				request.setAttribute("wishList", item);	

		    	
				return "/user/wishList.jsp";			
		    }
	    	catch(Exception e) {
	     		request.setAttribute("findFailed", true);
	    		request.setAttribute("exception", e);
	    		return "/user/wishList.jsp";
	    	}
	    	
	    
	    }
	}

	

