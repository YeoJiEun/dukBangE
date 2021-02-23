package controller.counsel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Schedule;
import model.User;
import model.service.CounselManager;

public class ScheduleListController implements Controller{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		User user = (User)request.getSession().getAttribute("who");
		int a_no = Integer.parseInt(user.getNO());
		CounselManager manager = CounselManager.getInstance();
		
		List<Schedule> sc = manager.findSchedule(a_no);
		
		request.setAttribute("schejule", sc);
		
		return "/user/scheduleList.jsp";	
	
	}
}
