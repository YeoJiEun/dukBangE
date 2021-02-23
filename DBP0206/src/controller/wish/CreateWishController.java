package controller.wish;
import model.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;

import model.service.WishManager;

public class CreateWishController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(CreateWishController.class);

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
			User user = (User)request.getSession().getAttribute("who");
			Basket wish = new Basket(Integer.parseInt(request.getParameter("item_no")),Integer.parseInt(user.getNO()));		
	        System.out.println(request.getParameter("item_no")+" , "+user.getNO());
			try {
				WishManager manager = WishManager.getInstance();
				manager.createWish(wish);
				
		        return "redirect:/user/wish";	
		        
			} catch (Exception e) {		
				request.setAttribute("exception", e);
				return "redirect:/user/wish";
			}
	
	}

}
