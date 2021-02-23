package controller.item;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Item;
import model.User;
import model.service.ItemManager;

public class UsersItemController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User)request.getSession().getAttribute("who");
		String a_no = user.getNO();

		ItemManager manager = ItemManager.getInstance();
		List<Item> itemList = new ArrayList<Item>();
		
   		try {
   			itemList = manager.findUsersItemList(Integer.parseInt(a_no));

   			//itemList ��ü�� �˻��� �ּҰ��� request�� �����Ͽ� ����
   			request.setAttribute("itemList", itemList);

   			//�� �Ź� ȭ������ �̵�(forwarding)
   			return "/item/usersItem.jsp";  
   		}
   		catch(Exception e) {
   			request.setAttribute("searchFailed", true);
   			request.setAttribute("exception", e);
   			return "/item/usersItem.jsp";
   		}
	}
}
