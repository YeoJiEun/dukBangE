package controller.wish;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.*;
import model.Basket;
import model.User;
import model.service.WishManager;
public class RemoveWishController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(RemoveWishController.class);

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		
		 User user = (User)request.getSession().getAttribute("who");
		 Basket wish = new Basket(Integer.parseInt(request.getParameter("item_no"))
				 ,Integer.parseInt(user.getNO()));
		
		System.out.println(wish.getC_no()+","+wish.getItem_no()); 
		 try {
			WishManager manager = WishManager.getInstance();
			manager.removeWish(wish);
			
	    	
	        return "redirect:/user/wish";
	        
		} catch (Exception e) {		// 예외 발생 시 입력 form으로 forwarding
            request.setAttribute("RemoveFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("wish",wish);
			return "/user/wishList.jsp";
		}
		
	}
	
}
