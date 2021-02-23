package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Point;
import model.dao.PointDAO;

public class PaylogManager {
	private static PaylogManager paylogMan = new PaylogManager();
	private PointDAO pointDAO;
	
	
	private PaylogManager() {
		try {
			pointDAO = new PointDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}

	public static PaylogManager getInstance() {
		return paylogMan;
	}

	public int selectCurrentPoint(String a_no) throws SQLException {
		return pointDAO.selectCurrentPoint(a_no);
	}
	
	public int fingLastPayNo() throws SQLException {
		return pointDAO.findLastPayNo();
	}
	
	public int insertPointLog(Point point) throws SQLException, ExistingItemException {
		return pointDAO.insertPointLog(point);
	}

	public List<Point> findPaylogList(int a_no) throws SQLException {
		return pointDAO.fingPaylogList(a_no);
	}

}
