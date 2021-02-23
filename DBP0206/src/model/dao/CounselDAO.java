package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.*;
import controller.*;

public class CounselDAO {

	private JDBCUtil jdbcUtil = null;
	public CounselDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}

	public List<Counsel> findCounselList(int c_no) throws SQLException {
        String sql = "SELECT c.counsel_no, a.a_name,c.item_no,c.counsel_date,c.counsel_tf, c.a_no FROM counsel c, agent a WHERE c.a_no = a.a_no and c.c_no = ?";
     		 
		jdbcUtil.setSqlAndParameters(sql, new Object[] {c_no});		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Counsel> List = new ArrayList<Counsel>();	// User���� ����Ʈ ����
			while (rs.next()) {
			Counsel c = new Counsel(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
					rs.getInt("counsel_no"),
					rs.getInt("item_no"),
					rs.getInt("a_no"),c_no,null,	
					rs.getString("counsel_date").split(" ")[0],
					rs.getString("counsel_tf"),
					rs.getString("a_name"));
				List.add(c);				// List�� User ��ü ����
			}		
			return List;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	public List<Counsel> findCounselList_A(int a_no) throws SQLException {
        String sql = "SELECT c.counsel_no, a.c_name,c.item_no,c.counsel_date,c.counsel_tf, c.c_no FROM counsel c, customer a WHERE c.c_no = a.c_no and c.a_no = ?";
     		 
		jdbcUtil.setSqlAndParameters(sql, new Object[] {a_no});		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Counsel> List = new ArrayList<Counsel>();	// User���� ����Ʈ ����
			while (rs.next()) {
			Counsel c = new Counsel(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
					rs.getInt("counsel_no"),
					rs.getInt("item_no"),a_no,
					rs.getInt("c_no"),null,	
					rs.getString("counsel_date").split(" ")[0],
					rs.getString("counsel_tf"),
					rs.getString("c_name"));
				List.add(c);				// List�� User ��ü ����
			}		
			return List;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	public String findCounselDetail(int counsel_no) throws SQLException {
        String sql = "SELECT counsel_detail FROM counsel WHERE counsel_no = ?";
     		 
		jdbcUtil.setSqlAndParameters(sql, new Object[] {counsel_no});		// JDBCUtil�� query�� ����
		String c = "";			
		try {
			ResultSet rs = jdbcUtil.executeQuery();			
			if (rs.next()) {
			  c = rs.getString("counsel_detail");
			}		
			return c;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	public List<Schedule> findSchedule(int a_no) throws SQLException {
        String sql = "SELECT schedule_no, available_date FROM schedule WHERE a_no = ?";
     		 
		jdbcUtil.setSqlAndParameters(sql, new Object[] {a_no});		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Schedule> List = new ArrayList<Schedule>();	// User���� ����Ʈ ����
			while (rs.next()) {
			Schedule c = new Schedule(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
					rs.getInt("schedule_no"),a_no,
					rs.getDate("available_date"));
				List.add(c);				// List�� User ��ü ����
			}		
			return List;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	public int createCounsel(Counsel c) {
		String sql = "INSERT INTO COUNSEL VALUES (COUNSEL_SEQ.nextval, ?,?,?,?,?,'FALSE')";		
		try {		
			java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");  // ����: ���� ��Ÿ���� MM�� �빮��
			java.util.Date utilDate = df.parse(c.getCounsel_date());        
			Object[] param = new Object[] { c.getItem_no(),c.getA_no(),
					c.getC_no(), c.getCounsel_detail(), new java.sql.Date(utilDate.getTime()) };				
			jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
			
			int result = jdbcUtil.executeUpdate();	// insert �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;			
	}
	
	public int CreateSchedule(Schedule s) {
		String sql = "INSERT INTO SCHEDULE VALUES (SCHEDULE_SEQ.nextval,?,?)";		
		Object[] param = new Object[] {s.getA_no(), s.getAvaliable_date() };				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
		try {				
			int result = jdbcUtil.executeUpdate();	// insert �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;			
	}	
	
	public int removeCounsel(int c_no) {
		String sql = "DELETE FROM COUNSEL WHERE c_no = ?, counsel_tf = 'TRUE'";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {c_no});	// JDBCUtil�� delete���� �Ű� ���� ����

		try {				
			int result = jdbcUtil.executeUpdate();	// delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
	
	public boolean existingCounsel(int c_no, int item_no) throws SQLException {
		String sql = "SELECT count(*) FROM COUNSEL WHERE c_no=?, item_no = ?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {c_no, item_no});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count >= 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return false;
	}
	

}
