package controller.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.Controller;
import controller.customer.UserSessionUtils;
import model.Item;
import model.User;
import model.service.ItemManager;
import model.service.UserManager;

import java.net.URLEncoder;

public class DeleteItemController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(DeleteItemController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String deleteId = request.getParameter("item_no");
		
    	log.debug("Delete Item : {}", deleteId);

    	User user = (User)request.getSession().getAttribute("who");
    	
    	ItemManager manager = ItemManager.getInstance();
    	Item item = manager.findItem(Integer.parseInt(deleteId));
    	int curA_no = item.getA_no();
   
		int a_no = Integer.parseInt(user.getNO());
		
    	//삭제 -> 물건을 올린 중개사만 가능
		if (a_no == curA_no) {	//로그인한 중개사의 이메일과 삭제할 물건을 올린 중개사의 이메일이 같으면
			manager.remove(Integer.parseInt(deleteId));			// 물건 정보 삭제
			return "redirect:/item/userItem";		// 사용자리스트로 이동
		}
    	
		/* 삭제가 불가능한 경우 */   
		return "redirect:/item/usersItemView?item_no=" + deleteId + "&deleteFailed=true";  	// redirect 실행
	}
}
