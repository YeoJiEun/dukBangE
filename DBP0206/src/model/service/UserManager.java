package model.service;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Agent;
import model.Customer;
import model.Schedule;
import model.User;
import model.dao.UserDAO;

public class UserManager {
	private static UserManager userMan = new UserManager();
	private UserDAO userDAO;
	private static int userType;

	private UserManager() {
		try {
			userDAO = new UserDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static UserManager getInstance(int type) {
		userType = type;
		return userMan;
	}
	
	public static UserManager getInstance() {
		return userMan;
	}
	
	public int create(Customer customer) throws SQLException, ExistingUserException {
		if (userDAO.existingUser(customer.getEMAIL(), userType) == true) {
			throw new ExistingUserException(customer.getEMAIL() + "�� �����ϴ� ���̵��Դϴ�.");
		}
		return userDAO.create(customer);
	}
	
	public int create(Agent agent) throws SQLException, ExistingUserException {
		if (userDAO.existingUser(agent.getEMAIL(), userType) == true) {
			throw new ExistingUserException(agent.getEMAIL() + "�� �����ϴ� ���̵��Դϴ�.");
		}
		return userDAO.create(agent);
	}

	public int update(Customer customer) throws SQLException, UserNotFoundException {
		return userDAO.update(customer);
	}	
	
	public int update(Agent agent) throws SQLException, UserNotFoundException {
		return userDAO.update(agent);
	}

	public int remove(String userId) throws SQLException, UserNotFoundException {
		return userDAO.remove(userId, userType);
	}

	public User findUser(String userId)
		throws SQLException, UserNotFoundException {
		User user = userDAO.findUser(userId, userType);
		
		if (user == null) {
			throw new UserNotFoundException(userId + "�� �������� �ʴ� ���̵��Դϴ�.");
		}		
		return user;
	}
	public User findUser(String userId,int type)
			throws SQLException, UserNotFoundException {
			User user = userDAO.findUser(userId, type);
			
			if (user == null) {
				throw new UserNotFoundException(userId + "�� �������� �ʴ� ���̵��Դϴ�.");
			}		
			return user;
		}
	
	public Customer findCustomer(String email) throws SQLException{
		Customer c = userDAO.findCustomer(email);
		return c;
	}

	public Customer findCustomer(int c_no) throws SQLException{
		Customer c = userDAO.findCustomer(c_no);
		return c;
	}
	
	public User findAgentInfo(int a_no) throws SQLException {
		return userDAO.findAgentInfo(a_no);
	}

	public User login(String userId, String password)
		throws SQLException, UserNotFoundException, PasswordMismatchException {
		User user = findUser(userId);

		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		return user;
	}
	
	public UserDAO getUserDAO() {
		return this.userDAO;
	}
	
	public ArrayList<Schedule> findAgentSchedule(String a_no) throws SQLException{
		ArrayList<Schedule> list = userDAO.FindAgentSchedule(a_no);

		return list;
	}
}
