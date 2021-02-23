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
		
    	//���� -> ������ �ø� �߰��縸 ����
		if (a_no == curA_no) {	//�α����� �߰����� �̸��ϰ� ������ ������ �ø� �߰����� �̸����� ������
			manager.remove(Integer.parseInt(deleteId));			// ���� ���� ����
			return "redirect:/item/userItem";		// ����ڸ���Ʈ�� �̵�
		}
    	
		/* ������ �Ұ����� ��� */   
		return "redirect:/item/usersItemView?item_no=" + deleteId + "&deleteFailed=true";  	// redirect ����
	}
}
