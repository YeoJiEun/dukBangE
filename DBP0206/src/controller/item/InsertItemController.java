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
    	//1. 세션에 있는 이메일에서 a_no을 가져옴
		User user = (User)request.getSession().getAttribute("who");
		String type = (String) request.getSession().getAttribute("type");

		String a_no = user.getNO();
    	
    	//2. a_no의 현재 포인트를 가져오기
    	PaylogManager managerPoint = PaylogManager.getInstance();
    	int currentPoint = managerPoint.selectCurrentPoint(a_no);
    	
    	//3-1. 만약 currentPoint 값이 -면 물건 등록 안되고 alert("현재 포인트는 0 포인트이므로 물건 등록이 불가능합니다.") 띄우기
    	if(currentPoint <= 0) {
    		request.setAttribute("currentPointZeroFailed", true);
    		request.setAttribute("exception", "현재 포인트는 0 포인트이므로 물건 등록이 불가능합니다.");
			return "/item/insertForm.jsp";
    	}
    	else {	//3-2. currentPoint 값이 +면 (1)물건 등록하고 (2)10 포인트 차감, (3)pay_log 테이블에 정보 기록하기
    		//(1)물건 등록
    		//item_no 값 입력은 select문으로 현재까지 있는 item_no을 다 찾아서 for문을 돌리고 마지막 no+1한 값을 집어넣기
        	ItemManager manager = ItemManager.getInstance();
    		int item_no = manager.findLastItemNo();
    		int item_hits = 0;		//초기의 물건 조회수는 0
        	
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
    		} catch (ExistingItemException e) {	// 예외 발생 시 입력 form으로 forwarding
                request.setAttribute("registerFailed", true);
    			request.setAttribute("exception", e);
    			request.setAttribute("item", item);
    			return "/item/insertForm.jsp";
    		}
            
            //(2) 10포인트 차감하고, pay_log 테이블에 정보 기록
            //pay_no 값 입력은 select문으로 현재까지 있는 item_no을 다 찾아서 for문을 돌리고 마지막 no+1한 값을 집어넣기
        	int pay_no = managerPoint.fingLastPayNo();
        
    		//a_no에 pay log를 insert함
    		Point point = new Point(pay_no, Integer.parseInt(a_no), 10, Date.valueOf(LocalDate.now()));

    		log.debug("Insert PayLog : {}", point);
    		
    		try {
    			managerPoint.insertPointLog(point);
    	        return "redirect:/item/userItem";	// 성공 시 사용자 리스트 화면으로 redirect
    	    } catch (ExistingItemException e) {	// 예외 발생 시 입력 form으로 forwarding
                request.setAttribute("registerFailed", true);
    			request.setAttribute("exception", e);
    			request.setAttribute("item", item);
    			return "/item/insertForm.jsp";
    	    }
    	}
    }
}
