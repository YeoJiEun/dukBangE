package controller.counsel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Counsel;
import model.service.CounselManager;

public class CounselDetailController implements Controller{

	  public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
			
	    	int counsel_no = Integer.parseInt(request.getParameter("counsel_no"));

	    	CounselManager manager = CounselManager.getInstance();
	    	String detail = manager.findCounselDetail(counsel_no);
	    	
	    	request.setAttribute("counsel_detail", detail);

	    	return "/user/counselDetail.jsp";
	  }	
}
