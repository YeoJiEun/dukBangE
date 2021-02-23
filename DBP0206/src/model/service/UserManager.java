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
			throw new ExistingUserException(customer.getEMAIL() + "는 존재하는 아이디입니다.");
		}
		return userDAO.create(customer);
	}
	
	public int create(Agent agent) throws SQLException, ExistingUserException {
		if (userDAO.existingUser(agent.getEMAIL(), userType) == true) {
			throw new ExistingUserException(agent.getEMAIL() + "는 존재하는 아이디입니다.");
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
			throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
		}		
		return user;
	}
	public User findUser(String userId,int type)
			throws SQLException, UserNotFoundException {
			User user = userDAO.findUser(userId, type);
			
			if (user == null) {
				throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
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
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
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
