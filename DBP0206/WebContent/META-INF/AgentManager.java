package model.service;

import java.sql.SQLException;

import model.Agent;
import model.dao.AgentDAO;
public class AgentManager {
	private static AgentManager agentMan = new AgentManager();
	private AgentDAO aDAO;

	private AgentManager() {
		try {
			aDAO = new AgentDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static AgentManager getInstance() {
		return agentMan;
	}
	
	public int create(Agent agent) throws SQLException, ExistingUserException {
		if (aDAO.existingAgent(agent.getNO())== true) {
			throw new ExistingUserException(agent.getEMAIL() + "는 존재하는 이메일입니다.");
		}
		return aDAO.create(agent);
	}

	public int update(Agent agent) throws SQLException, UserNotFoundException {
		return aDAO.update(agent);
	}	

	public int remove(String a_no) throws SQLException, UserNotFoundException {
		return aDAO.remove(a_no);
	}
	
	public Agent findAgent(String a_email)
			throws SQLException, UserNotFoundException {
			Agent agent = aDAO.findAgent(a_email);
			
			if (agent == null) {
				throw new UserNotFoundException(a_email + "는 존재하지 않는 이메일입니다.");
			}		
			return agent;
		}

	public Agent login(String a_email, String password)
		throws SQLException, UserNotFoundException, PasswordMismatchException {
		Agent agent = findAgent(a_email);
		
		if (!agent.matchPassword(password)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return agent;
	}

	public AgentDAO getAgentDAO() {
		return this.aDAO;
	}
}
