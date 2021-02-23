package controller.point;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.customer.UserSessionUtils;
import model.Point;
import model.User;
import model.service.PaylogManager;
import model.service.UserManager;

public class ListPayLogController implements Controller {
	 @Override
	 public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		 //세션에 있는 이메일에서 a_no을 가져옴
		 String email = UserSessionUtils.getUserFromSession(request.getSession());
		 String type = (String) request.getSession().getAttribute("type");
			
		 UserManager managerUser = UserManager.getInstance(Integer.parseInt(type));
		 User user = managerUser.findUser(email);
		
		 String a_no = user.getNO();
		 
		 //a_no으로 pay log 기록 가져오기
		 try {
			 PaylogManager manager = PaylogManager.getInstance();
			 List<Point> pointList = new ArrayList<Point>();
		 
			 pointList = manager.findPaylogList(Integer.parseInt(a_no));
			 int currentPoint = manager.selectCurrentPoint(a_no);

			 //pointList 객체를 request에 저장하여 전달
			 request.setAttribute("pointList", pointList);
			 request.setAttribute("currentPoint", currentPoint);
			 
			 //pay log 보여주는 화면으로 이동 
			 return "/point/pointList.jsp"; 
		 }
		 catch(Exception e) {
			request.setAttribute("searchPaylogFailed", true);
			request.setAttribute("exception", e);
			return "/user/userInfo"; 
		 }
	 }
}
