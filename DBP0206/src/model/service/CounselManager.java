package model.service;

import java.sql.SQLException;
import java.util.List;

import model.*;
import model.dao.*;
public class CounselManager {
	private static CounselManager manager = new CounselManager();
	private static CounselDAO CounselDAO;
	private CounselManager() {
		try {
			CounselDAO = new CounselDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	public static CounselManager getInstance() {
		return manager;
	}
	public CounselDAO getCounselDAO() {
		return this.CounselDAO;
	}
	public int create(Counsel Counsel) throws SQLException, ExistingCounselException {
		if (CounselDAO.existingCounsel(Counsel.getC_no(),Counsel.getItem_no())== true) {
			throw new ExistingCounselException("이미 진행중인 상담입니다.");
		}
		return CounselDAO.createCounsel(Counsel);
	}

	public int remove(int c_no) throws SQLException, UserNotFoundException {
		return CounselDAO.removeCounsel(c_no);
	}
	public String findCounselDetail(int counsel_no) throws SQLException{
		return CounselDAO.findCounselDetail(counsel_no);
	}
	public List<Counsel> findCounselList(int c_no)
			throws SQLException, UserNotFoundException {
			List<Counsel> CounselList = CounselDAO.findCounselList(c_no);
				
		return CounselList;
	}
	public List<Counsel> findCounselList_A(int a_no)
			throws SQLException, UserNotFoundException {
			List<Counsel> CounselList = CounselDAO.findCounselList_A(a_no);
				
		return CounselList;
	}
	public List<Schedule> findSchedule(int a_no)
			throws SQLException, UserNotFoundException {
			List<Schedule> List = CounselDAO.findSchedule(a_no);
				
		return List;
	}
	public int CreateSchedule(Schedule s) {
		return CounselDAO.CreateSchedule(s);
	}
	
	public int insert(Counsel item) throws SQLException, ExistingItemException {
		return CounselDAO.createCounsel(item);
	}


}
