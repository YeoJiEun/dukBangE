package controller.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Item;
import model.User;
import model.service.*;

public class ViewItemController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User)request.getSession().getAttribute("who");
		String item_no = request.getParameter("item_no");

		Item item = null;
		ItemManager manager = ItemManager.getInstance();
	
		User agent = null;
		UserManager managerUser = UserManager.getInstance();

		if(user != null && user.getUSER_NO().equals("2") && request.getParameter("where").equals("usersItem")) {
			try {
				item = manager.findItemUpdateHits(Integer.parseInt(item_no));
			} catch(Exception e) {
				e.printStackTrace();
				return "redirect:/item/usersItem";
			}
			
			request.setAttribute("item", item);		// 물건 정보 저장	
			
			if (request.getParameter("updateFailed") != null) {
				// 수정 시도 실패
		    	request.setAttribute("exception", new IllegalStateException("물건 정보 수정에 실패했습니다."));            
				request.setAttribute("updateFailed", true);
			}
			else if (request.getParameter("deleteFailed") != null) {
				// 삭제 시도 실패
		    	request.setAttribute("exception", new IllegalStateException("물건 삭제에 실패했습니다."));
				request.setAttribute("deleteFailed", true);
			}
			
			return "/item/usersItemView.jsp";		// 사용자 보기 화면으로 이동
		}
		else {
			String address = request.getParameter("address");

			try {
				item = manager.findItemUpdateHits(Integer.parseInt(item_no));	
				agent = managerUser.findAgentInfo(item.getA_no());
				
			} catch(Exception e) {
				e.printStackTrace();
				return "redirect:/item/list";
			}
			
			request.setAttribute("item", item);		// 물건 정보 저장	
			request.setAttribute("agent", agent);
			request.setAttribute("address", address);	
			
			return "/item/view.jsp";		// 사용자 보기 화면으로 이동
		}
    }
}
