package controller.item;

import java.net.URLEncoder;
import java.sql.Date;

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

public class UpdateItemController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(UpdateItemController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	String updateId = request.getParameter("item_no");
    	
    	User user = (User)request.getSession().getAttribute("who");
    	
    	ItemManager manager = ItemManager.getInstance();

    	if (request.getMethod().equals("GET")) {	
    		// GET request: 회원정보 수정 form 요청	
    		// 원래는 UpdateItemFormController가 처리하던 작업을 여기서 수행
        	
        	Item item = manager.findItem(Integer.parseInt(updateId));
        	int curA_no = item.getA_no();
    		int a_no = Integer.parseInt(user.getNO());
	
			if (curA_no == a_no) {
				// 현재 로그인한 사용자가 관리자이거나 수정 대상 사용자인 경우 -> 수정 가능
				item = manager.findItem(Integer.parseInt(updateId));	// 수정하려는 사용자 정보 검색
				request.setAttribute("item", item);			
				
				return "/item/updateForm.jsp";   // 검색한 물건 정보를 update form으로 전송     
			}   
			
			// 수정 불가능한 경우 -> 물건 정보 보기로 redirect
			return "redirect:/item/usersItemView?item_no=" + updateId + "&updateFailed=true"; 
	    }
	
    	// POST request (물건정보가 parameter로 전송됨)
    	Item updateItem = new Item(
    		Integer.parseInt(request.getParameter("item_no")),	//수정 불가
    		Integer.parseInt(request.getParameter("a_no")),		//수정 불가
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
    		Date.valueOf(request.getParameter("item_avail_date")),
    		request.getParameter("item_close_tf"),
    		Integer.parseInt(request.getParameter("item_hits")));
    	log.debug("Update Item : {}", updateItem);

		manager.update(updateItem);	
		request.setAttribute("item", updateItem);
		
        return "/item/usersItemView.jsp";			
    }
}