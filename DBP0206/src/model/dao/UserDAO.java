package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Agent;
import model.Customer;
import model.Schedule;
import model.User;

public class UserDAO {
private JDBCUtil jdbcUtil = null;
	
	public UserDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public int create(Customer customer) throws SQLException {
		System.out.println(customer.getUSER_NO()+ 
				customer.getNAME()+ customer.getEMAIL()+ customer.getPHONE()+ 
				customer.getPWD()+ customer.getADDR());
		String sql = "INSERT INTO CUSTOMER VALUES (CUSTOMER_SEQ.nextval, ?, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] { customer.getUSER_NO(), 
							customer.getNAME(), customer.getEMAIL(), customer.getPHONE(), 
							customer.getPWD(), customer.getADDR()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;			
	}
	
	public int create(Agent agent) throws SQLException {
		String sql = "INSERT INTO AGENT VALUES (AGENT_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, 100, 'false')";		
		Object[] param = new Object[] {agent.getUSER_NO(), agent.getNAME(),
										agent.getEMAIL(), agent.getPHONE(), agent.getPWD(), 
										agent.getADDR(), agent.getOFFICE()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;			
	}
	
	public int update(Customer customer) throws SQLException {
		String sql = "UPDATE CUSTOMER SET C_NAME=?, C_PWD=?, C_PHONE=?, C_ADDR=? WHERE C_NO=?";
		Object[] param = new Object[] {customer.getNAME(), customer.getPWD(), customer.getPHONE(), customer.getADDR(),
									customer.getNO()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
		System.out.println(sql);
		for(int i = 0; i < param.length; i++)
		{
			System.out.print(param[i]);
		}
		try {				
			jdbcUtil.executeUpdate();	// update 문 실행
			
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}
	
	public int update(Agent agent) throws SQLException {
		String sql = "UPDATE AGENT "
					+ "SET A_NAME=?, A_PWD=?, A_PHONE=?, A_ADDR=?"
					+ "WHERE A_NO=?";
		Object[] param = new Object[] {agent.getNAME(), agent.getPWD(), agent.getPHONE(), agent.getADDR(),
										agent.getNO()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
			System.out.println("예외 발생 ㅡㅡㅅㅂ");
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}
	
	public int remove(String no, int type) throws SQLException {
		String sql = null;
		if(type == 1) {
			sql = "DELETE FROM CUSTOMER WHERE C_NO=?";
		}
		else if(type == 2) {
			sql = "DELETE FROM AGENT WHERE A_NO=?";
		}
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {no});	// JDBCUtil에 delete문과 매개 변수 설정

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}
	
	public boolean existingUser(String email, int type) throws SQLException {
		String sql = null;
		if(type == 1) {
			sql = "SELECT count(*) FROM CUSTOMER WHERE C_EMAIL=?";
		}
		else if(type == 2) {
			sql = "SELECT count(*) FROM AGENT WHERE A_EMAIL=?";
		}
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {email});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return false;
	}
	
	public User findUser(String email, int type) throws SQLException {
		if(type == 1) {
			Customer c = findCustomer(email);
			return c;
		}
		else {
			Agent a = findAgent(email);
			return a;
		}
	}
	public Customer findCustomer(int c_no) throws SQLException {
		 String sql = "SELECT * "
     			+ "FROM CUSTOMER "
     			+ "WHERE C_NO=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {c_no});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			if (rs.next()) {						
				Customer customer = new Customer(		
					rs.getString("C_NO"),
					rs.getString("USER_NO"),
					rs.getString("C_NAME"),
					rs.getString("C_EMAIL"),
					rs.getString("C_PHONE"),
					rs.getString("C_PWD"),					
					rs.getString("C_ADDR"));
				return customer;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	
	}
	
	public User findAgentInfo(int a_no) throws SQLException {
		String sql = "SELECT * FROM AGENT WHERE a_no=?";            
		jdbcUtil.setSqlAndParameters(sql, new Object[] {a_no});	

		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			if (rs.next()) {						
				Agent agent = new Agent(		
						rs.getString("A_NO"),
						rs.getString("USER_NO"),
						rs.getString("A_NAME"),
						rs.getString("A_EMAIL"),
						rs.getString("A_PHONE"),
						rs.getString("A_PWD"),					
						rs.getString("A_ADDR"),
						rs.getString("A_OFFICE"),
						rs.getString("A_POINT"),
						rs.getString("A_BAN")
				);
			return agent;
		}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	public Customer findCustomer(String email) throws SQLException {
        String sql = "SELECT * "
        			+ "FROM CUSTOMER "
        			+ "WHERE C_EMAIL=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {email});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			if (rs.next()) {						
				Customer customer = new Customer(		
					rs.getString("C_NO"),
					rs.getString("USER_NO"),
					rs.getString("C_NAME"),
					email,
					rs.getString("C_PHONE"),
					rs.getString("C_PWD"),					
					rs.getString("C_ADDR"));
				return customer;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	public Agent findAgent(String email) throws SQLException {
        String sql = "SELECT * "
        			+ "FROM AGENT "
        			+ "WHERE A_EMAIL=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {email});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			if (rs.next()) {						
				Agent agent = new Agent(		
					rs.getString("A_NO"),
					rs.getString("USER_NO"),
					rs.getString("A_NAME"),
					email,
					rs.getString("A_PHONE"),
					rs.getString("A_PWD"),					
					rs.getString("A_ADDR"),
					rs.getString("A_OFFICE"),
					rs.getString("A_POINT"),
					rs.getString("A_BAN")
					);
				return agent;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	public String[] findAllAgentEmail() throws SQLException {
		String[] email = null;
		int i = 0;
		String sql = "SELECT A_EMAIL FROM AGENT";              
		jdbcUtil.setSqlAndParameters(sql, null);	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			while (rs.next()) {						
				email[i] = rs.getString("A_EMAIL");System.out.println("유저디에오 이메일은 : " + email[i]);
				i++;
		}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return email;
	}
	
	public ArrayList<Schedule> FindAgentSchedule(String a_no) throws SQLException {
		String sql = "SELECT * " 
     		   + "FROM SCHEDULE where AVAILABLE_DATE > SYSDATE and A_NO=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {a_no});		
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();					
			ArrayList<Schedule> scheduleList = new ArrayList<Schedule>();	

			while (rs.next()) {
				Schedule item = new Schedule(			
					rs.getInt("SCHEDULE_NO"),
					rs.getInt("A_NO"),
					rs.getDate("AVAILABLE_DATE")
					);
				scheduleList.add(item);				
			}		
			return scheduleList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}

	
}
