
package controller.counsel;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import model.*;
import model.service.CounselManager;
import model.service.ExistingUserException;

import java.util.ArrayList;
import java.sql.Date;

import java.util.List;

import controller.Controller;

public class CreateScheduleController implements Controller{
	
	public java.sql.Date transformDate(String year, String month, String day)
	{
	    String date = year+"-"+month+"-"+day;
	    java.sql.Date d = java.sql.Date.valueOf(date);
	    
	    return d;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		User user = (User)request.getSession().getAttribute("who");
		int a_no = Integer.parseInt(user.getNO());
		String y = request.getParameter("y");
		String m = request.getParameter("m");
		String d = request.getParameter("d");
		System.out.println("y ="+y+"m = "+m+"d=" +d);
		try {
			Date date = transformDate(y,m,d);
			CounselManager manager = CounselManager.getInstance();
	
			Schedule s = new Schedule(a_no, date);
		
			manager.CreateSchedule(s);	
		
		List<Schedule> sc = manager.findSchedule(a_no);
		
		request.setAttribute("schejule", sc);
				
		return "redirect:/user/scheduleList";			
		}
		catch (Exception e){
			request.setAttribute("schejule",null);
			return "redirect:/user/schedule/";
		}
		
	}
}
