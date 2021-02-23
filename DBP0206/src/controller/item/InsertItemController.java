package controller.item;

import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.customer.UserSessionUtils;
import model.Item;
import model.Point;
import model.User;
import model.service.ExistingItemException;
import model.service.ItemManager;
import model.service.PaylogManager;
import model.service.UserManager;

public class InsertItemController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(InsertItemController.class);
	
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {	
    	//1. ���ǿ� �ִ� �̸��Ͽ��� a_no�� ������
		User user = (User)request.getSession().getAttribute("who");
		String type = (String) request.getSession().getAttribute("type");

		String a_no = user.getNO();
    	
    	//2. a_no�� ���� ����Ʈ�� ��������
    	PaylogManager managerPoint = PaylogManager.getInstance();
    	int currentPoint = managerPoint.selectCurrentPoint(a_no);
    	
    	//3-1. ���� currentPoint ���� -�� ���� ��� �ȵǰ� alert("���� ����Ʈ�� 0 ����Ʈ�̹Ƿ� ���� ����� �Ұ����մϴ�.") ����
    	if(currentPoint <= 0) {
    		request.setAttribute("currentPointZeroFailed", true);
    		request.setAttribute("exception", "���� ����Ʈ�� 0 ����Ʈ�̹Ƿ� ���� ����� �Ұ����մϴ�.");
			return "/item/insertForm.jsp";
    	}
    	else {	//3-2. currentPoint ���� +�� (1)���� ����ϰ� (2)10 ����Ʈ ����, (3)pay_log ���̺� ���� ����ϱ�
    		//(1)���� ���
    		//item_no �� �Է��� select������ ������� �ִ� item_no�� �� ã�Ƽ� for���� ������ ������ no+1�� ���� ����ֱ�
        	ItemManager manager = ItemManager.getInstance();
    		int item_no = manager.findLastItemNo();
    		int item_hits = 0;		//�ʱ��� ���� ��ȸ���� 0
        	
        	Item item = new Item(
        		item_no,
        		Integer.parseInt(a_no),
    			request.getParameter("item_addr"),
    			request.getParameter("item_deal_type"),
    			request.getParameter("item_type"),
    			Integer.parseInt(request.getParameter("item_cost")),
    			Integer.parseInt(request.getParameter("item_deposit")),
    			Integer.parseInt(request.getParameter("item_layer")),
    			Integer.parseInt(request.getParameter("item_size")),
    			Integer.parseInt(request.getParameter("item_manage_cost")),
    			request.getParameter("item_park_tf"),
    			request.getParameter("item_ele_tf"),
    			request.getParameter("item_pet_tf"),
    			Date.valueOf(request.getParameter("item_avail_date1") + "-" + request.getParameter("item_avail_date2")
    								+ "-" + request.getParameter("item_avail_date3")),
    			"TRUE", item_hits);
    		
            log.debug("Insert Item : {}", item);
            
            try {
    			manager.insert(item); 	        
    		} catch (ExistingItemException e) {	// ���� �߻� �� �Է� form���� forwarding
                request.setAttribute("registerFailed", true);
    			request.setAttribute("exception", e);
    			request.setAttribute("item", item);
    			return "/item/insertForm.jsp";
    		}
            
            //(2) 10����Ʈ �����ϰ�, pay_log ���̺� ���� ���
            //pay_no �� �Է��� select������ ������� �ִ� item_no�� �� ã�Ƽ� for���� ������ ������ no+1�� ���� ����ֱ�
        	int pay_no = managerPoint.fingLastPayNo();
        
    		//a_no�� pay log�� insert��
    		Point point = new Point(pay_no, Integer.parseInt(a_no), 10, Date.valueOf(LocalDate.now()));

    		log.debug("Insert PayLog : {}", point);
    		
    		try {
    			managerPoint.insertPointLog(point);
    	        return "redirect:/item/userItem";	// ���� �� ����� ����Ʈ ȭ������ redirect
    	    } catch (ExistingItemException e) {	// ���� �߻� �� �Է� form���� forwarding
                request.setAttribute("registerFailed", true);
    			request.setAttribute("exception", e);
    			request.setAttribute("item", item);
    			return "/item/insertForm.jsp";
    	    }
    	}
    }
}
