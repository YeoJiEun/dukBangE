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
		 //���ǿ� �ִ� �̸��Ͽ��� a_no�� ������
		 String email = UserSessionUtils.getUserFromSession(request.getSession());
		 String type = (String) request.getSession().getAttribute("type");
			
		 UserManager managerUser = UserManager.getInstance(Integer.parseInt(type));
		 User user = managerUser.findUser(email);
		
		 String a_no = user.getNO();
		 
		 //a_no���� pay log ��� ��������
		 try {
			 PaylogManager manager = PaylogManager.getInstance();
			 List<Point> pointList = new ArrayList<Point>();
		 
			 pointList = manager.findPaylogList(Integer.parseInt(a_no));
			 int currentPoint = manager.selectCurrentPoint(a_no);

			 //pointList ��ü�� request�� �����Ͽ� ����
			 request.setAttribute("pointList", pointList);
			 request.setAttribute("currentPoint", currentPoint);
			 
			 //pay log �����ִ� ȭ������ �̵� 
			 return "/point/pointList.jsp"; 
		 }
		 catch(Exception e) {
			request.setAttribute("searchPaylogFailed", true);
			request.setAttribute("exception", e);
			return "/user/userInfo"; 
		 }
	 }
}
