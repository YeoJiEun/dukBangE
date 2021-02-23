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
			
			request.setAttribute("item", item);		// ���� ���� ����	
			
			if (request.getParameter("updateFailed") != null) {
				// ���� �õ� ����
		    	request.setAttribute("exception", new IllegalStateException("���� ���� ������ �����߽��ϴ�."));            
				request.setAttribute("updateFailed", true);
			}
			else if (request.getParameter("deleteFailed") != null) {
				// ���� �õ� ����
		    	request.setAttribute("exception", new IllegalStateException("���� ������ �����߽��ϴ�."));
				request.setAttribute("deleteFailed", true);
			}
			
			return "/item/usersItemView.jsp";		// ����� ���� ȭ������ �̵�
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
			
			request.setAttribute("item", item);		// ���� ���� ����	
			request.setAttribute("agent", agent);
			request.setAttribute("address", address);	
			
			return "/item/view.jsp";		// ����� ���� ȭ������ �̵�
		}
    }
}
