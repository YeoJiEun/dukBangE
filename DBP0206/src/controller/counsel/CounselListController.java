package controller.counsel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;
import model.service.CounselManager;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;

public class CounselListController implements Controller{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		User user = (User)request.getSession().getAttribute("who");
		int no = Integer.parseInt(user.getNO());

		List<Counsel> counsel = new ArrayList<Counsel>();
		CounselManager manager = CounselManager.getInstance();
		if(user.getUSER_NO().equals("1")) {
			counsel = manager.findCounselList(no);
			request.setAttribute("counselList", counsel);
		}
		else if(user.getUSER_NO().equals("2")){
			counsel = manager.findCounselList_A(no);
			request.setAttribute("counselList", counsel);
		}
		
		return "/user/counselList.jsp";			
	
	}
}
