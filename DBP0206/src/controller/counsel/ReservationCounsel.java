package controller.counsel;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Agent;
import model.Counsel;
import model.Item;
import model.Schedule;
import model.service.CounselManager;
import model.service.ExistingItemException;
import model.service.UserManager;

public class ReservationCounsel implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		if (request.getMethod().equals("GET")) {
			String item_no = request.getParameter("item_no");
			String agent_no = request.getParameter("agent_no");
			request.setAttribute("item", item_no);		// ���� ���� ����	
			request.setAttribute("agent", agent_no);
			
			UserManager manager = UserManager.getInstance();
			
			try {
				ArrayList<Schedule> sList = new ArrayList<Schedule>();
				sList = manager.findAgentSchedule(agent_no);
				request.setAttribute("sList", sList);
				
			} catch(Exception e) {
				request.setAttribute("searchFailed", true);
				request.setAttribute("exception", e);
				return "/item/view.jsp";
			}
			return "/item/counselForm.jsp";
        }
		
		String detail = (String) request.getParameter("detail");
		String date = (String) request.getParameter("checkDate");
		String item = request.getParameter("item");
		String agent = request.getParameter("agent");
		
		HttpSession session = request.getSession();
		int userNo = Integer.parseInt((String) session.getAttribute("userNo"));
		
		CounselManager manager = CounselManager.getInstance();
		
		Counsel counsel = new Counsel(
				Integer.parseInt(item),
				Integer.parseInt(agent),
				userNo,
				detail,
				date
				);
		try {
			manager.insert(counsel);
	        return "redirect:/user/counselList";	// ���� �� ����� ����Ʈ ȭ������ redirect
	        
		} catch (ExistingItemException e) {	// ���� �߻� �� �Է� form���� forwarding
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			return "/item/counselForm.jsp";
		}
		
	}

}
