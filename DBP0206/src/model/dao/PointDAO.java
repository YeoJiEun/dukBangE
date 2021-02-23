package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Agent;
import model.Item;
import model.Point;

public class PointDAO {
	private JDBCUtil jdbcUtil = null;
	
	public PointDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
	
	//InsertItemController���� ����
	public int selectCurrentPoint(String a_no) throws SQLException {
		int currentPoint = 0;
	
		String sql1 = "SELECT A_POINT FROM AGENT WHERE A_NO = ?";
		jdbcUtil.setSqlAndParameters(sql1, new Object[] {Integer.parseInt(a_no)});		// JDBCUtil�� query�� ����
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			if (rs.next()) 
				currentPoint = rs.getInt("A_POINT");			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		
		return currentPoint;
	}
	
	//InsertItemController���� ����
	public int insertPointLog(Point point) throws SQLException {		
		int result = 0;
		
		//1. agent�� ����Ʈ 10 ����
		String sql1 = "UPDATE AGENT SET a_point = a_point - 10 WHERE a_no = ?";
		jdbcUtil.setSqlAndParameters(sql1, new Object[] {point.getA_no()}); 
		
		try {
			result = jdbcUtil.executeUpdate();
		} catch(Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		
		//2. PAY_LOG ���̺� ���� ���
		String sql3 = "INSERT INTO PAY_LOG VALUES(?, ?, ?, ?)";
		Object[] param = new Object[] {point.getPay_no(), point.getA_no(), point.getPay_price(), point.getPay_date()};
			
		jdbcUtil.setSqlAndParameters(sql3, param); 

		try {
			result = jdbcUtil.executeUpdate();	
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}	
			
		return result;
	}
	
	
	//InsertItemController���� ����
	public int findLastPayNo() {
		String sql = "SELECT pay_no FROM PAY_LOG";
		jdbcUtil.setSqlAndParameters(sql, null);
		int pay_no = 0;
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			
			if(rs == null)		//pay log ���̺� ����� ������ ���� ���
				pay_no = 1;
			else {
				while (rs.next()) {						
					pay_no = rs.getInt("pay_no");	
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		
		return pay_no + 1;
	}
	
	
	//ListPayLogController���� ����
	public List<Point> fingPaylogList(int a_no) throws SQLException {
		String sql = "SELECT * FROM PAY_LOG WHERE a_no = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {a_no});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Point> pointList = new ArrayList<Point>();
			
			while(rs.next()) {
				Point point = new Point(
						rs.getInt("pay_no"),
						a_no,
						rs.getInt("pay_price"),
						rs.getDate("pay_date"));
				pointList.add(point);
			}
			return pointList;
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
}
